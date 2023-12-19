package week4.day1.assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SnapdealActions {
	static int count =0;
	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Actions act= new Actions(driver);
		WebElement mensfashion = driver.findElement(By.xpath("(//span[@class='catText'])[1]"));
		act.moveToElement(mensfashion).perform();
		driver.findElement(By.xpath("((//a[contains(@class,'rightMenuLink')])/span[text()='Sports Shoes'])[1]")).click();
		String shoecount = driver.findElement(By.xpath("(//div[text()='Sports Shoes for Men'])/following-sibling::div")).getText();
		System.out.println("count of sport shoes for men :"+shoecount);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.className("sort-selected")).click();
		driver.findElement(By.xpath("//ul/li[@data-sorttype='plth']")).click();
		Thread.sleep(2000);
		List<WebElement> priceorder = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> price=  new ArrayList<Integer>();
		List<Integer> price1=  new ArrayList<Integer>();

		for(int i=0; i<priceorder.size();i++) {
			String text=priceorder.get(i).getAttribute("data-price");
			int num = Integer.parseInt(text);
			price.add(num);
		}

		for(int i=0; i<priceorder.size();i++) {
			String text1=priceorder.get(i).getAttribute("data-price");
			int num = Integer.parseInt(text1);
			price1.add(num);
		}
		Collections.sort(price); 
		System.out.print("The order of price from Application : "+price1);
		System.out.println();
		System.out.print("The order of price after sorting : "+price);
		System.out.println();
		

//		for(int i=0; i<price.size();i++) {
//			for (int j=0; j<price1.size();j++) {
//				if(price.get(i)==price1.get(j)) {
//					count=count+1;
//					break;
//					//System.out.println("The values are sorted correctly");
//				}
//				else {
//					count=0;
//					break;
//					//System.out.println("The values are not sorted correctly");
//				}
//
//			}
//			break;
//		}
//		System.out.println("The count value is: "+count);
//		if (count>0) {
//			System.out.println("The values are sorted correctly");
//		}
//		else {
//			System.out.println("The values are not sorted correctly");
//		}

		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("500");
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1000");
		driver.findElement(By.xpath("//div[contains(@class,'price-go')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='filter-color-tile White&Blue ']")).click();
		Thread.sleep(2000);
		//check the filters
		String expfilterprice = driver.findElement(By.xpath("(//div[@class='navFiltersPill'])[1]")).getText();
		System.out.println(expfilterprice);
		String expfiltercolor = driver.findElement(By.xpath("(//div[@class='navFiltersPill'])[2]")).getText();
		System.out.println(expfiltercolor);
//		String fromprice = driver.findElement(By.xpath("//span[@class='from-price-text']")).getText();
//		String toprice = driver.findElement(By.xpath("//span[@class='to-price-text']")).getText();
//		String filter1 = driver.findElement(By.xpath("((//span[@class='filter-dot'])[1])/parent::div")).getText();
//		System.out.println(filter1+": "+fromprice+" - "+toprice);
//		String filter2 = driver.findElement(By.xpath("((//span[@class='filter-dot'])[2])/parent::div")).getText();
//		String Color = driver.findElement(By.xpath("//div[@class='sdCheckbox filters-list active-filter']/label/a")).getText();
//		System.out.println(filter2+": "+Color);
		
		if (expfilterprice.contains("Price")) {
			System.out.println("Price filter applied correctly");
		}
		else {
			System.out.println("Price filter not applied correctly");
		}
		
		if (expfiltercolor.contains("Color")) {
			System.out.println("Color filter applied correctly");
		}
		else {
			System.out.println("Color filter not applied correctly");
		}

		WebElement shoes = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		
		act.moveToElement(shoes).perform();
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("The Cost of the shoe is :"+cost);
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("The discount for the shoe is :"+discount);
		
		WebElement snapshot = driver.findElement(By.xpath("(//img[@class='cloudzoom'])[1]"));
		File src = snapshot.getScreenshotAs(OutputType.FILE);
		File des=new File("./snap/shoes.png");
		FileUtils.copyFile(src, des);
		driver.findElement(By.xpath("//div[contains(@class,'close close1')]")).click();
		Thread.sleep(1000);
		driver.close();
	}

}
