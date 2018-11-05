package basicapplication1.termapp.listview;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import basicapplication1.termapp.mTask;

/**
 * Created by 리쌍d on 2018-11-04.
 */
public class pageList {
    mTask task=new mTask();

    public ListViewItem[] makeList(String searchType, String searchText, String limitStart, String limitCount){
        String result="0";
        String[] sendMsg=new String[2];
        sendMsg[1]="page";
        ListViewItem[] listViewItems=null;
            try{
                sendMsg[0]="type=search&search_type="+searchType+"&search_text="
                        +searchText+"&search_text_start="+limitStart+"&search_text_size="+limitCount;
                result=task.execute(sendMsg).get();
                if(result.equals("0")){
                    Log.i("쿼리문전송실패,",result);
                    return null;
                }
                JSONParser jsonParser=new JSONParser();
                JSONObject jsonObject=(JSONObject) jsonParser.parse(result);
               JSONArray jsonArray=(JSONArray) jsonObject.get("list");
                 listViewItems=new ListViewItem[jsonArray.size()];
                for(int i=0;i<jsonArray.size();i++){
                    jsonObject=(JSONObject) jsonArray.get(i);
                    listViewItems[i]=new ListViewItem(
//                    private String page_nickname,page_num,page_link,page_tag,page_like,page_unlike,page_info,page_theme;
                            jsonObject.get("page_nickname").toString(),
                            jsonObject.get("page_num").toString(),
                            jsonObject.get("page_link").toString(),
                            jsonObject.get("page_tag").toString(),
                            jsonObject.get("page_like").toString(),
                            jsonObject.get("page_unlike").toString(),
                            jsonObject.get("page_info").toString(),
                            jsonObject.get("page_theme").toString()
                    );
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        return listViewItems;
    }
    public String totalCountList(String searchType,String searchText){
        String result="0";
        String[] sendMsg=new String[2];
        sendMsg[1]="page";
        try{
            sendMsg[0]="type=search&search_type="+searchType+"&search_text="+searchText;
            result=task.execute(sendMsg).get();
        }catch (Exception e){
            e.printStackTrace();
        }
            return result;
    }
}
