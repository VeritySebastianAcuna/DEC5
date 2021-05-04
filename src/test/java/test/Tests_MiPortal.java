package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.bouncycastle.crypto.params.DESParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageDec5;
import pages.PageLoginAdm;
import pages.PageMiPortal;
import pages.PagePendientes;

public class Tests_MiPortal {
	private WebDriver driver;
	String datapool = Configuration.ROOT_DIR+"DataPool.xlsx";
	LeerExcel leerExcel = new LeerExcel(); 
	
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
//		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://5cap.dec.cl/portal");// Aquí se ingresa la URL para hacer las pruebas.
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
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
