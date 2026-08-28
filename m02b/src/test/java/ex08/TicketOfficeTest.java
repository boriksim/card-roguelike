package ex08;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * Тесты к уроку 2b.8 — мини-задача «билетная касса».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.8)")
class TicketOfficeTest {

    @Test
    void categoryDependsOnAge() {
        assertEquals(Category.CHILD, TicketOffice.categoryFor(0), "Новорождённый — ребёнок");
        assertEquals(Category.CHILD, TicketOffice.categoryFor(11), "11 лет — ещё ребёнок");
        assertEquals(Category.ADULT, TicketOffice.categoryFor(12), "12 лет — уже взрослый билет");
        assertEquals(Category.ADULT, TicketOffice.categoryFor(64), "64 — ещё взрослый");
        assertEquals(Category.SENIOR, TicketOffice.categoryFor(65), "65 — уже льготный");
    }

    @Test
    void negativeAgeIsRejected() {
        // assertThrows: проверяем, что код бросает исключение нужного типа
        // (подробнее — в ex06).
        assertThrows(IllegalArgumentException.class, () -> TicketOffice.categoryFor(-1),
                "Отрицательный возраст — ошибка вызывающего кода, а не категория");
    }

    @Test
    void priceDependsOnCategory() {
        assertEquals(50, TicketOffice.priceFor(Category.CHILD, 100), "Детям — половина");
        assertEquals(75, TicketOffice.priceFor(Category.SENIOR, 100), "Льготным — три четверти");
        assertEquals(100, TicketOffice.priceFor(Category.ADULT, 100), "Взрослым — полная цена");
    }

    @Test
    void sellBuildsTheWholeTicket() {
        Ticket ticket = TicketOffice.sell("Концерт", "30", 100);
        // Сравниваем ЦЕЛЫЕ record-ы: equals по значениям пишет компилятор.
        assertEquals(new Ticket("Концерт", Category.ADULT, 100), ticket,
                "30 лет — взрослый билет за полную цену");
        assertEquals(new Ticket("Концерт", Category.CHILD, 50),
                TicketOffice.sell("Концерт", "7", 100),
                "7 лет — детский билет за полцены");
    }

    @Test
    void sellRejectsGarbageAge() {
        assertThrows(IllegalArgumentException.class,
                () -> TicketOffice.sell("Концерт", "тридцать", 100),
                "Возраст буквами — понятная ошибка, а не падение с NumberFormatException");
        assertThrows(IllegalArgumentException.class,
                () -> TicketOffice.sell("Концерт", "", 100),
                "Пустая строка — тоже не возраст");
    }

    @Test
    void ticketDescribesItselfThroughTheInterface() {
        // Переменная типа ИНТЕРФЕЙСА, объект — record Ticket.
        Describable ticket = new Ticket("Концерт", Category.ADULT, 100);
        assertEquals("Билет на «Концерт» (ADULT): 100", ticket.describe(),
                "Формат описания — в комментарии к Ticket.describe");
    }
}
