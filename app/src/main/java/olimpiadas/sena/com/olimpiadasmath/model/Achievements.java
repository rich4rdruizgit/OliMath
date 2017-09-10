package olimpiadas.sena.com.olimpiadasmath.model;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rich4 on 10/09/2017.
 */

public class Achievements extends RealmObject {
    @Ignore
    private final String TAG = Achievements.class.toString();


    @PrimaryKey
    private String idAchievement;

    private String nameAchievement;
    private String levelAchievement;
    private String descriptionAchievement;
    private String stateAchievement;
    private String rewardMoney;
    public Achievements() {
    }

    public Achievements(String nameAchievement, String levelAchievement, String descriptionAchievement, String stateAchievement, String rewardMoney) {
        this.rewardMoney = rewardMoney;
        this.idAchievement = UUID.randomUUID().toString();
        this.nameAchievement = nameAchievement;
        this.levelAchievement = levelAchievement;
        this.descriptionAchievement = descriptionAchievement;
        this.stateAchievement = stateAchievement;
    }

    public String getIdAchievement() {
        return idAchievement;
    }

    public void setIdAchievement(String idAchievement) {
        this.idAchievement = idAchievement;
    }

    public String getNameAchievement() {
        return nameAchievement;
    }

    public void setNameAchievement(String nameAchievement) {
        this.nameAchievement = nameAchievement;
    }

    public String getLevelAchievement() {
        return levelAchievement;
    }

    public void setLevelAchievement(String levelAchievement) {
        this.levelAchievement = levelAchievement;
    }

    public String getDescriptionAchievement() {
        return descriptionAchievement;
    }

    public void setDescriptionAchievement(String descriptionAchievement) {
        this.descriptionAchievement = descriptionAchievement;
    }

    public String getStateAchievement() {
        return stateAchievement;
    }

    public void setStateAchievement(String stateAchievement) {
        this.stateAchievement = stateAchievement;
    }

    public String getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney) {
        this.rewardMoney = rewardMoney;
    }
}
