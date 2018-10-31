package basicapplication1.termapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by  sj 2018-10-10.
 */
public class MypageActivity   extends AppCompatActivity {
//test first

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
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
