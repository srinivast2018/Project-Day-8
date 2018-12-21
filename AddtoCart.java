package com.ibm.groceries;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.groceriespages.GroceriesUserPage;
import com.ibm.groceriespages.ShoppingCartPage;
import com.ibm.initialization.WebDriverLaunch;
import com.ibm.utilities.GetScreenshot;

public class AddtoCart extends WebDriverLaunch {

	@Test(priority = 1, testName = "AddPoductToCart", groups = "low")
	public void addProductToCat() throws IOException, InterruptedException {
		String userPage = data.get("userPageUrl");
		String fullName = data.get("fullName");
		String phoneNum = data.get("phoneNum");
		String passwd = data.get("passwd");
		String confirmPassword = data.get("confirmPassword");
		String expMessage = data.get("expectedShopingCartMsg");
		String quantity = data.get("quantity");
		// To launch Groceries user page
		driver.get(userPage);

		GroceriesUserPage userpage = new GroceriesUserPage(driver, wait);
		userpage.signUp(fullName, phoneNum, passwd, confirmPassword);
		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);
		userpage.addProductToCart();
		screen.takeScreenshot(driver);

		ShoppingCartPage shop = new ShoppingCartPage(driver, wait);
		String pageSource = shop.updateQuantity(quantity);
		screen.takeScreenshot(driver);

		try {
			if (pageSource.contains(expMessage)) {
				Reporter.log(expMessage);
				// Assertion on updated quantity value
				Assert.assertTrue(shop.verifyQuantity(quantity));

				// Assertion on subtotal value
				Assert.assertTrue(shop.verifySubTotal());
			}

		}

		catch (Exception e) {
			Assert.fail();
		}

	}

}
