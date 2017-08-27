package olimpiadas.sena.com.olimpiadasmath.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by rich4 on 16/06/2017.
 */

public class Product extends RealmObject {
    @Ignore
    public static final int FOR_BUY  = 1; // para comprar
    @Ignore
    public static final int BOUGTH  = 2; // comprado
    @Ignore
    public static final int USED  = 3; //usado


    private int urlImg;
    protected String name;
    protected int price;
    protected String constraint; // Restriccion del nivel para acceder al item



    protected String sourceName;
    protected int state;


    public Product(int urlImg, String name, int price, String constraint, int state, String sourceName) {
        this.urlImg = urlImg;
        this.name = name;
        this.price = price;
        this.constraint = constraint;
        this.state = state;
        this.sourceName = sourceName;
    }

    public Product() {
    }

    public int getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(int urlImg) {
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
