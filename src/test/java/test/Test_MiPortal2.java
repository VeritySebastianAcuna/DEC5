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

public class Test_MiPortal2 {
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
	public void Script_0867() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0867";
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		// Seleccionar Cancelar del menu inferior 
		pageProcesoFirma.BtnCancelar_menu_inf(cp);
		
		//verificar Primer Registro deseleccionado (no checked)
		assertTrue(!driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
	
		if((driver.findElement(By.id("barra_herramientas")).isDisplayed()) && 
				(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0869() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0869";
		String num_Documento = "CA80000002CF380CO2";
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		// Seleccionar Vincular del menu inferior 
		pageProcesoFirma.BtnVincular_menu_inf(cp);
		
		// implementar función de búsqueda de segundo ID (Cod. Documento)
		// TO DO
		
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		
		//verificar Documento desplegado - deseleccionado (no checked)
		assertTrue(!driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected());
	
		//crear función boolean para verificar tests de popup
		
		if((driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0870() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0870";
		String num_Documento = "CA80000002CF380CO2";
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		// Seleccionar Vincular del menu inferior 
		pageProcesoFirma.BtnVincular_menu_inf(cp);
		
		// implementar función de búsqueda de segundo ID (Cod. Documento)
		// TO DO
		
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		
		//verificar Documento desplegado - deseleccionado (no checked)
		assertTrue(!driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected());
		
		pageProcesoFirma.VincularDocumento(cp);
	
		//crear función boolean para verificar tests de popup
		
		if(!(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected()) &&
				!(driver.findElement(By.id("relateDocSubmit")).isDisplayed())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0871() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0871";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
		String resultado_busqueda;
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		// Seleccionar Vincular del menu inferior 
		pageProcesoFirma.BtnVincular_menu_inf(cp);
		
		// implementar función de búsqueda de segundo ID (Cod. Documento)
		// TO DO
		
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		
		//crear función boolean para verificar tests de popup
		
		resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0872() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0872";
		String titulo_PopUp_esperado = "Vincular Documento";
		String titulo_PopUp;
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		// Seleccionar Vincular del menu inferior 
		pageProcesoFirma.BtnVincular_menu_inf(cp);
		
		pageProcesoFirma.CerrarVincularDocumento(cp);

		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
			
		}
		else {
			String texto = "Cierre de PopUp OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0874() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0874";
		String nombre_Carpeta_PopUp;
		String nombre_Carpeta_Agregada;
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		
		// Seleccionar Agregar A del menu inferior 
		pageProcesoFirma.BtnAgregarA_menu_inf(cp);
		
		//Seleccionar primera carpeta
		pageProcesoFirma.SeleccionarCarpeta_AgregarA(cp);
		
		nombre_Carpeta_PopUp = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/form/div/div[1]/label")).getText();
		
		pageProcesoFirma.BtnMover(cp);
		
		nombre_Carpeta_Agregada = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText();

		if((driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0) &&
				(nombre_Carpeta_PopUp.equals(nombre_Carpeta_Agregada))) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0876() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0876";
		String nombre_Nueva_Carpeta_PopUp = "Prueba QA -"+System.currentTimeMillis();
		String nombre_Carpeta_Agregada;
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		
		// Seleccionar Agregar A del menu inferior 
		pageProcesoFirma.BtnAgregarA_menu_inf(cp);
		
		int antes = pageProcesoFirma.CantidadCarpeta(cp);
		
		pageProcesoFirma.BtnCrearCarpeta(cp);
		pageProcesoFirma.CrearCarpeta_Mover_A(cp, nombre_Nueva_Carpeta_PopUp);
		
		int despues = pageProcesoFirma.CantidadCarpeta(cp);
		
		//pageProcesoFirma.BuscarCarpetaCreada(cp, nombre_Nueva_Carpeta_PopUp);
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0877() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0877";
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		
		// Seleccionar Agregar A del menu inferior 
		pageProcesoFirma.BtnAgregarA_menu_inf(cp);
		
		pageProcesoFirma.CerrarMoverA(cp);

		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0879() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0879";
		//String nombre_Nueva_Etiqueta_PopUp = "Etiqueta QA -"+System.currentTimeMillis();
		//String nombre_Etiqueta_Agregada;
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
		String btn_Etiquetar = "Etiquetar";
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
		
		//verificar Primer Registro seleccionado (checked)
		assertTrue(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected());
		
		// Seleccionar Etiquetar del menu inferior 
		pageProcesoFirma.BarraHerramientas(cp, "Etiquetar");
		
		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getText().equals(campo_texto_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[3]/button")).getText().equals(btn_Etiquetar)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0880() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0880";
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
		
		// Seleccionar Etiquetar del menu inferior 
		pageProcesoFirma.BarraHerramientas(cp, "Etiquetar");
		
		pageProcesoFirma.CerrarEtiquetar(cp);

		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0884() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0884";
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
		pageProcesoFirma.BarraHerramientas(cp, "Compartir");
		
		pageProcesoFirma.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pageProcesoFirma.CompartirDocumentoRut(cp, datos[1]);
		pageProcesoFirma.CompartirDocumentoBtnAgregar(cp);
		
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
	public void Script_0885() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0885";
		String rut_no_registrado = "18.215.678-7";
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
		pageProcesoFirma.BarraHerramientas(cp, "Compartir");
		
		pageProcesoFirma.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pageProcesoFirma.CompartirDocumentoRut(cp, rut_no_registrado);
		pageProcesoFirma.CompartirDocumentoIngresarMail(cp, "asd@asd.cl");
		pageProcesoFirma.CompartirDocumentoBtnAgregar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/span[1]")).getText().equals("Usuario no registrado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0888() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0888";
		String rut_valido = "23.409.729-6";
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
		pageProcesoFirma.BarraHerramientas(cp, "Compartir");
		
		pageProcesoFirma.CompartirDocumentoTipoPersona(cp, "ACEPTA");
		pageProcesoFirma.SeleccionRol(cp, "ADMIN");
		pageProcesoFirma.CompartirDocumentoRut(cp, rut_valido);
		pageProcesoFirma.CompartirDocumentoBtnAgregar(cp);
		pageProcesoFirma.CompartirDocumentoBtnCompartir(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0890() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0890";
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
		pageProcesoFirma.BarraHerramientas(cp, "Ocultar");
		pageProcesoFirma.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}

	
	@Test
	public void Script_0891() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0891";
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
		pageProcesoFirma.BarraHerramientas(cp, "Ocultar");
		pageProcesoFirma.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0893() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0893";
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
		
		// Generar Pre-condición : documento oculto
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.BarraHerramientas(cp, "Ocultar");
		pageProcesoFirma.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		pageProcesoFirma.BtnMostrarOcultos(cp);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.BarraHerramientas(cp, "Mostrar");
		pageProcesoFirma.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0894() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0894";
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
		
		// Generar Pre-condición : documento oculto
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.BarraHerramientas(cp, "Ocultar");
		pageProcesoFirma.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		pageProcesoFirma.BtnMostrarOcultos(cp);
		pageProcesoFirma.ClickPrimerRegistro(cp);
		pageProcesoFirma.BarraHerramientas(cp, "Mostrar");
		pageProcesoFirma.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0896() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0896";
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
		pageProcesoFirma.BarraHerramientas_Traza(cp);
		
		File folder = new File(downloadFilePath);
		File[] archivosAntes = folder.listFiles();
		System.out.println("Archivos antes: "+archivosAntes.length);
		pageProcesoFirma.TrazaBtnDescargaPdf(cp);
		File[] archivosDespues = folder.listFiles();
		System.out.println("Archivos después: "+archivosDespues.length);

		if(archivosDespues.length>archivosAntes.length) {
			crearLogyDocumento.CasoOk(cp);
		}
		else{
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0897() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0897";
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
		pageProcesoFirma.BarraHerramientas_Traza(cp);
		pageProcesoFirma.TrazaBtnClose(cp);

		if(driver.findElement(By.id("modaltraza")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	
	@Test
	public void Script_0899() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0899";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.IngresoEtiqueta(cp, "contrato");
		pageFirmadoPorTodos.BuscarEtiqueta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/label")).getText().contains("Contrato")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
			
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0900() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0900";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.IngresoEtiqueta(cp, "AJKLÑO");
		pageFirmadoPorTodos.BuscarEtiqueta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::td[1]")).getText().equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
			
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0903() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0903";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickCarpetas(cp);
		pageFirmadoPorTodos.LinkCrearNuevaCarpeta(cp);
		pageFirmadoPorTodos.CrearCarpeta(cp, nombre_Nueva_Carpeta);
		
		crearLogyDocumento.CasoOk(cp);

		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0904() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0904";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickCarpetas(cp);
		pageFirmadoPorTodos.AgregarCarpetaYaExistente(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div/div/div/span")).getText().equals("Carpeta ya existe")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0905() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0905";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickCarpetas(cp);
		pageFirmadoPorTodos.LinkCrearNuevaCarpeta(cp);
		pageFirmadoPorTodos.CrearCarpeta(cp, nombre_Nueva_Carpeta);
		pageFirmadoPorTodos.IngresarNombreCarpeta(cp, nombre_Nueva_Carpeta);
		pageFirmadoPorTodos.BtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0907() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0907";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickCarpetas(cp);
		pageFirmadoPorTodos.LinkCrearNuevaCarpeta(cp);
		pageFirmadoPorTodos.BtnFiltrarCarpetas(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}	
	
	@Test
	public void Script_0908() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0908";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickCarpetas(cp);
		pageFirmadoPorTodos.SeleccionarCarpeta(cp);
		String nombre_Carpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div[1]/button")).getText();
		pageFirmadoPorTodos.LinkEliminarCarpeta(cp);
		String titulo_PopUp_Eliminar_Carpeta = "¿Estás seguro de querer eliminar la siguente carpeta?";
	
		if(driver.findElement(By.id("modalDeleteFolders")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[1]/h1")).getText().equals(titulo_PopUp_Eliminar_Carpeta) &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[2]/ul/li")).getText().equals("- "+nombre_Carpeta) &&
				driver.findElement(By.id("eliminarFolder")).getText().equals("Eliminar")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	
	}

	
	@Test
	public void Script_0909() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0909";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickCarpetas(cp);
		
		int cantidadAntes = pageFirmadoPorTodos.CantidadCarpeta(cp);
		System.out.println("Cantidad inicial de carpetas: "+cantidadAntes);
		pageFirmadoPorTodos.SeleccionarCarpeta(cp);
		pageFirmadoPorTodos.LinkEliminarCarpeta(cp);
		pageFirmadoPorTodos.EliminarCarpeta(cp);
		int cantidadDespues = pageFirmadoPorTodos.CantidadCarpeta(cp);
		System.out.println("Cantidad final de carpetas: "+cantidadDespues);
	
		if(cantidadDespues<cantidadAntes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0912() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0912";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.CambiarEstadoFiltro(cp, "FIRMADO POR TODOS");
		pageFirmadoPorTodos.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0915() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0915";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.CambiarEstadoFiltro(cp, "RECHAZADO");
		pageFirmadoPorTodos.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0916() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0916";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.CambiarEstadoFiltro(cp, "TODOS");
		pageFirmadoPorTodos.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos") ||
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado") ||
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Pendiente") ||
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("En Proceso de Firma")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0917() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0917";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.FechaDesde(cp);
		pageFirmadoPorTodos.BtnFiltrar(cp);
		
	
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_from\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}

	
	@Test
	public void Script_0918() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0918";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.FechaHasta(cp);
		pageFirmadoPorTodos.BtnFiltrar(cp);
		
	
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_to\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	// VIERNES
	
	@Test
	public void Script_0919() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0919";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.FechaDesde(cp);
		pageFirmadoPorTodos.FechaHasta(cp);
		pageFirmadoPorTodos.BtnFiltrar(cp);
		
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
	public void Script_0921() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0921";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.TipoDocumento(cp, "Contrato");
		pageFirmadoPorTodos.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/label")).getText().contains("Contrato")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0922() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0922";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.TipoDocumento(cp, "123NM");
		pageFirmadoPorTodos.BtnFiltrar(cp);
		
		String mensaje = pageFirmadoPorTodos.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0923() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0923";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.BtnMostrarOcultos(cp);

		String mensaje = pageFirmadoPorTodos.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0924() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0924";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.BtnMostrarOcultos(cp);
		pageFirmadoPorTodos.BtnMostrarOcultos(cp);
		
		String mensaje = pageFirmadoPorTodos.MensajeSinResultados(cp);
		
		if(mensaje.equals("dataTables_empty") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0927() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0927";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.LinkAgregarCarpeta(cp);
		pageFirmadoPorTodos.MoverA(cp);
		String nombre_Carpeta_PopUp = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/form/div/div[1]/label")).getText();
		pageFirmadoPorTodos.BtnMover(cp);
		String nombre_Carpeta_Agregada = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText();

		if(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0 &&
				nombre_Carpeta_PopUp.equals(nombre_Carpeta_Agregada)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	

	@Test
	public void Script_0929() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0929";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.LinkAgregarCarpeta(cp);
		
		int antes = pageFirmadoPorTodos.CantidadCarpeta(cp);
		System.out.println("antes: "+antes);
		
		pageFirmadoPorTodos.BtnCrearCarpeta(cp);
		pageFirmadoPorTodos.CrearNuevaCarpeta(cp, nombre_Nueva_Carpeta);

		int despues = pageFirmadoPorTodos.CantidadCarpeta(cp);
		System.out.println("después: "+despues);
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0930() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0930";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.LinkAgregarCarpeta(cp);
		pageFirmadoPorTodos.BtnCrearCarpeta(cp);
		pageFirmadoPorTodos.IngresarNombreCarpeta_FirmadoPorTodos(cp, nombre_Nueva_Carpeta);
		pageFirmadoPorTodos.BtnCerrar_FirmadoPorTodos(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0932() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0932";
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
		String btn_Etiquetar = "Etiquetar";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.LinkAgregarEtiquetas(cp);
		
		
		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getAttribute("placeholder").equals(campo_texto_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0933() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0933";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.LinkAgregarEtiquetas(cp);
		pageFirmadoPorTodos.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	// Lunes 07/06/2021
	
	
	@Test
	public void Script_0935() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0935";
		String titulo_PopUp_Vincular = "Vincular Documento";
		String campo_texto_Etiqueta = "Búsqueda por palabra clave";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		System.out.println("titulo popup: "+driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText());
		System.out.println("titulo placeholder: "+driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder"));
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText().equals(titulo_PopUp_Vincular) &&
				driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder").equals(campo_texto_Etiqueta) &&
				!driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected()){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}

	
	@Test
	public void Script_0936() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0936";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		System.out.println("titulo popup: "+driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText());
		System.out.println("titulo placeholder: "+driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder"));
		
		pageFirmadoPorTodos.VincularDocumento(cp);
		
		//crear función boolean para verificar tests de popup
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected() &&
				driver.findElement(By.id("relateDocSubmit")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0937() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0937";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		String resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0938() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0938";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		pageFirmadoPorTodos.CerrarVincularDocumento(cp);

		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
			
		}
		else {
			String texto = "Cierre de PopUp OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0940() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0940";
		String titulo_PopUp_Desvincular_exito = "Vinculación Eliminada";
		String texto_PopUp_Desvincular_exito = "Documento desvinculado con éxito";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		pageFirmadoPorTodos.VincularDocumento(cp);
		pageFirmadoPorTodos.BtnVincular(cp);
		pageFirmadoPorTodos.LinkDesvincularDocumento(cp);
		pageFirmadoPorTodos.BtnSiDesvincularDocumento(cp);
		
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText().equals(titulo_PopUp_Desvincular_exito) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/p")).getText().equals(texto_PopUp_Desvincular_exito) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/button")).getText().equals("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0941() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0941";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		pageFirmadoPorTodos.VincularDocumento(cp);
		pageFirmadoPorTodos.BtnVincular(cp);
		pageFirmadoPorTodos.LinkDesvincularDocumento(cp);
		pageFirmadoPorTodos.BtnSiDesvincularDocumento(cp);
		pageFirmadoPorTodos.BtnCerrarDesvincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0942() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0942";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnVincularDocumento(cp);
		
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		pageFirmadoPorTodos.VincularDocumento(cp);
		pageFirmadoPorTodos.BtnVincular(cp);
		pageFirmadoPorTodos.LinkDesvincularDocumento(cp);
		pageFirmadoPorTodos.BtnNoDesvincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
		
		// Se continúa con el flujo para Desvincular documento
		pageFirmadoPorTodos.LinkDesvincularDocumento(cp);
		pageFirmadoPorTodos.BtnSiDesvincularDocumento(cp);
	}
	
	
	@Test
	public void Script_0944() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0944";
		String comentario = "Prueba QA";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		int antes = driver.findElements(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[1]/descendant::div")).size();
		System.out.println("Antes: "+antes);
		pageFirmadoPorTodos.BtnCrearComentario(cp);
		pageFirmadoPorTodos.IngresarComentario(cp, comentario);
		pageFirmadoPorTodos.BtnComentar(cp);
		int despues = driver.findElements(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[1]/descendant::div")).size();
		System.out.println("Despues: "+despues);
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0945() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0945";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageFirmadoPorTodos.BtnCrearComentario(cp);
		pageFirmadoPorTodos.BtnCancelarComentario(cp);

		if(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[9]/div/div[1]")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0947() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0947";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Cancelar");
		
		if((driver.findElement(By.id("barra_herramientas")).isDisplayed()) && 
				(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	// Martes 08/06/2021
	
	@Test
	public void Script_0949() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0949";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Vincular");
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		
		if((driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0950() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0950";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Vincular");
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		pageFirmadoPorTodos.VincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected() &&
				driver.findElement(By.id("relateDocSubmit")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0951() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0951";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Vincular");
		pageFirmadoPorTodos.Buscar_Vincular_Documento(cp, num_Documento);
		
		String resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0952() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0952";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Vincular");
		pageFirmadoPorTodos.CerrarVincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);	
		}
		else {
			String texto = "Cierre de PopUp OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0954() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0954";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Agregar a");
		
		//Seleccionar primera carpeta
		pageFirmadoPorTodos.SeleccionarCarpeta_AgregarA(cp);
		
		String nombre_Carpeta_PopUp = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/form/div/div[1]/label")).getText();
		
		pageFirmadoPorTodos.BtnMover(cp);
		
		String nombre_Carpeta_Agregada = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText();

		if((driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0) &&
				(nombre_Carpeta_PopUp.equals(nombre_Carpeta_Agregada))) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0956() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0956";
		String nombre_Nueva_Carpeta_PopUp = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Agregar a");
		
		int antes = pageFirmadoPorTodos.CantidadCarpeta(cp);
		System.out.println("Cantidad antes: "+antes);
		pageFirmadoPorTodos.BtnCrearCarpeta(cp);
		pageFirmadoPorTodos.CrearCarpeta_Mover_A(cp, nombre_Nueva_Carpeta_PopUp);
		
		int despues = pageFirmadoPorTodos.CantidadCarpeta(cp);
		System.out.println("Cantidad despues: "+despues);
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0957() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0957";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Agregar a");
		pageFirmadoPorTodos.CerrarMoverA(cp);

		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0959() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0959";
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Etiquetar");

		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getAttribute("placeholder").equals(campo_texto_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0960() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0960";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Etiquetar");
		pageFirmadoPorTodos.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0964() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0964";
		String rut_incorrecto = "1234";
		String email_incorrecto = "1234";
		String rut_registrado = "13.712.759-8";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Compartir");
		
		pageFirmadoPorTodos.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pageFirmadoPorTodos.CompartirDocumentoRut(cp, rut_incorrecto);
		pageFirmadoPorTodos.CompartirDocumentoIngresarMail(cp, email_incorrecto);
		pageFirmadoPorTodos.CompartirDocumentoBtnAgregar(cp);
		
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
	public void Script_0965() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0965";
		String rut_no_registrado = "18.215.678-7";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Compartir");
		
		pageFirmadoPorTodos.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pageFirmadoPorTodos.CompartirDocumentoRut(cp, rut_no_registrado);
		//pageFirmadoPorTodos.CompartirDocumentoBtnAgregar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/span[1]")).getText().equals("Usuario no registrado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	// Miércoles 09/06/2021
	
	@Test
	public void Script_0968() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0968";
		String rut_valido = "23.409.729-6";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Compartir");
		
		pageFirmadoPorTodos.CompartirDocumentoTipoPersona(cp, "ACEPTA");
		pageFirmadoPorTodos.SeleccionRol(cp, "ADMIN");
		pageFirmadoPorTodos.CompartirDocumentoRut_Acepta(cp, rut_valido);
		pageFirmadoPorTodos.CompartirDocumentoBtnAgregar(cp);
		pageFirmadoPorTodos.CompartirDocumentoBtnCompartir(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0970() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0970";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Ocultar");
		pageFirmadoPorTodos.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0971() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0971";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Ocultar");
		pageFirmadoPorTodos.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0973() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0973";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		// Generar Pre-condición : documento oculto
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Ocultar");
		pageFirmadoPorTodos.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		pageFirmadoPorTodos.BtnMostrarOcultos(cp);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Mostrar");
		pageFirmadoPorTodos.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0974() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0974";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		// Generar Pre-condición : documento oculto
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Ocultar");
		pageFirmadoPorTodos.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		pageFirmadoPorTodos.BtnMostrarOcultos(cp);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas(cp, "Mostrar");
		pageFirmadoPorTodos.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0977() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0977";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas_Traza(cp);
		
		File folder = new File(downloadFilePath);
		File[] archivosAntes = folder.listFiles();
		System.out.println("Archivos antes: "+archivosAntes.length);
		pageFirmadoPorTodos.TrazaBtnDescargaPdf(cp);
		File[] archivosDespues = folder.listFiles();
		System.out.println("Archivos después: "+archivosDespues.length);

		if(archivosDespues.length>archivosAntes.length) {
			crearLogyDocumento.CasoOk(cp);
		}
		else{
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0978() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0978";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		pageFirmadoPorTodos.ClickPrimerRegistro(cp);
		pageFirmadoPorTodos.BarraHerramientas_Traza(cp);
		
		pageFirmadoPorTodos.TrazaBtnClose(cp);

		if(driver.findElement(By.id("modaltraza")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0980() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0980";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		String Nombre_Etiqueta = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/span[1]")).getText().substring(0, 5);
		pageRechazados.IngresoEtiqueta(cp, Nombre_Etiqueta);
		pageRechazados.BuscarEtiqueta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/span[1]")).getText().contains(Nombre_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
			
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0981() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0981";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.IngresoEtiqueta(cp, "AJKLÑO");
		pageRechazados.BuscarEtiqueta(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::td[1]")).getText().equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
			
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0984() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0984";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		
		pageRechazados.ClickCarpetas(cp);
		pageRechazados.LinkCrearNuevaCarpeta(cp);
		pageRechazados.CrearCarpeta(cp, nombre_Nueva_Carpeta);
		
		crearLogyDocumento.CasoOk(cp);

		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0985() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0985";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		
		pageRechazados.ClickCarpetas(cp);
		pageRechazados.AgregarCarpetaYaExistente(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div/div/div/span")).getText().equals("Carpeta ya existe")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	// Jueves 10/06/2021
	
	@Test
	public void Script_0986() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0986";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickCarpetas(cp);
		pageRechazados.LinkCrearNuevaCarpeta(cp);
		pageRechazados.CrearCarpeta(cp, nombre_Nueva_Carpeta);
		pageRechazados.IngresarNombreCarpeta(cp, nombre_Nueva_Carpeta);
		pageRechazados.BtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0988() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0988";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickCarpetas(cp);
		pageRechazados.BtnFiltrarCarpetas(cp);
		
		crearLogyDocumento.CasoOk(cp);
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0989() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0989";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickCarpetas(cp);
		pageRechazados.SeleccionarCarpeta(cp);
		String nombre_Carpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div[1]/button")).getText();
		pageRechazados.LinkEliminarCarpeta(cp);
		String titulo_PopUp_Eliminar_Carpeta = "¿Estás seguro de querer eliminar la siguente carpeta?";
	
		if(driver.findElement(By.id("modalDeleteFolders")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[1]/h1")).getText().equals(titulo_PopUp_Eliminar_Carpeta) &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[2]/ul/li")).getText().equals("- "+nombre_Carpeta) &&
				driver.findElement(By.id("eliminarFolder")).getText().equals("Eliminar")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0990() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0990";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickCarpetas(cp);
		
		int cantidadAntes = pageRechazados.CantidadCarpeta(cp);
		System.out.println("Cantidad inicial de carpetas: "+cantidadAntes);
		pageRechazados.SeleccionarCarpeta(cp);
		pageRechazados.LinkEliminarCarpeta(cp);
		pageRechazados.EliminarCarpeta(cp);
		int cantidadDespues = pageRechazados.CantidadCarpeta(cp);
		System.out.println("Cantidad final de carpetas: "+cantidadDespues);
	
		if(cantidadDespues<cantidadAntes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0993() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0993";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.CambiarEstadoFiltro(cp, "RECHAZADO");
		pageRechazados.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0997() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0997";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.CambiarEstadoFiltro(cp, "TODOS");
		pageRechazados.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos") ||
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado") ||
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Pendiente") ||
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("En Proceso de Firma")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0998() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0998";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.FechaDesde(cp);
		pageRechazados.BtnFiltrar(cp);
		
	
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_from\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0999() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0999";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.FechaHasta(cp);
		pageRechazados.BtnFiltrar(cp);
		
	
		if(driver.findElement(By.xpath("//*[@id=\"__tag_date_to\"]/button")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1000() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1000";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.FechaDesde(cp);
		pageRechazados.FechaHasta(cp);
		pageRechazados.BtnFiltrar(cp);
		
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
	public void Script_1002() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1002";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		String Nombre_Etiqueta = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/span[1]")).getText().substring(0, 5);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.TipoDocumento(cp, Nombre_Etiqueta);
		pageRechazados.BtnFiltrar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]/span[1]")).getText().contains(Nombre_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	// Viernes 11/06/2021
	 
	@Test
	public void Script_0913() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0913";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.CambiarEstadoFiltro(cp, "FIRMADO POR TODOS");
		pageFirmadoPorTodos.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0914() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0914";
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
		pageMiPortal.ClickEnFirmadoPorTodos(cp);
		
		PageFirmadoPorTodos pageFirmadoPorTodos = new PageFirmadoPorTodos(driver);
		
		pageFirmadoPorTodos.ClickFiltrosAvanzados(cp);
		pageFirmadoPorTodos.CambiarEstadoFiltro(cp, "FIRMADO POR TODOS");
		pageFirmadoPorTodos.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Firmado por Todos")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0994() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0994";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.CambiarEstadoFiltro(cp, "RECHAZADO");
		pageRechazados.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0995() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0995";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.CambiarEstadoFiltro(cp, "RECHAZADO");
		pageRechazados.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0996() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0996";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.CambiarEstadoFiltro(cp, "RECHAZADO");
		pageRechazados.BtnFiltrar(cp);
	
		if(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::div[2]")).getAttribute("title").equals("Rechazado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	 
	@Test
	public void Script_1003() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1003";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickFiltrosAvanzados(cp);
		pageRechazados.TipoDocumento(cp, "123NM");
		pageRechazados.BtnFiltrar(cp);
		this.driver.switchTo().frame("NombreB");
		
		String mensaje = pageRechazados.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1004() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1004";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.BtnMostrarOcultos(cp);

		String mensaje = pageRechazados.MensajeSinResultados(cp);
		
		if(mensaje.equals("No se encontraron resultados") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1005() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1005";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.BtnMostrarOcultos(cp);
		pageRechazados.BtnMostrarOcultos(cp);
		
		String mensaje = pageRechazados.MensajeSinResultados(cp);
		
		if(mensaje.equals("dataTables_empty") || mensaje.length()>0) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1025() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1025";
		String comentario = "Prueba QA";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		int antes = driver.findElements(By.xpath("//*[@id=\"details-doc\"]/div/div[7]/div/div[1]/descendant::div")).size();
		System.out.println("Antes: "+antes);
		pageRechazados.BtnCrearComentario(cp);
		pageRechazados.IngresarComentario(cp, comentario);
		pageRechazados.BtnComentar(cp);
		int despues = driver.findElements(By.xpath("//*[@id=\"details-doc\"]/div/div[7]/div/div[1]/descendant::div")).size();
		System.out.println("Despues: "+despues);
		
		if(despues>=antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1026() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1026";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageRechazados.BtnCrearComentario(cp);
		pageRechazados.BtnCancelarComentario(cp);

		if(driver.findElement(By.id("comment-text")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1028() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1028";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		
		pageRechazados.BarraHerramientas(cp, "Cancelar");

		if((driver.findElement(By.id("barra_herramientas")).isDisplayed()) && 
				(driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[1]/input[1]")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1030() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1030";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		
		pageRechazados.BarraHerramientas(cp, "Vincular");
		pageRechazados.Buscar_Vincular_Documento(cp, num_Documento);
		
		if((driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1031() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1031";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		
		pageRechazados.BarraHerramientas(cp, "Vincular");
		pageRechazados.Buscar_Vincular_Documento(cp, num_Documento);
		pageRechazados.VincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected() &&
				driver.findElement(By.id("relateDocSubmit")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1032() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1032";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Vincular");
		pageRechazados.Buscar_Vincular_Documento(cp, num_Documento);
		
		String resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1033() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1033";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Vincular");
		pageRechazados.CerrarVincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);	
		}
		else {
			String texto = "Cierre de PopUp OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1035() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1035";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Agregar a");
		
		//Seleccionar primera carpeta
		pageRechazados.SeleccionarCarpeta_AgregarA(cp);
		
		String nombre_Carpeta_PopUp = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/form/div/div[1]/label")).getText();
		
		pageRechazados.BtnMover(cp);
		
		String nombre_Carpeta_Agregada = driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText();

		if((driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[1]/button/span[1]")).getText().length()>0) &&
				(nombre_Carpeta_PopUp.equals(nombre_Carpeta_Agregada))) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1037() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1037";
		String nombre_Nueva_Carpeta_PopUp = "Prueba QA -"+System.currentTimeMillis();
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Agregar a");
		
		int antes = pageRechazados.CantidadCarpeta(cp);
		System.out.println("Cantidad antes: "+antes);
		pageRechazados.BtnCrearCarpeta(cp);
		pageRechazados.CrearCarpeta_Mover_A(cp, nombre_Nueva_Carpeta_PopUp);
		
		int despues = pageRechazados.CantidadCarpeta(cp);
		System.out.println("Cantidad despues: "+despues);
		
		if(despues>antes) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1038() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1038";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Agregar a");
		pageRechazados.CerrarMoverA(cp);

		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	// Miércoles 30/06
	
	@Test
	public void Script_1040() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1040";
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Etiquetar");

		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getAttribute("placeholder").equals(campo_texto_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1041() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1041";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Etiquetar");
		pageRechazados.CerrarAgregarEtiquetas(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1045() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1045";
		String rut_incorrecto = "1234";
		String email_incorrecto = "1234";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Compartir");
		
		pageRechazados.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pageRechazados.CompartirDocumentoRut(cp, rut_incorrecto);
		pageRechazados.CompartirDocumentoIngresarMail(cp, email_incorrecto);
		pageRechazados.CompartirDocumentoBtnAgregar(cp);
		
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
	public void Script_1046() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1046";
		String rut_no_registrado = "18.215.678-7";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Compartir");
		
		pageRechazados.CompartirDocumentoTipoPersona(cp, "PERSONA NATURAL");
		pageRechazados.CompartirDocumentoRut(cp, rut_no_registrado);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/span[1]")).getText().equals("Usuario no registrado")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1049() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1049";
		String rut_valido = "23.409.729-6";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Compartir");
		
		pageRechazados.CompartirDocumentoTipoPersona(cp, "ACEPTA");
		pageRechazados.SeleccionRol(cp, "ADMIN");
		pageRechazados.CompartirDocumentoRut_Acepta(cp, rut_valido);
		pageRechazados.CompartirDocumentoBtnAgregar(cp);
		pageRechazados.CompartirDocumentoBtnCompartir(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1051() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1051";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Ocultar");
		pageRechazados.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1052() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1052";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Ocultar");
		pageRechazados.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1054() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1054";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		
		// Generar Pre-condición : documento oculto
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Ocultar");
		pageRechazados.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		pageRechazados.BtnMostrarOcultos(cp);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Mostrar");
		pageRechazados.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1055() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1055";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		
		// Generar Pre-condición : documento oculto
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Ocultar");
		pageRechazados.BtnMover(cp); // Botón Aceptar tiene el mismo id que botón Mover
		
		pageRechazados.BtnMostrarOcultos(cp);
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas(cp, "Mostrar");
		pageRechazados.OcultarDocumentoBtnCancelar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1058() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1058";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas_Traza(cp);
		
		File folder = new File(downloadFilePath);
		File[] archivosAntes = folder.listFiles();
		System.out.println("Archivos antes: "+archivosAntes.length);
		pageRechazados.TrazaBtnDescargaPdf(cp);
		File[] archivosDespues = folder.listFiles();
		System.out.println("Archivos después: "+archivosDespues.length);

		if(archivosDespues.length>archivosAntes.length) {
			crearLogyDocumento.CasoOk(cp);
		}
		else{
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_1059() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_1059";
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
		pageMiPortal.ClickEnRechazados(cp);
		
		PageRechazados pageRechazados = new PageRechazados(driver);
		
		pageRechazados.ClickPrimerRegistro(cp);
		pageRechazados.BarraHerramientas_Traza(cp);
		
		pageRechazados.TrazaBtnClose(cp);

		if(driver.findElement(By.id("modaltraza")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	// viernes 02/06/2021  Casos no automatizados por Sebastián
	
	@Test
	public void Script_0714() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0714";
		String nombre_Nueva_Carpeta = "Prueba QA -"+System.currentTimeMillis();
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
		pagePendientes.LinkCrearNuevaCarpeta(cp);
		pagePendientes.CrearCarpeta(cp, nombre_Nueva_Carpeta);
		
		crearLogyDocumento.CasoOk(cp);

		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0719() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0719";
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
		pagePendientes.SeleccionarCarpeta(cp);
		String nombre_Carpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div[1]/button")).getText();
		pagePendientes.LinkEliminarCarpeta(cp);
		String titulo_PopUp_Eliminar_Carpeta = "¿Estás seguro de querer eliminar la siguente carpeta?";
	
		if(driver.findElement(By.id("modalDeleteFolders")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[1]/h1")).getText().equals(titulo_PopUp_Eliminar_Carpeta) &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[2]/ul/li")).getText().equals("- "+nombre_Carpeta) &&
				driver.findElement(By.id("eliminarFolder")).getText().equals("Eliminar")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0743() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0743";
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
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
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pagePendientes.LinkAgregarEtiquetas(cp);
		
		
		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getAttribute("placeholder").equals(campo_texto_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0746() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0746";
		String titulo_PopUp_Vincular = "Vincular Documento";
		String campo_texto_Etiqueta = "Búsqueda por palabra clave";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pagePendientes.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pagePendientes.BtnVincularDocumento(cp);
		
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		System.out.println("titulo popup: "+driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText());
		System.out.println("titulo placeholder: "+driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder"));
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText().equals(titulo_PopUp_Vincular) &&
				driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder").equals(campo_texto_Etiqueta) &&
				!driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected()){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0747() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0747";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pagePendientes.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pagePendientes.BtnVincularDocumento(cp);
		
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		System.out.println("titulo popup: "+driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText());
		System.out.println("titulo placeholder: "+driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder"));
		
		pagePendientes.VincularDocumento(cp);
		
		//crear función boolean para verificar tests de popup
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected() &&
				driver.findElement(By.id("relateDocSubmit")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0748() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0748";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
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
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pagePendientes.BtnVincularDocumento(cp);
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		String resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0751() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0751";
		String titulo_PopUp_Desvincular_exito = "Vinculación Eliminada";
		String texto_PopUp_Desvincular_exito = "Documento desvinculado con éxito";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pagePendientes.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pagePendientes.BtnVincularDocumento(cp);
		
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		pagePendientes.VincularDocumento(cp);
		pagePendientes.BtnVincular(cp);
		pagePendientes.LinkDesvincularDocumento(cp);
		pagePendientes.BtnSiDesvincularDocumento(cp);
		
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText().equals(titulo_PopUp_Desvincular_exito) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/p")).getText().equals(texto_PopUp_Desvincular_exito) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/button")).getText().equals("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0760() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0760";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Vincular");
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		
		if((driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected())) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0761() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0761";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pagePendientes.ClickPrimerRegistro(cp);
		pagePendientes.BarraHerramientas(cp, "Vincular");
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		pagePendientes.VincularDocumento(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected() &&
				driver.findElement(By.id("relateDocSubmit")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0762() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0762";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
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
		pagePendientes.Buscar_Vincular_Documento(cp, num_Documento);
		
		String resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0770() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0770";
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
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

		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getAttribute("placeholder").equals(campo_texto_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0828() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0828";
		String titulo_PopUp_Eliminar_Carpeta = "¿Estás seguro de querer eliminar la siguente carpeta?";
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
		pageProcesoFirma.SeleccionarCarpeta(cp);
		String nombre_Carpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div[1]/button")).getText();
		pageProcesoFirma.LinkEliminarCarpeta(cp);
	
		if(driver.findElement(By.id("modalDeleteFolders")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[1]/h1")).getText().equals(titulo_PopUp_Eliminar_Carpeta) &&
				driver.findElement(By.xpath("//*[@id=\"modalDeleteFolders\"]/div/div/div[2]/ul/li")).getText().equals("- "+nombre_Carpeta) &&
				driver.findElement(By.id("eliminarFolder")).getText().equals("Eliminar")){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0851() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0851";
		String titulo_PopUp_Etiqueta = "Agregar Etiquetas";
		String campo_texto_Etiqueta = "Separa las etiquetas por comas";
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
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageProcesoFirma.LinkAgregarEtiquetas(cp);
		
		
		if(driver.findElement(By.id("myModalLabel")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/div/input")).getAttribute("placeholder").equals(campo_texto_Etiqueta)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0855() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0855";
		String titulo_PopUp_Vincular = "Vincular Documento";
		String campo_texto_Etiqueta = "Búsqueda por palabra clave";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageProcesoFirma.BtnVincularDocumento(cp);
		
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		System.out.println("titulo popup: "+driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText());
		System.out.println("titulo placeholder: "+driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder"));
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText().equals(titulo_PopUp_Vincular) &&
				driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder").equals(campo_texto_Etiqueta) &&
				!driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected()){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0856() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0856";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageProcesoFirma.BtnVincularDocumento(cp);
		
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		System.out.println("titulo popup: "+driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText());
		System.out.println("titulo placeholder: "+driver.findElement(By.xpath("//*[@id=\"modalSearchDocs\"]")).getAttribute("placeholder"));
		
		pageProcesoFirma.VincularDocumento(cp);
		
		//crear función boolean para verificar tests de popup
		
		if(driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td[1]/input")).isSelected() &&
				driver.findElement(By.id("relateDocSubmit")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	
	@Test
	public void Script_0857() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0857";
		String num_Documento = "MOCK_TEST";
		String resultado_busqueda_esperado = "No se encontraron resultados";
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
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageProcesoFirma.BtnVincularDocumento(cp);
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		String resultado_busqueda = driver.findElement(By.xpath("//*[@id=\"table-adjuntar\"]/tbody/tr/td")).getText();
		
		if(resultado_busqueda_esperado.equals(resultado_busqueda)) {
			String texto = "Busqueda OK";
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0860() throws InterruptedException, IOException, InvalidFormatException, AWTException {
		String cp = "DEC_0860";
		String titulo_PopUp_Desvincular_exito = "Vinculación Eliminada";
		String texto_PopUp_Desvincular_exito = "Documento desvinculado con éxito";
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
		
		String num_Documento = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[2]")).getAttribute("data-id");
		System.out.println("Número de documento: "+num_Documento);
		
		pageProcesoFirma.ClickPrimerRegistro(cp);
		
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.setAutoDelay(100);
		robot.mouseWheel(50);
		Thread.sleep(2000);
		
		pageProcesoFirma.BtnVincularDocumento(cp);
		
		pageProcesoFirma.Buscar_Vincular_Documento(cp, num_Documento);
		pageProcesoFirma.VincularDocumento(cp);
		pageProcesoFirma.BtnVincular(cp);
		pageProcesoFirma.LinkDesvincularDocumento(cp);
		pageProcesoFirma.BtnSiDesvincularDocumento(cp);
		
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText().equals(titulo_PopUp_Desvincular_exito) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/p")).getText().equals(texto_PopUp_Desvincular_exito) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/button")).getText().equals("Cerrar")){
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
