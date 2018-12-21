package com.ibm.groceriespages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ShoppingCartPage {

	@FindBy(xpath = "//input[@title='Qty']")
	WebElement qtyEle;

	@FindBy(xpath = "//button[text()='Update Shoping Cart']")
	WebElement updatecartEle;

	@FindBy(xpath = "//table[@class='shop_table cart']/tbody/tr[1]/td[3]/span")
	WebElement unitpriceEle;

	@FindBy(xpath = "//table[@class='shop_table cart']/tbody/tr[1]/td[5]/span")
	WebElement subtotalEle;

	WebDriverWait wait;
	WebDriver driver;

	public ShoppingCartPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public String updateQuantity(String quantity) {

		qtyEle.clear();
		// To assign quantity value
		qtyEle.sendKeys(quantity);

		// TO click on update shopping cart button
		updatecartEle.click();
		return driver.getPageSource();
	}

	public boolean verifyQuantity(String quantity) {
		String updatedqtyValue = qtyEle.getAttribute("value");
		if (updatedqtyValue.equals(quantity))
			return true;
		else
			return false;
	}

	// To verify the subtotal value
	public boolean verifySubTotal()

	{

		String unitprice = unitpriceEle.getText();
		String updatedqtyValue = qtyEle.getAttribute("value");
		String subTotal = subtotalEle.getText();

		// To remove the first character ? and decimal places
		unitprice = unitprice.trim().substring(1).replace(".00", "");
		subTotal = subTotal.trim().substring(1).replace(".00", "");

		int intUnitPrice = Integer.parseInt(unitprice);
		int intSubTotal = Integer.parseInt(subTotal);
		int intQuantiy = Integer.parseInt(updatedqtyValue);

		System.out.println("unit price " + intUnitPrice);
		System.out.println("quantity " + intQuantiy);
		System.out.println("sub total " + intSubTotal);

		if (intSubTotal == (intQuantiy * intUnitPrice))
			return true;
		else
			return false;

	}
}