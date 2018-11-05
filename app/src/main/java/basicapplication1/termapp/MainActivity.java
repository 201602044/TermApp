package basicapplication1.termapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import basicapplication1.termapp.listview.ListViewAdapter;
import basicapplication1.termapp.listview.ListViewItem;
import basicapplication1.termapp.listview.pageList;

public class MainActivity extends AppCompatActivity {
//test first

    Intent intent;
    ImageView imageView;
    AnimationDrawable aniCatDrawable;
    ListView listview ;
    ListViewAdapter adapter;
    pageList pl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animaition();
        pl=new pageList();
        adapter=new ListViewAdapter();
        listview = (ListView) findViewById(R.id.main_listview);
        listview.setAdapter(adapter);
        final ListViewItem[] listViewItems;
        listViewItems= pl.makeList("page_like","","0","3");

        if(listViewItems==null){
            Toast.makeText(this,"조회실패",Toast.LENGTH_LONG).show();
            return;
        }
        for(int i=0;i<listViewItems.length;i++){
            adapter.addItem(listViewItems[i]);
        }
        Log.i("문자","오긴옴?:"+listViewItems.length);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent =new Intent(getApplicationContext(),PostActivity.class);
                intent.putExtra("page_num",listViewItems[position].getPage_num());
                intent.putExtra("page_nickname",listViewItems[position].getPage_nickname());
                startActivity(intent);

            }
        });
    }
    public  void animaition(){
        imageView =(ImageView) findViewById(R.id.main_slide);
        aniCatDrawable = (AnimationDrawable) imageView.getDrawable();
        if(aniCatDrawable!=null){
            aniCatDrawable.start();
        }else {
            Toast.makeText(this,"애니메이션 실패!!",Toast.LENGTH_SHORT).show();
        }
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
