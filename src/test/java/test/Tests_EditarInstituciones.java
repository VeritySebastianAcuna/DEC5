package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageDec5;
import pages.PageEditarInstituciones;
import pages.PageInstituciones;
import pages.PageLoginAdm;

public class Tests_EditarInstituciones {
	private WebDriver driver;
	String datapool = Configuration.ROOT_DIR+"DataPool.xlsx";
	LeerExcel leerExcel = new LeerExcel(); 
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
//		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://5cap.dec.cl/portal");// Aqu? se ingresa la URL para hacer las pruebas.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void Script_0124() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0124";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarRut(cp, "123213231");
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.ValidarMensajeRut(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0125() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0125";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarRut(cp, "123213231-lKj");
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.ValidarMensajeRut(cp);
		
		System.out.println("FLUJO OK");
	}
	@Test
	public void Script_0126() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0126";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarDescripcion(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0127() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0127";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarMail(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0128() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0128";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarMailNotificaciones(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0129() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0129";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.FlagAceptaNo(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0130() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0130";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.FlagAceptaSi(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0131() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0131";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarDescripcion(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0132() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0132";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarRubro(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0133() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0133";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarPais(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0134() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0134";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarComuna(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0135() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0135";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarDireccion(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0136() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0136";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.EditarColor(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0140() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0140";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckNoRegistro(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0141() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0141";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckNoHuella(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0142() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0142";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckExtranjeros(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0143() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0143";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckPlantillaColaborativa(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0144() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0144";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckFirmantePorDefinir(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0145() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0145";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckEnvioAdjuntoPendiente(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0146() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0146";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckDescripcionCargaBatch(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0147() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0147";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckVacacionesRex(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0148() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0148";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckRelacionEntreInstituciones(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0149() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0149";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckTipoDocumentoExterno(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0150() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0150";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckModuloReportes(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0151() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0151";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckDescargarVinculados(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0152() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0152";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckReenvioDocumentoAprobado(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0153() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0153";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckCasillaDigital(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0154() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0154";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckMensajes(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0155() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0155";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckPdfConPassword(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0156() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0156";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckCampoImagenPlantillaDec(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0157() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0157";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.ClickRuedaConfiguracion(cp);
		pageDec5.OpcionInstituciones(cp);
		
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.EditarInstitucion(cp);
		
		PageEditarInstituciones pageEditarInstituciones = new PageEditarInstituciones(driver);
		pageEditarInstituciones.CheckValidadorDocumental(cp);
		pageEditarInstituciones.BtnGuardar(cp);
		pageEditarInstituciones.MensajeEditado(cp);
		
		System.out.println("FLUJO OK");
	}
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
