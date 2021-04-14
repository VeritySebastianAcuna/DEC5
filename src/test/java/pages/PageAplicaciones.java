package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
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
	
	public void IconoBusqueda(String caso) {
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
	}
}
