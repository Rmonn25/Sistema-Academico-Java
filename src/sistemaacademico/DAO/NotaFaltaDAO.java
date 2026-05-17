package sistemaacademico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.Conexao;

public class NotaFaltaDAO {

	public void salvar(String rgm, String disciplina, String semestre, double nota, int faltas) {

		String sql = """
				INSERT INTO notas_faltas
				(rgm, disciplina, semestre, nota, faltas)
				VALUES (?, ?, ?, ?, ?)
				""";

		try {
			Connection conn = Conexao.conectar();

			if (conn == null) {
				JOptionPane.showMessageDialog(null, "A conexão com o banco falhou. Verifique a classe Conexao.");
				return;
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);
			stmt.setString(2, disciplina);
			stmt.setString(3, semestre);
			stmt.setDouble(4, nota);
			stmt.setInt(5, faltas);

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Nota e faltas cadastradas com sucesso!");

			stmt.close();
			conn.close();
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar nota/faltas: " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public String[] buscarAlunoParaNotas(String busca) {

		String sql = "SELECT rgm, nome, periodo FROM alunos WHERE rgm = ? OR cpf = ?";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, busca);
			stmt.setString(2, busca);

			var rs = stmt.executeQuery();

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

	public String[] listarDisciplinasPorRgm(String rgm) {

		String sql = "SELECT disciplina FROM notas_faltas WHERE rgm = ? ORDER BY disciplina";

		java.util.ArrayList<String> lista = new java.util.ArrayList<>();

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);

			var rs = stmt.executeQuery();

			while (rs.next()) {
				lista.add(rs.getString("disciplina"));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao listar disciplinas: " + erro.getMessage());
		}

		return lista.toArray(new String[0]);
	}

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

			stmt.setString(1, semestre);
			stmt.setDouble(2, nota);
			stmt.setInt(3, faltas);
			stmt.setString(4, rgm);
			stmt.setString(5, disciplina);

			int linhas = stmt.executeUpdate();

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

	public boolean excluirNotaPorId(int idNota) {

		String sql = "DELETE FROM notas_faltas WHERE id = ?";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idNota);

			int linhas = stmt.executeUpdate();

			stmt.close();
			conn.close();

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
