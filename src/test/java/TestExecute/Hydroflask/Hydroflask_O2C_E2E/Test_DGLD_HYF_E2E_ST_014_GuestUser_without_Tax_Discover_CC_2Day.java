package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_014_GuestUser_without_Tax_Discover_CC_2Day {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_GuestUser_without_Tax_Discover_CC_2Day () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Guest User without Tax - Discover CC - 2Day";
			Hydro.verifingHomePage();
			Hydro.search_product("SKU-TT40PS474");     
			Hydro.Configurable_addtocart_pdp("SKU-TT40PS474");
			Hydro.search_product("SKU-P-S21001 - 1"); 
			Hydro.Add_Myhydro("SKU-P-S21001 - 1");
			Hydro.search_product("SKU-SCS415 -1QTY");     
			Hydro.addtocart("SKU-SCS415 -1QTY");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("No Tax Address");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("2 Day method");
            String OrderNumber=Hydro.updatePaymentAndSubmitOrder("CCDiscovercard");
            Hydro.Admin_signin("Login Details");
            Hydro.click_Sales();
			HashMap<String,String> Orderstatus1 = Hydro.Admin_Order_Details(OrderNumber);
			Hydro.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode);

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
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}
