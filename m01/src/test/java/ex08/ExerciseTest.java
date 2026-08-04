package ex08;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Тесты к уроку 1.8 — разминка перед сборкой v0.1.
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 1.8)")
class ExerciseTest {

    @Test
    void canPlayComparesCostAndEnergy() {
        assertTrue(Exercise.canPlay(2, 3), "Карта за 2 при 3 энергии играется");
        assertTrue(Exercise.canPlay(3, 3), "Впритык — тоже играется");
        assertFalse(Exercise.canPlay(3, 2), "Карта дороже, чем есть энергии, — не играется");
    }

    @Test
    void enemyIntentDefendsEveryThirdTurn() {
        assertEquals("ATTACK", Exercise.enemyIntent(1), "Ход 1 — атака");
        assertEquals("ATTACK", Exercise.enemyIntent(2), "Ход 2 — атака");
        assertEquals("DEFEND", Exercise.enemyIntent(3), "Ход 3 — защита");
        assertEquals("ATTACK", Exercise.enemyIntent(4), "Ход 4 — снова атака");
        assertEquals("DEFEND", Exercise.enemyIntent(6), "Ход 6 — снова защита");
    }

    @Test
    void winnerLooksAtBothHp() {
        assertEquals("NOBODY", Exercise.winner(10, 10), "Оба живы — бой продолжается");
        assertEquals("HERO", Exercise.winner(10, 0), "Враг пал — победа героя");
        assertEquals("ENEMY", Exercise.winner(0, 10), "Герой пал — победа врага");
        assertEquals("ENEMY", Exercise.winner(0, 0), "Пали оба — жестокий мир засчитывает победу врагу");
    }
}
