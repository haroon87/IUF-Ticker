

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class my_details
 * <br /><br />
 * This servlet check if the session is valid or not, if it is valid, then displays the user and group details of the user.
 * if the session is invalid then redirect to sign_off servlet.
 * 
 * @author Haroon Ashraf
 */
public class my_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public my_details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			//check session
			if(Session.getUsername().equals("invalid"))
			{
				//redirect if session is invalid
				response.sendRedirect("/IUF/sign_off");
			}
			else
			{// session is valid, get user details
			functions f = new functions();
			ResultSet rs_md = f.getMyDetails(Session.getEID());
			
			
			//output html
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
					+ "<!-- TemplateBeginEditable name=\"doctitle\" -->"
					+ "<title>IUF</title>"
					+ "<!-- TemplateEndEditable -->"
					+ "<!-- TemplateBeginEditable name=\"head\" -->"
					+ "<!-- TemplateEndEditable -->"
					+ "<script"
					+ "	src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js\"></script>"
					+ "<script src=\"js/kickstart.js\"></script>"
					+ "<!-- KICKSTART -->"
					+ "<link rel=\"stylesheet\" href=\"css/kickstart.css\" media=\"all\" />"
					+ "<!-- KICKSTART -->"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\" />"
					+ "</head>"
					+ ""
					+ "<body>"
					+ "<div style=\" position:absolute; margin-top: 0px; top:0px; height: 80px; width:100%; background-color: #600;\"><h1 align=\"left\"> <img src=\"Images/iuf_symbol_red.jpg\" width=\"50\" height=\"50\" style=\"top:-25px; left:10px;\"/><font color=\"#fff\" size=\"+3\"> &nbsp;I</font><font color=\"#ececec\" size=\"+2\">NDIANA</font> <font color=\"#fff\" size=\"+3\">U</font><font color=\"#ececec\" size=\"+2\">NIVERSITY</font> <font color=\"#fff\" size=\"+3\">F</font><font color=\"#ececec\" size=\"+2\">OUNDATION</font></h1></div>"
					+ "<!-- Menu Vertical Left -->"
					+ ""
					+ "<div class=\"col_2\" style=\" position:absolute; margin-bottom:0px; margin-left:0px; margin-top:80px; height:100%; background-color:#cecece; overflow: hidden; \">" 
					+ "<ul class=\"menu vertical\">"
					+ "<li><form method=\"post\" action=\"/IUF/in_progress\"><button class=\"medium \" type=\"submit\" style=\"width:100%; text-align:left\">In Progress</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/assignments\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Assignments</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/status_update\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Status Update</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/my_details\"><button class=\"medium red\" type=\"submit\" style=\"width:100%; text-align:left\">My Details</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/sign_off\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Sign Off &nbsp;<i class=\"icon-off\"></i></button></form></li>"
					+ "</ul>"
					+ "<br /><br /><br /><br /><br /><br /><br /><br /><br />"
					+ "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
					+ "</div>"
					+ "<!-- END - Menu Vertical Left -->"
					+ "<!-- DIV content -->"
					+ "<div class=\"col_10\" style=\"position: absolute; margin-top: 80px; left: 220px; top: 30px;\">");
			int flag=0;//temp flag
			
			//result set of mydetails, returns name, id, username, etc
			if(rs_md.next()){
				flag=1;
			out.println(	"<h6 style=\"color:#600\"> My Details:</h6>"+
					"						<table class=\"striped sortable\" width=\"60%\" border=\"0\" cellspacing=\"5\">" +
					"						  <tr>" +
					"							<th width=\"5%\" align=\"center\"><font color=\"#660000\">ID.</font></th>" +
					"						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">FIRST NAME</font></th>" +
					"						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">LAST NAME</font></th>" +
					"						    <th width=\"10%\"  align=\"center\"><font color=\"#660000\">USERNAME</font></th>" +
					"						   	</font>" +
					" 						  </tr>" +
					"						  " );
			//iterate to print records
			do{
				
			out.println(
					
					"						  <tr>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_id")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_fname")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_lname")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_username")+"</font></td>" +
					"						  </tr>");
			
			}while(rs_md.next());
			out.println(
					"						</font>" +
					"						</table>");
			}		
			//get group details, like group id, group description. 
			rs_md = f.getGroupDetails(Session.getEID());
			//iterating result set if we get data, to output record.
			if(rs_md.next()){
				flag=1;
			out.println(	"<h6 style=\"color:#600\"> Group Details:</h6>"+
					"						<table class=\"striped sortable\" width=\"50%\" border=\"0\" cellspacing=\"5\">" +
					"						  <tr>" +
					"							<th width=\"5%\" align=\"center\"><font color=\"#660000\">GROUP ID.</font></th>" +
					"						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">GROUP DESCRIPTION</font></th>" +
					"						   </font>" +
					" 						  </tr>" +
					"						  " );
			do{
				
			out.println(
					
					"						  <tr>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("group_id")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("group_desc")+"</font></td>" +
					"						   </tr>");
			
			}while(rs_md.next());
			out.println(
					"						</font>" +
					"						</table>");
			}		
		
			//get team details, like team members and their email id , etc.
			rs_md = f.getTeamDetails(Session.getEID());
			
			//if we get data, iterate resultset and print records.
			if(rs_md.next()){
				flag=1;int i=1;
			out.println(	"<h6 style=\"color:#600\"> Group Members:</h6>"+
					"						<table class=\"striped sortable\" width=\"80%\" border=\"0\" cellspacing=\"5\">" +
					"						  <tr>" +
					"							<th width=\"5%\" align=\"center\"><font color=\"#660000\">NO.</font></th>" +
					"						    <th width=\"5%\" align=\"center\"><font color=\"#660000\">GROUP ID.</font></th>" +
					"						    <th width=\"5%\" align=\"center\"><font color=\"#660000\">STAFF ID</font></th>" +
					"						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">FIRST NAME</font></th>" +
					"						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">LAST NAME</font></th>" +
					"						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">E-MAIL ID</font></th>" +
					"						   </font>" +
					" 						  </tr>" +
					"						  " );
			do{
				
			out.println(
					
					"						  <tr>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+i+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("group_id")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_id")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_fname")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_lname")+"</font></td>" +
					"						    <td class=\"right\" ><font color=\"#000066\">"+rs_md.getString("staff_username")+"</font></td>" +
					"						   </tr>");
			i++;
			}while(rs_md.next());
			out.println(
					"						</font>" +
					"						</table>");
			}	
			
			
			// if we din't get group details we show follow,
			else if(flag==0){
			out.println("<h6 style=\"color:#600\"> <br /> <br /> You are not assigned to any group. </h6>" );}
			out.println("</div><!-- END DIV content -->"
					+ "</body>" + "</html>" + "");
			rs_md.close();
		
		// close database connection
		f.close_functions();

			}

	} catch (Exception e) {
		System.out.println("error");
		e.printStackTrace();
	}

	}

}
