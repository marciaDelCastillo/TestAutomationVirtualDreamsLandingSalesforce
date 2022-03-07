package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import data.Dato;
import helpers.Helpers;

public class SalesForceContactPage {
	private WebDriver driver;
	private By nameField;
	private By mailField;
	private By companyField;
	private By productDrop;
	private By phoneField;
	private By submitBtn;
	private By successDiv;
	private By errorLabels;
	
	public SalesForceContactPage(WebDriver driver) {
		this.driver = driver;
		nameField= By.id("contact_form_nombre");
		mailField= By.id("contact_form_email");
		companyField= By.id("contact_form_empresa");
		productDrop= By.id("contact_form_producto");
		phoneField= By.id("contact_form_telefono");
		submitBtn= By.id("submit-landing-form");
		successDiv = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
		errorLabels = By.tagName("label");
	}

	public void llenarFormulario(Dato dato) {

		//cargo los valores
		driver.findElement(nameField).sendKeys(dato.nombre);
		driver.findElement(mailField).sendKeys(dato.email);
		driver.findElement(companyField).sendKeys(dato.empresa);
		Select selectProduct = new Select(driver.findElement(productDrop));
		selectProduct.selectByVisibleText(dato.producto);
		driver.findElement(phoneField).sendKeys(dato.telefono);
		Helpers helper = new Helpers();
		helper.sleepSeconds(2);
		
		driver.findElement(submitBtn).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	public void assertPageCase1() {
		List <WebElement> labelErrors = driver.findElements(errorLabels);
		Boolean condicion = true;
		int error = -1;
		for(int i=0;i<5;i++) {
			if(!labelErrors.get(i).getText().contains("Este campo es obligatorio")) {
				condicion = false;
				error = i;
			}
		}
		Assert.assertTrue(condicion, "No funcionó la validación del campo "+error);
}
	
	
	//datos: nombre, mail, empresa, producto, telefono (index es el valor que viene vacio y cuya validacion debe aparecer)
	public void assertPageCase2to6(int index) {
		WebElement labelError=null;
		String nombreError="";
		switch(index)
		{
		   case 0 :
		      labelError = driver.findElement(By.id("contact_form_nombre-error"));
		      nombreError = "Nombre";
		      break; // break es opcional
		   case 1 :
			   labelError = driver.findElement(By.id("contact_form_email-error"));
			      nombreError = "Email";
		      break; // break es opcional
		   case 2 :
			   labelError = driver.findElement(By.id("contact_form_empresa-error"));
			      nombreError = "Empresa";
			  break; // break es opcional   
		   case 3 :
			   labelError = driver.findElement(By.id("contact_form_producto-error"));
			      nombreError = "Producto";
			  break; // break es opcional
		   case 4 :
			   labelError = driver.findElement(By.id("contact_form_telefono-error"));
			      nombreError = "Teléfono";
			  break; // break es opcional
		   default : 
		      Assert.assertTrue(false, "Error de programación");
		}
		Assert.assertTrue(labelError.getText().contains("Este campo es obligatorio"), "La validación del campo "+nombreError+" no funcionó");

	}
	
	public void assertPageCase7() {
			Assert.assertTrue(driver.findElement(successDiv).getText().contains("Gracias por contactarte con nosotros"), "El formulario no fue enviado");
		
	}
}
