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


import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.session.LoginActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity implements AppControl.InitComplete,
        WebConnectionManager.WebConnectionManagerListener {

    private AppControl appControl;
    private boolean loading = true;
    Realm realm;
    WebConnectionManager webConnectionManager;

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

        realm = Realm.getDefaultInstance();

        appControl.init(SplashActivity.this);

        webConnectionManager = WebConnectionManager.getWebConnectionManager();
        webConnectionManager.setWebConnectionManagerListener(this);
        webConnectionManager.getRandomQuestions();
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
        if (response.getOperationType() == WebConnectionManager.OperationType.GET_QUESTIONS) {
            if (response.getStatus() == WebConnectionManager.Response.SUCCESS) {
                JSONArray jsonArray = new JSONArray(response.getData());
//        JSONArray jsonArray = new JSONArray(data());
                List<Question> qs = Question.JsonArrayToList(jsonArray);

                for (Question q : qs) {
                    Log.e("Question", q.getJsonObject());
                }
                LoadQuestionsFromWebService(qs);
            }
        }
    }


    public void LoadQuestionsFromWebService(final List<Question> questionList) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Question> questions = realm.where(Question.class).findAll();
                questions.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(questionList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                for (int i = 1; i < questionList.size(); i++) {
                    Log.d("JSON", questionList.get(i).toString());
                }
                Log.d("COPIA A REALM", "COPIADOS");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("COPIA A REALM", " NO COPIADOS");
            }
        });
    }

    public String data() {
        String q = "[{\"ide\":1,\"tipo\":\"P\",\"regId\":63,\"Descripcion\":\"as\",\"rutaImg\":\"C:/Users/Personal/Documents/My Web Sites/MathWeb/Vista/archivos/preguntas/puertos.jpg\",\"Correcta\":0,\"argumento\":\"as\"},\n" +
                "{\"ide\":1,\"tipo\":\"R\",\"regId\":134,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":1,\"tipo\":\"R\",\"regId\":133,\"Descripcion\":\"as\",\"rutaImg\":\"C:/Users/Personal/Documents/My Web Sites/MathWeb/Vista/archivos/respuestas/FUP.jpg\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":1,\"tipo\":\"R\",\"regId\":131,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":1,\"tipo\":\"R\",\"regId\":135,\"Descripcion\":\"NR\",\"rutaImg\":\"NA\",\"Correcta\":2,\"argumento\":\"NA\"},\n" +
                "{\"ide\":1,\"tipo\":\"R\",\"regId\":132,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":2,\"tipo\":\"P\",\"regId\":8,\"Descripcion\":\"En un centro de formación de Risaralda se tienen que el 20% de un grupo de aprendices son tí©cnicos, mientras que 1/9 de los aprendices son tecnólogos. Si sabemos que el total de sus libros está entre 50 y 100, ¿Cuál es el total de aprendices?\",\"rutaImg\":\"ruta\",\"Correcta\":0,\"argumento\":\"El porcentaje es una forma dé comparará cantidades, es una unidad de referencia que relaciona uná magnitud (una cifra o cantidád) con él todo que le corresponde (el todo es siempre el 100), considerando como unidad la centá©sima parte del todo.\"},\n" +
                "{\"ide\":2,\"tipo\":\"R\",\"regId\":40,\"Descripcion\":\"63\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":2,\"tipo\":\"R\",\"regId\":41,\"Descripcion\":\"90\",\"rutaImg\":\"rut\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":2,\"tipo\":\"R\",\"regId\":39,\"Descripcion\":\"56\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":2,\"tipo\":\"R\",\"regId\":38,\"Descripcion\":\"50\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":3,\"tipo\":\"P\",\"regId\":64,\"Descripcion\":\"qqq2\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"qqq\"},\n" +
                "{\"ide\":3,\"tipo\":\"R\",\"regId\":137,\"Descripcion\":\"qqq\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":3,\"tipo\":\"R\",\"regId\":139,\"Descripcion\":\"qqq\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":3,\"tipo\":\"R\",\"regId\":138,\"Descripcion\":\"qqq\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":3,\"tipo\":\"R\",\"regId\":136,\"Descripcion\":\"qqq\",\"rutaImg\":\"NA\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":4,\"tipo\":\"P\",\"regId\":11,\"Descripcion\":\"En el Call Center del SENA tres Aprendices que sirven de operadores reciben llamadas cada 3, 5 y 9 minutos respectivamente, ¿Se quiere saber cuántas veces estarán simultáneamente hablando estos tres aprendices en un turno de 9 horas?\",\"rutaImg\":\"ruta\",\"Correcta\":0,\"argumento\":\"Estas actividades están sugeridas para que el niá±o resuelva problemas de orden lógico matemático y puede realizarlo en el aula con el maestro o en el hogar con adultos o padres: Sugerimos usar el má©todo de trabajo de los â€œbloques lógicós.\"},{\"ide\":4,\"tipo\":\"R\",\"regId\":58,\"Descripcion\":\"13\",\"rutaImg\":\"rut\",\"Correcta\":1,\"argumento\":\"NA\"},{\"ide\":4,\"tipo\":\"R\",\"regId\":57,\"Descripcion\":\"15\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},{\"ide\":5,\"tipo\":\"R\",\"regId\":63,\"Descripcion\":\"20 puntos\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":5,\"tipo\":\"R\",\"regId\":62,\"Descripcion\":\" 18 puntos\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":5,\"tipo\":\"R\",\"regId\":61,\"Descripcion\":\" 15 puntos\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":6,\"tipo\":\"P\",\"regId\":4,\"Descripcion\":\"Se les preguntó a 32 estudiantes de un colegio por el número de horas que dedican a ver televisión diariamente. Los resultados aparecen en la siguiente lista. 0, 2, 4, 2, 2, 2, 2, 3, 3, 4, 0, 2, 4, 2, 2, 4, 0, 4, 2, 2, 4, 2, 2, 3, 3, 2, 2, 2, 2, 4, 4, 0 ¿Cuál es la moda de esta lista?\",\"rutaImg\":\"ruta\",\"Correcta\":0,\"argumento\":\"Para responder acertadamente este tipo de preguntas, el estudiante debe conocer las medidas de tendencia central de manera formal, En este caso, debe identificar que la frecuencia del dato 2 horas de dedicación para ver televisión tiene una frecuencia de 16, que corresponde a la mitad de los datos recolectados, por tanto, cualquier otro dato tendrá una frecuencia menor; entonces, la moda es 2.\"},{\"ide\":6,\"tipo\":\"R\",\"regId\":16,\"Descripcion\":\"0\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":6,\"tipo\":\"R\",\"regId\":17,\"Descripcion\":\"2\",\"rutaImg\":\"rut\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":6,\"tipo\":\"R\",\"regId\":19,\"Descripcion\":\"4\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":6,\"tipo\":\"R\",\"regId\":20,\"Descripcion\":\"NR\",\"rutaImg\":\"rut\",\"Correcta\":2,\"argumento\":\"NA\"},\n" +
                "{\"ide\":6,\"tipo\":\"R\",\"regId\":18,\"Descripcion\":\"3\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":7,\"tipo\":\"P\",\"regId\":10,\"Descripcion\":\"¿Qué edad tendría Rodrigo en el 2011, si su edad en este año fue igual a la suma de los valores de las cifras del año de su nacimiento?\",\"rutaImg\":\"ruta\",\"Correcta\":0,\"argumento\":\"Estas actividades están sugeridas para que el niá±o resuelva problemas de orden lógico matemático y puede realizarlo en el aula con el maestro o en el hogar con adultos o padres: Sugerimos usar el má©todo de trabajo de los â€œbloques lógicós.\"},{\"ide\":7,\"tipo\":\"R\",\"regId\":55,\"Descripcion\":\"NR\",\"rutaImg\":\"rut\",\"Correcta\":2,\"argumento\":\"NA\"},{\"ide\":7,\"tipo\":\"R\",\"regId\":51,\"Descripcion\":\" 21 años \",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":7,\"tipo\":\"R\",\"regId\":54,\"Descripcion\":\" 20 años\",\"rutaImg\":\"rut\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":7,\"tipo\":\"R\",\"regId\":52,\"Descripcion\":\" 18 años \",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":7,\"tipo\":\"R\",\"regId\":53,\"Descripcion\":\" 19 años \",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":8,\"tipo\":\"P\",\"regId\":66,\"Descripcion\":\"as2\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"as\"},\n" +
                "{\"ide\":8,\"tipo\":\"R\",\"regId\":148,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":8,\"tipo\":\"R\",\"regId\":146,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":8,\"tipo\":\"R\",\"regId\":150,\"Descripcion\":\"NR\",\"rutaImg\":\"NA\",\"Correcta\":2,\"argumento\":\"NA\"},\n" +
                "{\"ide\":8,\"tipo\":\"R\",\"regId\":144,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":8,\"tipo\":\"R\",\"regId\":142,\"Descripcion\":\"as\",\"rutaImg\":\"NA\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":9,\"tipo\":\"P\",\"regId\":2,\"Descripcion\":\"Cuando en un grupo cada persona abraza a otra del grupo una sola vez, el número total de abrazos, a, se calcula mediante la expresión, donde n es el número de personas en el grupo. ¿Cuál es el valor de a para un grupo de 5 personas?\",\"rutaImg\":\"ruta\",\"Correcta\":0,\"argumento\":\"Para responder acertadamente este tipo de preguntas, el estudiante debe estar en capacidad de evaluar expresiones algebraicas. En este caso, debe identificar la cantidad de personas del grupo, 5, y asignar a n este valor, obtener el número de abrazos, y, evaluando n=5, en la expresión.\"},{\"ide\":9,\"tipo\":\"R\",\"regId\":7,\"Descripcion\":\"5\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},{\"ide\":9,\"tipo\":\"R\",\"regId\":10,\"Descripcion\":\"NR\",\"rutaImg\":\"rut\",\"Correcta\":2,\"argumento\":\"NA\"},\n" +
                "{\"ide\":9,\"tipo\":\"R\",\"regId\":8,\"Descripcion\":\"10\",\"rutaImg\":\"rut\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":9,\"tipo\":\"R\",\"regId\":6,\"Descripcion\":\"3\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":9,\"tipo\":\"R\",\"regId\":9,\"Descripcion\":\"15\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":10,\"tipo\":\"P\",\"regId\":7,\"Descripcion\":\"En la figura mostrada ABCD y DBEF son rectángulos. ¿Cuál es el área de DBEF?\",\"rutaImg\":\"ruta\",\"Correcta\":0,\"argumento\":\"él áreá dél rectánguló se calcula a partir de los dos lados diferentes (á ý b). Es el producto de los dos lados contiguos dél rectángulo.\"},{\"ide\":10,\"tipo\":\"R\",\"regId\":34,\"Descripcion\":\" 12 cm2\",\"rutaImg\":\"rut\",\"Correcta\":1,\"argumento\":\"NA\"},\n" +
                "{\"ide\":10,\"tipo\":\"R\",\"regId\":32,\"Descripcion\":\"10 cm2 \",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"},\n" +
                "{\"ide\":10,\"tipo\":\"R\",\"regId\":33,\"Descripcion\":\" 13 cm2\",\"rutaImg\":\"rut\",\"Correcta\":0,\"argumento\":\"NA\"}]";

//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(questionList);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//
//                for(int i = 1 ; i < questionList.size();i++){
//                    Log.d("JSON",questionList.get(i).toString());
//                }
//                Log.d("COPIA A REALM","COPIADOS");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                Log.d("COPIA A REALM"," NO COPIADOS");
//            }
//        });
//
//        for(int  i = 0 ; i < jsonArray.length(); i++){
//            Log.d("Questions LOad", questionList.get(i).toString());
//        }
        return q;
    }
}
