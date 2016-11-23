import java.io.BufferedReader;
import java.io.FileReader;

public class Converter1 {
    
    public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("data_map.csv"));

	String inbuffer;
	while ((inbuffer = br.readLine()) != null) {
	    //	    System.out.println(inbuffer);
	    String[] argz = inbuffer.split(",");
	    System.out.println("<TicketMap>");
	    for (int ii = 0; ii < argz.length; ii++) {
		//		System.out.println(ii + ":" + argz[ii]);
		switch(ii) {
		case 0: 
		    System.out.println("<group>" + argz[ii].trim() + "</group>");
		    break;
		case 1:
		    System.out.println("<method>" + argz[ii].trim() + "</method>");
		    break;
		case 2:
		    System.out.println("<support_group>" + argz[ii].trim() + "</support_group>");
		    break;
		case 3:
		    System.out.println("<support_script>" + argz[ii].trim() + "</support_script>");
		    break;
		case 4:
		    System.out.println("<problem_category>" + argz[ii].trim() + "</problem_category>");
		    break;
		case 5:
		    System.out.println("<problem_subcategory>" + argz[ii].trim() + "</problem_subcategory>");
		    break;
		case 6:
		    System.out.println("<problem_product>" + argz[ii].trim() + "</problem_product>");
		    break;
		case 7:
		    System.out.println("<problem>" + argz[ii].trim() + "</problem>");
		    break;
		case 8:
		    System.out.println("<problem_assignment_group>" + argz[ii].trim() + "</problem_assignment_group>");
		    break;
		case 9:
		    System.out.println("<problem_location>" + argz[ii].trim() + "</problem_location>");
		    break;
		default:
		}
	    }
	    System.out.println("</TicketMap>");
	}
	
	br.close();
    }
}
