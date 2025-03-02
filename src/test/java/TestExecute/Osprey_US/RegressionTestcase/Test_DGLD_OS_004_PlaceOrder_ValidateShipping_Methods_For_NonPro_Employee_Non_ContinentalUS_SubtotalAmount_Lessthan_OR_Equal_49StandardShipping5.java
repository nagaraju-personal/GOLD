package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_004_PlaceOrder_ValidateShipping_Methods_For_NonPro_Employee_Non_ContinentalUS_SubtotalAmount_Lessthan_OR_Equal_49StandardShipping5 {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Guest_user_checkout_PP_with_Multiple_products_with_Discount_samebilling_and_shipping () throws Exception {
   
		try {
			 Osprey_ReEu.verifingHomePage();
		        Osprey_ReEu.search_product("Product<50");
		        Osprey_ReEu.simple_addtocart("Product<50");
		        Osprey_ReEu.minicart_Checkout();
		       
		        Osprey_ReEu.addDeliveryAddress_Guestuser("Non_Continental");
		        Osprey_ReEu.selectshippingmethod("GroundShipping method");
		        Osprey_ReEu.Verify_ShippingAmount_Lessthan_Or_Equal49();
		        Osprey_ReEu.clickSubmitbutton_Shippingpage();
		        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
		        
        
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
