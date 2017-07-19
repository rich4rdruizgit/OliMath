package olimpiadas.sena.com.olimpiadasmath.model;

/**
 * Created by jyanguas on 29/06/2017.
 */

public class Topic {

    String name;
    int image;


    public Topic(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
