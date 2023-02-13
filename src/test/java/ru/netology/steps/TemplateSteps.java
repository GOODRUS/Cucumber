package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static ru.netology.data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage;


    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyDashboardPage() {
        dashboardPage.verifyIsDashboardPage();
    }

    @Тогда("появляется ошибка о неверном коде проверки")
    public void verifyCodeIsInvalid() {
        verificationPage.verifyCodeIsInvalid();
    }

    @Когда("пользователь в личном кабинете нажимает на кнопку пополнение счёта '5559 0000 0000 0001' \\{{int}}")
    public void verifyDashboardPageV2(int index) {
        transferPage = dashboardPage.getCardBalance(index);
    }

    @И("указывает в поле сумму {string} и номер карты списания {string}")
    public void transferFromSecondCardToFirst(String amount, String count) {
        dashboardPage = transferPage.transferFromSecondCountOnFirst(amount, count);
    }

    @Тогда("пользователь успешно производит перевод и попадает на страницу с 'Номерами карт'")
    public void verifyDashboardPageV3() {
        dashboardPage.verifyIsDashboardPage();
    }

    @Когда("сумма на счету '5559 0000 0000 0001' равняется \\{{int}}, сумма на счету '5559 0000 0000 0002' равняется \\{{int}}")
    public void Check(int amountFirstCard, int amountSecondCard) {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalanceV2(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalanceV2(secondCardInfo);
        assertEquals(amountFirstCard, firstCardBalance);
        assertEquals(amountSecondCard, secondCardBalance);
    }
}
