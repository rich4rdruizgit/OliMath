package olimpiadas.sena.com.olimpiadasmath.util.error;

/**
 * Created by defytek on 6/14/17.
 */

public class Message {

    String code,rawMessage,message;

    public Message(String code, String rawMessage, String message) {
        this.code = code;
        this.rawMessage = rawMessage;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String toString(){
        StringBuffer buf = new StringBuffer();
        buf.append("{");
        buf.append("'code': '" + code + "',");
        buf.append("'rawMessage': '" + rawMessage + "',");
        buf.append("'message': '" + message + "'");
        buf.append("}");

        return   buf.toString();

    }
}
