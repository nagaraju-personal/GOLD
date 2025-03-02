package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_136_Create_Edit_and_Delete_Gift_Registery_For_Register_User {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"GiftRegistry");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_Edit_and_Delete_Gift_Registery_For_Register_User () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("Giftaccount");
			Drybar.giftCreation("Birthday");
			Drybar.edit_gift("Giftaccount");
			Drybar.delete_giftcard();
			
			

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
