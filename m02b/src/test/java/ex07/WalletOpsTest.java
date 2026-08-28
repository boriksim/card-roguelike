package ex07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/*
 * Тесты к уроку 2b.7 «Что хранит переменная-объект».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.7)")
class WalletOpsTest {

    @Test
    void depositChangesTheCallersWallet() {
        Wallet wallet = new Wallet();
        wallet.coins = 10;
        WalletOps.deposit(wallet, 5);
        assertEquals(15, wallet.coins,
                "Метод получил копию ссылки, но объект тот же — изменение видно снаружи");
    }

    @Test
    void richerOfReturnsTheSameObjectNotACopy() {
        Wallet poor = new Wallet();
        poor.coins = 3;
        Wallet rich = new Wallet();
        rich.coins = 100;

        // assertSame сравнивает ССЫЛКИ: зелёный, только если это ТОТ ЖЕ
        // объект, а не равный по содержимому двойник.
        assertSame(rich, WalletOps.richerOf(poor, rich),
                "Верни ссылку на существующий кошелёк, а не новый объект");
        assertSame(rich, WalletOps.richerOf(rich, poor),
                "Порядок аргументов не должен влиять на ответ");

        Wallet sameAmount = new Wallet();
        sameAmount.coins = 3;
        assertSame(poor, WalletOps.richerOf(poor, sameAmount),
                "При равенстве возвращается first");
    }

    @Test
    void tryToEmptyCannotTouchTheCallersWallet() {
        // Этот тест зелёный СРАЗУ, и это не ошибка. Прочитай tryToEmpty
        // и комментарий над ним: метод переприсвоил свою копию ссылки,
        // а объект вызывающего кода остался нетронутым.
        Wallet wallet = new Wallet();
        wallet.coins = 50;
        WalletOps.tryToEmpty(wallet);
        assertEquals(50, wallet.coins,
                "Переприсваивание ПАРАМЕТРА не меняет объект снаружи");
    }
}
