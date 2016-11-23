package com.bgi.esm.monitoring.platform.junit.local;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class DataMapRuleTest extends TestCase {
	
	/**
	 * Row key tests
	 */
	public void test01() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test01a", a1);
		
		assertTrue("test01b", a1.getGroup().equals(DataMapRule.DEFAULT_KEY));
	
		try {
			a1 = new DataMapRule((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test01c", flag);
		flag = false;
		
		try {
			a1 = new DataMapRule(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test01d", flag);
		flag = false;
		
		String candidate = "bogus value";
		
		a1 = new DataMapRule(candidate);
		assertTrue("test01e", a1.getGroup().equals(candidate));
	}
	
	/**
	 * method tests
	 */
	public void test02() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test02a", a1);
		
		assertTrue("test02b", a1.getMethod().equals("bogus"));
	
		try {
			a1.setMethod(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test02c", flag);
		flag = false;
		
		try {
			a1.setMethod(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test02d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setMethod(candidate);
		assertTrue("test02e", a1.getMethod().equals(candidate));
	}
	
	/**
	 * AP group tests
	 */
	public void test03() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test03a", a1);
		
		assertTrue("test03b", a1.getAlarmpointGroup().equals("bogus"));
	
		try {
			a1.setAlarmpointGroup(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test03c", flag);
		flag = false;
		
		try {
			a1.setAlarmpointGroup(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test03d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setAlarmpointGroup(candidate);
		assertTrue("test03e", a1.getAlarmpointGroup().equals(candidate));
	}
	
	/**
	 * AP script tests
	 */
	public void test04() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test04a", a1);
		
		assertTrue("test04b", a1.getAlarmpointScript().equals("bogus"));
	
		try {
			a1.setAlarmpointScript(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test04c", flag);
		flag = false;
		
		try {
			a1.setAlarmpointScript(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test04d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setAlarmpointScript(candidate);
		assertTrue("test04e", a1.getAlarmpointScript().equals(candidate));
	}
	
	/**
	 * Peregrine category tests
	 */
	public void test05() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test05a", a1);
		
		assertTrue("test05b", a1.getPeregrineCategory().equals("bogus"));
	
		try {
			a1.setPeregrineCategory(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test05c", flag);
		flag = false;
		
		try {
			a1.setPeregrineCategory(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test05d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setPeregrineCategory(candidate);
		assertTrue("test05e", a1.getPeregrineCategory().equals(candidate));
	}
	
	/**
	 * Peregrine SubCategory tests
	 */
	public void test06() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test06a", a1);
		
		assertTrue("test06b", a1.getPeregrineSubCategory().equals("bogus"));
	
		try {
			a1.setPeregrineSubCategory(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test06c", flag);
		flag = false;
		
		try {
			a1.setPeregrineSubCategory(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test06d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setPeregrineSubCategory(candidate);
		assertTrue("test06e", a1.getPeregrineSubCategory().equals(candidate));
	}
	
	/**
	 * Peregrine product tests
	 */
	public void test07() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test07a", a1);
		
		assertTrue("test07b", a1.getPeregrineProduct().equals("bogus"));
	
		try {
			a1.setPeregrineProduct(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test07c", flag);
		flag = false;
		
		try {
			a1.setPeregrineProduct(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test07d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setPeregrineProduct(candidate);
		assertTrue("test07e", a1.getPeregrineProduct().equals(candidate));
	}
	
	/**
	 * Peregrine Problem tests
	 */
	public void test08() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test08a", a1);
		
		assertTrue("test08b", a1.getPeregrineProblem().equals("bogus"));
	
		try {
			a1.setPeregrineProblem(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test08c", flag);
		flag = false;
		
		try {
			a1.setPeregrineProblem(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test08d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setPeregrineProblem(candidate);
		assertTrue("test08e", a1.getPeregrineProblem().equals(candidate));
	}
	
	/**
	 * Peregrine Assignment tests
	 */
	public void test09() {
		boolean flag = false;
		
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test09a", a1);
		
		assertTrue("test09b", a1.getPeregrineAssignment().equals("bogus"));
	
		try {
			a1.setPeregrineAssignment(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test09c", flag);
		flag = false;
		
		try {
			a1.setPeregrineAssignment(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test09d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setPeregrineAssignment(candidate);
		assertTrue("test09e", a1.getPeregrineAssignment().equals(candidate));
	}
	
	/**
	 * Test hashCode, equals and toString
	 */
	public void test10() {
		DataMapRule a1 = new DataMapRule();
		assertNotNull("test10a", a1);
		assertEquals("test10b", a1.hashCode(), -842694656);
		
		DataMapRule a2 = new DataMapRule();
		assertNotNull("test10c", a2);
		assertEquals("test10d", a1, a2);
		
		assertTrue("test10e", a1.equals(a2));
		assertTrue("test10f", a2.equals(a1));
		
		DataMapRule a3 = new DataMapRule("testaroo");
		assertEquals("test10g", a3.hashCode(), 731590656);
		
		System.out.println(a3);
		assertTrue("test10h", a3.toString().equals("DataMapRule:testaroo:bogus:bogus:bogus:bogus:bogus:bogus:bogus:bogus:bogus"));
		
		assertFalse("test10i", a1.equals(a3));
		assertFalse("test10j", a3.equals(a1));
	}
}
