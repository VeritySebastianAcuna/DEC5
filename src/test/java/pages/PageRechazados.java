package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageRechazados {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageRechazados(WebDriver driver) {
		this.driver=driver;
	}
	
	public void IngresoEtiqueta (String caso, String etiqueta) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.name("searchTerm")).sendKeys(etiqueta);
				String texto ="Ingreso Etiqueta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ingresar Etiqueta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BuscarEtiqueta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/div/button")).click();
				String texto ="Buscar Etiqueta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Buscar Etiqueta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}

}
