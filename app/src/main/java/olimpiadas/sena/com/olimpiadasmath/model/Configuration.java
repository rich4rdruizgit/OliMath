package olimpiadas.sena.com.olimpiadasmath.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by defytek on 8/27/17.
 */

public class Configuration extends RealmObject {

    @PrimaryKey
    private String key;
    private Boolean value;

    public Configuration() {
    }

    public Configuration(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
