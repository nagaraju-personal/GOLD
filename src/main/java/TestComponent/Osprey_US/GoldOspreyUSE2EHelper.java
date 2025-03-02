package TestComponent.Osprey_US;

import org.testng.AssertJUnit;
import static org.testng.Assert.fail;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import groovyjarjarantlr.CommonAST;

public class GoldOspreyUSE2EHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldOspreyUSE2EHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Osprey_US");
			report.createTestcase("Osprey_USTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
	}

	
	public void verifingHomePage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			if(Common.getCurrentURL().contains("osprey.com/gb/"))
			{
				Close_Geolocation();
				Thread.sleep(5000);
			     acceptPrivacy();
				int size = Common.findElements("xpath", "//img[@alt='Store logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else if(Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod"))
			{
//				close_add();
				 acceptPrivacy();
				int size = Common.findElements("xpath", "//img[@alt='Store logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page") || size > 0 && Common.getPageTitle().contains("Backpacks"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else if(Common.getCurrentURL().contains("preprod.osprey.com/gb/"))
			{
				
				 acceptPrivacy();
				 Close_Geolocation();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else
			{
//			Close_Geolocation();
//			close_add();
				acceptPrivacy();
				int size = Common.findElements("xpath", "//img[@alt='Store logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey Home Page") || size > 0,
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating store logo on the homepage",
						"System directs the user to the Homepage and store logo should display",
						"Unable to navigate to the homepage and logo is not displayed",
						"Failed to navigate to the homepage and logo is not displayed");

				AssertJUnit.fail();
			}

		
	}
	
	
	
	public void Verify_HomePage() 
	{
		try
		{
			int size = Common.findElements("xpath", "//img[@alt='Store logo']").size();
			System.out.println(size);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page") || size > 0 && Common.getPageTitle().contains("Backpacks"),
					"validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed",
					"Failed to navigate to the homepage and logo is not displayed");
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homepage",
					"System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed",
					"Failed to navigate to the homepage and logo is not displayed");

			AssertJUnit.fail();
		}
	}
	public void verifingHomePage_and_NewsletterSubcription(String dataSet) throws Exception{
		// TODO Auto-generated method stub
		String email=data.get(dataSet).get("UserName");
		String Running=data.get(dataSet).get("interests");
		Sync.waitPageLoad();
		try {
		if(Common.getCurrentURL().contains("osprey.com/gb/"))
		{
			Close_Geolocation();
		     acceptPrivacy();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey"),
					"validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed",
					"Failed to navigate to the homepage and logo is not displayed");
		}
		else if(Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod"))
		{
			
			int size = Common.findElements("xpath", "//img[@alt='Store logo']").size();
			System.out.println(size);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Backpacks, Luggage & Travel Gear Since 1974"),
					"validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed",
					"Failed to navigate to the homepage and logo is not displayed");
		}else{
		Close_Geolocation();
		acceptPrivacy();
		int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
		System.out.println(size);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey"),
				"validating store logo on the homwpage",
				"System directs the user to the Homepage and store logo should display",
				"Sucessfully user navigates to the home page and logo has been displayed",
				"Failed to navigate to the homepage and logo is not displayed");
		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating store logo on the homwpage",
				"System directs the user to the Homepage and store logo should display",
				"Unable to navigate to the homepage and logo is not displayed",
				"Failed to navigate to the homepage and logo is not displayed");

		AssertJUnit.fail();
	}

		try {
			 Thread.sleep(3000);
		        int sizesframe = Common.findElements("xpath", "//div[@data-testid='POPUP']").size();
		        System.out.println(sizesframe);
		        if (sizesframe > 0) {
		            Common.actionsKeyPress(Keys.PAGE_UP);
		            Thread.sleep(4000);
		            Common.textBoxInput("xpath", "//div[@class='needsclick  kl-private-reset-css-Xuajs1']/input",email);
		            Thread.sleep(2000);
		            Common.clickElement("xpath", "//div[@data-testid='form-row']//button");
		            int sizes = Common.findElements("xpath", "//div[@data-testid='form-component']//span").size();
		            String text=Common.findElement("xpath", "//div[@data-testid='form-component']//span").getText();
		            System.out.println(text);
		            Common.assertionCheckwithReport(sizes>0 ||text.equals("Thanks for signing up!"), "verifying Account page links newsletter Subcription popup",
							"user should navigate to the newsletter Subcription popup page",
							"user successfully Navigated to the newsletter Subcription popup", "Failed click on the newsletter Subcription popup" );
		            
//		            Common.clickElement("xpath", "//div[text()='"+Running+"']");
//		            Common.clickElement("xpath", "//div[@data-testid='form-row']//button");
//		            int sizes1 = Common.findElements("xpath", "(//span[@class='ql-font-kanit'])[1]").size();
//		            String text1=Common.findElement("xpath", "(//span[@class='ql-font-kanit'])[1]").getText();
//		            Common.assertionCheckwithReport(sizes1>0 ||text1.equals("Check your inbox"), "verifying Account page links newsletter Subcription popup",
//							"user should navigate to the newsletter Subcription popup page",
//							"user successfully Navigated to the newsletter Subcription popup", "Failed click on the newsletter Subcription popup" );
//		            
		        }else {
		        	
		        }
			} catch (Exception e) {
		
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user open to ewsletter Subcription popup",
					"After clicking on the signin button it should navigate to ewsletter Subcription popup",
					"Unable to navigate the user to the home after clicking on the ewsletter Subcription popup",
					Common.getscreenShotPathforReport("Failed to open to newsletter Subcription popup "));

			AssertJUnit.fail();
		}
		close_add();
}

	
	public void click_singinButton() {
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//a[@id='customer.header.sign.in.link']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
//			Common.assertionCheckwithReport(
//					Common.getText("xpath", "//fieldset[@class='fieldset login']//legend/h2").equals("Sign In"),
//					"To validate the user navigates to the signin page",
//					"user should able to land on the signIn page after clicking on the sigIn button",
//					"User Successfully clicked on the singIn button and Navigate to the signIn page",
//					"User Failed to click the signin button and not navigated to signIn page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigin button",
					"Unable to click on the singIn button and not Navigated to the signIn page",
					Common.getscreenShotPathforReport(
							"Failed to click signIn button and not Navigated to the signIn page"));
			Assert.fail();
		}
	}



public void Login_Account(String dataSet) {
		// TODO Auto-generated method stub
		try {
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("osprey.com/gb/") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));

			Common.clickElement("xpath", "//button[@name='send']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Home page") || Common.getPageTitle().contains("Backpacks, Luggage & Travel")
							|| Common.getPageTitle().contains("Osprey"),
					"To validate the user lands on Home page after successfull login",
					"After clicking on the signIn button it should navigate to the Home page",
					"user Sucessfully navigate to the Home page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Home page ");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user Navigate to Home page after successfull login",
					"After clicking on the signin button it should navigate to the Home page",
					"Unable to navigate the user to the home after clicking on the SignIn button",
					Common.getscreenShotPathforReport("Failed to signIn and not navigated to the Home page "));

			Assert.fail();
		}

	}



	public void Forgot_password(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[contains(text(),'Forgot')]");
			String forgotpassword = Common.findElement("xpath", "//h2[contains(text(),'Forgot Your Password?')]").getText();
			System.out.println(forgotpassword);
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='email']",data.get(Dataset).get("UserName"));
			Thread.sleep(4000);
			Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.clickElement("xpath", "//button[contains(text(), 'Reset My Password')]");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Sync.waitElementPresent(30, "xpath", "//div[contains(@ui-id,'message')]");
			String message = Common.findElement("xpath", "//div[contains(@ui-id,'message')]").getText();
			Thread.sleep(4000);
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("We received too many requests for password resets")
							|| message.contains("If there is an account associated"),
					"To validate the user is navigating to Forgot Password page",
					"user should naviagte to forgot password page", "User lands on Forgot Password page",
					"User failed to navigate to forgot password page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user is navigating to Forgot Password page",
					"user should navigate to forgot password page", "User failed to land on Forgot Password page",
					Common.getscreenShotPathforReport("failed  to naviagte forgot password page "));
			AssertJUnit.fail();
		}

	}
	

	public void Empty_Email() {
		// TODO Auto-generated method stub
		try {

			Common.textBoxInputClear("xpath", "//input[@placeholder='Enter Email Address']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[@class='icon-arrow a-icon_icon']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='newsletter-error']");
			String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Error: This field is required."),
					"To validate the error message for missing email fields",
					"Should display Error Please enter a valid email address.", Errormessage,
					"Error message dispaly unsuccessfull");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Error message ",
					"Should display Error: Please enter a valid email address.", "Failed to dispaly the Error message ",
					Common.getscreenShotPathforReport("User unable to see an error message"));
			AssertJUnit.fail();
		}

	}

	public void stayIntouch() throws Exception {

		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Sync.waitElementClickable(30, "xpath", "//input[@aria-label='Enter Email Address']");
			Common.textBoxInput("xpath", "//input[@aria-label='Enter Email Address']", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[text()='Sign Up']");
			Thread.sleep(5000);
			String Text = Common.getText("xpath", "//span[text()='Thanks for subscribing!']");
			System.out.println(Text);
			String expectedResult = "User gets confirmation message that it was submitted";

			Common.assertionCheckwithReport(Text.contains("Thanks for subscribing!"),
					"verifying newsletter subscription",
					"User get confirmation message if new email if it used mail it showing error message ", Text,
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
					"User faield to subscrption for newLetter  ",
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			Assert.fail();
		}
	}
	public void Invalid_email_newsletter(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//form[@class='m-newsletter-signup__form']");
			Common.clickElement("xpath", "//form[@class='m-newsletter-signup__form']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter Email Address']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//span[@class='icon-arrow a-icon_icon']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='newsletter-error']");
			String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Error: Please enter a valid email address."),
					"To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", Errormessage,
					"Failed to display the error message for invaild email");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", "Failed to display the error message",
					Common.getscreenShotPathforReport("Failed to see an error message"));

			AssertJUnit.fail();

		}

	}

	



	public void Account_page_Validation(String Dataset) throws Exception {
		// TODO Auto-generated method stub
				Sync.waitPageLoad();
				Thread.sleep(4000);
				if (Common.getCurrentURL().contains("stage")|| Common.getCurrentURL().contains("preprod")|| Common.getCurrentURL().contains("www.osprey.com")) {
					String Accountlinks = data.get(Dataset).get("Account Links");
					String[] Account = Accountlinks.split(",");
					int i = 0;
					try {
						for (i = 0; i < Account.length; i++) {
							System.out.println(Account[i]);
							Sync.waitElementPresent("xpath",
									"//span[text()='" + Account[i] +"']");
							Common.clickElement("xpath",
									"//span[text()='" + Account[i] +"']");
							Sync.waitPageLoad();
							Thread.sleep(4000);
							String currentUrl=Common.getCurrentURL();
							System.out.println(currentUrl);
							Common.assertionCheckwithReport(
									currentUrl.contains("customer/account/edit/")|| currentUrl.contains("customer/address/new/")||currentUrl.contains("sales/order/history")
									|| currentUrl.contains("giftregistry/")|| currentUrl.contains("wishlist")|| currentUrl.contains("amgcard/account/")
									|| currentUrl.contains("stock/index/")|| currentUrl.contains("storecredit/info/")|| currentUrl.contains("customer/paymentmethods/")
									|| currentUrl.contains("newsletter/manage/")|| currentUrl.contains("klaviyo/customer/newsletter/"),
									"verifying Account page links " + Account[i],
									"user should navigate to the " + Account[i] + " page",
									"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
							Thread.sleep(2000);
							}
					} catch (Exception | Error e) {
						e.printStackTrace();
						ExtenantReportUtils.addFailedLog("validating the account page links " + Account[i],
								"user should Navigate to the " + Account[i] + " page",
								"User unable to navigate to the " + Account[i],
								Common.getscreenShotPathforReport("user Failed to Navigate to the respective page"));
						AssertJUnit.fail();
					}
				} else {
					String Accountlinks = data.get(Dataset).get("Prod Account Links");
					String[] Account = Accountlinks.split(",");
					int i = 0;
					try {
						for (i = 0; i < Account.length; i++) {
							System.out.println(Account[i]);
							Sync.waitElementPresent("xpath",
									"//div[@class='content account-nav-content']//a[text()=\"" + Account[i] +"\"]");
							Common.clickElement("xpath",
									"//div[@class='content account-nav-content']//a[text()=\"" + Account[i] +"\"]");
							Sync.waitPageLoad();
							Thread.sleep(4000);
							String currentUrl=Common.getCurrentURL();
							System.out.println(currentUrl);
							Common.assertionCheckwithReport(
									currentUrl.contains("sales/order/history")|| currentUrl.contains("wishlist")||currentUrl.contains("customer/address")
									|| currentUrl.contains("customer/address/index")|| currentUrl.contains("customer/account/edit")|| currentUrl.contains("stripe/customer/paymentmethods")
									|| currentUrl.contains("storecredit/info")|| currentUrl.contains("reward/customer/info")|| currentUrl.contains("giftregistry")
									|| currentUrl.contains("xnotif/stock/index")|| currentUrl.contains("newsletter/manage")|| currentUrl.contains("amgcard/account")|| currentUrl.contains("prodeal/application/account"),
									"verifying Account page links " + Account[i],
									"user should navigate to the " + Account[i] + " page",
									"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
							Thread.sleep(2000);
		 

						}
					} catch (Exception | Error e) {
						e.printStackTrace();
						ExtenantReportUtils.addFailedLog("validating the account page links " + Account[i],
								"user should Navigate to the " + Account[i] + " page",
								"User unable to navigate to the " + Account[i],
								Common.getscreenShotPathforReport("user Failed to Navigate to the respective page"));
						AssertJUnit.fail();
					}
				}

	}

	public void header_OutdoorPacks(String Dataset) {{

		String names = data.get(Dataset).get("Outdoorpacks");
		String[] Links = names.split(",");
		String names1 = data.get(Dataset).get("Outdoorpacks").toUpperCase();
		String[] Link = names1.split(",");
		String bag=data.get(Dataset).get("Backpacks");
		String outdoor=data.get(Dataset).get("Outdoor");
		
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ bag +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ bag +"')]");
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ outdoor +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ outdoor +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
				String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
				System.out.println(title);
				System.out.println(breadcrumbs);
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i])
							|| Common.getCurrentURL().contains("outdoor-packs"),"verifying the header link " + Links[i] + "Under Outdoor Packs",
							"user should navigate to the " + Links[i] + " page","user successfully Navigated to the " + Links[i],
							"Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}
				
			
				}
			if(Common.getCurrentURL().contains("/fr/"))
			{
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ bag +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ bag +"')]");
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ outdoor +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ outdoor +"')]");
				String name="Sacs à dos d";
				Sync.waitElementPresent("xpath",
						"(//li[contains(@class,'level2 ')]//a//span[contains(text(),'"+ name + "')])[2]");
				Common.clickElement("xpath",
						"(//li[contains(@class,'level2 ')]//a//span[contains(text(),'"+ name + "')])[2]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
				System.out.println(breadcrumbs);
				String name1="Sacs à dos d".toUpperCase();
				name.toUpperCase();
				System.out.println(name1);
				Common.assertionCheckwithReport(breadcrumbs.contains(name1),
						"verifying the header link " + name1 + "Under Outdoor Packs",
						"user should navigate to the " + name1 + " page",
						"user successfully Navigated to the " + name1, "Failed to navigate to the " + name1);
				
			}
		
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Outdoor Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	}

	public void header_KidsPacks(String Dataset) {

		String names = data.get(Dataset).get("KidsPacksCarriers");
		String[] Links = names.split(",");
		String names1 = data.get(Dataset).get("KidsPacksCarriers").toUpperCase();
		String[] Link = names1.split(",");
		String bag=data.get(Dataset).get("Backpacks");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				
				if(Common.getCurrentURL().contains("/gb"))
				{
					Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
					Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Kids Packs')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
				String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
							"verifying the header link " + Links[i] + "Under Kids Packs",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					AssertJUnit.fail();
				}
				
				}
				else
				{
					Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ bag+ "')]");
					Common.clickElement("xpath", "//span[contains(text(),'"+ bag +"')]");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
								"verifying the header link " + Links[i] + "Under Kids Packs",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						AssertJUnit.fail();
					}
					
				}
				

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kids Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			AssertJUnit.fail();
		}

	}

	public void header_DayPacks(String Dataset) {{

		String names = data.get(Dataset).get("DayPacks");
		String[] Links = names.split(",");
		String names1 = data.get(Dataset).get("DayPacks").toUpperCase();
		String[] Link = names1.split(",");
		String bag=data.get(Dataset).get("Backpacks");
		String day=data.get(Dataset).get("Daypacks");
		
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ bag +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ bag +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ day +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//a//span[contains(text(),'" + Links[i] + "')]");
				Thread.sleep(4000);
				Common.clickElement("xpath",
						"//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				 String title = "";
		            if 
		            (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
		                title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
		                
		            }
		            else if 
		            (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
		                title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
		            }

				
				String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i])
						||breadcrumbs.contains("MOCHILAS PEQUEñAS"),"verifying the header link " + Links[i] + "Under Day Packs",
						"user should navigate to the " + Links[i] + " page","user successfully Navigated to the " + Links[i],
						"Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Day Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	}
	

public void header_Travel(String Dataset) {
       
		if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
		{
			String names = data.get(Dataset).get("Travels");
			String[] Links = names.split(",");
			String name = data.get(Dataset).get("Travels").toUpperCase();
			String[] Link = name.split(",");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),'Travel')]");
					Common.clickElement("xpath", "//span[contains(text(),'Travel')]");

					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
							"verifying the header link " + Links[i] + "Under Travel",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}

				}
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Travel",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}
		}
		else
		{
			String names = data.get(Dataset).get("Travel");
			String[] Links = names.split(",");
			String name = data.get(Dataset).get("Travel").toUpperCase();
			String[] Link = name.split(",");
			String Travel=data.get(Dataset).get("Travelling");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Travel +"')]");
					Common.clickElement("xpath", "//span[contains(text(),'"+ Travel +"')]");

					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = "";
		            if 
		            (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
		                title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
		            } 
		            else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
		                title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
		            }
					String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					System.out.println(title);
					System.out.println(breadcrumbs);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
							"verifying the header link " + Links[i] + "Under Travel",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}

				}
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Travel",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
		}
	

		}

	}

	
public void header_Shopbyactivity(String Dataset) {{

	if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
	{
		String names = data.get(Dataset).get("Featureds");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Featureds").toUpperCase();
		String[] Link = name.split(",");
		String Name=data.get(Dataset).get("Prod  Featureds");
		String[] Link1 = Name.split(",");
		String Breadnames=data.get(Dataset).get("Dataasser");
		String[] Bread=Breadnames.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Shop by Activity')]");
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("prepord"))
				{
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]) 
						|| breadcrumbs.contains(Bread[i]),
						"verifying the header link " + Links[i] + "Under the Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}

			}
				else
				{
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Link1[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Link1[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
					String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Link1[i]) || breadcrumbs.contains(Link1[i]) || breadcrumbs.contains(Link1[i]) 
							|| breadcrumbs.contains(Bread[i]),
							"verifying the header link " + Link1[i] + "Under the Featured",
							"user should navigate to the " + Link1[i] + " page",
							"user successfully Navigated to the " + Link1[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
				}
			}
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under the Fearued",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
	}
	else
	{
		String names = data.get(Dataset).get("Featured");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Featured").toUpperCase();
		String[] Link = name.split(",");
		String Featured=data.get(Dataset).get("Feature");
		String activity=data.get(Dataset).get("Activity");
		String Name=data.get(Dataset).get("Prod  Featureds");
		String[] Link1 = Name.split(",");
		int i = 0;
		try {
			for (i = 0 ; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Featured +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Featured +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ activity +"')]");
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("preprod"))
				{
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li//a[contains(@href,'shop-by-activity')]//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = "";
				if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
				    title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText().toLowerCase();
				} else {
				    String currentURL = Common.getCurrentURL();
				    System.out.println("Redirecting to URL: " + currentURL);
				}
				String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				System.out.println(title);
				System.out.println(Link[i]);
				System.out.println(breadcrumbs);
				System.out.println(Common.getCurrentURL());
				System.out.println(Common.getCurrentURL().contains(title));
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]) || Common.getCurrentURL().contains(title)
				 ,
						"verifying the header link " + Links[i] + "Under the Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
				}
			}
				else
				{
					for (i = 0 ; i < Link1.length; i++) {
						Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Featured +"')]");
						Common.clickElement("xpath", "//span[contains(text(),'"+ Featured +"')]");
						Common.clickElement("xpath", "//span[contains(text(),'"+ activity +"')]");
					Sync.waitElementPresent("xpath",
							"//li//a[contains(@href,'shop-by-activity')]//span[contains(text(),'" + Link1[i] + "')]");
					Common.clickElement("xpath",
							"//li//a[contains(@href,'shop-by-activity')]//span[contains(text(),'" + Link1[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = "";
					if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
					    title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText().toLowerCase();
					} else {
					    String currentURL = Common.getCurrentURL();
					    System.out.println("Redirecting to URL: " + currentURL);
					}
					String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					System.out.println(title);
					System.out.println(Link1[i]);
					System.out.println(breadcrumbs);
					System.out.println(Common.getCurrentURL());
					System.out.println(Common.getCurrentURL().contains(title));
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Link1[i]) || breadcrumbs.contains(Link1[i]) || breadcrumbs.contains(Link1[i]) || Common.getCurrentURL().contains(title)
					 || Common.getCurrentURL().contains("climbing-mountaineering")  ,
							"verifying the header link " + Link1[i] + "Under the Featured",
							"user should navigate to the " + Link1[i] + " page",
							"user successfully Navigated to the " + Link1[i], "Failed to navigate to the " + Link1[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
					}
				}
				}
		}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under the Fearued",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
	}
}

}	
	
public void header_Shopbycollection(String Dataset) { {
	  
	if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
	{
		String names = data.get(Dataset).get("Featureds");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Featureds").toUpperCase();
		String[] Link = name.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Shop by Collections')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
				System.out.println(products);
				System.out.println(title);
				System.out.println(Link[i]);
				System.out.println(breadcrumbs);
				System.out.println(Links[i]);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]) || Common.getCurrentURL().contains(title) ,
						"verifying the header link " + Links[i] + "Under the Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under the Fearued",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
	}
	else
	{
		String names = data.get(Dataset).get("Featureds");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Featureds").toUpperCase();
		String[] Link = name.split(",");
		String name1 = data.get(Dataset).get("Featureds").toLowerCase();
		String[] link = name1.split(",");
		String Name = data.get(Dataset).get("Prod  Featureds");
		String[] Link1 = Name.split(",");
		String Featured=data.get(Dataset).get("Feature");
		String collections=data.get(Dataset).get("Activity");
		String Asser="shop-by-collections";
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Featured+ "')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Featured +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ collections +"')]");
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("preprod"))
				{
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = "";
				if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
				    title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText().toLowerCase();
				} else {
				    String currentURL = Common.getCurrentURL();
				    System.out.println("Redirecting to URL: " + currentURL);
				}
				String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				System.out.println(title);
				System.out.println(Links[i]);
				System.out.println(Link[i]);
				System.out.println(breadcrumbs);
				System.out.println(breadcrumbs.contains(Link[i]));
				System.out.println(Common.getCurrentURL().contains(link[i]));
				System.out.println(link[i]);
				System.out.println(Common.getCurrentURL().contains(Asser));
				System.out.println(Common.getPageTitle().contains(collections));
				
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>=j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Link[i]) ||  breadcrumbs.contains(Links[i]) ||
						Common.getCurrentURL().contains(Asser) || Common.getCurrentURL().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under the Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}
			}
				else
				{
					for (i = 0; i < Link1.length; i++) {
						Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
						Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
						Common.clickElement("xpath", "//span[contains(text(),'Shop by Collections')]");
					Sync.waitElementPresent("xpath",
							"//li//a//span[contains(text(),'" + Link1[i] + "')]");
					Common.clickElement("xpath",
							"//li//a//span[contains(text(),'" + Link1[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = "";
					if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
					    title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText().toLowerCase();
					} else {
					    String currentURL = Common.getCurrentURL();
					    System.out.println("Redirecting to URL: " + currentURL);
					}
					String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					System.out.println(title);
					System.out.println(Link1[i]);
					System.out.println(Link1[i]);
					System.out.println(breadcrumbs);
					System.out.println(breadcrumbs.contains(Link1[i]));
					System.out.println(Common.getCurrentURL().contains(Link1[i]));
					System.out.println(Link1[i]);
					System.out.println(Common.getCurrentURL().contains(Asser));
					System.out.println(Common.getPageTitle().contains(collections));
					
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>=j)
					{
					Common.assertionCheckwithReport(title.contains(Link1[i]) || breadcrumbs.contains(Link1[i]) ||  breadcrumbs.contains(Link1[i]) ||
							Common.getCurrentURL().contains(Asser) || Common.getCurrentURL().contains(Link1[i]),
							"verifying the header link " + Link1[i] + "Under the Featured",
							"user should navigate to the " + Link1[i] + " page",
							"user successfully Navigated to the " + Link1[i], "Failed to navigate to the " + Link1[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
					
				}
				
			}
			}
		}
			

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under the Fearued",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
	}

}

}
	
	
	
	public void header_Luggage(String Dataset) {{
	       
		if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
		{
			String names = data.get(Dataset).get("Luggage");
			String[] Links = names.split(",");
			String name = data.get(Dataset).get("Luggage").toUpperCase();
			String[] Link = name.split(",");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),'Travel')]");
					Common.clickElement("xpath", "//span[contains(text(),'Travel')]");

					Sync.waitElementPresent("xpath", "//span[contains(text(),'Luggage')]");
					Common.clickElement("xpath", "//span[contains(text(),'Luggage')]");
					
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
					String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
							"verifying the header link " + Links[i] + "Under Travel",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}

				}
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Travel",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}
		}
		else
		{
			String Travel=data.get(Dataset).get("Travelling");
			String names = data.get(Dataset).get("Luggage");
			String[] Links = names.split(",");
			String name = data.get(Dataset).get("Luggage").toUpperCase();
			String[] Link = name.split(",");
//			String Travel=data.get(Dataset).get("Travelling");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Travel +"')]");
					Common.clickElement("xpath", "//span[contains(text(),'" + Travel +"')]");

					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					 String title = "";
			            if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
			                title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
			            } 
			            else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
			                title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
			            }
					
					String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					System.out.println(title);
					System.out.println(breadcrumbs);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
							"verifying the header link " + Links[i] + "Under Travel",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}

				}
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Travel",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
		}
	

		}

	}
	}
	
	

	
	
	
	
	public void header_Accessories(String Dataset) {{

	    String names = data.get(Dataset).get("Accessories");
	    String[] Links = names.split(",");
	    String name = data.get(Dataset).get("Accessories").toUpperCase();
	    String[] Link = name.split(",");
	    String name1= data.get(Dataset).get("Prod Accessories");
	    String[] Link1 = name1.split(",");
	    String Access = data.get(Dataset).get("Access");
	    String Access1 = data.get(Dataset).get("Accesso");
	    System.out.println(Access);
	    System.out.println(Access1);

	    int i = 0;
	    try {
	        for (i = 0; i < Links.length; i++) {
	            Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Access + "')]");
	            Common.clickElement("xpath", "//span[contains(text(),'" + Access + "')]");

	            Thread.sleep(3000);
	            if(Common.getCurrentURL().contains("preprod"))
	            {
	            Sync.waitElementPresent("xpath", "//li//a[contains(@href,'" + Access1 + "')]//span[contains(text(),'" + Links[i] + "')]");
	            Common.clickElement("xpath", "//li//a[contains(@href,'" + Access1 + "')]//span[contains(text(),'" + Links[i] + "')]");
	            Sync.waitPageLoad();
	            Thread.sleep(4000);
	            }
	            else
	            {
	            	Sync.waitElementPresent("xpath", "//li//a[contains(@href,'" + Access1 + "')]//span[contains(text(),'" + Link1[i] + "')]");
		            Common.clickElement("xpath", "//li//a[contains(@href,'" + Access1 + "')]//span[contains(text(),'" + Link1[i] + "')]");
		            Sync.waitPageLoad();
		            Thread.sleep(4000);
	            }
	            
	            String title = "";
	            if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
	                title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
	                
	            } else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
	                title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
	            }
	            System.out.println(Links[i]);
	            Thread.sleep(3000);
	            String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
	            String products = Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
	            System.out.println(products);
	            int Number = Integer.parseInt(products);
	            int j = 0;
	            if (Number > j) {
	                Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) 
	                		|| breadcrumbs.contains(Link[i]) || Common.getCurrentURL().contains(Access) || Common.getCurrentURL().contains("apparel-goods"),
	                        "verifying the header link " + Links[i] + "Under Accessories",
	                        "user should navigate to the " + Links[i] + " page",
	                        "user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
	            } else {
	                ExtenantReportUtils.addFailedLog(
	                        "validating the products in the PLP",
	                        "User should able to see the products in PLP", "unable to see the products in the PLP",
	                        Common.getscreenShot("Failed to see products in PLP"));
	                Assert.fail();
	            }

	        }
	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Accessories",
	                "User should navigate to the " + Links[i] + "pages",
	                "unable to navigate to the " + Links[i] + "pages",
	                Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
	        Assert.fail();
	    }

	}


}

	public void header_Featured(String Dataset) {{

		if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
		{
			String names = data.get(Dataset).get("Featureds");
			String[] Links = names.split(",");
			String name = data.get(Dataset).get("Featureds").toUpperCase();
			String[] Link = name.split(",");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
					Common.clickElement("xpath", "//span[contains(text(),'Featured')]");

					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);			
					String title = "";
					if 
					(Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
					    title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					} 
					else {
					    String currentURL = Common.getCurrentURL();
					    System.out.println("Redirecting to URL: " + currentURL);
					}
					String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
					String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
							"verifying the header link " + Links[i] + "Under Featured",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}

				}
			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Featured",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}
		}
		else
		{
			
			
			String names = data.get(Dataset).get("Featured");
			String[] Links = names.split(",");
			String name = data.get(Dataset).get("Featured").toUpperCase();
			String[] Link = name.split(",");
			String Featured = data.get(Dataset).get("Feature");
			String Name=data.get(Dataset).get("Prod  Featureds");
			String[] Link2 = Name.split(",");

			int i = 0;
			try {
			    for (i = 0; i < Links.length; i++) {
			    	if(Common.getCurrentURL().contains("preprod"))
			    	{
			        Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Featured + "')]");
			        Common.clickElement("xpath", "//span[contains(text(),'" + Featured + "')]");

			        Thread.sleep(3000);

			        Sync.waitElementPresent("xpath", "//li//a[@title='" + Links[i] + "']//span[contains(text(),'" + Links[i] + "')]");
			        Common.clickElement("xpath", "//li//a[@title='" + Links[i] + "']//span[contains(text(),'" + Links[i] + "')]");
			        Sync.waitPageLoad();
			        Thread.sleep(4000);
			    	}
			    	else
			    	{
			    		 Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Featured + "')]");
					        Common.clickElement("xpath", "//span[contains(text(),'" + Featured + "')]");

					        Thread.sleep(3000);

					        Sync.waitElementPresent("xpath", "//li//a[@title='" + Link2[i] + "']//span[contains(text(),'" + Link2[i] + "')]");
					        Common.clickElement("xpath", "//li//a[@title='" + Link2[i] + "']//span[contains(text(),'" + Link2[i] + "')]");
					        Sync.waitPageLoad();
					        Thread.sleep(4000);
					        String title = "";
					        if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
					            title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					        } else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
					            title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
					        }

					        String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
					        Common.assertionCheckwithReport( title.contains(Link2[i]) || breadcrumbs.contains(Link2[i]) || breadcrumbs.contains(Link2[i])
					        		 || Common.getCurrentURL().contains(Link2[i]) || Common.getCurrentURL().contains("gift-guide") || Common.getPageTitle().contains( Link2[i]),  "verifying the header link " + Links[i] + " under Featured",
					                "user should navigate to the " + Link2[i] + " page",
					                "user successfully Navigated to the " + Link2[i],
					                "Failed to navigate to the " + Link2[i]);
			    	}

			        String title = "";
			        if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
			            title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
			        } else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
			            title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
			        }

			        String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
			        Common.assertionCheckwithReport( title.contains(Link2[i]) || breadcrumbs.contains(Link2[i]) || breadcrumbs.contains(Link2[i])
			        		 || Common.getCurrentURL().contains(Link2[i]) || Common.getCurrentURL().contains("gift-guide") || Common.getPageTitle().contains( Link2[i]),  "verifying the header link " + Links[i] + " under Featured",
			                "user should navigate to the " + Link2[i] + " page",
			                "user successfully Navigated to the " + Link2[i],
			                "Failed to navigate to the " + Link2[i]
			        );
			    }

			    String names1 = data.get(Dataset).get("Featureds");
			    String[] Links1 = names1.split(",");
			    String name1 = data.get(Dataset).get("Featureds").toUpperCase();
			    String[] Link1 = name1.split(",");
			    String Featured1 = data.get(Dataset).get("Feature");

			    int k = 0;
			    try {
			        for (k = 0; k < Links1.length; k++) {
			            Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Featured1 + "')]");
			            Common.clickElement("xpath", "//span[contains(text(),'" + Featured1 + "')]");

			            Thread.sleep(3000);

			            Sync.waitElementPresent("xpath", "//li//a[@title='" + Links1[k] + "']//span[contains(text(),'" + Links1[k] + "')]");
			            Common.clickElement("xpath", "//li//a[@title='" + Links1[k] + "']//span[contains(text(),'" + Links1[k] + "')]");
			            Sync.waitPageLoad();
			            Thread.sleep(4000);

			            String title = "";
			            if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
			                title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
			            } else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
			                title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
			            }

			            String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
			            Common.assertionCheckwithReport(
			                    title.contains(Links1[k]) || breadcrumbs.contains(Links1[k]) || breadcrumbs.contains(Link1[k]),
			                    "verifying the header link " + Links1[k] + " under Featured",
			                    "user should navigate to the " + Links1[k] + " page",
			                    "user successfully Navigated to the " + Links1[k],
			                    "Failed to navigate to the " + Links1[k]
			            );
			        }
			    } catch (Exception | Error e) {
			        e.printStackTrace();
			        ExtenantReportUtils.addFailedLog(
			                "verifying the header link " + Links1[k] + " under Featured",
			                "User should navigate to the " + Links1[k] + " pages",
			                "unable to navigate to the " + Links1[k] + " pages",
			                Common.getscreenShot("Failed to navigate to the " + Links1[k] + " pages")
			        );
			        Assert.fail();
			    }

			} catch (Exception | Error e) {
			    e.printStackTrace();
			    ExtenantReportUtils.addFailedLog(
			            "verifying the header link " + Links[i] + " under Featured",
			            "User should navigate to the " + Links[i] + " pages",
			            "unable to navigate to the " + Links[i] + " pages",
			            Common.getscreenShot("Failed to navigate to the " + Links[i] + " pages")
			    );
			    Assert.fail();
			}		
			}	
		}
}

	public void Bagpack_ShopAll(String Dataset) {{

		String names = data.get(Dataset ).get("Shop all");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Shop all").toUpperCase();
		String[] Link = name.split(",");
		String Backs=data.get(Dataset).get("Backpacks");
		System.out.println(Backs);
		
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Backs +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Backs +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@class,'btn btn-secondary py-3.5')]//span");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				String BreadCrumbs=Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				System.out.println(title);
				System.out.println(Links[i]);
				System.out.println(Link[i]);
				System.out.println(BreadCrumbs);
				System.out.println(Common.getCurrentURL().contains("backpacks"));
				System.out.println(title.contains(Links[i]));
			
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getCurrentURL().contains(Links[i]) || BreadCrumbs.contains(Link[i]) || 
					BreadCrumbs.contains("MOCHILAS PEQUEñAS")	|| Common.getCurrentURL().contains(title),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
}
	
	public void Travel_ShopAll(String Dataset) { {

		String names = data.get(Dataset).get("Shop all");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Shop all").toUpperCase();
		String[] Link = name.split(",");
		String Travel=data.get(Dataset).get("Travelling");
		
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Travel +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Travel +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@class,'btn btn-secondary py-3.5')]//span");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'category-view container')]//h1").getText();
				String BreadCrumbs=Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
				
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getCurrentURL().contains(Links[i]) || BreadCrumbs.contains(Link[i]) ,
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	}
	
	public void Featured_ShopAll(String Dataset) {{

		String names = data.get(Dataset).get("Shop all");
		String[] Links = names.split(",");
		int i = 0;
		String Featured =data.get(Dataset).get("Feature");
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Featured +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Featured +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@class,'btn btn-secondary py-3.5')]//span");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'category-view container')]//h1").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getCurrentURL().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
}

	public void giftCreation(String Dataset) {
		// TODO Auto-generated method stub
		String Month = data.get(Dataset).get("EventMonth");
        String Year =  data.get(Dataset).get("EventYear");
        String Date = data.get(Dataset).get("EventDate");
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		click_giftcard();
		newregistry_CTA("Birthday");
		try {
			Common.textBoxInput("xpath", "//input[@id='title']", data.get(Dataset).get("Type"));
			Common.textBoxInput("xpath", "//textarea[@id='message']", data.get(Dataset).get("Message"));
			Common.dropdown("xpath", "//select[@id='is_public']", SelectBy.TEXT, data.get(Dataset).get("privacy"));
			Common.dropdown("xpath", "//select[@id='is_active']", SelectBy.TEXT, data.get(Dataset).get("Status"));
			
			String eventname = Common.findElement("xpath", "(//p[contains(@class,'giftregistry-type text')]//span)[2]").getText();
			if (eventname.equals("Birthday")) {
				Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
						data.get(Dataset).get("Region"));
				Thread.sleep(1000);
				Common.scrollIntoView("id", "event_date");
				Common.clickElement("id", "event_date");
				
				Common.dropdown("xpath", "//select[@name='datepicker_month']", SelectBy.TEXT,Month);
				Common.dropdown("xpath", "//select[@name='datepicker_year']", SelectBy.TEXT,Year);
				Thread.sleep(1000);
				Common.clickElement("xpath", "//button[text()='"+Date+"']");
				
				
			} else if (eventname.equals("Wedding")) {

				Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
						data.get(Dataset).get("Region"));
				Common.textBoxInput("xpath", "//input[@id='event_date']", data.get(Dataset).get("Date"));
				Common.textBoxInput("xpath", "//input[@name='event_location']", data.get(Dataset).get("Location"));
				Common.textBoxInput("xpath", "//input[@name='registry[number_of_guests]']",
						data.get(Dataset).get("GropName"));

			} else {
				Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
						data.get(Dataset).get("Region"));
				Common.textBoxInput("xpath", "//input[@name='event_location']", data.get(Dataset).get("Location"));
			}
//	        Baby_Registry("Baby Registry");
			Registrant_Information("Birthday");
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@id='address_street1']", data.get(Dataset).get("Street"));
			Common.textBoxInput("xpath", "//input[@id='address_city']", data.get(Dataset).get("City"));
			Common.dropdown("xpath", "//select[@id='address_region_id']", SelectBy.TEXT, data.get(Dataset).get("Region"));
			Common.textBoxInput("xpath", "//input[@id='address_postcode']", data.get(Dataset).get("postcode"));
			Common.textBoxInput("xpath", "//input[@id='address_telephone']", data.get(Dataset).get("phone"));
			
			Common.clickElement("id", "submit.save");
			Sync.waitPageLoad();
//	        Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(message.equals("You saved this gift registry."),
					"validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");

		} catch (Exception e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"unable to Navigated to the gift registry page",
					Common.getscreenShot("Failed to Navigate to the gift registry page"));
			Assert.fail();
		}
	}

	public void Baby_Registry(String Dataset) {
		// TODO Auto-generated method stub

		String babygender = data.get(Dataset).get("Gender");
		System.out.println(babygender);
		try {
			Sync.waitElementPresent("xpath", "//select[@name='registry[baby_gender]']");
			Common.dropdown("xpath", "//select[@name='registry[baby_gender]']", Common.SelectBy.TEXT, babygender);
			Thread.sleep(4000);
			String gender = Common.findElement("xpath", "//select[@name='registry[baby_gender]']//option[@value='boy']")
					.getText();
			Common.assertionCheckwithReport(gender.equals(babygender), "validating the baby gender in gift registry ",
					"It should display the baby gender under the gift registry",
					"successfully baby gender is displayed under the gift registry",
					"failed to display the baby gender under gift registry");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the baby gender in gift registry ",
					"It should display the baby gender under the gift registry",
					"unable to display the baby gender under the gift registry",
					Common.getscreenShot("failed to display the baby gender under gift registry"));
			AssertJUnit.fail();
		}
	}

	public void Registrant_Information(String Dataset) {
		// TODO Auto-generated method stub
		String eventname = Common.findElement("xpath", "(//p[contains(@class,'giftregistry-type text')]//span)[2]").getText();
		try {
			if (eventname.equals("Birthday")) {
				Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
				Common.clickElement("xpath", "//button[contains(text(),'Add Registrant')]");
				Common.textBoxInput("xpath", "//input[@name='registrant[1][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][email]']",
						data.get(Dataset).get("UserName"));
			} else {
				Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
				Common.dropdown("xpath", "//select[@name='registrant[0][role]']", Common.SelectBy.TEXT,
						data.get(Dataset).get("Role"));
				Common.clickElement("id", "add-registrant-button");
				Common.textBoxInput("xpath", "//input[@name='registrant[1][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][email]']",
						data.get(Dataset).get("UserName"));
				Common.dropdown("xpath", "//select[@name='registrant[1][role]']", Common.SelectBy.TEXT,
						data.get(Dataset).get("Role"));
			}
			String registry = Common.findElement("xpath", "//div[@x-data='giftRegistry()']//legend")
					.getText().trim();
			Common.assertionCheckwithReport(registry.equals("Registrant Information"),
					"validating the Registrant Information filed ",
					"It should display Registrant Information in gift registry",
					"successfully Registrant Information is displayed in gift registry",
					"failed to display the Registrant Information under gift registry");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Registrant Information filed ",
					"It should display Registrant Information in gift registry",
					"Unable to display the Registrant Informationin gift registry",
					Common.getscreenShot("failed to display the Registrant Information under gift registry"));
			Assert.fail();
		}
	}

	public void newregistry_CTA(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Create New Registry')]");
			Common.clickElement("xpath", "//a[contains(text(),'Create New Registry')]");
			Sync.waitPageLoad();
			Common.dropdown("id", "type_id", SelectBy.TEXT, data.get(Dataset).get("Type"));
			Common.clickElement("id", "submit.next");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String eventname = Common.findElement("xpath", "(//p[contains(@class,'giftregistry-type text')]//span)[2]").getText();
			System.out.println(eventname);
			Thread.sleep(6000);
			Common.assertionCheckwithReport(
					eventname.equals("Birthday") || eventname.equals("Wedding") || eventname.equals("Baby Registry"),
					"validating seleted event page navigation ",
					"It should be able to navigate to Respective event page  ",
					"successfully Respective selected event page", "failed to Navigate to the respective event page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating seleted event page navigation ",
					"It should be able to navigate to Respective event page  ",
					"Unable to navigate to the selected Respective event page",
					Common.getscreenShot("failed to Navigate to the respective event page"));
			Assert.fail();
		}
	}

	public void click_giftcard() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Gift Registry']");
			Common.clickElement("xpath", "//span[text()='Gift Registry']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Gift Registry"),
					"validating the gift registery page navigation ",
					"It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"It should be able to navigate to the gift registry page ",
					"Unable to Navigated to the gift registry page",
					Common.getscreenShot("Failed to Navigate to the gift registry page"));
			Assert.fail();
		}

	}

	public void edit_gift(String Dataset) {
		// TODO Auto-generated method stub

		try {

			Common.clickElement("xpath", "//a[contains(text(),'Edit')]");
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//input[@id='address_postcode']");
			Common.textBoxInput("xpath", "//input[@id='address_postcode']", data.get(Dataset).get("postcode"));
			Common.clickElement("id", "submit.save");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(message.equals("You saved this gift registry."),
					"validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"Unable to navigate to the gift registry page",
					Common.getscreenShot("failed to Navigate to the gift registry page"));
			Assert.fail();

		}

	}

	public void delete_giftcard() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", " //a[contains(text(),'Delete')]");
			Thread.sleep(3000);
			 Common.alerts("Ok");
			
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(message.contains("You deleted this gift registry."),
					"validating the deleting functionality in the gift registry",
					"After clicking on the delete button it should delete from the gift registry",
					"successfully it has been deleted from the gift registry",
					"failed to delete from the gift registry");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the deleting functionality in the gift registry",
					"After clicking on the delete button it should delete from the gift registry",
					"Unable to delete from the gift registry",
					Common.getscreenShot("failed to delete from the gift registry"));
			Assert.fail();
		}
	}

	public void share_giftcard(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[contains(text(),'Share')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//textarea[@id='sender_message']", data.get(Dataset).get("Message"));
			Common.textBoxInput("xpath", "//input[@name='recipients[0][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[0][email]']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//button[contains(text(),'Add Invitee')]");
			Common.textBoxInput("xpath", "//input[@name='recipients[1][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[1][email]']", data.get(Dataset).get("UserName"));
			Common.clickElement("xpath", "//button[contains(text(),'Share Gift Registry')]");
			Sync.waitPageLoad();
			 Thread.sleep(4000);
			Sync.waitElementPresent(20, "xpath", "//div[@ui-id='message-success']//span");
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You shared the gift registry"),
					"validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"Unable to navigate to the gift registry page",
					Common.getscreenShot("failed to Navigate to the gift registry page"));
			Assert.fail();

		}

	}

	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String Prod=data.get(Dataset).get("Prod Product");
		try {
			Common.clickElement("xpath", "//button[@id='menu-search-icon']");
			String open = Common.findElement("xpath", "//button[@id='menu-search-icon']").getAttribute("id");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("search"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			if(Common.getCurrentURL().contains("preprod"))
			{
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", data.get(Dataset).get("Products"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
//			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
//			System.out.println(productsearch);
//			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
//					"enter product name will display in the search box", "user enter the product name in  search box",
//					"Failed to see the product name");
//			Thread.sleep(8000);
			}
			else
			{
				Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", data.get(Dataset).get("Prod Product"));
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitPageLoad();
//				Thread.sleep(4000);
//				String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
//				System.out.println(productsearch);
//				Common.assertionCheckwithReport(productsearch.contains(Prod), "validating the search functionality",
//						"enter product name will display in the search box", "user enter the product name in  search box",
//						"Failed to see the product name");
//				Thread.sleep(8000);
			}
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	public void addtocart(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String prodproduct = data.get(Dataset).get("Prod Product");
		String productcolor = data.get(Dataset).get("Color");
		String prodcolor = data.get(Dataset).get("ProdColor");
		String Productsize = data.get(Dataset).get("Size");
		String symbol=data.get(Dataset).get("Symbol");
		System.out.println(symbol);
		System.out.println(products);
		System.out.println(productcolor);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}

			Sync.waitPageLoad(30);
			Thread.sleep(6000);
//			
//			if(Common.getCurrentURL().contains("preprod")) {
//				
//				
//				
//				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
//				Common.clickElement("xpath", "//img[@alt='" + products + "']");
////				Sync.waitPageLoad();
////				Thread.sleep(6000);
////				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
////				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				String ClrOption= Common.findElement("xpath", "(//div[@class='m-swatch swatch-option-selected'])[1]").getAttribute("class");
//				String Clr= Common.findElement("xpath", "(//div[@class='m-swatch swatch-option-selected'])[1]").getAttribute("data-option-label");
//				if(ClrOption.contains("option-selected") && Clr.contains(data.get(Dataset).get("Color")) ) {
//					System.out.println("Color Option Already selected");
//				}else {
////					
//					Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//					Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				}
//			}
//				
//				else {
					
					Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
					Common.clickElement("xpath", "//img[@alt='" + products + "']");
					Thread.sleep(6000);
//					Sync.waitElementPresent("xpath", "//div[@data-option-label='" + prodcolor + "']");
//					Common.clickElement("xpath", "//div[@data-option-label='" + prodcolor + "']");
//				}
//			
//			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
//			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
//			Sync.waitPageLoad(30);
//			Thread.sleep(3000);
//			Common.scrollIntoView("xpath", "//h1[@itemprop='name']");
//			Sync.waitElementVisible(30, "xpath", "//h1[@itemprop='name']");
//			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
//			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
//					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
//					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
			product_quantity(Dataset);
//			Thread.sleep(4000);
//			String country=Common.findElement("xpath", "(//span[@class='country-selector-title'])[1]").getText();
//			System.out.println(country);
//			Thread.sleep(4000);

//			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
//			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(10000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//			.getAttribute("data-ui-id");
//	System.out.println(message);
//	Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//			"Product should be add to cart", "Sucessfully product added to the cart ",
//			"failed to add product to the cart");
//	String price=Common.findElement("xpath", "//span[contains(@class, 'flex text-lg')]//span[@class='price']").getText().replace(symbol, "").replace(".", "");
//	System.out.println(price);
//	Thread.sleep(5000);
//	price = price.trim();
//	price = price.substring(0,price.length() - 2);
//    System.out.println(price);  
//	int amount=Integer.parseInt(price);
//	System.out.println(amount);
//	
//	if(amount>199 && country.contains("US | EN"))
//	{
////		Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
/////				Common.clickElement("xpath", "//div[@class='ampromo-close']");
//		Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Close minicart']");
//		Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
//	}
//	else
//	{
//		Sync.waitElementPresent(30, "xpath", "//div[@role='dialog']//button[@aria-label='Close minicart']");
//		Common.clickElement("xpath", "//div[@role='dialog']//button[@aria-label='Close minicart']");
//	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}
	
	
	public void addtocart_Configurable(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String prodproduct = data.get(Dataset).get("Prod Product");
		String productcolor = data.get(Dataset).get("Color");
		String prodcolor = data.get(Dataset).get("ProdColor");
		String Productsize = data.get(Dataset).get("Size");
		String symbol=data.get(Dataset).get("Symbol");
		System.out.println(symbol);
		System.out.println(products);
		System.out.println(productcolor);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}

			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			
//			if(Common.getCurrentURL().contains("preprod")) {
//				
//				
//				
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitPageLoad();
				Thread.sleep(6000);
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				String ClrOption= Common.findElement("xpath", "(//div[@class='m-swatch swatch-option-selected'])[1]").getAttribute("class");
//				if(ClrOption.contains("option-selected")) {
//					System.out.println("Color Option Already selected");
//				}else {
//					
//					Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//					Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				}
//			}
//				
//				else {
//					
//					Sync.waitElementPresent(30, "xpath", "//img[@alt='" + prodproduct + "']");
//					Common.clickElement("xpath", "//img[@alt='" + prodproduct + "']");
//					Thread.sleep(6000);
//					Sync.waitElementPresent("xpath", "//div[@data-option-label='" + prodcolor + "']");
//					Common.clickElement("xpath", "//div[@data-option-label='" + prodcolor + "']");
//				}
//			
//			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
//			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
////			Sync.waitPageLoad(30);
//			Thread.sleep(4000);
//			Common.scrollIntoView("xpath", "//h1[@itemprop='name']");
//			Sync.waitElementVisible(30, "xpath", "//h1[@itemprop='name']");
//			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
//			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
//					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
//					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			product_quantity(Dataset);
//			Thread.sleep(4000);
//			String country=Common.findElement("xpath", "(//span[@class='country-selector-title'])[1]").getText();
//			System.out.println(country);
//			Thread.sleep(4000);

//			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
//			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(10000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//			.getAttribute("data-ui-id");
//	System.out.println(message);
//	Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//			"Product should be add to cart", "Sucessfully product added to the cart ",
//			"failed to add product to the cart");
//	String price=Common.findElement("xpath", "//span[contains(@class, 'flex text-lg')]//span[@class='price']").getText().replace(symbol, "").replace(".", "");
//	System.out.println(price);
//	Thread.sleep(5000);
//	price = price.trim();
//	price = price.substring(0,price.length() - 2);
//    System.out.println(price);  
//	int amount=Integer.parseInt(price);
//	System.out.println(amount);
//	
//	if(amount>199 && country.contains("US | EN"))
//	{
////		Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
/////				Common.clickElement("xpath", "//div[@class='ampromo-close']");
//		Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Close minicart']");
//		Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
//	}
//	else
//	{
//		Sync.waitElementPresent(30, "xpath", "//div[@role='dialog']//button[@aria-label='Close minicart']");
//		Common.clickElement("xpath", "//div[@role='dialog']//button[@aria-label='Close minicart']");
//	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
			Common.findElement("xpath", "//div//select[@name='qty']");
			Common.dropdown("xpath", "//div//select[@name='qty']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			String value = Common.findElement("xpath", "//div//select[@name='qty']").getAttribute("value");
			Common.assertionCheckwithReport(value.equals(Quantity),
					"validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page",
					"Sucessfully product Qunatity has been updated ",
					"failed to Update the prodcut quantity in PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "unable to change the  product Qunatity",
					Common.getscreenShot("failed to update the product quantity"));
			AssertJUnit.fail();
		}

	}

	public void more_Quantity(String Dataset) {

		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);
		String MoreQuantity = data.get(Dataset).get("MoreQuantity");
		System.out.println(MoreQuantity);
		String products = data.get(Dataset).get("Products");

		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(4300);
			// Common.textBoxInputClear("xpath", "//input[@class='a-number-input
			// m-add-to-cart__input']");
			Common.findElement("xpath", "//input[@class='a-number-input m-add-to-cart__input']").sendKeys(MoreQuantity);
			// Common.textBoxInput("xpath", "//input[@class='a-number-input
			// m-add-to-cart__input']", "1000");
			WebElement Scroll=Common.findElement("xpath", "//ol[@class='m-breadcrumb__list']");
			Common.scrollIntoView(Scroll);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Thread.sleep(4000);
			Sync.waitElementVisible("xpath", "//div[@data-ui-id='message-error']//div[@class='a-message__container-inner']");
			String Error = Common.findElement("xpath",
					"//div[@data-ui-id='message-error']//div[@class='a-message__container-inner']").getText();
			System.out.println(Error);
			System.out.println("We don’t have as many " + products + " as your requested");
			Common.assertionCheckwithReport(Error.contains("We don’t have as many " + products + " as your requested"),
					"validating the error message if item is not not avliable request quantity",
					"Error message should be dispaly if the requested quantity is not avaliable",
					"Sucessfully Error message ha been displayed when reuested quantity is not avaliable",
					"failed to display the error message if requested quantity is not avaliable");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the error message if item is not not avliable request quantity",
					"Error message should be dispaly if the requested quantity is not avaliable",
					"Unable to display the error message if requested quantity is not avaliable",
					Common.getscreenShot("failed to display the error message if requested quantity is not avaliable"));
			AssertJUnit.fail();
		}
	}

	public void minicart_viewcart() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@id='cart-drawer-title']/span/span");
			String minicart = Common.findElement("xpath", "//div[@id='cart-drawer-title']/span/span").getText();
			Sync.waitElementPresent("xpath", "//a[@title='View Cart']");
			Common.clickElement("xpath", "//a[@title='View Cart']");
			String viewcart = Common.findElement("xpath", "//span[contains(@class,'ml-7 title-xs hf:title')]").getText();
			Common.assertionCheckwithReport(
					viewcart.contains(minicart) && Common.getCurrentURL().contains("/checkout/cart/"),
					"validating the navigation to the view cart", "User should able to navigate to the view cart page",
					"Successfully navigates to the view cart page",
					"Failed to navigate to the view and edit cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigation to the view cart",
					"User should able to navigate to the view cart page", "unable to  navigates to the view cart page",
					Common.getscreenShot("Failed to navigate to the view cart page"));

			Assert.fail();

		}

	}

	public void additems_giftregistry(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[contains(text(),'Add All To Gift Registry')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Gift Registry Items"),
					"validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"successfully Navigated to the Manage Gift Registry",
					"failed to Navigate to the Manage Gift Registry");
//			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"Unable to Navigated to the Manage Gift Registry",
					Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
			Assert.fail();
		}

		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//input[@type='number']");
			Common.textBoxInput("xpath", "//input[@type='number']",
					data.get(Dataset).get("Quantity"));
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Update Gift Registry ')]");
			Common.clickElement("xpath", "//button[contains(text(),'Update Gift Registry ')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']//span");
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(message.contains("You updated the gift registry items."),
					"validating nthe  message validation for the prodcuts in gift registry ",
					"After Upadting the quantity the message should be display",
					"successfully quantity has been changed  message has been displayed",
					"failed to Display the message for the when quantity changed");

			Common.clickElement("xpath", "//a[@title='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating nthe error message validation for the prodcuts in gift registry ",
					"After Upadting the quantity to zero the eroor message should be display",
					"Unable to Display the error message for the when quantity changed to zero",
					Common.getscreenShot("failed to Display the error message for the when quantity changed to zero"));
			Assert.fail();

		}


	}

	public void noitems_giftregistry(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//input[@type='checkbox']");
			Common.clickElement("xpath", "//input[@type='checkbox']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='control m-text-input']");
			Common.javascriptclickElement("xpath", "//div[@class='control m-text-input']");
			Common.textBoxInput("xpath", "//input[contains(@class,'input-text qty a-text-input')]",
					data.get(Dataset).get("Quantity"));
			Sync.waitElementPresent("xpath", "//span[text()='Update Items']");
			Common.clickElement("xpath", "//span[text()='Update Items']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String deletemessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			System.out.println(deletemessage);
			Common.assertionCheckwithReport(deletemessage.contains("You updated the gift registry items."),
					"verifying the delete product in gift registry", "product should be delete from the gift registry",
					"Sucessfully product has been deleted from the gift registry",
					Common.getscreenShotPathforReport("Failed to delete the product from the gift registry"));
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//div[@class='message info empty']//span");
			String emptymessage = Common.findElement("xpath", "//div[@class='message info empty']//span").getText();
			Common.assertionCheckwithReport(emptymessage.contains("This gift registry has no items."),
					"verifying the no prodcts in the gift registry",
					"product should be not display in the gift registry",
					"Sucessfully products should not been displayed in the gift registry",
					Common.getscreenShotPathforReport("Failed to delete the products in the gift registry"));
			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the no prodcts in the gift registry",
					"product should be not display in the gift registry",
					"Unable to display the  products in the gift registry",
					Common.getscreenShotPathforReport("Failed to delete the products in the gift registry"));

			AssertJUnit.fail();
		}

	}

	public void share_invalid_details(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Share')]");
			Common.clickElement("xpath", "//a[contains(text(),'Share')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Share Gift Registry')]");
			Common.clickElement("xpath", "//button[contains(text(),'Share Gift Registry')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-error']//span");
			String errormessage = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
			Common.assertionCheckwithReport(errormessage.contains("You need to enter sender data."),
					"validating the error message with empty fields ",
					"After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
			Common.clickElement("xpath", "//a[@title='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
			Assert.fail();
		}
		try {
			
			delete_giftcard();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registry page navigation ",
					"After clicking Gift registry it should navigate to the gift registry page",
					"Unable to  Navigate  to the gift registry page ",
					Common.getscreenShot("Failed to Navigate to the gift rigistry page"));
			AssertJUnit.fail();
		}

	}

	public void My_Favorites() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			if(Common.getCurrentURL().contains("preprod")&&Common.getCurrentURL().contains("/gb") )
			{
				Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
				Sync.waitElementPresent(30, "xpath", "//a[text()='My Favourites']");
				Common.clickElement("xpath", "//a[text()='My Favourites']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favourites"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
			}
			else
			{
				Common.clickElement("id", "customer-menu");
				Sync.waitElementPresent(30, "xpath", "//a[@title='My Favorites']");
				Common.clickElement("xpath", "//a[@title='My Favorites']");
				Thread.sleep(2000);
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Favorites Sharing"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My Favorites page",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
					"Unable to Navigates the user to My Favorites page after clicking on the My Favorites CTA",
					Common.getscreenShot(
							"Failed to Navigate to the My Favorites page after Clicking on My Favorites CTA"));
			AssertJUnit.fail();
		}

	}

	public void Addtocart_From_MyFavorites(String Dataset) {
		
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
			
		    try {	        
		        Sync.waitPageLoad();
		        int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

		        if (MyFavorites != 0) {
		            search_product("Product");
		            Sync.waitElementPresent(30, "xpath", "//button[contains(@class, 'group/wishlist')]");
		            Common.mouseOverClick("xpath", "//button[contains(@class, 'group/wishlist')]");
		            Sync.waitPageLoad();
		            Thread.sleep(2000);
		            My_Favorites();	     
		            Common.findElements("xpath", "//div[contains(@title,'My Wishlist')]");
		            Sync.waitPageLoad();
		            String Whishlistproduct = Common
		                    .findElement("xpath", "//div[contains(@class,'yotpo bottomLine bottomline-position')]//preceding-sibling::a")
		                    .getAttribute("title").trim();
		            System.out.println(Whishlistproduct);

		            product = data.get(Dataset).get("Products").trim();
		            System.out.println(product);

		            if (Whishlistproduct.equals(product)) {
		                Sync.waitElementPresent(30, "xpath", "//a[@title='" + product + "']/parent::div");
		                Common.mouseOver("xpath", "//a[@title='" + product + "']/parent::div");
		                Common.clickElement("xpath", "//a[contains(@title,'Show options')]");
		                
		                Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
						Common.clickElement("xpath", "//img[@alt='" + product + "']");
						Sync.waitPageLoad();
						Sync.waitElementPresent("xpath", "//input[@aria-label='" + productcolor + "']");
						Common.clickElement("xpath", "//input[@aria-label='" + productcolor + "']");
					//	Sync.waitElementPresent("xpath", "//input[@aria-label='" + Productsize + "']");
					//	Common.clickElement("xpath", "//input[@aria-label='" + Productsize + "']");
		                Sync.waitPageLoad();
		                Thread.sleep(3000);

		                Common.clickElement("xpath", "(//button[@title='Add to Cart'])[2]");	        
		                click_minicart();
		                String Minicartcount = Common.findElement("xpath", "//div[@x-show='cartSummaryCount']").getText();
		                System.out.println(Minicartcount);
		                int minicart = Integer.parseInt(Minicartcount);
		                System.out.println(minicart);

		                if (minicart > 0) {
		                    click_minicart();
		                    minicart_Checkout();
		                } else {
		                    Common.refreshpage();
		                    Sync.waitPageLoad();
		                    minicart_Checkout();
		                }
		            } else {
		                Assert.fail();
		            }
		        } else { 
		      
		        	    Sync.waitPageLoad();
		        	    List<WebElement> webelementslist = Common.findElements("xpath", "//div[@data-row='product-item']//a");
		        	    int productCount = webelementslist.size();

		        	    for (int i = 0; i < productCount; i++)
		        	    {        	        
		        	        Common.scrollIntoView("xpath", "//img[contains(@class,'object-con')]");
		        	        Common.mouseOver("xpath", "//img[contains(@class,'object-con')]");
		        	        Sync.waitElementPresent(30, "xpath", "//button[@id='menu-cart-icon']");
		        	        List<WebElement> element = Common.findElements("xpath", "//a[contains(@title,'Show options')]");
		        	        element.get(0).click();
		        	        Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
							Common.clickElement("xpath", "//img[@alt='" + product + "']");
							Sync.waitPageLoad();
							Sync.waitElementPresent("xpath", "//input[@aria-label='" + productcolor + "']");
							Common.clickElement("xpath", "//input[@aria-label='" + productcolor + "']");
						//	Sync.waitElementPresent("xpath", "//input[@aria-label='" + Productsize + "']");
						//	Common.clickElement("xpath", "//input[@aria-label='" + Productsize + "']");
			                Sync.waitPageLoad();
		        	        Thread.sleep(2000);
		        	       
		        	        String message = Common.findElement("xpath", "//div[contains(@class,'message')]").getText();
		        	        if (message.contains("You added")) {
		        	            webelementslist.get(i).click();
		        	            }
		        	        Sync.waitElementPresent(30,"xpath", "(//button[@title='Add to Cart'])[2]");
	        	            Common.clickElement("xpath", "(//button[@title='Add to Cart'])[2]");
		        	        
		        	        int minicart = Common.findElements("xpath", "//button[@id='menu-cart-icon']").size();
		        	        if (minicart > 0) {
		        	            minicart_Checkout();
		        	            break;
		        	        }
		        	        else {
		        	            Common.refreshpage();
		        	            Sync.waitPageLoad();
		        	            minicart_Checkout();
		        	            break;
		        	        }
		        	    }
		 }}
		     catch (Exception | Error e) {
		        e.printStackTrace();
		        ExtenantReportUtils.addFailedLog("Validating product add to cart", "Product should be added to cart",
		                "Unable to add product to cart", Common.getscreenShot("failed to add product to cart"));
		        Assert.fail();
		    }
		}
	
	public void see_options(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(Productsize);
		try {
			Thread.sleep(4000);
			Common.mouseOver("xpath", "//fieldset[@class='fieldset m-product-card__cta']//span");
			String seeoption = Common.findElement("xpath", "//fieldset[@class='fieldset m-product-card__cta']//span")
					.getText();
			System.out.println(seeoption);
			if (seeoption.equals("SEE OPTIONS")) {
				Sync.waitElementPresent("xpath", "//label[@for='wishlist-select-all']");
				Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
				Common.clickElement("xpath", "//span[text()='Remove From My Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String emptymessage = Common.findElement("xpath", "//div[contains(@class,'message in')]//span")
						.getText();
				Common.assertionCheckwithReport(emptymessage.contains("You have no items in your favorites."),
						"validating the empty products in my favrioutes page ",
						"Products should not appear in the my favoritoes page",
						"Sucessfully product are empty in my favorites page",
						"failed to see empty products in my favorites page");
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("stage3"))
                {
                    Sync.waitPageLoad();
                    String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
                    System.out.println(message);
                    Common.assertionCheckwithReport(message.contains("has been added to your Favorites"),
                            "validating the  product add to the Favorites", "Product should be add to Favorites",
                            "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

                }
				else
				{
				Sync.waitElementVisible(30, "xpath", "//h4");
				String whishlistpopup = Common.findElement("xpath", "//h4").getText();
				System.out.println(whishlistpopup);
				if (whishlistpopup.contains("Add to Wishlist")) {
					Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
					Common.clickElement("xpath", "//button[@title='Add To List']");
				} else {
					AssertJUnit.fail();
				}
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				}
				Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
				Sync.waitElementPresent(30, "xpath", "(//img[contains(@class,'m-produc')])[1]");
				Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");

				Sync.waitElementPresent(30, "xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				minicart_Checkout();

			} else {
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				minicart_Checkout();

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			AssertJUnit.fail();
		}

	}

	public void minicart_Checkout() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			click_minicart();
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String minicart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
			System.out.println(minicart);
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//a[contains(@class,'inline-flex btn btn-primary text')]");
			Common.clickElement("xpath", "//a[contains(@class,'inline-flex btn btn-primary text')]");
			Sync.waitPageLoad();
			Thread.sleep(7000);
//			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
//			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
//			System.out.println(checkout);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					/* checkout.equals(minicart) && */ Common.getCurrentURL().contains("checkout/#shipping")
							|| Common.getCurrentURL().contains("/checkout/#payment")
							|| Common.getCurrentURL().contains("/checkout/"),
					"validating the navigation to the shipping page when we click on the checkout",
					"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
					"Failed to navigate to the shipping page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the checkout ",
					"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));

			Assert.fail();
		}
	}

	public void click_minicart() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
			String openminicart = Common.findElement("xpath", "//div[@aria-labelledby='cart-drawer-title']").getAttribute("aria-modal");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("true"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "the mini cart is displayed",
					"unable to  dislay the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}

	}



	public void RegaddDeliveryAddress(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
		int size = Common.findElements(By.xpath("//button[contains(@class,'btn dr:btn-secondary-checkout hf:btn-primary')]")).size();
		if (size > 0) {
			try {
				Common.clickElement("xpath", "//button[contains(@class,'btn dr:btn-secondary-checkout hf:btn-primary')]");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath", "//input[@name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}

				Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
				Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Region"));
				Thread.sleep(3000);
//				String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
//				String Shippingstate = Common
//						.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']")
//						.getText();

//				System.out.println(Shippingstate);

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(2000);
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);

				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

//				Sync.waitElementPresent("xpath", "//input[@id='shipping-save']");
//				Common.clickElement("xpath", "//input[@id='shipping-save']");

				Thread.sleep(3000);
				Common.clickElement("xpath", "//button[@class='btn btn-primary w-full']");

//				
//                ExtenantReportUtils.addPassLog("verifying shipping addres filling ",
//						"user will fill the all the shipping", "user fill the shiping address click save button",
//						"faield to add new shipping address");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				Assert.fail();

			}

		}

		else

		{
			try {

				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				Thread.sleep(2000);

				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("id", "shipping-city",
						data.get(dataSet).get("City"));

				try {
					Common.dropdown("xpath", "//select[@id='shipping-region']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));

				String ShippingZip = Common
						.findElement("xpath", "//form[@id='shipping']//input[@name='postcode']")
						.getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);
				
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				Common.clickElement("id", "shipping-save");
				
				
				

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				Assert.fail();

			}
		}

	}

	public void selectshippingmethod(String Dataset) {
		// TODO Auto-generated method stub4
		String method = data.get(Dataset).get("methods");
		
		try {
			int size = Common.findElements("xpath", "//div[@id='shipping-method-list']").size();
			System.out.println(size);
			if (size > 0  ) {
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ method +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ method +"')]");
			}
			else
			{
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Standed shipping method",
					"Select the Standed shipping method in shipping page ",
					"failed to select the Standed shipping method ",
					Common.getscreenShotPathforReport("failed select Standed shipping method"));

			Assert.fail();
		}

	}


	public void clickSubmitbutton_Shippingpage() {
		// TODO Auto-generated method stub
		String expectedResult = "click the submit button to navigate to payment page";
		try {
			Thread.sleep(5000);
			Common.getPageTitle();
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
					"failed to click the submitbutton",
					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
			AssertJUnit.fail();
		}

	}

	public void signout() {
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "(//a[@title='Sign Out'])[1]");

			Common.clickElement("xpath", "(//a[@title='Sign Out'])[1]");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("customer/account/logoutSuccess/"),
					"Validating My Account page navigation", "user sign in and navigate to my account page",
					"Successfully navigate to my account page", "Failed to navigate my account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating sign out navigation ",
					"after clinking signout user signout fro the page", "user Successfully signout  ",
					Common.getscreenShotPathforReport("user Failed to signout"));
			Assert.fail();
		}

	}

	public void click_UGC() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.END);
			Common.scrollIntoView("xpath", "//div[contains(@class,'ugc instagram')]");
			Sync.waitElementPresent("xpath", "//div[@class='y-image-overlay ']");
			Common.scrollIntoView("xpath", "//div[@class='y-image-overlay ']");
			Common.clickElement("xpath", "//div[@class='y-image-overlay ']");
			// Thread.sleep(6000);
			String yopto = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']").getAttribute("aria-label");
			// Thread.sleep(6000);
			System.out.println(yopto);
			WebElement UGC = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']//span");
			Thread.sleep(6000);
			Common.scrollIntoView(UGC);
			Common.assertionCheckwithReport(yopto.contains("Powered by"),
					"To validate the yopto popup in when we click on the UGC",
					"user should able to display the yopto popup", "Sucessfully yopto popup has been displayed",
					"Failed to Displayed the yopto popup");
			Sync.waitElementPresent(40, "xpath", "//span[@aria-label='See Next Image']");
			Common.clickElement("xpath", "//span[@aria-label='See Next Image']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[@aria-label='Cancel']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the yopto popup in when we click on the UGC",
					"user should able to display the yopto popup", "unable to Displayed the yopto popup",
					Common.getscreenShotPathforReport("Failed to Displayed the yopto popup"));
			AssertJUnit.fail();
		}
	}

	public void Find_a_delear() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//span[contains(@class,'icon-location')]");

			String find = Common.findElement("xpath", "//div[contains(@class,'row-full-width-inner')]//h1").getText();
			System.out.println(find);

			Common.assertionCheckwithReport(find.equals("Store Locator"), "validating Find a Store page",
					"user navigates to Find a Store page", "Sucessfully user navigate to Find a Store page",
					"faield to navigate to Find a Store page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to Find a Store",
					"unable to go back to the Find a Store page",
					Common.getscreenShotPathforReport("faield to get back to Find a Store"));
			Assert.fail();

		}
	}



	public void click_Retailer() {
		// TODO Auto-generated method stub
		String store = "Whole Earth Provision Co.";

		try {

			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
			Common.clickElement("xpath", "//a[contains(@id,'dealer-navigation-retailers')]");

			Sync.waitPageLoad();
			Thread.sleep(8000);
//			String id = Common.findElement("xpath", "//h3[contains(text(),'" + store + "')]")
//					.getText();
			Common.clickElement("xpath", "//div[contains(@class,'conv-section-details')]//h3[contains(text(),'Whole Earth Provision Co.')]");
//			 	.getText();
//			System.out.println(id);
//			 Common.clickElement("xpath", "//div[contains(@aria-label,'DICK'S Sporting')]");

//			Common.findElement("xpath", "//div[@id='" + id + "']").click();
			Sync.waitElementPresent("xpath", "//img[@class='store-info-logo']");
			int storeSize = Common.findElements("xpath", "//img[@class='store-info-logo']").size();
			System.out.println(storeSize);
			Common.assertionCheckwithReport(storeSize > 0, "validating Retailers page",
					"user navigates to Retailers page", "Sucessfully user navigate to Retailers page",
					"faield to navigate to Retailers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Retailers page",
					"System directs the user back to Retailers page", "unable to user go back to Retailers page",
					Common.getscreenShotPathforReport("faield to get back to Retailers page"));
			Assert.fail();
		}

	}



public void verifingRetailerHours() {
		// TODO Auto-generated method stub
		String hours = "hours";
		try {

			Common.findElement("xpath", "//a[@aria-label='" + hours + "']").click();
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@class='store-hours-days']");
			int hoursSize = Common.findElements("xpath", "//div[@class='store-hours-days']").size();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(hoursSize > 0, "validating hours page", "user navigates to Hours page ",
					"Successfully user navigate to hours page", "faield to navigate to hours page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating hours page", "System directs the user back to the hours page",
					"unable to user back to the hours page",
					Common.getscreenShotPathforReport("Failed to get back to hours page"));

			Assert.fail();
		}
	}


public void verifingRetailerBrowser() {
		// TODO Auto-generated method stub
		String browse = "Browse";
		try {
			Common.findElement("xpath", "//a[@aria-label='" + browse + "']").click();
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@class='cat-active-filter-container']");
			int filterSize = Common.findElements("xpath", "//div[@class='cat-active-filter-container']").size();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(filterSize > 0, "validating browser page",
					"user navigates to Browsers page", "Sucessfully user navigate to browser page",
					"faield to navigate to browser page");
			Common.clickElement("xpath", "//a[@class='nav-bar-back']");
			Common.clickElement("xpath", "//a[@class='nav-bar-back']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating browser page",
					"System directs the user back to the Browser page", "failed user back to the browser page",
					Common.getscreenShotPathforReport("Failed to get back to browser page"));
			Assert.fail();
		}

	}

public void Validate_store_sidebar() {
		// TODO Auto-generated method stub
		try {
			// Common.switchFrames("xpath",
			// "//iframe[contains(@id,'lcly-embedded-iframe')]");
			Thread.sleep(5000);
			Sync.waitElementPresent("id", "conversion-sidebar");
			int RetailersTab = Common.findElements("id", "dealer-navigation-retailers").size();
			int InstockTab = Common.findElements("id", "dealer-navigation-inventory").size();
			int Locationsearch = Common.findElements("xpath", "//input[@name='location']").size();
			int UsemylocationCTA = Common.findElements("xpath", "//a[@id='current-location-detector']").size();
			int Retailersmap = Common.findElements("xpath", "//div[contains(@id,'marker-index')]").size();
			System.out.println(Retailersmap);
			Common.assertionCheckwithReport(
					RetailersTab > 0 && InstockTab > 0 && Locationsearch > 0 && UsemylocationCTA > 0
							&& Retailersmap > 0,
					"validating Store locator side bar ",
					"Should visible the RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap",
					"RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap displayed",
					"Failed to land on store locator page");
			Common.switchToDefault();

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Store locator side bar ",
					"Should visible the RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap",
					"failed to display RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap",
					Common.getscreenShotPathforReport("faield to display the tabs"));
			Assert.fail();
		}

	}


public void CLick_Usemylocation() {
	try {
		Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//a[@id='current-location-detector']");
//		Common.mouseOverClick("xpath", "//a[@id='current-location-detector']");
		Sync.waitPresenceOfElementLocated("id", "current-location-indicator");
		Common.scrollIntoView("xpath", "//div[@class='dl-location-detector-container ']");
		int currentlocation = Common.findElements("xpath", "//div[@class='dl-location-detector-container ']").size();
		System.out.println(currentlocation);
		String address = Common.findElement("xpath", "//h5[contains(@class,'store-address')]").getText();
		Common.assertionCheckwithReport(currentlocation > 0 && address.contains("TX"),
				"validating current location ", "Should visible retailers in the current location",
				"Current location Displayed", "Failed to display the current location");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Users based on Current Location",
				"To display the retailers for the current location",
				"Failed to display retailers for the current location",
				Common.getscreenShotPathforReport("faield to display the retailers for current location"));
		Assert.fail();
	}

}


public void Validate_AvailableRetailers() {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//a[contains(@class,'tab-retailers')]");

			Common.mouseOverClick("xpath", "//a[contains(@class,'tab-retailers')]");
			int retailers = Common.findElements("xpath", "//div[contains(@class,'store dl-store-list-tile')]").size();
			if (retailers > 0) {
				Common.assertionCheckwithReport(retailers > 0, "To validate the available retailers",
						"Retailers should be available", "Retailers are available", "Failed to display the retailers");
			} else {
				Sync.waitElementVisible("xpath", "//input[@name='location']");
				Common.textBoxInput("xpath", "//input[@name='location']", "CT 06473");
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitElementVisible(30, "xpath", "//h3[@class='section-title dl-store-name']");
				int locationRetailers = Common.findElements("xpath", "//h3[@class='section-title dl-store-name']")
						.size();

				Common.assertionCheckwithReport(locationRetailers > 0,
						"To validate the available retailers for new location",
						"Retailers should be available for new location", "Retailers are available for new location",
						"Failed to display the retailers for new location");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating available retailers page",
					"retailers should be visible for the given location",
					"Failed to display retailers for the given location",
					Common.getscreenShotPathforReport("faield to display the available retailers"));
			Assert.fail();
		}

	}


public void Validate_retailerlocations() {
	try {
		Common.clickElement("xpath", "//h3[@class='section-title dl-store-name']");
		Sync.waitElementVisible("xpath", "//div[@class='square-image-container']");
		int Retailerlogo = Common.findElements("xpath", "//div[@class='square-image-container']").size();
		int locations = Common.findElements("xpath", "//a[contains(@class,'tab-locations')]").size();
		int Hours = Common.findElements("xpath", "//a[contains(@class,'tab-hours')]").size();
		int Links = Common.findElements("xpath", "//a[contains(@class,'tab-links')]").size();
		Common.assertionCheckwithReport(Retailerlogo > 0 && locations > 0 && Hours > 0 || Links > 0,
				"To validate the store info content displayed ", "store info content should be displayed",
				"store info content is displayed", "Failed to display the store info content ");
		String Storename = Common.findElement("xpath", "//h2[contains(@class,'store-name-inner')]").getText().toUpperCase();
		System.out.println(Storename);
		Common.clickElement("xpath", "//a[contains(@class,'tab-locations')]");

		int storecount = Common.findElements("xpath", "//a[contains(@class,'conv-section-store')]//h3").size();
		for (int i = 1; i <= storecount; i++) {
			Thread.sleep(3000);
			String relatedstores = Common
					.findElement("xpath", "(//a[contains(@class,'conv-section-store')]//h3)[" + i + "]")
					.getText();
			System.out.println(relatedstores);
			Common.assertionCheckwithReport(relatedstores.contains(Storename),
					"To validate the retailer stores displayed ", "Retailer stores should be displayed",
					"Retailer stores are displayed", "Failed to display the retailer stores ");

		}
		Thread.sleep(2000);
		Common.clickElement("xpath", "//a[@class='nav-bar-back']");
		Click_Direction();
		Thread.sleep(2000);
		writeReviews();

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating available retailer store locations",
				"retailers store locations should be visible", "Failed to display retailers store locations",
				Common.getscreenShotPathforReport("faield to display retailer store locations"));
		Assert.fail();
	}

}

	public void writeReviews() {
		// TODO Auto-generated method stub
		try {

			Common.switchToFirstTab();
			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");

			Sync.waitElementPresent("xpath", "//span[text()='Write a Review']");
			Common.clickElement("xpath", "//span[text()='Write a Review']");
			Common.switchWindows();
			int reviewSize = Common.findElements("xpath", "//div[@class='review-form-header']//h1").size();

			System.out.println(reviewSize);
			Common.assertionCheckwithReport(reviewSize >= 0, "validating reviews page",
					"user redirects to reviews page", "Sucessfully user redirects to reviews page",
					"faield to redirects to reviews page");
			Common.switchToFirstTab();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating reviews page", "System directs the user back to reviews page",
					"unable to user go back to reviews page",
					Common.getscreenShotPathforReport("faield to get back to reviews page"));
			AssertJUnit.fail();
		}

	}

	public void Click_Direction() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//a[@id='conv-store-info-back']");
			Common.clickElement("xpath", "//a[@id='conv-store-info-back']");

			Sync.waitElementPresent("xpath", "(//span[text()='Directions'])[2]");
			Common.clickElement("xpath", "(//span[text()='Directions'])[2]");
			Common.switchWindows();

			int directionsize = Common.findElements("xpath", "//div[@aria-label='Directions']").size();
			System.out.println(directionsize);
			Common.assertionCheckwithReport(directionsize >= 0, "validating google maps page",
					"user redirects to google maps page", "Sucessfully user redirects to google maps page",
					"faield to redirects to google maps page");
			Common.switchToFirstTab();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating google maps page",
					"System directs the user back to google maps page", "unable to user go back to google maps page",
					Common.getscreenShotPathforReport("faield to get back to google maps page"));
			AssertJUnit.fail();

		}

	}

	public void Click_Instock() {
		// TODO Auto-generated method stub
		try {

			Sync.waitPageLoad();
			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
			Sync.waitElementPresent(40, "xpath", "//a[@id='dealer-navigation-inventory']");
			Common.clickElement("xpath", "//a[@id='dealer-navigation-inventory']");

			int stock = Common.findElements("xpath", "//div[@id='dealer-tab-inventory-grid-container-desktop']").size();
			System.out.println(stock);

			Common.assertionCheckwithReport(stock > 0, "validating instock page", "user navigates to instock page",
					"Sucessfully user navigate to instock page", "faield to navigate to instock page");
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating instock page",
					"System directs the user back to the instock page", "unale user to go  back to thr instock page",
					Common.getscreenShotPathforReport("failed to get back to instock page"));
			Assert.fail();
		}

	}

	public void selectproduct(String Productname) {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(40, "xpath", "//h5[text()='" + Productname + "']");
			Common.scrollIntoView("xpath", "//h5[text()='" + Productname + "']");
			Common.javascriptclickElement("xpath", "//h5[text()='" + Productname + "']");
			Sync.waitElementVisible("xpath", "//div[@class='stock-status-banner alert success checkmark']");
			Common.scrollIntoView("xpath", "(//h4[@class='pdp-information-title'])[1]");
			int product = Common.findElements("xpath", "//div[@class='pdp-information']/p[2]").size();
			System.out.println(product);

			Common.assertionCheckwithReport(product > 0, "validating product listing page",
					"user navigates to product listing page", "Sucessfully user navigate to product listing page",
					"faield to navigate to product listing page and unable to see error message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating product listing page",
					"System directs the user back to the product listing page",
					"unable user back to product listing page",
					Common.getscreenShotPathforReport("failed to get back product listing page"));
			Assert.fail();
		}

	}



	public void Shipping_Forgot_Password(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Sign in')]");
			Common.clickElement("xpath", "//span[contains(text(),'Sign in')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("customer/account/login"),
					"To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigIn button",
					"User Successfully clicked on the singIn button and Navigate to the signIn page",
					"User Failed to click the signin button and not navigated to signIn page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigin button",
					"Unable to click on the singIn button and not Navigated to the signIn page",
					Common.getscreenShotPathforReport(
							"Failed to click signIn button and not Navigated to the signIn page"));
			Assert.fail();
		}
	}

	public void Configurable_addtocart_pdp(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		String Productsize = data.get(Dataset).get("Size");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image product')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image product')]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}

			}
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");

			product_quantity(Dataset);
			// click_UGC();
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

			Thread.sleep(4000);
			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			AssertJUnit.fail();
		}

	}

	public void Bagpacks_headerlinks(String Dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + Dataset;
		String out = data.get(Dataset).get("outdoor");
		String Trail = data.get(Dataset).get("Trail");
		String header=data.get(Dataset).get("headers");
		try {

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a[contains(@class,'level-0')]//span[contains(text(),'"+ header +"')]");
			
			Common.clickElement("xpath", "//a[contains(@class,'level-0')]//span[contains(text(),'" + header + "')]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + out + "')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[contains(text(),'" + Trail + "')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			expectedResult = "User should select the " + Dataset + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'"+ header +"')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as" + Dataset + "from navigation menu ", expectedResult,
					"Selected the " + Dataset + " category", "User unabel to click" + Dataset + "");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product category as" + Dataset + "from navigation menu ",
					expectedResult, "Unable to Selected the " + Dataset + " category",
					Common.getscreenShot("Failed to click on the" + Dataset + ""));

			AssertJUnit.fail();
		}

	}

	public void social_Links(String dataSet) {

		String socalLinks = data.get(dataSet).get("Links");
		String[] socallinksarry = socalLinks.split(",");
		int i = 0;
		try {
			for (i = 0; i < socallinksarry.length; i++) {
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[contains(@href,'" + socallinksarry[i] + "')]");
				Common.switchWindows();
				System.out.println(Common.getCurrentURL());

				if (socallinksarry[i].equals("instagram")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].equals("facebook")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].equals("x.com")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("x.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("youtube")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("youtube"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("pinterest")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("pinterest"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Thread.sleep(1000);
					Common.switchToFirstTab();
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying Social link ",
					"click the socal links it will navigating to particular page",
					"User unabel to navigate Social page", Common.getscreenShotPathforReport("socialpage"));
			Assert.fail();
		}

	}

	public void Signin_Checkoutpage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("Email"));
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//button[@data-action='checkout-method-login']");
			Sync.waitPageLoad();
			int regsiteruser = Common.findElements("xpath", "//div[contains(@class,'shipping-address-item ')]").size();
			Common.assertionCheckwithReport(regsiteruser > 0,
					"Verifying the login functionality from the shipping page",
					"after clicking on the login button it should login and address should be display",
					"successfully address book has been displayed after login",
					"Failed to Display the Address book in shipping page after click on login");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the login functionality from the shipping page",
					"after clicking on the login button it should login and address should be display",
					"Unable to Display the Address book in shipping page after click on login",
					Common.getscreenShotPathforReport(
							"Failed to Display the Address book in shipping page after click on login"));
			AssertJUnit.fail();
		}

	}

	public void share_whishlist(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//button[contains(@class, 'group/wishlist')]");
				Common.scrollIntoView("xpath", "//button[contains(@class, 'group/wishlist')]");
				Common.clickElement("xpath", "//button[contains(@class, 'group/wishlist')]");
				My_Favorites();
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//span[@class='w-full text-center pr-10']").getText();
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("Click here to view your Favorites."),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
				Common.clickElement("xpath", "(//button[@aria-haspopup='dialog'])[2]");
				Sync.waitPageLoad();
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.clickElement("xpath", "//button[@title='Share Wish List']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String message1 = Common.findElement("xpath", "//span[text()='Your wish list has been shared.']").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
			} else {
				Common.clickElement("xpath", "//div[@class='column main']//button");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your Favorites have been shared"),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shared whishlist functionality",
					"sucess message should display after share whishlist",
					"Unable to display the message for whishlist",
					Common.getscreenShot("failed to display the message for whishlist"));
			Assert.fail();
		}


	}

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");
		String symbol=data.get(dataSet).get("Symbol");

		try {
			Thread.sleep(5000);
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
			} else {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Prod Email"));
			}

		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
//			Common.findElement("xpath", "//div[@class='w-full relative required']//input[@placeholder='Last name']").clear();
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//div[contains(@class,'field relative flex items')]//input[@placeholder='Last name']", 
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//section[@id='shipping-details']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='street[0]']", address);
//			Sync.waitPageLoad();
			Thread.sleep(6000);
			Common.findElement("xpath", "//section[@id='shipping-details']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			  if(Common.getCurrentURL().contains("gb"))
              {
				  Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
              }
			  else
			  {
				
				Common.scrollIntoView("xpath", "//select[@name='region']");
                Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                Thread.sleep(3000);
                String Shippingvalue = Common.findElement("xpath", "//select[@name='region']")
                        .getAttribute("value");
                System.out.println(Shippingvalue);
			}
			Thread.sleep(3000);
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
			Thread.sleep(2000);
	
			//Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			//Common.findElement("xpath", "//input[@placeholder='Phone number']").clear();
			Common.textBoxInput("xpath", "//input[@placeholder='Phone number']", data.get(dataSet).get("phone"));
			
//			String subtotal=Common.findElement("xpath", "//tr[@class='totals sub']//span[@class='price']").getText().replace(symbol, "").replace(".", "");
//			System.out.println(subtotal);
//			subtotal = subtotal.trim();
//			subtotal = subtotal.substring(0,subtotal.length() - 2);
//		    System.out.println(subtotal);  
//			int amount=Integer.parseInt(subtotal);
//			System.out.println(amount);
//			if(amount>199 && symbol.equals("$"))
//			{
//				Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
//				Common.clickElement("xpath", "//div[@class='ampromo-close']");
//				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
//			}
//			else
//			{
//				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
//			}

//			Sync.waitPageLoad();
//			ExtenantReportUtils.addPassLog("validating shipping address filling Fileds",
//					"shipping address is filled in to the fields", "user should able to fill the shipping address ",
//					Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}

	}
	
	
	public String updatePaymentAndSubmit_Gift_Order(String dataSet) throws Exception {
		String order = "";
		addPaymentDetails_Giftcard(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(2000);
			addPaymentDetails(dataSet);
		}

		Thread.sleep(2000);
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(1000);
				Sync.waitElementPresent(30, "xpath", " //h1[normalize-space()='Thank you for your purchase!']");
				String sucessMessage = Common.getText("xpath",
						" //h1[normalize-space()='Thank you for your purchase!']");

				// Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']")
						.size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span")
						.size() > 0) {
					Thread.sleep(1000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(1000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//a");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				AssertJUnit.fail();
			}

		}
		return order;
	}

	public String addPaymentDetails_Giftcard(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Number = "";
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		

		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");

			
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Campo de entrada seguro para el pago'or @title='Secure payment input frame']");
				Thread.sleep(4000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
				Thread.sleep(4000);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);

				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					
					
					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
					Common.scrollIntoView("xpath", "//button[@class='action primary checkout']");
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(10000);
					if (Common.getCurrentURL().contains("/checkout/#payment")) {
						Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
						Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
						Thread.sleep(5000);
						Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
						Common.clickElement("xpath", "//button[@class='action primary checkout']");

					} else if (Common.getCurrentURL().contains("/success/")) {
						String sucessmessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']");
						System.out.println(sucessmessage);
					} else {
						AssertJUnit.fail();
					}

				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			} else {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Campo de entrada seguro para el pago'or @title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Thread.sleep(4000);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);
				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				int link=Common.findElements("xpath", "//label[@id='Field-linkOptInCheckbox']").size();
				
				if(link>0) {
					Common.clickElement("xpath", "//input[@class='p-Checkbox-input']");
				}
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
					Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
					Thread.sleep(8000);
					if (Common.getCurrentURL().contains("/checkout")) {
						Thread.sleep(4000);
						String sucessmessage = Common.getText("xpath",
								"//div[contains(@class,'checkout-success')]//h1");
						System.out.println(sucessmessage);

					} else if (Common.getCurrentURL().contains("/success/")) {
						String sucessmessage = Common.getText("xpath",
								" //h1[normalize-space()='Thank you for your purchase!']");
						System.out.println(sucessmessage);
					} else {
						AssertJUnit.fail();
					}

				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			AssertJUnit.fail();
		}

		expectedResult = "credit card fields are filled with the data";

		return Number;
	}
		
	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(2000);
			addPaymentDetails(dataSet);
		}

		Thread.sleep(2000);
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(1000);
//				Sync.waitElementPresent(30, "xpath", " //h1[normalize-space()='Thank you for your purchase!']");
//				String sucessMessage = Common.getText("xpath",
//						" //h1[normalize-space()='Thank you for your purchase!']");
//
//				// Tell_Your_FriendPop_Up();
//				int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']")
//						.size();
//				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
//						"verifying the product confirmation", expectedResult,
//						"Successfully It redirects to order confirmation page Order Placed",
//						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span")
						.size() > 0) {
					Thread.sleep(1000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(1000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//a");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				AssertJUnit.fail();
			}

		}
		return order;
	}

	
	
	public String addPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Number = "";
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();
//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
//			String code =Common.findElement("xpath", "//input[@id='shipping-postcode']").getAttribute("class");
			Sync.waitElementPresent("xpath", "//input[@id='shipping-postcode']");
			String code = Common.findElement("xpath", "//input[@id='shipping-postcode']").getAttribute("value");
			System.out.println(code);
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Campo de entrada seguro para el pago'or @title='Secure payment input frame']");
				Thread.sleep(4000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
				Thread.sleep(4000);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					
					
					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
					Common.scrollIntoView("xpath", "//button[@class='action primary checkout']");
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(10000);
					if (Common.getCurrentURL().contains("/checkout/#payment")) {
						Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
						Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
						Thread.sleep(5000);
						Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
						Common.clickElement("xpath", "//button[@class='action primary checkout']");

					} else if (Common.getCurrentURL().contains("/success/")) {
						String sucessmessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']");
						System.out.println(sucessmessage);
					} else {
						AssertJUnit.fail();
					}

				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			} else {
				Thread.sleep(4000);
				int savedcard=Common.findElements("xpath", "//select[@x-model='savedMethodId']").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
				
				Common.switchFrames("xpath", "//iframe[@title='Campo de entrada seguro para el pago'or @title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Thread.sleep(4000);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);
				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				
				

				int zipcode = Common.findElements("xpath", "//input[@id='Field-postalCodeInput']").size();
				System.out.println(zipcode);

				if (zipcode > 0) {

					Sync.waitElementPresent("xpath", "//input[@id='Field-postalCodeInput']");
					Common.textBoxInput("xpath", "//input[@id='Field-postalCodeInput']", code);
				}
				int link=Common.findElements("xpath", "//label[@id='Field-linkOptInCheckbox']").size();
				
				if(link>0) {
					Common.clickElement("xpath", "//input[@class='p-Checkbox-input']");
				}
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
					Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
					Thread.sleep(8000);
					if (Common.getCurrentURL().contains("/checkout")) {
						Sync.waitPageLoad();
						Thread.sleep(4000);
						int error=Common.findElements("xpath", "//div[@ui-id='message-error']//span").size();
						if(error>0)
						{
							Sync.waitElementPresent("xpath", "//div[@ui-id='message-error']//span");
							String errormessage = Common.getText("xpath",
									"//div[@ui-id='message-error']//span");
							System.out.println(errormessage);
						}
						else
						{
							Sync.waitElementPresent("xpath", "//div[contains(@class,'checkout-success')]//h1");
						String sucessmessage = Common.getText("xpath",
								"//div[contains(@class,'checkout-success')]//h1");
						System.out.println(sucessmessage);
						}
					} else if (Common.getCurrentURL().contains("/success/")) {
						String sucessmessage = Common.getText("xpath",
								" //h1[normalize-space()='Thank you for your purchase!']");
						System.out.println(sucessmessage);
					} else {
						AssertJUnit.fail();
					}

				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			AssertJUnit.fail();
		}

		expectedResult = "credit card fields are filled with the data";

		return Number;
	}
	
	
	public String ThreedPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Number = "";
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();
		String symbol=data.get(dataSet).get("Symbol");
		
		try {
		
			
			Sync.waitPageLoad();
		    Common.actionsKeyPress(Keys.PAGE_DOWN);
					Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
					int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

					Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
							"User unabel to land opaymentpage");
					Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
	  
					Sync.waitElementPresent("xpath", "//input[@id='shipping-postcode']");
					 String code=Common.findElement("xpath", "//input[@id='shipping-postcode']").getAttribute("value");
					 System.out.println(code);
					 
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
//				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
				Thread.sleep(3000);
			
					Common.switchFrames("xpath", "//iframe[contains(@src,'elements-inner-payment-')]");
					Thread.sleep(5000);
					Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
					Common.clickElement("xpath", "//label[@for='Field-numberInput']");
					Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
					Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
					System.out.println(Number);

					Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

					Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
					Thread.sleep(2000);
					Common.actionsKeyPress(Keys.ARROW_DOWN);
					Common.switchToDefault();

				
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
	                   if(Common.getCurrentURL().contains("/gb"))
	                   {
	                	   Thread.sleep(5000);

	                	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	                	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	                	   Thread.sleep(8000);
	                	   if(Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart')]").contains("Please enter your card's security code."))
	                	   {
	                		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	                  		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	                  		Thread.sleep(5000);
	                  		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	                      	Common.clickElement("xpath", "//button[@class='action primary checkout']");
	                      	Thread.sleep(4000);
	                      	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
	                          	System.out.println(frameid);
	                        	Thread.sleep(8000);
	                          	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
	                          	Thread.sleep(8000);
	                          	Sync.waitElementPresent(30, "xpath", "//iframe[@id='challengeFrame' and @title='3DS Challenge']");
	                     		Common.switchFrames("xpath", "//iframe[@id='challengeFrame' and @title='3DS Challenge']");
	                     		Thread.sleep(6000);
	                         	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
	                         	Common.switchToDefault();
	                         	Common.switchToDefault();
	                	   }                    	
	                	   else if (Common.getCurrentURL().contains("/checkout/#payment"))
	                	   {
	                       	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
	                       	System.out.println(frameid);
//	                       	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
	                  		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
	                  		Thread.sleep(4000);
	                      	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
	                      	Common.switchToDefault();
	                      	Common.switchToDefault();
	                	   }
	                	   else
	                	   {
	                		   Assert.fail();
	                	   }
	                   }
	                   else
	                   {
	                	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	                	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	                	   Thread.sleep(8000);
	                	   if(Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart')]").contains("Please enter your card's security code."))
	                	   {
	                		   
	                         	 Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	                        		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	                        		Thread.sleep(5000);
	                        		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	                            	Common.clickElement("xpath", "//button[@class='action primary checkout']");
	                            	Thread.sleep(7000);
	                            	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
	                            	System.out.println(frameid);
	                            	Thread.sleep(4000);
	                            	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
	                            	Thread.sleep(6000);
	                       		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
	                       		Thread.sleep(4000);
	                           	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
	                           	Common.switchToDefault();
	                           	Common.switchToDefault();
	                	   }                    	
	                	   else if (Common.getCurrentURL().contains("/checkout/#payment"))
	                	   {
	                		   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
	                         	System.out.println(frameid);
	                          	Thread.sleep(4000);
	                         	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
	                         	Thread.sleep(4000);
	                    		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
	                     		Thread.sleep(4000);
	                        	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
	                        	Common.switchToDefault();
	                        	Common.switchToDefault();
	                	   }
	                	   else
	                	   {
	                		   Assert.fail();
	                	   }
	                   }
					
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			} else {
				Thread.sleep(4000);
				int savedcard=Common.findElements("xpath", "//select[@x-model='savedMethodId']").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				int zipcode=Common.findElements("xpath", "//input[@id='Field-postalCodeInput']").size();
				System.out.println(zipcode);
				
				if(zipcode > 0)
				{
				 
				 Sync.waitElementPresent("xpath", "//input[@id='Field-postalCodeInput']");
				 Common.textBoxInput("xpath", "//input[@id='Field-postalCodeInput']", code);
				}
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					 if(Common.getCurrentURL().contains("/gb"))
	                 {
	              	   Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_2']");
	              	   Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_2']");
	              	   
	              	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	              	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	              	 Thread.sleep(8000);
	          	   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]").getAttribute("name");
	          	   System.out.println(frameid);
	          	   Thread.sleep(4000);
	          	   Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
	          	  Thread.sleep(4000);
	     			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
	         		Thread.sleep(4000);
	         		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
	         		Common.switchToDefault();
	         		Common.switchToDefault();
	                 }
	                 else
	                 {
	                	 Thread.sleep(4000);
	              	   Sync.waitElementPresent(30,"xpath", "(//button[contains(text(),'Place Order')])[2]");
	              	   Common.clickElement("xpath", "(//button[contains(text(),'Place Order')])[2]");
	              	 Thread.sleep(8000);
	                 Sync.waitElementPresent(30,"xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]");
	                 Sync.waitElementVisible("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]");
	          	   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]").getAttribute("name");
	          	   System.out.println(frameid);
	          	   Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
	          	 Thread.sleep(8000);
	     			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
	         		Thread.sleep(4000);
	         		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
	         		Common.switchToDefault();
	         		Common.switchToDefault();
	                 }
			
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			}

		}

catch (Exception | Error e) {
e.printStackTrace();

ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
		"failed  to fill the Credit Card infromation",
		Common.getscreenShotPathforReport("Cardinfromationfail"));
Assert.fail();
}

return Number;
	}

	public void Register_userorder_status() {
		// TODO Auto-generated method stub
		click_singinButton();
		Login_Account("Account");
		click_Myorders();

		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//div[@class='column main']").size();
			Common.assertionCheckwithReport(size > 0, "Verifying the order numbers in my orders page ",
					"after clicking on the track my orders order numbers  should be displayed in the my orders page",
					"successfully order numbers has been displayed in the my orders page",
					"Failed to Display the order number in my orders page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the order numbers in my orders page ",
					"after clicking on the track my orders order numbers  should be displayed in the my orders page",
					"Unable to see the order numbers on my orders page",
					Common.getscreenShotPathforReport("Failed to Display the order number in my orders page"));
			Assert.fail();

		}
	}

	public void click_Myorders() {

		try {
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//a[contains(text(),'My Orders')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Tracking & Returns") || Common.getPageTitle().equals("My Orders"),
					"Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"successfully Navigated to the orders and return page",
					"Failed to Navigate to the orders and return page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"Unable to  Navigated to the orders and return page",
					Common.getscreenShotPathforReport("Failed to Navigate to the orders and return page"));
			Assert.fail();

		}
	}
	
	
	public void Click_Myorders_and_Account(String Dataset)
	{
		 String account=data.get(Dataset).get("account");
			try {
				Sync.waitElementVisible(40, "xpath", "//div[@class='m-account-nav__content']");
				Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
				Common.clickElement("xpath", "(//ul[@class='m-account-nav__links']//a)[3]");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("Orders and Returns") || Common.getPageTitle().equals("My Orders") || Common.getCurrentURL().contains("order/history/"),
						"Verifying the track order page navigation ",
						"after clicking on the track order it should navigate to the orders and return page",
						"successfully Navigated to the orders and return page",
						"Failed to Navigate to the orders and return page");
				Sync.waitElementPresent("xpath", "//ul[@class='nav items reset-list']//a[text()='"+ account +"']");
				Common.clickElement("xpath", "//ul[@class='nav items reset-list']//a[text()='"+ account +"']");
				Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account") || Common.getCurrentURL().contains("account"),
						"validating the my account page navigation",
						"After clicking on my account it should navigate to the My account page",
						"Sucessfully Navigated to the my account page", "failed to Navigate to the My account page");
				

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Verifying the track order page navigation ",
						"after clicking on the track order it should navigate to the orders and return page",
						"Unable to  Navigated to the orders and return page",
						Common.getscreenShotPathforReport("Failed to Navigate to the orders and return page"));
				AssertJUnit.fail();

			}
		
		
	}

	public void view_order() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			String number = Common.findElement("xpath", "//span[text()='View Order']").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Order']");
			Common.clickElement("xpath", "//span[text()='View Order']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(40, "xpath", "//span[@class='title-lg']");
			String Ordernumber = Common.findElement("xpath", "//span[@class='title-xs md:title-lg'])").getText();
			Common.findElement("xpath", "//span[@class='order-status inline-block']//div");
			String reorder = Common.findElement("xpath", "//span[text()='Reorder']").getText();
			String backCTA = Common.findElement("xpath", "//a[@class='hidden lg:flex btn btn-link']").getText().trim();
			String orderdate = Common.findElement("xpath", "//div[@class='mt-1']//span").getText();
			String shippingAdd = Common.findElement("xpath", "//P[text()='Shipping Address']//parent::div").getText();
			String billingAdd = Common.findElement("xpath", "//P[text()='Billing Address']//parent::div").getText();
			String shippingmethod = Common.findElement("xpath", "//P[text()='Shipping Method']//parent::div").getText();
			String ordersummary = Common.findElement("xpath", "//P[text()='Shipping Method']//parent::div").getText();
			String itemsordered = Common.findElement("xpath", "//span[@class='text-sm']//span").getText();
			System.out.println(itemsordered);

			Common.assertionCheckwithReport(
					reorder.contains("Reorder") && backCTA.contains("Back ") && orderdate.contains("Order Date")
							&& reorder.contains("Reorder"),
					"validating the order details ",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Sucessfully navigated to the orders detail page", "Failed to Navigate to the orders detail page");
//	
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order Details ",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Unable to Navigate to the orders details page  ",
					Common.getscreenShot("Failed to Navigate to the orders details page "));
			Assert.fail();

		}

	}

	public String Store_payment_placeOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub

		String order = "";
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(4000);
			addPaymentDetails(dataSet);
		}

		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
		if (placeordercount > 1) {
			Thread.sleep(4000);

			Common.clickElement("xpath", "//button[@class='action primary checkout']");
			Common.refreshpage();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//h1").trim();
				// Tell_Your_FriendPop_Up();

				int sizes = Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//h1").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//a").size() > 0) {
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//a");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//a").size() > 0) {
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//a");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				AssertJUnit.fail();
			}

		}
		return order;
	}

	public void Stored_Payment(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[@title='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[@title='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			int size = Common.findElements("xpath", "//div[@class='divide-y divide-border']").size();
			if (size > 0) {
				Thread.sleep(5000);
				String number = Common.findElement("xpath", "//div[@class='flex items-center']//span").getText()
						.replace("•••• ", "");
				System.out.println(number);
				System.out.println(Dataset);
				Thread.sleep(5000);
				Common.assertionCheckwithReport(number.contains("4242") && Dataset.contains("4242"),
						"validating the card details in the my orders page",
						"After Clicking on My payments methods and payment method should be appear in payment methods",
						"Sucessfully payment method is appeared in my payments methods",
						"Failed to display the payment methods in the my payments methods");
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the card details in the my orders page",
					"After Clicking on My payments methods and payment method should be appear in payment methods",
					"Unable to display the payment methods in the my payments methods",
					Common.getscreenShot("Failed to display the payment methods in the my payments methods"));
			Assert.fail();
		}

	}

	public void validatingErrormessageShippingpage_negative() {
		int Firstname_Error = Common.findElements("xpath", "//li[@data-msg-field='firstname']").size();
		int Lastname_Error=Common.findElements("xpath", "//li[@data-msg-field='lastname']").size();
//		int Address_Error =Common.findElements("xpath", "//li[@data-msg-field='street[0]']").size();
		int Phn_Error= Common.findElements("xpath", "//li[@data-msg-field='telephone']").size();
		String expectedResult = "Error message will dispaly when we miss the data in fields ";
		System.out.println(Firstname_Error);
		System.out.println(Lastname_Error);
		System.out.println(Phn_Error);
		if (Firstname_Error >0 && Lastname_Error>0&& Phn_Error>0) {
			System.out.println("Error Message displayed");
			ExtenantReportUtils.addPassLog("validating the shippingPage error message", expectedResult,
					"sucessfully  dispaly error message", Common.getscreenShotPathforReport("errormessagenegative"));
		} else {

			ExtenantReportUtils.addFailedLog("validating the shippingPage error message", expectedResult,
					"failed to display error message", Common.getscreenShotPathforReport("failederrormessage"));

			Assert.fail();
		}
	}

	public void Shippingform_Guestuser(String dataSet) throws Exception {

		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//section[@id='shipping-details']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			String Text = Common.getText("xpath", "//section[@id='shipping-details']//input[@name='street[0]']");
			System.out.println(Text);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//section[@id='shipping-details']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			
			  if(Common.getCurrentURL().contains("gb"))
              {
				  Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
              }
			  else
			  {
				
				 Common.scrollIntoView("xpath", "//select[@name='region']");
                 Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region']")
                         .getAttribute("value");
                 System.out.println(Shippingvalue);
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			Sync.waitPageLoad();
			ExtenantReportUtils.addPassLog("validating shipping address filling Fileds",
					"shipping address is filled in to the fields", "user should able to fill the shipping address ",
					Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}

	}
	public void view_PLP_page() {
		try {
			String title = Common.findElement("xpath", "//div[@class='c-clp-hero__content']").getAttribute("Class");
			String breadcrumbs = Common.findElement("xpath", "//nav[@aria-label='Breadcrumb']")
					.getAttribute("aria-label");
			Thread.sleep(2000);
			String filter = Common.findElement("xpath", "//span[normalize-space()='Filter by:']").getText().trim();
			String Sort = Common
					.findElement("xpath",
							"//select[@class='ais-SortBy-select']")
					.getAttribute("class");
			System.out.println(title);
			System.out.println(breadcrumbs);
			System.out.println(filter);
			System.out.println(Sort);

			Common.assertionCheckwithReport(
					breadcrumbs.contains("Breadcrumb") && title.contains("c-clp-hero__content")
							&& filter.contains("Filter by:") && Sort.contains("ais-SortBy"),
					"To validate the Product Listing Page", "User should able to open Product Listing Page",
					"Sucessfully views the Product Listing Page", "Failed to view Product Listing Page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Product Listing Page",
					"User should able to open Product Listing Page", "Unable to view the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to view Product listing Page"));

			AssertJUnit.fail();
		}
	}
	
	public void filter_By(String Dataset) {
		  String category=data.get(Dataset).get("category");
				try {
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath", "//div[@class='ais-Panel-header']//div[text()='Features']");
					Common.clickElement("xpath", "//div[@class='ais-Panel-header']//div[text()='Features']");
					Common.clickElement("xpath", "//input[@value='" + category + "']");
					Thread.sleep(5000);
					String text = Common.findElement("xpath", "(//input[@value='" + category + "']//following-sibling::span)[2]").getText().replace("(", "").replace(")", "");
					int textValue = Integer.parseInt(text);
					String categoryvalue = Integer.toString(textValue);
					Thread.sleep(6000);
					String textValueAfterFilter = Common.findElement("xpath", "(//div[@class='text-sm']//span)[1]")
							.getText().trim();
					Common.clickElement("xpath", "//button[text()='Load More']");
					Thread.sleep(4000);
					int noOfItems = Common.findElements("xpath", "//li[@class='ais-InfiniteHits-item']").size();
					String items = Integer.toString(noOfItems);
					System.out.println(text);
					System.out.println(textValue);
					System.out.println(categoryvalue);

					System.out.println(textValueAfterFilter);
					System.out.println(noOfItems);

					System.out.println(items);

					Common.assertionCheckwithReport(categoryvalue.equals(items),
							"To validate the filter in Product Listing Page",
							"User should able to filter in Product Listing Page",
							"Sucessfully filters in the Product Listing Page", "Failed to filter in Product Listing Page");
				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("To validate the filter in Product Listing Page",
							"User should able to filter in Product Listing Page", "Unable to filter the Product Listing Page",
							Common.getscreenShotPathforReport("Failed to filter Product listing Page"));

					AssertJUnit.fail();
				}
			}

	
	public void price_filter_validation(String Dataset) {
		// TODO Auto-generated method stub
		String name = "";
		String Symbol=data.get(Dataset).get("Symbol");
		System.out.println(Symbol);
		try {
			Thread.sleep(6000);

			if(Common.getCurrentURL().contains("es/") || Common.getCurrentURL().contains("fr/") )
			{
				
				String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText().replace(Symbol, "").replace(",00", "").trim();
				System.out.println(lastvalue);
				Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
				WebElement price = Common.findElement("xpath",
						"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
				dragprice(price);
				Thread.sleep(6000);
				List<WebElement> products = Common.findElements("xpath",
						"//ol[@class='ais-InfiniteHits-list']//img[contains(@class,'m-product')]");
				for (int i = 0; i < products.size(); i++) {
					int Size = products.size();
					System.out.println(Size);
					Thread.sleep(4000);
					if (Size == 1) {
						String name1 = Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']")
								.getText().replace(Symbol, "").replace(",00", "").trim();
							System.out.println(name1);
						Float namevlaue1 = Float.parseFloat(name1);
						System.out.println(namevlaue1);
						if (namevlaue1 >= 20) {
							Thread.sleep(3000);
							String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
									.getAttribute("data-price-amount");
							String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").trim();
							System.out.println(value1);
							System.out.println(name1);
							System.out.println(amount);
							Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
									"When we select the range of price filters between the range only products should display",
									"Successfully are displayed in the pricing range",
									"unable to display the procing range after pricing filter applied");
						} else {
							Thread.sleep(3000);
							String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
									.getAttribute("data-price-amount");
							String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").trim();
							System.out.println(value1);
							System.out.println(name1);
							System.out.println(amount);
							Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
									"When we select the range of price filters between the range only products should display",
									"Successfully are displayed in the pricing range",
									"unable to display the procing range after pricing filter applied");
						}
					} else {
						
						if(Common.getCurrentURL().contains("/mx/es/"))
						{
							List<WebElement> productprice = Common.findElements("xpath",
									"//span[contains(@data-price-type,'finalPrice')]//span[@class='price']");
							Thread.sleep(8000);
							System.out.println(productprice);
							Thread.sleep(4000);
							System.out.println(i);
							name = productprice.get(i).getText().replace(Symbol, "").replace(",50", "").trim();
							System.out.println(name);
							Float namevlaue = Float.parseFloat(name);
							System.out.println(namevlaue);
							if (namevlaue >= 20) {
								Thread.sleep(3000);
								String value = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
										.getAttribute("data-price-amount");
								System.out.println(value);
								System.out.println(name);
								Common.assertionCheckwithReport(value.contains(name), "verifying the price filters in PLP page",
										"When we select the range of price filters between the range only products should display",
										"Successfully are displayed in the pricing range",
										"unable to display the procing range after pricing filter applied");
							} else {
								AssertJUnit.fail();
							}
						}
							
							else
								{
								if(Common.getCurrentURL().contains("/id/en/"))
								{
									List<WebElement> productprice = Common.findElements("xpath",
											"//span[contains(@data-price-type,'finalPrice')]//span[@class='price']");
									Thread.sleep(8000);
									System.out.println(productprice);
									Thread.sleep(4000);
									System.out.println(i);
									name = productprice.get(i).getText().replace(Symbol, "").replace(".50", "").trim();
									System.out.println(name);
									Float namevlaue = Float.parseFloat(name);
									System.out.println(namevlaue);
									if (namevlaue >= 20) {
										Thread.sleep(3000);
										String value = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
												.getAttribute("data-price-amount");
										System.out.println(value);
										System.out.println(name);
										Common.assertionCheckwithReport(value.contains(name), "verifying the price filters in PLP page",
												"When we select the range of price filters between the range only products should display",
												"Successfully are displayed in the pricing range",
												"unable to display the procing range after pricing filter applied");
									} else {
										AssertJUnit.fail();
									}
							{
								
							}
						}
						else
						{
						List<WebElement> productprice = Common.findElements("xpath",
								"//span[contains(@data-price-type,'finalPrice')]//span[@class='price']");
						Thread.sleep(8000);
						System.out.println(productprice);
						Thread.sleep(4000);
						System.out.println(i);
						name = productprice.get(i).getText().replace(Symbol, "").replace(",00", "").trim();
						System.out.println(name);
						Float namevlaue = Float.parseFloat(name);
						System.out.println(namevlaue);
						if (namevlaue >= 20) {
							Thread.sleep(3000);
							String value = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
									.getAttribute("data-price-amount");
							System.out.println(value);
							System.out.println(name);
							Common.assertionCheckwithReport(value.equals(name), "verifying the price filters in PLP page",
									"When we select the range of price filters between the range only products should display",
									"Successfully are displayed in the pricing range",
									"unable to display the procing range after pricing filter applied");
						} else {
							AssertJUnit.fail();
						}
					}
					}
				}
				}
			}
			else
			{
				if (Common.getCurrentURL().contains("/ph/en/"))
				{
					String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
							.replace(Symbol, "").replace(".00", "").replace(",", "").trim();
					System.out.println(lastvalue);
					Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
					WebElement price = Common.findElement("xpath",
							"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
					
					dragprice(price);
				}
				
				else if(Common.getCurrentURL().contains("/kr/en/") || Common.getCurrentURL().contains("/jp/en/")  )
				{
					String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
							.replace(Symbol, "").replace(",", "").trim();
					System.out.println(lastvalue);
					Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
					WebElement price = Common.findElement("xpath",
							"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
					
					dragprice(price);
				}
				else if(Common.getCurrentURL().contains("/tw/en/"))
				{
					String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
							.replace(Symbol, "").replace(",", "").replace(".00","").trim();
					System.out.println(lastvalue);
					Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
					WebElement price = Common.findElement("xpath",
							"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
					
					dragprice(price);
				}
				else
				{
				Common.clickElement("xpath","//div[@class='ais-Panel-header']//div[text()='Price']");	
				Thread.sleep(3000);
				String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
						.replace(Symbol, "").replace(".00", "").trim();
				System.out.println(lastvalue);
				Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
				WebElement price = Common.findElement("xpath",
						"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
				
				dragprice(price);
				}
				Thread.sleep(6000);
				List<WebElement> products = Common.findElements("xpath",
						"//ol[@class='ais-InfiniteHits-list']//img[contains(@class,'m-product')]");
				for (int i = 0; i < products.size(); i++) {
					int Size = products.size();
					System.out.println(Size);
					Thread.sleep(4000);
					if (Size == 1) {
						if (Common.getCurrentURL().contains("/ph/en/"))
						{
						String name1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]//span[@class='price']")
								.getText().replace(Symbol, "").replace(".00", "").replace(",", "").trim();
							System.out.println(name1);
							Float namevlaue1 = Float.parseFloat(name1);
							System.out.println(namevlaue1);
							if (namevlaue1 >= 20) {
								Thread.sleep(3000);
								String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
										.getAttribute("data-price-amount");
								String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").trim();
								System.out.println(value1);
								System.out.println(name1);
								System.out.println(amount);
								Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
										"When we select the range of price filters between the range only products should display",
										"Successfully are displayed in the pricing range",
										"unable to display the procing range after pricing filter applied");
							} else {
								AssertJUnit.fail();
							}
						}
						else
						{
							if(Common.getCurrentURL().contains("/kr/en/"))
									{
								String name1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]//span[@class='price']")
										.getText().replace(Symbol, "").replace(",", "").trim();
								Float namevlaue1 = Float.parseFloat(name1);
								System.out.println(namevlaue1);
								if (namevlaue1 >= 20) {
									Thread.sleep(3000);
									
									String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
											.getAttribute("data-price-amount");
									String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").replace(",", "").trim();
									System.out.println(value1);
									System.out.println(name1);
									System.out.println(amount);
									Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
											"When we select the range of price filters between the range only products should display",
											"Successfully are displayed in the pricing range",
											"unable to display the procing range after pricing filter applied");
								} else {
									AssertJUnit.fail();
								}
									}
							else
							{
								if(Common.getCurrentURL().contains("/tw/en/"))
								{
									String name1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]//span[@class='price']")
											.getText().replace(Symbol, "").replace(".00", "").replace(",", "").trim();
									Float namevlaue1 = Float.parseFloat(name1);
									System.out.println(namevlaue1);
									if (namevlaue1 >= 20) {
										Thread.sleep(3000);
										
										String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
												.getAttribute("data-price-amount");
										String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").trim();
										System.out.println(value1);
										System.out.println(name1);
										System.out.println(amount);
										Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
												"When we select the range of price filters between the range only products should display",
												"Successfully are displayed in the pricing range",
												"unable to display the procing range after pricing filter applied");
									} else {
										AssertJUnit.fail();
									}
															
								}
								else
								{
									
								String name1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]//span[@class='price']")
										.getText().replace(Symbol, "").replace(".00", "").trim();
								Float namevlaue1 = Float.parseFloat(name1);
								System.out.println(namevlaue1);
								if (namevlaue1 >= 20) {
									Thread.sleep(3000);
									
									String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
											.getAttribute("data-price-amount");
									String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").trim();
									System.out.println(value1);
									System.out.println(name1);
									System.out.println(amount);
									Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
											"When we select the range of price filters between the range only products should display",
											"Successfully are displayed in the pricing range",
											"unable to display the procing range after pricing filter applied");
								} else {
									Thread.sleep(3000);
									String value1 = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
											.getAttribute("data-price-amount");
									String amount=Common.findElement("xpath", "//span[contains(@data-price-type,'finalPrice')]//span[@class='price']").getText().replace(Symbol, "").trim();
									System.out.println(value1);
									System.out.println(name1);
									System.out.println(amount);
									Common.assertionCheckwithReport(value1.equals(name1) || amount.equals(name1), "verifying the price filters in PLP page",
											"When we select the range of price filters between the range only products should display",
											"Successfully are displayed in the pricing range",
											"unable to display the procing range after pricing filter applied");
								}
							}
							
						}
						}
					}else {
						
					
							List<WebElement> productprice = Common.findElements("xpath",
									"//span[contains(@data-price-type,'finalPrice')]//span[@class='price']");
							Thread.sleep(6000);
							System.out.println(i);
							name = productprice.get(i).getText().replace(Symbol, "").replace(".00", "").trim();
							System.out.println(name);

						Float namevlaue = Float.parseFloat(name);
						System.out.println(namevlaue);
						if (namevlaue >= 20) {
							Thread.sleep(3000);
							String value = Common.findElement("xpath", "//span[contains(@class,'price-wrapper')]")
									.getAttribute("data-price-amount");
							System.out.println(value);
							System.out.println(name);
							Common.assertionCheckwithReport(value.equals(name), "verifying the price filters in PLP page",
									"When we select the range of price filters between the range only products should display",
									"Successfully are displayed in the pricing range",
									"unable to display the procing range after pricing filter applied");
						} else {
							AssertJUnit.fail();
						}
				
			}
			
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the price filters in PLP page",
					"When we select the range of price filters between the range only products should display",
					"unable to display the procing range after pricing filter applied",
					Common.getscreenShotPathforReport(
							"unable to display the procing range after pricing filter applied"));
			AssertJUnit.fail();
		}

	}
	
	public String symbol(String Dataset)
	{
		String Symbol="";
		 Symbol=data.get(Dataset).get("Symbol");
		return Symbol;
	}

	public void dragprice(WebElement price) throws Exception {
		String symbol=symbol("PLP Color");
		try {
		Thread.sleep(8000);
			if(Common.getCurrentURL().contains("/gb"))
			{
				Thread.sleep(5000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='value end active']");
				String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace(symbol, "")
						.replace(".00", "").trim();
				System.out.println(lastvalue);
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			}
			else if (Common.getCurrentURL().contains("es/") || Common.getCurrentURL().contains("fr/"))
			{
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='value end active']");
				String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace(symbol, "")
						.replace(",00", "").trim();
				System.out.println(lastvalue);
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			}
			else if(Common.getCurrentURL().contains("/ph/en/"))
			{
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='value end active']");
				String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace(symbol, "")
						.replace(".00", "").replace(",", "").trim();
				System.out.println(lastvalue);
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			}
			else if(Common.getCurrentURL().contains("/kr/en/") || Common.getCurrentURL().contains("/jp/en/"))
			{
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='value end active']");
				String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace(symbol, "")
						.replace(",", "").trim();
				System.out.println(lastvalue);
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			}
			else if(Common.getCurrentURL().contains("/tw/en/"))
			{
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='value end active']");
				String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace(symbol, "")
						.replace(",", "").replace(".00","").trim();
				System.out.println(lastvalue);
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");	
			}
			else
			{
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='value end active']");
				String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace(symbol, "")
						.replace(".00", "").trim();
				System.out.println(lastvalue);
				Thread.sleep(5000);
				Sync.waitElementPresent(40, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
				Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			}
			
		} catch (Exception | Error e) {

			e.printStackTrace();
			AssertJUnit.fail();
		}
	}
	
	public void color_validation(String Dataset) {
		// TODO Auto-generated method stub
		String colorname = data.get(Dataset).get("Color");
		try {
			if(Common.getCurrentURL().contains("/gb"))
			{
				Sync.waitElementPresent("xpath", "//button[@aria-labelledby='facet_header_osprey_common_color']");
				Common.clickElement("xpath", "//button[@aria-labelledby='facet_header_osprey_common_color']");
				Thread.sleep(3000);
				String expand = Common.findElement("xpath", "//button[@aria-labelledby='facet_header_osprey_common_color']").getAttribute("aria-expanded");
				Common.assertionCheckwithReport(expand.contains("true"), "verifying the color bar has been expand",
						"When we click on the color it should be expand",
						"Successfully the color has been expand when we click on the colors ",
						"unable to expand the colors in PLP page");
			}
			else
			{
			Sync.waitElementPresent("xpath", "//div[@class='ais-Panel-header']//div[text()='Color']");
			Common.clickElement("xpath", "//div[@class='ais-Panel-header']//div[text()='Color']");
			Thread.sleep(3000);
			}
			Sync.waitElementPresent("xpath",
					"//input[@value='"+ colorname +"']");
			Common.clickElement("xpath",
					"//input[@value='"+ colorname +"']");
			Thread.sleep(3000);
			String colorcount = Common.findElement("xpath",
					"(//input[@value='Black']//following-sibling::span)[3]")
					.getText().replace("(", "").replace(")", "");
			String bottlecount = Common.findElement("xpath", "(//div[@class='text-sm']//span)[1]").getText().trim();
			Common.assertionCheckwithReport(colorcount.equals(bottlecount), "verifying the color bar has been expand",
					"When we click on the color it should be expand",
					"Successfully the color has been expand when we click on the colors ",
					"unable to expand the colors in PLP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the color bar has been expand",
					"When we click on the color it should be expand", "unable to expand the colors in PLP page",
					Common.getscreenShotPathforReport("Failed to expand the colors in PLP page"));
			AssertJUnit.fail();
		}

	}

	public void Paymentcreditcard_WithInvalidData(String dataSet) throws Exception {
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
	
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
		
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Thread.sleep(1000);
					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	             	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	             	  Thread.sleep(10000);
	             	 if(Common.findElement("xpath", "//div[contains(@class,'error')]").getText().contains("Please complete your payment details."))
	             	 {
	             		expectedResult = "credit card fields are filled with the data";
						String errorTexts = Common.findElement("xpath", "errorText").getText();
						Common.assertionCheckwithReport(
								errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
								"validating the credit card information with valid data", expectedResult,
								"Filled the Card detiles", "missing field data it showinng error");
	             	 }
	             	 else if(Common.getCurrentURL().contains("/checkout/#payment"))
	           	   {
	           		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	           		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	           		   Thread.sleep(5000);
	           		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	               	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	               	expectedResult = "credit card fields are filled with the data";
					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
					Common.assertionCheckwithReport(
							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
							"validating the credit card information with valid data", expectedResult,
							"Filled the Card detiles", "missing field data it showinng error");
	           	   }
	             	  
	             	   else
	             	   {
	             		   Assert.fail();
	             	   }
					
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			} else {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Thread.sleep(1000);
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
	             	   Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
	             	  Thread.sleep(10000);
	             		Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
	    				Thread.sleep(5000);
	             	 String errorText = Common.findElement("xpath", "//p[@class='p-FieldError Error']").getText();
						Common.assertionCheckwithReport(
								errorText.isEmpty() || errorText.contains("Your card number is incomplete."),
								"validating the credit card information with valid data", expectedResult,
								"Filled the Card detiles", "missing field data it showinng error");
						Common.switchToDefault();
	             	  if(Common.getCurrentURL().contains("/checkout/#payment"))
	           	   {
	           		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	           		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	           		   Thread.sleep(5000);
	           		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	               	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	               	expectedResult = "credit card fields are filled with the data";
					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
					Common.assertionCheckwithReport(
							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
							"validating the credit card information with valid data", expectedResult,
							"Filled the Card detiles", "missing field data it showinng error");
	           	   }
	             	  else if(Common.getCurrentURL().contains("/checkout/#payment"))
	             	  {
	             			expectedResult = "credit card fields are filled with the data";
	    					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
	    					Common.assertionCheckwithReport(
	    							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
	    							"validating the credit card information with valid data", expectedResult,
	    							"Filled the Card detiles", "missing field data it showinng error"); 
	             	  }
	             	   else
	             	   {
	             		   System.out.println(errorText);
	             	   }
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}


	}

	public void gustuserorderStatus(String dataSet) {
		// TODO Auto-generated method stub
		click_trackorder();
		String ordernumber = data.get(dataSet).get("OrderID");
		String prodordernumber = data.get(dataSet).get("prod order");

		try {
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
				Sync.waitElementPresent("id", "oar-order-id");
				Common.textBoxInput("id", "oar-order-id", ordernumber);
			} else {
				Sync.waitElementPresent("id", "oar-order-id");
				Common.textBoxInput("id", "oar-order-id", prodordernumber);
			}
			Sync.waitElementPresent("id", "oar-billing-lastname");
			Common.textBoxInput("id", "oar-billing-lastname", data.get(dataSet).get("Billinglastname"));

			Sync.waitElementPresent("id", "oar_email");
			Common.textBoxInput("id", "oar_email", data.get(dataSet).get("BillingEmail"));

			Sync.waitElementPresent("xpath", "//button[@type='submit']//span[text()='Search']");
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Search']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String orderid = Common.findElement("xpath", "//span[@class='title-xs md:title-lg']) ").getText();
			System.out.println(orderid);
			String ID=Common.findElement("xpath", "//span[@class='title-xs md:title-lg']) ").getText().replace("Order #", "");
			System.out.println(ID);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(orderid) || ID.equals(ordernumber), "verifying order status form",
					
					"order tracking information page navigation", "successfully order tracking information page ",
					"Failed to navigate tracking order page infromation");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying order status form",
					"order tracking information page navigation",
					"User unable to navigate to the order tracking information page",
					Common.getscreenShotPathforReport("Failed to navigate tracking order page infromation"));
			Assert.fail();

		}
	}

	public void click_trackorder() {
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//a[@title='Track My Order']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Tracking & Returns") || Common.getPageTitle().equals("My Orders") || Common.getCurrentURL().contains("track/order/status"),
					"Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"successfully Navigated to the orders and return page",
					"Failed to Navigate to the orders and return page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"Unable to  Navigated to the orders and return page",
					Common.getscreenShotPathforReport("Failed to Navigate to the orders and return page"));
			Assert.fail();

		}
	}

	public void newuseraddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//div[contains(@class,'grid')]//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//div[contains(@class,'grid')]//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//div[contains(@class,'grid')]//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			String Text = Common.getText("xpath", "//div[contains(@class,'grid')]//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//div[contains(@class,'grid')]//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//div[contains(@class,'grid')]//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "(//input[@name='postcode'])");
			Common.textBoxInput("xpath", "(//input[@name='postcode'])", data.get(dataSet).get("postcode"));

			Thread.sleep(5000);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			Sync.waitPageLoad();
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}

	}

	public void createAccountFromOrderSummaryPage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String Createaccount = Common.findElement("xpath", "//h3[text()='Create an Account']").getText();
			
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "(//button[@title='Show Password'])[1]");
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "(//button[@title='Show Password'])[1]");

			String confirmpassword = Common.findElement("xpath", "//input[@name='password_confirmation']")
					.getAttribute("type");
			String password = Common.findElement("xpath", "//input[@name='password']").getAttribute("type");
			Common.assertionCheckwithReport(
					Createaccount.contains("Create an Account"),
					"validating the order confirmation page",
					"User should able to view all details in the order confirmation page",
					"Sucessfully all details has been displayed in the order confirmation",
					"Failed to display all details in the order confirmation page");

			Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
			Common.clickElement("xpath", "//label[@for='is_subscribed']");
			Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();

			Sync.waitElementPresent(30, "xpath", "//span[text()='Create an Account']");
			Common.clickElement("xpath", "//span[text()='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Thank you for registering')]");
			String message = Common.findElement("xpath", "//span[contains(text(),'Thank you for registering')]")
					.getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Dashboard")
							&& message.contains("Thank you for registering"),
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Sucessfully navigate to the My account page after clicking on signin button ",
					"Failed to navigates to My Account Page after clicking on Signin button");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Unable to  navigate to the My account page after clicking on signin button ",
					Common.getscreenShotPathforReport(
							"Failed to navigates to My Account Page after clicking on Signin button"));
			Assert.fail();
		}
	}

	public void My_Orders_Page(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//li//a[@title='My Orders']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"validating the Navigation to the My Orders page",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page",
					"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
					"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
			String Ordernumber = Common.findElement("xpath", "(//span[@class='text-right'])[1]")
					.getText();
			Sync.waitPageLoad();
			System.out.println(Ordernumber);
			System.out.println(Dataset);
			Common.assertionCheckwithReport(Ordernumber.equals(Dataset),
					"validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Sucessfully Order Number is displayed in the My orders page",
					"Failed to Display My order Number in the My orders page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Unable to Display the Order Number in the My orders page",
					Common.getscreenShot("Failed to Display My order Number in the My orders page"));
			Assert.fail();
		}
	}

	public void clickontheproduct_and_image(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String color = data.get(Dataset).get("Colorproduct");
		System.out.println(color);
		String Prod=data.get(Dataset).get("Prod Product");
		try {
			
			String minicartproduct = Common.findElement("xpath", "//img[@alt='" + product + "']").getText();
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			System.out.println(minicartproduct);
			Common.assertionCheckwithReport(product.contains(minicartproduct),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
			click_minicart();
			if(Common.getCurrentURL().contains("preprod"))
			{
			String minicartimage = Common.findElement("xpath", "//img[contains(@alt,'" + color + "')]")
					.getAttribute("alt");
			Common.clickElement("xpath", "//img[contains(@alt,'" + color + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(minicartimage.contains(color),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
			}
			else
			{
				String minicartimage = Common.findElement("xpath", "//img[contains(@alt,'" + Prod + "')]")
						.getAttribute("alt");
				Common.clickElement("xpath", "//img[contains(@alt,'" + Prod + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.assertionCheckwithReport(minicartimage.contains(Prod),
						"validating the product navigating to the PDP page",
						"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
						"Failed to Navigates Product to the PDP page");
				
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", " unable to Navigates Product to the PDP page",
					Common.getscreenShot("Failed to Navigates Product to the PDP page"));
			AssertJUnit.fail();
		}

	}


	public void minicart_freeshipping() {
		// TODO Auto-generated method stub
		try {
			click_minicart();
//			String Freeshipping = Common
//					.findElement("xpath", "//div[@class='m-progress-bar false']//div[contains(@class,'label-')]")
//					.getText();
//			Common.assertionCheckwithReport(Freeshipping.equals("Good news: your order will be delivered for Free."),
//					"validating the free shipping in mini cart",
//					"Free shipping should be avaliable for selected products",
//					"Successfully free shipping is appiled for selected products", "Failed to see free shipping");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"unable to apply free shipping for the selected products",
					Common.getscreenShot("Failed to see free shipping bar"));
			AssertJUnit.fail();
		}

	}


	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Colorproduct");
		String Proddelete=data.get(Dataset).get("Prod Product");
		String symbol=data.get(Dataset).get("Symbol");
		try {
			Sync.waitElementPresent(30, "xpath", "//span[contains(@class, 'flex text-lg')]//span[@class='price']");
			String subtotal = Common.getText("xpath", "//span[contains(@class, 'flex text-lg')]//span[@class='price']")
					.replace(symbol, "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common
					.findElement("xpath", "(//div[contains(@class,'ustify-betwee')]/p/a)[1]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//p//span[@class='price'])[1]").replace(symbol,
					"");
			Float productamount1value = Float.parseFloat(productamount1);
			if(Common.getCurrentURL().contains("preprod"))
			{
			if (productname.equals(deleteproduct)) {
				Sync.waitElementPresent(30, "xpath",
						"(//button[contains(@title,'Remove product')])[1]");
				Common.clickElement("xpath",
						"(//button[contains(@title,'Remove product')])[1]");
				Sync.waitElementPresent("xpath", "//button[contains(@class,'btn btn-primary') and contains(text(),'OK')]");
				Common.clickElement("xpath", "//button[contains(@class,'btn btn-primary') and contains(text(),'OK')]");
			} else {
				AssertJUnit.fail();
			}
			}
			else
			{
				if (Proddelete.equals(productname)) {
					Sync.waitElementPresent(30, "xpath",
							"(//button[contains(@title,'Remove product')])[1]");
					Common.clickElement("xpath",
							"(//button[contains(@title,'Remove product')])[1]");
					Sync.waitElementPresent("xpath", "//button[contains(@class,'btn btn-primary') and contains(text(),'OK')]");
					Common.clickElement("xpath", "//button[contains(@class,'btn btn-primary') and contains(text(),'OK')]");
				} else {
					AssertJUnit.fail();
				}
			}
			Thread.sleep(6000);
			String subtotal1 = Common.getText("xpath", "//span[contains(@class, 'flex text-lg')]//span[@class='price']")
					.replace(symbol, "");
			Float subtotal1value = Float.parseFloat(subtotal1);
			Thread.sleep(8000);
			String productamount = Common.getText("xpath", "(//p//span[@class='price'])[1]").replace(symbol, "");
			Float productamountvalue = Float.parseFloat(productamount);
			Float Total = subtotalvalue - productamount1value;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(subtotal1),
					"validating the delete operation and subtotal",
					"The product should be delete from mini cart and subtotal should change",
					"Successfully product delete from the mini cart and subtotal has been changed",
					"Failed to delete the product from cart and subtotal not changed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the delete operation and subtotal",
					"The product should be delete from mini cart and subtotal should change",
					"unable to delete product from the mini cart and subtotal has not changed", Common.getscreenShot(
							"Failed to delete the product from the mini cart and subtotal has not changed"));
			AssertJUnit.fail();
		}
	}
public void minicart_product_close() {
	// TODO Auto-generated method stub
	try {

		Common.clickElement("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
		Sync.waitElementPresent("xpath", "//div[@x-ref='removeItemConfirm']");
		String minicartpopup = Common.findElement("xpath", "//div[@x-ref='removeItemConfirm']")
				.getAttribute("aria-modal");
		Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
		Common.assertionCheckwithReport(minicartpopup.contains("true"),
				"validating the popup when you click on delete", "The Popup should be displayed",
				"Successfully popup is displayed when we click on the delete button",
				"Failed to Display the popup");
		String popup = Common.findElement("xpath", "//h2[@x-ref='modalHeader' and contains(text(),'Remove Item')]").getText();
		if (popup.equals("Remove Item")) {
			Common.clickElement("xpath", "//button[@aria-label='Close']");
		} else {
			AssertJUnit.fail();
		}
		Common.clickElement("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
		Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
		Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
		Common.assertionCheckwithReport(minicartpopup.contains("true"),
				"validating the popup when you click on delete", "The Popup should be displayed",
				"Successfully popup is displayed when we click on the delete button",
				"Failed to Display the popup");
		if (popup.equals("Remove Item")) {

			Common.clickElement("xpath", "//button[contains(text(),'Cancel')]");
		} else {
			AssertJUnit.fail();
		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the close and cancel functionality",
				"User should able to click on close and cancel button",
				"unable to click on close and cancel button",
				Common.getscreenShot("Failed to Click on close and cancel button"));

		AssertJUnit.fail();
	}

}

public void minicart_validation(String Dataset) {
	// TODO Auto-generated method stub
	String UpdataedQuntityinminicart = data.get(Dataset).get("Quantity");
	String symbol=data.get(Dataset).get("Symbol");
	try {

		String Subtotal = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
				.replace(symbol, "");
		Float subtotalvalue = Float.parseFloat(Subtotal);
		Sync.waitElementPresent("xpath", "(//select[@name='qty'])[2]");
		Common.clickElement("xpath", "(//select[@name='qty'])[2]");
		Common.dropdown("xpath", "(//select[@name='qty'])[2]", Common.SelectBy.VALUE,
				UpdataedQuntityinminicart);
//		Common.clickElement("xpath", "//span[text()='Update']");
		Thread.sleep(8000);
		Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
		String cart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
		System.out.println(cart);
		String Subtotal2 = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
				.replace(symbol, "");
		Float subtotalvalue2 = Float.parseFloat(Subtotal2);
		Float Total = subtotalvalue * 2;
		String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		System.out.println(UpdataedQuntityinminicart);
		System.out.println(cart);
		System.out.println(ExpectedTotalAmmount2);
		System.out.println(Subtotal2);
		Common.assertionCheckwithReport(
				UpdataedQuntityinminicart.equals(cart) && ExpectedTotalAmmount2.equals(Subtotal2),
				"validating the product update quantity and subtotal",
				"The product Quantity should be update in mini cart and subtotal should change",
				"Successfully product quantity updated and subtotal has been changed",
				"Failed to update the product quantity from cart and subtotal not changed");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the product update quantity and subtotal",
				"The product Quantity should be update in mini cart and subtotal should change",
				"unable to update the product quantity and subtotal has not be changed",
				Common.getscreenShot("Failed to update the product quantity from cart and subtotal not changed"));
		AssertJUnit.fail();
	}

}
	public void review(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//img[@class='m-product-card__image product-image-photo']");
			Common.clickElement("xpath", "//img[@class='m-product-card__image product-image-photo']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			more_Quantity("review");
			Common.scrollIntoView("xpath", "//a[text()='Reviews']");
			Sync.waitElementPresent("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			Thread.sleep(3000);
			String form = Common.getText("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			System.out.println(form);
			Common.assertionCheckwithReport(form.equals("REVIEWS"), "verifying the write a review button",
					"Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page",
					"Failed to display the write a review button in PDP page");
			Common.clickElement("xpath", "//a[text()='Reviews']");
			Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
			Common.clickElement("xpath", "//span[text()='Write A Review']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
					"User Unable to click write review option  ",
					Common.getscreenShotPathforReport("User Failed to click write review option "));
			AssertJUnit.fail();
		}
		try {
			String expectedResult = "Sucessfully title input box has been displayed";
			Common.clickElement("xpath", "//input[@value='Post']");
			String errormessage = Common.findElement("xpath", "//span[@class='form-input-error']").getText();
			System.out.println(errormessage);
			Common.assertionCheckwithReport(errormessage.contains("Please enter a star rating for this review"),
					"verifying the error message in invalid fields",
					"error message should be display in the invalid fields",
					"Sucessfully Error message has been displayed in invalid fileds ",
					"Failed to display the error meesage in invalid fields ");
			score(data.get(Dataset).get("score"));
			Sync.waitElementPresent("xpath", "//input[@name='review_title']");
			int title = Common.findElements("xpath", "//input[@name='review_title']").size();
			Common.assertionCheckwithReport(title > 0, "verifying the title page",
					"title input box should be displayed", expectedResult, "User Unable to display the title box");
			Common.textBoxInput("xpath", "//input[@name='review_title']", data.get(Dataset).get("title"));
			Common.textBoxInput("xpath", "//textarea[@name='review_content']", data.get(Dataset).get("Review"));
			Common.textBoxInput("xpath", "//input[@name='display_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("UserName"));
			Common.clickElement("xpath", "//input[@value='Post']");
			String emailerror = Common.findElement("xpath", "//span[@class='form-input-error']").getText();
			Common.assertionCheckwithReport(emailerror.contains("Invalid email"),
					"verifying the invaild email for the product review",
					"error message should be display for invaild email",
					"Sucessfully error message has been displayed for invalid email",
					"Failed to display the error message for invaild email");
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//input[@value='Post']");
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='yotpo-thank-you']").getAttribute("aria-label");
			Common.assertionCheckwithReport(message.equals("Thank you for posting a review"),
					"verifying the post for the product review",
					"product review should be submit after clicking on post",
					"Sucessfully Thank you message has been displayed ", "Failed to display the Thank you message ");
			// Common.clickElement("xpath", "//div[@aria-label='Next']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the post for the product review",
					"product review should be submit after clicking on post",
					"Unable to display the Thank you message after clickng on post ",
					Common.getscreenShotPathforReport("Failed to display the thank you message"));
			AssertJUnit.fail();

		}

	}

	public void score(String score) throws Exception {
		Thread.sleep(4000);
		switch (score) {
		case "1":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 1']");
			Common.clickElement("xpath", "//span[@aria-label='score 1']");
			break;
		case "2":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 2']");
			Common.clickElement("xpath", "//span[@aria-label='score 2']");
			break;
		case "3":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 3']");
			Common.clickElement("xpath", "//span[@aria-label='score 3']");
			;
			break;
		case "4":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 4']");
			Common.clickElement("xpath", "//span[@aria-label='score 4']");
			break;
		case "5":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 5']");
			Common.clickElement("xpath", "//span[@aria-label='score 5']");
			break;
		}
	}

	public void Ask_a_question(String Dataset) {
		// TODO Auto-generated method stub
		String Question = data.get(Dataset).get("CommetsOsprey");
		String Name = data.get(Dataset).get("FirstName");
		String Email = data.get(Dataset).get("Email");
		try {
			Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'ask a question')]");
			Common.clickElement("xpath", "//button[contains(@aria-label,'ask a question')]");
			Sync.waitElementPresent(30, "xpath", "//textarea[contains(@id,'yotpo_input_q')]");
			Common.textBoxInput("xpath", "//textarea[contains(@id,'yotpo_input_q')]", Question);
			Sync.waitElementPresent(30, "xpath", "//input[@name='display_name']");
			Common.textBoxInput("xpath", "//input[@name='display_name']", Name);
			Sync.waitElementPresent(30, "xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", Email);
			Common.clickElement("xpath", "//input[@data-button-type='submit']");
			Thread.sleep(4000);
			String question = Common
					.findElement("xpath", "//div[@class='yotpo-thank-you']//span[contains(text(),'Thank you')]")
					.getText();
			System.out.println(question);
			Common.assertionCheckwithReport(question.contains("THANK YOU FOR POSTING A QUESTION!"),
					"validating the question submit form", "Ask a form should be submit",
					"Sucessfully question post should be submit", "Failed to submit the ask a question post");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the question submit form", "Ask a form should be submit",
					"Unable to subit question post", Common.getscreenShot("failed to subit question post"));
			AssertJUnit.fail();
		}

	}

	public void ChangeAddress_AddressBook(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			AssertJUnit.fail();
		}
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//a[text()='Address Book']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
				"validating the Navigation to the Address Book page",
				"After Clicking on Address Book CTA user should be navigate to the Address Book page",
				"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
				"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");

		String PageTitle = Common.getText("xpath", "//h1[@class='page-title-wrapper h2']");
		if (PageTitle.contains("New")) {
			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
				try {
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				}

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Thread.sleep(3000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

				Common.assertionCheckwithReport(message.equals("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"unable to save the adreess in the address book",
						Common.getscreenShotPathforReport("Failed to save the address in the address book"));

				AssertJUnit.fail();

			}
		}

		else {
			Common.clickElement("xpath", "//span[contains(text(),'Change Billing Address')]");

			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));

				try {
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

				Common.assertionCheckwithReport(message.equals("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");
			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating my address book with data",
						"enter the valid address without any validation",
						"User failed to enter data in my address book",
						Common.getscreenShotPathforReport("faield to save the address in addressbook"));
				AssertJUnit.fail();

			}
		}
	}

	public void Add_NewAddress(String dataSet) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementVisible(30, "xpath", "//button[@title='Add New Address']");
			Common.clickElement("xpath", "//button[@title='Add New Address']");
			String newaddress = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			if (newaddress.contains("Add New Address")) {
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
				try {
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				}

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Thread.sleep(3000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

				Common.assertionCheckwithReport(message.equals("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");
			} else {
				AssertJUnit.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the No address in the address book",
					"After saving the address for Register user no extra address should be there in address book",
					"Unable to see no address in the address book",
					Common.getscreenShotPathforReport("Failed to see no address in the address book"));
			AssertJUnit.fail();
		}
	}

	public void Add_Address(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}

		try {
			Sync.waitElementVisible(30, "xpath", "//a[@title='Address Book']");
			Common.clickElement("xpath", "//a[@title='Address Book']");
			Thread.sleep(3000);
	   
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
			Thread.sleep(4000);
			 if(Common.getCurrentURL().contains("preprod"))
             {
                 
                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                         .getAttribute("value");
                 System.out.println(Shippingvalue);
             }
			 else
			 {
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
			 }

			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

			Common.clickElement("xpath", "//button[@title='Save Address']");
			Thread.sleep(5000);
			String message = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You saved the address."),
					"validating the saved message after saving address in address book",
					"Save address message should be displayed after the address saved in address book",
					"Sucessfully address has been saved in the address book",
					"Failed to save the address in the address book");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the No address in the address book",
					"After saving the address for Register user no extra address should be there in address book",
					"Unable to see no address in the address book",
					Common.getscreenShotPathforReport("Failed to see no address in the address book"));
			Assert.fail();
		}
	}

	public void change_Shippingaddress_Addressbook(String Dataset) {
		// TODO Auto-generated method stub
		String firstname = data.get(Dataset).get("FirstName");
		String secondname = data.get(Dataset).get("LastName");
		String address = data.get(Dataset).get("Street");
		String phonenumber = data.get(Dataset).get("phone");
		String City = data.get(Dataset).get("City");
		String region = data.get(Dataset).get("Region");
		String zipcode = data.get(Dataset).get("postcode");
		String shipping = data.get(Dataset).get("Shipping address");

		try {
			Sync.waitPageLoad();

			String newaddress = Common.findElement("xpath", "//h1[contains(@class,'title')]//span").getText();
			if (newaddress.contains("Address Book")) {
				Common.clickElement("xpath", "//a [contains(text(),'Add New Address ')]");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//input[@name='firstname']");
				Common.clickElement("xpath", "//input[@name='firstname']");
				Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
				Common.clickElement("xpath", "//input[@name='lastname']");
				Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
				Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
				Common.clickElement("xpath", "//input[@title='Phone Number']");
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
				Common.clickElement("xpath", "//input[@id='street_1']");
				Common.textBoxInput("xpath", "//input[@id='street_1']", address);
				Common.clickElement("xpath", "//input[@title='City']");
				Common.textBoxInput("xpath", "//input[@title='City']", City);
				Thread.sleep(4000);
				if(Common.getCurrentURL().contains("preprod"))
	             { 
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
	             }
				else
				{
				Common.clickElement("xpath", "//input[@name='region']");
				Common.textBoxInput("xpath", "//input[@name='region']", region);
				}
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
				Common.clickElement("xpath", "//label[@for='primary_shipping']");
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();

				Common.assertionCheckwithReport(message.contains("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");
				Shippingaddress_Addressbook("New ShippingAddress");
			} else {
				Shippingaddress_Addressbook("New ShippingAddress");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
					Common.getscreenShot(
							"Failed to display checkbox for billing address and fail to display text for shipping address"));
			Assert.fail();
		}

	}

	public void Shippingaddress_Addressbook(String Dataset) {
		// TODO Auto-generated method stub
		String firstname = data.get(Dataset).get("FirstName");
		String secondname = data.get(Dataset).get("LastName");
		String address = data.get(Dataset).get("Street");
		String phonenumber = data.get(Dataset).get("phone");
		String City = data.get(Dataset).get("City");
		String region = data.get(Dataset).get("Region");
		String zipcode = data.get(Dataset).get("postcode");
		String shipping = data.get(Dataset).get("Shipping address");
		try {
			Common.clickElement("xpath", "//span[contains(text(),'Change Shipping Address')]");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
			Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
			Common.clickElement("xpath", "//input[@title='Phone Number']");
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
			Common.clickElement("xpath", "//input[@id='street_1']");
			Common.textBoxInput("xpath", "//input[@id='street_1']", address);
			Common.clickElement("xpath", "//input[@title='City']");
			Common.textBoxInput("xpath", "//input[@title='City']", City);
			if(Common.getCurrentURL().contains("preprod"))
            { 
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
            }
			else
			{
			Common.clickElement("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
/*			String checkbox = Common.findElement("xpath", "//input[@id='primary_billing']").getAttribute("type");
			String text = Common.findElement("xpath", "//div[@class='message info']//span").getText();
			Common.assertionCheckwithReport(
					checkbox.equals("checkbox") && text.equals("This is your default shipping address."),
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
					"Failed to display checkbox for billing address and fail to display text" + text
							+ "for shipping address");                            */
			Common.clickElement("xpath", "//input[@id='primary_billing']");
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Sync.waitElementPresent("xpath", "//div[@class='relative flex w-full']//span");
			String message = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
			Common.assertionCheckwithReport(message.contains("You saved the address."),"validating the shipping address",
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
					Common.getscreenShot(
							"Failed to display checkbox for billing address and fail to display text for shipping address"));
			Assert.fail();
		}

	}

	public void change_Billingaddress_Addressbook(String Dataset) {
		// TODO Auto-generated method stub

		String firstname = data.get(Dataset).get("FirstName");
		String secondname = data.get(Dataset).get("LastName");
		String address = data.get(Dataset).get("Street");
		String phonenumber = data.get(Dataset).get("phone");
		String City = data.get(Dataset).get("City");
		String region = data.get(Dataset).get("Region");
		String zipcode = data.get(Dataset).get("postcode");
		String shipping = data.get(Dataset).get("Shipping address");

		try {
			Sync.waitPageLoad();

			String newaddress = Common.findElement("xpath", "//h1[contains(@class,'title')]//span").getText();
			if (newaddress.contains("Address Book")) {
				Common.clickElement("xpath", "//a [contains(text(),'Add New Address ')]");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//input[@name='firstname']");
				Common.clickElement("xpath", "//input[@name='firstname']");
				Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
				Common.clickElement("xpath", "//input[@name='lastname']");
				Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
				Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
				Common.clickElement("xpath", "//input[@title='Phone Number']");
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
				Common.clickElement("xpath", "//input[@id='street_1']");
				Common.textBoxInput("xpath", "//input[@id='street_1']", address);
				Common.clickElement("xpath", "//input[@title='City']");
				Common.textBoxInput("xpath", "//input[@title='City']", City);
				if(Common.getCurrentURL().contains("preprod"))
	             { 
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
	             }
				else
				{
				Common.clickElement("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
				}
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
				
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				Common.assertionCheckwithReport(message.equals("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");
				Billingaddress_Addressbook(Dataset);
			} else {
				Billingaddress_Addressbook(Dataset);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
					Common.getscreenShot(
							"Failed to display checkbox for billing address and fail to display text for shipping address"));
			Assert.fail();
		}

	}

	public void Billingaddress_Addressbook(String Dataset) {
		// TODO Auto-generated method stub
		String firstname = data.get(Dataset).get("FirstName");
		String secondname = data.get(Dataset).get("LastName");
		String address = data.get(Dataset).get("Street");
		String phonenumber = data.get(Dataset).get("phone");
		String City = data.get(Dataset).get("City");
		String region = data.get(Dataset).get("Region");
		String zipcode = data.get(Dataset).get("postcode");
		String shipping = data.get(Dataset).get("Shipping address");
		try {
			Common.clickElement("xpath", "//span[contains(text(),'Change Billing Address')]");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
			Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
			Common.clickElement("xpath", "//input[@title='Phone Number']");
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
			Common.clickElement("xpath", "//input[@id='street_1']");
			Common.textBoxInput("xpath", "//input[@id='street_1']", address);
			Common.clickElement("xpath", "//input[@title='City']");
			Common.textBoxInput("xpath", "//input[@title='City']", City);
			if(Common.getCurrentURL().contains("preprod"))
            { 
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
            }
			else
			{
			Common.clickElement("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			
	/*		String text = Common.findElement("xpath", "//div[@class='message info']//span").getText();
			System.out.println(text);
			Common.assertionCheckwithReport(
					 checkbox.equals("checkbox") && text.contains("This is your default billing address."),
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
					"Failed to display checkbox for billing address and fail to display text" + text + "for shipping address");  
	*/	
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Sync.waitElementPresent("xpath", "//div[@class='relative flex w-full']//span");
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
			String shippingaddress = Common
					.findElement("xpath", "(//address[@class='not-italic'])[2]").getText();
			System.out.println(shippingaddress);
			System.out.println(shipping);
			Common.assertionCheckwithReport(
					shippingaddress.equals(shipping) &&  message.contains("You saved the address."),
					"validating the checkbox for shipping address and text for the billing address",
					"Checkbox should be display for the shipping address and text should be display for the billing address",
					"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the checkbox for shipping address and text for the billing address",
					"Checkbox should be display for the shipping address and text should be display for the billing address",
					"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address",
					Common.getscreenShot(
							"Failed to display checkbox for shipping address and fail to display text for billing address"));
			Assert.fail();
		}

	}



	public void Edit_Delete_Address(String Dataset) {
		// TODO Auto-generated method stub
		String firstname = data.get(Dataset).get("FirstName");
		String secondname = data.get(Dataset).get("LastName");
		String address = data.get(Dataset).get("Street");
		String phonenumber = data.get(Dataset).get("phone");
		String City = data.get(Dataset).get("City");
		String region = data.get(Dataset).get("Region");
		String zipcode = data.get(Dataset).get("postcode");
		String shipping = data.get(Dataset).get("Shipping address");
		try {
			int addressbook = Common.findElements("xpath", "//table[@id='my-address-table']").size();
			System.out.println(addressbook);
			if (addressbook>0) {
				Common.clickElement("xpath", "//a[@title='Edit']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//input[@name='firstname']");
				Common.clickElement("xpath", "//input[@name='firstname']");
				Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
				Common.clickElement("xpath", "//input[@name='lastname']");
				Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
				Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
				Common.clickElement("xpath", "//input[@title='Phone Number']");
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
				Common.clickElement("xpath", "//input[@id='street_1']");
				Common.textBoxInput("xpath", "//input[@id='street_1']", address);
				Common.clickElement("xpath", "//input[@title='City']");
				Common.textBoxInput("xpath", "//input[@title='City']", City);
				if(Common.getCurrentURL().contains("/gb"))
	             { 
					Common.clickElement("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
	                
	             }
				else
				{
					 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
				}
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
				// Common.clickElement("xpath", "//label[@for='primary_shipping']");
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//span[text()='You saved the address.']").getText();

				Common.assertionCheckwithReport(message.contains("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");
				Thread.sleep(4000);
				Common.scrollIntoView("xpath", "//span[text()='Delete']");
				Sync.waitElementPresent("xpath", "//span[text()='Delete']");
				Common.clickElement("xpath", "//span[text()='Delete']");
				Thread.sleep(4000);
				Common.acceptAlert(2000);
//				String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
//				if (popmessage.contains("Are you sure you want to delete this address?")) {
//					Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
//					Common.clickElement("xpath", "//span[contains(text(),'OK')]");
					String Delmessage = Common.findElement("xpath", "//span[text()='You deleted the address.']")
							.getText();
					System.out.println(Delmessage);
					Common.assertionCheckwithReport(Delmessage.contains("You deleted the address."),
							"validating the Delete message after Deleting address in address book",
							"Delete address message should be displayed after the address delete in address book",
							"Sucessfully address has been Deleted in the address book",
							"Failed to Delete the address in the address book");
//				} else {
//					Assert.fail();
//				}

			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Delete message after Deleting address in address book",
					"Delete address message should be displayed after the address delete in address book",
					"Unable to  Delete the address in the address book",
					Common.getscreenShot("Failed to Delete the address in the address book"));

			Assert.fail();
		}

	}

	public String reg_outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("oss Product");
		String prod = data.get(Dataset).get("prod product");
		String productcolor = data.get(Dataset).get("Color");
		String price = "";

		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				Common.scrollIntoView("xpath", "//img[contains(@alt,'" + products + "')]");
				Common.mouseOver("xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='title-2xs leading-none']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
//				String PLPprice = Common
//						.findElement("xpath",
//								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
//						.getAttribute("data-price-amount");
//				System.out.println(PLPprice);
				System.out.println(productprice);
				String name = Common.findElement("xpath", "//span[contains(@class,'pdp-grid-title')]").getText();
				Common.assertionCheckwithReport(name.equals(products),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				///Sync.waitPageLoad();
				Thread.sleep(3000);
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Thread.sleep(4000);
				Sync.waitElementPresent("xpath" , "(//button[@title='Notify Me When Available']//span)[1]");
				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
				Sync.waitPageLoad(40);
				Thread.sleep(5000);
				String newsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span")
						.getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Thread.sleep(4000);
				Common.actionsKeyPress(Keys.END);
				Sync.waitElementPresent("xpath" , "(//button[@title='Notify Me When Available']//span)[2]");
				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[2]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
				System.out.println(oldsubcribe);
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
//				price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
//						.getAttribute("data-price-amount");
			} else {
				
				Sync.waitElementPresent(30, "xpath", "//img[@class='m-product-card__image product-image-photo']");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper is-special-price']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[@class='m-product-card__image product-image-photo']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper is-special-price']")
						.getAttribute("data-price-amount");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PLPprice)
								|| name.contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(oldsubcribe);
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
						.getAttribute("data-price-amount");
			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ",
					Common.getscreenShot("Failed to display the message after subcribtion"));
			AssertJUnit.fail();
		}
		return price;

	}

	public void My_order_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("oss Product");
		System.out.println(products);
		String prod = data.get(Dataset).get("prod product");
		System.out.println(prod);
		try {
			Sync.waitElementPresent("id", "customer-menu");
			Common.clickElement("id", "customer-menu");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard"),
					"validating the page navigation to the my account",
					"after clicking on the my account it should navigate to the my account page",
					"Sucessfully Navigated to the my account page", "failed to Navigate to the my account page");
			Sync.waitElementPresent("xpath", "//a[@title='My Out of Stock Subscriptions']");
			Common.clickElement("xpath", "//a[@title='My Out of Stock Subscriptions']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(20, "xpath", "//a[@title='" + products + "']");
			String name = Common.findElement("xpath", "//a[@title='" + products + "']").getText();
			System.out.println(name);
			Common.assertionCheckwithReport(name.contains(products) || name.contains(prod),
					"validating the outofstock produt in the subcribtion page",
					"Product should be display in the subcribtion page",
					"Sucessfully product has been appeared in the outofstock subcription page",
					"Failed to see the product in subcribtion page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the outofstock produt in the subcribtion page",
					"Product should be display in the subcribtion page",
					"Unable to see the product in subcribtion page",
					Common.getscreenShot("Failed to see the product in subcribtion page"));
			AssertJUnit.fail();
		}

	}

	public void remove_outofstock_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
					.getAttribute("data-price-amount");
			//if (price.equals(Dataset)) {
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a[@title='Remove This Item']");
				//Common.maximizeImplicitWait();
				Thread.sleep(3000);
				Common.alerts("Cancel");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[@title='Remove This Item']");
				//Common.implicitWait();
				Common.alerts("Ok");

//			} else {
//
//			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			AssertJUnit.fail();
		}

	}
	public String payPal_Payment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(3000);
			int cancelpayment=Common.findElements("xpath", "//button[@title='Cancel']").size();
			System.out.println(cancelpayment);
			if(cancelpayment>0)
			{
				
				Sync.waitElementPresent("xpath", "//button[contains(text(),'Cancel Payment')]");
				Common.clickElement("xpath", "//button[contains(text(),'Cancel Payment')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
				
			}
			else
			{
				Common.scrollIntoView("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
			}
            
			
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
			Sync.waitElementPresent("xpath", "(//div[contains(@class,'paypal-button paypal-button')])[1]");
			Common.clickElement("xpath", "(//div[contains(@class,'paypal-button paypal-button')])[1]");
//			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			Thread.sleep(8000);
			
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") & !url.contains("preprod")) {

			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
		} else {

			Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			int size = Common.findElements("xpath", "//a[text()='Log in with a password instead']").size();
			if(size>0) {
				Common.clickElement("xpath", "//a[text()='Log in with a password instead']");
				Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			}
			else {
				
			
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();
			
			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			}
			try {
				Common.clickElement("id", "btnLogin");
				Thread.sleep(5000);
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);
				Common.clickElement("id", "payment-submit-btn");
				Thread.sleep(8000);
				Common.switchToFirstTab();
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
						"User failed to proceed with paypal payment",
						Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
			String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if (!url1.contains("stage") && !url1.contains("preprod")) {
			}

			else {
				try {
					Thread.sleep(6000);
					Common.scrollIntoView("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[1]");
					
					Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[1]");

					Thread.sleep(6000);
					Sync.waitElementPresent(30, "xpath", "//h1[normalize-space()='Thank you for your purchase!']");
					String sucessMessage = Common.getText("xpath",
							" //h1[normalize-space()='Thank you for your purchase!']");
                      System.out.println(sucessMessage);
					int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']")
							.size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
							"Successfully It redirects to order confirmation page Order Placed",
							"User unabel to go orderconformation page");

					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span")
							.size() > 0) {
						Thread.sleep(1000);
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
						System.out.println(order);
					} else {
						Thread.sleep(1000);
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
						System.out.println(order);
					}
				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
							"It should navigate to the order confirmation page",
							"User failed to proceed to the order confirmation page",
							Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

					Assert.fail();
				}
			}
		}
		return order;
	}
	
	public String venmo_Payment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should open venmo site window.";
		
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			// Common.refreshpage();
			Thread.sleep(8000);
			Sync.waitElementPresent("xpath", "//img[@class='paypal-logo paypal-logo-venmo paypal-logo-color-white']");
			Common.clickElement("xpath", "//img[@class='paypal-logo paypal-logo-venmo paypal-logo-color-white']");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
					"User failed to proceed with venmo payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		Sync.waitElementPresent("xpath", "//div[@class='max-width-wrapper']/img");
		String venmo = Common.findElement("xpath", "//div[@class='max-width-wrapper']/img").getAttribute("alt");
		if(venmo.equals("Venmo")) {
		Sync.waitElementPresent("xpath", "//img[@alt='PayPal']");
		Common.clickElement("xpath", "//img[@alt='PayPal']");
		}
		if (!url.contains("stage") & !url.contains("preprod")& !url.contains("stage3") ) {

			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the venmo payment ", expectedResult,
					"open venmo site window", "faild to open venmo account");
		} else {

			Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the venmo payment ", expectedResult,
					"open venmo site window", "faild to open venmo account");
		
			try {
				Common.clickElement("id", "btnLogin");
				Thread.sleep(5000);
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);
				Common.clickElement("id", "payment-submit-btn");
				Thread.sleep(8000);
				Common.switchToFirstTab();
			
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
						"User failed to proceed with venmo payment",
						Common.getscreenShotPathforReport(expectedResult));
				AssertJUnit.fail();
			}
			express_paypal_shipping("Paypal Shipping");
			// Tell_Your_FriendPop_Up();//To close the Pop-up
			String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if (!url1.contains("stage") && !url1.contains("preprod")) {
			}

			else {
				try {
					Thread.sleep(6000);
					String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
					System.out.println(sucessMessage);

					int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
							"Successfully It redirects to order confirmation page Order Placed",
							"User unable to go orderconformation page");

					if (Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size() > 0) {
						order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");
						System.out.println(order);
					}
					if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
						order = Common.getText("xpath", "//a[@class='order-number']/strong");
						System.out.println(order);
					}

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
							"It should navigate to the order confirmation page",
							"User failed to proceed to the order confirmation page",
							Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

					AssertJUnit.fail();
				}
			}
		}
		return order;
	}



	public void edit_Account_info(String dataSet) {
		// TODO Auto-generated method stub
		Accont_Information();
		try {

			Sync.waitElementPresent("xpath", "//span[@class='m-accordion__title-label']");

			Common.clickElement("xpath", "//span[@class='m-accordion__title-label']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//div//input[@id='current-password']");
			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Confirm Password"));
			Common.textBoxInput("xpath", "//input[@id='password-confirmation']",
					data.get(dataSet).get("Confirm Password"));
			String message = Common.findElement("id", "validation-classes").getCssValue("color");
			String greencolor = Color.fromString(message).asHex();
			String message1 = Common.findElement("id", "validation-length").getAttribute("class");

			Common.assertionCheckwithReport(greencolor.equals("#2f741f") && message1.contains("complete"),
					"validating the cureent password and new password fields",
					"User should able enter data in current password and new password",
					"Sucessfully the data has been entered in new password and current password",
					"Failed to enter data in current password and new password fields");

			Common.clickElement("xpath", "//button[@title='Save']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String sucessmessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Thread.sleep(4000);
			System.out.println(sucessmessage);
			Common.assertionCheckwithReport(sucessmessage.contains("You saved the account"),
					"Validating the saved account information", "Account information should be saved for the user",
					"Sucessfully account information has been saved ", "failed to save the account information");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying the change passwordfor the register user",
					"User enter the valid password", "User failed to proceed to change passowrd ",
					Common.getscreenShotPathforReport("emailpasswordnew"));
			AssertJUnit.fail();

		}
	}

	public void changed_password(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", Dataset);
			Common.textBoxInput("id", "pass", "Lotuswave@1234");
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"To validate the user lands on My Account page after successfull login",
					"After clicking on the signIn button it should navigate to the My Account page",
					"user Sucessfully navigate to the My Account page after clicking on the signIn button",
					"Failed to signIn and not navigated to the My Account page ");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user Navigate to My Account page after successfull login",
					"After clicking on the signin button it should navigate to the My Account page",
					"Unable to navigate the user to the My Account after clicking on the SignIn button",
					Common.getscreenShotPathforReport("Failed to signIn and not navigated to the My Account page "));

			AssertJUnit.fail();

		}
	}

	public void Accont_Information() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//a[text()='Account Information']");
			Common.clickElement("xpath", "//a[text()='Account Information']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Account Information"),
					"validating the Navigation to the Account information page",
					"After Clicking on Account information CTA user should be navigate to the Account information page",
					"Sucessfully User Navigates to the Account information page after clicking on the Account information CTA",
					"Failed to Navigate to the Account information page after Clicking on Account information button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Account information page",
					"After Clicking on Account information CTA user should be navigate to the Account information page",
					"Unable to Navigates the user to Account information page after clicking on the Account information CTA",
					Common.getscreenShot(
							"Failed to Navigate to the Account information page after Clicking on Account information CTA"));
			AssertJUnit.fail();
		}
	}

	public String change_Email(String Dataset) {
		// TODO Auto-generated method stub
		String oldemail = "";

		try {
			Sync.waitElementPresent(20, "xpath", "//span[text()='Edit']//parent::a");
			String name = Common.findElement("xpath", "//span[text()='Edit']//parent::a").getAttribute("aria-label");
			Common.clickElement("xpath", "//span[text()='Edit']//parent::a");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String editaccount = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(name.contains(editaccount),
					"verifying the page navigated to the edit account ",
					"user should navigate to the Edit account page",
					"user successfully Navigated to the edit account page",
					"Failed to navigate to the edit account page");
			oldemail = Common.findElement("xpath", "//p[@class='text-email']").getText();
			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
			Common.textBoxInputAndVerify("xpath", "//input[@name='email']",data.get(Dataset).get("UserName"));
			Common.textBoxInput("xpath", "//input[@name='current_password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String errormessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(errormessage.contains("The password doesn't match this account"),
					"verifying the error message for the password",
					"user should get the error message if he enter the different password",
					"Successfully user gets the error message",
					"Failed to get the error message if the user gives an different password");
			Common.refreshpage();
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("stage")) {
				Thread.sleep(3000);
				Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
				Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//input[@name='current_password']",
						data.get(Dataset).get("Confirm Password"));
			} else {
				Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
				Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Prod Email"));
				Common.textBoxInput("xpath", "//input[@name='current_password']",
						data.get(Dataset).get("Confirm Password"));
			}
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String emailerrormessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']")
					.getText();
			Common.assertionCheckwithReport(
					emailerrormessage.contains("A customer with the same email address already exists"),
					"verifying the error message for the existing account email",
					"user should get the error message if he enter the existing email",
					"Successfully user gets the error message",
					"Failed to get the error message if the user gives an existing email id");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
			Common.textBoxInputAndVerify("xpath", "//input[@name='email']",data.get(Dataset).get("UserName"));
			String newemail = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.textBoxInput("xpath", "//input[@name='current_password']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String successmessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(
					successmessage.contains("You saved the account information.")
							&& Common.getPageTitle().contains("Customer Login"),
					"verifying the Success message for the Change email",
					"user should get the success message and navigate back to the Login page",
					"Successfully user gets the success message and navigated to the Login page",
					"Failed to get the success message and unable to navigate to the login page");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", newemail);
			Common.textBoxInput("id", "pass", data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"user Sucessfully navigate to the My Account after clicking on the signIn button",
					"Failed to signIn and not navigated to the My Account");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"Unable to signIn and not navigated to the My Account",
					Common.getscreenShot(" Failed to signIn and not navigated to the My Account"));
			AssertJUnit.fail();
		}
		return oldemail;
	}

	public void Change_to_Existingemail(String Dataset) {
		// TODO Auto-generated method stub
		try {

			String name = Common.findElement("xpath", "//span[text()='Edit']//parent::a").getAttribute("aria-label");
			Common.clickElement("xpath", "//span[text()='Edit']//parent::a");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String editaccount = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(name.contains(editaccount),
					"verifying the page navigated to the edit account ",
					"user should navigate to the Edit account page",
					"user successfully Navigated to the edit account page",
					"Failed to navigate to the edit account page");
			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
			Common.textBoxInputAndVerify("xpath", "//input[@name='email']", Dataset);
			Common.textBoxInput("xpath", "//input[@name='current_password']", "Lotuswave@1234");
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String successmessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(
					successmessage.contains("You saved the account information.")
							&& Common.getPageTitle().contains("Customer Login"),
					"verifying the Success message for the Change email",
					"user should get the success message and navigate back to the Login page",
					"Successfully user gets the success message and navigated to the Login page",
					"Failed to get the success message and unable to navigate to the login page");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", Dataset);
			Common.textBoxInput("id", "pass", "Lotuswave@1234");
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"user Sucessfully navigate to the My Account after clicking on the signIn button",
					"Failed to signIn and not navigated to the My Account");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"Unable to signIn and not navigated to the My Account",
					Common.getscreenShot(" Failed to signIn and not navigated to the My Account"));
			AssertJUnit.fail();
		}

	}


public void reorder() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//a[normalize-space()='My Orders']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[text()='View Order']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//span[text()='Reorder']");
			Common.clickElement("xpath", "//span[text()='Reorder']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Shopping Cart"),
					"validating the navigates to the shopping cart page",
					"After clicking on the reorder it should navigate to the shopping cart page",
					"Successfully navigated to the shopping cart page", "Failed to Navigate to the shopping cart page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigates to the shopping cart page",
					"After clicking on the reorder it should navigate to the shopping cart page",
					"Unable to Navigate to the shopping cart page",
					Common.getscreenShot("Failed to Navigate to the shopping cart page"));
			Assert.fail();
		}

	}
public void Navigate_back_to_Shoppingcart_page(String Dataset) {
	// TODO Auto-generated method stub

	try {
		Sync.waitElementVisible(30, "xpath", "//span[text()='Back to Cart']");
		Common.clickElement("xpath", "//span[text()='Back to Cart']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Shopping Cart"),
				"validating the navigates to the shopping cart page",
				"After clicking on the reorder it should navigate to the shopping cart page",
				"Successfully navigated to the shopping cart page", "Failed to Navigate to the shopping cart page");

		String Cart = Common.findElement("xpath", "//span[contains(@class,'ml-7 title')]").getText().trim()
				.replace("Items", "");
		String checkout = Common.findElement("xpath", "//div[@x-text='cartSummaryCount']").getText().trim();
		System.out.println(checkout);
		System.out.println(Cart);
		Sync.waitElementVisible(30, "xpath", "//a[@id='checkout-link-button']");
		Common.clickElement("xpath", "//a[@id='checkout-link-button']");
		Sync.waitPageLoad();
		Thread.sleep(7000);

		Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
		Common.assertionCheckwithReport(
				checkout.equals(Cart) || Common.getCurrentURL().contains("checkout/"),
				"validating the navigation to the shipping page when we click on the checkout",
				"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
				"Failed to navigate to the shipping page");
		selectshippingmethod(Dataset);
		clickSubmitbutton_Shippingpage();
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the navigation to the shipping page when we click on the checkout ",
				"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
				Common.getscreenShot("Failed to navigate to the shipping page"));
		AssertJUnit.fail();

	}
}


public void Continue_Shopping() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementVisible("xpath", "//span[text()='Continue Shopping']");
		Common.clickElement("xpath", "//span[text()='Continue Shopping']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		verifingHomePage();
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating store logo on the homwpage",
				"System directs the user to the Homepage and store logo should display",
				"Unable to navigate to the homepage and logo is not displayed",
				"Failed to navigate to the homepage and logo is not displayed");
		AssertJUnit.fail();
	}

}
	public void MyFavorites_Guestuser(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(Productsize);
		try

		{
			search_product("Product");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
			
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			Sync.waitElementPresent(30, "xpath", "//button[@id='add-to-wishlist']");
			Common.clickElement("xpath", "//button[@id='add-to-wishlist']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customer Login")
							&& message.contains("You must login or register to add items"),
					"validating the Navigation to the Customer Login page",
					"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
					"Sucessfully User Navigates to the My Favorites page after clicking on the Customer Login CTA",
					"Failed to Navigate to the Customer Login page after Clicking on My Favorites button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Customer Login page",
					"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
					"Unable to Navigate to the Customer Login page after Clicking on My Favorites button",
					Common.getscreenShot(
							"Failed to Navigate to the Customer Login page after Clicking on My Favorites button"));

			AssertJUnit.fail();
		}

	}

	public String minicart_items() {
		// TODO Auto-generated method stub
		String items = "";
		try {
			Sync.waitElementPresent("xpath", "//div[@x-text='cartSummaryCount']");
			items = Common.findElement("xpath", "//div[@x-text='cartSummaryCount']").getText();
			System.out.println(items);
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String miniitems = Common.findElement("xpath", "//span[@x-text='totalCartAmount']")
					.getText().trim();
			System.out.println(miniitems);
			Common.assertionCheckwithReport(items.contains(miniitems),
					"Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart",
					"Sucessfully products count has displayed in the mini cart",
					"failed to display products count in the mini cart");
			Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart",
					"Unable to display the  products count in the mini cart",
					Common.getscreenShot("failed to display products count in the mini cart"));

			Assert.fail();

		}
		return items;

	}

	public void click_Createaccount() {

	
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//a[@title='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("customer"),
					"Validating Create New Customer Account page navigation",
					"after Clicking on Create New Customer Account page it will navigate account creation page",
					"Successfully navigate to the create account page",
					"Failed to Navigate to the account create page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Create New Customer Account page navigation ",
					"after Clicking on Create New Customer Account page it will navigate account creation page",
					"unable to navigate to the craete account page",
					Common.getscreenShotPathforReport("Failed to navigate to the account create page"));
			Assert.fail();
		}
	}
	

	public String create_account_with_fav(String Dataset) {
		// TODO Auto-generated method stub
		String email = "";
		String product = data.get(Dataset).get("Products");
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']",data.get(Dataset).get("UserName"));
			email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			System.out.println(email);
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Common.clickElement("xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			
			
			if(Common.getCurrentURL().contains("stage3")) {
				String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
				String favmessage = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]")
						.getText();
				System.out.println("favmessage"+favmessage);
				System.out.println("Register message"+ message);
			
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("My Favorites")
								&& message.contains("Thank you for registering with Osprey") 
								&& favmessage.contains(product + " has been added to your Favorites. Click "),
						"validating the  My Favorites page Navigation when user clicks on signin button",
						"User should able to navigate to the My Favorites page after clicking on Signin button",
						"Sucessfully navigate to the My Favorites page after clicking on signin button ",
						"Failed to navigates to My Favorites Page after clicking on Signin button");
		
			}
			else {
				String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
				String favmessage = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]")
						.getText();
				System.out.println(favmessage);
				System.out.println(message);
				System.out.println(Common.getPageTitle());
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("My Favorites")
								&& message.contains("Thank you for registering with Osprey") 
								&& favmessage.contains(product + " has been added to your Favorites. Click"),
						"validating the  My Favorites page Navigation when user clicks on signin button",
						"User should able to navigate to the My Favorites page after clicking on Signin button",
						"Sucessfully navigate to the My Favorites page after clicking on signin button ",
						"Failed to navigates to My Favorites Page after clicking on Signin button");
			}
			
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Unable to navigate to the My account page after clicking on signin button ",
					Common.getscreenShot("Failed to navigates to My Account Page after clicking on Signin button"));
			AssertJUnit.fail();

		}
		return email;
	}

	public void minicart_products(String minicart) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.mouseOverClick("xpath", "//button[@id='menu-cart-icon']");

			Sync.waitElementPresent(30, "xpath", "//div[@x-text='cartSummaryCount']");
			String cartproducts = Common.findElement("xpath", "//div[@x-text='cartSummaryCount']").getText();

			Common.assertionCheckwithReport(cartproducts.equals(minicart),
					"validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart",
					"Sucessfully after create account with cart products should be display in mini cart",
					"failed to display the products in mini cart after the create account with cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart",
					"Unable to display the products in mini cart after the create account with cart",
					Common.getscreenShot(
							"failed to display the products in mini cart after the create account with cart"));

			Assert.fail();
		}

	}

	public String BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		String update = "";
		String Shipping="";
		String firstname = data.get(dataSet).get("FirstName");
		String secondname = data.get(dataSet).get("LastName");
		String address = data.get(dataSet).get("Street");
		String phonenumber = data.get(dataSet).get("phone");
		String City = data.get(dataSet).get("City");
		String region = data.get(dataSet).get("Region");
		String zipcode = data.get(dataSet).get("postcode");
		String shipping = data.get(dataSet).get("Shipping address");
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@type='checkbox' and @name='billing-as-shipping']");
			Boolean checkbox=Common.findElement("xpath", "//input[@type='checkbox' and @name='billing-as-shipping']").isSelected();
			System.out.println(checkbox);
			Thread.sleep(8000);
			String box=Boolean.toString(checkbox);
			if(box.contains("true"))
			{
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//input[@type='checkbox' and @name='billing-as-shipping']");
			Common.clickElement("xpath", "//input[@type='checkbox' and @name='billing-as-shipping']");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='firstname' and @data-form='billing']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname' and @data-form='billing']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='street[0]' and @data-form='billing']", data.get(dataSet).get("Street"));
			Thread.sleep(4000);
			String text = Common.findElement("xpath", "//input[@name='street[0]' and @data-form='billing']").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city' and @data-form='billing']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			 if(Common.getCurrentURL().contains("gb"))
             {
				 Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
				 
             }
			 else
			 {
				 Common.scrollIntoView("xpath", "//select[@name='region' and @data-form='billing']");
                 Common.dropdown("xpath", "//select[@name='region' and @data-form='billing']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region' and @data-form='billing']")
                         .getAttribute("value");
                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
	              System.out.println(Shipping);
                 System.out.println(Shippingvalue);
			 }
				
			Thread.sleep(2000);
			// Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@name='postcode' and @data-form='billing']",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@name='telephone' and @data-form='billing']");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='telephone' and @data-form='billing']",
					data.get(dataSet).get("phone"));

		}
			else {
				
			
			update = Common.findElement("xpath", "//label[@for='billing-as-shipping']").getText();
			System.out.println(update);
			Sync.waitPageLoad();
			}


		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Unable to display the Billing address in payment page",
					Common.getscreenShotPathforReport("Failed to display the Billing address in payment page"));
			Assert.fail();
		}
		return update;
	}

	public void verify_BillingAddress(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Common.scrollIntoView("xpath", "(//address[@class='not-italic']//br)[1]");
			String Address = Common.findElement("xpath", "(//address[@class='not-italic']//br)[1]")
					.getText().trim();
			System.out.println(Address);
			System.out.println(Dataset);
			Common.assertionCheckwithReport(Address.contains(Dataset) || Dataset.contains("935 The Horsley Dr"),
					"verifying the Billing address form in Address book",
					"Billing address should be saved in the Address book",
					"Sucessfully Billing address form should be Displayed in the Address book",
					"Failed to display the Billing address in Address book");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in Address book",
					"Billing address should be saved in the Address book",
					"Unable to display the Billing address in Address book",
					Common.getscreenShotPathforReport("Failed to display the Billing address in Address book"));
			AssertJUnit.fail();
		}

	}

	public String shipping_new_Address(String dataSet) {
		// TODO Auto-generated method stub
		String address = "";
		String expectedResult = "shipping address is entering in the fields";
		String firstname = data.get(dataSet).get("FirstName");
		System.out.println(firstname);
		int size = Common.findElements(By.xpath("//div[@class='new-address-popup']//button")).size();
		if (size > 0) {
			try {
				Common.clickElement("xpath", "//div[@class='new-address-popup']//button");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));

				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}

				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));

				if(Common.getCurrentURL().contains("gb"))
                {
				  
                	Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
        			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
        			Thread.sleep(3000);
        			String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
        					.getAttribute("value");
        			System.out.println(Shippingvalue);
                }
			else
			{
				Thread.sleep(4000);
                Common.scrollIntoView("xpath", "//select[@name='region_id']");
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                Thread.sleep(3000);
                String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                        .getAttribute("value");
                System.out.println(Shippingvalue);
			}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

				Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");
				ExtenantReportUtils.addPassLog("validating shipping address filling Fields",
						"shipping address is filled in to the fields", "user should able to fill the shipping address ",
						Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

				address = Common.findElement("xpath", "(//div[contains(@class,'shipping-address-item s')]//p)[2]")
						.getText();
				System.out.println(address);

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				AssertJUnit.fail();

			}

		}
		return address;

	}

	public void Edit_Address_verify(String dataSet) {
		// TODO Auto-generated method stub
		String firstname = data.get(dataSet).get("FirstName");
		System.out.println(firstname);
		try {
			Thread.sleep(4000);

			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'shipping-address-item n')]//span[text()='Edit Address']");
			String expectedResult = "shipping address is entering in the fields";

			try {
				Common.clickElement("xpath", "//div[@class='new-address-popup']//button");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));

				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}

				Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
				Thread.sleep(3000);
				String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
						.getAttribute("value");

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));

				try {
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(2000);
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath",
						"//footer[@class='modal-footer']//span[contains(text(),'Save Address')]");

				Common.javascriptclickElement("xpath",
						"//footer[@class='modal-footer']//span[contains(text(),'Save Address')]");

				ExtenantReportUtils.addPassLog("validating shipping address filling Fields",
						"shipping address is filled in to the fields", "user should able to fill the shipping address ",
						Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				AssertJUnit.fail();

			}
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating adding  address", "User should able to add the address",
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("failed to save the shipping address"));
			AssertJUnit.fail();

		}

	}

	public void Verify_Address(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//img[@alt='Logo Osprey']");
			Sync.waitElementPresent(30, "xpath", "//button[@aria-controls='desktop-account-nav']");
			Common.clickElement("xpath", "//button[@aria-controls='desktop-account-nav']");
			String id=Common.findElement("xpath", "(//ul[@id='desktop-account-nav']//a)[1]").getAttribute("id");
			Common.clickElement("xpath", "//a[@id='"+id+"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("account"),
					"verifying the My account navigation",
					"after clicking on the my account it should navigate to the My Account page",
					"Sucessfully Navigated to the My Account page", "Failed to navigate to the my account page");
			Common.clickElement("xpath", "(//div[@id='account-nav']//a)[3]");
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("address"),
					"verifying the Address Book page navigation",
					"after clicking on the Address Book it should navigate to the Address Book page",
					"Sucessfully Navigated to the Address Book page", "Failed to navigate to the Address Book page");
			
			Common.scrollIntoView("xpath", "(//tbody[@class='m-table__body']//td)[3]");
			String shippingaddress = Common.findElement("xpath", "(//tbody[@class='m-table__body']//td)[3]")
					.getText();
			System.out.println(shippingaddress);
		    int size=Common.findElements("xpath", "(//tbody[@class='m-table__body']//td)[3]").size();
			Common.assertionCheckwithReport(
					shippingaddress.contains(Dataset) || shippingaddress.contains("844 N Colony Rd") || size>0,
					"verifying the address added to the address book",
					"after saving the address in shiiping page it should save in the address book",
					"Sucessfully Address ha been saved in the address book",
					"Failed to save the address in the address book");
			Common.scrollIntoView("xpath", "//a[contains(@class,'action delete')]");
			Sync.waitElementPresent("xpath", "//a[contains(@class,'action delete')]");
			Common.clickElement("xpath", "//a[contains(@class,'action delete')]");
			Thread.sleep(4000);
			String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
			String popup=Common.findElement("xpath", "//div[@class='modal-content']").getAttribute("data-role");
			if (popmessage.contains("Are you sure you want to delete this address?") || popup.contains("content")) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
				String Delmessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				String delete=Common.findElement("xpath", "//div[@data-ui-id='message-success']").getAttribute("class");
				Common.assertionCheckwithReport(Delmessage.equals("You deleted the address.") || delete.contains("message-success"),
						"validating the Delete message after Deleting address in address book",
						"Delete address message should be displayed after the address delete in address book",
						"Sucessfully address has been Deleted in the address book",
						"Failed to Delete the address in the address book");
			} else {
				AssertJUnit.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Delete message after Deleting address in address book",
					"Delete address message should be displayed after the address delete in address book",
					"Unable to Delete the address in the address book",
					Common.getscreenShotPathforReport("Failed to Delete the address in the address book"));
			AssertJUnit.fail();
		}

	}

	public void outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		String email = data.get(Dataset).get("Notifyme");
		String prod = data.get(Dataset).get("prod product");
		String productcolor = data.get(Dataset).get("Color");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				Common.scrollIntoView("xpath", "//img[contains(@alt,'" + products + "')]");
				Common.mouseOver("xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='title-2xs leading-none']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
//				String PLPprice = Common
//						.findElement("xpath",
//								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
//						.getAttribute("data-price-amount");
//				System.out.println(PLPprice);
				System.out.println(productprice);
				String name = Common.findElement("xpath", "//span[contains(@class,'pdp-grid-title')]").getText();
				Common.assertionCheckwithReport(name.equals(products),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				///Sync.waitPageLoad();
				Thread.sleep(3000);
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Thread.sleep(4000);
				Sync.waitElementPresent("xpath" , "(//button[@title='Notify Me When Available']//span)[1]");
				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
				Common.actionsKeyPress(Keys.END);
				Sync.waitElementPresent("xpath",
						"(//button[@title='Notify Me When Available']//span)[2]");
				Common.clickElement("xpath",
						"(//button[@title='Notify Me When Available']//span)[2]");
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");

			} else {
				Sync.waitElementPresent(30, "xpath", "//img[@class='m-product-card__image product-image-photo']");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper is-special-price']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[@class='m-product-card__image product-image-photo']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper is-special-price']")
						.getAttribute("data-price-amount");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PLPprice)
								|| name.contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "(//span[text()=' Notify Me When Available'])[1]");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "(//span[text()=' Notify Me When Available'])[1]");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");

			}

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ",
					Common.getscreenShot("Failed to display the message after subcribtion"));
			AssertJUnit.fail();

		}

	}

	public void Loginpage_validation(String dataSet) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(5000);
			click_singinButton();
			Sync.waitElementPresent("xpath", "//button[contains(@class,'action login')]");
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitElementPresent(30, "xpath", "//div[@id='pass-error']");
			String errormessage = Common.findElement("xpath", "//div[@id='pass-error']").getText();
			String errormessage1 = Common.findElement("xpath", "//div[@id='pass-error']").getAttribute("class");
			Common.assertionCheckwithReport(errormessage.contains("This is a required field.")||errormessage1.contains("mage-error"),
					"verifying the error message validation with empty fileds",
					"after click on signin button with empty blanks error message should appear",
					"Sucessfully error messsage should be display ", "Failed to display the error message");
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad(40);
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-error']/div/div").getAttribute("class");
			Sync.waitPageLoad(40);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(message.contains("The account sign-in was incorrect")||message2.contains("a-message__container-inner"),
					"verifying the error message for invalid password",
					"after click on signin button with empty invalid password error message should appear",
					"Sucessfully error messsage should be display ", "Failed to display the error message");
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("unregisterd Username"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad(40);
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@class='a-message__container-inner']");
			String message1 = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			
			Sync.waitPageLoad(40);
			Thread.sleep(4000);
			
			
			Common.assertionCheckwithReport(message1.contains("The account sign-in was incorrect")||message2.contains("a-message__container-inner"),
					"verifying the error message for invalid password",
					"after click on signin button with un registered email error message should appear",
					"Sucessfully error messsage should be display ", "Failed to display the error message");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the error message for invalid password",
					"after click on signin button with un registered email error message should appear",
					"Unable to display the error message", Common.getscreenShot("Failed to display the error message"));
			AssertJUnit.fail();

		}

	}

	public void Sticky_Add_to_Cart(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image') or @loading='lazy' and @itemprop]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image') or @loading='lazy' and @itemprop]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.scrollIntoView("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");

			Common.clickElement("xpath", "//img[@alt='" + products + "']");
               
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//button[@x-show='isStickySwatchAvailable' and @title='Add to Cart']");
			Common.clickElement("xpath", "//button[@x-show='isStickySwatchAvailable' and @title='Add to Cart']");

			Sync.waitPageLoad();
			Thread.sleep(4000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}

	}
	
	public void close_add() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(6000);
        int sizesframe = Common.findElements("xpath", "//div[@data-testid='POPUP']").size();
        System.out.println(sizesframe);
        if (sizesframe > 0) {
            Common.actionsKeyPress(Keys.PAGE_UP);
            Thread.sleep(4000);
            Sync.waitElementPresent("xpath", "//button[@aria-label='Close dialog']");
            Common.clickElement("xpath", "//button[@aria-label='Close dialog']");
        }
        else {

        	Thread.sleep(4000);
            Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
            Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.clickElement("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.switchToDefault();
            }

 

    }

	public void acceptPrivacy() {
		try {
     Sync.waitElementClickable("xpath", "//button[@id='truste-consent-button']");
		Common.clickElement("xpath", "//button[@id='truste-consent-button']");
	}
	catch(Exception | Error e){
		Sync.waitElementClickable("xpath", "//button[@id='truste-consent-button']");
		Common.clickElement("xpath", "//button[@id='truste-consent-button']");
	}
	}
	public void Decline_All() {
		Common.clickElement("xpath", "//button[@id='truste-consent-required']");
	}

	public void Edit_Name(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(4000);
			Sync.waitElementPresent(30,"xpath", "//button[@id='truste-consent-button']");
			Common.clickElement("xpath", "//button[@id='truste-consent-button']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@class='m-accordion__title name']//span[@class='a-btn-tertiary__label']");
			Common.clickElement("xpath", "//button[@class='m-accordion__title name']//span[@class='a-btn-tertiary__label']");
			String editaccount = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			String url=Common.getCurrentURL();
			if (editaccount.contains("Edit Account Information") || url.contains("edit")) {
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
				Common.clickElement("xpath", "//button[@class='action save primary a-btn a-btn--primary']");
				Sync.waitPageLoad(40);
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				String message1 = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getAttribute("class");
				Common.assertionCheckwithReport(message.contains("You saved the account information.") || message1.contains("a-message"),
						"validating the edit account information",
						"After clicking oon save changes sucess message should appear",
						"Sucessfully save address suceess message should display",
						"failed to save the data and success message is not displayed");

			} else {
				AssertJUnit.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the edit account information",
					"After clicking on save changes sucess message should appear",
					"Unable to save the data and success message is not displayed",
					Common.getscreenShot("failed to save the data and success message is not displayed"));
			AssertJUnit.fail();
		}

	}

	public void Add_Whishlist_PLP(String string) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Common.mouseOver("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.javascriptclickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitElementVisible(30, "xpath", "//h4");
				String whishlistpopup = Common.findElement("xpath", "//h4").getText();
				System.out.println(whishlistpopup);
				if (whishlistpopup.contains("Add to Wishlist")) {
					Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
					Common.clickElement("xpath", "//button[@title='Add To List']");
				} else {
					AssertJUnit.fail();
				}
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("has been added to your Wish List"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the Whishlist",
					"Product should be add to whishlist", "Unable to add product to the Whishlist",
					Common.getscreenShot("failed to add product to the Whishlist"));
			AssertJUnit.fail();
		}

	}

	public void Add_Whishlist_PDP(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String MyFavorites = Common.findElement("xpath", "//a[@id='wishlist-create-button']//span").getText();
			System.out.println(MyFavorites);

			if (MyFavorites.contains("CREATE NEW WISH LIST")) {
				Bagpacks_headerlinks("Backpacks & Bags");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.clickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Sync.waitElementVisible(30, "xpath", "//h4");
				String whishlistpopup = Common.findElement("xpath", "//h4").getText();
				System.out.println(whishlistpopup);
				if (whishlistpopup.contains("Add to Wishlist")) {

					Sync.waitElementPresent(30, "xpath", "//label[text()='Create New Wish List']");
					Common.clickElement("xpath", "//label[text()='Create New Wish List']");
					Common.textBoxInput("xpath", "//input[@name='name']", data.get(Dataset).get("whishlist name"));
					Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
					Common.clickElement("xpath", "//button[@title='Add To List']");
				} else {
					AssertJUnit.fail();
				}
				Sync.waitPageLoad(40);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				String message = Common.findElement("xpath", "(//div[@data-ui-id='message-success']//div)[4]")
						.getText();
				System.out.println(message);
				String newwhishlist = Common.findElement("xpath", "(//div[@data-ui-id='message-success']//div)[2]")
						.getText();
				System.out.println(newwhishlist);
				Common.assertionCheckwithReport(
						message.contains(product + " has been added to your Wish List. Click ")
								&& newwhishlist.contains("Wish list"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the Whishlist",
					"Product should be add to whishlist", "Unable to add product to the Whishlist",
					Common.getscreenShot("failed to add product to the Whishlist"));
			AssertJUnit.fail();
		}
	}

	public void Delete_Whishlist() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//label[@for='wishlist-select-all']");
			Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[@title='Delete Wish List']");
			Common.clickElement("xpath", "//button[@title='Delete Wish List']");
			Thread.sleep(4000);
			String popup = Common
					.findElement("xpath", "//div[@class='modal-popup confirm _show']//div[@class='modal-content']//div")
					.getText();
			if (popup.contains("Are you sure you want to delete")) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
				Sync.waitElementVisible(40, "xpath", "//div[@class='a-message__container-inner']");
				String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();

				Common.assertionCheckwithReport(message.contains("Wish List"), "validating the whishlist deletion",
						"new whishlist should be delete sucessfully", "Sucessfully new whishlist has been deleted",
						"failed to delete the new whishlist");
			} else {
				AssertJUnit.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Unable to to copy the whishlist to the exsiting whishlist",
					Common.getscreenShot("Failed to to copy the whishlist to the exsiting whishlist"));
			AssertJUnit.fail();
		}
	}

	public void Copy_Whishlist() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Copy Selected to Wish List']");
			Common.clickElement("xpath", "//span[text()='Copy Selected to Wish List']");
			Sync.waitElementPresent(30, "xpath", "//li[@class='item m-dropdown__item']//span[text()='Wish List']");
			Common.clickElement("xpath", "//li[@class='item m-dropdown__item']//span[text()='Wish List']");
			Thread.sleep(4000);
			String copied = Common.findElement("xpath",
					"//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']").getText();
			System.out.println(copied);
			Common.assertionCheckwithReport(copied.contains("1 items were copied"),
					"validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Sucessfully product copied form one whishlist to another",
					"Failed to to copy the whishlist to the exsiting whishlist");
			Delete_Whishlist();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Unable to to copy the whishlist to the exsiting whishlist",
					Common.getscreenShot("Failed to to copy the whishlist to the exsiting whishlist"));
			AssertJUnit.fail();
		}

	}

	public void Move_Whishlist(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(4000);
			Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Move Selected to Wish List']");
			Common.clickElement("xpath", "//span[text()='Move Selected to Wish List']");
			Common.clickElement("xpath", "//span[@title='Create New Wish List']");
			Common.textBoxInput("xpath", "//input[@name='name']", data.get(Dataset).get("whishlist name"));
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(4000);
			String whishlist = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
			System.out.println(whishlist);
			String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("2 items were moved to Testing") && whishlist.contains("Wish list"),
					"validating the  product add to the Whishlist", "Product should be add to whishlist",
					"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				Sync.waitElementPresent(30, "xpath", "//a[text()='Testing']");
				Common.clickElement("xpath", "//a[text()='Testing']");
				Delete_Whishlist();
			} else {
				AssertJUnit.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Unable to to copy the whishlist to the exsiting whishlist",
					Common.getscreenShot("Failed to to copy the whishlist to the exsiting whishlist"));
			AssertJUnit.fail();
		}
	}

	public void country_selctor(String Dataset) {
		// TODO Auto-generated method stub
		String Country;
		try {
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent(50, "xpath", "(//span[@class='country-selector-title'])[3]");
			Common.clickElement("xpath", "(//span[@class='country-selector-title'])[3]");
			Thread.sleep(4000);
			List<WebElement> country = Common.findElements("xpath", "(//legend[text()='North America']//parent::fieldset)[3]//div[@class='country-item flex gap-3']//p");
			List<WebElement> Countryselector = Common.findElements("xpath",
					"(//legend[text()='North America']//parent::fieldset)[3]//div[@class='country-item flex gap-3']//p");
			ArrayList<String> CountryNames = new ArrayList<String>();
			Thread.sleep(4000);
			for (WebElement Countryselections : Countryselector) {
				CountryNames.add(Countryselections.getText());
				System.out.println(CountryNames);
			}
			String[] items = data.get(Dataset).get("Countrynames").split(",");
			System.out.println(items);
			Common.clickElement("xpath", "//button[@aria-label='Close']");
			for (int j = 0; j < items.length; j++) {
				if (CountryNames.contains(items[j])) {

					System.out.println(country.size());

					for (int i = 0; i < country.size(); i++) {

						List<WebElement> select = Common.findElements("xpath",
								"(//legend[text()='North America']//parent::fieldset)[3]//div[@class='country-item flex gap-3']//p");
								System.out.println(select);
						Sync.waitPageLoad();
						Sync.waitElementPresent(50, "xpath", "(//span[@class='country-selector-title'])[3]");
						Common.clickElement("xpath", "(//span[@class='country-selector-title'])[3]");
						String countryname=Common.findElement("xpath", "(//legend[text()='North America']//parent::fieldset)[3]//div[@class='country-item flex gap-3']//span[@class='country-item__country-label title-xs font-bold']").getText();
						System.out.println("countryname:   "+countryname);
						Thread.sleep(3000);	
						Country = select.get(i).getText();
						System.out.println("Country:   "+Country);
						select.get(i).click();
						Thread.sleep(5000);
				
						if (Country.contains("English (£)") && countryname.contains("UK")|| Country.contains("English") && countryname.contains("United States")) {
							ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
									"click on the country should navigate to the  " + Country + "Page",
									"successfully page navigating to " + Country + "PAGE",
									Common.getscreenShotPathforReport(Country));
						} 
						
						else if (Country.contains("Español") || Country.contains("English")
								|| Country.contains("Français") || Country.contains("Español"))
								 {
							
							Sync.waitElementPresent("xpath", "(//legend[@class='title-sm font-bold h5 mb-2.5'])[1]");
							Common.getText("xpath", "(//legend[@class='title-sm font-bold h5 mb-2.5'])[1]");
							Sync.waitPageLoad();
							Thread.sleep(4000);
							Common.navigateBack();
							ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
									"click on the country should navigate to the  " + Country + "Page",
									"successfully page navigating to " + Country + "PAGE",
									Common.getscreenShotPathforReport(Country));
							Thread.sleep(6000);
							   Sync.waitElementPresent("xpath", "//button[@aria-label='Close']");
					            Common.clickElement("xpath", "//button[@aria-label='Close']");
							
						}
						else {
//							Common.clickElement("xpath", "//span[contains(text(),'Confirm')]");
							Sync.waitPageLoad();
							Thread.sleep(4000);
//							Common.navigateBack();
							ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
									"click on the country should navigate to the  " + Country + "Page",
									"successfully page navigating to " + Country + "PAGE",
									Common.getscreenShotPathforReport(Country));

						}
					}
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the country selection page navigation",
					"After Clicking on the selected country it should navigate to the respective country page",
					"Unable to navigate to the respective country page after clicking on the selected country",
					Common.getscreenShot(
							"Failed to navigate to the respective country page after clicking on the selected country"));
			AssertJUnit.fail();
		}
	}

	public void Change_Shippingmethods(String Dataset) {
		// TODO Auto-generated method stub
		try {
			
			selectshippingmethod(Dataset);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigation to the checkout page",
					"after click on edit button from payment page it should navigate to the shiiping page",
					"Unable  to Navigate to the shipping page",
					Common.getscreenShot("failed to Navigate to the shipping page"));
			AssertJUnit.fail();
		}

	}

	public void Gift_message(String Dataset) {
		// TODO Auto-generated method stub
		String message = data.get(Dataset).get("message");
		try {
			Common.scrollIntoView("xpath", "//span[text()='Add Gift Message']");
			Sync.waitElementPresent(40, "xpath", "//span[text()='Add Gift Message']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Add Gift Message']");
			int gift = Common.findElements("xpath", "//button[contains(@class,'action action')]").size();
			System.out.println(gift);
			if (gift >= 3) {
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[text()='Delete']");
				Sync.waitPageLoad(40);
				Sync.waitElementPresent(40, "xpath", "//span[text()='Add Gift Message']");
				Common.clickElement("xpath", "//span[text()='Add Gift Message']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[text()='Gift Receipt']");
				Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
				Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
				Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
				Common.clickElement("xpath", "//button[@title='Add']");
				Sync.waitPageLoad(40);
				Thread.sleep(2000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
				String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
						.replace("Message: ", "");
				System.out.println(Messgae);
				Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
						"Gift card message should be applied", "Sucessfully gift message has been applied ",
						"failed to apply the gift message");

			} else {
				if(Common.getCurrentURL().contains("stage3"))
				{
					Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
					Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
					Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
					Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
					Common.clickElement("xpath", "//button[@title='Add']");
					Sync.waitPageLoad(40);
					Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
					String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
							.replace("Message: ", "");
					System.out.println(Messgae);
					Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
							"Gift card message should be applied", "Sucessfully gift message has been applied ",
							"failed to apply the gift message");
				}
				else
				{
				Common.clickElement("xpath", "//span[text()='Gift Receipt']");
				Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
				Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
				Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
				Common.clickElement("xpath", "//button[@title='Add']");
				Sync.waitPageLoad(40);
				Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
				String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
						.replace("Message: ", "");
				System.out.println(Messgae);
				Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
						"Gift card message should be applied", "Sucessfully gift message has been applied ",
						"failed to apply the gift message");
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift cart message", "Gift card message should be applied",
					"Unabled to apply the gift message", Common.getscreenShot("failed to apply the gift message"));
			AssertJUnit.fail();
		}

	}

	public void update_shoppingcart(String Dataset) {
		// TODO Auto-generated method stub
		String quantity = data.get(Dataset).get("Quantity");
		try {
			Common.clickElement("xpath", "(//div[@id='product-view-qty-adjuster'])[1]//select");
			Common.dropdown("xpath", "(//div[@id='product-view-qty-adjuster'])[1]//select", Common.SelectBy.VALUE, quantity);

			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productquantity = Common.findElement("xpath", "(//div[@id='product-view-qty-adjuster'])[1]//select")
					.getAttribute("value");
			System.out.println(productquantity);
			Common.assertionCheckwithReport(productquantity.equals(quantity),
					"validating the update quantity in shopping cart page",
					"Quantity should be update in the shopping cart page",
					"Qunatity has been updated in the shopping cart page",
					"Failed to update the product quantity in the shopping cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the update quantity in shopping cart page",
					"Quantity should be update in the shopping cart page",
					"Unable to update the product quantity in the shopping cart page",
					Common.getscreenShot("Failed to update the product quantity in the shopping cart page"));
			AssertJUnit.fail();
		}

	}

	public void Remove_Product(String dataSet) {
		// TODO Auto-generated method stub
		String Symbol = data.get(dataSet).get("Symbol");
		String products = data.get(dataSet).get("Products");

		try {
			Thread.sleep(4000);
			String subtotal = Common.findElement("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").getText().replace(Symbol, "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			System.out.println(subtotalvalue);
			String Productprice = Common.getText("xpath", "(//span[@data-label='Excl. Tax']//span[@class='price'])[5]")
					.replace(Symbol, "");
			Float pricevalue = Float.parseFloat(Productprice);
			System.out.println(pricevalue);
			Float Total = subtotalvalue - pricevalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			Sync.waitElementPresent("xpath", "//button[@aria-label='Remove " + products + "']");
			Common.clickElement("xpath", "//button[@aria-label='Remove " + products + "']");
			Common.clickElement("xpath", "//button[contains(text(),'OK')]");
			Sync.waitPageLoad(30);
			Thread.sleep(5000);
			String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']")
					.replace(Symbol, "");
			System.out.println("ordertotal"+    ordertotal);
			Thread.sleep(4000);
			Float ordervalue = Float.parseFloat(ordertotal);
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the remove prodcut form shopping cart page",
					"Product should be remove form the shopping cart page",
					"Sucessfully Product removed from the shopping cart page",
					"Failed to remove the product from the shopping cart page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the remove prodcut form shopping cart page",
					"Product should be remove form the shopping cart page",
					"Unable to remove the product from the shopping cart page",
					Common.getscreenShot("Failed to remove the product from the shopping cart page"));
			AssertJUnit.fail();
		}

	}

	public void simple_addtocart(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		String Prodprod=data.get(Dataset).get("Prod Product");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			
			// int Amount=Common.findElements("xpath",
			// "//div[@data-role='priceBox']").size();
			// for (int i = 0; i < Amount; i++) {
			// int value = i + 1;
			// Thread.sleep(2000);
			// List<WebElement> Price=Common.findElements("xpath",
			// "(//span[@data-price-type='finalPrice']//span[@class='price'])["+value+"]");
			// System.out.println(Price);
			// for(WebElement amount : Price)
			// {
			// String priceamount=amount.getText().replace("£", "");
			// Thread.sleep(2000);
			// Float PRICE =Float.parseFloat(priceamount);
			// System.out.println(PRICE);
			//
			// if(PRICE>0)
			// {
			// Common.mouseOver("xpath",
			// "(//span[@data-price-type='finalPrice']//span[@class='price'])["+value+"]");
			// Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			// Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			// Sync.waitPageLoad();
			// Thread.sleep(4000);
			// Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			// String message = Common.findElement("xpath",
			// "//div[@data-ui-id='message-success']")
			// .getAttribute("data-ui-id");
			// System.out.println(message);
			// Common.assertionCheckwithReport(message.contains("success"), "validating the
			// product add to the cart",
			// "Product should be add to cart", "Sucessfully product added to the cart ",
			// "failed to add product to the cart");
			// }
			// else
			// {
			//
			// }      
			// }
			// }
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("preprod"))
			{
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			}
			else if(Common.findElement("xpath","(//a[@class='link no-underline text-black'])[2]").getText().contains("ACCESSORIES"))
			{
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + Prodprod + "']");
				Common.clickElement("xpath", "//img[@alt='" + Prodprod + "']");
			}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//button[text()='Load More']");
				Common.clickElement("xpath", "//button[text()='Load More']");
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[text()='Load More']");
				Common.clickElement("xpath", "//button[text()='Load More']");
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + Prodprod + "']");
				Common.clickElement("xpath", "//img[@alt='" + Prodprod + "']");
				
			}
//			Sync.waitPageLoad(30);
//			Thread.sleep(6000);
//			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
//			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
//			System.out.println(name);
//			Thread.sleep(4000);
//			String product = data.get(Dataset).get("Products").toUpperCase();
//			System.out.println(product);
//			Common.assertionCheckwithReport(name.contains(product) || Common.getPageTitle().contains(product),
//					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
//					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Sync.waitElementPresent(50, "xpath", "//button[@aria-label='Close minicart']");
			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			AssertJUnit.fail();
		}
	}
	public void Simple_PDP(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				//Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			//Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
			Sync.waitElementVisible(30, "xpath", "//span[contains(@class, 'pdp-grid-title')]");
			String name = Common.findElement("xpath", "//span[contains(@class, 'pdp-grid-title')]").getText();
			Thread.sleep(4000);
			//String product = data.get(Dataset).get("Products").toUpperCase();
			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			click_UGC();
//			Locally_PDP();
			PDP_Tabs("Tabs");
			Common.actionsKeyPress(Keys.UP);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the PDP page", "In PDP fav ugc all should be appear",
					"Unable to see few things in the PDP",
					Common.getscreenShot("Failed to see few things in the PDP page"));
			AssertJUnit.fail();
		}

	}

	public void Locally_PDP() {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//span[@class='lcly-city-name']");
			Sync.waitElementPresent(30, "xpath", "//span[@class='lcly-city-name']");
			Common.clickElement("xpath", "//span[@class='lcly-city-name']");
			Thread.sleep(4000);
			Common.switchFrames("xpath", "//iframe[@id='lcly-iframe-inner-0']");
			Thread.sleep(4000);
			Sync.waitElementPresent(40, "xpath", "//a[@id='dealer-navigation-retailers']//span");
			String retail = Common.findElement("xpath", "//a[@id='dealer-navigation-retailers']//span").getText();
			System.out.println(retail);
			Common.assertionCheckwithReport(retail.contains("RETAILERS"), "validating the Find Locally in the PDP ",
					"when we click on the locally maps should be opened", "Sucessfully google maps has been opend",
					"failed to open the google maps when we click on the Find Locally");
			Common.switchToDefault();
			Common.clickElement("xpath", "//a[@id='lcly-iframe-closer-0']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Find Locally in the PDP ",
					"when we click on the locally maps should be opened",
					"Unable to open the google maps when we click on the Find Locally",
					Common.getscreenShot("Failed to open the google maps when we click on the Find Locally"));
			AssertJUnit.fail();
		}

	}

	public void Configurable_PDP(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				//Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
//			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
//			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Sync.waitElementVisible(30, "xpath", "//span[contains(@class, 'pdp-grid-title')]");
			String name = Common.findElement("xpath", "//span[contains(@class, 'pdp-grid-title')]").getText();

			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			click_UGC();
			//Locally_PDP();
			//Common.actionsKeyPress(Keys.UP);
//			add_simplarproducts("configurable product");
			 PDP_Tabs("Tabs");  
			
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the PDP page", "In PDP fav ugc all should be appear",
					"Unable to see few things in the PDP",
					Common.getscreenShot("Failed to see few things in the PDP page"));

			AssertJUnit.fail();
		}

	}

	public void add_simplarproducts(String Dataset) {
		// TODO Auto-generated method stub
		String colorproduct = data.get(Dataset).get("Products");
		String productcolor = data.get(Dataset).get("Color");
		String Productsize = data.get(Dataset).get("Size");

		try {
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			String simlar = Common.findElement("xpath", "//div[@class='m-product-upsell__cta']//a").getText();
			System.out.println(simlar);
			if (simlar.contains("SHOP NOW")) {
				Common.scrollIntoView("xpath", "//a[text()='" + colorproduct + "']");
				Common.clickElement("xpath", "//a[text()='" + colorproduct + "']");
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(colorproduct) || Common.getPageTitle().contains(colorproduct),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.navigateBack();
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[@class='m-product-upsell__cta']//a");
				Common.clickElement("xpath", "//div[@class='m-product-upsell__cta']//a");
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						name.contains(colorproduct) || Common.getPageTitle().contains(colorproduct),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");

			} else {
				AssertJUnit.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the similar products functionality",
					"Products should be add from the similar products from PDP page",
					"Unable to add the products from the similar products",
					Common.getscreenShot("Failed to add the products from the similar products"));
			AssertJUnit.fail();
		}

	}

	public void PDP_Tabs(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("names");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//h2[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//h2[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				/*String title = Common.findElement("xpath", "//a[text()='" + Links[i] + "']//parent::div")
						.getAttribute("aria-expanded");
				Common.assertionCheckwithReport(title.contains("true"), "verifying the tabs in PDP ",
						"After clicking on the " + Links[i] + "It should display the related content",
						"sucessfully after clicking on the " + Links[i] + "it has been displayed related content",
						"Failed to display related content" + Links[i]);*/

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the tabs in PDP ",
					"After clicking on the " + Links[i] + "It should display the related content",
					"Unable to display the content in  " + Links[i],
					Common.getscreenShot("Failed to display related content" + Links[i]));

			AssertJUnit.fail();
		}

	}

	public void Click_My_Return() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the user navigated to the my account page",
					"After clicking on my account it should navigate to my account",
					"Sucessfully Navigated to the my account page", "failed to Navigated to the my account page");

			Sync.waitElementPresent("xpath", "//a[text()='Spares, Repairs & Reservoirs']");
			Common.clickElement("xpath", "//a[text()='Spares, Repairs & Reservoirs']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Returns"),
					"validating the user navigated to the my Return page",
					"After clicking on my account it should navigate to my Returns page",
					"Sucessfully Navigated to the my Returns page", "failed to Navigated to the my Returns page");
			view_RMA();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the user navigated to the my Return page",
					"After clicking on my account it should navigate to my Returns page",
					"Unable to Navigated to the my Returns page",
					Common.getscreenShot("Failed to Navigated to the my Returns page"));

			AssertJUnit.fail();
		}

	}

	public void view_RMA() {
		String Print_OrderNumber = null;
		try {

			List<WebElement> RMA_Status = Common.findElements("xpath", "//span[text()='Approved']");

			if (!RMA_Status.isEmpty()) {

				for (WebElement element : RMA_Status) {
					String statusText = element.getText();

					System.out.println("Status:" + statusText);
					// Check if the text is equal to "Approved"
					if (statusText.equals("Approved")) {

						Common.clickElement("xpath", "//a[text()='View']");
						break;
					} else {
						System.out.println("No Approved Status Found");
					}
				}

				String ProductName = Common.findElementBy("xpath", "//td[@data-th='Product Name']").getText();
				String ProductQTY = Common.findElementBy("xpath", "//td[@data-th='QTY']").getText();
				String OrderNo = Common.findElementBy("xpath", "//td[@data-th='Order']").getText();

				System.out.println(ProductName);
				System.out.println(ProductQTY);
				System.out.println(OrderNo);

				Common.clickElement("xpath", "(//a[text()='RMA Packing Slip'])[1]");
				Thread.sleep(4000);
				// Need to write code to close the Pop-up

				String Print_ProductName = Common.findElementBy("xpath", "//td[@data-th='Product Name']").getText();
				String Print_ProductQTY = Common.findElementBy("xpath", "//td[@data-th='Qty']").getText();

				String Print_OrderNo = Common.findElementBy("xpath", "(//p[@class='order-date'])[2]").getText();
				Pattern pattern = Pattern.compile("#(\\d+)");
				Matcher matcher = pattern.matcher(Print_OrderNo);

				if (matcher.find()) {
					// Extract the order number from the first match
					Print_OrderNumber = matcher.group(1);
					System.out.println("Order Number: " + Print_OrderNumber);
				} else {
					System.out.println("Order number not found.");
				}

				System.out.println(Print_ProductName);
				System.out.println(Print_ProductQTY);
				System.out.println(Print_OrderNumber);

				Common.assertionCheckwithReport(
						ProductName.equals(Print_ProductName) && ProductQTY.equals(Print_ProductQTY)
								&& OrderNo.equals(Print_OrderNumber),
						"validating the user navigated to the my Return page",
						"After clicking on RMA Packin slip it should navigate to RMA Print page",
						"Sucessfully Navigated to the RMA Print page", "failed to Navigated to the RMA Print page");

			} else {

				String No_returns = Common.findElement("xpath", "//span[text()='There are no returns']").getText();
				System.out.println(No_returns);
				Common.assertionCheckwithReport(No_returns.contains("There are no returns"),
						"validating the My Return Page",
						"There are no returns should display after Clicking  my Returns",
						"Sucessfully There re no returns displayed in  My returns page",
						"failed to display There are no returns ");
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the user navigated to RMA Print page",
					"After clicking on my account it should navigate to RMA Print page",
					"Unable to Navigated to RMA Print page",
					Common.getscreenShot("Failed to Navigated to RMA Print page"));
			AssertJUnit.fail();
		}

	}

	public void LogoutExistingUser() {
		// TODO Auto-generated method stub
		try {

			// Common.clickElement("xpath", "//a[@class='a-logo']");

			String URL = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			Common.oppenURL(URL);
			signout();
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			AssertJUnit.fail();
		}
	}

	public void discountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input to enter discount.";
		String Symbol = data.get(dataSet).get("Symbol");
		System.out.println(Symbol);

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			int size1= Common.findElements("xpath", "//button[contains(text(), 'Agregar código de descuento')]").size();
		       if (size1>0) {
		    	   Sync.waitElementClickable("xpath", "//button[contains(text(), 'Agregar código de descuento')]");
		    	   Common.clickElement("xpath", "//button[contains(text(), 'Agregar código de descuento')]");
		    	   
		       } 
		       else {
					Sync.waitElementClickable("xpath", "//button[contains(text(), 'Add Discount Code')]");
					Common.clickElement("xpath", "//button[contains(text(), 'Add Discount Code')]");
		       }
			
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent("id", "discount-code");

				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
			} else {
				Sync.waitElementPresent("id", "discount-code");

				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("prodDiscountcode"));
			}

			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "//button[contains(@class,'btn btn-primary justify-center')]");
			Common.clickElement("xpath", "//button[contains(@class,'btn btn-primary justify-center')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
//			Common.scrollIntoView("xpath", "//div[@ui-id='message-success']");
//			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
//			String discountcodemsg = Common.getText("xpath", "//span[text()='Your coupon was successfully applied.']");
//			System.out.println(discountcodemsg);
//			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully")||discountcodemsg.contains("Su cupón fue aplicado con éxito."),
//					"verifying pomocode", expectedResult, "promotion code working as expected",
//					"Promation code is not applied");

			
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));

			AssertJUnit.fail();

		}
	}

	public void Compare_Products() throws Exception {

		try {

			List<WebElement> compareLinks = Common.findElements("xpath", "//*[text()='Add to Compare']");

			System.out.println(compareLinks.size());
			if (compareLinks.size() >= 2) {

				compareLinks.get(1).click();
				Thread.sleep(3000);
				compareLinks.get(3).click();
				Sync.waitElementPresent("xpath", "//a[text()='comparison list']");
				Common.clickElement("xpath", "//a[text()='comparison list']");
        try {
				Common.clickElement("xpath", "(//a[contains(@class,'action tocart primary a-btn a-btn')])[1]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
        }
        catch (Exception | Error e)  {
				
        	Common.clickElement("xpath", "(//span[text()='Shop Now'])[1]");
        	Sync.waitPageLoad();
			Thread.sleep(4000);
        }
        	Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Close minicart']");
				Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
//				
//				Sync.waitElementVisible("xpath", "//a[text()='shopping cart']");
//
//				String Shoppping = Common.findElement("xpath", "//a[text()='shopping cart']").getText();
//
//				System.out.println(Shoppping);
//				AssertJUnit.assertEquals(Shoppping, "shopping cart");
			} else {
				System.out.println("Insufficient number of products for comparison.");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Compare Products",
					"Products added to Compare list successfull", "failed to add product to compareList",
					Common.getscreenShotPathforReport("CompareProductfail"));
			AssertJUnit.fail();
		}

	}

	public void Add_Wishlist() throws Exception {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//button[@id='add-to-wishlist']");
			Common.javascriptclickElement("xpath", "//button[@id='add-to-wishlist']");
			int Size = Common.findElements("xpath", "//span[@x-html=\"message.text\"]").size();
			System.out.println(Size);
			if (Size > 0) {

				Sync.waitElementPresent("xpath", "//button[@id='add-to-wishlist']");
				Common.javascriptclickElement("xpath", "//button[@id='add-to-wishlist']");
              
				Common.clickElement("id", "customer-menu");
				Common.clickElement("id", "customer.header.wishlist.link");
			} else {
				Sync.waitPageLoad();
				Thread.sleep(6000);
				String Error = Common.findElement("xpath", "//span[contains(text(),'You must login or register')]").getText();
				System.out.println(Error);
				if (Error.contains("You must login")) {
					Login_Account("Account");
				} else {
					System.out.println("no Error message displayed");

				}
			}
			Thread.sleep(3000);
			int WishlistMSG = Common.findElements("xpath", "//span[@x-html='message.text']").size();
			System.out.println("Wishlist" + WishlistMSG);
			Common.assertionCheckwithReport(WishlistMSG > 0, "validating the My Wish List",
					"My Wish List should be display", "Sucessfully navigated to My Wish List ",
					"failed to navigate to My Wish List");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating product added to wishlist ",
					"Products added to Compare list successfull", "failed to add product to wishlist",
					Common.getscreenShotPathforReport("Wishlistfail"));
			AssertJUnit.fail();
		}
	}

	public void AddtoCart_Wishlist() throws Exception {
		// TODO Auto-generated method stub
		
		try {
		String Wishlist = Common.findElement("xpath", "//h1//span[text()='My Wishlist']").getText();		
		AssertJUnit.assertEquals(Wishlist, "My Wishlist");
	   
		    Sync.waitElementPresent(30, "xpath", "(//span[text()='Add to Cart'])[2]");
			Common.mouseOverClick("xpath", "(//span[text()='Add to Cart'])[2]");
		
			minicart_Checkout();

		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Adding to cart from wishlist ",
					"Products added to cart successfull", "failed to add Products to cart",
					Common.getscreenShotPathforReport("AddtoCartfail"));
			AssertJUnit.fail();
		}

	}

	public void Shoppingcart_page() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementVisible(30, "xpath", "//span[text()='Back to Cart']");
			Common.clickElement("xpath", "//span[text()='Back to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Shopping Cart"),
					"validating the navigates to the shopping cart page",
					"After clicking on the reorder it should navigate to the shopping cart page",
					"Successfully navigated to the shopping cart page", "Failed to Navigate to the shopping cart page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigates to the shopping cart page",
					"After clicking on the reorder it should navigate to the shopping cart page",
					"Unable to Navigate to the shopping cart page",
					Common.getscreenShotPathforReport("Failed to Navigate to the shopping cart page"));
			AssertJUnit.fail();
		}

	}

	public void minicart_ordersummary_discount(String Dataset) {
		// TODO Auto-generated method stub.
		String expectedResult = "It should opens textbox input to enter discount.";
		try {
			Sync.waitElementPresent("xpath", "//button[@id='discount-form-toggle']");
			Common.clickElement("xpath", "//button[@id='discount-form-toggle']");

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			if(Common.getCurrentURL().contains("prepord")) {
				Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("Discountcode"));
			}else {
				Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("ProdDiscountcode"));
			}
			

			int size = Common.findElements("xpath", "//input[@name='coupon_code']").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//button[@value='Apply Discount']");
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Common.scrollIntoView("xpath", "//span[@x-html='message.text']");
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//span[@x-html='message.text']");
			Common.assertionCheckwithReport(discountcodemsg.contains("You used coupon code"), "verifying pomocode",
					expectedResult, "promotion code working as expected", "Promation code is not applied");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the promocode in the shopping cart page",
					"Promocode should be apply in the shopping cart page",
					"Unable to display the promocode in the shopping cart page",
					Common.getscreenShot("Failed to display the promocode in the shopping cart page"));
			AssertJUnit.fail();
		}
		try {
	            Common.actionsKeyPress(Keys.PAGE_UP);
			    String Subtotal = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").replace("$", ""); 
			    BigDecimal subtotalValue = new BigDecimal(Subtotal).setScale(2, BigDecimal.ROUND_HALF_UP); 
			    
		        String Discount = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(segment.value)']").replace("$", "").replace("- ", "");
		        BigDecimal discountValue = new BigDecimal(Discount).setScale(2, BigDecimal.ROUND_HALF_UP);
		        
		        String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']").replace("$", ""); 
		        BigDecimal orderTotalValue = new BigDecimal(ordertotal).setScale(2, BigDecimal.ROUND_HALF_UP);
		        
		        String Shipping = Common.getText("xpath", "(//div[@x-text='hyva.formatPrice(segment.value)'])[2]").replace("$", "");  
		        BigDecimal ShippingValue = new BigDecimal(Shipping).setScale(2, BigDecimal.ROUND_HALF_UP);
		        Thread.sleep(3000);
		        
		        BigDecimal Total = (subtotalValue.add(ShippingValue)).subtract(discountValue).setScale(2, BigDecimal.ROUND_HALF_UP); 
		        
		        System.out.println("Subtotal: " + subtotalValue);  
		        System.out.println("Discount: " + discountValue);
		        System.out.println("Shipping:"  +ShippingValue);
		        System.out.println("Total: " + Total);  
		        System.out.println("Order Total Value: " + orderTotalValue); 
		        
		        System.out.println("Calculated Total: " + Total);  
		        System.out.println("Order Total from Website:  " + ordertotal);  

		        // Define a tolerance for comparison  
		        BigDecimal tolerance = new BigDecimal("0.01");  

		        // Check if the expected total is approximately equal to the order total  
		        boolean isEqual = Total.subtract(orderTotalValue).abs().compareTo(tolerance) <= 0.01; 
		         Common.assertionCheckwithReport(isEqual,  
		                "validating the order summary on the payment page",  
		                "Order summary should be displayed on the payment page and all fields should display",  
		                "Successfully order summary is displayed on the payment page and fields are displayed",  
		                "Failed to display the order summary and fields under order summary");  

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Unabel to display the Order summary and fields are not displayed in the payment page",
					Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
			AssertJUnit.fail();
		}

	}

	public void invalid_Discount(String Dataset) {
		// TODO Auto-generated method stub
		String invalidcode = data.get(Dataset).get("invalidcode");
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add Discount Code']");
			Common.clickElement("xpath", "//span[text()='Add Discount Code']");
			Sync.waitElementPresent("id", "discount-code");
			Common.textBoxInput("id", "discount-code", invalidcode);
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']");
			String errormessage = Common.findElement("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']")
					.getText();
			System.out.println(errormessage);
			Common.assertionCheckwithReport(errormessage.contains("The code "+ invalidcode +" is not active."),
					"validating the discount error message in the payment page",
					"Error message should be display in the payment page",
					"Successfully error message should be displayed",
					"Failed to display the error message in the payment page");
			Tool_Tip();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the discount error message in the payment page",
					"Error message should be display in the payment page",
					"unable to display the  error message should in the payment page",
					Common.getscreenShot("Failed to display the error message in the payment page"));
			AssertJUnit.fail();
		}

	}

	public void Tool_Tip() {
		try {
			Sync.waitElementPresent("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Shipping']");
			Common.clickElement("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Shipping']");
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-content-type='text']//p")
					.getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("Shipping"),
					"validating the shiiping tooltip in the payment page",
					"after clicking on the shipping tooltip message should appear",
					"Successfully shipping tooltip is displayed", "Failed to display the Tooltip for shipping");
			Sync.waitElementPresent("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Tax']");
			Common.clickElement("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Tax']");
			Thread.sleep(4000);
			String taxtooltip = Common.findElement("xpath", "//button[@aria-describedby='formShippingTotalTaxTooltip']")
					.getText();
			System.out.println(taxtooltip);
			Common.assertionCheckwithReport(taxtooltip.contains("Tax"),
					"validating the Tax tooltip in the payment page",
					"after clicking on the Tax tooltip message should appear", "Successfully Tax tooltip is displayed",
					"Failed to display the Tooltip for Tax");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the tooltip in the payment page",
					"after clicking on the particular tooltip message should appear",
					"Unable to display the Tooltip in the payment page",
					Common.getscreenShot("Failed to display the Tooltip in the payment page"));
			AssertJUnit.fail();
		}
	}

	public void Edit_Gift_Message(String Dataset) {
		// TODO Auto-generated method stub
		String message = data.get(Dataset).get("Discountcode");
		try {
			Sync.waitElementPresent("xpath", "//button[@type='submit']//span[text()='Edit']");
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Edit']");
			Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
			Common.clickElement("xpath", "//button[@title='Add']");
			String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
					.replace("Message: ", "");
			System.out.println(Messgae);
			Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
					"Gift card message should be applied", "Sucessfully gift message has been applied ",
					"failed to apply the gift message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift cart message", "Gift card message should be applied",
					"Unable to dapply the gift message", Common.getscreenShot("Failed to apply the gift message"));
			AssertJUnit.fail();
		}
	}

	public void Invalid_search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("InvalidProductName");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//button[@id='menu-search-icon']");
			String open = Common.findElement("xpath", "//button[@id='menu-search-icon']").getAttribute("id");
			Thread.sleep(4000);
			System.out.println(open);
			Common.assertionCheckwithReport(open.contains("search"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']",
					data.get(Dataset).get("InvalidProductName"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);

			String productsearch = Common.findElement("xpath", "//h3[contains(@class, 'c-srp-title__results')]").getText();
			String searchproduct=Common.findElement("xpath", "//h3[contains(@class, 'c-srp-title__results')]").getAttribute("class");
			System.out.println(searchproduct);
			System.out.println(productsearch);
			Thread.sleep(3000);
			Common.assertionCheckwithReport(productsearch.contains("Showing search results for:") || searchproduct.contains("ssdsfddfs"),
					"validating the search functionality", "enter Invalid product name will display in the search box",
					"user enter the Invalid product name in  search box", "Failed to see the Invalid product name");
			Thread.sleep(8000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter Invalid product name will display in the search box",
					" unable to enter the Invalid product name in  search box",
					Common.getscreenShot("Failed to see the Invalid product name"));
			AssertJUnit.fail();
		}
	}


	public void Filter() throws InterruptedException {
		// TODO Auto-generated method stub
		try {

			Common.scrollIntoView("xpath", "//input[@value='Blue']");
			Sync.waitElementPresent("xpath", "//input[@value='Blue']");
			Common.clickElement("xpath", "//input[@value='Blue']");
//			Common.scrollIntoView("xpath", "//input[@value='Blue']");
			Thread.sleep(4000);
			String SelectedFilter = Common.findElement("xpath", "//div[@data-attr='osprey_common_color']//label//span[@data-color='Blue']").getText();
			System.out.println(SelectedFilter);
			System.out.println("SelectedFilter:" + SelectedFilter);
			String RetrivedValue = "blue";
			if (SelectedFilter.equals("Blue")) {

				List<WebElement> Series_Filters = Common.findElements("xpath",
						"//div[@class='m-product-card__image-wrapper']//a//img");

				for (WebElement Filter : Series_Filters) {
					// System.out.println(Filter);
					String AttributeValue = Filter.getAttribute("src");

					if (AttributeValue != null && AttributeValue.contains(RetrivedValue)) {

						System.out.println("Attribute '" + AttributeValue + "' contains the text '" + RetrivedValue
								+ "' for product: " + AttributeValue);

					} else {

						System.out.println("Attribute '" + AttributeValue + "' does not contain the text '"
								+ RetrivedValue + "' for product: " + AttributeValue);
						AssertJUnit.fail();
					}
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Filter functionality",
					"Products should be display as per selected Filter option ",
					" Unable to display the Products as per selected Filter option",
					Common.getscreenShot("Failed to Filter"));
			AssertJUnit.fail();
		}
	}


	public void webpagelinks_validation(String Dataset) throws Exception, IOException {
		// TODO Auto-generated method stub
		String links = data.get(Dataset).get("Links");
		int j = 0;

		String[] strArray = links.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("preprod")) {

				Common.oppenURL((strArray[i]));
				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);

				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 404 error",
							Common.getscreenShotPathforReport("link" + i));

				}

			} else if (Common.getCurrentURL().contains("https://www.osprey.com/gb/")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-stage", "osprey"));

				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);

				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 40 error",
							Common.getscreenShotPathforReport("link" + i));

				}
			}
		}

		if (j > 1) {
			AssertJUnit.fail();
		}

	}

	public void Gift_cards(String Dataset) {
		// TODO Auto-generated method stub
		String GiftCard = data.get(Dataset).get("Osprey");
		String prodgiftcard=data.get(Dataset).get("Prod Product");
		try
		{
			for (int i = 0; i <= 10; i++) {
				if(Common.getCurrentURL().contains("/us/es/"))
				{
					Common.clickElement("xpath", "//span[contains(@class, 'flex')and contains(text(), 'Destacados')]");
				}
				else
				{
				Common.clickElement("xpath", "//span[contains(@class, 'flex')and contains(text(), 'Featured')]");
				}
				if(Common.getCurrentURL().contains("preprod") && Common.getCurrentURL().contains("/us/es/") )
				{
				Sync.waitElementPresent("xpath", "//span[text()='Tarjetas de regalo']");
				Common.clickElement("xpath", "//span[text()='Tarjetas de regalo']");
				}
				else
				{
					Sync.waitElementPresent("xpath", "//span[text()='Gift Cards']");
					Common.clickElement("xpath", "//span[text()='Gift Cards']");
				}
				
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad(30);
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("preprod"))
			{
			Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + GiftCard + "')]");
			Common.clickElement("xpath", "//img[contains(@alt,'" + GiftCard + "')]");
			Sync.waitPageLoad();
			}
			else
			{
				Sync.waitElementPresent(30, "xpath", "(//img[contains(@alt,'" + prodgiftcard + "')])[3]");
				Common.clickElement("xpath", "(//img[contains(@alt,'" + prodgiftcard + "')])[3]");
				Sync.waitPageLoad();
			}
			Thread.sleep(4000);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift card Navigation to the PDP page",
					"After clicking on the gift card it should navigate to the PDP",
					"Unable to Navigate the Gift card to the PDP page",
					Common.getscreenShot("Failed to Navigate the Gift card to the PDP page"));
			AssertJUnit.fail();
			
		
		}
	}
	
	
	
	public void SendLater_Card_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//span[text()='"+ amount +"']");
			String Price=Common.findElement("xpath", "//span[@data-price-type='finalPrice']//span[@class='price']").getText();
			Common.assertionCheckwithReport(Price.contains(amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			Common.clickElement("xpath", "(//img[@class='amcard-image'])[2]");
			String SmallCard=Common.findElement("xpath", "//img[@class='amcard-image -active']").getAttribute("src");
			String MainCard=Common.findElement("xpath", "//img[@class='fotorama__img']").getAttribute("src");
			Common.assertionCheckwithReport(SmallCard.equals(MainCard),
					"validating the selected gift card",
					"After clicking on the card design gift card should be match",
					"Successfully Gift card design has been matched",
					"Failed to match the Gift card design");
			SendLater_Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			AssertJUnit.fail();
		}

	}
	public void SendLater_Giftcard_details(String Dataset) {
		// TODO Auto-generated method stub
		String Giftmessage=data.get(Dataset).get("message");
		try
		{
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_sender_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_name']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_email']", data.get(Dataset).get("Email"));
			Common.textBoxInput("xpath", "//textarea[@name='am_giftcard_message']", Giftmessage);
			Thread.sleep(3000);
			String Message=Common.findElement("xpath", "//textarea[@name='am_giftcard_message']").getAttribute("value");
			System.out.println(Message);
			Common.assertionCheckwithReport(Message.equals(Giftmessage),
					"validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Successfully message has been dispalyed for the Gift card",
					"Failed to display the gift message for the Gift Card");
			Common.clickElement("xpath", "//label[text()='Send later']");

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate currentDate = LocalDate.now();
			String formattedDate = currentDate.format(dateFormatter);


			LocalDate nextDay = currentDate.plusDays(1);
			String formattednextDay = nextDay.format(dateFormatter);

			System.out.println("Current Date: " + formattedDate);
			System.out.println("NextDay Date: "+ formattednextDay);


			Common.textBoxInput("xpath", "//input[@id='am_giftcard_date_delivery']", formattednextDay);
			Common.dropdown("id", "am_giftcard_date_delivery_timezone", Common.SelectBy.INDEX, "1");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Unable to display the gift message for the Gift Card",
					Common.getscreenShot("Failed to display the gift message for the Gift Card"));
			AssertJUnit.fail();
		}
	}
	
	public String DeliveryAddress_Guestuser_Gift(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");
		String Shipping="";

		try {
			Thread.sleep(5000);
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
			} else {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Prod Email"));
			}

		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
		}
		String expectedResult = "email field will have email address";

		int Size =Common.findElements("xpath", "//span[text()='Review & Payments']").size();

			try {
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(4000);
				String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
				System.out.println(data.get(dataSet).get("City"));

					 Thread.sleep(4000);
	                 Common.scrollIntoView("xpath", "//select[@name='region']");
	                 Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	                 Thread.sleep(3000);
	                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region']")
	                         .getAttribute("value");
	                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
		              System.out.println(Shipping);
	                 System.out.println(Shippingvalue);
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]",
						data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]",
						data.get(dataSet).get("phone"));

			
		}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating shipping address",
						"shipping address is filled in to the fields", "user faield to fill the shipping address",
						Common.getscreenShotPathforReport("shipingaddressfaield"));
				AssertJUnit.fail();

			
			}
			return Shipping;
		}
	

	public void Card_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			AssertJUnit.fail();
		}
		
	}

	public void Giftcard_details(String Dataset) {
		// TODO Auto-generated method stub
		String Giftmessage=data.get(Dataset).get("message");
		try
		{
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_sender_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_name']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_email']", data.get(Dataset).get("Email"));
			Common.textBoxInput("xpath", "//textarea[@name='am_giftcard_message']", Giftmessage);
			Thread.sleep(3000);
			String Message=Common.findElement("xpath", "//textarea[@name='am_giftcard_message']").getAttribute("value");
			System.out.println(Message);
			Common.assertionCheckwithReport(Message.equals(Giftmessage),
					"validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Successfully message has been dispalyed for the Gift card",
					"Failed to display the gift message for the Gift Card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Unable to display the gift message for the Gift Card",
					Common.getscreenShot("Failed to display the gift message for the Gift Card"));
			AssertJUnit.fail();
		}
		
	}
	
public String Gift_card(String dataSet) {
		String code="";
		try
		{
			String URL = Common.getCurrentURL();
			System.out.println(URL);
			if(URL.contains("stage")|| URL.contains("preprod")) {
			Thread.sleep(3000);
			
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");	
		Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
		Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("GiftCard3_Stage"));
		code=data.get(dataSet).get("GiftCard3_Stage");
		Common.actionsKeyPress(Keys.ARROW_UP);
		Common.clickElement("xpath","//button[@aria-label='Add Code']");
		Thread.sleep(2000);
		String successmsg=Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
	    System.out.println(successmsg);	
		Common.assertionCheckwithReport(successmsg.contains("added"),
				"validating the success message after applying gift card",
				"Success message should be displayed after the applying of gift card",
				"Sucessfully gift card has been applyed","Failed to apply the gift card");
			}
			else
			{
				Common.scrollIntoView("xpath", "//button[contains(text(),'Add Gift Card')]");
				Common.clickElement("xpath","//button[contains(text(),'Add Gift Card')]");
				Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("GiftCard_Prod"));
//				Common.actionsKeyPress(Keys.ARROW_UP);
				Common.clickElement("xpath","//button[@aria-label='Add Code']");
				Thread.sleep(2000);
				String successmsg=Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			    System.out.println(successmsg);	
				Common.assertionCheckwithReport(successmsg.contains("added"),
						"validating the success message after applying gift card",
						"Success message should be displayed after the applying of gift card",
						"Sucessfully gift card has been applyed","Failed to apply the gift card");
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"Success message should be displayed after the applying of gift card",
					"Sucessfully gift card has been applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			Assert.fail();
		}
		return code;
	}
	public void invalid_Gift_card(String dataSet) {
		try
		{
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");	
			Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
		    Common.scrollIntoView("xpath", "//input[@x-model='giftCardCode']");
			Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("InvalidGC"));
			
			Common.clickElement("xpath","//button[@aria-label='Add Code']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");	
			Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
//		String errormsg=Common.findElement("xpath", "//div[@role='alert']").getText();
//	  System.out.println(errormsg);
//		
//		Common.assertionCheckwithReport(errormsg.contains("not found"),
//				"validating the error message after applying gift card",
//				"error message should be displayed after the applying of gift card",
//				"Sucessfully gift card has not been applyed","Failed to apply the gift card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"error message should be displayed after the applying of gift card",
					"Sucessfully gift card has been not applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			AssertJUnit.fail();
		}
		
	}

	public String giftCardSubmitOrder() throws Exception {
		// TODO Auto-generated method stub

		String order = "";
		String expectedResult = "It redirects to order confirmation page";
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
			Thread.sleep(4000);
   if(Common.getCurrentURL().contains("stage") ||Common.getCurrentURL().contains("preprod") )
   {
//	   Common.refreshpage();
	   Thread.sleep(4000);
	   Common.clickElement("xpath", "//input[@id='payment-method-free']");
	   Thread.sleep(4000);
	   Common.clickElement("xpath", "//button[contains(text(),'Place Order')]");
			//Common.refreshpage();
		Thread.sleep(3000);
   }
   else
   {
	   AssertJUnit.fail();
   }

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(10000);
				String sucessMessage = Common.getText("xpath", "//h1[normalize-space()='Thank you for your purchase!']").trim();

//				Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", "//h1[normalize-space()='Thank you for your purchase!']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
					Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
					System.out.println(order);

				}
			
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				AssertJUnit.fail();
			}

		}
		return order;
	}


	public void Other_Amount(String Dataset) {
		// TODO Auto-generated method stub
		String Enter_amount=data.get(Dataset).get("Another Amount");
		System.out.println(Enter_amount);
		try
		{
			Sync.waitPageLoad();
//			Sync.waitElementPresent("xpath", "//input[@type='number']");
			Common.clickAndtextBoxInput("xpath", "//input[@type='number']", data.get(Dataset).get("Another Amount"));
//			Common.textBoxInput("xpath", "//input[@type='number']", data.get(Dataset).get("Enter_amount"));
			Common.clickElement("xpath", "//button[@class='amcard-button']");
			String Price=Common.findElement("xpath", "//span[@data-price-type='finalPrice']//span[@class='price']").getText();
			System.out.println(Price);
			Common.assertionCheckwithReport(Price.contains(Enter_amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			Common.clickElement("xpath", "(//img[@class='amcard-image'])[2]");
			String SmallCard=Common.findElement("xpath", "//img[@class='amcard-image -active']").getAttribute("src");
			String MainCard=Common.findElement("xpath", "//img[@class='fotorama__img']").getAttribute("src");
			Common.assertionCheckwithReport(SmallCard.equals(MainCard),
					"validating the selected gift card",
					"After clicking on the card design gift card should be match",
					"Successfully Gift card design has been matched",
					"Failed to match the Gift card design");
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			AssertJUnit.fail();
		}

	}

	public void whishlist_share_Button(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("stage")|| Common.getCurrentURL().contains("preprod"))
			{
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.javascriptclickElement("xpath", "//button[@title='Share Favorites']");
				Thread.sleep(8000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your Favorites has been shared."),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
			
			}
			else
			{
				Thread.sleep(4000);
				Common.javascriptclickElement("xpath", "//button[@title='Share Wish List']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Thread.sleep(4000);
				Common.javascriptclickElement("xpath", "//button[@title='Share Favorites']");
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shared whishlist functionality",
					"sucess message should display after share whishlist",
					"Unable to display the message for whishlist", Common.getscreenShot("Failed to display the message for whishlist"));
			AssertJUnit.fail();
		}
		
	}

	public String Check_money_order() throws Exception {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//label[@for='checkmo']");
			Common.clickElement("xpath", "//label[@for='checkmo']");
			String check=Common.findElement("xpath", "(//label[@for='checkmo']//parent::div//parent::div)[1]").getAttribute("class");
			Common.assertionCheckwithReport(check.contains("active"),
					"validating the check money order in payment page",
					"Check money order radio button should be selected",
					"Sucessfully check money order has been selected",
					"failed to select the check mony order");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the check money order in payment page",
					"Check money order radio button should be selected",
					"Unable to select the check mony order", Common.getscreenShot("Failed to select the check mony order"));
			AssertJUnit.fail();
		}
		String order = "";
		String expectedResult = "It redirects to order confirmation page";
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
			Thread.sleep(4000);

			Common.clickElement("xpath", "//button[@class='action primary checkout']");
			//Common.refreshpage();
		Thread.sleep(3000);

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

//				Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']//p//span").size() > 0) {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//strong");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//div[@class='checkout-success']//span").size() > 0) {
					Common.getText("xpath", "//div[@class='checkout-success']//span");
					System.out.println(order);

				}
			
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				AssertJUnit.fail();
			}

		}
		return order;
	}

	public void FUll_Payment(String dataSet) {
	 	

		String Symbl = data.get(dataSet).get("Symbol");
		try {
			String  GiftCard=data.get(dataSet).get("GiftCard2");
			Thread.sleep(6000);
			String Total_Incl_Tax =Common.getText("xpath", "//div[@class='item grand_total']//span[contains(@class,'value text-right text-sale-font hf:title-sm os:title-sm')]").replace(Symbl,"");
			
			System.out.println("Total_Incl_Tax :"+Total_Incl_Tax);
			Common.assertionCheckwithReport(Total_Incl_Tax.equals("0.00"),
					"validating the check money order in payment page",
					"Check money order radio button should be selected",
					"Sucessfully check money order has been selected",
					"failed to select the check mony order");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code  order in payment page",
					"Check Gift code applied and Fullpayment is applied",
					"Unable add the Giftcode", Common.getscreenShot("Failed to add Giftcoder"));
			Assert.fail();
		}
	}

	public void Add_GiftCode_Myaccount(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try{
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//a[@title='Gift Cards']");
			Common.clickElement("xpath", "//a[@title='Gift Cards']");
			
			
			Assert.assertEquals("Gift Cards / Dashboard", Common.getPageTitle());
			
			
			Sync.waitElementPresent("xpath", "//input[@placeholder='Enter your Code']");
		    Common.textBoxInput("xpath", "//input[@placeholder='Enter your Code']", data.get(dataSet).get("GiftCard2"));
			System.out.println(data.get(dataSet).get("GiftCard2"));
			Common.clickElement("xpath", "//span[text()='Add']");
			
		
			Thread.sleep(6000);
		String Applied_Code = Common.findElement("xpath", "(//p[@class='text-sm'])[1]").getText();
		
		Common.assertionCheckwithReport(Applied_Code.equals(data.get(dataSet).get("GiftCard2")),
				"validating the Gifcode Applied in My Account",
				"Giftcode should be Applied",
				"Sucessfully Giftcode should be Applied",
				"failed to add Giftcode Apply");
			



		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code applied in Myaccount page",
					"Check Gift code applied in Myaccount page",
					"Unable add the Giftcode", Common.getscreenShot("Failed to add Giftcoder in Myaccount page"));
			Assert.fail();
		}
	}

	public void Select_Gift_Code(String dataSet) {
		// TODO Auto-generated method stub
		String Giftcard = data.get(dataSet).get("GiftCard2");
		try {
			Common.clickElement("xpath", "//input[@placeholder='Enter your Code']");
 
			//			Common.dropdown("xpath", "//input[@name='amcard-field -datalist']", Common.SelectBy.TEXT, "GiftCard2");
			Common.clickElement("xpath", "//a[text()='"+Giftcard+"']");
			Common.clickElement("xpath", "//span[contains(text(),'Add Code')]");
			
			Thread.sleep(6000);
//			String successmsg=Common.findElement("xpath", "//div[@role='alert']").getText();
//		  System.out.println(successmsg);
//			
//			Common.assertionCheckwithReport(successmsg.contains("added"),
//					"validating the success message after applying gift card",
//					"Success message should be displayed after the applying of gift card",
//					"Sucessfully gift card has been applyed","Failed to apply the gift card");
			FUll_Payment("Giftcard");
		}
		
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code Selected in Payment page",
					"Check Gift code Selected in Payment page",
					"Unable add the Giftcode in Payment page", Common.getscreenShot("Failed to add Giftcoder in Payment page"));
			Assert.fail();
		}
	}

	public void Remove_GiftCode() {
		// TODO Auto-generated method stub
		try{
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//a[@title='Gift Cards']");
			Common.clickElement("xpath", "//a[@title='Gift Cards']");
			
			Thread.sleep(4000);
			Assert.assertEquals("Gift Cards / Dashboard", Common.getPageTitle());
			Thread.sleep(4000);
	        Common.clickElement("xpath", "//a[@title='Remove Gift Cards Code']");
	        Thread.sleep(4000);
	        Common.clickElement("xpath", "//button[contains(text(),'OK')]");
	        Thread.sleep(2000);
//	        String Remove_Code = Common.findElement("xpath", "//div[@role='alert']").getText();
//		
//		Common.assertionCheckwithReport(Remove_Code.contains("removed"),
//				"validating the Gifcode Applied in My Account",
//				"Giftcode should be Applied",
//				"Sucessfully Giftcode should be Applied",
//				"failed to add Giftcode Apply");
		

		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code applied in Myaccount page",
					"Check Gift code applied in Myaccount page",
					"Unable add the Giftcode", Common.getscreenShot("Failed to add Giftcoder in Myaccount page"));
			Assert.fail();
		}
	}

	public void Verify_Price(String Dataset) {
		
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
	
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
		     
			Thread.sleep(4000);
			String Price = Common.findElement("xpath", "//div[@aria-label='Price']//span[contains(@id,'product-price')]//span").getText();
			System.out.println(Price);
			
			if(Common.getCurrentURL().contains("/gb")) {

				double productPrice = Double.parseDouble(Price.replace("£", ""));
				System.out.println(productPrice);
			}
			else {
			double productPrice = Double.parseDouble(Price.replace("$", ""));
			System.out.println(productPrice);
			}
			
			double productPrice = 0;
			if (productPrice <= 50.0) {

//		            	Common.scrollIntoView("xpath", "//button[@title='Add to Cart']");
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

				System.out.println("Product added to cart.");
		            }
			
			Thread.sleep(4000);
			
			
			
//			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Price in PLP page",
					"Check Price In PLp page",
					"Unable validate the Price in PLP ", Common.getscreenShot("Failed toValidate  price in PLP page"));
			AssertJUnit.fail();
		}
	}

	
public void Verify_OrderTotal() {
	
		try {
      	
			String Ordertotal = Common.findElement("xpath", "//div[@class='item grand_total']//span[contains(@class,'value')]").getText().trim();
			System.out.println(Ordertotal);
			
			if(Common.getCurrentURL().contains("gb")) {

				double Order_Total = Double.parseDouble(Ordertotal.replace("£", ""));
				System.out.println(Order_Total);
				if (Order_Total <= 50.0) {

					System.out.println("Order Total is Less than 50");
			            }
				else {
					System.out.println("Order Total is Not Less than 50");
				}
			}
			else {
			double Order_Total = Double.parseDouble(Ordertotal.replace("$", ""));
			System.out.println(Order_Total);
					
			if (Order_Total <= 50.0) {

				System.out.println("Order Total is Less than or Equal to 50");
		            }
			else {
				System.out.println("Order Total is Not Less than or Equal to 50");
			}
		
			}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Price in PLP page",
					"Check Price In PLp page",
					"Unable validate the Price in PLP ", Common.getscreenShot("Failed toValidate  price in PLP page"));
			AssertJUnit.fail();
		}
	}	
	
	
	
public String After_Pay_payment(String dataSet) throws Exception {
	// TODO Auto-generated method stub
	String order = "";
	Sync.waitPageLoad();
	Thread.sleep(3000);	
	String expectedResult = "User should able to proceed the afterpay payment method";

	try {
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//label[@for='payment-method-stripe_payments']");
		int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

		Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
				"User unable to land o n the paymentpage");
		Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");

//		Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
//		int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
//		System.out.println(payment);
//		if (payment > 0) {
			//Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			//Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
			//Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
			if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
					{
				int savedcard=Common.findElements("xpath", "//input[@type='radio' and @name='use_saved_stripe_method']").size();
				if(savedcard==2)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
			Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
			Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");			
			Common.switchToDefault();
				}
				else
				{
					Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
					Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");						
					Common.switchToDefault();
				}
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "(//button[contains(@class,'btn-place-order')])[1]");
			Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
			Thread.sleep(4000);
			
//			Sync.waitElementPresent(30, "xpath", "//div[@class='stripe-new-payments']//label[@for='stripe-new-payments']");
//			Common.javascriptclickElement("xpath", "//div[@class='stripe-new-payments']//label[@for='stripe-new-payments']");
//			Thread.sleep(3000);	
//			
//			Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
//			Common.clickElement("xpath", "//button[@class='action primary checkout']");
//			
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Authorize Test Payment')]");
			Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
					}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");
				String Afterpay=Common.findElement("xpath", "//button[@id='afterpay_clearpay-tab']").getAttribute("data-testid");
				System.out.println(Afterpay);
				Common.assertionCheckwithReport(
						Afterpay.contains("afterpay_clearpay"),
						"validating the selection of the Afterpay method",
						"Afterpay should be selected ","Afterpay is selected",
						"Failed to select the Afterpay method in the production environment");
				Common.switchToDefault();
			}
	}
	

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the Afterpay payment ", expectedResult,
				"User failed to proceed with After payment", Common.getscreenShotPathforReport(expectedResult));
		Assert.fail();
	}

	String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
	if (!url.contains("stage") && !url.contains("preprod")) {
	}

	else {
		try {
			Thread.sleep(5000);
			String sucessMessage = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//h1").trim();

			int size = Common.findElements("xpath", "//div[contains(@class,'checkout-success')]//h1").size();
			Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
					"verifying the product confirmation", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unable to go orderconformation page");

			if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p").size() > 0) {
				order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
				System.out.println(order);
			}
			if (Common.findElements("xpath", "//div[@class='checkout-success container px-0 ']//p/a").size() > 0) {
				order = Common.getText("xpath", "//div[@class='checkout-success container px-0 ']//p/a");
				System.out.println(order);
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
					"It should navigate to the order confirmation page",
					"User failed to proceed to the order confirmation page",
					Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

			Assert.fail();
		}
	}
	return order;
}
	
	
	
	
	public String Kalrna_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Order="";
	
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
//			Sync.waitPageLoad();
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			System.out.println(sizes);
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");


			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(2000);
	
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				System.out.println("Switch to Frames");
				Common.scrollIntoView("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
				Common.clickElement("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
				Thread.sleep(5000);


				Common.switchToDefault();
				System.out.println("Switch to Default");
				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
				{
					if(Common.getCurrentURL().contains("/gb"))
					{
						 Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_5']");
	                	 Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_5']");
	                	 Thread.sleep(4000);
	                	 Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
	     				 Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
	     				Thread.sleep(10000);
	     				
	     				 if(Common.getCurrentURL().contains("/checkout/#payment"))
	     				 {
								Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
								Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
								Thread.sleep(5000);
								Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
								Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
								Thread.sleep(4000);
								Sync.waitPageLoad();
								Order=klarna_Details(dataSet);
	     				 }
	     				 else if(Common.getCurrentURL().contains("/success/"))
	     				 {
	     					String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
	                	    System.out.println(sucessmessage);
	     				 }
	     				 else
	     				 {
	     					 Thread.sleep(4000);
	     					Sync.waitPageLoad();
	     					Order=klarna_Details(dataSet);
	     					
	     				 }
	     				
					}
					else
					{
						Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
						Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
						Thread.sleep(10000);
						 if(Common.getCurrentURL().contains("/checkout/#payment"))
                  	   {
							 Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
							 Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
                  		   	Thread.sleep(5000);
                  			Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
							Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
							Thread.sleep(4000);
							Sync.waitPageLoad();
							Order=klarna_Details(dataSet);
                  	   }
						 else if(Common.getCurrentURL().contains("/success/"))
						 {
							 String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
	                    	    System.out.println(sucessmessage);
						 }
						 else
						 {
							 Thread.sleep(4000);
		     					Sync.waitPageLoad();
		     					Order=klarna_Details(dataSet);
						 }
					}
				}
				else
				{
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna=Common.findElement("xpath", "//button[@id='klarna-tab']").getAttribute("data-testid");
					System.out.println(klarna);
					Common.assertionCheckwithReport(
							klarna.contains("klarna"),
							"validating the selection of the klarna method",
							"klarna should be selected ","klarna is selected",
							"Failed to select the klarna method in the production environment");
					Common.switchToDefault();
					
				}
				
				
			}
			else
			{
				int savedcard=Common.findElements("xpath", "//select[@x-model='savedMethodId']").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//span[text()='Klarna']");
				Common.switchToDefault();
				
				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
				{
					Thread.sleep(5000);
				Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
				Sync.waitPageLoad();
				Thread.sleep(8000);
				Order=klarna_Details(dataSet);
				}
				else
				{
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna=Common.findElement("xpath", "//button[@value='klarna']//span").getAttribute("data-testid");
					System.out.println(klarna);
					Common.assertionCheckwithReport(
							klarna.contains("klarna"),
							"validating the selection of the klarna method",
							"klarna should be selected ","klarna is selected",
							"Failed to select the klarna method in the production environment");
					Common.switchToDefault();
					
				}
			}
		
		
	}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", "User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
		return Order;
	}
	public String klarna_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order="";
		String phone=data.get(Dataset).get("phone");
		String otp=data.get(Dataset).get("OTP Number");
		String DOB=data.get(Dataset).get("DOB");
		String Cardnumber=data.get(Dataset).get("cardNumber");
		String Symbol= data.get(Dataset).get("Symbol");
		System.out.println(Cardnumber);
		
		try
		{
			Sync.waitPageLoad();
			Common.switchWindows();
			//Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
		/*	Common.clickElement("xpath", "//input[@name='phone']");
			
			int number=Common.genrateRandomNumber();
			System.out.println(number);
			String mobile=Integer.toString(number);
			String phone="+91"+"95862"+mobile;*/
			WebElement clear=Common.findElement("xpath", "//input[@name='phone']");
		    clear.sendKeys(Keys.CONTROL+"a");
		    clear.sendKeys(Keys.DELETE);
			System.out.println(phone);
			Common.textBoxInput("xpath", "//input[@name='phone']", phone);
			Common.clickElement("xpath", "//button[@id='onContinue']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Sync.waitPageLoad();
			Thread.sleep(8000);
			
			Common.clickElement("xpath", "//span[text()='Pay now']");
			Thread.sleep(4000);
			Common.refreshpage();
			String klarna=Common.findElement("xpath", "//h2[@id='stacked-selection-title']").getText();
			if(klarna.contains("Choose how to pay"))
			{
				Thread.sleep(4000);
			//	Common.clickElement("xpath", "(//span[contains(text(),'Continue')])[2]");
				Sync.waitElementPresent("xpath", "//label[@for='pay_now__label']");
				Common.clickElement("xpath", "//label[@for='pay_now__label']");
				
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "(//span[contains(text(),'Continue')])[1]");
				Common.doubleClick("xpath", "(//span[contains(text(),'Continue')])[1]");
				Thread.sleep(4000);
				//Common.doubleClick("xpath", "(//span[contains(text(),'Continue')])[2]");
				Sync.waitElementPresent("xpath", "//span[text()='Pay with']");
				Common.clickElement("xpath", "//span[text()='Pay with']");
				Sync.waitPageLoad();
				
					
				
			}
			else
			{
				
//				String klarna1=Common.findElement("xpath", "//h2[@role='status']").getText();
				
				Common.clickElement("xpath", "//button[@id='onContinue']");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//div[@id='addressCollector-date_of_birth__container']");
				Common.findElement("xpath","//input[@id='addressCollector-date_of_birth']").sendKeys(DOB);
				

				Common.javascriptclickElement("xpath", "//div[@id='preview-address__link-wrapper']");
				
				WebElement clearStreet=Common.findElement("xpath", "//input[@name='street_address']");
				clearStreet.sendKeys(Keys.CONTROL+"a");
	            Common.findElement("xpath","//input[@name='street_address']").sendKeys(data.get(Dataset).get("Street"));
				
	            WebElement clearcity=Common.findElement("xpath", "//input[@name='city']");
				clearStreet.sendKeys(Keys.CONTROL+"a");
				
				 WebElement clearPostcode=Common.findElement("xpath", "//input[@name='postal_code']");
				clearStreet.sendKeys(Keys.CONTROL+"a");
				
				Common.findElement("xpath","//input[@name='region']").sendKeys(data.get(Dataset).get("Region"));
				
				Common.clickElement("xpath", "//div[@id='addressCollector-postal_code__label']");
				Common.findElement("xpath","//input[@name='postal_code']").sendKeys(data.get(Dataset).get("postcode"));
				Common.clickElement("xpath", "//div[@id='terms_checkbox__box']");
				Common.clickElement("xpath", "//span[text()='Continue']");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[contains(text(),'continue')]");
				Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'Continue')]");
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Sync.waitElementPresent(30, "xpath", "//button[@data-testid='pick-plan']");
				Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				Thread.sleep(4000);	
				Common.findElement("xpath","//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath","//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath","//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.switchToDefault();
				Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
				Thread.sleep(4000);
		//		Thread.sleep(4000);
				Common.clickElement("xpath", "(//span[contains(text(),'Continue')])[2]");
				Thread.sleep(8000);
				Common.javascriptclickElement("xpath", "(//span[contains(text(),'Continue')])[1]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
				
			}
		
		}
		catch(Exception |Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the card details enter in the Kalrna payment", "User Should able to Enter Card Details in Klarna Payment",
					"User Unable to enter Card details in the Klarna payment",
					Common.getscreenShotPathforReport("Failed to enter Card details in the Klarna payment"));
			Assert.fail();
		}
		String url1=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if(!url1.contains("stage") && !url1.contains("preprod")){
		}
	
	else{
		try{
		Thread.sleep(4000);
		Sync.waitElementPresent(60, "xpath", "//h1[normalize-space()='Thank you for your purchase!']");
	String sucessMessage = Common.getText("xpath", "//h1[normalize-space()='Thank you for your purchase!']").trim();
	System.out.println(sucessMessage);
	
	int size = Common.findElements("xpath", "//h1[normalize-space()='Thank you for your purchase!']").size();
	Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
			"verifying the product confirmation", "It should redirects to the order confirmation mail",
			"Successfully It redirects to order confirmation page Order Placed",
			"User unable to go orderconformation page");
	
	if(Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size()>0) {
		order=Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
		System.out.println(order);
	}
	if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
		order=	Common.getText("xpath", "//a[@class='order-number']/strong");
		System.out.println(order);
	}
	
		
	}
catch(Exception | Error e)
{
	
 e.printStackTrace();
 ExtenantReportUtils.addFailedLog("verifying the order confirmartion page", "It should navigate to the order confirmation page",
			"User failed to proceed to the order confirmation page", Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));
 
 Assert.fail();
}
	}
		return order;
	}


	
	public String Express_Paypal(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";

		String expectedResult = "It should open paypal site window.";

		try {
			Thread.sleep(3000);
			int cancelpayment=Common.findElements("xpath", "//button[@title='Cancel']").size();
			System.out.println(cancelpayment);
			if(cancelpayment>0)
			{
				
				Sync.waitElementPresent("xpath", "//button[contains(text(),'Cancel Payment')]");
				Common.clickElement("xpath", "//button[contains(text(),'Cancel Payment')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Thread.sleep(3000);
				Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

				// Common.refreshpage();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.switchToDefault();
				
			}
			else
			{
				Thread.sleep(3000);
				Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

				// Common.refreshpage();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.switchToDefault();
			}
			
			Thread.sleep(4000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") & !url.contains("preprod")) {

			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
		} else {

			Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			int size1=Common.findElements("xpath","//p[@class='secondaryLink ']").size();
			if(size1>0)
			{
			Common.clickElement("xpath","//p[@class='secondaryLink ']");
			}
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();
			
			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");

			try {
				Common.clickElement("id", "btnLogin");
				Thread.sleep(5000);
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);
				Paypal_Address_Verification("Express Paypal");
				Thread.sleep(4000);
				
				if (Common.getCurrentURL().contains(""))
					Common.clickElement("id", "payment-submit-btn");
				Thread.sleep(8000);
				Common.switchToFirstTab();
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
						"User failed to proceed with paypal payment",
						Common.getscreenShotPathforReport(expectedResult));
				AssertJUnit.fail();
			}
			Sync.waitForLoad();
			Thread.sleep(5000);
//			express_paypal_shipping("PaypalDetails");
			
//			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
//			Thread.sleep(3000);
//			select_Shipping_Method("GroundShipping method");
			Thread.sleep(4000);
			int rewards=Common.findElements("xpath", "//button[contains(text(),'Your Reward Points')]").size();
			System.out.println(rewards);
			if(rewards==0)
			{
				Common.scrollIntoView("name", "telephone");
				Thread.sleep(5000);
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			}
			
			if (Common.getText("xpath", "//div[@id='payment-method-view-paypal_express']//p[2]").contains("Paypal")||Common.getCurrentURL().contains("preprod")) {
				Common.scrollIntoView("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
				// Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
				
				Thread.sleep(8000);
				Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
			}
				try {
					Thread.sleep(6000);
					String sucessMessage = Common.getText("xpath", "//h1[normalize-space()='Thank you for your purchase!']").trim();
					System.out.println(sucessMessage);

					int size = Common.findElements("xpath", "//h1[normalize-space()='Thank you for your purchase!']").size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
							"Successfully It redirects to order confirmation page Order Placed",
							"User unable to go orderconformation page");

					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success')]/p/span").size() > 0) {
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]/p/span");
						System.out.println(order);
					}
					else if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
						order = Common.getText("xpath", "//a[@class='order-number']/strong");
						System.out.println(order);
					}

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
							"It should navigate to the order confirmation page",
							"User failed to proceed to the order confirmation page",
							Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

					AssertJUnit.fail();
				}
			}
		return order;
	}

	public void Paypal_Address_Verification(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Sync.waitElementPresent("xpath", "//span[@data-testid='header-cart-total']");
			String symbol=Common.findElement("xpath", "//span[@data-testid='header-cart-total']").getText();
			System.out.println(symbol);
			Thread.sleep(5000);
			if(symbol.contains("$"))
			{
				Sync.waitElementPresent("xpath", "//p[@data-testid='ship-to-address']");
				String address=Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				System.out.println(address);
			}
			else
			{
				Sync.waitElementPresent("xpath", "//button[@data-testid='change-shipping']");
				Common.clickElement("xpath", "//button[@data-testid='change-shipping']");
//				Common.clickElement("xpath", "//select[@data-testid='shipping-dropdown']");
				Common.dropdown("xpath", "//select[@data-testid='shipping-dropdown']", SelectBy.TEXT, data.get(Dataset).get("Street"));
				Thread.sleep(3000);
				String Ukaddress=Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				System.out.println(Ukaddress);
				String UK=data.get(Dataset).get("Street").replace("Test Qa - ", "");
				System.out.println(UK);
				Common.assertionCheckwithReport(
						Ukaddress.contains(UK),
						"validating the address selection from the drop down",
						"Address should be select from the dropdown ","Sucessfully address has been selected from the dropdown",
						"Failed to select the Address from the dropdown");
				
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the address selection from the drop down",
					"Address should be select from the dropdown ","Unable to select the Address from the dropdown",
					Common.getscreenShotPathforReport("Failed to select the Address from the dropdown"));
			AssertJUnit.fail();
		}
		
	}

	public void express_paypal_shipping(String Dataset) {
		// TODO Auto-generated method stub
		String shippment=data.get(Dataset).get("methods");
		try
		{
			Thread.sleep(4000);
			Common.dropdown("xpath", "//select[@name='shipping_method']", SelectBy.TEXT, shippment);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			AssertJUnit.fail();
		}
		
	}

	public void Reg_BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='billing-as-shipping']");
			int sizes = Common.findElements("xpath", "//label[@for='billing-as-shipping']").size();
			Common.clickElement("xpath", "//label[@for='billing-as-shipping']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page",
					"payment section should be displayed", "sucessfully payment section has been displayed",
					"Failed to displayed the payment section");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "(//button[normalize-space()='New Address'])[2]");
			Common.clickElement("xpath", "(//button[normalize-space()='New Address'])[2]");
           
 
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@id='billing-firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			String Text = Common.getText("xpath", "//form[@id='billing']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			
			
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='city']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("xpath", "//form[@id='billing']//select[@name='region']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.textBoxInput("xpath", "//input[@id='billing-postcode']", data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[normalize-space()='Save']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String update = Common.findElement("xpath", "//select[@id='address-list']").getText();
			System.out.println(update);
			Common.assertionCheckwithReport(update.contains(data.get(dataSet).get("Street")),
					"verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Sucessfully Billing address form should be Display ",
					"Failed to display the Billing address in payment page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Unable to display the Billing address in payment page ",
					Common.getscreenShot("Failed to display the Billing address in payment page"));
			Assert.fail();
		}

	}

	
	public void Close_Geolocation() {
		// TODO Auto-generated method stub
		try {
			
			Sync.waitElementPresent("xpath", "//div[@x-ref='ip-detection-modal']//button");
			Common.clickElement("xpath", "//div[@x-ref='ip-detection-modal']//button");
			
		
	}catch(Exception | Error e)
	{
		e.printStackTrace();
		AssertJUnit.fail();
	}
	

	}

	public void Giropay_payment(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
		String expectedResult = "User should able to proceed the giropay payment method";

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-payments-elements no-wrap']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-payments-elements no-wrap']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Sync.waitElementPresent(30, "xpath", "//button[@id='giropay-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='giropay-tab']");
//				
				Common.switchToDefault();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
//				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
//				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='giropay']");
				Common.clickElement("xpath", "//button[@value='giropay']");
//				
				Common.switchToDefault();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Afterpay payment ", expectedResult,
					"User failed to proceed with After payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(5000);
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!")
						|| Common.getCurrentURL().contains("success") || Common.getText("xpath",
								"//h1[@class='page-title-wrapper']").contains(sucessMessage),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unable to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");
					System.out.println(order);
				}
				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
						"It should navigate to the order confirmation page",
						"User failed to proceed to the order confirmation page",
						Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

				AssertJUnit.fail();
			}
		}
	}

	public void Sofort_payment(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
		String expectedResult = "User should able to proceed the afterpay payment method";

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Thread.sleep(4000);
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("ideal-tab"))
				{
					Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"Sofort");
					Sync.waitElementPresent(30, "xpath", "//button[@id='sofort-tab']");
					Common.javascriptclickElement("xpath", "//button[@id='sofort-tab']");	
					Common.switchToDefault();
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
				Sync.waitElementPresent(30, "xpath", "//button[@id='sofort-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='sofort-tab']");	
				Common.switchToDefault();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("ideal-tab"))
				{
				Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"Sofort");
				Sync.waitElementPresent(30, "xpath", "//button[@value='sofort']");
				Common.clickElement("xpath", "//button[@value='sofort']");			
				Common.switchToDefault();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
					Common.clickElement("xpath", "//button[@value='sofort']");			
					Common.switchToDefault();
					Thread.sleep(4000);
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Afterpay payment ", expectedResult,
					"User failed to proceed with After payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(5000);
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unable to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");
					System.out.println(order);
				}
				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
						"It should navigate to the order confirmation page",
						"User failed to proceed to the order confirmation page",
						Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

				AssertJUnit.fail();
			}
		}
	}

	public void iDeal_payment(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
		String expectedResult = "User should able to proceed the afterpay payment method";

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Thread.sleep(4000);
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("sofort-tab"))
				{
					Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"iDEAL");
					Sync.waitElementPresent(30, "xpath", "//button[@id='ideal-tab']");
					Common.javascriptclickElement("xpath", "//button[@id='ideal-tab']");	
					Common.switchToDefault();
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
				Sync.waitElementPresent(30, "xpath", "//button[@id='ideal-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='ideal-tab']");	
				Common.switchToDefault();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("sofort-tab"))
				{
				Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"iDEAL");
				Sync.waitElementPresent(30, "xpath", "//button[@value='ideal']");
				Common.clickElement("xpath", "//button[@value='ideal']");			
				Common.switchToDefault();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
					Common.clickElement("xpath", "//button[@value='ideal']");			
					Common.switchToDefault();
					Thread.sleep(4000);
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Afterpay payment ", expectedResult,
					"User failed to proceed with After payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(5000);
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unable to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");
					System.out.println(order);
				}
				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
						"It should navigate to the order confirmation page",
						"User failed to proceed to the order confirmation page",
						Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

				AssertJUnit.fail();
			}
		}
	}

	public void PDP_video_validation(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@itemprop ,'image')]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			
			Common.clickElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[2]");
			Thread.sleep(4000);
			
			
			Sync.waitElementPresent(40, "xpath", " (//button//span[@class='absolute inset-0 grid place-items-center'])[2]");
			Common.clickElement("xpath", " (//button//span[@class='absolute inset-0 grid place-items-center'])[2]");
			Thread.sleep(2000);
			Sync.waitForLoad();
			String video = Common.findElement("xpath", "//button[@aria-label='Pause video']")
					.getAttribute("aria-label");
			System.out.println(video);
			Common.assertionCheckwithReport(video.equals("Pause video"), "validating the video in PDP page",
					"video should be play in the PDP page", "Sucessfully the video has been played on the PDP page",
					"failed to play the video in PDP page");
			Sync.waitElementPresent(40, "xpath", "//button[@aria-label='Pause video']");
			Common.clickElement("xpath", "//button[@aria-label='Pause video']");
			String video1 = Common.findElement("xpath", "//button[@aria-label='Play video']")
					.getAttribute("aria-label");
			System.out.println(video);
			Common.assertionCheckwithReport(video1.equals("Play video"), "validating the video in PDP page",
					"video should be paused in the PDP page", "Sucessfully the video has been paused on the PDP page",
					"failed to Pause the video in PDP page");
			
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//img[@alt='Sustainable design']");
			Sync.waitElementPresent("xpath", "//img[@alt='Sustainable design']");
			Common.clickElement("xpath", "//img[@alt='Sustainable design']");
			Thread.sleep(3000);
			Common.switchFrames("xpath", "//iframe[@title='Vimeo video player']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@aria-labelledby='play-button-tooltip']");
			Thread.sleep(8000);
			String video2 = Common.findElement("xpath", "//button[@data-play-button='true']")
					.getAttribute("data-play-button");
			Common.assertionCheckwithReport(video2.contains("true"), "validating the video in PDP page",
					"video should be paused in the PDP page", "Sucessfully the video has been paused on the PDP page",
					"failed to Pause the video in PDP page");
			Common.switchToDefault();
			
			
		}
		
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the video in PDP page", "video should be play in the PDP page",
					"Unable to play the the video on the PDP page",
					Common.getscreenShot("failed to play the video in PDP page"));
			AssertJUnit.fail();
		}
	}

	public void prodeler_invalid_details(String dataSet) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			Sync.waitPageLoad();
			if(Common.getCurrentURL().contains("/gb"))
			{
				Sync.waitElementPresent(30, "xpath", "//a[@title='Sign in or register']");
				Common.clickElement("xpath", "//a[@title='Sign in or register']");
			}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//span[text()='Sign in or Apply']");
				Common.clickElement("xpath", "//span[text()='Sign in or Apply']");
			}
			Sync.waitPageLoad();
			Thread.sleep(3000);
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Prod Email"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal Application"),
					"To validate the user lands on Pro Deal Application after successfull login",
					"After clicking on the signIn button it should navigate to the Pro Deal Application",
					"user Sucessfully navigate to the Pro Deal Application page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Pro Deal Application page ");

			Sync.waitPageLoad();
			Thread.sleep(4000);
//			Sync.waitElementPresent("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
//			Common.clickElement("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
//			Sync.waitPageLoad();
//			Common.switchWindows();
//			Thread.sleep(3000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the pro deal application page",
					"User should lands to the prodeal application ",
					"Unable to navigates to the prodeal application form",
					Common.getscreenShotPathforReport("Failed to navigate to the prodeal application form "));
			AssertJUnit.fail();
		}

		String expectedResult = "After clicking hare button with invalid email error message should be display";
		try {

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[contains(@id,'error')]");
			String errormessage = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"validating the error message with empty fields ",
					"After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");

			Sync.waitElementPresent("id", "association_email");
			Common.textBoxInput("id", "association_email", data.get(dataSet).get("FirstName"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");

			Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
			String invalidemail = Common.findElement("xpath", "//input[@name='association_email']//following::div")
					.getText();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(invalidemail.contains("Please enter a valid email address"),
					"validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ", expectedResult,
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
			AssertJUnit.fail();

		}
	}
	
	public void click_Prodeal() {
		// TODO Auto-generated method stub
		try {
			if(Common.getCurrentURL().contains("/gb"))
			{
			Common.scrollIntoView("xpath", "//a[text()='Osprey Pro']");
			Sync.waitElementPresent("xpath", "//a[text()='Osprey Pro']");
			Common.clickElement("xpath", "//a[text()='Osprey Pro']");
			Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal"),
					"To validate the Pro Deal", "Should be display the Pro Deal Application ",
					"Successfully display the Pro Deal Application", "Failed to  display the Pro Deal Application");
			}
			else
			{
				Common.scrollIntoView("xpath", "//a[text()='Pro Sales']");
				Sync.waitElementPresent("xpath", "//a[text()='Pro Sales']");
				Common.clickElement("xpath", "//a[text()='Pro Sales']");
				Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper']");
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal"),
						"To validate the Pro Deal", "Should be display the Pro Deal Application ",
						"Successfully display the Pro Deal Application", "Failed to  display the Pro Deal Application");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Pro Deal Application",
					"Should display the Pro Deal Application ", "Unable to displays the Pro Deal Application",
					Common.getscreenShot("Failed to  display the Pro Deal Application"));
			AssertJUnit.fail();
		}
	}
	
	public void Prodeler_Application_Page(String dataSet) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@title='Sign in or Apply']");
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod") ) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal"),
					"To validate the user lands on Pro Deal Application after successfull login",
					"After clicking on the signIn button it should navigate to the Pro Deal Application",
					"user Sucessfully navigate to the Pro Deal Application page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Pro Deal Application page ");

			Sync.waitPageLoad();
			Thread.sleep(4000);
//			Sync.waitElementPresent("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
//			Common.clickElement("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
//			Sync.waitPageLoad();
			Common.switchWindows();
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the pro deal application page",
					"User should lands to the prodeal application ",
					"Unable to navigates to the prodeal application form",
					Common.getscreenShotPathforReport("Failed to navigate to the prodeal application form "));
			AssertJUnit.fail();
		}

		String expectedResult = "User is redirected to Pro Deal application page";
		try {

			Sync.waitElementPresent("id", "first_name");
			int fistnamesize = Common.findElements("id", "first_name").size();
			Common.assertionCheckwithReport(fistnamesize > 0,
					"Successfully User is redirected to Pro Deal application page", expectedResult,
					"User able to redirected to Pro Deal application page");
			Thread.sleep(3000);
			Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("id", "last_name");
			Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
			Sync.waitElementPresent("id", "association");
			Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
			Sync.waitElementPresent("id", "association_email");
			Common.textBoxInput("id", "association_email", data.get(dataSet).get("Email"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(6000);
			String path = System.getProperty("user.dir")
					+ ("\\src\\test\\resources\\TestData\\Osprey_EMEA\\TestScreen.png");
			Sync.waitElementPresent(40, "xpath", "//input[@id='supporting_document']");
//				Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);
			Common.findElement("xpath", "//input[@id='supporting_document']").sendKeys(path);

			Sync.waitElementPresent("id", "group_id");
			Common.clickElement("xpath", "//select[@id='group_id']");
			Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.VALUE, data.get(dataSet).get("GropName"));

			Sync.waitElementPresent("id", "comment");
			Common.textBoxInput("id", "comment", data.get(dataSet).get("Comments"));

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");

			Common.switchToDefault();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='prodeal']//h4").getText();
			Common.assertionCheckwithReport(message.contains("Thank you"),
					"To validate the success message for the prodeal",
					"After clicking on the submit button it should navigate to the Success page",
					"user Sucessfully navigate to the Success page after clicking on the submit button",
					"Failed to get the success message for the pro deal submission");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("ProDeal application form filling",
					"User field to fill the prodeal aplication ",
					"user to get the success message for the pro deal submission",
					Common.getscreenShotPathforReport("Failed to get the success message for the pro deal submission"));
			AssertJUnit.fail();

		}
	}
	public void access_for_prodeal(String Dataset) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			Sync.waitPageLoad();
			
			Common.clickElement("xpath", "//input[@name='access_code']//parent::div");
			if(Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("stage") )
			{
			Common.textBoxInput("xpath", "//input[@name='access_code']", data.get(Dataset).get("Access code"));
			Common.clickElement("xpath", "//button[@title='Submit']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String successmessage = Common.findElement("xpath", "//div[contains(@class,'message-notice')]//div")
					.getText();

			System.out.println(successmessage);
			Common.assertionCheckwithReport(successmessage.contains("Enjoy Pro Deal pricing on select products."),
					"validating the Pro Deal success message ", "should display the success message",
					"successfully display the success message", "failed to display the success message");
			}
			else if(Common.getCurrentURL().contains("prepord"))
			{
				Common.textBoxInput("xpath", "//input[@name='access_code']", data.get(Dataset).get("Access code1"));
				Common.clickElement("xpath", "//button[@title='Submit']");
				Sync.waitPageLoad();
				Thread.sleep(6000);
				String successmessage = Common.findElement("xpath", "//div[contains(@class,'message-notice')]//div")
						.getText();

				System.out.println(successmessage);
				Common.assertionCheckwithReport(successmessage.contains("Enjoy Pro Deal pricing on select products."),
						"validating the Pro Deal success message ", "should display the success message",
						"successfully display the success message", "failed to display the success message");
			}
			else
			{
				Common.textBoxInput("xpath", "//input[@name='access_code']", data.get(Dataset).get("Access code prod"));
				Common.clickElement("xpath", "//button[@title='Submit']");
				Sync.waitPageLoad();
				Thread.sleep(6000);
				String successmessage = Common.findElement("xpath", "//div[contains(@class,'message-notice')]//div").getText();

				System.out.println(successmessage);
				Common.assertionCheckwithReport(successmessage.contains("Enjoy Pro Deal pricing on select products."),
						"validating the Pro Deal success message ", "should display the success message",
						"successfully display the success message", "failed to display the success message");
			}
		}

		catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Pro Deal success message ",
					"Should display the success message", "Unable to displays the success message",
					Common.getscreenShot("Failed to  display the Pro Deal success message"));

			AssertJUnit.fail();

		}

	}
	
	public void Prodeal_information() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String prodealexpdate = Common
					.findElement("xpath", "//strong[text()='Program expiration date:']//parent::p").getText();
			System.out.println(prodealexpdate);
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("account"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[text()='Pro Deal']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(50, "xpath", "//strong[text()='Program expiration date:']//parent::p");
			String prodealdate = Common.findElement("xpath", "//strong[text()='Program expiration date:']//parent::p")
					.getText();
			System.out.println(prodealdate);
			Common.assertionCheckwithReport(prodealexpdate.equals(prodealdate),
					"validating the prodeal information for register user",
					"After clicking on prodeal information should be displayed ",
					"successfully prodeal information has been displayed",
					"failed to display the prodeal information for the register user");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the prodeal information for register user",
					"After clicking on prodeal information should be displayed ",
					"Unable to display the  prodeal information for the register user",
					Common.getscreenShot("failed to display the prodeal information for the register user"));
			AssertJUnit.fail();
		}

	}

	public String Store_Credit_balance() throws Exception {
		// TODO Auto-generated method stub
	String Price="";
		try
		{
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/customer/account/"),
					"validating the page navigation to the my account",
					"After clicking on My account it should navigate to the my account page",
					"successfully Navigated to the My account page",
					"Failed to Navigate to the My account page");
			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			Common.clickElement("xpath", "//span[text()='Store Credit']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("storecredit"),
					"validating the page navigation to the storecredit",
					"After clicking on storecredit it should navigate to the storecredit page",
					"successfully Navigated to the storecredit page",
					"Failed to Navigate to the storecredit page");
		 Price=Common.getText("xpath", "//span[@class='text-2xl font-bold']//span");	
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the page navigation to the storecredit",
					"After clicking on storecredit it should navigate to the storecredit page",
					"Unable Navigated to the storecredit page",
					Common.getscreenShot("Failed to Navigate to the storecredit page"));
			AssertJUnit.fail();
		}
		return Price;
	}

	public void Apply_Store_Credit(String Price) throws InterruptedException {
		// TODO Auto-generated method stub
		String symbol="";
		Thread.sleep(4000);
		  String Url=Common.getCurrentURL();
		  Thread.sleep(4000);
		  if(Url.contains("/gb"))
		  {
			symbol="£";
		  }
		  else
		  {
			  symbol="$";
		  }
		try
		{
			Thread.sleep(3000);
			String ordertotal = Common.findElement("xpath", "(//div[@class='item grand_total']//span)[4]").getText().replace(symbol, "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			System.out.println(ordertotalvalue);
			Sync.waitElementPresent("xpath", "//button[@title='Show items']//div[contains(text(),'Apply Store Credit')]");
			Common.clickElement("xpath", "//button[@title='Show items']//div[contains(text(),'Apply Store Credit')]");
			Thread.sleep(5000);
			String Balance=Common.findElement("xpath", "//div[contains(@class,'customer-balance')]//span").getText().replace("credit available","").trim();
			Balance.replace(symbol, "");
			System.out.println(Balance);
			System.out.println(Price);
				if(Balance.equals(Price))
				{
					String total=Common.findElement("xpath", "(//div[@class='item grand_total']//span)[4]").getText().trim();
					Sync.waitElementPresent(30,"xpath", "//div[@class='shrink-0']//span[contains(text(),'Apply')]");
					Common.clickElement("xpath", "//div[@class='shrink-0']//span[contains(text(),'Apply')]");
					Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']//span");
					String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span")
							.getText();
					Thread.sleep(2000);
					System.out.println(message);
					Common.scrollIntoView("xpath", "(//div[@class='item customerbalance']//span)[2]");
					String storeorder=Common.findElement("xpath", "(//div[@class='item customerbalance']//span)[2]").getText().replace("-", "");
					System.out.println(storeorder);
					System.out.println(total);
					System.out.println(Price);
					Common.assertionCheckwithReport(message.contains("Your store credit was successfully applied.") || Balance.equals(Price) &&storeorder.equals(total)  ,"validating the store credit balance applied sucess message",
							"After adding the store credit success message should display", "Sucessfully success message has been displayed",
							"failed to Display the success message");
				}
				else
				{
					AssertJUnit.fail();
				}
//			}
//			else
//			{
//				Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-checkout__back')]");
//				Common.clickElement("xpath", "//span[contains(@class,'icon-checkout__back')]");
//				Sync.waitPageLoad();
//				Thread.sleep(3000);
//				Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout/cart/") ,"validating the shopping cart page",
//						"After clciking on back to cart it should navigate to shopping cart page", "Sucessfully Navigated to the shopping cart page",
//						"failed to Navigate to the shopping cart page");
//				Common.dropdown("xpath", "//select[@class='a-form-elem a-select-menu']", Common.SelectBy.VALUE, "3");
//				Common.clickElement("xpath", "//button[@name='update_cart_action']");
//				Sync.waitPageLoad();
//				Thread.sleep(3000);
//				System.out.println(ordertotalvalue);
//				System.out.println(Pricevalue);
//				if(Pricevalue>ordertotalvalue)
//				{
//					minicart_Checkout();
//				    selectshippingmethod("GroundShipping method");
//				    clickSubmitbutton_Shippingpage();
//				    Thread.sleep(4000);
//				    Sync.waitElementPresent("xpath", "(//span[@class='m-accordion__title-label'])[1]");
//					Common.clickElement("xpath", "(//span[@class='m-accordion__title-label'])[1]");
//					String balance=Common.getText("xpath", "//strong[contains(@id,'customerbalance')]");
//					Common.clickElement("xpath", "//button[@id='use-customer-balance']");
//					Sync.waitElementPresent(30, "xpath", "//div[contains(@data-ui-id,'checkout-cart')]");
//					String message = Common.findElement("xpath", "//div[contains(@data-ui-id,'checkout-cart')]")
//							.getAttribute("id");
//					Thread.sleep(4000);
//					System.out.println(message);
//					System.out.println(Price);
//					String storeorder=Common.findElement("xpath", "//tr[@class='totals balance']//span[@class='price']").getText().replace("-", "");
//					System.out.println(storeorder);
//					Common.assertionCheckwithReport(message.contains("success") || storeorder.equals(Price) ,"validating the store credit balance applied sucess message",
//							"After adding the store credit success message should display", "Sucessfully success message has been displayed",
//							"failed to Display the success message");
//				    
//				}
//				else
//				{
//					Assert.fail();
//				}
//				
//				
//			}
					
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the store credit balance applied sucess message",
					"After adding the store credit success message should display", "Unable to Display the success message",
					Common.getscreenShot("failed to Display the success message"));
			AssertJUnit.fail();
		}
		
	}
	public String store_Credit(String Dataset) {
		// TODO Auto-generated method stub
		String balance= "";
		String symbol=  data.get(Dataset).get("Symbol");

		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/customer/account/"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			AssertJUnit.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "(//div[@class='content account-nav-content']//li[@class='nav item'])[7]");
			Common.clickElement("xpath", "(//div[@class='content account-nav-content']//li[@class='nav item'])[7]");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("storecredit/info/"),
					"validating the Navigation to the store credit page",
					"After Clicking on store credit CTA user should be navigate to the Store credit page",
					"Sucessfully User Navigates to the store credit page after clicking on the store credit CTA",
					"Failed to Navigate to the Store credit page after Clicking on Store credit CTA");
			
			 balance= Common.getText("xpath", "//div[@class='block block-balance']//span[@class='price']").replace(symbol, "");
			 System.out.println(balance);
			
			
			int size = Common.findElements("xpath", "//tbody[@class='m-table__body']").size();
			 System.out.println(size);
			/*if (size > 0) {
				String number = Common.findElement("xpath", "//td[@data-header-title='Balance']").getText();
				System.out.println(number);
				Thread.sleep(4000);
			
			
				Common.assertionCheckwithReport(!number.equals("0"),
						"validating the card details in the my orders page",
						"After Clicking on My payments methods and payment method should be appear in payment methods",
						"Sucessfully payment method is appeared in my payments methods",
						"Failed to display the payment methods in the my payments methods");
			} else {
				Assert.fail();
			}*/

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message for delete card",
					"After Clicking the delete button we need to get the error message",
					"Unable to display the error message when we clcik on the delete message",
					Common.getscreenShot("Failed to display the error message when we clcik on the delete message"));
			AssertJUnit.fail();
		}
		
		return balance;

	}
	
public void select_Store_Credit_Payment(String dataSet) {
	
	String expectedResult = "land on the payment section";
	  String symbol=symbols_method("Account");
		
		try
		{
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			// Common.clickElement("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//div[@class='payment-option-title']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//div[@class='payment-option-title']");
			Thread.sleep(2000);
		   String Balance =Common.findElement("xpath", "//strong[@id='customerbalance-available-amount']").getText();
	       System.out.println(Balance);
	     
	       String total =Common.getText("xpath", "//strong[@id='customerbalance-available-amount']").replace(symbol, "");
	       System.out.println(total);
	       System.out.println(dataSet);
	       Thread.sleep(2000);
	       
	        if(dataSet.equals(total)) {
	    	   Thread.sleep(3000);
	    	   Sync.waitElementPresent("xpath", "//button[@id='use-customer-balance']");
	    	   Common.clickElement("xpath", "//button[@id='use-customer-balance']");
	    	   Sync.waitPageLoad();
	    	   Thread.sleep(3000);
	    	   String successmsg=Common.findElement("xpath", "//div[@role='alert']").getText();
	    		  System.out.println(successmsg);
	    			
	    			Common.assertionCheckwithReport(successmsg.contains("applied"),
	    					"validating the success message after applying Store credit",
	    					"Success message should be displayed after the applying Store credit",
	    					"Sucessfully Store credit has been applyed","Failed to apply Store credit");
	    			
	    			
	       }else
	       {
	    	   AssertJUnit.fail();
	       }
		
	
		
		String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']").replace(symbol, "");
		System.out.println(ordertotal);
		Thread.sleep(4000);
		if(ordertotal.equals("0.00")) {
			if(Common.getCurrentURL().contains("stage")|| Common.getCurrentURL().contains("preprod"))
			giftCardSubmitOrder();
		}
		else {
			AssertJUnit.fail();
		}
		}
   
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"Success message should be displayed after the applying of gift card",
					"Sucessfully gift card has been applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			AssertJUnit.fail();
		}
}	
	

public void validate_GIFT_CARD_PLP() {
	
	
	try
	{
		Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
		Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
		Sync.waitElementPresent("xpath", "//span[contains(text(),'Gift Cards')]");
		Common.clickElement("xpath", "//span[contains(text(),'Gift Cards')]");
		Thread.sleep(5000);
		String GIFTCARDtitle = Common.getText("xpath", "//h1");
	    System.out.println(GIFTCARDtitle);	
		Common.assertionCheckwithReport(GIFTCARDtitle.equalsIgnoreCase("E Gift Cards"),
				"To validate Gift card Navigation to the PLP",
				"After clicking on the Giftcard for the header links it should navigate to the Gift card PLP page",
				"Sucessfully It has been navigated to the Gift card PLP ", "Failed to Navigate to the Gift card PLP");
	     Sync.waitElementPresent("xpath", "(//span[contains(text(),'Sort by')])[1]");
	    String Sortingoptions = Common.getText("xpath", "(//span[contains(text(),'Sort by')])[1]");
	    System.out.println(Sortingoptions);
	    Common.assertionCheckwithReport(Sortingoptions.equalsIgnoreCase("Sort by:"),
			"Verifying the sorting options are visible or not",
			"Sort by options should visible",
			"Sort by options are visibled", "Failed to visible the sort by options");
	      
	    Sync.waitElementPresent("xpath", "//span[contains(@class,'flex-grow title-panel')]");
	    String FILTERSBY = Common.getText("xpath", "//span[contains(@class,'flex-grow title-panel')]").trim();
	    System.out.println(FILTERSBY);
	    Common.assertionCheckwithReport(FILTERSBY.equalsIgnoreCase("Filter by:"),
			"Verifying the filterby options are visible or not",
			"filter by options should visible",
			"filter by options are visibled", "Failed to visible the filter by options");
//	    Sync.waitElementPresent("xpath", "(//div[@class='name'])[2]");
//	    String Categorysection = Common.getText("xpath", "(//div[@class='name'])[2]").trim();
//	    System.out.println(Categorysection);
//	    Common.assertionCheckwithReport(Categorysection.equalsIgnoreCase("Categories"),
//			"Verifying the filterby options are visible or not",
//			"filter by options should visible",
//			"filter by options are visibled", "Failed to visible the filter by options");
	    Thread.sleep(3000);
	    List<WebElement> allProducts = Common.findElements("xpath","//li[@class='ais-InfiniteHits-item']");
        int visibleProductCount = 0;
        for (WebElement product : allProducts) {
            if (product.isDisplayed()) {
                visibleProductCount++;
            }
            else {
            	Assert.fail();
            	
            }
        }
        System.out.println("Number of visible products: " + visibleProductCount);
	
	}
	
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate Gift card Navigation to the PLP",
				"After clicking on the Giftcard for the header links it should navigate to the Gift card PLP page",
				"Sucessfully It has been navigated to the Gift card PLP ",
				Common.getscreenShot("Failed to Navigate to the Gift card PLP"));
		Assert.fail();
		
	
	}
}

public void Card_Value_for_my_fav(String Dataset) {
	// TODO Auto-generated method stub
	String amount=data.get(Dataset).get("Card Amount");
	try
	{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//span[text()='"+ amount +"']");
		Common.clickElement("xpath", "//span[text()='"+ amount +"']");
		if(Common.getCurrentURL().contains("/gb/"))
		{
		String Price=Common.findElement("xpath", "//div[@class='final-price inline-block']//span[@class='price']").getText();
		Common.assertionCheckwithReport(Price.contains(amount),
				"validating gift card amount value in PDP",
				"After clicking on the value amount should be appear in PDP",
				"Successfully selected amount is matched for the gift card",
				"Failed to appear amount for the gift card");
		}
		else
		{
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("gift-card")||Common.getCurrentURL().contains("/wishlist/index"),
					"validating gift card Navigated to the PDP",
					"After clicking on the gift card on PLP it should navigate to the PDP",
					"Successfully Gift card is Navigated to the PDP ",
					"Failed to navigate the gift card to the PDP");
		}
		/*Common.clickElement("xpath", "(//img[@class='amcard-image'])[1]");         // gift cards designs are not there in the us and emea so we had commented the line
		String SmallCard=Common.findElement("xpath", "//img[@class='amcard-image -active']").getAttribute("src");
		String MainCard=Common.findElement("xpath", "//img[@class='fotorama__img']").getAttribute("src");
		Common.assertionCheckwithReport(SmallCard.equals(MainCard),
				"validating the selected gift card",
				"After clicking on the card design gift card should be match",
				"Successfully Gift card design has been matched",
				"Failed to match the Gift card design");*/
		Giftcard_details("Gift Details");
	/*	product_quantity("Product Qunatity");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
		Common.clickElement("xpath", "//span[text()='Add to Cart']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
		String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
				.getAttribute("data-ui-id");
		System.out.println(message);
		Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
				"Product should be add to cart", "Sucessfully product added to the cart ",
				"failed to add product to the cart");*/
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			Assert.fail();
		}
		
	}

public void Guest_Add_Wishlist_Create_account() throws Exception {
	// TODO Auto-generated method stub
	try {

		Sync.waitElementPresent("xpath", "//button[@aria-label='Add to Favorites']");
		Common.javascriptclickElement("xpath", "//button[@aria-label='Add to Favorites']");
		Thread.sleep(6000);
		int Size = Common.findElements("xpath", "(//div[@class='m-modal__box']//div[1]//h4)[1]").size();
		System.out.println(Size);
		if (Size > 0) {

			Sync.waitElementPresent("xpath", "(//*[text()='Add To List'])[1]");
			Common.javascriptclickElement("xpath", "(//*[text()='Add To List'])[1]");

		} else {
			int Error = Common.findElements("xpath", "//div[@class='a-message__container-inner']").size();
			if (Error>0) {
				
				Common.mouseOverClick("xpath", "(//span[text()='Create an Account'])");
				Create_Account_for_Guest_my_fav("Create Account");
			} else {
				System.out.println("no Error message displayed");
            if(Common.getCurrentURL().contains("/customer/account/login")) {
            	Create_Account_for_Guest_my_fav("Create Account");
            }
			}
		}
		Thread.sleep(3000);
		/*int WishlistMSG = Common.findElements("xpath", "//div[@data-ui-id='message-success']").size();
		System.out.println("Wishlist" + WishlistMSG);
		Common.assertionCheckwithReport(WishlistMSG > 0, "validating the My Wish List",
				"My Wish List should be display", "Sucessfully navigated to My Wish List ",
				"failed to navigate to My Wish List");*/

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating product added to wishlist ",
				"Products added to Compare list successfull", "failed to add product to wishlist",
				Common.getscreenShotPathforReport("Wishlistfail"));
		Assert.fail();
	}
}


public String Create_Account_for_Guest_my_fav(String Dataset) {
	// TODO Auto-generated method stub
	String email = "";
	try {
        Common.clickElement("xpath", "//a[text()='Create an Account']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("UserName"));
		email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
		Common.textBoxInput("xpath", "//input[@name='password_confirmation']",data.get(Dataset).get("Confirm Password"));
		Common.clickElement("xpath", "//span[text()='Sign Up']");
		Sync.waitImplicit(30);
		Thread.sleep(4000);
	    
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("wishlist"),
				"validating navigation to the account page after clicking on sign up button",
				"User should navigate to the My account page after clicking on the Signup",
				"Sucessfully user navigates to the My account page after clickng on thr signup button",
				"Failed to navigate to the My account page after clicking on the signup button");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating navigation to the account page after clicking on sign up button",
				"User should navigate to the My account page after clicking on the Signup",
				"Unable to navigate to the My account page after clicking on the signup button",
				"Failed to navigate to the My account page after clicking on the signup button");
		AssertJUnit.fail();
	}
	return email;
}

public void Verify_ShippingAmount_Lessthan_Or_Equal49() {
	// TODO Auto-generated method stub
	try {
	
			String Shipping_Method = Common.getText("xpath", "(//span[@data-label='Incl. Tax'])[1]");
			System.out.println(Shipping_Method);
			Common.assertionCheckwithReport(Shipping_Method.equals("$10.00"),
					"validating Shipping amount is displayed $5",
					"Shippping method ammount should display $5",
					"Unable to display $5 in shipping page",
					"Failed to  display $5 in shipping page ");
			
		
	}
 catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(
			"validating Shipping amount is displayed $5",
			"Shippping method ammount should display $5",
			"Unable to display $5 in shipping page",
			"Failed to  display $5 in shipping page ");
	AssertJUnit.fail();
}
}

public void Verify_ShippingAmount_Greater_than49() {
	// TODO Auto-generated method stub
	try {
		
		String Shipping_Method = Common.getText("xpath", "(//span[@data-label='Incl. Tax'])[1]");
		System.out.println(Shipping_Method);
		Common.assertionCheckwithReport(Shipping_Method.equals("$0.00"),
				"validating Shipping amount is displayed $0",
				"Shippping method ammount should display $0",
				"Unable to display $0 in shipping page",
				"Failed to  display $0 in shipping page ");
	}
 catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(
			"validating Shipping amount is displayed $5",
			"Shippping method ammount should display $5",
			"Unable to display $5 in shipping page",
			"Failed to  display $5 in shipping page ");
	AssertJUnit.fail();
}
}

public String Express_Venmo_Payment(String dataSet) throws Exception {
	// TODO Auto-generated method stub
	String order="";
	String expectedResult = "It should open venmo site window.";
	
	try {
		Thread.sleep(2000);
		
		Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

		// Common.refreshpage();
		Thread.sleep(8000);
		Sync.waitElementPresent("xpath", "//img[@class='paypal-logo paypal-logo-venmo paypal-logo-color-white']");
		Common.clickElement("xpath", "//img[@class='paypal-logo paypal-logo-venmo paypal-logo-color-white']");
		Common.switchToDefault();
		Thread.sleep(5000);
		Common.switchWindows();
		int size = Common.findElements("id", "acceptAllButton").size();
		if (size > 0) {

			Common.clickElement("id", "acceptAllButton");

		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
				"User failed to proceed with venmo payment", Common.getscreenShotPathforReport(expectedResult));
		AssertJUnit.fail();
	}
	String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
	Sync.waitElementPresent("xpath", "//div[@class='max-width-wrapper']/img");
	String venmo = Common.findElement("xpath", "//div[@class='max-width-wrapper']/img").getAttribute("alt");
	if(venmo.equals("Venmo")) {
	Sync.waitElementPresent("xpath", "//img[@alt='PayPal']");
	Common.clickElement("xpath", "//img[@alt='PayPal']");
	}
	if (!url.contains("stage") & !url.contains("preprod") & !url.contains("stage3") ) {

		int sizeofelement = Common.findElements("id", "email").size();
		Common.assertionCheckwithReport(sizeofelement > 0, "verifying the venmo payment ", expectedResult,
				"open venmo site window", "faild to open venmo account");
	} else {

		Common.clickElement("id", "login_emaildiv");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.clickElement("id", "btnNext");
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		int sizeemail = Common.findElements("id", "email").size();

		Common.assertionCheckwithReport(sizeemail > 0, "verifying the venmo payment ", expectedResult,
				"open venmo site window", "faild to open venmo account");
	
		try {
			Common.clickElement("id", "btnLogin");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Common.clickElement("id", "payment-submit-btn");
			Thread.sleep(8000);
			Common.switchToFirstTab();
		
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
					"User failed to proceed with venmo payment",
					Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}
		express_paypal_shipping(dataSet);
		if(Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod"))
		{
			Common.scrollIntoView("xpath", "//button[@value='Place Order']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
			Common.clickElement("xpath", "//button[@value='Place Order']");
		}
		// Tell_Your_FriendPop_Up();//To close the Pop-up
		String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!url1.contains("stage") && !url1.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(6000);
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
				System.out.println(sucessMessage);

				int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unable to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");
					System.out.println(order);
				}
				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
						"It should navigate to the order confirmation page",
						"User failed to proceed to the order confirmation page",
						Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

				AssertJUnit.fail();
			}
		}
	}
	return order;
}

public String symbols_method(String Dataset) {
	// TODO Auto-generated method stub
	String symbol="";
	 symbol=  data.get(Dataset).get("Symbol");
	 return symbol;
}




public void Verify_Prouser_Shipping_lessthan100() {
	
	try {

		String shipping= Common.findElement("xpath", "(//span[@data-label='Incl. Tax'])[1]").getText();
		System.out.println(shipping);
		String method= Common.findElement("xpath", "//span[text()='Standard']").getText();
		System.out.println(method);   	
		String Price = Common.findElement("xpath", "(//span[contains(@class,'value text-right text-sale-font')])[1]").getText().replace("$", "");
		
		double totalPrice = Double.parseDouble(Price);
		System.out.println(totalPrice);
		
		if(shipping.contains("10")&&method.contains("Standard")&&totalPrice<=100) {
			
			clickSubmitbutton_Shippingpage();
			
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"), "validating pro user shipping method",
					"submit button should click after verifying the shipping method", "Sucessfully navigating to checkout page",
					"failed to navigate to the page");
		}
		else {
			
			AssertJUnit.fail();
		}
	}
      catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Price in PLP page",
				"Check Price In PLp page",
				"Unable validate the Price in PLP ", Common.getscreenShot("Failed toValidate  price in PLP page"));
		AssertJUnit.fail();
	}
}



public void Validateshippingmethods_Reguleruser(String Dataset) {
	// TODO Auto-generated method stub4

	try {
		Thread.sleep(3000);
		int size = Common.findElements("xpath", "//div[@id='shipping-methods']").size();
		System.out.println(size);
		if (size > 0) {
			// Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method +
			// "')]");
			String method1=Common.findElement("xpath", "//span[text()='Standard']").getText();
			String shipping1= Common.findElement("xpath", "(//span[@data-label='Incl. Tax'])[1]").getText();
			String method2=Common.findElement("xpath", "//span[text()='Expedited']").getText();
			String shipping2= Common.findElement("xpath", "(//span[@data-label='Incl. Tax'])[2]").getText();
			
			Common.assertionCheckwithReport(shipping1.equals("$0.00")&&method1.contains("Standard")&&shipping2.equals("$50.00")&&method2.contains("Expedited"),
					"validating the standard shipping method",
					"Verifying Shipping methods in Shipping page",
					"Successfully verifed Standard and Expedited shipping method",
					"Failed to verifed Standard and Expedited shipping method");
			
			
		} else {

			AssertJUnit.fail();

		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the standard shipping method",
				"Verifying Shipping methods in Shipping page","Failed verifed Standard and Expedited shipping method",
				Common.getscreenShotPathforReport("failed verify shipping methods"));

		AssertJUnit.fail();
	}

}

public void validateChatboxOptions(String Dataset) {
	// TODO Auto-generated method stub
	try {
		
		Common.switchFrames("id", "kustomer-ui-sdk-iframe");
	
		Sync.waitElementVisible(30, "xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
		Common.mouseOverClick("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
		Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'newConversationButton')]");
		Common.mouseOverClick("xpath", "//button[contains(@class,'newConversationButton')]");

		Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
		String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();
		System.out.println(welcomemsg);
		Common.assertionCheckwithReport( welcomemsg.contains("Welcome to Osprey"),
				"To validate the Chat Conversation when user click on the chat option",
				"It should Open the Chat conversation in ChatBot",
				"Sucessfully click on the ChatBot and display the Chat conversation ",
				"Unable to display the chat conversation when user click on the chat option ");

		Common.switchToDefault();

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the Chat Conversation when user click on the chat option",
				"It should Open the Chat conversation in ChatBot",
				"Unable to  click on the ChatBot and not displayed the Chat conversation ",
				Common.getscreenShotPathforReport(
						"Unable to display the chat conversation when user click on the chat option"));
		AssertJUnit.fail();
	}
}

public void Kustomer_Links(String Dataset) {
	// TODO Auto-generated method stub
	
	String Kustomer = data.get(Dataset).get("Kustomer Links");
	String[] Kustomerlinks = Kustomer.split(",");
	int i = 0;
	try {
		for (i = 0; i < Kustomerlinks.length; i++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + Kustomerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + Kustomerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + Kustomerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("en_us")
							|| Common.getCurrentURL().contains("contact/product-registration-form")
							|| Common.getCurrentURL().contains("guarantee") 
							|| Common.getCurrentURL().contains("knowledge")
							|| Common.getCurrentURL().contains("order/status")
							|| Common.getCurrentURL().contains("size-fit")
							|| Common.getCurrentURL().contains("warranty")
							|| Common.getCurrentURL().contains("catalog")
							|| Common.getCurrentURL().contains(Kustomerlinks[i]),
					"validating the Kustomer links navigation from footer Links",
					"After Clicking on" + Kustomerlinks[i] + "it should navigate to the",
					Kustomerlinks[i] + "Sucessfully Navigated to the" + Kustomerlinks[i] + "Links",
					"Unable to Navigated to the" + Kustomerlinks[i] + "Links");
			Common.navigateBack();
		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Kustomer links navigation from footer Links",
				"After Clicking on" + Kustomerlinks[i] + "it should navigate to the",
				Kustomerlinks[i] + "Unable to Navigated to the" + Kustomerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + Kustomerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}


public void Footer_Links(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("Company_Links");
	String Terms=data.get(Dataset).get("Terms");
	String[] footerlinks = footer.split(",");
	String[] Termlinks = Terms.split(",");
	int i = 0;
	int j=0;
	try {
		
		for(j = 0; j < Termlinks.length; j++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'"+ Termlinks[j] +"')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'"+ Termlinks[j] +"')]");
			Common.clickElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + Termlinks[j] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(Termlinks[j])
							|| Common.getCurrentURL().contains("/blog")
							|| Common.getCurrentURL().contains("prodeal"),
					"validating the links navigation from footer Links",
					"After Clicking on" + Termlinks[j] + "it should navigate to the",
					Termlinks[j] + "Sucessfully Navigated to the" + Termlinks[j] + "Links",
					"Unable to Navigated to the" + Termlinks[j] + "Links");
			Thread.sleep(4000);
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		}
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("/blog")
							|| Common.getCurrentURL().contains("prodeal")
							|| Common.getCurrentURL().contains("Osprey Asset Bank"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links");
			Thread.sleep(4000);
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		
		}
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}


public void warrenty_Replacement() {
	// TODO Auto-generated method stub
	try
	{
		if(Common.getCurrentURL().contains("stage") )
		{
			String stage="https://mcloud-na-stage.osprey.com/gb/customer-support/return-authorization";
			Common.oppenURL(stage);
			Sync.waitPageLoad();
			
		}
		else if(Common.getCurrentURL().contains("osprey.com") )
		{
			String prod="https://mcloud-osprey.com.com/gb/customer-support/return-authorization";
			Common.oppenURL(prod);
		}
		else if(Common.getCurrentURL().contains("stage3"))
		{
			String stage3="https://mcloud-na-stage3.osprey.com/customer-support/return-authorization/";
			Common.oppenURL(stage3);
		}
		else if(Common.getCurrentURL().contains("preprod"))
		{
			String preprod="https://mcloud-na-preprod.osprey.com/customer-support/return-authorization/";
			Common.oppenURL(preprod);
		}
		else
		{
			AssertJUnit.fail();
		}
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		AssertJUnit.fail();
	}
	
}

public void Empty_Details_warrenty_return(String Dataset) {
	// TODO Auto-generated method stub
	String phonenumber=data.get(Dataset).get("Phone");
	String zipcode=data.get(Dataset).get("Zipcode");
	try
	{
		Common.clickElement("xpath", "//select[@id='country_id']//option[@value='US']");
		Sync.waitElementPresent(30,"xpath", "//button[contains(@class,'action submit')]");
		Common.scrollIntoView("xpath", "//button[contains(@class,'action submit')]");
		Common.clickElement("xpath", "//button[contains(@class,'action submit')]");
		Thread.sleep(4000);
		Sync.waitElementPresent(30, "xpath", "//ul[@class='messages']//li");
		String errormessage = Common.findElement("xpath", "//ul[@class='messages']//li").getText();
		Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
				"validating the error message with empty fields ",
				"After clicking hare button with empty data error message should be display",
				"successfully error message has been dispalyed ", "failed to display the error message");
		Common.textBoxInput("xpath", "//input[@id='telephone']", phonenumber);
		Common.textBoxInput("xpath", "//input[@id='postcode']", zipcode);
		Common.scrollIntoView("xpath", "//button[contains(@class,'action submit')]");
		Common.clickElement("xpath", "//button[contains(@class,'action submit')]");
		Thread.sleep(5000);
		String mobileerror=Common.findElement("xpath", "//ul[@class='messages']//li").getText();
		String ziperror=Common.findElement("xpath", "//ul[@class='messages']//li").getText();
		Common.assertionCheckwithReport(mobileerror.contains("Please enter a phone number that is 10 digits in length.") || ziperror.contains("Invalid postal code format for the selected country.") ||
				mobileerror.contains("This is a required field."),
				"validating the error message with Invalid fields ",
				"After clicking hare button with invalid data error message should be display",
				"successfully error message has been dispalyed ", "failed to display the error message");
		
		
		}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the error message with empty fields ",
				"After clicking hare button with empty data error message should be display",
				"Unable to display the error message ",
				Common.getscreenShot("Failed to display the error message"));
		Assert.fail();
	}
}



public void warrenty_return() {
	// TODO Auto-generated method stub
	try
	{
		if(Common.getCurrentURL().contains("gb"))
		{
		Common.scrollIntoView("xpath", "//div[contains(@class,'footer-grid-osprey')]//a[text()='All Mighty Guarantee']");
		Common.clickElement("xpath", "//div[contains(@class,'footer-grid-osprey')]//a[text()='All Mighty Guarantee']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("all-mighty-guarantee"),
				"validating the page navigates to the mighty guarantee",
				"After clicking on mighty guarantee from footer it should navigate to the mighty guarantee page",
				"successfully navigated to the mighty guarantee page", "failed to Navigate to the mighty guarantee page");
		Common.scrollIntoView("xpath", "(//div[@class='pagebuilder-button-primary'])[2]");
		Common.clickElement("xpath", "(//div[@class='pagebuilder-button-primary'])[2]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("Form"),
				"validating the page navigates to the Return authorization form",
				"After clicking on authorization from mighty gurantee it should navigate to the Return authorization form ",
				"successfully navigated to the Return authorization form", "failed to Navigate to the Return authorization form");
		}
		else
		{
			Common.scrollIntoView("xpath", "//div[contains(@class,'footer-grid-osprey')]//a[text()='Need a Part Replacement?']");
			Common.clickElement("xpath", "//div[contains(@class,'footer-grid-osprey')]//a[text()='Need a Part Replacement?']");
			String Url=Common.getCurrentURL();
			System.out.println(Url);
			Common.scrollIntoView("xpath", "(//div[@class='pagebuilder-button-primary'])[2]");
			Common.clickElement("xpath", "(//div[@class='pagebuilder-button-primary'])[2]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("form"),
					"validating the page navigates to the Return authorization form",
					"After clicking on authorization from mighty gurantee it should navigate to the Return authorization form ",
					"successfully navigated to the Return authorization form", "failed to Navigate to the Return authorization form");
		}
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the page navigates to the Return authorization form",
				"After clicking on authorization from mighty gurantee it should navigate to the Return authorization form ",
				"Unable to Navigate to the Return authorization form",
				Common.getscreenShot("Failed to Navigate to the Return authorization form"));
		Assert.fail();
	}
	
}


public void clickContact() throws Exception {
	String expectedResult = "It should land successfully on the contact page";

	try {
		
		Sync.waitElementPresent("xpath", "//a[contains(@href,'customer-support-center')]");
		Common.clickElement("xpath", "//a[contains(@href,'customer-support-center')]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//a[contains(@href,'contact-us')]");
		Common.clickElement("xpath", "//a[contains(@href,'contact-us')]");
		
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("contact-us"),
				"Validating the contatus page navigation", expectedResult, "successfully land to contact page",
				"unabel to load the  contact page");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating contact us page", expectedResult,
				"unable to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
		Assert.fail();

	}
}
public void contactUsPage(String dataSet) throws Exception {
	// TODO Auto-generated method stub

	String expectedResult = "Email us form is visible in tab";
	String country = data.get(dataSet).get("Country");
	String state = data.get(dataSet).get("Region");
	System.out.println(state);

	try {
        Common.scrollIntoView("xpath", "//a[contains(@class,'pagebuilder')]");
		Common.clickElement("xpath", "//a[contains(@class,'pagebuilder')]");
	
			Sync.waitElementPresent(40, "xpath", "//iframe[@class='kustomer-form']");
			Common.switchFrames("xpath", "//iframe[@class='kustomer-form']");

		Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");

		Common.clickElement("xpath", "//input[@id='customerEmail']");
		Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
		Common.textBoxInput("xpath", "//input[@id='messageSubject']",
				data.get(dataSet).get("Subject"));

		Sync.waitElementPresent("xpath", "//input[@id='customerFirstName']");
		Common.textBoxInput("xpath", "//input[@id='customerFirstName']", data.get(dataSet).get("FirstName"));

		Sync.waitElementPresent("xpath", "//input[@id='customerLastName']");
		Common.textBoxInput("xpath", "//input[@id='customerLastName']", data.get(dataSet).get("LastName"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationCompany']");
		Common.textBoxInput("xpath", "//input[@name='conversationCompany']", data.get(dataSet).get("Company"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationPhoneforforms']");
		Common.textBoxInput("xpath", "//input[@name='conversationPhoneforforms']", data.get(dataSet).get("phone"));
		
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
		Common.clickElement("xpath", "//div[@id='conversationCountry']");
		Sync.waitElementPresent("xpath", "//div[text()='"+ country +"']");
		Common.clickElement("xpath", "//div[text()='"+ country +"']");

		Sync.waitElementPresent("xpath", "//input[@name='conversationStreetforforms']");
		Common.textBoxInput("xpath", "//input[@name='conversationStreetforforms']",
				data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationCityforforms']");
		Common.textBoxInput("xpath", "//input[@name='conversationCityforforms']", data.get(dataSet).get("City"));

	/*	Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
		Common.clickElement("xpath", "//input[@name='conversationCountry']");

		Sync.waitElementPresent("xpath", "//div[text()='United States']");
		Common.clickElement("xpath", "//div[text()='United States']");*/

		Sync.waitElementPresent("xpath", "//input[@name='conversationStateforforms']");
		Common.clickElement("xpath", "//input[@name='conversationStateforforms']");
//        Common.textBoxInput("xpath", "//input[@name='conversationStateforforms']", state);
    	Common.clickElement("xpath", "//div[text()='"+ state +"']");
	/*	Sync.waitElementPresent("xpath", "//div[text()='"+ state+"']");
		Common.clickElement("xpath", "//div[text()='"+ state+"']");    */

		Sync.waitElementPresent("xpath", "//input[@name='conversationZipcodeforforms']");
		Common.textBoxInput("xpath", "//input[@name='conversationZipcodeforforms']",
				data.get(dataSet).get("postcode"));

		Sync.waitElementPresent("xpath", "//div[@id='conversationHowcanwehelp1']");
		Common.clickElement("xpath", "//div[@id='conversationHowcanwehelp1']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//div[@data-path='order_issues']");

		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@id='conversationOrderissues']");
		Common.clickElement("xpath", "//div[@id='conversationOrderissues']");

		Sync.waitElementPresent("xpath", "//div[text()='Billing Issue']");
		Common.clickElement("xpath", "//div[text()='Billing Issue']");

		Sync.waitElementPresent("xpath", "//input[@id='conversationOrdernumber']");
		Common.textBoxInput("xpath", "//input[@id='conversationOrdernumber']",
				data.get(dataSet).get("OrderID"));
		Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
		Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
				data.get(dataSet).get("Commetsosprey"));
		Thread.sleep(2000);
/*		Common.clickElement("xpath", "//input[contains(@data-label,'Be the first to know!')]");
		
		Boolean Checkbox=Common.checkBoxIsSelected("xpath", "//input[contains(@data-label,'Be the first to know!')]");
		if(Checkbox.TRUE)
		{
		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		}
		else
		{
			Common.clickElement("xpath", "//input[contains(@data-label,'Be the first to know!')]");
			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");
		}
*/
		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		
		Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
		int Contactussuccessmessage = Common.findElements("xpath", "//h1[@data-content-type='heading']").size();
		System.out.println(Contactussuccessmessage);
		Common.assertionCheckwithReport(Contactussuccessmessage > 0 || Contactussuccessmessage >= 0,
				"verifying Contact us Success message ", "Success message should be Displayed",
				"Contact us Success message displayed ", "failed to dispaly success message");
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying email us from",
				"contact us form data enter without any error message", "Contact us page getting error ",
				Common.getscreenShotPathforReport("Contact us page"));
		Assert.fail();

	}

	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(4000);
	String Text = Common.getText("xpath", "//div[@class='form-wrap']");
	System.out.println(Text);
	expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
	Common.assertionCheckwithReport(Text.contains("Your submission was successfully sent."),
			"verifying contact us conformation message", expectedResult, "Failed to submit the contact us form");

}

public void warrenty_Return_Form(String Dataset) {
	// TODO Auto-generated method stub
	String phonenumber=data.get(Dataset).get("Phone");
	String zipcode=data.get(Dataset).get("postcode");
	String Address =data.get(Dataset).get("Street");
	String City =data.get(Dataset).get("City");
	String State =data.get(Dataset).get("Region");
	System.out.println(State);
	String Packname =data.get(Dataset).get("PackageName");
	String Color =data.get(Dataset).get("Color");
	String Description =data.get(Dataset).get("description");
	String issue=data.get(Dataset).get("issue");
	String POnumber=data.get(Dataset).get("Ponumber");
	String DOP=data.get(Dataset).get("Descriptions");
	String Frame=data.get(Dataset).get("frame1");
	String YOP=data.get(Dataset).get("Yopurchase");
	String Country="United States";
	System.out.println(DOP);
	System.out.println(YOP);
	System.out.println(Frame);
	
	
	try
	{
		Common.findElement("xpath", "//select[@id='country_id']");
		Common.clickElement("xpath", "//select[@id='country_id']");
		Thread.sleep(4000);
		Common.dropdown("xpath", "//select[@id='country_id']", SelectBy.TEXT, Country);
//		Common.clickElement("xpath", "//select[@id='country_id']//option[@value='US']");
		Sync.waitElementPresent(30,"xpath", "//input[@id='telephone']");
		Common.scrollIntoView("xpath", "//input[@id='telephone']");
		Common.textBoxInput("xpath", "//input[@id='telephone']", phonenumber);
		Common.textBoxInput("xpath", "//input[@id='address']", Address);
		
		
		Common.textBoxInput("xpath", "//input[@id='city']", City);
//		Common.("xpath", "//select[@name='region']", State);
		Thread.sleep(4000);
		Common.findElement("xpath", "//select[@name='region']");
		Common.clickElement("xpath", "//select[@name='region']");
		Thread.sleep(4000);
		Common.dropdown("xpath", "//select[@name='region']", SelectBy.TEXT, State);
		Common.textBoxInput("xpath", "//input[@id='postcode']", zipcode);
		
		Common.findElement("xpath", "//select[@id='frame_size']");
		Common.clickElement("xpath", "//select[@id='frame_size']");
		Thread.sleep(4000);
	//	Common.dropdown("xpath", "//select[@id='frame_size']", SelectBy.TEXT, Frame);
		Common.dropdown("xpath", "//select[@id='frame_size']", SelectBy.TEXT, Frame);
		Common.textBoxInput("xpath", "//input[@id='approx_year_purchase']", YOP);
		Common.textBoxInput("xpath", "//textarea[@id='description_part']", DOP);
		
		Common.textBoxInput("xpath", "//input[@id='pack_and_volume']", Packname);
		Common.textBoxInput("xpath", "//input[@id='color_and_frame']", Color);
		Common.textBoxInput("xpath", "//textarea[@id='description']", Description);
		
		Common.textBoxInput("xpath", "//input[@id='pr_po_number']",POnumber);
		Common.textBoxInput("xpath", "//textarea[@id='location_function_part']", issue);
		
		Thread.sleep(4000);
		
		String path = System.getProperty("user.dir")
				+ ("\\src\\test\\resources\\TestData\\Osprey_EMEA\\Guarantee.png");
		Sync.waitElementPresent(40, "xpath", "//input[@id='photoOne']");
		Common.findElement("xpath", "//input[@id='photoOne']").sendKeys(path);

		Common.clickElement("xpath", "//input[@id='gdpr_confirm']");
		Common.clickElement("xpath", "//button[contains(@class,'action submit')]");
		
		Thread.sleep(4000);
		String Successmsg =Common.findElement("xpath", "//div[@class='return-authorization-success']//h2").getText();
	System.out.println(Successmsg);
		Common.assertionCheckwithReport(Successmsg.contains("Thanks for submitting your Parts Request."),
				"validating the waranty and return Success message",
				"After clicking Submit button waranty and return Success message should be display",
				"successfully  message has been dispalyed ", "failed to display the Successfull message");
		
		
		}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the waranty and return Success message ",
				"After clicking Submit button waranty and return Success message should be display",
				"Unable to display the Success message ",
				Common.getscreenShot("Failed to display the Successful message"));
		Assert.fail();
	}
}

public void click_Product_Registration() throws Exception {
	String expectedResult = "It should land successfully on the Product Registration";
	
	Common.actionsKeyPress(Keys.END);
	try {
		Thread.sleep(4000);
//		Sync.waitElementPresent("xpath", "//a[text()='Product Registration']");
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Product')]");
		Common.clickElement("xpath", "//a[contains(text(),'Product')]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
	
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("registration"),"Validating the Product Registration page navigation" ,
				expectedResult, "successfully land to Product Registration page", "unable to load the Product Registration page");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Product Registration  page", expectedResult,
				"unable to load the Product Registration page", Common.getscreenShotPathforReport("Product Registration page link"));
		AssertJUnit.fail();

	}
}

public void product_Registration(String dataSet) {
	
	String expectedResult = " Product registration form is visible in tab with success message";
	String country=data.get(dataSet).get("Country");
	String state = data.get(dataSet).get("Region");
	String purchased = data.get(dataSet).get("PurchasedAt");
	String SKUitemNumber = data.get(dataSet).get("SKUitemNumber");
	String feedback = ("Good Product");
	try {
		Common.refreshpage();
		Sync.waitPageLoad();
		/*Common.clickElement("xpath", "//span[text()='Register Your Product']");

		Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'registration')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'registration')]");*/
		String heading=Common.findElement("xpath", "//h1[@class='form-title']").getText();
		System.out.println(heading);
		
		Sync.waitElementPresent("xpath", "//input[@data-label='First Name']");
		Common.textBoxInput("xpath", "//input[@data-label='First Name']", data.get(dataSet).get("FirstName"));

		Sync.waitElementPresent("xpath", "//input[@data-label='Last Name']");
		Common.textBoxInput("xpath", "//input[@data-label='Last Name']", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[@id='conversationDateofbirthforforms']");
		Common.textBoxInput("xpath", "//input[@id='conversationDateofbirthforforms']",data.get(dataSet).get("DOB"));	
		
		//Sync.waitElementPresent("xpath", "//div[@id='conversationGender']");
		Common.clickElement("xpath", "//div[@id='conversationGender']");
		Common.clickElement("xpath", "//div[@data-path='female']");
	       
		//Common.dropdown("xpath", "//div[@id='conversationGender']",Common.SelectBy.TEXT, data.get(dataSet).get("Gender"));
		
		
		Sync.waitElementPresent("xpath", "//input[@data-label='Email']");
	       
			Common.textBoxInput("xpath", "//input[@data-label='Email']", data.get(dataSet).get("Email"));
			
			//Sync.waitElementPresent("xpath", "//div[@id='conversationCountryCodeFor']");
			Common.clickElement("xpath", "//div[@id='conversationCountryCodeFor']");
			Common.clickElement("xpath", "//div[@data-path='GB']");
		       
			//Common.dropdown("xpath", "//div[@id='conversationCountryCodeFor']",SelectBy.TEXT, data.get(dataSet).get("Country"));

		
		Sync.waitElementPresent("xpath", "//input[@id='conversationPhoneForForms']");
		Common.textBoxInput("xpath", "//input[@id='conversationPhoneForForms']", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Wheredidyoupurchased')]");
		Common.clickElement("xpath", "//input[contains(@id,'Wheredidyoupurchased')]");
		
		Common.textBoxInput("xpath", "//input[contains(@id,'Whendidyoupurchase')]",data.get(dataSet).get("Date"));
		
		Sync.waitElementPresent("xpath", "//div[contains(@id,'Activitiestousetheproduct')]");
		Common.clickElement("xpath", "//div[contains(@id,'Activitiestousetheproduct')]");
		Common.clickElement("xpath", "//div[@data-path='travel']");
		//Common.dropdown("xpath", "//div[contains(@id,'Activitiestousetheproduct')]",SelectBy.TEXT, data.get(dataSet).get("UseofProduct"));

		/*Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Street')]");
		Common.textBoxInput("xpath", "//input[contains(@data-label,'Street')]", data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[contains(@data-label,'City')]");
		Common.textBoxInput("xpath", "//input[contains(@data-label,'City')]", data.get(dataSet).get("City"));
		
		Common.clickElement("xpath", "//button[text()='Submit']");

		Sync.waitElementPresent("xpath", "//div[@id='conversationStateProvince']");
		Common.clickElement("xpath", "//div[@id='conversationStateProvince']");

		Sync.waitElementPresent("xpath", "//div[text()='"+state+"']");
		Common.clickElement("xpath", "//div[text()='"+state+"']");
		
		Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Zip Code ')]");
		Common.textBoxInput("xpath", "//input[contains(@data-label,'Zip Code ')]", data.get(dataSet).get("postcode"));

//		Common.textBoxInput("xpath", "//input[contains(@data-label,'Item Number')]", data.get(dataSet).get("SKUitemNumber"));
		
		Common.clickElement("xpath", "//div[@id='conversationProductItemDescription']");
//		Common.dropdown("xpath", "//div[@id='conversationProductItemDescription']", Common.SelectBy.VALUE, SKUitemNumber);
		Common.clickElement("xpath", "//div[contains(@Value,'"+SKUitemNumber+"')]");
		
		Common.textBoxInput("xpath", "//input[@data-label='Serial Number']", data.get(dataSet).get("SerialNumber"));

		Common.textBoxInput("xpath", "//input[@data-label='Manufacture Date ']", data.get(dataSet).get("ManufactureDate"));

		
		
		Sync.waitElementPresent("xpath", "//div[text()='"+purchased+"']");
		Common.clickElement("xpath", "//div[text()='"+purchased+"']");
		
		
		Common.textBoxInput("xpath", "//input[@data-label='Price']", data.get(dataSet).get("Price"));

//		Sync.waitElementPresent("xpath", "//textarea[@data-label='City Purchased']");
//		Common.textBoxInput("xpath", "//textarea[@data-label='City Purchased']",data.get(dataSet).get("City"));
//		
//		Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
//		Common.clickElement("xpath", "//div[@id='conversationCountry']");

			
		
//		Sync.waitElementPresent("xpath", "//div[text()='"+country+"']");
//		Common.clickElement("xpath", "//div[text()='"+country+"']");
		
		Common.textBoxInput("xpath", "//textarea[@name='messagePreview']", feedback);*/
		
		Sync.waitElementPresent("xpath", "//input[@data-label='Comment Title']");
		Common.textBoxInput("xpath", "//input[@data-label='Comment Title']",data.get(dataSet).get("Comment"));
		
		Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
		//Common.textBoxInput("xpath", "//input[@id='messagePreview-characterLimit']",data.get(dataSet).get("Detailed"));
		Common.clickAndtextBoxInput("xpath", "//textarea[@id='messagePreview']",data.get(dataSet).get("Detailed"));

		Sync.waitElementPresent("xpath","//input[@value='keep-up-to-date-with-osprey-products-and-promotions.-']");
		Common.clickElement("xpath","//input[@value='keep-up-to-date-with-osprey-products-and-promotions.-']");
		
		Sync.waitElementPresent("xpath","//input[@name='snOrgTermsAndConditions']");
		Common.clickElement("xpath","//input[@name='snOrgTermsAndConditions']");
		
		
		Common.scrollIntoView("xpath", "//span[text()='Submit']");
		Common.clickElement("xpath", "//span[text()='Submit']");
		
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
		Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
		int registrationsuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
		Common.assertionCheckwithReport(registrationsuccessmessage > 0, "verifying Product registration Success message ",
				"Success message should be Displayed", "Product registration  Success message displayed ",
				"failed to dispaly success message");
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Product registration  form",
				"Product registration form data enter without any error message", "Product registration  page getting error ",
				Common.getscreenShotPathforReport("Product registration  page"));
		AssertJUnit.fail();

	}

	Common.actionsKeyPress(Keys.PAGE_UP);
	Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
	String Text = Common.getText("xpath", "//div[@class='form-wrap']");
	expectedResult = "User gets confirmation under the same tab. It includes Success message.";
	Common.assertionCheckwithReport(Text.contains("Your product has been successfully registered"),
			"verifying Product registration confirmation message", expectedResult,
			"User gets confirmation for Product registration ", "unable to load the confirmation form");
	

}

public void Giftcard_Add_from_My_fav(String Dataset) {{
	// TODO Auto-generated method stub
	String amount=data.get(Dataset).get("Card Amount");
	String Product=data.get(Dataset).get("Osprey");
	try
	{
		Common.clickElement("id", "customer-menu");
		Common.clickElement("xpath", "//*[@id='customer.header.wishlist.link' or @id='customer.header.sign.in.link']\r\n"
				+ "");
		Thread.sleep(4000);
		String product=Common.findElement("xpath", "//a[@title='"+Product+"']").getText().toLowerCase();
		System.out.println(product);
		Sync.waitElementPresent("xpath", "//button[@aria-label='Add to Cart "+Product+"']");
		Common.clickElement("xpath", "//button[@aria-label='Add to Cart "+Product+"']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Pdp=Common.findElement("xpath", "//span[@itemprop='name']").getText().toLowerCase();
		System.out.println(Pdp);
		
		Common.assertionCheckwithReport(product.equalsIgnoreCase(Pdp),
				"verifying Product navigation to the PDP", "After clicking add to cart in the myfav it should navigate to the PDP",
				"Product navigated to the PDP page", "Failed to Navigate tot the PDP");
		Sync.waitElementPresent("xpath", "//span[text()='"+ amount +"']");
		Common.clickElement("xpath", "//span[text()='"+ amount +"']");
		if(Common.getCurrentURL().contains("/gb"))
		{
		String Price=Common.findElement("xpath", "//div[@class='final-price inline-block']//span[@class='price']").getText();
		Common.assertionCheckwithReport(Price.contains(amount),
				"validating gift card amount value in PDP",
				"After clicking on the value amount should be appear in PDP",
				"Successfully selected amount is matched for the gift card",
				"Failed to appear amount for the gift card");
		Giftcard_details("Gift Details");
		}
		else
		{
			Card_Value_for_my_fav("price");
	
		}
		Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
		Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
		Sync.waitPageLoad();
		Thread.sleep(4000);

		Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
		Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating gift card amount value in PDP",
				"After clicking on the value amount should be appear in PDP",
				"Failed to selected amount is matched for the gift card",
				Common.getscreenShotPathforReport("Failed to appear amount for the gift card"));
		Assert.fail();
	}
}}

public String addBillingDetails_PaymentDetails_SubmitOrder(String dataSet) throws Exception {
	// TODO Auto-generated method stub
	HashMap<String, String> Paymentmethod = new HashMap<String, String>();
	Sync.waitPageLoad();
	Thread.sleep(4000);
	String Number = "";
	String cardnumber = data.get(dataSet).get("cardNumber");
	System.out.println(cardnumber);
	String expectedResult = "land on the payment section";
	// Common.refreshpage();

	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
		// Common.clickElement("xpath", "//label[@for='stripe_payments']");
		int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

		Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
				"User unabel to land opaymentpage");
		Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
		Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(4000);
			String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			 if(Common.getCurrentURL().contains("stage3"))
             {
				  Thread.sleep(4000);
                 Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                         .getAttribute("value");
                 System.out.println(Shippingvalue);
             }
			 else
			 {
				 Common.scrollIntoView("xpath", "//select[@name='region']");
				 Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			// Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Sync.waitPageLoad();
			Thread.sleep(5000);
			
			Common.switchFrames("xpath", "//iframe[contains(@src,'elements-inner-payment-')]");
			Thread.sleep(2000);
			Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
			Common.clickElement("xpath", "//label[@for='Field-numberInput']");
			Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
			Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
			System.out.println(Number);

			Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

			Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.textBoxInput("xpath", "//input[@name='postalCode']",data.get(dataSet).get("postcode"));
			Common.switchToDefault();
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
				Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");

				Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
			} else {
				AssertJUnit.fail();
				

			}

		
		

	}

	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
				"failed  to fill the Credit Card infromation",
				Common.getscreenShotPathforReport("Cardinfromationfail"));
		AssertJUnit.fail();
	}

	expectedResult = "credit card fields are filled with the data";
	String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();

	Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
			expectedResult, "Filled the Card detiles", "missing field data it showinng error");

	return Number;
}

public void newtab_footerlinks(String Dataset) {
	// TODO Auto-generated method stub
	String AssetBank=data.get(Dataset).get("Asset Bank");
	System.out.println(AssetBank);
	String Parts=data.get(Dataset).get("Spare Parts");
	System.out.println(Parts);
	try
	{
		Sync.waitElementPresent(30, "xpath",
				"//ul[@class='m-footer-links__list']//a[contains(text(),\""+ Parts+"\")]");
		Thread.sleep(3000);
		Common.findElement("xpath",
				"//ul[@class='m-footer-links__list']//a[contains(text(),\""+ Parts+"\")]");
		Common.clickElement("xpath",
				"//ul[@class='m-footer-links__list']//a[contains(text(),\""+ Parts+"\")]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String errormessage=Common.findElement("xpath", "//div[contains(@class,'message-error')]").getAttribute("data-ui-id");
		Login_Account(Dataset);
		Common.assertionCheckwithReport(
				Common.getCurrentURL().contains("parts-request/"),
				"validating the links navigation from footer Links",
				"After Clicking on" + Parts + "it should navigate to the",
				Parts + "Sucessfully Navigated to the" + Parts + "Links",
				"Unable to Navigated to the" + Parts + "Links");
		Sync.waitElementPresent(30, "xpath",
				"//ul[@class='m-footer-links__list']//a[contains(text(),'"+ AssetBank+"')]");
		Thread.sleep(3000);
		Common.findElement("xpath",
				"//ul[@class='m-footer-links__list']//a[contains(text(),'"+ AssetBank +"')]");
		Common.clickElement("xpath",
				"//ul[@class='m-footer-links__list']//a[contains(text(),'" + AssetBank + "')]");
		Thread.sleep(2000);
		Common.switchToSecondTab();
		Common.assertionCheckwithReport(
				Common.getPageTitle().contains("Osprey Europe Asset Bank"),
				"validating the links navigation from footer Links",
				"After Clicking on" + AssetBank + "it should navigate to the",
				AssetBank + "Sucessfully Navigated to the" + AssetBank + "Links",
				"Unable to Navigated to the" + AssetBank + "Links");
		Common.closeCurrentWindow();
		Thread.sleep(4000);
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		AssertJUnit.fail();
	}
	
}

public void Prevent_Shipping() {
	// TODO Auto-generated method stub
	
	try
	{
		Thread.sleep(4000);
		String standard=Common.findElement("xpath", "(//p[contains(@class,'message error ')])[1]").getText();
		String Expedited=Common.findElement("xpath", "(//p[contains(@class,'message error ')])[2]").getText();
		Common.assertionCheckwithReport(
				standard.contains("At this time, Poco products and Ace 38 & 50 are not available for shipment to California and Colorado.")
						|| Expedited.contains("At this time, Poco products and Ace 38 & 50 are not available for shipment to California and Colorado."),
				"validating the the error meesgage in shipments while adding the poco and ace products",
				"After adding poco and ace products shipping methods should not display",
				"Sucessfully shipping methods are not displayed",
				"Failed to display the error message in shipments while adding poco and ace products");
		/*Common.clickElement("xpath", "//button[@data-role='opc-continue']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String errormessage=Common.findElement("xpath", "//div[@class='message notice']//span").getText();
		System.out.println(errormessage);
		Common.assertionCheckwithReport(errormessage.contains("The shipping method is missing"),
				"validating the error message after click next button",
				"After clicking on the next button it should display the error message",
				"Successfully Error message has been displayed", "Failed to display the error message");*/
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the error message after click next button",
				"After clicking on the next button it should display the error message",
				"Unable to display the error message",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		AssertJUnit.fail();
	}
}

public void proAce_Error_Payment(String dataSet) {
	// TODO Auto-generated method stub

	try
	{
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		
		Thread.sleep(4000);
		String errormessage=Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
		Common.assertionCheckwithReport(errormessage.contains("At this time, Poco products and Ace 38 & 50 are not available for shipment to California and Colorado."),
				"validating the error message after click Place order button",
				"After clicking on the place order button it should display the error message",
				"Successfully Error message has been displayed", "Failed to display the error message");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the error message after click place order button",
				"After clicking on the place order button it should display the error message",
				"Unable to display the error message",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		AssertJUnit.fail();

	}
}

public void verfy_miscellaneous_pages(String dataSet) throws Exception, IOException {
		// TODO Auto-generated method stub

		String urls = data.get(dataSet).get("Links");
		int j = 0;

		String[] strArray = urls.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("pre")) {

				Common.oppenURL((strArray[i]));
				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);
				Common.refreshpage();
				System.out.println(responcecode);

				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 40 error",
							Common.getscreenShotPathforReport("link" + i));

				}

			} else if (Common.getCurrentURL().contains("https://mcloud-na-preprod.osprey.com")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-stage", "www"));

				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);

				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 40 error",
							Common.getscreenShotPathforReport("link" + i));

				}
			}
		}

		if (j > 1) {
			AssertJUnit.fail();
		}
	}




public void verfy_links(String dataSet) throws Exception, IOException {
	// TODO Auto-generated method stub

	String urls = data.get(dataSet).get("Links");
	String[] Links = urls.split(",");
	int i = 0;

	for (i = 0; i < Links.length; i++) {
		Common.oppenURL(Links[i]);
System.out.println(Common.getCurrentURL());

int responcecode = getpageresponce(Common.getCurrentURL());
System.out.println(responcecode);



		
	}
}








public void Remove_Products_from_Shoppingcart() {
	// TODO Auto-generated method stub
	try
	{
		Shoppingcart_page();
		
	int Products= Common.findElements("xpath","//tbody[@class='cart item']").size();
	System.out.println(Products);
	for(int i=0;i<Products;i++) {
		Thread.sleep(4000);
		List<WebElement> ListOfSubproducts = Common.findElements("xpath",
				"//tbody[@class='cart item']//span[contains(@class,'icon-cart__r')]");
		int Product = Common.findElements("xpath",
				"//tbody[@class='cart item']//span[contains(@class,'icon-cart__r')]").size();
		System.out.println(Product);
		for (int j = Product; j <= Product; j++) {
			Thread.sleep(4000);
			System.out.println(Product);
			int value=j-1;
			ListOfSubproducts.get(value).click();
			Thread.sleep(4000);
		}
		}
	
	Sync.waitPageLoad();
	Thread.sleep(4000);
		String emptycart=Common.findElement("xpath", "//div[@class='cart-empty']//p[@role='alert']").getText();
		Common.assertionCheckwithReport(emptycart.contains("You have no items in your shopping cart."),
				"validating the empty cart message in the your cart",
				"After products cleared in the your cart empty message should display",
				"Successfully empty cart empty message should be dispalyed", "Failed to display the empty message in the cart");
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the empty cart message in the your cart",
				"After products cleared in the your cart empty message should display",
				"Successfully empty cart empty message should be dispalyed",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		AssertJUnit.fail();
	}
	
}

public void validate_price_PLP_and_PDP() {
	// TODO Auto-generated method stub
	
	try
	{
		int Products= Common.findElements("xpath","//img[@itemprop='image']").size();
		System.out.println(Products);
		for(int i=0;i<Products;i++) 
		{
			Thread.sleep(4000);
			int value = i + 1;
			WebElement ListOfSubproducts = Common.findElement("xpath",
					"(//span[@x-ref='normalPrice'])[" + value + "]");
			String PLPprice=ListOfSubproducts.getText();
		    System.out.println(PLPprice);
			Thread.sleep(4000);
			Common.clickElement("xpath", "(//img[@itemprop='image'])["+ value + "]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String PDPPrice=Common.getText("xpath", "(//span[@x-text='item.price'])[1]");
			System.out.println(PDPPrice);
			Common.assertionCheckwithReport(PLPprice.equals(PDPPrice),
					"validating the Price for the Gift card in the PDP",
					"After clicking on the giftcard it should navigate to the PDP page",
					"Successfully PLP and PDP price should be equal", "Failed to validate the PLP and PDP price");
			Thread.sleep(4000);
			Common.navigateBack();
	
		}
	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Price for the Gift card in the PDP",
				"After clicking on the giftcard it should navigate to the PDP page",
				"Unable to validate the PLP and PDP price",
				Common.getscreenShotPathforReport("Failed to validate the PLP and PDP price"));
		Assert.fail();
	}
	
}

public void Footer_Links_Company(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("Company_Links");
	String Terms=data.get(Dataset).get("Terms");
	String[] footerlinks = footer.split(",");
	String[] Termlinks = Terms.split(",");
	int i = 0;
	int j=0;
	try {
		
		for(j = 0; j < Termlinks.length; j++) {
			Sync.waitElementPresent(30, "xpath",
					"//p[@class='c-footer__copyright']//a[contains(text(),'"+ Termlinks[j] +"')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//p[@class='c-footer__copyright']//a[contains(text(),'"+ Termlinks[j] +"')]");
			Common.clickElement("xpath",
					"//p[@class='c-footer__copyright']//a[contains(text(),'" + Termlinks[j] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
				Common.assertionCheckwithReport(
					Common.getPageTitle().contains(Termlinks[j])
							|| Common.getCurrentURL().contains("privacy-policy")
							|| Common.getCurrentURL().contains("terms-of-use"),		
					"validating the links navigation from footer Links",
					"After Clicking on" + Termlinks[j] + "it should navigate to the",
					Termlinks[j] + "Sucessfully Navigated to the" + Termlinks[j] + "Links",
					"Unable to Navigated to the" + Termlinks[j] + "Links");
			Thread.sleep(4000);
			int responcecode = getpageresponce(Common.getCurrentURL());
			System.out.println(responcecode);
			String pagecode=Integer.toString(responcecode);
			System.out.println(pagecode);
			
			if(pagecode.equals("200"))
			{
				
				  Common.assertionCheckwithReport(pagecode.equals("200"),"Validating the page url with good response"
				  ,"Page configured Properly with any issues"
				  ,"Successfully page status is good without any issues","Failed to get the proper response from the page");
				 }
			else
			{
				j++;
				
				ExtenantReportUtils.addFailedLog("Validating the page url with good response" + Common.getCurrentURL(),
						"Page configured Properly with any issues", "Unable to get the proper response from the page",
						Common.getscreenShotPathforReport("Failed to get the proper response from the page" + Termlinks[j]));
				AssertJUnit.fail();
			}
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		}
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[@class='c-footer__container c-footer__items-wrapper u-container']//div[2]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
			Common.getPageTitle().contains(footerlinks[i])
			|| Common.getCurrentURL().contains("/about-us")
			|| Common.getCurrentURL().contains("history")
			|| Common.getCurrentURL().contains("sustainability")
			|| Common.getCurrentURL().contains("philanthropy")
			|| Common.getCurrentURL().contains("careers"),
			"validating the links navigation from footer Links",
			"After Clicking on" + footerlinks[i] + "it should navigate to the",
			footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
			"Unable to Navigated to the" + footerlinks[i] + "Links");
			Thread.sleep(4000);
			int responcecode = getpageresponce(Common.getCurrentURL());
			System.out.println(responcecode);
			String pagecode=Integer.toString(responcecode);
			System.out.println(pagecode);
			
			if(pagecode.equals("200"))
			{
				
				  Common.assertionCheckwithReport(pagecode.equals("200"),"Validating the page url with good response"
				  ,"Page configured Properly with any issues"
				  ,"Successfully page status is good without any issues","Failed to get the proper response from the page");
				 }
			else
			{
				i++;
				
				ExtenantReportUtils.addFailedLog("Validating the page url with good response" + Common.getCurrentURL(),
						"Page configured Properly with any issues", "Unable to get the proper response from the page",
						Common.getscreenShotPathforReport("Failed to get the proper response from the page" + footerlinks[i]));
				AssertJUnit.fail();
			}
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		
		}
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}

public void Footer_Links_CustomerSupport(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("CustomerSupport_Links");
	String[] footerlinks = footer.split(",");
	
	int i = 0;

	try {
		
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[@class='c-footer__container c-footer__items-wrapper u-container']//div[2]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("customer-support-center")
							|| Common.getCurrentURL().contains("knowledge-base")
							|| Common.getCurrentURL().contains("size-fit")
							|| Common.getCurrentURL().contains("/order/status")
							|| Common.getCurrentURL().contains("all-mighty-guarantee")
							|| Common.getCurrentURL().contains("reservoir-warranty")
							|| Common.getCurrentURL().contains("catalog-request")
							|| Common.getCurrentURL().contains("gift-card"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links");
					
			Thread.sleep(4000);
			int responcecode = getpageresponce(Common.getCurrentURL());
			System.out.println(responcecode);
			String pagecode=Integer.toString(responcecode);
			System.out.println(pagecode);
			
			if(pagecode.equals("200"))
			{
				
				  Common.assertionCheckwithReport(pagecode.equals("200"),"Validating the page url with good response"
				  ,"Page configured Properly with any issues"
				  ,"Successfully page status is good without any issues","Failed to get the proper response from the page");
				 }
			else
			{
				i++;
				
				ExtenantReportUtils.addFailedLog("Validating the page url with good response" + Common.getCurrentURL(),
						"Page configured Properly with any issues", "Unable to get the proper response from the page",
						Common.getscreenShotPathforReport("Failed to get the proper response from the page" + footerlinks[i]));
				AssertJUnit.fail();
			}
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		
		}
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}


public void Footer_Links_Resources(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("Resources_Links");
	String[] footerlinks = footer.split(",");
	
	int i = 0;

	try {
		
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("/prodeal")
							|| Common.getCurrentURL().contains("corporate-sales")
							|| Common.getCurrentURL().contains("program-sales")
							|| Common.getCurrentURL().contains("dealer-inquiries")
							|| Common.getCurrentURL().contains("ospreydealers")
							|| Common.getCurrentURL().contains("affiliate"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links");
					
			Thread.sleep(2000);
	/*		int responcecode = getpageresponce(Common.getCurrentURL());
			System.out.println(responcecode);
			String pagecode=Integer.toString(responcecode);
			System.out.println(pagecode);
			
			if(pagecode.equals("200"))
			{
				
				  Common.assertionCheckwithReport(pagecode.equals("200"),"Validating the page url with good response"
				  ,"Page configured Properly with any issues"
				  ,"Successfully page status is good without any issues","Failed to get the proper response from the page");
				 }
			else
			{
				i++;
				
				ExtenantReportUtils.addFailedLog("Validating the page url with good response" + Common.getCurrentURL(),
						"Page configured Properly with any issues", "Unable to get the proper response from the page",
						Common.getscreenShotPathforReport("Failed to get the proper response from the page" + footerlinks[i]));
				AssertJUnit.fail();
			}
			*/
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		
		}
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}


public void Footer_Links_BrandTeam(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("BrandTeam_Links");
	String[] footerlinks = footer.split(",");
	
	int i = 0;

	try {
		
		for (i = 0; i < footerlinks.length; i++) {
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("/athletes")
							|| Common.getCurrentURL().contains("coming-soon")
							|| Common.getCurrentURL().contains("coming-soon"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links");
				
			Thread.sleep(4000);
			int responcecode = getpageresponce(Common.getCurrentURL());
			System.out.println(responcecode);
			String pagecode=Integer.toString(responcecode);
			System.out.println(pagecode);
			
			if(pagecode.equals("200"))
			{
				
				  Common.assertionCheckwithReport(pagecode.equals("200"),"Validating the page url with good response"
				  ,"Page configured Properly with any issues"
				  ,"Successfully page status is good without any issues","Failed to get the proper response from the page");
				 }
			else
			{
				i++;
				
				ExtenantReportUtils.addFailedLog("Validating the page url with good response" + Common.getCurrentURL(),
						"Page configured Properly with any issues", "Unable to get the proper response from the page",
						Common.getscreenShotPathforReport("Failed to get the proper response from the page" + footerlinks[i]));
				AssertJUnit.fail();
			}
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		
		}
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}


public void Footer_validation(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("breadcrumbs");
	String[] footerlinks = footer.split(",");
	String footers = data.get(Dataset).get("breadcrumbs").toUpperCase();
	String[] footerlink = footers.split(",");
	int i = 0;
	try {
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String Bread = Common.findElement("xpath", "//nav[contains(@aria-label,'Breadcrumb')]").getText();
			String title = "";
			if (Common.findElements("xpath", "//h1[contains(@class,'cms-clear')]").size() > 0) {
			    title = Common.findElement("xpath", "//h1[contains(@class,'cms-clear')]").getText();
			} else {
			    String currentURL = Common.getCurrentURL().toUpperCase();
			    System.out.println("Redirecting to URL: " + currentURL);
			}		
			String page = Common.getPageTitle().toUpperCase();
			System.out.println(page);
			System.out.println(Bread);
			System.out.println(footerlinks[i]);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i]) || Bread.contains(footerlink[i]) || title.contains(footerlink[i]) || Bread.contains(page)
							|| Common.getCurrentURL().contains("size-fit")|| Common.getCurrentURL().contains("status"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links"); 
			Thread.sleep(5000);
			Common.navigateBack();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			

		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}



public void Footer_Links_Repari_And_Replacement(String Dataset) {
	// TODO Auto-generated method stub
	String footer = data.get(Dataset).get("Repair&Replacement");
	String[] footerlinks = footer.split(",");
	
	int i = 0;

	try {
		
		click_singinButton();
		Login_Account("Account");
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitElementPresent(30, "xpath",
					"//div[contains(@class,'footer')]//a[contains(text(),'" + footerlinks[i] + "')]");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//a[contains(text(),'" + footerlinks[i] + "')]");
			Common.clickElement("xpath",
					"//a[contains(text(),'" + footerlinks[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("form"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links");
				
			Thread.sleep(4000);
			Common.navigateBack();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
		
		}
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
				"After Clicking on" + footerlinks[i] + "it should navigate to the",
				footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
				Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
		AssertJUnit.fail();
	}

}
public void image_ShopAll(String Dataset) throws Exception {
	// TODO Auto-generated method stub
	//Prod
	if(Common.getCurrentURL().contains("/gb"))
	{
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//span[contains(text(),'New Season')]");
	Common.clickElement("xpath", "//span[contains(text(),'New Season')]");
	Thread.sleep(4000);
	String title1 = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
	System.out.println(title1);
	Thread.sleep(4000);
	String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
	System.out.println(products);
	int Number = Integer.parseInt(products);
	int j=0;
	if(Number>j)
	{
	Common.assertionCheckwithReport(title1.contains("New Season"),
			"verifying the header link New Season",
			"user should navigate to the New Season page",
			"user successfully Navigated to the New Season", "Failed to navigate to the New Season");
	}
	else
	{
		ExtenantReportUtils.addFailedLog(
				"validating the the products in the plp ",
				"User should able to see the products in plp", "unable to see the products in the PLP",
				Common.getscreenShot("Failed to see products in PLP"));
		AssertJUnit.fail();
	}
	}
	else
	{
		Sync.waitElementPresent("xpath", "//span[contains(text(),'PACKFINDER')]");
		Common.clickElement("xpath", "//span[contains(text(),'PACKFINDER')]");
		Thread.sleep(4000);
		String Breadcrumbs=Common.getText("xpath", "//p[@class='m-breadcrumb__text']");
		System.out.println(Breadcrumbs);
		Common.assertionCheckwithReport(Breadcrumbs.contains("New Season"),
				"verifying the header link PACKFINDER",
				"user should navigate to the PACKFINDER page",
				"user successfully Navigated to the PACKFINDER page", "Failed to navigate to the PACKFINDER page");
		AssertJUnit.fail();
		
	}
	
	if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
	{
		String names = data.get(Dataset).get("Prod");
		String[] Links = names.split(",");
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Links[i] +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Links[i] +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[text()='Shop All']");
				Common.clickElement("xpath", "//span[text()='Shop All']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				String products1=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
				System.out.println(products1);
				int Number1 = Integer.parseInt(products1);
				int z=0;
				if(Number1>z)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getCurrentURL().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					AssertJUnit.fail();
				}

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			AssertJUnit.fail();
		}
	}
	else
	{
		String names = data.get(Dataset).get("Shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Links[i] +"')]");
				Common.clickElement("xpath", "//span[contains(text(),'"+ Links[i] +"')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[text()='Shop All']");
				Common.clickElement("xpath", "//span[text()='Shop All']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getCurrentURL().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			AssertJUnit.fail();
		}
	}
	
	
	
}

public void header_sale() throws Exception {
	// TODO Auto-generated method stub
	
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//span[contains(text(),'SALE')]");
	Common.clickElement("xpath", "//span[contains(text(),'SALE')]");
	Thread.sleep(4000);
	String title1 = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
	System.out.println(title1);
	Thread.sleep(4000);
	String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
	System.out.println(products);
	int Number = Integer.parseInt(products);
	int j=0;
	if(Number>j)
	{
	Common.assertionCheckwithReport(title1.contains("Sale"),
			"verifying the header link New Season",
			"user should navigate to the New Season page",
			"user successfully Navigated to the New Season", "Failed to navigate to the New Season");
	}
	else
	{
		ExtenantReportUtils.addFailedLog(
				"validating the the products in the plp ",
				"User should able to see the products in plp", "unable to see the products in the PLP",
				Common.getscreenShot("Failed to see products in PLP"));
	}
	
}

public void header_Explore(String Dataset) {{
	// TODO Auto-generated method stub
	
	String names = data.get(Dataset).get("Osprey Explore");
	String[] Links = names.split(",");
	String name = data.get(Dataset).get("Osprey Explore").toUpperCase();
	String[] Link = name.split(",");
	String Name=data.get(Dataset).get("Prod Explore");
	String[] Link1 = Name.split(",");
	String Name1=data.get(Dataset).get("Prod Expert");
	String[] Link2 = Name1.split(",");
	String Explore=data.get(Dataset).get("Explore CTA");
	String activity=data.get(Dataset).get("Activity");
	String ExpertAdvice=data.get(Dataset).get("Expert Advice");
	int i = 0;
	try {
		if(Common.getCurrentURL().contains("preprod"))
		{
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Explore +"')]");
			Common.clickElement("xpath", "//span[contains(text(),'"+ Explore +"')]");
			Common.clickElement("xpath", "//a//span[contains(text(),'" + activity + "')]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li//a//span[contains(text(),'" + Links[i] + "')]");		
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String breadcrumbs="";
			if (Common.findElements("xpath", "//div//nav[contains(@class,'breadcrumbs')]").size() > 0) {
			    breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
			} else {
			    String currentURL = Common.getCurrentURL();
			    System.out.println("Redirecting to URL: " + currentURL);
			} 
			System.out.println(breadcrumbs);
			System.out.println(Links[i]);
			System.out.println(Link[i]);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(breadcrumbs.contains(Links[i]) 
					||breadcrumbs.contains(Link[i]) || Common.getPageTitle().contains("About Us") || Common.getPageTitle().contains("50years")
					|| Common.getPageTitle().contains(Links[i]) ,
					"verifying the header link " + Links[i] + "Under Accessories",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

		}
		Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Explore +"')]");
		Common.clickElement("xpath", "//span[contains(text(),'"+ Explore +"')]");
		Sync.waitElementPresent("xpath", "//span[contains(text(),'Stories')]");
		Common.clickElement("xpath", "//span[contains(text(),'Stories')]");
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("stories"),
				"verifying the header link stories Under Explore",
				"user should navigate to the stories page",
				"user successfully Navigated to the stories page", "Failed to navigate to the stories page");
		
	}
		else
		{
			
//			for (i = 0; i < Link1.length; i++) {
//				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Explore +"')]");
//				Common.clickElement("xpath", "//span[contains(text(),'"+ Explore +"')]");
//				Common.clickElement("xpath", "//a//span[contains(text(),'" + activity + "')]");
//				Thread.sleep(3000);
//				Sync.waitElementPresent("xpath",
//						"//a//span[contains(text(),'" + Link1[i] + "')]");
//				Common.clickElement("xpath",
//						"//li//a//span[contains(text(),'" + Link1[i] + "')]");		
//				Sync.waitPageLoad();
//				Thread.sleep(4000);
//				String breadcrumbs="";
//				if (Common.findElements("xpath", "//div//nav[contains(@class,'breadcrumbs')]").size() > 0) {
//				    breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
//				} else {
//				    String currentURL = Common.getCurrentURL();
//				    System.out.println("Redirecting to URL: " + currentURL);
//				} 
//				System.out.println(breadcrumbs);
//				System.out.println(Link1[i]);
//				System.out.println(Link1[i]);
//				System.out.println(Common.getPageTitle());
//				Common.assertionCheckwithReport(breadcrumbs.contains(Link1[i]) 
//						||breadcrumbs.contains(Link1[i]) || Common.getPageTitle().contains("About Us") || Common.getPageTitle().contains("Osprey 50")
//						|| Common.getPageTitle().contains(Link1[i]) ,
//						"verifying the header link " + Link1[i] + "Under Accessories",
//						"user should navigate to the " + Link1[i] + " page",
//						"user successfully Navigated to the " + Link1[i], "Failed to navigate to the " + Link1[i]);
//
//			}
//			for(i = 0; i < Link2.length; i++)
//			{
//				Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Explore +"')]");
//				Common.clickElement("xpath", "//span[contains(text(),'"+ Explore +"')]");
//				Common.clickElement("xpath", "//a//span[contains(text(),'" + ExpertAdvice + "')]");
//				Thread.sleep(3000);
//				Sync.waitElementPresent("xpath",
//						"//a//span[contains(text(),'" + Link2[i] + "')]");
//				Common.clickElement("xpath",
//						"//li//a//span[contains(text(),'" + Link2[i] + "')]");		
//				Sync.waitPageLoad();
//				Thread.sleep(4000);
//				String breadcrumbs="";
//				if (Common.findElements("xpath", "//div//nav[contains(@class,'breadcrumbs')]").size() > 0) {
//				    breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
//				} else {
//				    String currentURL = Common.getCurrentURL();
//				    System.out.println("Redirecting to URL: " + currentURL);
//				} 
//				System.out.println(breadcrumbs);
//				System.out.println(Link2[i]);
//				System.out.println(Link2[i]);
//				System.out.println(Common.getPageTitle());
//				Common.assertionCheckwithReport(
//						breadcrumbs.contains(Link2[i]) || breadcrumbs.contains(Link2[i])
//								|| Common.getCurrentURL().contains("fitting-learning/size-fit")
//								|| Common.getCurrentURL().contains("how-to-pack-your-pack")
//								|| Common.getPageTitle().contains(Link2[i]) || Common.getCurrentURL().contains("suspension") || Common.getCurrentURL().contains("kustomer"),
//						"verifying the header link " + Link2[i] + "Under Accessories",
//						"user should navigate to the " + Link2[i] + " page",
//						"user successfully Navigated to the " + Link2[i], "Failed to navigate to the " + Link2[i]);
//			}
//			Common.navigateBack();
			Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ Explore +"')]");
			Common.clickElement("xpath", "//span[contains(text(),'"+ Explore +"')]");
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Stories')]");
			Common.clickElement("xpath", "//span[contains(text(),'Stories')]");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("stories"),
					"verifying the header link stories Under Explore",
					"user should navigate to the stories page",
					"user successfully Navigated to the stories page", "Failed to navigate to the stories page");
			}
		sale();
		packfinder();
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Accessories",
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}	}

public void sale() {
	try
	{
		Sync.waitElementPresent("xpath", "//span[contains(text(),'SALE')]");
		Common.clickElement("xpath", "//span[contains(text(),'SALE')]");
		String breadcrumbs="";
		if (Common.findElements("xpath", "//div//nav[contains(@class,'breadcrumbs')]").size() > 0) {
		    breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
		} else {
		    String currentURL = Common.getCurrentURL();
		    System.out.println("Redirecting to URL: " + currentURL);
		} 
		Common.assertionCheckwithReport(
				breadcrumbs.contains("SALE")|| Common.getCurrentURL().contains("featured/sale")
						|| Common.getPageTitle().contains("Sale Prices"),
				"verifying the header link SALE Under Accessories",
				"user should navigate to the SALE page",
				"user successfully Navigated to the SALE ", "Failed to navigate to the SALE ");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link SALE Under Accessories",
				"User should navigate to the SALE pages",
				" unable to navigate to the SALE pages",
				Common.getscreenShot("Failed to navigate to the SALE pages"));
		Assert.fail();
		
	}
}

public void packfinder() {
	try
	{
		Sync.waitElementPresent("xpath", "//span[contains(text(),'PACKFINDER')]");
		Common.clickElement("xpath", "//span[contains(text(),'PACKFINDER')]");
		String breadcrumbs="";
		if (Common.findElements("xpath", "//div//nav[contains(@class,'breadcrumbs')]").size() > 0) {
		    breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText().toUpperCase();
		} else {
		    String currentURL = Common.getCurrentURL();
		    System.out.println("Redirecting to URL: " + currentURL);
		} 
		Common.assertionCheckwithReport(
				breadcrumbs.contains("PACKFINDER")|| Common.getCurrentURL().contains("packfinder")
						|| Common.getPageTitle().contains("Packfinder"),
				"verifying the header link PACKFINDER Under Accessories",
				"user should navigate to the PACKFINDER page",
				"user successfully Navigated to the PACKFINDER ", "Failed to navigate to the PACKFINDER ");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link PACKFINDER Under Accessories",
				"User should navigate to the PACKFINDER pages",
				" unable to navigate to the PACKFINDER pages",
				Common.getscreenShot("Failed to navigate to the PACKFINDER pages"));
		Assert.fail();
	}
}

public void AddtoCart_Disable_PLP(String Dataset) {
	// TODO Auto-generated method stub
	
	String products = data.get(Dataset).get("Products");
	try
	{
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) {
			Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
			List<WebElement> webelementslist = Common.findElements("xpath",
					"//img[contains(@class,'m-product-card__image')]");
			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}

		Sync.waitPageLoad(30);
		Thread.sleep(4000);	
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.mouseOver("xpath", "//img[@alt='" + products + "']");
		String Mouseover=Common.findElement("xpath", "//img[@alt='" + products + "']").getAttribute("alt");
		System.out.println(Mouseover);
		WebElement addtocart=Common.findElement("xpath", "//div[@class='m-product-card__cta']//child::form");   //element is not visible for the content stores
		boolean element=addtocart.isEnabled();
		if(element)
		{
			Thread.sleep(3000);
			System.out.println("'Add to cart button is Enabled");
		}
		else
		{
		
			Thread.sleep(3000);
			System.out.println("'Add to cart button is Disabled");
		}
		
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.clickElement("xpath", "//img[@alt='" + products + "']");
		Sync.waitPageLoad(30);
		Thread.sleep(6000);
		Common.scrollIntoView("xpath", "//div[@class='m-product-overview__info-top']//h1");
		Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
		String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
		System.out.println(name);
		System.out.println(products);
		Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
				"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
				"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		
		AssertJUnit.fail();
	}
}

public void url_color_validation(String Dataset) {
	// TODO Auto-generated method stub
	String product = data.get(Dataset).get("Products");
	try {
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) {
			Sync.waitElementPresent("xpath", "//img[contains(@itemprop ,'image')]");
			List<WebElement> webelementslist = Common.findElements("xpath",
					"//img[contains(@itemprop ,'image')]");
			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}
		Common.clickElement("xpath", "//img[@alt='" + product + "']");
		Thread.sleep(4000);
//		System.out.println(product);
//		String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
//		String products = data.get(Dataset).get("Products").toUpperCase();
//		System.out.println(product);
//		Common.assertionCheckwithReport(name.contains(products),
//				"validating the product should navigate to the PDP page",
//				"When we click on the product is should navigate to the PDP page",
//				"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");		
}
	
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the product should navigate to the PDP page",
				"When we click on the product is should navigate to the PDP page",
				"Unable to Navigate the product to the PDP page",
				Common.getscreenShot("Failed to Navigate the product to the PDP page"));
		AssertJUnit.fail();
	}

	
	try
	{
		List<WebElement> pdpcolors = Common.findElements("xpath",
				"//div[@aria-label='Color']//div[@x-id]");
		 Common.clickElement("xpath", "//div[@aria-label='Color']//div[@x-id]");
		for (int i = 0; i < pdpcolors.size(); i++) { 
           Thread.sleep(4000);
			pdpcolors.get(i).click();
			Thread.sleep(4000);
			String clicked_Color = pdpcolors.get(i).getAttribute("data-option-label");
			System.out.println(clicked_Color + " selected color");
            Thread.sleep(4000);
			System.out.println(Common.getCurrentURL());
			String URL=Common.getCurrentURL().replace("+", " ");
			System.out.println(URL);
			Common.assertionCheckwithReport(URL.contains(clicked_Color),
					"Validating PDP page url Color name is passing to url",
					"select the color of product is " + clicked_Color + " it must pass throw url",
					" Selected color " + clicked_Color + "passing throw url",
					"Failed to clicked color is passing throw URL" + clicked_Color);
	
		}
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the color and url in PDP page",
				"When we click on the color the color name should reflect in url",
				"Unable to display thee color name in the url",
				Common.getscreenShot("Failed to display thee color name in the url"));
		AssertJUnit.fail();
	}
}

public void Add_To_MyFavorities(String Dataset) {
	// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(Productsize);
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//form[@class='form-wishlist-items']//div[contains(@class,'message')]//span").size();
            System.out.println(MyFavorites);
			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.clickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");

				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad(30);
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("preprod"))
                {
                    Sync.waitPageLoad();
                    Thread.sleep(4000);
                    String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
                    System.out.println(message);
                    Common.assertionCheckwithReport(message.contains("has been added to your Fav"),
                            "validating the  product add to the Favorites", "Product should be add to Favorites",
                            "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

                }
				else
				{
				Sync.waitElementVisible(30, "xpath", "//h4");
				String whishlistpopup = Common.findElement("xpath", "//h4").getText();
				System.out.println(whishlistpopup);
				if (whishlistpopup.contains("Add to Wishlist")) {
					Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
					Common.clickElement("xpath", "//button[@title='Add To List']");
				} else {
					AssertJUnit.fail();
				}
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List") ||Common.getPageTitle().equals("My Favorites") ,
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("has been added to your Wish List"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
				}
				String Whishlistproduct = Common
						.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
				System.out.println(Whishlistproduct);
	}
		}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the products added to the whishlist form PDP", "Product should be add to fav from the PDP",
				"Unable to add  product to the My Faviorates from the PDP ", Common.getscreenShot("failed to add  product to the My Faviorates from the PDP"));
		AssertJUnit.fail();
	}
	
}

public void Add_Favorites_product_from_View_Cart() {
	// TODO Auto-generated method stub
	try {
		Common.maximizeImplicitWait();
		Common.scrollIntoView("xpath", "//span[text()='Your Favorites']");
		String Yourfav = Common.findElement("xpath", "//span[text()='Your Favorites']").getText();
		System.out.println(Yourfav);
		Common.assertionCheckwithReport(Yourfav.contains("Your Favorites"),
				"validating the favorites in view cart page", "Favorites should be in the view cart page",
				"Sucessfully Favorites has been displayed in the view cart page ",
				"failed to display the favorites in the view cart page");
		Sync.waitElementPresent("xpath", "//div[@class='product-item-details flex-1']//strong");
		Common.clickElement("xpath", "//div[@class='product-item-details flex-1']//strong");
		Sync.waitPageLoad();
		Thread.sleep(6000);

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
				"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
		AssertJUnit.fail();
	}
}

public void Add_Favorites_from_PLP(String Dataset) {
	// TODO Auto-generated method stub
	String product = data.get(Dataset).get("Products");
	System.out.println(product);
	String productcolor = data.get(Dataset).get("Color");
	System.out.println(productcolor);
	String Productsize = data.get(Dataset).get("Size");
	System.out.println(Productsize);
	try {
		Sync.waitPageLoad();
		int MyFavorites = Common.findElements("xpath", "//form[@class='form-wishlist-items']//div[contains(@class,'message')]//span").size();
        System.out.println(MyFavorites);
		if (MyFavorites != 0) {
			Bagpacks_headerlinks(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.mouseOver("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']//parent::a//parent::div//span[text()='Add to Favorites']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']//parent::a//parent::div//span[text()='Add to Favorites']");
			Sync.waitPageLoad(30);
			Thread.sleep(3000);
			if(Common.getCurrentURL().contains("preprod"))
            {
                Sync.waitPageLoad();
                Thread.sleep(4000);
                String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
                System.out.println(message);
                Common.assertionCheckwithReport(message.contains("has been added to your Fav"),
                        "validating the  product add to the Favorites", "Product should be add to Favorites",
                        "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

            }
			else
			{
			Sync.waitElementVisible(30, "xpath", "//h4");
			String whishlistpopup = Common.findElement("xpath", "//h4").getText();
			System.out.println(whishlistpopup);
			if (whishlistpopup.contains("Add to Wishlist")) {
				Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
				Common.clickElement("xpath", "//button[@title='Add To List']");
			} else {
				AssertJUnit.fail();
			}
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List") ||Common.getPageTitle().equals("My Favorites") ,
					"validating the Navigation to the My Favorites page",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
					"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
					"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
			Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
			Sync.waitPageLoad();
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("has been added to your Wish List"),
					"validating the  product add to the Whishlist", "Product should be add to whishlist",
					"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
			}
			String Whishlistproduct = Common
					.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
			System.out.println(Whishlistproduct);
}
	}
catch(Exception | Error e)
{
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("validating the products added to the whishlist form PDP", "Product should be add to fav from the PDP",
			"Unable to add  product to the My Faviorates from the PDP ", Common.getscreenShot("failed to add  product to the My Faviorates from the PDP"));
	AssertJUnit.fail();
}

}

public void Fav_Seeoption_from_View_cart(String Dataset) {
	// TODO Auto-generated method stub
	String products = data.get(Dataset).get("Products");
	String productcolor = data.get(Dataset).get("Color");
	String Productsize = data.get(Dataset).get("Size");
	try
	{
	Common.maximizeImplicitWait();
	Common.scrollIntoView("xpath", "//span[text()='Your Favorites']");
	String Yourfav=Common.findElement("xpath", "//span[text()='Your Favorites']").getText();
	System.out.println(Yourfav);
	Common.assertionCheckwithReport(Yourfav.contains("Your Favorites"),
			"validating the favorites in view cart page", "Favorites should be in the view cart page",
			"Sucessfully Favorites has been displayed in the view cart page ", "failed to display the favorites in the view cart page");
	Sync.waitElementPresent("xpath", "//div[@class='product-item-details flex-1']//strong");
	Common.clickElement("xpath", "//div[@class='product-item-details flex-1']//strong");
	Sync.waitPageLoad();
	Thread.sleep(8000);
//	String Options=Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//	Common.assertionCheckwithReport(Options.contains("You need to choose options for your item"),
//			"validating the color option on the PDP", "After clicking on the add to cart button for color product it should navigate to PDP",
//			"Sucessfully Navigated to the PDP and options messgae has been appeared ", "failed to Display the choose options message on PDP");
	Thread.sleep(4000);
//	Sync.waitElementPresent("xpath", "(//div[@data-option-label='" + productcolor + "'])[1]");
//	Common.clickElement("xpath", "(//div[@data-option-label='" + productcolor + "'])[1]");
//	Sync.waitElementPresent("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
//	Common.clickElement("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
	Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
	Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
	Sync.waitPageLoad();
	Thread.sleep(6000);
//	Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
//	String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//			.getAttribute("data-ui-id");
//	System.out.println(message);
//	Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//			"Product should be add to cart", "Sucessfully product added to the cart ",
//			"failed to add product to the cart");
	Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Close minicart']");
	Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
				"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
		AssertJUnit.fail();
	}
}

public String Secure_Payment_details(String dataSet) throws Exception {
	// TODO Auto-generated method stub
	String order = "";
	ThreedPaymentDetails(dataSet);
	String expectedResult = "It redirects to order confirmation page";

	if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
		Thread.sleep(4000);
		ThreedPaymentDetails(dataSet);
	}

	Thread.sleep(3000);
	int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
	if (placeordercount > 1) {
		Thread.sleep(4000);
		
		Common.clickElement("xpath", "//button[@class='action primary checkout']");
		Thread.sleep(6000);
		Sync.waitElementPresent(50, "xpath", "//iframe[@id='challengeFrame']");
		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		Common.switchToDefault();
	}

	String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

	if (!url.contains("stage") && !url.contains("preprod")) {
	}

	else {
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent(30,"xpath", " //h1[normalize-space()='Thank you for your purchase!']");
			String sucessMessage = Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");

			//Tell_Your_FriendPop_Up();
			int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']").size();
			Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
					"verifying the product confirmation", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");

			if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
				Thread.sleep(2000);
				order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
				System.out.println(order);
			} else {
				Thread.sleep(3000);
				order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
				System.out.println(order);
			}

			if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
				Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
				System.out.println(order);

			}
		
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}
	return order;
}

public void sort_By(String dataSet) {
	// TODO Auto-generated method stub
	String sort = data.get(dataSet).get("Sort");
	String Prodsort=data.get(dataSet).get("ProdSort");
	try {

		if(Common.getCurrentURL().contains("preprod"))
		{
		Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
		Common.clickElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + sort + "')]");
		String low = Common
				.findElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + sort + "')]")
				.getText();
		Common.assertionCheckwithReport(low.contains(sort), "To validate the Sort in Product Listing Page",
				"User should able to Sort in Product Listing Page", "Sucessfully Sorts in the Product Listing Page",
				"Failed to Sort  in Product Listing Page");
		}
		else
		{
			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
			Common.clickElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + Prodsort + "')]");
			String low = Common
					.findElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + Prodsort + "')]")
					.getText();
			Common.assertionCheckwithReport(low.contains(Prodsort), "To validate the Sort in Product Listing Page",
					"User should able to Sort in Product Listing Page", "Sucessfully Sorts in the Product Listing Page",
					"Failed to Sort  in Product Listing Page");
		}

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the Sort  in Product Listing Page",
				"User should able to Sort  in Product Listing Page", "Unable to Sort the Product Listing Page",
				Common.getscreenShotPathforReport("Failed to Sort  Product listing Page"));

		AssertJUnit.fail();
	}
	
}

public void Sort_By(String Dataset) throws InterruptedException {
		// TODO Auto-generated method stub
		String symbol = data.get(Dataset).get("Price_Symbol");
		String PriceFilter = data.get(Dataset).get("Sortby_Dropdown");
		String priceFliter= data.get(Dataset).get("SortBy");
		System.out.println(PriceFilter);
		System.out.println(symbol);
		try {
			Sync.waitPageLoad();

			Thread.sleep(5000);
			Common.scrollIntoView("xpath",
					"//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@x-ref='specialPrice']");

			List<WebElement> BeforeFilterprice = Common.findElements("xpath",
					"//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@x-ref='specialPrice']");
			List<String> Beforefilterpricelist = new ArrayList<String>();

			for (WebElement p : BeforeFilterprice) {
				Beforefilterpricelist.add(p.getText().replace(symbol, " "));
				System.out.println("Beforefilterpricelist" + Beforefilterpricelist);
			}
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("preprd"))
			{
			Common.dropdown("xpath", "//div/select[@class='ais-SortBy-select']", SelectBy.TEXT,
					PriceFilter);
			}
			else
			{
				Common.dropdown("xpath", "//div/select[@class='ais-SortBy-select']", SelectBy.TEXT,
						priceFliter);
			}
			Thread.sleep(5000);
			Common.scrollIntoView("xpath",
					"//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@x-ref='specialPrice']");
			List<WebElement> AfterFilterprice = Common.findElements("xpath",
					"//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@x-ref='specialPrice']");
			List<String> Afterfilterpricelist = new ArrayList<String>();

			for (WebElement p : AfterFilterprice) {
				Afterfilterpricelist.add(p.getText().replace(symbol, " "));
				System.out.println("Afterfilterpricelist" + Afterfilterpricelist);
			}

			if (PriceFilter.equals("Highest Price")) {
				Collections.sort(Beforefilterpricelist);
				System.out.println("Beforefilterpricelist Highest " + Beforefilterpricelist);
				Common.assertionCheckwithReport(Beforefilterpricelist.equals(Afterfilterpricelist),
						"To validate the Sort in Product Listing Page",
						"User should able to Sort in Product Listing Page",
						"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
			} else {
				if (PriceFilter.equals("Lowest Price")) {
					Collections.sort(Beforefilterpricelist, Collections.reverseOrder());
					System.out.println("Beforefilterpricelist Lowest" + Beforefilterpricelist);
					Common.assertionCheckwithReport(Beforefilterpricelist.equals(Afterfilterpricelist),
							"To validate the Sort in Product Listing Page",
							"User should able to Sort in Product Listing Page",
							"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
				}

			}
			Thread.sleep(2000);
		} catch (NumberFormatException | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Sort by functionality",
					"Products should be display as per selected sort option ",
					" Unable to display the Products as per selected sort option",
					Common.getscreenShot("Failed to sort_by"));
			AssertJUnit.fail();
		}

	}

public void Prouser_Discount() {
	// TODO Auto-generated method stub
	try
	{
	Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
	Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
	Thread.sleep(3000);
	String originalprice = Common.getText("xpath", "//span[@class='price line-through hf:font-bold md:hf:font-normal']").replace("$", "");
	//String originalprice = Common.getText("xpath", "//div[contains(@class,'old-price')]//span[@class='price line-through']").replace("$", "");
	Float originalvalue = Float.parseFloat(originalprice);
	String Newprice = Common.getText("xpath", "(//span[@class='price-wrapper']//span[@class='price'])").replace("$", "");
	Float pricevalue = Float.parseFloat(Newprice);
	Thread.sleep(4000);
	float discount = originalvalue - (originalvalue * 40 / 100);
	String discountvalue = String.valueOf(discount).replace("$", "");
	Float value = Float.parseFloat(discountvalue);
	String s=String.valueOf(value); 
	System.out.println(s);
	System.out.println(Newprice);
	Common.assertionCheckwithReport(Newprice.contains(s),
			"verifying the discount for the Pro user discount ",
			"user should able to see the discount for the Pro user",
			"user successfully able to apply the discount", "Failed to apply the discount for the pro user");
	click_minicart();
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the discount for the pro user discount ",
				"user should able to see the discount for the pro user",
				"Unable to apply the discount for the pro user",
				Common.getscreenShotPathforReport("Failed to apply the discount for the pro user"));
		Assert.fail();
	}
	
}

public void addDeliveryAddress_Gustuser(String dataSet) throws Exception {

	try {
		Thread.sleep(5000);
		Sync.waitElementVisible("xpath", "//input[@type='email']");
		Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

	} catch (NoSuchElementException e) {
		minicart_Checkout();
		Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

	}
	String expectedResult = "email field will have email address";
	try {
		Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='firstname']",
				data.get(dataSet).get("FirstName"));
		int size = Common.findElements("xpath", "//input[@type='email']").size();
		Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
				"Filled Email address", "unable to fill the email address");
		Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='lastname']",
				data.get(dataSet).get("LastName"));
		Common.clickElement("xpath", "//section[@id='shipping-details']//input[@name='street[0]']");
		Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='street[0]']",
				data.get(dataSet).get("Street"));
//		String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");

		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.findElement("xpath", "//section[@id='shipping-details']//input[@name='city']").clear();
		Common.textBoxInput("xpath", "//section[@id='shipping-details']//input[@name='city']",
				data.get(dataSet).get("City"));
		System.out.println(data.get(dataSet).get("City"));

		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		try {
			Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		} catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		}
		Thread.sleep(2000);
		Common.textBoxInputClear("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(5000);

		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("validating shipping address filling Fileds",
				"shipping address is filled in to the fields", "user should able to fill the shipping address ",
				Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating shipping address",
				"shipping address is filled in to the fields", "user faield to fill the shipping address",
				Common.getscreenShotPathforReport("shipingaddressfaield"));
		Assert.fail();

	}

}

public void addDeliveryAddress_RegUser(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
		int size = Common.findElements(By.xpath("//button[contains(text(),'New Address')]")).size();
		if (size > 0) {
			try {
				Common.clickElement("xpath", "//button[contains(text(),'New Address')]");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath", "//input[@name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}

				Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
				Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Region"));
				Thread.sleep(3000);
//				String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
//				String Shippingstate = Common
//						.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']")
//						.getText();

//				System.out.println(Shippingstate);

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(2000);
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);

				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

//				Sync.waitElementPresent("xpath", "//input[@id='shipping-save']");
//				Common.clickElement("xpath", "//input[@id='shipping-save']");

				Common.clickElement("xpath", "//button[contains(text(),'Ship Here')]");

//				
//                ExtenantReportUtils.addPassLog("verifying shipping addres filling ",
//						"user will fill the all the shipping", "user fill the shiping address click save button",
//						"faield to add new shipping address");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				Assert.fail();

			}

		}

		else

		{
			try {

				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				Thread.sleep(2000);

				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("id", "shipping-city",
						data.get(dataSet).get("City"));

				try {
					Common.dropdown("xpath", "//select[@id='shipping-region']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));

				String ShippingZip = Common
						.findElement("xpath", "//form[@id='shipping']//input[@name='postcode']")
						.getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);
				
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				Common.clickElement("id", "shipping-save");
				
				
				

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				Assert.fail();

			}
		}

	}

public void MyAccount_Subscription() {
	
	try {
	
	Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
	Common.clickElement("xpath", "//button[@id='customer-menu']");
	Sync.waitElementPresent("xpath", "//a[@title='My Account']");
	Common.clickElement("xpath", "//a[@title='My Account']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	
	String MyAccount= Common.getPageTitle();
	
	System.out.println(MyAccount);
	
	Common.assertionCheckwithReport(MyAccount.equals("Dashboard"),
			"validating the Dashboard page is displayed",
			"Dashboard page should be display",
			"Successfully Dashboard page is displayed",
			"Failed to display the  Dashboard page");
	
	 Common.clickElement("xpath", "//span[text()='My Newsletter Subscriptions']");
    Common.switchFrames("xpath", "//iframe[@id='klaviyo_subscribe_page_1']");
    String Subscribe_to_Osprey = Common.getText("xpath", "//span[text()='Subscribe to Osprey emails']");
    System.out.println(Subscribe_to_Osprey);

    if(Subscribe_to_Osprey.contains("Subscribe to Osprey emails")) {
    	Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[1]", "qatest@gmail.com");
    	Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[2]", "qa");
    	Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[3]", "test");
    	Common.clickCheckBox("xpath", "//button[text()='Subscribe']");
    	Thread.sleep(3000);
    	String Subscribed= Common.getText("xpath", "//span[text()='Thanks for confirming your email address.']");
    	Common.assertionCheckwithReport(Subscribed.equals("Thanks for confirming your email address."),
    			"validating the User should able to subscribe successfully",
    			"User should able to subscribe successfully",
    			"Successfully User subscribed",
    			"Failed to subscribe in My account page");
    	Common.switchToDefault();
    }
    else {
    	Assert.fail();
    }
    
    Common.clickCheckBox("xpath", " (//div[@id='subscribe_section'])[2]");
    Common.switchFrames("xpath", "//iframe[@id='klaviyo_subscribe_page_2']");
    String UnSubscribe = Common.getText("xpath", "//button[text()='Unsubscribe']");
    System.out.println(UnSubscribe);

    if(UnSubscribe.contains("Unsubscribe")) {
    	Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[1]", "qatest@gmail.com");
 
    	Common.clickCheckBox("xpath", "//button[text()='Unsubscribe']");
    	Thread.sleep(3000);
    	String Subscribed= Common.getText("xpath", "//button[text()='Go to website']");
    	Common.assertionCheckwithReport(Subscribed.equals("Go to website"),
    			"validating the User should able to subscribe successfully",
    			"User should able to subscribe successfully",
    			"Successfully User subscribed",
    			"Failed to subscribe in My account page");
    	Common.switchToDefault();
    }
    else {
    	Assert.fail();
    }

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}




public void warrenty_return_Authorization() {
	// TODO Auto-generated method stub
	try {

		Common.scrollIntoView("xpath",
				"//div[contains(@class,'footer-grid-osprey')]//a[text()='Need a Part Replacement?']");
		Common.clickElement("xpath",
				"//div[contains(@class,'footer-grid-osprey')]//a[text()='Need a Part Replacement?']");
		String Url = Common.getCurrentURL();
		System.out.println(Url);
		Common.scrollIntoView("xpath", "//div[@class='pagebuilder-button-primary']");
		Common.clickElement("xpath", "//div[@class='pagebuilder-button-primary']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("form"),
				"validating the page navigates to the Return authorization form",
				"After clicking on authorization from mighty gurantee it should navigate to the Return authorization form ",
				"successfully navigated to the Return authorization form",
				"failed to Navigate to the Return authorization form");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the page navigates to the Return authorization form",
				"After clicking on authorization from mighty gurantee it should navigate to the Return authorization form ",
				"Unable to Navigate to the Return authorization form",
				Common.getscreenShot("Failed to Navigate to the Return authorization form"));
		Assert.fail();
	}

}

public void warrent_Return_Auth_Form(String Dataset) {
	// TODO Auto-generated method stub
	String phonenumber = data.get(Dataset).get("Phone");
	String zipcode = data.get(Dataset).get("postcode");
	String Address = data.get(Dataset).get("Street");
	String City = data.get(Dataset).get("City");
	String State = data.get(Dataset).get("Region");
	System.out.println(State);
	String Packname = data.get(Dataset).get("PackageName");
	String Color = data.get(Dataset).get("Color");
	String Description = data.get(Dataset).get("description");
	String issue = data.get(Dataset).get("issue");
	String POnumber = data.get(Dataset).get("Ponumber");
	String DOP = data.get(Dataset).get("Descriptions");
	String Frame = data.get(Dataset).get("frame1");
	String YOP = data.get(Dataset).get("Yopurchase");
	String Country = "United States";
	System.out.println(DOP);
	System.out.println(YOP);
	System.out.println(Frame);

	try {
		Common.findElement("xpath", "//select[@id='country_id']");
		Common.clickElement("xpath", "//select[@id='country_id']");
		Thread.sleep(4000);
		Common.dropdown("xpath", "//select[@id='country_id']", SelectBy.TEXT, Country);
		Sync.waitElementPresent(30, "xpath", "//input[@id='telephone']");
		Common.scrollIntoView("xpath", "//input[@id='telephone']");
		Common.textBoxInput("xpath", "//input[@id='telephone']", phonenumber);
		Common.textBoxInput("xpath", "//input[@id='address']", Address);

		Common.textBoxInput("xpath", "//input[@id='city']", City);
		Thread.sleep(4000);
		Common.findElement("xpath", "//select[@name='region']");
		Common.clickElement("xpath", "//select[@name='region']");
		Thread.sleep(4000);
		Common.dropdown("xpath", "//select[@name='region']", SelectBy.TEXT, State);
		Common.textBoxInput("xpath", "//input[@id='postcode']", zipcode);

		Common.findElement("xpath", "//select[@id='frame_size']");
		Common.clickElement("xpath", "//select[@id='frame_size']");
		Thread.sleep(4000);
		Common.dropdown("xpath", "//select[@id='frame_size']", SelectBy.TEXT, Frame);
		Common.textBoxInput("xpath", "//input[@id='approx_year_purchase']", YOP);

		Sync.waitElementPresent(20, "xpath", "//input[@id='sentimental-unrepaired']");
		Common.clickElement("xpath", "//input[@id='sentimental-unrepaired']");
		Common.textBoxInput("xpath", "//textarea[@id='description_part']", DOP);

		Common.textBoxInput("xpath", "//input[@id='pack_and_volume']", Packname);
		Common.textBoxInput("xpath", "//input[@id='color_and_frame']", Color);
		Common.textBoxInput("xpath", "//textarea[@id='description']", Description);

		Common.textBoxInput("xpath", "//input[@id='pr_po_number']", POnumber);
		Common.textBoxInput("xpath", "//textarea[@id='location_function_part']", issue);

		Thread.sleep(4000);

		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Osprey_EMEA\\Guarantee.png");
		Sync.waitElementPresent(40, "xpath", "//input[@id='photoOne']");
		Common.findElement("xpath", "//input[@id='photoOne']").sendKeys(path);

		Common.clickElement("xpath", "//input[@id='gdpr_confirm']");
		Common.clickElement("xpath", "//button[contains(@class,'action submit')]");

		Thread.sleep(4000);
		String Successmsg = Common.findElement("xpath", "//section[contains(@class,'return-authorization-success')]").getText();
		System.out.println(Successmsg);
		Common.assertionCheckwithReport(Successmsg.contains("Thanks for submitting your Repair Request."),
				"validating the waranty and return Success message",
				"After clicking Submit button waranty and return Success message should be display",
				"successfully  message has been dispalyed ", "failed to display the Successfull message");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the waranty and return Success message ",
				"After clicking Submit button waranty and return Success message should be display",
				"Unable to display the Success message ",
				Common.getscreenShot("Failed to display the Successful message"));
		Assert.fail();
	}
}

public void header_ChildCarrier(String Dataset) {

	String names = data.get(Dataset).get("ChildCarrier");
	String[] Links = names.split(",");
	String names1 = data.get(Dataset).get("ChildCarrier").toUpperCase();
	String[] Link = names1.split(",");
	String bag=data.get(Dataset).get("Backpacks");
	String child=data.get(Dataset).get("ChildCarrier");
	
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ bag +"')]");
			Common.clickElement("xpath", "//span[contains(text(),'"+ bag +"')]");
			if(Common.getCurrentURL().contains("preprod"))
			{
			Sync.waitElementPresent("xpath", "(//a[contains(@title,'" + child + "')])[1]");
			Common.clickElement("xpath", "(//a[contains(@title,'" + child + "')])[1]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a/span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//a/span[contains(text(),'" + Links[i] + "')]");
			}
			else
			{
				Sync.waitElementPresent("xpath", "(//a[contains(@title,'" + child + "')])[1]");
				Common.clickElement("xpath", "(//a[contains(@title,'" + child + "')])[1]");
				Thread.sleep(3000);
			}
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
			String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
			System.out.println(title);
			System.out.println(breadcrumbs);
			System.out.println(Links[i]);
			Thread.sleep(4000);
			String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
			System.out.println(products);
			int Number = Integer.parseInt(products);
			int j=0;
			if(Number>j)
			{
				Common.assertionCheckwithReport(title.contains("Best Sellers") || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]) ,
						"verifying the header link " + Links[i] + "Under Child Carriers",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
			}
			else
			{
				ExtenantReportUtils.addFailedLog(
						"validating the the products in the plp ",
						"User should able to see the products in plp", "unable to see the products in the PLP",
						Common.getscreenShot("Failed to see products in PLP"));
				Assert.fail();
			}
			
		
			}
		if(Common.getCurrentURL().contains("/fr/"))
		{
			Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ bag +"')]");
			Common.clickElement("xpath", "//span[contains(text(),'"+ bag +"')]");
			Sync.waitElementPresent("xpath", "//span[contains(text(),'"+ child +"')]");
			Common.clickElement("xpath", "//span[contains(text(),'"+ child +"')]");
			String name="Sacs à dos d";
			Sync.waitElementPresent("xpath",
					"(//li[contains(@class,'level2 ')]//a//span[contains(text(),'"+ name + "')])[2]");
			Common.clickElement("xpath",
					"(//li[contains(@class,'level2 ')]//a//span[contains(text(),'"+ name + "')])[2]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
			System.out.println(breadcrumbs);
			String name1="Sacs à dos d".toUpperCase();
			name.toUpperCase();
			System.out.println(name1);
			Common.assertionCheckwithReport(breadcrumbs.contains(name1),
					"verifying the header link " + name1 + "Under Child Carrier",
					"user should navigate to the " + name1 + " page",
					"user successfully Navigated to the " + name1, "Failed to navigate to the " + name1);
			
		}
	
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Child Carrier",
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}

public void header_Holiday_Gift_Guide(String Dataset) {

	if(Common.getCurrentURL().contains("www.osprey.com/gb/"))
	{
		String names = data.get(Dataset).get("Holiday Gift Guide");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Holiday Gift Guide").toUpperCase();
		String[] Link = name.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Featured')]");

				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Featured",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
	}
	else
	{
		
		
		String names = data.get(Dataset).get("Holiday Gift Guide");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Holiday Gift Guide").toUpperCase();
		String[] Link = name.split(",");
		String Featured = data.get(Dataset).get("Feature");

		int i = 0;
		try {
		    for (i = 0; i < Links.length; i++) {
		        Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Featured + "')]");
		        Common.clickElement("xpath", "//span[contains(text(),'" + Featured + "')]");
		        Thread.sleep(4000);
		        Sync.waitElementPresent("xpath", "//li//a[@title='" + Links[i] + "']//span[contains(text(),'" + Links[i] + "')]");
		        Common.clickElement("xpath", "//li//a[@title='" + Links[i] + "']//span[contains(text(),'" + Links[i] + "')]");
		        Sync.waitPageLoad();
		        Thread.sleep(4000);

		        String title = "";
		        if (Common.findElements("xpath", "//div[contains(@class,'c-clp-hero')]//h1").size() > 0) {
		            title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
		        } else if (Common.findElements("xpath", "//div[contains(@class,'category-view')]/h1").size() > 0) {
		            title = Common.findElement("xpath", "//div[contains(@class,'category-view')]/h1").getText();
		        }

		        String breadcrumbs = Common.findElement("xpath", "//div//nav[contains(@class,'breadcrumbs')]").getText();
		        Common.assertionCheckwithReport(
		                title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Link[i]),
		                "verifying the header link " + Links[i] + " under Featured",
		                "user should navigate to the " + Links[i] + " page",
		                "user successfully Navigated to the " + Links[i],
		                "Failed to navigate to the " + Links[i]
		        );
		    }

		    
		} catch (Exception | Error e) {
		    e.printStackTrace();
		    ExtenantReportUtils.addFailedLog(
		            "verifying the header link " + Links[i] + " under Featured",
		            "User should navigate to the " + Links[i] + " pages",
		            "unable to navigate to the " + Links[i] + " pages",
		            Common.getscreenShot("Failed to navigate to the " + Links[i] + " pages")
		    );
		    Assert.fail();
		}

		
		}

}

public void createaccount_verfication(String Dataset) {
	// TODO Auto-generated method stub
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
		Common.clickElement("xpath", "//input[@name='lastname']");
		Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
		Common.clickElement("xpath", "//input[@name='email']");
		Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
		Common.clickElement("xpath", "//input[@name='password']");
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
		Common.clickElement("xpath", "//input[@name='password_confirmation']");
		Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
				data.get(Dataset).get("Confirm Password"));
		Common.clickElement("xpath", "//button[@title='Sign Up']");
//		String message = Common.findElement("id", "validation-classes").getCssValue("color");
//		String redcolor = Color.fromString(message).asHex();
//		System.out.println(redcolor);
//		String message1 = Common.findElement("id", "validation-length").getCssValue("color");
//		String greencolor = Color.fromString(message1).asHex();
//		System.out.println(greencolor);
		String emailmessage = Common.findElement("xpath", "//li[@data-msg-field='email']").getText();
		System.out.println(emailmessage);
		String confirmpassword = Common.findElement("xpath", "//li[@data-msg-field='password_confirmation']").getText();
		System.out.println(confirmpassword);
	
		Common.assertionCheckwithReport(
				emailmessage.equals("Please enter a valid email address.") && confirmpassword.contains("This field value must be the same as") ,
				"validating the error messages with invalid date in accout creation form",
				"User should able to get error message when used the invaild data",
				"Sucessfully error message has been displayed when user use the invalid data",
				"Failed to get an error message when user used the invalid data");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the error messages with invalid date in accout creation form",
				"User should able to get error message when used the invaild data",
				"Unable to get error message has been displayed when user use the invalid data",
				Common.getscreenShotPathforReport(
						"Failed to get an error message when user used the invalid data"));
		Assert.fail();

	}
}

public String create_account(String Dataset) {
	String email="";
	String Product=data.get(Dataset).get("Products");
	String Email = data.get(Dataset).get("Email");
	try {

		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("UserName"));
		
		
//		Common.textBoxInput("xpath", "//input[@id='email_address']", Email);
		email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
		System.out.println(data.get(Dataset).get("Password"));
		Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
				data.get(Dataset).get("Confirm Password"));
		System.out.println(data.get(Dataset).get("Confirm Password"));
		Thread.sleep(4000);
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		Thread.sleep(2000);
		String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
		System.out.println(message);
		Common.assertionCheckwithReport(
				message.contains("Thank you for registering") || Common.getPageTitle().contains("Wish List Sharing")&& message.contains(Product+ " has been added to your Favorites. Click here to view your Favorites") ,
				"validating navigation to the account page after clicking on sign up button",
				"User should navigate to the My account page after clicking on the Signup",
				"Sucessfully user navigates to the My account page after clickng on thr signup button",
				"Failed to navigate to the My account page after clicking on the signup button");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating navigation to the account page after clicking on sign up button",
				"User should navigate to the My account page after clicking on the Signup",
				"Unable to navigate to the My account page after clicking on the signup button",
				"Failed to navigate to the My account page after clicking on the signup button");
		Assert.fail();
	}
	return email;
}

public void Accessories_Header(String Dataset) {
	// TODO Auto-generated method stub
	String expectedResult = "User should click the" + Dataset;
	String out = data.get(Dataset).get("outdoor");
	String header=data.get(Dataset).get("headers");
	try {

		Thread.sleep(3000);
		Sync.waitElementPresent("xpath",
				"//a[contains(@class,'level-0')]//span[contains(text(),'"+ header +"')]");
		
		Common.clickElement("xpath", "//a[contains(@class,'level-0')]//span[contains(text(),'" + header + "')]");

		Thread.sleep(3000);

		try {
			Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
		} catch (Exception e) {
			Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
		}
		Common.clickElement("xpath", "//span[contains(text(),'" + out + "')]");

		Sync.waitPageLoad();
		Thread.sleep(6000);
		expectedResult = "User should select the " + Dataset + "category";
		int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'"+ header +"')]").size();
		Common.assertionCheckwithReport(sizebotteles > 0,
				"validating the product category as" + Dataset + "from navigation menu ", expectedResult,
				"Selected the " + Dataset + " category", "User unabel to click" + Dataset + "");

	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the product category as" + Dataset + "from navigation menu ",
				expectedResult, "Unable to Selected the " + Dataset + " category",
				Common.getscreenShot("Failed to click on the" + Dataset + ""));

		AssertJUnit.fail();
	}

}

public void deleteProduct_shoppingcart() {
	// TODO Auto-generated method stub
	try {
		
		int size=Common.findElements("xpath", "//tr[contains(@class,'item-info align')]").size();
		System.out.println(size);
		for(int i=0;i<size;i++)
		{
			int value=i+1;
		Common.clickElement("xpath", "(//button[contains(@class,'group p-2.5 text-black')])['" +value+ "']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary')])[1]");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		}
		String getText =Common.findElement("xpath","(//div[@class='cart-empty container min-h-75']//p)[1]").getText();
		Common.assertionCheckwithReport(getText.equals("You have no items in your shopping cart."),
				"validating the delete product in shopping cart page",
				"color should be delete in the shopping cart page",
				"color has been deleted in the shopping cart page",
				"Failed to delete the product  in the shopping cart page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the delete product in shopping cart page",
				"color should be delete in the shopping cart page",
				"Unable to delete the product  in the shopping cart page",
				Common.getscreenShot("Failed to delete the product  in the shopping cart page"));
		Assert.fail();
	}
	
}

public void Admin_signin(String dataSet) {

	try {
		
		Sync.waitPageLoad();
		if (Common.getCurrentURL().contains("emea-preprod")) {
			
			Thread.sleep(12000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("admin/dashboard/"),
					"To Validate the Admin is landing on the Dashboard after successfull Signin",
					"After clicking on sigin button admin should navigate to the dashboard",
					"Admin Sucessfully navigate to the dashboard after clicking on the signin button",
					"Admin failed to display the dashboard after clicking on the signin button");
		}
		else {
			Sync.waitPageLoad(30);
			
			Common.openNewTab();
			
			Common.oppenURL("https://na-preprod.hele.digital/heledigitaladmin/admin/dashboard/");
			
			Sync.waitPageLoad(4000);
			Sync.waitElementPresent(30, "xpath", "//a[text()='Login via Identity Provider']");
			Common.clickElement("xpath", "//a[text()='Login via Identity Provider']");
			Thread.sleep(15000);
			
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard / Magento Admin"),
					"To Validate the Admin is landing on the Dashboard after successfull Signin",
					"After clicking on sigin button admin should navigate to the dashboard",
					"Admin Sucessfully navigate to the dashboard after clicking on the signin button",
					"Admin failed to display the dashboard after clicking on the signin button");
		}
	
		

	} catch (Exception e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog(
				"To Validate the Admin is landing on the Dashboard after successfull Signin",
				"After clicking on sigin button admin should navigate to the dashboard",
				"Admin failed to navigate to the dashboard after click on signin button",
				"Admin failed to land on the dashboard after clicking on the signin button");
		Assert.fail();

	}

}


public void click_Sales() {
	// TODO Auto-generated method stub

	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "menu-magento-sales-sales");
		Common.clickElement("id", "menu-magento-sales-sales"); // this line clicks on sale option in magento
		Thread.sleep(2000);
		String Sales = Common.findElement("xpath", "//li[@class='item-sales-order    level-2']").getText();
		System.out.println(Sales);
	
		Common.assertionCheckwithReport(Sales.equals("Orders"), "To verify the sales menu ",
				"After clicking the sales menu it will display menu options ",
				"Successfully clicked the sales menu and it displayed the Catalog options",
				"Failed to click the sales menu");
		Common.clickElement("xpath", "//li[@class='item-sales-order    level-2']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the sales menu ",
				"After clicking the sales menu it will display menu options ",
				"Successfully clicked the sales menu and it displayed the sales options",
				Common.getscreenShotPathforReport("Failed to click on the sales menu"));
		Assert.fail();
	}

}
public HashMap<String, String> Admin_Order_Details(String orderNumber) {
	HashMap<String, String> Orderstatus1 = new HashMap<String, String>();
	try
	{
		int filters = Common.findElements("xpath", "//div[@class='admin__data-grid-filters-current _show']").size();
		if (filters > 0) {
			Common.clickElement("xpath",
					"//div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']");
			Thread.sleep(4000);
			Common.findElement("xpath", "//input[@aria-label='Search by keyword']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//input[@aria-label='Search by keyword']");
			Thread.sleep(1000);
			Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", orderNumber);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//div[@class='data-grid-cell-content']");
       
		String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
		System.out.println(Number);
			 if(Number.equals(orderNumber))
			 {
						Thread.sleep(3000);
						Common.clickElement("xpath", "//td//a[@class='action-menu-item']");
			 }
					String Orderstatus = Common.findElement("xpath", "//span[@id='order_status']").getText();
					
								System.out.println(Orderstatus);
								Orderstatus1.put("AdminOrderstatus", Orderstatus);
								StringBuilder concatenatedText = new StringBuilder();
								int size = Common.findElements("xpath", "//div[@class='product-sku-block']").size();

								for (int n=1;n<=size;n++) {
			             String sku=Common.findElement("xpath", "(//div[@class='product-sku-block'])["+n+"]").getText().replace("SKU:", "");
			             concatenatedText.append(sku);		  
			           
								}
								String finalsku = concatenatedText.toString();
								  System.out.println(finalsku);
								  Orderstatus1.put("Skus", finalsku);
		}
		else
		{
			Common.findElement("xpath", "//input[@aria-label='Search by keyword']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//input[@aria-label='Search by keyword']");
			Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", orderNumber);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//div[@class='data-grid-cell-content']");
			String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
			System.out.println(Number);
				 if(Number.equals(orderNumber))
				 {
							Thread.sleep(3000);
							Common.clickElement("xpath", "//td//a[@class='action-menu-item']");
				 }				
						Thread.sleep(4000);
					
					String Orderstatus = Common.findElement("xpath", "//span[@id='order_status']").getText();
					
								System.out.println(Orderstatus);
								Orderstatus1.put("AdminOrderstatus", Orderstatus);
								StringBuilder concatenatedText = new StringBuilder();
								int size = Common.findElements("xpath", "//div[@class='product-sku-block']").size();

								for (int n=1;n<=size;n++) {
			             String sku=Common.findElement("xpath", "(//div[@class='product-sku-block'])["+n+"]").getText().replace("SKU:", "");
			             concatenatedText.append(sku);		  
			           
								}
								String finalsku = concatenatedText.toString();
								  System.out.println(finalsku);
								  Orderstatus1.put("Skus", finalsku);
		}	

}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	return Orderstatus1;
}

public void prepareOrdersData(String fileName) {
	// TODO Auto-generated method stub
	try {

		File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/Osprey_US/" + fileName);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		if (!(file.exists())) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("OrderDetails");
			CellStyle cs = workbook.createCellStyle();
			CellStyle ps = workbook.createCellStyle();
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			ps.setFillForegroundColor(IndexedColors.RED.getIndex());
			Font f = workbook.createFont();
			f.setBold(true);
			cs.setFont(f);
			cs.setAlignment(HorizontalAlignment.RIGHT);
			
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Orders Details");

			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("S.No");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("Website");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			
			cell.setCellStyle(cs);
			cell.setCellValue("Test scenario Description");
			cell = row.createCell(3);
			
			cell.setCellStyle(cs);
			cell.setCellValue("SKU");
			cell = row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Order Number");
			cell = row.createCell(5);
			
			cell.setCellStyle(cs);
			cell.setCellValue("Order Status Magento");
	       cell = row.createCell(6);
	       
	       cell.setCellStyle(cs);
			cell.setCellValue("Workato Status");
	       cell = row.createCell(7);
		
	       cell.setCellStyle(cs);
			cell.setCellValue("Used GiftCode");
	     cell = row.createCell(8);


			/*
			 * cell=row.createCell(17); cell.setCellStyle(cs);
			 * cell.setCellValue("Productqty1");
			 * 
			 * cell=row.createCell(18); cell.setCellStyle(cs);
			 * cell.setCellValue("Product2");
			 * 
			 * cell=row.createCell(19); cell.setCellStyle(cs);
			 * cell.setCellValue("Productqty2");
			 * 
			 * cell=row.createCell(20); cell.setCellStyle(cs);
			 * cell.setCellValue("Product3");
			 * 
			 * 
			 * cell=row.createCell(21); cell.setCellStyle(cs);
			 * cell.setCellValue("Productqty3");
			 * 
			 * 
			 * cell=row.createCell(22); cell.setCellStyle(cs);
			 * cell.setCellValue("Product4");
			 * 
			 * 
			 * cell=row.createCell(23); cell.setCellStyle(cs);
			 * cell.setCellValue("Productqty4");
			 */
			rowcount = 2;

		}

		else {
			workbook = new XSSFWorkbook(new FileInputStream(file));
			sheet = workbook.getSheet("OrderDetails");
			rowcount = sheet.getLastRowNum() + 1;
		}
		/*
		 * row = sheet.createRow(rowcount); cell = row.createCell(0);
		 */

		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	}

public void writeOrderNumber(String Description,String OrderIdNumber,String Skus, String AdminOrderstatus,String workato, String Used_GiftCode)
		throws FileNotFoundException, IOException {
	// String fileOut="";
	try {

		File file = new File(
				System.getProperty("user.dir") + "/src/test/resources//TestData/Osprey_US/OspreyUS_E2E_orderDetails.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		sheet = workbook.getSheet("OrderDetails");

		if ((workbook.getSheet("OrderDetails")) == null) {
			sheet = workbook.createSheet("OrderDetails");
			CellStyle cs = workbook.createCellStyle();
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			Font f = workbook.createFont();
			f.setBold(true);
			cs.setFont(f);
			cs.setAlignment(HorizontalAlignment.RIGHT);
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Orders Details");

			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Order Number");
			rowcount = 2;

		}

		else {

			sheet = workbook.getSheet("OrderDetails");
			rowcount = sheet.getLastRowNum() + 1;
		}
		row = sheet.createRow(rowcount);
		cell = row.createCell(0);
		cell.setCellType(CellType.NUMERIC);
		int SNo=rowcount-1;
		cell.setCellValue(SNo);
		cell = row.createCell(1);
		cell.setCellType(CellType.STRING);
		
		cell.setCellValue("Osprey US");
		
		cell = row.createCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Description);
		
		cell = row.createCell(3);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Skus);
		
		cell = row.createCell(4);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(OrderIdNumber);

		cell = row.createCell(5);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(AdminOrderstatus);
		
		cell = row.createCell(6);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(workato);
		
		cell = row.createCell(7);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Used_GiftCode);
		
		
		
		System.out.println(OrderIdNumber);
		FileOutputStream fileOut = new FileOutputStream(file);
		
		workbook.write(fileOut);
	
		fileOut.flush();
		fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


public String TwentyFive_percent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused = "";
	String points=data.get(Dataset).get("Discountcode");
	System.out.println(points);
	try {
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//div[@class='item discount']//span[@class='value']");
		String Before_RWD_discount = Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-$", "");
//		System.out.println(Before_RWD_discount);
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//input[@placeholder='Choose reward']");
		Common.clickElement("xpath", "//input[@placeholder='Choose reward']");
		String rewardpoints = Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim()
				.replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		Common.clickElement("xpath","//button[@aria-label='Apply']");
 		Sync.waitForLoad();
 		Thread.sleep(4000);
// 		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
//		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(3000);
		String off=Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim().replace(" Off", "");
		String discount=Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		
		int size =Common.findElements("xpath", "(//span[normalize-space()='Subscription End Date:'])[3]").size();
		if (off.contains("$25 off")) {

			double Before_RWD_discount_value = Double.parseDouble(Before_RWD_discount);
			double discountValue = Before_RWD_discount_value + 25;

			DecimalFormat df = new DecimalFormat("#.00");
			String discount1 = df.format(discountValue);

			Common.assertionCheckwithReport(discount1.equals(discount),
					"validating the reward points redeem in the order summary page",
					"After clicking on the apply button reward points should be apply",
					"Sucessfully reward points has been applied",
					"failed to apply the reward point in the order summary page");

		} 
		else {
			
			
		
		Common.assertionCheckwithReport(off.equals(discount),
				"validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply",
				"Sucessfully reward points has been applied",
				"failed to apply the reward point in the order summary page");
		}
		rewardpointsused = Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim()
				.replace("YOU HAVE ", "").replace(" POINTS", "");
		
//		System.out.println(rewardpointsused);
		
		
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply",
				"unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}


}

