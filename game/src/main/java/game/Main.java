package game;

import java.util.Scanner;
import java.util.Random;

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
 *   - Maven-обвязка и тесты — в модуле 5.
 */
public class Main {

    // ============================================================
    // === МЕНЯЙ МЕНЯ (урок 0.2) ==================================
    // ============================================================
    // Это константы (constants) — значения, задающие правила боя.
    // Поменяй число или текст, запусти игру и посмотри на эффект.

    static final String GAME_TITLE = "Dungeon Explorer: Card Battler"; // урок 0.3: придумай своё название!
    static final String HERO_NAME = "Bob";
    static final int HERO_HP = 30;          // здоровье героя
    static final int ENERGY_PER_TURN = 3;   // энергия на один ход

    static int totalCardsPlayed = 0;

    // Рука героя — три карты. Данные лежат «параллельно»: HAND.name[0],
    // HAND.cost[0], HAND.damage[0] — это всё про первую карту.
    // Такие списки называются массивами (arrays) — разберём в уроке 1.6.
    static final Card[] HAND = Card.starter();

    // === конец блока «МЕНЯЙ МЕНЯ» ===============================

    // Текущее состояние боя. Меняется по ходу игры — в отличие от констант выше.
    static Hero hero;
    static Enemy enemy;

    // Scanner читает то, что игрок вводит с клавиатуры. ⚙ Разберём в уроке 1.8.
    static final Scanner INPUT = new Scanner(System.in);

    // Отсюда программа начинает выполняться.
    public static void main(String[] args) {
        printBanner();
        hero = new Hero(HERO_NAME, HERO_HP);

        Enemy[] enemies = { new Slime(), new Goblin(), new Archer() };
        shuffle(enemies); // каждый забег — свой порядок комнат

        for (int room = 0; room < enemies.length; room++) {
            System.out.println();
            System.out.println(Ansi.BOLD + "--- Комната " + (room + 1) + " из "
                    + (enemies.length + 1) + " ---" + Ansi.RESET);
            enemy = enemies[room];
            fight();
            if (!hero.isAlive()) {
                printResult();
                return;
            }
            rest(); // привал между боями
        }

        System.out.println();
        System.out.println(Ansi.BOLD + Ansi.RED + "--- Комната " + (enemies.length + 1)
                + ": ЛОГОВО БОССА ---" + Ansi.RESET);
        enemy = new Boss();
        fight();
        printResult();
    }

    static void fight() {
        System.out.println("Навстречу выходит " + Ansi.RED + enemy.getName() + Ansi.RESET
                + " (" + enemy.getHp() + " HP)!");
        int turn = 1;

        while (hero.isAlive() && enemy.isAlive()) {
            hero.resetBlock();
            String intent = enemy.chooseIntent();

            System.out.println();
            System.out.println(Ansi.BOLD + "======== ХОД " + turn + " ========" + Ansi.RESET);
            printStatus();
            System.out.println(Ansi.YELLOW + "Намерение врага: " + intent + "." + Ansi.RESET);

            playerTurn();
            if (enemy.isAlive()) {
                enemy.resetBlock();
                enemy.act(hero);
            }
            turn = turn + 1;
        }

        if (hero.isAlive()) {
            System.out.println(Ansi.GREEN + Ansi.BOLD + enemy.getName() + " повержен!" + Ansi.RESET);
        }
    }

    static void rest() {
        int before = hero.getHp();
        hero.heal(hero.getMaxHp() / 3);
        System.out.println(Ansi.GREEN + "Привал у костра: " + hero.getName() + " восстанавливает "
                + (hero.getHp() - before) + " HP (" + hero.getHp() + "/" + hero.getMaxHp() + ")."
                + Ansi.RESET);
    }

    static void shuffle(Enemy[] enemies) {
        Random random = new Random();
        for (int i = enemies.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Enemy tmp = enemies[i];
            enemies[i] = enemies[j];
            enemies[j] = tmp;
        }
    }

    // ---------- ход игрока ----------

    static void playerTurn() {
        int energy = ENERGY_PER_TURN;
        // Какие карты уже сыграны в этом ходу (каждую можно сыграть один раз).
        boolean[] played = new boolean[HAND.length];

        while (energy > 0 && enemy.getHp() > 0) {
            printHand(energy, played);
            int choice = readInt("Номер карты (0 — закончить ход): ");
            if (choice == 0) {
                break; // игрок сам заканчивает ход
            }

            int index = choice - 1; // игрок видит карты с 1, массив считает с 0
            if (index < 0 || index >= HAND.length) {
                System.out.println(Ansi.YELLOW + "Такой карты нет. Выбери номер из списка." + Ansi.RESET);
                continue;
            }
            if (played[index]) {
                System.out.println(Ansi.YELLOW + "«" + HAND[index] + "» уже сыграна в этом ходу." + Ansi.RESET);
                continue;
            }
            if (HAND[index].getCost() > energy) {
                System.out.println(Ansi.YELLOW + "Не хватает энергии на «" + HAND[index] + "»." + Ansi.RESET);
                continue;
            }

            energy = energy - HAND[index].getCost();
            played[index] = true;
            playCard(index);
        }
    }

    // Применяем эффекты карты с номером index (в массивах — с нуля!).
    static void playCard(int index) {
        Card card = HAND[index];
        System.out.println(Ansi.CYAN + HERO_NAME + " играет «" + card.getName() + "»." + Ansi.RESET);

        if (card.getDamage() > 0) {
            int before = enemy.getHp();
            enemy.takeDamage(card.getDamage());
            System.out.println("  " + enemy.getName() + " получает " + Ansi.RED + (before - enemy.getHp()) + " урона" + Ansi.RESET + ".");
        }
        if (card.getBlock() > 0) {
            hero.addBlock(card.getBlock());
            System.out.println("  " + HERO_NAME + " поднимает " + Ansi.GREEN + card.getBlock() + " блока" + Ansi.RESET + ".");
        }
        if (card.getHeal() > 0) {
            int before = hero.getHp();
            hero.heal(card.getHeal());
            System.out.println("  " + HERO_NAME + " лечится на " + Ansi.GREEN + (hero.getHp() - before) + " HP" + Ansi.RESET + ".");
        }
        totalCardsPlayed++;
    }

    // Полоска здоровья из 10 клеток: '#' — есть HP, '-' — потеряно.
    static String hpBar(int hp, int maxHp) {
        int filled = Math.max(0, hp * 10 / maxHp);
        return "#".repeat(filled) + "-".repeat(10 - filled);
    }

    // ---------- вывод на экран ----------

    static void printBanner() {
        System.out.println(Ansi.BOLD + Ansi.CYAN + "=".repeat(50));
        System.out.println("   " + GAME_TITLE);
        System.out.println("=".repeat(50) + Ansi.RESET);
        System.out.println(HERO_NAME + " входит в подземелье.");
        System.out.println(HERO_NAME + ": " + HERO_HP + " HP, энергия на ход: " + ENERGY_PER_TURN);
    }

    static void printStatus() {
        System.out.println(Ansi.GREEN + HERO_NAME + "  [" + hpBar(hero.getHp(), HERO_HP) + "] "
                + hero.getHp() + "/" + HERO_HP + " HP" + Ansi.RESET);
        if (hero.getHp() * 4 <= HERO_HP) {
            System.out.println(Ansi.YELLOW + "Осторожно: HP на исходе!" + Ansi.RESET);
        }
        String enemyLine = Ansi.RED + enemy.getName() + "  [" + hpBar(enemy.getHp(), enemy.getMaxHp()) + "] "
                + enemy.getHp() + "/" + enemy.getMaxHp() + " HP";
        if (enemy.getBlock() > 0) {
            enemyLine = enemyLine + " (блок " + enemy.getBlock() + ")";
        }
        System.out.println(enemyLine + Ansi.RESET);
    }

    static void printHand(int energy, boolean[] played) {
        System.out.println();
        System.out.println(Ansi.CYAN + "Энергия: " + energy + "/" + ENERGY_PER_TURN + Ansi.RESET + "  Карты в руке:");
        for (int i = 0; i < HAND.length; i++) {
            String line = "  " + (i + 1) + ") " + HAND[i];
            if (played[i]) {
                line = line + "  — уже сыграна";
            }
            System.out.println(line);
        }
    }

    static void printResult() {
        System.out.println();
        if (hero.isAlive()) {
            System.out.println(Ansi.GREEN + Ansi.BOLD + "ПОБЕДА В ЗАБЕГЕ! Подземелье зачищено, босс пал." + Ansi.RESET);
        } else {
            System.out.println(Ansi.RED + Ansi.BOLD + "ПОРАЖЕНИЕ... " + hero.getName() + " пал в бою." + Ansi.RESET);
        }
        System.out.println("Это была " + GAME_TITLE + " v0.2. Продолжение — в следующих модулях!");
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
                System.out.println(Ansi.YELLOW + "Ввод закончился — бой прерван. До встречи!" + Ansi.RESET);
                System.exit(0); // корректно выходим из игры
            }
            INPUT.next(); // выбрасываем то, что числом не является
            System.out.print("Нужно число. Попробуй ещё раз: ");
        }
        return INPUT.nextInt();
    }
}
