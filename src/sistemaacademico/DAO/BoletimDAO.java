package sistemaacademico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.Conexao;

// Classe responsável pelas consultas do boletim acadêmico
public class BoletimDAO {

	// Método responsável por carregar o boletim completo do aluno pelo RGM
	public void carregarBoletim(String rgm, JFormattedTextField campoNome, JFormattedTextField campoCurso,
			JFormattedTextField campoPeriodo, DefaultTableModel modelo) {

		// Consulta os dados básicos do aluno
		String sqlAluno = "SELECT nome, curso, periodo FROM alunos WHERE rgm = ?";

		// Consulta as disciplinas, semestre, notas e faltas do aluno
		String sqlNotas = """
					SELECT disciplina, semestre, nota, faltas
					FROM notas_faltas
					WHERE rgm = ?
					ORDER BY semestre, disciplina
				""";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Limpa a tabela antes de carregar novos dados
			modelo.setRowCount(0);

			// Prepara a consulta dos dados do aluno
			PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno);
			stmtAluno.setString(1, rgm);

			// Executa a consulta do aluno
			ResultSet rsAluno = stmtAluno.executeQuery();

			// Se o aluno for encontrado, preenche os campos da tela
			if (rsAluno.next()) {
				campoNome.setText(rsAluno.getString("nome"));
				campoCurso.setText(rsAluno.getString("curso"));
				campoPeriodo.setText(rsAluno.getString("periodo"));
			} else {
				// Caso o aluno não seja encontrado, limpa os campos
				campoNome.setText("");
				campoCurso.setText("");
				campoPeriodo.setText("");
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				return;
			}

			// Prepara a consulta das notas e faltas
			PreparedStatement stmtNotas = conn.prepareStatement(sqlNotas);
			stmtNotas.setString(1, rgm);

			// Executa a consulta das notas
			ResultSet rsNotas = stmtNotas.executeQuery();

			// Adiciona cada disciplina encontrada na tabela do boletim
			while (rsNotas.next()) {
				modelo.addRow(new Object[] { rsNotas.getString("disciplina"), rsNotas.getString("semestre"),
						rsNotas.getDouble("nota"), rsNotas.getInt("faltas") });
			}

			// Fecha os recursos utilizados
			rsAluno.close();
			rsNotas.close();
			stmtAluno.close();
			stmtNotas.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe erro caso ocorra problema ao carregar o boletim
			JOptionPane.showMessageDialog(null, "Erro ao carregar boletim: " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	// Método responsável por carregar o boletim com filtro por disciplina e semestre
	public void carregarBoletimFiltrado(String rgm, String disciplina, String semestre, JFormattedTextField campoNome,
			JFormattedTextField campoCurso, JFormattedTextField campoPeriodo, DefaultTableModel modelo) {

		// Consulta os dados básicos do aluno
		String sqlAluno = "SELECT nome, curso, periodo FROM alunos WHERE rgm = ?";

		// Consulta as notas usando filtros opcionais
		// Se disciplina for "Todas", traz todas as disciplinas
		// Se semestre for "Todos", traz todos os semestres
		String sqlNotas = """
			SELECT disciplina, semestre, nota, faltas
			FROM notas_faltas
			WHERE rgm = ?
			AND (? = 'Todas' OR disciplina = ?)
			AND (? = 'Todos' OR semestre = ?)
			ORDER BY semestre, disciplina
		""";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Limpa a tabela antes de aplicar o filtro
			modelo.setRowCount(0);

			// Prepara a consulta dos dados do aluno
			PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno);
			stmtAluno.setString(1, rgm);

			// Executa a consulta do aluno
			var rsAluno = stmtAluno.executeQuery();

			// Preenche os campos do aluno caso ele seja encontrado
			if (rsAluno.next()) {
				campoNome.setText(rsAluno.getString("nome"));
				campoCurso.setText(rsAluno.getString("curso"));
				campoPeriodo.setText(rsAluno.getString("periodo"));
			} else {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				return;
			}

			// Prepara a consulta das notas filtradas
			PreparedStatement stmtNotas = conn.prepareStatement(sqlNotas);
			stmtNotas.setString(1, rgm);
			stmtNotas.setString(2, disciplina);
			stmtNotas.setString(3, disciplina);
			stmtNotas.setString(4, semestre);
			stmtNotas.setString(5, semestre);

			// Executa a consulta das notas filtradas
			var rsNotas = stmtNotas.executeQuery();

			// Adiciona os resultados na tabela
			while (rsNotas.next()) {
				modelo.addRow(new Object[] { rsNotas.getString("disciplina"), rsNotas.getString("semestre"),
						rsNotas.getDouble("nota"), rsNotas.getInt("faltas") });
			}

			// Fecha os recursos utilizados
			rsNotas.close();
			rsAluno.close();
			stmtNotas.close();
			stmtAluno.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe erro caso ocorra problema no filtro
			JOptionPane.showMessageDialog(null, "Erro ao filtrar boletim: " + erro.getMessage());
		}
	}

	// Método alternativo responsável por filtrar o boletim usando LIKE
	public void carregarBoletimFiltrado1(String rgm, String disciplina, String semestre, JFormattedTextField campoNome,
			JFormattedTextField campoCurso, JFormattedTextField campoPeriodo, DefaultTableModel modelo) {

		// Consulta as notas e faltas filtrando por disciplina e semestre
		String sqlNotas = """
				SELECT disciplina, semestre, nota, faltas
				FROM notas_faltas
				WHERE rgm = ?
				AND disciplina LIKE ?
				AND semestre LIKE ?
				ORDER BY semestre, disciplina
			""";

		try {
			// Abre conexão com o banco
			Connection conn = Conexao.conectar();

			// Limpa a tabela antes de aplicar o filtro
			modelo.setRowCount(0);

			// Prepara a consulta das notas filtradas
			PreparedStatement stmtNotas = conn.prepareStatement(sqlNotas);
			stmtNotas.setString(1, rgm);

			// Se a opção for "Todas", usa "%" para trazer qualquer disciplina
			if (disciplina.equals("Todas")) {
				stmtNotas.setString(2, "%");
			} else {
				stmtNotas.setString(2, disciplina);
			}

			// Se a opção for "Todos", usa "%" para trazer qualquer semestre
			if (semestre.equals("Todos")) {
				stmtNotas.setString(3, "%");
			} else {
				stmtNotas.setString(3, semestre);
			}

			// Executa a consulta
			var rsNotas = stmtNotas.executeQuery();

			// Preenche a tabela com o resultado filtrado
			while (rsNotas.next()) {
				modelo.addRow(new Object[] { rsNotas.getString("disciplina"), rsNotas.getString("semestre"),
						rsNotas.getDouble("nota"), rsNotas.getInt("faltas") });
			}

			// Fecha os recursos utilizados
			rsNotas.close();
			stmtNotas.close();
			conn.close();

		} catch (Exception erro) {
			// Exibe erro caso ocorra problema ao filtrar
			JOptionPane.showMessageDialog(null, "Erro ao filtrar boletim: " + erro.getMessage());
		}
	}
}