package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {

        System.out.println("Start Test case: testCase01");

        driver.get("https://calendar.google.com/");
        wait.until(ExpectedConditions.urlContains("calendar"));
        String calendarUrl = driver.getCurrentUrl();
        System.out.println("The Calenar URL: " + calendarUrl);

        if (calendarUrl.contains("calendar")) {

            System.out.println("The URL of the Calendar homepage contains calendar: PASS");

        } else {

            System.out.println("The URL of the Calendar homepage missing calendar: FAIL");
        }

        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {

        System.out.println("Start Test case: testCase02");

        // to locate the calendar view type
        WebElement viewOption = driver.findElement(By.xpath(
                "//button[@class='AeBiU-LgbsSe AeBiU-LgbsSe-OWXEXe-Bz112c-UbuQg AeBiU-LgbsSe-OWXEXe-dgl2Hf I2n60c']//span[@class='AeBiU-vQzf8d']"));
        // javascript executor to perform click action
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", viewOption);
        String currentView = viewOption.getText();// to fetch the text of view type
        System.out.println("current view:" + currentView);

        // condition to check if the current view is Month or Day
        if (currentView.equalsIgnoreCase("Month")) {

            // to click on day option from the dropdown
            WebElement dayOption = driver.findElement(By.xpath("//li[@data-viewkey='day']"));
            dayOption.click();
            Thread.sleep(2000);

            // again click on dropdown to change to month view
            viewOption = driver.findElement(By.xpath(
                    "//button[@class='AeBiU-LgbsSe AeBiU-LgbsSe-OWXEXe-Bz112c-UbuQg AeBiU-LgbsSe-OWXEXe-dgl2Hf I2n60c']//span[@class='AeBiU-vQzf8d']"));
            js.executeScript("arguments[0].click();", viewOption);
            Thread.sleep(1000);

            // to select month option from the dropdown
            WebElement monthOption = driver.findElement(By.xpath("//li[@data-viewkey='month']"));
            monthOption.click();
            Thread.sleep(2000);

        } else if (currentView.equalsIgnoreCase("Day")) {

            // Another way to select the dropdown
            WebElement dropdown = driver.findElement(By.xpath("//i[normalize-space()='arrow_drop_down']"));
            dropdown.click();
            Thread.sleep(2000);

            // to select month view
            WebElement monthOption = driver.findElement(By.xpath("//li[@data-viewkey='month']"));
            Thread.sleep(5000);
            js.executeScript("arguments[0].click();", monthOption);
            Thread.sleep(2000);
        }

        // to fetch the final view of the calendar
        WebElement finalView = driver.findElement(By.xpath(
                "//button[@class='AeBiU-LgbsSe AeBiU-LgbsSe-OWXEXe-Bz112c-UbuQg AeBiU-LgbsSe-OWXEXe-dgl2Hf I2n60c']//span[@class='AeBiU-vQzf8d']"));
        String finalViewText = finalView.getText();

        // to locate the create button
        WebElement create = driver.findElement(
                By.xpath("//button[@class='E9bth-BIzmGd E9bth-BIzmGd-OWXEXe-X9G3K T2watc aAW7Jd APIQad']"));
        create.click();
        Thread.sleep(1000);

        // to locate the task option from the dropdown
        WebElement task = driver.findElement(By.xpath("//li[@data-key='task']"));
        task.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='Add title']")));

        // to add title in the task pop up
        WebElement addTitle = driver.findElement(By.xpath("//input[@aria-label='Add title']"));
        addTitle.sendKeys("Crio INTV Task Automation");

        // to add date in the task pop up
        WebElement date = driver.findElement(By.xpath("(//span[@class='JyrDof'])[1]"));
        date.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@aria-label='30, Wednesday'])[2]")));
        WebElement selectDate = driver.findElement(By.xpath("(//button[@aria-label='30, Wednesday'])[2]"));
        selectDate.click();

        // to add description in the task pop up
        WebElement description = driver.findElement(By.xpath("//textarea[@aria-label='Add description']"));
        description.sendKeys("Crio INTV Calendar Task Automation");

        // finally save the task
        WebElement save = driver.findElement(By.xpath(
                "//button[@class='UywwFc-LgbsSe UywwFc-LgbsSe-OWXEXe-dgl2Hf UywwFc-StrnGf-YYd4I-VtOx3e UywwFc-kSE8rc-FoKg4d-sLO9V-YoZ4jf pEVtpe']"));
        save.click();
        Thread.sleep(2000);

        // to locate the created task
        WebElement createdTask = driver.findElement(By.xpath("//div[@class='KF4T6b smECzc jKgTF QGRmIf']"));
        System.out.println("Created Task text is:" + createdTask.getText());

        // nested if
        // where first if will vrify whether the view mode is Month
        if (finalViewText.contains("Month")) {
            // to check whether task is created or not
            if (createdTask.getText().contains("Crio INTV Task Automation")) {

                System.out.println("The Calendar switched to month view and a task was created.: PASS");

            } else {

                System.out.println("The Calendar didn't switch to month view and task was not created.: FAIL");
            }

            System.out.println("end Test case: testCase02");
        }

    }

    public void testCase03() throws InterruptedException {

        System.out.println("Start Test case: testCase03");

        WebElement createdTask = driver.findElement(By.xpath("//div[@class='KF4T6b smECzc jKgTF QGRmIf']"));
        createdTask.click();
        Thread.sleep(2000);

        // to edit the created task
        WebElement editTask = driver
                .findElement(By.xpath("//button[@aria-label='Edit task']//span[@class='pYTkkf-Bz112c-kBDsod-Rtc0Jf']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", editTask);

        // to clear the previous description and add a new one
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//textarea[@class='Fgl6fe-fmcmS-wGMbrd vRGQ0d']")));
        WebElement description = driver.findElement(By.xpath("//textarea[@class='Fgl6fe-fmcmS-wGMbrd vRGQ0d']"));
        description.clear();
        Thread.sleep(1000);
        description.sendKeys(
                "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");

        WebElement save = driver.findElement(By.xpath(
                "//button[@class='UywwFc-LgbsSe UywwFc-LgbsSe-OWXEXe-dgl2Hf UywwFc-StrnGf-YYd4I-VtOx3e UywwFc-kSE8rc-FoKg4d-sLO9V-YoZ4jf']"));
        save.click();
        Thread.sleep(2000);

        // to fetch the updated description and verify whether the new description is updated
        WebElement updatedTask = driver.findElement(By.xpath("//div[@class='KF4T6b smECzc jKgTF QGRmIf']"));
        updatedTask.click();
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@class='toUqff vfzv']")));

        WebElement updatedTaskDesc = driver.findElement(By.xpath("//div[@class='toUqff vfzv']"));
        js.executeScript("arguments[0].click();", updatedTaskDesc);
        String taskDescText = updatedTaskDesc.getText();

        if (taskDescText.contains(
                "Crio INTV Task Automation is a test suite")) {

            System.out.println("The task description is successfully updated and displayed.: PASS");

        } else {

            System.out.println("The task description is not updated and displayed.: FAIL");
        }

        //to close the task pop up post verification
        WebElement closeBtn = driver.findElement(By.xpath("(//span[@class='notranslate VfPpkd-kBDsod'])[6]"));
        js.executeScript("arguments[0].click();", closeBtn);
        Thread.sleep(1000);

        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {

        System.out.println("Start Test case: testCase04");

        WebElement task = driver.findElement(By.xpath("//div[@class='KF4T6b smECzc jKgTF QGRmIf']"));
        task.click();
        Thread.sleep(1000);

        //to locate and verify task title
        WebElement taskTitle = driver
                .findElement(By.xpath("(//span[normalize-space()='Crio INTV Task Automation'])[2]"));

        if (taskTitle.getText().equalsIgnoreCase("Crio INTV Task Automation")) {

            System.out.println("Task Title verified: PASS");

        } else {

            System.out.println("Task title is not verified: FAIL");
        }

        //to delete a created task
        WebElement deleteTask = driver
                .findElement(By.xpath("//button[@aria-label='Delete task']//div[@class='pYTkkf-Bz112c-RLmnJb']"));
        deleteTask.click();
        Thread.sleep(1000);

        WebElement deleteTaskMsg = driver.findElement(By.xpath("//div[@class='VYTiVb']"));
        String deleteMsgText = deleteTaskMsg.getText();

        if (deleteMsgText.contains("Task deleted")) {

            System.out.println(
                    "The task is successfully deleted, and the confirmation message indicates Task deleted: PASS");
        } else {

            System.out.println("The task is still present: FAIL");
        }

        System.out.println("end Test case: testCase04");
    }
}