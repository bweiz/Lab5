import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class heapSortDriver {
	public static void main(String[] args) throws IOException {
		ArrayList<SingleDay> popAr = new ArrayList<>();
		
		Boolean firstTime = true;
		Long population = (long) 0;
		
		BufferedReader in = new BufferedReader(new FileReader("owid-covid-data.csv"));			// New buffer reader to read csv file
		String line = in.readLine();	
		
		while ((line = in.readLine()) != null) {
			
			String[] dayString = line.split(",");
			SingleDay day = new SingleDay(dayString);
			
			if (firstTime) {
				popAr.add(day);
				population = day.Population();
				firstTime = false;
			}  else if (population.equals(day.Population())) {
				line = in.readLine();
			} else {
				dayString = line.split(",");
				day = new SingleDay(dayString);
				popAr.add(day);
				population = day.Population();
			}
		}
		in.close();
		
		SingleDay a[] = new SingleDay[(popAr.size())];
		a = sort(popAr.toArray(new SingleDay[popAr.size()]));
		
		FileWriter myWriter = new FileWriter("out_lab6_2.txt");
		for (SingleDay s : a) {
			SingleDay outDay = s;
			outDay.printDay();
			myWriter.write("New Cases: " + outDay.getNewCases() + " at " + outDay.getCountry() + "/" + outDay.Continent() + " on " + outDay.Date() + " Total: " + outDay.TotalCases() + " Pop: " + outDay.Population() + "\n");
		}
	 myWriter.close();
		
		
	}
	public static SingleDay[] sort(SingleDay[] a) {
		int z = a.length;
		for (int k = z/2; k >= 1; k--) {
			sink(a,k,z);
		}
		while (z > 1) {
			exchange(a, 1, z--);
			sink(a, 1, z);
		}
		return a;
	}
	private static void sink(SingleDay[] a, int k, int z) {
		while (2*k <= z) {
			int j = 2*k;
			if (j < z && less(a, j, j+1)) j++;
			if (!less(a, k, j)) break;
			exchange(a, k, j);
			k = j;
		}
	}

	private static boolean less(SingleDay[] a, int i, int j) {
		return a[i-1].Population().compareTo(a[j-1].Population()) < 0;
	}
	private static void exchange(Object[] a, int i, int j) {
		Object swap = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = swap;
	}
}


