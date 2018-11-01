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
    private EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        linearLayout=new LinearLayout[3];
        imageViews=new  ImageView[3];
        linearLayout[0]=(LinearLayout) findViewById(R.id.mypage_list_1);
        linearLayout[1]=(LinearLayout) findViewById(R.id.mypage_list_2);
        linearLayout[2]=(LinearLayout) findViewById(R.id.mypage_list_3);
        imageViews[0]=(ImageView) findViewById(R.id.mypage_image_1);
        imageViews[1]=(ImageView) findViewById(R.id.mypage_image_2);
        imageViews[2]=(ImageView) findViewById(R.id.mypage_image_3);
        change=new boolean[3];
        Arrays.fill(change,false);

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
                break;
            case R.id.mypage_change_passwd_button:
                break;
            case R.id.mypage_sign_out:
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
        for(int i=0;i<3;i++){
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
}
