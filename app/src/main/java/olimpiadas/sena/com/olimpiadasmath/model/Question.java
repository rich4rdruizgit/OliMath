package olimpiadas.sena.com.olimpiadasmath.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rich4 on 22/06/2017.
 */

public class Question  extends RealmObject{
    @Ignore
    private final String TAG = Question.class.toString();
    @PrimaryKey
    private String idQuestion;
    private String jsonObject;


    public Question() {
    }

    public Question(String jsonObject) {
        this.idQuestion = UUID.randomUUID().toString();
        this.jsonObject = jsonObject;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getQuestionText(){
        try {
            Log.d(TAG,"Se va a crear json");
            JSONObject question = new JSONObject(jsonObject);
            Log.d(TAG,"Text key " + question.getString("text") );
            return question.getString("text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAnswerText(int i){
        try {
            JSONObject answer = new JSONObject(jsonObject);
            return answer.getJSONArray("answers").getJSONObject(i).getString("text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAnswerCorrect(int i){
        try {
            JSONObject iscorrect = new JSONObject(jsonObject);
            return iscorrect.getJSONArray("answers").getJSONObject(i).getString("isCorrect");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}