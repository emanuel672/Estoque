package Base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Estoque extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable Tblist;
    private DefaultTableModel Tblista;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Estoque frame = new Estoque();
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
    public Estoque() {
    	ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
    	setTitle("loja de informatica"); // Título da janela
    	setIconImage(icon.getImage()); // Define o ícone da janela
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 664, 348);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        contentPane.add(buttonPanel, BorderLayout.NORTH); // botao superior

        JButton Btatualizar = new JButton("Atualizar");
        buttonPanel.add(Btatualizar, BorderLayout.WEST); // botao esquerdo

        JButton Bteditar = new JButton("Editar");
        buttonPanel.add(Bteditar, BorderLayout.EAST); // botao direito
        Bteditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Tblist.getSelectedRow();
                if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                    // Recupera os dados da linha selecionada
                    Object[] rowData = new Object[Tblista.getColumnCount()];
                    for (int i = 0; i < Tblista.getColumnCount(); i++) {
                        rowData[i] = Tblista.getValueAt(selectedRow, i);
                    }
                    
                    System.out.println(Arrays.toString(rowData));
                    
                    // Abre a janela de edição com os dados do produto selecionado
                    Editarproduto editarForm = new Editarproduto(rowData);
                    editarForm.setVisible(true);
                    editarForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    JOptionPane.showMessageDialog(null, "Produto selecionado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Se nenhuma linha estiver selecionada, exibe uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Nenhum produto selecionado para edição.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Tblista = new DefaultTableModel();
        Tblist = new JTable(Tblista);
        Tblist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Define o modo de seleção para apenas uma linha por vez
        JScrollPane scrollPane = new JScrollPane(Tblist);
        contentPane.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane ao centro

        Tblist.setRowHeight(30); // Altere este valor conforme necessário

        // Renderizador de células personalizado para alternar as cores das linhas
        Tblist.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE); // Cor da linha par
                    } else {
                        c.setBackground(Color.LIGHT_GRAY); // Cor da linha ímpar
                    }
                }
                return c;
            }
        });

        Btatualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
            }
        });

        // Carrega os dados da tabela ao iniciar o frame
        carregarTabela();
        
        JButton Btdeletar = new JButton("Excluir");
        buttonPanel.add(Btdeletar, BorderLayout.CENTER); // botao central
        Btdeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Tblist.getSelectedRow();
                if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                    int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o produto selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Remove o item da tabela e do banco de dados
                        try (Connection con = ConectionFabric.getConection(); Statement stmt = con.createStatement();) {
                            Object idToDelete = Tblista.getValueAt(selectedRow, 0); // Supondo que a primeira coluna seja o ID do produto
                            String sql = "DELETE FROM produtos WHERE id = " + idToDelete;
                            stmt.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            atualizarTabela(); // Atualiza a tabela após a exclusão
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // Se nenhuma linha estiver selecionada, exibe uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Nenhum produto selecionado para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel deleteButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Cria um novo painel com layout FlowLayout alinhado à direita
        deleteButtonPanel.add(Btdeletar); // Adiciona o botão "Excluir" ao novo painel

        buttonPanel.add(deleteButtonPanel, BorderLayout.CENTER);

    }

    // Método para carregar os dados da tabela a partir do banco de dados
    private void carregarTabela() {
        try (Connection con = ConectionFabric.getConection(); Statement stmt = con.createStatement();) {
            String sql = "select ID, produtos.nome, modelo, observacao , categoria.Names as Categoria, preco as \"Preço\", quantidade from produtos inner join categoria on categoria.categoryCod = produtos.categoria";
            ResultSet rs = stmt.executeQuery(sql);

            // Limpa o modelo da tabela
            Tblista.setRowCount(0);
            Tblista.setColumnCount(0);

            // Adiciona as colunas ao modelo da tabela
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                Tblista.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Preenche as linhas da tabela com os dados do ResultSet
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                Tblista.addRow(row);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar produto" + ex.getMessage());
        }
    }

    // Método para atualizar a tabela
    private void atualizarTabela() {
        carregarTabela(); // Recarrega os dados da tabela
    }
}
