package trial.trial_first;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView resetbutton=(TextView) findViewById(R.id.resetbtn);
        TextView signupbutton=(TextView) findViewById(R.id.signupbtn);

        resetbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent toreset=new Intent(Login.this, Reset.class);
                startActivity(toreset);
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent tosignup=new Intent(Login.this, Signup.class);
                startActivity(tosignup);

                finish();
            }
        });
    }

    public int count=0;
    @Override
    public void onBackPressed()
    {
        count++;
        if(count==1)
        {
            Toast.makeText(Login.this, "Press BACK once more to Exit", Toast.LENGTH_SHORT).show();
        }
        else if(count==2)
        {
            count=0;
            finish();
        }
    }

}
