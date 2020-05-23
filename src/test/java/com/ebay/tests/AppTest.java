package com.ebay.tests;

import org.testng.annotations.Test;

import com.ebay.BaseTest;
import com.ebay.pages.CartPage;
import com.ebay.pages.DelieveryAddressPage;
import com.ebay.pages.LoginPage;
import com.ebay.pages.PaymentPage;
import com.ebay.pages.ProductsDetailsPage;
import com.ebay.pages.ProductsPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	ProductsDetailsPage productDetailsPage;
	CartPage cartPage;
	DelieveryAddressPage delieveryAddressPage;
	PaymentPage paymentPage;

	InputStream datais;
	JSONObject data;
	// private String PrdctTitlonPrdctDetailPage;
	// private String PrdctPriceonPrdctDetailPage;
	// private String PrdctTitlonCartPage;
	// private String PrdctPriceonCartPage;

	@BeforeClass
	public void beforeClass() throws Exception {
		try {
			String dataFileName = "data/data.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			data = new JSONObject(tokener);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		closeApp();
		launchApp();

	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		loginPage = new LoginPage();
		System.out.println("\n" + "**** Starting test:" + m.getName() + "*****" + "\n");
	}

	@AfterMethod
	public void afterMethod() {

	}

	@Test(priority = 1)
	public void validLogin() {

		// Sign in as already registered user
		loginPage.signIn();

		// Enter email ID and password from json file
        loginPage.enterEmail(data.getJSONObject("validLogin").getString("email"));
		loginPage.enterPassword(data.getJSONObject("validLogin").getString("password"));

		// Click on Login
		productsPage = loginPage.pressLoginBtn();

		// Search Product by keyword - fetching through json
		productsPage.searchProduct(data.getJSONObject("product").getString("searchkeyword"));

		// Select product from Product page
		productDetailsPage = productsPage.selectProduct();

		productDetailsPage.cancelonlang();

		// Get Details of the selected Product
		String PrdctTitlonPrdctDetailPage = productDetailsPage.getTitle().substring(0,53).trim();
		System.out.println("Title of prdct on prdct page is" + PrdctTitlonPrdctDetailPage);
		String PrdctPriceonPrdctDetailPage = productDetailsPage.getPrice().substring(7).trim();
		System.out.println("Price of prdct on prdct page is" + PrdctPriceonPrdctDetailPage);

		// Add product to the cart
		productDetailsPage.addtoCart();

		// Go to Cart
		cartPage = productDetailsPage.goToCart();

		// Get product details from cart
		String PrdctTitlonCartPage = cartPage.getTitle().substring(0, 53).trim();
		System.out.println(PrdctTitlonCartPage);
		String PrdctPriceonCartPage = cartPage.getPrice().substring(3,11).trim();
		System.out.println(PrdctPriceonCartPage);


		// Check wether the selected product details from Product page are same as in cart
		Assert.assertEquals(PrdctTitlonPrdctDetailPage, PrdctTitlonCartPage);
		Assert.assertEquals(PrdctPriceonPrdctDetailPage, PrdctPriceonCartPage);

		// Buy Product
		delieveryAddressPage = cartPage.purchasePrdct();

		// Select Delievery AddressandTime
		paymentPage = delieveryAddressPage.selectDefaultAddressTime();

		// Go till payment option - cash on Delievery
		paymentPage.goTillCashonDelvry();
	}

}
