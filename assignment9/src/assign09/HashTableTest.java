package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {
	
	HashTable<StudentBadHash, Double> badTable = new HashTable<StudentBadHash, Double>();
	HashTable<StudentBadHash, Double> badTableEmpty = new HashTable<StudentBadHash, Double>();
	StudentBadHash alan = new StudentBadHash(1019999, "Alan", "Turing");
	StudentBadHash ada = new StudentBadHash(1004203, "Ada", "Lovelace");
	StudentBadHash edsger = new StudentBadHash(1010661, "Edsger", "Dijkstra");
	StudentBadHash grace = new StudentBadHash(1019941, "Grace", "Hopper");
	
	HashTable<StudentMediumHash, Double> mediumTable = new HashTable<StudentMediumHash, Double>();
	HashTable<StudentGoodHash, Double> goodTable = new HashTable<StudentGoodHash, Double>();

	@BeforeEach
	void setUp() throws Exception {
		badTable.put(alan, 3.2);
		badTable.put(ada, 3.5);
		badTable.put(edsger, 3.8);
		badTable.put(grace, 4.0);

	}
	
	/**
	 * Helper method which fills an ArrayList with badHash Students
	 * 
	 * @param amount number of badHash Students to add to ArrayList
	 * @return ArrayList of badHash Students
	 */
	private ArrayList<StudentBadHash> badHashStudentGenerator(int amount) {
		ArrayList<StudentBadHash> list = new ArrayList<StudentBadHash>();
		for(int i = 0; i < amount; i++) {
			Random rand = new Random();
			list.add(new StudentBadHash(rand.nextInt(1999999), "First", "Last"));
		}
		
		return list;
	}
	
//-----------------------put Tests Begin------------------------------
	@Test
	void testAddRequiresGrowthBad() {
	HashTable<StudentBadHash, Double> badTableLimitedCapacity = new HashTable<StudentBadHash, Double>(2);
	ArrayList<StudentBadHash> list = badHashStudentGenerator(25);	
	
		//Initial Size is 0
		assertTrue(badTableLimitedCapacity.size() == 0);
		//Initial Capacity is 2
		assertTrue(badTableLimitedCapacity.capacity == 2);
		//Add students from badHash ArrayList
		for(StudentBadHash student : list) {
			badTableLimitedCapacity.put(student, 4.0);
		}
		
		//Size after growing and adding should be 25
		assertTrue(badTableLimitedCapacity.size() == 25);
		//Grown Capacity should now be 4 after doubling
		assertEquals(4, badTableLimitedCapacity.capacity);

	}
	
	@Test
	void testAddRequiresGrowthGood() {
	HashTable<StudentBadHash, Double> badTableLimitedCapacity = new HashTable<StudentBadHash, Double>(2);
	ArrayList<StudentBadHash> list = badHashStudentGenerator(25);	
	
		//Initial Size is 0
		assertTrue(badTableLimitedCapacity.size() == 0);
		//Initial Capacity is 2
		assertTrue(badTableLimitedCapacity.capacity == 2);
		//Add students from badHash ArrayList
		for(StudentBadHash student : list) {
			badTableLimitedCapacity.put(student, 4.0);
		}
		
		//Size after growing and adding should be 25
		assertTrue(badTableLimitedCapacity.size() == 25);
		//Grown Capacity should now be 4 after doubling
		assertEquals(4, badTableLimitedCapacity.capacity);

	}
	
	@Test
	void testAddToTableWithItems() {
		StudentBadHash mike = new StudentBadHash(12319234, "Mike", "Phelps");
		badTable.put(mike, 100.0);
		assertTrue(badTable.containsKey(mike));
	}
	
	
	@Test
	void testAddToEmpty() {
		badTableEmpty.put(alan, 100.0);
		assertTrue(badTableEmpty.containsKey(alan));
	}
	
//-----------------------put Tests End-------------------------------
	
	
	
//-----------------------remove Tests Start--------------------------
	
	
	
//-----------------------remove Tests End----------------------------

	
//-----------------------containsKey Tests Start---------------------
	

	
//-----------------------containsKey Tests End-----------------------
	
	
	
//-----------------------containsValue Tests Start-------------------
	
	
	
//-----------------------containsValue Tests End---------------------
	
	
	
//-----------------------entries Tests Start-------------------------
	
	
	
//-----------------------entries Tests End---------------------------	
	
	
	
//-----------------------get Tests Start-----------------------------
	
	
	
//-----------------------get Tests End-------------------------------	
}
