package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageMisDocumentos {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageMisDocumentos(WebDriver driver) {
		this.driver=driver;
	}
	
	public void MisDocumentos(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/a")).click();
		String texto="Click Mis Documentos";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
public void etiquetaDocumento (String caso, String etiqueta) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("searchTerm")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("searchTerm")).sendKeys(etiqueta);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/div/button")).click();;
		Thread.sleep(10000);
		
		crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos Etiqueta Ingresada");
		capturaPantalla.takeScreenShotTest(driver, "Etiqueta",caso);
	}

public String ResultadoBusqueda(String caso) throws IOException, InvalidFormatException, InterruptedException {
	String resultado = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]")).getText();
	String texto ="Resultado Busqueda de etiquetas";
	log.modificarArchivoLog(caso,texto);
	crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
	texto=texto.replace(" ","_");
	capturaPantalla.takeScreenShotTest(driver,texto, caso);
	return(resultado);
}


}
