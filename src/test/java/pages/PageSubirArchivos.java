package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.CapturaPantalla;
import common.Configuration;
import common.CrearDocEvidencia;
import common.Log;

public class PageSubirArchivos {

	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageSubirArchivos(WebDriver driver) {
		this.driver=driver;
	}
	
	public void CargarArchivoPorLote(String caso,String archivo) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]/input"));
		String rutaArchivo=Configuration.ROOT_DIR+"Archivos/"+archivo; 
		fileInput.sendKeys(rutaArchivo);
		Thread.sleep(2000);
		}
	public void SubirArchivo(String caso,String archivo) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement fileInput = driver.findElement(By.id("uploadFile"));
		String rutaArchivo=Configuration.ROOT_DIR+"Archivos/"+archivo; 
		fileInput.sendKeys(rutaArchivo);
		Thread.sleep(2000);
		}

}
