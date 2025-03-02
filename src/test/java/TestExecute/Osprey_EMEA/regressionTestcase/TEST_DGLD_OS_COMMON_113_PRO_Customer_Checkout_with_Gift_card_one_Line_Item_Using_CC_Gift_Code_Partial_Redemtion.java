package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_COMMON_113_PRO_Customer_Checkout_with_Gift_card_one_Line_Item_Using_CC_Gift_Code_Partial_Redemtion {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_PRO_Customer_Checkout_with_Gift_card_one_Line_Item_Using_CC_Gift_Code_Partial_Redemtion () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("prouser");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product"); 
        Osprey_ReEu.Prouser_Discount();
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");      
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.Gift_card("Partial Giftcard");
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
