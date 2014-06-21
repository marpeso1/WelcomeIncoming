package util;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/**
 * Created by Cristian on 1/05/14.
 */
public class RssReader {
    // Our class has an attribute which represents RSS Feed URL
    private String rssUrl;
    /**
     * We set this URL with the constructor
     */
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }
    /**
     * Get RSS items. This method will be called to get the parsing process result.
     * @return
     */
    public List<RSSitem> getItems() throws Exception {
        List<RSSitem> list = new ArrayList<RSSitem>();
        try{
        // At first we need to get an SAX Parser Factory object
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Using factory we create a new SAX Parser instance
        SAXParser saxParser = factory.newSAXParser();
        // We need the SAX parser handler object
        RSSparser handler = new RSSparser();
        // We call the method parsing our RSS Feed
        saxParser.parse(rssUrl, handler);
        list =  handler.getItems();
        // The result of the parsing process is being stored in the handler object
        }catch (Exception e){
            Log.e("ReaderRSS", e.getMessage());
        }
        return list;
    }
}
