package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
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
	
	public void BtnAsignar(String caso) throws InterruptedException {
		driver.findElement(By.name("s_role")).click();
		Thread.sleep(2000);
	}
}
