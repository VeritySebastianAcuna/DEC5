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
import pages.PageAcepta;
import pages.PageDec5;
import pages.PageInstituciones;
import pages.PageLoginAdm;

public class Tests_AdmInstituciones {
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
	public void Script_0078() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0078";
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
		
		String buscar=datos[3];
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.BusquedaInstituciones(cp, buscar);
		String resultado = pageInstituciones.ResultadoBusqueda(cp);
		resultado=resultado.toLowerCase();
		if(resultado.contains(buscar)) {
			System.out.println("OK");
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			System.out.println("NOK");
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0079() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0079";
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
		
		String buscar=datos[3];
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		pageInstituciones.BusquedaInstituciones(cp, buscar);
		pageInstituciones.CheckInstitucionesDeshabilitadas(cp);
		String resultado = pageInstituciones.ResultadoBusqueda(cp);
		resultado=resultado.toLowerCase();
		if(resultado.contains(buscar)) {
			System.out.println("OK");
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			System.out.println("NOK");
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0081() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0081";
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
		pageInstituciones.CrearInstituciones(cp);
		pageInstituciones.BtnCrearInstitucion(cp);
		
		String texto= driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[3]/div[1]/div/span")).getText();
		
		if(texto.equals("El campo Institución es obligatorio.")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Intitución OK");
			System.out.println(texto);
			texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[3]/div[3]/div/span")).getText();
			if(texto.equals("El campo RUT es obligatorio.")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Rut OK");
				System.out.println(texto);
				texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[4]/div[2]/div/span")).getText();
				if(texto.equals("El campo Email es obligatorio.")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Email OK");
					System.out.println(texto);
					texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[8]/div[1]/div/span")).getText();
					if(texto.equals("El campo RUT es obligatorio.")) {
						crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Rut OK");
						System.out.println(texto);
						texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[8]/div[2]/div/span")).getText();
						if(texto.equals("El campo Nombre es obligatorio.")) {
							crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Nombre OK");
							System.out.println(texto);
							texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[9]/div/div/span")).getText();
							if(texto.equals("El campo Email es obligatorio.")) {
								crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Email OK");
								System.out.println(texto);
								crearLogyDocumento.CasoOk(cp);
							}
						}
					}
				}
			}
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("NOK");
		}
		System.out.println("FLUJO OK");
	}
	
//	PAGINA PERMITE EL INGRESO DE CARACTERES ESPECIALES, NO SE ESTÁ VALIDANDO EL INGRESO DE LOS CARACTERES ESPECIALES
	@Test
	public void Script_0082() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0082";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0083() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0083";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0084() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0084";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		rut=rut+"-K";
		System.out.println(rut);
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			String texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[3]/div[3]/div/span")).getText();
			System.out.println(texto);
			if(texto.equals("RUT no válido")) {
				crearLogyDocumento.CasoOk(cp);
			}
			else {
				System.out.println("Otro mensaje");
				crearLogyDocumento.CasoNok(cp);
			}
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0085() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0085";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		rut=rut+"K111";
		System.out.println(rut);
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			String texto=driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[3]/div[3]/div/span")).getText();
			System.out.println(texto);
			if(texto.equals("RUT no válido")) {
				crearLogyDocumento.CasoOk(cp);
			}
			else {
				System.out.println("Otro mensaje");
				crearLogyDocumento.CasoNok(cp);
			}
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0086() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0086";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0087() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0087";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0088() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0088";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0089() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0089";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		pageInstituciones.FlagAcepta(cp, "No");
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
	}
	@Test
	public void Script_0090() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0090";
		System.out.println(cp);
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
				
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		pageInstituciones.FlagAcepta(cp, "Si");
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoOk(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			String mensaje = driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[1]/div/div/div/div[2]/div/div/p")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("Nombre de la Institución muy corto ó existente")){
			crearLogyDocumento.CasoNok(cp);
			}
		}
		System.out.println("FLUJO OK");

	}
	
	@Test
	public void Script_0091() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0091";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		//Enviar nombre Razón Social con Caracteres especiales
		driver.findElement(By.name("razon")).sendKeys("RAZON, SOCI@L #%/");
				
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		
	}
	
	@Test
	public void Script_0092() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0092";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		//Enviar Rubro con Caracteres especiales
		driver.findElement(By.name("rubro")).sendKeys("RUBRO, #%/");
				
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
				
	}
	
	@Test
	public void Script_0093() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0093";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		//Enviar Rubro con más de 100 Caracteres
		driver.findElement(By.name("rubro")).sendKeys("ASDFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDAFSADAFADAFDAFADAFDSFASDFADFSDFADFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDFASDAFSADAFADAFDAFADAFDSFASDFADFSDFADF");
				
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
				
	}
	
	@Test
	public void Script_0094() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0094";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		//Enviar Comuna no existe
		driver.findElement(By.name("comuna")).sendKeys("AFADAFDSF");
				
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
				
	}
	
	@Test
	public void Script_0095() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0095";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		//Enviar dirección con caracteres especiales
		driver.findElement(By.name("address")).sendKeys("DIRECCION, F%()7777");
				
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
				
	}
	
	@Test
	public void Script_0099() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0099";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
			
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
				
	}
	
	@Test
	public void Script_0100() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0100";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageInstituciones pageInstituciones = new PageInstituciones(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionInstituciones(cp);
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
			
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
				
	}
	
	@Test
	public void Script_0101() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0101";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		pageInstituciones.CodigoColor(cp, "asd");
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0104() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0104";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		pageInstituciones.NoRegistro(cp);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0105() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0105";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0106() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0106";
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
		pageInstituciones.CrearInstituciones(cp);
		
		CrearRut crearRut = new CrearRut();
		String rut= crearRut.RutEmpresa();
		pageInstituciones.DatosNuevaInstitucion(cp, datos[4], datos[5], rut, datos[6], datos[7]);
		
		rut=crearRut.RutPersona();
		pageInstituciones.DatosAdminDec(cp, rut, datos[8], datos[9]);
		pageInstituciones.NoHuella(cp);
		
		pageInstituciones.BtnCrearInstitucion(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se creó correctamente")) {
				crearLogyDocumento.CasoNok(cp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
