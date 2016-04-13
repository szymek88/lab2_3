package edu.iis.mto.similarity;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class SimilarityFinderTest {

	SimilarityFinder similarityFinder;
	MockSearcher searcher;
	
	@Before
	public void setUp() {
		searcher = new MockSearcher();
		similarityFinder = new SimilarityFinder(searcher);
	}
	
	@Test
	public void shouldReturnOneForEmptySequences() {
		int[] seq1 = new int[0];
		int[] seq2 = new int[0];
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(1.0d));
	}
	
	@Test
	public void shouldReturnZeroForEmptyAndSingleElemSequences() {
		int[] seq1 = new int[0];
		int[] seq2 = new int[] {1};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(0.0d));
	}
	
	@Test
	public void shouldReturnOneForSameSingleElemSequences() {
		int[] seq1 = new int[] {1};
		int[] seq2 = new int[] {1};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(1.0d));
	}
	
	@Test
	public void shouldReturnZeroForDiffSingleElemSequences() {
		int[] seq1 = new int[] {2};
		int[] seq2 = new int[] {1};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(0.0d));
	}
	
	@Test
	public void shouldReturnOneForSameMultiElemSequences() {
		int[] seq1 = new int[] {1,2,3};
		int[] seq2 = new int[] {2,3,1};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(1.0d));
	}
	
	@Test
	public void shouldReturnHalfForMultiElemSequences() {
		int[] seq1 = new int[] {1};
		int[] seq2 = new int[] {1,2};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(0.5d));
	}
	
	@Test
	public void shouldReturnZeroForDiffMultiElemSequences() {
		int[] seq1 = new int[] {1,2,3,4};
		int[] seq2 = new int[] {5,6,7,8,9};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(0.0d));
	}
	
	@Test
	public void shouldNotCallSearchMethod() {
		int[] seq1 = new int[0];
		int[] seq2 = new int[] {1,2};
		similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(searcher.getCounter(), equalTo(0));
	}
	
	@Test
	public void shouldCallSearchMethodOnce() {
		int[] seq1 = new int[] {1};
		int[] seq2 = new int[] {1,2};
		similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(searcher.getCounter(), equalTo(1));
	}
	
	@Test
	public void shouldCallSearchMethodThreeTimes() {
		int[] seq1 = new int[] {1,2,3};
		int[] seq2 = new int[] {1,2};
		similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(searcher.getCounter(), equalTo(3));
	}
	
	@Test
	public void shouldCallSearchMethodWithSpecifiedKeyArg() {
		int key = 1;
		int[] seq1 = new int[] {key};
		int[] seq2 = new int[] {2,3};
		similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(searcher.getKey(), equalTo(key));
	}
	
	@Test
	public void shouldCallSearchMethodWithSpecifiedSeqArg() {
		int[] seq1 = new int[] {1};
		int[] seq2 = new int[] {1,2};
		similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(searcher.getSeq(), equalTo(seq2));
	}
	
}
