package Base;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tabela_produtos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tabela_produtos frame = new Tabela_produtos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Tabela_produtos() {
    	ImageIcon icon = new ImageIcon("C:\\Users\\davie\\OneDrive\\Documentos\\java\\Informatica\\loja_info.png");
    	setTitle("loja de informatica"); // Título da janela
    	setIconImage(icon.getImage()); // Define o ícone da janela
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 697, 385);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setBounds(0, 0, 681, 36);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel Lbtitulo = new JLabel("Produtos");
        Lbtitulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        Lbtitulo.setBounds(10, 11, 79, 20);
        panel.add(Lbtitulo);

        JButton Btcadastro = new JButton("Cadastro");
        Btcadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cadastro cadastro = new Cadastro();
                cadastro.setVisible(true);
                cadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        Btcadastro.setBounds(384, 8, 89, 23);
        panel.add(Btcadastro);

        JButton Btestoque = new JButton("Estoque");
        Btestoque.setBounds(483, 8, 89, 23);
        panel.add(Btestoque);
        Btestoque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Estoque estoque = new Estoque();
                estoque.setVisible(true);
                estoque.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        JButton Btcontas = new JButton("Contas");
        Btcontas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Contas contas = new Contas();
                contas.setVisible(true);
                contas.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        Btcontas.setBounds(582, 8, 89, 23);
        panel.add(Btcontas);

        JButton Btsair = new JButton("Finalizar");
        Btsair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        Btsair.setBounds(592, 323, 89, 23);
        contentPane.add(Btsair);
        
        // Adicionando o ComponentListener para monitorar as mudanças no tamanho da janela
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Obtendo as dimensões da janela
                int width = getContentPane().getWidth();
                int height = getContentPane().getHeight();

                // Redimensionando e reposicionando o painel superior
                panel.setBounds(0, 0, width, 36);

                // Redimensionando e reposicionando o título "Produtos"
                Lbtitulo.setBounds(10, 11, 100, 20);

                // Redimensionando e reposicionando os botões no painel superior
                int buttonWidth = 100;
                int buttonHeight = 23;
                Btsair.setBounds(width - buttonWidth, height - buttonHeight - 10, buttonWidth, buttonHeight);
                Btcontas.setBounds(width - 2 * buttonWidth - 10, 8, buttonWidth, buttonHeight);
                Btestoque.setBounds(width - 3 * buttonWidth - 20, 8, buttonWidth, buttonHeight);
                Btcadastro.setBounds(width - 4 * buttonWidth - 30, 8, buttonWidth, buttonHeight);
            }
        });



    }

}
