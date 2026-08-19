package ex08;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/*
 * Тесты к уроку 2a.8 — мини-задача на все концепты модуля.
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.8)")
class FleetTest {

    @Test
    void taxiCountsItsTripPrice() {
        Taxi taxi = new Taxi();
        assertEquals("Такси", taxi.getName(), "Имя такси хранится в полях родителя");
        assertEquals(2, taxi.getCapacity(), "Такси возит 2 посылки");
        assertEquals(200, taxi.tripPrice(10), "Такси на 10 км: 50 + 15 * 10 = 200");
        assertEquals(50, taxi.tripPrice(0), "Рейс в ноль километров — только подача: 50");
    }

    @Test
    void busCountsItsTripPrice() {
        Bus bus = new Bus();
        assertEquals("Автобус", bus.getName(), "Передай родителю имя «Автобус» через super(...)");
        assertEquals(20, bus.getCapacity(), "Передай родителю вместимость 20 через super(...)");
        assertEquals(250, bus.tripPrice(10), "Автобус на 10 км: 200 + 5 * 10 = 250");
    }

    @Test
    void pricePerParcelIsSharedLogic() {
        assertEquals(100, new Taxi().pricePerParcel(10),
                "Такси на 10 км: рейс 200 на 2 посылки = 100 за посылку");
        assertEquals(12, new Bus().pricePerParcel(10),
                "Автобус на 10 км: рейс 250 на 20 посылок = 12 за посылку (целочисленно)");
    }

    @Test
    void cheapestPicksByPolymorphicPrice() {
        Vehicle bus = new Bus();
        Vehicle taxi = new Taxi();
        Vehicle[] fleet = { bus, taxi };

        assertSame(taxi, Fleet.cheapest(fleet, 10),
                "На 10 км такси дешевле: 200 против 250 у автобуса");
        assertSame(bus, Fleet.cheapest(fleet, 40),
                "На 40 км дешевле автобус: 400 против 650 у такси");
    }

    @Test
    void cheapestPrefersEarlierOnTie() {
        Vehicle bus = new Bus();
        Vehicle taxi = new Taxi();
        // На 15 км цены равны: 50 + 15*15 = 275 и 200 + 5*15 = 275.
        assertSame(bus, Fleet.cheapest(new Vehicle[] { bus, taxi }, 15),
                "При равной цене побеждает тот, кто стоит в массиве раньше");
        assertSame(taxi, Fleet.cheapest(new Vehicle[] { taxi, bus }, 15),
                "При равной цене побеждает тот, кто стоит в массиве раньше");
    }
}
