package com.bgi.esm.monitoring.platform.junit.local;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ThrottleRuleTest extends TestCase {

	/**
	 * ctor tests
	 */
	public void test01() {
		boolean flag = false;
		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test01a", a1);
		
		assertTrue("test01b", a1.getRuleKey().equals(ThrottleRule.DEFAULT_KEY));
	
		try {
			a1 = new ThrottleRule((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test01c", flag);
		flag = false;
		
		try {
			a1 = new ThrottleRule(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test01d", flag);
		flag = false;
		
		String candidate = "bogus value";
		
		a1 = new ThrottleRule(candidate);
		assertTrue("test01e", a1.getRuleKey().equals(candidate));
	}
	
	/**
	 * Group tests
	 */
	public void test02() {
		boolean flag = false;
		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test02a", a1);
		
		assertTrue("test02b", a1.getGroup().equals("group"));
	
		try {
			a1.setGroup(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test02c", flag);
		flag = false;
		
		try {
			a1.setGroup(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test02d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setGroup(candidate);
		assertTrue("test02e", a1.getGroup().equals(candidate));
	}
	
	/**
	 * Storm level tests
	 */
	public void test03() {
		boolean flag = false;
		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test03a", a1);
		
		assertEquals("test03b", 0, a1.getStormLevel());
	
		try {
			a1.setStormLevel((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test03c", flag);
		flag = false;
		
		try {
			a1.setStormLevel(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test03d", flag);
		flag = false;
		
		try {
			a1.setStormLevel("bogus value");
		} catch(NumberFormatException nfe) {
			flag = true;
		}
		
		assertTrue("test03e", flag);
		flag = false;
		
		try {
			a1.setStormLevel("-123");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test03f", flag);
		flag = false;
		
		try {
			a1.setStormLevel("123");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertEquals("test03g", 123, a1.getStormLevel());
		
		try {
			a1.setStormLevel(-123);
		} catch(IllegalArgumentException iae) {
			flag = true;
		}

		assertTrue("test03h", flag);
		flag = false;
		
		a1.setStormLevel(345);
		assertEquals("test03i", 345, a1.getStormLevel());
	}
	
	/**
	 * duration tests
	 */
	public void test04() {
		boolean flag = false;
		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test04a", a1);
		
		assertEquals("test04b", 0, a1.getDuration());
	
		try {
			a1.setDuration((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test04c", flag);
		flag = false;
		
		try {
			a1.setDuration(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test04d", flag);
		flag = false;
		
		try {
			a1.setDuration("bogus value");
		} catch(NumberFormatException nfe) {
			flag = true;
		}
		
		assertTrue("test04e", flag);
		flag = false;
		
		try {
			a1.setDuration("-123");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test04f", flag);
		flag = false;
		
		try {
			a1.setDuration("123");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertEquals("test04g", 123, a1.getDuration());
		
		try {
			a1.setDuration(-123);
		} catch(IllegalArgumentException iae) {
			flag = true;
		}

		assertTrue("test04h", flag);
		flag = false;
		
		a1.setDuration(345);
		assertEquals("test04i", 345, a1.getDuration());
	}

	/**
	 * Threshold tests
	 */
	public void test05() {
		boolean flag = false;
		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test05a", a1);
		
		assertEquals("test05b", 0, a1.getThreshold());
	
		try {
			a1.setThreshold((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test05c", flag);
		flag = false;
		
		try {
			a1.setThreshold(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test05d", flag);
		flag = false;
		
		try {
			a1.setThreshold("bogus value");
		} catch(NumberFormatException nfe) {
			flag = true;
		}
		
		assertTrue("test05e", flag);
		flag = false;
		
		try {
			a1.setThreshold("-123");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test05f", flag);
		flag = false;
		
		try {
			a1.setThreshold("123");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertEquals("test05g", 123, a1.getThreshold());
		
		try {
			a1.setThreshold(-123);
		} catch(IllegalArgumentException iae) {
			flag = true;
		}

		assertTrue("test05h", flag);
		flag = false;
		
		a1.setThreshold(345);
		assertEquals("test05i", 345, a1.getThreshold());
	}
	
	/**
	 * ctor tests
	 */
	public void test06() {
		boolean flag = false;
		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test06a", a1);
		
		assertTrue("test06b", a1.getAction().equals(ThrottleAction.PASS_THRU));
	
		try {
			a1.setAction((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test06c", flag);
		flag = false;
		
		try {
			a1.setAction(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test06d", flag);
		flag = false;
		
		try {
			a1.setAction("bogus value");
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test06e", flag);
		flag = false;
		
		a1.setAction(ThrottleAction.DISCARD);
		assertTrue("test06f", ThrottleAction.DISCARD.equals(a1.getAction()));
		
		try {
			a1.setAction((ThrottleAction) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test06g", flag);
		flag = false;
	}

	/**
	 * message enable tests
	 */
	public void test07() {		
		ThrottleRule a1 = new ThrottleRule();
		assertNotNull("test07a", a1);
		
		assertTrue("test07b", a1.isEnableStormMessages());
		
		a1.setEnableStormMessages(false);
		
		assertFalse("test07c", a1.isEnableStormMessages());
	}
	
	/**
	 * equals, etc
	 */
	public void test08() {
		//boolean flag = false;
		
		ThrottleRule tr1 = new ThrottleRule();
		assertNotNull("test08a", tr1);
		
		ThrottleRule tr2 = new ThrottleRule();
		assertNotNull("test08b", tr2);
		
		assertTrue("test08c", tr1.equals(tr2));
		assertTrue("test08d", tr2.equals(tr1));
		
		assertFalse("test08e", tr1.equals(null));
		
		tr1.setAction(ThrottleAction.SPOOL);
		assertFalse("test08f", tr1.equals(tr2));
		assertFalse("test08g", tr2.equals(tr1));
		
		tr1.setStormLevel(2);
		tr2.setStormLevel(3);
		
		assertEquals("test08h", -1, tr1.compareTo(tr2));
		assertEquals("test08i",  1, tr2.compareTo(tr1));
		assertEquals("test08j",  0, tr1.compareTo(tr1));
	}
}
