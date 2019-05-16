import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FormTest {


    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/cornholio/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }

    @Test
    public void fillForm() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        WebElement firstName = driver.findElement(By.id("first-name"));
        if (firstName.isDisplayed()) {
            firstName.sendKeys("Karol");
        }


        WebElement lastName = driver.findElement(By.id("last-name"));
        String name = lastName.getAttribute("name");
        String inputValue = "Kowalski";
        workWithWebelement(lastName, name, inputValue);

        List<WebElement> elements = driver.findElements(By.cssSelector(".radio-inline"));
        for (WebElement element : elements) {
            if (element.getText().equals("Female")) {
                element.click();
                break;
            }
        }
        driver.findElement(By.id("dob")).sendKeys("02/10/1995");
        WebElement address = driver.findElement(By.id("address"));
        String addressName = address.getAttribute("name");
        String addressInputValue = "Prosta 51";
        workWithWebelement(address,addressName,addressInputValue);

        driver.findElement(By.id("email")).sendKeys("tes@test.com");
        driver.findElement(By.id("password")).sendKeys("haslo0123");
        driver.findElement(By.id("company")).sendKeys("CodersLab");

        Select roleDropDown = new Select(driver.findElement(By.name("role")));
        roleDropDown.selectByVisibleText("Manager");

        Select jobDropdown = new Select(driver.findElement(By.name("expectation")));
        jobDropdown.selectByVisibleText("Good teamwork");

        driver.findElement(By.xpath("//label[text() = 'Read books']")).click();

        driver.findElement(By.id("comment")).sendKeys("testujemy czy dziala");

        driver.findElement(By.id("submit")).click();

        String text = driver.findElement(By.id("submit-msg")).getText();
        assertEquals("Successfully submitted!", text);


    }

        @Test
        public void checkErrors() {
            driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
            driver.findElement(By.id("submit")).click();
//            List<String> listOfIds = getListOfIds();
            List<String> listOfIds = Arrays.asList("first-name", "last-name", "gender");
            int counter = 0;
            for (String elementLocator : listOfIds) {
                elementLocator = elementLocator + "-error";
                assertEquals("This field is required.", driver.findElement(By.id(elementLocator)).getText());
                counter++;
            }
            System.out.println(counter);
        }
            private List<String> getListOfIds() {
            List<String> listId = new ArrayList<>();
            listId.add("first-name");
            listId.add("last-name");
            listId.add("gender");
            return listId;
        }

//        System.out.println(elements.get(0).getText());
//        System.out.println(elements.get(1).getText());
//        System.out.println(elements.get(2).getText());
//        elements.get(0).click();



    private void workWithWebelement(WebElement webElement, String name, String inputValue) {
        if (webElement.isDisplayed()) {
            webElement.sendKeys(inputValue);
            System.out.println(name + ": " + inputValue);
        }
    }


    }


