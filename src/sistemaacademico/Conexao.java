package sistemaacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexao {
	
	// Método responsável por abrir a conexão com o banco de dados
	public static Connection conectar() {
		try {
			
			// URL, Usuario e Senha de conexão com o banco MySQL
			String url = "jdbc:mysql://localhost:3306/sistemaacademico";
			String usuario = "root";
			String senha = "251912";

			Connection conn = DriverManager.getConnection(url, usuario, senha);

			System.out.println("Conexão realizada com sucesso!");
			
			// Retorna a conexão para ser usada nos DAOs
			return conn;

		} catch (Exception erro) {
			// Exibe mensagem caso ocorra erro na conexão
			JOptionPane.showMessageDialog(null, "Erro na conexão: " + erro.getMessage());
			erro.printStackTrace();
			return null;
		}
	}
}