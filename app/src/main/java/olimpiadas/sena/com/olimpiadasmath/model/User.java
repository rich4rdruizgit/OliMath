package olimpiadas.sena.com.olimpiadasmath.model;

/**
 * Created by andres on 19/06/2017.
 */

public class User {
    private String nickname;
    private int score;
    private int position;
    private int coins;
    private int tickets;
    private double experience;
    

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
}
