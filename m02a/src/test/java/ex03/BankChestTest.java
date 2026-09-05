package ex03;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2a.3 «Инкапсуляция».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
//@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.3)")
class BankChestTest {

    @Test
    void newChestIsEmpty() {
        BankChest chest = new BankChest();
        assertEquals(0, chest.getGold(), "Новый сундук пуст: 0 золота");
    }

    @Test
    void depositAddsGold() {
        BankChest chest = new BankChest();
        chest.deposit(100);
        chest.deposit(50);
        assertEquals(150, chest.getGold(), "Два вклада должны сложиться: 100 + 50");
    }

    @Test
    void depositIgnoresNonPositiveAmounts() {
        BankChest chest = new BankChest();
        chest.deposit(100);
        chest.deposit(-30);
        chest.deposit(0);
        assertEquals(100, chest.getGold(), "Отрицательный и нулевой вклады молча игнорируются");
    }

    @Test
    void withdrawGivesOnlyWhatChestHas() {
        BankChest chest = new BankChest();
        chest.deposit(100);

        assertEquals(40, chest.withdraw(40), "Золота хватает — отдай ровно сколько попросили");
        assertEquals(60, chest.getGold(), "После снятия 40 из 100 остаётся 60");

        assertEquals(60, chest.withdraw(999), "Просят больше, чем есть — отдай всё, что было");
        assertEquals(0, chest.getGold(), "Сундук пуст, но НЕ в минусе — это и есть инвариант");
    }

    @Test
    void withdrawIgnoresNonPositiveAmounts() {
        BankChest chest = new BankChest();
        chest.deposit(100);
        assertEquals(0, chest.withdraw(-5), "Отрицательное снятие не отдаёт ничего");
        assertEquals(0, chest.withdraw(0), "Нулевое снятие не отдаёт ничего");
        assertEquals(100, chest.getGold(), "Золото при этом не должно измениться");
    }
}
