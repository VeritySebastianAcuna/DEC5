package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PagePendientes {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PagePendientes(WebDriver driver) {
		this.driver=driver;
	}
	
	public void BusquedaEtiquetas (String caso, String busqueda) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/input")).sendKeys(busqueda);
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/div/button/span")).click();
				Thread.sleep(15000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible realizar Busqueda");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
}
