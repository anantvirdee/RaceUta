package com.anantvirdee.localevent.xml;

public class EventsParseException extends Exception {
    private static final long serialVersionUID = 4324798465917855L;

    public EventsParseException() {
		super();
	}
	
	public EventsParseException(String str) {
		super(str);
	}
	
	public EventsParseException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public EventsParseException(Throwable cause) {
		super(cause);
	}
}
