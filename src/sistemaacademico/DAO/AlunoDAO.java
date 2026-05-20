package sistemaacademico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import sistemaacademico.Aluno;
import sistemaacademico.Conexao;

// Classe responsável por realizar as operações no banco de dados relacionadas à tabela alunos
public class AlunoDAO {

	// Método responsável por salvar um novo aluno no banco de dados
	public void salvar(Aluno aluno) {

		// Comando SQL para inserir os dados do aluno na tabela alunos
		String sql = """
				    INSERT INTO alunos
				    (rgm, nome, data_nasc, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo)
				    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try {
			// Abre a conexão com o banco de dados
			Connection conn = Conexao.conectar();

			// Verifica se a conexão falhou
			if (conn == null) {
				JOptionPane.showMessageDialog(null, "A conexão com o banco falhou. Verifique a classe Conexao.");
				return;
			}

			// Prepara o comando SQL para receber os valores
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define os valores que serão inseridos no banco
			stmt.setString(1, aluno.getRgm());
			stmt.setString(2, aluno.getNome());

			// Converte a data do formato dd/MM/yyyy para yyyy-MM-dd, formato aceito pelo MySQL
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

			// Executa o INSERT no banco
			stmt.executeUpdate();

			// Exibe mensagem de sucesso
			JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");

			// Fecha o comando e a conexão
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe mensagem caso ocorra erro no cadastro
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno: " + erro.getMessage());
		}
	}

	// Método usado para buscar apenas o nome e o curso do aluno pelo RGM
	public String[] BuscarAlunoPorRgm(String rgm) {

		// Consulta SQL que busca nome e curso do aluno
		String sql = "SELECT nome, curso FROM alunos WHERE rgm = ?";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Prepara o SELECT
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define o RGM que será pesquisado
			stmt.setString(1, rgm);

			// Executa a consulta
			var qy = stmt.executeQuery();

			// Se encontrar o aluno, retorna nome e curso
			if (qy.next()) {
				String nome = qy.getString("nome");
				String curso = qy.getString("curso");

				// Fecha recursos
				qy.close();
				stmt.close();
				conn.close();

				return new String[] { nome, curso };
			}

			// Fecha recursos caso não encontre aluno
			qy.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe mensagem caso ocorra erro na busca
			JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + erro.getMessage());
			erro.printStackTrace();
		}

		// Retorna null caso não encontre nenhum aluno
		return null;
	}

	// Método responsável por buscar todos os dados do aluno usando RGM ou CPF
	public Aluno buscarPorRgmOuCpf(String busca) {

		// Consulta SQL que permite buscar aluno por RGM ou CPF
		String sql = """
					SELECT rgm, nome, data_nasc, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo
					FROM alunos
					WHERE rgm = ? OR cpf = ?
				""";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Prepara o SELECT
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define os dois parâmetros da busca
			stmt.setString(1, busca);
			stmt.setString(2, busca);

			// Executa a consulta
			var rs = stmt.executeQuery();

			// Se encontrar o aluno, monta um objeto Aluno com os dados do banco
			if (rs.next()) {
				Aluno aluno = new Aluno(rs.getString("rgm"), rs.getString("nome"), rs.getString("data_nasc"),
						rs.getString("cpf"), rs.getString("email"), rs.getString("endereco"), rs.getString("municipio"),
						rs.getString("uf"), rs.getString("celular"), rs.getString("curso"), rs.getString("campus"),
						rs.getString("periodo"));

				// Fecha recursos
				rs.close();
				stmt.close();
				conn.close();

				// Retorna o aluno encontrado
				return aluno;
			}

			// Fecha recursos caso nenhum aluno seja encontrado
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe mensagem caso ocorra erro na busca
			JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + erro.getMessage());
		}

		// Retorna null caso não encontre aluno
		return null;
	}

	// Método responsável por alterar os dados de um aluno no banco
	public void alterar(Aluno aluno) {

		// Comando SQL para atualizar os dados do aluno, mantendo o RGM como referência
		String sql = """
					UPDATE alunos
					SET nome = ?, data_nasc = ?, cpf = ?, email = ?, endereco = ?,
						municipio = ?, uf = ?, celular = ?, curso = ?, campus = ?, periodo = ?
					WHERE rgm = ?
				""";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Prepara o UPDATE
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define os novos valores do aluno
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

			// Define o RGM do aluno que será alterado
			stmt.setString(12, aluno.getRgm());

			// Executa o UPDATE e guarda quantas linhas foram alteradas
			int linhas = stmt.executeUpdate();

			// Verifica se algum registro foi alterado
			if (linhas > 0) {
				JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum aluno foi alterado.");
			}

			// Fecha recursos
			stmt.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe mensagem caso ocorra erro na alteração
			JOptionPane.showMessageDialog(null, "Erro ao alterar aluno: " + erro.getMessage());
		}
	}

	// Método responsável por excluir um aluno pelo RGM
	public boolean excluirPorRgm(String rgm) {

		// Comando SQL para excluir aluno da tabela alunos
		String sql = "DELETE FROM alunos WHERE rgm = ?";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Prepara o DELETE
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Define o RGM do aluno que será excluído
			stmt.setString(1, rgm);

			// Executa o DELETE e guarda a quantidade de linhas excluídas
			int linhas = stmt.executeUpdate();

			// Fecha recursos
			stmt.close();
			conn.close();

			// Verifica se o aluno foi excluído
			if (linhas > 0) {
				JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum aluno foi excluído.");
				return false;
			}

		} catch (Exception erro) {
			// Exibe mensagem caso ocorra erro na exclusão
			JOptionPane.showMessageDialog(null, "Erro ao excluir aluno: " + erro.getMessage());
			erro.printStackTrace();
			return false;
		}
	}

}