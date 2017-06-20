package olimpiadas.sena.com.olimpiadasmath.model;

/**
 * Created by andres on 19/06/2017.
 */

public class User {
    protected String nickname;
    protected int points;
    protected int position;

    public User(String nickname, int points, int position) {
        this.nickname = nickname;
        this.points = points;
        this.position = position;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
