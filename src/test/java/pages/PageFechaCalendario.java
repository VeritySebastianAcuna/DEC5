package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageFechaCalendario {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageFechaCalendario(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void fechaDesde(String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException
	{
		int i=0;
		int j=0;
		do {
			try {
				Calendar calendario = new GregorianCalendar();
				int dia = calendario.get(Calendar.DAY_OF_MONTH);
				int mes = calendario.get(Calendar.MONTH);
				mes=mes+1;
				int anio = calendario.get(Calendar.YEAR);

				String fechaDesde;		
				if(mes<12) {
					//fechaDesde=anio+"-0"+mes+"-01";
					fechaDesde= dia+"-"+mes+"-"+anio;
				}
				else {
					//fechaDesde=anio+"-"+mes+"-01";
					fechaDesde= dia+"-"+mes+"-"+anio;
				}

				driver.findElement(By.name("from")).sendKeys(fechaDesde);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Se Agrega Fecha Desde");
				capturaPantalla.takeScreenShotTest(driver, "Fecha_Desde",caso);
				Thread.sleep(5000);
				i=1;
			} catch (Exception e) {
				PageAlerta pageAlerta = new PageAlerta(driver);
				pageAlerta.alertaManejoError();
				j++;
				if(j==3) {
					System.out.println("No se puede agregar datos Fecha");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void fechaHasta(String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException
	{
		int i=0;
		int j=0;
		do {
			try {
				Calendar calendario = new GregorianCalendar();
				int dia = calendario.get(Calendar.DAY_OF_MONTH);
				int mes = calendario.get(Calendar.MONTH);
				mes=mes+1;
				int anio = calendario.get(Calendar.YEAR);

				String fechaHasta;		
				if(mes<12) {
					
					fechaHasta=dia+"-"+mes+"-"+anio;
				}
				else {
					
					fechaHasta=dia+"-"+mes+"-"+anio;
				}

				driver.findElement(By.name("to")).sendKeys(fechaHasta);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Se Agrega Fecha Hasta");
				capturaPantalla.takeScreenShotTest(driver, "Fecha_Hasta",caso);
				Thread.sleep(5000);
				i=1;
			} catch (Exception e) {
				PageAlerta pageAlerta = new PageAlerta(driver);
				pageAlerta.alertaManejoError();
				j++;
				if(j==3) {
					System.out.println("No se puede agregar datos Fecha");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void fechaHastaMayor(String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException
	{
		int i=0;
		int j=0;
		do {
			try {
				Calendar calendario = new GregorianCalendar();
				int dia = calendario.get(Calendar.DAY_OF_MONTH);
				int mes = calendario.get(Calendar.MONTH);
				mes=mes+1;
				int anio = calendario.get(Calendar.YEAR);

				String fechaHastaMayor;		
				if(mes<12) {
					
					fechaHastaMayor=dia+"-"+mes+"-"+anio;
				}
				else {
					
					fechaHastaMayor=dia+"-"+mes+"-"+anio;
				}
				
				String fechaDesdeMenor;
				int mes1 = calendario.get(Calendar.MONTH);
				mes1=mes-1;
				if(mes<12) {
					
					fechaDesdeMenor=dia+"-"+mes1+"-"+anio;
				}
				else {
					
					fechaDesdeMenor=dia+"-"+mes1+"-"+anio;
				}

				driver.findElement(By.name("to")).sendKeys(fechaHastaMayor);
				driver.findElement(By.name("from")).sendKeys(fechaDesdeMenor);
				crearDocEvidencia.modificarArchivoEvidencia(caso, "Se Agrega Fecha");
				capturaPantalla.takeScreenShotTest(driver, "Fecha_Hasta_mayor",caso);
				Thread.sleep(5000);
				i=1;
			} catch (Exception e) {
				PageAlerta pageAlerta = new PageAlerta(driver);
				pageAlerta.alertaManejoError();
				j++;
				if(j==3) {
					System.out.println("No se puede agregar datos Fecha");
					i=1;
				}
			}
		}while(i==0);
	}
	
//	public void fechaHastaMenor(String caso) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException
//	{
//		int i=0;
//		int j=0;
//		do {
//			try {
//				Calendar calendario = new GregorianCalendar();
//				int dia = calendario.get(Calendar.DAY_OF_MONTH);
//				int mes = calendario.get(Calendar.MONTH);
//				mes=mes+1;
//				int anio = calendario.get(Calendar.YEAR);
//
//				String fechaDesdeMayor;		
//				if(mes<12) {
//					
//					fechaDesdeMayor=dia+"-"+mes+"-"+anio;
//				}
//				else {
//					
//					fechaDesdeMayor=dia+"-"+mes+"-"+anio;
//				}
//				
//				String fechaHastaMenor;
//				int mes1 = calendario.get(Calendar.MONTH);
//				mes1=mes-1;
//				if(mes<12) {
//					
//					fechaHastaMenor=dia+"-"+mes1+"-"+anio;
//				}
//				else {
//					
//					fechaHastaMenor=dia+"-"+mes1+"-"+anio;
//				}
//
//				driver.findElement(By.name("to")).sendKeys(fechaHastaMenor);
//				driver.findElement(By.name("from")).sendKeys(fechaDesdeMayor);
//				crearDocEvidencia.modificarArchivoEvidencia(caso, "Se Agrega Fecha");
//				capturaPantalla.takeScreenShotTest(driver, "Fecha_Hasta_menor",caso);
//				Thread.sleep(5000);
//				i=1;
//			} catch (Exception e) {
//				PageAlerta pageAlerta = new PageAlerta(driver);
//				pageAlerta.alertaManejoError();
//				j++;
//				if(j==3) {
//					System.out.println("No se puede agregar datos Fecha");
//					i=1;
//				}
//			}
//		}while(i==0);
//	}

}
