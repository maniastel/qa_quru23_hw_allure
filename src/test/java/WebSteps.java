import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
   @Step("Открываем домашнюю страницу")
    public void openHomePage () {
       open("https://github.com/");
    }

    @Step("Ищем репозиторий {repository}")
    public void searchForRepository (String repository) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repository);
        $("#query-builder-test").pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repository}")
    public void clickOnRepositoryLink (String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Кликаем на таб Issue")
    public void openIssueTab () {
        $("#issues-tab").click();
    }

    @Step("Проверка наличия issue с номером #{issue}")
    public void shouldSeeIssueWithNumber (int issue) {
        $(withText("#" + issue)).shouldBe(Condition.exist);
    }

}
