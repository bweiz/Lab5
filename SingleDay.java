
public class SingleDay implements Comparable<SingleDay> {
	
	private String continent;
	private String country;
	private String date;
	private Long total_cases;
	private Long new_cases;
	private Long population;
	
	public SingleDay(String[] item) {
		this.continent = item[0];
		this.country = item[1];
		this.date = item[2];
		this.total_cases = Long.parseLong(item[3]);
		this.new_cases = Long.parseLong(item[4]);
		this.population = Long.parseLong(item[5]);
	}
	
	public SingleDay() {
		country = new String("none");
	}
	
	public int compareTo(SingleDay Day) {
		if (this.new_cases < Day.new_cases) return -1;
		else if (this.new_cases > Day.new_cases) return 1;
		return 0;
	}
	
	public long getNewCases() {
		return new_cases;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String Continent() {
		return continent;
	}
	
	public String Date() {
		return date;
	}
	
	public Long TotalCases() {
		return total_cases;
	}
	
	public Long Population() {
		return population;
	}
	public void printDay() {
		System.out.println("New Cases: " + new_cases + " at " + country + "/" + continent + " on " + date + " Total " + total_cases + " Pop: " + population);
	}
	
}
