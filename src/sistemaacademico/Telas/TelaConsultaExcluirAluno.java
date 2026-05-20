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

import sistemaacademico.Aluno;
import sistemaacademico.DAO.AlunoDAO;
import sistemaacademico.DAO.NotaFaltaDAO;

// Tela responsável por consultar e excluir alunos
public class TelaConsultaExcluirAluno extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaConsultaExcluirAluno() {

		// Configurações principais da janela
		setTitle("Consultar Aluno");
		setBounds(150, 150, 750, 520);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Campo de busca por RGM ou CPF
		Label lblBusca = new Label("RGM ou CPF");
		lblBusca.setBounds(20, 20, 104, 25);
		lblBusca.setFont(new Font("Dialog", Font.PLAIN, 16));
		getContentPane().add(lblBusca);

		JFormattedTextField Text_Busca = new JFormattedTextField();
		Text_Busca.setBounds(130, 20, 180, 28);
		Text_Busca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(Text_Busca);

		// Botão responsável por consultar os dados do aluno
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(320, 20, 120, 28);
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(btnConsultar);

		// Botão responsável por excluir o aluno consultado
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(455, 20, 100, 28);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(btnExcluir);

		// Campo RGM
		Label lblRgm = new Label("RGM");
		lblRgm.setBounds(20, 65, 90, 25);
		lblRgm.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblRgm);

		JFormattedTextField Text_Rgm = new JFormattedTextField();
		Text_Rgm.setBounds(120, 65, 180, 28);
		Text_Rgm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Rgm.setEditable(false);
		getContentPane().add(Text_Rgm);

		// Campo nome
		Label lblNome = new Label("Nome");
		lblNome.setBounds(20, 100, 90, 25);
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setBounds(120, 100, 580, 28);
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Nome.setEditable(false);
		getContentPane().add(Text_Nome);

		// Campo data de nascimento
		Label lblDataNasc = new Label("Data Nasc.");
		lblDataNasc.setBounds(20, 135, 90, 25);
		lblDataNasc.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblDataNasc);

		JFormattedTextField Text_DataNasc = new JFormattedTextField();
		Text_DataNasc.setBounds(120, 135, 180, 28);
		Text_DataNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_DataNasc.setEditable(false);
		getContentPane().add(Text_DataNasc);

		// Campo CPF
		Label lblCpf = new Label("CPF");
		lblCpf.setBounds(350, 135, 42, 25);
		lblCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblCpf);

		JFormattedTextField Text_Cpf = new JFormattedTextField();
		Text_Cpf.setBounds(400, 135, 180, 28);
		Text_Cpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Cpf.setEditable(false);
		getContentPane().add(Text_Cpf);

		// Campo email
		Label lblEmail = new Label("Email");
		lblEmail.setBounds(20, 170, 90, 25);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblEmail);

		JFormattedTextField Text_Email = new JFormattedTextField();
		Text_Email.setBounds(120, 170, 580, 28);
		Text_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Email.setEditable(false);
		getContentPane().add(Text_Email);

		// Campo endereço
		Label lblEndereco = new Label("Endereço");
		lblEndereco.setBounds(20, 205, 90, 25);
		lblEndereco.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblEndereco);

		JFormattedTextField Text_Endereco = new JFormattedTextField();
		Text_Endereco.setBounds(120, 205, 580, 28);
		Text_Endereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Endereco.setEditable(false);
		getContentPane().add(Text_Endereco);

		// Campo município
		Label lblMunicipio = new Label("Município");
		lblMunicipio.setBounds(20, 240, 90, 25);
		lblMunicipio.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblMunicipio);

		JFormattedTextField Text_Municipio = new JFormattedTextField();
		Text_Municipio.setBounds(120, 240, 220, 28);
		Text_Municipio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Municipio.setEditable(false);
		getContentPane().add(Text_Municipio);

		// Campo UF
		Label lblUf = new Label("UF");
		lblUf.setBounds(360, 240, 40, 25);
		lblUf.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblUf);

		JFormattedTextField Text_Uf = new JFormattedTextField();
		Text_Uf.setBounds(400, 240, 80, 28);
		Text_Uf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Uf.setEditable(false);
		getContentPane().add(Text_Uf);

		// Campo celular
		Label lblCelular = new Label("Celular");
		lblCelular.setBounds(520, 240, 60, 25);
		lblCelular.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblCelular);

		JFormattedTextField Text_Celular = new JFormattedTextField();
		Text_Celular.setBounds(580, 240, 120, 28);
		Text_Celular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Celular.setEditable(false);
		getContentPane().add(Text_Celular);

		// Campo curso
		Label lblCurso = new Label("Curso");
		lblCurso.setBounds(20, 275, 90, 25);
		lblCurso.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblCurso);

		JFormattedTextField Text_Curso = new JFormattedTextField();
		Text_Curso.setBounds(120, 275, 400, 28);
		Text_Curso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Curso.setEditable(false);
		getContentPane().add(Text_Curso);

		// Campo período
		Label lblPeriodo = new Label("Período");
		lblPeriodo.setBounds(530, 275, 60, 25);
		lblPeriodo.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblPeriodo);

		JFormattedTextField Text_Periodo = new JFormattedTextField();
		Text_Periodo.setBounds(600, 275, 100, 28);
		Text_Periodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Periodo.setEditable(false);
		getContentPane().add(Text_Periodo);

		// Modelo da tabela que exibirá as disciplinas, notas e faltas do aluno
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Disciplina");
		modelo.addColumn("Semestre");
		modelo.addColumn("Nota");
		modelo.addColumn("Faltas");

		// Tabela de notas e faltas
		JTable tabela = new JTable(modelo);
		tabela.setRowHeight(24);

		// Barra de rolagem da tabela
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(20, 325, 690, 130);
		getContentPane().add(scroll);

		// Ação do botão Consultar
		// Busca o aluno pelo RGM ou CPF e exibe seus dados e notas
		btnConsultar.addActionListener(e -> {

			String busca = Text_Busca.getText().trim();

			if (busca.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o RGM ou CPF.");
				return;
			}

			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.buscarPorRgmOuCpf(busca);

			// Limpa a tabela antes de carregar novas notas
			modelo.setRowCount(0);

			if (aluno != null) {
				Text_Rgm.setText(aluno.getRgm());
				Text_Nome.setText(aluno.getNome());
				Text_DataNasc.setText(aluno.getDataNasc());
				Text_Cpf.setText(aluno.getCpf());
				Text_Email.setText(aluno.getEmail());
				Text_Endereco.setText(aluno.getEndereco());
				Text_Municipio.setText(aluno.getMunicipio());
				Text_Uf.setText(aluno.getUf());
				Text_Celular.setText(aluno.getCelular());
				Text_Curso.setText(aluno.getCurso());
				Text_Periodo.setText(aluno.getPeriodo());

				// Carrega as notas e faltas do aluno na tabela
				NotaFaltaDAO notaDAO = new NotaFaltaDAO();
				notaDAO.carregarNotasNaTabela(aluno.getRgm(), modelo);

			} else {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
			}
		});

		// Ação do botão Excluir
		// Exclui o aluno consultado e, por causa do ON DELETE CASCADE,
		// as notas/faltas ligadas a ele também são removidas
		btnExcluir.addActionListener(e -> {

			String rgm = Text_Rgm.getText().trim();

			if (rgm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Consulte um aluno antes de excluir.");
				return;
			}

			// Confirmação antes de excluir definitivamente
			int opcao = JOptionPane.showConfirmDialog(null,
					"Deseja realmente excluir este aluno?\nAs notas e faltas também serão excluídas.",
					"Confirmar exclusão", JOptionPane.YES_NO_OPTION);

			if (opcao != JOptionPane.YES_OPTION) {
				return;
			}

			AlunoDAO alunoDAO = new AlunoDAO();
			boolean excluiu = alunoDAO.excluirPorRgm(rgm);

			// Se excluiu com sucesso, limpa todos os campos e a tabela
			if (excluiu) {
				Text_Busca.setText("");
				Text_Rgm.setText("");
				Text_Nome.setText("");
				Text_DataNasc.setText("");
				Text_Cpf.setText("");
				Text_Email.setText("");
				Text_Endereco.setText("");
				Text_Municipio.setText("");
				Text_Uf.setText("");
				Text_Celular.setText("");
				Text_Curso.setText("");
				Text_Periodo.setText("");
				modelo.setRowCount(0);
			}
		});
	}
}