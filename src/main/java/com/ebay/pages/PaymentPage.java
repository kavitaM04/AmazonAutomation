package com.ebay.pages;

import com.ebay.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PaymentPage extends BaseTest {

	@AndroidFindBy(id = "pp-502JdC-154")
	private MobileElement selectCashOnDelievery;

	public PaymentPage goTillCashonDelvry() {
		verticalSwipeByPercentage(0.8, 0.2, 0.5);
		return this;
	}

}
