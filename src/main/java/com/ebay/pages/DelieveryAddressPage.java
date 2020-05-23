package com.ebay.pages;

import com.ebay.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DelieveryAddressPage extends BaseTest {

	@AndroidFindBy(id = "a-autoid-0-announce")
	private MobileElement selectDefaultAdress;

	@AndroidFindBy(className = "android.widget.Button")
	private MobileElement selectContinueOndeliveryPage;

	public PaymentPage selectDefaultAddressTime() {
		clickMobileElement(selectDefaultAdress);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickMobileElement(selectContinueOndeliveryPage);

		return new PaymentPage();
	}

}
