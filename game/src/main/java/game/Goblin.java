package game;

public class Goblin extends Enemy {

    static final int HP = 26;
    static final int DAMAGE = 7;
    static final int BLOCK = 4;

    private int turn = 0;    // какой по счёту ход гоблина идёт
    private boolean defends; // план на текущий ход

    public Goblin() {
        super("Гоблин-мародёр", HP);
    }

    @Override
    public String chooseIntent() {
        turn = turn + 1;
        defends = (turn % 3 == 0); // каждый третий ход — защита
        if (defends) {
            return "уйти в защиту (+" + BLOCK + " блока)";
        }
        return "атаковать на " + DAMAGE;
    }

    @Override
    public void act(Hero hero) {
        if (defends) {
            addBlock(BLOCK);
            System.out.println(Ansi.RED + getName() + " прячется за щитом (+" + BLOCK
                    + " блока)." + Ansi.RESET);
        } else {
            int before = hero.getHp();
            hero.takeDamage(DAMAGE);
            System.out.println(Ansi.RED + getName() + " атакует! " + hero.getName()
                    + " получает " + (before - hero.getHp()) + " урона." + Ansi.RESET);
        }
    }
}