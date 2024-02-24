import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import page_object_model.OrderPage;
import page_object_model.MainPage;

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

    //Класс-конструктор для формы заказа
    public OrderSamokatTest(String name, String lastName, String city, String metroStation, String phoneNumber, String date, String howLong, String colour, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.howLong = howLong;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Виктор", "Уткин", "Москва", "Сокольники", "+79127964353", "11.04.2024", "сутки", "чёрный жемчуг", "Курьер на входе"},
                {"Мария", "Фамилия", "Курган", "Текстильщики", "+79222890666", "11.03.2024", "трое суток", "серая безысходность", "Как можно раньше с утра привезти"},
        };
    }

    @Test
    public void CreateOrderByMiddleButton() {
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookiesOnMainPage();
        mainPage.scrollMiddleOrderButton();
        mainPage.clickMiddleOrderButton();
        orderPage.fillOrderFirstPage(name, lastName, city, metroStation, phoneNumber);
        orderPage.transferToSecondPage();
        orderPage.fillOrderSecondPage(date, howLong, colour, comment);
        orderPage.fillOrderConfirmation();

        Assert.assertEquals("Неверный статус.Заказ не оформлен", "Заказ оформлен", orderPage.getConfirmationMessage());
    }


    @Test
    public void CreateOrderByTopButton() {
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookiesOnMainPage();

        mainPage.clickTopOrderButton();
        orderPage.fillOrderFirstPage(name, lastName, city, metroStation, phoneNumber);
        orderPage.transferToSecondPage();
        orderPage.fillOrderSecondPage(date, howLong, colour, comment);
        orderPage.fillOrderConfirmation();

        Assert.assertEquals("Заказ не оформлен", "Заказ оформлен", orderPage.getConfirmationMessage());
    }

}
