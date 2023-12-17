import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 84;

    WebSteps steps = new WebSteps();
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание issue")
    @Owner("mstelmakh")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение issue #84 в табе Issue в репозитории eroshenkoam/allure-example")
    public void LamdaStepsTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем домашнюю страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Кликаем на таб Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверка наличия issue с номером #" + ISSUE, () -> {
            $(withText("#" + ISSUE)).shouldBe(Condition.exist);
        });

    }

    @Test
    public void AnnotationStepsTest () {
        steps.openHomePage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

}
