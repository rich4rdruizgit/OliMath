package olimpiadas.sena.com.olimpiadasmath.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.challenge.ChallengeActivity;
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

    public interface DialogHelperListener {
        public void dialogEnd(boolean result);
    }
    public static int BUY = 1;
    public static int NO_BUY = 2;

    static Dialog mProgressDialog;

    public static void showBusyDialog(Context context, String message) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_processing);
        ((TextView) mProgressDialog.getWindow().findViewById(R.id.tv_dialogProcessInfo)).setText(message);
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

        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_dialog_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SettingsActivity.class);
                context.startActivity(intent);
            }
        });

        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void showCopyRightDialog(final Context context) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.copyright_layout);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void showNoCoins(final Context context) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.layout_no_coins);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        ((Button)mProgressDialog.getWindow().findViewById(R.id.btn_exit_no_coin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((AppCompatActivity)context).finish();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }







    public static void showChallengePracticeDialog(final Context context, int type) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.challenge_view);
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        if (type == 2) {
            String titleDialog = context.getResources().getString(R.string.title_dialog_challenge);
            ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_tittle_tip)).setText(titleDialog);
            String infoDialog = context.getResources().getString(R.string.text_dialog_info_challenge);
            ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_argument_tip_one)).setText(infoDialog);
            String[] array = context.getResources().getStringArray(R.array.array_advices_challenge);
            String randomStr = array[new Random().nextInt(array.length)];
            ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_dialog_advices)).setText(randomStr);

            ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_dialog_continue)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProgressDialog.dismiss();
                    Intent intChallenge = new Intent(context.getApplicationContext(), ChallengeActivity.class);
                    intChallenge.putExtra("type", 2);
                    AppControl.getInstance().currentUser.addTickets(-2);
                    context.startActivity(intChallenge);
                    ((MainActivity) context).finish();

                }
            });


        } else {
            if (type == 1) {
                String titleDialog = context.getResources().getString(R.string.title_dialog_practice);
                ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_tittle_tip)).setText(titleDialog);
                String infoDialog = context.getResources().getString(R.string.text_dialog_info_practice);
                ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_argument_tip_one)).setText(infoDialog);
                String[] array = context.getResources().getStringArray(R.array.array_advices_practice);
                String randomStr = array[new Random().nextInt(array.length)];
                ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_dialog_advices)).setText(randomStr);

                ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_dialog_continue)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mProgressDialog.dismiss();
                        Intent intChallenge = new Intent(context.getApplicationContext(), PracticeActivity.class);
                        context.startActivity(intChallenge);
                        ((MainActivity) context).finish();

                    }
                });
            }
        }
        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
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

        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_dialog_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).finishAffinity();
                ((MainActivity) context).finish();


            }
        });

        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void ConfimrFinishTestDialog(final Context context, String msg) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_confirm_exit);
        ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_msg_dialog)).setText(msg);

        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_dialog_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
                ((AppCompatActivity)context).finish();
                context.startActivity(new Intent(context, MainActivity.class));


            }
        });

        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void ConfimrBuyDialog(final Context context, String msg, int type, final DialogHelperListener dialogHelperListener) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_confirm_buy);
        ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_msg_dialog)).setText(msg);

        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        Button ok = ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_dialog_continue));
        Button cancel = ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_exit_tip));
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

        if (type == NO_BUY) {
            ok.setVisibility(View.GONE);
            cancel.setText("Aceptar");
        }

        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void FeedbackDialog(final Context context, String myanswer, String theanswer, String feedback, final FeedbackDialogListener listener) {


        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.dialog_retroalimentacion);
        ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_myanswer_retro)).setText(myanswer);
        ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_theanswer_retro)).setText(theanswer);
        ((TextView) mProgressDialog.getWindow().findViewById(R.id.txt_justification)).setText(feedback);

        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        ((Button) mProgressDialog.getWindow().findViewById(R.id.btn_retro_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.dismiss();
                listener.closeFeedBackDialog();
            }
        });

        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public interface FeedbackDialogListener {
        void closeFeedBackDialog();
    }
}

