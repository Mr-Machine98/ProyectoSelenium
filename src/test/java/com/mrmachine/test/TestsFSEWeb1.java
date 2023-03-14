package com.mrmachine.test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.mrmachine.main.utils.Credenciales;

/**
 * Este es un proyecto para demostrar los conocimientos de Selenium, en una
 * aplicación en la empresa donde trabajo.
 * 
 * Problema: Fueron migrados unos datos de tipo numérico a la base de datos,
 * pero estos datos al viajar a la app, no presentan un formato adecuado en sus
 * puntos decimales y coma flotante, así que, la misma app les da formato pero
 * hay que actualizar para que se les agregue el formato presionando el botón en
 * cada proyecto, de los clientes.
 * 
 * Solución: Hacer un programa de Selenium para que se vaya poryecto por
 * proyecto actualizando los valores nume'ricos de los clientes.
 * 
 * Este código es de prueba, en los paquetes o carpetas de Test es la forma
 * adecuada.
 * 
 * @author: Juan Camilo Mamian Ruiz
 * @version: 1.1.0
 */
public class TestsFSEWeb1 extends AbstractTestsInit {

	@Test
	public void testButtonProducciones() {
		long startTime = System.currentTimeMillis();
		System.out.println(">>> Start @Test testButtonProducciones() ");
		
		app().flow().setWaitTimeOfResponse(20);
		app().flow().setUrl("https://co-fseweb.fi-group.com/login");
		app().flow().maximize();

		app().pages().getPageFSWeb().setInputUserName(Credenciales.USERNAME);
		app().pages().getPageFSWeb().setinputPassWord(Credenciales.PASSWORD);
		app().pages().getPageFSWeb().clickBtnLogIn();
		app().pages().getPageFSWeb().clickBtnMenu();
		app().pages().getPageFSWeb().clickBtnConsultoria();
		app().pages().getPageFSWeb().clickBtnProducciones();

		System.out.println("    * get URL productions:" + app().flow().getCurrentPageUrl() + "\n");
		String urlAllProdCompany = app().flow().getCurrentPageUrl();

		int indexAllProd = 1;
		int quantityProd = app().pages().getPageFSWeb().numAllProd();

		while (indexAllProd <= quantityProd) {
			System.out.println(
					"	#" + indexAllProd + " Prod: " + app().pages().getPageFSWeb().getTitleCompanyProd(indexAllProd)+ "\n");

			app().pages().getPageFSWeb().clickBtnCompanyProd(indexAllProd);
			app().pages().getPageFSWeb().clicCollapsibleProd();
			app().pages().getPageFSWeb().updateProd();
			app().flow().navigateTo(urlAllProdCompany);
			
			indexAllProd++;
		}
		System.out.println(">>> End @Test testButtonProducciones() ");
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("Process finished in: " 
				+ TimeUnit.MILLISECONDS.toMinutes(endTime)
				+ " minutes."
		);
	}

}
