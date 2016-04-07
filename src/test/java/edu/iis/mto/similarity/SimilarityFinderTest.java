package edu.iis.mto.similarity;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class SimilarityFinderTest {

	SimilarityFinder similarityFinder;
	
	@Before
	public void setUp() {
		similarityFinder = new SimilarityFinder(new MockSearcher());
	}
	
	@Test
	public void shouldReturnOneForTwoEmptySequences() {
		int[] seq1 = new int[0];
		int[] seq2 = new int[0];
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(1.0d));
	}
	
	@Test
	public void shouldReturnZeroForOneEmptySequence() {
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
	public void checkResultForTwoSameMultiElemSequences() {
		int[] seq1 = new int[] {1,2};
		int[] seq2 = new int[] {1,2};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(1.0d));
	}
	
	@Test
	public void checkResultForTwoHalfSameMultiElemSequences() {
		int[] seq1 = new int[] {1,2,3,4};
		int[] seq2 = new int[] {5,4,1,6};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(0.33d));
	}
	
	@Test
	public void checkResultForTwoDiffMultiElemSequences() {
		int[] seq1 = new int[] {1,2,3};
		int[] seq2 = new int[] {4,5,6};
		double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
		
		assertThat(result, equalTo(0.0d));
	}
	
}
