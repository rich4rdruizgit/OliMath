package olimpiadas.sena.com.olimpiadasmath.activities.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.adapter.profile.ViewPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.fragments.profile.LogroFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.profile.PerfilFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.profile.ProfileResultsFragment;
import olimpiadas.sena.com.olimpiadasmath.model.Achievements;
import olimpiadas.sena.com.olimpiadasmath.util.CircleImage.CropCircleTransformation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity implements PerfilFragment.OnFragmentInteractionListener, LogroFragment.OnFragmentInteractionListener, ProfileResultsFragment.OnFragmentInteractionListener {


    AppControl appControl;

    ViewPager viewPager;

    TextView tvLvl,tvCoins,tvTickets,txtNameUser,tvScore;
    ImageView imgExp, imgProfile;
    private ClipDrawable mImageDrawable;
    private String recurso= "drawable";
    List<Achievements> achievementsList;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.vpPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        btnBack = (Button) findViewById(R.id.btn_back_profile);
        appControl = AppControl.getInstance();


        appControl = AppControl.getInstance();
        appControl.currentActivity = ProfileActivity.class.getSimpleName();

        imgProfile = (ImageView) findViewById(R.id.img_profile_user);
        int avatar = getResources().getIdentifier(appControl.currentUser.getAvatar(),recurso, getPackageName());
        imgProfile.setImageResource(avatar);

        txtNameUser = (TextView) findViewById(R.id.txt_profile_user);
        txtNameUser.setText(appControl.currentUser.getNickname());

        tvLvl = (TextView) findViewById(R.id.tv_profile_level_number);
        tvCoins = (TextView) findViewById(R.id.tv_profile_coins);

        imgProfile.setImageResource(avatar);
        Picasso.with(imgProfile.getContext())
                .load(avatar)
                .transform(new CropCircleTransformation())
                .into(imgProfile);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButton = MediaPlayer.create(getApplicationContext(),appControl.soundButtonEfect);
                if(appControl.isBackgroundPlaying)
                    appControl.soundButton.start();
                Intent goBack = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onResume() {
        super.onResume();

        //tvCoins.setText(" X "+appControl.currentUser.getCoins());
        //tvTickets.setText(" X "+appControl.currentUser.getTickets());

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
