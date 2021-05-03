package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.CrearRut;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageAcepta;
import pages.PageCrearDocumento;
import pages.PageDatosDocumento;
import pages.PageDec5;
import pages.PageDescargarArchivos;
import pages.PageLeerExcel;
import pages.PageLoginAdm;

public class Prueba {
	private WebDriver driver;//Declaro el objeto webdriver
	private String downloadFilePath = "C:\\Users\\Laura Andrade\\eclipse-workspace\\Dec5\\Downloads";
	private PageLeerExcel readFile;
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
		driver.navigate().to("https://5cap.dec.cl/portal");// Aqu� se ingresa la URL para hacer las pruebas.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//String filepath = "C:\\Users\\Laura Andrade\\eclipse-workspace\\Dec5\\Downloads\\PlantillaExcel_012020400216984_28Apr21_03_04_23.xls";
		//String buscarTexto =
			
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
	public void Script_1182() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1182";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
			
		//driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]")).click();
		File file = new File("C:\\Users\\Laura Andrade\\eclipse-workspace\\Dec5\\Upload_File\\PlantillaExcel_002020560195310_22Apr21_02_53_36.xls");//Ruta donde esta el archivo a subir
		String path = file.getAbsolutePath();
		
		driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]")).sendKeys(path);
		String mensaje = driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[1]")).getText();
		assertEquals("Se cargaron los datos de 1 Documentos con �xito", mensaje);
		
		
		//driver.get("");
		
		System.out.println("FLUJO OK");
	}
	
	@AfterMethod
	public void FinEjecucion() {
		//driver.close();
	}
}
