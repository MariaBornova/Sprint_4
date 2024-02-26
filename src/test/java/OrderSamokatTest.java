import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import page_object_model.OrderPage;
import page_object_model.MainPage;
import static page_object_model.MainPage.ORDER_BUTTON_MIDDLE;
import static page_object_model.MainPage.ORDER_BUTTON_TOP;


@RunWith(Parameterized.class)
public class OrderSamokatTest extends StartOptions {
    private final String name;
    private final String lastName;
    private final String city;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String howLong;
    private final String colour;
    private final String comment;
    private final By orderButtonLocator;

    //Класс-конструктор для формы заказа
    public OrderSamokatTest(String name, String lastName, String city, String metroStation, String phoneNumber, String date, String howLong, String colour, String comment, By orderButtonLocator) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.howLong = howLong;
        this.colour = colour;
        this.comment = comment;
        this.orderButtonLocator = orderButtonLocator;

    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Виктор", "Уткин", "Москва", "Сокольники", "+79127964353", "11.04.2024", "сутки", "чёрный жемчуг", "Курьер на входе", ORDER_BUTTON_TOP},
                {"Мария", "Фамилия", "Курган", "Текстильщики", "+79222890666", "11.03.2024", "трое суток", "серая безысходность", "Как можно раньше с утра привезти", ORDER_BUTTON_MIDDLE},
        };
    }

    @Before
    public void openMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookiesOnMainPage();
    }


    @Test
    public void testCreateOrder() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickOrderButton(orderButtonLocator);
        orderPage.fillOrderFirstPage(name, lastName, city, metroStation, phoneNumber);
        orderPage.transferToSecondPage();
        orderPage.fillOrderSecondPage(date, howLong, colour, comment);
        orderPage.fillOrderConfirmation();

        Assert.assertEquals("Заказ не оформлен", "Заказ оформлен", orderPage.getConfirmationMessage());
    }

}
