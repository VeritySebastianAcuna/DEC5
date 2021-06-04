package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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
		
		public void OpcionTiposdeDocumentos(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/ul/li[3]/a")).click();
					String texto ="Click Tipos de Documentos";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en Tipo de Documentos");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void seleccionEmpresaBusqueda (String empresa, String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
			int i=0;
			int j=0;
			do {
				try {
					Select tipodeFirmante = new Select (driver.findElement(By.id("origin")));
					switch (empresa){
					case "ACEPTA":
						tipodeFirmante.selectByValue("1");
						break;
					case "Otras Instituciones":
						tipodeFirmante.selectByValue("2");
						break;
					}
					Thread.sleep(2000);
					log.modificarArchivoLog(caso, "empresa: "+empresa);
					crearDocEvidencia.modificarArchivoEvidencia(caso, "Seleccion empresa Busqueda");
					capturaPantalla.takeScreenShotTest(driver, "Seleccion empresa búsqueda", caso);
					i=1;
				} catch (Exception e){
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar empresa en Selección Búsqueda");
						i=1;
					}
				}
			}while(i==0);
		}
		
		public void btnIconoBuscar(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"searchDocsTypes\"]/div[2]/div/div/div/button")).click();
					String texto ="Click ícono buscar";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en ícono buscar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void inputBuscar(String caso, String buscarEmpresa) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"searchDocsTypes\"]/div[2]/div/div/input")).click();
					driver.findElement(By.xpath("//*[@id=\\\"searchDocsTypes\\\"]/div[2]/div/div/input")).sendKeys(buscarEmpresa);
					String texto ="Ingreso Empresa en campo buscar";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar empresa en campo buscar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
	}

