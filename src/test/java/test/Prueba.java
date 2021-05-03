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
import pages.PageDec5;
import pages.PageLoginAdm;

public class Prueba {
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
		
		//String filepath = "C:\\Users\\Laura Andrade\\eclipse-workspace\\Dec5\\Downloads\\PlantillaExcel_012020400216984_28Apr21_03_04_23.xls";
		//String buscarTexto =
			
	}
	
	@Test
	public void Script_1181() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1181";
		System.out.println(cp);
		//FUNCIONOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
		
	}
	
	@AfterMethod
	public void FinEjecucion() {
		//driver.close();
	}
}
