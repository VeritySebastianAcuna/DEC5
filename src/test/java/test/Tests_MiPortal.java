package test;

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
import common.CrearRut;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageDec5;
import pages.PageLoginAdm;
import pages.PageMiPortal;
import pages.PagePendientes;
import pages.PageProcesoFirma;

public class Tests_MiPortal {
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
	
	@Test
	public void Script_0710() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0710";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.BusquedaEtiquetas(cp,datos[3]);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::label[1]")).getText().equals(datos[3])) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0711() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0711";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.BusquedaEtiquetas(cp,datos[3]);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::td[1]")).getText().equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0715() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0715";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickCarpetas(cp);
		pagePendientes.AgregarCarpetaYaExistente(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div/div/div/span")).getText().equals("Carpeta ya existe")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0716() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0716";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickCarpetas(cp);
		pagePendientes.CrearNuevaCarpeta(cp);
		pagePendientes.BtnCancelarNuevaCarpeta(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed());
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0718() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0718";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickCarpetas(cp);
		pagePendientes.BtnFiltrarCarpetas(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0720() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0720";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickCarpetas(cp);
		int cantidadAntes = pagePendientes.CantidadCarpeta(cp);
		pagePendientes.SeleccionarCarpeta(cp);
		pagePendientes.LinkEliminarCarpeta(cp);
		pagePendientes.EliminarCarpeta(cp);
		int cantidadDespues = pagePendientes.CantidadCarpeta(cp);
		
		System.out.println(cantidadAntes);
		System.out.println(cantidadDespues);
		
		if(cantidadDespues<cantidadAntes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0723() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0723";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);
		pagePendientes.CambiarEstadoFiltro(cp, "PENDIENTE");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		String estado = driver.findElement(By.id("__tag_status")).getText();
		estado=estado.substring(8, estado.length());
		
		if("PENDIENTE".contains(estado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0724() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0724";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);
		pagePendientes.CambiarEstadoFiltro(cp, "EN PROCESO DE FIRMA");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		String estado = driver.findElement(By.id("__tag_status")).getText();
		estado=estado.substring(8, estado.length());
		
		if("EN PROCESO DE FIRMA".contains(estado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0725() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0725";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);
		pagePendientes.CambiarEstadoFiltro(cp, "FIRMADO POR TODOS");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		String estado = driver.findElement(By.id("__tag_status")).getText();
		estado=estado.substring(8, estado.length());
		
		if("FIRMADO POR TODOS".contains(estado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0726() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0726";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);
		pagePendientes.CambiarEstadoFiltro(cp, "RECHAZADO");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		String estado = driver.findElement(By.id("__tag_status")).getText();
		estado=estado.substring(8, estado.length());
		
		if("RECHAZADO".contains(estado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0727() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0727";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);
		pagePendientes.CambiarEstadoFiltro(cp, "TODOS");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		String estado = driver.findElement(By.id("__tag_status")).getText();
		estado=estado.substring(8, estado.length());
		
		if("TODOS".contains(estado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0728() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0728";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);	
		pagePendientes.FechaDesde(cp);
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0729() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0729";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);	
		pagePendientes.FechaHasta(cp);
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0730() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0730";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);	
		pagePendientes.FechaDesde(cp);
		pagePendientes.FechaHasta(cp);
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0732() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0732";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);	
		pagePendientes.TipoDocumento(cp,"010203");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0733() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0733";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickFiltrosAvanzados(cp);	
		pagePendientes.TipoDocumento(cp,"noexistedocumento");
		pagePendientes.BtnFiltrarFiltrosAvanzados(cp);
		String mensaje = pagePendientes.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0734() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0734";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.BtnMostrarOcultos(cp);
		String mensaje = pagePendientes.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0735() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0735";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.BtnMostrarOcultos(cp);
		pagePendientes.BtnMostrarOcultos(cp);
		String mensaje = pagePendientes.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0738() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0738";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp,"Agregar a");
		pagePendientes.MoverA(cp);
		pagePendientes.BtnMover(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0740() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0740";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.AgregarCarpeta(cp);
		pagePendientes.BtnCrearCarpeta(cp);
		String nombre = "nuevaCarpetaQa1";
		pagePendientes.CrearCarpeta(cp, nombre);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0741() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0741";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.AgregarCarpeta(cp);
		pagePendientes.CerrarMoverCrearCarpeta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0744() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0744";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.AgregarEtiquetas(cp);
		pagePendientes.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0749() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0749";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Vincular");
		pagePendientes.CerrarVincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0752() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0752";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		pagePendientes.DesvincularDocumento(cp);
		pagePendientes.BtnSiDesvincularDocumento(cp);
		pagePendientes.BtnCerrarDesvincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0753() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0753";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		pagePendientes.DesvincularDocumento(cp);
		pagePendientes.BtnNoDesvincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0755() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0755";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		
		pagePendientes.BtnCrearComentario(cp);
		pagePendientes.IngresarComentario(cp, "Prueba QA");
		Thread.sleep(2000);
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		pagePendientes.BtnComentar(cp);

		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0756() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0756";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		
		pagePendientes.BtnCrearComentario(cp);
		Thread.sleep(2000);
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		pagePendientes.BtnCancelar(cp);

		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0758() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0758";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Cancelar");
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp); 
		}
		else{
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0763() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0763";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Vincular");
		pagePendientes.CerrarVincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else{
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0765() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0765";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Agregar a");
		pagePendientes.MoverA(cp);
		pagePendientes.BtnMover(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0767() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0767";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Agregar a");
		pagePendientes.MoverA(cp);
		pagePendientes.BtnCrearCarpeta(cp);
		String nombre = "nuevaCarpetaQaprueba1";
		pagePendientes.CrearCarpeta(cp, nombre);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0768() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0768";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Agregar a");
		pagePendientes.CerrarMoverCrearCarpeta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0771() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0771";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Etiquetar");
		pagePendientes.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0775() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0775";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Compartir");
		pagePendientes.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pagePendientes.CompartirDocumentoRut(cp, datos[1]);
		pagePendientes.CompartirDocumentoBtnAgregar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/span")).getText().equals("RUT Inválido") && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[3]/span")).getText().equals("Email Inválido")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0776() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0776";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Compartir");
		pagePendientes.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		
		CrearRut crearRut = new CrearRut();
		String rut = crearRut.RutPersona();
				
		pagePendientes.CompartirDocumentoRut(cp,rut);
		pagePendientes.CompartirDocumentoIngresarMail(cp, "asd@asd.cl");
		pagePendientes.CompartirDocumentoBtnAgregar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/span[1]")).getText().equals("Usuario no registrado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0779() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0779";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Compartir");
		pagePendientes.CompartirDocumentoTipoPersona(cp, "ACEPTA");
		pagePendientes.CompartirDocumentoSeleccionRol(cp);
		pagePendientes.CompartirDocumentoBtnAgregar(cp);
		pagePendientes.CompartirDocumentoBtnCompartir(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0781() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0781";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Ocultar");
		pagePendientes.BtnMover(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0782() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0782";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Ocultar");
		pagePendientes.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0784() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0784";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.BtnMostrarOcultos(cp);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Mostrar");
		pagePendientes.BtnMover(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0785() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0785";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.BtnMostrarOcultos(cp);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Mostrar");
		pagePendientes.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0787() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0787";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Traza");
		
		File folder = new File(downloadFilePath);
		File[] archivosAntes = folder.listFiles();
		pagePendientes.TrazaBtnDescargaPdf(cp);
		File[] archivosDespues = folder.listFiles();

		if(archivosDespues.length>archivosAntes.length) {
			crearLogyDocumento.CasoOk(cp);
		}
		else{
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0788() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0788";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Traza");
		pagePendientes.TrazaBtnClose(cp);
		
		if(driver.findElement(By.id("modaltraza")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
//	@Test
	public void Script_0797() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0797";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Rechazar");
		pagePendientes.RechazarDocumentoComentario(cp, "Rechazo QA");
		pagePendientes.RechazarDocumentoConClave(cp);
		pagePendientes.RechazarDocumentoIngresoClave(cp, datos[1]);
		pagePendientes.RechazarDocumentoBtnRechazar(cp);
		
		// SE DEBE COMPLETAR YA QUE LA CLAVE DEL USUARIO CON EL QUE HACEMOS LOGIN NO SIRVE PARA RECHAZAR
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0798() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0798";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Rechazar");
		pagePendientes.RechazarDocumentoComentario(cp, "Rechazo QA");
		pagePendientes.RechazarDocumentoConClave(cp);
		pagePendientes.RechazarDocumentoIngresoClave(cp, datos[1]);
		pagePendientes.RechazarDocumentoBtnRechazar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[2]/div/span")).getText().equals("Clave incorrecta.")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0799() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0799";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Rechazar");
		pagePendientes.RechazarDocumentoComentario(cp, "Rechazo QA");
		pagePendientes.RechazarDocumentoConHuella(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal_error\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
//	@Test
	public void Script_0805() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0805";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickPendientes(cp);
		
		PagePendientes pagePendientes = new PagePendientes(driver);
		int i=0;
		int j=1;
		do {
			pagePendientes.ClickRegistroPorParametro(cp,j);
			pagePendientes.BarraHerramientas(cp, "Firmar");
			if(driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button")).isDisplayed()) {
				//COMPLETAR
			}
		}while(i==0);
		pagePendientes.RechazarDocumentoComentario(cp, "Rechazo QA");
		pagePendientes.RechazarDocumentoConHuella(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal_error\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0819() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0819";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.IngresoEtiqueta(cp, "contrato");
		pageProcesoFirma.BuscarEtiqueta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/label")).getText().contains("Contrato")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
			
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0820() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0820";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.IngresoEtiqueta(cp, "AJKLÑO");
		pageProcesoFirma.BuscarEtiqueta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::td[1]")).getText().equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
			
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0823() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0823";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickCarpetas(cp);
		pageProcesoFirma.CrearNuevaCarpeta(cp);
		String nombre = "nuevaCarpetaQa111";
		pageProcesoFirma.CrearCarpeta(cp, nombre);
		
		crearLogyDocumento.CasoOk(cp);
			
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0824() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0824";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickCarpetas(cp);
		pageProcesoFirma.AgregarCarpetaYaExistente(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div/div/div/span")).getText().equals("Carpeta ya existe")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0825() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0825";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickCarpetas(cp);
		pageProcesoFirma.CrearNuevaCarpeta(cp);
		pageProcesoFirma.BtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0827() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0827";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickCarpetas(cp);
		pageProcesoFirma.BtnFiltrarCarpetas(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0829() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0829";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickCarpetas(cp);
		
		int cantidadAntes = pageProcesoFirma.CantidadCarpeta(cp);
		pageProcesoFirma.SeleccionarCarpeta(cp);
		pageProcesoFirma.LinkEliminarCarpeta(cp);
		pageProcesoFirma.EliminarCarpeta(cp);
		int cantidadDespues = pageProcesoFirma.CantidadCarpeta(cp);
		
		if(cantidadDespues<cantidadAntes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0830() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0830";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickCarpetas(cp);
		
		int cantidadAntes = pageProcesoFirma.CantidadCarpeta(cp);
		pageProcesoFirma.SeleccionarCarpeta(cp);
		pageProcesoFirma.LinkEliminarCarpeta(cp);
		pageProcesoFirma.EliminarCarpeta(cp);
		int cantidadDespues = pageProcesoFirma.CantidadCarpeta(cp);
		
		if(cantidadDespues<cantidadAntes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0832() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0832";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"__tag_status\"]")).getText().contains("EN PROCESO")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0833() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0833";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("En Proceso de Firma")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0834() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0834";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.CambiarEstadoFiltro(cp, "TODOS");
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0835() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0835";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.CambiarEstadoFiltro(cp, "RECHAZADO");
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0836() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0836";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.CambiarEstadoFiltro(cp, "TODOS");
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0837() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0837";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.FechaDesde(cp);
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_from\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0838() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0838";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.FechaHasta(cp);
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_to\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0839() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0839";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.FechaDesde(cp);
		pageProcesoFirma.FechaHasta(cp);
		pageProcesoFirma.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_from\"]/button")).isDisplayed() && 
				driver.findElement(By.xpath("//*[@id=\"__tag_date_to\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0841() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0841";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.TipoDocumento(cp, "Contrato");
		pageProcesoFirma.BtnFiltrar(cp);
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/label")).getText().contains("Contrato")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0842() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0842";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickFiltrosAvanzados(cp);
		pageProcesoFirma.TipoDocumento(cp, "123NM");
		pageProcesoFirma.BtnFiltrar(cp);

		String mensaje = pageProcesoFirma.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0843() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0843";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.BtnMostrarOcultos(cp);

		String mensaje = pageProcesoFirma.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0844() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0844";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.BtnMostrarOcultos(cp);
		pageProcesoFirma.BtnMostrarOcultos(cp);

		String mensaje = pageProcesoFirma.MensajeSinResultados(cp);
		
		if(mensaje.equals("dataTables_empty") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0847() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0847";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.AgregarCarpeta(cp);
		pageProcesoFirma.MoverA(cp);
		pageProcesoFirma.BtnMover(cp);

		if(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0849() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0849";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.AgregarCarpeta(cp);
		
		int antes = pageProcesoFirma.CantidadCarpeta(cp);
		
		pageProcesoFirma.BtnCrearCarpeta(cp);
		pageProcesoFirma.CrearNuevaCarpeta(cp, "PruebaQa1234");

		int despues = pageProcesoFirma.CantidadCarpeta(cp);
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0850() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0850";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.AgregarCarpeta(cp);		
		pageProcesoFirma.BtnCrearCarpeta(cp);
		pageProcesoFirma.CerrarMoverCrearCarpeta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0853() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0853";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.AgregarEtiquetas(cp);
		pageProcesoFirma.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0858() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0858";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.AgregarEtiquetas(cp);
		pageProcesoFirma.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0861() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0861";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		pageProcesoFirma.DesvincularDocumento(cp);
		pageProcesoFirma.BtnSiDesvincularDocumento(cp);
		pageProcesoFirma.BtnCerrarDesvincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0862() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0862";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		pageProcesoFirma.DesvincularDocumento(cp);
		pageProcesoFirma.BtnNoDesvincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0864() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0864";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		
		int antes = driver.findElements(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[1]/descendant::div")).size();
		pageProcesoFirma.BtnCrearComentario(cp);
		pageProcesoFirma.IngresarComentario(cp, "Prueba QA");
		Thread.sleep(2000);
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		pageProcesoFirma.BtnComentar(cp);
		int despues = driver.findElements(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[1]/descendant::div")).size();
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0865() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0865";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.ClickMiPortal(cp);
		
		PageMiPortal pageMiPortal = new PageMiPortal(driver);
		pageMiPortal.ClickEnProcesoDeFirma(cp);
		
		PageProcesoFirma pageProcesoFirma = new PageProcesoFirma(driver);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button"));
		builder.moveToElement(element).perform();
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		
		pageProcesoFirma.BtnCrearComentario(cp);
		Thread.sleep(2000);
		robot.setAutoDelay(100);
		robot.mouseWheel(2000);
		Thread.sleep(2000);
		pageProcesoFirma.BtnCancelarComentario(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[9]/div/div[1]")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}