import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project5 {
	public static void main(String args[]) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		List<State> statelist = new ArrayList<State>();
		CSVFileReader reader = new CSVFileReader();
 		
		// if csv file present in your project 
		File f1=new File(".");
		String path=f1.getAbsolutePath().replace(".", "").trim() ;
		Boolean fileFlag=true;
		System.out.println("COP3530 Project 5");
		System.out.println("Hash Tables");
		System.out.print("Enter the file name:");
		while(fileFlag) {
			try {
				// csv reader
				String filename = sc.next();
				statelist = reader.CSVReader(path, filename);
				fileFlag=false;
			}catch(Exception e ) {
				System.out.print("Incorrect File Name, Please Enter again:");
			}
		}
		
		HashTable stateHashTable = new HashTable();
		int i=0;
		for(State s: statelist ) {
			// hashtable insert method to add all state object into hash table
			stateHashTable.csvReaderInsert(s.getName(), s.getPopulation(), s.getCovDeaths());
			i++;
			
		}
		
		
		System.out.println("\nThere were "+i+" state records read into the hash table.");
		
		
		boolean flag = true;
		// user input
		
		// start of while loop
		while (flag) {
			System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n1) Print hash table");
			System.out.println("2) Delete a state of a given name ");
			System.out.println("3) Insert a state of a given name");
			System.out.println("4) Search and print a state and its DR for a given name");
			System.out.println("5) Print numbers of empty cells and collisions");
			System.out.println("6) Exit");
			System.out.print("Enter your choice:");
			boolean checkchoice = true;
			int choice = 0;
			while (checkchoice) {
				try {
					choice = sc.nextInt();
					if (choice >= 1 && choice <= 6) {
						checkchoice = false;
					} else {
						System.out.print("Invalid choice, enter 1-6: ");
					}
				} catch (Exception e) {
					System.out.println("Invalid choice, enter 1-6:");
					sc.next();
				}

			}
			switch (choice) {
			case 1:
				// print hash table here
				stateHashTable.display();
				break;
			case 2:
				// delete a node 
				State s=getStateObjectOnName(statelist);
				if(s!=null) {
					stateHashTable.delete(s.getName(), s.getPopulation(), s.getCovDeaths());
				}
					
				break;
			case 3:
				//insert a state of given name 
				State e=getStateObjectOnName(statelist);
				if(e!=null) {
					stateHashTable.insert(e.getName(), e.getPopulation(), e.getCovDeaths());
				}
				break;
			case 4:
				// Search and print a state and its DR for a given name
				State p=getStateObjectOnName(statelist);
				if(p != null) {
					stateHashTable.find(p.getName(), p.getPopulation(), p.getCovDeaths());
				}
				break;
			case 5:
				// Print numbers of empty cells and collisions
				stateHashTable.printEmptyAndCollisions();
				break;
			case 6:
				//Exit
				System.out.println("\n \nHave a good day!");
				System.exit(0);
				break;
			default:
				System.out.println("\n Invalid choice, enter 1-6:");

			}

		} // while loop end
		
	}
	/*
	 * As there is no mention in project docx how to insert population and deadths to calculate hashindex because we are 
	 * taking only state name as input that's why we are using this function to find population and deadths on state name inputed by user
	 * and reading csv file again and again make it cumbersome so that we read csv once and make a list of states objects to make it little easier.
	 * 
	 * */
	public static State getStateObjectOnName(List<State> list) {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		System.out.print("\nEnter state name:");
		String stateName=inp.next();
		for(State obj: list) {
			if(obj.getName().equals(stateName)) {
				return obj;
			}
		}
		// if state is not exist 
		System.out.println("\n"+stateName+" is not a state");
		return null;
	}

}
