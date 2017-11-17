package olimpiadas.sena.com.olimpiadasmath.model;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
            //Log.d(TAG,"Se va a crear json");
            JSONObject question = new JSONObject(jsonObject);
            //Log.d(TAG,"Text key " + question.getString("text") );
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
            //Log.d(TAG,"getAnswerCorrect i = " + i);
            //Log.d(TAG,"isCorrect toString =  " + iscorrect.toString());
            return iscorrect.getJSONArray("answers").getJSONObject(i).getString("isCorrect");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getAnswerId(int i){
        try {
            JSONObject iscorrect = new JSONObject(jsonObject);
            //Log.d(TAG,"getAnswerCorrect i = " + i);
            //Log.d(TAG,"isCorrect toString =  " + iscorrect.toString());
            return iscorrect.getJSONArray("answers").getJSONObject(i).getString("idAnswer");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getCorrectAnswerText(){
        try {
            JSONObject iscorrect = new JSONObject(jsonObject);
            String text = "";
            for(int i = 0; i < iscorrect.getJSONArray("answers").length(); i ++){
                if(iscorrect.getJSONArray("answers").getJSONObject(i).getString("isCorrect").equals("1")){
                    text = iscorrect.getJSONArray("answers").getJSONObject(i).getString("text");
                }
            }
            return text;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String getFeedback(){
        try {
            JSONObject iscorrect = new JSONObject(jsonObject);
            String text = "";
            for(int i = 0; i < iscorrect.getJSONArray("answers").length(); i ++){
                if(iscorrect.getJSONArray("answers").getJSONObject(i).getString("isCorrect").equals("1")){
                    text = iscorrect.getJSONArray("answers").getJSONObject(i).getString("argument");
                }
            }
            return text;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No encontrado";

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<Question> JsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<Question> qs = new ArrayList<>();
        /**
         * Aqui vamos a recorrer la lista de preguntas y respuestar para formatearlas y almacenarlas en la
         * base de datos.
         */
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObjectQuestion = (JSONObject) jsonArray.get(i);
            /**
             * Verifico si es una pregunta, si lo es busco sus respuestas y elimino los objetos del
             * JSONArray para luego no hacer tantas verificaciones
             */
            if (jsonObjectQuestion.getString("tipo").equals("P")) {
                int num_question = Integer.parseInt(jsonObjectQuestion.getString("ide"));
                int id_question = Integer.parseInt(jsonObjectQuestion.getString("regId"));
                String text_question = jsonObjectQuestion.getString("Descripcion");
                String url_img_question = jsonObjectQuestion.getString("rutaImg");
                String argument_question  = jsonObjectQuestion.getString("argumento");
                //Elimino del array la pregunta porque ya tengo
                jsonArray.remove(i);
                i--;
                List<String> listAnswers = new ArrayList<>();
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObjectAnswer = (JSONObject) jsonArray.get(j);
                    if (num_question == Integer.parseInt(jsonObjectAnswer.getString("ide")) && jsonObjectAnswer.getString("tipo").equals("R")) {
                        int id_answer = Integer.parseInt(jsonObjectAnswer.getString("regId"));
                        String text_answer = jsonObjectAnswer.getString("Descripcion");
                        String url_img_answer = jsonObjectAnswer.getString("rutaImg");
                        String argument = jsonObjectAnswer.getString("argumento");
                        int is_correct = Integer.parseInt(jsonObjectAnswer.getString("Correcta"));
                        String answer = "{'idAnswer':'" + id_answer + "' ," +
                                "'text':'" + text_answer + "' ," +
                                "'isCorrect':'" + is_correct + "' ," +
                                "'urlImage':'" + url_img_answer + "' ," +
                                "'argument':'" + argument + "'}";
                        listAnswers.add(answer);
                        jsonArray.remove(j);
                        j--;
                    }
                }
                String answers = "[";
                for (int k = 0; k < listAnswers.size(); k++) {
                    answers += listAnswers.get(k);
                    if (k < (listAnswers.size() - 1)) {
                        answers += ",\n";
                    }
                }
                answers += "]\n";
                /**
                 * Cadena question con el formato deseado
                 */
                String question = ("{'idQuestion':'" + id_question + "', " +
                        "'text':'" + text_question + "', " +
                        "'urlImage':'" + url_img_question + "'," +
                        "'argumento':'" + argument_question + "'," +
                        "'answers':" + answers + "}");
                /**
                 * Aqui ya debe almacenarce en la DB
                 */
                qs.add(new Question(question));
            }
        }
        return qs;
    }
}
