package page_object_model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class OrderPage {
    private final WebDriver driver;
    private static final By SECOND_ORDER_PAGE = By.className("Order_Form__17u6u");
    // Поля формы заказа 1 и 2 страниц
    private static final By NAME_INPUT = By.xpath("//input[@placeholder ='* Имя']");
    private static final By LASTNAME_INPUT = By.xpath("//input[@placeholder ='* Фамилия']");
    private static final By ADDRESS_INPUT = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    private static final By METRO_STATION = By.className("select-search");
    private static final By PHONENUMBER_INPUT = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.xpath("//button[text() ='Далее']");
    private static final By DATE_INPUT = By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    private static final By HOW_LONG = By.xpath("//span[@class = 'Dropdown-arrow']");
    //Раздел подтверждения заказа
    private final By COMMENT_INPUT = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private static final By ORDER_BUTTON = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text() ='Заказать']");
    // Попап с запросом подтверждения заказа и кнопками Да/Нет
    private static final By REQUEST_FOR_CONFIRMATION = By.className("Order_Modal__YZ-d3");
    private static final By CONFIRMATION_BUTTON = By.xpath("//button[text() ='Да']");
    //Попап с сообщением о созданном заказе и данными о заказе (номер заказа и тд)
    private static final By CONFIRMED_ORDER_MESSAGE = By.className("Order_ModalHeader__3FDaJ");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void fillOrderFirstPage(String name, String lastname, String city, String metro, String phone) {
        driver.findElement(NAME_INPUT).clear();
        driver.findElement(NAME_INPUT).sendKeys(name);
        driver.findElement(LASTNAME_INPUT).clear();
        driver.findElement(LASTNAME_INPUT).sendKeys(lastname);
        driver.findElement(ADDRESS_INPUT).clear();
        driver.findElement(ADDRESS_INPUT).sendKeys(city);
        driver.findElement(METRO_STATION).click();
        String metroLocator = String.format("//div[@class='Order_Text__2broi' and text()='%s']", metro);
        driver.findElement(By.xpath(metroLocator)).click();
        driver.findElement(PHONENUMBER_INPUT).clear();
        driver.findElement(PHONENUMBER_INPUT).sendKeys(phone);
    }

    public void transferToSecondPage() {
        driver.findElement(NEXT_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(SECOND_ORDER_PAGE));
    }


    public void fillOrderSecondPage(String date, String rentalPeriod, String colour, String comment) {
        driver.findElement(DATE_INPUT).click();
        driver.findElement(DATE_INPUT).sendKeys(date);
        driver.findElement(HOW_LONG).click();
        String rentalPeriodValue = String.format("//div[@class = 'Dropdown-option' and text()='%s']", rentalPeriod);
        driver.findElement(By.xpath(rentalPeriodValue)).click();
        String colourSelected = String.format(".//label[@class= 'Checkbox_Label__3wxSf' and text()='%s']", colour);
        driver.findElement(By.xpath(colourSelected)).click();
        driver.findElement(COMMENT_INPUT).clear();
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
        driver.findElement(ORDER_BUTTON).click();
    }

    public void fillOrderConfirmation() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REQUEST_FOR_CONFIRMATION));
        driver.findElement(CONFIRMATION_BUTTON).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(CONFIRMED_ORDER_MESSAGE).getText();
    }

    public void clickOrderButton(By orderButtonLocator) {
        WebElement orderButton = driver.findElement(orderButtonLocator);
        orderButton.click();
    }
}
