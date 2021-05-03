package common;

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

public class LeerExcel {
	public String[] LeerArchivoExcel () throws FileNotFoundException, IOException{
		File f = new File("asd");
	    InputStream inp = new FileInputStream(f);
	    Workbook wb = WorkbookFactory.create(inp);
	    Sheet sheet = wb.getSheetAt(0);
	    int iRow=0;
	    int i=0;
	    Row row = sheet.getRow(0); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	    String value2="";
	    String[] valores = new String[2];
	    while(row!=null) 
	    {
	    	String value=null;
	    	Cell cell = row.getCell(iRow);  
		    try {
		  	  value = cell.getStringCellValue();
		    }catch (Exception e) {
		      if(cell!=null) {
		    	  double valor = cell.getNumericCellValue();
		    	  value = String.valueOf(valor);
		      }
		      else{
		   		i++;
		    	row = sheet.getRow(i);
		    	iRow=-1;
		   	  }
		    }
		    if(value!=null) {
//		    System.out.println(i+","+iRow +": Valor de la celda es " + value);
		    	if(value.equalsIgnoreCase("asd")) {
			    	cell = row.getCell(iRow+1);  
			    	value2=cell.getStringCellValue();
			    	System.out.println(value2);
//			    	System.out.println(value+": "+value2);
			    }
		    }
		    
		    iRow++;
	    }
		return valores;
	}	
	
	public String[] ObtenerDatosCP (String archivo, String cp) throws FileNotFoundException, IOException{
		File f = new File(archivo);
	    InputStream inp = new FileInputStream(f);
	    Workbook wb = WorkbookFactory.create(inp);
	    Sheet sheet = wb.getSheetAt(0);
	    int c = ObtenerCabecera(archivo);
	    int i=1;
	    Row row = sheet.getRow(i); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	    String value=null;
	    String[] cabecera = new String[c];
	    Cell cell;
	    double valor;
	    do {
	    	cell = row.getCell(0);
	    	try {
			  	value = cell.getStringCellValue();
			}
	    	catch (Exception e) {
		        if(cell!=null) {
		        	valor = cell.getNumericCellValue();
		       	  	value = String.valueOf(valor);
		        }
		    	else{
		    		System.out.println("Celda es Nula");
		    	}
		    }
	    	if(value.equalsIgnoreCase(cp)) {
	    		for(int j=0;j<cabecera.length;j++) {
					cell = row.getCell(j);
					try {
					  	value = cell.getStringCellValue();
					}catch (Exception e) {
				        if(cell!=null) {
				        	valor = cell.getNumericCellValue();
				       	  	value = String.valueOf(valor);
				        }
				    	else{
				    		System.out.println("Celda es Nula");
				    	}
				    }
					System.out.println(i+"."+j+": "+value);
					cabecera[j]=value;
			    }
	    		row = sheet.getRow(1000);
	    	}
	    	else {
	    		i++;
		    	row = sheet.getRow(i);
	    	}
	    }while(row!=null);
		return cabecera;
	}
	
	public int ObtenerCabecera (String archivo) throws FileNotFoundException, IOException{
		File f = new File(archivo);
	    InputStream inp = new FileInputStream(f);
	    Workbook wb = WorkbookFactory.create(inp);
	    Sheet sheet = wb.getSheetAt(0);
	    int i=0;
	    Row row = sheet.getRow(0); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	    String value=null;
	    Cell cell;
	    double valor;
	    do {
			cell = row.getCell(i);
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
	
	public int LeerCeldas (String archivo) throws FileNotFoundException, IOException{//FUNCIÓN QUE DEBO IMPLEMENTAR PARA QUE LEEA EL ARCHIVO
		File f = new File(archivo);
	    InputStream inp = new FileInputStream(f);//Ingresar archivo
	    Workbook wb = WorkbookFactory.create(inp);
	    Sheet sheet = wb.getSheetAt(0);//Leo la hoja
	    int i=0;
	    Row row = sheet.getRow(0); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	    String value=null;
	    Cell cell;//almacena valor celda
	    double valor;//Trasnformo algún datos que no es String a String
	    do {//Termina cuando ya no hay mas datos
//			cell = row.getCell(i);//Parte de la 1A
	    	for (int c = 0; c < 5; c++)//RECORRER LAS COLUMNAS
			{
	    		cell = row.getCell(c);//OBTENGO LA CELDA
			try {
//			  	value = cell.getStringCellValue();
//			  	System.out.println(value);
//			  	i++;
//			  	row = sheet.getRow(i);
					value = cell.getStringCellValue();//oBTENGO LOS DATOS DE LA CELDA
					System.out.println(value);	
				
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
			
			}
	    	i++;
			row = sheet.getRow(i);//PASA A LA SIGUIENTE FILA
	    } while(row!=null);
	    System.out.println("El tamaño es: "+i);
		return i;
		
	}
}