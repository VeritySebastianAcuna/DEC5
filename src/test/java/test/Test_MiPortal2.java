package test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
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
import pages.PageDec5;
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
	
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
