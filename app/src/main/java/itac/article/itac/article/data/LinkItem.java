package itac.article.itac.article.data;

import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by user on 16/3/18.
 */
public class LinkItem{
    private String url;
    private String title;

    public LinkItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public LinkItem(String url) {
        this.url = url;
    }

    public LinkItem(){

    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
