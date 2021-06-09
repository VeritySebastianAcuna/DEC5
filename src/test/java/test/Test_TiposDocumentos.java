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
	public void Script_245() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_245";
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
		//pageAcepta.btnIconoBuscar(cp);
	
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
	public void Script_246() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_245";
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
		//pageAcepta.btnIconoBuscar(cp);
	
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
	public void Script_247() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_247";
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
		pageAcepta.inputBuscar(cp, "AGSFAD"); //PENDIENTE NO INGRESA EL DATO
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
	public void Script_248() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_247";
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
		pageAcepta.inputBuscar(cp, "002020560195310"); //PENDIENTE NO INGRESA EL DATO
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
	public void Script_249() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_247";
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
		pageAcepta.inputBuscar(cp, "002020560195310"); //PENDIENTE NO INGRESA EL DATO
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
	public void Script_251() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_247";
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
		pageAcepta.inputBuscar(cp, "002020560195310"); //PENDIENTE NO INGRESA EL DATO
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
	
	@AfterMethod
	public void FinEjecucion() {
		driver.close();
	}

}
