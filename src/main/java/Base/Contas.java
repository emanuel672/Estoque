package Base;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Contas extends JFrame {

    private JPanel contentPane;
    private JTable Tblista;
    private JScrollPane scrollPane;

    // Conexão com o banco de dados
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Contas frame = new Contas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Contas() {
    	ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
    	setTitle("loja de informatica"); // Título da janela
    	setIconImage(icon.getImage()); // Define o ícone da janela
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 654, 451);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(32, 178, 170));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 61, 618, 314);
        contentPane.add(scrollPane);

        Tblista = new JTable();
        scrollPane.setViewportView(Tblista);
        
        JButton Btnovo = new JButton("Novo Login");
        Btnovo.setFont(new Font("Arial Black", Font.PLAIN, 12));
        Btnovo.setBounds(508, 11, 120, 27);
        contentPane.add(Btnovo);
        Btnovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Novologin novo = new Novologin();
                novo.setVisible(true);
                novo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        
        JLabel Lbtitulo = new JLabel("Contas cadastradas e suas respectivas senhas");
        Lbtitulo.setForeground(new Color(124, 252, 0));
        Lbtitulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        Lbtitulo.setBounds(10, 15, 374, 19);
        contentPane.add(Lbtitulo);
        
        JButton Btexcluir = new JButton("Excluir");
        Btexcluir.setBounds(539, 386, 89, 23);
        contentPane.add(Btexcluir);
        
        JButton Btatualizar = new JButton("Atualizar");
        Btatualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Limpa o modelo atual da tabela
                    DefaultTableModel model = (DefaultTableModel) Tblista.getModel();
                    model.setRowCount(0); // Remove todas as linhas
                    
                    // Preenche novamente a tabela com os dados atualizados do banco de dados
                    fillTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Btatualizar.setBounds(414, 30, 89, 23);
        contentPane.add(Btatualizar);
        
        
        Btexcluir.addActionListener(e -> {
            int selectedRow = Tblista.getSelectedRow();
            if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o usuário selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        // Obtém o ID do usuário selecionado
                        int userId = (int) Tblista.getValueAt(selectedRow, 0); // Supondo que o ID do usuário está na primeira coluna
                        // Deleta o usuário do banco de dados
                        String deleteQuery = "DELETE FROM users WHERE id = ?";
                        preparedStatement = connection.prepareStatement(deleteQuery);
                        preparedStatement.setInt(1, userId);
                        preparedStatement.executeUpdate();
                        // Remove a linha da tabela
                        DefaultTableModel model = (DefaultTableModel) Tblista.getModel();
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // Se nenhuma linha estiver selecionada, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Nenhum usuário selecionado para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Conectar-se ao banco de dados e preencher a tabela
        try {
            connectDatabase();
            fillTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para conectar-se ao banco de dados
    private void connectDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estoque"; // URL do seu banco de dados
        String user = "root";
        String password = "2004";
        connection = DriverManager.getConnection(url, user, password);
    }

    // Método para preencher a tabela com os dados do banco de dados
    private void fillTable() throws SQLException {
        String query = "SELECT * FROM users"; // SQL para selecionar todos os usuários
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        // Cria um modelo de tabela
        DefaultTableModel model = new DefaultTableModel();
        
        // Adiciona as colunas ao modelo de tabela
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            model.addColumn(resultSet.getMetaData().getColumnName(i));
        }
        
        // Preenche as linhas da tabela com os dados do ResultSet
        while (resultSet.next()) {
            Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
            for (int i = 0; i < row.length; i++) {
                row[i] = resultSet.getObject(i + 1);
            }
            model.addRow(row);
        }
        
        // Define o modelo de tabela para a JTable
        Tblista.setModel(model);
    }
}
