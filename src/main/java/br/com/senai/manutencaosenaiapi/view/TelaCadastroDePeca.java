package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.TipoPeca;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TipoPecaService;
import br.com.senai.manutencaosenaiapi.view.table.TipoPecaTableModel;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@Component
public class TelaCadastroDePeca extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtDescricao;
	private JLabel lblEspecificacoes;
	private JTextArea jtaEspecificacoes;
	
	@Autowired
	private PecaService service;
	@Autowired
	private TipoPecaService tipoPecaService;
	
	@Autowired
	@Lazy
	private TelaConsultaDePeca telaDeConsulta;
	
	private JTextField edtQtde;
	private JScrollPane scrollPane;
	private JLabel lblConsultaTP;
	private JTextField edtFiltro;
	private JButton btnPesquisar;
	private JTable table;
	private JLabel lblIdTipoPeca;
	private JTextField edtIdTipoPeca;

	/**
	 * Create the frame.
	 */
	public TelaCadastroDePeca() {
		setTitle("Cadastro de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblId = new JLabel("ID");
		
		edtId = new JTextField();
		edtId.setEnabled(false);
		edtId.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		
		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (edtId.getText() != null && edtId.getText().length() > 0) {
						Peca pecaSalva = new Peca();
						pecaSalva.setDescricao(edtDescricao.getText());
						pecaSalva.setQtdeEmEstoque(Integer
								.parseInt(edtQtde.getText()));
						pecaSalva.setEspecificacoes(jtaEspecificacoes.getText());
						pecaSalva.setId(Integer.parseInt(edtId.getText()));
						if (edtIdTipoPeca.getText().toString() != null || edtIdTipoPeca.getText().toString() != "") {
							pecaSalva.setIdTipoPeca(Integer.parseInt(edtIdTipoPeca.getText()));
						}
						service.alterar(pecaSalva);
						JOptionPane.showMessageDialog(
								contentPane, "Peça alterada com sucesso");
					}else {
						Peca novaPeca = new Peca();
						novaPeca.setDescricao(edtDescricao.getText());
						novaPeca.setQtdeEmEstoque(Integer
								.parseInt(edtQtde.getText()));
						novaPeca.setEspecificacoes(jtaEspecificacoes.getText());
						if (edtIdTipoPeca.getText().toString() == null || edtIdTipoPeca.getText().toString() == "") {
							novaPeca.setIdTipoPeca(Integer.parseInt(edtIdTipoPeca.getText()));
						}
						Peca pecaSalva = service.inserir(novaPeca);
						edtId.setText(pecaSalva.getId().toString());
						JOptionPane.showMessageDialog(
								contentPane, "Peça inserida com sucesso");
					}					
					
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(
							contentPane, ex.getMessage());
				}
				
			}
		});
		
		JLabel lblQtde = new JLabel("Qtde");
		
		edtQtde = new JTextField();
		edtQtde.setColumns(10);
		
		lblEspecificacoes = new JLabel("Especificações");
		
		jtaEspecificacoes = new JTextArea();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeConsulta.setVisible(true);
				setVisible(false);
			}
		});
		
		scrollPane = new JScrollPane((java.awt.Component) null);
		
		lblConsultaTP = new JLabel("Consulta de Tipos de Peças");
		
		edtFiltro = new JTextField();
		edtFiltro.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TipoPeca> pecas = tipoPecaService.listarPor(edtFiltro.getText());
				TipoPecaTableModel model = new TipoPecaTableModel(pecas);
				table.setModel(model);
				TableColumnModel cm = table.getColumnModel();
				cm.getColumn(0).setPreferredWidth(50);
				cm.getColumn(1).setPreferredWidth(500);
				table.updateUI();
			}
		});
		
		lblIdTipoPeca = new JLabel("ID Tipo de Peça");
		
		edtIdTipoPeca = new JTextField();
		edtIdTipoPeca.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescricao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(edtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQtde))
					.addGap(14))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(edtFiltro, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(136))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblConsultaTP)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(jtaEspecificacoes, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblIdTipoPeca)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblEspecificacoes)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(edtIdTipoPeca, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSalvar))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblDescricao)
						.addComponent(lblQtde))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIdTipoPeca)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(edtIdTipoPeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEspecificacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtaEspecificacoes, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblConsultaTP)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar)))
		);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void colocarEmEdicao(Peca pecaSalva) {
		edtId.setText(pecaSalva.getId().toString());
		edtDescricao.setText(pecaSalva.getDescricao());
		jtaEspecificacoes.setText(pecaSalva.getEspecificacoes());
		edtQtde.setText(pecaSalva.getQtdeEmEstoque().toString());
		if (pecaSalva.getIdTipoPeca() != null) {
			edtIdTipoPeca.setText(pecaSalva.getIdTipoPeca().toString());
		}
	}
}
