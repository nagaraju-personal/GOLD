package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_033_Footer_Links_Validation {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"FooterLinks");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Footer_Links_Validation () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.Kustomer_Links("Kustomer");
			Hydro.Footer_Links("Footer");
			Hydro.Footer_validation("Breadcrumbs");
			Hydro.Footer_Dogood("Do Good");
			Hydro.Terms_and_privacy();
			
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
