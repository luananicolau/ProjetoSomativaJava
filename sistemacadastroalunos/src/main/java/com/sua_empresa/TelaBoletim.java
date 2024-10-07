package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaBoletim extends JFrame {
    private AlunoDAO alunoDAO;

    public TelaBoletim(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
        setTitle("Boletim Escolar");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes da interface
        JTextField textMatricula = new JTextField(15); // Campo para matrícula
        JButton buttonGerarBoletim = new JButton("Gerar Boletim");

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite a Matrícula do Aluno:"));
        panel.add(textMatricula);
        panel.add(buttonGerarBoletim);
        add(panel);

        // Ação para gerar boletim
        buttonGerarBoletim.addActionListener(e -> {
            String matricula = textMatricula.getText();
            Aluno aluno = alunoDAO.buscarPorMatricula(matricula); // Implemente esse método

            if (aluno != null) {
                // Gerar boletim para o aluno encontrado
                String boletim = "Boletim de " + aluno.getNome() + "\n" +
                                 "Matrícula: " + aluno.getMatricula() + "\n" +
                                 "Curso: " + aluno.getCurso() + "\n" +
                                 "Idade: " + aluno.getIdade() + "\n";
                JOptionPane.showMessageDialog(this, boletim);
            } else {
                JOptionPane.showMessageDialog(this, "Aluno não encontrado com a matrícula fornecida.");
            }
        });
    }
}
