package com.mrmachine.elements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Clase Factory (Fábrica), que recupera todos los elementos para trabajar en los testeos de la apliación.
 * */
public class ElementsPageFSWeb {
	
	// Controlador
	private WebDriver driver;
	
	@FindBy(id = "username") public WebElement inputUserName;
	@FindBy(id = "senha") public WebElement inputPassword;
	@FindBy(id = "btn-entrar") public WebElement btnLogin;
	@FindBy(id = "menu") public WebElement btnMenu;
	@FindBy(xpath = "//a[text()='Consultoría ']") public WebElement btnConsultoria;
	@FindBy(xpath = "//a[text()='Producción']") public WebElement btnProduccion;
	@FindBy(xpath = "//table[@id='tabela-empresas']/child::tbody/child::tr" ) public List<WebElement> sizeList;
	@FindBy(xpath = "//body/div[@class='container']/div[@class='row']/ul[@class='collapsible']/li[2]/div[1]") public WebElement btnDesplegableProduccion;
	@FindBy(xpath = "//div[@id='listContratos']/div[1]/div[1]/input") public WebElement btnCampaniaProd;
	@FindBy(xpath = "//span[text()='Todas']") public WebElement itemTodosCampaniaProd;
	@FindBy(xpath = "//table[@id='tabela-producao']/child::tbody/child::tr") public List<WebElement> quantityOfProdByCompany;
	@FindBy(xpath = "//table[@id='tabela-etapas']/child::tbody[3]/child::tr/child::td[2]/a") public WebElement btnEtapaPostulacion;
	@FindBy(xpath = "//button[@id='salvar']") public WebElement btnSaveEtapaPostulacion;
	
	public ElementsPageFSWeb(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

}
