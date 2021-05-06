package test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import pages.PageAvanzarPagina;
import pages.PageCrearDocumento;
import pages.PageDec5;
import pages.PageDescargarArchivos;
import pages.PageLoginAdm;

public class Test_DescargaPlantilla {
	private WebDriver driver;//Declaro el objeto webdriver
	private String downloadFilePath = "C:\\Users\\Laura Andrade\\eclipse-workspace\\Dec5\\Downloads";
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
		driver.navigate().to("https://5cap.dec.cl/portal");// Aquí se ingresa la URL para hacer las pruebas.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
	}
	
	@Test
	public void Script_1181() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1181";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageDescargarArchivos pageDescargarArchivos = new PageDescargarArchivos(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		
		pageDescargarArchivos.downloadFile(cp);
		File folder = new File(downloadFilePath);
		File[] lisofFiles = folder.listFiles();//Guardando todos los archivos en el arreglo
		assertTrue(lisofFiles.length>0, "Archivo descarga NOK");//Valido si efectivamente hay o no archivos
		System.out.println(lisofFiles[0]);
		leerExcel.LeerCeldas(lisofFiles[0].toString());
		
				
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1195() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1195";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageDescargarArchivos pageDescargarArchivos = new PageDescargarArchivos(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		
		pageDescargarArchivos.downloadFile(cp);
		File folder = new File(downloadFilePath);
		File[] lisofFiles = folder.listFiles();//Guardando todos los archivos en el arreglo
		assertTrue(lisofFiles.length>0, "Archivo descarga NOK");//Valido si efectivamente hay o no archivos
		System.out.println(lisofFiles[0]);
		leerExcel.LeerCeldas(lisofFiles[0].toString());
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1216() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1216";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageDescargarArchivos pageDescargarArchivos = new PageDescargarArchivos(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		
		pageDescargarArchivos.downloadFile(cp);
		File folder = new File(downloadFilePath);
		File[] lisofFiles = folder.listFiles();//Guardando todos los archivos en el arreglo
		assertTrue(lisofFiles.length>0, "Archivo descarga NOK");//Valido si efectivamente hay o no archivos
		System.out.println(lisofFiles[0]);
		leerExcel.LeerCeldas(lisofFiles[0].toString());
		
		System.out.println("FLUJO OK");
	}
		
	@AfterMethod
	public void FinEjecucion() {
		//driver.close();
	}
}
