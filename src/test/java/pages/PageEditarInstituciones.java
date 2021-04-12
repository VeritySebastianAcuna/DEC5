package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;
import evidence.CrearLogyDocumento;

public class PageEditarInstituciones {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageEditarInstituciones(WebDriver driver) {
		this.driver=driver;
	}
	CrearLogyDocumento crearLogyDocumento = new CrearLogyDocumento(driver);
	
	public String ObtenerRut() {
		String rut = driver.findElement(By.name("rut")).getAttribute("value");
		return rut;
	}
	
	public void EditarRut(String caso, String rut) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name("rut")).clear();
		driver.findElement(By.name("rut")).sendKeys(rut);
		String texto ="Editar Rut";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void ValidarMensajeRut(String caso) throws InvalidFormatException, IOException, InterruptedException {
		String mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div[1]/div/div/div[1]/div[3]/div[3]/div/span")).getText();
		System.out.println(mensaje);
		String texto="Mensaje OK";
		switch(mensaje) {
			case "RUT no válido":
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
			case "El campo RUT debe ser de al menos 9 caracteres de longitud.":
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
			case "El campo RUT no puede superar los 10 caracteres de longitud.":
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
			default:
				texto ="Mensaje NOK";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				break;
		}
	}
	
	public void BtnGuardar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name("submit")).click();
		String texto ="Click Boton Guardar";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void EditarDescripcion(String caso) {
		String texto = driver.findElement(By.name("description")).getText();
		texto = texto+" editado";
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(texto);
	}
	
	public void MensajeEditado(String caso) throws InvalidFormatException, IOException, InterruptedException {
		int i=0;
		int j=0;
		String mensaje="";
		do {
			try {
				mensaje = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/h2")).getText();
				System.out.println(mensaje);
				i=1;
				if(mensaje.contains("se actualizó correctamente")) {
					crearLogyDocumento.CasoOk(caso);
				}
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible obtener el mensaje");
					i=1;
				}
			}
		}while(i==0);
	}
	
	public void EditarMail(String caso) {
		String mail = driver.findElement(By.name("email")).getAttribute("value");
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(mail);
	}
	
	public void EditarMailNotificaciones(String caso) {
		String mail = driver.findElement(By.name("sender")).getAttribute("value");
		driver.findElement(By.name("sender")).clear();
		driver.findElement(By.name("sender")).sendKeys(mail);
	}
	
	public void FlagAceptaNo(String caso) {
		Select flag = new Select (driver.findElement(By.name("flagacepta")));
		flag.selectByValue("0");
	}
	
	public void FlagAceptaSi(String caso) {
		Select flag = new Select (driver.findElement(By.name("flagacepta")));
		flag.selectByValue("1");
	}
	
	public void EditarRazonSocial(String caso) {
		String mail = driver.findElement(By.name("razon")).getAttribute("value");
		driver.findElement(By.name("razon")).clear();
		driver.findElement(By.name("razon")).sendKeys(mail);
	}
	
	public void EditarRubro(String caso) {
		String rubro = driver.findElement(By.name("rubro")).getAttribute("value");
		driver.findElement(By.name("rubro")).clear();
		driver.findElement(By.name("rubro")).sendKeys(rubro);
	}
	
	public void EditarPais(String caso) {
		Select pais = new Select (driver.findElement(By.name("country")));
		pais.selectByValue("CHILE");
	}
	
	public void EditarComuna(String caso) {
		String mail = driver.findElement(By.name("comuna")).getAttribute("value");
		driver.findElement(By.name("comuna")).clear();
		driver.findElement(By.name("comuna")).sendKeys(mail);
	}
	
	public void EditarDireccion(String caso) {
		String mail = driver.findElement(By.name("address")).getAttribute("value");
		driver.findElement(By.name("address")).clear();
		driver.findElement(By.name("address")).sendKeys(mail);
	}
	
	public void EditarColor(String caso) {
		String mail = driver.findElement(By.name("scheme_color")).getAttribute("value");
		driver.findElement(By.name("scheme_color")).clear();
		driver.findElement(By.name("scheme_color")).sendKeys(mail);
	}
	
	public void CheckNoRegistro(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("scheme_no_registro")).click();
		String texto="Check No Registro";
		log.modificarArchivoLog(caso,texto);
		crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
		texto=texto.replace(" ","_");
		capturaPantalla.takeScreenShotTest(driver,texto, caso);
		Thread.sleep(2000);
	}
	
	public void CheckNoHuella(String caso) throws IOException, InvalidFormatException, InterruptedException {
		driver.findElement(By.name("scheme_no_finger")).click();
		String texto="Check No Huella";
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
}
