package test;

import static org.testng.Assert.assertEquals;

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
import pages.PageUsuarios;

public class Tests_Usuarios2 {
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
	
	@Test
	public void Script_0134() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0134";
		// Usuarios - Crear Rol
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.CrearRol(cp);
		
		System.out.println(driver.findElement(By.id("myModalLabel")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"r1\"]/div/label")).getText());
		System.out.println(driver.findElement(By.name("role")).isEnabled());
		System.out.println(driver.findElement(By.id("bContinuar")).isEnabled());
		System.out.println(driver.findElement(By.id("bContinuar")).getText().contains("Continuar"));
		System.out.println();
		
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Nuevo rol en ACEPTA") && 
				driver.findElement(By.xpath("//*[@id=\"r1\"]/div/label")).getText().contains("Nombre") &&
				driver.findElement(By.name("role")).isEnabled() &&	
				driver.findElement(By.id("bContinuar")).isEnabled() &&
				driver.findElement(By.id("bContinuar")).getText().contains("Continuar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0135() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0135";
		String nombreRol = "Prueba "+System.currentTimeMillis();
		// Usuarios - Crear Rol - ingresar nombre ok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.CrearRol(cp);
		pageUsuarios.NombreRol(cp, nombreRol);
		pageUsuarios.BtnContinuar(cp);
		
		System.out.println(driver.findElement(By.id("myModalLabel")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"r2\"]/p")).getText());
		System.out.println(driver.findElement(By.id("bCancelar")).isEnabled());
		System.out.println(driver.findElement(By.id("bCancelar")).getText().contains("Cancelar"));
		System.out.println(driver.findElement(By.id("bAceptar")).isEnabled());
		System.out.println(driver.findElement(By.id("bAceptar")).getText().contains("Aceptar"));
		
		String textoEsperado = "¿Está seguro de crear el rol "+ nombreRol +"?\r\n" + 
				"\r\n" + 
				"Recuerde que una vez guardado, no podrá ser eliminado";
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"r2\"]/p")).getText().contains(textoEsperado));
		System.out.println(textoEsperado);
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Nuevo rol en ACEPTA") && 
				//driver.findElement(By.xpath("//*[@id=\"r2\"]/p")).getText().contains(textoEsperado) &&
				driver.findElement(By.id("bCancelar")).isEnabled() &&
				driver.findElement(By.id("bCancelar")).getText().contains("Cancelar") &&	
				driver.findElement(By.id("bAceptar")).isEnabled() &&
				driver.findElement(By.id("bAceptar")).getText().contains("Aceptar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0136() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0136";
		String nombreRol = "Prueba "+System.currentTimeMillis();
		// Usuarios - Crear Rol - ingresar nombre ok - aceptar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.CrearRol(cp);
		pageUsuarios.NombreRol(cp, nombreRol);
		pageUsuarios.BtnContinuar(cp);
		pageUsuarios.BtnAceptar(cp);
		
		System.out.println(driver.findElement(By.id("myModalLabel")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/label")).getText());
		
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Asignar Usuarios a rol "+nombreRol) && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/label")).getText().contains("Usuario y Periodo de Validez")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0137() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0137";
		String nombreRol = "Prueba "+System.currentTimeMillis();
		// Usuarios - Crear Rol - ingresar nombre ok - cancelar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.CrearRol(cp);
		pageUsuarios.NombreRol(cp, nombreRol);
		pageUsuarios.BtnContinuar(cp);
		pageUsuarios.BtnCancelar(cp);
		
		if(!driver.findElement(By.id("myModalLabel")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0138() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0138";
		String nombreRol = "$_";
		// Usuarios - Crear Rol - ingresar nombre nok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.CrearRol(cp);
		pageUsuarios.NombreRol(cp, nombreRol);
		pageUsuarios.BtnContinuar(cp);
		pageUsuarios.BtnAceptar(cp);
		
		if(driver.findElement(By.className("error-text")).getText().contains("El campo Rol debe contener sólo caracteres alfanuméricos sin acentos ni apóstrofes")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0139() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0139";
		// Usuarios - Crear Rol - ingresar nombre ok - cancelar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.CrearRol(cp);
		pageUsuarios.CerrarPopUpNuevoRol(cp);
		
		if(!driver.findElement(By.id("actionForm")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0143() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0143";
		// Usuarios - Asignar Usuarios a un Rol - rol email formato nok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRolMail(cp, "123");
		pageUsuarios.IngresarRut(cp, "5985844-0");
		pageUsuarios.IngresarMail(cp, "correo@correo.com");
		pageUsuarios.BtnAsignar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText().contains("El campo Email debe contener un email válido.")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0146() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0146";
		// Usuarios - Asignar Usuarios a un Rol - rut no valido
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRolMail(cp, "123");
		pageUsuarios.IngresarRut(cp, "123");
		pageUsuarios.IngresarMail(cp, "correo@correo.com");
		pageUsuarios.BtnAsignar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[1]/div/span")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[1]/div/span")).getText().contains("RUT no válido")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0148() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0148";
		// Usuarios - Asignar Usuarios a un Rol - email formato no valido
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRolMail(cp, "123");
		pageUsuarios.IngresarRut(cp, "5985844-0");
		pageUsuarios.IngresarMail(cp, "nnnnn.");
		pageUsuarios.BtnAsignar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText().contains("El campo Email debe contener un email válido.")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0151() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0151";
		// Usuarios - Asignar Usuarios a un Rol - fecha desde - formato nok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRolMail(cp, "123");
		pageUsuarios.IngresarRut(cp, "5985844-0");
		pageUsuarios.IngresarMail(cp, "correo@correo.com");
		pageUsuarios.ingresarFechaDesdeVacia(cp);
		pageUsuarios.BtnAsignar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[3]/div/span")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[3]/div/span")).getText().contains("El campo Desde es obligatorio.")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0154() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0154";
		// Usuarios - Asignar Usuarios a un Rol - fecha hasta - formato nok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRolMail(cp, "123");
		pageUsuarios.IngresarRut(cp, "5985844-0");
		pageUsuarios.IngresarMail(cp, "correo@correo.com");
		pageUsuarios.ingresarFechaHastaVacia(cp);
		pageUsuarios.BtnAsignar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[4]/div/span")).getText());
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[4]/div/span")).getText().contains("El campo Hasta es obligatorio.")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0157() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0157";
		// Usuarios - Asignar Usuarios a un Rol - boton asignar sin datos
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.ingresarFechaDesdeVacia(cp);
		pageUsuarios.ingresarFechaHastaVacia(cp);
		pageUsuarios.BtnAsignar(cp);
		
		
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[1]/div/span")).getText().contains("El campo RUT es obligatorio.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText().contains("El campo Email es obligatorio.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[3]/div/span")).getText().contains("El campo Desde es obligatorio.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[4]/div/span")).getText().contains("El campo Hasta es obligatorio.")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0159() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0159";
		// Usuarios - Asignar Usuarios a un Rol - asignacion masiva
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.AsignacionMasiva(cp);
		
		
		//imprimir valores
		System.out.println(driver.findElement(By.id("myModalLabel")).getText()); 
		System.out.println(driver.findElement(By.cssSelector("select[id='role']")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[1]/div[2]/div/div[2]/div/label")).getText());
		System.out.println(driver.findElement(By.name("role_email")).isDisplayed());
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/label")).getText());
		System.out.println(driver.findElement(By.name("date_from_m")).isDisplayed());
		System.out.println(driver.findElement(By.name("date_to_m")).isDisplayed());
		System.out.println(driver.findElement(By.name("indef_m")).isDisplayed());
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[4]/button")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[4]/button")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).getText());
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/p/small/i")).getText());
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).isEnabled());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).getText());	
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[2]")).isEnabled());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[2]")).getText());
		
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Asignar Usuarios a un Rol") && 
				driver.findElement(By.cssSelector("select[id='role']")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[1]/div[2]/div/div[2]/div/label")).getText().contains("Rol Email") &&
				driver.findElement(By.name("role_email")).isDisplayed() &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/label")).getText().contains("Periodo de Validez") &&
				driver.findElement(By.name("date_from_m")).isDisplayed() &&
				driver.findElement(By.name("date_to_m")).isDisplayed() &&
				driver.findElement(By.name("indef_m")).isDisplayed() &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[4]/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[4]/button")).getText().contains("Descargar Plantilla") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).getText().contains("Subir Plantilla") &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/p/small/i")).getText().contains("* Utilice solo las dos primeras columnas (los usuarios sin email o con email inválido no serán considerados)") &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).isEnabled() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).getText().contains("Cerrar") &&	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[2]")).isEnabled() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[2]")).getText().contains("Guardar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}

	
	@Test
	public void Script_0161() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0161";
		// Usuarios - Asignar Usuarios a un Rol - asignacion masiva - subir plantilla
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.AsignacionMasiva(cp);
		pageUsuarios.BtnSubirPlantilla(cp);
		
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		String tituloVentana1 = driver.getTitle();
		System.out.println("Imprimiendo nombre de ventana Windows 2: "+tituloVentana1);
		int cantidad = 0;
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
				cantidad = 1;
			}
		}
		
		String tituloVentana2 = driver.getTitle();
		System.out.println("Imprimiendo nombre de ventana Windows 2: "+tituloVentana2);
		System.out.println("cantidad: "+cantidad);
		
		/*
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Asignar Usuarios a un Rol") && 
				driver.findElement(By.cssSelector("select[id='role']")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[1]/div[2]/div/div[2]/div/label")).getText().contains("Rol Email") &&
				driver.findElement(By.name("role_email")).isDisplayed() &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/label")).getText().contains("Periodo de Validez") &&
				driver.findElement(By.name("date_from_m")).isDisplayed() &&
				driver.findElement(By.name("date_to_m")).isDisplayed() &&
				driver.findElement(By.name("indef_m")).isDisplayed() &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[4]/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[4]/button")).getText().contains("Descargar Plantilla") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).getText().contains("Subir Plantilla") &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/p/small/i")).getText().contains("* Utilice solo las dos primeras columnas (los usuarios sin email o con email inválido no serán considerados)") &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).isEnabled() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).getText().contains("Cerrar") &&	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[2]")).isEnabled() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[2]")).getText().contains("Guardar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		*/
	}
	
	
	@Test
	public void Script_0162() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0162";
		// Usuarios - Asignar Usuarios a un Rol - Guardar - sin datos
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.BtnGuardar(cp);
		

		if(!driver.findElement(By.id("myModalLabel")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0164() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0164";
		// Usuarios - Asignar Usuarios a un Rol - Cerrar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.BtnCerrar(cp);
		

		if(!driver.findElement(By.id("myModalLabel")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0168() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0168";
		// Usuarios - Deshabilitar Usuarios de un Rol - ingresar ruts invalidos - cerrar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRutInhabilitar(cp, "123");
		pageUsuarios.BtnInhabilitar(cp);
		pageUsuarios.BtnCerrarDeshabilitar(cp);
		
		
		if(!driver.findElement(By.id("myModalLabel")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0169() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0169";
		// Usuarios - Deshabilitar Usuarios de un Rol - ingresar ruts separados por espacio
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		String ruts = "13712759-8 18092588-0";
		pageUsuarios.IngresarRutInhabilitar(cp, ruts);
		pageUsuarios.BtnInhabilitar(cp);
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.id("myModalLabel")).getText().contains("Deshabilitar Usuarios de un Rol") && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/label")).getText().contains("Los siguientes usuarios no pudieron procesarse, revise por favor.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).getText().contains("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0167() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0167";
		// Usuarios - Deshabilitar Usuarios de un Rol - ingresar ruts invalidos
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		String rut = "123";
		pageUsuarios.IngresarRutInhabilitar(cp, rut);
		pageUsuarios.BtnInhabilitar(cp);
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.id("myModalLabel")).getText().contains("Deshabilitar Usuarios de un Rol") && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/label")).getText().contains("Los siguientes usuarios no pudieron procesarse, revise por favor.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).getText().contains("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0170() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0170";
		// Usuarios - Deshabilitar Usuarios de un Rol - ingresar rut separados por carácter (no coma)
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		String ruts = "13712759-8#18092588-0";
		pageUsuarios.IngresarRutInhabilitar(cp, ruts);
		pageUsuarios.BtnInhabilitar(cp);
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.id("myModalLabel")).getText().contains("Deshabilitar Usuarios de un Rol") && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/label")).getText().contains("Los siguientes usuarios no pudieron procesarse, revise por favor.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).getText().contains("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0171() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0171";
		// Usuarios - Deshabilitar Usuarios de un Rol - ingresar rut separado por coma
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		String ruts = "13712759-8,18092588-0";
		pageUsuarios.IngresarRutInhabilitar(cp, ruts);
		pageUsuarios.BtnInhabilitar(cp);
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.id("myModalLabel")).getText().contains("Deshabilitar Usuarios de un Rol") && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/label")).getText().contains("Todos los usuarios fueron inhabilitados exitosamente.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).getText().contains("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0172() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0172";
		// Usuarios - Deshabilitar Usuarios de un Rol - boton inhabilitar sin datos
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.BtnInhabilitar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/div[2]/div/div[1]/div/span")).getText().contains("El campo Rol es obligatorio.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div/span")).getText().contains("El campo Lista de Usuarios es obligatorio.")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0173() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0173";
		// Usuarios - Deshabilitar Usuarios de un Rol - boton inhabilitar con datos
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		String ruts = "13712759-8,18092588-0";
		pageUsuarios.IngresarRutInhabilitar(cp, ruts);
		pageUsuarios.BtnInhabilitar(cp);
		
		if(driver.findElement(By.id("myModalLabel")).isDisplayed() &&
				driver.findElement(By.id("myModalLabel")).getText().contains("Deshabilitar Usuarios de un Rol") && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/label")).getText().contains("Todos los usuarios fueron inhabilitados exitosamente.") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).getText().contains("Cerrar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0174() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0174";
		// Usuarios - Deshabilitar Usuarios de un Rol - masiva 
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.BtnDeshabilitacionMasiva(cp);
	
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Deshabilitar Usuarios de un Rol") && 
				driver.findElement(By.cssSelector("select[id='role']")).isDisplayed() &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[1]/button")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[1]/button")).getText().contains("Descargar Plantilla") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[2]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[2]/div[2]")).getText().contains("Subir Plantilla") &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/p/small/i")).getText().contains("* Utilice solo la primera columna") &&
				
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).isEnabled() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).getText().contains("Inhabilitar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0177() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0177";
		// Usuarios - Deshabilitar Usuarios de un Rol - deshabilitacion masiva - subir plantilla nok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.BtnDeshabilitacionMasiva(cp);
		pageUsuarios.SeleccionarRol(cp);
		String nombreArchivo = "PruebaQA.txt";
		pageUsuarios.SubirPlantilla(cp, nombreArchivo);
		
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[2]/div[3]/span")).getText().contains("Archivo Inválido")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0178() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0178";
		// Usuarios - Deshabilitar Usuarios de un Rol - masiva 
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnCuentasRegistradas(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/div")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/div")).getText().contains("Solicitud ingresada - ID Reporte"));
		System.out.println(driver.findElement(By.partialLinkText("Ver Reportes")).isDisplayed());
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Descargar Cuentas Registradas") && 	
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/div")).getText().contains("Solicitud ingresada - ID Reporte") &&
				driver.findElement(By.partialLinkText("Ver Reportes")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0179() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0179";
		// Usuarios - Cuentas Registradas - ver reportes 
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnCuentasRegistradas(cp);
		pageUsuarios.LinkVerReportes(cp);
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
			}
		}
		
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
		
		System.out.println("imprimir valores de grilla");
		System.out.println(driver.findElement(By.id("empname")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText());		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_length\"]/label")).getText());
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[3]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[4]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[5]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[6]")).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[7]")).getText());
		System.out.println(driver.findElement(By.id("tabla_grilla_reportesNEW_info")).getText());
		System.out.println(driver.findElement(By.partialLinkText("Anterior")).getText());
		System.out.println(driver.findElement(By.partialLinkText("Siguiente")).getText());
		
		if(driver.findElement(By.id("empname")).getText().contains("ACEPTA") && 
				driver.findElement(By.xpath("//*[@id=\"form_params\"]/div[1]/h3")).getText().contains("Reportes") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_length\"]/label")).getText().contains("Mostrar") &&	
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_length\"]/label")).getText().contains("registros") &&
				
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_filter\"]/label")).getText().contains("Buscar:") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_filter\"]/label/input")).isDisplayed() &&

				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[1]")).getText().contains("N°") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[3]")).getText().contains("ID") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[4]")).getText().contains("FECHA") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[5]")).getText().contains("CANAL") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[6]")).getText().contains("FILTROS") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[7]")).getText().contains("TOTAL REGISTROS") &&
				driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/thead/tr/th[8]")).getText().contains("ESTADO") &&
				
				driver.findElement(By.id("tabla_grilla_reportesNEW_info")).getText().contains("Mostrando registros del 1 al "+filas+" de un total de "+filas+" registros")){
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
	public void Script_0181() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0181";
		// Usuarios - Cuentas Registradas - reportes buscar 
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnCuentasRegistradas(cp);
		pageUsuarios.LinkVerReportes(cp);
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
			}
		}
		
		Thread.sleep(3000);
		
		String elemento = driver.findElement(By.xpath("/html/body/div[8]/div/section/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(elemento);
		pageUsuarios.ReportesBuscar(cp, elemento);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);

	
		if(driver.findElement(By.xpath("/html/body/div[8]/div/section/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText().contains(elemento) &&
				filas == 1){
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
	public void Script_0182() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0182";
		// Usuarios - Cuentas Registradas - eliminar
		String texto_esperado = "¿Desea continuar? Esta acción eliminará el registro seleccionado.";
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnCuentasRegistradas(cp);
		pageUsuarios.LinkVerReportes(cp);
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
			}
		}
		
		Thread.sleep(3000);
		pageUsuarios.ClicOpciones(cp);
		Thread.sleep(2000);
		pageUsuarios.ClicEliminar(cp);
		
	
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();

		if(texto_alerta.contains(texto_esperado)){
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
	public void Script_0184() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0184";
		// Usuarios - Cuentas Registradas - eliminar - cancelar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnCuentasRegistradas(cp);
		pageUsuarios.LinkVerReportes(cp);
		
		//cambio de ventana
		String mainWin = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()){
			if(!handle.equals(mainWin)){
				driver.switchTo().window(handle);
			}
		}
		
		Thread.sleep(3000);
		pageUsuarios.ClicOpciones(cp);
		Thread.sleep(2000);
		pageUsuarios.ClicEliminar(cp);
		
	
		// Alerta
		String texto_alerta = driver.switchTo().alert().getText();
		System.out.println(texto_alerta);
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(2000);

		if(!pageUsuarios.AlertIsPresent(cp)){
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
	public void Script_0190() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0190";
		// Usuarios - busqueda por rut nok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.IngresarRutUsuario(cp, "123");
		
		Thread.sleep(3000);
		
		String mensaje = driver.findElement(By.className("dataTables_empty")).getText();
		System.out.println(mensaje);

		if(mensaje.contains("No se encontraron resultados")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0191() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0191";
		// Usuarios - busqueda por rut ok
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		String rut_esperado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr[1]/td[2]/div/div")).getText();
		System.out.println(rut_esperado);
		pageUsuarios.IngresarRutUsuario(cp, rut_esperado);
		
		Thread.sleep(3000);
		
		String rut_mostrado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr/td[2]/div/div")).getText();
		System.out.println(rut_mostrado);

		if(rut_mostrado.contains(rut_esperado)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0192() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0192";
		// Usuarios - busqueda por rut no existente
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.IngresarRutUsuario(cp, "11111111-1");
		
		Thread.sleep(3000);
		
		String mensaje = driver.findElement(By.className("dataTables_empty")).getText();
		System.out.println(mensaje);

		if(mensaje.contains("No se encontraron resultados")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	
	@Test
	public void Script_0193() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0193";
		// Usuarios - busqueda por roles 
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.SeleccionarRolUsuario(cp, "Admin");
		
		Thread.sleep(3000);
		
		int filas = driver.findElements(By.xpath("//*[@id=\"table-admin-users\"]/tbody/descendant::tr")).size();
		System.out.println("cantidad filas:"+filas);
		
		/*
		if(driver.findElement(By.xpath("/html/body/div[8]/div/section/div[2]/div/div[1]/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText().contains(elemento) &&
				filas == 1){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		*/
	}
	
	
	@Test
	public void Script_0195() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0195";
		// Usuarios - check bok Notifica Mail Personal
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		String rut_esperado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr[1]/td[2]/div/div")).getText();
		System.out.println(rut_esperado);
		
		pageUsuarios.CheckNotificaMailPersonal(cp);
		pageUsuarios.IngresarRutUsuario(cp, rut_esperado);
		
		Thread.sleep(3000);
		
		String rut_mostrado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr/td[2]/div/div")).getText();
		System.out.println(rut_mostrado);

		if(rut_mostrado.contains(rut_esperado)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0196() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0196";
		// Usuarios - check bok Notifica Mail Institucional
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		String rut_esperado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr[1]/td[2]/div/div")).getText();
		System.out.println(rut_esperado);
		
		pageUsuarios.CheckNotificaMailInstitucional(cp);
		pageUsuarios.IngresarRutUsuario(cp, rut_esperado);
		
		Thread.sleep(3000);
		
		String rut_mostrado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr/td[2]/div/div")).getText();
		System.out.println(rut_mostrado);

		if(rut_mostrado.contains(rut_esperado)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0197() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0197";
		// Usuarios - check bok Notifica Mail Documento
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		String rut_esperado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr[1]/td[2]/div/div")).getText();
		System.out.println(rut_esperado);
		
		pageUsuarios.CheckNotificaMailDocumentos(cp);
		pageUsuarios.IngresarRutUsuario(cp, rut_esperado);
		
		Thread.sleep(3000);
		
		String rut_mostrado = driver.findElement(By.xpath("//*[@id=\"table-admin-users\"]/tbody/tr/td[2]/div/div")).getText();
		System.out.println(rut_mostrado);

		if(rut_mostrado.contains(rut_esperado)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	
	@Test
	public void Script_0198() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0198";
		// Usuarios - Configurar Notificaciones
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnConfigurarNotificaciones(cp);
		
		Thread.sleep(1000);
		
		if(driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[1]/h2")).getText().contains("Configuración Masiva de Notificaciones") && 
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[2]/div[1]/p")).getText().contains("La siguiente configuracion será aplicada a todos los usuarios") &&
				
				driver.findElement(By.id("notif_roles_personal")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[2]/div[2]/div[1]/label")).getText().contains("Notifica Mail Personal") &&
				driver.findElement(By.id("notif_roles_institucional")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[2]/div[2]/div[2]/label")).getText().contains("Notifica Mail Institucional") &&
				driver.findElement(By.id("notif_roles_docs")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[2]/div[2]/div[3]/label")).getText().contains("Notifica Mail Documento") &&
				
				
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[2]/div[3]/p")).getText().contains("Nota: Esta acción sobreescribirá la configuración existente.") &&
				
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[3]/button[1]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[3]/button[1]")).getText().contains("Cancelar") &&
				
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[3]/button[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[3]/button[2]")).getText().contains("Modificar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0203() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0203";
		// Usuarios - Configurar Notificaciones - boton cancelar
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
		pageDec5.OpcionUsuarios(cp);
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.BtnConfigurarNotificaciones(cp);
		pageUsuarios.BtnCancelarConfigurarNotificaciones(cp);
		
		Thread.sleep(1000);
		
		if(!driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[1]/h2")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}

		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	

	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
