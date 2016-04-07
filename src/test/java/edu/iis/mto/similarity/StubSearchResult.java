package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;

public class StubSearchResult implements SearchResult {
	
	private static final int NOT_FOUND = -1;
	private int position = NOT_FOUND;

	public boolean isFound() {
		return position != NOT_FOUND;
	}

	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}

}
