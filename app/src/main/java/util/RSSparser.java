package util;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import util.RSSitem;

/**
 * SAX tag handler. The Class contains a list of RssItems which is being filled while the parser is working
 * @author ITCuties
 */
public class RSSparser extends DefaultHandler {

    // List of items parsed
    private List<RSSitem> rssItems;
    // We have a local reference to an object which is constructed while parser is working on an item tag
    // Used to reference item while parsing
    private RSSitem currentItem;
    // We have two indicators which are used to differentiate whether a tag title or link is being processed by the parser
    // Parsing title indicator
    private boolean parsingTitle;
    // Parsing link indicator
    private boolean parsingLink;
    private boolean parsingDescription;

    public RSSparser() {
        rssItems = new ArrayList();
    }
    // We have an access method which returns a list of items that are read from the RSS feed. This method will be called when parsing is done.
    public List<RSSitem> getItems() {
        return rssItems;
    }
    // The StartElement method creates an empty RssItem object when an item start tag is being processed. When a title or link tag are being processed appropriate indicators are set to true.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RSSitem();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
        } else if ("link".equals(qName)) {
            parsingLink = true;
        } else if ("description".equals(qName)) {
        parsingDescription = true;
        }
    }
    // The EndElement method adds the  current RssItem to the list when a closing item tag is processed. It sets appropriate indicators to false -  when title and link closing tags are processed
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;
        } else if ("link".equals(qName)) {
            parsingLink = false;
        } else if ("description".equals(qName)) {
        parsingDescription = false;
        }
    }
    // Characters method fills current RssItem object with data when title and link tag content is being processed
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        }else if (parsingDescription) {
            if (currentItem != null) {
                currentItem.setDescription(new String(ch, start, length));
                parsingDescription = false;
            }
        }
    }
}