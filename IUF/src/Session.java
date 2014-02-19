import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class holds the methods for controlling sessions
 * <br /><br />
 * The class variables <i>request, EID, UID, session</i> are declared as <i>static</i> because, the same copy of these variable must be accessed by all of the instances. 
 * 
 * @author Haroon Ashraf
 */
public class Session {
// these class variables are declared as static because, the same copy of these variables must be accessed by all the instances.
	private static HttpServletRequest request;
	private static String EID;
	private static String UID;
	private static HttpSession session;

	/**
	 * This method sets the variables request, EID(staff_id) and UID(staff_username) which are used to assign set attributes. 
	 * 
	 * @param req - (request)
	 * @param ID - (staff_id of the current user)
	 * @param uname - (staff_username of the current user)
	 */
	public static void setVariables(HttpServletRequest req, String ID,
			String uname) {
		request = req;
		EID = ID;
		UID = uname;
	}

	/**
	 * This method starts the session and set Session attributes EID and UID
	 * where EID hold staff_id and UID holds Staff_username of the user signed in. 
	 */
	public static void startSession() {
		session = request.getSession(true);
		session.setAttribute(EID, EID);
		session.setAttribute(UID, UID);
	}

	/**
	 * This method is used to retrieve current user's staff_username by using Session attribute UID 
	 * 
	 * @return uname - (staff_username of the current user)
	 */
	public static String getUsername() {
		try {
			String uname = (String) session.getAttribute(UID);
			return uname;
		} catch (Exception ex) {
			return ("invalid");
		}

	}

	/**
	 * This method is used to retrieve current user's staff_id by using Session attribute EID 
	 * 
	 * @return ID - (staff_id of the current user)
	 */
	public static String getEID() {
		try {
			String ID = (String) session.getAttribute(EID);
			return ID;
		} catch (Exception ex) {
			return ("invalid");
		}

	}

	/**
	 * This method is used to invalidate the session.
	 */
	public static void stopSession() {
		try {
			session.invalidate();

		} catch (Exception ex) {
			// System.out.println("Already signed out!");
		}
	}

}
