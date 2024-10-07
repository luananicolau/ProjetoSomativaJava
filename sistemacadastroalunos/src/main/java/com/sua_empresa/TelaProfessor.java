package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;
import com.sua_empresa.reports.RelatorioAluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaProfessor extends JFrame {
    private AlunoDAO alunoDAO;

    public TelaProfessor(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
        setTitle("Tela do Professor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes da interface
        JButton buttonListar = new JButton("Listar Alunos");
        JButton buttonRelatorio = new JButton("Gerar Relatório");
        JButton buttonBoletim = new JButton("Gerar Boletim");

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(buttonListar);
        panel.add(buttonRelatorio);
        panel.add(buttonBoletim); // Adicionando o botão de boletim
        add(panel);

        // Ação para listar alunos
        buttonListar.addActionListener(e -> {
            StringBuilder alunosStr = new StringBuilder("Alunos cadastrados:\n");
            for (Aluno aluno : alunoDAO.listarAlunos()) {
                alunosStr.append(aluno.getNome()).append(" - ").append(aluno.getCurso()).append("\n");
            }
            JOptionPane.showMessageDialog(this, alunosStr.toString());
        });

        // Ação para gerar relatório
        buttonRelatorio.addActionListener(e -> {
            String caminhoArquivo = JOptionPane.showInputDialog("Digite o caminho para salvar o relatório:");
            if (caminhoArquivo != null && !caminhoArquivo.isEmpty()) {
                RelatorioAluno relatorio = new RelatorioAluno(alunoDAO);
                try {
                    relatorio.gerarRelatorio(caminhoArquivo);
                    JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Caminho do arquivo não pode estar vazio!");
            }
        });

        // Ação para gerar boletim
        buttonBoletim.addActionListener(e -> {
            String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para gerar o boletim:");
            if (matricula != null && !matricula.isEmpty()) {
                Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
                if (aluno != null) {
                    StringBuilder boletim = new StringBuilder();
                    boletim.append("Boletim Escolar\n");
                    boletim.append("Nome: ").append(aluno.getNome()).append("\n");
                    boletim.append("Curso: ").append(aluno.getCurso()).append("\n");
                    boletim.append("Nota Prova 1: ").append(aluno.getNotaProva1()).append("\n");
                    boletim.append("Nota Prova 2: ").append(aluno.getNotaProva2()).append("\n");
                    JOptionPane.showMessageDialog(this, boletim.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
                }
            }
        });
    }
}
