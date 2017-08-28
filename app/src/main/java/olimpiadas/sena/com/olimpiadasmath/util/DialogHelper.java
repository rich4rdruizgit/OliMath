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
import olimpiadas.sena.com.olimpiadasmath.activities.library.LibraryActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.settings.SettingsActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;


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
                Intent intent = new Intent(context.getApplicationContext(), SettingsActivity.class);
                context.startActivity(intent);
            }
        });

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void showChallengeDialog(final Context context) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.challenge_view);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_show_more_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
                Intent intChallenge = new Intent(context.getApplicationContext(), TestActivity.class);
                intChallenge.putExtra("type",2);
                AppControl.getInstance().currentUser.addTickets(-2);
                context.startActivity(intChallenge);
                ((MainActivity)context).finish();

            }
        });

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }
    public static void ConfimrExitDialog(final Context context) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_confirm_exit);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_show_more_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).finish();
                System.exit(0);

            }
        });

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void ConfimrFinishTestDialog(final Context context,String msg) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_confirm_exit);
        ((TextView)mProgressDialog.getWindow().findViewById(R.id.txt_msg_dialog)).setText(msg);

        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_show_more_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TestActivity)context).finish();
                context.startActivity(new Intent(context, MainActivity.class));


            }
        });

        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

}

