import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideGithubTest {

    @Test
    void verifyTextOnWikiPage() {
        open("https://github.com/selenide/selenide");
        $("span[data-content='Wiki']").click();
        $("#wiki-body").shouldHave(text("Soft assertions"));
        $(byLinkText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("JUnit5 extension"));
    }
}
