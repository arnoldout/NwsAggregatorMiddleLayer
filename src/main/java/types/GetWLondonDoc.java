package main.java.types;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

/*
 * XML Document
 * gets data from the getWestLondon Sports Rss feed, 
 * Converts info into story objects
 * stores into list of stories
 */
public class GetWLondonDoc extends XMLDoc{
	//link to GetWestLondon Sports Rss 
	public final String url = "http://www.getwestlondon.co.uk/sport/?service=rss";
	//parse rss feed
	@Override
	public void parseXml() {
		try {
			URL feedSource = new URL(url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new InputStreamReader(feedSource.openStream()));

			List<SyndEntry> items = feed.getEntries();
			for (SyndEntry entry : items) {
				NeverNullString uri = new NeverNullString(entry.getUri());
				NeverNullString title = new NeverNullString(entry.getTitle());
				NeverNullString desc = new NeverNullString(entry.getDescription().getValue(), "No Description Provided");
				NeverNullString imgUri = new NeverNullString(entry.getEnclosures().get(0).getUrl());
				Story item = new Story(uri, title, desc, imgUri);
				super.add(item);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
