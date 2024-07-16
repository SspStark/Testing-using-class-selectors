import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class JobbyAppTest {

    public static void main(String[] args){

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\sspto\\Downloads1\\Softwares\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");

        // Open Chrome Browser
        WebDriver driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://qajobbyapp.ccbp.tech/login");

        // Find and fill in the form fields
        WebElement usernameEl = driver.findElement(By.cssSelector("input#userNameInput"));
        usernameEl.sendKeys("rahul");

        WebElement passwordEl = driver.findElement(By.cssSelector("input#passwordInput"));
        passwordEl.sendKeys("rahul@2021");

        WebElement buttonEl = driver.findElement(By.cssSelector("button[type='submit']"));
        buttonEl.submit();

        // Define the expected URL of the home page
        String homePageUrl=  "https://qajobbyapp.ccbp.tech/";

        // Wait for the expected URL to be loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(homePageUrl));

        // Get the current URL and verify redirection to home page
        String currentUrl = driver.getCurrentUrl();

        if(currentUrl.equals(homePageUrl)){
            System.out.println("Navigation to home page is successful!");
        }

        // Find and click on the "Find Jobs" button
        WebElement findJobsButtonEl = driver.findElement(By.cssSelector("button.find-jobs-button"));
        findJobsButtonEl.click();

        // Define the expected URL of the home page
        String jobsPageUrl=  "https://qajobbyapp.ccbp.tech/jobs";

        // Wait for the expected URL to be loaded
        wait.until(ExpectedConditions.urlToBe(jobsPageUrl));

        // Get the current URL and verify redirection to jobs page
        currentUrl = driver.getCurrentUrl();

        if(currentUrl.equals(jobsPageUrl)){
            System.out.println("Navigation to jobs page is successful!");
        }else {
            System.out.println("Navigation to jobs page failed!");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.jobs-list")));

        // Verify the display of jobs on the jobs page
        List<WebElement> jobs = driver.findElements(By.cssSelector("li[class*='job-it']"));

        if (jobs.size() > 0) {
            System.out.println("Jobs are displayed successfully on the jobs page: "+ jobs.size());

            // here we are using css combinator selectors
            String expectedRating = "4";
            WebElement jobRatingEle = driver.findElement(By.cssSelector("a[href='/jobs/bb95e51b-b1b2-4d97-bee4-1d5ec2b96751'] > li p.rating"));
            String rating = jobRatingEle.getText();

            if (expectedRating.equals(rating)) {
                System.out.println("Rating is 4");
            } else {
                System.out.println("Job Rating is not as expected");
            }

            // here we are using css Pseudo-class Selectors
            // WebElement childElement = driver.findElement(By.cssSelector("ul.jobs-list>:first-child"));
            // WebElement childElement = driver.findElement(By.cssSelector("ul.jobs-list>:last-child"));
            WebElement childElement = driver.findElement(By.cssSelector("ul.jobs-list>:nth-child(2)"));
            // WebElement childElement = driver.findElement(By.cssSelector("ul.jobs-list>a:nth-of-type(2)"));
            childElement.click();

            String jobsDetailsPageUrl = "https://qajobbyapp.ccbp.tech/jobs/d6019453-f864-4a2f-8230-6a9642a59466";
            wait.until(ExpectedConditions.urlToBe(jobsDetailsPageUrl));

            currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(jobsDetailsPageUrl)) {
                System.out.println("Navigation to job details page is successful!");
            }
        } else {
            System.out.println("No Jobs found on the jobs page.");
        }

        // Close the browser
        driver.quit();

    }
}