package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageDec5 {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageDec5(WebDriver driver) {
		this.driver=driver;
	}
	
	public void ClickRuedaConfiguracion(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[2]/div/button")).click();
		String texto="Click a Configuraciones";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void EditarCuenta(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[2]/div/ul/li[1]/a")).click();
		String texto="Click a Editar Cuentas";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public void RegistroCertificadoHSM(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/form/table/tbody/tr[7]/th/span/button")).click();
		String texto="Click a Registro Certificado HSM";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(2000);
	}
	
	public String MensajeCertificadoHSM() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String mensaje = driver.findElement(By.cssSelector("#modal > div > div > div.modal-body > div > div > p > strong")).getText();
		return mensaje;
	}
	
	public void OpcionInstituciones(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[2]/div/ul/li[2]/a")).click();
		String texto ="Click a Instituciones";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
	}
	
	public void OpcionUserName(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/button")).click();
		String texto ="Click a Nombre Usuario";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
	}
	
	public void ClickIngresarLogin(String caso) {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("id-login")).click();
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println();
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar al login");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void Buscar (String caso, String nombre) {
		driver.findElement(By.id("searchTerm")).sendKeys(nombre);
		driver.findElement(By.xpath("//*[@id=\"searchButton\"]/span")).click();
		
	}
	
	public void OpcionAplicaciones(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[2]/div/ul/li[3]/a")).click();
		String texto ="Click a Aplicaciones";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void CambiarEmpresa (String caso) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/child::button")).click();
		Thread.sleep(2000);
		int hijos= driver.findElements(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/descendant::a")).size();
		String empresa="";
		for (int h=1;h<=hijos;h++) {
			empresa = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/descendant::a["+h+"]")).getText();
//			System.out.println(empresa);
			if(empresa.contains("ACEPTA")) {
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/descendant::a["+h+"]")).click();
				h=10;
			}
		}
		Thread.sleep(2000);
	}
	
	public void OpcionUsuarios(String caso) throws IOException, InvalidFormatException, InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		int hijos = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/descendant::a")).size();
		int i=1;
		do {
			if(driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/descendant::a["+i+"]")).getText().equals("Usuarios")) {
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/descendant::a["+i+"]")).click();
				i=hijos+1;
			}
		}while(i<=hijos);
//		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/ul/li[5]/a")).click();
		String texto ="Click a Usuarios";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
	public void ClickMiPortal (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/a")).click();
				String texto ="Click MI PORTAL";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en MI PORTAL");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void ClickCubo (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[2]/div/button")).click();
				String texto ="Click en Cubo";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar clic en Cubo");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void SeleccionarOpcionCubo (String caso, String opcion) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.partialLinkText(opcion)).click();
				String texto ="Seleccionar opción en Cubo";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar opción en Cubo");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	// implementación 05/08/2021
	
	public void OpcionEditarCuenta(String caso) throws IOException, InvalidFormatException, InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("Editar Cuenta")).click();
		
		String texto ="Click a Editar Cuenta";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
	
}
