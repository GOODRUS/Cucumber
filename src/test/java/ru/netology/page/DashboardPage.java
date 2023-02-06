package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    public void verifyIsDashboardPage(){
        heading.shouldBe(visible);
    }

    public DashboardPage transferFromSecondCountOnFirst(String amount, String count) {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").$("[data-test-id=action-deposit]").click();
        $("[data-test-id=amount] input").sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue(amount);
        $("[data-test-id=from] input").sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue(count);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }
}
