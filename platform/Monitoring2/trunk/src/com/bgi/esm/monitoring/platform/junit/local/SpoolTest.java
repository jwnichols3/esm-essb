package com.bgi.esm.monitoring.platform.junit.local;

import java.util.Calendar;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.shared.value.Spool;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class SpoolTest extends TestCase {

	/**
	 * ctor tests
	 */
	public void test01() {
		boolean flag = false;
		
		Spool a1 = new Spool();
		assertNotNull("test01a", a1);
		
		assertTrue("test01b", a1.getRowKey().equals(Spool.DEFAULT_KEY));
	
		try {
			a1 = new Spool((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test01c", flag);
		flag = false;
		
		try {
			a1 = new Spool(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test01d", flag);
		flag = false;
		
		String candidate = "bogus value";
		
		a1 = new Spool(candidate);
		assertTrue("test01e", a1.getRowKey().equals(candidate));
	}
		
	/**
	 * time stamp tests
	 */
	public void test02() {
		boolean flag = false;
		
		Spool a1 = new Spool();
		assertNotNull("test02a", a1);
		
		assertNotNull("test02b", a1.getTimeStamp());
		
		try {
			a1.setTimeStamp(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test02c", flag);
		flag = false;
		
		try {
			Thread.sleep(3L);
		} catch(InterruptedException ie) {
			//empty
		}
		
		Calendar c1 = Calendar.getInstance();
		a1.setTimeStamp(c1);
		Calendar c2 = a1.getTimeStamp();
		
		assertEquals("test02d", c1.getTimeInMillis(), c2.getTimeInMillis());
	}

	/**
	 * Group tests
	 */
	public void test03() {
		boolean flag = false;
		
		Spool a1 = new Spool();
		assertNotNull("test03a", a1);
		
		assertTrue("test03b", a1.getGroup().equals("group"));
	
		try {
			a1.setGroup(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test03c", flag);
		flag = false;
		
		try {
			a1.setGroup(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test03d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setGroup(candidate);
		assertTrue("test03e", a1.getGroup().equals(candidate));
	}
	
	/**
	 * spool key tests
	 */
	public void test04() {
		boolean flag = false;
		
		Spool a1 = new Spool();
		assertNotNull("test04a", a1);
		
		assertTrue("test04b", a1.getSpoolKey().equals("bogus"));
	
		try {
			a1.setSpoolKey(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test04c", flag);
		flag = false;
		
		try {
			a1.setSpoolKey(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test04d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setSpoolKey(candidate);
		assertTrue("test04e", a1.getSpoolKey().equals(candidate));
	}
	
	/**
	 * Ovo key tests
	 */
	public void test05() {
		boolean flag = false;
		
		Spool a1 = new Spool();
		assertNotNull("test05a", a1);
		
		assertTrue("test05b", a1.getOvoKey().equals("bogus"));
	
		try {
			a1.setOvoKey(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test05c", flag);
		flag = false;
		
		try {
			a1.setOvoKey(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test05d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setOvoKey(candidate);
		assertTrue("test05e", a1.getOvoKey().equals(candidate));
	}
}
