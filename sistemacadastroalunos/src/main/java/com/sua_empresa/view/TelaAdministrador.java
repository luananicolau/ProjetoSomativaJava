package com.sua_empresa.view;

import com.sua_empresa.AlunoDAO;
import com.sua_empresa.model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaAdministrador extends JFrame {

    private AlunoDAO alunoDao;
    private JTable tabelaAlunos;

    public TelaAdministrador() {
        alunoDao = new AlunoDAO(); // Inicializa o DAO

        // Configurações da tela
        setTitle("Sistema de Gerenciamento - Administrador");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cria a tabela de alunos
        tabelaAlunos = new JTable();
        atualizarTabelaAlunos();

        // Cria os botões
        JButton botaoAdicionar = new JButton("Adicionar Aluno");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioAdicionarAluno();
            }
        });

        // Painel com os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAdicionar);

        // Adiciona os componentes à tela
        add(new JScrollPane(tabelaAlunos), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void atualizarTabelaAlunos() {
        Aluno[] alunos = alunoDao.listarAlunos();
        String[] colunas = {"ID", "Nome", "Idade", "Curso", "Matrícula"};
        Object[][] dados = new Object[alunos.length][colunas.length];

        for (int i = 0; i < alunos.length; i++) {
            Aluno aluno = alunos[i]; // Acesso correto ao objeto Aluno
            dados[i][0] = aluno.getId();
            dados[i][1] = aluno.getNome();
            dados[i][2] = aluno.getIdade();
            dados[i][3] = aluno.getCurso();
            dados[i][4] = aluno.getMatricula();
        }

        tabelaAlunos.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }

    private void mostrarFormularioAdicionarAluno() {
        JFrame frameAdicionar = new JFrame("Adicionar Aluno");
        frameAdicionar.setSize(300, 300);
        frameAdicionar.setLayout(new GridLayout(5, 2));

        JTextField campoNome = new JTextField();
        JTextField campoIdade = new JTextField();
        JTextField campoCurso = new JTextField();
        JTextField campoMatricula = new JTextField();

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Cria um novo aluno com os dados do formulário
                    Aluno novoAluno = new Aluno(
                            0, // O ID deve ser gerado automaticamente ou definido pelo DAO
                            campoNome.getText(),
                            Integer.parseInt(campoIdade.getText()),
                            campoCurso.getText(),
                            campoMatricula.getText(), getDefaultCloseOperation()
                    );
                    alunoDao.adicionarAluno(novoAluno);
                    frameAdicionar.dispose(); // Fecha o formulário
                    atualizarTabelaAlunos(); // Atualiza a tabela de alunos
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frameAdicionar, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frameAdicionar.add(new JLabel("Nome:"));
        frameAdicionar.add(campoNome);
        frameAdicionar.add(new JLabel("Idade:"));
        frameAdicionar.add(campoIdade);
        frameAdicionar.add(new JLabel("Curso:"));
        frameAdicionar.add(campoCurso);
        frameAdicionar.add(new JLabel("Matrícula:"));
        frameAdicionar.add(campoMatricula);
        frameAdicionar.add(botaoSalvar);

        frameAdicionar.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdministrador tela = new TelaAdministrador();
            tela.setVisible(true);
        });
    }
}
