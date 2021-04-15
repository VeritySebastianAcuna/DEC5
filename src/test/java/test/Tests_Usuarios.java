package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.CrearRut;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageDec5;
import pages.PageLoginAdm;
import pages.PageUsuarios;

public class Tests_Usuarios {
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
	public void Script_0175() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0175";
		System.out.println(cp);
		
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
		pageUsuarios.NombreRol(cp, "Prueba");
		pageUsuarios.BtnContinuar(cp);
		pageUsuarios.BtnAceptar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"myModalLabel\"]")).getText();
		System.out.println(mensaje);
		if(mensaje.contains("Asignar Usuarios a rol")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0176() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0176";
		System.out.println(cp);
		
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
		pageUsuarios.NombreRol(cp, "Prueba");
		pageUsuarios.BtnContinuar(cp);
		pageUsuarios.BtnCancelar(cp);
		
		if(driver.findElement(By.id("actionForm")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0177() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0177";
		System.out.println(cp);
		
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
		pageUsuarios.NombreRol(cp, "#$%&");
		pageUsuarios.BtnContinuar(cp);
		pageUsuarios.BtnAceptar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"dInput\"]/span")).getText();
		if(mensaje.equals("El campo Rol debe contener sólo caracteres alfanuméricos sin acentos ni apóstrofes")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0178() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0178";
		System.out.println(cp);
		
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
		
		if(driver.findElement(By.id("actionForm")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0182() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0182";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.OpcionUsuarios(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut = crearRut.RutPersona();
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRut(cp, rut);
		pageUsuarios.IngresarMail(cp, "asd");
		pageUsuarios.BtnAsignar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText();
		if(mensaje.equals("El campo Email debe contener un email válido.") || mensaje.equals("El campo Email es obligatorio.")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0185() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0185";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.OpcionUsuarios(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut = crearRut.RutPersona();
		rut=rut.substring(0,rut.length()-1);
		System.out.println("Rut cortado: "+rut);
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRut(cp, rut);
		pageUsuarios.IngresarMail(cp, "prueba@prueba.cl");
		pageUsuarios.BtnAsignar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[1]/div/span")).getText();
		if(mensaje.equals("RUT no válido")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0187() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0187";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.OpcionUsuarios(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut = crearRut.RutPersona();
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRut(cp, rut);
		pageUsuarios.IngresarMail(cp, "prueba prueba.cl");
		pageUsuarios.BtnAsignar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[2]/div/span")).getText();
		if(mensaje.equals("El campo Email debe contener un email válido.")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0196() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0196";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.OpcionUsuarios(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut = crearRut.RutPersona();
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRut(cp, rut);
		pageUsuarios.IngresarMail(cp, "prueba@prueba.cl");
		driver.findElement(By.name("date_to")).clear();
		pageUsuarios.BtnAsignar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[4]/div/span")).getText();
		if(mensaje.equals("El campo Hasta es obligatorio.")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0197() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0197";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.OpcionUsuarios(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut = crearRut.RutPersona();
		
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.AsignarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRut(cp, rut);
		pageUsuarios.IngresarMail(cp, "prueba@prueba.cl");
		pageUsuarios.BtnAsignar(cp);
		pageUsuarios.RescatarUsuarioAsociadoRol(cp,rut);
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0201() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0201";
		System.out.println(cp);
		
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
		pageUsuarios.CerrarPopupAsignarUsuariosRol(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0207() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0207";
		System.out.println(cp);
		
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
		pageUsuarios.CerrarPopupDeshabilitarUsuariosRol(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0210() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0210";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		pageDec5.CambiarEmpresa(cp);
		pageDec5.OpcionUsuarios(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut=crearRut.RutPersona();
		rut=rut+","+crearRut.RutPersona();
		System.out.println(rut);
		PageUsuarios pageUsuarios = new PageUsuarios(driver);
		pageUsuarios.DeshabilitarUsuariosRol(cp);
		pageUsuarios.SeleccionarRol(cp);
		pageUsuarios.IngresarRutInhabilitar(cp, rut);
		pageUsuarios.BtnInhabilitar(cp);
		
		String mensaje = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/label")).getText();
		if(mensaje.equals("Todos los usuarios fueron inhabilitados exitosamente.")) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0211() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0211";
		System.out.println(cp);
		
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
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/div[2]/div/div[1]/div/span")).getText().equals("El campo Rol es obligatorio.") 
		&& driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div/span")).getText().equals("El campo Lista de Usuarios es obligatorio.")) {
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
