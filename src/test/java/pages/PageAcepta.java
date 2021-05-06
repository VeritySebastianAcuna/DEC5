package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

	public class PageAcepta {
		private WebDriver driver;
		Log log = new Log();
		CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
		CapturaPantalla capturaPantalla = new CapturaPantalla();
		public PageAcepta(WebDriver driver) {
			this.driver=driver;
		}

				
		public void empresaAcepta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/ul/li[2]/a")).click();
					String texto="Click empresa ACEPTA";
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
						System.out.println("No fue posible realizar Busqueda");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
						
		public void ClickRuedaConfiguracion(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/button")).click();
					String texto="Click a Configuraciones";
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
						System.out.println("No fue posible realizar Busqueda");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void OpcionInstituciones(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/ul/li[2]/a")).click();
					String texto ="Click a Instituciones";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
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

