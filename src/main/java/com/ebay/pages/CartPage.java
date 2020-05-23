package com.ebay.pages;

import com.ebay.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BaseTest {

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[6]/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View")
	private MobileElement PrsctTitleOnCartPage;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[6]/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View/android.view.View[3]/android.widget.ListView/android.view.View[1]")
	private MobileElement PrsctPriceOnCartPage;
	
	
	@AndroidFindBy(id = "sc-mini-buy-box")
	private MobileElement proceedToBuy;


	public DelieveryAddressPage purchasePrdct() {
		clickMobileElement(proceedToBuy);
		return new DelieveryAddressPage();
	}
	
	public String getPrice() {
		return getTxt(PrsctPriceOnCartPage);
	}

	
	public String getTitle() {
		return getTxt(PrsctTitleOnCartPage);
	}


}
