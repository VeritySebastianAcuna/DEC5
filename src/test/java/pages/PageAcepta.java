package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.Configuration;
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
					driver.switchTo().frame(0);//SwitchTo().frame permite ingresar y reconocer el iframe
					driver.findElement(By.xpath("/html/body/p")).sendKeys(txtEditor);
					Thread.sleep(3000);
					driver.switchTo().defaultContent();//SwitchTo().defaultContent permite salir del iframe y continuar flujo
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
		
		public void CerrarEditorPlantilla(String caso) throws IOException, InvalidFormatException, InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modal_template\"]/div/div/div[1]/button/span")).click();
					Thread.sleep(3000);
					String texto ="Cerrar Editor Plantilla";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible cerrar Editor Plantilla");
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
					//Thread.sleep(4000);
					driver.findElement(By.name("name")).sendKeys(tipoDoc);
					//Thread.sleep(2000);
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
			Thread.sleep(1000);
		}
		
		public void rolCreador (String caso, String rolCreador) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Select rol = new Select (driver.findElement(By.cssSelector("select[name='cmb_rol_creador']")));
					Select rol = new Select (driver.findElement(By.id("cmb_rol_creador")));
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
			Thread.sleep(2000);
		}
		
		public void rolRut (String caso, String rolRut) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Select rol = new Select (driver.findElement(By.cssSelector("select[name='signer_role_0']")));
					Select rol = new Select (driver.findElement(By.name("signer_role_0")));
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
			Thread.sleep(1000);
		}
		
			
		public void rolRut1 (String caso, String rolRut) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Select rol = new Select (driver.findElement(By.cssSelector("select[name='signer_role_1']")));
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
					//Select rol = new Select (driver.findElement(By.cssSelector("select[name='signer_num_0']")));
					Select rol = new Select (driver.findElement(By.name("signer_num_0")));
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
			Thread.sleep(2000);
		}
		
		public void orden (String caso, String orden) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Thread.sleep(2000);
					driver.findElement(By.name("order_0")).clear();
					driver.findElement(By.name("order_0")).sendKeys(orden);
					//Thread.sleep(2000);
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
			Thread.sleep(1000);
		}
		
		public void tipoAccion (String caso, String rolRut) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Select rol = new Select (driver.findElement(By.cssSelector("select[name='tipoaccion_0']")));
					Select rol = new Select (driver.findElement(By.name("tipoaccion_0")));
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
			Thread.sleep(1000);
		}
		
		public void estadoEspecificacion (String caso, String especificacion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div/div[6]/div/span[1]/span[1]/span/ul/li/input")).click();
					Thread.sleep(1000);          
					driver.findElement(By.xpath("//*[text()= '"+ especificacion + "']")).click();//para enviar un texto a un campo y lo puedan seleccionar
					Thread.sleep(1000); 
					String texto ="Seleccion Especificación";
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
			Thread.sleep(2000);
		}
		
		public void estadoEspecificacion1 (String caso, String especificacion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[3]/div[2]/div[6]/div/span[1]/span[1]/span/ul/li/input")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[text()= '"+ especificacion + "']")).click();//para enviar un texto a un campo y lo puedan seleccionar
					String texto ="Seleccion Especificación";
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
					//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			Thread.sleep(2000);
		}
		
		public void btn2CrearTipodeDocumento(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.id("crearBotonTemplate")).click();
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
			Thread.sleep(2000);
		}
		
		public void checkboxRecibirNotificaciones (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
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
			Thread.sleep(2000);
		}
		
		public void checkboxTituloDocumentoIgualNombreArchivo (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					Thread.sleep(4000);
					driver.findElement(By.name("ckb_titulo_file")).click();
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
					//Select institucionList = new Select (driver.findElement(By.className("signer-institution")));
					Select institucionList = new Select (driver.findElement(By.name("signer_institution_0")));
					institucionList .selectByVisibleText(institucion);
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
			Thread.sleep(2000);
		}
		
		
		public void tipoNotificacion(String caso, String notificacion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Select rol = new Select (driver.findElement(By.cssSelector("select[name='notificar_0']")));
					Select rol = new Select (driver.findElement(By.name("notificar_0")));
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
			Thread.sleep(1000);
		}
		
		public void btnAgregar(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[4]/div[2]/div/div[10]/button")).click();
					String texto="Click botón Agregar";
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
						System.out.println("No fue posible dar click en botón Agregar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAgregar1EtiquetasOpcional(String caso, String etiqueta) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div[2]/div[1]/button")).click();
					Thread.sleep(1000);                   
					driver.findElement(By.id("newTag")).sendKeys(etiqueta);
					driver.findElement(By.id("newTag")).sendKeys(Keys.ENTER);
					Actions actions = new Actions(driver);
					String texto="Click botón Agregar Etiquetas (opcional)"; 
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
						System.out.println("No fue posible dar click en botón Agregar Etiquetas (opcional)");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAgregar2EtiquetasOpcional(String caso, String etiqueta) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div[2]/div[2]/button")).click();
					Thread.sleep(1000);                   
					driver.findElement(By.id("newTag")).sendKeys(etiqueta);
					driver.findElement(By.id("newTag")).sendKeys(Keys.ENTER);
					Actions actions = new Actions(driver);
					String texto="Click botón Agregar Etiquetas (opcional)"; 
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
						System.out.println("No fue posible dar click en botón Agregar Etiquetas (opcional)");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAgregar3EtiquetasOpcional(String caso, String etiqueta) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[2]/div/div[2]/div[3]/button")).click();
					Thread.sleep(1000);                   
					driver.findElement(By.id("newTag")).sendKeys(etiqueta);
					driver.findElement(By.id("newTag")).sendKeys(Keys.ENTER);
					Actions actions = new Actions(driver);
					String texto="Click botón Agregar Etiquetas (opcional)"; 
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
						System.out.println("No fue posible dar click en botón Agregar Etiquetas (opcional)");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAgregarEtiquetaCampoValor(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div[3]/button")).click();
					String texto="Click botón Agregar Etiqueta Campo/Valor"; 
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
						System.out.println("No fue posible dar click en botón Agregar Etiqueta Campo/Valor");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAceptarListaValoresPopup(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).click();
					String texto="Click botón Aceptar popup";
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
						System.out.println("No fue posible dar click en botón Aceptar del popUp Lista de Valores");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void typeRadioConfigurarSeguridad1 (String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/input[1]")).click();
					String texto="Click opción: Cualquiera puede compartir y descargar el documento."; 
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
						System.out.println("No fue posible dar click radio button");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void typeRadioConfigurarSeguridad2 (String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/input[2]")).click();
					String texto="Click opción: Solo los firmantes pueden compartir y descargar el documento."; 
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
						System.out.println("No fue posible dar click radio button");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void typeRadioConfigurarSeguridad3 (String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/input[3]")).click();
					String texto="Click opción:  Nadie puede compartir ni descargar el documento."; 
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
						System.out.println("No fue posible dar click radio button");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void typeRadioConfigurarSeguridad4 (String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/input[4]")).click();
					String texto="Click opción:  Documento Público (Se accede mediante URL)."; 
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
						System.out.println("No fue posible dar click radio button");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void campoEtiqueta (String caso, String tipoDoc) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("field_value_code_0")).sendKeys(tipoDoc);
					Thread.sleep(2000);
					String texto ="Ingreso campo etiqueta";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar campo etiqueta");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void valorEtiqueta (String caso, String tipoDoc) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("field_value_default_0")).sendKeys(tipoDoc);
					Thread.sleep(2000);
					String texto ="Ingreso campo etiqueta";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar campo etiqueta");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void configuracionEtiqueta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.name("config-0")).click();
					String texto="Click Rueda configuración Etiqueta"; 
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
						System.out.println("No fue posible dar Click Rueda configuración Etiqueta");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void listaValores (String caso, String valor) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("op-val")).sendKeys(valor);
					Thread.sleep(2000);
					String texto ="Ingreso lista Valores";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar valores");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		public void btnAgregarConfg(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).click();
					String texto="Click botón Agregar";
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
						System.out.println("No fue posible dar click en botón Agregar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void campoDescripcionDocumento (String caso, String valor) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("desc")).sendKeys(valor);
					String texto ="Ingreso descripción del Documento (opcional)";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar texto en descripción del Documento (opcional)");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(8000);
		}
		
		
		// Ricardo
		
		public void btnAgregarEtiquetasCampoValor(String caso, String campo, String valor) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div[3]/button")).click();
					Thread.sleep(1000);                   
					driver.findElement(By.name("field_value_code_0")).sendKeys(campo);
					driver.findElement(By.name("field_value_default_0")).sendKeys(valor);
					//Actions actions = new Actions(driver);
					String texto="Click botón Agregar Etiquetas (opcional)"; 
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
						System.out.println("No fue posible Agregar Etiquetas (opcional)");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAdd(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.id("crearBoton")).click();
					String texto="Click botón Crear Tipo Documento"; 
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
						System.out.println("No fue posible click botón Crear Tipo Documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void IconoConfiguracion(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("config-0")).click();
					String texto="Click icono Configuración"; 
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
						System.out.println("No fue posible click icono Configuración");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void IngresarValorListaValores(String caso, String valor) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("op-val")).sendKeys(valor);
					driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).click();
					String texto="Ingresar Valor"; 
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
						System.out.println("Ingresar Valor");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void IngresarDescripcionDoc (String caso, String descripcion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Thread.sleep(4000);
					driver.findElement(By.name("desc")).sendKeys(descripcion);
					//Thread.sleep(2000);
					String texto ="Ingreso descripción";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar descripción");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(2000);
		}
		
		public void btnAdd2(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"crearBoton\"]")).click();
					String texto="Click botón Crear Tipo Documento"; 
					log.modificarArchivoLog(caso, texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
					texto=texto.replace(" ", "_");
					capturaPantalla.takeScreenShotTest(driver, texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible click botón Crear Tipo Documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(1000);
		}
		
		public void LinkPlantillaPdf(String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.partialLinkText("Plantilla PDF")).click();
					Thread.sleep(1000);
					String texto ="click Link Plantilla PDF";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en Link Plantilla PDF");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(2000);
		}
		
		public void SeleccionarArchivoEquipo (String caso, String archivo) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					//Thread.sleep(4000);
					driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input")).sendKeys(archivo);
					//Thread.sleep(2000);
					String texto ="Ingreso archivo";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar archivo");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(2000);
		}
		
		public void ClickCheckCargarSinEditar (String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
	
					driver.findElement(By.id("make-changes")).click();
					String texto ="Ingreso archivo";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible ingresar archivo");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(2000);
		}

		public void btnCerrarEditorPlantillaEditorPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modalTemplateEditorPDF\"]/div/div/div[3]/button[1]")).click();
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
			Thread.sleep(2000);
		}
		
		public void cerrarPopUp(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button/span")).click();
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
			Thread.sleep(1000);
		}
		
		public void BtnAbrirEditor(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.id("open-editor")).click();
					String texto="Click en botón Abrir Editor"; 
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
						System.out.println("No fue posible dar click en Abrir Editor");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(2000);
		}
		
		public void estadoEspecificacionPlantillaPdf (String caso, String especificacion) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[5]/div[3]/div/div[6]/div/span/span[1]/span/ul/li/input")).click();
					Thread.sleep(1000);          
					driver.findElement(By.xpath("//*[text()= '"+ especificacion + "']")).click();//para enviar un texto a un campo y lo puedan seleccionar
					Thread.sleep(1000); 
					String texto ="Seleccion Especificación";
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
			Thread.sleep(2000);
		}
		
		
		public void btnContinuarEditorPlantillaPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modalTemplateEditorPDF\"]/div/div/div[3]/button[2]")).click();
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
		
		public void btnAdd3(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.id("enviarBoton")).click();
					String texto="Click botón Crear Tipo Documento"; 
					log.modificarArchivoLog(caso, texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso, texto);
					texto=texto.replace(" ", "_");
					capturaPantalla.takeScreenShotTest(driver, texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible click botón Crear Tipo Documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(1000);
		}
		
		public void btnAgregar_PlantillaPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[1]/button")).click();
					String texto="Click botón Agregar";
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
						System.out.println("No fue posible dar click en botón Agregar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		
		public void IngresarEtiqueta(String caso, String etiqueta) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.id("newTag")).sendKeys(etiqueta);
					Thread.sleep(2000);
					driver.findElement(By.id("newTagButton")).click();
					String texto="Ingresar Etiqueta";
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
						System.out.println("No fue posible Ingresar Etiquet");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void EliminarEtiqueta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[6]/div[2]/div/div/div[1]/span")).click();
					String texto="Click botón Eliminar";
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
						System.out.println("No fue posible dar click en botón Eliminar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		
		public void checkboxCualquieraPuedeCompartir(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[1]")).click();
					String texto="Click checkbox Cualquiera puede compartir y descargar el documento";
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
						System.out.println("No fue posible dar Click checkbox Cualquiera puede compartir y descargar el documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxSoloFirmantes(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[2]")).click();
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxNadiePuedeCompartir(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[3]")).click();
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxDocumentoPublico(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[4]")).click();
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void EditorPlantillaDobleClic(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					System.out.println("ingresando doble clic");
					/*
					driver.findElement(By.xpath("/html/body/p")).click();
					driver.findElement(By.xpath("/html/body/p")).click();
					*/
					
					/*
					Actions action = new Actions(driver);
					WebElement link = driver.findElement(By.xpath("/html/body/p"));
					action. doubleClick (link).perform();
					*/
					Actions actions = new Actions(driver);
					WebElement elementLocator = driver.findElement(By.xpath("/html/body/p"));
					actions.doubleClick(elementLocator).perform();
					
					
					driver.findElement(By.partialLinkText("Rut")).click();
					
					
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		
		public void btnAgregarEtiquetaCampoValor_PlantillaPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div[1]/div[8]/div[2]/div/div[3]/button")).click();
			Thread.sleep(3000);
		}
		
		public void IngresarCampoValor(String caso, String campo, String valor) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("field_value_code_0")).sendKeys(campo);
					Thread.sleep(2000);
					driver.findElement(By.name("field_value_default_0")).sendKeys(valor);
					Thread.sleep(2000);
					String texto="Ingresar Campo Valor";
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
						System.out.println("No fue posible Ingresar Campo Valor");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void ClickConfiguracionEtiqueta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("config-0")).click();
					Thread.sleep(2000);
					String texto="Click rueda configuración";
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
						System.out.println("No fue posible Click rueda configuración");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		
		public void IngresarCamposConfiguracionEtiqueta(String caso, String valor) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.name("op-val")).sendKeys(valor);
					Thread.sleep(2000);
					
					String texto="Ingresar Campo Valor Configuración";
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
						System.out.println("No fue posible Ingresar Campo Valor Configuración");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void ClickAgregarConfiguracionEtiqueta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[2]/button")).click();
					Thread.sleep(2000);
					String texto="Click Agregar";
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
						System.out.println("No fue posible Click Agregar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void ClickAceptarConfiguracionEtiqueta(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button")).click();
					Thread.sleep(2000);
					String texto="Click Aceptar";
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
						System.out.println("No fue posible Click Aceptar");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		
		public void btnCerrarEditorPdf(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"modalTemplateEditorPDF\"]/div/div/div[1]/button")).click();
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
			Thread.sleep(2000);
		}
		
		
		public void LinkPlantillaColaborativa(String caso) throws InterruptedException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.partialLinkText("Plantilla Colaborativa")).click();
					Thread.sleep(1000);
					String texto ="click Link Plantilla Colaborativa";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}catch (Exception e) {
					// TODO: handle exception
					j++;
					if(j==3) {
						System.out.println("No fue posible dar click en Link Plantilla Colaborativa");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(2000);
		}
		
		public void checkboxCualquieraPuedeModificarTag(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[1]")).click();
					String texto="Click checkbox Cualquiera puede modificar los Tags";
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
						System.out.println("No fue posible dar Click checkbox  Cualquiera puede modificar los Tags");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxSoloCreadorTagModificar(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[7]/div[2]/div/div/input[2]")).click();
					String texto="Click checkbox Cualquiera puede modificar los Tags";
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
						System.out.println("No fue posible dar Click checkbox  Cualquiera puede modificar los Tags");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		
		public void checkboxCualquieraPuedeCompartir2(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[1]")).click();
					String texto="Click checkbox Cualquiera puede compartir y descargar el documento";
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
						System.out.println("No fue posible dar Click checkbox Cualquiera puede compartir y descargar el documento");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxSoloFirmantes2(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[2]")).click();
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxNadiePuedeCompartir2(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[3]")).click();
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void checkboxDocumentoPublico2(String caso) throws InterruptedException, IOException, InvalidFormatException {
			int i=0;
			int j=0;
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"primerPaso\"]/div[8]/div[2]/div/div/input[4]")).click();
					String texto="Click checkbox Solo firmantes";
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
						System.out.println("No fue posible dar Click checkbox Solo Firmantes");
						i=1;
					}
				}
			}while(i==0);
			Thread.sleep(3000);
		}
		
		public void btnAgregarEtiquetaCampoValor_PlantillaColaborativa(String caso) throws InterruptedException, IOException, InvalidFormatException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div/div[1]/div[9]/div[2]/div/div[3]/button")).click();
			Thread.sleep(3000);
		}
		
		
	}

