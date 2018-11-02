package basicapplication1.termapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by sj 2018-10-10.
 */
public class EnrollActivity   extends AppCompatActivity {
//test first
//spinner 의 순서쌍은 폰트 사이즈 칼라 순서이다 .
    Intent intent;
    private EditText[] editTexts;
    private Spinner[] spinners;
    private int editTextSize,spinSize;
    private  String[] sendMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        sendMsg=new String[2];
        sendMsg[1]="page_insert";
        editTextSize=4;
        spinSize=3;
        editTexts=new EditText[editTextSize];
        spinners=new Spinner[spinSize];

    }
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.enroll_save:
                editTexts[0]=(EditText) findViewById(R.id.enroll_theme);
                editTexts[1]=(EditText) findViewById(R.id.enroll_link);
                editTexts[2]=(EditText) findViewById(R.id.enroll_introduce);//=info
                editTexts[3]=(EditText) findViewById(R.id.enroll_tag);
                spinners[0]=(Spinner) findViewById(R.id.enroll_spinner_font);
                spinners[1]=(Spinner) findViewById(R.id.enroll_spinner_size);
                spinners[2]=(Spinner) findViewById(R.id.enroll_spinner_color);
                //닉네임하고 폰트는 다른거
                String font=spinners[0].getSelectedItem().toString()+"#"+spinners[1].getSelectedItem().toString()+"#"+
                        spinners[2].getSelectedItem().toString();
                for(int i=0;i<editTextSize;i++){

                                if(editTexts[i].getText().toString().equals("")){
                                    Toast.makeText(this,"공백이있습니다",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                }
                if(font.equals("")){
                    Toast.makeText(this,"공백이 있습니다",Toast.LENGTH_SHORT).show();
                    return;
                }
                //공백처리
                sendMsg[0]="page_theme="+editTexts[0].getText().toString()
                        +"&page_link="+editTexts[1].getText().toString()
                        +"&page_info="+editTexts[2].getText().toString()
                        +"&page_nickname="+LoginActivity.now_nickname
                        +"&page_tag="+editTexts[3].getText().toString()
                        +"&page_font="+font;
                mTask task=new mTask();


                try{
                    String result=task.execute(sendMsg).get();
                    task.cancel(true);
                    if(result.contains("1")){
                        intent=new Intent(this,PostActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(this,"실패",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
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
