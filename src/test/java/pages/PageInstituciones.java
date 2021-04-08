package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageInstituciones {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageInstituciones(WebDriver driver) {
		this.driver=driver;
	}
	
	public void CheckInstitucionesDeshabilitadas (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.id("check-inactive")).click();
		String texto ="Click a Check Instituciones Deshabilitadas";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
	}
	
	public void BusquedaInstituciones (String caso, String busqueda) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.id("searchTerm")).sendKeys(busqueda);
		String texto ="Busqueda de Instituciones";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
	}
	
	public String ResultadoBusqueda(String caso) throws IOException, InvalidFormatException, InterruptedException {
		String resultado = driver.findElement(By.xpath("//*[@id=\"table-institutions\"]/tbody/tr/td/div/div[1]/a/h2")).getText();
		String texto ="Resultado Busqueda de Instituciones";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		return(resultado);
	}
	
	public void CrearInstituciones (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/a")).click();
		String texto ="Click a Crear Institucion";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
	}
	
	public void DatosNuevaInstitucion (String caso, String institucion, String prefijoAuditoria, String rut, String descripcion, 
			String email) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.id("institution_name")).sendKeys(institucion);
		driver.findElement(By.name("nemo")).sendKeys(prefijoAuditoria);
		driver.findElement(By.name("rut")).sendKeys(rut);
		driver.findElement(By.name("description")).sendKeys(descripcion);
		driver.findElement(By.name("email")).sendKeys(email);
		String texto ="Datos Nueva Institución";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
	}
	
	public void DatosAdminDec(String caso, String adminRut, String adminNombre, String adminEmail) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.id("admin-rut")).sendKeys(adminRut);
		driver.findElement(By.id("admin-rut")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		try {
			driver.findElement(By.id("admin-name")).sendKeys(adminNombre);
		}catch (Exception e) {
			System.out.println("Nombre ya ingresado");
		}
		driver.findElement(By.name("admin_email")).sendKeys(adminEmail);
		String texto ="Datos Admin DEC";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(5000);
	}
	
	public void BtnCrearInstitucion(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[2]/div/input")).click();
		String texto ="Click a Boton Crear Institucion";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void FlagAcepta(String caso, String valor) {
		int i=0;
		int j=0;
		do {
			try {
				Select flag = new Select (driver.findElement(By.name("flagacepta")));
				switch (valor){
					case "No":
						flag.selectByValue("0");
						break;
					case "Si":
						flag.selectByValue("01");
						break;
					default:
						System.out.println("Tipo CPE Valor inválido");
						break;
				}
				Thread.sleep(2000);
				String texto = "Valor Flag";
				log.modificarArchivoLog(caso, texto+": "+valor);
				crearDocEvidencia.modificarArchivoEvidencia(caso, valor);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver, texto+valor, caso);
				i=1;
			} catch(Exception e) {
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar Flag");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void CodigoColor (String caso, String codigo) {
		driver.findElement(By.name("scheme_color")).clear();
		driver.findElement(By.name("scheme_color")).sendKeys(codigo);
	}
	
}
