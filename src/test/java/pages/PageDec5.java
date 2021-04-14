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
	
	public void CambiarEmpresa (String caso) {
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/child::button")).click();
		int hijos= driver.findElements(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/descendant::a")).size();
		String empresa="";
		for (int h=1;h<=hijos;h++) {
			empresa = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/descendant::a["+h+"]")).getText();
			System.out.println(empresa);
			if(empresa.contains("ACEPTA")) {
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/descendant::a["+h+"]")).click();
				h=10;
			}
		}
	}
	
	public void OpcionUsuarios(String caso) throws IOException, InvalidFormatException, InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/ul/li[5]/a")).click();
		String texto ="Click a Usuarios";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(3000);
	}
}
