package com.mrmachine.lib;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

/**
 * Clase que sirve para determinar el flujo del Programa.
 */
public class FlowLib {
	// Controlador
	private WebDriver driver;

	public FlowLib(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	/**
	 * Método para establecer la URL de la página actual.
	 */
	public void setUrl(String url) {
		this.driver.get(url);
	}
	
	/**
	 * Método para navegar a otra  URL .
	 */
	public void navigateTo(String url) {
		this.driver.navigate().to(url);
	}

	/**
	 * Método para recuperar la URL de la página actual.
	 * @return driver Usa el driver para retornar la URL de la página como un String.
	 */
	public String getCurrentPageUrl() {
		return this.driver.getCurrentUrl();
	}

	/**
	 * Método para recuperar el título de la página actual.
	 * @return driver Usa el driver para retornar el título de la página como un String.
	 */
	public String getCurrentTitle() {
		return this.driver.getTitle();
	}
	
	/**
	 * Método para establecer el tiempo de respuesta que el controlador tiene para encontrar algún
	 * elemento en concreto o esperar para el retorno de información del backend.
	 * @param timeSeconds tiempo en segundos a establecer.
	 */
	public void setWaitTimeOfResponse(int timeSeconds) {
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSeconds));
	}
	
	/**
	 * Método para maximizar la página actual.
	 */
	public void maximize() {
		this.driver.manage().window().maximize();
	}
	
	

}
