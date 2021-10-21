import java.text.DecimalFormat;



// class of hashTable
public class HashTable {
	private Node head;
	private Node tail;
	@SuppressWarnings("unused")
	private int size;
	// to round off deadths rate
	DecimalFormat roundoff = new DecimalFormat("#.####"); 
	
	// array of linkedlist
	Node[] table;
	
	// size of hash table
	private static final  int TABLE_SIZE=101;
	
	private class Node {
 		String name;
 		long population;
 		long deaths;
 		Node nextNode;
 		
 		public Node(String name, long population, long deaths) {
 			this.name = name;
 			this.population = population;
 			this.deaths = deaths;
 		}
 		
 		public Node() {
			super();
			// TODO Auto-generated constructor stub
		}

		@SuppressWarnings("unused")
		public void printNode() {
 			System.out.printf("%-30s %-20.2f\n", name, (double) deaths / population);
 		}
 	}
	
	// constructor
	HashTable() {
		table = new Node[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = null;
		}
			
	}

	// hash function to calculate index
	private int hashFunction(String stateName, long population, long deaths) {
		int position;
		long unicodeSum = 0;
		for (int i = 0; i < stateName.length(); i++) {
			char ch = stateName.charAt(i);
			unicodeSum = unicodeSum + (int) ch;
		}
		position = (int) (unicodeSum + population + deaths) % TABLE_SIZE;
		return position;
	}

	// to insert node in table
	public void insert(String state, long population, long deaths) {
		int index = hashFunction(state, population, deaths);
		Node entry = new Node(state, population, deaths);
		
		if(table[index]==null) {
			head = new Node();
			tail = new Node();
			table[index]=entry;
			head.nextNode= entry;
			tail.nextNode=entry;
			size++;
			System.out.println(entry.name + " is inserted to hash table ");
		}
		// handle collision by adding into end of linked list
		else {
			Node temp = table[index];
			// walk to end
			while(temp.nextNode!=null) {
				temp = temp.nextNode;
			}
			// add our new entry there
			temp.nextNode = entry;
			tail.nextNode=entry;
			size++;
			System.out.println(entry.name + " is inserted to hash table ");
		}

	}
	public void csvReaderInsert(String state, long population, long deaths) {
		int index = hashFunction(state, population, deaths);
		Node entry = new Node(state, population, deaths);
		
		if(table[index]==null) {
			head = new Node();
			tail = new Node();
			table[index]=entry;
			head.nextNode= entry;
			tail.nextNode=entry;
			size++;
			
		}
		// handle collision by adding into end of linked list
		else {
			Node temp = table[index];
			// walk to end
			while(temp.nextNode!=null) {
				temp = temp.nextNode;
			}
			// add our new entry there
			temp.nextNode = entry;
			tail.nextNode=entry;
			size++;
			
		}

	}

	// to find value in hash table
	public int find(String state, long population, long deaths) {
		// get index
		int index = hashFunction(state, population, deaths);
		
		// get current list of entries
		Node temp=table[index];
		
		// if we have existing entries against this key
		if(temp != null) {
			
			while(! (temp.name.equals(state) && temp.population == population && temp.deaths == deaths)
					&& temp.nextNode != null )
			{
			
				temp = temp.nextNode;
			}
			// return value
			System.out.println("\n"+temp.name + " is found at index " + index +" with DR of " + roundoff.format(deathRate(temp.deaths,temp.population)));
			return index;
		}
		return -1;
	}

	
	// to delete value from ;hash table
	public void delete(String state, long population, long deaths) {
		int index=hashFunction(state,population,deaths);
		Node start=table[index];
		Node end=start;
		if(start.name.equals(state) && start.population == population && start.deaths == deaths) {
			size --;
			table[index]=start.nextNode;
			 System.out.println(state+" is deleted from hashtable");
			return;
		}
		while (end.nextNode != null && ! (end.name.equals(state) && end.population == population && end.deaths == deaths))
		{  
			end = end.nextNode;
		
		}
		if (end.nextNode == null)
        {
            System.out.println("\n"+end.name + " is not a state");
            return;
        }
		size--;
		if (end.nextNode.nextNode == null)
        {
            end.nextNode = null;
            return;
        }
        end.nextNode = end.nextNode.nextNode;
        table[index] = start;
        System.out.println(state+" is deleted from hashtable");

	}

	// to display hash table
	public void display() {
		 System.out.println();
	        for (int i = 0; i < table.length; i++)
	        {	
	        	System.out.print (i + ".     ");
	        	if(table[i]!=null) {              
		            Node start = table[i];
		            while(start != null)
		            {
		                System.out.println(start.name +"     " + roundoff.format(deathRate(start.deaths,start.population)));
		                System.out.print("         ");
		                start = start.nextNode;
		            }
		            System.out.println();
	        	}else {
	        		  System.out.println("Empty");
	        	}
	            
	        }
	}

	// to print empty and collisions value
	public void printEmptyAndCollisions() {
		int emptyNodes=0;
		int collisionNodes=0;
			for(int i=0;i<table.length;i++) {
				if(table[i]==null) {
					emptyNodes++;
				}else {
					Node temp=table[i];
					if(temp.nextNode!=null) {
						collisionNodes++;
					}
				}
			}
			System.out.println("\nThere are "+emptyNodes+" empty cells and "+collisionNodes+" collisions in the hash table");
	}
	
	// to calculate deadth rate
	public double deathRate(long deaths, long population) {
		// you can change death rate formula from here
		double dr= ((double)deaths/(double)population);
		return dr*100000;
	}
	
// to get prime capacity 
//	 private int getNextPrime(int num) {
//	        for (int i = num; ; i++) {
//	            if (isPrimeNumber(i)) {
//	                return i;
//	            }
//	        }
//	    }
//	 private boolean isPrimeNumber(int capacity) {
//
//	        for (int i = 2; i * i < capacity; i++) {
//	            if (capacity % i == 0) {
//	                return false;
//	            }
//	        }
//	        return true;
//	    }
}
