package Base;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Novologin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldSenha;
    private JComboBox<Integer> comboBoxNivel;

    // Conexão com o banco de dados
    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Novologin frame = new Novologin();
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
    public Novologin() {
    	ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
    	setTitle("loja de informatica"); // Título da janela
    	setIconImage(icon.getImage()); // Define o ícone da janela
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 401, 285);
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel Lbtitulo = new JLabel("Novo Login");
        Lbtitulo.setForeground(Color.WHITE);
        Lbtitulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        Lbtitulo.setBounds(10, 11, 111, 24);
        contentPane.add(Lbtitulo);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNome.setBounds(10, 80, 46, 14);
        contentPane.add(lblNome);
        
        textFieldNome = new JTextField();
        textFieldNome.setBounds(66, 80, 194, 20);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);
        
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSenha.setBounds(10, 120, 46, 14);
        contentPane.add(lblSenha);
        
        textFieldSenha = new JTextField();
        textFieldSenha.setBounds(66, 120, 194, 20);
        contentPane.add(textFieldSenha);
        textFieldSenha.setColumns(10);
        
        JLabel lblNivelAcesso = new JLabel("Nível de Acesso:");
        lblNivelAcesso.setForeground(Color.WHITE);
        lblNivelAcesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNivelAcesso.setBounds(10, 160, 112, 14);
        contentPane.add(lblNivelAcesso);
        
        comboBoxNivel = new JComboBox<>();
        comboBoxNivel.addItem(1); // Valor padrão
        comboBoxNivel.addItem(2); // Adicione outros valores conforme necessário
        comboBoxNivel.addItem(3);
        comboBoxNivel.setBounds(130, 160, 60, 20);
        contentPane.add(comboBoxNivel);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalvar.setBounds(66, 200, 89, 23);
        contentPane.add(btnSalvar);

        // Conectar-se ao banco de dados ao inicializar a janela
        try {
            connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Adicionar funcionalidade ao botão "Salvar"
        btnSalvar.addActionListener(e -> {
            String nome = textFieldNome.getText();
            String senha = textFieldSenha.getText();
            int nivel_acessado = (int) comboBoxNivel.getSelectedItem();
            
            if (!nome.isEmpty() && !senha.isEmpty()) {
                try {
                    // Inserir novo login no banco de dados
                    String query = "INSERT INTO users (nome, senha, nivel_acessado) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, senha);
                    preparedStatement.setInt(3, nivel_acessado);
                    preparedStatement.executeUpdate();
                    
                    // Limpar campos após salvar
                    textFieldNome.setText("");
                    textFieldSenha.setText("");
                    
                    // Mostrar mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Novo login salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao salvar novo login: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para conectar-se ao banco de dados
    private void connectDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estoque"; // URL do seu banco de dados
        String user = "root";
        String password = "2004";
        connection = DriverManager.getConnection(url, user, password);
    }
}
