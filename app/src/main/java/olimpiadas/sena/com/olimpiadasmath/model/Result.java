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

    // numero de preguntas, num de resp correctas, num resp NR, num resp incorrectas, tiempo global, tiempo en test, monedas ganadas ,monedas apostadas, id user




}
