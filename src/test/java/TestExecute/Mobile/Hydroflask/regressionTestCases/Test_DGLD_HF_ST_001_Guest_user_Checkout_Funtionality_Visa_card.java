package TestExecute.Mobile.Hydroflask.regressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_001_Guest_user_Checkout_Funtionality_Visa_card {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper_Mobile Hydro = new GoldHydroHelper_Mobile(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_Checkout_Funtionality_Visa_card () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");      
			Hydro.addtocart("Product");                    
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress("AccountDetails");
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");

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

     
        String device=System.getProperty("dev","andriod");
		System.setProperty("configFile", "Hydroflask/mobile_config.properties");
		if(device.equalsIgnoreCase("ios")) {
			System.setProperty("configFile", "Hydroflask/mobile_config_ios.properties");
		}
		  Login.mobilesignIn(device);
		   Hydro.close_add();

	}

}
