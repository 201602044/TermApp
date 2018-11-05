package basicapplication1.termapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import basicapplication1.termapp.listview.ListViewAdapter;
import basicapplication1.termapp.listview.ListViewItem;
import basicapplication1.termapp.listview.pageList;

/**
 * Created by sj on 2018-10-10.
 */
public class ListActivity   extends AppCompatActivity {
//test first

    Intent intent;
    ListView listview ;
    ListViewAdapter adapter;
    pageList pl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
         pl=new pageList();
        intent=getIntent();
        // 리스트뷰 참조 및 Adapter달기
        adapter=new ListViewAdapter();
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
        String option="page_theme",text="";
        final ListViewItem[] listViewItems;
        if(intent.getBooleanExtra("list_to_list",false)) {
            option = selectOption(intent.getStringExtra("list_search_option"));
            text = intent.getStringExtra("list_search_text");
        }
            listViewItems= pl.makeList(option,text,"0","100");

        if(listViewItems==null){
            Toast.makeText(this,"조회실패",Toast.LENGTH_SHORT).show();
            return;
        }
        for(int i=0;i<listViewItems.length;i++){
            adapter.addItem(listViewItems[i]);
        }
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
    public String selectOption(String option){
        String temp;
        if(option.equals("태그")){
            temp="page_tag";
        }
       else  if(option.equals("제목")){
            temp="page_theme";
        }
        else{
            temp="page_nickname";
        }
        return temp;
    }
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.list_search_button:
                Spinner spinner=(Spinner) findViewById(R.id.list_search_option) ;
                EditText editText=(EditText) findViewById(R.id.list_search_text);
                intent=new Intent(this,ListActivity.class);

                intent.putExtra("list_to_list",true);
                intent.putExtra("list_search_text",editText.getText().toString());
                intent.putExtra("list_search_option",spinner.getSelectedItem().toString());
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
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
        return super.onCreateOptionsMenu(menu);
    }
}
