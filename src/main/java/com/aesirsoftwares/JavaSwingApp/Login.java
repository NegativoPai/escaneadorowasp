import javax.swing.JOptionPane;

/**
 * Janela de Login para o Detector de Vulnerabilidades
 * @author joaom
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        textUsuario = new javax.swing.JTextField();
        textSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnCriarConta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Detector (COM OWASP)");

        btnFechar.setFont(new java.awt.Font("Arial", 1, 12)); 
        btnFechar.setText("Cancelar");
        btnFechar.addActionListener(evt -> System.exit(0));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); 
        jLabel2.setText("Usuário:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); 
        jLabel3.setText("Senha:");

        btnEntrar.setFont(new java.awt.Font("Arial", 1, 12)); 
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(evt -> {
            String usuario = textUsuario.getText();
            String senha = new String(textSenha.getPassword());
            if (usuario.equals("admin") && senha.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); 
        jLabel4.setText("Não tem conta?");

        btnCriarConta.setFont(new java.awt.Font("Arial", 1, 12)); 
        btnCriarConta.setText("Criar");

        // Layout do Formulário
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEntrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnFechar))
                            .addComponent(textSenha)
                            .addComponent(textUsuario)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCriarConta)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar)
                    .addComponent(btnFechar))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnCriarConta))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null); // Centraliza a janela
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnCriarConta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField textSenha;
    private javax.swing.JTextField textUsuario;
}
