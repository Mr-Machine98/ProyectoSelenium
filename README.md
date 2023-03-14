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

1. Establecemos el controlador y le decimos donde se encuentra en el equipo, y damos permisos para conexiones remotas:
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
3. Establecemos las crendenciales para ingresar a la App, pero Obvio 🤦‍♂️🤦‍♂️🤦‍♂️ que no colocare las credenciales en este reporitorio me hechan de mi trabajo jajaj.

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
