package com.mrmachine.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mrmachine.elements.ElementsPageFSWeb;

/**
 * Clase que sirve como MODELO y SERVICIO de información, en el cual,
 * nos provee las acciones que debemos hacer cuando estamos testeando la aplicación.
 * */
public class PageFSWeb {
	// Controlador
	private WebDriver driver;
	// Elementos
	private ElementsPageFSWeb elementsFsweb;

	public PageFSWeb(WebDriver driver) {
		super();
		this.driver = driver;
		this.elementsFsweb = new ElementsPageFSWeb(driver);
	}
	
	/**
	 * Método que coloca datos en el input username.
	 * @param username Credencial de usuario para ingresar.
	 */
	public void setInputUserName(String username) {
		this.elementsFsweb.inputUserName.sendKeys(username);
	}
	
	/**
	 * Método que coloca datos en el input paswword.
	 * @param pass Credencial de usuario para ingresar. 
	 */
	public void setinputPassWord(String pass) {
		this.elementsFsweb.inputPassword.sendKeys(pass);
	}
	
	/**
	 * Realiza la acción click en el botón log in.
	 */
	public void clickBtnLogIn() {
		this.elementsFsweb.btnLogin.click();
	}
	
	/**
	 * Realiza la acción click en el botón menú.
	 */
	public void clickBtnMenu() {
		this.elementsFsweb.btnMenu.click();
	}
	
	/**
	 * Realiza la acción click en el botón consultoría.
	 */
	public void clickBtnConsultoria() {
		this.elementsFsweb.btnConsultoria.click();
	}
	
	/**
	 * Realiza la acción click en el botón producciones.
	 */
	public void clickBtnProducciones() {
		this.elementsFsweb.btnProduccion.click();
	}
	
	/**
	 * Método que devuelve la cantidad de producciones que hay.
	 * @return elementsFsweb Utiliza el objeto elementsFsweb para devolver la cantidad de producciones que hay.
	 */
	public int numAllProd() {
		return this.elementsFsweb.sizeList.size();
	}
	
	/**
	 * Método que consulta cada empresa en la pestaña de producciones, y devuelve el nombre de la empresa que tiene producciones.
	 * @param indice Selecciona el índice del renglón a elegir.
	 * @return retorna el nombre de la empresa.
	 */
	public String getTitleCompanyProd(int indice) {
		return this.driver.findElement(By.xpath("//table[@id='tabela-empresas']/child::tbody/child::tr[" + indice + "]/child::td[2]")).getText();
	}
	
	/**
	 * Método que consulta cada empresa en la pestaña de producciones, y realiza el click para ingresar.
	 * @param indice Selecciona el índice del renglón a elegir.
	 */
	public void clickBtnCompanyProd(int indice) {
		driver.findElement(By.xpath("//table[@id='tabela-empresas']/child::tbody/child::tr[" + indice + "]/child::td[3]/child::a")).click();
	}
	
	/**
	 * Método que realiza la acción de hacer clic en el desplegable de producción, selecciona el botón
	 * campaña y establece la opción TODAS.
	 */
	public void clicCollapsibleProd() {
		this.elementsFsweb.btnDesplegableProduccion.click();
		this.elementsFsweb.btnCampaniaProd.click();
		this.elementsFsweb.itemTodosCampaniaProd.click();
	}
	
	/**
	 * Método que ingresa producción por producción por cada empresa para actualizar los valores de
	 * postulación.
	 */
	public void updateProd() {
		
		int quantityOfProdByCompany = this.elementsFsweb.quantityOfProdByCompany.size();
		String urlAllProdByCompany = this.driver.getCurrentUrl();
		
		for (int i = 1; i <= quantityOfProdByCompany ; i++) {
			String selector = "//table[@id='tabela-producao']/child::tbody/child::tr[" + i + "]";
			String estadoProyecto = driver.findElement(By.xpath(selector + "/child::td[4]")).getText();
			System.out.println("		#" + i + " Estado del Proyecto: " + estadoProyecto + "\n");
			this.driver.findElement(By.xpath(selector + "/child::td[9]/child::a")).click();
			this.elementsFsweb.btnEtapaPostulacion.click();
			this.elementsFsweb.btnSaveEtapaPostulacion.click();
			driver.navigate().to(urlAllProdByCompany);
			clicCollapsibleProd();
		}
	}

	
}
