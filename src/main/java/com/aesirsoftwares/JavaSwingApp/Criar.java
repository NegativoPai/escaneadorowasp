import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Criar extends javax.swing.JFrame {

    public Criar() {
        initComponents();
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        textSenha = new javax.swing.JPasswordField();
        textConfirmarSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Criar Conta");
    
        // Configurar os botões
        jButton1.setText("Criar");
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
    
        // Labels
        jLabel1.setText("Usuario:");
        jLabel2.setText("Senha:");
        jLabel3.setText("Confirmar Senha:");
        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Criar Conta");
    
        // Layout configurado
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar))
                            .addComponent(textConfirmarSenha)
                            .addComponent(textSenha)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnCancelar))
                .addGap(82, 82, 82))
        );
    
        pack();
    }
    

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {
        // Fechar a janela de criação
        System.exit(0);
    }

    private void textSenhaActionPerformed(java.awt.event.ActionEvent evt) {
        // Se necessário, pode adicionar mais funcionalidades aqui
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = jTextField1.getText();
        String senha = new String(textSenha.getPassword());
        String confirmarSenha = new String(textConfirmarSenha.getPassword());

        // Validando se os campos estão vazios
        if (usuario.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validando se as senhas são iguais
        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Conectar ao banco de dados e salvar
        salvarUsuario(usuario, senha);
    }

    private void salvarUsuario(String usuario, String senha) {
        // Configuração da URL do banco de dados SQLite
        String dbUrl = "jdbc:sqlite:usuarios.db"; // Aqui você pode alterar o nome do banco se necessário
        
        // SQL para inserir o usuário no banco
        String insertSQL = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            // Preparando o statement
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, usuario);
            pstmt.setString(2, senha);
            
            // Executando a inserção no banco de dados
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();  // Fechar a janela de criar conta

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Criar janela = new Criar();
                janela.setSize(400, 300);  // Tamanho da janela
                janela.setLocationRelativeTo(null);  // Centraliza a janela
                janela.setVisible(true);
            }
        });
    }
    

    // Declaração das variáveis
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPasswordField textConfirmarSenha;
    private javax.swing.JPasswordField textSenha;
}
