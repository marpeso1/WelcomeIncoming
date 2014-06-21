package upv.welcomeincoming.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import util.ListListener;
import util.RSSitem;
import util.RssReader;

public class home_fragment extends Fragment {
    private Activity local;
        @Override
        public View onCreateView( LayoutInflater inflater, ViewGroup  container, Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.fragment_home, container,false);
            local = getActivity();
            /*GetRSSDataTask task = new GetRSSDataTask();
            // Start download RSS task
            task.execute("http://www.upv.es/pls/oalu/sic_rss.rss_ver20?p_rss_feed=0&p_idioma=i&p_miwser=PI");
            return view;*/
            WebView wv = (WebView) view.findViewById(R.id.webView);
            wv.loadUrl("http://www.upv.es/entidades/OPII/index.html");
            return view;
        }
    /*private class GetRSSDataTask extends AsyncTask<String, Void, List<RSSitem> > {
        @Override
        protected List<RSSitem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("ITCRssReader", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("ITCRssReader", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RSSitem> result) {

            // Get a ListView from main view
            ListView itcItems = (ListView) local.findViewById(R.id.listaRSS);
            // Create a list adapter
            ArrayAdapter adapter = new ArrayAdapter<RSSitem>(getActivity(),android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);

            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(result, local));
        }
    }*/
}
