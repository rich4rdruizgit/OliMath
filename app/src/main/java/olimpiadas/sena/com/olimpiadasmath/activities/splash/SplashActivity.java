package olimpiadas.sena.com.olimpiadasmath.activities.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.session.LoginActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity implements AppControl.InitComplete, WebConnectionManager.WebConnectionManagerListener {

    private AppControl appControl;
    private boolean loading = true;
    private WebConnectionManager webConnectionManager;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        appControl = AppControl.getInstance();
        appControl.currentActivity = SplashActivity.class.getSimpleName();
        webConnectionManager = WebConnectionManager.getWebConnectionManager();
        webConnectionManager.setWebConnectionManagerListener(this);
        webConnectionManager.mostrarPreguntasAleatorias();
        appControl.init(SplashActivity.this);
        startAnimations();

    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (loading) {
                        sleep(5000);
                    }


                    if (AppControl.getInstance().isLogged) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }


                } catch (InterruptedException e) {
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

    @Override
    public void initComplete(boolean result) {

        loading = false;


        //todo manejar error de cargado


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void webRequestComplete(WebConnectionManager.Response response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response.getData());
        List<String> qs = new ArrayList<>();
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
                                    "'answers':" + answers + "}");
                /**
                 * Aqui ya debe almacenarce en la DB
                 */
                qs.add(question);
            }
        }

        for (String q: qs) {
            Log.e("Question", q);
        }
    }
}
