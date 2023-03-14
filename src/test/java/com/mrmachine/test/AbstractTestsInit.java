package com.mrmachine.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mrmachine.lib.AppLib;

/**
 * Clase que sirve como puente de conexion para inicializar la conexión de
 * elementos, recuperación de elementos, iniciar establecimientos del
 * controlador web, establecer flujos usando el controlador,etc.
 */
public abstract class AbstractTestsInit {

	// Controlador
	public WebDriver driver;
	// Objeto en general para configurar con que página trabajar y establecer
	// direcciones etc.
	private AppLib appLib;

	/**
	 * Método inicializador del test.
	 * 
	 * @param usarbrowser String que se establece del xml a correr, que indica que
	 *                    controlador vamos a usar.
	 * @param pathbrowser String que se establece del xml a correr, que indica la
	 *                    dirección de las carpetas en donde se encuentra el
	 *                    controlador.
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters({ "usarbrowser", "pathbrowsercontroller" })
	public void initTest(@Optional("Chrome") String usarbrowser,
			@Optional("C:\\Mr_Machine\\Programacion\\WebDrivers\\chromeWebDriver\\chromedriver.exe") String pathBrowserController) {
		// Si el controlador es chrome use ese.
		if (usarbrowser.equals("Chrome")) {

			// Establecemos el controlador Chrome
			System.setProperty("webdriver.chrome.driver", pathBrowserController);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			this.driver = new ChromeDriver(options);

			System.out.println("Executed initTest(); ...");
			// Objeto general
			this.appLib = new AppLib(driver);
		}
	}

	/**
	 * Método finalización del test.
	 */
	@AfterTest(alwaysRun = true)
	public void endTest() {
		this.driver.quit();
		System.out.println("Executed endTest(); ...");
	}
	
	/**
	 * Método para establecer flujo y páginas a trabajar.
	 * @return Retorna el objeto principal para establecer las páginas y flujos.
	 * */
	public AppLib app() {
		return this.appLib;
	}

}
