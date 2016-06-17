package br.najara.brinquedoteca;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaLogin extends JFrame implements ActionListener {
	ImageIcon logo;
	JTextField login;
	JPasswordField senha;
	JButton botao;
	
	//Construtor
	public TelaLogin(){
		 java.net.URL imgURL = getClass().getResource("logo.png");
		logo = new ImageIcon(imgURL);
		login = new JTextField(10);		
		senha = new JPasswordField(10);		
		botao = new JButton("Entrar");
		
		botao.addActionListener(this);
		Container p = getContentPane();
		p.setLayout(new BorderLayout());
		p.add(BorderLayout.NORTH, new JLabel(logo));
		JPanel painelCentro = new JPanel();
		painelCentro.setLayout(new GridLayout(3,2));
		
		
		painelCentro.add(new JLabel("Login:  ",SwingConstants.RIGHT));
		painelCentro.add(login);
		painelCentro.add(new JLabel("Senha:  ",SwingConstants.RIGHT));
		painelCentro.add(senha);
		p.add(BorderLayout.CENTER,painelCentro);
		JPanel painelSul=new JPanel();
		painelSul.setLayout(new FlowLayout());
		painelSul.add(botao);
		p.add(BorderLayout.SOUTH,painelSul);
		setVisible(true);
		setSize(400, 400);
		
	}
	public static void main(String[] args) {
		
		//Instanciando a tela de login:
		TelaLogin telaLogin = new TelaLogin();
		
	}
		//Ouvinte do botao Entrar:
	public void actionPerformed(ActionEvent e) {
		
		try{
			
		//Carrega o drive do banco mysql:
		Class.forName("com.mysql.jdbc.Driver");
		
		//Conexao com o banco, usando a url:
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brinquedoteca?user=sbrinquedoteca&password=brinquedo");
		
		//Statement serve para: Select, Insert, Update e Delete:
		java.sql.Statement statement = connection.createStatement();
		
		String sql = "select * from funcionario where login='"+ login.getText()+"' and senha = '"+ new String(senha.getPassword())+"'";
		
		//Executa sql de consulta:
		ResultSet rs=statement.executeQuery(sql);
		
		if (rs.next()){
			TelaConsultaBrinquedo tela=new TelaConsultaBrinquedo();
		}
		else{
			JOptionPane.showMessageDialog(this, "Login ou senha inválido");
		}
		
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}

}
