package com.bgi.esm.monitoring.platform.junit.local;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.RawOviMessage;

/**
 * Exercise the RawOviMessageClass
 * 
 * @author coleguy
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class RawOviMessageTest extends TestCase {

	/**
	 * module tests
	 */
	public void test01() {
		boolean flag = false;
		
		RawOviMessage a1 = new RawOviMessage();
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
	 * XML payload tests
	 */
	public void test02() {
		boolean flag = false;
		
		RawOviMessage a1 = new RawOviMessage();
		assertNotNull("test02a", a1);
		
		assertTrue("test02b", a1.getXmlPayload().equals("bogus"));
	
		try {
			a1.setXmlPayload(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test02c", flag);
		flag = false;
		
		try {
			a1.setXmlPayload(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test02d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setXmlPayload(candidate);
		assertTrue("test02e", a1.getXmlPayload().equals(candidate));
	}
	
	/**
	 * Message ID tests
	 */
	public void test03() {
		boolean flag = false;
		
		RawOviMessage a1 = new RawOviMessage();
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
}
