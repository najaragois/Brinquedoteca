package br.najara.brinquedoteca;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

public class TelaAluguel extends JFrame {
	JTextField nomeResponsavel;
	JDatePicker dataAluguel;
	JComboBox<String> brinquedo;
	
	public ArrayList<String> retornaNomeBriquedos(){
		ArrayList<String> listaNomeBrinquedos = new ArrayList<String>();
		try {
			// Carrega o drive do banco mysql:
			Class.forName("com.mysql.jdbc.Driver");

			// Conexao com o banco, usando a url:
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/brinquedoteca?user=sbrinquedoteca&password=brinquedo");

			// Statement serve para: Select, Insert, Update e Delete:
			java.sql.Statement statement = connection.createStatement();

			ResultSet resultadoConsulta=statement.executeQuery("select nome from brinquedo;");
			
			while (resultadoConsulta.next()){
				
				String nome=resultadoConsulta.getString(1);
				listaNomeBrinquedos.add(nome);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaNomeBrinquedos;
	}
	public TelaAluguel(){
		nomeResponsavel = new JTextField(50);
		brinquedo = new JComboBox<String>();
		ArrayList<String> lista = retornaNomeBriquedos();
		for (String string : lista) {
			brinquedo.addItem(string);
			
		}
		dataAluguel = new JDatePicker() {
			
			public void removeActionListener(ActionListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public DateModel<?> getModel() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void addActionListener(ActionListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setShowYearButtons(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setDoubleClickAction(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isShowYearButtons() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isDoubleClickAction() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void setTextEditable(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setButtonFocusable(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isTextEditable() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean getButtonFocusable() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		Container container = getContentPane();
	    container.setLayout(new FlowLayout());
	    container.add(new JLabel("Nome do responsável:"));
	    container.add(nomeResponsavel);
	    // container.add(new JLabel("Data do aluguel:"));
	    // container.add(dataAluguel);
	    container.add(new JLabel("Brinquedos"));
	    container.add(brinquedo);
	    setVisible(true);
	    pack();
	}
	public static void main(String[] args) {
		TelaAluguel tela = new TelaAluguel();
		
	}
	

}
