package com.bgi.esm.monitoring.platform.junit.local;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class SuppressionRuleTest extends TestCase {

    private int getTimezoneOffset()
    {
        return TimeZone.getTimeZone( "America/Los_Angeles" ).getRawOffset();
    }

	/**
	 * ctor tests
	 */
	public void test01() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test01a", a1);
		
		assertTrue("test01b", a1.getRuleKey().equals(SuppressionRule.DEFAULT_KEY));
	
		try {
			a1 = new SuppressionRule((String) null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test01c", flag);
		flag = false;
		
		try {
			a1 = new SuppressionRule(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test01d", flag);
		flag = false;
		
		String candidate = "bogus value";
		
		a1 = new SuppressionRule(candidate);
		assertTrue("test01e", a1.getRuleKey().equals(candidate));
	}
	
	/**
	 * start time tests
	 */
	public void test02() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test02a", a1);
		
		assertNotNull("test02b", a1.getStartTime());
		
		try {
			a1.setStartTime(null);
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
		a1.setStartTime(c1);
		Calendar c2 = a1.getStartTime();
		
		assertEquals("test02d", c1.getTimeInMillis(), c2.getTimeInMillis());
	}

	/**
	 * end time tests
	 */
	public void test03() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test03a", a1);
		
		assertNotNull("test03b", a1.getEndTime());
		
		try {
			a1.setEndTime(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test03c", flag);
		flag = false;
		
		try {
			Thread.sleep(3L);
		} catch(InterruptedException ie) {
			//empty
		}
		
		Calendar c1 = Calendar.getInstance();
		a1.setStartTime(c1);
		Calendar c2 = a1.getStartTime();
		
		assertEquals("test03d", c1.getTimeInMillis(), c2.getTimeInMillis());
	}

	/**
	 * application name tests
	 */
	public void test04() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test04a", a1);
		
		assertTrue("test04b", a1.getApplicationName().equals("apname"));
	
		try {
			a1.setApplicationName(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test04c", flag);
		flag = false;
		
		try {
			a1.setApplicationName(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test04d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setApplicationName(candidate);
		assertTrue("test04e", a1.getApplicationName().equals(candidate));
	}
	
	/**
	 * node name tests
	 */
	public void test05() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test05a", a1);
		
		assertTrue("test05b", a1.getNodeName().equals("nodename"));
	
		try {
			a1.setNodeName(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test05c", flag);
		flag = false;
		
		try {
			a1.setNodeName(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test05d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setNodeName(candidate);
		assertTrue("test05e", a1.getNodeName().equals(candidate));
	}
	
	/**
	 * db server name tests
	 */
	public void test06() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test06a", a1);
		
		assertTrue("test06b", a1.getDatabaseServerName().equals("db"));
	
		try {
			a1.setDatabaseServerName(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test06c", flag);
		flag = false;
		
		try {
			a1.setDatabaseServerName(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test06d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setDatabaseServerName(candidate);
		assertTrue("test06e", a1.getDatabaseServerName().equals(candidate));
	}
	
	/**
	 * message tests
	 */
	public void test07() {
		boolean flag = false;
		
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test07a", a1);
		
		assertTrue("test07b", a1.getMessage().equals("message"));
	
		try {
			a1.setMessage(null);
		} catch(NullPointerException npe) {
			flag = true;
		}
		
		assertTrue("test07c", flag);
		flag = false;
		
		try {
			a1.setMessage(new String(""));
		} catch(IllegalArgumentException iae) {
			flag = true;
		}
		
		assertTrue("test07d", flag);
		flag = false;

		String candidate = "bogus value";
		
		a1.setMessage(candidate);
		assertTrue("test07e", a1.getMessage().equals(candidate));
	}
	
	/**
	 * Test non setters and getters
	 */
	public void test08() {
		SuppressionRule a1 = new SuppressionRule();
		assertNotNull("test10a", a1);
		
		SuppressionRule a2 = new SuppressionRule();
		assertNotNull("test10b", a2);
		
		assertTrue("test10c", a1.equals(a2));
		assertTrue("test10d", a2.equals(a1));
		
		SuppressionRule a3 = new SuppressionRule("testaroo");
		assertNotNull("test10e", a3);
		
		a3.setNodeName("new node");
		
		assertFalse("test10f", a1.equals(a3));
		assertFalse("test10g", a3.equals(a1));
		
		Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		String temp0 = _sdf.format(c1.getTime());
		System.out.println("c1:" + temp0);
		
		Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		c2.add(Calendar.SECOND, 100);
		String temp1 = _sdf.format(c2.getTime());
		System.out.println("c2:" + temp1);
		
		Calendar c3 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		c3.add(Calendar.SECOND, -100);
		String temp2 = _sdf.format(c3.getTime());
		System.out.println("c3:" + temp2);
		
		Calendar c4 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		c4.add(Calendar.SECOND, 500);
		String temp3 = _sdf.format(c4.getTime());
		System.out.println("c4:" + temp3);
		
		try {
			Thread.sleep(3000L);
		} catch(InterruptedException ie) {
			//empty
		}
		
		//
		// Legal happy active rule
		//
		a1.setStartTime(c3);
		a1.setEndTime(c2);
		System.out.println(a1);

		assertTrue("test10h", a1.isValidTime());
		assertFalse("test10i", a1.isExpired(getTimezoneOffset()));
		assertTrue("test10j", a1.isStarted(getTimezoneOffset()));
		assertTrue("test10k", a1.isActive(getTimezoneOffset()));
		
		//
		// Reversed time
		//
		a1.setStartTime(c2);
		a1.setEndTime(c3);	
		
		assertFalse("test10m", a1.isValidTime());
		
		//
		// Expired rule, not active
		//
		a1.setStartTime(c3);
		a1.setEndTime(c1);	
		
		assertTrue("test10n", a1.isValidTime());
		assertTrue("test10o", a1.isExpired(getTimezoneOffset()));
		assertTrue("test10p", a1.isStarted(getTimezoneOffset()));
		assertFalse("test10q", a1.isActive(getTimezoneOffset()));
		
		//
		// Future rule, not yet active
		//
		a1.setStartTime(c2);
		a1.setEndTime(c4);	
		
		assertTrue("test10r", a1.isValidTime());
		assertFalse("test10s", a1.isExpired(getTimezoneOffset()));
		assertFalse("test10t", a1.isStarted(getTimezoneOffset()));
		assertFalse("test10u", a1.isActive(getTimezoneOffset()));
	}
	
	/**
	 * format employed by toString() method, MM/DD/YYYY HH:mm:ss
	 */
	private static SimpleDateFormat _sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
}
