package week4.day1.assignment;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertAssignment {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://buythevalue.in/");
		driver.findElement(By.xpath("(//div[contains(@class,'owl-item active')])[1]")).click();
		driver.findElement(By.id("wk_zipcode")).sendKeys("600045");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@value='Check']")).click();
		Thread.sleep(5000);
		String text = driver.findElement(By.className("wk_availability_msg")).getText();
		System.out.println(text);
		driver.findElement(By.xpath("(//span[text()='Add to Cart'])[1]")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[text()='View Cart']")).click();
		driver.findElement(By.id("checkout")).click();
		Alert alert = driver.switchTo().alert();
		String text2 = alert.getText();
		System.out.println("The Alert message is :"+text2 );
		alert.accept();
		driver.findElement(By.id("agree")).click();
		
	}

}
