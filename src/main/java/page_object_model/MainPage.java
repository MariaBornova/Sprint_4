package page_object_model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class MainPage {
    private final WebDriver driver;
    // URL главной страницы
    private static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // Кнопка "Заказать" вверху страницы
    public static final By ORDER_BUTTON_TOP = By.cssSelector(".Button_Button__ra12g");
    // Кнопка "Заказать" внизу страницы
    public static final By ORDER_BUTTON_MIDDLE = By.cssSelector(".Button_Middle__1CSJM");
    //Элемент секции "Вопросы о важном"
    private static final By QUESTION_ITEM = By.className("accordion__item");
    //Кнопка вопроса секции "Вопросы о важном"
    private static final By QUESTION_BUTTON = By.className("accordion__heading");
    //Текст ответа секции "Вопросы о важном"
    private static final By ANSWER_TEXT = By.tagName("p");
    //Секция видимого ответа "О важном" - not hidden
    private static final By VISIBLE_ANSWER = By.xpath("//div[@class='accordion__panel' and not(@hidden)]");
    //Заголовок секции "Вопросы о важном"
    private static final By QUESTION_SECTION = By.className("Home_SubHeader__zwi_E");
    // Кнопка Принять куки
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    public void acceptCookiesOnMainPage() {
        driver.findElement(COOKIE_BUTTON).click();
    }


    public void scrollToQuestion() {
        WebElement questionSection = driver.findElement(QUESTION_SECTION);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionSection);
    }


    private WebElement getQuestionElement(int number) {
        List<WebElement> listFAQ = driver.findElements(QUESTION_ITEM);
        return listFAQ.get(number);
    }

    public void openQuestionOnClick(int number) {
        WebElement item = getQuestionElement(number);
        WebElement questionElement = item.findElement(QUESTION_BUTTON);
        questionElement.click();
    }

    public String getQuestionText(int number) {
        WebElement item = getQuestionElement(number);
        WebElement questionElement = item.findElement(QUESTION_BUTTON);
        return questionElement.getText();
    }

    public String getAnswerText(int number) {
        WebElement item = getQuestionElement(number);
        WebElement answerElement = item.findElement(ANSWER_TEXT);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(answerElement));
        return answerElement.getText();
    }

    public int sizeOfQuestionSectionVisible() {

        return driver.findElements(VISIBLE_ANSWER).size();
    }


}
