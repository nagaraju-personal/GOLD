package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_045_Contactus_form {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"Forms");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Contactus_form() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.clickContact();
			Oxo.contactUsPage("contactusEmail");
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
		 String device=System.getProperty("dev","IOS");
			System.setProperty("configFile", "oxo/mobile_config.properties");
			if(device.equalsIgnoreCase("ios")) {
				System.setProperty("configFile", "oxo/mobile_config_ios.properties");
			}
			  Login.mobilesignIn(device);
			  Oxo.acceptPrivacy();
		}


}