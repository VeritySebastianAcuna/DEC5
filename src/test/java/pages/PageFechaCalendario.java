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
import common.FechaActual;
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
				if(mes<10) {
					//fechaDesde=anio+"-0"+mes+"-01";
					fechaDesde="01-"+mes+"-"+anio;
				}
				else {
					//fechaDesde=anio+"-"+mes+"-01";
					fechaDesde="01-"+mes+"-"+anio;
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
				mes=mes-1;
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

}
