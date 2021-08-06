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
import org.openqa.selenium.Alert;
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
		driver.navigate().to("https://5qa.dec.cl/");// Aquí se ingresa la URL para hacer las pruebas.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	// Miércoles 07/07/2021
	
	@Test
	public void Script_01254() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1254";
		// Reporteria - reporte - estado de firma de docs
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
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FIRMANTE") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ESTADO FIRMA") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADO DOCUMENTO") &&
				
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
	public void Script_01255() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1255";
		// Reporteria - reporte - estado de firma de docs - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01256() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1256";
		// Reporteria - reporte - estado de firma de docs - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01257() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1257";
		// Reporteria - reporte - quienes estan pendiente de firma
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Quienes están pendiente de firma");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("ROL") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("FECHA CREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("RUT") &&
				
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
		// Reporteria - reporte - quienes estan pendiente de firma - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Quienes están pendiente de firma");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01259() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1259";
		// Reporteria - reporte - quienes estan pendiente de firma - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Quienes están pendiente de firma");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01260() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1260";
		// Reporteria - reporte - quienes han firmado
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Quienes han firmado");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[10]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("RUT") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ROL") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("FECHA CREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("FECHA FIRMA") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[10]")).getText().contains("TIPO") &&
				
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
	
	// Lunes 02/08/2021
	
	@Test
	public void Script_01261() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1261";
		// Reporteria - reporte - quienes han firmado - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Quienes han firmado");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01262() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1262";
		// Reporteria - reporte - quienes han firmado - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Quienes han firmado");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	
	@Test
	public void Script_01263() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1263";
		// Reporteria - reporte - documentos con el flujo de firma completo
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos con el flujo de firma completo");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("FECHA CREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("FECHA ULT ACCION") &&
				
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
	public void Script_01264() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1264";
		// Reporteria - reporte - documentos con el flujo de firma completo - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos con el flujo de firma completo");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01265() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1265";
		// Reporteria - reporte - documentos con el flujo de firma completo - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos con el flujo de firma completo");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01266() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1266";
		// Reporteria - reporte - documentos por RUT / Rol
		String texto_esperado = "Debe ingresar Rut Firmante";
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos por RUT / Rol");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		

		System.out.println("imprimir mensaje");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div/label")).getText());
		
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div/label")).getText().contains(texto_esperado)){
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
	public void Script_01267() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1267";
		// Reporteria - reporte - documentos por RUT / Rol - rut ok
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos por RUT / Rol");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.RutFirmante(cp, "13712759-8");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FECCREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ROL FIRMANTE") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("ESTADOFIRMA") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("VISUALIZACION") &&
				
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
	public void Script_01268() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1268";
		// Reporteria - reporte - documentos por RUT / Rol - rut ok - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos por RUT / Rol");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.RutFirmante(cp, "13712759-8");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01269() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1269";
		// Reporteria - reporte - documentos por RUT / Rol - rut ok - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos por RUT / Rol");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.RutFirmante(cp, "13712759-8");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01270() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1270";
		// Reporteria - reporte - envio de recordatorios (pendientes)
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Envío de Recordatorios (Pendientes)");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("FECHA CREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("ROL") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("CORREO") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("ACCIONES") &&
				
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
	public void Script_01271() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1271";
		// Reporteria - reporte - envio de recordatorios (pendientes) - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Envío de Recordatorios (Pendientes)");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01272() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1272";
		// Reporteria - reporte - envio de recordatorios (pendientes) - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos por RUT / Rol");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.RutFirmante(cp, "13712759-8");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01273() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1273";
		// Reporteria - reporte - visor de documentos
		String texto_esperado = "Debe ingresar Codigo de Documento";
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Visor de Documentos");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		

		System.out.println("imprimir mensaje");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div/label")).getText());
		
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div/label")).getText().contains(texto_esperado)){
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
	public void Script_01274() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1274";
		// Reporteria - reporte - visor de documentos - cod documento
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Visor de Documentos");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.CodigoDocumento(cp, "CA80000002DC488ZZ2");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("FECCREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("VISUALIZACION") &&
				
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
	public void Script_01275() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1275";
		// Reporteria - reporte - visor de documentos - cod documento - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Visor de Documentos");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.CodigoDocumento(cp, "CA80000002DC488ZZ2");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01276() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1276";
		// Reporteria - reporte - visor de documentos - cod documento - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Visor de Documentos");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.CodigoDocumento(cp, "CA80000002DC488ZZ2");
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	
	@Test
	public void Script_01277() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1277";
		// Reporteria - reporte - documentos creados
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos Creados");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[6]")).getText().contains("ESTADO DOCUMENTO") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("FECCREACION") &&
				
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
	public void Script_01278() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1278";
		// Reporteria - reporte - documentos creados - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos Creados");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01279() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1279";
		// Reporteria - reporte - documentos creados - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Documentos Creados");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	
	@Test
	public void Script_01280() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1280";
		// Reporteria - reporte - cartola
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Cartola");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("CODIGO") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("ESTADO") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("FECCREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("INSTITUCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[9]")).getText().contains("TIPO DCTO") &&
				
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
	
	
	// Martes 03/08/2021
	
	@Test
	public void Script_01281() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1281";
		// Reporteria - reporte - cartola - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Cartola");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01282() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1282";
		// Reporteria - reporte - cartola - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Cartola");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	
	@Test
	public void Script_01283() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1283";
		// Reporteria - reporte - participantes de un flujo
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
			}
		}
		
		String palabra = driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText();
		System.out.println("Imprimiendo palabra:");
		System.out.println(palabra);
		
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Participantes de un flujo");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(2000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
	
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[2]")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[1]/input")).getAttribute("value").contains("Exportar") && 
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/div[1]/div[2]/input")).getAttribute("value").contains("Descargar PDFs") &&
				driver.findElement(By.xpath("//*[@id=\"grilla_busqueda_dec\"]/div/label[1]")).getText().contains("Mostrando del "+"1"+" al "+filas+" de "+filas+" Registros") &&	
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[3]")).getText().contains("ULTIMO FIRMANTE") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[4]")).getText().contains("DESCRIPCION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[5]")).getText().contains("CODDOC") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[7]")).getText().contains("FECCREACION") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_busqueda_dec\"]/thead/tr/th[8]")).getText().contains("FIRMANTES EXTENDIDOS") &&
				
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
	public void Script_01284() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1284";
		// Reporteria - reporte - participantes de un flujo - exportar
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Participantes de un flujo");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnExportar(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	@Test
	public void Script_01285() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1285";
		// Reporteria - reporte - participantes de un flujo - descargar pdf's
		String texto_esperado = "Usted ya tiene un reporte en ejecucion";
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
			}
		}
		
		PageReporteriaEscritorioAcepta pageReporteriaEscritorioAcepta = new PageReporteriaEscritorioAcepta(driver);
		pageReporteriaEscritorioAcepta.SeleccionarReporte(cp, "Participantes de un flujo");
		pageReporteriaEscritorioAcepta.FechaEmision(cp);
		pageReporteriaEscritorioAcepta.BtnBuscar(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(3);
		robot.mouseWheel(3);
		Thread.sleep(4000);
		
		pageReporteriaEscritorioAcepta.BtnDescargarPdfs(cp);
		
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		
		
		if(texto_alerta.equals(texto_esperado)){
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
	}
	
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
}
