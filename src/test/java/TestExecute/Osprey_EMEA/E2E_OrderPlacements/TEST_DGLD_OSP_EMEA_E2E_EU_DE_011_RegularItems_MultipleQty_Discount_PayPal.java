package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2EHelper;
import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_EMEA_E2E_EU_DE_011_RegularItems_MultipleQty_Discount_PayPal {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_RegularItems_MultipleQty_Discount_Paypal_Payment () throws Exception {

		try {
			
			Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");
			String Description = "Europe_Germany Store  -Regular Items + Multiple Qty + Discount + PayPal";
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.search_product("SKU-10002926 -2QTY");
			Osprey_ReEu.simple_addtocart("SKU-10002926 -2QTY");
			Osprey_ReEu.search_product("SKU-10004993 -2QTY");
			Osprey_ReEu.simple_addtocart("SKU-10004993 -2QTY");
			Osprey_ReEu.minicart_Checkout();
			Osprey_ReEu.addDeliveryAddress_Guestuser("Europe_Germany-GuestAddress");
//			Osprey_ReEu.selectshippingmethod("GroundShipping method");
			String Used_GiftCode="NO";
			Osprey_ReEu.discountCode("Discount");
//			Osprey_ReEu.clickSubmitbutton_Shippingpage();
			String OrderNumber = Osprey_ReEu.payPal_Payment("PaypalDetails");
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
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
//		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
		
		String url = "https://mcloud-na-preprod.osprey.com/de/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
