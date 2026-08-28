package ex02;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2b.2 «Enum».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.2)")
class SeasonsTest {

    @Test
    void everySeasonHasARussianName() {
        assertEquals("зима", Seasons.russianName(Season.WINTER), "WINTER — зима");
        assertEquals("весна", Seasons.russianName(Season.SPRING), "SPRING — весна");
        assertEquals("лето", Seasons.russianName(Season.SUMMER), "SUMMER — лето");
        assertEquals("осень", Seasons.russianName(Season.AUTUMN), "AUTUMN — осень");
    }

    @Test
    void nextSeasonFollowsTheCalendar() {
        assertEquals(Season.SPRING, Seasons.next(Season.WINTER), "После зимы — весна");
        assertEquals(Season.SUMMER, Seasons.next(Season.SPRING), "После весны — лето");
        assertEquals(Season.AUTUMN, Seasons.next(Season.SUMMER), "После лета — осень");
        assertEquals(Season.WINTER, Seasons.next(Season.AUTUMN), "После осени год замыкается: снова зима");
    }

    @Test
    void fourStepsMakeAFullYear() {
        // values() — встроенный метод любого enum: массив всех значений
        // в порядке объявления. Год из четырёх шагов возвращает к старту.
        Season season = Season.WINTER;
        for (int i = 0; i < Season.values().length; i++) {
            season = Seasons.next(season);
        }
        assertEquals(Season.WINTER, season, "Через " + Season.values().length
                + " шага next должен вернуть исходный сезон");
    }
}
