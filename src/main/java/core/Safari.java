package core;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.*;
import com.beust.jcommander.*;

public class Safari {

	@Parameter(names = { "-p", "--password" }, description = "PasswordFacebook")
	static String password = null;

	public static void main(String[] args) throws InterruptedException, IOException, FileNotFoundException {

		JCommander.newBuilder().addObject(new Safari()).build().parse(args);

		String file = "./ts_0101.properties";
		Properties info = new Properties();
		info.load(new FileInputStream(file));

		String phone = info.getProperty("fb_username");
		String idMail = info.getProperty("id_mail");           							
		String idPassword = info.getProperty("id_password");							
		String btCl = info.getProperty("bt_cl");
		String url = info.getProperty("u_rl");								
		String xPath = info.getProperty("x_path");							
		String xPathFriends= info.getProperty("x_pathfriends");
		String xPathNumOfFriends= info.getProperty("x_pathnumoffriends");
		String label = info.getProperty("l_abel");
		String logOut = info.getProperty("log_out");
		
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		WebDriver driver;
		driver = new SafariDriver();
		maxWindWebBrowser(driver);

		openWebPage(driver, url);

		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		System.out.println("Title: " + getTitleWebPage(driver));

		System.out.println("Copyright: " + getCopyRight(driver, xPath));

		System.out.println(logIn(driver, idMail, phone, idPassword, password, btCl)); // return title posle loggin

		Thread.sleep(3000);

		openFriends(driver, xPathFriends);

		Thread.sleep(7000);

		System.out.println(numOfFriends(driver, xPathNumOfFriends)); // vivod kol-va druzei v formate int

		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		LogOut(driver, wait, label, logOut); // LogOut

		driver.quit();
	}

	static void maxWindWebBrowser(WebDriver driverInp) {
		driverInp.manage().window().maximize();
	}

	static void openWebPage(WebDriver driverInp, String urlInp) {
		driverInp.get(urlInp);
	}

	static String getTitleWebPage(WebDriver driverInp) {
		return driverInp.getTitle();
	}

	static String getCopyRight(WebDriver driver, String xPathInp) {
		return driver.findElement(By.xpath(xPathInp)).getText();
	}

	static String logIn(WebDriver driver, String idMail, String phone, String idPassword, String pasWord, String btCl)
			throws InterruptedException {
		driver.findElement(By.id(idMail)).sendKeys(phone);
		driver.findElement(By.id(idPassword)).sendKeys(pasWord);
		driver.findElement(By.id(btCl)).click();
		Thread.sleep(10000);
		return driver.getTitle();
	}

	static void logIn2(WebDriver driver, String idMail, String phone, String idPassword, String pasWord, String btCl)
			throws InterruptedException {
		driver.findElement(By.id(idMail)).sendKeys(phone);
		driver.findElement(By.id(idPassword)).sendKeys(pasWord);
		driver.findElement(By.id(btCl)).click();
		Thread.sleep(5000);
	}

	static void openFriends(WebDriver driver, String xPathFriends) {
		driver.findElement(By.xpath(xPathFriends)).click();
	}

	static int numOfFriends(WebDriver driver, String xPathNumOfFriends) {
		String Friends = driver.findElement(By.xpath(xPathNumOfFriends)).getText();
		
		int numFriends = Integer.parseInt(Friends);

		return numFriends;
	}

	static void LogOut(WebDriver driver, WebDriverWait wait, String label, String logOut) {
		driver.findElement(By.id(label)).click();
		WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(logOut)));
		logout.click();
	}

}
