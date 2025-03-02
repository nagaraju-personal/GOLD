package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Account_Information_062_AlternatePath_User_Changes_Password {
	String datafile = "OXO//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Account_Registration_Page_validations () throws Exception {
		try {
	       
			Oxo.validateCreateAccountpageNavigation();
			
			String Email=Oxo.ValidateAccountcreationeyeicon("AccountDetails");
			Oxo.Validate_Createaccount_signup_newsletter();			
			Oxo.Validate_Accountinformation_change_password("AccountDetails");
			Oxo.validate_Accountinformation_invalid_password("invalidpassword");
			Oxo.Changepassword_signin("invalidpassword");
		
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		  Oxo.AcceptAll();
		 
		  
	  }

	
	
	
	
	
	
	
	
	
	
}
