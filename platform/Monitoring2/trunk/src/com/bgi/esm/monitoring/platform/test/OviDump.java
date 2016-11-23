package com.bgi.esm.monitoring.platform.test;

import java.io.File;
import java.io.PrintStream;

import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.RawOvi;

/**
 * Dump the XML payload contents from RAW_OVI table to a file.
 * 
 * @author coleguy
 */
public class OviDump {
	
	/**
	 * Write table contents to output file
	 * 
	 * @param arg file name of output file
	 * @throws Exception for anything
	 */
	public void execute(String arg) throws Exception {
		BackEndFacade bef = new BackEndFacade();
		List list = bef.getAllRawOvi();
		System.out.println("result size:" + list.size());
		
		PrintStream ps = new PrintStream(new File(arg));
		
		for (int ii = 0; ii < list.size(); ii++) {
			RawOvi temp = (RawOvi) list.get(ii);
	//		System.out.println(temp);
			ps.println(temp.getXmlPayload());
		}
		
		ps.close();
	}

	/**
	 * <OL>
	 * <LI>args[0] file name of output file
	 * </OL>
	 * 
	 * @param args array of command line arguments
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");
		
		OviDump od = new OviDump();
		od.execute(args[0]);
		
		System.out.println("end");
	}
}
