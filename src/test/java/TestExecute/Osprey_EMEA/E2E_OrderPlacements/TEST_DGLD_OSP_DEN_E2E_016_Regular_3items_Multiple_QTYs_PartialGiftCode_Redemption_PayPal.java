package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_DEN_E2E_016_Regular_3items_Multiple_QTYs_PartialGiftCode_Redemption_PayPal {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Osprey_E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Regular_3items_Multiple_QTYs_PartialGiftCode_Redemption_PayPal () throws Exception {

		try {
		Osprey_ReEu.prepareOrdersData("OSP_E2E_orderDetails.xlsx");	
		String Description ="Regular 3 items + Multiple QTYs + Partial Gift Code Redemption + PayPal";
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("10004993- 2");
        Osprey_ReEu.addtocart("10004993- 2");
        Osprey_ReEu.search_product("10004999 - 2");
        Osprey_ReEu.Configurable_addtocart_pdp("10004999 - 2");
        Osprey_ReEu.search_product("10004966- 2");
        Osprey_ReEu.Configurable_addtocart_pdp("10004966- 2");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.addDeliveryAddress_Guestuser("Den Address");
        Osprey_ReEu.selectshippingmethod("GroundShipping method"); 
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        String Used_GiftCode=Osprey_ReEu.Gift_card("Partial Giftcard");
        String OrderNumber=Osprey_ReEu.payPal_Payment("PaypalDetails");
        Osprey_ReEu.Admin_signin("Login Details");
        Osprey_ReEu.click_Sales();
		HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
		Osprey_ReEu.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode);
 
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
