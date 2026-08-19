package ex04;

import javax.annotation.processing.SupportedSourceVersion;

/*
 * Урок 1.4 — «Условия».
 *
 * Задание: реши исход вражеского удара с помощью if/else,
 * лестницы else if и стрелочного switch.
 *
 * Проверка: запусти (▶ слева от main) и сверь вывод с expected-output.txt.
 */
public class Exercise {

    public static void main(String[] args) {
        // Исходная боевая ситуация — не меняй, иначе вывод не сойдётся.
        int heroHp = 12;
        int incomingDamage = 15;
        boolean hasBlock = true;
        String roomType = "shop";

        // ШАГ 1. Блок: если hasBlock == true, урон уменьшается на 5
        // (но не может стать меньше 0). Посчитай итоговый урон и выведи:
        // Урон после блока: 10
        // Затем if/else: если урон >= heroHp — выведи «Смертельный удар!»,
        // иначе — «Пережил.»
        if (hasBlock) {
            incomingDamage -= 5;
            if (incomingDamage < 0) {
                incomingDamage = 0;
            }
        }
        System.out.println("Урон после блока: " + incomingDamage);

        if (incomingDamage >= heroHp) {
            System.out.println("Смертельный удар!");
        } else  {
            System.out.println("Пережил.");
        }

        // ШАГ 2. Вычти итоговый урон из heroHp и выведи:
        // HP после удара: 2
        // Дальше лестница else if по оставшемуся heroHp:
        //   больше 20 — выведи «Полон сил.»
        //   больше 5  — выведи «Ранен.»
        //   иначе     — выведи «При смерти!»
        heroHp -= incomingDamage;
        System.out.println("HP после удара: " + heroHp);
        if (heroHp > 20) {
            System.out.println("Полон сил.");
        } else if (heroHp > 5) {
            System.out.println("Ранен.");
        } else {
            System.out.println("При смерти!");
        }

        // ШАГ 3. Стрелочный switch (Java 21) по roomType:
        //   "battle" -> Бой: доставай карты.
        //   "rest"   -> Отдых: можно подлечиться.
        //   "shop"   -> Магазин: купи карту.
        //   default  -> Неизвестная комната...
        // Подсказка:
        // switch (roomType) {
        //     case "battle" -> System.out.println("...");
        //     ...
        // }
        switch (roomType) {
            case "battle" -> System.out.println("Бой: доставай карты.");
            case "rest" -> System.out.println("Отдых: можно подечиться.");
            case "shop" -> System.out.println("Магазин: купи карту.");
            default -> System.out.println("Неизвестная комната...");
        }
    }
}
