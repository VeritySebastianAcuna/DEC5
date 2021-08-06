package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.Configuration;
import common.CrearDocEvidencia;
import common.Log;

public class PageUsuarios {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageUsuarios(WebDriver driver) {
		this.driver=driver;
	}
	
	public void CrearRol(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/button[1]")).click();
		String texto ="Click Crear Rol";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void NombreRol(String caso, String rol) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"dInput\"]/input")).sendKeys(rol);
		Thread.sleep(3000);
	}
	
	public void BtnContinuar(String caso) throws InterruptedException {
		driver.findElement(By.id("bContinuar")).click();
		Thread.sleep(3000);
	}
	
	public void BtnAceptar(String caso) throws InterruptedException {
		driver.findElement(By.id("bAceptar")).click();
		Thread.sleep(3000);
	}
	
	public void BtnCancelar(String caso) throws InterruptedException {
		driver.findElement(By.id("bCancelar")).click();
		Thread.sleep(3000);
	}
	
	public void CerrarPopUpNuevoRol(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[1]/div/button")).click();
		Thread.sleep(3000);
	}
	
	public void AsignarUsuariosRol(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/button[2]")).click();
		String texto ="Click Crear Rol";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}		
	
	public void SeleccionarRol (String caso) throws InterruptedException {
		Select rol = new Select (driver.findElement(By.xpath("//*[@id=\"role\"]")));
		int hijos = driver.findElements(By.xpath("//*[@id=\"role\"]/child::option")).size();
		String roles[] = new String[hijos];
		int h;
		for(h=1;h<=hijos;h++) {
			roles[h-1]=driver.findElement(By.xpath("//*[@id=\"role\"]/child::option["+h+"]")).getAttribute("value");
		}
		h=(int)(Math. random()*hijos+0);
		rol.selectByValue(roles[h]);
		Thread.sleep(2000);
	}
	
	public void IngresarRut(String caso, String rut) throws InterruptedException {
		driver.findElement(By.name("rut")).sendKeys(rut);
		Thread.sleep(2000);
	}
	
	public void IngresarMail(String caso, String mail) throws InterruptedException {
		driver.findElement(By.name("email")).sendKeys(mail);
		Thread.sleep(2000);
	}
	
	public void ingresarFechaDesdeVacia(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[3]/div/input")).clear();
		Thread.sleep(2000);
	}
	
	public void ingresarFechaHastaVacia(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[1]/div/div[4]/div/input")).clear();
		Thread.sleep(2000);
	}
	
	public void BtnAsignar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name("s_role")).click();
		String texto ="Click Boton Asignar";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(4000);
	}
	
	public void RescatarUsuarioAsociadoRol (String caso, String rut) throws IOException, InvalidFormatException, InterruptedException {
		int hijos=driver.findElements(By.xpath("//*[@id=\"tbl-users\"]/descendant::div")).size();
		System.out.println(hijos);
		System.out.println(rut);
		for(int i=1;i<=hijos;i++) {
			System.out.println(driver.findElement(By.xpath("//*[@id=\"tbl-users\"]/descendant::div["+i+"]")).getText());
			if(driver.findElement(By.xpath("//*[@id=\"tbl-users\"]/descendant::div["+i+"]")).getText().equals(rut)) {
				String texto ="Usuario Agregado con exito";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(3000);
			}
//			else {
//				String texto ="No se pudo encontrar usuario";
//				log.modificarArchivoLog(caso,texto);
//				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
//				texto=texto.replace(" ","_");
//				capturaPantalla.takeScreenShotTest(driver,texto, caso);
//				Thread.sleep(3000);
//			}
		}
	}
	
	public void CerrarPopupAsignarUsuariosRol(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[1]/div[1]/button")).click();
		String texto ="PopUp Cerrado";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void DeshabilitarUsuariosRol(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/button[3]")).click();
		String texto ="Click Crear Rol";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}	
	
	public void BtnInhabilitar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).click();
		String texto ="Click Boton Inhabilitar";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(4000);
	}
	
	public void CerrarPopupDeshabilitarUsuariosRol(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/div[1]/button")).click();
		String texto ="PopUp Cerrado";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void IngresarRutInhabilitar(String caso, String ruts) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.id("list_users")).sendKeys(ruts);
		String texto ="Se Ingresan Ruts";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void BtnDeshabilitacionMasiva(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/div[2]/div/div[2]/button")).click();
		String texto ="Click Boton Deshabilitacion Masiva";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void CargarArchivoDeshabilitacionMasiva(String caso,String archivo) throws IOException, InvalidFormatException, InterruptedException {
		WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[2]/div[2]/input"));
		String rutaArchivo=Configuration.ROOT_DIR+"Archivos/"+archivo;
		fileInput.sendKeys(rutaArchivo);
		String texto="Carga de Archivo";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
			
	public void BtnCuentasRegistradas(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div[2]/button[1]")).click();
		String texto ="Click Boton Cuentas Registradas";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void LinkVerReportes(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.partialLinkText("Ver Reportes")).click();
		String texto ="Click Link Ver Reportes";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public String CambiarPestanaReportes(String caso) throws InterruptedException, IOException, InvalidFormatException {
		// Almacena el ID de la ventana original
		String originalWindow = driver.getWindowHandle();
		// Espera a la nueva ventana o pestaña
		Thread.sleep(7000);
		for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		String texto = "Pestana Reportes";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		return originalWindow;
	}
	
	// Martes 03/08/2021
	
	public void IngresarRolMail(String caso, String mail) throws InterruptedException {
		driver.findElement(By.name("role_email")).sendKeys(mail);
		Thread.sleep(2000);
	}
	
	public void BtnGuardar(String caso) throws InterruptedException {
		driver.findElement(By.className("btn-save")).click();
		Thread.sleep(3000);
	}
	
	public void AsignacionMasiva(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.className("add-multiple")).click();
		String texto ="Click Boton Asignación Masiva";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(4000);
	}
	
	public void BtnCerrarDeshabilitar(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/div/button")).click();
		Thread.sleep(3000);
	}
	
	public void ReportesBuscar(String caso, String text) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"tabla_grilla_reportesNEW_filter\"]/label/input")).sendKeys(text);
		Thread.sleep(2000);
	}
	
	public void ClicOpciones(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"dropdown\"]/button")).click();
		Thread.sleep(2000);
	}
	
	public void ClicEliminar(String caso) throws InterruptedException {
		driver.findElement(By.id("1Eliminargrilla_reportesNEW")).click();
		Thread.sleep(2000);
	}
	
	public boolean AlertIsPresent(String caso) throws InterruptedException {
		try { 
	        driver.switchTo().alert(); 
	        return true; 
	    }
	    catch (NoAlertPresentException Ex) { 
	        return false; 
	    }
	}
	
	public void IngresarRutUsuario(String caso, String rut) throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[2]/div[1]/table/tbody/tr/td[1]/input")).sendKeys(rut);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[2]/div[1]/table/tbody/tr/td[2]/button")).click();
		Thread.sleep(2000);
	}
	
	public void SeleccionarRolUsuario(String caso, String rol) throws InterruptedException {
		Select lista_rol = new Select (driver.findElement(By.className("filter-role")));
		lista_rol.selectByVisibleText(rol);
		Thread.sleep(2000);
	}
	
	public void CheckNotificaMailPersonal(String caso) throws InterruptedException {
		driver.findElement(By.id("notif_roles_personal")).click();
		Thread.sleep(2000);
	}
	
	public void CheckNotificaMailInstitucional(String caso) throws InterruptedException {
		driver.findElement(By.id("notif_roles_institucional")).click();
		Thread.sleep(2000);
	}
	
	public void CheckNotificaMailDocumentos(String caso) throws InterruptedException {
		driver.findElement(By.id("notif_roles_docs")).click();
		Thread.sleep(2000);
	}
	
	public void BtnConfigurarNotificaciones(String caso) throws InterruptedException {
		driver.findElement(By.id("config_notificaciones")).click();
		Thread.sleep(2000);
	}
	
	public void BtnCancelarConfigurarNotificaciones(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"notificaciones_masivas\"]/div/div/div[3]/button[1]")).click();
		Thread.sleep(2000);
	}
	
	public void BtnSubirPlantilla(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[2]/div[3]/div/div[5]/div[2]")).click();
		Thread.sleep(2000);
	}
	
	public void BtnCerrar(String caso) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[3]/div[2]/button[1]")).click();
		Thread.sleep(2000);
	}
	
	public void SubirPlantilla(String caso, String nombreArchivo) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[2]/div/div[2]/div[2]/input")).sendKeys(Configuration.FILES_DIR2+nombreArchivo);
		Thread.sleep(2000);
	}
	
}
