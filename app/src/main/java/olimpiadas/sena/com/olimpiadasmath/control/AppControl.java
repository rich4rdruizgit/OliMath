package olimpiadas.sena.com.olimpiadasmath.control;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import olimpiadas.sena.com.olimpiadasmath.model.BonusTable;
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
    public int numberOfQuestions;
    public boolean musik;
    public boolean efects;


    public User currentUser;

    //ranking
    public int betCoins;
    public int initBetCoins;

    private boolean init = false;

    public static AppControl getInstance() {
        return ourInstance;
    }

    private AppControl() {

    }

    public boolean init(final InitComplete listener){

        init = true;

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if (realm.where(BonusTable.class).findAll().isEmpty()) {


                    Log.d(TAG,"Creating BonusTables");

                    //Practice
                    BonusTable bonus1 = new BonusTable(1, 0.9f, 2, 3, 3, 1.5f, 1);
                    BonusTable bonus2 = new BonusTable(0.8f, 0.89f,2 , 3, 3, 1.5f, 1);
                    BonusTable bonus3 = new BonusTable(0.6f, 0.79f, 1, 1, 1, 1f, 1);
                    BonusTable bonus4 = new BonusTable(0.40f, 0.59f, 0, 0, 0, 0f, 1);
                    BonusTable bonus5 = new BonusTable(0, 0.39f, -1, -1, 0, 1.5f, 1);

                    //challenge
                    BonusTable bonus6 = new BonusTable(1, 0.9f, 2, 3, 3, 1.5f, 2);
                    BonusTable bonus7 = new BonusTable(0.8f, 0.89f,2 , 3, 3, 1.5f, 2);
                    BonusTable bonus8 = new BonusTable(0.6f, 0.79f, 1, 1, 1, 1f, 2);
                    BonusTable bonus9 = new BonusTable(0.40f, 0.59f, 0, 0, 0, 0f, 2);
                    BonusTable bonus10 = new BonusTable(0, 0.39f, -1, -1, 0, 1.5f, 2);




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

                    String jsonText1 = ("{'text':' x= 10 + 3 * 4 ',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'Respuesta 22' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'Respuesta 52' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 17' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 20' ,'isCorrect':'0'}]\n" +
                            "\t\t\t\n" +
                            "\t\t}}");
                    String jsonText2 = ("{'text':'Cuanto es 2 +2 ....',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'Respuesta 4' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'Respuesta 2' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 1' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 8' ,'isCorrect':'0'}]\n" +
                            "\t\t\t\n" +
                            "\t\t}}");
                    String jsonText3 = ("{'text':'Quien se comió el queso de...Maria del charco.',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'Respuesta Jhon Yanguas' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta Harold Muñoz' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta Andres Muñoz' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'Respuesta Andres Chapid' ,'isCorrect':'0'}]\n" +
                            "\t\t\t\n" +
                            "\t\t}}");
                    String jsonText4 = ("{'text':'Me comi una manzana....',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'Respuesta 1' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 2' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 3' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'Respuesta 4' ,'isCorrect':'0'}]\n" +
                            "\t\t\t\n" +
                            "\t\t}}");
                    String jsonText5 = ("{'text':'Me comi una manzana....',\n" +
                            "\t\t'answers':[\n" +
                            "\t\t{'text':'Respuesta 1' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 2' ,'isCorrect':'0'},\n" +
                            "\t\t{'text':'Respuesta 3' ,'isCorrect':'1'},\n" +
                            "\t\t{'text':'Respuesta 4' ,'isCorrect':'0'}]\n" +
                            "\t\t\t\n" +
                            "\t\t}}");
                    Question question1 = new Question(jsonText1);
                    Question question2 = new Question(jsonText2);
                    Question question3 = new Question(jsonText3);
                    Question question4 = new Question(jsonText4);
                    Question question5 = new Question(jsonText5);

                    Log.d(TAG,"created BonusTables");
                    realm.copyToRealm(question1);
                    realm.copyToRealm(question2);
                    realm.copyToRealm(question3);
                    realm.copyToRealm(question4);
                    realm.copyToRealm(question5);

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

                    User user_uno = new User("rich4rd","1234",1322,7,47,12,10,2);
                    Log.d(TAG,"created User");
                    User manageduser = realm.copyToRealm(user_uno);
                    ourInstance.currentUser = realm.copyFromRealm(manageduser);


                }else{
                    ourInstance.currentUser = realm.copyFromRealm(realm.where(User.class).findFirst());

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
