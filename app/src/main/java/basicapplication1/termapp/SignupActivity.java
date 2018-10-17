package basicapplication1.termapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by sj on 2018-10-10.
 */
public class SignupActivity   extends Activity {
    //test first
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


   /* public void onClick(View v){
        EditText id=(EditText) findViewById(R.id.signup_id);
        if(id.getText().length()==0) {
            Toast.makeText(getApplicationContext(),"아이디가 공백입니다",Toast.LENGTH_SHORT);
            return;
        }
        //id 처리 (db삽입안했음)
        EditText password= (EditText) findViewById(R.id.signup_password);
        EditText password2= (EditText) findViewById(R.id.signup_re_password);
        if(password.getText().length()==0) {
            Toast.makeText(getApplicationContext(),"비밀번호가 공백입니다",Toast.LENGTH_SHORT);
            return;
        }
        if(!password.getText().equals(password2.getText())){
            Toast.makeText(getApplicationContext(),"비밀번호가 같지않습니다.",Toast.LENGTH_SHORT);
            return;
        }
        EditText nickname=(EditText) findViewById(R.id.signup_nickname);
        //닉네임도 중복확인해줘야함
        EditText phonenumber=(EditText) findViewById(R.id.signup_phonenumber);
        EditText email=(EditText) findViewById(R.id.signup_mail);
        if(nickname.getText().length()==0 || phonenumber.getText().length()==0 || email.getText().length()==0){
            Toast.makeText(getApplicationContext(),"공백입니다.",Toast.LENGTH_SHORT);
            return;
        }


        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }
    */
}
