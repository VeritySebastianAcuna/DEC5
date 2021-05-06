package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

	public class PageCrearDocumento {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageCrearDocumento(WebDriver driver) {
		this.driver=driver;
	}
	
	public void crearDocumento(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[3]/a")).click();
				String texto="Click Crear Documento";
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
					System.out.println("No fue posible Crear Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnCrearPlantillaDec(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr[1]/td/div/div/div[4]/a")).click();
				String texto="Click Botón Crear Plantilla DEC";
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
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnCrearPlantillaArchivo(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr[2]/td/div/div/div[4]/a")).click();
				String texto="Click Botón Crear Plantilla Archivo";
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
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnCrearPlantillaPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr[4]/td/div/div/div[4]/a")).click();
				String texto="Click Botón Crear Plantilla PDF";
				log.modificarArchivoLog(caso, texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
				texto=texto.replace(" ", "_");
				capturaPantalla.takeScreenShotTest(driver, texto, caso);
				Thread.sleep(5000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnCrearPlantillaColaborativa(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr[1]/td/div/div/div[4]/a")).click();
		String texto="Click Botón Crear Plantilla Colaborativa";
		log.modificarArchivoLog(caso, texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
		texto=texto.replace(" ", "_");
		capturaPantalla.takeScreenShotTest(driver, texto, caso);
		Thread.sleep(6000);
		i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void CrearPlantillaDec(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/a")).click();
				String texto="Click Crear Plantilla DEC";
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
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void CrearPlantillaPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/a")).click();
				String texto="Click Crear Plantilla PDF";
				log.modificarArchivoLog(caso, texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
				texto=texto.replace(" ", "_");
				capturaPantalla.takeScreenShotTest(driver, texto, caso);
				Thread.sleep(3000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnCrear(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("crear_make_plantilla")).click();
				String texto="Click Botón Crear Documento";
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
					System.out.println("No fue posible Crear Plantilla");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnCrearPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("crear_make")).click();
				String texto="Click Botón Crear Documento";
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
					System.out.println("No fue posible Crear Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void linkCrearNuevoDoc(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a")).click();
				String texto="Click Link Crear Nuevo Documento";
				log.modificarArchivoLog(caso, texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
				texto=texto.replace(" ", "_");
				capturaPantalla.takeScreenShotTest(driver, texto, caso);
				Thread.sleep(5000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Crear Nuevo Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void linkIrAlDoc(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[2]")).click();
				String texto="Click Link Ir al Documento";
				log.modificarArchivoLog(caso, texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
				texto=texto.replace(" ", "_");
				capturaPantalla.takeScreenShotTest(driver, texto, caso);
				Thread.sleep(5000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ir Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void CrearDocumentoLote(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[3]/a")).click();
				String texto="Click Crear Documento por Lote"; 
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
					System.out.println("No fue posible Crear Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
//	public void DescargarPlantillaExcel(String caso) throws InterruptedException, IOException, InvalidFormatException {
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/button")).click();
//		String texto="Click Descargar Plantilla Excel";
//		log.modificarArchivoLog(caso, texto);
//		crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
//		texto=texto.replace(" ", "_");
//		capturaPantalla.takeScreenShotTest(driver, texto, caso);
//		Thread.sleep(2000);
//	}
	
	public void btnProcesar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("btnContinuar")).click();
				String texto="Click Botón procesar";
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
					System.out.println("No fue posible Procesar Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void btnVolver(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/a")).click();
				String texto="Click Botón Volver";
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
					System.out.println("No fue posible Volver");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	

}
