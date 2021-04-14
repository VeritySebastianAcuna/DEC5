package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.CapturaPantalla;
import common.Configuration;
import common.CrearDocEvidencia;
import common.Log;

public class PageAplicaciones {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageAplicaciones(WebDriver driver) {
		this.driver=driver;
	}
	
	public void IconoBusqueda(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"searchApps\"]/div/div/button")).click();
				String texto="Click a Icono de Busqueda";
				log.modificarArchivoLog(caso, texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
				texto=texto.replace(" ", "_");
				capturaPantalla.takeScreenShotTest(driver, texto, caso);
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click a icono de búsqueda");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void NuevaAplicaciones(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/div[2]/a")).click();
				String texto="Click a Nueva Aplicación";
				log.modificarArchivoLog(caso, texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
				texto=texto.replace(" ", "_");
				capturaPantalla.takeScreenShotTest(driver, texto, caso);
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click a Nueva Aplicacione");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void CargarArchivo(String caso,String archivo) throws IOException, InvalidFormatException, InterruptedException {
		WebElement fileInput = driver.findElement(By.id("uploadFile"));
		String rutaArchivo=Configuration.ROOT_DIR+"Archivos/"+archivo;
		fileInput.sendKeys(rutaArchivo);
		String texto="Carga de Archivo";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void MensajeErrorCargaArchivo (String caso) throws IOException, InvalidFormatException, InterruptedException {
		String mensaje = driver.findElement(By.xpath("//*[@id=\"error-upload\"]/div/span")).getText();
		System.out.println(mensaje);
		if(mensaje.equals("El tipo de archivo que intentas subir no está permitido.")) {
			String texto="Mensaje de Error Correcto";
			log.modificarArchivoLog(caso, texto);
			crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
			texto=texto.replace(" ", "_");
			capturaPantalla.takeScreenShotTest(driver, texto, caso);
		}
		else {
			String texto="Mensaje de Error Incorrecto";
			log.modificarArchivoLog(caso, texto);
			crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
			texto=texto.replace(" ", "_");
			capturaPantalla.takeScreenShotTest(driver, texto, caso);
		}
	}
}
