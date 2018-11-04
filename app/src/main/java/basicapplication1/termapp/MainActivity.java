package basicapplication1.termapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//test first

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_to_list:
                intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.main_to_enroll:
                intent = new Intent(this, EnrollActivity.class);
                startActivity(intent);
                break;
           //   웹크롤링버튼, 공유하기 넣어야함
            case  R.id.main_to_share:
                intent = new Intent(this, PostActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.home_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.menu_home:
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.menu_logout:
                    intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("logout", true);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.menu_mypage:
                    intent = new Intent(this, MypageActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

    }
