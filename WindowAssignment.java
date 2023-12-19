package week4.day1.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowAssignment {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		Set<String> wh1 = driver.getWindowHandles();
		List<String> winhan1=new ArrayList<String>(wh1);
		driver.switchTo().window(winhan1.get(1));
		driver.findElement(By.xpath("((//div[contains(@class,'x-grid3-row')])/table/tbody/tr[1]/td[1])[3]")).click();
		driver.switchTo().window(winhan1.get(0));
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> wh2 = driver.getWindowHandles();
		List<String> winhan2=new ArrayList<String>(wh2);
		driver.switchTo().window(winhan2.get(1));
		driver.findElement(By.xpath("((//div[contains(@class,'x-grid3-row')])/table/tbody/tr[1]/td[1])[4]")).click();
		driver.switchTo().window(winhan2.get(0));
		driver.findElement(By.className("buttonDangerous")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(5000);
		String title = driver.getTitle();
		System.out.println("Title of the page is : "+title);
		
	}

}
