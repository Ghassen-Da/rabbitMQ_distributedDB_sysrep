package branch_office;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Retrieve {

	public static void main(String[] args) throws Exception {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Article article= new Article();
		String url = "jdbc:mysql://localhost:3306/tpsysrep_branch_office?useSSL=false";
		String user = "root";
		String password = "";
		String query = "Select * from articles";
		Send sender = new Send();
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				article.setId(rs.getInt(1));
				article.setNom(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setPrix(rs.getInt(4));
				String jsonOutput = gson.toJson(article);
				System.out.println(jsonOutput);
				sender.send(jsonOutput);
			}
		}

		catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Retrieve.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

}
