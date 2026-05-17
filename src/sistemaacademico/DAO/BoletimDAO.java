package sistemaacademico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.Conexao;

public class BoletimDAO {

	public void carregarBoletim(String rgm, JFormattedTextField campoNome, JFormattedTextField campoCurso,
			JFormattedTextField campoPeriodo, DefaultTableModel modelo) {

		String sqlAluno = "SELECT nome, curso, periodo FROM alunos WHERE rgm = ?";

		String sqlNotas = """
					SELECT disciplina, semestre, nota, faltas
					FROM notas_faltas
					WHERE rgm = ?
					ORDER BY semestre, disciplina
				""";

		try {
			Connection conn = Conexao.conectar();

			modelo.setRowCount(0);

			PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno);
			stmtAluno.setString(1, rgm);

			ResultSet rsAluno = stmtAluno.executeQuery();

			if (rsAluno.next()) {
				campoNome.setText(rsAluno.getString("nome"));
				campoCurso.setText(rsAluno.getString("curso"));
				campoPeriodo.setText(rsAluno.getString("periodo"));
			} else {
				campoNome.setText("");
				campoCurso.setText("");
				campoPeriodo.setText("");
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				return;
			}

			PreparedStatement stmtNotas = conn.prepareStatement(sqlNotas);
			stmtNotas.setString(1, rgm);

			ResultSet rsNotas = stmtNotas.executeQuery();

			while (rsNotas.next()) {
				modelo.addRow(new Object[] { rsNotas.getString("disciplina"), rsNotas.getString("semestre"),
						rsNotas.getDouble("nota"), rsNotas.getInt("faltas") });
			}

			rsAluno.close();
			rsNotas.close();
			stmtAluno.close();
			stmtNotas.close();
			conn.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar boletim: " + erro.getMessage());
			erro.printStackTrace();
		}
	}
}