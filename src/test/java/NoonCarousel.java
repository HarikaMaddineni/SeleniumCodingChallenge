

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.dockerjava.api.model.Driver;

public class NoonCarousel {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.noon.com/uae-en/");
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0, 1000)");
		
			String sec="Recommended for you";
			//String sec2="Trending deals in electronics";
			List<String> srt=printCarousel(sec, driver);
			Collections.sort(srt);
			for(String l:srt) {
				System.out.println(l);
				
			}
		}
	
	public static List printCarousel(String name,WebDriver driver) throws InterruptedException {
		
		List<String> list = new ArrayList();
		String xpath="//div[@data-qa='widget_productCarousel']//h2";
		List<WebElement> carousel =driver.findElements(By.xpath(xpath));
		System.out.println("No of carousel "+carousel.size());
		loopCarosuel:for(int i=0;i<=carousel.size();i++) {
			
			
			String xpath_colName="("+xpath+")["+(i+1)+"]";
			System.out.println(xpath_colName);
			
			WebElement carousel_name= driver.findElement(By.xpath(xpath_colName));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
					String list_name=carousel_name.getText();
				//	System.out.println("name of carousel "+list_name);
					
							if(list_name.equalsIgnoreCase(name)) {
								
								String xpath_productName=xpath_colName+"//ancestor::div[@data-qa='widget_productCarousel']//div[@data-qa='product-name']/span";
								System.out.println("xpath "+xpath_productName);
								List<WebElement> carousel_list= driver.findElements(By.
										xpath(xpath_productName));
								
								System.out.println("no produts in carousel = "+carousel_list.size());
							
										for(WebElement ele :carousel_list) {
																						
													list.add(ele.getText().replaceAll("\\n", ""));
													if(ele.getText().isBlank()) {
																										
														WebElement next=driver.findElement(By.xpath("//div[@class='sc-kItwNn ifHGEf']/div[2]"));
														if(next.isDisplayed()) {
																			
															next.click();
															Thread.sleep(1000);
															
														}
													
														else break;
														
													
												}
								
							}
										
						break loopCarosuel;			
					}
		
							js.executeScript("window.scrollBy(0, 500)");
			}
		return list;

	
	}
}
