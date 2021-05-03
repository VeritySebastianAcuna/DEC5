package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

	public class PageDatosDocumento {

		private WebDriver driver;
		Log log = new Log();
		CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
		CapturaPantalla capturaPantalla = new CapturaPantalla();
		public PageDatosDocumento(WebDriver driver) {
			this.driver=driver;
		}
		
		public void datosDocumento(String nombre , String caso) throws IOException, InvalidFormatException, InterruptedException { 
			
					driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div[1]/input[1]")).click();
					driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div[1]/input[1]")).sendKeys(nombre);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div[2]/input[1]")).click();
					driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div[2]/input[1]")).sendKeys(Keys.TAB);
					Thread.sleep(2000);
					crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos del Documento");
					capturaPantalla.takeScreenShotTest(driver, "Datos_Documento", caso);
					System.out.println("No se puede agregar datos del Documento");
					
		}
		
		public void btnRevisaryContinuar(String caso) throws InterruptedException, IOException, InvalidFormatException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("btnRevisar")).click();
			String texto="Click Botón Revisar y Continuar";
			log.modificarArchivoLog(caso, texto);
			crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
			texto=texto.replace(" ", "_");
			capturaPantalla.takeScreenShotTest(driver, texto, caso);
			Thread.sleep(2000);
		}
		
		public void btnRevisaryContinuarPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("btnRevisarPlantillaPdf")).click();
			String texto="Click Botón Revisar y Continuar documento PDF";
			log.modificarArchivoLog(caso, texto);
			crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
			texto=texto.replace(" ", "_");
			capturaPantalla.takeScreenShotTest(driver, texto, caso);
			Thread.sleep(5000);
		}
		
		public void btnContinuar(String caso) throws InterruptedException, IOException, InvalidFormatException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id=\"divRevisar\"]/div/div/div[3]/div/button")).click();
			String texto="Click Botón Continuar";
			log.modificarArchivoLog(caso, texto);
			crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
			texto=texto.replace(" ", "_");
			capturaPantalla.takeScreenShotTest(driver, texto, caso);
			Thread.sleep(5000);
		}
		

}