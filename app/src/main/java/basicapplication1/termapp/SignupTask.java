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
 * Created by 리쌍d on 2018-10-17.
 */
public class SignupTask  extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;
    @Override
    protected String doInBackground(String... strings) {
        try {
//            JSONObject jsonObject=new JSONObject();
            String str;
            URL url = new URL("http://thdeo706.vps.phps.kr:8080/TermApp/insert.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //json설정
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            sendMsg = "user_id="+strings[0]+"&user_passwd="+strings[1]+"&user_nickname="+strings[2]
                    +"&user_phone_number="+strings[3]+"&user_email="+strings[4];
//            jsonObject.put("user_id", strings[0]);
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

