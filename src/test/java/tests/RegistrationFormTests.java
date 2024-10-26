package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.RegistrationPage;
import utils.Hobby;
import utils.RandomDataUtil;

import java.util.List;
import java.util.stream.Stream;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomDataUtil random = new RandomDataUtil();

    String name = random.getRandomFirstName(),
            lastName = random.getRandomLastName(),
            email = random.getRandomEmail(),
            mobile = random.getRandomMobile(),
            subject = random.getRandomSubject(),
            address = random.getRandomAddress(),
            state = random.getRandomState(),
            city = random.getRandomCity(state),
            gender = random.getRandomGender(),
            hobby = random.getRandomHobby(),
            day = random.getRandomDay(),
            month = random.getRandomMonth(),
            year = random.getRandomYear(),
            picture = random.getRandomPicture();

    @DisplayName("Успешная регистрация пользователя с почтой с доменом из разных стран")
    @ValueSource(strings = {
            "rus@test.ru", "uk@test.uk", "germany@test.de"
    })
    @ParameterizedTest(name = "Успешная регистрация с почтой {0}")
    void successRegistrationWithEmailFromCertainCountryDomain(String email) {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadImage(picture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();
        //asserts
        registrationPage
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @CsvFileSource(resources = "/data/test_data.csv")
    @DisplayName("Регистрация пользователя с различными именами и фамилиями")
    @ParameterizedTest(name = "Регистрация пользователя с именем {0} и фамилией {1}")
    void successRegistrationWithDifferentNamesAndLastNames(String name, String lastName) {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadImage(picture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();
        //asserts
        registrationPage
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @EnumSource(Hobby.class)
    @ParameterizedTest(name = "Успешная регистрация с хобби: {0}")
    void successRegistrationWithDifferentHobbies(Hobby hobby) {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby.getHobby())
                .uploadImage(picture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();
        //asserts
        registrationPage
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby.getHobby())
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    static Stream<Arguments> successRegistrationForDifferentHobbiesAndSubjects() {
        return Stream.of(
                Arguments.of(
                        Hobby.SPORTS,
                        List.of("Computer Science", "Commerce")
                ),
                Arguments.of(
                        Hobby.MUSIC,
                        List.of("Arts", "Chemistry")
                ),
                Arguments.of(
                        Hobby.READING,
                        List.of("Maths", "History")
                )
        );
    }
    @MethodSource("successRegistrationForDifferentHobbiesAndSubjects")
    @ParameterizedTest(name = "Успешная регистрация с хобби {0} и subject {1}")
    void successRegistrationForDifferentHobbiesAndSubjects(Hobby hobby, List<String> subjects) {
        //act
        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth(day, month, year)
                .setSubjects(subjects)
                .setHobby(hobby.getHobby())
                .uploadImage(picture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();
        //asserts
        registrationPage
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResults("Subjects", subjects)
                .checkResult("Hobbies", hobby.getHobby())
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }
}
