package Login;

import org.eclipse.sisu.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class login {

    //import selenium web driver
    private WebDriver driver;
    @BeforeTest

    public void setup() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "resources/chromeDriver.exe");
        driver = new ChromeDriver();

        //waiting for globally
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        // maximize the window
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php");
        driver.manage().deleteAllCookies();
    }
     @Test(priority = 1)
     public void verifyCompanyLogo (){
         //Find the company logo element and verify
         driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img")).isDisplayed();
     }
     @Test(priority = 2)
    public void verifyForgotPassword(){
        //find the forgot password elements
        driver.findElement(By.xpath("//*[@id=\"forgotPasswordLink\"]")).isDisplayed();

    }
    @Test(priority = 3)
    public void Successfullogin (){
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        System.out.println("i have enter my password");
        driver.findElement(By.id("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        System.out.println("I Have successfully login ");
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

    }
    @Test(priority = 4)
    public void  addSystemuser(){
        driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/b")).click();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.findElement(By.id("btnAdd")).click();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.findElement(By.id("systemUser_userType")).click();
        driver.findElement(By.xpath("//*[@id=\"systemUser_userType\"]/option[2]")).isSelected();
        driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("coolliver");
        driver.findElement(By.id("systemUser_userName")).sendKeys("liversam1");
        driver.findElement(By.id("systemUser_status")).click();
        driver.findElement(By.xpath("//*[@id=\"systemUser_status\"]/option[1]")).isSelected();
        driver.findElement(By.id("systemUser_password")).sendKeys("Test@123");
        driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("Test@123");
        driver.findElement(By.id("btnSave")).click();
        System.out.println("New user added ");      
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"menu_leave_viewLeaveModule\"]/")).click();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList");
    }
    @Test(priority = 5)
    public void assignLeave(){
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.findElement(By.id("menu_leave_assignLeave")).click();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave");
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.findElement(By.id("assignleave_txtEmployee_empName")).sendKeys("coolliver");
        driver.findElement(By.id("assignleave_txtLeaveType")).click();
        driver.findElement(By.xpath("//*[@id=\"assignleave_txtLeaveType\"]/option[11]")).isSelected();
        driver.findElement(By.id("assignleave_leaveBalance")).sendKeys("10");
        driver.findElement(By.id("assignleave_txtFromDate")).sendKeys("2022-03-07");
        driver.findElement(By.id("assignleave_txtToDate")).sendKeys("2022-05-03");
        driver.findElement(By.id("assignleave_txtComment")).sendKeys("ENjoy your Honey moon");
        driver.findElement(By.id("assignBtn")).click();
        System.out.println("user assign leave successfully ");




    }

    @AfterTest
    public void close(){
        // close the browser after test
        driver.quit();


    }

    //initiate the test run command
    public static void main(String arg[]) throws InterruptedException {
        login test = new login();
        test.setup();
        test.verifyCompanyLogo();
        test.verifyForgotPassword();
        test.Successfullogin();
        test.addSystemuser();
        test.assignLeave();

    }


}
