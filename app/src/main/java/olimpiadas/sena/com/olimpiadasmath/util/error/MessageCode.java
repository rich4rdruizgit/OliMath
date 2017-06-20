package olimpiadas.sena.com.olimpiadasmath.util.error;



/**
 * Created by defytek on 6/14/17.
 */

public enum MessageCode  {

    FACE_PRINT_DEL_ERROR ("DB001"),
    DEFAUTL_ERROR("UK001");

    private String code;

    MessageCode(String code) {
        this.code = code;
    }




    public String getCode() {
        return code;
    }
}
