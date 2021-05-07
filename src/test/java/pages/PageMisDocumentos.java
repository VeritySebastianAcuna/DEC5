package pages;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
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
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/a")).click();
				String texto="Click Mis Documentos";
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
	

	
	public void BusquedaEtiquetas (String caso, String busqueda) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/input")).sendKeys(busqueda);
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/div/button/span")).click();
				String texto ="Busqueda realizada";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
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
	
	public String ResultadoBusqueda(String caso) throws IOException, InvalidFormatException, InterruptedException {
		
		String resultado = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]")).getText();
		String texto ="Resultado Busqueda de etiquetas";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		return(resultado);
			
	}

	
	public void clickCarpeta(String caso) throws IOException, InvalidFormatException, InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[1]/div/button")).click();
				String texto ="Click Selección Carpetas";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
		
	}
	
	public void clickNuevaCarpeta(String caso) throws IOException, InvalidFormatException, InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/h1/div/a[2]")).click();
				String texto ="Crear Nueva Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
		
	}
	
	public void AgregarCarpetaYaExistente (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				String carpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div/button")).getText();
				driver.findElement(By.name("folder")).sendKeys(carpeta);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button[2]")).click();
				Thread.sleep(1000);
				String texto ="Carpeta Existente";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void FiltrarCarpetaExistente(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div[1]/button")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[2]/div[2]/button")).click();
				Thread.sleep(1000);
				String texto ="Filtrar Carpeta Existente";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Filtrar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void EliminarCarpetaExistente(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div[4]/button")).click();//Selecciono la carpeta a eliminar en este caso es la 4ta carpeta
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/h1/div/a[1]")).click();//link eliminar carpeta
				Thread.sleep(1000);
				String texto ="Eliminar Carpeta Existente";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Eliminar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
		
	public void btnEliminarCarpeta(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("eliminarFolder")).click();
				String texto ="Eliminar Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Eliminar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BtnCancelar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				String carpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div/button")).getText();//envío el nombre de la 1ra carpeta
				driver.findElement(By.name("folder")).sendKeys(carpeta);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button[1]")).click();
				Thread.sleep(1000);
				String texto ="Boton Cancelar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Boton Cancelar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BusquedaTipoDocumento(String caso, String busqueda) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("tipoDctoSel")).sendKeys(busqueda);
				String texto ="Filtrar Tipo Documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(5000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible realizar Filtro");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void clickMostrarOcultos(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("tipoDctoSel")).click();
				String texto ="Mostrar ocultos";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(15000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible realizar la busqueda");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void clickNoMostrarOcultos(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[3]/div/button")).click();
				String texto ="No Mostrar ocultos";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(15000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible realizar la busqueda");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void clickDocumento(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/tbody/tr[1]/td[2]")).click();
				String texto ="Selección documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(15000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void clickAgregarCarpeta(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/h3/span/a")).click();
				String texto ="Agregar a carpeta"; //*[@id="details-doc"]/div/div[2]/div/h3/span/a
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Agregar documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void moverCarpeta(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("checkMover_582")).click();
				driver.findElement(By.id("move-folder")).click();
				String texto ="Mover Documento a Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Mover documento a carpeta seleccionada");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BarraHerramientas (String caso, String opcion) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				int hijos = driver.findElements(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button")).size();//Almacena hijos
				int h=4;//recorre hijos 
				int g=0;//Variable recorre hijos y msj de error
				do {
					System.out.println(driver.findElement(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button["+h+"]")).getText());//Imprime hijo h
					if(driver.findElement(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button["+h+"]")).getText().equals(opcion)) {
						driver.findElement(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button["+h+"]")).click();
					}
					else {
						g++;
						if(g>hijos) {
								System.out.println("No se encuentra opción");
						}
					}
					h++;
		        }while(h<hijos);
				Thread.sleep(1000);
				String texto ="Seleccionar Opcion Barra Herramientas";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible Seleccionar Opcion");
						i=1;
					}
				}
		}while(i==0);
		Thread.sleep(2000);
	}
}
