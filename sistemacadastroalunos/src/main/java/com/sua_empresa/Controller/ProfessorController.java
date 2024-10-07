package com.sua_empresa.Controller;

import com.sua_empresa.dao.CursoDAO;
import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Curso;
import com.sua_empresa.model.Aluno;

import java.util.List;

public class ProfessorController {
    private CursoDAO cursoDao;
    private AlunoDAO alunoDao;

    public ProfessorController() {
        this.cursoDao = new CursoDAO(); // Certifique-se de que CursoDAO esteja corretamente implementado
        this.alunoDao = new AlunoDAO(null); // Certifique-se de que AlunoDAO esteja corretamente implementado
    }

    public List<Curso> listarCursos() {
        return cursoDao.listarCursos(); // Delegar a chamada para o DAO
    }

    public List<Aluno> listarAlunosPorCurso(int cursoId) {
        return alunoDao.listarAlunosPorCurso(cursoId); // Delegar a chamada para o DAO
    }
}
