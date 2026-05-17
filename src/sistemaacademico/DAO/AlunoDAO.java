package sistemaacademico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import sistemaacademico.Aluno;
import sistemaacademico.Conexao;

public class AlunoDAO {

	public void salvar(Aluno aluno) {

		String sql = """
				    INSERT INTO alunos
				    (rgm, nome, data_nasc, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo)
				    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try {
			Connection conn = Conexao.conectar();

			if (conn == null) {
				JOptionPane.showMessageDialog(null, "A conexão com o banco falhou. Verifique a classe Conexao.");
				return;
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, aluno.getRgm());
			stmt.setString(2, aluno.getNome());

			String dataTela = aluno.getDataNasc();
			String[] partes = dataTela.split("/");
			String dataBanco = partes[2] + "-" + partes[1] + "-" + partes[0];
			stmt.setString(3, dataBanco);

			stmt.setString(4, aluno.getCpf());
			stmt.setString(5, aluno.getEmail());
			stmt.setString(6, aluno.getEndereco());
			stmt.setString(7, aluno.getMunicipio());
			stmt.setString(8, aluno.getUf());
			stmt.setString(9, aluno.getCelular());
			stmt.setString(10, aluno.getCurso());
			stmt.setString(11, aluno.getCampus());
			stmt.setString(12, aluno.getPeriodo());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");

			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno: " + erro.getMessage());
		}
	}

	public String[] BuscarAlunoPorRgm(String rgm) {

		String sql = "SELECT nome, curso FROM alunos WHERE rgm = ?";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);

			var qy = stmt.executeQuery();

			if (qy.next()) {
				String nome = qy.getString("nome");
				String curso = qy.getString("curso");

				qy.close();
				stmt.close();
				conn.close();

				return new String[] { nome, curso };
			}

			qy.close();
			stmt.close();
			conn.close();
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + erro.getMessage());
			erro.printStackTrace();
		}

		return null;
	}

	public Aluno buscarPorRgmOuCpf(String busca) {

		String sql = """
					SELECT rgm, nome, data_nasc, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo
					FROM alunos
					WHERE rgm = ? OR cpf = ?
				""";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, busca);
			stmt.setString(2, busca);

			var rs = stmt.executeQuery();

			if (rs.next()) {
				Aluno aluno = new Aluno(rs.getString("rgm"), rs.getString("nome"), rs.getString("data_nasc"),
						rs.getString("cpf"), rs.getString("email"), rs.getString("endereco"), rs.getString("municipio"),
						rs.getString("uf"), rs.getString("celular"), rs.getString("curso"), rs.getString("campus"),
						rs.getString("periodo"));

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

	public void alterar(Aluno aluno) {

		String sql = """
					UPDATE alunos
					SET nome = ?, data_nasc = ?, cpf = ?, email = ?, endereco = ?,
						municipio = ?, uf = ?, celular = ?, curso = ?, campus = ?, periodo = ?
					WHERE rgm = ?
				""";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getDataNasc());
			stmt.setString(3, aluno.getCpf());
			stmt.setString(4, aluno.getEmail());
			stmt.setString(5, aluno.getEndereco());
			stmt.setString(6, aluno.getMunicipio());
			stmt.setString(7, aluno.getUf());
			stmt.setString(8, aluno.getCelular());
			stmt.setString(9, aluno.getCurso());
			stmt.setString(10, aluno.getCampus());
			stmt.setString(11, aluno.getPeriodo());
			stmt.setString(12, aluno.getRgm());

			int linhas = stmt.executeUpdate();

			if (linhas > 0) {
				JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum aluno foi alterado.");
			}

			stmt.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar aluno: " + erro.getMessage());
		}
	}

	public boolean excluirPorRgm(String rgm) {

		String sql = "DELETE FROM alunos WHERE rgm = ?";

		try {
			Connection conn = Conexao.conectar();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rgm);

			int linhas = stmt.executeUpdate();

			stmt.close();
			conn.close();

			if (linhas > 0) {
				JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum aluno foi excluído.");
				return false;
			}

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir aluno: " + erro.getMessage());
			erro.printStackTrace();
			return false;
		}
	}

}