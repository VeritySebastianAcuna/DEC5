package test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageAcepta;
import pages.PageCrearDocumento;
import pages.PageDec5;
import pages.PageDescargarArchivos;
import pages.PageLoginAdm;

public class Test_TiposDocumentos {
	private WebDriver driver;//Declaro el objeto webdriver
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
	public void Script_0245() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0245";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
	
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed()) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Grilla Documento OK");
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0246() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0246";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);

		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "Otras Instituciones");
		
	
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed()) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Grilla Documento OK");
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0247() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0247";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "AGSFAD"); 
		pageAcepta.btnIconoBuscar(cp);
	
		String msj_No_Resultados = "No se encontraron resultados";
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td")).getText().equals(msj_No_Resultados)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0248() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0248";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "002020560195310"); 
		pageAcepta.btnIconoBuscar(cp);
	
		String nombre_Carpeta_Buscar = "002020560195310";
	
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td/div/div/div[1]")).getText().contains(nombre_Carpeta_Buscar)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0249() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0249";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "002020560195310");
		pageAcepta.checkIncluirOcultos(cp);
		pageAcepta.btnIconoBuscar(cp);
	
		String nombre_Carpeta_Buscar = "002020560195310";
	
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td/div/div/div[1]")).getText().contains(nombre_Carpeta_Buscar)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0251() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0251";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "002020560195310"); 
		pageAcepta.btnIconoBuscar(cp);
	
		String nombre_Carpeta_Buscar = "002020560195310";
	
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td/div/div/div[1]")).getText().contains(nombre_Carpeta_Buscar)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0253() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0253";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		
	
		if(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")).getText().contains(datos[4])) {
			String texto = "Ingreso texto editor OK"; 
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
				
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0254() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0254";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0255() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0255";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0256() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0256";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.btnCerrarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0257() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0257";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0258() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0258";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0259() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0259";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0260() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0260";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPermitirAgregarFirmantes(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0261() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0261";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxRecibirNotificaciones(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0262() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0262";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxTituloDocumentoIgualNombreArchivo(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0263() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0263";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxVisualizacionOrdenFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0264() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0264";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxEnviarBotonFirmaCorreoPendienteFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0265() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0265";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPDFconPassword(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0266() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0266";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxValidacionCorreoPersonal(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0267() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0267";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "PERSONAL");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0319() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0319";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
				
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);
		//Comparar nombre carpeta

		
		System.out.println("FLUJO OK");
		
	}
	

	@Test
	public void Script_0320() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0320";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolRut(cp, "Admin");
				
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);
		//Comparar nombre carpeta
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0321() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0321";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxPermitirAgregarFirmantes(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0322() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0322";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxRecibirNotificaciones(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0323() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0323";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxTituloDocumentoIgualNombreArchivo(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0324() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0324";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxVisualizacionOrdenFirma(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0325() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0325";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxEnviarBotonFirmaCorreoPendienteFirma(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0326() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0326";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxPDFconPassword(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0327() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0327";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxValidacionCorreoPersonal(cp);
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Documento Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Documento: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0328() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0328";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0329() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0329";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.seleccionInstitucion(cp, "PERSONAL");
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0330() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0330";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.seleccionInstitucion(cp, "GRUPO PERSONAS");
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0331() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0331";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.seleccionInstitucion(cp, "Otras Instituciones");
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	@Test
	public void Script_0332() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0332";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0333() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0333";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECÍFICO");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0334() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0334";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "CUALQUIERA");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0335() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0335";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0336() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0336";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0337() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0337";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0338() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0338";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Compartir");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0339() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0339";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Pin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0340() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0340";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Huella");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0341() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0341";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con HSM");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0342() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0342";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Firma Móvil Avanzada");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0343() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0343";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Token");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0344() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0344";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Clave Unica");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0345() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0345";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Cedula Identidad");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0346() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0346";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Clave Unica + Cedula Identidad");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0347() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0347";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Pin Notarial");
	
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0348() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0348";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firma Cédula(Notarios)");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0349() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0349";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firma Bioholografa(Notarios)");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0350() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0350";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar solo con Pin");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0351() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0351";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar solo con Huella");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0352() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0352";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar solo con Clave Unica");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0353() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0353";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar con Firma");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0354() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0354";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0355() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0355";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Todas");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0356() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0356";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Finalizado");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0357() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0357";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Firmado");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0358() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0358";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Rechazo");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0359() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0359";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		//String nombre_Tipo_Documento = driver.findElement(By.name("name")).getText();//NO TOMA NOMBRE AL RESCATAR TEXTO EN VARIABLE
		String nombre_Tipo_Documento = "Prueba Automatizada";
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		String nombre_Documento_Creado = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2/strong")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ nombre_Documento_Creado);

		if(nombre_Tipo_Documento.equals(nombre_Documento_Creado)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@AfterMethod
	public void FinEjecucion() {
		driver.close();
	}

}
