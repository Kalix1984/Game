package application.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	final String URL = "jdbc:derby:sampleDB;create=true";

	Connection connection = null;
	Statement createStatement = null;
	DatabaseMetaData dbmd = null;

	public DataBase() {

		try {
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (connection != null) {
			try {
				createStatement = connection.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			dbmd = connection.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			ResultSet rs1 = dbmd.getTables(null, "APP", "TOPLIST", null);

			if (!rs1.next()) {
				String sql = "create table toplist (playername VARCHAR(20), score INTEGER) ";
				createStatement.execute(sql);

				System.out.println("Tábla létrehozva");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addScore(String playerName, int score) {

		String sql = "insert into toplist values (?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playerName);
			preparedStatement.setInt(2, score);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Winner> getToplist() {
		List<Winner> winners = new ArrayList<>();
		
		String sql = "select * from toplist order by score desc";

		try {
			ResultSet rs = createStatement.executeQuery(sql);

			while (rs.next()) {
				winners.add(new Winner(rs.getString("playername"), rs.getInt("score")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return winners;
	}

}
