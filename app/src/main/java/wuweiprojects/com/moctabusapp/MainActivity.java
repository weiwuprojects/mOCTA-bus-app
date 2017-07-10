package wuweiprojects.com.moctabusapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import android.widget.VideoView;



public class MainActivity extends AppCompatActivity {

    VideoView vv;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Window window = this.getWindow();
        window.setStatusBarColor(0xff1a5eab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

        final EditText expDate = (EditText) findViewById(R.id.textView3);
        expDate.setCursorVisible(false);
        expDate.getBackground().setColorFilter(0xff1a5eab, PorterDuff.Mode.SRC_IN);
        SharedPreferences prefs = getSharedPreferences("textField", MODE_PRIVATE);
        String text = prefs.getString("date", "Aug 10, 2017 3:00 PM");
        expDate.setText(text);

        expDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                SharedPreferences.Editor editor = getSharedPreferences("textField", MODE_PRIVATE).edit();
                editor.putString("date", expDate.getText().toString());
                editor.apply();
            }
        });

        //video stuff
        vv = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        vv.setVideoURI(Uri.parse(path));
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vv.requestFocus();
            }
        });
        vv.start();


    }



}

