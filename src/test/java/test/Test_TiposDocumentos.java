package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Configuration;
import common.LeerExcel;
import evidence.CrearLogyDocumento;
import pages.PageAcepta;
import pages.PageCrearDocumento;
import pages.PageDec5;
import pages.PageDescargarArchivos;
import pages.PageLoginAdm;
import pages.PageSubirArchivos;

public class Test_TiposDocumentos {
	private WebDriver driver;//Declaro el objeto webdriver
	String datapool = Configuration.ROOT_DIR+"DataPool.xlsx";
	LeerExcel leerExcel = new LeerExcel(); 

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
//		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://5qa.dec.cl/portal");// Aqu? se ingresa la URL para hacer las pruebas. https://5cap.dec.cl/portal
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
	}
	
		
	@Test
	public void Script_0245() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0245";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
	
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed()) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Grilla Documento OK");
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0246() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0246";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);

		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "Otras Instituciones");
		
	
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]")).isDisplayed()) {
			crearLogyDocumento.AgregarRegistroLog(cp, "Grilla Documento OK");
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0247() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0247";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "AGSFAD"); 
		pageAcepta.btnIconoBuscar(cp);
	
		String msj_No_Resultados = "No se encontraron resultados";
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td")).getText().equals(msj_No_Resultados)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0248() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0248";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "002020560195310"); 
		pageAcepta.btnIconoBuscar(cp);
	
		String nombre_Carpeta_Buscar = "002020560195310";
	
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td/div/div/div[1]")).getText().contains(nombre_Carpeta_Buscar)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0249() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0249";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.seleccionEmpresaBusqueda(cp, "ACEPTA");
		pageAcepta.inputBuscar(cp, "002020560195310");
		pageAcepta.checkIncluirOcultos(cp);
		pageAcepta.btnIconoBuscar(cp);
	
		String nombre_Carpeta_Buscar = "002020560195310";
	
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr/td/div/div/div[1]")).getText().contains(nombre_Carpeta_Buscar)){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0251() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0251";
		//Tipos Doc - crear 
		String titulo_pesta?a_Crear = "Crear nuevo Tipo de Documento";
		String nombre_link_tipo_Doc1 = "Plantilla DEC";
		String nombre_link_tipo_Doc2 = "Subir un archivo";
		String nombre_link_tipo_Doc3 = "Plantilla PDF";
		String nombre_link_tipo_Doc4 = "Plantilla Colaborativa";
		String nombre_bloque1 = "Mi Portal";
		String nombre_bloque2 = "Mis Documentos";
		String nombre_bloque3 = "Crear Documento";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		
		//Validaci?n Fila men? principal (Mi portal, Mis documentos, Crear Documento)
		if(driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/a")).getText().equals(nombre_bloque1) &&
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/a")).getText().equals(nombre_bloque2) &&
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[3]/a")).getText().equals(nombre_bloque3)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Bloque men? principal OK");
		} 
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Bloque men? principal NOOK");
		}

		//Validaci?n bloque crear tipo de documento
				if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/h1")).getText().equals(titulo_pesta?a_Crear)&&
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/a")).getText().equals(nombre_link_tipo_Doc1)&&
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[3]/a")).getText().equals(nombre_link_tipo_Doc2)&&
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[4]/a")).getText().equals(nombre_link_tipo_Doc3) &&
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[5]/a")).getText().equals(nombre_link_tipo_Doc4)){
					crearLogyDocumento.CasoOk(cp);
					System.out.println("FLUJO OK");
				} 
				else {
					crearLogyDocumento.CasoNok(cp);
					System.out.println("FLUJO NOOK");
				}		
	}
	
	@Test
	public void Script_0253() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0253";
		//Tipos Doc - crear - Pantilla DEC - Ingresar formato de plantilla 
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		
	
		if(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")).getText().contains(datos[4])) {
			String texto = "Ingreso texto editor OK"; 
			crearLogyDocumento.AgregarRegistroLog(cp, texto);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
				
		System.out.println("FLUJO OK");
	}
	/*
	@Test
	public void Script_0254() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0254";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0255() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0255";
		//Tipos Doc - crear - Pantilla DEC - formatos de campo no correcto
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	*/
	
	@Test
	public void Script_0256() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0256";
		//Tipos Doc - crear - Pantilla DEC - Cerrar
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.btnCerrarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	/*
	@Test
	public void Script_0257() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0257";
		//Tipos Doc - crear - Pantilla DEC - Continuar
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp);
		}
		else {
			crearLogyDocumento.CasoOk(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0258() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0258";
		//Tipos Doc - crear - Pantilla DEC - Nombre Tipo Documento
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0259() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0259";
		//Tipos Doc - crear - Pantilla DEC - Rol Creador (seleccionar)
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0260() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0260";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPermitirAgregarFirmantes(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0261() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0261";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxRecibirNotificaciones(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0262() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0262";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxTituloDocumentoIgualNombreArchivo(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0263() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0263";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxVisualizacionOrdenFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0264() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0264";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxEnviarBotonFirmaCorreoPendienteFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0265() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0265";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxPDFconPassword(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0266() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0266";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.checkboxValidacionCorreoPersonal(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0267() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0267";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0268() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0268";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "PERSONAL");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0269() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0269";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "GRUPO PERSONAS");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0270() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0270";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "000_VERITY_PRUEBA");//OTRAS INSTITUCIONES
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0271() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0271";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Trabajador");//Selecciona rol/rut
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0272() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0272";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPEC?FICO");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0273() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0273";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "CUALQUIERA");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0274() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0274";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0275() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0275";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0276() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0276";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0277() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0277";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Compartir");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0278() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0278";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Pin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0279() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0279";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Huella");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0280() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0280";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con HSM");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0281() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0281";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Firma M?vil Avanzada");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0282() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0282";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Token");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0283() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0283";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Clave Unica");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0284() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0284";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Cedula Identidad");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0285() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0285";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Clave Unica + Cedula Identidad");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0286() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0286";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar solo con Pin Notarial");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0287() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0287";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firma C?dula(Notarios)");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0288() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0288";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firma Bioholografa(Notarios)");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0289() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0289";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar solo con Pin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0290() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0290";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar solo con Huella");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0291() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0291";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar solo con Clave Unica");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0292() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0292";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Visar con Firma");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0293() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0293";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0294() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0294";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Todas");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0295() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0295";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Finalizado");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0296() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0296";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Firmado");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0297() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0297";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Rechazo");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0298() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0298";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0299() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0299";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregar(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		pageAcepta.rolRut1(cp, "Admin");
		pageAcepta.estadoEspecificacion1(cp, "Firmar");
		
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0300() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0300";
		System.out.println(cp);
		String etiqueta1 = "pruebaQA1";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		pageAcepta.orden(cp, "1");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		pageAcepta.btnAgregar1EtiquetasOpcional(cp, etiqueta1);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div[2]/div[1]/button/span")).getText().contains(etiqueta1)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btn2CrearTipodeDocumento(cp);
	}
	
	@Test
	public void Script_0301() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0301";
		System.out.println(cp);
		String etiqueta1 = "pruebaQA1";
		String etiqueta2 = "pruebaQA2";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
//		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
//		pageAcepta.tipoFirma(cp, "ESPECIFICO");
//		pageAcepta.orden(cp, "1");
//		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
//		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		pageAcepta.btnAgregar1EtiquetasOpcional(cp, etiqueta1);
		pageAcepta.btnAgregar2EtiquetasOpcional(cp, etiqueta2);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div[2]/div[2]/button/span")).getText().contains(etiqueta2)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btn2CrearTipodeDocumento(cp);
	}
	
	@Test
	public void Script_0302() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0302";
		System.out.println(cp);
		String etiqueta1 = "pruebaQA1";
		String etiqueta2 = "pruebaQA2";
		String etiqueta3 = "pruebaQA3";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
//		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
//		pageAcepta.tipoFirma(cp, "ESPECIFICO");
//		pageAcepta.orden(cp, "1");
//		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
//		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		pageAcepta.btnAgregar1EtiquetasOpcional(cp, etiqueta1);
		pageAcepta.btnAgregar2EtiquetasOpcional(cp, etiqueta2);
		pageAcepta.btnAgregar3EtiquetasOpcional(cp, etiqueta3);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div[2]/div[3]/button/span")).getText().contains(etiqueta3)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btn2CrearTipodeDocumento(cp);
	}
	
	
	@Test
	public void Script_0303() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0303";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad1(cp);// Opci?n: Cualquiera puede compartir y descargar el documento.
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0304() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0304";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad2(cp);// Opci?n:   Solo los firmantes pueden compartir y descargar el documento.
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0305() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0305";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad3(cp);// Opci?n: Nadie puede compartir ni descargar el documento.
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0306() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0306";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad4(cp);// Opci?n: Documento P?blico (Se accede mediante URL).
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0307() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0307";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregarEtiquetaCampoValor(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[3]/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[3]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
	}
	
	@Test
	public void Script_0308() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0308";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregarEtiquetaCampoValor(cp);
		pageAcepta.campoEtiqueta(cp, "CampoAutomatizado");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
	}
	
	@Test
	public void Script_0311() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0311";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregarEtiquetaCampoValor(cp);
		pageAcepta.valorEtiqueta(cp, "ValorAutomatizado");
		pageAcepta.btn2CrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
	}
	
	@Test
	public void Script_0312() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0312";
		System.out.println(cp);
		
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregarEtiquetaCampoValor(cp);
		pageAcepta.campoEtiqueta(cp, "CampoAutomatizado");
		pageAcepta.configuracionEtiqueta(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed() &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().contains("Lista de Valores") &&
				driver.findElement(By.name("op-val")).isDisplayed() && 
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().contains("Agregar") &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().contains("Aceptar")){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		
		//pageAcepta.btn2CrearTipodeDocumento(cp);	
		
	}
	
	@Test
	public void Script_0313() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0313";
		String titulo_PopUp_Etiqueta = "Lista de Valores";
		String btn_Agregar = "Agregar";
		String btn_Aceptar = "Aceptar";
		String nombre_ListaValor_PopUp;
		String nombre_ListaValor_Agregada;
		System.out.println(cp);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregarEtiquetaCampoValor(cp);
		pageAcepta.campoEtiqueta(cp, "CampoAutomatizado");
		pageAcepta.configuracionEtiqueta(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/h1")).getText().equals(titulo_PopUp_Etiqueta) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).getText().equals(btn_Agregar) &&
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).getText().equals(btn_Aceptar)) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		pageAcepta.listaValores(cp, "Valor automatizado"); //Ingreso texto del nombre a agregar
		nombre_ListaValor_PopUp = driver.findElement(By.name("op-val")).getText(); //Rescato texto ingresado
		
		pageAcepta.btnAgregarConfg(cp);
		
		nombre_ListaValor_Agregada = driver.findElement(By.name("valOp0")).getText();

		if((driver.findElement(By.name("valOp0")).getText().length()>0) &&
				(nombre_ListaValor_PopUp.equals(nombre_ListaValor_Agregada))) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		
		
		//pageAcepta.btn2CrearTipodeDocumento(cp);	
		
	}
	
	@Test
	public void Script_0314() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0314";
		System.out.println(cp);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btnAgregarEtiquetaCampoValor(cp);
		pageAcepta.campoEtiqueta(cp, "CampoAutomatizado");
		pageAcepta.configuracionEtiqueta(cp);
	
		pageAcepta.listaValores(cp, "Valor automatizado"); //Ingreso texto del nombre a agregar popup
		pageAcepta.btnAgregarConfg(cp);
		pageAcepta.btnAceptarListaValoresPopup(cp);
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO NOOK");
		}
		//pageAcepta.btn2CrearTipodeDocumento(cp);	
		
	}
	
	@Test
	public void Script_0315() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0315";
		System.out.println(cp);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.campoDescripcionDocumento(cp, "Esto es un documento de prueba Automatizada");
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		pageAcepta.btn2CrearTipodeDocumento(cp);	
		
	}
	
	@Test
	public void Script_0316() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0316";
		System.out.println(cp);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		//pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar");
		pageAcepta.btn2CrearTipodeDocumento(cp);	
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
		}
		
	}
	
	@Test
	public void Script_0317() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0317";
		String campo_obligatorio_NombreDoc = "El campo Nombre Tipo Documento es obligatorio.";
		String campo_obligatorio_RolCreador = "El campo Rol Creador es obligatorio.";
		String campo_obligatorio_RolRut = "El campo Rol es obligatorio.";
		String campo_obligatorio_Especificacion = "El campo Acci?n es obligatorio.";
		System.out.println(cp);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkPlantillaDEC(cp);
		pageAcepta.EditorPlantilla(cp, datos[4]);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.btn2CrearTipodeDocumento(cp);	
		pageAcepta.CerrarEditorPlantilla(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[1]/div[2]/div/span")).getText().equals(campo_obligatorio_NombreDoc)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campo Obligatorio Nombre Tipo Documento OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Campo Obligatorio Nombre Tipo Documento NOOK");
		}
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[2]/div[3]/div/span")).getText().equals(campo_obligatorio_RolCreador)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campo Obligatorio Rol Creador OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Campo Obligatorio Rol Creador NOOK");
		}
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[2]/div/span")).getText().equals(campo_obligatorio_RolRut)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campo Obligatorio Rol Rut OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Campo Obligatorio Rol Rut NOOK");
		}
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span[2]")).getText().equals(campo_obligatorio_Especificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campo Obligatorio Especificaci?n OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Campo Obligatorio Especificaci?n NOOK");
		}
		
		System.out.println("FLUJO OK");
		
	}
	*/
	
	@Test
	public void Script_0319() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0319";
		System.out.println(cp);
		String tipoDoc = "Nombre Tipo Documento";
		String resultado = null;
		
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[1]/div/div/label")).getText().contains(tipoDoc)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Tipo Doc OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Tipo Doc NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	

	@Test
	public void Script_0320() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0320";
		System.out.println(cp);
		String Rol = "Rol Creador";
		String resultado = null;
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[2]/div[2]/label")).getText().contains(Rol)){
			crearLogyDocumento.CasoOk(cp); 
			System.out.println("Rol Creador OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Rol Creado NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0321() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0321";
		System.out.println(cp);
		String check1 = "Permitir agregar mas firmantes";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[1]")).getText().contains(check1)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check permitir agregar m?s firmantes OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check permitir agregar m?s firmantes NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.checkboxPermitirAgregarFirmantes(cp);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0322() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0322";
		System.out.println(cp);
		String check2 = "Recibir Notificaciones";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[2]")).getText().contains(check2)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check Recibir Notificaciones OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check Recibir Notificaciones NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.checkboxRecibirNotificaciones(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0323() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0323";
		System.out.println(cp);
		String check3 = "Titulo Documento igual Nombre Archivo";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[3]")).getText().contains(check3)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check Titulo Documento igual Nombre Archivo OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check Titulo Documento igual Nombre Archivo NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.checkboxTituloDocumentoIgualNombreArchivo(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0324() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0324";
		System.out.println(cp);
		String check4 = "Visualizaci?n seg?n Orden de Firma";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[4]")).getText().contains(check4)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check Visualizaci?n seg?n Orden de Firma OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check Visualizaci?n seg?n Orden de Firma NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.checkboxVisualizacionOrdenFirma(cp);
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0325() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0325";
		System.out.println(cp);
		String check5 = "Enviar Bot?n de Firma en Correo Pendiente de Firma";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[5]")).getText().contains(check5)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check Enviar Bot?n de Firma en Correo Pendiente de Firma OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check Enviar Bot?n de Firma en Correo Pendiente de Firma NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.checkboxEnviarBotonFirmaCorreoPendienteFirma(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0326() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0326";
		System.out.println(cp);
		String check6 = "PDF con Password";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[6]")).getText().contains(check6)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check PDF con Password OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check PDF con Password NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.checkboxPDFconPassword(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0327() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0327";
		System.out.println(cp);
		String check7 = "Validaci?n Correo Personal para Notificar";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[3]/div[7]")).getText().contains(check7)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Check Validaci?n Correo Personal para Notificar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Check Validaci?n Correo Personal para Notificar NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.checkboxValidacionCorreoPersonal(cp);
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0328() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0328";
		System.out.println(cp);
		String Institucion1 = "ACEPTA";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[1]/div/span/span[1]/span")).getText().contains(Institucion1)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Empresa Acepta OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Empresa Acepta NOOK");
			resultado = "FLUJO NOOK";
		}
				
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
//		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
//		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
//			crearLogyDocumento.CasoOk(cp); 
//		}
//		else {
//			crearLogyDocumento.CasoNok(cp);
//		}
//		
//		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0329() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0329";
		System.out.println(cp);
		String Institucion2 = "PERSONAL";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		pageAcepta.seleccionInstitucion(cp, "PERSONAL");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[1]/div/span")).getText().contains(Institucion2)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Empresa Personal OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Empresa Personal NOOK");
			resultado = "FLUJO NOOK";
		}
		
		
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
//		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
//		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
//			crearLogyDocumento.CasoOk(cp); 
//		}
//		else {
//			crearLogyDocumento.CasoNok(cp);
//		}
//		
//		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0330() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0330";
		System.out.println(cp);
		String Institucion3 = "GRUPO PERSONAS";
		String resultado = null;
	
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "GRUPO PERSONAS");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[1]/div/span")).getText().contains(Institucion3)){
		    crearLogyDocumento.CasoOk(cp);
		    System.out.println("Empresa Grupo Personas OK");
		    resultado = "FLUJO OK";
	    }
	    else {
	  	    crearLogyDocumento.CasoNok(cp);
		    System.out.println("Empresa Grupo Personas NOOK");
		    resultado = "FLUJO NOOK";
	    }
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
//		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
//		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
//			crearLogyDocumento.CasoOk(cp); 
//		}
//		else {
//			crearLogyDocumento.CasoNok(cp);
//		}
//		
//		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0331() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0331";
		System.out.println(cp);
		String Institucion4 = "000_VERITY_PRUEBA";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		
		pageAcepta.seleccionInstitucion(cp, "000_VERITY_PRUEBA");//Otras instituciones
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[1]/div/span")).getText().contains(Institucion4)){
	        crearLogyDocumento.CasoOk(cp);
	        System.out.println("Empresa Otras Instituciones OK");
	        resultado = "FLUJO OK";
      }
      else {
  	    crearLogyDocumento.CasoNok(cp);
	        System.out.println("Empresa Otras Instituciones NOOK");
	        resultado = "FLUJO NOOK";
      }
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		pageAcepta.btnCrearTipoDocEnviar(cp);
		
//		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
//		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
//			crearLogyDocumento.CasoOk(cp);
//		}
//		else {
//			crearLogyDocumento.CasoNok(cp);
//		}
//		
//		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	@Test
	public void Script_0332() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0332";
		System.out.println(cp);
		String Rol = "Rol / RUT";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
				
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[2]/div/div[2]")).getText().contains(Rol) &&
				driver.findElement(By.name("signer_role_0")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n ROl/Rut OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n ROl/Rut NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0333() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0333";
		System.out.println(cp);
		String Firma = "ESPEC?FICO";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "ESPEC?FICO");
		
		if(driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div/div[1]/div[4]/div[3]/div/div[3]/select/option[1]")).getText().contains(Firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Firma Espec?fico OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Firma Espec?fico NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0334() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0334";
		System.out.println(cp);
		String Firma = "CUALQUIERA";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoFirma(cp, "CUALQUIERA");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[3]/select/option[2]")).getText().contains(Firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Firma Cualquiera OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Firma Cualquiera NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0335() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0335";
		System.out.println(cp);
		String orden = "Orden";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
//		pageAcepta.tipoFirma(cp, "ESPECIFICO");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[2]/div/div[5]/label")).getText().contains(orden) &&
				driver.findElement(By.name("order_0")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n campo Orden OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n campo Orden NOOK");
			resultado = "FLUJO NOOK";
		}
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
//		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
//		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
//			crearLogyDocumento.CasoOk(cp);
//		}
//		else {
//			crearLogyDocumento.CasoNok(cp);
//		}
//		
//		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0336() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0336";
		System.out.println(cp);
		String accion = "Firmar";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
//		pageAcepta.tipoFirma(cp, "ESPECIFICO");
//		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		
		if(driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div/div[1]/div[4]/div[3]/div/div[5]/div/select/option[2]")).getText().contains(accion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Acci?n Firmar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Acci?n Firmar NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0337() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0337";
		System.out.println(cp);
		String accion = "Visar";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.orden(cp, "1");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		
		//Agrego otro firmante para la validaci?n de Visar
		pageAcepta.btnAgregar(cp);
		pageAcepta.rolRut1(cp, "Admin2");
		pageAcepta.orden1(cp, "2");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion1(cp, "Visar");
		
		if(driver.findElement(By.xpath("//*[@id=\"tipoaccion_1\"]/option[3]")).getText().contains(accion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Acci?n Visar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Acci?n Visar NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.estadoEspecificacion1(cp, "Visar solo con Pin");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0338() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0338";
		System.out.println(cp);
		String accion = "Visualizar";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Visualizar");
		
		if(driver.findElement(By.xpath("//*[@id=\"tipoaccion_0\"]/option[4]")).getText().contains(accion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Acci?n Visualizar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Acci?n Visualizar NOOK");
			resultado = "FLUJO NOOK";
		}
		pageAcepta.estadoEspecificacion(cp, "Compartir");
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0339() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0339";
		System.out.println(cp);
		String firma = "Firmar solo con Pin";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Pin OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Pin NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0340() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0340";
		System.out.println(cp);
		String firma = "Firmar solo con Huella";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Huella");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Huella OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Huella NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0341() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0341";
		System.out.println(cp);
		String firma = "Firmar solo con HSM";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con HSM");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con HSM OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con HSM NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	
	}
	
	@Test
	public void Script_0343() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0343";
		System.out.println(cp);
		String firma = "Firmar solo con Token";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Token");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp); //*[@id="primerPaso"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]
			System.out.println("Validaci?n Firmar solo con Token OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Token NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0342() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0342";
		System.out.println(cp);
		String firma = "Firmar solo con Firma M?vil Avanzada";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Firma M?vil Avanzada");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Firma M?vil Avanzada OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Firma M?vil Avanzada NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0344() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0344";
		System.out.println(cp);
		String firma = "Firmar solo con Clave Unica";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Clave Unica");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Clave Unica OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Clave Unica NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0345() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0345";
		System.out.println(cp);
		String firma = "Firmar solo con Cedula Identidad";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Cedula Identidad");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Cedula Identidad OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Cedula IdentidadNOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0346() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0346";
		System.out.println(cp);
		String firma = "Firmar solo con Clave Unica + Cedula Identidad";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Clave Unica + Cedula Identidad");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Clave Unica + Cedula Identidad OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Clave Unica + Cedula Identidad NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0347() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0347";
		System.out.println(cp);
		String firma = "Firmar solo con Pin Notarial";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin Notarial");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firmar solo con Pin Notarial OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firmar solo con Pin Notarial NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0348() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0348";
		System.out.println(cp);
		String firma = "Firma C?dula(Notarios)";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firma C?dula(Notarios)");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firma C?dula(Notarios) OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firma C?dula(Notarios) NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0349() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0349";
		System.out.println(cp);
		String firma = "Firma Bioholografa(Notarios)";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firma Bioholografa(Notarios)");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Firma Bioholografa(Notarios) OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Firma Bioholografa(Notarios) NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0350() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0350";
		System.out.println(cp);
		String firma = "Visar solo con Pin";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		//Agrego otro firmante para la validaci?n de Visar
		pageAcepta.btnAgregar(cp);
		pageAcepta.rolRut1(cp, "Admin2");
		pageAcepta.orden1(cp, "2");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion1(cp, "Visar");
		pageAcepta.estadoEspecificacion1(cp, "Visar solo con Pin");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Visar solo con Pin OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Visar solo con Pin NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0351() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0351";
		System.out.println(cp);
		String firma = "Visar solo con Huella";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		//Agrego otro firmante para la validaci?n de Visar
		pageAcepta.btnAgregar(cp);
		pageAcepta.rolRut1(cp, "Admin2");
		pageAcepta.orden1(cp, "2");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion1(cp, "Visar");
		pageAcepta.estadoEspecificacion1(cp, "Visar solo con Huella");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Visar solo con Huella OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Visar solo con Huella NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0352() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0352";
		System.out.println(cp);
		String firma = "Visar solo con Clave Unica";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		//Agrego otro firmante para la validaci?n de Visar
		pageAcepta.btnAgregar(cp);
		pageAcepta.rolRut1(cp, "Admin2");
		pageAcepta.orden1(cp, "2");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion1(cp, "Visar");
		pageAcepta.estadoEspecificacion1(cp, "Visar solo con Clave Unica");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Visar solo con Clave Unica OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Visar solo con Clave Unica NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
		
	
	@Test
	public void Script_0353() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0353";
		System.out.println(cp);
		String firma = "Visar con Firma";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		//Agrego otro firmante para la validaci?n de Visar
		pageAcepta.btnAgregar(cp);
		pageAcepta.rolRut1(cp, "Admin2");
		pageAcepta.orden1(cp, "2");//Ingreso el n?mero de orden que quiero que firmen
		pageAcepta.tipoAccion1(cp, "Visar");
		pageAcepta.estadoEspecificacion1(cp, "Visar con Firma");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]/div[6]/div/span/span[1]/span/ul/li[1]")).getText().contains(firma)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n Visar con Firma OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n Visar con Firma NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0354() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0354";
		System.out.println(cp);
		String notificacion = "Sin notificaciones";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Sin notificaciones");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[7]/div/select/option[1]")).getText().contains(notificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n opci?n: Sin notificaciones OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n opci?n: Sin notificaciones NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0355() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0355";
		System.out.println(cp);
		String notificacion = "Todas";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Todas");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[7]/div/select/option[2]")).getText().contains(notificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n opci?n: Todas notificaciones OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n opci?n: Todas notificaciones NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0356() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0356";
		System.out.println(cp);
		String notificacion = "Finalizado";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Finalizado");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[7]/div/select/option[3]")).getText().contains(notificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Finalizado OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Finalizado NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0357() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0357";
		System.out.println(cp);
		String notificacion = "Firmado";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Firmado");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[7]/div/select/option[4]")).getText().contains(notificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Firmado OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Firmado NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0358() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0358";
		System.out.println(cp);
		String notificacion = "Rechazo";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Rechazo");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[7]/div/select/option[5]")).getText().contains(notificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Rechazo OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Rechazo NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0359() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0359";
		System.out.println(cp);
		String notificacion = "Pendiente de firma";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.seleccionInstitucion(cp, "ACEPTA");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.tipoAccion(cp, "Firmar");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.tipoNotificacion(cp, "Pendiente de firma");
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[7]/div/select/option[6]")).getText().contains(notificacion)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Pendiente de firma OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Validaci?n opci?n: Notificaciones Pendiente de firma NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp);
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0361() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0361";
		System.out.println(cp);
		String etiqueta1 = "pruebaQA1";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.btnAgregar4EtiquetasOpcional(cp, etiqueta1);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
			
				
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div/div[1]/button/span")).getText().contains(etiqueta1)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
//		pageAcepta.btnCrearTipodeDocumento(cp);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0362() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0362";
		System.out.println(cp);
		String etiqueta1 = "pruebaQA2";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.btnAgregar4EtiquetasOpcional(cp, etiqueta1);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		
				
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div/div[1]/button/span")).getText().contains(etiqueta1)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0363() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0363";
		System.out.println(cp);
		String etiqueta1 = "pruebaQA3";
		String etiqueta2 = "pruebaQA4";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnAgregar4EtiquetasOpcional(cp, etiqueta1);
		pageAcepta.btnAgregar5EtiquetasOpcional(cp, etiqueta2);
				
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div/div[2]/button/span")).getText().contains(etiqueta2)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("FLUJO OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("FLUJO NOOK");
			resultado = "FLUJO NOOK";
		}
		pageAcepta.btnCrearTipodeDocumento(cp);
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
		
	}
	
	@Test
	public void Script_0367() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0367";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad5(cp);//Click opci?n Configurar Seguridad: Cualquiera puede compartir y descargar el documento.
		pageAcepta.btnCrearTipodeDocumento(cp);
				
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0368() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0368";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad6(cp);//Click opci?n Configurar Seguridad:  Solo los firmantes pueden compartir y descargar el documento.
		pageAcepta.btnCrearTipodeDocumento(cp);
				
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0369() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0369";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad7(cp);//Click opci?n Configurar Seguridad: Nadie puede compartir ni descargar el documento.
		pageAcepta.btnCrearTipodeDocumento(cp);
				
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	@Test
	public void Script_0370() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0370";
		System.out.println(cp);
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.typeRadioConfigurarSeguridad8(cp);//Click opci?n Configurar Seguridad:  Documento P?blico (Se accede mediante URL).
		pageAcepta.btnCrearTipodeDocumento(cp);
				
		System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed());
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
		
	}
	
	/*
	//CASO 371 REPLICADO CON RICARDO EN CLASE TIPO DE DOCUMENTOS 2
	@Test
	public void Script_0371() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0371";
		System.out.println(cp);
		
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.LinkCrear(cp);
		pageAcepta.LinkSubirArchivo(cp);
		pageAcepta.nombreTipoDocumento(cp, datos[5]);
		pageAcepta.rolCreador(cp, "Admin");
		pageAcepta.rolRut(cp, "Admin");
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con Pin");
		pageAcepta.btnAgregarEtiquetaCampoValor2(cp);
		
		//Validaci?n que se despliega Bloque Etiqueta
		System.out.println(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[3]/div")).isDisplayed()) {
			crearLogyDocumento.CasoOk(cp +"Bloque Etiqueta OK");
			System.out.println("Bloque Etiqueta OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp +"Bloque Etiqueta NOK");
			System.out.println("Bloque Etiqueta NOK");
		}
		
		//Validaci?n Campo
		System.out.println(driver.findElement(By.name("field_value_code_0")).isDisplayed());
		if(driver.findElement(By.name("field_value_code_0")).getAttribute("placeholder").contains("Campo")) {
			crearLogyDocumento.CasoOk(cp +"Campo OK");
			System.out.println("Bloque Campo OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp +"Bloque Campo NOK");
			System.out.println("Bloque Campo NOK");
		}
		
		//Validaci?n Campo Valor por Defecto
		System.out.println(driver.findElement(By.name("field_value_default_0")).isDisplayed());
		if(driver.findElement(By.name("field_value_default_0")).getAttribute("placeholder").contains("Valor por Defecto (opcional)")) {
			crearLogyDocumento.CasoOk(cp +"Bloque Valor por Defecto OK");
			System.out.println("Bloque Valor por Defecto OK");
		}
		else {
			crearLogyDocumento.CasoNok(cp +"Bloque  Valor por Defecto NOK");
			System.out.println("Bloque Valor por Defecto NOK");
		}
		
		//pageAcepta.btnCrearTipodeDocumento(cp);
		
	}
	*/
	
	@Test
	public void Script_0557() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0557";
		System.out.println(cp);
		String titulo = "Editor de Plantilla";
		String subtitulo = "Selecciona el texto para activar el campo din?mico";
		String btnContinuar = "Continuar";
		String nombre = "nombre";
		String fecha = "fecha";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.SeleccionarArchivoEditorPlantilla(cp);
		//pageAcepta.btnContinuarEditorPlantilla(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[1]/h1")).getText().contains(titulo)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Titulo OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Titulo NOOK");
			resultado = "FLUJO NOOK";
		}
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div[5]/div/div/div[2]/div/div[1]/label")).getText().contains(subtitulo)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("SubTitulo OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("SubTitulo NOOK");
			resultado = "FLUJO NOOK";
		}
		
		
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[3]/button[2]")).getText().contains(btnContinuar)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Btn Continuar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Btn Continuar  NOOK");
			resultado = "FLUJO NOOK";
		}
		
		if(driver.findElement(By.xpath("//*[@id=\"variableFields\"]/div[2]/div[1]/div[1]/label")).getText().contains(nombre)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campo Nombre OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Campo Nombre NOOK");
			resultado = "FLUJO NOOK";
		}
		
		if(driver.findElement(By.xpath("//*[@id=\"variableFields\"]/div[4]/div[1]/div[1]/label")).getText().contains(fecha)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Campo Fecha OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Campo Fecha NOOK");
			resultado = "FLUJO NOOK";
		}

		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0558() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0558";
		System.out.println(cp);
			
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.SeleccionarArchivoEditorPlantilla(cp);
		pageAcepta.btnCerrarEditorPlantilla2(cp);
		
//		boolean popUp = driver.findElement(By.xpath("//*[@id=\\\"modal_template\\\"]/div/div/div[1]/h1")).isDisplayed();
//		if(popUp) {                                  
//			crearLogyDocumento.CasoNok(cp + "No se cierra PopUp");
//		}
//		else {
//			crearLogyDocumento.CasoOk(cp + "Se cierra PopUp");
//		}
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[1]/h1")).isDisplayed());
		if(driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[1]/h1")).isDisplayed()) {
			crearLogyDocumento.CasoNok(cp); 
		}
		else {
			crearLogyDocumento.CasoNok(cp);
		}
		
		System.out.println("FLUJO OK");
	}
	
	@Test
	public void Script_0559() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0559";
		System.out.println(cp);
		String msjExito = "se actualiz? correctamente";
		String resultado = null;
			
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.SeleccionarArchivoEditorPlantilla2(cp);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.checkboxValidacionCorreoPersonal(cp);//Cambio que se aplica
		pageAcepta.btnGuardarCambios(cp);
		
			
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText().contains(msjExito)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("CAMBIOS OK");
			resultado = "CAMBIOS OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("CAMBIOS NOOK");
			resultado = "CAMBIOS NOOK";
		}
		
		assertEquals(resultado, "CAMBIOS OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0560() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0560";
		System.out.println(cp);
		String msjExito = "se actualiz? correctamente";
		String resultado = null;
			
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.SeleccionarArchivoEditorPlantilla2(cp);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.btnGuardarCambios(cp);
		
				
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText().contains(msjExito)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("CAMBIOS OK");
			resultado = "CAMBIOS OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("CAMBIOS NOOK");
			resultado = "CAMBIOS NOOK";
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "CAMBIOS OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0561() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0557";
		System.out.println(cp);
		String nameBtn1 = "Opciones";
		String nameBtn2 = "Exportar";
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		String[] datos = leerExcel.ObtenerDatosCP(datapool,cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, datos[1], datos[2]);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageDec5.CambiarEmpresa(cp);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"table-nuevo\"]/tbody/tr[1]/td/div/div/div[3]/div/button")).getText().contains(nameBtn1)&&
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div[2]/div/button")).getText().contains(nameBtn2)){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Bot?n Opciones y Exportar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Bot?n Opciones Y exportar NOOK");
			resultado = "FLUJO NOOK";
		}
		
		
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0562() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0562";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionOcultar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("PopUp Ocultar tipo Documento OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("PopUp Ocultar tipo Documento NOOK");
			resultado = "FLUJO NOOK";
		}
		
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0563() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0563";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionEditar(cp);
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div[5]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("PopUp Editor plantilla OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("PopUp Editor plantilla NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0564() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0564";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionEditar(cp);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"primerPaso\"]")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Vista Tipo de Documento con Plantilla DEC OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Vista Tipo de Documento con Plantilla DEC NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0565() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0565";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionEditar(cp);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.estadoEspecificacion(cp, "Firmar solo con HSM");
		pageAcepta.btnGuardarCambios(cp);
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Vista Editar tipo de Documento OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Vista Editar tipo de Documento NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0566() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0566";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionEditar(cp);
		pageAcepta.btnContinuarEditorPlantilla(cp);
		pageAcepta.btnGuardarCambios(cp);
		
		if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("Vista Editar tipo de Documento OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("Vista Editar tipo de Documento NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0567() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0567";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionClonar(cp);
		
		if(driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div")).isDisplayed()){
			crearLogyDocumento.CasoOk(cp);
			System.out.println("popUp Clonar OK");
			resultado = "FLUJO OK";
		}
		else {
			crearLogyDocumento.CasoNok(cp);
			System.out.println("popUp Clonar NOOK");
			resultado = "FLUJO NOOK";
		}
		
		System.out.println("FLUJO OK");
		assertEquals(resultado, "FLUJO OK", "Se verifica resultado del test "+cp);
	}
	
	@Test
	public void Script_0568() throws InterruptedException, IOException, InvalidFormatException {
		String cp = "DEC_0568";
		System.out.println(cp);
		String resultado = null;
	
		CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
		crearLogyDocumento.CrearEvidencias(cp);
		
		PageDec5 pageDec5 = new PageDec5(driver);
		pageDec5.ClickIngresarLogin(cp);
		
		PageLoginAdm pageLoginAdm = new PageLoginAdm(driver);
		pageLoginAdm.LoginIdentidadDigital(cp, Configuration.USER_RUTH, Configuration.PASS_RUTH);
		
		pageDec5.CambiarEmpresa(cp);
		
		PageAcepta pageAcepta = new PageAcepta(driver);
		pageAcepta.ClickRuedaConfiguracion(cp);
		pageAcepta.OpcionTiposdeDocumentos(cp);
		pageAcepta.btnOpciones(cp);
		pageAcepta.opcionClonar(cp);
		pageAcepta.btnClonar(cp);
		
				
		System.out.println("FLUJO OK");
	}
	
	@AfterMethod
	public void FinEjecucion() {
		driver.close();
	}

}
