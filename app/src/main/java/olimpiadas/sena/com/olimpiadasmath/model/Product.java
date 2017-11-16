package olimpiadas.sena.com.olimpiadasmath.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by rich4 on 16/06/2017.
 */

public class Product extends RealmObject {
    @Ignore
    public static final int FOR_BUY = 0; // para comprar
    @Ignore
    public static final int BOUGTH = 1; // comprado
    @Ignore
    public static final int USED = 2; //usado
    @Ignore
    public static final int POTION = 2; //usado
    @Ignore
    public static final int AVATAR = 1; //usado


    private String urlImg;
    protected String name;
    protected int price;
    protected String constraint; // Restriccion del nivel para acceder al item
    protected String sourceName;
    protected int buy;
    protected int state;
    protected int type;


    public Product(String urlImg, String name, int price, String constraint, int state, String sourceName, int type, int buy) {
        this.urlImg = urlImg;
        this.name = name;
        this.price = price;
        this.constraint = constraint;
        this.state = state;
        this.sourceName = sourceName;
        this.type = type;
        this.buy = buy;
    }

    public Product() {
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    @Override
    public String toString() {
        return "Product{" +
                "urlImg=" + urlImg +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", constraint='" + constraint + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", state=" + state +
                '}';
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
