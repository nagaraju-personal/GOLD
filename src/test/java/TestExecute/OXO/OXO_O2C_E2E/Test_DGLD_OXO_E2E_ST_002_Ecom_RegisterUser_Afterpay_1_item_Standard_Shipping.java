package TestExecute.OXO.OXO_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.Common;
import TestLib.*;

public class Test_DGLD_OXO_E2E_ST_002_Ecom_RegisterUser_Afterpay_1_item_Standard_Shipping {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Afterpay_1_item_Standard_Shipping() throws Exception {
		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			Thread.sleep(5000);
			String Description ="RegisterUser_Afterpay_1_item_Standard_Shipping";
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_product("SKU-8710100 - 1QTY");
			Oxo.addtocart("SKU-8710100 - 1QTY");
			Oxo.minicart_Checkout();
		    Oxo.addDeliveryAddress_registerUser("AccountDetails");
		    String Used_GiftCode = "NULL";
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			String OrderNumber=Oxo.After_Pay_payment("Afterpay");
			Oxo.Admin_signin("Login Details");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode);
		
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


