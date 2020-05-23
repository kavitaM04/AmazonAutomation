package com.ebay.pages;

import org.openqa.selenium.By;

import com.ebay.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsDetailsPage extends BaseTest {

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[13]/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View")
	private MobileElement prdctTitle;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[13]/android.view.View[12]/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.widget.EditText")
	private MobileElement prdctPrice;

	@AndroidFindBy(xpath = "//android.app.Dialog/android.view.View[2]")
	private MobileElement cancelonlanguageselection;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_cart_count")
	private MobileElement cart;

	@AndroidFindBy(id = "add-to-cart-button")
	private MobileElement AddToCartBtn;

	public String getTitle() {
		return getTxt(prdctTitle);

	}

	public String getPrice() {
		// return getTxt(prdctPrice);
		return getTxt(prdctPrice);

	}
	
	
	public ProductsDetailsPage cancelonlang() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isDisplay(cancelonlanguageselection)) {
		clickMobileElement(cancelonlanguageselection);
		return this;
	}
		else{
			return this;
			}
		}

	public ProductsDetailsPage addtoCart() {
	   for(int i=0; i<2; i++) {
       verticalSwipeByPercentage(0.8, 0.3, 0.5);
	   }
       clickMobileElement(AddToCartBtn);
		return this;
	}

	public CartPage goToCart() {
		clickMobileElement(cart);
		return new CartPage();
	}

}
