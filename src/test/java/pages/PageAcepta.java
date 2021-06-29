package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

	public class PageAcepta {
		private WebDriver driver;
		Log log = new Log();
		CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
		CapturaPantalla capturaPantalla = new CapturaPantalla();
		public PageAcepta(WebDriver driver) {
			this.driver=driver;
		}

				
		public void empresaAcepta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[1]/div/ul/li[2]/a")).click();
					String texto="Click empresa ACEPTA";
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
						System.out.println("No fue posible realizar Busqueda");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
						
		public void ClickRuedaConfiguracion(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/button")).click();
					String texto="Click a Configuraciones"; 
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
						System.out.println("No fue posible realizar Busqueda");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void OpcionInstituciones(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/ul/li[2]/a")).click();
					String texto ="Click a Instituciones";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible realizar Busqueda");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void OpcionTiposdeDocumentos(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/div/ul/li[3]/div/ul/li[3]/a")).click();
					String texto ="Click Tipos de Documentos";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en Tipo de Documentos");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void seleccionEmpresaBusqueda ( String caso, String empresa) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException {
			int i=0;
			int j=0;
			do {
				try {
					Select tipodeFirmante = new Select (driver.findElement(By.id("origin")));
					switch (empresa){
					case "ACEPTA":
						tipodeFirmante.selectByValue("1");
						break;
					case "Otras Instituciones":
						tipodeFirmante.selectByValue("2");
						break;
					}
					Thread.sleep(2000);
					log.modificarArchivoLog(caso, "empresa: "+empresa);
					crearDocEvidencia.modificarArchivoEvidencia(caso, "Seleccion empresa Busqueda");
					capturaPantalla.takeScreenShotTest(driver, "Seleccion empresa búsqueda", caso);
					i=1;
				} catch (Exception e){
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar empresa en Selección Búsqueda");
						i=1;
					}
				}
			}while(i==0);
		}
		
		public void btnIconoBuscar(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"searchDocsTypes\"]/div[2]/div/div/div/button")).click();
					Thread.sleep(1000);
					String texto ="Click ícono buscar";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en ícono buscar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void inputBuscar(String caso, String buscarEmpresa) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"searchDocsTypes\"]/div[2]/div/div/input")).sendKeys(buscarEmpresa);
					Thread.sleep(1000);
					String texto ="Ingreso Empresa en campo buscar";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar empresa en campo buscar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkIncluirOcultos(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.id("check-inactive")).click();
					Thread.sleep(1000);
					String texto ="Check Incluir Ocultos";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible hacer Check Incluir Ocultos");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void LinkCrear (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.partialLinkText("Crear")).click();
					Thread.sleep(1000);
					String texto ="click Link Crear";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible click Crear");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void LinkPlantillaDEC(String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.partialLinkText("Plantilla DEC")).click();
					Thread.sleep(1000);
					String texto ="click Link Plantilla DEC";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en Link plantilla DEC");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void LinkSubirArchivo(String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.partialLinkText("Subir un archivo")).click();
					Thread.sleep(1000);
					String texto ="click Link Subir Archivo";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en Link Subir Archivo");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void EditorPlantilla(String caso, String txtEditor) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("/html/body/p")).sendKeys(txtEditor);
					Thread.sleep(1000);          
					String texto ="Ingreso texto Editor Plantilla";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar texto Editor Plantilla");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnContinuarEditorPlantilla(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[3]/button[2]")).click();
					String texto="Click en Continuar"; 
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
						System.out.println("No fue posible dar click en Continuar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnCerrarEditorPlantilla(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[3]/button[1]")).click();
					String texto="Click en botón Cerrar"; 
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
						System.out.println("No fue posible dar click en botón cerrar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void nombreTipoDocumento (String caso, String tipoDoc) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("name")).sendKeys(tipoDoc);
					Thread.sleep(2000);
					String texto ="Ingreso nombre tipo de documento";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar Nombre tipo de documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void rolCreador (String caso, String rolCreador) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='cmb_rol_creador']")));
					rol.selectByVisibleText(rolCreador); 
					String texto ="Seleccion Rol Creador";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar Rol Creador");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void rolRut (String caso, String rolRut) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='signer_role_0']")));
					rol.selectByVisibleText(rolRut); 
					String texto ="Seleccion Rol Rut";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar Rol Rut");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void tipoFirma (String caso, String firma) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='signer_num_0']")));
					rol.selectByVisibleText(firma); 
					String texto ="Seleccion tipo de firma";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar tipo de firma");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void orden (String caso, String orden) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(2000);
					driver.findElement(By.name("order_0")).clear();
					driver.findElement(By.name("order_0")).sendKeys(orden);
					Thread.sleep(2000);
					String texto ="Ingreso orden de firma";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar orden de firma");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void tipoAccion (String caso, String rolRut) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='tipoaccion_0']")));
					rol.selectByVisibleText(rolRut); 
					String texto ="Seleccion Tipo de Accion";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar Tipo de Accion");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void estadoEspecificacion (String caso, String especificacion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span/span[1]/span")).sendKeys(especificacion);
					Thread.sleep(2000);          
					String texto ="Ingreso estado Especificación";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar estado Especificación");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void btnCrearTipodeDocumento(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.id("crearBoton")).click();
					String texto="Click botón Crear Tipo de Documento"; 
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
						System.out.println("No fue posible dar click en botón Crear Tipo de Documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxPermitirAgregarFirmantes (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_new_firm")).click();
					Thread.sleep(2000);
					String texto ="CheckBox Permitir Agregar más Firmantes";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox Permitir Agregar más Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void checkboxRecibirNotificaciones (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_notificar_creador")).click();
					Thread.sleep(2000);
					String texto ="CheckBox Recibir Notificaciones";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox Recibir Notificaciones");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void checkboxTituloDocumentoIgualNombreArchivo (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_new_firm")).click();
					Thread.sleep(2000);
					String texto ="CheckBox Título Documento Igual Nombre Archivo";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox Título Documento Igual Nombre Archivo");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void checkboxVisualizacionOrdenFirma (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_visualizacion_x_orden_firma")).click();
					Thread.sleep(2000);
					String texto ="CheckBox Visualización según Orden de Firma";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox Visualización según Orden de Firma");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void checkboxEnviarBotonFirmaCorreoPendienteFirma (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_boton_firma")).click();
					Thread.sleep(2000);
					String texto ="CheckBox Enviar Botón de Firma en Correo Pendiente de Firma";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox Enviar Botón de Firma en Correo Pendiente de Firma");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void checkboxPDFconPassword (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_pdf_password")).click();
					Thread.sleep(2000);
					String texto ="CheckBox PDF con Password";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox PDF con Password");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void checkboxValidacionCorreoPersonal (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_validacion_correo_personal")).click();
					Thread.sleep(2000);
					String texto ="CheckBox Validación Correo Personal para Notificar";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click CheckBox Validación Correo Personal para Notificar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void seleccionInstitucion (String caso, String institucion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='signer_institution_0']")));
					rol.selectByVisibleText(institucion); 
					String texto ="Seleccion Institución";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar Institución");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void tipoNotificacion(String caso, String notificacion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='notificar_0']")));
					rol.selectByVisibleText(notificacion); 
					String texto ="Seleccion Notificación";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible seleccionar Notificación");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
	}

