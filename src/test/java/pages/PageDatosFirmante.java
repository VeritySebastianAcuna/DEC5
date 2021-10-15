package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageDatosFirmante {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageDatosFirmante(WebDriver driver) {
		this.driver=driver;
	}
	
	public void btnConfigurarFirmantes(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[6]/div[1]/div[2]/div/button")).click();
		String texto="Click Botón Configurar Firmantes"; //*[@id="formUpload"]/div[1]/div[2]/div[6]/div[1]/div[2]/div/button
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void btnConfigurarFirmantesArchivo(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[6]/div[2]/div[2]/div/button")).click();
		String texto="Click Botón Configurar Firmantes";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void buscarPersonasAdmin(String personasAdmin, String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"ms-email_0\"]/div[1]/input")).click();
				driver.findElement(By.xpath("//*[@id=\"ms-email_0\"]/div[1]/input")).sendKeys(personasAdmin);
				Thread.sleep(2000);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Búsqueda Personas en Admin");
				capturaPantalla.takeScreenShotTest(driver, "Personas_Admin", caso);
				i=1;
			} catch(Exception e) {
				j++;
				if(j==3) {
					System.out.println("No se puede agregar personas Admin");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void buscarPersonasAsinarFirmante(String personasAdmin, String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"ms-email_1\"]/div[1]/input")).click();
				driver.findElement(By.xpath("//*[@id=\"ms-email_1\"]/div[1]/input")).sendKeys(personasAdmin);
				Thread.sleep(2000);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Búsqueda Personas en Admin");
				capturaPantalla.takeScreenShotTest(driver, "Personas_Admin", caso);
				i=1;
			} catch(Exception e) {
				j++;
				if(j==3) {
					System.out.println("No se puede agregar personas Admin");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void seleccionFirmantes(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"-1780271057-selectable\"]")).click();
		String texto="Click Firmante";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void seleccionFirmantesAsignar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"-1780271057-selectable\"]/span")).click();
		String texto="Click Firmante";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void btnAsignar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("cerrarButton_")).click();
		String texto="Click Botón Asignar";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void btnAgregar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[5]/div/div[7]/button")).click();
		String texto="Click Botón Agregar";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	public void btnAgregar3(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[3]/div[5]/div/div[7]/button")).click();
		String texto="Click Botón Agregar";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	
	public void seleccionTipoFirmante (String caso, String tipoFirmante) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Select rol = new Select (driver.findElement(By.cssSelector("select[name='institucion_2']")));
				rol.selectByVisibleText(tipoFirmante); 
				String texto ="Seleccion tipo firmante";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar tipo firmante");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}

	
	public void datosRutFirmante0 (String caso, String rutFirmante, String ordenFirma) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("rut_institution_0")).sendKeys(rutFirmante);
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_0")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos de firmante");
		capturaPantalla.takeScreenShotTest(driver, "Datos_Firmante_DEC",caso);
	}
		
    public void datosRutFirmante1 (String caso, String rutFirmante, String ordenFirma) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("rut_institution_1")).sendKeys(rutFirmante);
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_1")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos de firmante");
		capturaPantalla.takeScreenShotTest(driver, "Datos_Firmante_DEC",caso);
	}
	
	
	public void datosFirmante (String caso, String rutFirmante, String ordenFirma) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("rut_institution_2")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_2")).sendKeys(rutFirmante);
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_2")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("orden_2")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("orden_2")).sendKeys(ordenFirma);
		Thread.sleep(2000);
		crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos de firmante");
		capturaPantalla.takeScreenShotTest(driver, "Datos_Firmante_DEC",caso);
	}
	
	public void datosFirmantePdf (String caso, String rutFirmante) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("rut_institution_1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_1")).sendKeys(rutFirmante);
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_1")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos de firmante Pdf");
		capturaPantalla.takeScreenShotTest(driver, "Datos_Firmante_Pdf",caso);
	}
	
	
public void datosFirmanteArchivo (String caso, String rutFirmante) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("rut_institution_0")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_0")).sendKeys(rutFirmante);
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_0")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		crearDocEvidencia.modificarArchivoEvidencia(caso, "Datos de firmante");
		capturaPantalla.takeScreenShotTest(driver, "Datos_Firmante",caso);
	}
	
	
	public void seleccionTipoFirma (String caso, String tipoFirma) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Select rol = new Select (driver.findElement(By.cssSelector("select[name='tipo_firma_2']")));
				rol.selectByVisibleText(tipoFirma); 
				String texto ="Seleccion tipo firma";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar tipo firma");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void seleccionTipoNotificacion (String caso, String notificacion) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Select rol = new Select (driver.findElement(By.cssSelector("select[name='notificar_2']")));
				rol.selectByVisibleText(notificacion); 
				String texto ="Seleccion tipo firma";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar tipo firma");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
		
	
}
