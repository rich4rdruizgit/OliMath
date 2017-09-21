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
import olimpiadas.sena.com.olimpiadasmath.activities.practice.PracticeActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.settings.SettingsActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;


/**
 * Created by defytek on 4/20/17.
 */

public class DialogHelper {

    public interface DialogHelperListener{
        public void dialogEnd(boolean result);
    }
    public static int BUY = 1;
    public static int NO_BUY = 2;

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
                ((MainActivity)context).finishAffinity();
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
                ((PracticeActivity)context).finish();
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

    public static void ConfimrBuyDialog(final Context context,String msg,int type, final DialogHelperListener dialogHelperListener) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_confirm_buy);
        ((TextView)mProgressDialog.getWindow().findViewById(R.id.txt_msg_dialog)).setText(msg);

        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        Button ok = ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_show_more_tip));
        Button cancel = ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            dialogHelperListener.dialogEnd(true);
                mProgressDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });

        if(type == NO_BUY){
            ok.setVisibility(View.GONE);
            cancel.setText("Aceptar");
        }

        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void FeedbackDialog(final Context context, String myanswer, String theanswer , String feedback, final FeedbackDialogListener listener) {


        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_retroalimentacion);
        ((TextView)mProgressDialog.getWindow().findViewById(R.id.txt_myanswer_retro)).setText(myanswer);
        ((TextView)mProgressDialog.getWindow().findViewById(R.id.txt_theanswer_retro)).setText(theanswer);
        ((TextView)mProgressDialog.getWindow().findViewById(R.id.txt_justification)).setText(feedback);

        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_retro_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
                listener.closeFeedBackDialog();
            }
        });

        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }
    public interface FeedbackDialogListener{
        void closeFeedBackDialog();
    }
}

