package test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.FechaActual;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageAcepta;
import pages.PageDec5;
import pages.PageFirmadoPorTodos;
import pages.PageLoginAdm;
import pages.PageMiPortal;
import pages.PagePendientes;
import pages.PageProcesoFirma;
import pages.PageRechazados;
import pages.PageReporteriaEscritorioAcepta;

public class Test_Reporteria {
	private WebDriver driver;
	private String downloadFilePath = Configuration.ROOT_DIR+"Downloads/";
	String datapool = Configuration.ROOT_DIR+"DataPool.xlsx";
	LeerExcel leerExcel = new LeerExcel();
	
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
//		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");	
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups",0);//Deshabilito abrir explorador de archivo 
		chromePrefs.put("download.default_directory", downloadFilePath);//Coloco el archivo descargado en la ruta que le indico
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);//Preferencias de chrome
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("https://5cap.dec.cl/");// Aquí se ingresa la URL para hacer las pruebas.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	// Miércoles 07/07/2021
	
	@Test
	public void Script_01254() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1254";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").equals("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").equals("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FIRMANTE") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ESTADO FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADO DOCUMENTO") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("FECHA FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		// cerrar ventana 2
		driver.close();
		// cambiar de ventana, a la principal
		driver.switchTo().window(mainWin);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	/*
	@Test
	public void Script_01255() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1255";
		String titulo_PopUp = "dec5cap.acepta.com dice";
		String texto_PopUp = "Usted ya tiene un reporte en ejecucion";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()='Usted ya tiene un reporte en ejecucion']")).getText();
		System.out.println(driver.findElement(By.xpath("//*[text()='Usted ya tiene un reporte en ejecucion']")).getText());
		Thread.sleep(6000);
		
		
		//driver.switchTo().alert().toString();
		System.out.println("Alertas:");
		System.out.println(driver.switchTo().alert().getText());
		System.out.println(driver.switchTo().alert().toString());
		Thread.sleep(6000);
	}
	*/
	
	@Test
	public void Script_01255() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1255";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").equals("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").equals("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FIRMANTE") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ESTADO FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADO DOCUMENTO") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("FECHA FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		// cerrar ventana 2
		driver.close();
		// cambiar de ventana, a la principal
		driver.switchTo().window(mainWin);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_01256() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1256";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").equals("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").equals("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FIRMANTE") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ESTADO FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADO DOCUMENTO") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("FECHA FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		// cerrar ventana 2
		driver.close();
		// cambiar de ventana, a la principal
		driver.switchTo().window(mainWin);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_01257() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1257";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").equals("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").equals("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FIRMANTE") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ESTADO FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADO DOCUMENTO") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("FECHA FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		// cerrar ventana 2
		driver.close();
		// cambiar de ventana, a la principal
		driver.switchTo().window(mainWin);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_01258() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1258";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").equals("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").equals("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FIRMANTE") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ESTADO FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADO DOCUMENTO") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("FECHA FIRMA") &&
				
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		// cerrar ventana 2
		driver.close();
		// cambiar de ventana, a la principal
		driver.switchTo().window(mainWin);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_01259() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1259";
		System.out.println(cp);
		String resultado = null;
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickCubo(cp);
		pageDec5.SeleccionarOpcionCubo(cp, "Reportería");
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				//driver.close();
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Estado de Firma de Docs.");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").equals("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").equals("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		// cerrar ventana 2
		driver.close();
		// cambiar de ventana, a la principal
		driver.switchTo().window(mainWin);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
}
