package game;

import java.util.Scanner;

/*
 * ============================================================
 *   КАРТОЧНЫЙ РОГЛАЙК — v0.0 «ходячий скелет»
 * ============================================================
 *
 * Это стартовый код игры, которая будет расти вместе с курсом.
 * Пока здесь один захардкоженный бой: герой против одного врага.
 *
 * Читать и понимать весь файл СЕЙЧАС не нужно!
 *  - Урок 0.2: поменяй константы в блоке «МЕНЯЙ МЕНЯ» и запусти игру.
 *  - Урок 0.3: придумай игре название и сделай первый коммит.
 *  - Модуль 1: разберём каждую строчку — типы, строки, условия,
 *    циклы, массивы и методы. К уроку 1.8 весь этот код станет твоим.
 *
 * ⚙ «Магия авансом»:
 *   - public static void main — что значат эти слова, разберём в модуле 2a;
 *   - Scanner (чтение с клавиатуры) — в конце модуля 1 (урок 1.8);
 *   - Maven-обвязка и тесты — в модуле 5.
 */
public class Main {

    // ============================================================
    // === МЕНЯЙ МЕНЯ (урок 0.2) ==================================
    // ============================================================
    // Это константы (constants) — значения, задающие правила боя.
    // Поменяй число или текст, запусти игру и посмотри на эффект.

    static final String GAME_TITLE = "БЕЗЫМЯННЫЙ КАРТОЧНЫЙ РОГЛАЙК"; // урок 0.3: придумай своё название!
    static final String HERO_NAME = "Bob";
    static final int HERO_HP = 50;          // здоровье героя
    static final int ENERGY_PER_TURN = 3;   // энергия на один ход

    static final String ENEMY_NAME = "Гоблин-мародёр";
    static final int ENEMY_HP = 50;         // здоровье врага
    static final int ENEMY_DAMAGE = 7;      // сколько враг бьёт
    static final int ENEMY_BLOCK = 4;       // сколько блока даёт его защита

    // Рука героя — три карты. Данные лежат «параллельно»: CARD_NAMES[0],
    // CARD_COST[0], CARD_DAMAGE[0] — это всё про первую карту.
    // Такие списки называются массивами (arrays) — разберём в уроке 1.6.
    static final String[] CARD_NAMES  = { "Удар", "Щит", "Зелье" };
    static final int[]    CARD_COST   = { 1, 1, 2 };  // цена карты в энергии
    static final int[]    CARD_DAMAGE = { 9, 0, 0 };  // урон врагу
    static final int[]    CARD_BLOCK  = { 0, 5, 0 };  // блок себе (гасит удар врага)
    static final int[]    CARD_HEAL   = { 0, 0, 7 };  // лечение себе

    // === конец блока «МЕНЯЙ МЕНЯ» ===============================

    // ANSI-коды — «волшебные» строки, которые красят текст в терминале.
    // Работают в Windows Terminal и в консоли IntelliJ.
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String RED    = "\u001B[31m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN   = "\u001B[36m";

    // Текущее состояние боя. Меняется по ходу игры — в отличие от констант выше.
    static int heroHp;
    static int heroBlock;
    static int enemyHp;
    static int enemyBlock;

    // Scanner читает то, что игрок вводит с клавиатуры. ⚙ Разберём в уроке 1.8.
    static final Scanner INPUT = new Scanner(System.in);

    // Отсюда программа начинает выполняться.
    public static void main(String[] args) {
        printBanner();

        heroHp = HERO_HP;
        enemyHp = ENEMY_HP;
        int turn = 1;

        // Главный цикл боя: крутимся, пока оба живы (урок 1.5).
        while (heroHp > 0 && enemyHp > 0) {
            heroBlock = 0; // блок живёт ровно один ход и «сгорает»
            boolean enemyDefends = enemyDefendsOnTurn(turn);

            System.out.println();
            System.out.println(BOLD + "======== ХОД " + turn + " ========" + RESET);
            printStatus();
            printIntent(enemyDefends);

            playerTurn();
            if (enemyHp > 0) {
                enemyTurn(enemyDefends);
            }
            turn = turn + 1;
        }

        printResult();
    }

    // ---------- ход игрока ----------

    static void playerTurn() {
        int energy = ENERGY_PER_TURN;
        // Какие карты уже сыграны в этом ходу (каждую можно сыграть один раз).
        boolean[] played = new boolean[CARD_NAMES.length];

        while (energy > 0 && enemyHp > 0) {
            printHand(energy, played);
            int choice = readInt("Номер карты (0 — закончить ход): ");
            if (choice == 0) {
                break; // игрок сам заканчивает ход
            }

            int index = choice - 1; // игрок видит карты с 1, массив считает с 0
            if (index < 0 || index >= CARD_NAMES.length) {
                System.out.println(YELLOW + "Такой карты нет. Выбери номер из списка." + RESET);
                continue;
            }
            if (played[index]) {
                System.out.println(YELLOW + "«" + CARD_NAMES[index] + "» уже сыграна в этом ходу." + RESET);
                continue;
            }
            if (CARD_COST[index] > energy) {
                System.out.println(YELLOW + "Не хватает энергии на «" + CARD_NAMES[index] + "»." + RESET);
                continue;
            }

            energy = energy - CARD_COST[index];
            played[index] = true;
            playCard(index);
        }
    }

    // Применяем эффекты карты с номером index (в массивах — с нуля!).
    static void playCard(int index) {
        System.out.println(CYAN + HERO_NAME + " играет «" + CARD_NAMES[index] + "»." + RESET);

        if (CARD_DAMAGE[index] > 0) {
            int hit = damageAfterBlock(CARD_DAMAGE[index], enemyBlock);
            enemyBlock = Math.max(0, enemyBlock - CARD_DAMAGE[index]);
            enemyHp = enemyHp - hit;
            System.out.println("  " + ENEMY_NAME + " получает " + RED + hit + " урона" + RESET + ".");
        }
        if (CARD_BLOCK[index] > 0) {
            heroBlock = heroBlock + CARD_BLOCK[index];
            System.out.println("  " + HERO_NAME + " поднимает " + GREEN + CARD_BLOCK[index] + " блока" + RESET + ".");
        }
        if (CARD_HEAL[index] > 0) {
            int before = heroHp;
            heroHp = healedHp(heroHp, CARD_HEAL[index], HERO_HP);
            System.out.println("  " + HERO_NAME + " лечится на " + GREEN + (heroHp - before) + " HP" + RESET + ".");
        }
    }

    // ---------- ход врага ----------

    static void enemyTurn(boolean defends) {
        enemyBlock = 0; // старый блок врага сгорает
        if (defends) {
            enemyBlock = ENEMY_BLOCK;
            System.out.println(RED + ENEMY_NAME + " уходит в защиту (+" + ENEMY_BLOCK + " блока)." + RESET);
        } else {
            int hit = damageAfterBlock(ENEMY_DAMAGE, heroBlock);
            heroHp = heroHp - hit;
            System.out.println(RED + ENEMY_NAME + " атакует! " + HERO_NAME + " получает " + hit + " урона." + RESET);
        }
    }

    // Намерение врага: каждый третий ход (3, 6, 9, ...) он защищается.
    static boolean enemyDefendsOnTurn(int turn) {
        return turn % 3 == 0;
    }

    // ---------- чистая боевая математика ----------
    // Эти маленькие функции проверяет тест SmokeTest (урок 0.2).

    // Сколько урона проходит сквозь блок. Меньше нуля не бывает.
    static int damageAfterBlock(int damage, int block) {
        return Math.max(0, damage - block);
    }

    // Лечение с потолком: выше maxHp здоровье не поднимается.
    static int healedHp(int currentHp, int healAmount, int maxHp) {
        return Math.min(maxHp, currentHp + healAmount);
    }

    // Полоска здоровья из 10 клеток: '#' — есть HP, '-' — потеряно.
    static String hpBar(int hp, int maxHp) {
        int filled = Math.max(0, hp * 10 / maxHp);
        return "#".repeat(filled) + "-".repeat(10 - filled);
    }

    // ---------- вывод на экран ----------

    static void printBanner() {
        System.out.println(BOLD + CYAN + "==================================================");
        System.out.println("   " + GAME_TITLE);
        System.out.println("==================================================" + RESET);
        System.out.println(HERO_NAME + " входит в подземелье. Навстречу — " + ENEMY_NAME + "!");
    }

    static void printStatus() {
        System.out.println(GREEN + HERO_NAME + "  [" + hpBar(heroHp, HERO_HP) + "] "
                + heroHp + "/" + HERO_HP + " HP" + RESET);
        String enemyLine = RED + ENEMY_NAME + "  [" + hpBar(enemyHp, ENEMY_HP) + "] "
                + enemyHp + "/" + ENEMY_HP + " HP";
        if (enemyBlock > 0) {
            enemyLine = enemyLine + " (блок " + enemyBlock + ")";
        }
        System.out.println(enemyLine + RESET);
    }

    static void printIntent(boolean defends) {
        if (defends) {
            System.out.println(YELLOW + "Намерение врага: уйти в защиту (+" + ENEMY_BLOCK + " блока)." + RESET);
        } else {
            System.out.println(YELLOW + "Намерение врага: атаковать на " + ENEMY_DAMAGE + "." + RESET);
        }
    }

    static void printHand(int energy, boolean[] played) {
        System.out.println();
        System.out.println(CYAN + "Энергия: " + energy + "/" + ENERGY_PER_TURN + RESET + "  Карты в руке:");
        for (int i = 0; i < CARD_NAMES.length; i++) {
            String line = "  " + (i + 1) + ") " + CARD_NAMES[i]
                    + " (стоимость " + CARD_COST[i] + "):" + describeCard(i);
            if (played[i]) {
                line = line + "  — уже сыграна";
            }
            System.out.println(line);
        }
    }

    // Собирает описание эффектов карты, например " урон 6" или " лечение 7".
    static String describeCard(int index) {
        String text = "";
        if (CARD_DAMAGE[index] > 0) {
            text = text + " урон " + CARD_DAMAGE[index];
        }
        if (CARD_BLOCK[index] > 0) {
            text = text + " блок " + CARD_BLOCK[index];
        }
        if (CARD_HEAL[index] > 0) {
            text = text + " лечение " + CARD_HEAL[index];
        }
        return text;
    }

    static void printResult() {
        System.out.println();
        if (heroHp > 0) {
            System.out.println(GREEN + BOLD + "ПОБЕДА! " + ENEMY_NAME + " повержен." + RESET);
        } else {
            System.out.println(RED + BOLD + "ПОРАЖЕНИЕ... " + HERO_NAME + " пал в бою." + RESET);
        }
        System.out.println("Это была " + GAME_TITLE + " v0.0. Продолжение — в следующих модулях!");
    }

    // ---------- ввод ----------

    // Читает целое число. Если игрок ввёл не число — просит ещё раз.
    // Если ввод закончился совсем (Ctrl+Z в Windows, Ctrl+D в Linux/macOS,
    // или конец перенаправленного файла) — вежливо завершаем игру.
    static int readInt(String prompt) {
        System.out.print(prompt);
        while (!INPUT.hasNextInt()) {
            if (!INPUT.hasNext()) { // ввода больше не будет
                System.out.println();
                System.out.println(YELLOW + "Ввод закончился — бой прерван. До встречи!" + RESET);
                System.exit(0); // корректно выходим из игры
            }
            INPUT.next(); // выбрасываем то, что числом не является
            System.out.print("Нужно число. Попробуй ещё раз: ");
        }
        return INPUT.nextInt();
    }
}
