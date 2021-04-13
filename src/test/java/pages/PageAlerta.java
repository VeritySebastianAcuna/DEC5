package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class PageAlerta {
	private WebDriver driver;
	public PageAlerta(WebDriver driver) {
		this.driver=driver;
	}
	
	public void PopUpDeshabilitar() throws InterruptedException
	{
		Alert alert = driver.switchTo().alert();
		System.out.println("Mensaje: "+alert.getText());
		alert.accept();
		Thread.sleep(2000);
	}
	
}
