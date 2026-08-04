package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Смоук-тест (smoke test) — быстрая проверка, что проект «дышит».
 * Ты запускаешь его в уроке 0.2 и должен увидеть зелёную галочку.
 *
 * ⚙ Что такое @Test и как устроены тесты — разберём в модуле 5.
 * Тест нарочно не проверяет конкретные числа из блока «МЕНЯЙ МЕНЯ»:
 * меняй их смело, зелёная галочка не пропадёт.
 */
class SmokeTest {

    @Test
    void gameConstantsLookSane() {
        assertFalse(Main.GAME_TITLE.isBlank(), "GAME_TITLE не должен быть пустой строкой");
        assertFalse(Main.HERO_NAME.isBlank(), "У героя должно быть имя");
        assertTrue(Main.HERO_HP > 0, "HERO_HP должен быть больше нуля — иначе герой мёртв ещё до боя");
        assertTrue(Main.ENEMY_HP > 0, "ENEMY_HP должен быть больше нуля");
        assertTrue(Main.ENERGY_PER_TURN > 0, "Без энергии не сыграть ни одной карты");
    }

    @Test
    void everyCardHasAllStats() {
        int cards = Main.CARD_NAMES.length;
        assertTrue(cards > 0, "В руке должна быть хотя бы одна карта");
        assertEquals(cards, Main.CARD_COST.length,
                "Массивы карт «параллельные»: у каждой карты должна быть стоимость");
        assertEquals(cards, Main.CARD_DAMAGE.length,
                "У каждой карты должно быть значение урона (хотя бы 0)");
        assertEquals(cards, Main.CARD_BLOCK.length,
                "У каждой карты должно быть значение блока (хотя бы 0)");
        assertEquals(cards, Main.CARD_HEAL.length,
                "У каждой карты должно быть значение лечения (хотя бы 0)");
    }

    @Test
    void damageAfterBlockNeverNegative() {
        assertEquals(4, Main.damageAfterBlock(6, 2), "6 урона через 2 блока — должно пройти 4");
        assertEquals(0, Main.damageAfterBlock(3, 10), "Блок не может «отлечить»: минимум 0 урона");
    }

    @Test
    void healingRespectsMaximum() {
        assertEquals(20, Main.healedHp(13, 7, 30), "13 HP + 7 лечения = 20");
        assertEquals(30, Main.healedHp(28, 7, 30), "Лечение не поднимает HP выше максимума");
    }
}
