package ex08;

/*
 * Билет — record: собран один раз в кассе и больше не меняется.
 * Заметь: record МОЖЕТ реализовывать интерфейс, как обычный класс.
 *
 * ШАГ 1. describe: верни строку ровно в виде
 *   Билет на «событие» (КАТЕГОРИЯ): цена
 * например: Билет на «Концерт» (ADULT): 100
 * Подсказка: enum в конкатенации сам превращается в своё имя.
 */
public record Ticket(String event, Category category, int price) implements Describable {

    @Override
    public String describe() {
        // TODO: замени заглушку
        return "";
    }
}
