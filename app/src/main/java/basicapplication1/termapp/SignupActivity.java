package basicapplication1.termapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onClick(View v){
        EditText id=(EditText) findViewById(R.id.signup_id);
        TextView set_id_toast=(TextView) findViewById(R.id.signup_toast_id);
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
        else if(password.getText().equals(password2.getText())){
            TextView set_id_password=(TextView) findViewById(R.id.signup_toast_password);
            //비번중복처리
            set_id_password.setText("불일치");
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
}
