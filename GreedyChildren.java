
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//Greedy Algorithm implementation
public class GreedyChildren {
	
	public int greedyFactor[]; //Stores greedy factor of each child
	public int sweetFactor[]; //Stores sweetness factor of each piece of candy
	public int happy = 0; //Number of happy children that will get the candy that satisfies their greedy factor
	public int angry = 0; //Number of angry children that will get the candy that doesn't satisfy their greedy factor
	
	public GreedyChildren(int piecesOfCandy, int numberOfChildren, String gFile, String sFile) {
		greedyFactor = new int[numberOfChildren];
		sweetFactor = new int[piecesOfCandy];
		read(gFile, greedyFactor);
        read(sFile, sweetFactor);
		
	}
	
	public void read(String fileName, int[] array) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < array.length) {
                array[index] = Integer.parseInt(line);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void greedyCandy() {
		//Sort, so it easier to determine which candies, with higher sweetness, to give to greediest children
		Arrays.sort(greedyFactor);
        Arrays.sort(sweetFactor);

        int childIndex = 0; //Keeps track of which child we are currently trying to give candy to
        for (int i = 0; i < sweetFactor.length; i++) { //Iterate through the candy sweetness levels
            if (childIndex < greedyFactor.length && sweetFactor[i] >= greedyFactor[childIndex]) { //Checks whether a candy can be given to a child. Then it checks whether the current candy's sweetness is greater than or equal to current child's greediness
                happy++; //Increase happy if, if statement is true
                childIndex++; //Increase counter index
            }
        }
        angry = greedyFactor.length - happy; //Subtract happy from total number of children, leaving us with angry
    }
	
	
	public void display() {
		System.out.println("There are " + happy + " happy children.");
        System.out.println("There are " + angry + " angry children.");
	    }
	
	
	
}
