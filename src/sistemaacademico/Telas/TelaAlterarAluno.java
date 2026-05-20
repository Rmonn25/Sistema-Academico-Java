package sistemaacademico.Telas;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import sistemaacademico.Aluno;
import sistemaacademico.DAO.AlunoDAO;

// Tela responsável por alterar os dados cadastrais de um aluno
public class TelaAlterarAluno extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaAlterarAluno() {

		// Configurações principais da janela
		setTitle("Alterar Aluno");
		setBounds(150, 150, 720, 430);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Campo usado para buscar o aluno pelo RGM ou CPF
		Label lblBusca = new Label("RGM ou CPF");
		lblBusca.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBusca.setBounds(20, 20, 90, 25);
		getContentPane().add(lblBusca);

		JFormattedTextField Text_Busca = new JFormattedTextField();
		Text_Busca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Busca.setBounds(120, 20, 180, 28);
		getContentPane().add(Text_Busca);

		// Botão responsável por buscar os dados do aluno
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(320, 20, 100, 28);
		getContentPane().add(btnBuscar);

		// Botão responsável por salvar as alterações realizadas
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(440, 20, 100, 28);
		getContentPane().add(btnAlterar);

		// Campo RGM
		Label lblRgm = new Label("RGM");
		lblRgm.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblRgm.setBounds(20, 65, 90, 25);
		getContentPane().add(lblRgm);

		JFormattedTextField Text_Rgm = new JFormattedTextField();
		Text_Rgm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Rgm.setBounds(120, 65, 160, 28);
		getContentPane().add(Text_Rgm);

		// Campo nome
		Label lblNome = new Label("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(20, 100, 90, 25);
		getContentPane().add(lblNome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Nome.setBounds(120, 100, 500, 28);
		getContentPane().add(Text_Nome);

		// Campo data de nascimento
		Label lblDataNasc = new Label("Data Nasc.");
		lblDataNasc.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDataNasc.setBounds(20, 135, 90, 25);
		getContentPane().add(lblDataNasc);

		JFormattedTextField Text_DataNasc = new JFormattedTextField();
		Text_DataNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_DataNasc.setBounds(120, 135, 160, 28);
		getContentPane().add(Text_DataNasc);

		// Campo CPF
		Label lblCpf = new Label("CPF");
		lblCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCpf.setBounds(334, 135, 60, 25);
		getContentPane().add(lblCpf);

		JFormattedTextField Text_Cpf = new JFormattedTextField();
		Text_Cpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Cpf.setBounds(400, 135, 180, 28);
		getContentPane().add(Text_Cpf);

		// Campo email
		Label lblEmail = new Label("Email");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(20, 170, 90, 25);
		getContentPane().add(lblEmail);

		JFormattedTextField Text_Email = new JFormattedTextField();
		Text_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Email.setBounds(120, 170, 500, 28);
		getContentPane().add(Text_Email);

		// Campo endereço
		Label lblEndereco = new Label("Endereço");
		lblEndereco.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEndereco.setBounds(20, 205, 90, 25);
		getContentPane().add(lblEndereco);

		JFormattedTextField Text_Endereco = new JFormattedTextField();
		Text_Endereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Endereco.setBounds(120, 205, 500, 28);
		getContentPane().add(Text_Endereco);

		// Campo município
		Label lblMunicipio = new Label("Município");
		lblMunicipio.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMunicipio.setBounds(20, 240, 90, 25);
		getContentPane().add(lblMunicipio);

		JFormattedTextField Text_Municipio = new JFormattedTextField();
		Text_Municipio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Municipio.setBounds(120, 240, 200, 28);
		getContentPane().add(Text_Municipio);

		// Campo UF
		Label lblUf = new Label("UF");
		lblUf.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblUf.setBounds(334, 240, 40, 25);
		getContentPane().add(lblUf);

		JFormattedTextField Text_Uf = new JFormattedTextField();
		Text_Uf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Uf.setBounds(380, 240, 60, 28);
		getContentPane().add(Text_Uf);

		// Campo celular
		Label lblCelular = new Label("Celular");
		lblCelular.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCelular.setBounds(460, 240, 60, 25);
		getContentPane().add(lblCelular);

		JFormattedTextField Text_Celular = new JFormattedTextField();
		Text_Celular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Celular.setBounds(520, 240, 130, 28);
		getContentPane().add(Text_Celular);

		// ComboBox para selecionar o curso do aluno
		Label lblCurso = new Label("Curso");
		lblCurso.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCurso.setBounds(20, 275, 90, 25);
		getContentPane().add(lblCurso);

		JComboBox<String> comboBox_Curso = new JComboBox<>();
		comboBox_Curso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_Curso.setModel(new DefaultComboBoxModel<>(new String[] { "Análise e Desenvolvimento de Sistemas",
				"Ciência da Computação", "Engenharia de Software", "Gestão de TI" }));
		comboBox_Curso.setBounds(120, 275, 360, 28);
		getContentPane().add(comboBox_Curso);

		// Campo campus
		Label lblCampus = new Label("Campus");
		lblCampus.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCampus.setBounds(20, 310, 90, 25);
		getContentPane().add(lblCampus);

		JFormattedTextField Text_Campus = new JFormattedTextField();
		Text_Campus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Text_Campus.setBounds(120, 310, 180, 28);
		getContentPane().add(Text_Campus);

		// ComboBox para selecionar o período do aluno
		Label lblPeriodo = new Label("Período");
		lblPeriodo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPeriodo.setBounds(320, 310, 90, 25);
		getContentPane().add(lblPeriodo);

		JComboBox<String> comboBox_Periodo = new JComboBox<>();
		comboBox_Periodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_Periodo.setModel(new DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Noturno" }));
		comboBox_Periodo.setBounds(420, 310, 120, 28);
		getContentPane().add(comboBox_Periodo);

		// Ação do botão Buscar
		// Busca o aluno pelo RGM ou CPF e preenche os campos da tela
		btnBuscar.addActionListener(e -> {

			String busca = Text_Busca.getText().trim();

			AlunoDAO dao = new AlunoDAO();
			Aluno aluno = dao.buscarPorRgmOuCpf(busca);

			if (aluno != null) {
				Text_Rgm.setText(aluno.getRgm());
				Text_Rgm.setEditable(false);

				Text_Nome.setText(aluno.getNome());
				Text_DataNasc.setText(aluno.getDataNasc());
				Text_Cpf.setText(aluno.getCpf());
				Text_Email.setText(aluno.getEmail());
				Text_Endereco.setText(aluno.getEndereco());
				Text_Municipio.setText(aluno.getMunicipio());
				Text_Uf.setText(aluno.getUf());
				Text_Celular.setText(aluno.getCelular());
				comboBox_Curso.setSelectedItem(aluno.getCurso());
				Text_Campus.setText(aluno.getCampus());
				comboBox_Periodo.setSelectedItem(aluno.getPeriodo());
			} else {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
			}
		});

		// Ação do botão Alterar
		// Cria um objeto Aluno com os dados modificados e envia para o DAO atualizar no banco
		btnAlterar.addActionListener(e -> {

			Aluno aluno = new Aluno(Text_Rgm.getText().trim(), Text_Nome.getText().trim(),
					Text_DataNasc.getText().trim(), Text_Cpf.getText().trim(), Text_Email.getText().trim(),
					Text_Endereco.getText().trim(), Text_Municipio.getText().trim(), Text_Uf.getText().trim(),
					Text_Celular.getText().trim(), comboBox_Curso.getSelectedItem().toString(),
					Text_Campus.getText().trim(), comboBox_Periodo.getSelectedItem().toString());

			AlunoDAO dao = new AlunoDAO();
			dao.alterar(aluno);
		});
	}
}