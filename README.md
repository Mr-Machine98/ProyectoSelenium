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

1. Establecemos el codigo
```javascript
function fancyAlert(arg) {
  if(arg) {
    $.facebox({div:'#foo'})
  }
}
```
