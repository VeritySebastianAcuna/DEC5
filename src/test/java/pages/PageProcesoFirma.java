package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.FechaActual;
import common.Log;

public class PageProcesoFirma {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageProcesoFirma(WebDriver driver) {
		this.driver=driver;
	}
	
	public void IngresoEtiqueta (String caso, String etiqueta) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.name("searchTerm")).sendKeys(etiqueta);
				String texto ="Ingreso Etiqueta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Ingresar Etiqueta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}
	
	public void BuscarEtiqueta (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/div/div/div/button")).click();
				String texto ="Buscar Etiqueta";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Buscar Etiqueta");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
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
				driver.findElement(By.name("folder")).sendKeys(nombre);
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button[2]")).click();
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
	
	public void BtnCancelar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/form/div[3]/button[1]")).click();
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
	
	public void BtnFiltrar (String caso) throws InterruptedException {
		int i=0;
		int j=0;
		do {
			try {
				driver.findElement(By.xpath("//*[@id=\"filteredForm\"]/div[1]/div/div/div/div[3]/div[3]/div[2]/button")).click();
				Thread.sleep(3000);
				String texto ="click Filtrar";
				log.modificarArchivoLog(caso,texto);
				crearDocEvidencia.modificarArchivoEvidencia(caso,texto);
				texto=texto.replace(" ","_");
				capturaPantalla.takeScreenShotTest(driver,texto, caso);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible dar click en Filtrar");
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
}
