package olimpiadas.sena.com.olimpiadasmath.model;

/**
 * Created by andres on 22/06/2017.
 */


//// TODO: 22/06/2017 Crear funcionalidad para almacenar la informacion del usuario actual

public class Player extends User {
    private int coins;
    private int tickets;
    private double experience;

    public Player(String nickname, int score, int position, int coins, int tickets, double experience) {
        super(nickname, score, position);
        this.coins = coins;
        this.tickets = tickets;
        this.experience = experience;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }
}
