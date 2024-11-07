import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


//
//  Did not finish Program 1 in time, so submitted what I had.  At first struggled with MinPQ, so I used a HashMap, but could not get it working.
//	This time I read the input file and used 1 MinPQ, got it to work, and then began with Lab 5 from there.  No real differences between the Drivers
//	besides renaming MinPQ to heapMinPQ.
//
public class Driver {
	public static void main(String[] args) throws IOException {
		heapMinPQ<SingleDay> mpq = new heapMinPQ();						// Changed the name of MinPQ to heapMinPQ
		BST<Long, SingleDay> daysTree = new BST();						
		Boolean firstTime = true;
		String country = new String("");
		
		BufferedReader in = new BufferedReader(new FileReader("owid-covid-data.csv"));			// New buffer reader to read csv file
			String line = in.readLine();																					// skip first line
			
			while ((line = in.readLine()) != null) {
				
				String[] dayString = line.split(",");
				SingleDay day = new SingleDay(dayString);
				
				
				if (firstTime) {
					mpq.insert(day);
					country = day.getCountry();
					firstTime = false;
				} else if (country.equals(day.getCountry())) {
					mpq.insert(day);	
					
					if (mpq.size() > 3) {mpq.delMin();}
				
				} else {
					while (mpq.size() != 0) {
						SingleDay minSD = mpq.delMin();
						daysTree.put(minSD.getNewCases(), minSD);	
					}
					mpq.insert(day);
					country = day.getCountry();
				}
			}
			in.close();
		
			while(!mpq.isEmpty()) {
				SingleDay day = mpq.delMin();
				daysTree.put(day.getNewCases(), day);
			}
			
			FileWriter myWriter = new FileWriter("out_lab6.txt");
				for (Long s : daysTree.OrderedTraversal("In")) {
					SingleDay outDay = daysTree.get(s);
					outDay.printDay();
					myWriter.write("New Cases: " + outDay.getNewCases() + " at " + outDay.getCountry() + "/" + outDay.Continent() + " on " + outDay.Date() + " Total: " + outDay.TotalCases() + " Pop: " + outDay.Population() + "\n");
				}
			 myWriter.close();
			
		
	}
}
