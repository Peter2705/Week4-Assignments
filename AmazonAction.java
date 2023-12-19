package week4.day1.assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonAction {

	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Oneplus 9 pro",Keys.ENTER);
		String expPrice = driver.findElement(By.xpath("(//*[@class='a-price-whole'])[1]")).getText();
		System.out.println("Expected price is "+expPrice);
		String customerRating = driver.findElement(By.xpath("(//*[@class='a-row a-size-small'])[1]/span[2]")).getText();
		System.out.println("Customer rating for the product is :"+customerRating);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("(//span[contains(@class,'a-text-normal')])[1]")).click();
		Thread.sleep(2000);
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles.size());
		for(String handle:windowHandles) {
			System.out.println(handle);
		}
		List<String> winhan= new ArrayList<String>(windowHandles);
		for(int i=1;i<winhan.size();i++) {
			driver.switchTo().window(winhan.get(i));
			System.out.println(driver.getTitle());
			
		}
		Thread.sleep(5000);
//		driver.switchTo().window(winhan.get(1));
//		System.out.println(driver.getTitle());
		WebElement product = driver.findElement(By.id("landingImage"));
		File source = product.getScreenshotAs(OutputType.FILE);
		File destn = new File("./snap/pic1.png");
		FileUtils.copyFile(source, destn);
		driver.findElement(By.id("add-to-cart-button")).click();
		//	driver.findElement(By.xpath("(//*[@id='submit.add-to-cart'])[1]")).click();
		Thread.sleep(5000);
		String actPrice = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		System.out.println("Actual price is "+actPrice);
		if(actPrice.contains(expPrice) ) {
			System.out.println("Cart Total is Correct");
		}
		else {
			System.out.println("Cart Total is not correct");
		}
		
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(winhan.get(0));
		driver.close();
		
	}

}
