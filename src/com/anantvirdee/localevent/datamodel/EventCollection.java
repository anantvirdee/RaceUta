package com.anantvirdee.localevent.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class EventCollection {
	
	//composition
	private ArrayList<Event> mEventsList = new ArrayList<Event>();
    private ListIterator<Event> mListIterator = mEventsList.listIterator();	
	
	public EventCollection() {
		super();
	}
    
    public Event add(Event ent) { 
    		//while (hasNext()) { 
    			//try { getNext(); } catch (Exception e) {} 
    		//} // move to end of list
    		mListIterator.add( ent ); 
    		return ent;
    }

    public int getCount() { return mEventsList.size(); }

    public boolean hasNext() { return mListIterator.hasNext(); }

    public Event getNext() { 
    		if ( hasNext() ) { 
    			return mListIterator.next(); 
    		} else { 
    			return null; 
    		}
    }

    public void remove() { mListIterator.remove(); }

    //public void reset() { entriesListIterator = entries.listIterator(); }

    public Event getEntryAt(int position) {
    	if (position < 0 || position >= getCount()) {
    		return null;
    	} else {
    		return mEventsList.get(position);
    	}
    }
    
    public void sort() {
    	Collections.sort(mEventsList);
    }
    
    /**
     * TODO: testing
     * 
     * Remove item at position index
     * @param index
     *
    public void remove(int index) {
    	entries.remove(index);
    }*/
}
