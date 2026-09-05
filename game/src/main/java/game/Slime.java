package game;

public class Slime extends Enemy {

    static final int HP = 24;
    static final int DAMAGE = 4;

    public Slime() {
        super("Слизень", HP);
    }

    @Override // пометка: подменяю метод родителя своим
    public String chooseIntent() {
        return "атаковать на " + DAMAGE;
    }

    @Override
    public void act(Hero hero) {
        int before = hero.getHp();
        hero.takeDamage(DAMAGE);
        System.out.println(Ansi.RED + getName() + " атакует! " + hero.getName()
                + " получает " + (before - hero.getHp()) + " урона." + Ansi.RESET);
    }
}