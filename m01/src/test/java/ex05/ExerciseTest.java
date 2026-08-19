package ex05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 1.5 «Циклы».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
//@Disabled("Убери эту строку, когда начнёшь упражнение (урок 1.5)")
class ExerciseTest {

    @Test
    void totalDamageAddsUp() {
        assertEquals(18, Exercise.totalDamage(3, 6),
                "3 удара по 6 урона = 18");
        assertEquals(0, Exercise.totalDamage(0, 6),
                "0 ударов = 0 урона: цикл не должен выполниться ни разу");
    }

    @Test
    void hitsToDefeatCountsHits() {
        assertEquals(3, Exercise.hitsToDefeat(10, 4),
                "10 HP при уроне 4: враг падает на 3-м ударе (4+4+4 = 12)");
        assertEquals(3, Exercise.hitsToDefeat(12, 4),
                "12 HP при уроне 4: ровно 3 удара, впритык");
        assertEquals(1, Exercise.hitsToDefeat(1, 4),
                "1 HP сносится одним ударом");
    }

    @Test
    void lastTurnOfHeroSimulatesBattle() {
        assertEquals(3, Exercise.lastTurnOfHero(10, 4, 0),
                "10 HP, урон 4, без лечения: 10 -> 6 -> 2 -> -2, герой пал на 3-м ходу");
        assertEquals(4, Exercise.lastTurnOfHero(10, 4, 2),
                "10 HP, урон 4, лечение 2: герой тянет до 4-го хода");
        assertEquals(-1, Exercise.lastTurnOfHero(10, 4, 4),
                "Лечение полностью покрывает урон: герой бессмертен, жди -1 (а не бесконечный цикл!)");
    }
}
