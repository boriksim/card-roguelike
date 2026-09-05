package ex08;

/*
 * ШАГ 4. Диспетчер автопарка. Метод static осознанно: это правило
 * всей службы, а не чьё-то личное свойство — объект ему не нужен.
 */
public class Fleet {

    // Верни транспорт с МИНИМАЛЬНОЙ ценой рейса на dist километров.
    // При равной цене побеждает тот, кто стоит в массиве раньше.
    // Пустой массив в службе не бывает — на null можно не проверять.
    //
    // Обрати внимание на тип параметра: подойдёт ЛЮБОЙ Vehicle[] —
    // полиморфизм сам вызовет нужную формулу tripPrice() у каждого.
    public static Vehicle cheapest(Vehicle[] fleet, int dist) {
        Vehicle best = fleet[0];
        for (int i = 1; i < fleet.length; i++) {
            if (fleet[i].tripPrice(dist) < best.tripPrice(dist)) {
                best = fleet[i];
            }
        }
        return best;
    }
}
