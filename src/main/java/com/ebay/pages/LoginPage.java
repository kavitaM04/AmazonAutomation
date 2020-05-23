package com.ebay.pages;

import com.ebay.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
	
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private MobileElement signInBtn;

	@AndroidFindBy(id = "ap_email_login")
	private MobileElement emailTxtFld;

	@AndroidFindBy(className  = "android.widget.Button")
	private MobileElement ContinueBtn;

	@AndroidFindBy(id = "ap_password")
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy(id = "signInSubmit")
	private MobileElement loginBtn;
	
	
    public LoginPage signIn() {
    	clickMobileElement(signInBtn);
    	return this;
    }
	
	public LoginPage enterEmail(String email) {
		if(isDisplay(emailTxtFld))
		{
		sendKeys(emailTxtFld, email);
		clickMobileElement(ContinueBtn);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else {
		return this;
		}
		/*sendKeys(emailTxtFld, email);
        return this;*/
		return this;
	}
	
	public LoginPage continueToPassword() {
    	clickMobileElement(ContinueBtn);
    	return this;
    }

	public LoginPage enterPassword(String password) {
		sendKeys(passwordTxtFld, password);
		return this;
	}

	/*public String getErrorText() {
		return getAttribute(passwordErrtxt, "text");
	}*/

	
	public ProductsPage pressLoginBtn() {
		clickMobileElement(loginBtn);
		return new ProductsPage();
	}

}
