package olimpiadas.sena.com.olimpiadasmath.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by didier on 27/09/2017.
 */

public class Result extends RealmObject {

    @Ignore
    private final String TAG = Result.class.toString();

    @PrimaryKey
    private String idResult;
    private String idUserResult;
    private int numQuestionResult;
    private int answerCorrectResult;
    private int answerIncorrectResult;
    private int answerNoneResult;
    private long timeGlobalResult;
    private long timeTestResult;
    private int coinsWinResult;
    private int coinsLoseResult;
    private int coinsBetResult;



    // numero de preguntas, num de resp correctas, num resp NR, num resp incorrectas, tiempo global, tiempo en test, monedas ganadas ,monedas apostadas, id user


    public Result(String idResult, String idUserResult, int numQuestionResult, int answerCorrectResult, int answerIncorrectResult, int answerNoneResult, long timeGlobalResult, long timeTestResult, int coinsWinResult, int coinsLoseResult, int coinsBetResult) {
        this.idResult = idResult;
        this.idUserResult = idUserResult;
        this.numQuestionResult = numQuestionResult;
        this.answerCorrectResult = answerCorrectResult;
        this.answerIncorrectResult = answerIncorrectResult;
        this.answerNoneResult = answerNoneResult;
        this.timeGlobalResult = timeGlobalResult;
        this.timeTestResult = timeTestResult;
        this.coinsWinResult = coinsWinResult;
        this.coinsLoseResult = coinsLoseResult;
        this.coinsBetResult = coinsBetResult;
    }

    public Result() {
    }

    public String getIdResult() {
        return idResult;
    }

    public void setIdResult(String idResult) {
        this.idResult = idResult;
    }

    public String getIdUserResult() {
        return idUserResult;
    }

    public void setIdUserResult(String idUserResult) {
        this.idUserResult = idUserResult;
    }

    public int getNumQuestionResult() {
        return numQuestionResult;
    }

    public void setNumQuestionResult(int numQuestionResult) {
        this.numQuestionResult = numQuestionResult;
    }

    public int getAnswerCorrectResult() {
        return answerCorrectResult;
    }

    public void setAnswerCorrectResult(int answerCorrectResult) {
        this.answerCorrectResult = answerCorrectResult;
    }

    public int getAnswerIncorrectResult() {
        return answerIncorrectResult;
    }

    public void setAnswerIncorrectResult(int answerIncorrectResult) {
        this.answerIncorrectResult = answerIncorrectResult;
    }

    public int getAnswerNoneResult() {
        return answerNoneResult;
    }

    public void setAnswerNoneResult(int answerNoneResult) {
        this.answerNoneResult = answerNoneResult;
    }


    public long getTimeGlobalResult() {
        return timeGlobalResult;
    }

    public void setTimeGlobalResult(long timeGlobalResult) {
        this.timeGlobalResult = timeGlobalResult;
    }

    public long getTimeTestResult() {
        return timeTestResult;
    }

    public void setTimeTestResult(long timeTestResult) {
        this.timeTestResult = timeTestResult;
    }

    public int getCoinsWinResult() {
        return coinsWinResult;
    }

    public void setCoinsWinResult(int coinsWinResult) {
        this.coinsWinResult = coinsWinResult;
    }

    public int getCoinsLoseResult() {
        return coinsLoseResult;
    }

    public void setCoinsLoseResult(int coinsLoseResult) {
        this.coinsLoseResult = coinsLoseResult;
    }

    public int getCoinsBetResult() {
        return coinsBetResult;
    }

    public void setCoinsBetResult(int coinsBetResult) {
        this.coinsBetResult = coinsBetResult;
    }
}
