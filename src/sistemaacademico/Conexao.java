package sistemaacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexao {

	public static Connection conectar() {
		try {
			String url = "jdbc:mysql://localhost:3306/sistemaacademico";
			String usuario = "root";
			String senha = "251912";

			Connection conn = DriverManager.getConnection(url, usuario, senha);

			System.out.println("Conexão realizada com sucesso!");
			return conn;

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro na conexão: " + erro.getMessage());
			erro.printStackTrace();
			return null;
		}
	}
}