package uta.se1.rss.reader.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import uta.se1.rss.reader.data.Item;

public class FBRssReader {
	
	private String rssUrl;


	public FBRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<Item> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		Parser handler = new Parser();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

}
