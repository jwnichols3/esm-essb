import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * CheckDST validates the DST period for a given time zone
 * Sample usage >java CheckDST PST 03/11/2007 11/04/2007
 */
public class CheckDST
{

	// sample DST data - 2007 DST data for PST Timezone
	private static final String PST_2007_START_DST = "03/11/2007";
	private static final String PST_2007_END_DST  = "11/04/2007";
	private static final String PST_TIMEZONE_LOCATION  = "PST";

	private static final  String DATE_FORMAT = "MM/dd/yyyy";

	private Date startDST;
	private Date endDST;
	private String startDSTString;
	private String endDSTString;
	private TimeZone timeZone;
	private String timezonLocation;

	public CheckDST(String timezonLocation, String entryDate, String exitDate) throws Exception 
    {
		this.timezonLocation = timezonLocation;
		this.startDSTString = entryDate;;
		this.endDSTString = exitDate;
		if (!isValidTimeZone(timezonLocation))
        {
			throw new Exception("Not a valid Timezone: " + timezonLocation);
		}
		timeZone = TimeZone.getTimeZone(timezonLocation);
		startDST = this.stringToDate(entryDate, DATE_FORMAT);
		endDST = this.stringToDate(exitDate, DATE_FORMAT);
	}

	public static void main(String[] args) 
    {
		try 
        {
			CheckDST checkDST;
			if (args.length==0)
            {
				checkDST = new CheckDST(PST_TIMEZONE_LOCATION, PST_2007_START_DST, PST_2007_END_DST);
			}
            else
            {
				checkDST = new CheckDST(args[0], args[1], args[2]);
			}

			System.out.println("Testing JRE TimeZone DST transitioning for: " + checkDST.timezonLocation);

			if (checkDST.entryDSTWorksProperly())
            {
				System.out.print("SUCCESS ");
			}
            else
            {
				System.out.print("FAILURE ");
			}
			System.out.println("for DST entry date " + checkDST.startDSTString);

			if (checkDST.exitDSTWorksProperly())
            {
				System.out.print("SUCCESS ");
			}
            else
            {
				System.out.print("FAILURE ");
			}
			System.out.println("for DST exit date " + checkDST.endDSTString);
		}
        catch ( Exception e ) 
        {
			e.printStackTrace();
			System.out.println("Please make sure to use a valid Timezone and enter the date in the following format MM/dd/YY.");
			System.out.println("e.g. >java CheckDST PST 03/11/2007 11/04/2007");
		}

	}

	public static boolean dstStartDateIsTransitioningProperly(TimeZone timezone, Date startDST)
    {
		return (!timezone.inDaylightTime(previousDay(startDST)) && timezone.inDaylightTime(dayAfter(startDST)));
	}

	public static boolean dstExitDateIsTransitioningProperly(TimeZone timezone, Date endDST)
    {
		return (timezone.inDaylightTime(previousDay(endDST)) && !timezone.inDaylightTime(dayAfter(endDST)));
	}

	public static boolean isValidTimeZone(String timezoneLocation) 
    {
		String[] allTimeZoneIds = TimeZone.getAvailableIDs();
		boolean foundTimezone = false;
		for (int i = 0; i < allTimeZoneIds.length; i++) 
        {
			if (timezoneLocation.equals(allTimeZoneIds[i])) 
            {
				foundTimezone = true;
				break;
			}
		}
		return foundTimezone;
	}

	public boolean entryDSTWorksProperly()   
    {
		return dstStartDateIsTransitioningProperly(this.timeZone, this.startDST);
	}

	public boolean exitDSTWorksProperly()  
    {
		return dstExitDateIsTransitioningProperly(this.timeZone, this.endDST);
	}

	private Date stringToDate(String dateStr, String dateFormat) throws java.text.ParseException 
    {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

		return simpleDateFormat.parse(dateStr);
	}

	private static Date previousDay(Date date) 
    {
		return new Date(date.getTime() - 1000 * 60 * 60 * 24);
	}

	private static Date dayAfter(Date date) 
    {
		return new Date(date.getTime() + 1000 * 60 * 60 * 24);
	}

}

