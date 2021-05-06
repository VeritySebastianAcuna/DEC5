package pages;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageDescargarArchivos {

	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageDescargarArchivos(WebDriver driver) {
		this.driver=driver;
	}
	
		
	public void downloadFile(String caso) throws InterruptedException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/button")).click();
		Thread.sleep(2000);
		
	}
	
}


