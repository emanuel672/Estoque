package Base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class sqlCadastro {
	
	public void Adicionarproduto(Produtos produto) {
		String sql="INSERT INTO produtos( Nome, Modelo, observacao ,Categoria, Preco, Quantidade) VALUES(?,?,?,?,?,?)";
		try(Connection con = ConectionFabric.getConection();PreparedStatement stmt = con.prepareStatement(sql)){
			//stmt.setInt(1, produto.getID());
			stmt.setString(1, produto.getNome());
	        stmt.setString(2, produto.getmodelo());
	        stmt.setString(3, produto.getCor());
            stmt.setInt(4, produto.getCategoria());
            stmt.setFloat(5, produto.getpreco());
            stmt.setInt(6, produto.getquantidade());
            
            stmt.executeUpdate();
            System.out.println("\n Produto adcionada com sucesso");
		}catch(SQLException e) {
			System.out.println("erro ao adcionar a produto:"+e.getMessage());
		}
	}
	
	public void Listaprodutos() {
		String sql="select * from produtos ";
		try(Connection con= ConectionFabric.getConection(); Statement stmt= con.createStatement(); ResultSet rs= stmt.executeQuery(sql)){
			while(rs.next()) {
				Produtos produto= new Produtos(rs.getInt("id"), rs.getString("Nome"), rs.getString("modelo"), rs.getString("observacao"), rs.getInt("Categoria"), rs.getFloat("preco"), rs.getInt("quantidade") );
				System.out.println(produto);
			}
		}catch(SQLException e) {
			System.out.println("Erro ao listar produto"+e.getMessage());
		}
	}
	
	public void Atualizarproduto(Produtos produto) {
	    String sql = "UPDATE produtos SET nome = ?, modelo = ?, observacao = ?, categoria = ?, preco = ?, quantidade = ? WHERE id = ?";
	    try (Connection con = ConectionFabric.getConection(); PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, produto.getNome());
	        stmt.setString(2, produto.getmodelo());
	        stmt.setString(3, produto.getCor());
	        stmt.setInt(4, produto.getCategoria());
	        stmt.setFloat(5, produto.getpreco());
	        stmt.setInt(6, produto.getquantidade());
	        stmt.setInt(7, produto.getID());
	        stmt.executeUpdate();
	        System.out.println("Produto atualizado com sucesso");
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar produto: " + e.getMessage());
	    }
	}

	
	public void Deletarproduto(int id) {
		String sql = "DELETE FROM produtos WHERE id=?";
		try(Connection con= ConectionFabric.getConection(); PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Produto deletada com suscesso");
		}catch(SQLException e){
			System.out.println("erro ao deletar: "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		sqlCadastro cadastro = new sqlCadastro();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("1 adcionar produto");
			System.out.println("2 listar produtos");
			System.out.println("3 atualizar produtos");
			System.out.println("4 deletar produto");
			System.out.println("5 sair");
			System.out.println("Escolar uma opção\n");
			int opcao= scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			case 1:
				System.out.println("Nome: ");
				String Nome= scanner.nextLine();
				System.out.println("Categoria");
				String Categoria= scanner.nextLine();
				System.out.println("Preço por Unidade");
				String Preco= scanner.nextLine();
				System.out.println("No estoque: ");
				String Quantidade= scanner.nextLine();
				System.out.println("No Modelo: ");
				String Modelo= scanner.nextLine();
				System.out.println("obeservação: ");
				String observacao= scanner.nextLine();
				Produtos Novaproduto= new Produtos(1, Nome, Modelo, observacao, Integer.parseInt(Categoria), Float.parseFloat(Preco), Integer.parseInt(Quantidade));
				cadastro.Adicionarproduto(Novaproduto);
				break;
			case 2:
				cadastro.Listaprodutos();
				break;
			case 3:
				System.out.println("ID da produto a ser atualizada: ");
				int Idatualizar= scanner.nextInt();
				scanner.nextLine();
				System.out.println("Nome: ");
				Nome= scanner.nextLine();
				System.out.println("Categoria");				
				Categoria= scanner.nextLine();
				System.out.println("Preço por Unidade");
				Preco= scanner.nextLine();
				System.out.println("No estoque: ");
				Quantidade= scanner.nextLine();
				System.out.println("por modelo:");
				Modelo= scanner.nextLine();
				System.out.println("Cor ");
				observacao= scanner.nextLine();
				Produtos Produtoatualizada= new Produtos(Idatualizar, Nome, Modelo, observacao, Integer.parseInt(Categoria), Integer.parseInt(Preco), Integer.parseInt(Quantidade));
				cadastro.Atualizarproduto(Produtoatualizada);
				break;
			case 4:
				System.out.println("ID da produto a ser deletada: ");
				int Iddeletar= scanner.nextInt();
				cadastro.Deletarproduto(Iddeletar);
				break;
			case 5:
				scanner.close();
				System.exit(0);
			default:
				System.out.println("opcao invalida! ");
			}
		}
	} 
}