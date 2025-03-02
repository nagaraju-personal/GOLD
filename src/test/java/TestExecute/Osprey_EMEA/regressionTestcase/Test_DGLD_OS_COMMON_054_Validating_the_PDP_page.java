package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_054_Validating_the_PDP_page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"PDP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_PDP_Page () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");       
        Osprey_ReEu.Simple_PDP("Simple product");
        Osprey_ReEu.search_product("Product");  
        Osprey_ReEu.Configurable_PDP("Product");

  
        
 
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
