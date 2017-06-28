package olimpiadas.sena.com.olimpiadasmath.model;

import io.realm.RealmObject;

/**
 * Created by andres on 19/06/2017.
 */

public class User extends RealmObject {
    private String nickname;
    private String password;
    private int score;
    private int position;
    private int coins;
    private int tickets;
    private double experience;
    private int level;

    public User() {
    }

    public User(String nickname, String password, int score, int position, int coins, int tickets, double experience, int level) {
        this.nickname = nickname;
        this.password = password;
        this.score = score;
        this.position = position;
        this.coins = coins;
        this.tickets = tickets;
        this.experience = experience;
        this.level = level;
    }


    public User(String nickname, int score, int position) {
        this.nickname = nickname;
        this.score = score;
        this.position = position;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
