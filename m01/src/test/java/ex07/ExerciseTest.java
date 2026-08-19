package ex07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Урок 1.7. Эти тесты — готовая спецификация: НЕ меняй их.
 * Твоя задача — написать в Exercise.java методы, которым тесты поверят.
 * Убери строку @Disabled — и вперёд.
 */
//@Disabled("Убери эту строку, когда начнёшь упражнение (урок 1.7)")
class ExerciseTest {

    @Test
    void damageAfterBlockSubtractsBlock() {
        assertEquals(4, Exercise.damageAfterBlock(6, 2),
                "6 урона через 2 блока: до цели должно дойти 4");
    }

    @Test
    void damageAfterBlockNeverGoesNegative() {
        assertEquals(0, Exercise.damageAfterBlock(3, 10),
                "Блок больше урона: проходит 0, а не отрицательное число");
    }

    @Test
    void healedHpAddsHealing() {
        assertEquals(20, Exercise.healedHp(13, 7, 30),
                "13 HP + 7 лечения = 20");
    }

    @Test
    void healedHpRespectsMaximum() {
        assertEquals(30, Exercise.healedHp(28, 7, 30),
                "28 + 7 упирается в потолок 30");
        assertEquals(30, Exercise.healedHp(30, 5, 30),
                "Полного героя лечить некуда — остаётся 30");
    }

    @Test
    void hpBarShowsTenCells() {
        assertEquals("##########", Exercise.hpBar(30, 30),
                "Полное здоровье — все 10 клеток '#'");
        assertEquals("----------", Exercise.hpBar(0, 30),
                "Ноль HP — все 10 клеток '-'");
        assertEquals("#####-----", Exercise.hpBar(15, 30),
                "Половина HP — закрашено 5 клеток из 10");
        assertEquals("#########-", Exercise.hpBar(29, 30),
                "Лёгкая царапина уже видна: 29 * 10 / 30 = 9 клеток (целочисленное деление)");
    }
}
