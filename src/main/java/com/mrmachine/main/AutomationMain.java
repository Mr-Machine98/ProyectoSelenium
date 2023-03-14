package com.mrmachine.main;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.mrmachine.main.utils.Credenciales;

/**
 * Este es un proyecto para demostrar los conocimientos de Selenium, en una
 * aplicación en la empresa donde trabajo.
 * 
 * Problema: Fueron migrados unos datos de tipo numérico a la base de datos,
 * pero estos datos al viajar a la app, no presentan un formato adecuado en sus
 * puntos decimales y coma flotante, así que, la misma app les da formato pero
 * hay que actualizar para que se les agregue el formato presionando el boto'n
 * en cada proyecto, de los clientes.
 * 
 * Solución: Hacer un programa de Selenium para que se vaya poryecto por
 * proyecto actualizando los valores nume'ricos de los clientes.
 * 
 * Este código es de prueba, en los paquetes o carpetas de Test es la forma adecuada.
 * 
 * @author: Juan Camilo Mamian Ruiz
 * @version: 1.0.0
 */
public class AutomationMain {
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		// Establecer el controlador de la web, usamos Chrome.
		System.setProperty("webdriver.chrome.driver",
				"C:\\Mr_Machine\\Programacion\\WebDrivers\\chromeWebDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		System.out.println(">>> Start\n");

		// Establecemos al controlador que espere n tiempo cuando el tiempo de respuesta
		// de la app se demore.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Maximizar la ventana
		driver.manage().window().maximize();
		// acceder a la pa'g, pasamos la URL
		driver.get("https://co-fseweb.fi-group.com/login");

		// Credenciales para ingresar a la app
		String usr = Credenciales.USERNAME;
		String pass = Credenciales.PASSWORD;

		// Buscamos los inputs y establecemos las credenciales
		driver.findElement(By.id("username")).sendKeys(usr);
		driver.findElement(By.id("senha")).sendKeys(pass);
		// Click al btn entrar para ingresar
		driver.findElement(By.id("btn-entrar")).click();
		// Click al btn menu para ingresar al menu
		driver.findElement(By.id("menu")).click();
		// Click al btn Consultori'a
		driver.findElement(By.xpath("//a[text()='Consultoría ']")).click();
		// Click al btn Produccio'n
		driver.findElement(By.xpath("//a[text()='Producción']")).click();

		// Obtenemos la url de todas los producciones para trabajar cada cliente
		String urlAllProd = driver.getCurrentUrl();
		System.out.println("	* get URL productions: " + urlAllProd + "\n");

		// Obtenemos los producciones para poder ingresar a ellos dando clic
		// index para recorrer
		int indexAllProd = 1;
		// size de la lista de producciones
		int listSizeProd = driver.findElements(By.xpath("//table[@id='tabela-empresas']/child::tbody/child::tr"))
				.size();

		// Recorremos todos los producciones
		while (indexAllProd <= listSizeProd) {

			String selector = "//table[@id='tabela-empresas']/child::tbody/child::tr[" + indexAllProd + "]";
			System.out.println("	#" + indexAllProd + " Prod: "
					+ driver.findElement(By.xpath(selector + "/child::td[2]")).getText() + "\n");

			// Seleccionamos los btn y presionamos clic
			driver.findElement(By.xpath(selector + "/child::td[3]/child::a")).click();

			// clic en el desplegable de producción
			clicCollapsibleProd(driver);

			// Actualizar los valores de postulación
			updateProd(driver);

			// Volver a los producciones
			driver.navigate().to(urlAllProd);
			indexAllProd++;
		}

		// Cerramos la ventana, el programa termino'
		driver.quit();
		System.out.println(">>> End\n");
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("Process finished in: " 
				+ TimeUnit.MILLISECONDS.toMinutes(endTime)
				+ " minutes."
		);
	}

	/**
	 * Método que ingresa producción por producción por cada empresa para actualizar los valores de
	 * postulación.
	 * 
	 * @param driver El parámetro a utilizar para acceder a los elementos de la pág
	 *               web.
	 */
	public static void updateProd(WebDriver driver) {

		// size de la lista de producciones
		int cantidadProdTodas = driver.findElements(By.xpath("//table[@id='tabela-producao']/child::tbody/child::tr"))
				.size();
		// Obtenemos la url de todos los proyectos por campaña
		String urlAllProyectos = driver.getCurrentUrl();

		// Iteramos por todos proyectos
		for (int i = 1; i <= cantidadProdTodas; i++) {

			String selector = "//table[@id='tabela-producao']/child::tbody/child::tr[" + i + "]";
			String estadoProyecto = driver.findElement(By.xpath(selector + "/child::td[4]")).getText();
			System.out.println("		#" + i + " Estado del Proyecto: " + estadoProyecto + "\n");

			// Clic al botón para acceder al proyecto
			driver.findElement(By.xpath(selector + "/child::td[9]/child::a")).click();

			// Clic en etapa de postulación 
			driver.findElement(By.xpath("//table[@id='tabela-etapas']/child::tbody[3]/child::tr/child::td[2]/a")).click();

			// Clic en el boton guardar para persistir los datos nuevos a la bd. 
			driver.findElement(By.xpath("//button[@id='salvar']")).click();
			
			driver.navigate().to(urlAllProyectos);

			// clic en el desplegable de producción
			clicCollapsibleProd(driver);
		}
	}

	/**
	 * Método que realiza la acción de hacer clic en el desplegable de producción.
	 * 
	 * @param driver El parámetro a utilizar para acceder a los elementos de la pág
	 *               web.
	 */
	public static void clicCollapsibleProd(WebDriver driver) {
		// Clic en el desplegable produccion
		driver.findElement(
				By.xpath("//body/div[@class='container']/div[@class='row']/ul[@class='collapsible']/li[2]/div[1]"))
				.click();
		// Clic en boto'n campania
		driver.findElement(By.xpath("//div[@id='listContratos']/div[1]/div[1]/input")).click();
		// Clic en todas para seleccionar todos los proyectos
		driver.findElement(By.xpath("//span[text()='Todas']")).click();
	}
}
