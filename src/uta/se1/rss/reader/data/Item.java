package uta.se1.rss.reader.data;

public class Item {
	
	// item title
	private String title;
	// item link
	private String link;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
