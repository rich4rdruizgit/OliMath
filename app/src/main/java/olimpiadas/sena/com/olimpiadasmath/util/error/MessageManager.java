package olimpiadas.sena.com.olimpiadasmath.util.error;

import android.content.Context;
import android.util.Log;



/**
 * Created by defytek on 6/14/17.
 */

public class MessageManager {

    private final String TAG = MessageManager.class.toString();
    Context context;



    private static final MessageManager ourInstance = new MessageManager();

    public static MessageManager getInstance() {
        return ourInstance;
    }

    private MessageManager() {
    }


    public void setContext(Context context) {
        this.context = context;
    }


    public Message getMessage(MessageCode messageCode){

        Log.d(TAG,"messageCode " + messageCode.toString());
        String rawMessage = context.getResources().getString(context.getResources().getIdentifier(messageCode.toString() + "_RAW", "string", context.getPackageName()));
        String message = context.getResources().getString(context.getResources().getIdentifier(messageCode.toString(), "string", context.getPackageName()));

        Message msg = new Message(messageCode.getCode(),rawMessage,message);
        return msg;
    }
}
