package test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
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
import common.FechaActual;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageDec5;
import pages.PageFirmadoPorTodos;
import pages.PageLoginAdm;
import pages.PageMiPortal;
import pages.PagePendientes;
import pages.PageProcesoFirma;

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
	

	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
