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

    @Когда("пользователь в личном кабинете нажимает на кнопку пополнение счёта {string} \\{{int}}")
    public void verifyDashboardPageV2(String count, int index) {
        transferPage = dashboardPage.clickCard(index);
    }

    @И("указывает в поле сумму {string} и номер карты списания {string}")
    public void transferFromSecondCardToFirst(String amount, String count) {
        dashboardPage = transferPage.transferFromSecondCountOnFirst(amount, count);
    }

    @Тогда("пользователь успешно производит перевод и попадает на страницу с 'Номерами карт'")
    public void verifyDashboardPageV3() {
        dashboardPage.verifyIsDashboardPage();
    }

    @Когда("сумма на счету {string} равняется \\{{int}}, сумма на счету {string} равняется \\{{int}}")
    public void Check(String countFirst, int amountFirstCard, String countSecond, int amountSecondCard) {
        var firstCardBalance = dashboardPage.getCardBalance(countFirst);
        var secondCardBalance = dashboardPage.getCardBalance(countSecond);
        assertEquals(amountFirstCard, firstCardBalance);
        assertEquals(amountSecondCard, secondCardBalance);
    }
}
