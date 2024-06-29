package ru.netology.web;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardWithDeliveryTest {

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldTest() {
        open("http://localhost:9999");
        //SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("Казань");
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}

   



