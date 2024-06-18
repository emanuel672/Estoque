package Base;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;

public class Editarproduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField Txtid;
	private JLabel Lbnome;
	public JTextField Txtnome;
	private JLabel Lbmodelo;
	public JTextField Txtmodelo;
	private JLabel Lbcategoria;
	public JTextField Txtcategoria;
	private JLabel Lbquantidade;
	public JTextField Txtquantidade;
	private JLabel Lbvalor;
	public JTextField Txtvalor;
	private JLabel Lbobs;
	public JTextField Txtobs;

		public void Editarproduto() {
			ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
			setTitle("loja de informatica"); // Título da janela
			setIconImage(icon.getImage()); // Define o ícone da janela
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 508, 383);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.activeCaptionBorder);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel Lbtitulo = new JLabel("Edição dos Produtos");
			Lbtitulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			Lbtitulo.setBounds(10, 11, 173, 24);
			contentPane.add(Lbtitulo);
			
			JLabel Lbid = new JLabel("ID");
			Lbid.setForeground(SystemColor.inactiveCaptionBorder);
			Lbid.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbid.setBounds(10, 50, 64, 14);
			contentPane.add(Lbid);
			
			Txtid = new JTextField();
			Txtid.setEditable(false);
			Txtid.setBounds(10, 80, 86, 20);
			contentPane.add(Txtid);
			Txtid.setColumns(10);
			
			Lbnome = new JLabel("Nome");
			Lbnome.setForeground(SystemColor.inactiveCaptionBorder);
			Lbnome.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbnome.setBounds(187, 50, 64, 14);
			contentPane.add(Lbnome);
			
			Txtnome = new JTextField();
			Txtnome.setColumns(10);
			Txtnome.setBounds(175, 80, 173, 20);
			contentPane.add(Txtnome);
			
			Lbmodelo = new JLabel("Modelo");
			Lbmodelo.setForeground(SystemColor.inactiveCaptionBorder);
			Lbmodelo.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbmodelo.setBounds(10, 120, 98, 14);
			contentPane.add(Lbmodelo);
			
			Txtmodelo = new JTextField();
			Txtmodelo.setColumns(10);
			Txtmodelo.setBounds(10, 150, 173, 20);
			contentPane.add(Txtmodelo);
			
			Lbcategoria = new JLabel("Categoria");
			Lbcategoria.setForeground(SystemColor.inactiveCaptionBorder);
			Lbcategoria.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbcategoria.setBounds(250, 120, 98, 14);
			contentPane.add(Lbcategoria);
			
			Txtcategoria = new JTextField();
			Txtcategoria.setEditable(false);
			Txtcategoria.setColumns(10);
			Txtcategoria.setBounds(250, 150, 86, 20);
			contentPane.add(Txtcategoria);
			
			Lbquantidade = new JLabel("Quantidade");
			Lbquantidade.setForeground(SystemColor.inactiveCaptionBorder);
			Lbquantidade.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbquantidade.setBounds(10, 190, 98, 14);
			contentPane.add(Lbquantidade);
			
			Txtquantidade = new JTextField();
			Txtquantidade.setColumns(10);
			Txtquantidade.setBounds(10, 220, 173, 20);
			contentPane.add(Txtquantidade);
			
			

			
			Lbvalor = new JLabel("Valor");
			Lbvalor.setForeground(SystemColor.inactiveCaptionBorder);
			Lbvalor.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbvalor.setBounds(250, 192, 98, 14);
			contentPane.add(Lbvalor);
			
			Txtvalor = new JTextField();
			Txtvalor.setColumns(10);
			Txtvalor.setBounds(250, 220, 173, 20);
			contentPane.add(Txtvalor);
			
			Lbobs = new JLabel("Observação");
			Lbobs.setForeground(SystemColor.inactiveCaptionBorder);
			Lbobs.setFont(new Font("Arial Black", Font.PLAIN, 15));
			Lbobs.setBounds(10, 257, 98, 14);
			contentPane.add(Lbobs);
			
			Txtobs = new JTextField();
			Txtobs.setColumns(10);
			Txtobs.setBounds(10, 285, 173, 48);
			contentPane.add(Txtobs);
			
			

			System.out.print("ok 1 ");
		}

		public Editarproduto(Object[] produtoData) {

			Editarproduto();
			initComponents(produtoData);
			
			}

	    // Método para inicializar os componentes da janela de edição com os dados do produto
	    private void initComponents(Object[] produtoData) {
	      
	    	Txtid.setText(String.valueOf(produtoData[0]));
	       Txtnome.setText(produtoData[1].toString());
	       Txtmodelo.setText(produtoData[2].toString());
	       Txtobs.setText(produtoData[3].toString());
	       Txtcategoria.setText(String.valueOf(produtoData[4]));
	       Txtvalor.setText(String.valueOf(produtoData[5]));
	       Txtquantidade.setText(String.valueOf(produtoData[6]));
	       
	       JButton Btsalvar = new JButton("Salvar");
			Btsalvar.setBounds(271, 285, 132, 36);
			contentPane.add(Btsalvar);
			Btsalvar.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        
			        sqlCadastro cadastro = new sqlCadastro();
			        String nome = Txtnome.getText();
			        String modelo = Txtmodelo.getText();
			        String Obeservacao = Txtobs.getText();
			        String categoria = Txtcategoria.getText();
			        String preco = Txtvalor.getText();
			        String quantidade = Txtquantidade.getText();
			        String id = Txtid.getText();
			        Produtos Atualizarproduto= new Produtos(Integer.parseInt(id), nome, modelo, Obeservacao, Integer.parseInt(categoria), Float.parseFloat(preco), Integer.parseInt(quantidade));
			        
			        cadastro.Atualizarproduto(Atualizarproduto);
			        cadastro.Listaprodutos();
			        
			        dispose();
			    }
			});

	    }

	    
	/**
	 * Launch the application.
	 */

// RESOLVIDO :like:

	/**
	 * Create the frame.
	 */

}
