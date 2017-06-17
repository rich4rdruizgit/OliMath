package olimpiadas.sena.com.olimpiadasmath.model;

/**
 * Created by rich4 on 16/06/2017.
 */

public class Product {
    private int urlImg;
    protected String name;
    protected String price;
    protected String constraint; // Restriccion del nivel para acceder al item


    public Product(int urlImg, String name, String price, String constraint) {
        this.urlImg = urlImg;
        this.name = name;
        this.price = price;
        this.constraint = constraint;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    @Override
    public String toString() {
        return "Product{" +
                "urlImg=" + urlImg +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", constraint='" + constraint + '\'' +
                '}';
    }
}
