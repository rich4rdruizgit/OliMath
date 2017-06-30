package olimpiadas.sena.com.olimpiadasmath.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;


/**
 * Created by defytek on 4/20/17.
 */

public class DialogHelper {


    static Dialog mProgressDialog;

    public static void showBusyDialog(Context context,String message) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_processing);
        ((TextView)mProgressDialog.getWindow().findViewById(R.id.tv_dialogProcessInfo)).setText(message);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    static public void hideBusyDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public static void showTipDialog(final Context context) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.tip_view);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_show_more_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.setCancelable(true);
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

}
