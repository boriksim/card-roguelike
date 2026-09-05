package ex06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2a.6 «Полиморфизм».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
//@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.6)")
class ChorusTest {

    @Test
    void catAndDogOverrideVoice() {
        assertEquals("Мяу!", new Cat().voice(), "Cat должен переопределить voice()");
        assertEquals("Гав!", new Dog().voice(), "Dog должен переопределить voice()");
        assertEquals("...", new Animal().voice(), "Базовый Animal остаётся неразборчивым — его не меняем");
    }

    @Test
    void variableTypeDoesNotHideRealObject() {
        Animal someone = new Cat(); // в переменной типа Animal живёт кошка
        assertEquals("Мяу!", someone.voice(),
                "Java выбирает метод по РЕАЛЬНОМУ объекту, а не по типу переменной");
    }

    @Test
    void chorusCallsEveryVoicePolymorphically() {
        Animal[] band = { new Cat(), new Dog(), new Animal(), new Cat() };
        assertEquals("Мяу! Гав! ... Мяу!", Exercise.chorus(band),
                "Собери голоса через один пробел, без пробела в конце");
    }

    @Test
    void emptyChorusIsSilent() {
        assertEquals("", Exercise.chorus(new Animal[0]), "Пустой хор молчит: верни пустую строку");
    }
}
