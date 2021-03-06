package main.java.types;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * XML Document
 * gets data from the BBC Rss feed, 
 * Converts info into story objects
 * stores into list of stories
 */
public class BBCDoc extends XMLDoc {

	//link to bbc rss feed
	public final String url = "http://feeds.bbci.co.uk/news/rss.xml";

	//parse rss feed for stories
	@Override
	public void parseXml() {
		Document xmlReader = getXML(this.url);

		NodeList nList = xmlReader.getElementsByTagName("item");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			NeverNullString uri = new NeverNullString(eElement.getElementsByTagName("link").item(0).getTextContent());
			NeverNullString title = new NeverNullString(eElement.getElementsByTagName("title").item(0).getTextContent());
			NeverNullString desc = new NeverNullString(eElement.getElementsByTagName("description").item(0).getTextContent());
			NeverNullString imgUri = new NeverNullString((eElement.getElementsByTagName("media:thumbnail").item(0)).getAttributes().getNamedItem("url").getNodeValue());
			Story item = new Story(uri, title, desc, imgUri);
			super.add(item);
		}

	}
}
