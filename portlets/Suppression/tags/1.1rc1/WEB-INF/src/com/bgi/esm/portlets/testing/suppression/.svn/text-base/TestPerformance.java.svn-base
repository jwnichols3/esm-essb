package com.bgi.esm.portlets.testing.suppression;

import org.apache.log4j.Logger;
import com.bgi.esm.portlets.testing.suppression.SuppressionTestcase;

public class TestPerformance extends SuppressionTestcase
{
    final private static Logger _log = Logger.getLogger ( TestPerformance.class );

    public TestPerformance ( String param )
    {
        super ( param );
    }

    /* 
    public void testMultipleUsersAddingEntries1() throws Exception
    {
    	int num_simultaneous_users   = 100;
    	int counter                  = 0;
    	ThreadAddEntryForm threads[] = new ThreadAddEntryForm[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadAddEntryForm ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		System.out.println ( "Starting run...");
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersAddingEntries2() throws Exception
    {
    	int num_simultaneous_users   = 150;
    	int counter                  = 0;
    	ThreadAddEntryForm threads[] = new ThreadAddEntryForm[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadAddEntryForm ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		System.out.println ( "Starting run...");
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersAddingEntries3() throws Exception
    {
    	int num_simultaneous_users   = 200;
    	int counter                  = 0;
    	ThreadAddEntryForm threads[] = new ThreadAddEntryForm[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadAddEntryForm ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		System.out.println ( "Starting run...");
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersAddingEntries4() throws Exception
    {
    	int num_simultaneous_users   = 250;
    	int counter                  = 0;
    	ThreadAddEntryForm threads[] = new ThreadAddEntryForm[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadAddEntryForm ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		System.out.println ( "Starting run...");
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersAddingEntries5() throws Exception
    {
    	int num_simultaneous_users   = 300;
    	int counter                  = 0;
    	ThreadAddEntryForm threads[] = new ThreadAddEntryForm[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadAddEntryForm ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		System.out.println ( "Starting run...");
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersViewingOwnCurrentEntries1() throws Exception
    {
    	int num_simultaneous_users             = 100;
    	int counter                            = 0;
    	ThreadViewCurrentEntriesMine threads[] = new ThreadViewCurrentEntriesMine[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadViewCurrentEntriesMine ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersViewingOwnCurrentEntries2() throws Exception
    {
    	int num_simultaneous_users             = 150;
    	int counter                            = 0;
    	ThreadViewCurrentEntriesMine threads[] = new ThreadViewCurrentEntriesMine[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadViewCurrentEntriesMine ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersViewingOwnCurrentEntries3() throws Exception
    {
    	int num_simultaneous_users             = 200;
    	int counter                            = 0;
    	ThreadViewCurrentEntriesMine threads[] = new ThreadViewCurrentEntriesMine[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadViewCurrentEntriesMine ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersViewingOwnCurrentEntries4() throws Exception
    {
    	int num_simultaneous_users             = 250;
    	int counter                            = 0;
    	ThreadViewCurrentEntriesMine threads[] = new ThreadViewCurrentEntriesMine[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadViewCurrentEntriesMine ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    
    public void testMultipleUsersViewingOwnCurrentEntries5() throws Exception
    {
    	int num_simultaneous_users             = 300;
    	int counter                            = 0;
    	ThreadViewCurrentEntriesMine threads[] = new ThreadViewCurrentEntriesMine[num_simultaneous_users];
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter] = new ThreadViewCurrentEntriesMine ( hostname, counter+1 );
    	}
    	
    	for ( counter = 0; counter < num_simultaneous_users; counter++ )
    	{
    		threads[counter].start();
    	}
    	
    	boolean is_running = true;
    	while ( is_running )
    	{
    		Thread.sleep ( 500 );
    		
    		is_running = false;
    		for ( counter = 0; counter < num_simultaneous_users; counter++ )
    		{
    			if ( threads[counter].isAlive() )
    			{
    				is_running = true;
    				
    				break;
    			}
    		}
    	}
    }
    //*/
};
