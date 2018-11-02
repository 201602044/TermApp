package basicapplication1.termapp;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 리쌍d on 2018-10-30.
 */
public class mTask extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg,table;
    @Override
    protected String doInBackground(String... strings) {
        try {

            String str;
            sendMsg = strings[0];
            table=strings[1];
            //코드받을때 string[0]에서 처리해서온다
            //두번째 주소
            URL url = new URL("http://thdeo706.vps.phps.kr:8080/TermApp/"+table+".jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(sendMsg);
            osw.flush();
            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine())!= null) {
                    buffer.append(str);
                }

                receiveMsg = buffer.toString();
                receiveMsg=    receiveMsg.replaceAll(" ", "");
                Log.i("문자",receiveMsg);
            } else {
                Log.i("통신 결과", conn.getResponseCode()+"에러");
                System.out.print("에러");
                if(android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }


            }

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  receiveMsg;
    }
}
//AsyncTask 클래스
//원하는 멤버 정보를 가져오고 , 삭제를 하는 클래스