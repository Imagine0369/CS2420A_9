package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {

	HashTable<StudentBadHash, Double> badTable = new HashTable<StudentBadHash, Double>();
	HashTable<StudentBadHash, Double> badTableEmpty;
	StudentBadHash alan = new StudentBadHash(1019999, "Alan", "Turing");
	StudentBadHash ada = new StudentBadHash(1004203, "Ada", "Lovelace");
	StudentBadHash edsger = new StudentBadHash(1010661, "Edsger", "Dijkstra");
	StudentBadHash grace = new StudentBadHash(1019941, "Grace", "Hopper");
	
	HashTable<StudentGoodHash, Double> goodTableEmpty = new HashTable<StudentGoodHash, Double>();
	StudentGoodHash john = new StudentGoodHash(1019999, "John", "Turing");
	StudentGoodHash jacob = new StudentGoodHash(1004203, "Jacob", "Lovelace");
	StudentGoodHash david = new StudentGoodHash(1010661, "Daid", "Dijkstra");
	StudentGoodHash sarah = new StudentGoodHash(1019941, "Sarah", "Hopper");

	StudentMediumHash alex = new StudentMediumHash(1019999, "Alex", "Turing");
	StudentMediumHash noah = new StudentMediumHash(1004203, "Noah", "Lovelace");
	StudentMediumHash bradley = new StudentMediumHash(1010661, "Bradley", "Dijkstra");
	StudentMediumHash stewart = new StudentMediumHash(1019941, "Stewarat", "Hopper");

	HashTable<StudentMediumHash, Double> mediumTable = new HashTable<StudentMediumHash, Double>();
	HashTable<StudentGoodHash, Double> goodTable = new HashTable<StudentGoodHash, Double>();

	@BeforeEach
	void setUp() throws Exception {
		badTableEmpty = new HashTable<StudentBadHash, Double>();
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
		for (int i = 0; i < amount; i++) {
			Random rand = new Random();
			list.add(new StudentBadHash(rand.nextInt(1999999), "First", "Last"));
		}

		return list;
	}

	/**
	 * Helper method which fills an ArrayList with goodHash Students
	 * 
	 * @param amount number of goodHash Students to add to ArrayList
	 * @return ArrayList of goodHash Students
	 */
	private ArrayList<StudentGoodHash> goodHashStudentGenerator(int amount) {
		ArrayList<StudentGoodHash> list = new ArrayList<StudentGoodHash>();
		for (int i = 0; i < amount; i++) {
			Random rand = new Random();
			list.add(new StudentGoodHash(rand.nextInt(1999999), "First", "Last"));
		}

		return list;
	}

//-----------------------clear Tests Begin----------------------------

	@Test
	void testClearCommon() {
		assertTrue(0 == goodTable.size());
		goodTable.put(john, 33.2);
		goodTable.put(jacob, 3.2);
		goodTable.put(david, 4.6);
		goodTable.put(sarah, 1.2);
		assertFalse(0 == goodTable.size());
		
		goodTable.clear();
		assertTrue(0 == goodTable.size());
	}
	
	@Test
	void testClearTwice() {
		goodTable.put(john, 33.2);
		goodTable.put(jacob, 3.2);
		goodTable.put(david, 4.6);
		goodTable.put(sarah, 1.2);
		assertFalse(0 == goodTable.size());
		goodTable.clear();
		assertTrue(0 == goodTable.size());
		
		goodTable.put(david, 3.8);
		assertFalse(0 == goodTable.size());
		
		goodTable.clear();
		assertTrue(0 == goodTable.size());
	}
	
	@Test
	void testClearTwiceOnDouble() {
		goodTable.put(john, 33.2);
		goodTable.put(jacob, 3.2);
		goodTable.put(david, 4.6);
		goodTable.put(sarah, 1.2);
		assertFalse(0 == badTable.size());
		badTable.clear();
		assertTrue(0 == badTable.size());
		
		goodTable.put(john, 3.8);
		goodTable.put(david, 4.0);
		goodTable.put(john, 3.22);
		goodTable.put(sarah, 4.0);
		assertFalse(0 == goodTable.size());
		
		goodTable.clear();
		assertTrue(0 == goodTable.size());
	}
	
	@Test
	void testClearThrice() {
		goodTable.put(john, 33.2);
		goodTable.put(jacob, 3.2);
		goodTable.put(david, 4.6);
		goodTable.put(sarah, 1.2);
		assertFalse(0 == goodTable.size());
		goodTable.clear();
		assertTrue(0 == goodTable.size());
		
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertFalse(0 == goodTable.size());
		
		goodTable.clear();
		assertTrue(0 == goodTable.size());
		
		goodTable.put(david, 4.6);
		goodTable.put(sarah, 1.2);
		assertFalse(0 == goodTable.size());
		
		goodTable.clear();
		assertTrue(0 == goodTable.size());
	}
	
	@Test
	void testClearOnEmpty1() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertFalse(0 == goodTable.size());
		goodTable.clear();
		assertTrue(0 == goodTable.size());
		goodTable.clear();
		assertTrue(0 == goodTable.size());	
	}
	
	@Test
	void testClearOnEmpty2() {
		assertTrue(0 == goodTableEmpty.size());
		goodTableEmpty.clear();
		assertTrue(0 == goodTableEmpty.size());	
	}
	
	@Test
	void testClearGetValueAfter() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertFalse(0 == goodTable.size());
		goodTable.clear();
		assertTrue(0 == goodTable.size());
		
		goodTable.put(sarah, 36.0);
		assertFalse(0 == goodTable.size());
		goodTable.clear();
		assertTrue(0 == goodTable.size());
		assertEquals(null, goodTable.get(sarah));		
	}
	
	@Test
	void testClearInRow() {
		assertTrue(0 == goodTableEmpty.size());
		goodTableEmpty.clear();
		goodTableEmpty.clear();
		goodTableEmpty.clear();
		assertTrue(0 == goodTableEmpty.size());	
		
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertFalse(0 == goodTable.size());
		goodTable.clear();
		goodTable.clear();
		goodTable.clear();
		assertTrue(0 == goodTable.size());
	}

//-----------------------clear Tests End------------------------------	

//-----------------------isEmpty Tests Begin--------------------------

	@Test
	void testIsEmptyOnEmpty() {
		assertTrue(badTableEmpty.isEmpty());
	}

	@Test
	void testIsEmptyOnEmptyAfterAddTrue() {
		assertTrue(badTableEmpty.isEmpty());
		badTableEmpty.put(ada, 33.2);
		assertFalse(badTableEmpty.isEmpty());
	}

	@Test
	void testIsEmptyOnEmptyAfterRemoveTrue() {
		badTableEmpty.put(alan, 22.1);
		assertFalse(badTableEmpty.isEmpty());
		badTableEmpty.remove(alan);
		assertTrue(badTableEmpty.isEmpty());
	}

	@Test
	void testIsEmptyOnEmptyAfterRemoveFalse() {
		badTableEmpty.put(ada, 33.2);
		badTableEmpty.put(edsger, 31.9);
		assertFalse(badTableEmpty.isEmpty());
		badTableEmpty.remove(edsger);
		assertFalse(badTableEmpty.isEmpty());
	}

//-----------------------isEmpty Tests End----------------------------	

//-----------------------size Tests Begin-----------------------------

	@Test
	void testSizeCommon() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertEquals(4, badTable.size());
	}
	
	@Test
	void testSizeEmpty() {
		assertEquals(0, goodTableEmpty.size());
	}
	
	@Test
	void testSizeAfterPutOnEmpty() {
		assertEquals(0, goodTableEmpty.size());
		goodTableEmpty.put(david, 3.2);
		assertEquals(1,goodTableEmpty.size());
	}
	@Test
	void testSizeAfterPutOnNormal() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		assertEquals(3, goodTable.size());
		goodTable.put(sarah, 4.0);
		assertEquals(4,goodTable.size());
	}
	
	@Test
	void testSizeAfterClear() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertEquals(4, goodTable.size());
		goodTable.clear();
		assertEquals(0, goodTable.size());
		assertEquals(goodTableEmpty.size(), goodTable.size());
	}
	
	@Test
	void testSizeAfterRemove() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertEquals(4, goodTable.size());
		goodTable.remove(jacob);
		assertEquals(3, goodTable.size());	
	}
	
	@Test
	void testSizeSequentialPut() {
		assertEquals(0, goodTable.size());
		goodTable.put(john, 3.8);
		assertEquals(1, goodTable.size());
		goodTable.put(jacob, 4.0);
		assertEquals(2, goodTable.size());
		goodTable.put(david, 3.22);
		assertEquals(3, goodTable.size());
		goodTable.put(sarah, 4.0);
		assertEquals(4, goodTable.size());
		goodTable.remove(jacob);
		assertEquals(3, goodTable.size());	
	}
	
	@Test
	void testSizeSequentialRemove() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.0);
		assertEquals(4, goodTable.size());
		goodTable.remove(john);
		assertEquals(3, goodTable.size());
		goodTable.remove(jacob);
		assertEquals(2, goodTable.size());
		goodTable.remove(david);
		assertEquals(1, goodTable.size());
		goodTable.remove(sarah);
		assertEquals(0, goodTable.size());
	}
	
	@Test
	void testSizeWithDuplicatePut() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		assertEquals(3, goodTable.size());
		goodTable.put(david, 4.0);
		assertEquals(3,goodTable.size());
		goodTable.put(sarah, 4.0);
		assertEquals(4,goodTable.size());
	}
	
	@Test
	void testSizeWithDuplicateRemove() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		assertEquals(3, goodTable.size());
		goodTable.remove(david);
		assertEquals(2,goodTable.size());
		goodTable.remove(david);
		assertEquals(2,goodTable.size());
	}

//-----------------------size Tests End-------------------------------		

//-----------------------put Tests Begin------------------------------
	@Test
	void testAddRequiresGrowthBadTable() {
		HashTable<StudentBadHash, Double> badTableLimitedCapacity = new HashTable<StudentBadHash, Double>(2);
		ArrayList<StudentBadHash> list = badHashStudentGenerator(25);

		// Initial Size is 0
		assertTrue(badTableLimitedCapacity.size() == 0);
		// Initial Capacity is 2
		assertTrue(badTableLimitedCapacity.capacity == 2);
		// Add students from badHash ArrayList
		for (StudentBadHash student : list) {
			badTableLimitedCapacity.put(student, 4.0);
		}

		// Size after growing and adding should be 25
		assertTrue(badTableLimitedCapacity.size() == 25);
		// Grown Capacity should now be 4 after doubling
		assertEquals(4, badTableLimitedCapacity.capacity);

	}

	@Test
	void testAddRequiresGrowthGoodTable() {
		HashTable<StudentGoodHash, Double> goodTableLimitedCapacity = new HashTable<StudentGoodHash, Double>(2);
		ArrayList<StudentGoodHash> list = goodHashStudentGenerator(25);

		// Initial Size is 0
		assertTrue(goodTableLimitedCapacity.size() == 0);
		// Initial Capacity is 2
		assertTrue(goodTableLimitedCapacity.capacity == 2);
		// Add students from badHash ArrayList
		for (StudentGoodHash student : list) {
			goodTableLimitedCapacity.put(student, 4.0);
		}

		// Size after growing and adding should be 25
		assertTrue(goodTableLimitedCapacity.size() == 25);
		// Grown Capacity should now be 4 after doubling
		assertEquals(4, goodTableLimitedCapacity.capacity);

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

	@Test
	void testPutDuplicateKeyDifferentValue() {
		badTableEmpty.put(ada, 33.2);
		assertEquals(badTableEmpty.get(ada), 33.2);
		badTableEmpty.put(ada, 99.3);
		// adding same key different value only updates previous value
		assertEquals(badTableEmpty.get(ada), 99.3);
	}

	@Test
	void testPutDuplicateKeyAndValue() {
		badTableEmpty.put(ada, 88.3);
		assertEquals(badTableEmpty.size(), 1);
		badTableEmpty.put(ada, 88.3);
		// Duplicate item does not get added, and therefore
		// will not increase size
		assertEquals(badTableEmpty.size(), 1);

	}

	@Test
	void testPutValueReturnNull() {
		assertEquals(badTableEmpty.put(ada, 44.2), null);
	}

	@Test
	void testPutValueReturnValid() {
		badTableEmpty.put(alan, 32.1);
		assertEquals(badTableEmpty.put(alan, 55.3), 32.1);
	}

//-----------------------put Tests End-------------------------------

//-----------------------remove Tests Start--------------------------

	@Test
	void testRemoveCommonOnGood() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.1);
		assertEquals(4.0, goodTable.remove(jacob));
	}
	
	@Test
	void testRemove1st() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.1);
		goodTable.remove(john);
		for(MapEntry<StudentGoodHash,Double> element: goodTable.entries()) {
			double unit = element.getValue();
			assertFalse(unit == 3.8);
		}
	}
	
	@Test
	void testRemove2nd() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.1);
		goodTable.remove(jacob);
		for(MapEntry<StudentGoodHash,Double> element: goodTable.entries()) {
			double unit = element.getValue();
			assertFalse(unit == 4.0);
		}
	}
	
	@Test
	void testRemove3rd() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.1);
		goodTable.remove(david);
		for(MapEntry<StudentGoodHash,Double> element: goodTable.entries()) {
			double unit = element.getValue();
			assertFalse(unit == 3.22);
		}
	}
	
	@Test
	void testRemove4th() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.1);
		goodTable.remove(sarah);
		for(MapEntry<StudentGoodHash,Double> element: goodTable.entries()) {
			double unit = element.getValue();
			assertFalse(unit == 4.1);
		}
	}
	
	@Test
	void testRemoveOnEmpty() {
		assertEquals(null,goodTable.remove(sarah));
	}
	
	@Test
	void testRemoveTwice() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		goodTable.put(sarah, 4.1);
		assertEquals(3.22,goodTable.remove(david));
		assertEquals(null,goodTable.remove(david));
	}
	
	@Test
	void testRemoveNonItem() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		assertEquals(null,goodTable.remove(sarah));
	}
	
	@Test
	void testRemoveAndGet() {
		goodTable.put(john, 3.8);
		goodTable.put(jacob, 4.0);
		goodTable.put(david, 3.22);
		assertEquals(3.22, goodTable.remove(david));
		assertEquals(null, goodTable.get(david));
	}

//-----------------------remove Tests End----------------------------

//-----------------------containsKey Tests Start---------------------

	@Test
	void testContainsKeyOnEmptyTable() {
		assertFalse(badTableEmpty.containsKey(ada));
	}

	@Test
	void testContainsKeyAfterAddOnEmptyFalse() {
		badTableEmpty.put(alan, 33.2);
		assertFalse(badTableEmpty.containsKey(ada));
	}

	@Test
	void testContainsKeyAfterAddOnEmptyTrue() {
		badTableEmpty.put(ada, 55.0);
		assertTrue(badTableEmpty.containsKey(ada));
	}

	@Test
	void testContainsKeyAfterRemoveTrue() {
		badTableEmpty.put(edsger, 33.2);
		badTableEmpty.put(alan, 39.5);
		assertTrue(badTableEmpty.containsKey(edsger));
		assertTrue(badTableEmpty.containsKey(alan));
		badTableEmpty.remove(alan);
		assertTrue(badTableEmpty.containsKey(edsger));
	}

	@Test
	void testContainsKeyAfterRemoveFalse() {
		badTableEmpty.put(grace, 19.0);
		badTableEmpty.put(alan, 39.5);
		assertTrue(badTableEmpty.containsKey(grace));
		assertTrue(badTableEmpty.containsKey(alan));
		badTableEmpty.remove(alan);
		assertTrue(badTableEmpty.containsKey(grace));
		assertFalse(badTableEmpty.containsKey(alan));
	}

	@Test
	void testContainsKeyOAfterAddTrue() {
		assertFalse(badTableEmpty.containsKey(ada));
		badTableEmpty.put(ada, 88.9);
		assertTrue(badTableEmpty.containsKey(ada));
	}

	@Test
	void testContainsKeyAfterAddFalse() {
		assertFalse(badTableEmpty.containsKey(ada));
		badTableEmpty.put(edsger, 33.2);
		assertFalse(badTableEmpty.containsKey(ada));
	}

//-----------------------containsKey Tests End-----------------------

//-----------------------containsValue Tests Start-------------------
	@Test
	void testContainsValueOnEmptyTable() {
		assertFalse(badTableEmpty.containsValue(33.2));
	}

	@Test
	void testContainsValueAfterAddOnEmptyFalse() {
		badTableEmpty.put(alan, 33.2);
		assertFalse(badTableEmpty.containsValue(19.2));
	}

	@Test
	void testContainsValueAfterAddOnEmptyTrue() {
		badTableEmpty.put(ada, 55.0);
		assertTrue(badTableEmpty.containsValue(55.0));
	}

	@Test
	void testContainsValueAfterRemoveTrue() {
		badTableEmpty.put(edsger, 33.2);
		badTableEmpty.put(alan, 39.5);
		assertTrue(badTableEmpty.containsValue(33.2));
		assertTrue(badTableEmpty.containsValue(39.5));
		badTableEmpty.remove(alan);
		assertTrue(badTableEmpty.containsValue(33.2));
	}

	@Test
	void testContainsValueAfterRemoveFalse() {
		badTableEmpty.put(grace, 19.0);
		badTableEmpty.put(alan, 39.5);
		assertTrue(badTableEmpty.containsValue(19.0));
		assertTrue(badTableEmpty.containsValue(39.5));
		badTableEmpty.remove(alan);
		assertTrue(badTableEmpty.containsValue(19.0));
		assertFalse(badTableEmpty.containsValue(39.5));
	}

	@Test
	void testContainsValueOAfterAddTrue() {
		assertFalse(badTableEmpty.containsValue(88.9));
		badTableEmpty.put(ada, 88.9);
		assertTrue(badTableEmpty.containsValue(88.9));
	}

	@Test
	void testContainsValueAfterAddFalse() {
		assertFalse(badTableEmpty.containsValue(33.2));
		badTableEmpty.put(edsger, 33.2);
		assertFalse(badTableEmpty.containsValue(18.9));
	}

//-----------------------containsValue Tests End---------------------

//-----------------------entries Tests Start-------------------------

	@Test
	void testListOnEmpty() {
		List<MapEntry<StudentBadHash, Double>> returnList = badTableEmpty.entries();
		assertTrue(badTableEmpty.size() == 0);
		assertTrue(returnList.size() == 0);

	}

	@Test
	void testListOnTableWithItems() {
		List<MapEntry<StudentBadHash, Double>> returnList = badTable.entries();
		assertTrue(badTable.size() == 4);
		assertTrue(returnList.size() == 4);

	}

	@Test
	void testListValues() {
		List<MapEntry<StudentBadHash, Double>> returnList = badTable.entries();
		assertTrue(returnList.get(0).getValue() == 3.2);
		assertTrue(returnList.get(1).getValue() == 3.5);
		assertTrue(returnList.get(2).getValue() == 3.8);
		assertTrue(returnList.get(3).getValue() == 4.0);

	}

	@Test
	void testListKeys() {
		List<MapEntry<StudentBadHash, Double>> returnList = badTable.entries();
		assertTrue((returnList.get(0).getKey()).equals(alan));
		assertTrue((returnList.get(1).getKey()).equals(ada));
		assertTrue((returnList.get(2).getKey()).equals(edsger));
		assertTrue((returnList.get(3).getKey()).equals(grace));

	}

//-----------------------entries Tests End---------------------------	

//-----------------------get Tests Start-----------------------------
	@Test
	void testGetOnEmptyTable() {
		assertEquals(badTableEmpty.get(alan), null);
	}

	@Test
	void testGetAfterAddOnEmptyValid() {
		badTableEmpty.put(alan, 33.2);
		assertEquals(badTableEmpty.get(alan), 33.2);
	}

	@Test
	void testGetAfterAddOnEmptyNull() {
		badTableEmpty.put(ada, 55.0);
		assertEquals(badTableEmpty.get(grace), null);
	}

	@Test
	void testGetAfterRemoveValid() {
		badTableEmpty.put(edsger, 33.2);
		badTableEmpty.put(alan, 39.5);
		assertTrue(badTableEmpty.containsValue(33.2));
		assertTrue(badTableEmpty.containsValue(39.5));
		badTableEmpty.remove(alan);
		assertEquals(badTableEmpty.get(edsger), 33.2);
	}

	@Test
	void testGetAfterRemoveNull() {
		badTableEmpty.put(grace, 19.0);
		badTableEmpty.put(alan, 39.5);
		assertTrue(badTableEmpty.containsValue(19.0));
		assertTrue(badTableEmpty.containsValue(39.5));
		badTableEmpty.remove(alan);
		assertEquals(badTableEmpty.get(alan), null);
	}

	@Test
	void testGetAfterAddValid() {
		assertFalse(badTableEmpty.containsValue(88.9));
		badTableEmpty.put(ada, 88.9);
		assertEquals(badTableEmpty.get(ada), 88.9);
	}

	@Test
	void testGetAfterAddNull() {
		assertFalse(badTableEmpty.containsValue(33.2));
		badTableEmpty.put(edsger, 33.2);
		assertEquals(badTableEmpty.get(grace), null);
	}

//-----------------------get Tests End-------------------------------	
}
