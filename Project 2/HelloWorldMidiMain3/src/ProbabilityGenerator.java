import java.util.ArrayList;

public class ProbabilityGenerator<T> {
	
	ArrayList<T> alphabet;
	ArrayList<Integer>alphabet_counts;
	ProbabilityGenerator(){
		alphabet = new ArrayList<T>();
		alphabet_counts = new ArrayList<Integer>();
		
		
	}
	
	
	
	void train(T[] newTokens) {
		
		for(int i=0; i < newTokens.length; i++) {
			
		int index = alphabet.indexOf(newTokens[i]);
		if(index<0) {
			index = alphabet.size();
			alphabet.add(newTokens[i]);
			alphabet_counts.add(0);
		}
		
			alphabet_counts.set(index,alphabet_counts.get(index)+1);
		}
	}
	
	float getTotal() {
		
		float total= 0;
		
		for(int i = 0; i<alphabet.size();i++) {
			total = total + alphabet_counts.get(i) ;
		}
		
		return total;
	}
	
	
	
	//get Probability
	ArrayList<Float> getProbability() {
		
		ArrayList<Float> probabilities = new ArrayList<Float>();
		
		for(int i=0; i<alphabet.size();i++) {
			float probability = (float)alphabet_counts.get(i) / getTotal();
			
            probabilities.add(probability);

		}
		
		return probabilities;
		
	}
	
	ArrayList<Float> getProbabilityDistribution() {
		
		ArrayList<Float> sumProbabilities = new ArrayList<Float>();
		float sum = 0;
		for(int i = 0;i<getProbability().size();i++) {
		     sum = getProbability().get(i) + sum;
		     sumProbabilities.add(sum);
		}
		
		return sumProbabilities;
		
	
	}
	T generator()
	{
		T newToken = null;
		//do something here
		
		
		
        float rIndex = (float)Math.random();
        
       
		int index = 0;
		boolean found = false;
		while(!found && index < getProbabilityDistribution().size()) {
            
			found = (rIndex < getProbabilityDistribution().get(index));
        	index ++;
        	
        }
  
        newToken = alphabet.get(index-1);

			
		return newToken;
	}
	
	void printAlphabet() {
	 System.out.println(alphabet);
	 
		
	}
	
	
	ArrayList<T> generator( int length){
		
		ArrayList<T> newSequence = new ArrayList<T>();
		for(int i = 0; i<length;i++)
		{
			newSequence.add(generator());
		}
		return newSequence;
	}
		
	}
