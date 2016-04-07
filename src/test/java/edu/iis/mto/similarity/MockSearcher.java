package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class MockSearcher implements SequenceSearcher {
	
	private int key;
	private int[] seq;
	private int counter;

	public SearchResult search(int key, int[] seq) {
		// Mock data
		this.key = key;
		this.seq = seq;
		++counter;
		
		// implementation
		StubSearchResult result = new StubSearchResult();
		for (int i = 0; i < seq.length; ++i) {
			if (seq[i] == key) {
				result.setPosition(i);
			}
		}
		return result;
	}

	public int getKey() {
		return key;
	}

	public int[] getSeq() {
		return seq;
	}

	public int getCounter() {
		return counter;
	}

}
