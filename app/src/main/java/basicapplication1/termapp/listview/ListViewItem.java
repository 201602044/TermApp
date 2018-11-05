package basicapplication1.termapp.listview;

/**
 * Created by 리쌍d on 2018-11-04.
 */
public class ListViewItem {
    private String page_nickname,page_num,page_link,page_tag,page_like,page_unlike,page_info,page_theme;
    public ListViewItem(String ... strings){
        page_nickname=strings[0];
        page_num=strings[1];
        page_link=strings[2];
        page_tag=strings[3];
        page_like=strings[4];
        page_unlike=strings[5];
        page_info=strings[6];
        page_theme=strings[7];
    }
    public ListViewItem(){

    }
    public String getPage_nickname() {
        return page_nickname;
    }

    public void setPage_nickname(String page_nickname) {
        this.page_nickname = page_nickname;
    }

    public String getPage_num() {
        return page_num;
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }

    public String getPage_link() {
        return page_link;
    }

    public void setPage_link(String page_link) {
        this.page_link = page_link;
    }

    public String getPage_tag() {
        return page_tag;
    }

    public void setPage_tag(String page_tag) {
        this.page_tag = page_tag;
    }

    public String getPage_like() {
        return page_like;
    }

    public void setPage_like(String page_like) {
        this.page_like = page_like;
    }

    public String getPage_unlike() {
        return page_unlike;
    }

    public void setPage_unlike(String page_unlike) {
        this.page_unlike = page_unlike;
    }

    public String getPage_info() {
        return page_info;
    }

    public void setPage_info(String page_info) {
        this.page_info = page_info;
    }

    public String getPage_theme() {
        return page_theme;
    }

    public void setPage_theme(String page_theme) {
        this.page_theme = page_theme;
    }
}
