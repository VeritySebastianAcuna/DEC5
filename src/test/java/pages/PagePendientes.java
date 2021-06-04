package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.FechaActual;
import common.Log;

public class PagePendientes {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PagePendientes(WebDriver driver) {
		this.driver=driver;
	}
	
	public void BusquedaEtiquetas (String caso, String busqueda) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/input")).sendKeys(busqueda);
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/div/button/span")).click();
				String texto ="Busqueda realizada";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(15000);
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
	
	public void ClickCarpetas (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[1]/div/button")).click();
				Thread.sleep(3000);
				String texto ="click Carpetas";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Carpetas");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}

	public void AgregarCarpetaYaExistente (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				String nombreCarpeta = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]/div/button")).getText();
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/h1/div/a[2]")).click();
				Thread.sleep(1000);
				driver.findElement(By.name("folder")).sendKeys(nombreCarpeta);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button[2]")).click();
				Thread.sleep(1000);
				String texto ="Carpeta Existente";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void CrearNuevaCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/h1/div/a[2]")).click();
				Thread.sleep(1000);
				String texto ="Agregar Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar para agregar carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BtnCancelarNuevaCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button[1]")).click();
				Thread.sleep(1000);
				String texto ="Boton Cancelar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Cancelar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BtnFiltrarCarpetas (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[2]/div[2]/button")).click();
				Thread.sleep(1000);         
				String texto ="Boton Filtrar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Filtrar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void SeleccionarCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/descendant::button[1]")).click();
				Thread.sleep(1000);
				String texto ="Carpeta Seleccionada";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Seleccionar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void LinkEliminarCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/h1/div/a[1]")).click();
				Thread.sleep(1000);
				String texto ="click Link Eliminar Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Link Eliminar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void EliminarCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("eliminarFolder")).click();
				Thread.sleep(1000);
				String texto ="Eliminar Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Eliminar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public int CantidadCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		int cantidad=0;
		do {
			try {
				cantidad = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/div/descendant::button")).size();
				Thread.sleep(1000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible contar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
		return cantidad-2;
	}
	
	public void ClickFiltrosAvanzados (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[2]/div/button")).click();
				Thread.sleep(3000);
				String texto ="click Filtros Avanzados";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Filtros Avanzados");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
		
	public void CambiarEstadoFiltro (String caso, String estadoFiltro) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Select estado = new Select (driver.findElement(By.id("status")));
				switch (estadoFiltro){
					case "PENDIENTE":
						estado.selectByValue("0");
						break;
					case "EN PROCESO DE FIRMA":
						estado.selectByValue("1");
						break;
					case "FIRMADO POR TODOS":
						estado.selectByValue("2");
						break;
					case "RECHAZADO":
						estado.selectByValue("3");
						break;
					case "TODOS":
						estado.selectByValue("10");
						break;
					default:
						System.out.println("Estado inválido");
						break;
				}
				Thread.sleep(3000);
				String texto ="Seleccion Estado Filtros Avanzados";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible seleccionar Estado en Filtros Avanzados");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BtnFiltrarFiltrosAvanzados (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"filteredForm\"]/div[1]/div/div/div/div[3]/div[3]/div[2]/button")).click();
				Thread.sleep(1000);
				String texto ="Boton Filtrar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Filtrar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(6000);
	}
	
	public void FechaDesde (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				FechaActual fechaActual = new FechaActual();
				String fecha = fechaActual.FechaDesde();
				driver.findElement(By.name("from")).sendKeys(fecha);
				Thread.sleep(1000);
				String texto ="Ingreso Fecha Desde";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Fecha Desde");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void FechaHasta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				FechaActual fechaActual = new FechaActual();
				String fecha = fechaActual.FechaHoy();
				driver.findElement(By.name("to")).sendKeys(fecha);
				Thread.sleep(1000);
				String texto ="Ingreso Fecha Hasta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Fecha Hasta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void TipoDocumento (String caso, String tipoDoc) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("tipoDctoSel")).sendKeys(tipoDoc);
				Thread.sleep(1000);
				String texto ="Tipo Documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Tipo Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public String MensajeSinResultados (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		String mensaje = "";
		do {
			try {
				mensaje = driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::td[1]")).getAttribute("class");
				Thread.sleep(1000);
				String texto ="Mensaje Sin Resultados";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible obtener mensaje");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
		return mensaje;
	}
	
	public void BtnMostrarOcultos (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[3]/div/button")).click();
				Thread.sleep(1000); 
				String texto ="Boton Mostrar Ocultos";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en botón Mostrar Ocultos");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(10000);
	}
	
	public void ClickPrimerRegistro (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::input[1]")).click();
				Thread.sleep(1000);
				String texto ="Seleccion Primer Registro";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en primer registro");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(5000);
	}
	
	public void ClickRegistroPorParametro (String caso, int registro) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				int hijos = driver.findElements(By.xpath("//*[@id=\"table-documentos\"]/descendant::input")).size();
				if(registro<=hijos){
					driver.findElement(By.xpath("//*[@id=\"table-documentos\"]/descendant::input["+registro+"]")).click();
					Thread.sleep(1000);
					String texto ="Seleccion Registro";
					log.modificarArchivoLog(caso,texto);
					crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
					texto=texto.replace(" ","_");
					capturaPantalla.takeScreenShotTest(driver,texto, caso);
					i=1;
				}
				else {
					System.out.println("Sin Dato");
				}
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en registro");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(5000);
	}
	
	public void BarraHerramientas (String caso, String opcion) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				int hijos = driver.findElements(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button")).size();
				int h=1;
				int g=0;
				do {
					System.out.println(driver.findElement(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button["+h+"]")).getText());
					if(driver.findElement(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button["+h+"]")).getText().equals(opcion)) {
						driver.findElement(By.xpath("//*[@id=\"barra_herramientas\"]/descendant::button["+h+"]")).click();
						h=hijos;
					}
					else {
						g++;
						if(g>hijos) {
							System.out.println("No se encuentra opción");
							h=hijos;
						}
					}
					h++;
				}while(h<hijos);
				Thread.sleep(3000);
				String texto ="Seleccionar Opcion Barra Herramientas";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Seleccionar Opcion");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(4000);
	}
	
	public void MoverA (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[2]/div/form/div/descendant::input[1]")).click();
//				System.out.println(driver.findElement(By.xpath("//*[@id=\"checkMover_580\"]")).getText());
				Thread.sleep(1000);
				String texto ="Seleccion Carpeta Destino";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en carpeta destino");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnMover (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("move-folder")).click();
				Thread.sleep(1000);
				String texto ="click Boton Mover";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en boton Mover");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void AgregarCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/h3/span/a")).click();
				Thread.sleep(1000);
				String texto ="click Agregar Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Agregar Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnCrearCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("id-add-folder")).click();
				Thread.sleep(1000);
				String texto ="click Boton Crear Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Crear Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CrearCarpeta (String caso, String nombre) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/div/div/form/input[2]")).sendKeys(nombre);
				driver.findElement(By.id("move-folder-create")).click();
				Thread.sleep(1000);
				String texto ="Crear Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Crear Carpeta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CerrarMoverCrearCarpeta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				String texto ="Cerrar Crear Mover Carpeta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[1]/button")).click();
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Cerrar Ventana");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void AgregarEtiquetas (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[2]/div/div[2]/a")).click();
				Thread.sleep(1000);
				String texto ="Agregar Etiquetas";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Agregar Etiquetas");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CerrarAgregarEtiquetas (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				String texto ="Cerrar Agregar Etiquetas";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[1]/button")).click();
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Cerrar Ventana");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CerrarVincularDocumento (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[1]/button")).click();
				String texto ="Cerrar Vincular Documento"; 
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
					System.out.println("No fue posible Cerrar Ventana");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void DesvincularDocumento (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
//				System.out.println(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[6]/div/table/tbody/tr/td[4]/a")).getText());
//				System.out.println(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[6]/div/table/tbody/tr/td[4]/a")).getTagName());
//				System.out.println(driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[6]/div/table/tbody/tr/td[4]/a")).getAttribute("href"));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[6]/div/table/tbody/tr/td[4]/a")).click();
				Thread.sleep(1000);
				String texto ="Desvincular Documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Desvincular Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnSiDesvincularDocumento (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/button[1]")).click();
				Thread.sleep(1000);
				String texto ="SI Desvincular Documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Si Desvincular Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnCerrarDesvincularDocumento (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/button")).click();
				Thread.sleep(1000);
				String texto ="Cerrar Desvincular Documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Cerrar Desvincular Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnNoDesvincularDocumento (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[3]/button[2]")).click();
				Thread.sleep(1000);
				String texto ="NO Desvincular Documento";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en No Desvincular Documento");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnCrearComentario (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"details-doc\"]/div/div[8]/div/div[2]/div/button")).click();;
				Thread.sleep(1000);
				String texto ="click Crear comentario";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Crear Comentario");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void IngresarComentario (String caso, String comentario) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("comment-text")).sendKeys(comentario);;
				Thread.sleep(1000);
				String texto ="Ingreso de comentario";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Comentario");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnComentar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("comment-submit")).click();;
				Thread.sleep(1000);
				String texto ="click Comentar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Comentar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void BtnCancelar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("comment-cancel")).click();;
				Thread.sleep(1000);
				String texto ="click Cancelar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Cancelar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CompartirDocumentoTipoPersona (String caso, String persona) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Select tipoPersona = new Select (driver.findElement(By.name("option")));
				switch (persona){
					case "PERSONA NATURAL":
						tipoPersona.selectByValue("PN");
						break;
					case "ACEPTA":
						tipoPersona.selectByValue("ACEPTA");
						break;
					default:
						System.out.println("Estado inválido");
						break;
				}
				String texto ="Seleccion Tipo Persona";	
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Seleccionar Tipo Persona");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CompartirDocumentoRut (String caso, String rut) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("searchUser")).sendKeys(rut);
				driver.findElement(By.id("searchUser")).sendKeys(Keys.TAB);
				String texto ="Ingreso Rut";	
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Buscar Rut");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(5000);
	}
	
	public void CompartirDocumentoBtnAgregar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[4]/button")).click();
				String texto ="click Boton Agregar";	
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Agregar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(5000);
	}
	
	public void CompartirDocumentoIngresarMail (String caso, String mail) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[2]/div[1]/div[3]/input[2]")).sendKeys(mail);
				String texto ="Ingreso Mail";	
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Mail");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(5000);
	}
	
	public void CompartirDocumentoSeleccionRol (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				int hijos= driver.findElements(By.xpath("")).size();
				String[] cod_prod = new String[hijos];
				for(int h=1;h<=hijos;h++) {
					cod_prod[h-1]=driver.findElement(By.xpath("//*[@id=\\\"modal\\\"]/div/div/form/div[2]/div[1]/div[3]/select/descendant::option["+h+"]")).getAttribute("value");					
				}
				int c=(int)(Math. random()*hijos+0);
				Select tipoRol = new Select (driver.findElement(By.xpath("//*[@id=\\\\\\\"modal\\\\\\\"]/div/div/form/div[2]/div[1]/div[3]/select")));
				tipoRol.selectByValue(cod_prod[c]);
				String texto ="Seleccion Rol";	
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Seleccionar Rol");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void CompartirDocumentoBtnCompartir (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.id("submitCompartir")).click();
				Thread.sleep(1000);
				String texto ="click Boton Compartir";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Compartir");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void OcultarDocumentoBtnCancelar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div/div[3]/button[2]")).click();
				Thread.sleep(1000);
				String texto ="click Boton Cancelar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Cancelar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void TrazaBtnClose (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"bts2\"]/button[2]")).click();
				Thread.sleep(1000);
				String texto ="click Boton Close";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Close");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(2000);
	}
	
	public void TrazaBtnDescargaPdf (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"bts2\"]/button[1]")).click();
				Thread.sleep(1000);         
				String texto ="click Boton Descarga PDF";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Descarga PDF");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void RechazarDocumentoConClave (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[1]")).click();
				Thread.sleep(1000);          
				String texto ="click Boton Rechazar con Clave";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Rechazar con Clave");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void BtnRechazarDocumentoConClave (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[3]")).click();
				Thread.sleep(1000);          
				String texto ="click Boton Rechazar con Clave";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Rechazar con Clave");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
		
	public void RechazarDocumentoComentario (String caso, String comentario) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[2]/div[2]/div/div/textarea")).sendKeys(comentario);
				Thread.sleep(1000);
				String texto ="Ingreso de Comentario";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Comentario");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void RechazarDocumentoIngresoClave (String caso, String pass) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.id("password")).sendKeys(pass);
				Thread.sleep(1000);
				String texto ="Ingreso de Clave";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Clave");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void RechazarDocumentoBtnRechazar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.id("firma_boton2")).click();
				Thread.sleep(1000);
				String texto ="Click Boton Rechazar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Rechazar");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void RechazarDocumentoConHuella (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[2]")).click();
				Thread.sleep(1000);
				String texto ="Click Boton Rechazar con Huella";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Rechazar con HUella");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void BtnAutorizarIdentidadDigital(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
				Thread.sleep(1000);          
				String texto ="Click Boton Autorizar Identidad Digital";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Autorizar de Identidad Digital");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void BtnFirmarIdentidadDigital(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[1]")).click();
				Thread.sleep(1000);          
				String texto ="Click Boton Firmar Identidad Digital";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Firmar de Identidad Digital");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void BtnFirmarEtoken(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"actionForm\"]/div[3]/button[3]")).click();
				Thread.sleep(1000);          
				String texto ="Click Boton Firma E-token";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Firma E-token");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void BtnSalirIdentidadDigital(String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("//*[@id=\"accordionExample\"]/section/button/div")).click();
				Thread.sleep(1000);
				String texto ="Click Boton Salir Identidad Digital";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Boton Salir de Identidad Digital");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void claveRechazoDocumento (String caso, String pass) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.id("password")).sendKeys(pass);
				Thread.sleep(1000);
				String texto ="Ingreso de Clave Incorrecta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible ingresar Clave Incorrecta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
	public void linkVerPendientes (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				Thread.sleep(4000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a")).click();
				Thread.sleep(1000);
				String texto ="Click Link Ir a Ver mis pendientes";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Link Ir a Ver mis pendientes");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(8000);
	}
	
}
