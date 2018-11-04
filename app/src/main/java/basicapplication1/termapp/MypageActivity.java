package basicapplication1.termapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by  sj 2018-10-10.
 */
public class MypageActivity   extends AppCompatActivity {
//test first

    Intent intent;
   private LinearLayout[] linearLayout;
    private ImageView[] imageViews;
    private  boolean[] change;
    private String type,result;
    private  String[] sendMsg;
    private  int size;
    private EditText editText1,editText2;
    private mTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        size=3;
        linearLayout=new LinearLayout[size];
        imageViews=new  ImageView[size];
        linearLayout[0]=(LinearLayout) findViewById(R.id.mypage_list_1);
        linearLayout[1]=(LinearLayout) findViewById(R.id.mypage_list_2);
        linearLayout[2]=(LinearLayout) findViewById(R.id.mypage_list_3);
        imageViews[0]=(ImageView) findViewById(R.id.mypage_image_1);
        imageViews[1]=(ImageView) findViewById(R.id.mypage_image_2);
        imageViews[2]=(ImageView) findViewById(R.id.mypage_image_3);

        change=new boolean[3];
        Arrays.fill(change,false);

        sendMsg=new  String[2];
        sendMsg[1]="member";
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.mypage_click_list1:
                folding(0);
                break;
            case R.id.mypage_click_list2:
                folding(1);
                break;
            case R.id.mypage_click_list3:
                folding(2);
                break;
            case R.id.mypage_change_nickname_button:
                    update_nickname();
                break;
            case R.id.mypage_change_passwd_button:
                update_passwd();
                break;
            case R.id.mypage_delete_id_button:
                delete_id();
                break;
            default:
                break;

        }
    }
    public void folding(int temp){
        if(change[temp] == true ) {

            linearLayout[temp].setVisibility(View.GONE);
            imageViews[temp].setImageResource(R.drawable.more);
            change[temp]=false;
            return;
        }
        for(int i=0;i<size;i++){
            if(change[i]==true){
                linearLayout[i].setVisibility(View.GONE);
                imageViews[i].setImageResource(R.drawable.more);
                change[i]=false;
            }
        }
        linearLayout[temp].setVisibility(View.VISIBLE);
        imageViews[temp].setImageResource(R.drawable.less);
        change[temp]=true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.menu_logout:
                intent=new Intent(this,LoginActivity.class);
                intent.putExtra("logout",true);
                startActivity(intent);
                finish();
                break;
            case  R.id.menu_mypage:
                intent=new Intent(this,MypageActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
    public void update_passwd(){

        editText1=(EditText) findViewById(R.id.mypage_now_passwd);
        editText2=(EditText) findViewById(R.id.mypage_change_passwd);
        String now_passwd=editText1.getText().toString();
        String change_passwd=editText2.getText().toString();

        if(change_passwd.equals("")||now_passwd.equals("")){
            Toast.makeText(this,"공백입니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            type="update_passwd";
            sendMsg[0]="type="+type+"&user_id="+LoginActivity.now_id+"&user_passwd="+now_passwd+"&change_passwd="+change_passwd;
            task =new mTask();
            result=task.execute(sendMsg).get();
            task.cancel(true);
            if(result.contains("1")){

                Toast.makeText(this,"비밀번호가 변경되었습니다. 다시 로그인하세요",Toast.LENGTH_SHORT).show();

                intent = new Intent(this, LoginActivity.class);
                intent.putExtra("logout", true);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this,"비밀번호가 바뀌지 않았습니다.",Toast.LENGTH_SHORT).show();
            }
            editText1.setText("");
            editText2.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update_nickname(){
        editText1=(EditText) findViewById(R.id.mypage_change_nickname);
        String change_nickname=editText1.getText().toString();
        if(change_nickname.equals("")){
            Toast.makeText(this,"닉네임이공백입니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            type="update_nickname";
            sendMsg[0]="type="+type+"&user_nickname="+LoginActivity.now_nickname+"&change_nickname="+change_nickname;
            task =new mTask();
            result=task.execute(sendMsg).get();
            task.cancel(true);
            if(result.contains("1")){
                Toast.makeText(this,"닉네임이 변경되었습니다.",Toast.LENGTH_SHORT).show();
                LoginActivity.now_nickname=change_nickname;
            }
            else {
                Toast.makeText(this,"닉네임이 바뀌지 않았습니다.",Toast.LENGTH_SHORT).show();
            }
            editText1.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void delete_id(){

        try {
            type="delete";
            sendMsg[0]="type="+type+"&user_id="+LoginActivity.now_id;
            task =new mTask();
            task.execute(sendMsg).get();
            task.cancel(true);
            Toast.makeText(this,"삭제.",Toast.LENGTH_SHORT).show();
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra("logout", true);
            startActivity(intent);
            finish();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
