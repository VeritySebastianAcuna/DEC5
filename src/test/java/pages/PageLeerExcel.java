package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageLeerExcel {

	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
//	public PageDescargarArchivos(WebDriver driver) {
//		this.driver=driver;
//	}
	
		
	public void LecturaExcel(String caso, String filepath, String sheetName) throws InterruptedException, InterruptedException, IOException {
		File file = new File(filepath);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
		int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
		for (int i = 0; i <= rowCount; i++) {
			XSSFRow row = newSheet.getRow(i);
			
			for (int j = 0; j < row.getLastCellNum(); j++) {
				System.out.println(row.getCell(j).getStringCellValue()+"||");
			}
		}
		Thread.sleep(2000);
		newWorkbook.close();
	}
	
	public String valorCelda(String caso, String filepath, String sheetName, int rowNumber, int cellNumber) throws IOException {
		File file = new File(filepath);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
		
		XSSFRow row = newSheet.createRow(rowNumber);
		XSSFCell cell = row.getCell(cellNumber);
		
		newWorkbook.close();
		return cell.getStringCellValue();
	}
	
	public int ObtenerCabecera (String archivo) throws FileNotFoundException, IOException{
		File f = new File(archivo);
	    InputStream inp = new FileInputStream(f);//Ingresar archivo
	    Workbook wb = WorkbookFactory.create(inp);
	    Sheet sheet = wb.getSheetAt(0);
	    int i=0;
	    Row row = sheet.getRow(0); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	    String value=null;
	    Cell cell;//almacena valor celda
	    double valor;
	    do {//Termina cuando ya no hay mas datos
			cell = row.getCell(i);//Parte de la 1A
			try {
			  	value = cell.getStringCellValue();
			  	System.out.println(value);
			  	i++;
			}catch (Exception e) {
				if(cell!=null) {
			       	valor = cell.getNumericCellValue();
			  	  	value = String.valueOf(valor);
			  	  	System.out.println(value);
			  	  	i++;
			    }
			    else{
			    	System.out.println("Celda es Nula");
			    	row = sheet.getRow(1000);
			    }
			}
	    } while(row!=null);
	    System.out.println("El tamaño es: "+i);
		return i;
	}

}
