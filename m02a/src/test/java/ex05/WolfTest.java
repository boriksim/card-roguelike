package ex05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Тесты к уроку 2a.5 «Наследование».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
//@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.5)")
class WolfTest {

    @Test
    void constructorPassesDataToParent() {
        Wolf wolf = new Wolf("Акела", 30);
        assertEquals("Акела", wolf.getName(),
                "Имя должно дойти до полей родителя — вызови super(name, hp)");
        assertEquals(30, wolf.getHp(),
                "И hp тоже: без super(...) волк получает запасные значения из Animal()");
    }

    @Test
    void wolfInheritsAnimalAbilities() {
        Wolf wolf = new Wolf("Акела", 30);
        wolf.takeDamage(10); // метод написан в Animal, но у волка он тоже есть
        assertEquals(20, wolf.getHp(), "takeDamage унаследован от Animal и должен работать у волка");
        assertTrue(wolf.isAlive(), "isAlive тоже достался по наследству");
    }

    @Test
    void huntBitesPreyAndFeedsWolf() {
        Wolf wolf = new Wolf("Акела", 20);
        Animal hare = new Animal("Заяц", 10);

        wolf.hunt(hare);

        assertEquals(10 - Wolf.BITE_DAMAGE, hare.getHp(),
                "Добыча должна получить укус на BITE_DAMAGE");
        assertEquals(22, wolf.getHp(), "После охоты волк отъедается на 2 HP: 20 + 2");
    }
}
