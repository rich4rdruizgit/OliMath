package olimpiadas.sena.com.olimpiadasmath.model;

import io.realm.RealmObject;

/**
 * Created by defytek on 6/21/17.
 * Tabla para gaurdar los bonus que se darán por cada experiencia
 */

public class BonusTable extends RealmObject {

    //1 practice,2 chalenge

    float max;
    float min;
    float coin;
    float ticket;
    float exp;
    float score;
    int type;


    public BonusTable(){

    }

    public BonusTable(float max, float min, float coin, float ticket, float exp, float score, int type) {
        this.max = max;
        this.min = min;
        this.coin = coin;
        this.ticket = ticket;
        this.exp = exp;
        this.score = score;
        this.type = type;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getCoin() {
        return coin;
    }

    public void setCoin(float coin) {
        this.coin = coin;
    }

    public float getTicket() {
        return ticket;
    }

    public void setTicket(float ticket) {
        this.ticket = ticket;
    }

    public float getExp() {
        return exp;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
