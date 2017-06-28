package trial.trial_first;


import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.ImageView;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Splashscreen extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        AnimationDrawable animation = new AnimationDrawable();
            animation.addFrame(getResources().getDrawable(R.drawable.sslogoone), 100);
            animation.addFrame(getResources().getDrawable(R.drawable.sslogotwo), 200);
            animation.addFrame(getResources().getDrawable(R.drawable.sslogothree), 300);
            animation.addFrame(getResources().getDrawable(R.drawable.sslogofour), 400);
            animation.addFrame(getResources().getDrawable(R.drawable.sslogofive), 500);
            animation.addFrame(getResources().getDrawable(R.drawable.sslogosix), 600);
            animation.addFrame(getResources().getDrawable(R.drawable.sslogo), 700);
        animation.setOneShot(false);

        ImageView imageAnim =  (ImageView) findViewById(R.id.sslogo_img);
        imageAnim.setImageDrawable(animation);


            TextView tid=(TextView) findViewById(R.id.trialID);

            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Della Robbia BT.ttf");

            tid.setTypeface(custom_font);





        // start the animation!
        animation.start();




            TimerTask tt=new TimerTask() {
                @Override
                public void run() {
                    Intent i=new Intent(Splashscreen.this, Login.class);
                    startActivity(i);

                    finish();
                }
            };


            Timer t=new Timer();
            t.schedule(tt, 4000);
    }
}
