package basicapplication1.termapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.StringTokenizer;


/**
 * Created by 리쌍d on 2018-10-10.
 */
public class PostActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
//test first
public static final String API_KEY = "AIzaSyAzEA-pjRmxTVOL0Cta1ActQh9ywUF8NSA";
//사용자가 얻은 API Key을 입력하면 된다.(개발자 콘솔에 얻은 것.)

    Intent intent;
    private String  page_nickname, page_num,result,result2;
    private String[] sendMsg;
    YouTubePlayerView youtubeView;
   private String VIDEO_ID = "RgKAFK5djSk";
    private static final int RQS_ErrorDialog = 1;
    private TextView[] textViews;
    private int textSize;
    private  mTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        intent=getIntent();
        page_nickname=intent.getStringExtra("page_nickname");
        page_num=intent.getStringExtra("page_num");
        if(page_nickname==null||page_nickname.equals("")||page_num==null||page_num.equals("")) {
            intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this,"게시물 조회 실패",Toast.LENGTH_SHORT).show();
        }
        sendMsg=new String[2];
        //type msg/ 이있어야한다.
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.post_youtube_fragment);
        frag.initialize(API_KEY, this);

        textSize=8;
        textViews=new TextView[textSize];
        textViews[0]=(TextView) findViewById(R.id.post_theme);
        textViews[1]=(TextView) findViewById(R.id.post_link);
        textViews[2]=(TextView) findViewById(R.id.post_info);
        textViews[3]=(TextView) findViewById(R.id.post_phone_numeber);
        textViews[4]=(TextView) findViewById(R.id.post_mail);
        textViews[5]=(TextView) findViewById(R.id.post_tag);
        textViews[6]=(TextView) findViewById(R.id.post_like_text);
        textViews[7]=(TextView) findViewById(R.id.post_unlike_text);

        madePost();
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.post_like:
                like(1);
                break;
            case R.id.post_unlike:
                like(0);
                break;
            default:
                break;
        }

    }
    public  void like(int like ){
        try{
           task =new mTask();
            sendMsg[1]="page";
            sendMsg[0]="type=like&page_nickname="+page_nickname+"&page_num="+page_num+"&page_like="+like;
            result=task.execute(sendMsg).get();
            task.cancel(true);
            if(result.equals("-1")){
                Toast.makeText(this,"오류",Toast.LENGTH_SHORT).show();
            }
            else{
                if(like ==1){
                    textViews[6].setText(result);
                } else  textViews[7].setText(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void madePost(){
        try{
           task =new mTask();
            sendMsg[1]="member";
            sendMsg[0]="type=contact&user_id="+LoginActivity.now_id;
            result2=task.execute(sendMsg).get();
            task.cancel(true);
            sendMsg[1]="page";
            sendMsg[0]="type=post&page_nickname="+page_nickname+"&page_num="+page_num;
            task =new mTask();
            result=task.execute(sendMsg).get();
            Log.i("문자",page_nickname+"#"+page_num);
            task.cancel(true);
            if(result.equals("0")||result2.equals("0")){
                Toast.makeText(this,"실패",Toast.LENGTH_SHORT);
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            JSONParser jsonParser=new JSONParser();
            JSONObject jsonObject=(JSONObject) jsonParser.parse(result);
            textViews[0].setText("제목:"+jsonObject.get("page_theme").toString());
            textViews[2].setText("소개:"+jsonObject.get("page_info").toString());
            textViews[5].setText("태그:"+jsonObject.get("page_tag").toString());
            textViews[6].setText(jsonObject.get("page_like").toString());
            textViews[7].setText(jsonObject.get("page_unlike").toString());
            StringTokenizer st=new StringTokenizer(result2,"#");
            textViews[3].setText("번호"+st.nextToken());
            textViews[4].setText("메일"+st.nextToken());
            for(int i=0;i<textSize;i++){
                if(jsonObject.get("page_font").toString().equals("sans")){

                    textViews[i].setTypeface(Typeface.SANS_SERIF);
                }
                if(jsonObject.get("page_font").toString().equals("serif")){
                    textViews[i].setTypeface(Typeface.SERIF);
                }
                if(jsonObject.get("page_font").toString().equals("monospace")){
                    textViews[i].setTypeface(Typeface.MONOSPACE);
                }
                if(jsonObject.get("page_color").toString().equals("black")){
                    textViews[i].setTextColor(Color.BLACK);
                }
                if(jsonObject.get("page_color").toString().equals("green")){
                    textViews[i].setTextColor(Color.GREEN);
                }
                if(jsonObject.get("page_color").toString().equals("red")){
                    textViews[i].setTextColor(Color.RED);
                }
                if(jsonObject.get("page_color").toString().equals("blue")){
                    textViews[i].setTextColor(Color.BLUE);
                }
                textViews[i].setTextSize(Integer.parseInt((jsonObject.get("page_size").toString())));
            }
            VIDEO_ID =jsonObject.get("page_link").toString();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            //I assume the below String value is your video id
            player.cueVideo(VIDEO_ID);
        }
    }
       @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {

                Toast.makeText(this, "오류", Toast.LENGTH_LONG).show();
           if (error.isUserRecoverableError()) {
               error.getErrorDialog(this, RQS_ErrorDialog).show();
           } else {
               Toast.makeText(this,
                       "YouTubePlayer.onInitializationFailure(): " + error.toString(),
                       Toast.LENGTH_LONG).show();
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
