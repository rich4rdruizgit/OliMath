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
    private String numQuestionResult;
    private String answerCorrectResult;
    private String answerIncorrectResult;
    private String answerNoneResult;
    private String timeGlobalResult;
    private String timeTestResult;
    private String coinsWinResult;
    private String coinsLoseResult;
    private String coinsBetResult;



    // numero de preguntas, num de resp correctas, num resp NR, num resp incorrectas, tiempo global, tiempo en test, monedas ganadas ,monedas apostadas, id user


    public Result(String idResult, String idUserResult, String numQuestionResult, String answerCorrectResult, String answerIncorrectResult, String answerNoneResult, String timeGlobalResult, String timeTestResult, String coinsWinResult, String coinsLoseResult, String coinsBetResult) {
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

    public String getNumQuestionResult() {
        return numQuestionResult;
    }

    public void setNumQuestionResult(String numQuestionResult) {
        this.numQuestionResult = numQuestionResult;
    }

    public String getAnswerCorrectResult() {
        return answerCorrectResult;
    }

    public void setAnswerCorrectResult(String answerCorrectResult) {
        this.answerCorrectResult = answerCorrectResult;
    }

    public String getAnswerIncorrectResult() {
        return answerIncorrectResult;
    }

    public void setAnswerIncorrectResult(String answerIncorrectResult) {
        this.answerIncorrectResult = answerIncorrectResult;
    }

    public String getAnswerNoneResult() {
        return answerNoneResult;
    }

    public void setAnswerNoneResult(String answerNoneResult) {
        this.answerNoneResult = answerNoneResult;
    }

    public String getTimeGlobalResult() {
        return timeGlobalResult;
    }

    public void setTimeGlobalResult(String timeGlobalResult) {
        this.timeGlobalResult = timeGlobalResult;
    }

    public String getTimeTestResult() {
        return timeTestResult;
    }

    public void setTimeTestResult(String timeTestResult) {
        this.timeTestResult = timeTestResult;
    }

    public String getCoinsWinResult() {
        return coinsWinResult;
    }

    public void setCoinsWinResult(String coinsWinResult) {
        this.coinsWinResult = coinsWinResult;
    }

    public String getCoinsLoseResult() {
        return coinsLoseResult;
    }

    public void setCoinsLoseResult(String coinsLoseResult) {
        this.coinsLoseResult = coinsLoseResult;
    }

    public String getCoinsBetResult() {
        return coinsBetResult;
    }

    public void setCoinsBetResult(String coinsBetResult) {
        this.coinsBetResult = coinsBetResult;
    }
}
