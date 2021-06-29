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
		this.driver.switchTo().frame("cke_wysiwyg_frame");//Permite entrar a un marco iframe
		pageAcepta.EditorPlantilla(cp, "ESTO ES UNA PRUEBA AUTOMATIZADA PARA EDITOR PLANTILLA");//REVISAR COMO INGRESAR DATOS POPUP/IFRAME(HTML)
		this.driver.switchTo().defaultContent();
		//pendiente terminar codigo
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
//		pageAcepta.EditorPlantilla(cp, "ESTO ES UNA PRUEBA AUTOMATIZADA PARA EDITOR PLANTILLA");//REVISAR COMO INGRESAR DATOS POPUP/IFRAME(HTML)
		pageAcepta.btnContinuarEditorPlantilla(cp);
		//PENDIENTE TERMINAR CODIGO
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Documento Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.seleccionInstitucion(cp, "000_VERITY_PRUEBA");//Otras Instituciones
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECÍFICO");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "CUALQUIERA");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visualizar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Huella"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con HSM"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Firma Móvil Avanzada"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Token"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Clave Unica"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Cedula Identidad"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Clave Unica + Cedula Identidad"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin Notarial"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firma Cédula(Notarios)"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firma Bioholografa(Notarios)"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		pageAcepta.estadoEspecificacion(cp, "Visar solo con Pin"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		pageAcepta.estadoEspecificacion(cp, "Visar solo con Huella"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		pageAcepta.estadoEspecificacion(cp, "Visar solo con Clave Unica"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el número de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		pageAcepta.estadoEspecificacion(cp, "Visar con Firma"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
		
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
		pageAcepta.nombreTipoDocumento(cp, "Prueba Automatizada");
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar"); //VER CON RICARDO, TOMAR SELECCIÓN LISTA DESPLEGABLE(ESPECIFICACION) y CUANDO HAY MAS TIPOS DE FIRMAS
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
