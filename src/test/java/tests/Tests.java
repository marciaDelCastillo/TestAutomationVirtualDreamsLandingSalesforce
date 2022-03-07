package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import pages.HomePage;
import pages.SalesForceContactPage;
import pages.SalesForcePage;

public class Tests {
	private WebDriver driver;
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://virtualdreams.io/salesforce/landing-salesforce/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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

	@Test // Case 2 to 6. resultado esperado: validacion de cada campo que imposibilite el envio del formulario
	public void llenarFormularioFaltandoUnCampo() {
		
		SalesForceContactPage contactPage = new SalesForceContactPage(driver);
		Helpers helper = new Helpers();
		//for para correr los distintos casos, en que vaya faltando solo un campo
		String [] datos= {"marcia", "marcia990_14@hotmail.com", "Delphos", "Marketing", "1122334455"};
		for(int i=0;i<datos.length;i++) {
				String [] datosAux={"marcia", "marcia990_14@hotmail.com", "Delphos", "Marketing", "1122334455"};
				if(i==3) {
					datosAux[i]="Producto";
				}else {
					datosAux[i]="";
				}
				driver.navigate().to(driver.getCurrentUrl()); //recargo la pagina
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				contactPage.llenarFormulario(datosAux[0],datosAux[1],datosAux[2],datosAux[3],datosAux[4]);
				helper.sleepSeconds(2);
				contactPage.assertPageCase2to6(i);
		}	
	}

	
	@Test // Case 7. resultado esperado: envio correcto del formulario
	public void llenarFormularioCompletoCorrecto() {
		
		SalesForceContactPage contactPage = new SalesForceContactPage(driver);
		//lectura de casos de prueba
		//for para correr los distintos casos
		contactPage.llenarFormulario("marcia", "marcia990_14@hotmail.com", "Delphos", "Marketing", "1122334455");
		Helpers helper = new Helpers();
		helper.sleepSeconds(3);
		contactPage.assertPageCase7();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
