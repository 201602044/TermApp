package basicapplication1.termapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkBox=(CheckBox) findViewById(R.id.login_checkbox);
        editText=((EditText) findViewById(R.id.login_id));
        editText2=((EditText) findViewById(R.id.login_passwd));
        pref=getSharedPreferences("pref",MODE_PRIVATE);
       checkBox.setChecked(pref.getBoolean("login_checkbox",false));
        if(checkBox.isChecked()) {
            editText.setText(pref.getString("user_id",""));
            editText2.setText(pref.getString("user_passwd",""));
//            login(); 주석해제시 자동 로그인
        }

    }
   public  void setPreferences(String id, String passwd){
     editor = pref.edit();
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
        if(result.equals("1"))
        {
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("user_id",id);
            intent.putExtra("user_passwd",passwd);
            if(checkBox.isChecked()) setPreferences(id,passwd);
            startActivity(intent);
            finish();
        }
        else    {
            editText.setText("");
            editText2.setText("");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
}

}

