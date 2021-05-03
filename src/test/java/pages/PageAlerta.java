package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class PageAlerta {

	private WebDriver driver;
	public PageAlerta(WebDriver driver) {
		this.driver=driver;
	}
	
	public void alertaPostDetalle() throws InterruptedException
	{
		Alert alert = driver.switchTo().alert();
		System.out.println("Mensaje: "+alert.getText());
		alert.accept();
		Thread.sleep(2000);
	}
	
	//Función para el manejo de las alertas que no permiten finalizar el flujo
	public void alertaManejoError() throws InterruptedException
	{
		Thread.sleep(2000);
		String mensaje ="";
		try {
			Alert alert = driver.switchTo().alert();
			mensaje = alert.getText();
			switch(mensaje){
				case "Su Sesión será finalizada":
					System.out.println("Mensaje: "+alert.getText());
					alert.accept();
					Thread.sleep(2000);
					System.out.println("Sesión Finalizada por inactividad");
					break;
			}
		}catch(Exception e) {
			System.out.println("No aparece alerta de error");
		}
		Thread.sleep(1000);
	}

}
