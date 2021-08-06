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
import pages.PageEditarCuenta;
import pages.PageLoginAdm;
import pages.PageUsuarios;

public class Tests_EditarCuenta {
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
	public void Script_0065() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0065";
		// Editar Cuenta - clave - Editar nombre
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		String nombreActual = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[1]/td[2]/div/span")).getText();
		System.out.println(nombreActual);
		pageEditarCuenta.LinkEditarNombre(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[1]/td[2]/div/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.id("c_name")).getText());
		System.out.println(driver.findElement(By.id("s_name")).getText());
		
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[1]/td[2]/div/input")).getAttribute("value").contains(nombreActual) && 	
				driver.findElement(By.id("c_name")).getText().contains("Cancelar") &&
				driver.findElement(By.id("s_name")).getText().contains("Guardar")){
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
	public void Script_0066() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0066";
		// Editar Cuenta - clave - Editar teléfono
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		String telefonoActual = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[3]/td[2]/div")).getText();
		System.out.println(telefonoActual);
		System.out.println(telefonoActual.substring(3));
		pageEditarCuenta.LinkEditarTelefono(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[3]/td[2]/div/div/div[2]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.id("c_phone")).getText());
		System.out.println(driver.findElement(By.id("s_phone")).getText());
		
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[3]/td[2]/div/div/div[2]/input")).getAttribute("value").contains(telefonoActual.substring(3)) && 	
				driver.findElement(By.id("c_phone")).getText().contains("Cancelar") &&
				driver.findElement(By.id("s_phone")).getText().contains("Guardar")){
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
	public void Script_0067() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0067";
		// Editar Cuenta - clave - Editar correo
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		String correoActual = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[4]/td[2]/div[1]/span")).getText();
		System.out.println(correoActual);
		pageEditarCuenta.LinkEditarCorreo(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[4]/td[2]/div[1]/input")).getAttribute("value"));
		System.out.println(driver.findElement(By.id("c_email")).getText());
		System.out.println(driver.findElement(By.id("s_email")).getText());
		
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[4]/td[2]/div[1]/input")).getAttribute("value").contains(correoActual) && 	
				driver.findElement(By.id("c_email")).getText().contains("Cancelar") &&
				driver.findElement(By.id("s_email")).getText().contains("Guardar")){
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
	public void Script_0068() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0068";
		// Editar Cuenta - clave - Editar clave
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		String claveActual = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[4]/td[2]/div[1]/span")).getText();
		System.out.println(claveActual);
		pageEditarCuenta.LinkEditarClave(cp);
		
		System.out.println(driver.findElement(By.id("c_password")).getText());
		System.out.println(driver.findElement(By.id("s_password")).getText());
		
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[6]/td[1]/div/div[1]/div/div/input")).isDisplayed() && 	
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[6]/td[1]/div/div[2]/div[1]/div/input")).isDisplayed() && 
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[6]/td[1]/div/div[2]/div[2]/div/input")).isDisplayed() && 
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[6]/td[1]/div/div[2]/div[3]/div")).isDisplayed() && 
				driver.findElement(By.id("c_password")).getText().contains("Cancelar") &&
				driver.findElement(By.id("s_password")).getText().contains("Guardar")){
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
	public void Script_0069() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0069";
		// Editar Cuenta - clave - Registro de Certificado HSM
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		pageEditarCuenta.BtnRegistrar(cp);
		
	
		
		if(driver.findElement(By.id("myModalLabel")).getText().contains("Registrar Certificado HSM") &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[1]/div/label")).getText().contains("Número de Solicitud") &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[1]/div/div/input")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[2]/div/label")).getText().contains("Pin de Solicitud") &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[2]/div/div/input")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[3]/div/label")).getText().contains("Contraseña") &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[3]/div/div/input")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[4]/div/label")).getText().contains("Repita Contraseña") &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[4]/div/div/input")).isDisplayed() &&
				
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[1]")).getText().contains("Cancelar") &&
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[2]")).getText().contains("Registrar")){
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
	public void Script_0080() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0080";
		// Editar Cuenta - clave -Imagen Ladrillo-ok
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		String archivo = "imagen.jpg";
		pageEditarCuenta.BtnCargarImagenLadrillo(cp, archivo);
		Thread.sleep(2000);
		String mensajeExito = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/div[1]")).getText();
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/div[1]")).getText());
		Thread.sleep(2000);
		pageEditarCuenta.cerrarPopUpEditarCuenta(cp);
		
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[8]/th[1]/div/button/div/span")).getText().contains(archivo) &&
				mensajeExito.contains("Imagen cargada correctamente")){
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
	public void Script_0081() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0081";
		// Editar Cuenta - clave -Imagen Ladrillo-nok
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
		pageDec5.OpcionEditarCuenta(cp);
		
		PageEditarCuenta pageEditarCuenta = new PageEditarCuenta(driver);
		String archivo = "PruebaQA.txt";
		pageEditarCuenta.BtnCargarImagenLadrillo(cp, archivo);
		Thread.sleep(2000);
		String mensajeError = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[8]/th[1]/div/div[2]/span/span")).getText();
		
		
		if(mensajeError.contains("El tipo de archivo que intentas subir no está permitido.")){
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
