package com.sua_empresa.view;

import com.sua_empresa.Controller.ProfessorController;
import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;
import com.sua_empresa.model.Curso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaProfessor extends JFrame {
    private ProfessorController professorController;
    private JPanel panelAlunos; // Mover a declaração para a classe para poder limpar o painel

    public TelaProfessor(ProfessorController professorController) {
        this.professorController = professorController; // Receber controller pelo construtor

        // Configurações da tela
        setTitle("Sistema de Boletim Escolar - Professor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chama o método para mostrar o formulário
        mostrarFormularioGerarBoletim();

        setVisible(true);
    }

    public void mostrarFormularioGerarBoletim() {
        List<Curso> cursos = professorController.listarCursos(); // Obtém a lista de cursos

        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum curso encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
        comboCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Curso cursoSelecionado = (Curso) comboCursos.getSelectedItem();
                if (cursoSelecionado != null) {
                    exibirAlunosPorCurso(cursoSelecionado);
                }
            }
        });

        // Adiciona o JComboBox ao layout
        add(comboCursos, BorderLayout.NORTH); // Exibe no topo da tela

        // Cria o painel para exibir alunos
        panelAlunos = new JPanel();
        panelAlunos.setLayout(new BorderLayout()); // Define o layout do painel
        add(panelAlunos, BorderLayout.CENTER); // Adiciona um painel central para alunos
    }

    public void exibirAlunosPorCurso(Curso curso) {
        // Limpa o painel de alunos antes de adicionar novos componentes
        panelAlunos.removeAll();

        List<Aluno> alunos = professorController.listarAlunosPorCurso(curso.getId());

        String[] colunas = {"ID", "Nome", "Idade", "Matrícula"};
        Object[][] dados = new Object[alunos.size()][colunas.length];

        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            dados[i][0] = aluno.getId();
            dados[i][1] = aluno.getNome();
            dados[i][2] = aluno.getIdade();
            dados[i][3] = aluno.getMatricula();
        }

        JTable tabelaAlunos = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos); // Adiciona uma barra de rolagem
        panelAlunos.add(scrollPane, BorderLayout.CENTER); // Adiciona a tabela ao painel

        // Botão para gerar boletim
        JButton botaoGerarBoletim = new JButton("Gerar Boletim");
        botaoGerarBoletim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para pegar o aluno selecionado e inserir notas
                // ...
            }
        });

        panelAlunos.add(botaoGerarBoletim, BorderLayout.SOUTH); // Adiciona o botão na parte inferior

        panelAlunos.revalidate(); // Atualiza o painel
        panelAlunos.repaint();    // Repaint para mostrar as alterações
    }

    public static void main(String[] args) {
        // Supondo que você tenha uma implementação da conexão com o banco de dados
        // e um ProfessorController corretamente configurado
        ProfessorController professorController = new ProfessorController(); // Instanciar corretamente
        SwingUtilities.invokeLater(() -> new TelaProfessor(professorController));
    }
}
