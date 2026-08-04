package ex06;

/*
 * Урок 1.6 — «Массивы».
 *
 * Рука карт в игре хранится в параллельных массивах — здесь ты
 * научишься по ним ходить.
 *
 * Проверка — JUnit-тесты: m01/src/test/java/ex06/ExerciseTest.java.
 * Убери там @Disabled, запусти, доведи до зелёного.
 */
public class Exercise {

    // ШАГ 1. Суммарная стоимость всех карт в руке.
    // Пройди по массиву циклом (for по индексам или for-each).
    public static int totalCost(int[] cardCosts) {
        // TODO: замени заглушку
        return 0;
    }

    // ШАГ 2. Индекс самой сильной карты (наибольший урон).
    // Если максимум встречается несколько раз — верни ПЕРВЫЙ из них.
    // Массив гарантированно не пустой.
    // Помни: индексы считаются с нуля!
    public static int strongestCardIndex(int[] cardDamage) {
        // TODO: замени заглушку
        return 0;
    }

    // ШАГ 3 (со звёздочкой). Сколько карт из руки хватит энергии сыграть.
    // Каждая карта рассматривается отдельно: cardCosts[i] <= energy.
    public static int countPlayable(int[] cardCosts, int energy) {
        // TODO: замени заглушку
        return 0;
    }
}
