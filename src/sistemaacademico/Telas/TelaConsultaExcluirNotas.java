package sistemaacademico.Telas;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.DAO.NotaFaltaDAO;

// Tela responsável por consultar e excluir notas/faltas de um aluno
public class TelaConsultaExcluirNotas extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaConsultaExcluirNotas() {

		// Configurações principais da janela
		setTitle("Consultar e Excluir Notas");
		setBounds(150, 150, 700, 430);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Campo de busca por RGM ou CPF
		Label lblBusca = new Label("RGM ou CPF");
		lblBusca.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblBusca.setBounds(14, 23, 100, 25);
		getContentPane().add(lblBusca);

		JFormattedTextField Text_Busca = new JFormattedTextField();
		Text_Busca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Busca.setBounds(120, 20, 160, 28);
		getContentPane().add(Text_Busca);

		// Botão responsável por consultar as notas do aluno
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsultar.setBounds(300, 20, 120, 28);
		getContentPane().add(btnConsultar);

		// Botão responsável por excluir a disciplina selecionada
		JButton btnExcluir = new JButton("Excluir Disciplina");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExcluir.setBounds(440, 20, 170, 28);
		getContentPane().add(btnExcluir);

		// Campo que exibe o nome do aluno
		Label lblNome = new Label("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNome.setBounds(20, 70, 60, 25);
		getContentPane().add(lblNome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setEditable(false);
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Nome.setBounds(90, 70, 370, 28);
		getContentPane().add(Text_Nome);

		// Campo que exibe o período do aluno
		Label lblPeriodo = new Label("Período");
		lblPeriodo.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPeriodo.setBounds(480, 70, 70, 25);
		getContentPane().add(lblPeriodo);

		JFormattedTextField Text_Periodo = new JFormattedTextField();
		Text_Periodo.setEditable(false);
		Text_Periodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Periodo.setBounds(550, 70, 100, 28);
		getContentPane().add(Text_Periodo);

		// Modelo da tabela que armazenará os dados exibidos na tela
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Disciplina");
		modelo.addColumn("Semestre");
		modelo.addColumn("Nota");
		modelo.addColumn("Faltas");

		// Tabela que exibe as disciplinas, notas e faltas cadastradas
		JTable tabela = new JTable(modelo);
		tabela.setRowHeight(24);

		// Barra de rolagem da tabela
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(20, 120, 630, 230);
		getContentPane().add(scroll);

		// Ação do botão Consultar
		// Busca o aluno por RGM ou CPF e carrega suas notas/faltas na tabela
		btnConsultar.addActionListener(e -> {

			String busca = Text_Busca.getText().trim();

			if (busca.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o RGM ou CPF.");
				return;
			}

			// Limpa a tabela antes de carregar uma nova consulta
			modelo.setRowCount(0);

			NotaFaltaDAO dao = new NotaFaltaDAO();

			// Busca dados básicos do aluno
			String[] aluno = dao.buscarAlunoParaNotas(busca);

			if (aluno != null) {
				String rgmEncontrado = aluno[0];

				Text_Nome.setText(aluno[1]);
				Text_Periodo.setText(aluno[2]);

				// Carrega as notas/faltas do aluno na tabela, incluindo o ID do registro
				dao.carregarNotasComIdNaTabela(rgmEncontrado, modelo);

			} else {
				Text_Nome.setText("");
				Text_Periodo.setText("");
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
			}
		});

		// Ação do botão Excluir Disciplina
		// Exclui a disciplina/nota selecionada na tabela
		btnExcluir.addActionListener(e -> {

			// Obtém a linha selecionada na tabela
			int linhaSelecionada = tabela.getSelectedRow();

			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione uma disciplina na tabela.");
				return;
			}

			// Pega o ID da nota e o nome da disciplina selecionada
			int idNota = Integer.parseInt(modelo.getValueAt(linhaSelecionada, 0).toString());
			String disciplina = modelo.getValueAt(linhaSelecionada, 1).toString();

			// Confirmação antes de excluir a disciplina
			int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir a disciplina: " + disciplina + "?",
					"Confirmar exclusão", JOptionPane.YES_NO_OPTION);

			if (opcao != JOptionPane.YES_OPTION) {
				return;
			}

			NotaFaltaDAO dao = new NotaFaltaDAO();

			// Exclui a nota/faltas pelo ID do registro
			boolean excluiu = dao.excluirNotaPorId(idNota);

			// Remove a linha da tabela caso a exclusão funcione
			if (excluiu) {
				modelo.removeRow(linhaSelecionada);
			}
		});
	}
}