package trial.trial_first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Signup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Spinner ageSpinner=(Spinner) findViewById(R.id.ageSpinner);

        ArrayAdapter <String> ageAdapter = new ArrayAdapter(Signup.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ageList));
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        TextView tnc=(TextView) findViewById(R.id.tnc);
        tnc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(Signup.this, TNCC.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent bck=new Intent(Signup.this, Login.class);
        startActivity(bck);

        finish();
    }
}
