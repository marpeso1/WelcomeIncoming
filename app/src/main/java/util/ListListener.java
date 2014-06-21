package util;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * Created by Cristian on 1/05/14.
 */
public class ListListener implements OnItemClickListener {
        List<RSSitem> listItems;
        Activity activity;
public ListListener(List<RSSitem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
        }

/** Start a browser with url from the rss item.*/
public void onItemClick(AdapterView parent, View view, int pos, long id) {
        // We create an Intent which is going to display data
        Intent i = new Intent(Intent.ACTION_VIEW);
        // We have to set data for our new Intent
        i.setData(Uri.parse(listItems.get(pos).getLink()));
        // And start activity with our Intent
        activity.startActivity(i);
        }
}