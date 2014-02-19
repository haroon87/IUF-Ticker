import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Database class has methods connect() and close()
 * 
 * @author Haroon Ashraf
 * 
 */
public class DataBase {

	private static Connection connection;
	Statement statement = null;

	/**
	 * This function is used to connect to the database.
	 * 
	 * @throws Exception
	 */
	public Statement getConnection() throws Exception {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "demonboy";
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();

		}

		catch (Exception e) {
			System.out.println("Error Connecting Database!");
			e.printStackTrace();
		}
		return statement;

	}

	/**
	 * This method is used to close the connection with database.
	 * 
	 * @throws Exception
	 */
	public void closeConnection() throws Exception {

		try {
			statement.close();
			connection.close();
		}

		catch (Exception e) {
			System.out.println("Error Closing Database Connection!");
			e.printStackTrace();
		}
	}

}
