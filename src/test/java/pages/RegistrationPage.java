package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultsTable;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbyRadioBtn = $("#hobbiesWrapper"),
            uploadImageInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateTabs = $("#state"),
            stateInput = $("#stateCity-wrapper"),
            cityTabs = $("#city"),
            cityInput = $("#stateCity-wrapper"),
            submitBtn = $("#submit"),
            userForm = $("form#userForm");

    CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Open registration page")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Set first name")
    public RegistrationPage setFirstName (String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Set last name")
    public RegistrationPage setLastName (String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Set email")
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Set gender")
    public RegistrationPage setGender (String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    @Step("set user number")
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("set date of birth")
    public RegistrationPage setDateOfBirth (String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Check results")
    public RegistrationPage checkResult(String key, String value) {
        new ResultsTable().checkResult(key, value);
        return this;
    }

    public RegistrationPage checkResults(String key, List<String> values) {
        for (String value : values) {
            new ResultsTable().checkResult(key, value);
        }
        return this;
    }

    @Step("Set subject")
    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Set subjects")
    public RegistrationPage setSubjects(List<String> values) {
        for (String value : values) {
            subjectInput.setValue(value).pressEnter();
        }
        return this;
    }

    @Step("Set hobby")
    public RegistrationPage setHobby(String hobby) {
        hobbyRadioBtn.$(byText(hobby)).click();
        return this;
    }

    @Step("Upload image")
    public RegistrationPage uploadImage(String value) {
        uploadImageInput.uploadFromClasspath("images/" + value);
        return this;
    }

    @Step("Set address")
    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    @Step("Set state")
    public RegistrationPage setState (String value) {
        stateTabs.scrollTo();
        stateTabs.click();
        stateInput.$(byText(value)).click();
        return this;
    }

    @Step("Set city")
    public RegistrationPage setCity (String value) {
        cityTabs.scrollTo();
        cityTabs.click();
        cityInput.$(byText(value)).click();
        return this;
    }

    @Step("Click to submit")
    public RegistrationPage submit() {
        submitBtn.click();
        return this;
    }

    public RegistrationPage checkUnsuccessfulValidation() {
        userForm.shouldHave(attribute("class", "was-validated"));
        return this;
    }
}
