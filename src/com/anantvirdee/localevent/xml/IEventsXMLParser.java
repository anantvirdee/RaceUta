package com.anantvirdee.localevent.xml;

import java.io.IOException;
import java.net.URL;

import com.anantvirdee.localevent.datamodel.EventCollection;

public interface IEventsXMLParser {
	
	public EventCollection parserFromURL (URL url) throws IOException, EventsParseException;
	
}

