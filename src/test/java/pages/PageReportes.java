package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageReportes {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageReportes(WebDriver driver) {
		this.driver=driver;
	}
	
	public void BuscarReporte (String caso) throws IOException, InvalidFormatException, InterruptedException {
		//*[@id="tabla_grilla_reportesNEW"]
		String buscar = driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW\"]/descendant::td[3]")).getText();
		driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_filter\"]/label/input")).sendKeys(buscar);
		Thread.sleep(3000);
		String texto = "Busqueda OK";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		driver.close();
	}
}
