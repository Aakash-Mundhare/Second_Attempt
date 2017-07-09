package trial.trial_first;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class Signup extends Activity {

    EditText firstName, lastName, ctfirstName, ctlastName, email, password, confirmPassword;
    Button signUP;
    String fn,ln,cfn,cln,eml,pswd;
    String age;
    Spinner ageSpinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstName=(EditText)findViewById(R.id.firstName);
        lastName= (EditText)findViewById(R.id.lastName);
        ctfirstName= (EditText)findViewById(R.id.ctfirstName);
        ctlastName= (EditText)findViewById(R.id.ctlastName);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        signUP=(Button) findViewById(R.id.signupbutton);



         ageSpinner=(Spinner) findViewById(R.id.ageSpinner);

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
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fn=firstName.getText().toString();
                ln=lastName.getText().toString();
                cfn=ctfirstName.getText().toString();
                cln=ctlastName.getText().toString();
                eml=email.getText().toString();
                pswd=password.getText().toString();
                age=ageSpinner.getSelectedItem().toString();
                if(isonline()){
                    Signup("http://miamigoic.azurewebsites.net/v1/register.php");
                }else{
                    Toast.makeText(Signup.this, "Network isnt avialable", Toast.LENGTH_LONG).show();
                }
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

    public   boolean isonline() {
        Log.e("coool start is online", " ");
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void afterGettingResult(){
        Toast.makeText(Signup.this,"After signup you are send to TNCC acitivy", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Signup.this, TNCC.class);
        startActivity(i);
        finish();
    }

    public void Signup(String uri){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String status="";
                        String message="";
                        try {
                            JSONObject obje= new JSONObject(response);
                            status=obje.getString("status");
                            message=obje.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status.equals("success")){
                            Toast.makeText(Signup.this,message,Toast.LENGTH_LONG).show();
                            afterGettingResult();
                        }
                        else{
                            Toast.makeText(Signup.this,message,Toast.LENGTH_LONG).show();
                        }

                    }



                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Signup.this, "Ërror in sign in ", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<String, String>();
                params.put("email", eml);
                params.put("password",pswd);
                params.put("patientFirstName",fn);
                params.put("patientLastName",ln);
                params.put("caretakerFirstName",cfn);
                params.put("caretakerLastName",cln);
                params.put("patientAge",age);

                //returning parameters
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding request to the queue
        requestQueue.add(stringRequest);

    }


}
