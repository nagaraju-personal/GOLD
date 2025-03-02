package TestComponent.OXO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class GoldOxoE2EHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
	}

	public GoldOxoE2EHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Oxo");
			report.createTestcase("OxoTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);

			int size = Common.findElements("xpath", "//a[@aria-label='Go to Home page']").size();
			System.out.println(size);

			Common.assertionCheckwithReport(
					size > 0 && Common.getPageTitle().contains("OXO Good")
							|| Common.getPageTitle().contains("Homepage OXO"),
					"validating store logo", "System directs to the Homepage", "Sucessfully navigate to home page",
					"faield to naviagte to homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs to the Homepage",
					"Unable to navigate Home page",
					Common.getscreenShotPathforReport("failed to get back to homepage"));
			Assert.fail();
		}

	}

	public void coffee_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementClickable("xpath", "//a[contains(@class,'level-0-link')]//span[contains(text(),' Shop')]");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//a[contains(@class,'level-0-link')]//span[contains(text(),' Shop')]");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[contains(@class,'level-0-link')]//span[contains(text(),' Shop')]");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			// Common.clickElement("xpath", "//span[text()='Shop All']");
			Common.clickElement("xpath", "//span[text()='Shop All']");
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'" + category + "')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as" + category + "from navigation menu ", expectedResult,
					"Selected the " + category + " category", "User unabel to click" + category + "");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product category as" + category + "from navigation menu ",
					expectedResult, "Unable to Selected the " + category + " category",
					Common.getscreenShot("Failed to click on the" + category + ""));

			Assert.fail();

		}
	}

	public void babytoddler_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "//a[contains(@class,'level-0-link')]//span[contains(text(),' Shop')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[contains(@class,'level-0-link')]//span[contains(text(),' Shop')]");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[contains(@class,'level-0-link')]//span[contains(text(),' Shop')]");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
//			Common.clickElement("xpath", "//span[text()='Shop All']");
			Common.clickElement("xpath", "//span[text()='Feeding & Drinking']");
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'" + category + "')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as" + category + "from navigation menu ", expectedResult,
					"Selected the " + category + " category", "User unabel to click" + category + "");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product category as" + category + "from navigation menu ",
					expectedResult, "Unable to Selected the " + category + " category",
					Common.getscreenShot("Failed to click on the" + category + ""));

			Assert.fail();
		}
	}

	public void addtocart(String Dataset) {

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image') or @loading='lazy' and @itemprop]");
//				Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image') or @loading='lazy' and @itemprop]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
		
				Common.scrollIntoView("xpath", "//img[contains(@alt,'" + products + "')]");
				Sync.waitElementPresent(30,"xpath", "//img[contains(@alt,'" + products + "')]");
//				Common.mouseOver("xpath", "//img[@alt='" + products + "']");

			Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			product_quantity(Dataset);
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//button[@title='Add to Cart']");
			Common.clickElement("xpath", "//button[@title='Add to Cart']");

			Sync.waitPageLoad();
			Thread.sleep(4000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			
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

	public void Configurable_addtocart_pdp(String Dataset) {
		String product = data.get(Dataset).get("Products");
		String productcolor = data.get(Dataset).get("Color");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image') or @loading='lazy' and @itemprop]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image') or @loading='lazy' and @itemprop]");
				String s = webelementslist.get(i).getAttribute("src");
				Thread.sleep(3000);
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad();
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");

			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");

//			click_UGC();
			product_quantity(Dataset);
			Common.scrollIntoView("xpath", "(//button[@title='Add to Cart'])[1]");
			Common.clickElement("xpath", "(//button[@title='Add to Cart'])[1]");
			Thread.sleep(4000);
//			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}


	}
	
	
	public void group_Addtocart(String Dataset) {
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
//				Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image')]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.scrollIntoView("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
//			Common.mouseOver("xpath", "//img[@alt='" + products + "']");

			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
//			product_quantity(Dataset);
			product_Qty_Grouped(Dataset);
			Sync.waitPageLoad();
//			click_UGC();
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");

			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

	public void product_Qty_Grouped(String Dataset) throws Exception {
		String quantity = data.get(Dataset).get("Quantity");
		int subproductsList = Common
				.findElements("xpath", "//div[@class='m-grouped__items']//div[@class='m-product-upsell__item']").size();
		System.out.println(quantity);
		System.out.println(subproductsList);

		for (int i = 0; i < subproductsList; i++) {

			int value = i + 1;

			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='m-grouped__items']//div[" + value + "]//div[@class=' m-grouped__control-qty']");

			System.out.println(ListOfSubproducts);
			for (int j = 0; j < ListOfSubproducts.size(); j++) {

				if (ListOfSubproducts.get(j).getAttribute("class").contains("m-grouped__con")) {
					Thread.sleep(3000);
					System.out.println(ListOfSubproducts);
//                            ListOfSubproducts.get(j).click();
					Thread.sleep(3000);
					Common.dropdown("xpath", "//div[@class='m-select-menu__wrapper']//select[@class='a-select-menu']",
							Common.SelectBy.VALUE, quantity);
//                       String Quantity=Common.findElement("xpath", "//div[@class='m-grouped__items']//div["+value+"]//div[@class=' m-grouped__control-qty']//input").getAttribute("value");
//                        Thread.sleep(3000);
//                       System.out.println(Quantity);   
//                     Common.assertionCheckwithReport(Quantity.equals(Dataset),"Verifying the products quantity ", "Quantity should be selected for each and every product in Grouped Bundle", "successfully Quantity has been selected for each and every product", "Failed to select the product quantity for the grouped bundle");
				} else {
					break;
				}

			}
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
//			Thread.sleep(6000);
			String yopto = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']").getAttribute("aria-label");
//			Thread.sleep(6000);
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
			Assert.fail();
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

	public void minicart_Checkout() {

		try {
			Thread.sleep(1000);
//			click_minicart();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String minicart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Checkout')]");
			Common.clickElement("xpath", "//a[contains(text(),'Checkout')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"),
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
		try {

			Thread.sleep(9000);
//			Common.scrollIntoView("xpath", "//a[contains(@class,'c-mini')]");
			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
//			Common.javascriptclickElement("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart = Common.findElement("xpath", "//button[@id='menu-cart-icon']").getAttribute("aria-expanded");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("true"), "To validate the minicart popup",
					"Should display the mini cart", "Mini cart is displayed", "mini cart is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "Should display the mini cart",
					"unable to  display the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}

	public String addDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String method = "";

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
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(3000);
//			Common.textBoxInputClear("name", "postcode");
//			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
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
		try {

			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {

				Sync.waitElementPresent(30, "xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");
				method = Common.findElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]")
						.getText();
				Common.clickElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");

//			Sync.waitElementPresent(30, "xpath", "//input[@value='flatrate_flatrate']");
//			Common.clickElement("xpath", "//input[@value='flatrate_flatrate']");
			}

			expectedResult = "shipping address is filled in to the fields";
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");

			int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();

			if (errorsize >= 0) {
				ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
						"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
			} else {

				ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas",
						expectedResult, "failed to add a addres in the filled",
						Common.getscreenShotPathforReport("failed to add a address"));

				Assert.fail();
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
					"failed to add a addres in the filled",
					Common.getscreenShotPathforReport("failed to add a address"));

			Assert.fail();
		}
		return method;
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
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
//			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
//					data.get(dataSet).get("Street"));
//			String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");

			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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

	public void addDeliveryAddress_Guest(String dataSet) throws Exception {

		try {
			Thread.sleep(2000);
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

			} else {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Prod Email"));
			}
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			if (Common.getCurrentURL().contains("preprod")) {
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
			} else {
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Prod Email"));
			}

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("id", "shipping-firstname", data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("id", "shipping-lastname", data.get(dataSet).get("LastName"));
			Common.clickElement("id", "shipping-street-0");
			Common.textBoxInput("id", "shipping-street-0", data.get(dataSet).get("Street"));
			String Text = Common.getText("id", "shipping-street-0");

			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.findElement("id", "shipping-city").clear();
			Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
//			Common.textBoxInputClear("name", "postcode");
//			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));

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

	public void select_Shipping_Method(String Dataset) {
		String method = data.get(Dataset).get("methods");

		try {
			Thread.sleep(15000);
			int size = Common.findElements("xpath", "//label[contains(@for,'shipping-method')]").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//span[text()='" + method + "']");
				Common.clickElement("xpath", "//span[text()='" + method + "']");
			} else {

				Common.refreshpage();
				Thread.sleep(5000);
				Sync.waitElementPresent(30, "xpath", "//span[text()='" + method + "']");
				Common.clickElement("xpath", "//span[text()='" + method + "']");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the " + method + " shipping method",
					"Select the " + method + " shipping method in shipping page ",
					"failed to select the " + method + " shipping method ",
					Common.getscreenShotPathforReport("failed select " + method + " shipping method"));

			Assert.fail();
		}
	}

	public void selectStandedshippingaddress() {
		try {

			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
				Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
			} else {

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
		String expectedResult = "click the submit button to navigate to payment page";
		try {
			System.out.println(Common.getCurrentURL());
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
					"failed to click the submitbutton",
					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
			Assert.fail();
		}
	}

	public void validatingErrormessageShippingpage_negative() {
		int Firstname_Error = Common.findElements("xpath", "//li[@data-msg-field='firstname']").size();
		int Lastname_Error=Common.findElements("xpath", "//li[@data-msg-field='lastname']").size();
		int Address_Error =Common.findElements("xpath", "//li[@data-msg-field='street[0]']").size();
		int Phn_Error= Common.findElements("xpath", "//li[@data-msg-field='telephone']").size();
		String expectedResult = "Error message will dispaly when we miss the data in fields ";
		if (Firstname_Error >0 && Lastname_Error>0&& Address_Error>0&& Phn_Error>0) {
			System.out.println("Error Message displayed");
			ExtenantReportUtils.addPassLog("validating the shippingPage error message", expectedResult,
					"sucessfully  dispaly error message", Common.getscreenShotPathforReport("errormessagenegative"));
		} else {

			ExtenantReportUtils.addFailedLog("validating the shippingPage error message", expectedResult,
					"failed to display error message", Common.getscreenShotPathforReport("failederrormessage"));

			Assert.fail();
		}
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
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
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

			Sync.waitElementPresent("xpath", "//input[@id='shipping-postcode']");
			String code = Common.findElement("xpath", "//input[@id='shipping-postcode']").getAttribute("value");
			System.out.println(code);
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
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

				int zipcode = Common.findElements("xpath", "//input[@id='Field-postalCodeInput']").size();
				System.out.println(zipcode);
				

				if (zipcode > 0) {

					Sync.waitElementPresent("xpath", "//input[@id='Field-postalCodeInput']");
					Common.textBoxInput("xpath", "//input[@id='Field-postalCodeInput']", code);
					
				}

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
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
					Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
					Thread.sleep(40000);
					if (Common.getCurrentURL().contains("/checkout")) {
						String sucessmessage = Common.getText("xpath",
								"//div[contains(@class,'checkout-success')]//h1");
						System.out.println(sucessmessage);

					} else if (Common.getCurrentURL().contains("/success/")) {
						String sucessmessage = Common.getText("xpath",
								" //h1[normalize-space()='Thank you for your purchase!']");
						System.out.println(sucessmessage);
					} else {
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

			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

		expectedResult = "credit card fields are filled with the data";

		return Number;
	}

	public void click_singinButton() {
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");

			Common.clickElement("xpath", "//a[@id='customer.header.sign.in.link']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("customer/account/login"),
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



	public void Usersignin(String dataSet) {

		try {
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Sign In']");

			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("OXO") || Common.getPageTitle().contains("My Wish List")
							|| Common.getPageTitle().contains("Dashboard")
							|| Common.getPageTitle().contains("Checkout"),
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

	public void addDeliveryAddress_registerUser(String dataSet) {
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

				
				Thread.sleep(4000);
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
	
public String Gift_card(String dataSet) {
		String code="";
		try
		{
			String URL = Common.getCurrentURL();
			System.out.println(URL);
			if(URL.contains("stage")|| URL.contains("preprod")) {
			Thread.sleep(3000);
			if(Common.findElement("xpath", "//button[contains(text(),'Add Gift Card')]").getAttribute("title").contains("Show items"))
			{
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");	
		Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
			}
		Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("GiftCard_Preprod"));
		code=data.get(dataSet).get("GiftCard_Preprod");
		Common.actionsKeyPress(Keys.ARROW_UP);
		Common.clickElement("xpath","//button[@aria-label='Add Code']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
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
//   Common.refreshpage();
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

	public void click_Createaccount() {

		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='Create an Account']");
			Common.clickElement("xpath", "//a[@title='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Create an Account"),
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

	public String create_account(String Dataset) {
		String email="";
		String Product=data.get(Dataset).get("Products");
		try {

			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
//			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("UserName"));
			
			
			Common.textBoxInput("xpath", "//input[@id='email_address']", data.get(Dataset).get("UserName"));
			email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			System.out.println(data.get(Dataset).get("Password"));
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			System.out.println(data.get(Dataset).get("Confirm Password"));
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitImplicit(30);
			Thread.sleep(8000);
			String message = Common.findElement("xpath", "//span[@x-html='message.text']").getText();
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
//			String message = Common.findElement("id", "validation-classes").getCssValue("color");
//			String redcolor = Color.fromString(message).asHex();
//			System.out.println(redcolor);
//			String message1 = Common.findElement("id", "validation-length").getCssValue("color");
//			String greencolor = Color.fromString(message1).asHex();
//			System.out.println(greencolor);
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

	public void minicart_validation(String Dataset) {
		// TODO Auto-generated method stub
		String UpdataedQuntityinminicart = data.get(Dataset).get("Quantity");
		String symbol=data.get(Dataset).get("Symbol");
		try {

			String Subtotal = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
					.replace(symbol, "");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			Sync.waitElementPresent("xpath", "(//select[@name='qty'])[2]");
//			Common.clickElement("xpath", "(//select[@name='qty'])[2]");
			Common.dropdown("xpath", "(//select[@name='qty'])[2]", Common.SelectBy.VALUE,
					UpdataedQuntityinminicart);

			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String cart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
			System.out.println(cart);
			String Subtotal2 = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
					.replace(symbol, "");
			System.out.println(Subtotal2);
			Float subtotalvalue2 = Float.parseFloat(Subtotal2);
			Float Total = subtotalvalue * 3;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			Thread.sleep(2000);
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
			Assert.fail();
		}

	}
	public void discountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input.";

		try {

			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Discount Code')]");
			Common.scrollIntoView("xpath", "//button[contains(text(),'Add Discount Code')]");
			Common.clickElement("xpath", "//button[contains(text(),'Add Discount Code')]");
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent("id", "discount-code");

				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
			} else {
				Sync.waitElementPresent("id", "discount-code");

				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("ProdDiscountcode"));
			}
			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//span[contains(text(),'Apply Code')]");
			Common.clickElement("xpath", "//span[contains(text(),'Apply Code')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//div[@ui-id='message-success']//span");
			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully applied."),
					"verifying pomocode", expectedResult, "promotion code working as expected",
					"Promation code is not applied");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));

			Assert.fail();

		}
	}

	public void updtePayementcrditcard_WithInvalidData(String dataSet) throws Exception {
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
			Assert.fail();
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
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Sync.waitPageLoad();
				Thread.sleep(5000);
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

				Assert.fail();

			}
		}

		else {
			Common.clickElementStale("xpath", "//span[contains(text(),'Change Billing Address')]");

			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
				try {
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				String message = Common.findElement("xpath", "//div[text()='You saved the address.']").getText();

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
				Assert.fail();

			}
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

	public void newuseraddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", Utils.getEmailid());
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", Utils.getEmailid());

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
			String shopping = Common.findElement("xpath", "//span[text()='Coffee & Beverage']//parent::a")
					.getAttribute("href");
			String kitchen = Common.findElement("xpath", "//span[text()='Kitchenware']//parent::a")
					.getAttribute("href");
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "(//button[@title='Show Password'])[1]");
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "(//button[@title='Show Password'])[1]");
//			String accounttext=Common.findElement("xpath", "//div[@data-appearance='full-bleed']//p").getText();
			String accounttext = Common.findElement("xpath", "//div[@class='order-success-registration-form']/h3")
					.getText();
			String confirmpassword = Common.findElement("xpath", "//input[@name='password_confirmation']")
					.getAttribute("type");
			String password = Common.findElement("xpath", "//input[@name='password']").getAttribute("type");
//			String Message = Common.findElement("id", "validation-classes").getCssValue("color");
//			String Greencolor = Color.fromString(Message).asHex();
//			System.out.println(Greencolor);
//			String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
//			System.out.println(Message1);
			Common.assertionCheckwithReport(
					shopping.contains("/shop/coffee-beverage") && kitchen.contains("kitchenware")
							&& confirmpassword.equals("text") && password.equals("text")
							&& accounttext.contains("Create an Account"),
					"validating the order confirmation page",
					"User should able to view all details in the order confirmation page",
					"Sucessfully all details has been displayed in the order confirmation",
					"Failed to display all details in the order confirmation page");

			Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
			Common.clickElement("xpath", "//label[@for='is_subscribed']");
			Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();
//			Common.assertionCheckwithReport(confirmpassword.equals("password") && password.equals("password"),
//					"validating the password field changed to dots",
//					"After clicking on the eye icon it should be change to dots",
//					"Sucessfully passwords has been changed to dots after clicking on eye icon",
//					"Failed change passwords into dots after clicking on eye icon");

			Sync.waitElementPresent(30, "xpath", "//span[text()='Create an Account']");
			Common.clickElement("xpath", "//span[text()='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Thank you for registering with OXO Store.']");
			String message = Common.findElement("xpath", "//span[text()='Thank you for registering with OXO Store.']")
					.getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Dashboard")
							&& message.contains("Thank you for registering with OXO Store."),
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

	public void My_Favorites() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Favorites']");
			Common.clickElement("xpath", "//a[text()='My Favorites']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
					"validating the Navigation to the My Favorites page",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
					"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
					"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My Favorites page",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
					"Unable to Navigates the user to My Favorites page after clicking on the My Favorites CTA",
					Common.getscreenShot(
							"Failed to Navigate to the My Favorites page after Clicking on My Favorites CTA"));
			Assert.fail();
		}

	}

	public void Addtocart_From_MyFavorites(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.mouseOverClick("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
//                Thread.sleep(3000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				String msg = Common.getText("xpath", "//div[@data-ui-id='message-success']//div/a");
				System.out.println(message);
				System.out.println(msg);
				Common.assertionCheckwithReport(
						message.contains("has been added to your Wish List") || msg.contains("here"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
				String Whishlistproduct = Common
						.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
				System.out.println(Whishlistproduct);
				String product = data.get(Dataset).get("Products");
				System.out.println(product);
				if (Whishlistproduct.equals(product)) {
//                	Sync.waitElementPresent(30, "xpath", "//img[@alt='" +product+ "']");
//        			Common.mouseOver("xpath", "//img[@alt='" +product+ "']");
					Sync.waitElementPresent(30, "xpath", "//a[@title='" + product + "']/parent::div");
					Common.mouseOver("xpath", "//a[@title='" + product + "']/parent::div");
					Common.clickElement("xpath", "//span[text()='Add to Cart']");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
							.getAttribute("data-ui-id");
					System.out.println(message1);
					Common.assertionCheckwithReport(message1.contains("success"),
							"validating the  product add to the cart", "Product should be add to cart",
							"Sucessfully product added to the cart ", "failed to add product to the cart");
					int minicart = Common.findElements("xpath", "//span[@class='c-mini-cart__counter']").size();
					System.out.println(minicart);
					if (minicart > 0) {
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
				for (int i = 0; i <= 10; i++) {
					Sync.waitElementPresent("xpath", "//li[@class='product-item']//a");
					List<WebElement> webelementslist = Common.findElements("xpath", "//li[@class='product-item']//a");

					String s = webelementslist.get(i).getAttribute("href");
					System.out.println(s);

					Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
					Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");
					Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
					List<WebElement> element = Common.findElements("xpath", "//span[text()='Add to Cart']");
					Thread.sleep(6000);
					element.get(0).click();
					String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
							.getAttribute("data-ui-id");
					Thread.sleep(4000);
					System.out.println(message);
					Common.assertionCheckwithReport(message.contains("success"),
							"validating the  product add to the cart", "Product should be add to cart",
							"Sucessfully product added to the cart ", "failed to add product to the cart");
					int minicart = Common.findElements("xpath", "//span[@class='c-mini-cart__counter']").size();
					System.out.println(minicart);
					if (minicart > 0) {
						minicart_Checkout();
					} else {
						Common.refreshpage();
						Sync.waitPageLoad();
						minicart_Checkout();
					}

				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add  product to the cart ", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//button[@id='menu-search-icon']");
			String open = Common.findElement("xpath", "//button[@id='menu-search-icon']").getAttribute("aria-expanded");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("true"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			WebElement serachbar = Common.findElement("xpath", "//input[@id='autocomplete-0-input']");
			serachbar.sendKeys(product);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(8000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
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

	public void click_giftcard() {
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

	public void newregistry_CTA(String Dataset) {
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

	public void Baby_Registry(String Dataset) {
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
			Assert.fail();
		}
	}

	public void Registrant_Information(String Dataset) {
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

	public void Manage_items() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[@title='Manage Items']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Manage Gift Registry"),
					"validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"successfully Navigated to the Manage Gift Registry",
					"failed to Navigate to the Manage Gift Registry");
			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"Unabel to Navigated to the Manage Gift Registry",
					Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
			Assert.fail();
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

	public void Klarna_Saved_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String email = data.get(dataSet).get("Email");
		String fullname = data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
			Sync.waitPageLoad();

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
				Common.clickElement("xpath", "//span[text()='New payment method']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath", "//button[@value='klarna']");
				Common.scrollIntoView("xpath", "//button[@value='klarna']");
				Common.mouseOverClick("xpath", "//button[@value='klarna']");

				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Common.clickElement("xpath", "//span[text()='Place Order']");
					Sync.waitPageLoad();
					klarna_Saved_Details(dataSet);

				} else {
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna = Common.findElement("xpath", "//button[@value='klarna']//span").getText();
					System.out.println(klarna);
					Common.assertionCheckwithReport(klarna.contains("Klarna"),
							"validating the selection of the klarna method", "klarna should be selected ",
							"klarna is selected", "Failed to select the klarna method in the production environment");
					Common.switchToDefault();

				}

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");

				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Common.clickElement("xpath", "//span[text()='Place Order']");
					Sync.waitPageLoad();
					klarna_Saved_Details(dataSet);
				} else {
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna = Common.findElement("xpath", "//button[@value='klarna']//span").getText();
					System.out.println(klarna);
					Common.assertionCheckwithReport(klarna.contains("Klarna"),
							"validating the selection of the klarna method", "klarna should be selected ",
							"klarna is selected", "Failed to select the klarna method in the production environment");
					Common.switchToDefault();

				}

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation",
					"User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}

	public void klarna_Saved_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order = "";
		String number = data.get(Dataset).get("phone");
		String otp = data.get(Dataset).get("OTP Number");
		String DOB = data.get(Dataset).get("DOB");
		String Cardnumber = data.get(Dataset).get("cardNumber");
		System.out.println(Cardnumber);

		try {
			Sync.waitPageLoad();
			Common.switchWindows();
			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");

			Common.clickElement("xpath", "//input[@name='phone']");
			WebElement clear = Common.findElement("xpath", "//input[@name='phone']");
			clear.sendKeys(Keys.CONTROL + "a");
			clear.sendKeys(Keys.DELETE);

			Common.findElement("xpath", "//input[@name='phone']").sendKeys(number);
			Common.clickElement("xpath", "//button[@id='onContinue']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
			int paymenttype = Common.findElements("xpath", "//p[@id='funding-source-card-issuer']").size();
			if (paymenttype > 0) {
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='SmoothCheckoutPopUp:skip']");
			} else {
				Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");

//				Common.textBoxInput("xpath", "//label//input[@id='cardNumber']", Cardnumber);
				Common.findElement("xpath", "//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath", "//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath", "//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the card details enter in the Kalrna payment",
					"User Should able to Enter Card Details in Klarna Payment",
					"User Unable to enter Card details in the Klarna payment",
					Common.getscreenShotPathforReport("Failed to enter Card details in the Klarna payment"));
			Assert.fail();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
				Sync.waitPageLoad(5000);
				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Thread.sleep(3000);
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase"),
						"verifying the product confirmation",
						"User Should able to Navigate to the order confirmation page",
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

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
				ExtenantReportUtils.addFailedLog("verifying the product confirmation",
						"User Should able to Navigate to the order confirmation page",
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}

		}

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
					String klarna=Common.findElement("xpath", "//button[@value='klarna']").getAttribute("data-testid");
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
			Thread.sleep(6000);
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
			
			
			String klarna=Common.findElement("xpath", "//h1[@id='summary-title']").getText();
			Thread.sleep(3000);
			if(klarna.contains("Confirm and pay"))
			{
				Thread.sleep(4000);
				Common.clickElement("xpath", "//div[@data-testid='summary.payment_preview']");
//				Sync.waitElementPresent("xpath", "//label[@for='pay_now__label']");
//				Common.clickElement("xpath", "//label[@for='pay_now__label']");
//				
//				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//span[text()='Continue']");
				Common.doubleClick("xpath", "//span[text()='Continue']");
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
	
	if(Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p/a").size()>0) {
		order=Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p/a");
		System.out.println(order);
	}
	if(Common.findElements("xpath","//div[contains(@class,'checkout-success container')]//p/a").size()>0) {
		order=	Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p/a");
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
	public String payPal_Payment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(2000);
            Common.scrollIntoView("xpath", "//input[@id='payment-method-paypal_express']");
			Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
			Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
			
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
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");

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
					
					Common.scrollIntoView("xpath", "//input[@id='payment-method-paypal_express']");
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

//			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
//			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
//			System.out.println(payment);
//			if (payment > 0) {
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
				
//				Sync.waitElementPresent(30, "xpath", "//div[@class='stripe-new-payments']//label[@for='stripe-new-payments']");
//				Common.javascriptclickElement("xpath", "//div[@class='stripe-new-payments']//label[@for='stripe-new-payments']");
//				Thread.sleep(3000);	
//				
//				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
//				Common.clickElement("xpath", "//button[@class='action primary checkout']");
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

	public void acceptPrivacy() throws Exception {
		close_Pop_up();
		Common.clickElementStale("id", "truste-consent-button");
	}

	public void click_trackorder() {
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

	public void guestuserorderStatus(String dataSet) {
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
			String orderid = Common.findElement("xpath", "//span[@class='title-lg']").getText();
			System.out.println(orderid);
			String ID=Common.findElement("xpath", "//span[@class='title-lg']").getText().replace("Order #", "");
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

	public void register_userorder_status() {

		click_singinButton();
		Usersignin("AccountDetails");
		click_trackorder();

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

	public void close_Pop_up() throws Exception {

		Sync.waitPageLoad();
		int sizesframe = Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
		if (sizesframe > 0) {
			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");

			Thread.sleep(2000);
			Sync.waitElementVisible("xpath", "//button[@aria-label='Close Modal']");
			Common.clickElement("xpath", "//button[@aria-label='Close Modal']");
			Common.switchToDefault();
		} else {
			int sizeofpopup = Common.findElements("id", "wpx-newsletter-popup").size();
			if (sizeofpopup > 0) {

				Sync.waitElementClickable("xpath", "//button[@aria-label='close']");
				Common.clickElement("xpath", "//button[@aria-label='close']");
			}

		}

	}

	public void edit_Account_info(String dataSet) {
		// TODO Auto-generated method stub
		Accont_Information();
		try {

			
			Thread.sleep(4000);
			Common.clickElement("xpath", "//label[@for='change-password']");
			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Current Password"));
			Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//input[@id='password-confirmation']", data.get(dataSet).get("Confirm Password"));
			
			String message = Common.findElement("xpath", "//div[@class='password-strength-meter']").getCssValue("background-color");
			String greencolor = Color.fromString(message).asHex();
			
			System.out.println(greencolor);
			Common.assertionCheckwithReport(greencolor.equals("#f1f1f1"),
					"validating the cureent password and new password fields",
					"User should able enter data in current password and new password",
					"Sucessfully the data has been entered in new password and current password",
					"Failed to enter data in current password and new password fields");

			Common.clickElement("xpath", "//button[@title='Save Account Information']");
			Thread.sleep(4000);
			String sucessmessage = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			System.out.println(sucessmessage);
			Common.assertionCheckwithReport(sucessmessage.contains("You saved the account information."),
					"Validating the saved account information", "Account information should be saved for the user",
					"Sucessfully account information has been saved ", "failed to save the account information");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the change passwordfor the register user",
					"User enter the valid password", "User failed to proceed to change passowrd ",
					Common.getscreenShotPathforReport("emailpasswordnew"));
			Assert.fail();

		}
	}
	public void changed_password(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", Dataset);
			Common.textBoxInput("id", "pass", "Lotuswave@1234");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[text()='Sign In']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard"),
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

			Assert.fail();

		}
	}

	public void Accont_Information() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//a[@title='Account Information']");
			Common.clickElement("xpath", "//a[@title='Account Information']");
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
			Assert.fail();
		}
	}

	public void Invalid_email_newsletter(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
//			Common.switchFrames("xpath", "//iframe[@title='OXO Gold Footer Signup']");
			Sync.waitElementClickable(30, "xpath", "//input[@id='subscribe-email']");
			Common.textBoxInput("xpath", "//input[@id='subscribe-email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//span[text()='Submit']");
			String Errormessage = Common.findElement("xpath", "//span[@class='error-message']").getText();
			System.out.println(Errormessage); // Error: Please enter a valid email address. (previous error)
			Common.assertionCheckwithReport(Errormessage.equals("Your Email is in an invalid format."),
					"To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", Errormessage,
					"Failed to display the error message for invaild email");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", "Failed to display the error message",
					Common.getscreenShotPathforReport("Failed to see an error message"));

			Assert.fail();

		}
	}

	public void Empty_Email() {
		try {

			Common.textBoxInputClear("xpath", "//input[@id='form_input_email']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[text()='Submit']");
			String Errormessage = Common.findElement("xpath", "//span[@class='error-message']").getText();
			System.out.println(Errormessage); // Error: This field is required. (Previous error)
			Common.assertionCheckwithReport(Errormessage.equals("Your Email is in an invalid format."),
					"To validate the error message for missing email fields",
					"Should display Error Please enter a valid email address.", Errormessage,
					"Error message dispaly unsuccessfull");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Error message ",
					"Should display Error: Please enter a valid email address.", "Failed to dispaly the Error message ",
					Common.getscreenShotPathforReport("User unable to see an error message"));
			Assert.fail();
		}
	}

	public void stayIntouch() throws Exception {

		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Sync.waitElementClickable(30, "xpath", "//input[@id='subscribe-email']");
			Common.textBoxInput("xpath", "//input[@id='subscribe-email']", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Submit']");
			Thread.sleep(5000);
			String Text = Common.getText("xpath", "//span[text()='Thank you for your subscription.']");
			System.out.println(Text);
			String expectedResult = "User gets confirmation message that it was submitted";

			Common.assertionCheckwithReport(Text.contains("Thank you for your subscription"),
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

	public void validateChatboxOptions(String DataSet) {

		try {
			Sync.waitPageLoad();
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");
			Sync.waitElementVisible(30, "xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			Common.mouseOverClick("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");

			String answers = Common.findElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p").getText();
			System.out.println(answers);
			Common.assertionCheckwithReport(answers.contains("Answers"), "To validate the Answers options in Chatbox",
					"Click the Answers option to display the related options",
					"Sucessfully click the answers option button", "Unable to click the Answers option button");

			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the ChatBot on the home page",
					"Open the ChatBot and answers option should be displayed",
					"Unable click on the ChatBot and answers are not displayed",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

		List<WebElement> Answerwebelements = Common.findElements("xpath",
				"//div[contains(@class,'SearchListItem__details')]");

		ArrayList<String> arrayoptionName = new ArrayList<String>();

		for (WebElement answernames : Answerwebelements) {
			arrayoptionName.add(answernames.getText());
		}
//	String[] items = data.get(DataSet).get("OXOAnswers").split(",");

		if (Common.getCurrentURL().contains("pre")) {
			String[] items = data.get(DataSet).get("OXOAnswersPreProd").split(",");

			for (int i = 0; i < items.length; i++) {

				if (arrayoptionName.contains(items[i])) {
				} else {

					ExtenantReportUtils.addFailedLog("To validate the Answers options in chatbox",
							"All the Answer related options are displayed ", "Missed the " + items[i] + "options",
							Common.getscreenShotPathforReport("failed to display answersoptions"));
					Assert.fail();
				}
			}
		}

		else {
			String[] items = data.get(DataSet).get("OXOAnswers").split(",");

			for (int i = 0; i < items.length; i++) {

				if (arrayoptionName.contains(items[i])) {
				} else {

					ExtenantReportUtils.addFailedLog("To validate the Answers options in chatbox",
							"All the Answer related options are displayed ", "Missed the " + items[i] + "options",
							Common.getscreenShotPathforReport("failed to display answersoptions"));
					Assert.fail();
				}
			}

			for (int i = 0; i < items.length; i++) {

				if (arrayoptionName.contains(items[i])) {
				} else {

					ExtenantReportUtils.addFailedLog("To validate the Answers options in chatbox",
							"All the Answer related options are displayed ", "Missed the " + items[i] + "options",
							Common.getscreenShotPathforReport("failed to display answersoptions"));
					Assert.fail();
				}

				ExtenantReportUtils.addPassLog("To Validate the Answers options ",
						"click on the answers options it must display all the options ",
						"Sucessfully displayed the answers options " + arrayoptionName,
						Common.getscreenShotPathforReport("Answervalidation"));
			}
		}
		try {
			String chat = Common.findElement("xpath", "//div[contains(@class,'footer__chatContainer')]/p").getText();
			System.out.println(chat);
			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
//		Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
//		Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");
			Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'newConversationButton')]");
			Common.mouseOverClick("xpath", "//button[contains(@class,'newConversationButton')]");

			Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
			String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();
			System.out.println(welcomemsg);
			Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to OXO!"),
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
			Assert.fail();
		}

	}

	public void clickContact() throws Exception {
		String expectedResult = "It should land successfully on the explore/contact page";

		Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Contact')]");
			Common.scrollIntoView("xpath", "//a[contains(text(),'Contact')]");
			Common.clickElement("xpath", "//a[contains(text(),'Contact')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Common.assertionCheckwithReport(Common.getCurrentURL().contains("contact"),
					"Validating the contatus page navigation", expectedResult, "successfully land to contact page",
					"unabel to load the  contact page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating contact us page", expectedResult,
					"unabel to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
			Assert.fail();

		}

	}

	public void contactUsPage(String dataSet) {
		// TODO Auto-generated method stub

		String expectedResult = "Email us form is visible in tab";
		String country = data.get(dataSet).get("Country");

		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Write to Us']");
			Thread.sleep(2000);
			Sync.waitElementPresent(40, "xpath", "//iframe[(@id='contact-us-form')]");
			Common.switchFrames("xpath", "//iframe[(@id='contact-us-form')]");

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'How can we')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'How can we')]",
					data.get(dataSet).get("Comments"));

			Common.clickElement("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[@name='customerFirstName']");
			Common.textBoxInput("xpath", "//input[@name='customerFirstName']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@name='customerLastName']");
			Common.textBoxInput("xpath", "//input[@name='customerLastName']", data.get(dataSet).get("LastName"));

//		Sync.waitElementPresent("xpath", "//input[@name='company']");
//		Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("Company"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
			Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Country']");
			Common.clickElement("xpath", "//input[@data-label='Country']");
			Sync.waitElementPresent("xpath", "//div[text()='United States']");
			Common.clickElement("xpath", "//div[text()='United States']");
			Sync.waitElementPresent("xpath", "//input[@data-label='Address']");
			Common.textBoxInput("xpath", "//input[@data-label='Address']", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@data-label='City']");
			Common.textBoxInput("xpath", "//input[@data-label='City']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'State')]");
			Common.clickElement("xpath", "//input[contains(@data-label,'State')]");

			Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
			Common.clickElement("xpath", "//div[text()='Alabama']");

			Sync.waitElementPresent("xpath", "//input[@data-label='Zip Code']");
			Common.textBoxInput("xpath", "//input[@data-label='Zip Code']", data.get(dataSet).get("postcode"));

			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@id='conversationHowCanWeHelp']");
			Common.clickElement("xpath", "//div[@id='conversationHowCanWeHelp']");

//		Common.clickElement("xpath", "//div[text()='Order Issues']");
//		Common.clickElement("xpath", "//div[@id='order_issues']/i");
			Common.clickElement("xpath", "//div[text()='General Inquiries']");

			Thread.sleep(4000);
//	     Sync.waitElementPresent("xpath", "//div[@id='conversationOrderIssues']");
//			Common.clickElement("xpath", "//div[@id='conversationOrderIssues']");

			Common.clickElement("xpath", "//div[@id='conversationGeneralInquiries']");

//		Sync.waitElementPresent("xpath", "//div[text()='Billing Issue']");
//		Common.clickElement("xpath", "//div[text()='Billing Issue']");

			Sync.waitElementPresent("xpath", "//div[text()='Blogger']");
			Common.clickElement("xpath", "//div[text()='Blogger']");

//		Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Order')]");
//		Common.textBoxInput("xpath", "//input[contains(@data-label,'Order')]",data.get(dataSet).get("OrderID"));
			Sync.waitElementPresent("xpath", "//textarea[@data-label='Comments']");
			Common.textBoxInput("xpath", "//textarea[@data-label='Comments']", data.get(dataSet).get("Comments"));

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

//		Sync.waitElementPresent("xpath", "//h1[@data-content-type='heading']");
//		Common.scrollIntoView("xpath", "//h1[@data-content-type='heading']");
			int Contactussuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			Common.assertionCheckwithReport(Contactussuccessmessage > 0, "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
		System.out.println(Text);
		expectedResult = "User gets confirmation email with Success message";
		Common.assertionCheckwithReport(Text.contains("Your submission was successful"),
				"verifying contact us confirmation message", expectedResult,
				"User gets confirmation under the same tab", "unabel to load the confirmation form");

	}

	public void Signin_Checkoutpage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("Email"));
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//span[text()='Sign In']");
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
			Assert.fail();
		}

	}

	public void ordersummary_validation() {
		// TODO Auto-generated method stub
		try {
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("$", "");
			Float Taxvalue = Float.parseFloat(Tax);
			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = subtotalvalue + shippingvalue + Taxvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ordertotal);
			System.out.println(ExpectedTotalAmmount2);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Successfully Order summary is displayed in the payment page and fields are displayed",
					"Failed to display the order summary and fileds under order summary");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Unabel to display the Order summary and fields are not displayed in the payment page",
					Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
			Assert.fail();
		}
	}

	public void addtocart_PLP(String Dataset) {
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			Common.refreshpage();
			Thread.sleep(5000);
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
			Common.mouseOver("xpath", "//img[@alt='" + products + "']");

//		Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");

			Sync.waitPageLoad();
			Thread.sleep(5000);
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

	public void review(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		try {
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//span[@class='yotpo-stars']");
				Common.clickElement("xpath", "//span[@class='yotpo-stars']");
				Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
				String form = Common.getText("xpath", "//span[text()='Write A Review']");
				System.out.println(form);
				Common.assertionCheckwithReport(form.contains("Write A Review"), "verifying the write a review button",
						"Write a review should be appear in the PDP page",
						"Sucessfully write a review button has been displayed in PDP page",
						"Failed to display the write a review button in PDP page");
				Common.clickElement("xpath", "//span[text()='Write A Review']");
				

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
					"User Unable to click write review option  ",
					Common.getscreenShotPathforReport("User Failed to click write review option "));
			Assert.fail();
		}
		try {
			String expectedResult = "Sucessfully title input box has been displayed";
			score(data.get(Dataset).get("score"));
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Sync.waitElementPresent("xpath", "//input[@name='review_title']");
			Common.scrollIntoView("xpath", "//input[@name='review_title']");
			int title = Common.findElements("xpath", "//input[@name='review_title']").size();
			Common.assertionCheckwithReport(title > 0, "verifying the title page",
					"title input box should be displayed", expectedResult, "User Unable to display the title box");
//		Common.textBoxInput("xpath", "//input[@name='review_title']",data.get(Dataset).get("title"));
			Common.clickAndtextBoxInput("xpath", "//input[@name='review_title']", data.get(Dataset).get("title"));
			Common.textBoxInput("xpath", "//textarea[@name='review_content']", data.get(Dataset).get("Review"));
			Common.textBoxInput("xpath", "//input[@name='display_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//input[@value='Post']");
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='yotpo-thank-you']").getAttribute("aria-label");
			Common.assertionCheckwithReport(message.equals("Thank you for posting a review"),
					"verifying the post for the product review",
					"product review should be submit after clicking on post",
					"Sucessfully Thank you message has been displayed ", "Failed to display the Thank you message ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the post for the product review",
					"product review should be submit after clicking on post",
					"Unable to display the Thank you message after clickng on post ",
					Common.getscreenShotPathforReport("Failed to display the thank you message"));
			Assert.fail();

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

	public void my_Account() {
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
			Assert.fail();
		}
	}

	public void stored_Payments(String Dataset) {
		// TODO Auto-generated method stub
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

	public void stored_Payment_Methods_Delete() {

		try {

			Sync.waitElementPresent(30, "xpath", "//a[contains(@href,'paymentmethods/delete')]/span");
			Common.clickElement("xpath", "//a[contains(@href,'paymentmethods/delete')]/span");
			String delete = Common.findElement("xpath", "//th[text()='Payment Method']").getText();

			Common.assertionCheckwithReport(delete.contains("Payment Method"),
					"validating the Navigation to the Stored Payment page",
					"After Clicking on Stored Payment Methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the Stored Payment Methods CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on Stored Payment Methods CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Stored Payment page",
					"After Clicking on Stored Payment Methods CTA user should be navigate to the My Payment Methods page",
					"Unable to Navigates the user to My Payment Methods page after clicking on the Stored Payment Methods",
					Common.getscreenShot(
							"Failed to Navigate to the My Payment Methods page after Clicking on Stored Payment Methods CTA"));
			Assert.fail();
		}
	}

	public void click_Voulantry_Recall() throws Exception {
		String expectedResult = "It should land successfully on the explore/product-recall";

//	Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//a[contains(text(),'Voluntary')]");
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Voluntary')]");
			Common.clickElement("xpath", "//a[contains(text(),'Voluntary')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("recall") || Common.getPageTitle().contains("Recall"),
					"Validating the Product recall page navigation", expectedResult,
					"successfully land to Product recall page", "unable to load the Product recall page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Product recall  page", expectedResult,
					"unabel to load the product recall page",
					Common.getscreenShotPathforReport("Product recall page link"));
			Assert.fail();

		}
	}

	public void voluntary_Recall(String dataSet) {
		// TODO Auto-generated method stub

		String expectedResult = " Product recall form is visible in tab with success message";
		String country = data.get(dataSet).get("Country");

		try {
			Common.clickElement("xpath", "//strong[text()='Contact Us']");
			Common.switchWindows();

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'How can we')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'How can we')]",
					data.get(dataSet).get("Comments"));

			Common.clickElement("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[@name='customerFirstName']");
			Common.textBoxInput("xpath", "//input[@name='customerFirstName']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@name='customerLastName']");
			Common.textBoxInput("xpath", "//input[@name='customerLastName']", data.get(dataSet).get("LastName"));

//		Sync.waitElementPresent("xpath", "//input[@name='company']");
//		Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("Company"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
			Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));
			Sync.waitElementPresent("xpath", "//input[@data-label='Country']");
			Common.clickElement("xpath", "//input[@data-label='Country']");

			Sync.waitElementPresent("xpath", "//div[text()='United States']");
			Common.clickElement("xpath", "//div[text()='United States']");
			Sync.waitElementPresent("xpath", "//input[@data-label='Address']");
			Common.textBoxInput("xpath", "//input[@data-label='Address']", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@data-label='City']");
			Common.textBoxInput("xpath", "//input[@data-label='City']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'State')]");
			Common.clickElement("xpath", "//input[contains(@data-label,'State')]");

			Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
			Common.clickElement("xpath", "//div[text()='Alabama']");

			Sync.waitElementPresent("xpath", "//input[@data-label='Zip Code']");
			Common.textBoxInput("xpath", "//input[@data-label='Zip Code']", data.get(dataSet).get("postcode"));

			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@id='conversationHowCanWeHelp']");
			Common.clickElement("xpath", "//div[@id='conversationHowCanWeHelp']");

			Common.clickElement("xpath", "//div[text()='General Inquiries']");

			Thread.sleep(4000);

			Common.clickElement("xpath", "//div[@id='conversationGeneralInquiries']");

			Sync.waitElementPresent("xpath", "//div[text()='Blogger']");
			Common.clickElement("xpath", "//div[text()='Blogger']");

			Sync.waitElementPresent("xpath", "//textarea[@data-label='Comments']");
			Common.textBoxInput("xpath", "//textarea[@data-label='Comments']", data.get(dataSet).get("Comments"));

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			// =======================

			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int recallsuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			Common.assertionCheckwithReport(recallsuccessmessage > 0, "verifying Product recall Success message ",
					"Success message should be Displayed", "Product recall Success message displayed ",
					"failed to dispaly success message");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"Product recall form data enter without any error message", "Product recall page getting error ",
					Common.getscreenShotPathforReport("Product recall page"));
			Assert.fail();

		}

		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
		expectedResult = "User gets confirmation under the same tab. It includes Success message.";
		Common.assertionCheckwithReport(Text.contains("Your submission was successful"),
				"verifying Product recall confirmation message", expectedResult,
				"User gets confirmation under the same tab", "unable to load the confirmation form");

	}

	public String Guest_BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		String update = "";
		String Shipping="";
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

	public void edit_BillingAddress_gustuser(String dataSet) {

		try {
			Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");

			Sync.waitElementPresent("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']");

			int billingaddressform = Common.findElements("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']")
					.size();

			Common.assertionCheckwithReport(billingaddressform > 0, "Filling the Billing address ",
					"user editing  the billing address", "user sucessfully open the billing address from ",
					"faield open the bulling address from");

			Common.textBoxInput("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='lastname']");
			Common.textBoxInput("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='lastname']",
					data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
			Common.textBoxInput("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']",
					data.get(dataSet).get("Street"));

			Common.textBoxInput("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']",
					data.get(dataSet).get("City"));

			Common.dropdown("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']",
					Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Thread.sleep(4000);

			// Thread.sleep(4000);
			Common.textBoxInput("xpath",
					"//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath",
					"(//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Common.clickElement("xpath", "//button[@class='action action-update']");

			Thread.sleep(5000);
			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]")
					.size();
			System.out.println("error messagess    " + sizeerrormessage);
			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying Billing addres filling ",
					"user will fill the all the billing address", "user fill the shipping address click save button",
					"faield to add new billing address");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Billing addres filling",
					"user will fill the all the Billing address", "faield to add new billing address",
					Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
			Assert.fail();

		}
	}

	public void country_selector() {

		String Country;
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.refreshpage();
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.END);
			List<WebElement> country = Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
			System.out.println(country.size());
			for (int i = 0; i < country.size(); i++) {

				List<WebElement> select = Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
				Sync.waitPageLoad();
				Common.actionsKeyPress(Keys.END);
				Sync.waitElementPresent(50, "xpath", "//img[contains(@src,'country-selector')]");
				Common.scrollIntoView("xpath", "//img[contains(@src,'country-selector')]");
				Common.clickElement("xpath", "//img[contains(@src,'country-selector')]");
				Thread.sleep(3000);
				Country = select.get(i).getText();
				select.get(i).click();
				if (Country.contains("United States")) {

					Common.clickElement("xpath", "//button[@data-role='closeBtn']");
					ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
							"click on the country should navigate to the  " + Country + "Page",
							"successfully page navigating to " + Country + "PAGE",
							Common.getscreenShotPathforReport(Country));
				} else {
					Common.clickElement("xpath", "//span[contains(text(),'Confirm')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					Common.navigateBack();
					ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
							"click on the country should navigate to the  " + Country + "Page",
							"successfully page navigating to " + Country + "PAGE",
							Common.getscreenShotPathforReport(Country));
				}

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the country selection page navigation",
					"After Clicking on the selected country it should navigate to the respective country page",
					"Unable to navigate to the respective country page after clicking on the selected country",
					Common.getscreenShot(
							"Failed to navigate to the respective country page after clicking on the selected country"));
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

			Common.assertionCheckwithReport(Common.getCurrentURL().contains("registration"),
					"Validating the Product Registration page navigation", expectedResult,
					"successfully land to Product Registration page", "unable to load the Product Registration page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Product Registration  page", expectedResult,
					"unable to load the Product Registration page",
					Common.getscreenShotPathforReport("Product Registration page link"));
			Assert.fail();

		}
	}

	public void product_Registration(String dataSet) {

		String expectedResult = " Product registration form is visible in tab with success message";
		String country = data.get(dataSet).get("Country");
		String state = data.get(dataSet).get("Region");
		String purchased = data.get(dataSet).get("PurchasedAt");
		String SKUitemNumber = data.get(dataSet).get("SKUitemNumber");
		String feedback = ("Good Product");
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Register Your Product']");

			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'registration')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'registration')]");

			Sync.waitElementPresent("xpath", "//input[@data-label='First Name']");
			Common.textBoxInput("xpath", "//input[@data-label='First Name']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Last Name']");
			Common.textBoxInput("xpath", "//input[@data-label='Last Name']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Email']");

			Common.textBoxInput("xpath", "//input[@data-label='Email']", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
			Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Street')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'Street')]", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'City')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'City')]", data.get(dataSet).get("City"));

			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@id='conversationStateProvince']");
			Common.clickElement("xpath", "//div[@id='conversationStateProvince']");

			Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
			Common.clickElement("xpath", "//div[text()='" + state + "']");

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Zip Code ')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'Zip Code ')]",
					data.get(dataSet).get("postcode"));

//		Common.textBoxInput("xpath", "//input[contains(@data-label,'Item Number')]", data.get(dataSet).get("SKUitemNumber"));

			Common.clickElement("xpath", "//div[@id='conversationProductItemDescription']");
//		Common.dropdown("xpath", "//div[@id='conversationProductItemDescription']", Common.SelectBy.VALUE, SKUitemNumber);
			Common.clickElement("xpath", "//div[contains(@Value,'" + SKUitemNumber + "')]");

			Common.textBoxInput("xpath", "//input[@data-label='Serial Number']", data.get(dataSet).get("SerialNumber"));

			Common.textBoxInput("xpath", "//input[@data-label='Manufacture Date ']",
					data.get(dataSet).get("ManufactureDate"));

			Sync.waitElementPresent("xpath", "//div[contains(@id,'WherePurchased')]");
			Common.clickElement("xpath", "//div[contains(@id,'WherePurchased')]");

			Sync.waitElementPresent("xpath", "//div[text()='" + purchased + "']");
			Common.clickElement("xpath", "//div[text()='" + purchased + "']");

			Common.textBoxInput("xpath", "//input[@data-label='Price']", data.get(dataSet).get("Price"));

//		Sync.waitElementPresent("xpath", "//textarea[@data-label='City Purchased']");
//		Common.textBoxInput("xpath", "//textarea[@data-label='City Purchased']",data.get(dataSet).get("City"));
//		
//		Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
//		Common.clickElement("xpath", "//div[@id='conversationCountry']");

			Common.textBoxInput("xpath", "//input[@data-label='Purchase Date']", data.get(dataSet).get("Date"));

//		Sync.waitElementPresent("xpath", "//div[text()='"+country+"']");
//		Common.clickElement("xpath", "//div[text()='"+country+"']");

			Common.textBoxInput("xpath", "//textarea[@name='messagePreview']", feedback);

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int registrationsuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			Common.assertionCheckwithReport(registrationsuccessmessage > 0,
					"verifying Product registration Success message ", "Success message should be Displayed",
					"Product registration  Success message displayed ", "failed to dispaly success message");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Product registration  form",
					"Product registration form data enter without any error message",
					"Product registration  page getting error ",
					Common.getscreenShotPathforReport("Product registration  page"));
			Assert.fail();

		}

		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
		expectedResult = "User gets confirmation under the same tab. It includes Success message.";
		Common.assertionCheckwithReport(Text.contains("Your product has been successfully registered!"),
				"verifying Product registration confirmation message", expectedResult,
				"User gets confirmation for Product registration ", "unable to load the confirmation form");

	}

	public void product_Registration_invalid(String dataSet) {

		String expectedResult = " Product registration form is visible in tab with success message";
		String country = data.get(dataSet).get("Country");
		String state = data.get(dataSet).get("Region");
		String purchased = data.get(dataSet).get("PurchasedAt");
		try {

			Common.clickElement("xpath", "//span[text()='Write to Us']");
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'registration')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'registration')]");

			Sync.waitElementPresent("xpath", "//input[@data-label='First Name']");
			Common.textBoxInput("xpath", "//input[@data-label='First Name']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Last Name']");
			Common.textBoxInput("xpath", "//input[@data-label='Last Name']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Email']");

			Common.textBoxInput("xpath", "//input[@data-label='Email']", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
			Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Street')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'Street')]", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'City')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'City')]", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
			Common.clickElement("xpath", "//div[@id='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
			Common.clickElement("xpath", "//div[text()='" + state + "']");

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'Zip Code')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'Zip Code')]",
					data.get(dataSet).get("postcode"));

//		Common.textBoxInput("xpath", "//input[contains(@data-label,'Item Number')]", data.get(dataSet).get("SKUitemNumber"));

			Common.textBoxInput("xpath", "//input[@data-label='Serial Number']", data.get(dataSet).get("SerialNumber"));

			Common.textBoxInput("xpath", "//input[@data-label='Manufacture Date ']",
					data.get(dataSet).get("ManufactureDate"));

			Sync.waitElementPresent("xpath", "//div[contains(@id,'WherePurchased')]");
			Common.clickElement("xpath", "//div[contains(@id,'WherePurchased')]");

			Sync.waitElementPresent("xpath", "//div[text()='" + purchased + "']");
			Common.clickElement("xpath", "//div[text()='" + purchased + "']");

			Common.textBoxInput("xpath", "//input[@data-label='Price']", data.get(dataSet).get("Price"));

//		Sync.waitElementPresent("xpath", "//textarea[@data-label='City Purchased']");
//		Common.textBoxInput("xpath", "//textarea[@data-label='City Purchased']",data.get(dataSet).get("City"));
//		
//		Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
//		Common.clickElement("xpath", "//div[@id='conversationCountry']");

			Common.textBoxInput("xpath", "//input[@data-label='Purchase Date']", data.get(dataSet).get("Date"));

//		Sync.waitElementPresent("xpath", "//div[text()='"+country+"']");
//		Common.clickElement("xpath", "//div[text()='"+country+"']");

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Thread.sleep(4000);
//		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
//		Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
//		int registrationsuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
//		Common.assertionCheckwithReport(registrationsuccessmessage > 0, "verifying Product registration Success message ",
//				"Success message should be Displayed", "Product registration  Success message displayed ",
//				"failed to dispaly success message");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Product registration  form",
					"Product registration form data enter without any error message",
					"Product registration  page getting error ",
					Common.getscreenShotPathforReport("Product registration  page"));
			Assert.fail();

		}
	}

	public void configurable_Sticky_add_to_cart(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
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
			Sync.waitElementPresent("xpath", "//img[@alt='" + product + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String Productname = Common.getText("xpath", "//a[contains(text(),'Write a review')]").trim();

			Thread.sleep(4000);
			System.out.println(Productname);
			String Breadcrumb = Common.getText("xpath", "//nav[@id='breadcrumbs']//ol");
			System.out.println(Breadcrumb);
//		Common.assertionCheckwithReport(Common.getPageTitle().contains(product),
			Common.assertionCheckwithReport(Productname.contains("Write a review") && Breadcrumb.contains("Home"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();

			Common.actionsKeyPress(Keys.END);  
			Sync.waitElementPresent("xpath", "//div[@x-show='showStickyBar']//div[@data-option-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@x-show='showStickyBar']//div[@data-option-label='" + productcolor + "']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@x-show='isStickySwatchAvailable' and @title='Add to Cart']");
			Thread.sleep(4000);
//			String message2 = Common.findElement("xpath", "//div[@ui-id='message-success']")
//					.getAttribute("ui-id");
//			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
			Common.actionsKeyPress(Keys.UP);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
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

	public void socialLinkValidation(String dataSet) {

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
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("/x.com"),
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

	public void Oxo_URLValidation(String dataSet) throws Exception, IOException {
		// TODO Auto-generated method stub

		String urls = data.get(dataSet).get("Links");
		int j = 0;

		String[] strArray = urls.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("pre")) {

				Common.oppenURL((strArray[i]));
				Sync.waitPageLoad();
				Thread.sleep(4000);
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

			} else if (Common.getCurrentURL().contains("https://mcloud-na-preprod.oxo.com/")) {

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
			Assert.fail();
		}
	}

	public void search_results(String search) {

		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='search']", search);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("xpath", "//h3[@class='c-srp-title__no-results']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains("Sorry, your search"),
					"validating the search functionality",
					"enter any search term will display no results in the search box",
					"user enter the search term in  search box", "Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.scrollIntoView("xpath", "//div[@id='instant-empty-results-container']//a[text()='FAQ']");
			String contact = Common
					.findElement("xpath", "//div[@id='instant-empty-results-container']//a[text()='FAQ']").getText();
			Thread.sleep(4000);
			System.out.println(contact);
			Common.assertionCheckwithReport(contact.contains("FAQ"), "validating the customer service information",
					"should display Customer serivce information", "user views the Customer serivce information",
					"Failed to see the Customer service info");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the validating the customer service information",
					"should display Customer serivce information", " user views the Customer serivce information",
					Common.getscreenShot("Failed to see the Customer service info"));
			Assert.fail();
		}

	}

	public void popular_searches() {
		String search;

		try {

			List<WebElement> Search = Common.findElements("xpath",
					"(//div[@id='algolia-cms-block-popular-search'])//span[@class='widget block block-category-link-inline']/a");
			System.out.println(Search);
			System.out.println(Search.size());
			for (int i = 0; i < Search.size(); i++) {

				List<WebElement> select = Common.findElements("xpath",
						"(//div[@id='algolia-cms-block-popular-search'])//span[@class='widget block block-category-link-inline']/a");
				Sync.waitPageLoad();
				Sync.waitElementPresent(50, "xpath",
						"(//div[@id='algolia-cms-block-popular-search'])//span[@class='widget block block-category-link-inline']/a");
				Thread.sleep(3000);
				search = select.get(i).getText();
//			System.out.println(search);
				select.get(i).click();
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.navigateBack();
				ExtenantReportUtils.addPassLog("Validating" + search + "Page  ",
						"click on the Popular search should navigate to the  " + search + "Page",
						"successfully page navigating to " + search + "PAGE",
						Common.getscreenShotPathforReport(search));

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"click on the Popular search should navigate to the ",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}
	}

	public void carousel() {
		try {

			Common.scrollIntoView("xpath", "//span[@class='icon-carousel__right']");
			Common.findElement("xpath", "//span[@class='icon-carousel__right']");
			Common.clickElement("xpath", "//span[@class='icon-carousel__right']");

			int carousel = Common.findElements("xpath", "//div[contains(@class,'js-slick-carousel')]").size();
			System.out.println(carousel);
			Common.assertionCheckwithReport(carousel > 0, "validating the carousel", "should navigate to the carousel",
					"user views the carousel", "Failed to see the carousel");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the carousel",
					"click on the carousel should navigate the carousel slider", " unable to view the carousel",
					Common.getscreenShot("Failed to see the carousel"));
			Assert.fail();
		}
	}

	public void Validating_search(String search) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"),
					"To validate the global search box is opened when we click on the search icon",
					"User should able to click on the search icon and search box opens",
					"Sucessfully the gobal search box opend when user clicks on search icon",
					"Failed to open the search box when user clicks on the search icon");
			Common.textBoxInput("xpath", "//input[@id='search']", search);
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-header__s')]");
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String close = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(close.contains("c-header-search"),
					"To validate the global search box is Closed when user click on the close icon",
					"User should able to click on the close icon and search box should be closed",
					"Sucessfully the gobal search box closed when user clicks on close icon",
					"Failed to close the search box when user clicks on the close icon");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the global search box is Closed when user click on the close icon",
					"User should able to click on the close icon and search box should be closed",
					"Unable to close the gobal search box when user clicks on close icon",
					Common.getscreenShotPathforReport(
							"Failed to close the search box when user clicks on the close icon"));

			Assert.fail();
		}

	}

	public void click_Shop() {

		try {
			Sync.waitElementPresent("xpath",
					"//nav[@class='megamenu navigation m-megamenu-nav']//span[text()=' Shop']");

			Common.clickElement("xpath", "//nav[@class='megamenu navigation m-megamenu-nav']//span[text()=' Shop']");
			Thread.sleep(2000);

			String catogories = Common.getText("xpath", "//ul[contains(@class,'level0 submenu')]");
			System.out.println(catogories);

			Common.assertionCheckwithReport(catogories.contains("Kitchenware"),
					"To Validate the catogories are displayed",
					"should display the catogories after clicking on the shop",
					"update catogories are displayed after a click on the shop", "Failed to display the catogories");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
					"should display the catogories after clicking on the shop",
					"unable to display catogories after a click on the shop", "Failed to display the catogories");
			Assert.fail();
		}

	}

	public void click_BabyToddler() {

		try {
			Sync.waitElementPresent("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Baby & Toddler']");

			Common.clickElement("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Baby & Toddler']");
			Thread.sleep(2000);

			String subcatogories = Common.getText("xpath", "//ul[contains(@class,'level0 submenu')]");
			System.out.println(subcatogories);

			Common.assertionCheckwithReport(subcatogories.contains("Bathing"),
					"To Validate the subcatogories are displayed",
					"should display the subcatogories after clicking on the BabyToddler",
					"update subcatogories are displayed after a click on the BabyToddler",
					"Failed to display the subcatogories");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
					"should display the catogories after clicking on the BabyToddler",
					"unable to display catogories after a click on the BabyToddler", "Failed to display subcatogories");
			Assert.fail();
		}

	}

	public void click_FeedingDrinking() {

		try {
			Sync.waitElementPresent("xpath",
					"//ul[contains(@class,'level0 submenu')]//span[text()=' Feeding & Drinking']");

			Common.clickElement("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Feeding & Drinking']");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			String pagetitle = Common.getPageTitle();
			System.out.println(pagetitle);

			Common.assertionCheckwithReport(pagetitle.contains("Shop Feeding & Drinking"),
					"To Validate the PLP page is displayed",
					"should display the PLP page after clicking on the FeedingDrinking",
					"update PLP page are displayed after a click on the FeedingDrinking",
					"Failed to display  catogories");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the PLP page are displayed",
					"should display the PLP page after clicking on the FeedingDrinking",
					"unable to display PLP page after a click on the FeedingDrinking",
					"Failed to display update PLP page");
			Assert.fail();
		}
	}

	public void click_product() {

		try {
			Sync.waitElementPresent("xpath", "//img[@alt='On-the-Go Fork and Spoon Set']");

			Common.clickElement("xpath", "//img[@alt='On-the-Go Fork and Spoon Set']");
			Thread.sleep(2000);

			String pagetitle = Common.getPageTitle();
			System.out.println(pagetitle);

			Common.assertionCheckwithReport(pagetitle.contains("OXO Tot On the Go Fork and Spoon Set"),
					"To Validate the PDP page is displayed",
					"should display the PDP page after clicking on the product image",
					"update PDP page are displayed after a click on the product image", "Failed to display  PDP page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the PDP page are displayed",
					"should display the PDP page after clicking on the product image",
					"unable to display PDP page after a click on the product image",
					"Failed to display update PDP page");
			Assert.fail();
		}

	}

	public void Configurableproduct_addtocart_pdp(String Dataset) {
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		String productquantity = data.get(Dataset).get("productquantity");
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

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, productquantity);
			Common.clickElement("xpath", "//button[@title='Add to Cart']");
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
			Assert.fail();
		}

	}

	public void PDP_cofigurable_product() {
		int subproductsList = Common.findElements("xpath", "//div[@class='field option bundle-item  required']").size();
		for (int i = 0; i < subproductsList; i++) {
			int value = i + 1;
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='fieldset']//div[" + value + "]//div[contains(@class,'m-swatch m')]");

			WebElement Colornames = Common.findElement("xpath",
					"//div[@class='fieldset']//div[" + value + "]//span[contains(@class,'m-swa')]");
			WebElement imagecolor = Common.findElement("xpath", "//div[@class='fieldset']//div[" + value + "]//img");
			for (int j = 0; j < ListOfSubproducts.size(); j++) {

				String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

				if (attributevalue != null) {
				} else {
					if (ListOfSubproducts.get(j).getAttribute("class").contains("m-swatch m")) {

						ListOfSubproducts.get(j).click();

						Common.assertionCheckwithReport(
								imagecolor.getAttribute("src").contains(Colornames.getText())
										|| imagecolor.getAttribute("src").trim().equals(""),
								"Vrifying  swatch color button " + Colornames.getText(),
								"after click color swatch button" + Colornames.getText()
										+ "it must dispaly swatch color image",
								"successfully color swatch image is dispalying", "Failed load color swatch image");
					} else {
						break;
					}
				}
			}
		}

	}

	public void click_minicartatPDP() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.ARROW_UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");
			Common.clickElement("xpath", "//span[@class='icon-minicart__close a-icon-text-btn__icon']");
			Thread.sleep(2000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "the mini cart is displayed",
					"unable to  dislay the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}

	public void click_Customize() {

		try {
			Sync.waitElementPresent("xpath", "//button[@data-role='customize-btn']");

			Common.clickElement("xpath", "//button[@data-role='customize-btn']");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			String text = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			System.out.println(text);

			Common.assertionCheckwithReport(text.equals("Bottle"), "To Validate the customize page is displayed",
					"should display the customize page after clicking on the customize button",
					"update customize page are displayed after a click on the customize button",
					"Failed to display  customize");
			Common.clickElement("xpath", "//img[@alt='Close icon']");
			Thread.sleep(2000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the customize page are displayed",
					"should display the customize page after clicking on the customize button",
					"unable to display customize page after a click on the customize button",
					"Failed to display update customize page");
			Assert.fail();
		}

	}

	public void configurableproduct_Sticky_add_to_cart(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		try {
			
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//div[@id='cart-drawer-title']").size();
			if(size>0) {
				Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
				Thread.sleep(3000);
				Common.actionsKeyPress(Keys.END);
				Sync.waitElementPresent("xpath", "//div[@x-data='stickyBar()']//div[@aria-label='Color']//div[@data-option-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@x-data='stickyBar()']//div[@aria-label='Color']//div[@data-option-label='" + productcolor +"']");
				Common.clickElement("xpath", "//div[@x-data='stickyBar()']//button[@title='Add to Cart']");
				Thread.sleep(3000);
				Common.actionsKeyPress(Keys.PAGE_UP);
			  int Opencart = Common.findElements("xpath", "//div[@id='cart-drawer-title']").size();
				Common.assertionCheckwithReport(Opencart>0, "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");	
				Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
			}
			else {
				
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void writeareview(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		try {

			Common.scrollIntoView("xpath", "//a[text()='Write a review']");
			Sync.waitElementPresent("xpath", "//a[text()='Write a review']");
			String form = Common.findElement("xpath", "//a[text()='Write a review']").getText();
			System.out.println(form);
			Common.assertionCheckwithReport(form.equals("Write a review"), "verifying the write a review button",
					"Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page",
					"Failed to display the write a review button in PDP page");
			Common.clickElement("xpath", "//a[text()='Write a review']");
			Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
			Common.clickElement("xpath", "//span[text()='Write A Review']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
					"User Unable to click write review option  ",
					Common.getscreenShotPathforReport("User Failed to click write review option "));
			Assert.fail();
		}

	}

	public void Recommended_for_you() {

		try {
			Common.scrollIntoView("xpath", "//h2[normalize-space()='Recommended Products']");

//			Common.clickElement("xpath", "//h2[@data-bind='text: storefrontLabel']");
			Common.clickElement("xpath", "(//button[@aria-label='Scroll right']//parent::button)[1]");
			Common.clickElement("xpath", "(//button[@aria-label='Scroll right']//parent::button)[1]");
			Common.clickElement("xpath", "(//button[@aria-label='Scroll left']//parent::button)[1]");

			Sync.waitPageLoad();
			Thread.sleep(2000);

			String text = Common
					.findElement("xpath",
							"//h2[normalize-space()='Recommended Products']")
					.getText();
			System.out.println(text);

			Common.assertionCheckwithReport(text.contains("Recommended Products"),
					"To Validate the Recommended for you is displayed",
					"should display the Recommended for you after scroll down the PDP page",
					"update Recommended for you are displayed after scroll down the PDP page", "Failed to display  ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Recommended for you are displayed",
					"should display the Recommended for you after scroll down the PDP page",
					"unable to display Recommended for you after scroll down the PDP page",
					"Failed to display Recommended for you");
			Assert.fail();
		}

	}

	public void Shipping_Forgot_Password(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.textBoxInput("xpath", "//input[@name=\"username\"]", data.get(dataSet).get("UserName"));
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//span[text()='Toggle password visibility']");
			String shipping = Common.findElement("xpath", "(//span[text()='Shipping'])[1]").getText();
			System.out.println(shipping);
			Common.clickElement("xpath", "//span[text()='Item in Cart']");
			String QTY = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[1]").getText();
			System.out.println(QTY);
			String Price = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[2]").getText();
			System.out.println(Price);
			Common.clickElement("xpath", "(//span[text()='View Details'])[2]");
			String Color = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[3]").getText();
			System.out.println(Color);
			Common.assertionCheckwithReport(shipping.equals("Shipping"),
					"To validate the user is navigating to Shipping page", "user should naviagte to Shipping page",
					"User lands on Shippingd page", "User failed to navigate to Shipping page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user is navigating to Shipping page",
					"user should navigate to Shipping page", "User failed to land on Shipping page",
					Common.getscreenShotPathforReport("failed  to naviagte Shipping page "));
			Assert.fail();

		}

	}

	public void Forgot_password(String DateSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[normalize-space()='Forgot Password?']");
			String forgotpassword = Common.findElement("xpath", "//h2[normalize-space()='Forgot Your Password?']")
					.getText();
			System.out.println(forgotpassword);
			Sync.waitPageLoad();
			Common.textBoxInput("xpath", "//input[@id='email_address']", Utils.getEmailid());
			Common.clickElement("xpath", "//button[normalize-space()='Reset My Password']");
			Sync.waitElementPresent(30, "xpath", "//div[contains(@ui-id,'message-success')]//span");
			String message = Common.findElement("xpath", "//div[contains(@ui-id,'message-success')]//span").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("We received too many requests for password resets")
							|| message.contains("If there is an account associated with"),
					"To validate the user is navigating to Forgot Password page",
					"user should naviagte to forgot password page", "User lands on Forgot Password page",
					"User failed to navigate to forgot password page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user is navigating to Forgot Password page",
					"user should navigate to forgot password page", "User failed to land on Forgot Password page",
					Common.getscreenShotPathforReport("failed  to naviagte forgot password page "));
			Assert.fail();

		}
	}

	public void view_PLP_page() {
		try {
			String title = Common.findElement("xpath", "//h5[@class='cms-clear']").getAttribute("Class");
			String breadcrumbs = Common.findElement("xpath", "//nav[@aria-label='Breadcrumb']")
					.getAttribute("aria-label");
			Thread.sleep(2000);
			String filter = Common.findElement("xpath", "//span[normalize-space()='Filter by:']").getText().trim();
			String Sort = Common
					.findElement("xpath",
							"//div[@class='flex items-center']")
					.getAttribute("class");
			System.out.println(title);
			System.out.println(breadcrumbs);
			System.out.println(filter);
			System.out.println(Sort);

			Common.assertionCheckwithReport(
					breadcrumbs.contains("Breadcrumb") && title.contains("cms-clear")
							&& filter.contains("Filter by:") && Sort.contains("flex items"),
					"To validate the Product Listing Page", "User should able to open Product Listing Page",
					"Sucessfully views the Product Listing Page", "Failed to view Product Listing Page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Product Listing Page",
					"User should able to open Product Listing Page", "Unable to view the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to view Product listing Page"));

			Assert.fail();
		}
	}

	public void filter_By(String category) {

		try {

			String text = Common.findElement("xpath", "//span[text()='" + category + "']//following-sibling::span").getText().replace("(", "").replace(")", "");
			System.out.println(text);
			Common.clickElement("xpath", "//span[text()='" + category + "']");
			int textValue = Integer.parseInt(text);
			String categoryvalue = Integer.toString(textValue);
			Thread.sleep(6000);
			String textValueAfterFilter = Common.findElement("xpath", "//div[@class='text-sm']//span")
					.getText().trim();
			Common.scrollIntoView("xpath", "//button[text()='Load More']");
			Common.clickElement("xpath", "//button[text()='Load More']");
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//button[text()='Load More']");
			Common.clickElement("xpath", "//button[text()='Load More']");
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//button[text()='Load More']");
			Common.clickElement("xpath", "//button[text()='Load More']");
			Thread.sleep(4000);
			int noOfItems = Common.findElements("xpath", "//li[@class='ais-InfiniteHits-item']").size();
			String items = Integer.toString(noOfItems);
			System.out.println(text);
			System.out.println(textValueAfterFilter);

			Common.assertionCheckwithReport(categoryvalue.equals(items),
					"To validate the filter in Product Listing Page",
					"User should able to filter in Product Listing Page",
					"Sucessfully filters in the Product Listing Page", "Failed to filter in Product Listing Page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the filter in Product Listing Page",
					"User should able to filter in Product Listing Page", "Unable to filter the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to filter Product listing Page"));

			Assert.fail();
		}
	}

	public void sort_By(String dataSet) {
		String sort = data.get(dataSet).get("Sort");
		try {

			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
//			Common.dropdown("xpath", "//option[@class='ais-SortBy-option']", Common.SelectBy.TEXT, sort);
			Common.clickElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + sort + "')]");

//			int size = Common.findElements("xpath", "//span[@data-price-type='finalPrice']/span[1]").size();
//			System.out.println("size ="+size);
//			float[] float_array = new float[size];
//			for(int i=0; i<size; i++) {
////				String text = Common.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']").get(i).getText();
//				String text = Common.findElements("xpath", "//span[@data-price-type='finalPrice']/span[1]").get(i).getText();
//				
//				String price = text.replace("$", "");
//				System.out.println(price);
//				Float priceValue = Float.parseFloat(price);
//				System.out.println(priceValue);
//				float_array[i]=priceValue;
//			}
//			Arrays.sort(float_array);
//			String firstItemPriceText = Common.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']").get(0).getText();
//			String firstItemPrice = firstItemPriceText.replace("$", "");
//			Float firstItemPriceValue = Float.parseFloat(firstItemPrice);
//			if(data.get(dataSet).get("Sort").equals("Lowest Price")) {
//				Common.assertionCheckwithReport(firstItemPriceValue.equals(float_array[0]),
//						"To validate the Sort in Product Listing Page",
//						"User should able to Sort in Product Listing Page",
//						"Sucessfully Sorts in the Product Listing Page",
//						"Failed to Sort  in Product Listing Page");
//			}
//			else if(data.get(dataSet).get("Sort").equals("Highest Price")) {
//				Common.assertionCheckwithReport(firstItemPriceValue.equals(float_array[size-1]),
//						"To validate the Sort in Product Listing Page",
//						"User should able to Sort in Product Listing Page",
//						"Sucessfully Sorts in the Product Listing Page",
//						"Failed to Sort  in Product Listing Page");
//			}
			//////

			String low = Common
					.findElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + sort + "')]")
					.getText();

			Common.assertionCheckwithReport(low.contains(sort), "To validate the Sort in Product Listing Page",
					"User should able to Sort in Product Listing Page", "Sucessfully Sorts in the Product Listing Page",
					"Failed to Sort  in Product Listing Page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Sort  in Product Listing Page",
					"User should able to Sort  in Product Listing Page", "Unable to Sort the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to Sort  Product listing Page"));

			Assert.fail();
		}
	}

	public void color_validation(String colorname) {
		// TODO Auto-generated method stub
		try {
//			Sync.waitElementPresent("xpath", "//button[@aria-label='Colors']");
//			Common.clickElement("xpath", "//button[@aria-label='Colors']");
//			Thread.sleep(3000);
//			String expand = Common.findElement("xpath", "//button[@aria-label='Colors']").getAttribute("aria-expanded");
//			Common.assertionCheckwithReport(expand.contains("true"), "verifying the color bar has been expand",
//					"When we click on the color it should be expand",
//					"Successfully the color has been expand when we click on the colors ",
//					"unable to expand the colors in PLP page");
			Sync.waitElementPresent("xpath",
					"//ul[contains(@class,'ais-RefinementList')]//input[@value='" + colorname + "']");
			Common.clickElement("xpath",
					"//ul[contains(@class,'ais-RefinementList')]//input[@value='" + colorname + "']");
			Thread.sleep(4000);
			String colorcount = Common.findElement("xpath",
					"//span[text()='Sage']//following-sibling::span")
					.getText().replace("(", "").replace(")", "");
			String bottlecount = Common.findElement("xpath", "//div[@class='text-sm']//span").getText().trim();
			Common.assertionCheckwithReport(colorcount.equals(bottlecount), "verifying the color bar has been expand",
					"When we click on the color it should be expand",
					"Successfully the color has been expand when we click on the colors ",
					"unable to expand the colors in PLP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the color bar has been expand",
					"When we click on the color it should be expand", "unable to expand the colors in PLP page",
					Common.getscreenShotPathforReport("Failed to expand the colors in PLP page"));
			Assert.fail();
		}

	}

	public void price_filter_validation() {

		String name = "";
		try {
			Thread.sleep(3000);
			String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
					.replace("$", "").replace(".00", "");
			System.out.println(lastvalue);
			Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			WebElement price = Common.findElement("xpath",
					"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			dragprice(price);
			Thread.sleep(6000);
			List<WebElement> products = Common.findElements("xpath",
					"//ol[@class='ais-InfiniteHits-list']//img[contains(@class,'m-product')]");
			for (int i = 0; i < products.size(); i++) {
				int Size = products.size();
				System.out.println(Size);
				Thread.sleep(4000);
				if (Size == 1) {
					String name1 = Common.findElement("xpath", "//span[@class='price-wrapper']//span[@class='price']")
							.getText().replace("$", "");
					System.out.println(name1);
					Float namevalue1 = Float.parseFloat(name1);
					System.out.println(namevalue1);
					if (namevalue1 >= 5) {
						Thread.sleep(3000);
						String value1 = Common.findElement("xpath", "//span[@class='price-wrapper']")
								.getAttribute("data-price-amount");
						System.out.println(value1);
						Common.assertionCheckwithReport(value1.equals(name1), "verifying the price filters in PLP page",
								"When we select the range of price filters between the range only products should display",
								"Successfully are displayed in the pricing range",
								"unable to display the procing range after pricing filter applied");
					} else {
						Assert.fail();
					}
				} else {
					List<WebElement> productprice = Common.findElements("xpath",
							"//span[@class='price-wrapper']//span[@class='price']");
					Thread.sleep(6000);
					name = productprice.get(i).getText().replace("$", "");
					Float namevlaue = Float.parseFloat(name);
					if (namevlaue >= 5) {
						Thread.sleep(3000);
						String value = Common
								.findElement("xpath", "//span[@class='price-wrapper']//span[@class='price']").getText()
								.replace("$", "");
						Common.assertionCheckwithReport(value.equals(name), "verifying the price filters in PLP page",
								"When we select the range of price filters between the range only products should display",
								"Successfully are displayed in the pricing range",
								"unable to display the procing range after pricing filter applied");
					} else {
						Assert.fail();
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
			Assert.fail();
		}

	}

	public void dragprice(WebElement price) {
		try {
			String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace("$", "")
					.replace(".00", "");
			System.out.println(lastvalue);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
			Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
		} catch (Exception | Error e) {
			e.printStackTrace();
		}
	}

	public void Store_payment_placeOrder(String Dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "It redirects to order confirmation page";
		String order="";
		
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
			Assert.fail();
		}

	}
	public void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);
		try {
			Common.findElement("xpath", "//select[@name='qty']");
//			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			String value = Common.findElement("xpath", "//select[@name='qty']").getAttribute("value");
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
			Assert.fail();
		}

	}

	public void outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String email = data.get(Dataset).get("Notifyme");
		try {
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
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");

			Common.clickElement("xpath", "//span[text()=' Notify Me When Available']");
			Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String newsubs = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(newsubs);
			String newsubscribe = Common.findElementBy("xpath", "//div[@data-ui-id='message-success']//div").getText();
			System.out.println(newsubscribe);
			Common.assertionCheckwithReport(
					newsubscribe.contains("Alert subscription has been saved.")
							|| newsubscribe.contains("Thank you! You are already subscribed to this product.")
							|| newsubs.contains("success"),
					"verifying the out of stock subcription", "after click on subcribe button message should be appear",
					"Sucessfully message has been displayed when we click on the subcribe button ",
					"Failed to display the message after subcribtion");
			Common.actionsKeyPress(Keys.END);

			Common.clickElement("xpath", "//span[text()=' Notify Me When Available']");
			Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String oldsubscribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			System.out.println(oldsubscribe);
			Common.assertionCheckwithReport(
					oldsubscribe.contains("Thank you! You are already subscribed to this product."),
					"verifying the out of stock subcription", "after click on subcribe button message should be appear",
					"Sucessfully message has been displayed when we click on the subcribe button ",
					"Failed to display the message after subcribtion");

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ",
					Common.getscreenShot("Failed to display the message after subcribtion"));
			Assert.fail();

		}

	}

	public String reg_outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String prod = data.get(Dataset).get("Prod Products OOS");
		String price = "";
		try {
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
			Thread.sleep(6000);
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PlpPrice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
						.getAttribute("data-price-amount");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PlpPrice)
								|| name.contains(prod) && productprice.equals(PlpPrice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubs = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
//			System.out.println(newsubs);
				String newsubscribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div")
						.getText();
				Common.assertionCheckwithReport(
						newsubscribe.contains("Alert subscription has been saved.")
								|| newsubscribe.contains("Thank you! You are already subscribed to this product.")
								|| newsubs.contains("success"),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
						.getAttribute("data-price-amount");
			} else {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + prod + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + prod + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ",
					Common.getscreenShot("Failed to display the message after subcribtion"));
			Assert.fail();
		}
		return price;

	}

	public void My_order_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String prod = data.get(Dataset).get("prod product");
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the page navigation to the my account",
					"after clicking on the my account it should navigate to the my account page",
					"Sucessfully Navigated to the my account page", "failed to Navigate to the my account page");
			Sync.waitElementPresent("xpath", "//a[text()='My Out of Stock Subscriptions']");
			Common.clickElement("xpath", "//a[text()='My Out of Stock Subscriptions']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(20, "xpath", "//span[@class='a-product-name']");
			String name = Common.findElement("xpath", "(//span[@class='a-product-name'])[1]").getText();
			Common.assertionCheckwithReport(name.equals(products) || name.equals(prod),
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
			Assert.fail();
		}

	}

	public void remove_outofstock_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
					.getAttribute("data-price-amount");
			if (price.equals(Dataset)) {
				Thread.sleep(3000);
				Common.clickElement("xpath", "(//span[text()='Remove'])[2]");
				Common.implicitWait();
				Common.alerts("Cancel");
				Thread.sleep(3000);
				Common.clickElement("xpath", "(//span[text()='Remove'])[2]");
				Common.implicitWait();
				Common.alerts("Ok");

			} else {

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void share_whishlist(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.scrollIntoView("xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("has been added to your Wish List"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your Favorites have been shared"),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
			} else {
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
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

	public void register_billingAddress(String dataSet) {
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
			Common.assertionCheckwithReport(update.contains("6 Walnut Valley Dr"),
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
			Assert.fail();
		}

	}

	public void minicart_ordersummary_discount(String Dataset) {
		// TODO Auto-generated method stub.
		String expectedResult = "It should opens textbox input to enter discount.";
		try {
			Sync.waitElementPresent("xpath", "//button[@id='discount-form-toggle']");
			Common.clickElement("xpath", "//button[@id='discount-form-toggle']");

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");

			Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("Discountcode"));

			int size = Common.findElements("xpath", "//input[@name='coupon_code']").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//button[@value='Apply Discount']");
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
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
			Assert.fail();
		}
		try {
	/*		String Subtotal = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
//			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
//					.replace("$", "");
//			Float shippingvalue = Float.parseFloat(shipping);
			String Discount = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(segment.value)']")
					.replace("$", "").replace("- ", "");
			Float Discountvalue = Float.parseFloat(Discount);

			String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = (subtotalvalue) - Discountvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			BigDecimal tolerance = new BigDecimal("0.01");
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);*/
			 String Subtotal = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").replace("$", "");  
		        String Discount = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(segment.value)']").replace("$", "").replace("- ", "");  
		        String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']").replace("$", "");  

		        BigDecimal subtotalValue = new BigDecimal(Subtotal).setScale(2, BigDecimal.ROUND_HALF_UP);  
		        BigDecimal discountValue = new BigDecimal(Discount).setScale(2, BigDecimal.ROUND_HALF_UP);  
		        BigDecimal orderTotalValue = new BigDecimal(ordertotal).setScale(2, BigDecimal.ROUND_HALF_UP);  

		        BigDecimal Total = subtotalValue.subtract(discountValue).setScale(2, BigDecimal.ROUND_HALF_UP); 
		        
		        System.out.println("Subtotal: " + subtotalValue);  
		        System.out.println("Discount: " + discountValue);  
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
			Assert.fail();
		}

	}

	// Temp
	public void blog_PDP() {

		try {
			Sync.waitElementPresent("xpath", "//div[@class='c-product-blog u-container']");
			Common.scrollIntoView("xpath", "//div[@class='c-product-blog u-container']");
			Common.clickElement("xpath", "//div[@class='m-blog-card__image-container']");
//			Thread.sleep(6000);
			String blog = Common.findElement("xpath", "//h2[@class='m-blog-post-heading__title']").getText();
			Thread.sleep(4000);
			System.out.println(blog);

			Common.assertionCheckwithReport(blog.contains("Article"),
					"To validate the Blog Article when we click on the PDP Blog section",
					"user should able to display the Blog Article", "Sucessfully Blog Article has been displayed",
					"Failed to Displayed the Blog Article");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog Article in when we click on the PDP Blog section",
					"user should able to display the Blog Article", "unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
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

	public void Account_page_Validation(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
		Common.clickElement("xpath", "//button[@id='customer-menu']");
		Sync.waitElementPresent("xpath", "//a[normalize-space()='My Account']");
		Common.clickElement("xpath", "//a[normalize-space()='My Account']");
		Sync.waitPageLoad();
		Thread.sleep(4000);

		String Accountlinks = data.get(Dataset).get("Account Links");
		String[] Account = Accountlinks.split(",");
		int i = 0;
		try {
			for (i = 0; i < Account.length; i++) {
				Sync.waitElementPresent("xpath",
						"//a//span[text()='" + Account[i] + "']");
				Common.clickElement("xpath",
						"//a//span[text()='" + Account[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				try {
				String title = Common.findElement("xpath", "//h1[@class='title-2xl']").getText();
                 System.out.println(title);
				Common.assertionCheckwithReport(title.contains(Account[i]),
						"verifying Account page links " + Account[i],
						"user should navigate to the " + Account[i] + " page",
						"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);

			}
				catch(Exception | Error e) {
				String Title = Common.getCurrentURL(); 
					Common.assertionCheckwithReport(Title.contains("giftregistry")||
							Title.contains("klaviyo/customer/newsletter/")||
							Title.contains("newsletter/manage/") ||Title.contains("stock/index/")|| Title.contains("customer/paymentmethods/"),
							"verifying Account page links " + Account[i],
							"user should navigate to the " + Account[i] + " page",
							"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
				}
			}
				
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the account page links " + Account[i],
					"user should Navigate to the " + Account[i] + " page",
					"User unable to navigate to the " + Account[i],
					Common.getscreenShotPathforReport("user Failed to Navigate to the respective page"));
			Assert.fail();
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
							"page configured with products ", "unable to find page it showing 40 error",
							Common.getscreenShotPathforReport("link" + i));

				}

			} else if (Common.getCurrentURL().contains("www.oxo.com")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-preprod", "www"));

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
			Assert.fail();
		}

	}

	public void signout() {
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementClickable("xpath", "//a[@id='customer.header.sign.out.link']");

			Common.javascriptclickElement("xpath", "//a[@id='customer.header.sign.out.link']");

//			Common.assertionCheckwithReport(
//					Common.getText("xpath", "//h1[contains(text(),'You are signed out')]").equals("You are signed out"),
//					"Validating My Account page navigation", "user sign in and navigate to my account page",
//					"Successfully navigate to my account page", "Failed to navigate my account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating sign out navigation ",
					"after clinking signout user signout fro the page", "user Successfully signout  ",
					Common.getscreenShotPathforReport("user Failed to signout"));
			Assert.fail();
		}

	}

	public void orderprodcut_search(String search) {
		// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='search']", search);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String searchresults = Common.getText("xpath", "//span[text()='32 oz mouth']");
			String productsearch1 = Common.findElement("xpath", "(//div[@id='algolia-right-container'])[1]").getText();
			System.out.println(productsearch1);
			Common.assertionCheckwithReport(searchresults.contains("32 oz mouth"),
					"validating the search functionality",
					"enter any search term will display no results in the search box",
					"user enter the search term in  search box", "Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	public void unorderprodcut_search(String search) {
// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='search']", search);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String searchresults = Common.getText("xpath", "//span[text()='mouth wide oz 32']");
			String productsearch = Common.findElement("xpath", "(//div[@id='algolia-right-container'])[1]").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(searchresults.contains("mouth wide oz 32"),
					"validating the search functionality",
					"enter any search term will display no results in the search box",
					"user enter the search term in  search box", "Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	public void chefs_and_Residence(String Dataset) {

		String chefslinks = data.get(Dataset).get("chefs");
		String Aluminichefs = data.get(Dataset).get("AluminiChefs");
		String[] Chef = chefslinks.split(",");
		String[] Alumini = Aluminichefs.split(",");
		int i = 0;
		try {
			Sync.waitPageLoad();

			Common.clickElement("xpath", "//span[normalize-space()='Chefs in Residence']");
			Sync.waitPageLoad();

			for (i = 0; i < Chef.length; i++) {
				Sync.waitPageLoad();
				String link = Common
						.findElement("xpath", "//a[normalize-space()='"+Chef[i]+"']")
						.getText();
				System.out.println(link);
				Common.clickElement("xpath", "//a[normalize-space()='"+Chef[i]+"']");
				System.out.println(Chef[i]);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(Common.getPageTitle().contains(Chef[i]),
						"validating navigation to the chefs and Residence page " + Chef,
						"After clicking on " + Chef + " it should navigate to " + Chef,
						"successfully Navigated to the chefs and Residence " + Chef,
						"failed to Navigate to the chefs and Residence ");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the chefs and Residence  page ",
					"After clicking on chefs and Residence button it should navigate to the chefs and Residence  page ",
					"Unable to Navigated to the chefs and Residence ",
					Common.getscreenShot("failed to Navigate to the chefs and Residence "));
			Assert.fail();
		}

	}

	public void alumini_Chefs(String Dataset) {

		String Alumnichefs = data.get(Dataset).get("AluminiChefs");
		String[] Alumni = Alumnichefs.split(",");
		int i = 0;
		try {
			Sync.waitPageLoad();
			for (i = 0; i < Alumni.length; i++) {
				Common.clickElement("xpath",
						"//span[normalize-space()='Chefs in Residence']");
				Sync.waitPageLoad();
				Common.mouseOver("xpath", "//a[text()='Alumni Chefs']");
				Common.mouseOverClick("xpath",
						"//a[normalize-space()='" + Alumni[i] + "']");

				String link = Common
						.findElement("xpath", "//a[normalize-space()='" + Alumni[i] + "']")
						.getText();
				System.out.println(link);
				System.out.println(Alumni[i]);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(Common.getPageTitle().contains(Alumni[i]),
						"validating navigation to the chefs and Residence page " + Alumni,
						"After clicking on " + Alumni + " it should navigate to " + Alumni,
						"successfully Navigated to the chefs and Residence " + Alumni,
						"failed to Navigate to the chefs and Residence ");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the chefs and Residence  page ",
					"After clicking on chefs and Residence button it should navigate to the chefs and Residence  page ",
					"Unable to Navigated to the chefs and Residence ",
					Common.getscreenShot("failed to Navigate to the chefs and Residence "));
			Assert.fail();
		}

	}

	public void back_to_cart() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.UP);
			Sync.waitElementPresent("xpath", "//a[@title='Back to Cart']");
			Common.clickElement("xpath", "//a[@title='Back to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Shopping Cart"),
					"verifying the shopping cart navigation",
					"user should be able to navigate to the shopping cart page",
					"user successfully Navigated to shopping cart page",
					"Failed to navigate to the shopping cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the shopping cart navigation",
					"user should be able to navigate to the shopping cart page",
					"user Unable Navigated to shopping cart page",
					Common.getscreenShot("user Failed to Navigated to shopping cart page"));
			Assert.fail();
		}

	}

	public void empty_storedpayment() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Thread.sleep(4000);
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
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[text()='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[text()='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			String card = Common.findElement("xpath", "//div[@class='message info empty']//span").getText();
			Common.assertionCheckwithReport(card.contains("You do not have any saved payment methods."),
					"validating the no saved payments in stored credit card",
					"After Clicking on stored methods CTA stored credit cart should be empty",
					"Sucessfully we dont have any payments in stored payments",
					"Failed to dispaly the message for empty stored payments");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Unable to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA",
					Common.getscreenShot(
							"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA"));
			Assert.fail();
		}
	}

	public void footerLinks_ProductSupport(String Dataset) {
		String Footerlinks = data.get(Dataset).get("Product Support");
		String[] Footer = Footerlinks.split(",");
		int i = 0;
		try {
			Sync.waitPageLoad();

			for (i = 0; i < Footer.length; i++) {
				Common.actionsKeyPress(Keys.END);
				Sync.waitPageLoad();
				Common.scrollIntoView("xpath",
						"//div[@class='c-footer__items-wrapper']//a[contains(text(),'" + Footer[i] + "')]");
				Common.clickElement("xpath",
						"//div[@class='c-footer__items-wrapper']//a[contains(text(),'" + Footer[i] + "')]");
				Sync.waitPageLoad();
				String breadcrumb = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				System.out.println(Footer[i]);
				System.out.println(Common.getCurrentURL());
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(
						Common.getCurrentURL().contains(Footer[i]) || Common.getPageTitle().contains(Footer[i])
								|| breadcrumb.contains(Footer[i]),
						"Validate the Footer link " + Footer[i],
						"Click the footer link " + Footer[i] + "it will navigate to page" + Footer[i],
						"successfully navigating to " + Footer[i] + "page ",
						"Failed to navigate to" + Footer[i] + "page");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Footer[i],
					"Click the footer link " + Footer[i] + "it will navigate to page" + Footer[i],
					"Failed to navigate to" + Footer[i] + "page",
					Common.getscreenShotPathforReport("failed to land on " + Footer[i]));
			Assert.fail();

		}
		footerLink_Shipping_Returns();
		footerLink_Better_Guarantee();
		footerLink_Cookware_Guarantee();
	}

	public void footerLinks_CustomerSupport(String Dataset) {

		String Footerlinks = data.get(Dataset).get("Customer Support");
		String[] Footer = Footerlinks.split(",");
		int i = 0;
		try {
			Sync.waitPageLoad();

			for (i = 0; i < Footer.length; i++) {
				Common.actionsKeyPress(Keys.END);
				Sync.waitPageLoad();
				Common.scrollIntoView("xpath",
						"//div[@class='c-footer__items-wrapper']//a[contains(text(),'" + Footer[i] + "')]");
				Common.clickElement("xpath",
						"//div[@class='c-footer__items-wrapper']//a[contains(text(),'" + Footer[i] + "')]");

				Sync.waitPageLoad();
				System.out.println(Footer[i]);
				System.out.println(Common.getCurrentURL());
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(
						Common.getCurrentURL().contains(Footer[i]) || Common.getPageTitle().contains(Footer[i]),
						"Validate the Footer link " + Footer[i],
						"Click the footer link " + Footer[i] + "it will navigate to page" + Footer[i],
						"successfully navigating to " + Footer[i] + "page ",
						"Failed to navigate to" + Footer[i] + "page");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Footer[i],
					"Click the footer link " + Footer[i] + "it will navigate to page" + Footer[i],
					"Failed to navigate to" + Footer[i] + "page",
					Common.getscreenShotPathforReport("failed to land on " + Footer[i]));
			Assert.fail();

		}
		footerLink_FAQs();
		footerLink_Voluntary_Recall();
	}

	public void footerLinks_WeAre_Oxo(String Dataset) {

		String Footerlinks = data.get(Dataset).get("We Are OXO");
		String[] Footer = Footerlinks.split(",");
		int i = 0;
		try {
			Sync.waitPageLoad();

			for (i = 0; i < Footer.length; i++) {
				Common.actionsKeyPress(Keys.END);
				Sync.waitPageLoad();
				Common.scrollIntoView("xpath",
						"//div[@class='c-footer__items-wrapper']//a[contains(text(),'" + Footer[i] + "')]");
				Common.clickElement("xpath",
						"//div[@class='c-footer__items-wrapper']//a[contains(text(),'" + Footer[i] + "')]");
				Footer[i].toLowerCase();
				Sync.waitPageLoad();
				System.out.println(Footer[i]);
				System.out.println(Common.getCurrentURL());
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(
						Common.getCurrentURL().contains(Footer[i]) || Common.getPageTitle().contains(Footer[i]),
						"Validate the Footer link " + Footer[i],
						"Click the footer link " + Footer[i] + "it will navigate to page" + Footer[i],
						"successfully navigating to " + Footer[i] + "page ",
						"Failed to navigate to" + Footer[i] + "page");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Footer[i],
					"Click the footer link " + Footer[i] + "it will navigate to page" + Footer[i],
					"Failed to navigate to" + Footer[i] + "page",
					Common.getscreenShotPathforReport("failed to land on " + Footer[i]));
			Assert.fail();

		}
		footerLink_Inventor_Submission();
		footerLink_Careers();
		footerLink_Investor_Relations();
	}

	public void footerLink_Shipping_Returns() {
		String Links = "Shipping & Returns";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

//			  Common.clickElement("xpath","//a[text()='Shipping & Returns']");
			Common.clickElement("xpath", "//a[contains(text(),'Shipping')]");
			Sync.waitPageLoad();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Shipping"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");
			Sync.waitPageLoad();
			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}

	public void footerLink_Inventor_Submission() {
		String Links = "Inventor Submission";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[contains(text(),'Inventor')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Inventor"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");
			Sync.waitPageLoad();
			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}

	public void footerLink_Better_Guarantee() {
		String Links = "Better Guarantee";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[contains(text(),'Better')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Guarantee"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");
			Sync.waitPageLoad();
			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}

	public void footerLink_Cookware_Guarantee() {
		String Links = "Cookware Guarantee";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[contains(text(),'Cookware Guarantee')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Cookware"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");
			Sync.waitPageLoad();
			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}

	public void footerLink_FAQs() {
		String Links = "FAQs";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[text()='FAQ']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Knowledge Base"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");

			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}

	public void footerLink_Voluntary_Recall() {
		String Links = "Voluntary Recall";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[contains(text(),'Voluntary')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Voluntary"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");

			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}

	public void footerLink_Careers() {
		String Links = "Careers";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[contains(text(),'Careers')]");
			Sync.waitPageLoad();
			Common.switchWindows();
			System.out.println(Common.getPageTitle());
			System.out.println(Common.getCurrentURL());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Careers") || Common.getCurrentURL().contains("careers"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}

		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	public void footerLink_Investor_Relations() {
		String Links = "Investor Relations";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);

			Common.clickElement("xpath", "//a[contains(text(),'Investor')]");
			Common.switchWindows();
			Sync.waitPageLoad();
			System.out.println(Common.getCurrentURL());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Investor Relations") || Common.getCurrentURL().contains("investor"),
					"Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Footer link " + Links,
					"Click the footer link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	// Header
	public void header_kitchenware(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Kitchen ware");

		String[] kitchenLinks = names.split(",");

		int i = 0;
		try {
			for (i = 0; i < kitchenLinks.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Kitchenware']");
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'" + kitchenLinks[i] + "')]");
//					Common.clickElement("xpath", "//span[contains(text(),'" +Links[i]+"')]//following::ul//span[contains(text(),' Prep & Go Containers')]");
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'" + kitchenLinks[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(kitchenLinks[i]);

				if (kitchenLinks[i].contains("Food Containers")) {
					header_kichenware_foodcontainers("FoodContainers");
				} else if (kitchenLinks[i].contains("Cutlery")) {
					header_kichenware_Cultery("Cutlery");
				} else if (kitchenLinks[i].contains("Cooking & Baking")) {
					header_kichenware_CookingAndBaking("CookingAndBaking");
				} else if (kitchenLinks[i].contains("Tools & Gadgets")) {
					header_kichenware_ToolsAndGadgets("ToolsAndGadgets");
				}

				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				String breadcrum = Common.findElement("xpath", "//nav[@aria-label='Breadcrumb']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				System.out.println(kitchenLinks[i]);
				System.out.println(breadcrum);

				Common.assertionCheckwithReport(title.contains(kitchenLinks[i]) || breadcrum.contains(kitchenLinks[i])
						|| breadcrumb.contains(kitchenLinks[i]) || Common.getPageTitle().contains(kitchenLinks[i]),
						"verifying the header link " + kitchenLinks[i] + "Under Kitchenware",
						"user should navigate to the " + kitchenLinks[i] + " page",
						"user successfully Navigated to the " + kitchenLinks[i],
						"Failed to navigate to the " + kitchenLinks[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + kitchenLinks[i] + "Under Kitchenware",
					"User should navigate to the " + kitchenLinks[i] + "pages",
					" unable to navigate to the " + kitchenLinks[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + kitchenLinks[i] + "pages"));
			Assert.fail();
		}
		header_Kitchenware_ShopAll("Kitchenware");

	}

	public void header_kichenware_foodcontainers(String Dataset) {

		String names = data.get(Dataset).get("Food Containers");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Sync.waitElementPresent("xpath", "//span[text()='Kitchenware']");
				Common.clickElement("xpath", "//span[text()='Kitchenware']");
				Thread.sleep(2000);
				Common.clickElement("xpath", "//a//span[contains(text(),'Food Containers')]");
				Thread.sleep(3000);
				// Sync.waitElementPresent("xpath", "//li[contains(@class,'level2
				// ')]//a//span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]");

				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Kitchenware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_kichenware_Cultery(String Dataset) {

		String names = data.get(Dataset).get("Cutleryy");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Kitchenware']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a//span[contains(text(),'Cutlery')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'Cutlery')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'Cutlery')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(
						title.contains(Links[i]) || breadcrumb.contains(Links[i])
								|| Common.getPageTitle().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Kitchenware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_kichenware_CookingAndBaking(String Dataset) {

		String names = data.get(Dataset).get("Cooking And Baking");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Kitchenware']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a//span[contains(text(),'Cooking & Baking')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'Cooking & Baking')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'Cooking & Baking')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(
						title.contains(Links[i]) || breadcrumb.contains(Links[i])
								|| Common.getPageTitle().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Kitchenware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_kichenware_ToolsAndGadgets(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Tools And Gadgets");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Kitchenware']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a//span[contains(text(),'Tools & Gadgets')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'Tools & Gadgets')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'Tools & Gadgets')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(
						title.contains(Links[i]) || breadcrumb.contains(Links[i])
								|| Common.getPageTitle().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Kitchenware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_CoffeeAndBeverage(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Coffee And Beverage");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[text()='Coffee & Beverage']");
				Common.clickElement("xpath", "//span[text()='Coffee & Beverage']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				if (Links[i].contains("Coffee & Tea")) {
					header_CoffeeAndBeverage_Coffee_Tea("CoffeeAndTea");
				} else if (Links[i].contains("Barware")) {
					header_CoffeeAndBeverage_Barware("Barware");
				}

				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrum = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(breadcrum);
				System.out.println(Links[i]);
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrum.contains(Links[i]) || breadcrum.contains(title),
						"verifying the header link " + Links[i] + "Under Kitchenware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
		header_CoffeeAndBeverage_ShopAll("Coffee & Beverage");

	}

	public void header_CoffeeAndBeverage_Coffee_Tea(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Coffee And Tea");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
//					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Coffee & Beverage']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a//span[contains(text(),'Coffee & Tea')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'Coffee & Tea')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'Coffee & Tea')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(
						title.contains(Links[i]) || breadcrumb.contains(Links[i])
								|| Common.getPageTitle().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under coffee and tea",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
//					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under coffee and tea",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_CoffeeAndBeverage_Barware(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Bar ware");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
//					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Coffee & Beverage']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//span[text()='Barware']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[contains(text(),'Barware')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li//a//span[contains(text(),'Barware')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(
						title.contains(Links[i]) || breadcrumb.contains(Links[i])
								|| Common.getPageTitle().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under barware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
//					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under barware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_CleaningAndOrganization(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Cleaning And Organization");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				if (Links[i].contains("Kitchen")) {
					header_CleaningAndOrganization_Kitchen("Kitchen");
				} else if (Links[i].contains("Bathroom")) {
					header_CleaningAndOrganization_Bathroom("Bathroom");
				} else if (Links[i].contains("Home & Office")) {
					header_CleaningAndOrganization_HomeAndOffice("HomeAndOffice");
				}
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrum = Common.findElement("xpath", "//nav[@aria-label='Breadcrumbs navigation']").getText();
				System.out.println(title);
				System.out.println(breadcrum);
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrum.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Kitchenware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
		header_CleaningAndOrganization_ShopAll("Cleaning & Organization");
	}

	public void header_CleaningAndOrganization_Kitchen(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Kitchenn");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
//					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Kitchen')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'Kitchen')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'Kitchen')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_CleaningAndOrganization_Bathroom(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Bath room");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
//					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Bathroom')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'Bathroom')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'Bathroom')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_CleaningAndOrganization_HomeAndOffice(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Home And Office");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
//					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Home & Office')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'Home & Office')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				// span[contains(text(),'Food
				// Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'Home & Office')]//following::ul//span[contains(text(),'"
								+ Links[i] + "')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchenware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void header_BabyandToddler(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Baby And Toddler");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Baby & Toddler']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				String breadcrum = Common.findElement("xpath", "//nav[@aria-label='Breadcrumb']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				System.out.println(Links[i]);
				System.out.println(breadcrum);

				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrum.contains(Links[i])
						|| breadcrumb.contains(Links[i]) || Common.getPageTitle().contains(Links[i]) ||title.contains(breadcrumb) ,
						"verifying the header link " + Links[i] + "Under Baby and Toddler",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i],
						"Failed to navigate to the " + Links[i]);

//					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Baby and Toddler",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}



	public void header_ShopAll(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[text()='Shop All']");
				Common.clickElement("xpath", "//span[text()='Shop All']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				Common.assertionCheckwithReport( breadcrumb.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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



	public void header_Kitchenware_ShopAll(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Kitchenware']");
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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

	public void header_CoffeeAndBeverage_ShopAll(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Coffee & Beverage']");
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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

	public void header_CleaningAndOrganization_ShopAll(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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

	public void header_Menu_ImageItemblocksLinks(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shop all");
		String blocks = data.get(Dataset).get("Image blocks");
		String[] Links = names.split(",");
		String[] Link = blocks.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
//						Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[@data-link-type]//span[contains(text(),'" + Link[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();

				Common.assertionCheckwithReport(title.contains(Link[i]) || Common.getPageTitle().contains(Link[i]),
						"verifying the header image link " + Link[i] + "Under Featured",
						"user should navigate to the headr image " + Link[i] + " page",
						"user successfully Navigated to the header image " + Link[i],
						"Failed to navigate to the header image " + Link[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header image link " + Link[i] + "Under Featured",
					"User should navigate to the header image " + Link[i] + "pages",
					" unable to navigate to the header image" + Link[i] + "pages",
					Common.getscreenShot("Failed to navigate to the header image " + Link[i] + "pages"));
			Assert.fail();
		}

	}


	public void header_WeAre_Oxo(String Dataset) {
		String Headerlinks = data.get(Dataset).get("We Are OXO");
		String[] header = Headerlinks.split(",");
		int i = 0;
		try {
			Sync.waitPageLoad();
			for (i = 0; i < header.length; i++) {
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[contains(text(),' We Are OXO')]");
				Sync.waitElementPresent("xpath",
						"//div[contains(@class,'megamenu')]//span[contains(text(),'" + header[i] + "')]");
				Common.clickElement("xpath",
						"//div[contains(@class,'megamenu')]//span[contains(text(),'" + header[i] + "')]");
				header[i].toLowerCase();
				Sync.waitPageLoad();
				System.out.println(header[i]);
				System.out.println(Common.getCurrentURL());
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(
						Common.getCurrentURL().contains(header[i]) || Common.getPageTitle().contains(header[i]),
						"Validate the Footer link " + header[i],
						"Click the footer link " + header[i] + "it will navigate to page" + header[i],
						"successfully navigating to " + header[i] + "page ",
						"Failed to navigate to" + header[i] + "page");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Header link " + header[i],
					"Click the header link " + header[i] + "it will navigate to page" + header[i],
					"Failed to navigate to" + header[i] + "page",
					Common.getscreenShotPathforReport("failed to land on " + header[i]));
			Assert.fail();
		}
		header_FAQs_Corporate_Responsibility();
	}

	public void header_FAQs_Corporate_Responsibility() {
		String Links = "FAQs";
		String Links1 = "Corporate Responsibility";
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[contains(text(),' We Are OXO')]");
			Common.clickElement("xpath", "//span[text()='FAQ']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Knowledge Base"),
					"Validate the header link " + Links,
					"Click the header link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");

			Common.navigateBack();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Header link " + Links,
					"Click the header link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
		// corporate
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[contains(text(),' We Are OXO')]");
			Common.clickElement("xpath", "//span[text()='Corporate Responsibility']");
//				  Common.clickElement("xpath","//a[contains(@aria-label,'Corporate Responsibility')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Corporate Responsibility"),
					"Validate the header link " + Links1,
					"Click the header link " + Links1 + "it will navigate to page" + Links1,
					"successfully navigating to " + Links1 + "page ", "Failed to navigate to" + Links1 + "page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Header link " + Links1,
					"Click the header link " + Links1 + "it will navigate to page" + Links1,
					"Failed to navigate to" + Links1 + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links1));
			Assert.fail();

		}
	}


public void header_1_Percent_Planet() {
		String Links = "1% For The Planet";
		try {
			Common.clickElement("xpath", "//a[contains(@href,'1-percent') and contains(@class,'level-0')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("1% For The Planet"),
					"Validate the header link " + Links,
					"Click the header link " + Links + "it will navigate to page" + Links,
					"successfully navigating to " + Links + "page ", "Failed to navigate to" + Links + "page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Header link " + Links,
					"Click the header link " + Links + "it will navigate to page" + Links,
					"Failed to navigate to" + Links + "page",
					Common.getscreenShotPathforReport("failed to land on " + Links));
			Assert.fail();

		}
	}
	public void Ask_a_question(String Dataset) {
		// TODO Auto-generated method stub
		String Question = data.get(Dataset).get("Comments");
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
			Assert.fail();
		}

	}

	public void filter_validation(String Dataset) {
		// TODO Auto-generated method stub
		String filter = data.get(Dataset).get("Type");

		try {
			Sync.waitElementPresent("xpath", "//div[@class='yotpo-nav-wrapper']//span[contains(text(),'REVIEWS')]");
			Common.clickElement("xpath", "//div[@class='yotpo-nav-wrapper']//span[contains(text(),'REVIEWS')]");
//					Common.clickElement("xpath", "//span[contains(text(),' Water ')]");
//					String search=Common.findElement("xpath", "//span[contains(text(),' Water ')]").getText();
//					for (int i = 0; i <= 10-6; i++) {
//					List<WebElement> webelementslist = Common.findElements("xpath",
//							"//span[@class='highlight-text']");
			//
//					String s = webelementslist.get(i).getText();
//					System.out.println(s);
//					Common.assertionCheckwithReport(s.contains("water") ,
//							"validating the filter reviews",
//							"After Clicking on filters the repective reviews should be displayed",
//							"Sucessfully Respective Reviews has been displayed",
//							"Failed to display the respective reviews");
//					
//					}
//					
//					Sync.waitElementPresent("xpath", "//div[contains(@class,'yotpo-default')]//span[text()='Clear All']");
//					Common.clickElement("xpath", "//div[contains(@class,'yotpo-default')]//span[text()='Clear All']");
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@type='search']", filter);
			Common.actionsKeyPress(Keys.ENTER);
			for (int i = 0; i <= 10 - 5; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath", "//span[@class='highlight-text']");

				String s = webelementslist.get(i).getText();
				System.out.println(s);
				Common.assertionCheckwithReport(s.contains("Star"), "validating the filter reviews search",
						"After Clicking on filters search the repective reviews should be displayed",
						"Sucessfully Respective search Reviews has been displayed",
						"Failed to display the respective search reviews");
			}

			click_arrows();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the filter reviews",
					"After Clicking on filters the repective reviews should be displayed",
					"Unable to display the respective reviews",
					Common.getscreenShot("Failed to display the respective reviews"));
			Assert.fail();
		}
	}

	public void click_arrows() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Next Page')]");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Next Page')]");
			Thread.sleep(3000);
			String rightarrow = Common.findElement("xpath", "//a[contains(@aria-label,'Page 2')]")
					.getAttribute("aria-label");
			Common.assertionCheckwithReport(rightarrow.contains("Current Page"),
					"validating the arrow for the page navigation",
					"After Clicking on right arrow button it display the next page",
					"Sucessfully next page has been displayed", "Failed to display the next page");
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Previous Page')]");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Previous Page')]");
			Thread.sleep(3000);
			String leftarrow = Common.findElement("xpath", "//a[contains(@aria-label,'Page 1')]")
					.getAttribute("aria-label");
			Common.assertionCheckwithReport(leftarrow.contains("Current Page"),
					"validating the arrow for the page navigation",
					"After Clicking on left arrow button it display the previous page",
					"Sucessfully previous page has been displayed", "Failed to display the previous page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the arrow for the page navigation",
					"After Clicking on left arrow button it display the previous page",
					"Unable to display the previous page", Common.getscreenShot("Failed to display the previous page"));
			Assert.fail();
		}

	}

	public void search_filter(String Dataset) {
		// TODO Auto-generated method stub
		String rating = data.get(Dataset).get("Review");
		String filter = data.get(Dataset).get("Comments");
		try {
			Common.clickElement("xpath", "//a[text()='Reviews']");

			Common.clickElement("xpath", "//span[text()='Select']");
			Sync.waitElementPresent("xpath", "//a[text()='" + filter + "']");
			Common.clickElement("xpath", "//a[text()='" + filter + "']");
			for (int i = 0; i <= 10 - 6; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//div[@class='yotpo-review-stars ']//span[text()='" + rating + "']");

				String s = webelementslist.get(i).getText();
				System.out.println(s);
				Common.assertionCheckwithReport(s.contains(rating), "validating the filter search",
						"After Clicking on filters search the repective reviews should be displayed",
						"Sucessfully Respective search Reviews has been displayed",
						"Failed to display the respective search reviews");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void goodTips_Blog(String Dataset) {

		String names = data.get(Dataset).get("blog");
		String[] Blog = names.split(",");
		
		
		int i = 0;
		try {
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Good Tips Blog')]");
			Common.clickElement("xpath", "//span[contains(text(),' Good Tips Blog')]");
			Common.clickElement("xpath", "(//span[text()='Good Tips Blog'])[1]");
			
			for (i = 0; i < Blog.length; i++) {
				
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//a[normalize-space()='" + Blog[i] +"']");
				Common.clickElement("xpath", "//a[normalize-space()='" + Blog[i] +"']");
//						Common.clickElement("xpath", "//a[contains(@aria-label,'" +Blog[i]+ "')]");
				Sync.waitPageLoad();
				Thread.sleep(2000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				Common.assertionCheckwithReport(title.contains(Blog[i]),
						"verifying the header link " + Blog[i] + "Under Featured",
						"user should navigate to the " + Blog[i] + " page",
						"user successfully Navigated to the " + Blog[i], "Failed to navigate to the " + Blog[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Blog[i] + "Under Featured",
					"User should navigate to the " + Blog[i] + "pages",
					" unable to navigate to the " + Blog[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Blog[i] + "pages"));
			Assert.fail();
		}

	}

	public void blog_page() {

		try {

			Common.clickElement("xpath", "//span[contains(text(),' Good Tips Blog')]");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "(//span[text()='Good Tips Blog'])[1]");
			int blogcategory = Common
					.findElements("xpath", "(//div[contains(@class,'hf:shadow-blog-hero block')])[1]")
					.size();
   System.out.println(blogcategory);
			Common.assertionCheckwithReport(
					blogcategory > 0,
					"To validate the Blog page", "user should able to see the Blog Article",
					"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");
			
			Common.scrollIntoView("xpath", "(//span[text()='Read Article'])[1]");
			Common.clickElement("xpath", "(//span[text()='Read Article'])[1]");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog page", "user should able to see the Blog Article",
					"unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
		}
		blog_article_page();
		blog_article_comments();
	}

	public void blog_article_page() {

		try {
			String blogbreadcrumb = Common.findElement("id", "breadcrumbs").getText();
			System.out.println(blogbreadcrumb);
			int blogarticle = Common.findElements("xpath", "//div[contains(@class,'m-blog-post-meta')]").size();
			Common.assertionCheckwithReport(blogbreadcrumb.contains("Good Tips Blog") && blogarticle > 0,
					"To validate the Blog page", "user should able to see the Blog Article",
					"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");

			Common.scrollIntoView("xpath", "//ul[@class='flex items-center']");
			List<WebElement> blogsocial = Common.findElements("xpath", "//ul[@class='flex items-center']//li//button");
			for (WebElement blog : blogsocial) {

				System.out.println(blog.getText());

				Common.assertionCheckwithReport(
						(blog.getText().contains("Pinterest")) || (blog.getText().contains("Facebook"))
								|| (blog.getText().contains("Twitter")) || (blog.getText().contains("Copy URL")),
						"To validate the Blog page", "user should able to see the Blog Article",
						"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog page", "user should able to see the Blog Article",
					"unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
		}
	}

	public void blog_article_comments() {

		try {
			
			Common.scrollIntoView("xpath",  "//iframe[@title='Disqus']");
			Common.switchFrames("xpath", "//iframe[@title='Disqus']");
			String comment = Common.findElement("xpath", "//span[@class='comment-count']").getText();
			System.out.println(comment);
			Common.clickElement("xpath", "//div[text()='Start the discussion…']");
			
			
			List<WebElement> blogsocial = Common.findElements("xpath", "//ul[@data-role='login-menu']/li/button");  

			List<String> ariaLabels = new ArrayList<>();  
			for (WebElement element : blogsocial) {  
			    String ariaLabel = element.getAttribute("aria-label"); 
			    Thread.sleep(1000);
			    ariaLabels.add(ariaLabel); 
			    System.out.println(ariaLabels);
				Common.assertionCheckwithReport(comment.contains("Comments") && ariaLabels.contains("Login with Disqus")||ariaLabels.contains("Apple")||ariaLabels.contains("Facebook")||ariaLabels.contains("Twitter")||ariaLabels.contains("Google")||ariaLabels.contains("Microsoft"),
						"To validate the Blog page", "user should able to see the Blog Article",
						"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");
			}  
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog page", "user should able to see the Blog Article",
					"unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
		}
	}

	public void PDP_video_validation(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
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
			Sync.waitElementPresent("xpath", "//img[@alt='" + product + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//span[@itemprop='name']").getText().trim();
			System.out.println(name);
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			
			WebElement video=Common.findElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[9]");
			Common.scrollIntoView(video);
	
//			Common.actionsKeyPress(Keys.UP);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//span[@itemprop='name']");
			Common.clickElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[9]");	
			Common.clickElement("xpath", "//button[@title='Play video']");
//					Sync.waitElementPresent(30, "xpath", "//button[@title='Play Video']");
//					Common.clickElement("xpath", "//button[@title='Play Video']");
			Sync.waitForLoad();
			
			String video1 = Common.findElement("xpath", "//button[@title='Pause video']")
					.getAttribute("aria-label");
			System.out.println(video1);
			Common.assertionCheckwithReport(video1.equals("Pause video"), "validating the video in PDP page",
					"video should be play in the PDP page", "Sucessfully the video has been played on the PDP page",
					"failed to play the video in PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the video in PDP page", "video should be play in the PDP page",
					"Unable to play the the video on the PDP page",
					Common.getscreenShot("failed to play the video in PDP page"));
			Assert.fail();
		}
	}

	public void click_on_product(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		try {
			String minicartproduct = Common
					.findElement("xpath", "//a[@class='product-item-link hover:underline inline-block' and text()='"+ products +"']").getText();
			Common.clickElement("xpath", "//a[@class='product-item-link hover:underline inline-block' and text()='"+ products +"']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(minicartproduct),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", " unable to Navigates Product to the PDP page",
					Common.getscreenShot("Failed to Navigates Product to the PDP page"));
			Assert.fail();
		}


	}
	public void click_on_Image(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		try {

			click_minicart();
			String minicartimage = Common.findElement("xpath", "//img[contains(@alt,'" + products + "')]")
					.getAttribute("alt");
			Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(products),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", " unable to Navigates Product to the PDP page",
					Common.getscreenShot("Failed to Navigates Product to the PDP page"));
			Assert.fail();
		}

	}


	public void minicart_freeshipping() {
		// TODO Auto-generated method stub
		try {
//			click_minicart();
			String Freeshipping = Common
					.findElement("xpath", "//div[@class='flex items-center']//p")
					.getText();
			Common.assertionCheckwithReport(Freeshipping.equals("Good news: your order will be delivered for Free."),
					"validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"Successfully free shipping is appiled for selected products", "Failed to see free shipping");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"unable to apply free shipping for the selected products",
					Common.getscreenShot("Failed to see free shipping bar"));
			Assert.fail();
		}
	}

	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Products");
		String symbol=data.get(Dataset).get("Symbol");

		try {
			click_minicart();
			Sync.waitElementPresent(30, "xpath", "//span[@x-html='cart.subtotal']//span");
			String subtotal = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
					.replace(symbol, "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common
					.findElement("xpath", "(//p[@class='text-md font-bold dr:title-sm']//a)[1]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").replace(symbol,
					"");
			Float productamount1value = Float.parseFloat(productamount1);
			if (productname.equals(deleteproduct)) {
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath",
						"(//a[contains(@aria-label,'Edit product')]//parent::div//button)[1]");
				Common.clickElement("xpath",
						"(//a[contains(@aria-label,'Edit product')]//parent::div//button)[1]");
				Sync.waitElementPresent("xpath", "//button[contains(text(),'OK')]");
				Common.clickElement("xpath", "//button[contains(text(),'OK')]");
			} else {
				Assert.fail();
			}
			Thread.sleep(6000);
			String subtotal1 = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
					.replace(symbol, "");
			Float subtotal1value = Float.parseFloat(subtotal1);
			Thread.sleep(8000);
			String productamount = Common.getText("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").replace(symbol, "");
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
			Assert.fail();
		}

	}


	public void minicart_product_close() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
			String minicartpopup = Common.findElement("xpath", "//div[@x-ref='removeItemConfirm']")
					.getAttribute("aria-modal");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(minicartpopup.contains("true"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully popup is displayed when we click on the delete button",
					"Failed to Display the popup");
			String popup = Common.findElement("xpath", "//h2[@x-ref='modalHeader' and contains(text(),'Remove Item')]").getText().trim();
			if (popup.equals("Remove Item")) {
				Common.clickElement("xpath", "//button[@aria-label='Close']");
			} else {
				Assert.fail();
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
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the close and cancel functionality",
					"User should able to click on close and cancel button",
					"unable to click on close and cancel button",
					Common.getscreenShot("Failed to Click on close and cancel button"));

			Assert.fail();
		}

	}

	public void MyFavorites_Guestuser(String Dataset) {

		String product = data.get(Dataset).get("Products");
		try

		{
			search_product("Product");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.actionsKeyPress(Keys.DOWN);
			Common.mouseOver("xpath", "//img[@alt='" + product + "']");
			Sync.waitElementPresent(30, "xpath", "//button[contains(@x-data,'initWishlistOnProductList')]");
			Common.clickElement("xpath", "//button[contains(@x-data,'initWishlistOnProductList')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customer Login")
							&& message.contains("You must login or register to add items to your wishlist."),
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

			Assert.fail();
		}

	}

	public void newsletter_subscription() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[text()='Newsletter Subscriptions']");
			Common.clickElement("xpath", "//a[text()='Newsletter Subscriptions']");
			String newsletter = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(newsletter.contains("Newsletter Subscription"),
					"validating the Navigation to the Newsletter Subscription page",
					"After Clicking on Newsletter Subscription CTA user should be navigate to the Newsletter Subscription page",
					"Sucessfully User Navigates to the Newsletter Subscription page after clicking on the Newsletter Subscription CTA",
					"Failed to Navigate to the Newsletter Subscription page after Clicking on Newsletter Subscription button");
			Common.switchFrames("xpath", "//iframe[@id='klaviyo_subscribe_page_1']");
			WebElement Checkbox = Common.findElement("xpath", "//input[@id='field7']");
			if (!Checkbox.isSelected()) {
				Checkbox.click();
				Sync.waitElementPresent(20, "xpath", "//button[text()='SAVE PREFERENCES']");
				Common.clickElement("xpath", "//button[text()='SAVE PREFERENCES']");
			} else {
				Sync.waitElementPresent(20, "xpath", "//button[text()='SAVE PREFERENCES']");
				Common.clickElement("xpath", "//button[text()='SAVE PREFERENCES']");
			}

			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='thxtext2']").getText();
			Common.assertionCheckwithReport(message.contains("Thank you"),
					"validating the success messgae for the Newsletter Subscription",
					"After Clicking on update preference CTA user should be navigate to see the success message",
					"Sucessfully User able to see the success message after clicking on the update preference",
					"Failed to see the success message after clicking on the update preference");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the success messgae for the Newsletter Subscription",
					"After Clicking on update preference CTA user should be navigate to see the success message",
					"Unable to see the success message after clicking on the update preference",
					Common.getscreenShot("Failed to see the success message after clicking on the update preference"));
			Assert.fail();

		}

	}

	// header all tmp
	public void header_Shop(String Dataset) {
		// TODO Auto-generated method stub
//				String names=data.get(Dataset).get("Home Shop");
//				String[] shopLinks=names.split(",");
		int i = 0;
		try {
			Sync.waitElementClickable("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);
			List<WebElement> shop = Common.findElements("xpath", "//li[contains(@class,'level1 nav-1-')]/a/span[2]");

			ArrayList<String> shopmenu = new ArrayList<String>();
			for (WebElement shp : shop) {
				shopmenu.add(shp.getText());

				System.out.println(shopmenu);
			}
			String[] items = data.get(Dataset).get("Home Shop").split(",");

			for (i = 0; i < items.length; i++) {

				if (shopmenu.contains(items[i])) {
					System.out.println(items[i]);
				} else {

					ExtenantReportUtils.addFailedLog("To validate the Answers options in chatbox",
							"All the Answer related options are displayed ", "Missed the " + items[i] + "options",
							Common.getscreenShotPathforReport("failed to display answersoptions"));
					Assert.fail();
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
//					ExtenantReportUtils.addFailedLog("verifying the header link "+kitchenLinks[i]+ "Under Kitchenware",
//							"User should navigate to the "+kitchenLinks[i]+"pages",
//							" unable to navigate to the "+kitchenLinks[i]+"pages",
//							Common.getscreenShot("Failed to navigate to the "+kitchenLinks[i]+"pages"));
			Assert.fail();
		}

	}

	public void view_order() {

		try {
			Sync.waitPageLoad();
			String number = Common.findElement("xpath", "//span[text()='View Order']").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Order']");
			Common.clickElement("xpath", "//span[text()='View Order']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(40, "xpath", "//span[@class='title-lg']");
			String Ordernumber = Common.findElement("xpath", "//span[@class='title-lg']").getText();
			Common.findElement("xpath", "//span[@class='order-status inline-block']//div");
			String reorder = Common.findElement("xpath", "//span[text()='Reorder']").getText();
			String backCTA = Common.findElement("xpath", "//a[@class='hidden lg:flex btn btn-link']").getText().trim();
			String orderdate = Common.findElement("xpath", "//div[@class='mt-1']//span").getText();
			String shippingAdd = Common.findElement("xpath", "//div[contains(@class,'shipping-address')]").getText();
			String billingAdd = Common.findElement("xpath", "//div[contains(@class,'billing-address')]").getText();
			String shippingmethod = Common.findElement("xpath", "//div[contains(@class,'shipping-method')]").getText();
			String ordersummary = Common.findElement("xpath", "//div[contains(@class,'shipping-method')]").getText();
			String itemsordered = Common.findElement("xpath", "//div[@class='product-name-wrapper']").getText();
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

	public void headr_kitchen(String Dataset) {

//				String names=data.get(Dataset).get("Home Shop");
//				String[] shopLinks=names.split(",");
		int i = 0;
		try {
			Sync.waitElementClickable("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");

			Common.mouseOverClick("xpath", "//a[@class='ui-corner-all']//span[contains(text(),'Kitchenware')]");
			// a[@class='ui-corner-all']//span[contains(text(),'Kitchenware')]
			Thread.sleep(3000);
			List<WebElement> shop = Common.findElements("xpath", "//ul//li[contains(@class,'level2 nav-1-1-')]/a");

			ArrayList<String> shopmenu = new ArrayList<String>();
			for (WebElement shp : shop) {
				shopmenu.add(shp.getText());

				System.out.println(shopmenu);
			}
			String[] items = data.get(Dataset).get("Kitchen ware").split(",");

			for (i = 0; i < items.length; i++) {

				if (shopmenu.contains(items[i])) {
					System.out.println(items[i]);
				} else {

					ExtenantReportUtils.addFailedLog("To validate the Header options in chatbox",
							"All the Header related options are displayed ", "Missed the " + items[i] + "options",
							Common.getscreenShotPathforReport("failed to display Header options"));
					Assert.fail();
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
//					ExtenantReportUtils.addFailedLog("verifying the header link "+kitchenLinks[i]+ "Under Kitchenware",
//							"User should navigate to the "+kitchenLinks[i]+"pages",
//							" unable to navigate to the "+kitchenLinks[i]+"pages",
//							Common.getscreenShot("Failed to navigate to the "+kitchenLinks[i]+"pages"));
			Assert.fail();
		}
	}

	public void Gift_Message(String Dataset) {
		// TODO Auto-generated method stub
		String message = data.get(Dataset).get("message");
		try {
			Common.scrollIntoView("xpath", "//button[normalize-space()='Add Gift Message']");
			Sync.waitElementPresent(40, "xpath", "//button[normalize-space()='Add Gift Message']");
			Thread.sleep(4000);
			
			
				Common.javascriptclickElement("xpath", "//button[normalize-space()='Add Gift Message']");
				Common.javascriptclickElement("xpath", "//button[normalize-space()='Add Gift Message']");
				Common.textBoxInput("id", "recipient-1", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("id", "sender-1", data.get(Dataset).get("LastName"));
				Common.textBoxInput("id", "message-1", message);
				Common.clickElement("xpath", "//span[text()='Update']");
				Sync.waitPageLoad(40);
				Thread.sleep(2000);
				Sync.waitElementPresent(40, "xpath", "//span[@x-text='savedFormMessage.message']");
				String Messgae = Common.findElement("xpath", "//span[@x-text='savedFormMessage.message']").getText()
						.replace("Message: ", "");
				System.out.println(Messgae);
				Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
						"Gift card message should be applied", "Sucessfully gift message has been applied ",
						"failed to apply the gift message");
				Thread.sleep(4000);
				
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift cart message", "Gift card message should be applied",
					"Unabled to apply the gift message", Common.getscreenShot("failed to apply the gift message"));
			Assert.fail();
		}
	}

	public String Express_Paypal(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";

		String expectedResult = "It should open paypal site window.";

		try {
			Thread.sleep(3000);
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			// Common.refreshpage();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[contains(@class,'paypal-button-lab')]");
			Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
			Common.switchToDefault();
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
				Assert.fail();
			}
			Thread.sleep(5000);
//			express_paypal_shipping("PaypalDetails");
			
//			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
//			Thread.sleep(3000);
//			select_Shipping_Method("GroundShipping method");
			Thread.sleep(4000);
			if (Common.getText("xpath", "//div[@id='payment-method-view-paypal_express']//p[2]").contains("Paypal")||Common.getCurrentURL().contains("preprod")) {
				Common.scrollIntoView("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
				// Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
				

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

					Assert.fail();
				}
			}
		return order;

	}

	public void express_paypal_shipping(String Dataset) {
		// TODO Auto-generated method stub
		String shippment = data.get(Dataset).get("methods");
		try {
			Thread.sleep(10000);

			int size = Common.findElements("xpath", "//select[@name='shipping_method']").size();
			if (size > 0) {

				Common.clickElement("xpath", "//select[@name='shipping_method']");
				Thread.sleep(1000);

				Common.clickElement("xpath", "(//optgroup[@label='3 to 5 days'])//option");
			} else {
				Common.dropdown("xpath", "//select[@name='shipping_method']", SelectBy.TEXT, shippment);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Paypal_Address_Verification(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//p[@data-testid='ship-to-address']");
			String address = Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
			if (address.contains("united states")) {
				Sync.waitElementPresent("xpath", "//button[@data-testid='change-shipping']");
				Common.clickElement("xpath", "//button[@data-testid='change-shipping']");
//							Common.clickElement("xpath", "//select[@data-testid='shipping-dropdown']");
				Common.dropdown("xpath", "//select[@data-testid='shipping-dropdown']", SelectBy.TEXT,
						data.get(Dataset).get("Street"));
				Thread.sleep(3000);
				String Ukaddress = Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				System.out.println(Ukaddress);
				String UK = data.get(Dataset).get("Street").replace("QA TEST - ", "");
				System.out.println(UK);
				Common.assertionCheckwithReport(Ukaddress.contains(UK),
						"validating the address selection from the drop down",
						"Address should be select from the dropdown ",
						"Sucessfully address has been selected from the dropdown",
						"Failed to select the Address from the dropdown");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the address selection from the drop down",
					"Address should be select from the dropdown ", "Unable to select the Address from the dropdown",
					Common.getscreenShotPathforReport("Failed to select the Address from the dropdown"));
			Assert.fail();
		}

	}

	public void writeOrderNumber(String Description,String OrderIdNumber,String Skus, String AdminOrderstatus, String warkato, String Used_GiftCode)
			throws FileNotFoundException, IOException {
		// String fileOut="";
		try {

			File file = new File(
					System.getProperty("user.dir") + "/src/test/resources//TestData/OXO/OXO_E2E_orderDetails.xlsx");
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
			
			cell.setCellValue("Oxo");
			
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
			cell.setCellValue(warkato);
			
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



	public void prepareOrdersData(String fileName) {
		// TODO Auto-generated method stub
		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/OXO/" + fileName);
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

	public String URL() throws InterruptedException {
		String Website = "";
		Common.findElement("xpath", "//div[@class='c-header__logo']//a[@href='https://mcloud-na-preprod.oxo.com/']");
		Thread.sleep(4000);
		// Common.clickElement("xpath", "//button[@class='needsclick klaviyo-close-form
		// kl-private-reset-css-Xuajs1']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Website = Common.getCurrentURL();

		return Website;

	}

	public HashMap<String, String> Shipingdetails(String dataSet) throws Exception {
		HashMap<String, String> Shippingaddress = new HashMap<String, String>();
		try {
			Thread.sleep(5000);

			int a = Common.findElements("xpath", "//span[contains(text(),'New Address')]").size();

			if (a > 0) {
				Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			} else {

			}
			Thread.sleep(5000);
			int b = Common
					.findElements("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']")
					.size();

			if (b > 0) {
				Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",
						data.get(dataSet).get("Email"));
			} else {

			}

			// Sync.waitElementPresent("xpath",
			// "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
			// Common.textBoxInput("xpath",
			// "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			/*
			 * Sync.waitElementPresent("xpath", "//input[@name='lastname']");
			 * Common.textBoxInput("xpath", "//input[@name='lastname']",
			 * data.get(dataSet).get("LastName"));
			 */
			// Sync.waitElementPresent("xpath", "//input[@name='company']");
			// Common.textBoxInput("xpath", "//input[@name='company']",
			// data.get(dataSet).get("CompanyName")); Sync.waitElementPresent("xpath",
			// "//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			/*
			 * Sync.waitElementPresent("xpath", "//select[@name='country_id']");
			 * Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT,
			 * data.get(dataSet).get("Countryname"));
			 */
			Thread.sleep(3000);
			Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Region"));
			Thread.sleep(5000);
			String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
			String Shippingstate = Common
					.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']")
					.getText();
			Shippingaddress.put("ShippingState", Shippingstate);
			Sync.waitElementPresent("xpath", "//input[@name='city']");
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			Thread.sleep(2000);
			// Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
			String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
			System.out.println("*****" + ShippingZip + "*******");
			Shippingaddress.put("ShippingZip", ShippingZip);
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@name='telephone']");
			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			Thread.sleep(3000);
			int c = Common
					.findElements("xpath", "//button[@class='a-btn a-btn--primary action primary action-save-address']")
					.size();

			if (c > 0) {
				Common.clickElement("xpath",
						"//button[@class='a-btn a-btn--primary action primary action-save-address']");
			} else {

			}

			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.ENTER);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping",
					"faield to add new shipping address",
					Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
			Assert.fail();
		}
		System.out.println(Shippingaddress);
		return Shippingaddress;
	}

	public HashMap<String, String> OrderSummaryValidation() {
		// TODO Auto-generated method stub
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			Thread.sleep(3000);

			String subtotla = Common.getText("xpath", "//tr[@class='totals sub']/td/span[@class='price']").replace("$",
					"");
			// subtotla.replace("", newChar)
			Float subtotlaValue = Float.valueOf(subtotla);
			data.put("subtotlaValue", subtotla);

			// Capabilities cap = (WebDriver).getCapabilities();

			String shippingammount = Common
					.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']").replace("$", "");
			Float shippingammountvalue = Float.valueOf(shippingammount);
			data.put("shippingammountvalue", shippingammount);

			String ActualTotalAmmount = Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			Float ActualTotalammountvalue = Float.valueOf(ActualTotalAmmount);
			data.put("ActualTotalammountvalue", ActualTotalAmmount);

			int SizesofDis = Common
					.findElements("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").size();
			if (SizesofDis > 0) {
				String Discount = Common
						.getText("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])")
						.replace("-$", "");
				Float Discountammountvalue = Float.valueOf(Discount);
				data.put("Discountammountvalue", Discount);

			} else {

				String Discountammountvalue = "0.00";
				data.put("Discountammountvalue", Discountammountvalue);

			}

			int Sizes = Common.findElements("xpath", "//td[@data-th='Tax']//span").size();
			if (Sizes > 0) {

				// Float Taxrate=data.get(dataset).get("tax");

				String TaxAmmount = Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
				Float Taxammountvalue = Float.valueOf(TaxAmmount);
				data.put("Taxammountvalue", TaxAmmount);

				Float ExpectedTotalAmmount = subtotlaValue + shippingammountvalue + Taxammountvalue;

				String ExpectedTotalAmmount2 = new BigDecimal(ExpectedTotalAmmount)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				String ExpectedTotalAmount = String.valueOf(ExpectedTotalAmmount2);
				data.put("ExpectedTotalAmmountvalue", ExpectedTotalAmount);

			} else {
				String Taxammountvalue = "0.00";
				data.put("Taxammountvalue", Taxammountvalue);

				Float ExpectedTotalAmmount = subtotlaValue + shippingammountvalue;
				String ExpectedTotalAmount = String.valueOf(ExpectedTotalAmmount);
				data.put("ExpectedTotalAmmountvalue", ExpectedTotalAmount);
			}

			ExtenantReportUtils.addPassLog("verifying tax calculation",
					"tax rate is matches to given shipping address tax ",
					"successfully tax rate is matches to given shipping address tax",
					"tax rate is not matches to given shipping address tax");
		} catch (Exception | Error e) {
			report.addFailedLog("verifying tax calculation", "getting price values from shipping page ",
					"Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

			e.printStackTrace();
			Assert.fail();

		}

		return data;

	}

	public HashMap<String, String> creditCard_payment(String dataSet) throws Exception {
		HashMap<String, String> Payment = new HashMap<String, String>();

		// Common.actionsKeyPress(Keys.PAGE_UP);

		try {
			// label[@class='label']//span[contains(text(),'Default Payment')]

			// Common.scrollIntoView("id", "paymetric_xisecure_frame");
			Common.clickElement("xpath", "//label[@class='label']//span[contains(text(),'Default Payment')]");
			Thread.sleep(5000);

			/*
			 * Common.switchFrames("xpath",
			 * "//iframe[@title='Secure payment input frame']"); Thread.sleep(5000);
			 * Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
			 * Common.clickElement("xpath", "//label[@for='Field-numberInput']");
			 * Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
			 * Number=Common.findElement("id",
			 * "Field-numberInput").getAttribute("value").replace(" ", "");
			 * System.out.println(Number);
			 * 
			 * Common.textBoxInput("id", "Field-expiryInput",
			 * data.get(dataSet).get("ExpMonthYear"));
			 * 
			 * Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
			 * Thread.sleep(2000); Common.actionsKeyPress(Keys.ARROW_DOWN);
			 * Common.switchToDefault();
			 */

			/*
			 * Common.switchFrames("xpath",
			 * "//iframe[@title='Secure payment input frame']"); int size =
			 * Common.findElements("xpath", "//select[@id='c-ct']").size();
			 * Common.switchToDefault(); Common.assertionCheckwithReport(size > 0,
			 * "validating Creditcard option", "click the creadit card label",
			 * "clicking credit card label and open the card fields",
			 * "user faield to open credit card form");
			 */
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label",
					"faield to click Credit Card option", Common.getscreenShotPathforReport("Cardinoption"));
			Assert.fail();

		}

		try {
			Thread.sleep(3000);
			int size = Common
					.findElements("xpath", "//div[@class='stripe-dropdown-selection']//img[@alt='credit card logo']")
					.size();
			if (size > 0) {

				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Thread.sleep(1000);

				Common.clickElement("xpath", "(//span[text()='New payment method'])[1]");
			}
			// else {}

			Thread.sleep(2000);
			// Common.refreshpage();
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Common.clickElement("xpath", "//label[@for='Field-numberInput']");
			Common.textBoxInput("id", "Field-numberInput", data.get(dataSet).get("cardNumber"));
			Thread.sleep(4000);
			// String CardType=Common.findElement("xpath",
			// "//select[@class='p-CardBrandChoice-select Input']").getAttribute("value");
			String Card = Common.findElement("xpath", "//div[@class='p-CardKnownIcon']").getText();
			Payment.put("Card", Card);
			System.out.println(Card);
			Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));
			Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
			Thread.sleep(2000);

			// Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();

			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//span[contains(text(),'Place Order')]");
			Thread.sleep(6000);
			String URL = Common.getCurrentURL();
			Thread.sleep(4000);
			if (URL.equals("https://oxo.com/checkout/#payment")) {

				Common.getCurrentURL();
				System.out.println(URL);

			} else {
				Thread.sleep(2000);

				Sync.waitElementClickable("xpath", "(//span[contains(text(),'Place Order')])[2]");
				Common.mouseOverClick("xpath", "(//span[contains(text(),'Place Order')])[2]");
			}

			// Common.clickElement("xpath", "(//button[@title='Place Order'])");

			/*
			 * Common.switchFrames("id", "paymetric_xisecure_frame"); String expectedResult
			 * = "credit card fields are filled with the data"; String errorTexts =
			 * Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			 * Common.switchToDefault();
			 * Common.assertionCheckwithReport(errorTexts.isEmpty(),
			 * "validating the credit card information with valid data", expectedResult,
			 * "Filled the Card detiles", "missing field data it showinng error");
			 * 
			 */

			Sync.waitPageLoad();
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation",
					"credit card fields are filled with the data", "faield to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			e.printStackTrace();
			Assert.fail();

		}
		return Payment;

	}

	public String Verify_order_page() throws InterruptedException {
		String Orderid = "";
		Thread.sleep(5000);
		// Common.textBoxInput("id",
		// "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
		String expectedResult = "It redirects to order confirmation page";
		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);

			for (int i = 0; i < 10; i++) {
				Thread.sleep(5000);
				if (Common.getCurrentURL().contains("success")) {
					break;
				}

			}

			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']");
			System.out.println(sucessMessage);
			// String Orderid="";

			Thread.sleep(3000);
			int size = Common.findElements("xpath",
					"//div[@class='m-account-nav__welcome']//span[@class='a-icon-text-btn__label']").size();
			if (size > 0) {

				Orderid = Common.getText("xpath", "(//div[@class='checkout-success']//strong)[1]");
			} else {
				Orderid = Common.getText("xpath", "(//div[@class='checkout-success']//span)[1]");
			}

			System.out.println(Orderid);
			System.out.println("Your order number is:" + Orderid);
			Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),
					"verifying the product confirmation", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();

		}

		return Orderid;

	}

	public String order_Success() throws Exception {

		String order = "";
		// addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		// if (Common.findElements("xpath", "//div[@class='message
		// message-error']").size() > 0) {
		// addPaymentDetails(dataSet);

		Sync.waitPageLoad();
		Thread.sleep(6000);
		// int placeordercount = Common.findElements("xpath", "//span[text()='Place
		// Order']").size();
		// Juttriles code //("xpath", "//span[text()='Place Order']")
		//// button[@title='Place Order'] stage

		// String
		// url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		String url = Common.getCurrentURL();
		Thread.sleep(3000);
		System.out.println(url);

		if (!url.contains("success")) {
			// ExtenantReportUtils.addPassLog(description, expectedResult, actualResult,
			// Common.getscreenShotPathforReport(expectedResult));
			/*
			 * int sizeofelement=Common.findElements("id", "email").size();
			 * Common.assertionCheckwithReport(sizeofelement > 0,
			 * "verifying the paypal payment ", expectedResult,"open paypal site window",
			 * "faild to open paypal account");
			 */
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title']").trim();
				// Assert.assertEquals(sucessMessage, "Your order has been
				// received","Sucess message validations");
				int sizes = Common.findElements("xpath", "//h1[@class='page-title']").size();
				Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "(//a[@class='order-number'])//strong").size() > 0) {
					order = Common.getText("xpath", "(//a[@class='order-number'])//strong");
				}

				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
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

	public void search_E2E_product(String Dataset) {

		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("SKU");

		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", data.get(Dataset).get("SKU"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(6000);
			// Common.refreshpage();
			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
					"enter product name will display in the search box", "user enter the product name in  search box",
					"Failed to see the product name");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	public void Addtocart(String Dataset) {

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
//							Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image')]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.scrollIntoView("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
//						Common.mouseOver("xpath", "//img[@alt='" + products + "']");

			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(products.contains(name), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");

			/*
			 * int size=Common.findElements("xpath",
			 * "//h1[@class='m-product-overview__title u-hidden--lg-down']").size();
			 * if(size>0) {
			 * 
			 * product1=Common.getText("xpath",
			 * "//h1[@class='m-product-overview__title u-hidden--lg-down']"); } else{
			 * product1=Common.getText("xpath",
			 * "//h1[@class='m-product-overview__title u-hidden--lg-down']"); }
			 * 
			 * System.out.println(product1); System.out.println("Product:"+product1);
			 */

			productquantity(Dataset);
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");

			Sync.waitPageLoad();
			Thread.sleep(6000);
			/*
			 * String message = Common.findElement("xpath",
			 * "//div[@data-ui-id='message-success']") .getAttribute("data-ui-id");
			 * Common.clickElement("xpath",
			 * "//button[@class='a-icon-text-btn a-icon-text-btn--icon-only a-message__close']"
			 * ); System.out.println(message);
			 * Common.assertionCheckwithReport(message.contains("success"),
			 * "validating the  product add to the cart", "Product should be add to cart",
			 * "Sucessfully product added to the cart ",
			 * "failed to add product to the cart");
			 */
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
		// return product1;
	}

	public void productquantity(String Dataset) {
		// String product1="";
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);
		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");
//						Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);

			String value = Common.findElement("xpath", "//select[@class='a-select-menu']").getAttribute("value");
			System.out.println(value);
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
			Assert.fail();
		}

	}

	public String shipping_order_details() throws InterruptedException {

		String Products_details = "";
		Thread.sleep(5000);
		try {
			Common.clickElement("xpath", "(//div[@data-role='trigger'])[1]");
			Thread.sleep(2000);
			// String value=Common.findElement("xpath", "//ol[@class='minicart-items
			// reset-list']").getText();
			// System.out.println(value);

			int size = Common.findElements("xpath", "//ol[@class='minicart-items reset-list']").size();
			if (size > 0) {

				Products_details = Common.getText("xpath", "//ol[@class='minicart-items reset-list']");
			} else {
				Products_details = Common.getText("xpath", "//ol[@class='minicart-items reset-list']");
			}

			System.out.println(Products_details);
			System.out.println("Product:" + Products_details);

		} catch (Exception | Error e) {
			// return Products_details;
		}
		return Products_details;

	}

	public HashMap<String, String> payPalPayment(String dataSet) throws Exception {
		HashMap<String, String> Payment = new HashMap<String, String>();

		// public String payPalPayment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(2000);
			Common.clickElement("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			// img[@class='payment-icon']

			// String
			// Card=Common.findElement("xpath","//img[@class='payment-icon']").getText();
			String Card = Common.findElement("xpath", "//span[text()='PayPal Express Checkout']").getText();
			Payment.put("Card", Card);
			System.out.println(Card);

			Thread.sleep(2000);
//						Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			// Common.refreshpage();
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//div[@class='paypal-button-label-container']");
			Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");
			Thread.sleep(2000);

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
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");

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

					Assert.fail();
				}
			}
		}
		// return order;
		return Payment;
	}

	public HashMap<String, String> AfterPayment(String dataSet) throws Exception {
		HashMap<String, String> Afterpay = new HashMap<String, String>();

		// public String payPalPayment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should Select Afterpay payment.";

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
				Common.clickElement("xpath", "//span[text()='New payment method']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(3000);
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath",
						"//div[@aria-label='Payment Methods']//button[@value='afterpay_clearpay']");

				Common.clickElement("xpath",
						"//div[@aria-label='Payment Methods']//button[@value='afterpay_clearpay']");
				Thread.sleep(3000);
				String Card = Common.findElement("xpath", "//span[@class='p-TabLabel TabLabel TabLabel--selected']")
						.getText();
				Afterpay.put("Card", Card);
				System.out.println(Card);

				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Common.clickElement("xpath", "//span[text()='Place Order']");
					Sync.waitPageLoad();
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");

				} else {
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String afterpay = Common.findElement("xpath", "//button[@value='afterpay_clearpay']//span")
							.getText();
					System.out.println(afterpay);
					Common.assertionCheckwithReport(afterpay.contains("Afterpay"),
							"validating the selection of the afterpay method in production environment",
							"After pay should be select in the production environment",
							"After pay is selected in the production environment",
							"Failed to select the after pay method in the production environment");
					Common.switchToDefault();

				}
			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//button[@value='afterpay_clearpay']");
//							Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
//							Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
//							Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
//							Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
//							Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='addressLine1']", data.get(dataSet).get("Street"));
//							Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='locality']", data.get(dataSet).get("City"));
//							Common.dropdown("xpath", "//select[@name='administrativeArea']", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
//							String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
//							Common.assertionCheckwithReport(
//									details.equals(email),
//									"validating the email field for the Afterpay Payment Method",
//									"Email should be entered in the email field in Afterpay payment method","Email has been dispalyed in the Afterpay payment",
//									"Failed to enter email in the Afterpay Payment");
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					Common.clickElement("xpath", "//span[text()='Place Order']");
					Sync.waitPageLoad();

					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
					Common.switchToDefault();

				} else {
					Thread.sleep(4000);
					String afterpay = Common.findElement("xpath", "//button[@value='afterpay_clearpay']//span")
							.getText();
					System.out.println(afterpay);
					Common.assertionCheckwithReport(afterpay.contains("Afterpay"),
							"validating the selection of the afterpay method in production environment",
							"After pay should be select in the production environment",
							"After pay is selected in the production environment",
							"Failed to select the after pay method in the production environment");

				}
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

				Assert.fail();
			}

		}
		// return order;
		return Afterpay;
	}

	public HashMap<String, String> Klarna(String dataSet) {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		// Thread.sleep(4000);

		String fullname = data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
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
				Common.clickElement("xpath", "//span[text()='New payment method']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Thread.sleep(2000);

				String Card = Common.findElement("xpath", "//span[@class='p-TabLabel TabLabel TabLabel--selected']")
						.getText();
				Paymentmethod.put("Card", Card);
				// paymAfterpay.put("Card", Card);
				System.out.println(Card);
//							
				Common.switchToDefault();
//							Common.clickElement("xpath", "//span[text()='Place Order']");

				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Common.clickElement("xpath", "//span[text()='Place Order']");
					Sync.waitPageLoad();
					klarna_Details(dataSet);
				} else {
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna = Common.findElement("xpath", "//button[@value='klarna']//span").getText();
					System.out.println(klarna);
					Common.assertionCheckwithReport(klarna.contains("Klarna"),
							"validating the selection of the klarna method", "klarna should be selected ",
							"klarna is selected", "Failed to select the klarna method in the production environment");
					Common.switchToDefault();

				}

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");
//							
				Common.switchToDefault();

				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Common.clickElement("xpath", "//span[text()='Place Order']");
					Sync.waitPageLoad();
					klarna_Details(dataSet);
				} else {
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna = Common.findElement("xpath", "//button[@value='klarna']//span").getText();
					System.out.println(klarna);
					Common.assertionCheckwithReport(klarna.contains("Klarna"),
							"validating the selection of the klarna method", "klarna should be selected ",
							"klarna is selected", "Failed to select the klarna method in the production environment");
					Common.switchToDefault();

				}
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation",
					"User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
		return Paymentmethod;

	}

	public String ExpressPaypal(String dataSet) throws Exception {

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
			int size1 = Common.findElements("xpath", "//a[text()='Log in with a password instead']").size();
			if(size1>0) {
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
				Assert.fail();
			}
			Sync.waitForLoad();
			Thread.sleep(5000);
//			express_paypal_shipping("PaypalDetails");
			
//			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
//			Thread.sleep(3000);
//			select_Shipping_Method("GroundShipping method");
			Thread.sleep(4000);
			int rewards=Common.findElements("xpath", "//span[contains(text(),'Sign in')]").size();
			System.out.println(rewards);
			if(rewards==1)
			{
				Thread.sleep(5000);
				Common.scrollIntoView("name", "telephone");
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
				Thread.sleep(4000);
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

					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success')]/p/a").size() > 0) {
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]/p/a");
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

					Assert.fail();
				}
			}
		return order;
	}

	// public HashMap<String, String> Paypal_Address(String string)

	public void Paypal_Address() {
		// TODO Auto-generated method stub
		// HashMap<String, String> Shippingaddress = new HashMap<String, String>();
		String expectedResult = "It should open paypal site window.";
		try {
			Sync.waitElementPresent("xpath", "//button[@data-testid='change-shipping']");
			Common.clickElement("xpath", "//button[@data-testid='change-shipping']");
			Thread.sleep(1000);
			Common.findElement("xpath", "//button[@id='add-option-shipping-link']");
			Common.clickElement("xpath", "//button[@id='add-option-shipping-link']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}

		// return null;
	}

	public String ExpressOrder() {

		String expectedResult = "";
		String order = "";
      
		if (Common.getCurrentURL().contains("preprod")) {
			Common.scrollIntoView("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[1]");
			// Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
			// Thread.sleep(2000);

			Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[1]");
		}

		// Tell_Your_FriendPop_Up();//To close the Pop-up
		String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!!url1.contains("preprod")) {
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

				Assert.fail();
			}
		}
		return order;
	}

	public HashMap<String, String> ExpressShipingdetails(String dataSet) throws Exception {
		HashMap<String, String> Shippingaddress = new HashMap<String, String>();
		try {

			Common.clickElement("xpath", "//input[@data-testid='address-auto-suggest']");
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@data-testid='address-auto-suggest']",
					data.get(dataSet).get("Street"));
			/*
			 * Sync.waitElementPresent("xpath", "//select[@name='country_id']");
			 * Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT,
			 * data.get(dataSet).get("Countryname"));
			 */
			Thread.sleep(3000);
			Common.dropdown("xpath", "//select[@id='state']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Thread.sleep(5000);
			String Shippingvalue = Common.findElement("xpath", "//select[@id='state']").getAttribute("value");
			String Shippingstate = Common
					.findElement("xpath", "//select[@id='state']//option[@value='" + Shippingvalue + "']").getText();
			Shippingaddress.put("ShippingState", Shippingstate);
			Sync.waitElementPresent("xpath", "//input[@data-testid='address-form-field-city']");
			Common.textBoxInput("xpath", "//input[@data-testid='address-form-field-city']",
					data.get(dataSet).get("City"));
			Thread.sleep(2000);
			// Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("xpath", "//input[@data-testid='address-form-field-postalCode']",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
			String ShippingZip = Common.findElement("xpath", "//input[@data-testid='address-form-field-postalCode']")
					.getAttribute("value");
			System.out.println("*****" + ShippingZip + "*******");
			Shippingaddress.put("ShippingZip", ShippingZip);
			Thread.sleep(3000);
			int c = Common.findElements("xpath", "//button[@data-testid='add-shipping-save-btn']").size();

			if (c > 0) {
				Common.clickElement("xpath", "//button[@data-testid='add-shipping-save-btn']");
			} else {

			}
			Common.clickElement("id", "payment-submit-btn");
			Thread.sleep(8000);
			Common.switchToFirstTab();

			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping",
					"faield to add new shipping address",
					Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
			Assert.fail();
		}
		System.out.println(Shippingaddress);
		return Shippingaddress;
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
				
				Common.oppenURL("https://na-preprod.hele.digital/heledigitaladmin/admin");
				
				Sync.waitPageLoad(8000);
				Thread.sleep(4000);
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
			Thread.sleep(6000);
			int filters = Common.findElements("xpath", "//div[@class='admin__data-grid-filters-current _show']").size();
			if (filters > 0) {
				Common.clickElement("xpath",
						"//div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']");
				Thread.sleep(4000);
				Common.scrollIntoView("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(3000);
				Common.javascriptclickElement("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", orderNumber);
				Common.actionsKeyPress(Keys.ENTER);
				Thread.sleep(6000);
				Common.findElement("xpath", "//div[@class='data-grid-cell-content']");
	       
			String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
			System.out.println(Number);
			String Warkatostatus=Common.findElement("xpath", "//a[@class='action-menu-item']//parent::td/following-sibling::td[2]//div").getText();
			System.out.println(Warkatostatus);
			Orderstatus1.put("warkato", Warkatostatus);
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
				Thread.sleep(6000);
				Common.scrollIntoView("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(4000);
				Common.javascriptclickElement("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", orderNumber);
				Common.actionsKeyPress(Keys.ENTER);
				Thread.sleep(6000);
				Common.scrollIntoView("xpath", "//div[@class='data-grid-cell-content']");
				String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
				System.out.println(Number);
				String Warkatostatus=Common.findElement("xpath", "//a[@class='action-menu-item']//parent::td/following-sibling::td[2]//div").getText();
				Orderstatus1.put("warkato", Warkatostatus);
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

	public void prepareTaxData(String fileName) {
		// TODO Auto-generated method stub

		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/" + fileName);
			XSSFWorkbook workbook;
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			if (!(file.exists())) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("Order ID");
				CellStyle cs = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.GOLD.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("OXO_US_OrderDetails");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("S.No");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("Company");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("Order Number");
				cell = row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("Digital QA Status(PASS/FAIL)");
				rowcount = 2;
			}

			else {
				workbook = new XSSFWorkbook(new FileInputStream(file));
				sheet = workbook.getSheet("Order ID");
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

	public void writeResultstoXLSx(String Ordernumber) {
		// String fileOut="";
		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/Oxo_OrderNumbers.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("Order ID");

			if ((workbook.getSheet("Order ID")) == null) {
				sheet = workbook.createSheet("Order ID");
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
				cell.setCellValue("Orders details");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Web Order Number");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("SubTotal");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("Shipping");
				cell = row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("TaxRate");
				cell = row.createCell(4);
				cell.setCellStyle(cs);
				cell.setCellValue("Web Configured TaxRate");
				cell = row.createCell(5);
				cell.setCellStyle(cs);
				cell.setCellValue("Actual TaxAmount");
				cell = row.createCell(6);
				cell.setCellStyle(cs);
				cell.setCellValue("Expected TaxAmount");

				rowcount = 2;

			}

			else {

				sheet = workbook.getSheet("Order ID");
				rowcount = sheet.getLastRowNum() + 1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellType(CellType.NUMERIC);
			int SNo = rowcount - 1;
			cell.setCellValue(SNo);
			cell = row.createCell(1);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue("Lotuswave");
			cell = row.createCell(2);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Ordernumber);
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("Lotuswave");

			String status;
			if (Ordernumber.contains("700")) {

				status = "PASS";
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			} else {
				status = "FAIL";
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			}

			cell.setCellValue(status);
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Invalid_search_product(String Dataset) {
		// TODO Auto-generated method stub
		String invalidproduct = data.get(Dataset).get("Products");
		System.out.println(invalidproduct);
		try {
		Common.clickElement("xpath", "//span[@x-show='!searchOpen']");
			String open = Common.findElement("xpath", "//input[contains(@id, 'autocomplete-0-input')]").getAttribute("type");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("search"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']",
					data.get(Dataset).get("Products"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("id", "instant-empty-results-container").getText();
			//String searchproduct=Common.findElement("id", "instant-empty-results-container").getAttribute("class");
			//System.out.println(searchproduct);
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains("No products for query"),
					"validating the search functionality", "enter Invalid product name will display in the search box",
					"user enter the Invalid product name in  search box", "Failed to see the Invalid product name");
			Thread.sleep(8000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter Invalid product name will display in the search box",
					" unable to enter the Invalid product name in  search box",
					Common.getscreenShot("Failed to see the Invalid product name"));
			Assert.fail();
		}
	}

	public void Sort_By(String Dataset) throws InterruptedException {
			// TODO Auto-generated method stub
			String symbol = data.get(Dataset).get("Price_Symbol");
			String PriceFilter = data.get(Dataset).get("Sortby_Dropdown");
			System.out.println(PriceFilter);
			System.out.println(symbol);
			try {
				Sync.waitPageLoad();

				Thread.sleep(5000);
				Common.scrollIntoView("xpath","//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@class=' old-price ']");

				List<WebElement> BeforeFilterprice = Common.findElements("xpath","//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@class=' old-price ']");
				List<String> Beforefilterpricelist = new ArrayList<String>();

				for (WebElement p : BeforeFilterprice) {
					Beforefilterpricelist.add(p.getText().replace(symbol, " "));
					System.out.println("Beforefilterpricelist" + Beforefilterpricelist);
				}
				Thread.sleep(4000);
				Common.dropdown("xpath", "//select[@class='ais-SortBy-select']", SelectBy.TEXT,PriceFilter);
				
				Thread.sleep(5000);
				Common.scrollIntoView("xpath",
						"//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@class=' old-price ']");
				List<WebElement> AfterFilterprice = Common.findElements("xpath",
						"//div[@data-role='priceBox']//span[@data-price-type='finalPrice']//span[@class=' old-price ']");
				List<String> Afterfilterpricelist = new ArrayList<String>();

				for (WebElement p : AfterFilterprice) {
					Afterfilterpricelist.add(p.getText().replace(symbol, " "));
					System.out.println("Afterfilterpricelist" + Afterfilterpricelist);
				}

				if (PriceFilter.equals("Highest price")) {
					Collections.sort(Beforefilterpricelist);
					System.out.println("Beforefilterpricelist Highest " + Beforefilterpricelist);
					Common.assertionCheckwithReport(Beforefilterpricelist.equals(Afterfilterpricelist),
							"To validate the Sort in Product Listing Page",
							"User should able to Sort in Product Listing Page",
							"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
				} else {
					if (PriceFilter.equals("Lowest price")) {
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
				Assert.fail();
			}

		}

	public void Filter() throws InterruptedException {
		// TODO Auto-generated method stub
		try {

			Common.scrollIntoView("xpath", "//div[@class='ais-HierarchicalMenu']//span[text()='Shop']");
			Sync.waitElementPresent("xpath", "//div[@class='ais-HierarchicalMenu']//span[text()='Shop']");
			Common.clickElement("xpath", "//div[@class='ais-HierarchicalMenu']//span[text()='Shop']");
			Sync.waitElementPresent("xpath", "//span[text()='Bundles']");
			Common.clickElement("xpath", "//span[text()='Bundles']");
			Thread.sleep(4000);
			String SelectedFilter = Common.findElement("xpath", "//ul[@class='ais-CurrentRefinements-list']//li[@class='ais-CurrentRefinements-item']//span[@class='ais-CurrentRefinements-category']//span").getText();
			System.out.println(SelectedFilter);
			System.out.println("SelectedFilter:" + SelectedFilter);
			String RetrivedValue = "Set Bundle";
			if (SelectedFilter.equals("Bundles")) {

				List<WebElement> Series_Filters = Common.findElements("xpath",
						"//div[contains(@class,'group/item-image')]//a//img");

				for (WebElement Filter : Series_Filters) {
					// System.out.println(Filter);
					String AttributeValue = Filter.getAttribute("alt");

					if (AttributeValue.contains(RetrivedValue)) {

						System.out.println("Attribute '" + AttributeValue + "' contains the text '" + RetrivedValue
								+ "' for product: " + AttributeValue);

					} else {

						System.out.println("Attribute '" + AttributeValue + "' does not contain the text '"
								+ RetrivedValue + "' for product: " + AttributeValue);
						Assert.fail();
					}
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Filter functionality",
					"Products should be display as per selected Filter option ",
					" Unable to display the Products as per selected Filter option",
					Common.getscreenShot("Failed to Filter"));
			Assert.fail();
		}
	}

	public void CustomerSupport(String Dataset) {
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Thread.sleep(1000);
				Common.findElement("xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Common.clickElement("xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
			
				System.out.println(footerlinks[i]);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
								|| Common.getCurrentURL().contains("contact-us")
								|| Common.getCurrentURL().contains("kustomer.support")
								|| Common.getCurrentURL().contains("product-registration/")
								|| Common.getCurrentURL().contains("voluntary-recall")
								|| Common.getCurrentURL().contains("accessibility-statement")
								|| Common.getCurrentURL().contains("privacy")
								|| Common.getCurrentURL().contains("terms")
								|| Common.getCurrentURL().contains("oxo-rewards-terms-and-conditions"),
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links"); 
				Thread.sleep(2000);
				Common.navigateBack();
				int size = Common.findElements("xpath", "//*[@id='logo']").size();
				System.out.println(size);
				

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
			Assert.fail();
		}

	}

	public void Product_Support(String Dataset) {
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Thread.sleep(1000);
				Common.findElement("xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Common.clickElement("xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
			
				System.out.println(footerlinks[i]);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
								|| Common.getCurrentURL().contains("/track/order/status")
								|| Common.getCurrentURL().contains("shipping")
								|| Common.getCurrentURL().contains("guarantee")
								|| Common.getCurrentURL().contains("oxo-cookware-warranty"),
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links"); 
				Thread.sleep(2000);
				Common.navigateBack();
				int size = Common.findElements("xpath", "//*[@id='logo']").size();
				System.out.println(size);
				

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
			Assert.fail();
		}

	}
	
	
	public void WeAre_Oxo(String Dataset) {
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Thread.sleep(1000);
				Common.findElement("xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Common.clickElement("xpath",
						"//a[text()='"+footerlinks[i] +"']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
			
				System.out.println(footerlinks[i]);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
								|| Common.getCurrentURL().contains("/aboutus/")
								|| Common.getCurrentURL().contains("chefs-in-residence")
								|| Common.getCurrentURL().contains("Blog")
								|| Common.getCurrentURL().contains("inventor-submissions")
								|| Common.getCurrentURL().contains("oxo-affiliate-program")
								|| Common.getCurrentURL().contains("1-percent/")
								|| Common.getCurrentURL().contains("corporate-responsibility")
								|| Common.getCurrentURL().contains("exclusive-savings"),
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links"); 
				Thread.sleep(3000);
				Common.navigateBack();
				int size = Common.findElements("xpath", "//a[@aria-label='Go to Home page']").size();
				System.out.println(size);
				

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
			Assert.fail();
		}

	}
	
	
	public void Newtab_Footerlinks(String dataset) {  
	    String footerLinksString = data.get(dataset).get("Footer Links");  
	    String[] footerLinks = footerLinksString.split(",");  
	    
	    try {  
	        for (String footerLink : footerLinks) {  
	            Sync.waitPageLoad();  
	            Sync.waitElementPresent(30, "xpath", "//a[@title='" + footerLink + "']");   
	            Common.switchWindowsAfterClick("xpath", "//a[@title='" + footerLink + "']"); 
	            Sync.waitPageLoad();  

	            String currentUrl = Common.getCurrentURL();  
	            System.out.println("Current URL: " + currentUrl);  
	            boolean isValidUrl = currentUrl.contains("careers") || currentUrl.contains("overview/default.aspx");  
	            
	            Common.assertionCheckwithReport(isValidUrl,  
	                "Validating the link navigation from footer Link: " + footerLink,  
	                "After clicking on " + footerLink + ", it should navigate to the correct page.",  
	                footerLink + " successfully navigated to the correct URL: " + currentUrl,  
	                "Unable to navigate to the " + footerLink + " link.");  

	            Common.closeCurrentWindow(); 
	           Common.switchToFirstTab();
//	             
	            Thread.sleep(2000);  

	            // Optionally check if home button is present  
	            int size = Common.findElements("xpath", "//a[@aria-label='Go to Home page']").size();  
	            System.out.println("Home button size: " + size);  
	        }  
	    } catch (Exception | Error e) {  
	        e.printStackTrace();  
	        ExtenantReportUtils.addFailedLog("Validating link navigation from footer Links",  
	            "After clicking on " + footerLinks[footerLinks.length - 1] + ", it should navigate to the correct page.",  
	            footerLinks[footerLinks.length - 1] + " Unable to navigate to the link.",  
	            Common.getscreenShot("Failed to navigate to " + footerLinks[footerLinks.length - 1] + " link."));  
	        Assert.fail();  
	    }  
	}

	public void header_CampandGrill(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Camp and Grill");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()='Camp & Grill']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li//a//span[text()='" + Links[i] + "']");
				Common.clickElement("xpath",
						"//li//a//span[text()='" + Links[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumb = Common.findElement("xpath", "//span[@aria-current='page']").getText();
				String breadcrum = Common.findElement("xpath", "//nav[@aria-label='Breadcrumb']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				System.out.println(Links[i]);
				System.out.println(breadcrum);

				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrum.contains(Links[i])
						|| breadcrumb.contains(Links[i]) || Common.getPageTitle().contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Baby and Toddler",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i],
						"Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Baby and Toddler",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
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
			Thread.sleep(8000);
			Sync.waitElementPresent(50, "xpath", "//iframe[@id='challengeFrame']");
			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
			Thread.sleep(4000);
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
				Assert.fail();
			}
		}
		return order;
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
			Thread.sleep(4000);
			String subtotal=Common.findElement("xpath", "(//div[@class='item subtotal']//span[@class='value'])[2]").getText().replace(symbol, "").replace(".", "");
			System.out.println(subtotal);
			subtotal = subtotal.trim();
			subtotal = subtotal.substring(0,subtotal.length() - 2);
		    System.out.println(subtotal);  
			int amount=Integer.parseInt(subtotal);
			System.out.println(amount);
			
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
				Thread.sleep(4000);
				if(amount>199 && symbol.equals("$"))
				{
					Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
					Common.clickElement("xpath", "//div[@class='ampromo-close']");
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
				
				}
				else
				{
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
				}
				
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
	                   if(Common.getCurrentURL().contains("/gb"))
	                   {
	                	   Thread.sleep(5000);
//	                	   Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_5']");
//	                	   Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_5']");
	                	   
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
//	                		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
//	                   		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
//	                   		Thread.sleep(5000);
//	                   		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
//	                       	Common.clickElement("xpath", "//button[@class='action primary checkout']");
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
	              	   Sync.waitElementPresent(30,"xpath", "(//button[@type='button'][normalize-space()='Place Order'])[1]");
	              	   Common.clickElement("xpath", "(//button[@type='button'][normalize-space()='Place Order'])[1]");
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

	public void click_singin_Shippingpage() {
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

	public void Gift_cards() {
		// TODO Auto-generated method stub
//		String GiftCard = data.get(Dataset).get("oxogift");
		try
		{
			
			Common.actionsKeyPress(Keys.DOWN);
				Sync.waitElementPresent("xpath", "//a[text()='Gift Card']");
				Common.clickElement("xpath", "//a[text()='Gift Card']");

			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/oxo-gift-card"),
					"validating the gift card page navigation",
					"After clicking on the gift card it sholud navigate to the PDP page",
					"Successfully Navigated tot he gift card page",
					"Failed to match the Gift card page");
		
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

	public void Card_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			String Amount=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
			Assert.assertEquals(Amount,amount);
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
			Assert.fail();
		}
		
	}
	
	
}