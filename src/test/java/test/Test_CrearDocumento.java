package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageAcepta;
import pages.PageAvanzarPagina;
import pages.PageCrearDocumento;
import pages.PageDatosDocumento;
import pages.PageDatosFirmante;
import pages.PageDec5;
import pages.PageLoginAdm;
import pages.PageSubirArchivos;

public class Test_CrearDocumento {

	private WebDriver driver;
	String datapool = Configuration.ROOT_DIR+"DataPool.xlsx";
	LeerExcel leerExcel = new LeerExcel(); 
	
		
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
//		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://5cap.dec.cl/portal");// Aquí se ingresa la URL para hacer las pruebas.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void Script_1171() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1171";
		//Menu ppal - Crear Documentos - plantilla DEC - crear
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);			
		
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/a")).isDisplayed());
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/a")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO NOOK");
		}
		
		
	}
											
		
	@Test
	public void Script_1175() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1175";
		//Menu ppal - Crear Documentos - plantilla DEC - plantilla DEC - ingreso datos - continuar - firmante ok
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearPlantillaDec(cp);
		pageDatosDocumento.datosDocumento("PRUEBA AUTOMATIZADA", cp);
		pageDatosDocumento.btnRevisaryContinuar(cp);
		pageDatosDocumento.btnContinuar(cp);
//		pageDatosFirmante.btnConfigurarFirmantes(cp);
//		pageDatosFirmante.buscarPersonasAdmin("RIQUELME SANDO",cp);
//		pageDatosFirmante.seleccionFirmantes(cp);
//		pageDatosFirmante.btnAsignar(cp);
		pageDatosFirmante.datosRutFirmante0(cp, datos[11], datos[12]);
		pageDatosFirmante.btnAgregar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[6]/div[3]")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[6]/div[3]")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}	
				
	}
	
	@Test
	public void Script_1176() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1176";
		//Menu ppal - Crear Documentos - plantilla DEC - plantilla DEC - ingreso datos - continuar - firmante nok
				System.out.println(cp);
				
				PageDec5 pageDec5 = new PageDec5(driver);
				PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
				PageAcepta pageAcepta = new PageAcepta(driver);
				PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
				PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
				PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
				
				CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
				crearLogyDocumento.CrearEvidencias(cp);
				
				String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
				
				pageDec5.ClickIngresarLogin(cp);
				pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
				pageDec5.OpcionUserName(cp);
				pageAcepta.empresaAcepta(cp);
				pageCrearDocumento.crearDocumento(cp);
				pageCrearDocumento.btnCrearPlantillaDec(cp);
				pageCrearDocumento.CrearPlantillaDec(cp);
				pageDatosDocumento.datosDocumento("PRUEBA AUTOMATIZADA", cp);
				pageDatosDocumento.btnRevisaryContinuar(cp);
				pageDatosDocumento.btnContinuar(cp);
//				pageDatosFirmante.btnConfigurarFirmantes(cp);
//				pageDatosFirmante.buscarPersonasAdmin("RIQUELME SANDO",cp);
//				pageDatosFirmante.seleccionFirmantes(cp);
//				pageDatosFirmante.btnAsignar(cp);
				pageDatosFirmante.datosRutFirmante0(cp, datos[11], datos[12]);
				pageDatosFirmante.btnAgregar(cp);
				
				
				if(driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[6]/div[1]/div[2]/div/span")).getText().equals("RUT no válido")){
					crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Rut Inválido OK");
					crearLogyDocumento.CasoOk(cp);
					System.out.println("FLUJO OK");
				}
				else {
					crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Rut Inválido NOOK");
					crearLogyDocumento.CasoNok(cp);
					System.out.println("FLUJO NOOK");
				}
	}
	
	@Test
	public void Script_1177() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1177";
		//Menu ppal - Crear Documentos - plantilla DEC - plantilla DEC - ingreso datos - continuar - firmante ok - crear
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearPlantillaDec(cp);
		pageDatosDocumento.datosDocumento("PRUEBA AUTOMATIZADA", cp);
		pageDatosDocumento.btnRevisaryContinuar(cp);
		pageDatosDocumento.btnContinuar(cp);
//		pageDatosFirmante.btnConfigurarFirmantes(cp);
//		pageDatosFirmante.buscarPersonasAdmin("RIQUELME SANDO",cp);
//		pageDatosFirmante.seleccionFirmantes(cp);
//		pageDatosFirmante.btnAsignar(cp);
		pageDatosFirmante.datosRutFirmante0(cp, datos[11], datos[12]);
		pageCrearDocumento.btnCrear(cp);
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText().contains("0000000001_plantillaDEC") &&
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/label")).getText().equals("¿Qué desea hacer?")&&
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a")).getText().equals("Crear nuevo documento")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
							
	}
	
	@Test
	public void Script_1178() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1178";
		//Menu ppal - Crear Documentos - plantilla DEC - plantilla DEC - ingreso datos - continuar - firmante ok - crear - Link crear nuevo doci
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearPlantillaDec(cp);
		pageDatosDocumento.datosDocumento("PRUEBA AUTOMATIZADA", cp);
		pageDatosDocumento.btnRevisaryContinuar(cp);
		pageDatosDocumento.btnContinuar(cp);
//		pageDatosFirmante.btnConfigurarFirmantes(cp);
//		pageDatosFirmante.buscarPersonasAdmin("RIQUELME",cp);
//		pageDatosFirmante.seleccionFirmantes(cp);
//		pageDatosFirmante.btnAsignar(cp);
//		pageDatosFirmante.btnAgregar(cp);
//		pageDatosFirmante.seleccionTipoFirmante(cp, datos[10]);
		pageDatosFirmante.datosRutFirmante0(cp, datos[11], datos[12]);
//		pageDatosFirmante.seleccionTipoFirma(cp, datos[13]);
//		pageDatosFirmante.seleccionTipoNotificacion(cp, datos[14]);
		pageCrearDocumento.btnCrear(cp);
		pageCrearDocumento.linkCrearNuevoDoc(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		
		
	}
	
//	@Test
//	public void Script_1179() throws InterruptedException, IOException, InvalidFormatException {
//		String cp = "DEC_1179";
//		//Menu ppal - Crear Documentos - plantilla DEC - plantilla DEC - ingreso datos - continuar - firmante ok - crear - link Ir al Documento
//		System.out.println(cp);
//		
//		PageDec5 pageDec5 = new PageDec5(driver);
//		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
//		PageAcepta pageAcepta = new PageAcepta(driver);
//		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
//		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
//		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
//		
//		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
//		crearLogyDocumento.CrearEvidencias(cp);
//		
//		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
//		
//		pageDec5.ClickIngresarLogin(cp);
//		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
//		pageDec5.OpcionUserName(cp);
//		pageAcepta.empresaAcepta(cp);
//		pageCrearDocumento.crearDocumento(cp);
//		pageCrearDocumento.btnCrearPlantillaDec(cp);
//		pageCrearDocumento.CrearPlantillaDec(cp);
//		pageDatosDocumento.datosDocumento("PRUEBA AUTOMATIZADA", cp);
//		pageDatosDocumento.btnRevisaryContinuar(cp);
//		pageDatosDocumento.btnContinuar(cp);
//		pageDatosFirmante.btnConfigurarFirmantes(cp);
//		pageDatosFirmante.buscarPersonasAdmin("RIQUELME",cp);
//		pageDatosFirmante.seleccionFirmantes(cp);
//		pageDatosFirmante.btnAsignar(cp);
//		pageDatosFirmante.btnAgregar(cp);
////		pageDatosFirmante.seleccionTipoFirmante(cp, datos[10]);
//		pageDatosFirmante.datosFirmante(cp, datos[11], datos[12]);
////		pageDatosFirmante.seleccionTipoFirma(cp, datos[13]);
////		pageDatosFirmante.seleccionTipoNotificacion(cp, datos[14]);
//		pageCrearDocumento.btnCrear(cp);
//	
//		
//		String documento = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
//		System.out.println(documento);
//		pageCrearDocumento.linkIrAlDoc(cp);
//		
//		String texto= driver.findElement(By.xpath("//*[@id=\"filteredForm\"]/div[2]/div/div/div[3]/div/button")).getText();
//		if(texto.contains(documento)) {
//			crearLogyDocumento.AgregarRegistroLog(cp,texto + "Documento creado OK");
//			System.out.println(texto);
//			crearLogyDocumento.CasoOk(cp);
//		}
//		else {
//			crearLogyDocumento.CasoNok(cp);
//			System.out.println("OK");
//			}
//		
//		System.out.println("FLUJO OK");
//	}
	
	@Test
	public void Script_1180() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1180";
		//Menu ppal - Crear Documentos - plantilla DEC - crear - Doc por lote
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
			if(driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/h1")).getText().equals("Crear Documento por Lote")//&&
					//driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/button")).getText().equals("Descargar Plantilla Excel")&&
					//driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]/input")).getText().equals("Subir Plantilla")
					){
				crearLogyDocumento.CasoOk(cp);
				System.out.println("FLUJO OK");
			}
			else {
				crearLogyDocumento.CasoNok(cp);
				System.out.println("FLUJO NOOK");
			}
		
	}
	
		
//	@Test
//	public void Script_1182() throws InterruptedException, IOException, InvalidFormatException {
//		String cp = "DEC_1182";
//		System.out.println(cp);
//		
//		PageDec5 pageDec5 = new PageDec5(driver);
//		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
//		PageAcepta pageAcepta = new PageAcepta(driver);
//		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
//		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
//		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
//		crearLogyDocumento.CrearEvidencias(cp);
//		
//		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
//		
//		pageDec5.ClickIngresarLogin(cp);
//		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
//		pageDec5.OpcionUserName(cp);
//		pageAcepta.empresaAcepta(cp);
//		pageCrearDocumento.crearDocumento(cp);
//		pageCrearDocumento.btnCrearPlantillaDec(cp);
//		pageCrearDocumento.CrearDocumentoLote(cp);
//		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
//		
//		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span")).getText();
//		if(mensaje.equals("El archivo está vacío")) {
//			crearLogyDocumento.AgregarRegistroLog(cp, "Archivo Vacío");
//			System.out.println(mensaje);
//			crearLogyDocumento.CasoNok(cp);
//			mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span")).getText();
//			if(mensaje.equals("La celda B2 tiene un formato de Email Inválido")) {
//				crearLogyDocumento.AgregarRegistroLog(cp, "Celda B2 tiene un formato de Email Inválido");
//				System.out.println(mensaje);
//				crearLogyDocumento.CasoNok(cp);
//			}
//		}
//		else {
//			crearLogyDocumento.CasoOk(cp);
//			System.out.println("Archivo Carga OK");
//			}
//												
//				
//		System.out.println("FLUJO OK");
//	}
	
	@Test
	public void Script_1183() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1183";
		//Menu ppal - Crear Documentos - plantilla DEC - crear - Doc por lote - subir plantilla - abrir archivo
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[1]")).getText();
				if(mensaje.equals("Se cargaron los datos de 1 Documentos con éxito")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);
					System.out.println("FLUJO OK");
				}else {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Carga Archivo NOOK");
			System.out.println("FLUJO NOOK");
			}
												
	}
	
	@Test
	public void Script_1184() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1184";
		//Menu ppal - Crear Documentos - plantilla DEC - crear - Doc por lote - subir plantilla - abrir archivo - Procesar
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[1]")).getText();
				if(mensaje.equals("Se cargaron los datos de 1 Documentos con éxito")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);	
				}else {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Carga Archivo NOOK");
			}
												
		pageCrearDocumento.btnProcesar(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		
	}
	
	@Test
	public void Script_1185() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1185";
		//Menu ppal - Crear Documentos - plantilla DEC - crear - Doc por lote - subir plantilla - abrir archivo - Procesar - volver
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaDec(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[1]")).getText();
				if(mensaje.equals("Se cargaron los datos de 1 Documentos con éxito")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);	
				}else {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Carga Archivo NOOK");
			}
												
		pageCrearDocumento.btnProcesar(cp);
		pageCrearDocumento.btnVolver(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]")).isDisplayed()&&
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText().equals("Crear Documento")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		
	}
	
	@Test
	public void Script_1187() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1187";
		//Menu ppal - Crear Documentos - Plantilla PDF - crear
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);			
		
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div")).isDisplayed() &&
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/a")).getText().contains("Plantilla PDF")&&
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[3]/a")).getText().contains("Crear Documento por Lote")) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
	}
	
	
	@Test
	public void Script_1189() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1189";
		//Menu ppal - Crear Documentos - plantilla PDF - crear - PDF - Revisar y Continuar
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
//		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"divRevisar\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"divRevisar\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
					
				
		
	}
	
	@Test
	public void Script_1190() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1190";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
//		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText();
		if(texto.equals(texto)) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Nombre documento OK");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
			texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[1]")).getText();
			if(texto.equals("Información para el envío del Documento")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Información envío del Documento OK");
				System.out.println(texto);
				crearLogyDocumento.CasoOk(cp);
				String doc = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText();
				texto= driver.findElement(By.name("document_title")).getText();
				if(texto.contains(doc)) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Título documento OK");
					System.out.println(texto);
					crearLogyDocumento.CasoOk(cp);
					texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[5]/div/div[1]/label")).getText();
					if(texto.equals("Firmantes")) {
						crearLogyDocumento.AgregarRegistroLog(cp, "Campo Firmantes OK");
						System.out.println(texto);
						crearLogyDocumento.CasoOk(cp);
						texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[5]/div/div[2]/label")).getText();
						if(texto.equals("Email")) {
							crearLogyDocumento.AgregarRegistroLog(cp, "Campo Email OK");
							System.out.println(texto);
							crearLogyDocumento.CasoOk(cp);
							texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[5]/div/div[3]/label")).getText();
							if(texto.equals("Orden")) {
								crearLogyDocumento.AgregarRegistroLog(cp, "Campo Orden OK");
								System.out.println(texto);
								crearLogyDocumento.CasoOk(cp);
								texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[5]/div/div[5]/label")).getText();
								if(texto.equals("Notificar")) {
									crearLogyDocumento.AgregarRegistroLog(cp, "Campo Notificar OK");
									System.out.println(texto);
									crearLogyDocumento.CasoOk(cp);
									texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[2]/div[2]/a")).getText();
									if(texto.equals("Volver")) {
										crearLogyDocumento.AgregarRegistroLog(cp, "Botón Volver OK");
										System.out.println(texto);
										crearLogyDocumento.CasoOk(cp);
										texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[2]/div[2]/a")).getText();
										if(texto.equals("Crear")) {
											crearLogyDocumento.AgregarRegistroLog(cp, "Botón Crear OK");
											System.out.println(texto);
											crearLogyDocumento.CasoOk(cp);
											texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[8]/div/div[1]/a/span[1]")).getText();
											if(texto.equals("Agregar comentario (opcional)")) {
												crearLogyDocumento.AgregarRegistroLog(cp, "Botón Crear OK");
												System.out.println(texto);
												crearLogyDocumento.CasoOk(cp);
												texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[11]/div/div[1]/button")).getText();
												if(texto.equals("Agregar Etiqueta")) {
													crearLogyDocumento.AgregarRegistroLog(cp, "Botón Etiqueta OK");
													System.out.println(texto);
													crearLogyDocumento.CasoOk(cp);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("NOK");
			
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1191() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1191";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		pageDatosFirmante.btnConfigurarFirmantes(cp);
		pageDatosFirmante.buscarPersonasAdmin("RIQUELME",cp);
		pageDatosFirmante.seleccionFirmantes(cp);
		pageDatosFirmante.btnAsignar(cp);
		pageDatosFirmante.datosFirmantePdf(cp, datos[11]);
		pageCrearDocumento.btnCrearPdf(cp);
		
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
		if(texto.equals(texto)) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Nombre documento OK");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
//			texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div")).getText();
//			if(texto.contains("¿Qué desea hacer?")) {
//				crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje ¿Qué desea hacer? OK");
//				System.out.println(texto);
//				crearLogyDocumento.CasoOk(cp);
				texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[1]")).getText();
				if(texto.contains("Crear nuevo documento")) {
					crearLogyDocumento.AgregarRegistroLog(cp, " Link Crear nuevo documento OK");
					System.out.println(texto);
					crearLogyDocumento.CasoOk(cp);
					texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[2]")).getText();
					if(texto.equals("Ir al Documento")) {
						crearLogyDocumento.AgregarRegistroLog(cp, "Link Ir al DocumentoOK");
						System.out.println(texto);
						crearLogyDocumento.CasoOk(cp);
					}
				}
//			}
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("NOK");
			
		}
									
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1192() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1192";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		
//		try {
//			String mensaje = driver.findElement(By.xpath("//*[@id=\\\"divRevisar\\\"]/div/div/div[1]/h4")).getText();
//			System.out.println(mensaje);
//			if(mensaje.contains("Revisar")) {
//				crearLogyDocumento.CasoOk(cp);
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//				crearLogyDocumento.CasoOk(cp);
//			}
		
		pageDatosFirmante.btnConfigurarFirmantes(cp);
		pageDatosFirmante.buscarPersonasAdmin("RIQUELME",cp);
		pageDatosFirmante.seleccionFirmantes(cp);
		pageDatosFirmante.btnAsignar(cp);
		pageDatosFirmante.datosFirmantePdf(cp, datos[11]);
		pageCrearDocumento.btnCrear(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
			System.out.println(mensaje);
			if(mensaje.contains("se ha creado con éxito.")) {
				crearLogyDocumento.CasoOk(cp);
			}
		}catch (Exception e) {
			
			crearLogyDocumento.CasoNok(cp);
		   }
		
		pageCrearDocumento.linkCrearNuevoDoc(cp);
									
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1193() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1193";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		pageDatosFirmante.btnConfigurarFirmantes(cp);
		pageDatosFirmante.buscarPersonasAdmin("RIQUELME",cp);
		pageDatosFirmante.seleccionFirmantes(cp);
		pageDatosFirmante.btnAsignar(cp);
		pageDatosFirmante.datosFirmantePdf(cp, datos[11]);
		pageCrearDocumento.btnCrear(cp);
		pageCrearDocumento.linkIrAlDoc(cp);
		
		try {
			String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText();
			System.out.println(mensaje);
			if(mensaje.equals("Mis Documentos")) {
				crearLogyDocumento.CasoOk(cp);
			}
		}catch (Exception e) {
			
			crearLogyDocumento.CasoNok(cp);
		   }
									
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1194() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1194";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/h1")).getText();
			if(mensaje.equals("Crear Documento por Lote")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Vista Documento por Lote OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);
					mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[2]")).getText();
					if(mensaje.contains("Ver video tutorial")) {
							crearLogyDocumento.AgregarRegistroLog(cp, "Ver video tutorial OK");
							System.out.println(mensaje);
							crearLogyDocumento.CasoOk(cp);
							mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/button")).getText();
							if(mensaje.contains("Descargar Plantilla Excel")) {
									crearLogyDocumento.AgregarRegistroLog(cp, "Botón Descargar Plantilla Excel OK");
									System.out.println(mensaje);
									crearLogyDocumento.CasoOk(cp);
									mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]/input")).getText();
									if(mensaje.contains("Subir Plantilla")) {
											crearLogyDocumento.AgregarRegistroLog(cp, "Botón subir Plantilla OK");
											System.out.println(mensaje);
											crearLogyDocumento.CasoOk(cp);
									}
							}
					}
			}
			
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Carga Archivo NOK");
			}									
				
		System.out.println("FLUJO OK");
	}
	
//	@Test
//	public void Script_1196() throws InterruptedException, IOException, InvalidFormatException {
//		String cp = "DEC_1196";
//		System.out.println(cp);
//		
//		PageDec5 pageDec5 = new PageDec5(driver);
//		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
//		PageAcepta pageAcepta = new PageAcepta(driver);
//		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
//		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
//		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
//		crearLogyDocumento.CrearEvidencias(cp);
//		
//		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
//		
//		pageDec5.ClickIngresarLogin(cp);
//		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
//		pageDec5.OpcionUserName(cp);
//		pageAcepta.empresaAcepta(cp);
//		pageCrearDocumento.crearDocumento(cp);
//		pageCrearDocumento.btnCrearPlantillaPdf(cp);
//		pageCrearDocumento.CrearDocumentoLote(cp);
//		
//		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]/input")).getText();
//		if(mensaje.equals("Subir Plantilla")) {
//			crearLogyDocumento.AgregarRegistroLog(cp, "Botón Subir");
//			System.out.println(mensaje);
//			crearLogyDocumento.CasoNok(cp);
//			
//		}
//		else {
//			crearLogyDocumento.CasoOk(cp);
//			System.out.println("Botón Subir Plantilla NOK");
//			}
//												
//		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
//		
//		mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span")).getText();
//		if(mensaje.equals("El archivo está vacío")) {
//			crearLogyDocumento.AgregarRegistroLog(cp, "Archivo Vacío");
//			System.out.println(mensaje);
//			crearLogyDocumento.CasoNok(cp);
//			mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span")).getText();
//			if(mensaje.equals("La celda B2 tiene un formato de Email Inválido")) {
//				crearLogyDocumento.AgregarRegistroLog(cp, "Celda B2 tiene un formato de Email Inválido");
//				System.out.println(mensaje);
//				crearLogyDocumento.CasoNok(cp);
//			}
//		}
//		else {
//			crearLogyDocumento.CasoOk(cp);
//			System.out.println("Archivo Carga OK");
//			}
//				
//		
//		System.out.println("FLUJO OK");
//	}
	
	@Test
	public void Script_1197() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1197";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div")).getText();
		if(mensaje.contains("Se cargaron los datos de 1 Documentos con éxito")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
			System.out.println(mensaje);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Carga Archivo NOK");
			}
												
				
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1198() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1198";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div")).getText();
		if(mensaje.contains("Se cargaron los datos de 1 Documentos con éxito")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
			System.out.println(mensaje);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Carga Archivo NOK");
			}
												
		pageCrearDocumento.btnProcesar(cp);
		
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/div/h2[1]")).getText();
		if(texto.contains("La solicitud está siendo procesada.")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "La solicitud procesada.");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
			texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/div/h2[2]")).getText();
			if(texto.contains("Le enviaremos una notificación al email ")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Notificación enviada");
				System.out.println(texto);
				crearLogyDocumento.CasoOk(cp);
				texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/a")).getText();
				if(texto.contains("Volver")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Botón Volver OK");
					System.out.println(texto);
					crearLogyDocumento.CasoOk(cp);
					}
				}
			}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Solicitud No procesada");
			}
				
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1199() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1199";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageSubirArchivos  pageSubirArchivos = new PageSubirArchivos(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaPdf(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[1]")).getText();
			if(mensaje.equals("Se cargaron los datos de 1 Documentos con éxito")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);
			}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Carga Archivo NOK");
			}
												
		pageCrearDocumento.btnProcesar(cp);
		pageCrearDocumento.btnVolver(cp);
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText();
		if(texto.equals("Crear Documento")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Página Crear Documento OK");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
			}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Página Crear Documento NOK");
			}
		
		System.out.println("FLUJO OK");
	}
//	@Test
//	public void Script_1202() throws InterruptedException, IOException, InvalidFormatException {
//		String cp = "DEC_1202";
//		System.out.println(cp);
//		
//		PageDec5 pageDec5 = new PageDec5(driver);
//		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
//		PageAcepta pageAcepta = new PageAcepta(driver);
//		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
//		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
//		crearLogyDocumento.CrearEvidencias(cp);
//		
//		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
//		
//		pageDec5.ClickIngresarLogin(cp);
//		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
//		pageDec5.OpcionUserName(cp);
//		pageAcepta.empresaAcepta(cp);
//		pageCrearDocumento.crearDocumento(cp);
//		pageCrearDocumento.btnCrearPlantillaArchivo(cp);
//		
//							
//			try {
//				String mensaje= driver.findElement(By.id("uploadFile")).getText();
//				System.out.println(mensaje); 
//				if(mensaje.contains("Selecciona un archivo de tu Equipo")) {
//					crearLogyDocumento.CasoOk(cp);
//				}
//			}catch (Exception e) {
//				crearLogyDocumento.CasoNok(cp);
//				System.out.println("Vista Carga Archivo NOK");
//			   }
//												
//		System.out.println("FLUJO OK");
//	}
	
	@Test
	public void Script_1203() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1203";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaArchivo(cp);
		pageSubirArchivos.SubirArchivo(cp, "Documento Prueba.pdf");		
		String archivo = driver.findElement(By.id("file_name_upload")).getText();
		
			if(archivo.equals("Documento Prueba.pdf")) {
				System.out.println("OK");
		}else {
			System.out.println("NOK");
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1204() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1204";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaArchivo(cp);
		pageSubirArchivos.SubirArchivo(cp, "Documento Prueba.pdf");		
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		String email = driver.findElement(By.name("email_0")).getText();
		if(email.contains("@a****ta.com")) {//DAR OTRA VUELTA COMO VALIDAR QUE EL MAIL CORRESPONDE AL RUT
			System.out.println("OK");
	}else {
		System.out.println("NOK");
	}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1205() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1205";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaArchivo(cp);
		pageSubirArchivos.SubirArchivo(cp, "Documento Prueba.pdf");		
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		pageDatosFirmante.btnAgregar3(cp);
		String archivo = driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[3]/div[6]/div[3]/div[1]/div/select/option[1]")).getText();
		String obj = "PERSONAL";
			if(archivo.equals(obj)) {
				System.out.println("Línea OK");
		}
			else{
			System.out.println("Línea NOK");
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1206() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1206";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageCrearDocumento.btnCrearPlantillaArchivo(cp);
		pageSubirArchivos.SubirArchivo(cp, "Documento Prueba.pdf");		
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		pageDatosFirmante.btnAgregar3(cp);
		
		try{
			String texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[3]/div[6]/div[3]/div[2]/div/span")).getText();
			if(texto.equals("El campo RUT es obligatorio.")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Falta Ingresar Rut NOK");
			System.out.println(texto);
			crearLogyDocumento.CasoNok(cp);
			texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[3]/div[6]/div[3]/div[3]/div/span")).getText();
			if(texto.equals("El campo Email es obligatorio.")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Falta Ingrersar email NOK");
				System.out.println(texto);
				crearLogyDocumento.CasoNok(cp);
				}
			}
		}catch (Exception e) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campos completos OK");
			}
		
		pageDatosFirmante.datosFirmantePdf(cp, "23409729-6");
		pageCrearDocumento.btnCrearPdf(cp);
		try{
			String texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[3]/div[6]/div[1]/div[2]/div/span")).getText();
				if(texto.equals("RUT repetido")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "RUT NOK");
					System.out.println(texto);
					crearLogyDocumento.CasoNok(cp);
					}
		}catch (Exception e) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campos OK");
			}
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
		String nameDoc = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/ul/li")).getText();
		if(texto.equals("Documento creado exitosamente")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Documento Creado OK" + nameDoc);
			System.out.println(texto + nameDoc);
			crearLogyDocumento.CasoOk(cp);
			
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Documento NOK");
			}
		
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1208() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1208";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
				
//		String texto= driver.findElement(By.id("title")).getText();
//		if(texto.equals("genera_plantilla_pdf")) {
//			crearLogyDocumento.AgregarRegistroLog(cp, "Creación Documento plantilla Pdf OK");
//			System.out.println(texto);
//			crearLogyDocumento.CasoOk(cp);
			String texto= driver.findElement(By.xpath("//*[@id=\"divRevisar\"]/div/div/div[3]/div/button")).getText();
			if(texto.equals("Continuar")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Botón Continuar OK");
				System.out.println(texto);
				crearLogyDocumento.CasoOk(cp);
			}
//		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("NOK");
			
		}
											
		System.out.println("FLUJO OK");
	}
	
		
	@Test
	public void Script_1209() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1209";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		
		
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText();
		if(texto.equals("NUEVO 01PLANTILLA_COLABORATIVA_PRUEBA")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Creación Documento plantilla Colaborativa OK");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
			texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[1]/div[2]/div[5]/div/div[1]/label")).getText();
			if(texto.equals("Firmantes")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje Firmantes OK");
				System.out.println(texto);
				crearLogyDocumento.CasoOk(cp);
				texto= driver.findElement(By.id("crear_make")).getText();
				if(texto.equals("Crear")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Botón Crear OK");
					System.out.println(texto);
					crearLogyDocumento.CasoOk(cp);
					texto= driver.findElement(By.xpath("//*[@id=\"formUpload\"]/div[2]/div[2]/a")).getText();
					if(texto.equals("Volver")) {
						crearLogyDocumento.AgregarRegistroLog(cp, "Link Volver OK");
						System.out.println(texto);
						crearLogyDocumento.CasoOk(cp);
					}
				}
			}
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("NOK");
			
		}
											
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1210() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1210";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		
		String email = driver.findElement(By.name("email_0")).getText();
		if(email.contains("@a****ta.com")) {//DAR OTRA VUELTA COMO VALIDAR QUE EL MAIL CORRESPONDE AL RUT
			System.out.println("OK");
			}else {
				System.out.println("NOK");
				}
										
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1211() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1211";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		pageDatosFirmante.btnConfigurarFirmantesArchivo(cp);
		
		String texto = driver.findElement(By.xpath("//*[@id=\"emailModal_1\"]/div/div/div[1]/label")).getText();
		if(texto.equals("Asignar Firmantes")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Ventana Asignar Firmantes OK");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
			texto = driver.findElement(By.id("rolLabel_1")).getText();
			if(texto.equals("Personas en ADMIN")) {
				crearLogyDocumento.AgregarRegistroLog(cp, "Personas ADMIN OK");
				System.out.println(texto);
				crearLogyDocumento.CasoOk(cp);
				texto = driver.findElement(By.xpath("//*[@id=\"idModalBody_1\"]/div[1]/div[2]")).getText();
				if(texto.equals("Seleccionados")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Seleccionados OK");
					System.out.println(texto);
					crearLogyDocumento.CasoOk(cp);
					}
				}
			}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Ventana Asignar Firmantes NOK");
			}
		
																
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1212() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1212";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		pageDatosFirmante.btnConfigurarFirmantesArchivo(cp);
		pageDatosFirmante.buscarPersonasAsinarFirmante("RIQUELME",cp);
		pageDatosFirmante.seleccionFirmantesAsignar(cp);
		pageDatosFirmante.btnAsignar(cp);
		pageCrearDocumento.btnCrearPdf(cp);
			
		String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
		String texto = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
		if(mensaje.contains("El Documento 01Plantilla_colaborativa_prueba")) {
			crearLogyDocumento.AgregarRegistroLog(cp,"" + texto);
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
//			mensaje= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div")).getText();
//			if(mensaje.contains("¿Qué desea hacer?")) {
//				crearLogyDocumento.AgregarRegistroLog(cp, "Mensaje ¿Qué desea hacer? OK");
//				System.out.println(mensaje);
//				crearLogyDocumento.CasoOk(cp);
			    mensaje= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[1]")).getText();
				if(mensaje.contains("Crear nuevo documento")) {
					crearLogyDocumento.AgregarRegistroLog(cp, " Link Crear nuevo documento OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);
					mensaje= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[2]")).getText();
					if(mensaje.equals("Ir al Documento")) {
						crearLogyDocumento.AgregarRegistroLog(cp, "Link Ir al DocumentoOK");
						System.out.println(mensaje);
						crearLogyDocumento.CasoOk(cp);
					}
				}
//			}
			}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Documento NOK");
			}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1213() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1213";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		pageDatosFirmante.btnConfigurarFirmantesArchivo(cp);
		pageDatosFirmante.buscarPersonasAsinarFirmante("RIQUELME",cp);
		pageDatosFirmante.seleccionFirmantesAsignar(cp);
		pageDatosFirmante.btnAsignar(cp);
		pageCrearDocumento.btnCrearPdf(cp);
		
		String mensaje= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[2]")).getText();
		String texto = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/h1")).getText();
		if(mensaje.equals("Ir al Documento")) {
			crearLogyDocumento.AgregarRegistroLog(cp, mensaje +"OK");
			System.out.println(mensaje);
			//crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Link Ir al Documento NOK");
			}
		pageCrearDocumento.linkIrAlDoc(cp);	
		String codigoDoc= driver.findElement(By.xpath("//*[@id=\"filteredForm\"]/div[2]/div/div/div[3]/div/button")).getText();
		if(texto.contains(codigoDoc)) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Nombre Documento OK");
			System.out.println(codigoDoc);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Documentos no son iguales NOK");
			}
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1214() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1214";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		PageDatosDocumento pageDatosDocumento = new PageDatosDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		PageDatosFirmante pageDatosFirmante = new PageDatosFirmante(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearPlantillaPdf(cp);
		pageDatosDocumento.btnRevisaryContinuarPdf(cp);
		pageDatosDocumento.btnContinuar(cp);
		pageDatosFirmante.datosFirmanteArchivo(cp, datos[11]);
		pageDatosFirmante.btnConfigurarFirmantesArchivo(cp);
		pageDatosFirmante.buscarPersonasAsinarFirmante("RIQUELME",cp);
		pageDatosFirmante.seleccionFirmantesAsignar(cp);
		pageDatosFirmante.btnAsignar(cp);
		pageCrearDocumento.btnCrearPdf(cp);
		
		String mensaje= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[1]")).getText();
		if(mensaje.equals("Crear nuevo documento")) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Link Crear Documento OK");
			System.out.println(mensaje);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Link Crear Documento NOK");
			}
		
		pageCrearDocumento.linkIrAlDoc(cp);	
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1215() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1215";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		
		
		String mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/h1")).getText();
			if(mensaje.equals("Crear Documento por Lote")) {
					crearLogyDocumento.AgregarRegistroLog(cp, "Vista Documento por Lote OK");
					System.out.println(mensaje);
					crearLogyDocumento.CasoOk(cp);
					mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/p[2]")).getText();
					if(mensaje.contains("Ver video tutorial")) {
							crearLogyDocumento.AgregarRegistroLog(cp, "Ver video tutorial OK");
							System.out.println(mensaje);
							crearLogyDocumento.CasoOk(cp);
							mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/button")).getText();
							if(mensaje.contains("Descargar Plantilla Excel")) {
									crearLogyDocumento.AgregarRegistroLog(cp, "Botón Descargar Plantilla Excel OK");
									System.out.println(mensaje);
									crearLogyDocumento.CasoOk(cp);
									mensaje= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/div[2]/input")).getText();
									if(mensaje.contains("Subir Plantilla")) {
											crearLogyDocumento.AgregarRegistroLog(cp, "Botón subir Plantilla OK");
											System.out.println(mensaje);
											crearLogyDocumento.CasoOk(cp);
									}
							}
					}
			}
			
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Página Crear Docuemnto NOK");
			}									
				
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1218() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1218";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		try{
			WebElement mensaje = driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span"));
			if(mensaje.equals("El tipo de archivo que intentas subir no está permitido.")) {
		}
			System.out.println("Tipo Archivo permitido NOK");
		}catch (Exception e) {
			System.out.println("Tipo Archivo permitido OK");
		}
		
		String texto= driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div")).getText();
		if(texto.contains("Se cargaron los datos de 1 Documentos con éxito")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Carga Archivo OK");
			System.out.println(texto);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Carga Archivo NOK");
			}
		String btn= driver.findElement(By.id("btnContinuar")).getText();
		if(btn.contains("Procesar")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Botón Procesar Existe");
			System.out.println(btn);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Boton NOK");
			}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1219() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1219";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		try{
			WebElement mensaje = driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span"));
			if(mensaje.equals("El tipo de archivo que intentas subir no está permitido.")) {
		}
			System.out.println("Tipo Archivo permitido NOK");
		}catch (Exception e) {
			System.out.println("Tipo Archivo permitido OK");
		}
		
		pageCrearDocumento.btnProcesar(cp);
		
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/div")).getText();
		String email= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/div/h2[2]/strong")).getText();
		if(texto.contains("Le enviaremos una notificación al email")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Solicitud Documento procesado OK "+email);
			System.out.println(texto + email);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Mensaje NOK");
			}
		
		String btn= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/a")).getText();
		if(btn.contains("Volver")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Botón Volver Existe");
			System.out.println(btn);
			crearLogyDocumento.CasoOk(cp);
			}
		else {
		crearLogyDocumento.CasoNok(cp);
		System.out.println("Botón NOK");
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_1220() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_1220";
		System.out.println(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		PageAcepta pageAcepta = new PageAcepta(driver);
		PageCrearDocumento pageCrearDocumento = new PageCrearDocumento(driver);
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		PageSubirArchivos pageSubirArchivos = new PageSubirArchivos(driver);
		PageAvanzarPagina pageAvanzarPagina = new PageAvanzarPagina(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		pageDec5.ClickIngresarLogin(cp);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		pageDec5.OpcionUserName(cp);
		pageAcepta.empresaAcepta(cp);
		pageCrearDocumento.crearDocumento(cp);
		pageAvanzarPagina.clickAvanzar(cp);
		pageCrearDocumento.btnCrearPlantillaColaborativa(cp);
		pageCrearDocumento.CrearDocumentoLote(cp);
		pageSubirArchivos.CargarArchivoPorLote(cp, "PlantillaExcel_002020560195310_26Apr21_10_39_31.xls");
		
		try{
			WebElement mensaje = driver.findElement(By.xpath("//*[@id=\"formTemplate\"]/div[1]/div/div/div[1]/div/div/div/span"));
			if(mensaje.equals("El tipo de archivo que intentas subir no está permitido.")) {
		}
			System.out.println("Tipo Archivo permitido NOK");
		}catch (Exception e) {
			System.out.println("Tipo Archivo permitido OK");
		}
		
		pageCrearDocumento.btnProcesar(cp);
		
		String texto= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/div")).getText();
		String email= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/div/h2[2]/strong")).getText();
		if(texto.contains("Le enviaremos una notificación al email")) { 
			crearLogyDocumento.AgregarRegistroLog(cp, "Solicitud Documento procesado OK "+email);
			System.out.println(texto + email);
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Mensaje NOK");
			}
		
		pageCrearDocumento.btnVolver(cp);
		
		System.out.println("FLUJO OK");
	}
	
	
	@AfterMethod
	public void FinEjecucion() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

}
