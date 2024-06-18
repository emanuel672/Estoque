package Base;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField Txtnome;
    private JTextField txtsenha;
    private Connection connection;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
        setTitle("loja de informatica");
        // Load custom icon (replace "icone.png" with your icon's path)
        ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
        // Set the icon for the JFrame
        setIconImage(icon.getImage());
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/estoque";
        String user = "root";
        String password = "2004";
        
        // Connect to the database
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit the application if unable to connect to the database
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 144, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton Btlogin = new JButton("Login");
        Btlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Checklogin(Txtnome.getText(), txtsenha.getText())) {
                    JOptionPane.showMessageDialog(null, "Bem vindo ");
                    Tabela_produtos tabela = new Tabela_produtos();
                            tabela.setVisible(true);
                            dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Nome ou senha invalidos");
                }
            }
        });
        Btlogin.setBounds(178, 197, 89, 23);
        contentPane.add(Btlogin);
        
        JLabel lblNewLabel = new JLabel("Loja de Informatica");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel.setBounds(10, 11, 146, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Area de Acesso");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(37, 36, 89, 14);
        contentPane.add(lblNewLabel_1);
        
        Txtnome = new JTextField();
        Txtnome.setBounds(147, 88, 146, 20);
        contentPane.add(Txtnome);
        Txtnome.setColumns(10);
        
        txtsenha = new JTextField();
        txtsenha.setBounds(147, 142, 146, 20);
        contentPane.add(txtsenha);
        txtsenha.setColumns(10);
        txtsenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Btlogin.doClick(); // Aciona o evento do botão de login quando Enter é pressionado no campo de senha
            }
        });
        
        JLabel Lbnome = new JLabel("Nome");
        Lbnome.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        Lbnome.setBounds(63, 94, 46, 14);
        contentPane.add(Lbnome);
        
        JLabel Lbsenha = new JLabel("Senha");
        Lbsenha.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        Lbsenha.setBounds(63, 148, 46, 14);
        contentPane.add(Lbsenha);     
    }
    
    public boolean Checklogin(String login, String senha) {
        String query = "SELECT * FROM users WHERE nome = ? AND senha = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // If ResultSet has at least one record, return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // In case of error, return false
        }
    }    
}
