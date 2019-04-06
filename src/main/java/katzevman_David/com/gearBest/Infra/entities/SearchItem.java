package katzevman_David.com.gearBest.Infra.entities;

public class SearchItem {

	public String searchTerm;
	public int itemIndex;
	public String expectedResult;
	
	public SearchItem(String searchTerm, int itemIndex, String expectedResult) {
		this.searchTerm = searchTerm;
		this.itemIndex = itemIndex;
		this.expectedResult = expectedResult;
	}
	
	public String toString() {
		return "Search Item: " + searchTerm + "; check index: " + itemIndex + "; expected result: " + expectedResult;
	}
}
