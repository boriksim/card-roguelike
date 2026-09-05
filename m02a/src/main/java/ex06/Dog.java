package ex06;

/*
 * Урок 2a.6 — «Полиморфизм».
 *
 * ШАГ 2. Переопредели voice(): собака говорит "Гав!".
 * Не забудь @Override.
 */
public class Dog extends Animal {

    @Override
    public String voice() { return "Гав!"; }
}
