package week4.day1.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NykaaActions {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions act= new Actions(driver);
		act.moveToElement(brand).perform();
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.xpath("(//a[contains(text(),'Oreal Paris')])[1]")).click();
		String title = driver.getTitle();
		System.out.println("The title is :"+title);
		String T1="L'Oréal Paris";
		if (title.contains(T1)) {
			System.out.println("The page navigated to the correct title");
		}
		else {
			System.out.println("The title is not correct");
		}
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//label[contains(@for,'customer top rated')]")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Colour Protection']")).click();
		String filterShampoo = driver.findElement(By.xpath("(//span[text()='Shampoo'])")).getText();
		String s1="Shampoo";
		if(filterShampoo.equals(s1)) {
			System.out.println("Shampoo is filtered");
		}
		else {
			System.out.println("Shampoo is not filtered");
		}
			
		driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();
		Set<String> wh1 = driver.getWindowHandles();
		List<String> winhan1=new ArrayList<String>(wh1);
		driver.switchTo().window(winhan1.get(1));
		driver.findElement(By.xpath("//span[text()='180ml']")).click();
		String mrp = driver.findElement(By.xpath("((//span[text()='MRP:'])[1])//following-sibling::span")).getText();
		System.out.println("Expected MRP is :"+mrp);
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		driver.findElement(By.className("cart-count")).click();
		WebElement findElement = driver.findElement(By.xpath("(//iframe)[1]"));
		driver.switchTo().frame(findElement);
		String grandTotal = driver.findElement(By.xpath("(//div[contains(@class,'footer-layout')]//div)[3]/span")).getText();
		System.out.println("Grand Total is :"+grandTotal);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button[text()='Continue as guest']")).click();
		driver.findElement(By.xpath("//div[@class='css-gecnnw eqr1d9n12']")).click();
		String actPrice = driver.findElement(By.xpath("(//div[contains(@class,'leftSelector')])[2]/p")).getText();
		System.out.println("Actual Bag Total is :"+actPrice);
		if(grandTotal.equals(actPrice)) {
			System.out.println("The price matches");
		}
		else {
			System.out.println("Price doesnt match");
		}
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(winhan1.get(0));
		driver.close();
	}

}
