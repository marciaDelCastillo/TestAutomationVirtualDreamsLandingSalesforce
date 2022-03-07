package tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.Dato;
import helpers.Helpers;
import pages.HomePage;
import pages.SalesForceContactPage;
import pages.SalesForcePage;

public class Tests {
	private WebDriver driver;
	private ArrayList<Dato> datos;
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://virtualdreams.io/salesforce/landing-salesforce/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		datos = Dato.leer();
		//System.out.println(datos);
		//posibilidad de navegar desde la pagina de inicio
		/*
		HomePage homePage = new HomePage(driver);
		homePage.ingresarASalesForce();
		Helpers helper = new Helpers();
		helper.sleepSeconds(2);
		SalesForcePage salesForcePage = new SalesForcePage(driver);
		salesForcePage.ingresarAlFormulario();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		*/
		
	}
	
	@Test // Case 1: Envio de formulario con todos los campos vacios
			//resultado esperado: todas las validaciones deben aparecer
	public void enviarFormularioVacio() {
		
		SalesForceContactPage contactPage = new SalesForceContactPage(driver);
		//lectura de casos de prueba
		//for para correr los distintos casos
		Dato dato = datos.get(0);
		contactPage.llenarFormulario(dato);
		Helpers helper = new Helpers();
		helper.sleepSeconds(3);
		contactPage.assertPageCase1();
	}

	@Test // Case 2 to 6: Envio de formulario con todos los campos excepto uno, recorriendo todos los campos
			//resultado esperado: validacion de cada campo que imposibilite el envio del formulario
	public void enviarFormularioFaltandoUnCampo() {
		
		SalesForceContactPage contactPage = new SalesForceContactPage(driver);
		Helpers helper = new Helpers();
		for(int i=0;i<5;i++) {
			Dato dato = datos.get(i+1);
			driver.navigate().to(driver.getCurrentUrl()); //recargo la pagina
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			contactPage.llenarFormulario(dato);
			helper.sleepSeconds(2);
			contactPage.assertPageCase2to6(i);
		}
		
	}

	
	@Test // Case 7. resultado esperado: envio correcto del formulario
	public void enviarFormularioCompletoCorrecto() {
		
		SalesForceContactPage contactPage = new SalesForceContactPage(driver);
		Dato dato = datos.get(6);
		contactPage.llenarFormulario(dato);
		Helpers helper = new Helpers();
		helper.sleepSeconds(3);
		contactPage.assertPageCase7();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
