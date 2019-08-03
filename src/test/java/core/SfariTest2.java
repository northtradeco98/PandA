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

public class SfariTest2 {
	
	static WebDriver driver = new SafariDriver();

	@BeforeTest
	public void BefTest() throws InterruptedException, IOException {

		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));

		String url = info.getProperty("u_rl");
		String phone = info.getProperty("fb_username");
		String idMail = info.getProperty("id_mail");
		String idPassword = info.getProperty("id_password");
		String btCl = info.getProperty("bt_cl");
		String xPathFriends= info.getProperty("x_pathfriends");
		String pasWord = "020202";

		Safari.maxWindWebBrowser(driver);
		Safari.openWebPage(driver, url);
		Thread.sleep(2000);
		Safari.logIn(driver, idMail, phone, idPassword, pasWord, btCl);
		Thread.sleep(3000);
		Safari.openFriends(driver, xPathFriends);
		Thread.sleep(7000);
	}
	
	@Test ()
	public void numOfFriendsTest() throws InterruptedException, IOException {
			
		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));
		
			int num = 1;
			String xPathNumOfFriends = info.getProperty("x_pathnumoffriends");
			assertThat(num, equalTo(Safari.numOfFriends(driver, xPathNumOfFriends)));
	}
	
	@AfterTest
	public void AfTest() {
		driver.quit();
	}

}
