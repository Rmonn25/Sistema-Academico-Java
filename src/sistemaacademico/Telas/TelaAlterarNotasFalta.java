package sistemaacademico.Telas;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sistemaacademico.DAO.NotaFaltaDAO;

// Tela responsável por alterar notas e faltas de um aluno
public class TelaAlterarNotasFalta extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaAlterarNotasFalta() {

		// Configurações principais da janela
		setTitle("Alterar Notas e Faltas");
		setBounds(150, 150, 620, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Campo de busca por RGM ou CPF
		Label lblBusca = new Label("RGM ou CPF");
		lblBusca.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBusca.setBounds(20, 20, 90, 25);
		getContentPane().add(lblBusca);

		JFormattedTextField Text_Busca = new JFormattedTextField();
		Text_Busca.setBounds(120, 20, 160, 28);
		getContentPane().add(Text_Busca);

		// Botão responsável por buscar o aluno
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(300, 20, 100, 28);
		getContentPane().add(btnBuscar);

		// Botão responsável por alterar nota e faltas
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(420, 20, 100, 28);
		getContentPane().add(btnAlterar);

		// Campo nome do aluno
		Label lblNome = new Label("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(20, 65, 90, 25);
		getContentPane().add(lblNome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Nome.setBounds(120, 65, 360, 28);
		Text_Nome.setEditable(false);
		getContentPane().add(Text_Nome);

		// Campo período do aluno
		Label lblPeriodo = new Label("Período");
		lblPeriodo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPeriodo.setBounds(20, 100, 90, 25);
		getContentPane().add(lblPeriodo);

		JFormattedTextField Text_Periodo = new JFormattedTextField();
		Text_Periodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Periodo.setBounds(120, 100, 120, 28);
		Text_Periodo.setEditable(false);
		getContentPane().add(Text_Periodo);

		// ComboBox com as disciplinas cadastradas para o aluno
		Label lblDisciplina = new Label("Disciplina");
		lblDisciplina.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDisciplina.setBounds(20, 135, 90, 25);
		getContentPane().add(lblDisciplina);

		JComboBox<String> comboDisciplina = new JComboBox<>();
		comboDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboDisciplina.setBounds(120, 135, 360, 28);
		getContentPane().add(comboDisciplina);

		// Campo semestre
		Label lblSemestre = new Label("Semestre");
		lblSemestre.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSemestre.setBounds(20, 180, 90, 25);
		getContentPane().add(lblSemestre);

		JFormattedTextField Text_Semestre = new JFormattedTextField();
		Text_Semestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Semestre.setBounds(120, 180, 120, 28);
		getContentPane().add(Text_Semestre);

		// Campo nota
		Label lblNota = new Label("Nota");
		lblNota.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNota.setBounds(270, 180, 50, 25);
		getContentPane().add(lblNota);

		JFormattedTextField Text_Nota = new JFormattedTextField();
		Text_Nota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Nota.setBounds(320, 180, 70, 28);
		getContentPane().add(Text_Nota);

		// Campo faltas
		Label lblFaltas = new Label("Faltas");
		lblFaltas.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFaltas.setBounds(410, 180, 60, 25);
		getContentPane().add(lblFaltas);

		JFormattedTextField Text_Faltas = new JFormattedTextField();
		Text_Faltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Faltas.setBounds(470, 180, 70, 28);
		getContentPane().add(Text_Faltas);

		// Array usado para guardar o RGM encontrado na busca
		// Foi usado array porque variáveis usadas dentro de lambda precisam ser finais ou efetivamente finais
		final String[] rgmEncontrado = new String[1];

		// Ação do botão Buscar
		// Busca o aluno por RGM ou CPF e carrega suas disciplinas
		btnBuscar.addActionListener(e -> {

			String busca = Text_Busca.getText().trim();

			if (busca.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o RGM ou CPF.");
				return;
			}

			// Limpa os campos antes de carregar uma nova busca
			comboDisciplina.removeAllItems();
			Text_Semestre.setText("");
			Text_Nota.setText("");
			Text_Faltas.setText("");

			NotaFaltaDAO dao = new NotaFaltaDAO();

			// Busca dados básicos do aluno
			String[] aluno = dao.buscarAlunoParaNotas(busca);

			if (aluno != null) {
				rgmEncontrado[0] = aluno[0];
				Text_Nome.setText(aluno[1]);
				Text_Periodo.setText(aluno[2]);

				// Lista as disciplinas que possuem nota/faltas cadastradas para o aluno
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

		// Ação executada quando uma disciplina é selecionada no ComboBox
		// Carrega semestre, nota e faltas da disciplina selecionada
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

		// Ação do botão Alterar
		// Envia os dados alterados para o DAO atualizar no banco de dados
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