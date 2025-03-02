package TestExecute.Drybar_US.regressionTestcase;
       
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_056_Validate_GuestUser_Checkout_CC_withsimple_configurable_products_Discount_GCpartialredeem_with_Same_Billing_and_shipping {
     
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_Checkout_CC_withsimple_configurable_products_Discount_GCpartialredeem_with_Same_Billing_and_shipping () throws Exception {

		try {
		    

			Drybar.Verify_Homepage();
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.addDeliveryAddress_Guestuser("Address");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.tax_validation_Paymentpage("Address");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.discountCode("Discount");
			Drybar.gitCard("GiftCode");
			Drybar.updatePaymentAndSubmitOrder("CCAmexcard");

			
			
		

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
       Drybar.close_add();
        

	}

}
