package com.mrmachine.lib;

import org.openqa.selenium.WebDriver;

import com.mrmachine.pages.PageFSWeb;

/**
 * Clase establecedora general de páginas con las cuales vamos a a trabajar.
 * */
public class PageLib {
	// Controlador
	private WebDriver driver;
	private PageFSWeb pageFsweb;
	
	public PageLib(WebDriver driver) {
		super();
		this.driver = driver;
		this.pageFsweb = new PageFSWeb(driver);
	}
	
	/**
	 * Recuperamos la página con la que vamos a trabajar, en este caso, con la página FSWeb.
	 * @return pageFsweb Objeto de la página FSWEB con la que podremos realizar acciones de busquedas o establecimientos de elementos. 
	 * */
	public PageFSWeb getPageFSWeb() {
		return this.pageFsweb;
	}
}
