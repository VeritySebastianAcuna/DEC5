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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"table-nuevo_paginate\"]/ul/li[4]/a")).click();
		Thread.sleep(2000);
	}

}
