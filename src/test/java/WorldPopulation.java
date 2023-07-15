

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorldPopulation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.worldometers.info/world-population/");
		
		int count=1;
		while(count<21) {
		WebElement cur_popu=driver.findElement(By.xpath("//span[@rel='current_population']"));
		System.out.println("current population "+cur_popu.getText());
		List<WebElement> today=driver.findElements(By.
				xpath("//div[@class='col1in' or 'col2in']//div[@class='sec-counter']/span"));
		System.out.println("***************Today and year population************"  +count);
		for(WebElement e:today) {
			System.out.print("\t"+e.getText()+"\t");
		}
		Thread.sleep(1000);
		count++;		}

	}

}
