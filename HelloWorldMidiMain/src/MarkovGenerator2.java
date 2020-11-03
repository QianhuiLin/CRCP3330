import java.util.ArrayList;

public class MarkovGenerator2<T> extends ProbabilityGenerator<T>{
	
	    
        int orderM;
		ArrayList<ArrayList<Integer>> transitionTable;
		ArrayList<ArrayList<T>> uniqueAlphabetSequences;
		ArrayList<ArrayList<T>> uniqueAlphaInitToken;
		int fromIndex;
		int toIndex;
	 	MarkovGenerator2(int M){
			super();
			orderM = M;
			transitionTable = new ArrayList();
		}
	 	
	 	void train(ArrayList<T> curSequence,ArrayList<T> inputTokens) {
	 for(int i = orderM - 1; i<inputTokens.size()-1;i++) {		
//1         
		// a.	add the previous tokens to a container (eg ArrayList). 
		// b.	You may do this in a for-loop or use .subList()
			curSequence = new ArrayList<T>(inputTokens.subList(fromIndex,toIndex));
	 		int rowIndex = uniqueAlphabetSequences.indexOf(curSequence);
	 		
//2 		
	 		if(rowIndex == -1) {
	 			
	 			//1. set rowIndex to the size of uniqueAlphabetSequences
	 			rowIndex = uniqueAlphabetSequences.size();
	 			
	 			//2. add the curSequence to uniqueAlphabetSequences
	 			rowIndex = uniqueAlphaInitToken.size();
	 			
	 			//3. add a new row to the transition table the size of the alphabet
	 			uniqueAlphabetSequences.add(curSequence);
	 			uniqueAlphaInitToken.add(curSequence);
	 		

	 		ArrayList<Integer> row = new ArrayList<Integer>();
	 		transitionTable.add(row);
	 		
//3	 		
	 		//tokenIndex = the next index of the token in the alphabet (i+1)
	 		int tokenIndex = alphabet.indexOf(inputTokens.get(i+1));
	 		
	 		if(tokenIndex == -1) {
	 			
	 			//1. tokenIndex = size of the alphabet 
	 			tokenIndex = alphabet.size();
	 			
	 			//2. add the token to the alphabet
	 			alphabet.add(inputTokens.get(i+1));
	 			
	 			//3. expand transitionTable horizontally
	 			alphabet_counts.add(0);
	 			
	 			
	 		}

//4
	 		//a.	Get the row using rowIndex
			//b.	Get the column using tokenIndex
			//c.	Add one to that value retrieved from the transition table
	 		ArrayList row2 = transitionTable.get(rowIndex);
	 		for(int j = 0; j <row2.size();j++) {
	 			if(j == tokenIndex) {
	 				
	 			//	row2.set(j, row2.get(j) + 1;
	 				int counts = alphabet_counts.get(j)+1;
	 				alphabet_counts.set(j, counts);
	 			}
	 		}
	 		
	 			
	 		}
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 	}
	 	
	 	}
	 	
}
