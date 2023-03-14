package com.mrmachine.lib;

import org.openqa.selenium.WebDriver;

/**
 * Clase que sirve como puente para determinar con que páginas,  elementos de las
 * páginas vamos a trabajar, y cual es el flujo que se determinará
 * en cada ejecución del programa.
 */
public class AppLib {	
	// Controlador
	private WebDriver driver;
	// Páginas
	private PageLib page;
	// Flujo
	private FlowLib flow;
	
	public AppLib(WebDriver driver) {
		super();
		this.driver = driver;
		this.page = new PageLib(driver);
		this.flow = new FlowLib(driver);
	}
	
	/**
	 * Administra las páginas de la aplicación estableciendo y recuperando información.
	 */
	public PageLib pages() {
		return this.page;
	}
	
	/**
	 * Administra el flujo de la aplicación estableciendo y recuperando información.
	 */
	public FlowLib flow() {
		return this.flow;
	}
}
