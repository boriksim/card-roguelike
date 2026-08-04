package ex07;

/*
 * Урок 1.7 — «Методы».
 *
 * Формат необычный: тесты уже написаны ЗА тебя и описывают, как методы
 * должны себя вести. Так часто работают в настоящих командах: сначала
 * читаешь спецификацию (тест), потом пишешь код под неё.
 *
 * Порядок работы:
 *   1) открой m01/src/test/java/ex07/ExerciseTest.java и ПРОЧИТАЙ его;
 *   2) убери там @Disabled и запусти — всё красное;
 *   3) реализуй методы ниже, пока каждый тест не позеленеет.
 */
public class Exercise {

    // ШАГ 1. Урон сквозь блок.
    // Чего ждёт тест — смотри damageAfterBlock* в ExerciseTest.
    public static int damageAfterBlock(int damage, int block) {
        // TODO: замени заглушку
        return 0;
    }

    // ШАГ 2. Лечение с потолком.
    // Спецификация — тесты healedHp* в ExerciseTest.
    public static int healedHp(int currentHp, int healAmount, int maxHp) {
        // TODO: замени заглушку
        return 0;
    }

    // ШАГ 3 (со звёздочкой). Полоска здоровья из 10 клеток:
    // '#' — оставшееся HP, '-' — потерянное.
    // Сколько клеток закрашивать — подскажут тесты (целочисленное деление!).
    public static String hpBar(int hp, int maxHp) {
        // TODO: замени заглушку
        return "";
    }
}
