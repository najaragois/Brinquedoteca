package br.najara.brinquedoteca;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaConsultaBrinquedo extends JFrame implements ActionListener {
	
	JButton botaoAtualizar;
	JTable tabela;
	ModeloTabelaBrinquedo modelo;
	JMenu menu;
	JMenuBar barraMenu;
	
    public TelaConsultaBrinquedo() {
    	menu = new JMenu("Brinquedo");
		JMenuItem menuItem = new JMenuItem("Cadastrar brinquedo");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("CadastrarBrinquedo");
		menu.add(menuItem);
		barraMenu = new JMenuBar();
		JMenu menuAluguel = new JMenu("Aluguel");
		JMenu menuCrianca=  new JMenu("Criança");
		JMenu responsavel=new JMenu("Responsável");
		JMenu funcionario=new JMenu("Funcionário");
		barraMenu.add(menu);
		barraMenu.add(menuAluguel);
		barraMenu.add(menuCrianca);
		barraMenu.add(responsavel);
		barraMenu.add(funcionario);
		setJMenuBar(barraMenu);
    	botaoAtualizar=new JButton("Atualizar");
    	botaoAtualizar.addActionListener(this);
    	botaoAtualizar.setActionCommand("Atualizar");
    	
    	modelo=new ModeloTabelaBrinquedo();
    	tabela=new JTable(modelo);
    	Container container=getContentPane();
    	container.setLayout(new FlowLayout());
    	container.add(botaoAtualizar);
    	container.add(new JScrollPane(tabela));
    	setVisible(true);
    	pack();
    	
		
	}
    
    public static void main(String[] args) {
		TelaConsultaBrinquedo tela=new TelaConsultaBrinquedo();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CadastrarBrinquedo")){
			TelaCadastroBrinquedo tela=new TelaCadastroBrinquedo();
		}
		if (e.getActionCommand().equals("Atualizar")){
			modelo.atualizar();
			
			modelo.fireTableDataChanged();
			tabela.setVisible(false);
			tabela.setVisible(true);
			tabela.repaint();
		}
		
	}

}
