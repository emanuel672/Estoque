package Base;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField Txtnome;
    private JTextField Txtmodelo;
    private JTextField Txtobs;
    private JTextField Txtvalor;
    private JTextField Txtquantidade;
    public JTextField Nmlst = new JTextField(); // Inicializando Nmlst
	static ArrayList<String> data = new ArrayList<String>();

	/**
	 * Launch the application.
	 * @return 
	 */
	public String getnml(String Names) {
	    String numeroLista = ""; // Inicializa a variável numeroLista como uma string vazia
	    try (Connection con = ConectionFabric.getConection(); Statement stmt = con.createStatement();) {
	        // Consulta SQL para selecionar o CategoryCod com base no nome da categoria
	        String sql = "SELECT CategoryCod FROM categoria WHERE Names = ?";
	        
	        // Prepara a consulta SQL com um espaço reservado para o nome da categoria
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, Names); // Define o valor do espaço reservado como o nome da categoria
	        ResultSet rs = pstmt.executeQuery(); // Executa a consulta
	        
	        // Verifica se o resultado da consulta não está vazio
	        if (rs.next()) {
	            numeroLista = rs.getObject(1).toString(); // Atribui o valor do banco de dados a numeroLista
	        }
	    } catch (SQLException ex) {
	        System.out.println("Erro ao obter código da categoria: " + ex.getMessage());
	    }
	    return numeroLista; // Retorna o número da lista
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public  Cadastro() {
		ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
		setTitle("loja de informatica"); // Título da janela
		setIconImage(icon.getImage()); // Define o ícone da janela
	
		addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Lbtxt = new JLabel("Cadastro de Produtos Novos");
		Lbtxt.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Lbtxt.setBounds(27, 11, 204, 14);
		contentPane.add(Lbtxt);
		
		JLabel Lbcategoria = new JLabel("Categoria do Produto");
		Lbcategoria.setForeground(Color.WHITE);
		Lbcategoria.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		Lbcategoria.setBounds(26, 36, 128, 14);
		contentPane.add(Lbcategoria);
		
		JLabel Lbnome = new JLabel("Nome do Produto");
		Lbnome.setForeground(Color.WHITE);
		Lbnome.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Lbnome.setBounds(27, 141, 108, 14);
		contentPane.add(Lbnome);
		
		Txtnome = new JTextField();
		Txtnome.setBounds(20, 171, 196, 20);
		contentPane.add(Txtnome);
		Txtnome.setColumns(10);
		
		JLabel Lbmodelo = new JLabel("Modelo");
		Lbmodelo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Lbmodelo.setForeground(Color.WHITE);
		Lbmodelo.setBounds(27, 202, 108, 14);
		contentPane.add(Lbmodelo);
		
		Txtmodelo = new JTextField();
		Txtmodelo.setBounds(20, 227, 196, 20);
		contentPane.add(Txtmodelo);
		Txtmodelo.setColumns(10);
		
		JLabel Lbobs = new JLabel("Observação");
		Lbobs.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Lbobs.setForeground(Color.WHITE);
		Lbobs.setBounds(27, 269, 108, 14);
		contentPane.add(Lbobs);
		
		Txtobs = new JTextField();
		Txtobs.setBounds(20, 294, 196, 20);
		contentPane.add(Txtobs);
		Txtobs.setColumns(10);
		
		JLabel Lbvalor = new JLabel("Valor de Custo");
		Lbvalor.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		Lbvalor.setForeground(Color.WHITE);
		Lbvalor.setBounds(367, 36, 98, 14);
		contentPane.add(Lbvalor);
		
		Txtvalor = new JTextField();
		Txtvalor.setBounds(358, 61, 121, 20);
		contentPane.add(Txtvalor);
		Txtvalor.setColumns(10);
		
		JLabel Lbquantidade = new JLabel("Quantidade comprado");
		Lbquantidade.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		Lbquantidade.setForeground(Color.WHITE);
		Lbquantidade.setBounds(358, 141, 128, 14);
		contentPane.add(Lbquantidade);
		
		Txtquantidade = new JTextField();
		Txtquantidade.setBounds(358, 171, 121, 20);
		contentPane.add(Txtquantidade);
		Txtquantidade.setColumns(10);
		
		final JLabel Lbfiltro = new JLabel("Selecione*");
		Lbfiltro.setForeground(Color.WHITE);
		Lbfiltro.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Lbfiltro.setBackground(Color.WHITE);
		Lbfiltro.setBounds(27, 61, 156, 20);
		contentPane.add(Lbfiltro);
		
		JButton Btlista = new JButton("lista de categorias");
		Btlista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarTabela();
                Object[] dat = data.toArray(new String[data.size()]); 
                JList<Object> list = new JList<Object>(dat);

                list.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JList list = (JList)evt.getSource();
                        if(evt.getClickCount() >= 2) {
                            String selected  = (String) list.getSelectedValue();
                            if (selected != null) {
                                Lbfiltro.setText(selected);
                                getnml(selected);
                                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(list);
                                frame.dispose(); // Fecha a janela de filtro
                            } else {
                                Lbfiltro.setText("Nenhum item selecionado");
                            }
                        }
                    }
                });

                JFrame Listfiltro = new JFrame("Lista de Categorias");

                JScrollPane scrollPane = new JScrollPane(list);
                scrollPane.setPreferredSize(new Dimension(200, 100));
                Listfiltro.getContentPane().add(scrollPane, BorderLayout.CENTER);

                JButton selectButton = new JButton("Selecionar");
                selectButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Object selectedItem = list.getSelectedValue();
                        if (selectedItem != null) {
                            Lbfiltro.setText(selectedItem.toString());
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(list);
                            frame.dispose(); // Fecha a janela de filtro
                        } else {
                            Lbfiltro.setText("Nenhum item selecionado");
                        }
                    }
                });

                Listfiltro.getContentPane().add(selectButton, BorderLayout.SOUTH);
                Listfiltro.setSize(400, 300);
                Listfiltro.setLocationRelativeTo(null);
                Listfiltro.setVisible(true);
            }
        });

		
		Btlista.setBounds(37, 94, 142, 23);
		contentPane.add(Btlista);
		JButton Btcadastro = new JButton("cadastrar");
		Btcadastro.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String categoria = Lbfiltro.getText();
		        String numeroLista = getnml(categoria); // Obtem o número da lista com base no nome da categoria

		        String nome = Txtnome.getText();
		        String modelo = Txtmodelo.getText();
		        String observacao = Txtobs.getText();
		        String preco = Txtvalor.getText();
		        String quantidade = Txtquantidade.getText();

		        // Verifica se a string retornada não está vazia ou nula antes de tentar converter para um número inteiro
		        if (!numeroLista.isEmpty()) {
		            // Se não estiver vazia, cria o objeto Produtos e o cadastra
		            sqlCadastro cadastro = new sqlCadastro();
		            Produtos novoProduto = new Produtos(1, nome, modelo, observacao, Integer.parseInt(numeroLista), Float.parseFloat(preco), Integer.parseInt(quantidade));
		            cadastro.Adicionarproduto(novoProduto);
		            cadastro.Listaprodutos();
		            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "sucesso", JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		        } else {
		            // Se estiver vazia, exibe uma mensagem de erro
		            JOptionPane.showMessageDialog(null, "Número da lista da categoria selecionada não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		Btcadastro.setBounds(376, 266, 108, 23);
		contentPane.add(Btcadastro);
		
		 //Nmlst = new JTextField();
	     //Nmlst.setBounds(184, 61, 47, 17); // aparece a linha de codigo da Categoria
	     //contentPane.add(Nmlst);
	}

	private void carregarTabela() {
        try (Connection con = ConectionFabric.getConection(); Statement stmt = con.createStatement();) {
            String sql = "SELECT * from categoria";
             ResultSet rs = stmt.executeQuery(sql);
			 data.clear();
			 while (rs.next()) {
				data.add(rs.getObject(2).toString());
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produto" + ex.getMessage());
        }
    }	
	
}