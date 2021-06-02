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
		String texto="Click Botón Configurar Firmantes";
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
	
	public void seleccionTipoFirmante (String tipoFirmante, String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		int i=0;
		int j=0;
		do {
			try {
				Select tipodeFirmante = new Select (driver.findElement(By.name("institucion_1")));
				switch (tipoFirmante){
				case "PERSONAL":
					tipodeFirmante.selectByValue("(institucion_personal)");
					break;
				case "GRUPO PERSONAS":
					tipodeFirmante.selectByValue("(grupo_personas)");
					break;
				case "ACEPTA":
					tipodeFirmante.selectByValue("ACEPTA");
					break;
				default:
					System.out.println("Tipo de Firmante Institución Valor inválido");
					break;
				}
				Thread.sleep(2000);
				log.modificarArchivoLog(caso, "Tipo Cargo: "+tipoFirmante);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Seleccion Tipo Firmante");
				capturaPantalla.takeScreenShotTest(driver, "Seleccion_Tipo_Firmante", caso);
				i=1;
			} catch (Exception e){
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar Tipo de Firmante Institución");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void datosFirmante (String caso, String rutFirmante, String ordenFirma) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		
		driver.findElement(By.name("rut_institution_1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_1")).sendKeys(rutFirmante);
		Thread.sleep(2000);
		driver.findElement(By.name("rut_institution_1")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("orden_1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("orden_1")).sendKeys(ordenFirma);
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
	
	public void seleccionTipoFirma (String tipoFirma, String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		int i=0;
		int j=0;
		do {
			try {
				Select tipodeFirma = new Select (driver.findElement(By.name("tipo_firma_1")));
				switch (tipoFirma){
				case "Firmar":
					tipodeFirma.selectByValue("0");
					break;
				case "Visar":
					tipodeFirma.selectByValue("2");
					break;
				case "Compartir":
					tipodeFirma.selectByValue("5");
					break;
				case "Firmar solo con Pin":
					tipodeFirma.selectByValue("10");
					break;
				case "Firmar solo con Huella":
					tipodeFirma.selectByValue("11");
					break;
				case "Firmar solo con HSM":
					tipodeFirma.selectByValue("12");
					break;
				case "Firmar solo con Firma Móvil Avanzada":
					tipodeFirma.selectByValue("20");
					break;
				case "Firmar solo con Token":
					tipodeFirma.selectByValue("13");
					break;
				case "Firmar solo con Clave Unica":
					tipodeFirma.selectByValue("21");
					break;
				case "Firmar solo con Cedula Identidad":
					tipodeFirma.selectByValue("24");
					break;
				case "Firmar solo con Clave Unica + Cedula Identidad":
					tipodeFirma.selectByValue("25");
					break;
				case "Facial":
					tipodeFirma.selectByValue("29");
					break;
				case "Firmar solo con Pin Notarial":
					tipodeFirma.selectByValue("30");
					break;
				case "Firma Cédula(Notarios)":
					tipodeFirma.selectByValue("15");
					break;
				case "Firma Bioholografa(Notarios)":
					tipodeFirma.selectByValue("16");
					break;
				case "Visar solo con Pin":
					tipodeFirma.selectByValue("32");
					break;
				case "Visar solo con Huella":
					tipodeFirma.selectByValue("42");
					break;
				case "Visar solo con Clave Unica":
					tipodeFirma.selectByValue("52");
					break;
				case "Visar con Firma":
					tipodeFirma.selectByValue("23");
					break;
				default:
					System.out.println("Tipo de Firma Valor inválido");
					break;
				}
				Thread.sleep(2000);
				log.modificarArchivoLog(caso, "Tipo Cargo: "+tipoFirma);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Seleccion Tipo Firma");
				capturaPantalla.takeScreenShotTest(driver, "Seleccion_Tipo_Firma", caso);
				i=1;
			} catch (Exception e){
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar Tipo de Firmante Institución");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void seleccionTipoNotificacion (String tipoNotificar, String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
		int i=0;
		int j=0;
		do {
			try {
				Select tipodeNotificar = new Select (driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[6]/div[3]/div[6]/select")));
				switch (tipoNotificar){
				case "Sin notificaciones":
					tipodeNotificar.selectByValue("0");
					break;
				case "Todas":
					tipodeNotificar.selectByValue("1");
					break;
				case "Finalizado":
					tipodeNotificar.selectByValue("2");
					break;
				case "Firmado":
					tipodeNotificar.selectByValue("3");
					break;
				case "Rechazo":
					tipodeNotificar.selectByValue("4");
					break;
				case "Pendiente de firma":
					tipodeNotificar.selectByValue("5");
					break;
				default:
					System.out.println("Tipo de Notificación Valor inválido");
					break;
				}
				Thread.sleep(2000);
				log.modificarArchivoLog(caso, "Tipo Cargo: "+tipoNotificar);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Seleccion Tipo Notificación");
				capturaPantalla.takeScreenShotTest(driver, "Seleccion_Tipo_Notificar", caso);
				i=1;
			} catch (Exception e){
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar Tipo de Notificación");
					i=1;
				}
			}
		}while(i==0);
	}
	
		
	
}
