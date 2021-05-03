package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageAcepta;
import pages.PageAvanzarPagina;
import pages.PageCrearDocumento;
import pages.PageDatosDocumento;
import pages.PageDatosFirmante;
import pages.PageDec5;
import pages.PageDescargarArchivos;
import pages.PageLoginAdm;
import pages.PageMisDocumentos;
import pages.PageSubirArchivos;

public class Test_MisDocumentos {

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
	public void Script_1062() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1062";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageMisDocumentos pageMisDocumentos = new PageMisDocumentos(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageMisDocumentos.MisDocumentos(cp);
		pageMisDocumentos.etiquetaDocumento(cp, datos[15]);
		
		String buscar=datos[15];
		String resultado = pageMisDocumentos.ResultadoBusqueda(cp);
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
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

}


