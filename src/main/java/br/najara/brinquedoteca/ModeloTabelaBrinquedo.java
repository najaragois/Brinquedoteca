package br.najara.brinquedoteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ModeloTabelaBrinquedo extends AbstractTableModel {
	
	//Coleção de brinquedos
	ArrayList<Brinquedo> brinquedos=new ArrayList<Brinquedo>();
	
	
	public void atualizar(){
		
		brinquedos=new  ArrayList<Brinquedo>();
		

		try {
			// Carrega o drive do banco mysql:
			Class.forName("com.mysql.jdbc.Driver");

			// Conexao com o banco, usando a url:
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/brinquedoteca?user=sbrinquedoteca&password=brinquedo");

			// Statement serve para: Select, Insert, Update e Delete:
			java.sql.Statement statement = connection.createStatement();

			ResultSet resultadoConsulta=statement.executeQuery("select nome,estado,genero,disponivel from brinquedo;");
			
			while (resultadoConsulta.next()){
				
				String nome=resultadoConsulta.getString(1);
				String estado=resultadoConsulta.getString(2);
				String genero=resultadoConsulta.getString(3);
				boolean disponivel=resultadoConsulta.getBoolean(4);
				
				Brinquedo brinquedo=new Brinquedo();
				brinquedo.setNome(nome);
				brinquedo.setEstado(estado);
				brinquedo.setGenero(genero);
				brinquedo.setDisponivel(disponivel);
				
				brinquedos.add(brinquedo);
				
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.fireTableDataChanged();
		
	}
	
	//Construtor
	public ModeloTabelaBrinquedo(){
		
		atualizar();

		
		
	}

	//Numero de linhas da tabela
	public int getRowCount() {
		
		//Retorna tamanho da coleção de brinquedos
		return brinquedos.size();
	}

	//Numero de colunas da tabela
	public int getColumnCount() {
		
		return 4;
	}

	public String getColumnName(int columnIndex) {
		
		if (columnIndex==0){
			return "Nome";
		}
		if(columnIndex==1){
			return "Estado";
		}
		if (columnIndex==2){
			return "Gênero";
		}
		if (columnIndex==3){
			return "Disponível";
		}
		
		return "nulo";
	}

	public Class<String> getColumnClass(int columnIndex) {
	
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return false;
	}

	public Object getValueAt(int numeroLinha, int numeroColuna) {
		
		
		Brinquedo brinquedo=brinquedos.get(numeroLinha);
		
		
		if (numeroColuna==0){
			return brinquedo.getNome();
		}
		if (numeroColuna==1){
			return brinquedo.getEstado();
		}
		if (numeroColuna==2){
			return brinquedo.getGenero();
		}
		if(numeroColuna==3){
			if (brinquedo.isDisponivel()){
				return "Disponível";
			}
			else{
				return "Indisponível";
			}
		
		}
		
		return "nulo";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		

	}

	public void addTableModelListener(TableModelListener l) {
		

	}

	public void removeTableModelListener(TableModelListener l) {
		

	}

}
