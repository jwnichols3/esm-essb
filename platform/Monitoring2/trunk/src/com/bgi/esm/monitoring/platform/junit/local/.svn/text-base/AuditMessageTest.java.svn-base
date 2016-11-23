package com.bgi.esm.monitoring.platform.junit.local;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.shared.value.AuditMessage;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;

/**
 * Exercise the AuditMessage object
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class AuditMessageTest extends TestCase {

	/**
	 * module tests
	 */
	public void test01() {
		boolean flag = false;
		
		AuditMessage a1 = new AuditMessage();
		assertNotNull("test01a", a1);
		
		assertNotNull("test01b", a1.getModule());
		assertTrue("test01c", a1.getModule().equals(BussModule.UNKNOWN));
		
		try {
			a1.setModule(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test01d", flag);
		flag = false;
	
		a1.setModule(BussModule.SUPPRESSION);
		assertTrue("test01e", a1.getModule().equals(BussModule.SUPPRESSION));
	}
	
	/**
	 * Action tests
	 */
	public void test02() {
		boolean flag = false;
		
		AuditMessage a1 = new AuditMessage();
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
		
		AuditMessage a1 = new AuditMessage();
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
	 * Test hashCode, equals and toString
	 */
	public void test04() {
		AuditMessage a1 = new AuditMessage();
		assertNotNull("test04a", a1);
		assertEquals("test04b", a1.hashCode(), 780831384);
		
		AuditMessage a2 = new AuditMessage();
		assertNotNull("test04c", a2);
		assertEquals("test04d", a1, a2);
	
		assertTrue("test04e", a1.equals(a2));
		assertTrue("test04f", a2.equals(a1));
		
		System.out.println(a1);
		
		assertTrue("test04x", a1.toString().equals("AuditMessage:UNKNOWN:unknown:unknown"));
	}
}
