package basicapplication1.termapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sj on 2018-10-10.
 *
 */
public class SignupActivity   extends Activity {
    //test first
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


 public void onClick(View v) {
    switch (v.getId()){
        case R.id.signup_to_main:
            TextView textView1=(TextView) findViewById(R.id.signup_id);
            TextView textView2=(TextView) findViewById(R.id.signup_passwd);
            TextView textView3=(TextView) findViewById(R.id.signup_nickname);
            TextView textView4=(TextView) findViewById(R.id.signup_phone_number);
            TextView textView5=(TextView) findViewById(R.id.signup_email);
            TextView repasswd=(TextView) findViewById(R.id.signup_re_passwd);
            String  temp=repasswd.getText().toString();
            String[] member={textView1.getText().toString(),textView2.getText().toString(),textView3.getText().toString()
            ,textView4.getText().toString(),textView5.getText().toString()};
            for(int i=0;i<member.length;i++){
                if(member[i]==null || member[i].equals("")) {
                    Toast.makeText(this,"공백이있습니다",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if(!member[1].equals(temp)) {
                Toast.makeText(this,"비밀번호가 중복되지않습니다.",Toast.LENGTH_SHORT).show();
                textView2.setText("");
                repasswd.setText("");
                return;
            }
            try {
                SignupTask signupTask = new SignupTask();
                String result = signupTask.execute(member).get();
                //result 1일때정상, 나머지 비정상
                if(result.contains("1")) {
                    Intent intent=new Intent(this, MainActivity.class);
                    intent.putExtra("user_id",member[0]);
                    LoginActivity loginActivity=(LoginActivity) LoginActivity.loginActivity;
                    loginActivity.finish();
                    //LoginActivity 종료
                    startActivity(intent);
                    finish();
                    return;
                } else  if(result.contains("2")){
                    Toast.makeText(this,"아이디가 중복됩니다",Toast.LENGTH_SHORT).show();
                    textView1.setText("");
                    return;
                }
                else  if(result.contains("3")) {
                    Toast.makeText(this, "닉네임이중복됩니다.", Toast.LENGTH_SHORT).show();
                    textView3.setText("");
                    return;
                }
                else {
                    Toast.makeText(this,"아이디와닉네임이중복됩니다.",Toast.LENGTH_SHORT).show();
                    textView1.setText("");
                    textView3.setText("");
                    return;
                }//0을받을때
            }catch (Exception e) {
                e.printStackTrace();
            }
            break;
        default:
            break;
    }
 }
}
