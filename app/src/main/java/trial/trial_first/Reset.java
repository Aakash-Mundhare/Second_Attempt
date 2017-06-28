package trial.trial_first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Reset extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
    }

    @Override
    public void onBackPressed()
    {
        Intent bck=new Intent(Reset.this, Login.class);
        startActivity(bck);

        finish();
    }
}
