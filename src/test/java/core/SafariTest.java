package core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SafariTest {

	static WebDriver driver = new SafariDriver();

	@BeforeTest
	public void BefTest() throws InterruptedException, IOException {

		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));

		String url = info.getProperty("u_rl");

		Safari.maxWindWebBrowser(driver);
		Safari.openWebPage(driver, url);
		Thread.sleep(2000);
	}

	@Test
	public void getCopyRightTest() throws InterruptedException, IOException {

		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));

		String xPath = info.getProperty("x_path");
		String copyRight = " Facebook © 2019";

		assertThat(copyRight, equalTo(Safari.getCopyRight(driver, xPath)));
	}

	@Test
	public void getTitleWebPageTest() throws InterruptedException, IOException {

		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));

		String strTitle = info.getProperty("str_title");

		assertThat(strTitle, equalTo(Safari.getTitleWebPage(driver)));
	}

	@Test
	public static void logInTest() throws InterruptedException, IOException {
		
		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));
		
		String phone = info.getProperty("fb_username");
		String idMail = info.getProperty("id_mail");
		String idPassword = info.getProperty("id_password");
		String btCl = info.getProperty("bt_cl");
		String pasWord = "";
		String title = "Facebook";

		assertThat(title, equalTo(Safari.logIn(driver, idMail, phone, idPassword, pasWord, btCl)));
	}

//	@Test
//	public void mainTest() {
//		assertE
//	}

//	@Test
//	public void maxWindWebBrowserTest() {
//		throw new RuntimeException("Test not implemented");
//	}

//	@Test
//	public void openFriendsTest() {
//		throw new RuntimeException("Test not implemented");
//	}

//	@Test
//	public void openWebPageTest() {
//		throw new RuntimeException("Test not implemented");
//	}
	
//	@Test
//	public void LogOutTest() {
//		Safari.LogOut(null, null, null, null);
//	}

	@AfterTest
	public void AfTest() {
		driver.quit();
	}
}
