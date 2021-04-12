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
	
	public void ColorNoOk (String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name("scheme_color")).clear();
		driver.findElement(By.name("scheme_color")).sendKeys("asd");
		String texto = "Color No Ok";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckNoRegistro (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("scheme_no_registro")).click();
		String texto = "Check No Registro";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckNoHuella (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("scheme_no_finger")).click();
		String texto = "Check No Huella";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckExtranjeros (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("foreign")).click();
		String texto = "Check Extranjeros";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckPlantillaColaborativa (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("collaborative")).click();
		String texto = "Check Plantilla Colaborativa";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckFirmantePorDefinir (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("signer_undefined")).click();
		String texto = "Check Firmante Por Definir";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckEnvioAdjuntoPendiente (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("pending_attachment")).click();
		String texto = "Check Envio Adjunto Pendiente";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckDescripcionCargaBatch (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("doc_name")).click();
		String texto = "Check Descripcion Carga Batch";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckVacacionesRex (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("vacation_request")).click();
		String texto = "Check Vacaciones REX";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckRelacionEntreInstituciones (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("relations_instit")).click();
		String texto = "Check Relacion Entre Instituciones";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckTipoDocumentoExterno (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("external_tipo_doc")).click();
		String texto = "Check Tipo Documento Externo";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckModuloReportes (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("reports_module")).click();
		String texto = "Check Modulo Reportes";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckDescargarVinculados (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("download_related")).click();
		String texto = "Check Descargar Vinculados";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckReenvioDocumentoAprobado (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("send_approve")).click();
		String texto = "Check Reenvio Documento Aprobado";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckCasillaDigital (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("digital_inbox")).click();
		String texto = "Check Casilla Digital";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckMensajes (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("messages")).click();
		String texto = "Check Mensajes";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckPdfConPassword (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("pdf_password")).click();
		String texto = "Check PDF con Password";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckCampoImagenPlantillaDec (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("image_template")).click();
		String texto = "Check Campo Imagen en Plantilla DEC";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckValidadorDocumental (String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("validador_firma")).click();
		String texto = "Check Validador Documental";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void EditarInstitucion(String caso) {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"table-institutions\"]/descendant::a[3]")).click();
				String texto="Click a Editar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible editar Institución");
					i=1;
				}
			}
		}while(i==0);
	}
}