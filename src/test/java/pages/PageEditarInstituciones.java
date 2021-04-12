package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;
import evidence.CrearLogyDocumento;

public class PageEditarInstituciones {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageEditarInstituciones(WebDriver driver) {
		this.driver=driver;
	}
	CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
	
	public String ObtenerRut() {
		String rut = driver.findElement(By.name("rut")).getAttribute("value");
		return rut;
	}
	
	public void EditarRut(String caso, String rut) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name("rut")).clear();
		driver.findElement(By.name("rut")).sendKeys(rut);
		String texto ="Editar Rut";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void ValidarMensajeRut(String caso) throws InvalidFormatException, IOException, InterruptedException {
		String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div[1]/div/div/div[1]/div[3]/div[3]/div/span")).getText();
		System.out.println(mensaje);
		String texto="Mensaje OK";
		switch(mensaje) {
			case "RUT no válido":
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
			case "El campo RUT debe ser de al menos 9 caracteres de longitud.":
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
			case "El campo RUT no puede superar los 10 caracteres de longitud.":
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
			default:
				texto ="Mensaje NOK";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
		}
	}
	
	public void BtnGuardar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name("submit")).click();
		String texto ="Click Boton Guardar";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void EditarDescripcion(String caso) {
		String texto = driver.findElement(By.name("description")).getText();
		texto = texto+" editado";
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(texto);
	}
	
	public void MensajeEditado(String caso) throws InvalidFormatException, IOException, InterruptedException {
		int i=0;
		int j=0;
		String mensaje="";
		do {
			try {
				mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
				System.out.println(mensaje);
				i=1;
				if(mensaje.contains("se actualizó correctamente")) {
					crearLogyDocumento.CasoOk(caso);
				}
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible obtener el mensaje");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void EditarMail(String caso) {
		String mail = driver.findElement(By.name("email")).getAttribute("value");
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(mail);
	}
	
	public void EditarMailNotificaciones(String caso) {
		String mail = driver.findElement(By.name("sender")).getAttribute("value");
		driver.findElement(By.name("sender")).clear();
		driver.findElement(By.name("sender")).sendKeys(mail);
	}
	
	public void FlagAceptaNo(String caso) {
		Select flag = new Select (driver.findElement(By.name("flagacepta")));
		flag.selectByValue("0");
	}
	
	public void FlagAceptaSi(String caso) {
		Select flag = new Select (driver.findElement(By.name("flagacepta")));
		flag.selectByValue("1");
	}
	
	public void EditarRazonSocial(String caso) {
		String mail = driver.findElement(By.name("razon")).getAttribute("value");
		driver.findElement(By.name("razon")).clear();
		driver.findElement(By.name("razon")).sendKeys(mail);
	}
	
	public void EditarRubro(String caso) {
		String mail = driver.findElement(By.name("rubro")).getAttribute("value");
		driver.findElement(By.name("rubro")).clear();
		driver.findElement(By.name("rubro")).sendKeys(mail);
	}
	
	public void EditarPais(String caso) {
		Select pais = new Select (driver.findElement(By.name("country")));
		pais.selectByValue("CHILE");
	}
	
	public void EditarComuna(String caso) {
		String mail = driver.findElement(By.name("comuna")).getAttribute("value");
		driver.findElement(By.name("comuna")).clear();
		driver.findElement(By.name("comuna")).sendKeys(mail);
	}
	
	public void EditarDireccion(String caso) {
		String mail = driver.findElement(By.name("address")).getAttribute("value");
		driver.findElement(By.name("address")).clear();
		driver.findElement(By.name("address")).sendKeys(mail);
	}
	
	public void EditarColor(String caso) {
		String mail = driver.findElement(By.name("scheme_color")).getAttribute("value");
		driver.findElement(By.name("scheme_color")).clear();
		driver.findElement(By.name("scheme_color")).sendKeys(mail);
	}
}
