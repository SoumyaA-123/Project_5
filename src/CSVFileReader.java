
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {

	List<State> stateList = new ArrayList<State>();

	public List<State> CSVReader(String path, String filename) throws IOException{
		String line = "";
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path + filename));
			State stateobject = null;
			while ((line = br.readLine()) != null) {
				stateobject = new State();
				if (isHeading(line))
					continue;

				String[] values = line.split(",");
				// System.out.println(values);
				stateobject.setName(values[0]);
				stateobject.setCapitol(values[1]);
				stateobject.setRegion(values[2]);
				stateobject.setSeats(Integer.parseInt(values[3]));
				stateobject.setPopulation(Long.parseLong(values[4]));
				stateobject.setCovCases(Long.parseLong(values[5]));
				stateobject.setCovDeaths(Long.parseLong(values[6]));
				stateobject.setMedHouseIncome(Long.parseLong(values[7]));
				stateobject.setViolCrimeRate(Float.parseFloat(values[8]));
				stateList.add(stateobject);
			}
		
		return stateList;
	}

	private static boolean isHeading(String line) {

		return line.substring(0, 5).equals("State");
	}

}
