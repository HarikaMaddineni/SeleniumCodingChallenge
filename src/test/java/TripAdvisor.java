

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TripAdvisor {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.tripadvisor.in/");
		driver.findElement(By.xpath("//div[@class='icwvJ P']//input[@name='q']")).
		sendKeys("club mahindra"+Keys.ENTER);
		driver.findElement(By.xpath("//div[@class='aspect  is-shown-at-desktop']")).click();
		
		Set<String> windowsHandle= driver.getWindowHandles();
		for(String handle:windowsHandle) {
			System.out.println(driver.switchTo().window(handle).getTitle());
		}
		Thread.sleep(2000);	
		
		
		driver.findElement(By.xpath("//span[contains(text(),'Review')]")).click();
		
		List<WebElement> ratings=driver.findElements(By.xpath("//*[local-name()='svg' and @class='yGxQr']"));
		 Actions act = new Actions(driver);
		for(WebElement e: ratings) {
			
			act.moveToElement(e);
			act.click().perform();
		
			 
		  }
		
		driver.findElement(By.xpath("//div[@class='RCAPL u']")).click();
		Thread.sleep(1000);
		List <WebElement> month_year=driver.findElements(By.xpath("//ul[@role='listbox']//li//span"));
		for(WebElement e :month_year) {
			if(e.getText().equalsIgnoreCase("April 2023")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div//span[contains(text(),'Family')]")).click();
		driver.findElement(By.xpath("//div[@class='wcqsK']//textarea")).sendKeys("I recently had the pleasure of staying at Club Mahindra, and I must say it was an exceptional experience from start to finish. From the moment I arrived, I was greeted with warm smiles and a genuine sense of hospitality. Club Mahindra truly knows how to create a welcoming and comfortable atmosphere for its guests." );
		
		WebElement title=driver.findElement(By.xpath("//input[@id='review-title']"));
		title.clear();
		title.sendKeys("THE GREAT EXPERIENCE");
		driver.findElement(By.xpath("//div[@class='biGQs _P fiohW qWPrE oXJmt']")).click();
		
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss= new StringSelection(System.getProperty("user.dir")+"\\images\\download.jpeg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.delay(1000);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		
		Set<String> presenthandles= driver.getWindowHandles();
		for(String h:presenthandles) {
			//System.out.println(driver.switchTo().window(h).getTitle());
		}
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//driver.switchTo().frame(0);
		WebElement close=driver.findElement(By.xpath("//button[@aria-label='Close']//*[name()='svg']"));
		close.click();
		
		WebElement submit=driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
		
		js.executeScript("arguments[0].click();",submit);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@aria-label='Close']")).click();
		
		
		
		
		
		

	}

}
