package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Смоук-тест (smoke test) — быстрая проверка, что проект «дышит».
 * Версия для v0.2: проверяет правила классов Hero, Card и Enemy,
 * а не конкретные числа из блока «МЕНЯЙ МЕНЯ» — меняй их смело,
 * зелёная галочка не пропадёт.
 */
class SmokeTest {

    @Test
    void heroKeepsHpInBounds() {
        Hero hero = new Hero("Тест", 30);
        hero.takeDamage(999);
        assertEquals(0, hero.getHp(), "HP не должно уходить в минус");
        assertFalse(hero.isAlive(), "Герой с 0 HP считается павшим");

        Hero healthy = new Hero("Тест", 30);
        healthy.heal(999);
        assertEquals(30, healthy.getHp(), "Лечение не поднимает HP выше максимума");
    }

    @Test
    void heroBlockAbsorbsDamage() {
        Hero hero = new Hero("Тест", 30);
        hero.addBlock(5);
        hero.takeDamage(7);
        assertEquals(28, hero.getHp(), "5 блока гасят 5 из 7 урона: до HP должно дойти 2");
        assertEquals(0, hero.getBlock(), "Блок расходуется ударом");
    }

    @Test
    void cardDescribesItsEffects() {
        Card strike = new Card("Тестовый удар", 1, 6, 0, 0);
        assertTrue(strike.describe().contains("урон"), "Карта с уроном должна упоминать урон");
        Card potion = new Card("Тестовое зелье", 2, 0, 0, 7);
        assertTrue(potion.describe().contains("лечение"), "Карта с лечением должна упоминать лечение");
    }

    @Test
    void everyEnemyKindIsReadyForBattle() {
        Enemy[] enemies = { new Slime(), new Goblin(), new Archer(), new Boss() };
        for (Enemy enemy : enemies) {
            assertTrue(enemy.isAlive(), "Враг должен выходить в бой живым: " + enemy.getName());
            assertFalse(enemy.getName().isBlank(), "У врага должно быть имя");
            assertFalse(enemy.chooseIntent().isBlank(),
                    "Враг обязан объявлять намерение: " + enemy.getName());
        }
    }

    @Test
    void enemyBlockAbsorbsDamage() {
        Enemy goblin = new Goblin();
        int startHp = goblin.getHp();
        goblin.addBlock(4);
        goblin.takeDamage(6);
        assertEquals(startHp - 2, goblin.getHp(), "4 блока гасят 4 из 6 урона: до HP должно дойти 2");
    }

    @Test
    void bossEnragesBelowHalfHp() {
        Boss boss = new Boss();
        assertFalse(boss.isEnraged(), "На старте босс спокоен");
        // Снимаем боссу здоровье до значения чуть ниже половины.
        boss.takeDamage(boss.getMaxHp() - boss.getMaxHp() / 2 + 1);
        assertTrue(boss.isEnraged(), "Ниже половины HP босс должен впасть в ярость");
    }
}