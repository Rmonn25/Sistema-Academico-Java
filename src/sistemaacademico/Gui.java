package sistemaacademico;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

// Importação dos arquivos
import sistemaacademico.DAO.AlunoDAO;
import sistemaacademico.DAO.BoletimDAO;
import sistemaacademico.DAO.NotaFaltaDAO;
import sistemaacademico.Telas.TelaAlterarNotasFalta;
import sistemaacademico.Telas.TelaAlterarAluno;
import sistemaacademico.Telas.TelaConsultaExcluirAluno;
import sistemaacademico.Telas.TelaConsultaExcluirNotas;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gui() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 395);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Aluno");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem AlterarAluno = new JMenuItem("Alterar");
		mnNewMenu.add(AlterarAluno);

		JMenuItem MenuConsultaAluno = new JMenuItem("Consultar e Excluir");
		mnNewMenu.add(MenuConsultaAluno);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Sair");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_1 = new JMenu("Notas e Faltas");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Salvar");
		mntmNewMenuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem AlterarNotaFalta = new JMenuItem("Alterar");
		AlterarNotaFalta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(AlterarNotaFalta);

		JMenuItem MenuConsultaNota = new JMenuItem("Consultar e Excluir");
		mnNewMenu_1.add(MenuConsultaNota);

		JMenu MenuAjuda = new JMenu("Ajuda");
		menuBar.add(MenuAjuda);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sobre");
		MenuAjuda.add(mntmNewMenuItem_9);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 27, 599, 293);
		contentPane.add(tabbedPane);

		JPanel dados_pessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, dados_pessoais, null);
		dados_pessoais.setLayout(null);

		Label Rgm = new Label("RGM");
		Rgm.setFont(new Font("Dialog", Font.PLAIN, 17));
		Rgm.setBounds(10, 21, 54, 28);
		dados_pessoais.add(Rgm);

		JFormattedTextField Text_Rgm = new JFormattedTextField();
		Text_Rgm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Rgm.setBounds(70, 21, 130, 28);
		dados_pessoais.add(Text_Rgm);

		Label Nome = new Label("Nome");
		Nome.setFont(new Font("Dialog", Font.PLAIN, 17));
		Nome.setBounds(211, 21, 47, 28);
		dados_pessoais.add(Nome);

		JFormattedTextField Text_Nome = new JFormattedTextField();
		Text_Nome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Nome.setBounds(264, 21, 308, 28);
		dados_pessoais.add(Text_Nome);

		Label DataNasc = new Label("Data de Nascimento");
		DataNasc.setFont(new Font("Dialog", Font.PLAIN, 17));
		DataNasc.setBounds(10, 75, 158, 28);
		dados_pessoais.add(DataNasc);

		JFormattedTextField text_DataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		text_DataNasc.setFont(new Font("Dialog", Font.PLAIN, 17));
		text_DataNasc.setBounds(174, 75, 91, 28);
		dados_pessoais.add(text_DataNasc);

		Label CPF = new Label("CPF");
		CPF.setFont(new Font("Dialog", Font.PLAIN, 17));
		CPF.setBounds(318, 75, 42, 28);
		dados_pessoais.add(CPF);

		JFormattedTextField text_CPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		text_CPF.setFont(new Font("Dialog", Font.PLAIN, 17));
		text_CPF.setBounds(366, 75, 130, 28);
		dados_pessoais.add(text_CPF);

		Label Email = new Label("Email");
		Email.setFont(new Font("Dialog", Font.PLAIN, 17));
		Email.setBounds(10, 130, 47, 28);
		dados_pessoais.add(Email);

		JFormattedTextField Text_Email = new JFormattedTextField();
		Text_Email.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Email.setBounds(63, 130, 509, 28);
		dados_pessoais.add(Text_Email);

		Label Endereco = new Label("End.");
		Endereco.setFont(new Font("Dialog", Font.PLAIN, 17));
		Endereco.setBounds(9, 176, 42, 28);
		dados_pessoais.add(Endereco);

		JFormattedTextField Text_Endereco = new JFormattedTextField();
		Text_Endereco.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Endereco.setBounds(63, 176, 510, 28);
		dados_pessoais.add(Text_Endereco);

		JFormattedTextField Text_Municipio = new JFormattedTextField();
		Text_Municipio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Municipio.setBounds(92, 219, 168, 28);
		dados_pessoais.add(Text_Municipio);

		Label UF = new Label("UF");
		UF.setFont(new Font("Dialog", Font.PLAIN, 17));
		UF.setBounds(271, 219, 32, 28);
		dados_pessoais.add(UF);

		JFormattedTextField Text_UF = new JFormattedTextField();
		Text_UF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_UF.setBounds(309, 219, 32, 28);
		dados_pessoais.add(Text_UF);

		Label Municipio = new Label("Município");
		Municipio.setFont(new Font("Dialog", Font.PLAIN, 17));
		Municipio.setBounds(12, 219, 76, 28);
		dados_pessoais.add(Municipio);

		Label Celular = new Label("Celular");
		Celular.setFont(new Font("Dialog", Font.PLAIN, 17));
		Celular.setBounds(358, 219, 54, 28);
		dados_pessoais.add(Celular);

		JFormattedTextField Text_Celular = new JFormattedTextField(new MaskFormatter("(##) ##### - ####"));
		Text_Celular.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Celular.setBounds(418, 219, 156, 28);
		dados_pessoais.add(Text_Celular);

		JPanel curso = new JPanel();
		tabbedPane.addTab("Curso", null, curso, null);
		curso.setLayout(null);

		Label Curso = new Label("Curso");
		Curso.setBounds(13, 13, 54, 28);
		Curso.setFont(new Font("Dialog", Font.PLAIN, 17));
		curso.add(Curso);

		Label Campus = new Label("Campus");
		Campus.setBounds(13, 62, 71, 28);
		Campus.setFont(new Font("Dialog", Font.PLAIN, 17));
		curso.add(Campus);

		Label Periodo = new Label("Período");
		Periodo.setBounds(13, 112, 71, 28);
		Periodo.setFont(new Font("Dialog", Font.PLAIN, 17));
		curso.add(Periodo);

		JRadioButton rdbtn_Matutino = new JRadioButton("Matutino");
		rdbtn_Matutino.setBounds(110, 112, 91, 23);
		rdbtn_Matutino.setFont(new Font("Tahoma", Font.PLAIN, 17));
		curso.add(rdbtn_Matutino);

		JRadioButton rdbtn_Vespertino = new JRadioButton("Vespertino");
		rdbtn_Vespertino.setBounds(222, 112, 109, 23);
		rdbtn_Vespertino.setFont(new Font("Tahoma", Font.PLAIN, 17));
		curso.add(rdbtn_Vespertino);

		JRadioButton rdbtn_Noturno = new JRadioButton("Noturno");
		rdbtn_Noturno.setBounds(345, 112, 91, 23);
		rdbtn_Noturno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		curso.add(rdbtn_Noturno);

		ButtonGroup rdbtn_Periodo = new ButtonGroup();
		rdbtn_Periodo.add(rdbtn_Matutino);
		rdbtn_Periodo.add(rdbtn_Vespertino);
		rdbtn_Periodo.add(rdbtn_Noturno);

		JComboBox<String> comboBox_Campus = new JComboBox<String>();
		comboBox_Campus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_Campus.setModel(new DefaultComboBoxModel<>(new String[] { "Tatuapé" }));
		comboBox_Campus.setBounds(90, 62, 444, 28);
		curso.add(comboBox_Campus);

		JComboBox<String> comboBox_Curso = new JComboBox<>();
		comboBox_Curso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_Curso.setModel(new DefaultComboBoxModel<>(new String[] { "Análise e Desenvolvimento de Sistemas",
				"Ciência da Computação", "Engenharia de Software", "Gestão de TI" }));
		comboBox_Curso.setBounds(90, 13, 444, 28);
		curso.add(comboBox_Curso);

		JPanel notas_e_faltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, notas_e_faltas, null);
		notas_e_faltas.setLayout(null);

		Label lblRgmNota = new Label("RGM");
		lblRgmNota.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblRgmNota.setBounds(15, 20, 50, 28);
		notas_e_faltas.add(lblRgmNota);

		JFormattedTextField Text_Rgm_Nota = new JFormattedTextField();
		Text_Rgm_Nota.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Rgm_Nota.setBounds(70, 20, 120, 28);
		notas_e_faltas.add(Text_Rgm_Nota);

		JFormattedTextField Text_Nome_Nota = new JFormattedTextField();
		Text_Nome_Nota.setEditable(false);
		Text_Nome_Nota.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Nome_Nota.setBounds(200, 20, 370, 28);
		notas_e_faltas.add(Text_Nome_Nota);

		JFormattedTextField Text_Curso_Nota = new JFormattedTextField();
		Text_Curso_Nota.setEditable(false);
		Text_Curso_Nota.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Curso_Nota.setBounds(15, 58, 555, 28);
		notas_e_faltas.add(Text_Curso_Nota);

		Text_Rgm_Nota.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e) {

				String rgm = Text_Rgm_Nota.getText().trim();

				if (rgm.isEmpty()) {
					return;
				}

				AlunoDAO alunoDAO = new AlunoDAO();
				String[] dadosAluno = alunoDAO.BuscarAlunoPorRgm(rgm);

				if (dadosAluno != null) {
					Text_Nome_Nota.setText(dadosAluno[0]);
					Text_Curso_Nota.setText(dadosAluno[1]);
				} else {
					Text_Nome_Nota.setText("");
					Text_Curso_Nota.setText("");
					JOptionPane.showMessageDialog(null, "Aluno não encontrado para o RGM informado.");
				}

			}
		});

		Label lblDisciplina = new Label("Disciplina");
		lblDisciplina.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblDisciplina.setBounds(15, 100, 80, 28);
		notas_e_faltas.add(lblDisciplina);

		JComboBox<String> comboBox_Disciplina = new JComboBox<String>();
		comboBox_Disciplina.setModel(new DefaultComboBoxModel<>(
				new String[] { "Programação Orientada a Objetos", "Estrutura de Dados I", "Banco de Dados",
						"Programação Web", "Engenharia de Software", "Modelagem de dados", "Sistemas Operacionais" }));
		comboBox_Disciplina.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_Disciplina.setBounds(105, 100, 465, 28);
		notas_e_faltas.add(comboBox_Disciplina);

		Label lblSemestre = new Label("Semestre");
		lblSemestre.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblSemestre.setBounds(15, 145, 80, 28);
		notas_e_faltas.add(lblSemestre);

		JComboBox<String> comboBox_Semestre = new JComboBox<String>();
		comboBox_Semestre.setModel(new DefaultComboBoxModel<>(new String[] { "2025-1", "2025-2", "2026-1", "2026-2" }));
		comboBox_Semestre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_Semestre.setBounds(105, 145, 95, 28);
		notas_e_faltas.add(comboBox_Semestre);

		Label lblNota = new Label("Nota");
		lblNota.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNota.setBounds(230, 145, 50, 28);
		notas_e_faltas.add(lblNota);

		JComboBox<String> comboBox_Nota = new JComboBox<String>();
		comboBox_Nota.setModel(new DefaultComboBoxModel<>(new String[] { "0", "0,5", "1", "1,5", "2", "2,5", "3", "3,5",
				"4", "4,5", "5", "5,5", "6", "6,5", "7", "7,5", "8", "8,5", "9", "9,5", "10" }));
		comboBox_Nota.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_Nota.setBounds(285, 145, 70, 28);
		notas_e_faltas.add(comboBox_Nota);

		Label lblFaltas = new Label("Faltas");
		lblFaltas.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblFaltas.setBounds(400, 145, 55, 28);
		notas_e_faltas.add(lblFaltas);

		JFormattedTextField Text_Faltas = new JFormattedTextField();
		Text_Faltas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Faltas.setBounds(460, 145, 110, 28);
		notas_e_faltas.add(Text_Faltas);

		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String rgm = Text_Rgm_Nota.getText().trim();
					String disciplina = comboBox_Disciplina.getSelectedItem().toString();
					String semestre = comboBox_Semestre.getSelectedItem().toString();

					String notaTexto = comboBox_Nota.getSelectedItem().toString().replace(",", ".");
					double nota = Double.parseDouble(notaTexto);

					int faltas = Integer.parseInt(Text_Faltas.getText().trim());

					if (rgm.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Informe o RGM do aluno.");
						return;
					}

					NotaFaltaDAO dao = new NotaFaltaDAO();
					dao.salvar(rgm, disciplina, semestre, nota, faltas);

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar nota/faltas: " + erro.getMessage());
				}
			}
		});

		JPanel boletim = new JPanel();
		tabbedPane.addTab("Boletim", null, boletim, null);

		boletim.setLayout(null);

		Label lblRgmBoletim = new Label("RGM");
		lblRgmBoletim.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblRgmBoletim.setBounds(15, 19, 50, 28);
		boletim.add(lblRgmBoletim);

		JFormattedTextField Text_Rgm_Boletim = new JFormattedTextField();
		Text_Rgm_Boletim.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Text_Rgm_Boletim.setBounds(70, 19, 120, 28);
		boletim.add(Text_Rgm_Boletim);

		JFormattedTextField Text_Nome_Boletim = new JFormattedTextField();
		Text_Nome_Boletim.setEditable(false);
		Text_Nome_Boletim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Nome_Boletim.setBounds(198, 19, 370, 28);
		boletim.add(Text_Nome_Boletim);

		JFormattedTextField Text_Curso_Boletim = new JFormattedTextField();
		Text_Curso_Boletim.setEditable(false);
		Text_Curso_Boletim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Curso_Boletim.setBounds(15, 55, 370, 28);
		boletim.add(Text_Curso_Boletim);

		JFormattedTextField Text_Periodo_Boletim = new JFormattedTextField();
		Text_Periodo_Boletim.setEditable(false);
		Text_Periodo_Boletim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Text_Periodo_Boletim.setBounds(400, 55, 170, 28);
		boletim.add(Text_Periodo_Boletim);

		DefaultTableModel modeloBoletim = new DefaultTableModel();
		modeloBoletim.addColumn("Disciplina");
		modeloBoletim.addColumn("Semestre");
		modeloBoletim.addColumn("Nota");
		modeloBoletim.addColumn("Faltas");

		JTable tabelaBoletim = new JTable(modeloBoletim);
		tabelaBoletim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabelaBoletim.setRowHeight(24);

		JScrollPane scrollBoletim = new JScrollPane(tabelaBoletim);
		scrollBoletim.setBounds(15, 95, 555, 155);
		boletim.add(scrollBoletim);

		Text_Rgm_Boletim.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e) {

				String rgm = Text_Rgm_Boletim.getText().trim();

				if (rgm.isEmpty()) {
					return;
				}

				BoletimDAO dao = new BoletimDAO();
				dao.carregarBoletim(rgm, Text_Nome_Boletim, Text_Curso_Boletim, Text_Periodo_Boletim, modeloBoletim);
			}
		});

		// AÇÃO DO MENU ALUNO > SALVAR
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("CLICOU NO SALVAR");

				String periodoSelecionado = "";

				if (rdbtn_Matutino.isSelected()) {
					periodoSelecionado = "Matutino";
				} else if (rdbtn_Vespertino.isSelected()) {
					periodoSelecionado = "Vespertino";
				} else if (rdbtn_Noturno.isSelected()) {
					periodoSelecionado = "Noturno";
				}

				if (Text_Rgm.getText().trim().isEmpty() || Text_Nome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha pelo menos RGM e Nome.");
					return;
				}

				if (periodoSelecionado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um período.");
					return;
				}

				Aluno aluno = new Aluno(Text_Rgm.getText().trim(), Text_Nome.getText().trim(),
						text_DataNasc.getText().trim(), text_CPF.getText().trim(), Text_Email.getText().trim(),
						Text_Endereco.getText().trim(), Text_Municipio.getText().trim(), Text_UF.getText().trim(),
						Text_Celular.getText().trim(), comboBox_Curso.getSelectedItem().toString(),
						comboBox_Campus.getSelectedItem().toString(), periodoSelecionado);

				AlunoDAO dao = new AlunoDAO();
				dao.salvar(aluno);
			}
		});

		MenuConsultaAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaExcluirAluno tela = new TelaConsultaExcluirAluno();
				tela.setVisible(true);
			}
		});

		MenuConsultaNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaExcluirNotas tela = new TelaConsultaExcluirNotas();
				tela.setVisible(true);
			}
		});

		AlterarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAlterarAluno tela = new TelaAlterarAluno();
				tela.setVisible(true);
			}
		});

		AlterarNotaFalta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAlterarNotasFalta tela = new TelaAlterarNotasFalta();
				tela.setVisible(true);
			}
		});
	}
}