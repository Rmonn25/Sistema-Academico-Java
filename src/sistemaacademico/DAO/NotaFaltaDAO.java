package sistemaacademico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.Conexao;

// Classe responsável pelas operações no banco relacionadas às notas e faltas
public class NotaFaltaDAO {

	// Método responsável por salvar uma nova nota e quantidade de faltas no banco
	public void salvar(String rgm, String disciplina, String semestre, double nota, int faltas) {

		// Comando SQL para inserir nota e faltas na tabela notas_faltas
		String sql = """
				INSERT INTO notas_faltas
				(rgm, disciplina, semestre, nota, faltas)
				VALUES (?, ?, ?, ?, ?)
				""";

		try {
			// Abre conexão com o banco de dados
			Connection conn = Conexao.conectar();

			// Verifica se a conexão falhou
			if (conn == null) {
				JOptionPane.showMessageDialog(null, "A conexão com o banco falhou. Verifique a classe Conexao.");
				return;
			}

			// Prepara o INSERT
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define os valores que serão inseridos
			stmt.setString(1, rgm);
			stmt.setString(2, disciplina);
			stmt.setString(3, semestre);
			stmt.setDouble(4, nota);
			stmt.setInt(5, faltas);

			// Executa o INSERT
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Nota e faltas cadastradas com sucesso!");

			// Fecha recursos
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar nota/faltas: " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	// Busca dados básicos do aluno para as telas de notas/faltas
	public String[] buscarAlunoParaNotas(String busca) {

		// Busca por RGM ou CPF
		String sql = "SELECT rgm, nome, periodo FROM alunos WHERE rgm = ? OR cpf = ?";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, busca);
			stmt.setString(2, busca);

			var rs = stmt.executeQuery();

			// Se encontrar o aluno, retorna RGM, nome e período
			if (rs.next()) {
				String[] aluno = { rs.getString("rgm"), rs.getString("nome"), rs.getString("periodo") };

				rs.close();
				stmt.close();
				conn.close();

				return aluno;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + erro.getMessage());
		}

		return null;
	}

	// Busca semestre, nota e faltas de uma disciplina específica do aluno
	public String[] buscarNotaPorDisciplina(String rgm, String disciplina) {

		String sql = """
					SELECT semestre, nota, faltas
					FROM notas_faltas
					WHERE rgm = ? AND disciplina = ?
				""";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);
			stmt.setString(2, disciplina);

			var rs = stmt.executeQuery();

			// Retorna os dados da nota caso encontre a disciplina
			if (rs.next()) {
				String[] dados = { rs.getString("semestre"), rs.getString("nota"), rs.getString("faltas") };

				rs.close();
				stmt.close();
				conn.close();

				return dados;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar nota: " + erro.getMessage());
		}

		return null;
	}

	// Lista todas as disciplinas cadastradas para um aluno pelo RGM
	public String[] listarDisciplinasPorRgm(String rgm) {

		String sql = "SELECT disciplina FROM notas_faltas WHERE rgm = ? ORDER BY disciplina";

		// Lista temporária usada para armazenar as disciplinas encontradas
		java.util.ArrayList<String> lista = new java.util.ArrayList<>();

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);

			var rs = stmt.executeQuery();

			// Adiciona cada disciplina encontrada na lista
			while (rs.next()) {
				lista.add(rs.getString("disciplina"));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao listar disciplinas: " + erro.getMessage());
		}

		// Converte a lista para array de String
		return lista.toArray(new String[0]);
	}

	// Altera semestre, nota e faltas de uma disciplina já cadastrada
	public void alterar(String rgm, String disciplina, String semestre, double nota, int faltas) {

		String sql = """
					UPDATE notas_faltas
					SET semestre = ?, nota = ?, faltas = ?
					WHERE rgm = ? AND disciplina = ?
				""";

		try {
			Connection conn = Conexao.conectar();

			if (conn == null) {
				JOptionPane.showMessageDialog(null, "A conexão com o banco falhou.");
				return;
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define os novos valores da nota/faltas
			stmt.setString(1, semestre);
			stmt.setDouble(2, nota);
			stmt.setInt(3, faltas);

			// Define qual registro será alterado
			stmt.setString(4, rgm);
			stmt.setString(5, disciplina);

			// Executa o UPDATE
			int linhas = stmt.executeUpdate();

			// Verifica se algum registro foi alterado
			if (linhas > 0) {
				JOptionPane.showMessageDialog(null, "Nota/faltas alteradas com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Nenhuma nota foi alterada.");
			}

			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar nota/faltas: " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	// Carrega notas e faltas na tabela sem exibir o ID
	public void carregarNotasNaTabela(String rgm, DefaultTableModel modelo) {

		String sql = """
					SELECT disciplina, semestre, nota, faltas
					FROM notas_faltas
					WHERE rgm = ?
					ORDER BY semestre, disciplina
				""";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);

			var rs = stmt.executeQuery();

			// Adiciona cada nota encontrada na tabela
			while (rs.next()) {
				modelo.addRow(new Object[] { rs.getString("disciplina"), rs.getString("semestre"), rs.getDouble("nota"),
						rs.getInt("faltas") });
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar notas: " + erro.getMessage());
		}
	}

	// Carrega notas e faltas na tabela exibindo também o ID do registro
	public void carregarNotasComIdNaTabela(String rgm, DefaultTableModel modelo) {

		String sql = """
					SELECT id, disciplina, semestre, nota, faltas
					FROM notas_faltas
					WHERE rgm = ?
					ORDER BY semestre, disciplina
				""";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);

			var rs = stmt.executeQuery();

			// Adiciona cada registro na tabela, incluindo o ID
			while (rs.next()) {
				modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("disciplina"), rs.getString("semestre"),
						rs.getDouble("nota"), rs.getInt("faltas") });
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar notas: " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	// Exclui uma nota/disciplina usando o ID do registro
	public boolean excluirNotaPorId(int idNota) {

		String sql = "DELETE FROM notas_faltas WHERE id = ?";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define o ID da nota que será excluída
			stmt.setInt(1, idNota);

			// Executa o DELETE
			int linhas = stmt.executeUpdate();

			stmt.close();
			conn.close();

			// Verifica se o registro foi excluído
			if (linhas > 0) {
				JOptionPane.showMessageDialog(null, "Disciplina excluída com sucesso!");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Nenhuma disciplina foi excluída.");
				return false;
			}

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir disciplina: " + erro.getMessage());
			erro.printStackTrace();
			return false;
		}
	}
}