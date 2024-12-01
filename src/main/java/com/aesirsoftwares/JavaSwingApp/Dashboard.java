package JavaSwingApp;

import main.java.com.example.Parser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import JavaSwingApp.VulnerabilityScanner;

public class Dashboard extends javax.swing.JFrame {

    public Dashboard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnFechar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textResultado = new javax.swing.JTextArea();
        textUrleIp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnFechar.setFont(new java.awt.Font("Arial", 1, 12));
        btnFechar.setText("X");
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });

        btnConfirmar.setFont(new java.awt.Font("Arial", 1, 12));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(evt -> btnConfirmarActionPerformed(evt));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel1.setText("Digite um URL/IP válido:");

        textResultado.setColumns(20);
        textResultado.setRows(5);
        textResultado.setEditable(false);
        jScrollPane1.setViewportView(textResultado);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel2.setText("Detector Via OWASP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConfirmar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(textUrleIp, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(0, 10, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textUrleIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFechar)
                        .addComponent(btnConfirmar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnConfirmarActionPerformed(ActionEvent evt) {
        String input = textUrleIp.getText().trim();
        StringBuilder resultado = new StringBuilder();

        if (input.isEmpty()) {
            textResultado.setText("Por favor, insira um URL ou IP.");
            return;
        }

        try {
            String parseResult = Parser.parseInput(input); // Validando entrada
            resultado.append("Resultado da análise inicial: ").append(parseResult).append("\n");

            if ("URL válida".equals(parseResult)) {
                resultado.append("Usando o OWASP ZAP para verificar vulnerabilidades...\n");
                List<String> vulnerabilities = VulnerabilityScanner.scanWithOWASPZAP(input);

                if (vulnerabilities.isEmpty()) {
                    resultado.append("Nenhuma vulnerabilidade encontrada.\n");
                } else {
                    resultado.append("Vulnerabilidades encontradas:\n");
                    vulnerabilities.forEach(v -> resultado.append("- ").append(v).append("\n"));
                }
            } else {
                resultado.append("O programa não suporta análises diretas de IP neste momento.\n");
            }
        } catch (Exception e) {
            resultado.append("Erro durante o processamento: ").append(e.getMessage());
        }

        textResultado.setText(resultado.toString());
    }

    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textResultado;
    private javax.swing.JTextField textUrleIp;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }
}
