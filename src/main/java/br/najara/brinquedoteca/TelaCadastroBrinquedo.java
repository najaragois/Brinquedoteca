package br.najara.brinquedoteca;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaCadastroBrinquedo extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField nome;
	JComboBox<String> estado;
	JComboBox<String> genero;
	JTextField idadeMinima;
	JCheckBox disponivel;
	JButton botao;
	JMenu menu;
	JMenuBar barraMenu;
	
	//Construtor da tela de cadastro de brinquedo:
	public TelaCadastroBrinquedo(){
		menu = new JMenu("Menu");
		JMenuItem menuItem = new JMenuItem("Consultar brinquedos");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("ConsultarBrinquedos");
		menu.add(menuItem);
		barraMenu = new JMenuBar();
		barraMenu.add(menu);
		setJMenuBar(barraMenu);
		nome = new JTextField(30);
		estado = new JComboBox<String>();
		estado.addItem("Inteiro");
		estado.addItem("Quebrado");
		genero = new JComboBox<String>();
		genero.addItem("Masculino");
		genero.addItem("Feminino");
		genero.addItem("Unisex");
		idadeMinima = new JTextField(10);
		disponivel = new JCheckBox("Disponível");
		
		//Container é o fundo cinza da tela:
		Container p = getContentPane();
		
		//Tabela gridlayout com 6 linhas e 2 colunas:
		p.setLayout(new GridLayout(6, 2));
		p.add(new JLabel("Nome:"));
		p.add(nome);
		p.add(new JLabel("Estado:"));
		p.add(estado);
		p.add(new JLabel("Gênero:"));
		p.add(genero);
		p.add(new JLabel("Idade Mínina de Uso:"));
		p.add(idadeMinima);
		p.add(new JLabel("Disponível:"));
		p.add(disponivel);
		botao = new JButton("Inserir");
		botao.addActionListener(this);
		botao.setActionCommand("Inserir");
		p.add(botao);
		setVisible(true);
		
		//pack define sozinho o tamanho da tela:
		pack();	
	}

	public static void main(String[] args) {
		TelaCadastroBrinquedo tela = new TelaCadastroBrinquedo();
		
	}
	//Ouvinte do botao inserir:
	public void actionPerformed(ActionEvent e) {
		try {
			
			//Carrega o drive do banco mysql:
			Class.forName("com.mysql.jdbc.Driver");
			
			//Conexao com o banco, usando a url:
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brinquedoteca?user=sbrinquedoteca&password=brinquedo");
			
			//Statement serve para: Select, Insert, Update e Delete:
			java.sql.Statement statement = connection.createStatement();
			
			statement.executeUpdate("Insert into brinquedo(nome, estado, genero,idade_crianca, disponivel) values ('" + nome.getText()+ "','" + estado.getSelectedItem()+ "','" + genero.getSelectedItem()+ "'," + idadeMinima.getText() + "," + disponivel.isSelected() + ");");
			
			JOptionPane.showMessageDialog(this, "Inserido com sucesso");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
