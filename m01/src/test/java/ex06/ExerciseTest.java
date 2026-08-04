package ex06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 1.6 «Массивы».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 1.6)")
class ExerciseTest {

    @Test
    void totalCostSumsWholeHand() {
        assertEquals(4, Exercise.totalCost(new int[] {1, 1, 2}),
                "Рука из карт за 1, 1 и 2 стоит 4 энергии");
        assertEquals(0, Exercise.totalCost(new int[] {}),
                "Пустая рука стоит 0");
    }

    @Test
    void strongestCardIndexFindsMax() {
        assertEquals(1, Exercise.strongestCardIndex(new int[] {3, 9, 5}),
                "Самая сильная карта — вторая, а её индекс 1 (индексы с нуля!)");
        assertEquals(0, Exercise.strongestCardIndex(new int[] {7}),
                "Единственная карта и есть самая сильная");
        assertEquals(1, Exercise.strongestCardIndex(new int[] {4, 9, 9}),
                "При равном уроне побеждает первая из равных");
    }

    @Test
    void countPlayableRespectsEnergy() {
        assertEquals(2, Exercise.countPlayable(new int[] {1, 2, 3}, 2),
                "С 2 энергии играются карты за 1 и за 2, но не за 3");
        assertEquals(0, Exercise.countPlayable(new int[] {3, 3}, 1),
                "Энергии всего 1 — дорогие карты не сыграть");
        assertEquals(3, Exercise.countPlayable(new int[] {0, 1, 1}, 5),
                "Энергии с запасом — играется вся рука");
    }
}
