import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
/**
 * This class holds all the methods used by all the servelts, which returns the appropriate data to the respective servlet calling it.
 * <br /><br />
 * @author Haroon Ashraf
 *
 */
public class functions {
	private DataBase db = new DataBase();
	private ResultSet rs = null;

	/**
	 * By using the username of the user, this method retrieves the user's assignments which are in progress, and also the details related to it like task_id, assignment description, asignee etc..
	 * used in sign_in and in_progress servlets.
	 * <br/><br/>
	 * Tables used: assignment, staff
	 * 
	 * @param username
	 * @return Resultset
	 */
	public ResultSet in_progress(String username) {

		try {
			//query
			rs = db.getConnection()
					.executeQuery(
							"select a.assignment_start_date, a.assignment_end_date, task_id, assignment_id, a.assignment_desc, a.assignment_assignee "
									+ "from assignment a, staff s "
									+ "where s.staff_username='"
									+ username
									+ "'"
									+ "and a.assignment_reporter=s.staff_id "
									+ "and a.assignment_end_date>=NOW()::DATE "
									+ "and a.assignment_start_date<=NOW()::DATE "
									+ "and a.assignment_status_code='I'"
									+ "order by a.assignment_end_date asc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return the Resultset
		return rs;

	}

	/**
	 * By using the eid(staff_id) of the user, this method retrieves all of the user's assignments , and also the details related to it like status, start and end dates  etc..
	 * used in assignments servlet.
	 * <br/><br/>
	 * Table used: assignment
	 * 
	 * @param eid
	 * @return Resultset
	 */
	public ResultSet assignments(String eid) {
		try {
			
			//querry
			rs = db.getConnection()
					.executeQuery(
							"select  a.assignment_status_code, a.assignment_start_date, a.assignment_end_date, task_id, assignment_id, a.assignment_desc, a.assignment_assignee "
									+ " from assignment a "
									+ " where a.assignment_reporter="
									+ eid
									+ " order by a.assignment_end_date desc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return Resultset
		return rs;
	}

	
	/**
	 * By using the eid(staff_id) of the user, this method retrieves the user details like staff_id, username  etc..
	 * used in my_details servlet.
	 * <br/><br/>
	 * Table used: staff
	 * 
	 * @param eid
	 * @return Resultset
	 */
	public ResultSet getMyDetails(String eid) {
		try {
			
			//query
			rs = db.getConnection().executeQuery(
					"select  s.staff_id, s.staff_fname, s.staff_lname, s.staff_username "
							+ " from staff s " + " where s.staff_id=" + eid
							+ "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//resturn Resultset
		return rs;
	}

	
	
	/**
	 * By using the eid(staff_id) of the user, this method retrieves the user's group details like group_id, description  etc..
	 * used in my_details servlet.
	 * <br/><br/>
	 * Tables used: groupds_desc, groups
	 * 
	 * @param eid
	 * @return Resultset
	 */
	public ResultSet getGroupDetails(String eid) {
		try {
			
			//query
			rs = db.getConnection().executeQuery(
					"select  gd.group_id, gd.group_desc "
							+ "from groups_desc gd, groups g"
							+ " where g.staff_id=" + eid
							+ " and gd.group_id=g.group_id"
							+ " order by gd.group_id desc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return Resultset
		return rs;
	}

	
	/**
	 * By using the eid(staff_id) of the user, this method retrieves the user's team details like team members and their email IDs  etc..
	 * used in my_details servlet.
	 * <br/><br/>
	 * Tables used: staff, groups
	 * 
	 * @param eid
	 * @return Resultset
	 */
	public ResultSet getTeamDetails(String eid) {
		try {
			//query
			rs = db.getConnection()
					.executeQuery(
							"select g.group_id, g.staff_id, s.staff_fname, s.staff_lname, s.staff_username "
									+ "from staff s, groups g "
									+ ""
									+ "where g.group_id = (select g1.group_id from groups g1 where g1.staff_id="
									+ eid + ") " + "and g.staff_id= s.staff_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return Resultset
		return rs;
	}

	
	/**
	 * By using the eid(staff_id) of the user, this method checks if the login credentials are valid or not,
	 * if the user is valid then set Session variables and start the session and return "valid" . if invalid return "invalid"  
	 * used in sign_in servlet. 
	 * <br/><br/>
	 * Table used: staff
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @return String "valid"/"invalid"
	 */
	public String check_user(HttpServletRequest request, String username,
			String password) {

		try {
			//query
			rs = db.getConnection().executeQuery(
					"select * from staff where staff_username='" + username
							+ "' and staff_password='" + password + "'");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			//if valid user
			if (rs.next()) {
				//set Session variables
				Session.setVariables(request, rs.getString("staff_id"),
						rs.getString("staff_username"));
				//start session
				Session.startSession();
				
				//return "valid"
				return ("valid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if user is not valid, return "invalid"
		return ("invalid");
	}

	/**
	 * By using the staff_id, this method returns the name of the person associated with it.
	 * used in sign_in, in_progress, assignment servlets.
	 * <br/><br/>
	 * Table used: staff
	 * 
	 * @param staff_id
	 * @return name
	 */
	public String get_assignee_name(String staff_id) {
		String name = "";
		try {
			//query
			rs = db.getConnection().executeQuery(
					"select s.staff_lname, s.staff_fname from staff s where s.staff_id="
							+ staff_id);
			//if staff exists
			if (rs.next()) {
				// construct full name
				name = rs.getString("staff_fname");
				name = name + " " + rs.getString("staff_lname");
				
				//return name
				return name;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return empty string if no asignee
		return name;
	}

	/**
	 * By using task_id and assignment_id, this method updates the assignment_status_code to 'C' which means 'completed'
	 * <br/><br/>
	 * Table used: assignment
	 * 
	 * @param task_id
	 * @param assignment_id
	 * @return String "failed"/"updated"
	 */
	public String assignment_done(String task_id, String assignment_id) {
		try {
			//get current user's staff_id by using session.getEID()
			String eid = Session.getEID();
			
			//query
			int i = db.getConnection().executeUpdate(
					"update assignment set assignment_status_code='C' "
							+ "where assignment_reporter=" + eid + ""
							+ " and task_id=" + task_id + ""
							+ " and assignment_id=" + assignment_id + ""
							+ " and assignment_start_date<=NOW()::DATE ");
			if (i == 0) {
				// operation failed
				return ("failed");
			}
			// operation successful 
			return ("updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ("failed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ("failed");
		}

	}

	/**
	 * This method closes the Resultset and Database connections 
	 */
	public void close_functions() {
		try {
			//close Resultset
			rs.close();
			//close database connection
			db.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
