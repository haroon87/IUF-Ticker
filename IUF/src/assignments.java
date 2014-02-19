
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class assignments
 * <br/><br />
 * This servlet check if the session is valid or not. If valid, then it display the details about all the assignment assigned to the user. 
 * If the session is not valid then redirects to sign_off servlet.
 * 
 * @author Haroon Ashraf
 * 
 */
public class assignments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public assignments() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			//check session
			if (Session.getUsername().equals("invalid")) {
				// if invalid
				response.sendRedirect("/IUF/sign_off");
			} 
			
			else {
				//if valid
				functions f = new functions();
				//get data
				ResultSet rs_as = f.assignments(Session.getEID());
				
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
						+ "<li><form method=\"post\" action=\"/IUF/assignments\"><button class=\"medium red\" type=\"submit\" style=\"width:100%; text-align:left\">Assignments</button></form></li>"
						+ "<li><form method=\"post\" action=\"/IUF/status_update\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Status Update</button></form></li>"
						+ "<li><form method=\"post\" action=\"/IUF/my_details\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">My Details</button></form></li>"
						+ "<li><form method=\"post\" action=\"/IUF/sign_off\"><button class=\"medium\" type=\"submit\" style=\"width:100%; text-align:left\">Sign Off &nbsp;<i class=\"icon-off\"></i></button></form></li>"
						+ "</ul>"
						+ "<br /><br /><br /><br /><br /><br /><br /><br /><br />"
						+ "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
						+ "</div>"
						+ "<!-- END - Menu Vertical Left -->"
						+ "<!-- DIV content -->"
						+ "<div class=\"col_10\" style=\"position: absolute; margin-top: 80px; left: 220px; top: 30px;\">");
				int flag = 0;// temp flag
				if (rs_as.next()) {
					flag = 1;// temp flag
					int i = 1; // loop variable
					String assignee = "";
					out.println("<h6 style=\"color:#600\"> Assignments assigned to you:</h6>"
							+ "						<table class=\"striped sortable\" width=\"98%\" border=\"0\" cellspacing=\"5\">"
							+ "						  <tr>"
							+ "							<th width=\"5%\" align=\"center\"><font color=\"#660000\">NO.</font></th>"
							+ "							<th width=\"10%\" align=\"center\"><font color=\"#660000\">STATUS</font></th>"
							+ "						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">START</font></th>"
							+ "						    <th width=\"10%\" align=\"center\"><font color=\"#660000\">END</font></th>"
							+ "						    <th width=\"8%\"  align=\"center\"><font color=\"#660000\">TASK #</font></th>"
							+ "						    <th width=\"13%\" align=\"center\"><font color=\"#660000\">ASSIGNMENT #</font></th>"
							+ "						    <th width=\"29%\" align=\"center\"><font color=\"#660000\">DESCRIPTION</font></th>"
							+ "						    <th width=\"15%\" align=\"center\"><font color=\"#660000\">ASSIGNED BY</font></th>"
							+ "						  	</font>"
							+ " 						  </tr>"
							+ "						  ");
					
					//iterate resultset to retrieve data
					do {
						//to know the name of asignee
						assignee = f.get_assignee_name(rs_as
								.getString("assignment_assignee"));
						//to show the assignment status
						String status = rs_as
								.getString("assignment_status_code");
						if (status.equals("I"))
							status = "In Progress";
						else
							status = "Completed";
						
						out.println("						  <tr>"
								+ "						    <td class=\"right\" ><font color=\"#000066\">"
								+ i
								+ "</font></td>"
								+ "				  			<td class=\"right\" ><font color=\"#000066\">"
								+ status
								+ "</font></td>"
								+ "						    <td class=\"right\" ><font color=\"#000066\">"
								+ rs_as.getString("assignment_start_date")
								+ "</font></td>"
								+ "						    <td class=\"right\" ><font color=\"#000066\">"
								+ rs_as.getString("assignment_end_date")
								+ "</font></td>"
								+ "						    <td class=\"right\" ><font color=\"#000066\">"
								+ rs_as.getString("task_id")
								+ "</font></td>"
								+ "						    <td class=\"right\" ><font color=\"#000066\">"
								+ rs_as.getString("assignment_id")
								+ "</font></td>"
								+ "						    <td><font color=\"#000066\">"
								+ rs_as.getString("assignment_desc")
								+ "</font></td>"
								+ "						    <td class=\"right\" ><font color=\"#000066\">"
								+ assignee + "</font></td>" + "						  </tr>");
						i++;//loop variable
					} while (rs_as.next());
					
					out.println("						</font>" + "						</table>");
				} 
				
				// if we get no data
				else if (flag == 0) {
					out.println("<h6 style=\"color:#600\"> No assignments are assigned to you till now. </h6>");
				}
				out.println("</div><!-- END DIV content -->" + "</body>"
						+ "</html>" + "");
				rs_as.close();// close resultset

				f.close_functions();// end database connection

			}

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}

	}

}
