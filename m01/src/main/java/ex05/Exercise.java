package ex05;

/*
 * Урок 1.5 — «Циклы».
 *
 * Проверка — JUnit-тесты: m01/src/test/java/ex05/ExerciseTest.java.
 * Порядок работы:
 *   1) открой тест и убери строку @Disabled;
 *   2) запусти тест (▶ слева от имени класса) — он красный, так и задумано;
 *   3) реализуй методы ниже, пока все тесты не позеленеют.
 */
public class Exercise {

    // ШАГ 1. Суммарный урон за несколько ударов.
    // Сложи damagePerHit ровно hits раз циклом for.
    // Умножением не считается — цель упражнения размять цикл.
    public static int totalDamage(int hits, int damagePerHit) {
        int totalDamage = 0;
        for (int i = 0; i < hits; i++) {
            totalDamage += damagePerHit;
        }
        return totalDamage;
    }

    // ШАГ 2. Сколько ударов нужно, чтобы добить врага.
    // Бьём по damagePerHit за удар, пока HP врага не упадёт до 0 или ниже.
    // Подойдёт цикл while со счётчиком ударов.
    public static int hitsToDefeat(int enemyHp, int damagePerHit) {
        int hits = 0;
        while (enemyHp > 0) {
            enemyHp -= damagePerHit;
            hits++;
        }
        return hits;
    }

    // ШАГ 3 (со звёздочкой). На каком ходу падёт герой.
    // Каждый ход герой СНАЧАЛА получает enemyDamage урона.
    // Если после удара hp <= 0 — герой пал: верни номер этого хода.
    // Если выжил — лечится на healPerTurn, и наступает следующий ход.
    // Внимание: если healPerTurn >= enemyDamage, герой не падёт никогда —
    // верни -1 сразу, иначе твой цикл станет бесконечным (тот самый баг из урока!).
    public static int lastTurnOfHero(int heroHp, int enemyDamage, int healPerTurn) {
        int turn = 1;
        while (heroHp > 0) {
            if (healPerTurn >= enemyDamage) {
                return -1;
            }
            heroHp -= enemyDamage;
            if (heroHp <= 0) {
                break;
            } else {
                heroHp += healPerTurn;
            }
            turn++;
        }
        return turn;
    }
}
