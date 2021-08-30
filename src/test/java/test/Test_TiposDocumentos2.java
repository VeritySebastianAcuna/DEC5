package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Test_TiposDocumentos2 {
	private WebDriver driver;//Declaro el objeto webdriver
	String datapool = Configuration.ROOT_DIR+"DataPool2.xlsx";
	LeerExcel leerExcel = new LeerExcel(); 

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
//		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://5qa.dec.cl/");// Aquí se ingresa la URL para hacer las pruebas. https://5cap.dec.cl/portal
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
	}
	
	// Casos de Prueba Ricardo Acuña
	
	@Test
	public void Script_0371() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0371";
		// Tipos Doc - crear - Subir archivo - Etiquetas - Campo - ingresar valor ok
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		
		if(driver.findElement(By.name("field_value_code_0")).getAttribute("placeholder").contains("Campo") &&
				driver.findElement(By.name("field_value_default_0")).getAttribute("placeholder").contains("Valor por Defecto (opcional)") &&
				driver.findElement(By.name("config-0")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div/div[3]/div[2]/span")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0372() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0372";
		// Tipos Doc - crear - Subir archivo - Etiquetas - Campo - ingresar valor ok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		pageAcepta.btnAgregarEtiquetasCampoValor(cp, "Etiqueta QA", "Valor etiqueta QA");
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		pageAcepta.btnAdd(cp);
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0373() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0373";
		// Tipos Doc - crear - Subir archivo - Etiquetas - Campo - ingresar valor nok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = "El campo Campo es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		pageAcepta.btnAgregarEtiquetasCampoValor(cp, "", "");
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		pageAcepta.btnAdd(cp);
		Thread.sleep(3000);
		
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div/div[1]/div/span")).getText();
		System.out.println("Mensaje por validar: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0374() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0374";
		// Tipos Doc - crear - Subir archivo - Etiquetas - Campo - no ingresar datos 
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = "El campo Campo es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		pageAcepta.btnAgregarEtiquetasCampoValor(cp, "", "");
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		pageAcepta.btnAdd(cp);
		Thread.sleep(3000);
		
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div/div[1]/div/span")).getText();
		System.out.println("Mensaje por validar: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0376() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0376";
		// Tipos Doc - crear - Subir archivo - Etiquetas - configuracion
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		/*
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		*/
		
		pageAcepta.btnAgregarEtiquetasCampoValor(cp, "CampoQA", "valor QA");
		pageAcepta.IconoConfiguracion(cp);
		
		Thread.sleep(3000);
	
		
		/*
		pageAcepta.btnAdd(cp);
		Thread.sleep(3000);
		*/
	
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText());
		
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") && 	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[1]/input")).isDisplayed() && 	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0377() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0377";
		// Tipos Doc - crear - Subir archivo - Etiquetas - configuracion - agregar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String valorListaValores = "Valor QA";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		/*
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		*/
		
		pageAcepta.btnAgregarEtiquetasCampoValor(cp, "CampoQA", "valor QA");
		pageAcepta.IconoConfiguracion(cp);
		pageAcepta.IngresarValorListaValores(cp, valorListaValores);
		
		Thread.sleep(3000);
	
		
		/*
		pageAcepta.btnAdd(cp);
		Thread.sleep(3000);
		*/
	
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[1]/h2")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText());
		
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") && 	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[1]/input")).isDisplayed() && 	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[1]/h2")).getText().contains(valorListaValores) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[2]/a")).isDisplayed() && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0379() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0379";
		// Tipos Doc - crear - Subir archivo - Descripcion del documento (opcional) 
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		//pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		//pageAcepta.tipoFirma(cp, "ESPECIFICO");
		//pageAcepta.orden(cp, "1");
		//pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Thread.sleep(3000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(3000);
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		pageAcepta.btnAdd(cp);
		Thread.sleep(4000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		//assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0380() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0380";
		// Tipos Doc - crear - Subir archivo - Crear Tipo de Documento - sin datos 
		String fraseEsperada1_nombreTipoDoc = "El campo Nombre Tipo Documento es obligatorio";
		String fraseEsperada2_rolCreador = "El campo Rol Creador es obligatorio.";
		String fraseEsperada3_rolRut = "El campo Rol es obligatorio.";
		String fraseEsperada4_accion = "El campo Acción es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		/*
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		*/
		
		Thread.sleep(3000);
		Robot robot = new Robot();
		robot.setAutoDelay(7);
		robot.mouseWheel(7);
		Thread.sleep(3000);
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		pageAcepta.btnAdd(cp);
		Thread.sleep(4000);
		
		
		String mensajePorValidar1 = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[1]/div/div/span")).getText();
		System.out.println(mensajePorValidar1);
		String mensajePorValidar2 = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[2]/div[3]/div/span")).getText();
		System.out.println(mensajePorValidar2);
		String mensajePorValidar3 = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[2]/div/span")).getText();
		System.out.println(mensajePorValidar3);
		String mensajePorValidar4 = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span[2]")).getText();
		System.out.println(mensajePorValidar4);
		

		if(mensajePorValidar1.contains(fraseEsperada1_nombreTipoDoc) &&
				mensajePorValidar2.contains(fraseEsperada2_rolCreador) &&
				mensajePorValidar3.contains(fraseEsperada3_rolRut) &&
				mensajePorValidar4.contains(fraseEsperada4_accion)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0381() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0381";
		// Tipos Doc - crear - Subir archivo - Crear Tipo de Documento - con datos ok 
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		
		// datos por defecto
		
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		//pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		//pageAcepta.tipoFirma(cp, "ESPECIFICO");
		//pageAcepta.orden(cp, "1");
		//pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		//pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		pageAcepta.IngresarDescripcionDoc(cp, "Descripción QA");
		
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		//pageAcepta.btnAdd(cp);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		

		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println(mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	/*
	@Test
	public void Script_0382() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0382";
		// Tipos Doc - crear - Subir archivo - Crear Tipo de Documento - con datos ok 
		String fraseEsperada = "El tipo de archivo que intentas subir no está permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		// datos
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"PruebaQA.xlsx");
		Thread.sleep(3000);
		

		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	*/
	
	
	
	@Test
	public void Script_0383() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0383";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - archivo del equipo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0384() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0384";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - archivo del equipo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0385() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0385";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - arrastrar archivo - no pdf
		String fraseEsperada = "El tipo de archivo que intentas subir no está permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"PruebaQA.xlsx");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0386() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0386";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - arrastrar archivo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0387() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0387";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - arrastrar archivo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0388() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0388";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - check cargar sin editar ok
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		
		Thread.sleep(3000);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0389() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0389";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - check cargar sin editar nok
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		Thread.sleep(3000);
		pageAcepta.ClickCheckCargarSinEditar(cp);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()  &&
				driver.findElement(By.id("file-name-upload")).getText().contains("Documento Prueba.pdf")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0390() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0390";
		// Tipos Doc - crear - Plantilla PDF - Editor de Plantilla - cerrar editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		pageAcepta.btnCerrarEditorPlantillaEditorPdf(cp);
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0391() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0391";
		// Tipos Doc - crear - Plantilla PDF - Nombre Tipo Documento
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		//pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		//pageAcepta.tipoFirma(cp, "ESPECIFICO");
		//pageAcepta.orden(cp, "1");
		//pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0392() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0392";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		// se valida POPUP
		
		String titulo = driver.findElement(By.xpath("//*[@id=\"modalTemplateEditorPDF\"]/div/div/div[1]/h1")).getText();
		System.out.println(titulo);
		String btn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button")).getText();
		System.out.println(btn);
		String mensaje_btn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span")).getText();
		System.out.println(mensaje_btn);
		String mensaje_btn2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]")).getText();
		System.out.println(mensaje_btn2);
		String ckeckbox_mensaje = driver.findElement(By.xpath("//*[@id=\"make-changes-wrapper\"]/div/div/label")).getText();
		System.out.println(ckeckbox_mensaje);
		

		if(titulo.contains("Editor de Plantilla.") &&
				btn.contains("Selecciona un archivo de tu Equipo") &&
				mensaje_btn.contains("* Archivos permitidos: pdf. Tamaño máximo: 2M") &&
				mensaje_btn2.contains("O arrastra tu archivo aquí") &&
				ckeckbox_mensaje.contains("Cargar sin editar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0394() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0394";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - archivo del equipo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0395() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0395";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - archivo del equipo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0396() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0396";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - arrastrar archivo - no pdf
		String fraseEsperada = "El tipo de archivo que intentas subir no está permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"PruebaQA.xlsx");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0397() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0397";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - arrastrar archivo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0398() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0398";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - arrastrar archivo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0399() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0399";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		// se valida POPUP
		
		String titulo = driver.findElement(By.xpath("//*[@id=\"modalTemplateEditorPDF\"]/div/div/div[1]/h1")).getText();
		System.out.println(titulo);
		String btn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button")).getText();
		System.out.println(btn);
		String mensaje_btn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span")).getText();
		System.out.println(mensaje_btn);
		String mensaje_btn2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]")).getText();
		System.out.println(mensaje_btn2);
		String ckeckbox_mensaje = driver.findElement(By.xpath("//*[@id=\"make-changes-wrapper\"]/div/div/label")).getText();
		System.out.println(ckeckbox_mensaje);
		

		if(titulo.contains("Editor de Plantilla.") &&
				btn.contains("Selecciona un archivo de tu Equipo") &&
				mensaje_btn.contains("* Archivos permitidos: pdf. Tamaño máximo: 2M") &&
				mensaje_btn2.contains("O arrastra tu archivo aquí") &&
				ckeckbox_mensaje.contains("Cargar sin editar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0400() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0400";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - check cargar sin editar ok
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0401() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0401";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - check cargar sin editar nok
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.ClickCheckCargarSinEditar(cp);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()  &&
				driver.findElement(By.id("file-name-upload")).getText().contains("Documento Prueba.pdf")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0402() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0402";
		// Tipos Doc - crear - Plantilla PDF - boton abrir editor - cerrar editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		pageAcepta.btnCerrarEditorPlantillaEditorPdf(cp);
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0403() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0403";
		// Tipos Doc - crear - Plantilla PDF - Rol Creador (seleccionar)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		//pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		//pageAcepta.tipoFirma(cp, "ESPECIFICO");
		//pageAcepta.orden(cp, "1");
		//pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0404() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0404";
		// Tipos Doc - crear - Plantilla PDF - checkbox permitir agregar mas firmantes
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPermitirAgregarFirmantes(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0405() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0405";
		// Tipos Doc - crear - Plantilla PDF - checkbox recibir notificaciones
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxRecibirNotificaciones(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0406() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0406";
		// Tipos Doc - crear - Plantilla PDF - checkbox titulo documento igual nombre archivo
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxTituloDocumentoIgualNombreArchivo(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0407() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0407";
		// Tipos Doc - crear - Plantilla PDF - checkbox visualizacion según orden de firma
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxVisualizacionOrdenFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0408() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0408";
		// Tipos Doc - crear - Plantilla PDF - checkbox enviar boton de firma en correo pendiente de firma
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxEnviarBotonFirmaCorreoPendienteFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0409() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0409";
		// Tipos Doc - crear - Plantilla PDF - checkbox pdf con contraseña
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPDFconPassword(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0410() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0410";
		// Tipos Doc - crear - Plantilla PDF - checkbox validacion correo personal para notificar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxValidacionCorreoPersonal(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0411() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0411";
		// Tipos Doc - crear - Plantilla PDF - institucion (acepta)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0412() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0412";
		// Tipos Doc - crear - Plantilla PDF - institucion (personal)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "PERSONAL");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0413() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0413";
		// Tipos Doc - crear - Plantilla PDF - institucion (grupo personas)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "GRUPO PERSONAS");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0414() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0414";
		// Tipos Doc - crear - Plantilla PDF - institucion (otras instituciones)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "000_VERITY_PRUEBA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(3000);
		pageAcepta.btnAdd3(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0415() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0415";
		// Tipos Doc - crear - Plantilla PDF - Rol / RUT
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0416() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0416";
		// Tipos Doc - crear - Plantilla PDF - Firma - Especifico
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0417() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0417";
		// Tipos Doc - crear - Plantilla PDF - Firma - Cualquiera
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "CUALQUIERA");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0418() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0418";
		// Tipos Doc - crear - Plantilla PDF - Orden
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "2");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0419() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0419";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "2");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0422() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0422";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Pin
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0423() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0423";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con huella
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Huella");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0424() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0424";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con HSM
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con HSM");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0425() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0425";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Firma Movil Avanzada
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Firma Móvil Avanzada");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0426() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0426";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Token
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Token");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0427() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0427";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Clave Unica
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Clave Unica");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0428() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0428";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Cedula de Identidad
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Cedula Identidad");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0429() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0429";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Clave Unica - Cedula de Indentidad Facial
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Clave Unica + Cedula Identidad");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0430() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0430";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firmar solo con Pin Notarial
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin Notarial");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0431() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0431";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firma cedula (notarios)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firma Cédula(Notarios)");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0432() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0432";
		// Tipos Doc - crear - Plantilla PDF - Accion - Firma Bioholografa (notarios)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firma Bioholografa(Notarios)");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0437() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0437";
		// Tipos Doc - crear - Plantilla PDF - Notificar - sin notificaciones
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0438() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0438";
		// Tipos Doc - crear - Plantilla PDF - Notificar - todas
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Todas");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0439() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0439";
		// Tipos Doc - crear - Plantilla PDF - Notificar - finalizado
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Finalizado");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0440() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0440";
		// Tipos Doc - crear - Plantilla PDF - Notificar - firmado
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Firmado");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0441() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0441";
		// Tipos Doc - crear - Plantilla PDF - Notificar - rechazo
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Rechazo");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0442() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0442";
		// Tipos Doc - crear - Plantilla PDF - Notificar - pendiente de firma
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0444() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0444";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas (opcional)
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregar_PlantillaPdf(cp);
	

		if(driver.findElement(By.id("newTag")).isDisplayed() &&
				driver.findElement(By.id("newTagButton")).getText().contains("Agregar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0445() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0445";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - Agregar
		String nombreEtiqueta = "EtiquetaQA";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregar_PlantillaPdf(cp);
		pageAcepta.IngresarEtiqueta(cp, nombreEtiqueta);

		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[1]/button/span")).getText().contains(nombreEtiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[2]/button")).getText().contains("Agregar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0446() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0446";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - Agregar - eliminar
		String nombreEtiqueta = "EtiquetaQA";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregar_PlantillaPdf(cp);
		pageAcepta.IngresarEtiqueta(cp, nombreEtiqueta);
		pageAcepta.EliminarEtiqueta(cp);

		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[1]/button")).getText().contains("Agregar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0447() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0447";
		// Tipos Doc - crear - Plantilla PDF - configurar - Cualquiera puede compartir y descargar el documento
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[1]")).isSelected()) {
			pageAcepta.checkboxCualquieraPuedeCompartir(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0448() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0448";
		// Tipos Doc - crear - Plantilla PDF - configurar - solo los firmantes pueden compartir y descargar el documentos
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[2]")).isSelected()) {
			pageAcepta.checkboxSoloFirmantes(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0449() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0449";
		// Tipos Doc - crear - Plantilla PDF - configurar - nadie puede compartir ni descargar el documento
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[3]")).isSelected()) {
			pageAcepta.checkboxNadiePuedeCompartir(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0450() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0450";
		// Tipos Doc - crear - Plantilla PDF - configurar - documento publico (Se accede mediante URL)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[4]")).isSelected()) {
			pageAcepta.checkboxDocumentoPublico(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0451() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0451";
		// Tipos Doc - crear - Plantilla PDF - boton Agregar Etiquetas Campo/Valor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(12);
		robot.mouseWheel(12);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		
		if(driver.findElement(By.name("field_value_code_0")).getAttribute("placeholder").contains("Campo") &&
				driver.findElement(By.name("field_value_default_0")).getAttribute("placeholder").contains("Valor por Defecto (opcional)") &&
				driver.findElement(By.name("config-0")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div/div[3]/div[2]/span")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0452() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0452";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - Campo - ingresar valor ok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		
		Thread.sleep(2000);
		//pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	

	@Test
	public void Script_0453() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0453";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - Campo - ingresar valor nok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String mensajeEsperado = "El campo Campo es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		//pageAcepta.IngresarCampoValor(cp, "", "");
		
		Thread.sleep(2000);
		//pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div/div[1]/div/span")).getText();
		System.out.println("mensaje: "+ mensajePorValidar);

		if(mensajePorValidar.contains(mensajeEsperado)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0454() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0454";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - Campo - no ingresar datos 
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String mensajeEsperado = "El campo Campo es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		
		Thread.sleep(2000);
		//pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div/div[1]/div/span")).getText();
		System.out.println("mensaje: "+ mensajePorValidar);

		if(mensajePorValidar.contains(mensajeEsperado)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0455() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0455";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - Valor por defecto(opcional)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		
		Thread.sleep(2000);
		//pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0456() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0456";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - configuracion
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		pageAcepta.ClickConfiguracionEtiqueta(cp);
		
		Thread.sleep(3000);


		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") &&
				driver.findElement(By.name("op-val")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0457() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0457";
		// Tipos Doc - crear - Plantilla PDF - Etiquetas - configuracion - agregar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String campoConfiguracionEtiqueta = "Valor Configuración QA";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaPdf(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		pageAcepta.ClickConfiguracionEtiqueta(cp);
		Thread.sleep(3000);
		pageAcepta.IngresarCamposConfiguracionEtiqueta(cp, campoConfiguracionEtiqueta);
		pageAcepta.ClickAgregarConfiguracionEtiqueta(cp);
		
		Thread.sleep(3000);


		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") &&
				driver.findElement(By.name("op-val")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[1]/h2")).getText().contains(campoConfiguracionEtiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[2]/a/span")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0459() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0459";
		// Tipos Doc - crear - Plantilla PDF - Descripcion del documento (opcional)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		String descripciónDocumento = "Descripción prueba QA";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.IngresarDescripcionDoc(cp, descripciónDocumento);
		
		Thread.sleep(2000);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0460() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0460";
		// Tipos Doc - crear - Plantilla PDF - Crear Tipo de Documento - sin datos
		String mensajeNombreTipoDoc_esperado = "El campo Nombre Tipo Documento es obligatorio.";
		String mensajePlanillaPdf_esperado = "El campo Archivo es obligatorio.";
		String mensajeRolCreador_esperado = "El campo Rol Creador es obligatorio.";
		String mensajeRolRut_esperado = "El campo Rol es obligatorio.";
		String mensajeEspecificacion_esperado = "El campo Acción es obligatorio.";

		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.btnCerrarEditorPdf(cp);
		
		// No se ingresan datos
		
		Thread.sleep(2000);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajeNombreTipoDoc = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[1]/div/div/span")).getText();
		String mensajePlanillaPdf = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[2]/div[2]/div/span")).getText();
		String mensajeRolCreador = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[3]/div/span")).getText();
		String mensajeRolRut = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[3]/div/div[2]/div/span")).getText();
		String mensajeEspecificacion = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[3]/div/div[6]/div/span[2]")).getText();

		if(mensajeNombreTipoDoc.contains(mensajeNombreTipoDoc_esperado) &&
				mensajePlanillaPdf.contains(mensajePlanillaPdf_esperado) &&
				mensajeRolCreador.contains(mensajeRolCreador_esperado) &&
				mensajeRolRut.contains(mensajeRolRut_esperado) &&
				mensajeEspecificacion.contains(mensajeEspecificacion_esperado)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0461() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0461";
		// Tipos Doc - crear - Plantilla PDF - Crear Tipo de Documento - con datos ok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		String descripciónDocumento = "Descripción prueba QA";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaPdf(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(3000);
		
		pageAcepta.IngresarDescripcionDoc(cp, descripciónDocumento);
		
		Thread.sleep(2000);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0464() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0464";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - archivo del equipo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		
		
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0465() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0465";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - archivo del equipo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0466() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0466";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - arrastrar archivo - no pdf
		String fraseEsperada = "El tipo de archivo que intentas subir no está permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"PruebaQA.xlsx");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0467() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0467";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - arrastrar archivo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		//String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		//pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		
		
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0468() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0468";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - arrastrar archivo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0469() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0469";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - check cargar sin editar ok
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0470() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0470";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - check cargar sin editar nok
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.ClickCheckCargarSinEditar(cp);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()  &&
				driver.findElement(By.id("file-name-upload")).getText().contains("Documento Prueba.pdf")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0471() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0471";
		// Tipos Doc - crear - Plantilla Colaborativa - Editor de Plantilla - cerrar editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		pageAcepta.btnCerrarEditorPlantillaEditorPdf(cp);
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0472() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0472";
		// Tipos Doc - crear - Plantilla Colaborativa - Nombre Tipo Documento
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		//pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		//pageAcepta.tipoFirma(cp, "ESPECIFICO");
		//pageAcepta.orden(cp, "1");
		//pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0473() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0473";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		// se valida POPUP
		
		String titulo = driver.findElement(By.xpath("//*[@id=\"modalTemplateEditorPDF\"]/div/div/div[1]/h1")).getText();
		System.out.println(titulo);
		String btn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button")).getText();
		System.out.println(btn);
		String mensaje_btn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span")).getText();
		System.out.println(mensaje_btn);
		String mensaje_btn2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]")).getText();
		System.out.println(mensaje_btn2);
		String ckeckbox_mensaje = driver.findElement(By.xpath("//*[@id=\"make-changes-wrapper\"]/div/div/label")).getText();
		System.out.println(ckeckbox_mensaje);
		

		if(titulo.contains("Editor de Plantilla.") &&
				btn.contains("Selecciona un archivo de tu Equipo") &&
				mensaje_btn.contains("* Archivos permitidos: pdf. Tamaño máximo: 2M") &&
				mensaje_btn2.contains("O arrastra tu archivo aquí") &&
				ckeckbox_mensaje.contains("Cargar sin editar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0475() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0475";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - archivo del equipo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		Thread.sleep(3000);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0476() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0476";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - archivo del equipo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0477() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0477";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - arrastrar archivo - no pdf
		String fraseEsperada = "El tipo de archivo que intentas subir no está permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"PruebaQA.xlsx");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0478() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0478";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - arrastrar archivo  - pdf > 2 MB
		String fraseEsperada = "El archivo que intentas subir es más grande que el tamaño permitido.";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"ArchivoSuperior2MB.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"seccion-subir\"]/div[3]/span")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0479() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0479";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - arrastrar archivo  - pdf < 2 MB
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0480() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0480";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - check cargar sin editar ok
		String fraseEsperada = "Campos Dinámicos Página 1";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"variable-fields-wrapper\"]/div[1]")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0481() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0481";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - check cargar sin editar nok
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.ClickCheckCargarSinEditar(cp);
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		Thread.sleep(3000);
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()  &&
				driver.findElement(By.id("file-name-upload")).getText().contains("Documento Prueba.pdf")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0482() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0482";
		// Tipos Doc - crear - Plantilla Colaborativa - boton abrir editor - cerrar editor
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		pageAcepta.cerrarPopUp(cp);
		pageAcepta.BtnAbrirEditor(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"Documento Prueba.pdf");
		pageAcepta.btnCerrarEditorPlantillaEditorPdf(cp);
		Thread.sleep(3000);
		
		
		String mensajePorValidar = driver.findElement(By.id("file-name-upload")).getText();
		System.out.println(mensajePorValidar);
		
		
		if(!driver.findElement(By.id("modalTemplateEditorPDF")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0483() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0483";
		// Tipos Doc - crear - Plantilla Colaborativa - Rol Creador (seleccionar)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		//pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		//pageAcepta.tipoFirma(cp, "ESPECIFICO");
		//pageAcepta.orden(cp, "1");
		//pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0484() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0484";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox permitir agregar mas firmantes
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPermitirAgregarFirmantes(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0485() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0485";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox recibir notificaciones
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxRecibirNotificaciones(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0486() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0486";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox titulo documento igual nombre archivo
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxTituloDocumentoIgualNombreArchivo(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0487() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0487";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox visualizacion según orden de firma
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxVisualizacionOrdenFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0488() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0488";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox enviar boton de firma en correo pendiente de firma
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxEnviarBotonFirmaCorreoPendienteFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0489() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0489";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox pdf con contraseña
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPDFconPassword(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0490() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0490";
		// Tipos Doc - crear - Plantilla Colaborativa - checkbox validacion correo personal para notificar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxValidacionCorreoPersonal(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0491() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0491";
		// Tipos Doc - crear - Plantilla Colaborativa - institucion (acepta)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0492() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0492";
		// Tipos Doc - crear - Plantilla Colaborativa - institucion (personal)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "PERSONAL");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0493() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0493";
		// Tipos Doc - crear - Plantilla Colaborativa - institucion (grupo personas)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "GRUPO PERSONAS");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0494() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0494";
		// Tipos Doc - crear - Plantilla Colaborativa - institucion (otras instituciones)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "000_VERITY_PRUEBA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(3000);
		pageAcepta.btnAdd3(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0495() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0495";
		// Tipos Doc - crear - Plantilla Colaborativa - Rol / RUT
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0496() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0496";
		// Tipos Doc - crear - Plantilla Colaborativa - Firma - Especifico
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0497() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0497";
		// Tipos Doc - crear - Plantilla Colaborativa - Firma - Cualquiera
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "CUALQUIERA");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0498() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0498";
		// Tipos Doc - crear - Plantilla Colaborativa - Orden
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
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
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "2");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0499() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0499";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "2");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	

	
	@Test
	public void Script_0502() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0502";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Pin
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0503() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0503";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con huella
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Huella");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0504() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0504";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con HSM
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con HSM");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0505() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0505";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Firma Movil Avanzada
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Firma Móvil Avanzada");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0506() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0506";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Token
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Token");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0507() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0507";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Clave Unica
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Clave Unica");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0508() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0508";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Cedula de Identidad
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Cedula Identidad");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0509() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0509";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Clave Unica - Cedula de Indentidad Facial
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Clave Unica + Cedula Identidad");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0510() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0510";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firmar solo con Pin Notarial
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin Notarial");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0511() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0511";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firma cedula (notarios)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firma Cédula(Notarios)");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0512() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0512";
		// Tipos Doc - crear - Plantilla Colaborativa - Accion - Firma Bioholografa (notarios)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firma Bioholografa(Notarios)");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0517() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0517";
		// Tipos Doc - crear - Plantilla Colaborativa - Notificar - sin notificaciones
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0518() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0518";
		// Tipos Doc - crear - Plantilla Colaborativa - Notificar - todas
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Todas");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0519() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0519";
		// Tipos Doc - crear - Plantilla Colaborativa - Notificar - finalizado
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Finalizado");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0520() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0520";
		// Tipos Doc - crear - Plantilla Colaborativa - Notificar - firmado
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Firmado");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0521() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0521";
		// Tipos Doc - crear - Plantilla Colaborativa - Notificar - rechazo
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Rechazo");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0522() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0522";
		// Tipos Doc - crear - Plantilla Colaborativa - Notificar - pendiente de firma
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0524() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0524";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas (opcional)
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregar_PlantillaPdf(cp);
	

		if(driver.findElement(By.id("newTag")).isDisplayed() &&
				driver.findElement(By.id("newTagButton")).getText().contains("Agregar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0525() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0525";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - Agregar
		String nombreEtiqueta = "EtiquetaQA";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregar_PlantillaPdf(cp);
		pageAcepta.IngresarEtiqueta(cp, nombreEtiqueta);

		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[1]/button/span")).getText().contains(nombreEtiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[2]/button")).getText().contains("Agregar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0526() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0526";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - Agregar - eliminar
		String nombreEtiqueta = "EtiquetaQA";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregar_PlantillaPdf(cp);
		pageAcepta.IngresarEtiqueta(cp, nombreEtiqueta);
		pageAcepta.EliminarEtiqueta(cp);

		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[1]/button")).getText().contains("Agregar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0527() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0527";
		// Tipos Doc - crear - Plantilla Colaborativa - configurar - Cualquiera puede modificar los Tags
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[1]")).isSelected()) {
			pageAcepta.checkboxCualquieraPuedeModificarTag(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0528() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0528";
		// Tipos Doc - crear - Plantilla Colaborativa - configurar - Solo el creador del tag puede modificar
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[2]")).isSelected()) {
			pageAcepta.checkboxSoloCreadorTagModificar(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}

	
	@Test
	public void Script_0529() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0529";
		// Tipos Doc - crear - Plantilla Colaborativa - configurar - Cualquiera puede compartir y descargar el documento
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[1]")).isSelected()) {
			pageAcepta.checkboxCualquieraPuedeCompartir2(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0530() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0530";
		// Tipos Doc - crear - Plantilla Colaborativa - configurar - solo los firmantes pueden compartir y descargar el documentos
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[2]")).isSelected()) {
			pageAcepta.checkboxSoloFirmantes2(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0531() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0531";
		// Tipos Doc - crear - Plantilla Colaborativa - configurar - nadie puede compartir ni descargar el documento
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[3]")).isSelected()) {
			pageAcepta.checkboxNadiePuedeCompartir2(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0532() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0532";
		// Tipos Doc - crear - Plantilla Colaborativa - configurar - documento publico (Se accede mediante URL)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[4]")).isSelected()) {
			pageAcepta.checkboxDocumentoPublico2(cp);
		}
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	

	
	@Test
	public void Script_0533() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0533";
		// Tipos Doc - crear - Plantilla Colaborativa - boton Agregar Etiquetas Campo/Valor
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
	
		Robot robot = new Robot();
		robot.setAutoDelay(12);
		robot.mouseWheel(12);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		
		if(driver.findElement(By.name("field_value_code_0")).getAttribute("placeholder").contains("Campo") &&
				driver.findElement(By.name("field_value_default_0")).getAttribute("placeholder").contains("Valor por Defecto (opcional)") &&
				driver.findElement(By.name("config-0")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[9]/div[3]/div/div[3]/div[2]/span")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	//----
	
	@Test
	public void Script_0534() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0534";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - Campo - ingresar valor ok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		
		Thread.sleep(2000);
		//pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	

	@Test
	public void Script_0535() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0535";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - Campo - ingresar valor nok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String mensajeEsperado = "El campo Campo es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
	
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
	
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[9]/div[3]/div/div[1]/div/span")).getText();
		System.out.println("mensaje: "+ mensajePorValidar);

		if(mensajePorValidar.contains(mensajeEsperado)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0536() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0536";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - Campo - no ingresar datos 
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String mensajeEsperado = "El campo Campo es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
	
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
	
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(2000);
		
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[9]/div[3]/div/div[1]/div/span")).getText();
		System.out.println("mensaje: "+ mensajePorValidar);

		if(mensajePorValidar.contains(mensajeEsperado)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0537() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0537";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - Valor por defecto(opcional)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
	
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
		Thread.sleep(2000);
		
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		
		Thread.sleep(2000);
		//pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0538() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0538";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - configuracion
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
	
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		pageAcepta.ClickConfiguracionEtiqueta(cp);
		
		Thread.sleep(3000);


		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") &&
				driver.findElement(By.name("op-val")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0539() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0539";
		// Tipos Doc - crear - Plantilla Colaborativa - Etiquetas - configuracion - agregar
		String campoConfiguracionEtiqueta = "Valor Configuración QA";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.setAutoDelay(20);
		robot.mouseWheel(20);
	
		pageAcepta.btnAgregarEtiquetaCampoValor_PlantillaColaborativa(cp);
		pageAcepta.IngresarCampoValor(cp, "campoQA", "ValorQA");
		pageAcepta.ClickConfiguracionEtiqueta(cp);
		Thread.sleep(3000);
		pageAcepta.IngresarCamposConfiguracionEtiqueta(cp, campoConfiguracionEtiqueta);
		pageAcepta.ClickAgregarConfiguracionEtiqueta(cp);
		
		Thread.sleep(3000);


		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") &&
				driver.findElement(By.name("op-val")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[1]/h2")).getText().contains(campoConfiguracionEtiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/table/tbody/tr/td[2]/a/span")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0541() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0541";
		// Tipos Doc - crear - Plantilla Colaborativa - Descripcion del documento (opcional)
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		String descripciónDocumento = "Descripción prueba QA";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.IngresarDescripcionDoc(cp, descripciónDocumento);
		
		Thread.sleep(2000);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0542() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0542";
		// Tipos Doc - crear - Plantilla Colaborativa - Crear Tipo de Documento - sin datos
		String mensajeNombreTipoDoc_esperado = "El campo Nombre Tipo Documento es obligatorio.";
		String mensajePlanillaPdf_esperado = "El campo Archivo es obligatorio.";
		String mensajeRolCreador_esperado = "El campo Rol Creador es obligatorio.";
		String mensajeRolRut_esperado = "El campo Rol es obligatorio.";
		String mensajeEspecificacion_esperado = "El campo Acción es obligatorio.";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.btnCerrarEditorPdf(cp);
		
		// No se ingresan datos
		
		Thread.sleep(2000);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajeNombreTipoDoc = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[1]/div/div/span")).getText();
		String mensajePlanillaPdf = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[2]/div[2]/div/span")).getText();
		String mensajeRolCreador = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[3]/div/span")).getText();
		String mensajeRolRut = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[3]/div/div[2]/div/span")).getText();
		String mensajeEspecificacion = driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[3]/div/div[6]/div/span[2]")).getText();

		if(mensajeNombreTipoDoc.contains(mensajeNombreTipoDoc_esperado) &&
				mensajePlanillaPdf.contains(mensajePlanillaPdf_esperado) &&
				mensajeRolCreador.contains(mensajeRolCreador_esperado) &&
				mensajeRolRut.contains(mensajeRolRut_esperado) &&
				mensajeEspecificacion.contains(mensajeEspecificacion_esperado)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0543() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0543";
		// Tipos Doc - crear - Plantilla Colaborativa - Crear Tipo de Documento - con datos ok
		String nombreTipoDocumento = "QA-"+System.currentTimeMillis();
		String fraseEsperada = nombreTipoDocumento+" se creó correctamente";
		String descripciónDocumento = "Descripción prueba QA";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaColaborativa(cp);
		
		pageAcepta.SeleccionarArchivoEquipo(cp, Configuration.FILES_DIR2+"DocumentoEnBlanco.pdf");
		pageAcepta.btnContinuarEditorPlantillaPdf(cp);
		Thread.sleep(2000);
		
		// datos por defecto
		pageAcepta.nombreTipoDocumento(cp, nombreTipoDocumento);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacionPlantillaPdf(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		Robot robot = new Robot();
		robot.setAutoDelay(10);
		robot.mouseWheel(10);
		Thread.sleep(2000);
		
		pageAcepta.IngresarDescripcionDoc(cp, descripciónDocumento);
		
		Thread.sleep(2000);
		pageAcepta.btnAdd2(cp);
		pageAcepta.btnAdd2(cp);
		Thread.sleep(5000);
		
		String mensajePorValidar = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div")).getText();
		System.out.println("Nombre tipo Docuemnto: "+ mensajePorValidar);

		if(mensajePorValidar.contains(fraseEsperada)) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@AfterMethod
	public void FinEjecucion() {
		driver.close();
	}

}
