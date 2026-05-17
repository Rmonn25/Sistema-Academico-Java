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

public class TelaConsultaExcluirNotas extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaConsultaExcluirNotas() {

		setTitle("Consultar e Excluir Notas");
		setBounds(150, 150, 700, 430);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		Label lblBusca = new Label("RGM ou CPF");
		lblBusca.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblBusca.setBounds(14, 23, 100, 25);
		getContentPane().add(lblBusca);

		JFormattedTextField Text_Busca = new JFormattedTextField();
		Text_Busca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Busca.setBounds(120, 20, 160, 28);
		getContentPane().add(Text_Busca);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsultar.setBounds(300, 20, 120, 28);
		getContentPane().add(btnConsultar);

		JButton btnExcluir = new JButton("Excluir Disciplina");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExcluir.setBounds(440, 20, 170, 28);
		getContentPane().add(btnExcluir);

		Label lblNome = new Label("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNome.setBounds(20, 70, 60, 25);
		getContentPane().add(lblNome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setEditable(false);
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Nome.setBounds(90, 70, 370, 28);
		getContentPane().add(Text_Nome);

		Label lblPeriodo = new Label("Período");
		lblPeriodo.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPeriodo.setBounds(480, 70, 70, 25);
		getContentPane().add(lblPeriodo);

		JFormattedTextField Text_Periodo = new JFormattedTextField();
		Text_Periodo.setEditable(false);
		Text_Periodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Periodo.setBounds(550, 70, 100, 28);
		getContentPane().add(Text_Periodo);

		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Disciplina");
		modelo.addColumn("Semestre");
		modelo.addColumn("Nota");
		modelo.addColumn("Faltas");

		JTable tabela = new JTable(modelo);
		tabela.setRowHeight(24);

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(20, 120, 630, 230);
		getContentPane().add(scroll);

		btnConsultar.addActionListener(e -> {

			String busca = Text_Busca.getText().trim();

			if (busca.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o RGM ou CPF.");
				return;
			}

			modelo.setRowCount(0);

			NotaFaltaDAO dao = new NotaFaltaDAO();

			String[] aluno = dao.buscarAlunoParaNotas(busca);

			if (aluno != null) {
				String rgmEncontrado = aluno[0];

				Text_Nome.setText(aluno[1]);
				Text_Periodo.setText(aluno[2]);

				dao.carregarNotasComIdNaTabela(rgmEncontrado, modelo);

			} else {
				Text_Nome.setText("");
				Text_Periodo.setText("");
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
			}
		});

		btnExcluir.addActionListener(e -> {

			int linhaSelecionada = tabela.getSelectedRow();

			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione uma disciplina na tabela.");
				return;
			}

			int idNota = Integer.parseInt(modelo.getValueAt(linhaSelecionada, 0).toString());
			String disciplina = modelo.getValueAt(linhaSelecionada, 1).toString();

			int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir a disciplina: " + disciplina + "?",
					"Confirmar exclusão", JOptionPane.YES_NO_OPTION);

			if (opcao != JOptionPane.YES_OPTION) {
				return;
			}

			NotaFaltaDAO dao = new NotaFaltaDAO();

			boolean excluiu = dao.excluirNotaPorId(idNota);

			if (excluiu) {
				modelo.removeRow(linhaSelecionada);
			}
		});
	}
}