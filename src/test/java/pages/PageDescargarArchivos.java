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
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/button")).click();
				Thread.sleep(2000);
				String texto ="click Boton Descarga plantilla Excel";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Descargar Plantilla Excel");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	
	public void TrazaBtnDescargaPdf (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"bts2\"]/button[1]")).click();
				Thread.sleep(1000);         
				String texto ="click Boton Descarga PDF";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Descarga PDF");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
}


