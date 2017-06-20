package olimpiadas.sena.com.olimpiadasmath.util.webConManager;

import android.content.Context;

/**
 * Created by jennyfer on 12/23/2016.
 */

public class BiometricConfig
{

    Context context;
    public String URL="url";
    public String CONFIG="config";
    public String ID="id";
    public String VOICE_BIOMETRIC_TYPE="voice";
    public static String FACIAL_BIOMETRIC_TYPE="facial";
    public String FINGER_BIOMETRIC_TYPE="finger";

    public BiometricConfig(Context context,String biometric_type) {
        if (biometric_type.equals(VOICE_BIOMETRIC_TYPE)){
            this.context = context;
            URL = "https://52.14.45.37/kivoxWeb/rest/authentication/";
            CONFIG = "es_m_m_";
            ID = "546e510a424ed";
        }else if (biometric_type.equals(FACIAL_BIOMETRIC_TYPE)){
            this.context = context;
            URL = "https://52.14.45.37/kivoxWeb/rest/face/";
            CONFIG = "face_cog";
            ID = "546e510a424ed";
        }else if (biometric_type.equals(FINGER_BIOMETRIC_TYPE)){
            this.context = context;
            URL = "https://52.14.45.37/kivoxWeb/rest/finger/";
            CONFIG = "finger_innovatrics";
            ID = "546e510a424ed";
        }
    }
}