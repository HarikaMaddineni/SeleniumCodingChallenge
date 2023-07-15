

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonProductSeaarch {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		String search_text="samsung washing machine top load 10 kg";
		//Samsung Galaxy Z Fold4  
		//apple iphone 14 128 gb yellow
		//samsung washing machine top load 10kg
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(search_text+Keys.ENTER);
		
		
				
		String pagesText=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span")).getText();
		System.out.println(pagesText);
		Pattern pattern =Pattern.compile("\\d+");
		Matcher m=pattern.matcher(pagesText);
		
		int first=0;
		int second=0;
		int third =0;
		if(m.find()) {
			first=Integer.parseInt(m.group());
		}
		if(m.find()) {
			second=Integer.parseInt(m.group());
		}
		if(m.find()) {
			third=Integer.parseInt(m.group());
		}
		System.out.println(" "+first+"  " +second +"\t"+third);
		int pageCount=third/second;;
		
		if(third%second!=0) {
			pageCount++;
			
		}
		 System.out.println("page count "+pageCount);
		 
		 forLoop:for(int i=1;i<=pageCount;i++) {
			 
			 if(pageCount>1) {
				 WebElement pagination_link=driver.findElement(By.xpath("//span[@class='s-pagination-strip']//*[text()='"+i+"']"));
				 pagination_link.click();
				 Thread.sleep(3000);
		 
			 }
			 List<WebElement> product_name=driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/div/h2/a/span"));
			 
			 for(WebElement e:product_name) {
				 System.out.println("product name "+e.getText());
				if( productIsAvailable(search_text, e.getText())) {
					e.click();
					 Set<String> windowsHandle= driver.getWindowHandles();
						for(String handle:windowsHandle) {
							System.out.println(driver.switchTo().window(handle).getTitle());
						}
					 
					 System.out.println("price of the product "+ driver.findElement(By.xpath("//div[@class='a-section a-spacing-none aok-align-center']//span[@class='a-price-symbol']")).getText()+driver.findElement(By.xpath("//div[@class='a-section a-spacing-none aok-align-center']//span[@class='a-price-whole']")).getText());
					break forLoop;
				}
				else {
					System.out.println("product is not available");
					//continue;
				}
				
				
			 }
			 
			 
		 }
		 
		
		
		
	}
	
public static boolean  productIsAvailable(String actual,String productName) {
		
		String[] actual_split= actual.split("\\s");
		String regex = "";
		for(int i=0;i<actual_split.length;i++) {
			regex=regex+"(?=.*\\b"+actual_split[i]+"\\b)";
		}
		regex=regex+".*$";
		System.out.println("regex generated "+regex);
		
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher match =pattern.matcher(productName);
		if(match.matches()) {
			System.out.println("pattern matched ********** Product found");
			return true;
		}
		else {
			System.out.println("pattern  not matched ********** Product  not found");
			return false;
		}
		
		
		
	}

}
