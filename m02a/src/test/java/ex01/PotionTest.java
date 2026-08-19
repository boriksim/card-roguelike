package ex01;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Тесты к уроку 2a.1 «Классы и объекты».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.1)")
class PotionTest {

    @Test
    void describeUsesOwnFields() {
        Potion small = new Potion();
        small.name = "Малое зелье";
        small.volume = 100;
        small.healPower = 7;
        assertEquals("Малое зелье (100 мл): +7 HP", small.describe(),
                "Собери строку из полей объекта: имя, объём, сила");

        Potion big = new Potion();
        big.name = "Большое зелье";
        big.volume = 250;
        big.healPower = 20;
        assertEquals("Большое зелье (250 мл): +20 HP", big.describe(),
                "У каждого объекта — свои значения полей: второй объект описывает себя, а не первого");
    }

    @Test
    void strongerThanComparesHealPower() {
        Potion weak = new Potion();
        weak.healPower = 7;
        Potion strong = new Potion();
        strong.healPower = 20;

        assertTrue(strong.strongerThan(weak), "Зелье на 20 HP сильнее зелья на 7 HP");
        assertFalse(weak.strongerThan(strong), "Зелье на 7 HP НЕ сильнее зелья на 20 HP");
        Potion sameAsWeak = new Potion();
        sameAsWeak.healPower = 7;
        assertFalse(weak.strongerThan(sameAsWeak), "При равной силе зелье не считается сильнее");
    }

    @Test
    void mixWithCreatesNewPotion() {
        Potion first = new Potion();
        first.name = "Малое зелье";
        first.volume = 100;
        first.healPower = 7;
        Potion second = new Potion();
        second.name = "Большое зелье";
        second.volume = 250;
        second.healPower = 20;

        Potion mix = first.mixWith(second);
        assertNotNull(mix, "mixWith должен вернуть новый объект, а не null");
        assertEquals("Микстура", mix.name, "Имя смеси — всегда «Микстура»");
        assertEquals(350, mix.volume, "Объём микстуры — сумма объёмов: 100 + 250");
        assertEquals(27, mix.healPower, "Сила микстуры — сумма сил: 7 + 20");

        assertEquals(100, first.volume, "Исходные зелья должны остаться нетронутыми");
        assertEquals(20, second.healPower, "Исходные зелья должны остаться нетронутыми");
    }
}
