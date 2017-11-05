package olimpiadas.sena.com.olimpiadasmath.control;

import android.media.MediaPlayer;
import android.util.Log;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.model.BonusTable;
import olimpiadas.sena.com.olimpiadasmath.model.Configuration;
import olimpiadas.sena.com.olimpiadasmath.model.Product;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.model.User;

/**
 * Created by defytek on 6/21/17.
 */

public class AppControl {

    public final String TAG = AppControl.class.toString();

    public interface InitComplete{
        public void initComplete(boolean result);
    }

    private static final AppControl ourInstance = new AppControl();

    public int language;
    public int numberOfQuestions = 5;
    public int[] answers;
    public boolean musik;
    public boolean efects;

    public boolean onChallenge = false;
    public boolean onPractice = false;

    public User currentUser;
    public int currentCoinsPool = 10;
    public int currentBet = 0;
    public int currentQuestion = 0;
    public long currentTime = 0;
    public int baseWinCoins = 10;

    //sonidos efectos app
    public MediaPlayer soundBackground,soundPositive,soundNegative,soundButton;
    public int soundButtonEfect = R.raw.puzzlefastwet;
    public int soundButtonPositive = R.raw.efectposit;
    public int soundButtonNegative = R.raw.efectneg;


    //ranking
    public int betCoins;
    public int initBetCoins;

    private boolean init = false;
    public boolean isLogged = false;
    public boolean isBackgroundPlaying = true;
    public boolean isPreview = false;
    public boolean previewUsed = false;
    public String currentActivity = "currentActivity";

    public static AppControl getInstance() {
        return ourInstance;
    }

    private AppControl() {

    }

    public boolean init(final InitComplete listener){

        init = true;
        answers = new  int[numberOfQuestions];
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if(realm.where(User.class).findAll().isEmpty()){
                    User user = new User("Juanito",305,30,150,8,1,30.0,"jhonny","");
                    realm.copyToRealm(user);
                    currentUser = user;
                }else{
                    currentUser = new User();
                    currentUser = realm.copyFromRealm(realm.where(User.class).findAll().first());
//                    currentUser = (User) realm.where(User.class).findAll().first();
                }
                Log.d("Usuario header", currentUser.toString());
                if (realm.where(BonusTable.class).findAll().isEmpty()) {


                    Log.d(TAG,"Creating BonusTables");
                    //float max, float min, float coin, float ticket, float exp, float score, int type
                    //Practice
                    BonusTable bonus1 = new BonusTable(1.1f, 0.9f, 2, 3, 3, 1.5f, 1);
                    BonusTable bonus2 = new BonusTable(0.89f, 0.8f,2 , 3, 3, 1.5f, 1);
                    BonusTable bonus3 = new BonusTable(0.79f, 0.6f, 1, 1, 1, 1f, 1);
                    BonusTable bonus4 = new BonusTable(0.59f, 0.4f, 0, 0, 0, 0f, 1);
                    BonusTable bonus5 = new BonusTable(0.38f, 0, -1, -1, 0, -1, 1);

                    //challenge
                    BonusTable bonus6 = new BonusTable(1.1f, 0.9f, 2, 3, 3, 1.5f, 2);
                    BonusTable bonus7 = new BonusTable(0.89f, 0.8f,2 , 3, 3, 1.5f, 2);
                    BonusTable bonus8 = new BonusTable(0.79f, 0.6f, 1, 1, 1, 1f, 2);
                    BonusTable bonus9 = new BonusTable(0.59f, 0.40f, 0, 0, 0, 0f, 2);
                    BonusTable bonus10 = new BonusTable(0.39f, 0, -1, -1, 0, -1, 2);




                    Log.d(TAG,"created BonusTables");
                    realm.copyToRealm(bonus1);
                    realm.copyToRealm(bonus2);
                    realm.copyToRealm(bonus3);
                    realm.copyToRealm(bonus4);
                    realm.copyToRealm(bonus5);
                    realm.copyToRealm(bonus6);
                    realm.copyToRealm(bonus7);
                    realm.copyToRealm(bonus8);
                    realm.copyToRealm(bonus9);
                    realm.copyToRealm(bonus10);

                    Log.d(TAG,"saved BonusTables");
                }

                if (realm.where(Question.class).findAll().isEmpty()) {


                    Log.d(TAG,"Creating Question Table");

                    String jsonText1 = ("{'text':' En una agencia de Empleos el 40% de los aspirantes son mujeres. De las mujeres que asisten a esta agencia el 30% son tecnólogas. Del total de asistentes a la agencia, ¿Qué porcentaje son mujeres que No son tecnólogas?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'12%' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'30%' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'28%' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'19%' ,'isCorrect':'0'}]\n" +
                            
                            "\t\t}}");
                    String jsonText2 = ("{'text':'En un centro de formación de Risaralda se tienen que el 20% de un grupo de  aprendices son técnicos, mientras que 1/9 de los aprendices son tecnólogos. Si sabemos que el total de sus libros está entre 50 y 100, ¿Cuál es el total de aprendices?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'50' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'56' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'90' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'63' ,'isCorrect':'0'}]\n" +
                            
                            "\t\t}}");
                    String jsonText3 = ("{'text':'¿Qué edad tendría Rodrigo en el 2011, si su edad en este año fue igual a la suma de los valores de las cifras del año de su nacimiento?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'26 años' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'21 años' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'20 años' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'19 años' ,'isCorrect':'0'}]\n" +
                            
                            "\t\t}}");
                    String jsonText4 = ("{'text':'El volumen de una bacteria se duplica cada minuto, al poner una bacteria en un vaso cilíndrico se llena totalmente en 61 minutos, ¿en cuántos minutos estará lleno un vaso que tiene la mitad del volumen inicial con el mismo tipo de células?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'31 min' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'60 min' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'28 min' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'29 min' ,'isCorrect':'0'}]\n" +
                            
                            "\t\t}}");
                    String jsonText5 = ("{'text':'En el Call Center del SENA tres Aprendices que sirven de operadores reciben llamadas cada 3, 5 y 9 minutos respectivamente, ¿Se quiere saber cuántas veces estarán simultáneamente hablando estos tres aprendices en un turno de 9 horas?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'12' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'15' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'11' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'13' ,'isCorrect':'1'}]\n" +
                            
                            "\t\t}}");
                    String jsonText6 = ("{'text':'En matemáticas se sabe que un número perfecto es aquel que al sumar sus divisores menores a el mismo (incluyendo el 1) se obtiene como resultado el mismo número; teniendo en cuenta esto, cuál de los siguientes números es NO perfecto.',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'98' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'28' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'6' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'496' ,'isCorrect':'0'}]\n" +
                            
                            "\t\t}}");
                    String jsonText7 = ("{'text':'En una bodega hay 100 bicicletas de dos marcas distintas M y P disponibles para vender, 40 bicicletas de la marca M y 60 bicicletas de la marca P.El 40 % de las bicicletas de marca M tienen un año de garantía, y las demás de la misma marca tienen 6 meses de garantía.\n" +
                            "Si un vendedor elige al azar una bicicleta para exhibirla, ¿Cuál es la probabilidad de que la bicicleta elegida sea de la marca P y tenga un año de garantía?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'10%' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'30%' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'20%' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'50%' ,'isCorrect':'0'}]\n" +
                            
                            "\t\t}}");
                    String jsonText8 = ("{'text':'Los 70 empleados de una empresa están divididos en clase A y clase B. La empresa paga una prima de $20.000 a los empleados de clase A y de $10.000 pesos a los de clase B. Si el pago total de la prima es de $1’200.000, entonces el número total de empleados de clase A es:\n',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'40' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'30' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'20' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'50' ,'isCorrect':'1'}]\n" +
                            
                            "\t\t}}");
                    String jsonText9 = ("{'text':'El volumen de una bacteria se duplica cada minuto, al poner una bacteria en un vaso cilíndrico se llena totalmente en 61 minutos, ¿en cuántos minutos estará lleno un vaso que tiene la mitad del volumen inicial con el mismo tipo de células?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'31 min' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'60 min' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'28 min' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'29 min' ,'isCorrect':'0'}]\n" +

                            "\t\t}}");
                    String jsonText10 = ("{'text':'En el Call Center del SENA tres Aprendices que sirven de operadores reciben llamadas cada 3, 5 y 9 minutos respectivamente, ¿Se quiere saber cuántas veces estarán simultáneamente hablando estos tres aprendices en un turno de 9 horas?',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'12' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'15' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'11' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'13' ,'isCorrect':'1'}]\n" +

                            "\t\t}}");
                    Question question1 = new Question(jsonText1);
                    Question question2 = new Question(jsonText2);
                    Question question3 = new Question(jsonText3);
                    Question question4 = new Question(jsonText4);
                    Question question5 = new Question(jsonText5);
                    //Question question6 = new Question(jsonText6);
                    //Question question7 = new Question(jsonText7);
                    //Question question8 = new Question(jsonText8);
                    //Question question9 = new Question(jsonText9);
                    //Question question10 = new Question(jsonText10);

                    Log.d(TAG,"created BonusTables");
                    realm.copyToRealm(question1);
                    realm.copyToRealm(question2);
                    realm.copyToRealm(question3);
                    realm.copyToRealm(question4);
                    realm.copyToRealm(question5);
                    //realm.copyToRealm(question6);
                    //realm.copyToRealm(question7);
                    //realm.copyToRealm(question8);
                    //realm.copyToRealm(question9);
                    //realm.copyToRealm(question10);

                    Log.d(TAG,"saved Question Table");
                }

                if (realm.where(User.class).findAll().isEmpty()) {
                    Log.d(TAG,"Creating User Table");

                    String user1 = (
                            "{'nickname':'rich4rd',"+
                            "'password':'1234',"+
                            "'score':'1322',"+
                            "'position':'7',"+
                            "'coins':'47',"+
                            "'tickets':'12',"+
                            "'experience':'300',"+
                            "'level':'10'}");

                    User user_uno = new User("rich4rd","1234",1322,7,47,12,10,2,"jhonny");
                    Log.d(TAG,"created User");
                    User manageduser = realm.copyToRealm(user_uno);
                    ourInstance.currentUser = realm.copyFromRealm(manageduser);

                }else{
                    ourInstance.currentUser = realm.copyFromRealm(realm.where(User.class).findFirst());
                }

                if(realm.where(Product.class).findAll().isEmpty()){

                    Product pdt1 = new Product(R.drawable.marco18,"Dragon\nblanco",80,"1",Product.FOR_BUY,"marco18",1);
                    Product pdt4 = new Product(R.drawable.marco8,"Dragon\nverde",100,"0",Product.FOR_BUY,"marco8",1);
                    Product pdt2 = new Product(R.drawable.marco,"Dragon\nazul",20,"20",Product.FOR_BUY,"marco",1);
                    Product pdt3 = new Product(R.drawable.marco2,"Dragon\nrojo",250,"30",Product.FOR_BUY,"marco2",1);
                    Product pdt5 = new Product(R.drawable.marco11,"Dragon\nnaranja",150,"25",Product.FOR_BUY,"marco11",1);

                    Product pdt6 = new Product(R.drawable.pocionazul,"Pocion\nTime",150,"10",Product.FOR_BUY,"pocionazul",2);
                    Product pdt7 = new Product(R.drawable.pociondragon,"Pocion\nMonedas",150,"10",Product.FOR_BUY,"pociondragon",2);
                    Product pdt8 = new Product(R.drawable.pocionroja,"Pocion\nSalvavidas",150,"10",Product.FOR_BUY,"pocionroja",2);
                    Product pdt9 = new Product(R.drawable.pocionverde,"Pocion\nVida",150,"10",Product.FOR_BUY,"pocionverde",2);

                    realm.copyToRealm(pdt1);
                    realm.copyToRealm(pdt2);
                    realm.copyToRealm(pdt3);
                    realm.copyToRealm(pdt4);
                    realm.copyToRealm(pdt5);
                    realm.copyToRealm(pdt6);
                    realm.copyToRealm(pdt7);
                    realm.copyToRealm(pdt8);
                    realm.copyToRealm(pdt9);

                }

                Configuration config = realm.where(Configuration.class).equalTo("key","isLogged").findFirst();
                if(config != null){
                    Log.d(TAG,"Configuration isLogged  founded = " + config.getValue());
                    isLogged = config.getValue();
                }else{
                    Log.d(TAG,"Configuration isLogged not founded");
                    config = new Configuration("isLogged",false);
                    realm.copyToRealm(config);
                }
                Configuration configSound = realm.where(Configuration.class).equalTo("key","isBackgroundPlaying").findFirst();
                if(configSound != null){
                    Log.d(TAG,"Configuration isBackgroundPlaying founded " + configSound.getValue());
                    //sound founded
                    isBackgroundPlaying = configSound.getValue();
                }else{
                    Log.d(TAG,"Configuration isBackgroundPlaying not founded");
                    configSound = new Configuration("isBackgroundPlaying",true);//terminarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
                    realm.copyToRealm(configSound);
                }


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"Transaction Success");

                listener.initComplete(true);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG,"Transaction Error");
                listener.initComplete(false);
            }
        });

        return true;

    }

}
