package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.Configuration;
import common.CrearDocEvidencia;
import common.Log;

public class PageEditarCuenta {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageEditarCuenta(WebDriver driver) {
		this.driver=driver;
	}
	
	public void LinkEditarNombre (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[1]/td[3]/a[3]")).click();
				String texto ="Click Editar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Editar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void LinkEditarTelefono (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[3]/td[3]/a[3]")).click();
				String texto ="Click Editar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Editar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	
	public void LinkEditarCorreo (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[4]/td[3]/a[3]")).click();
				String texto ="Click Editar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Editar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void LinkEditarClave (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[5]/td[3]/a[3]")).click();
				String texto ="Click Editar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Editar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	
	public void BtnRegistrar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[7]/th/span/button")).click();
				String texto ="Click Registrar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Registrar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BtnCargarImagenLadrillo (String caso, String archivo) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[8]/th[1]/div/div[1]/input[1]")).sendKeys(Configuration.FILES_DIR2+archivo);
				String texto ="Click Registrar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Registrar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void cerrarPopUpEditarCuenta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[1]/button")).click();
				String texto ="Click cerrar PopUp";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic cerrar PopUp");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	
	
	
	
	
	// previous -----------------------------------------------------------------
	
	public void ClickEnProcesoDeFirma (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("dashboard-tramite")).click();
				String texto ="Click En Proceso De Firma";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic En Proceso De Firma");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void ClickEnFirmadoPorTodos (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("dashboard-aprobados")).click();
				String texto ="Click En Firmado por Todos";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic En Firmado por Todos");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void ClickEnRechazados (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("dashboard-rechazados")).click();
				String texto ="Click En Rechazados";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic En Rechazadoss");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BtnCancelarRegistrar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[1]")).click();
				String texto ="Click Cancelar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Cancelar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void IngresarNumeroSolicitud (String caso, String numero) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.name("solicitud_hsm")).sendKeys(numero);
				String texto ="Ingresar Número de solicitud";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ingresar Número de solicitud");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(1000);
	}
	
	public void IngresarPinSolicitud (String caso, String numero) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.name("pin_hsm")).sendKeys(numero);
				String texto ="Ingresar PIN de solicitud";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ingresar PIN de solicitud");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(1000);
	}
	
	public void IngresarContrasenaSolicitud (String caso, String pass) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.name("password_hsm")).sendKeys(pass);
				String texto ="Ingresar contraseña de solicitud";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ingresar contraseña de solicitud");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(1000);
	}
	
	public void IngresarRepitaContrasenaSolicitud (String caso, String pass) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.name("confirm_password_hsm")).sendKeys(pass);
				String texto ="Ingresar contraseña de solicitud";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ingresar contraseña de solicitud");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(1000);
	}
	
	public void BtnRegistrarHSM (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[2]")).click();
				String texto ="Click Registrar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("Click Registrar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
}
