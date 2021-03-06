package olimpiadas.sena.com.olimpiadasmath.model;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andres on 19/06/2017.
 */

public class User  extends RealmObject {
    @Ignore
    private final String TAG = User.class.toString();


    @PrimaryKey
    private String idUser;

    private String id;

    private String nickname;
    private String password;
    private int score;
    private int position;
    private int coins;
    private int tickets;
    private double experience;
    private int level;
    private String avatar;
    private String answers;
    private String time;

    public User() {
    }

    public User(String nickname, String password, int score, int position, int coins, int tickets, double experience, int level , String avatar) {
        this.idUser = UUID.randomUUID().toString();
        this.nickname = nickname;
        this.password = password;
        this.score = score;
        this.position = position;
        this.coins = coins;
        this.tickets = tickets;
        this.experience = experience;
        this.level = level;
        this.avatar = avatar;
    }


    public User(String nickname, int score, int position) {
        this.idUser = UUID.randomUUID().toString();
        this.nickname = nickname;
        this.score = score;
        this.position = position;
    }

    public User(String nickname, int score, int position, int coins, int tickets, int level, double experience, String avatar, String answers) {
        this.idUser = UUID.randomUUID().toString();
        this.nickname = nickname;
        this.score = score;
        this.position = position;
        this.coins = coins;
        this.tickets = tickets;
        this.level = level;
        this.experience = experience;
        this.avatar = avatar;
        this.answers = answers;
    }
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
    public void addTickets(int tickets) {
        this.tickets = this.tickets +  tickets;
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

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getAnswers() {
        return answers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void updateUser(final User user){


        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(user);
            }
        });

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
