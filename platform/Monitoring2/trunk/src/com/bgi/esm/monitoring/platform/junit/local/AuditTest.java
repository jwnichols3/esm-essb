package com.bgi.esm.monitoring.platform.junit.local;

import java.util.Calendar;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;

/**
 * Exercise the Audit value object
 * 
 * @author guycole
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class AuditTest extends TestCase {

	/**
	 * Row key tests
	 */
	public void test01() {
		boolean flag = false;
		
		Audit a1 = new Audit();
		assertNotNull("test01a", a1);
		
		assertTrue("test01b", a1.getRowKey().equals(Audit.DEFAULT_KEY));
	
		try {
			a1 = new Audit((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test01c", flag);
		flag = false;
		
		try {
			a1 = new Audit(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test01d", flag);
		flag = false;
		
		String candidate = "bogus value";
		
		a1 = new Audit(candidate);
		assertTrue("test01e", a1.getRowKey().equals(candidate));
	}
	
	/**
	 * Action tests
	 */
	public void test02() {
		boolean flag = false;
		
		Audit a1 = new Audit();
		assertNotNull("test02a", a1);
		
		assertTrue("test02b", a1.getAction().equals("unknown"));
	
		try {
			a1.setAction(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test02c", flag);
		flag = false;
		
		try {
			a1.setAction(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test02d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setAction(candidate);
		assertTrue("test02e", a1.getAction().equals(candidate));
	}
	
	/**
	 * Message ID tests
	 */
	public void test03() {
		boolean flag = false;
		
		Audit a1 = new Audit();
		assertNotNull("test03a", a1);
		
		assertTrue("test03b", a1.getMessageId().equals("unknown"));
	
		try {
			a1.setMessageId(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test03c", flag);
		flag = false;
		
		try {
			a1.setMessageId(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test03d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setMessageId(candidate);
		assertTrue("test03e", a1.getMessageId().equals(candidate));
	}
	
	/**
	 * TimeStamp tests
	 */
	public void test04() {
		boolean flag = false;
		
		Audit a1 = new Audit();
		assertNotNull("test04a", a1);
		
		assertNotNull("test04b", a1.getTimeStamp());
		
		try {
			a1.setTimeStamp(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test04c", flag);
		flag = false;
		
		try {
			Thread.sleep(3000L);
		} catch(InterruptedException ie) {
			//empty
		}
		
		Calendar c1 = Calendar.getInstance();
		a1.setTimeStamp(c1);
		Calendar c2 = a1.getTimeStamp();
		
		assertEquals("test04d", c1.getTimeInMillis(), c2.getTimeInMillis());
	}
	
	/**
	 * Buss module tests
	 */
	public void test05() {
		boolean flag = false;
		
		Audit a1 = new Audit();
		assertNotNull("test05a", a1);
		
		assertNotNull("test05b", a1.getModule());
		assertTrue("test05c", a1.getModule().equals(BussModule.UNKNOWN));
		
		try {
			a1.setModule(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test05d", flag);
		flag = false;
	
		a1.setModule(BussModule.SUPPRESSION);
		assertTrue("test05e", a1.getModule().equals(BussModule.SUPPRESSION));
	}

	/**
	 * Test hashCode, equals and toString
	 */
	public void test06() {
		Audit a1 = new Audit();
		assertNotNull("test06a", a1);
		assertEquals("test06b", a1.hashCode(), Audit.DEFAULT_KEY.hashCode());
		
		Audit a2 = new Audit();
		assertNotNull("test06c", a2);
		assertEquals("test06d", a1, a2);
		
		assertTrue("test06e", a1.equals(a2));
		assertTrue("test06f", a2.equals(a1));
		
		Audit a3 = new Audit("testaroo");
		assertEquals("test06g", a3.hashCode(), -1146389149);
		
		assertTrue("test06h", a3.toString().equals("Audit:testaroo:UNKNOWN:unknown:unknown"));
		
		assertFalse("test06i", a1.equals(a3));
		assertFalse("test06j", a3.equals(a1));
	}
}
