package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class Task2Test {
    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldTest() {
        open("http://localhost:9999");
        //SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("Ка");
        $$(".menu-item__control").find(exactText("Казань")).click();
        String planningDate = generateDate(7, "dd.MM.yyyy");
        //$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").click();
        if (!generateDate(3, "MM").equals(generateDate(7, "MM"))){
            $$(".calendar__arrow_direction_right").last().click();
        }
        $$("[data-day]").findBy(Condition.text(generateDate(7, "d"))).click();
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }


}
