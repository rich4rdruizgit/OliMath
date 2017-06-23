package olimpiadas.sena.com.olimpiadasmath.control;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import olimpiadas.sena.com.olimpiadasmath.model.BonusTable;
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
