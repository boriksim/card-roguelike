package ex02;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2a.2 «Конструкторы и this».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
//@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.2)")
class WeaponTest {

    @Test
    void constructorFillsAllFields() {
        Weapon axe = new Weapon("Топор", 9, 3);
        assertEquals("Топор", axe.name, "Конструктор должен сохранить имя (подсказка: this.name = name)");
        assertEquals(9, axe.damage, "Конструктор должен сохранить урон");
        assertEquals(3, axe.durability, "Конструктор должен сохранить прочность");
    }

    @Test
    void twoWeaponsDoNotShareFields() {
        Weapon axe = new Weapon("Топор", 9, 3);
        Weapon sword = new Weapon("Меч", 7, 5);
        assertEquals("Топор", axe.name, "Создание второго оружия не должно менять первое");
        assertEquals(7, sword.damage, "У каждого объекта — свои поля");
    }

    @Test
    void shortConstructorUsesStandardDurability() {
        Weapon sword = new Weapon("Меч", 7);
        assertEquals("Меч", sword.name, "Короткий конструктор тоже должен сохранить имя");
        assertEquals(7, sword.damage, "Короткий конструктор тоже должен сохранить урон");
        assertEquals(10, sword.durability,
                "Короткий конструктор должен подставить прочность 10 — вызовом this(name, damage, 10)");
    }

    @Test
    void strikeWearsWeaponOut() {
        Weapon dagger = new Weapon("Кинжал", 5, 2);
        assertEquals(5, dagger.strike(), "Целое оружие бьёт на весь свой урон");
        assertEquals(1, dagger.durability, "После удара прочность падает на 1");
        assertEquals(5, dagger.strike(), "Прочность 1 — оружие ещё бьёт в полную силу");
        assertEquals(0, dagger.durability, "Второй удар добил прочность до 0");
        assertEquals(0, dagger.strike(), "Сломанное оружие (прочность 0) бьёт на 0");
        assertEquals(0, dagger.durability, "Прочность не должна уходить в минус");
    }
}
