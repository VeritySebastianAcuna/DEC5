package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.CapturaPantalla;
import common.CrearDocEvidencia;
import common.Log;

public class PageAvanzarPagina {
	private WebDriver driver;
	Log log = new Log();
	CrearDocEvidencia crearDocEvidencia = new CrearDocEvidencia();
	CapturaPantalla capturaPantalla = new CapturaPantalla();
	public PageAvanzarPagina(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickAvanzar(String caso) throws InterruptedException, IOException, InvalidFormatException {
		int i=0;
		int j=0;
		do {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//*[@id=\"table-nuevo_paginate\"]/ul/li[4]/a")).click();
				Thread.sleep(2000);
				i=1;
			}catch (Exception e) {
				// TODO: handle exception
				j++;
				if(j==3) {
					System.out.println("No fue posible Avanzar página");
					i=1;
				}
			}
		}while(i==0);
		Thread.sleep(3000);
	}

}
