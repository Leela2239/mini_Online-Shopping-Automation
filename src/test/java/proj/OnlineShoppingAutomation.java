package proj;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineShoppingAutomation extends Validation{
	public static String price1;
	public static String price2;
	public static String totalPrice;
	public static WebDriver driver; 
	public void DriverSetup() {
		driver=new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
	public void maximize() {
		driver.manage().window().maximize();
	}
	
	public void openUrl() {
		driver.get("https://www.flipkart.com/");
	}
	
	public void search() throws IOException {
	//driver.findElement(By.xpath("/html/body/div[3]/div/span")).click();
	
	//In the search box enter the search criteria “Home appliances”.
	driver.findElement(By.className("Pke_EE")).sendKeys(Excel.Input());
	driver.findElement(By.className("_2iLD__")).click();
	
	}
	
	public String getPrice1() {
		 price1 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/div/a[3]/div/div[1]")).getText();
		
		System.out.println(price1);
		return price1;
		
	}
	public String getPrice2() {
		
		price2 = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[2]/div/div[2]/div/a[3]/div/div[1]")).getText();
		System.out.println(price2);
		return price2;
	}
	
	
	public void addProduct1() {
		//select first product and add to the cart.
				driver.findElement(By.className("s1Q9rs")).click();
				Set<String> s = driver.getWindowHandles();
				ArrayList<String> ar = new ArrayList<String>(s);
				driver.switchTo().window((String)ar.get(1));
				driver.findElement(By.xpath("//*[@id=\'container\']/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")).click(); // first item add to cart
				
				//given explicit wait
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"_2KpZ6l _2ObVJD _3AWRsL\"]")));				driver.close();
				driver.switchTo().window((String)ar.get(0));
	}
	
	
	public String addProduct2() {
		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[2]/div/a[2]")).click();// second product
		Set<String> st = driver.getWindowHandles();// select and add second product control 
		ArrayList<String> arl = new ArrayList<String>(st);
		driver.switchTo().window((String)arl.get(1));
		driver.findElement(By.xpath("//*[@id=\'container\']/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")).click();
	 

		//display total amount on the console
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		totalPrice= driver.findElement(By.cssSelector("div[class='Ob17DV _3X7Jj1'] span")).getText();
		System.out.println(totalPrice);
		return totalPrice;
		
	}
	public void screenshot(String name) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\New folder\\example\\src\\test\\resources\\ScreenShot"+name+".png");
		FileUtils.copyFile(src, trg);
	}
	public static void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String args[]) throws IOException {

		OnlineShoppingAutomation obj=new OnlineShoppingAutomation();
		obj.DriverSetup(); 
		obj.openUrl();
		
		obj.maximize();
		obj.screenshot("Home page");
		
		obj.search();
		obj.screenshot("window");
		String price1=obj.getPrice1();
		String price2=obj.getPrice2();
		obj.addProduct1();
		obj.screenshot("1st item in cart");
		String totalPrice=obj.addProduct2();
		obj.screenshot("2nd item in cart");
		Excel.output(price1,price2,totalPrice);
		
		Validation.validation(price1,price2,totalPrice);
		closeBrowser();

	}
}
