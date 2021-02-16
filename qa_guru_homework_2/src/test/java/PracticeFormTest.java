import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormTest {

    //User's data
    String userFirstName = "Mister";
    String userLastName = "World";
    String userEmail = "misterworld@test.com";
    String userGender = "Female";
    String userNumber = "7777777777";
    String monthOfBirth = "June";
    String yearOfBirth = "1991";
    String dayOfBirth = "13";
    String subjects = "Computer Science";
    String hobbies = "Music";
    String currentAddress = "Green Street, 11";
    String state = "Haryana";
    String city = "Karnal";
    Map<String, String> actualResultMap  = new HashMap<>();

    @Test
    void populateAndSubmitContactFormTest() {
        open("https://demoqa.com/automation-practice-form");

        //Populate and Submit the contact form
        $("#firstName").setValue(userFirstName);
        $("#lastName").setValue(userLastName);
        $("#userEmail").setValue(userEmail);
        $(byText(userGender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--0" + dayOfBirth).click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $(byText(hobbies)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/avatar.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        //Verify the data in the submitted form is correct
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
        $$(".table.table-dark tbody tr").shouldHave(size(10));
        for (SelenideElement row : $$(".table.table-dark tbody tr")) {
            actualResultMap.put(row.find("td", 0).text(), row.lastChild().text());
        }
        assertEquals(String.format("%s %s", userFirstName, userLastName), actualResultMap.get("Student Name"));
        assertEquals(userEmail, actualResultMap.get("Student Email"));
        assertEquals(userGender, actualResultMap.get("Gender"));
        assertEquals(userNumber, actualResultMap.get("Mobile"));
        assertEquals(String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth), actualResultMap.get("Date of Birth"));
        assertEquals(subjects, actualResultMap.get("Subjects"));
        assertEquals(hobbies, actualResultMap.get("Hobbies"));
        assertEquals("avatar.jpg", actualResultMap.get("Picture"));
        assertEquals(currentAddress, actualResultMap.get("Address"));
        assertEquals(String.format("%s %s", state, city), actualResultMap.get("State and City"));
    }
}
