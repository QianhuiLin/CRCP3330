import java.util.ArrayList;

public class MarkovGenerator<T> extends ProbabilityGenerator<T>{
	
	ArrayList<ArrayList<Integer>> transitionTable;
	
	MarkovGenerator(){
		super();
		
		transitionTable = new ArrayList();
	}
	
	
	
	void train(ArrayList<T> newTokens) {
		int lastIndex = -1;
		
		for(int i=0; i < newTokens.size(); i++) {
			
			int tokenIndex = alphabet.indexOf(newTokens.get(i));
	
			
			
			if(tokenIndex==-1) {
				
				//  1. tokenIndex = size of alphabet
				tokenIndex = alphabet.size();
				
				//2. add a new row to the transition table (expand vertically)
                //That is, create a new array that is the size of the alphabet 
                //Then add to your transition table (the array of arrays)
				ArrayList<Integer> row = new ArrayList();
				for(int m = 0; m < alphabet.size(); m++) {
					row.add(0);
				}
				transitionTable.add(row);
				
				
               // 3. add a new column (expand horizontally)
               // That is, add a 0 on to all of the arrays in the transition table.
               // That is, for each array in the transition table add 0.
				
				for(int n = 0; n < transitionTable.size(); n++) {
					ArrayList<Integer> row1 = transitionTable.get(n);
					row1.add(0);
				}
				
				
				
				
				
				//4. add the token to the alphabet array 
				alphabet.add(newTokens.get(i));
				
			}
			
			if(lastIndex > -1) {
				
				// 1.	Use lastIndex to get the correct row (array) in your transition table.
				ArrayList<Integer> getRow = transitionTable.get(lastIndex);
				
				
				//2.	Use the tokenIndex to index the correct column (value of the row you accessed)
			    Integer getValue = getRow.get(tokenIndex);
			    
			    //3.	Add 1 to that value.
			    getRow.set(tokenIndex, getValue+1);
			}
				
			
			
			lastIndex = tokenIndex;
			
		}

	}
	
	
	ArrayList<Float> getSum() {
		ArrayList<Float> sum = new ArrayList<Float>();
		float oneSum=0;
		for(int i =0; i<transitionTable.size();i++) {
			oneSum = 0;
			for(int j =0; j<transitionTable.get(i).size();j++) {
		     
		     oneSum = oneSum + transitionTable.get(i).get(j);
		     
			}
			
			sum.add(oneSum);
		}

		return sum;
	}

	ArrayList<ArrayList<Float>> probabilityTable() {
		
		ArrayList<ArrayList<Float>> probabilities = new ArrayList();
		
        
    	ArrayList<Float> myRow = new ArrayList();;

            int index = 0;

        	for(int j = 0; j < transitionTable.size();j++) {
            float probability = transitionTable.get(index).get(j)/ getSum().get(index);
        	myRow.add(probability);
        	}
        	probabilities.add(myRow);
        
	
        
		return probabilities;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	T generator(T initToken)
	{
		
		//do something here
         float sum = 0;
         float index = 0;
         
         index = alphabet.indexOf(initToken);
         
         for(int i = 0; i < alphabet_counts.size();i++) {
        	 
        	 sum = sum + alphabet_counts.get(i);
        	
        }
  
        
		
		return super.generator();
	}
	
	

       ArrayList<T> generator( int length, T initToken){
		
		ArrayList<T> newSequence = new ArrayList<T>();
		for(int i = 0; i<length;i++)
		{
			newSequence.add(generator(initToken));
		}
		return newSequence;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
