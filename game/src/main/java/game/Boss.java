package game;

public class Boss extends Enemy{
    static final int HP = 42;
    static final int BASE_DAMAGE = 8;
    static final int RAGE_DAMAGE = 13;

    private boolean rageThisTurn;

    public Boss() {
        super("Повелитель подземелья", HP);
    }

    public boolean isEnraged() {
        return getHp() < getMaxHp() / 2;
    }

    @Override
    String chooseIntent() {
        rageThisTurn = isEnraged();
        if (rageThisTurn) {
            return "В ЯРОСТИ! атаковать на " + RAGE_DAMAGE;
        }
        return "атаковать на " + BASE_DAMAGE;
    }

    @Override
    void act(Hero hero) {
        int damage = BASE_DAMAGE;
        if (rageThisTurn) {
            damage = RAGE_DAMAGE;
        }
        int before = hero.getHp();
        hero.takeDamage(damage);
        if (rageThisTurn) {
            System.out.println(Ansi.RED + Ansi.BOLD + getName() + " яростно обрушивает удар! "
                    + hero.getName() + " получает " + (before - hero.getHp()) + " урона." + Ansi.RESET);
        } else {
            System.out.println(Ansi.RED + getName() + " бьёт тяжёлой лапой. " + hero.getName()
                    + " получает " + (before - hero.getHp()) + " урона." + Ansi.RESET);
        }
    }
}
