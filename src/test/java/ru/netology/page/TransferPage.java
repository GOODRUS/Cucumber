package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement formField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage transferFromSecondCountOnFirst(String amount, String count) {
        amountField.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        amountField.setValue(amount);
        formField.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        formField.setValue(count);
        transferButton.click();
        return new DashboardPage();
    }
}

