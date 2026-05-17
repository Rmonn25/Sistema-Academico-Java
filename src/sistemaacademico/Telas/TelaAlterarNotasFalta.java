package sistemaacademico.Telas;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sistemaacademico.DAO.NotaFaltaDAO;

public class TelaAlterarNotasFalta extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaAlterarNotasFalta() {

		setTitle("Alterar Notas e Faltas");
		setBounds(150, 150, 620, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Campos dos dados dos alunos
		Label lblBusca = new Label("RGM ou CPF");
		lblBusca.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBusca.setBounds(20, 20, 90, 25);
		getContentPane().add(lblBusca);

		JFormattedTextField Text_Busca = new JFormattedTextField();
		Text_Busca.setBounds(120, 20, 160, 28);
		getContentPane().add(Text_Busca);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(300, 20, 100, 28);
		getContentPane().add(btnBuscar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(420, 20, 100, 28);
		getContentPane().add(btnAlterar);

		Label lblNome = new Label("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(20, 65, 90, 25);
		getContentPane().add(lblNome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Nome.setBounds(120, 65, 360, 28);
		Text_Nome.setEditable(false);
		getContentPane().add(Text_Nome);

		Label lblPeriodo = new Label("Período");
		lblPeriodo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPeriodo.setBounds(20, 100, 90, 25);
		getContentPane().add(lblPeriodo);

		JFormattedTextField Text_Periodo = new JFormattedTextField();
		Text_Periodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Periodo.setBounds(120, 100, 120, 28);
		Text_Periodo.setEditable(false);
		getContentPane().add(Text_Periodo);

		Label lblDisciplina = new Label("Disciplina");
		lblDisciplina.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDisciplina.setBounds(20, 135, 90, 25);
		getContentPane().add(lblDisciplina);

		JComboBox<String> comboDisciplina = new JComboBox<>();
		comboDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboDisciplina.setBounds(120, 135, 360, 28);
		getContentPane().add(comboDisciplina);

		Label lblSemestre = new Label("Semestre");
		lblSemestre.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSemestre.setBounds(20, 180, 90, 25);
		getContentPane().add(lblSemestre);

		JFormattedTextField Text_Semestre = new JFormattedTextField();
		Text_Semestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Semestre.setBounds(120, 180, 120, 28);
		getContentPane().add(Text_Semestre);

		Label lblNota = new Label("Nota");
		lblNota.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNota.setBounds(270, 180, 50, 25);
		getContentPane().add(lblNota);

		JFormattedTextField Text_Nota = new JFormattedTextField();
		Text_Nota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Nota.setBounds(320, 180, 70, 28);
		getContentPane().add(Text_Nota);

		Label lblFaltas = new Label("Faltas");
		lblFaltas.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFaltas.setBounds(410, 180, 60, 25);
		getContentPane().add(lblFaltas);

		JFormattedTextField Text_Faltas = new JFormattedTextField();
		Text_Faltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Faltas.setBounds(470, 180, 70, 28);
		getContentPane().add(Text_Faltas);

		final String[] rgmEncontrado = new String[1];

		// Botão para buscar os dados
		btnBuscar.addActionListener(e -> {

			String busca = Text_Busca.getText().trim();

			if (busca.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o RGM ou CPF.");
				return;
			}

			comboDisciplina.removeAllItems();
			Text_Semestre.setText("");
			Text_Nota.setText("");
			Text_Faltas.setText("");

			NotaFaltaDAO dao = new NotaFaltaDAO();

			String[] aluno = dao.buscarAlunoParaNotas(busca);

			if (aluno != null) {
				rgmEncontrado[0] = aluno[0];
				Text_Nome.setText(aluno[1]);
				Text_Periodo.setText(aluno[2]);

				String[] disciplinas = dao.listarDisciplinasPorRgm(rgmEncontrado[0]);

				for (String disciplina : disciplinas) {
					comboDisciplina.addItem(disciplina);
				}

			} else {
				Text_Nome.setText("");
				Text_Periodo.setText("");
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
			}
		});

		comboDisciplina.addActionListener(e -> {

			if (comboDisciplina.getSelectedItem() == null || rgmEncontrado[0] == null) {
				return;
			}

			NotaFaltaDAO dao = new NotaFaltaDAO();

			String disciplina = comboDisciplina.getSelectedItem().toString();

			String[] dadosNota = dao.buscarNotaPorDisciplina(rgmEncontrado[0], disciplina);

			if (dadosNota != null) {
				Text_Semestre.setText(dadosNota[0]);
				Text_Nota.setText(dadosNota[1]);
				Text_Faltas.setText(dadosNota[2]);
			}
		});

		// Botão para alterar os dados
		btnAlterar.addActionListener(e -> {

			try {
				if (rgmEncontrado[0] == null || comboDisciplina.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Busque um aluno e selecione uma disciplina.");
					return;
				}

				String rgm = rgmEncontrado[0];
				String disciplina = comboDisciplina.getSelectedItem().toString();
				String semestre = Text_Semestre.getText().trim();
				double nota = Double.parseDouble(Text_Nota.getText().replace(",", "."));
				int faltas = Integer.parseInt(Text_Faltas.getText().trim());

				NotaFaltaDAO dao = new NotaFaltaDAO();
				dao.alterar(rgm, disciplina, semestre, nota, faltas);

			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar nota/faltas: " + erro.getMessage());
			}
		});
	}
}