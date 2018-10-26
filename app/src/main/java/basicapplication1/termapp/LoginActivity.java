package basicapplication1.termapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by sj on 2018-10-10. tesgt
 */

public class LoginActivity extends AppCompatActivity {
    //test first

    private CheckBox checkBox;
    SharedPreferences pref;
    private  EditText editText;
   private  EditText editText2;
    private  SharedPreferences.Editor editor;
    public static Activity loginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity=LoginActivity.this;
        pref=getSharedPreferences("pref",MODE_PRIVATE);
        editor = pref.edit();
        Intent intent = getIntent();
        if(intent.getBooleanExtra("logout",false)){
            editor.clear();
            return;
        }
        //만약 메인페이지에서 로그아웃을한다면
        checkBox=(CheckBox) findViewById(R.id.login_checkbox);
        editText=((EditText) findViewById(R.id.login_id));
        editText2=((EditText) findViewById(R.id.login_passwd));
       checkBox.setChecked(pref.getBoolean("login_checkbox",false));
        if(checkBox.isChecked()) {
            editText.setText(pref.getString("user_id",""));
            editText2.setText(pref.getString("user_passwd",""));
//            login(); 주석해제시 자동 로그인
        }

    }
   public  void setPreferences(String id, String passwd){
       editor.putString("user_id",id);
       editor.putString("user_passwd",passwd);
       editor.putBoolean("login_checkbox",true);
       editor.commit();
   }
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.login_to_main:
                login();
                break;
            case  R.id.login_to_signup:
                Intent intent;
                intent=new Intent(this,SignupActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

public void login(){
    String id=editText.getText().toString();
    String passwd=editText2.getText().toString();
    try{
        LoginTask task   =   new LoginTask();
        String result=task.execute(id,passwd).get();
        if(result.contains("1")&&!result.contains("-1"))
        {
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("user_id",id);
            if(checkBox.isChecked()) setPreferences(id,passwd);
            else editor.clear();
            startActivity(intent);
            finish();
        }
        else    {
            Toast.makeText(this,"아이디나 비밀번호가 일치x",Toast.LENGTH_SHORT).show();
            editText.setText("");
            editText2.setText("");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
}

}

//onStop 메소드로 자동로그인 해제시 shareprefrence 초기화필요
//inent 로 받아서 로그아웃 케이스 추가해줘야함
//signuptask 만들어줘야함
