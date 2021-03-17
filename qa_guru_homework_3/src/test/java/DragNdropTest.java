import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragNdropTest {

    @Test
    void dragNdropRectangleTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");
        $("#column-a").find("header").shouldHave(text("B"));
        $("#column-b").find("header").shouldHave(text("A"));
    }

}
