package head_office;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import branch_office.Article;

public class Insert {

	public static void main(String[] args) throws Exception {
		Gson gson= new GsonBuilder().setPrettyPrinting().create();;
		Article article = new Article();
		String receivedMessage = "";
		String cs = "jdbc:mysql://localhost:3306/tpsysrep_head_office?usesSL=false";
		String user = "root";
		String password = "";
		String sql = "INSERT INTO articles(id, nom, description, prix) VALUES(?,?,?,?)";
try (Connection con = DriverManager.getConnection(cs, user, password);
				PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 1; i <= 5; i++) {
				receivedMessage = Receive.receive();
				article = gson.fromJson(receivedMessage, Article.class);
				System.out.println(article);
				// pst.setInt(1, i);
				// pst.setString(2, "nom " + i);
				// pst.setString(3, "description " + i);
				// pst.setInt(4, i * 2);
				// pst.executeUpdate();
}
} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}