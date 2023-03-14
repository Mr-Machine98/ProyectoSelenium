# Proyecto Selenium en FSEWEB!

 - Este es un proyecto para demostrar los conocimientos de Selenium, en una aplicación de la empresa donde trabajo.
 
 - ***Problema:*** Fueron migrados unos datos de tipo numérico a la base de datos, pero estos datos al viajar a la app, no presentan un formato adecuado en sus puntos decimales y coma flotante, así que, la misma app les da formato pero hay que actualizar para que se les agregue el formato presionando el botón (Actualizar) en cada proyecto, de las empresas.
  
 - ***Solución:*** Hacer un programa con Selenium para que se vaya poryecto por proyecto actualizando los valores numéricos de los clientes.
 
 - Este código es de prueba, en los paquetes o carpetas de Test es la forma adecuada.
  
 > @author: Juan Camilo Mamian Ruiz
 >
 > @version: 1.0.0
 >> ***Note:*** El paquete ***com.mrmachine.test;*** contiene el ***@Test testButtonProducciones***, en donde es la mejor manera de utilizar Selenium junto con la librería ***TestNG***, permite ver logs de información en donde nos muestra lo que está pasando con los ***@Test***, además, de generar reportes de fallos, como por ejemplo en la siguiente imágen.
 >> 
 >> ![Alt text](http://knorrium.info/wp-content/uploads/2010/11/testng-report.png)
 >> 
 >  ***Note:*** Estos reportes los encontramos en la carpeta del proyecto ***test-output***.
 
 ## Explicación  del código

Explicare el código línea por línea del primer prototipo de automatización, ya que este lo programé de largo sin separar partes del código, entonces, creería que sería mas fácil explicar este primer código realizado.

1. Establecemos el controlador y le decimos donde se encuentra en el equipo, y damos permisos para conexiones remotas.
```java
System.setProperty("webdriver.chrome.driver",
				"C:\\Mr_Machine\\Programacion\\WebDrivers\\chromeWebDriver\\chromedriver.exe");
ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-allow-origins=*");
WebDriver driver = new ChromeDriver(options);
```
2. Establecemos el tiempo de respuesta que el controlador tiene para encontrar algún elemento en concreto o esperar para el retorno de información del backend, maximizamos la ventana, y le pasamos la url de la app.
```java
// Establecemos al controlador que espere n tiempo cuando el tiempo de respuesta de la app se demore.
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
// Maximizar la ventana
driver.manage().window().maximize();
// acceder a la pág, pasamos la URL
driver.get("https://co-fseweb.fi-group.com/login");
```
3. Establecemos las crendenciales para ingresar a la App, pero Obvio 🤦‍♂️🤦‍♂️🤦‍♂️ que no colocare las credenciales en este repositorio me hechan de mi trabajo jajaj.

```java 
// Credenciales para ingresar a la app
String usr = Credenciales.USERNAME;
String pass = Credenciales.PASSWORD;
```
4. Buscamos los elementos presentes en el navegador, en este caso son los inputs de las credenciales y le damos click al botón entrar para ingresar.
```java
// Buscamos los inputs y establecemos las credenciales
driver.findElement(By.id("username")).sendKeys(usr);
driver.findElement(By.id("senha")).sendKeys(pass);
// Click al btn entrar para ingresar
driver.findElement(By.id("btn-entrar")).click();
```
5. Navegamos por la app, buscando el menú, encontramos el botón desplegable consultoría y luego click en el item postulación. 
```java
// Click al btn menu para ingresar al menu
driver.findElement(By.id("menu")).click();
// Click al btn Consultori'a
driver.findElement(By.xpath("//a[text()='Consultoría ']")).click();
// Click al btn Produccio'n
driver.findElement(By.xpath("//a[text()='Producción']")).click();
```
6. En este punto obtenemos la url de las empresas que tienen producciones, la razón, porque cada vez que consultemos una por una, debemos devolvernos en donde se encuentran todas para ir seleccionando cada una de ellas, creamos un indice para utilizarlo en un while, y miramos cuantas empresas tienen producciones. 
```java
String urlAllProd = driver.getCurrentUrl();
int indexAllProd = 1;
int listSizeProd = driver.findElements(By.xpath("//table[@id='tabela-empresas']/child::tbody/child::tr")).size();
```
7. En este punto el programa lo que hace es recorrer empresa por empresa haciendo click en cada una de ellas, una vez dentro de la empresa, presiona el desplegable en donde abre sus producciones, las consulta todas y luego otra vez itera por esas producciones que tiene esa empresa, actualizando sus valores presionando el botón de guardar, una vez hecho eso, vuelve a la url de las empresas para repetir el proceso.
```java
// Recorremos todos las producciones
while (indexAllProd <= listSizeProd) {
	String selector = "//table[@id='tabela-empresas']/child::tbody/child::tr[" + indexAllProd + "]";
	System.out.println("	#" + indexAllProd + " Prod: " + driver.findElement(By.xpath(selector + "/child::td[2]")).getText() + "\n");

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
```
8. Aquí está el método en donde vuelve a iterar por cada producción que tiene una empresa.
```java
/**
 * Método que ingresa producción por producción por cada empresa para actualizar los valores de
* postulación.
* 
* @param driver El parámetro a utilizar para acceder a los elementos de la pág
 *               web.
 */
public static void updateProd(WebDriver driver) {

	// size de la lista de producciones
	int cantidadProdTodas = driver.findElements(By.xpath("//table[@id='tabela-producao']/child::tbody/child::tr")).size();
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
```
10. 
