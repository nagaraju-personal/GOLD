package TestExecute.OXO_loq.regressionTestcase;

import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoLoq_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_Loq_012_Register_PaymentPage_UpdateAddress_Mybilling_and_shippingaddress {
	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoLoq_Helper Oxo = new GoldOxoLoq_Helper(datafile,"Loqata");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_PaymentPage_UpdateAddress_Mybilling_and_shippingaddress () throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_product("Product");
			Oxo.addtocart_PLP("Product");
			Oxo.minicart_Checkout1();
			Oxo.BillingAddress1("BillingDetails");
			Oxo.updatePaymentAndSubmitOrder("PaymentDetails");
			

			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}
	
	
	
	
	
	
	

	
	
	
	
	

}
