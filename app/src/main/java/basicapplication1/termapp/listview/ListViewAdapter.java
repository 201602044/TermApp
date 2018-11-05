package basicapplication1.termapp.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import basicapplication1.termapp.R;

/**
 * Created by 리쌍d on 2018-11-04.
 */
public class ListViewAdapter  extends BaseAdapter  implements  View.OnClickListener{
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
       TextView[] textViews=new TextView[5];
        textViews[0]=(TextView)  convertView.findViewById(R.id.list_day);
        textViews[1]= (TextView)  convertView.findViewById(R.id.list_theme);
        textViews[2]= (TextView)  convertView.findViewById(R.id.list_nickname);
        textViews[3]= (TextView)  convertView.findViewById(R.id.list_like);
        textViews[4]= (TextView)  convertView.findViewById(R.id.list_unlike);

//만들어야할게 전체 제목 날짜  닉네임 좋아요 싫어요 // + linearlayout의 버튼 ,
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        String day=listViewItem.getPage_num().substring(0,4)+"-"+listViewItem.getPage_num().substring(4,6)+","+listViewItem.getPage_num().substring(6,8);
        textViews[0].setText(day);
        textViews[1].setText(listViewItem.getPage_theme());
        textViews[2].setText(listViewItem.getPage_nickname());
        textViews[3].setText(listViewItem.getPage_like());
        textViews[4].setText(listViewItem.getPage_unlike());


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem( ListViewItem item) {
        listViewItemList.add(item);
    }


    @Override
    public void onClick(View v) {

    }
}
