package ex04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2a.4 «static: у класса или у объекта?».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.4)")
class MinionTest {

    @BeforeEach
    void resetSharedCounter() {
        // static-состояние живёт между тестами — сбрасываем его перед каждым.
        Minion.resetCounter();
    }

    @Test
    void counterBelongsToTheClass() {
        assertEquals(0, Minion.getTotalCreated(),
                "До создания объектов счётчик равен 0 — и спрашиваем мы его у КЛАССА, без объекта");
        new Minion("Боб");
        new Minion("Кевин");
        new Minion("Стюарт");
        assertEquals(3, Minion.getTotalCreated(),
                "Создано 3 миньона — static-счётчик один на всех и должен насчитать 3");
    }

    @Test
    void eachMinionKeepsOwnNameAndId() {
        Minion bob = new Minion("Боб");
        Minion kevin = new Minion("Кевин");

        assertEquals("Боб", bob.getName(), "Имя — обычное поле: у каждого объекта своё");
        assertEquals("Кевин", kevin.getName(), "Имя — обычное поле: у каждого объекта своё");
        assertEquals(1, bob.getId(), "Первый созданный миньон получает id 1");
        assertEquals(2, kevin.getId(), "Второй созданный миньон получает id 2");

        new Minion("Мел");
        assertEquals(1, bob.getId(),
                "id Боба не должен меняться от создания новых миньонов: поле id не static");
    }
}
