

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class status_update_reply
 * <br /><br />
 * This servlet check the session is valid or not, if valid, then show the update operation reply and allow the user to update the status of assignment.
 * if session is not valid, redirect to sign_off servlet
 * 
 * @author Haroon Ashraf
 */
public class status_update_reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public status_update_reply() {
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
			{//session is invalid, redirect sign_off servlet
				response.sendRedirect("/IUF/sign_off");
			}
			else
			{//session is valid
			functions f = new functions();

			
			
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
					+ "<li><form method=\"post\" action=\"/IUF/in_progress\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">In Progress</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/assignments\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Assignments</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/status_update\"><button class=\"medium red\" type=\"submit\" style=\"width:100%; text-align:left\">Status Update</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/my_details\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">My Details</button></form></li>"
					+ "<li><form method=\"post\" action=\"/IUF/sign_off\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Sign Off &nbsp;<i class=\"icon-off\"></i></button></form></li>"
					+ "</ul>"
					+ "<br /><br /><br /><br /><br /><br /><br /><br /><br />"
					+ "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
					+ "</div>"
					+ "<!-- END - Menu Vertical Left -->"
					+ "<!-- DIV content -->"
					+ "<div class=\"col_10\" style=\"position: absolute; margin-top: 80px; left: 220px; top: 30px;\">");
			try{
				//get values from the form of status_update servlet
				String task_id=request.getParameter("task_id");
				String assignment_id=request.getParameter("assignment_id");
				
				//perform operation on database
				String result = f.assignment_done(task_id, assignment_id);
				
				//if the return string is 'updated' then operation is successful, print output below. 
				if (result.equals("updated"))
				{
					out.println(	"<h6 style=\"color:#600\"> <font color=\"#FF0000\">Assignment status updated to 'Completed'!</font><br /><br />Enter Task ID and Assignment ID, to set status 'Completed':  </h6>" +
							"<form method=\"post\" action=\"/IUF/status_update_reply\" > "+
							"						<table class=\"striped \" width=\"50%\" border=\"0\" cellspacing=\"5\">" +
							"						  <tr>" +
							"							<th width=\"4%\" align=\"center\"><font color=\"#660000\">TASK ID</font></th>" +
							"						    <td>  <input type=\"text\" name=\"task_id\" /></td>" +
							" 						  </tr>" +
							"						  <tr>" +
							"				   		  <th width=\"9%\" align=\"center\"><font color=\"#660000\">ASSIGNMENT ID</font></th>" +   
							"						    <td>  <input type=\"text\" name=\"assignment_id\" /></td>" +
							"						  </tr>" +
							"						  <tr>" +
							"						    <td colspan=\"2\"> <input class=\"medium red\" name=\"click\" type=\"submit\" value=\"Submit\" /></td>" +
							"						  </tr>" +
							"						</table> </form>");
					
				}
			
				//if database operation failed, then print below output
				else {
					out.println(	"<h6 style=\"color:#600\"> <font color=\"#FF0000\">Input datails not found in data base! </font><br /><br /> Enter Task ID and Assignment ID, to set status 'Completed':  </h6>" +
							"<form method=\"post\" action=\"/IUF/status_update_reply\" > "+
							"						<table class=\"striped \" width=\"50%\" border=\"0\" cellspacing=\"5\">" +
							"						  <tr>" +
							"							<th width=\"4%\" align=\"center\"><font color=\"#660000\">TASK ID</font></th>" +
							"						    <td>  <input type=\"text\" name=\"task_id\" /></td>" +
							" 						  </tr>" +
							"						  <tr>" +
							"				   		  <th width=\"9%\" align=\"center\"><font color=\"#660000\">ASSIGNMENT ID</font></th>" +   
							"						    <td>  <input type=\"text\" name=\"assignment_id\" /></td>" +
							"						  </tr>" +
							"						  <tr>" +
							"						    <td colspan=\"2\"> <input class=\"medium red\" name=\"click\" type=\"submit\" value=\"Submit\" /></td>" +
							"						  </tr>" +
							"						</table> </form>");
					
					
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			out.println("</div><!-- END DIV content -->"
					+ "</body>" + "</html>" + "");

		
		// close databse connection
		f.close_functions();

			}

	} catch (Exception e) {
		System.out.println("error");
		e.printStackTrace();
	}

	
	
	}

}
