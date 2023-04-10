/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.EditoraDAO;
import dao.DAOFactory;
import java.util.ArrayList;
import model.Editora;

/**
 *
 * @author 182120042
 */
public class EditoraServicos {
        public void cadEditora(Editora cVO) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        eDAO.cadastrarEditoraDAO(cVO);
    }

    public void atualizarEditora(Editora cVO) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        eDAO.atualizaEditoraByDoc(cVO);
    }

    public void deletarEditora(String cpf) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        eDAO.deletarEditoraDAO(cpf);
    }

    public Editora buscarEditoraByCOF(String cpf) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        return eDAO.getEditoraByDoc(cpf);
    }

    public ArrayList<Editora> getEditoras() {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        return eDAO.getEditorasDAO();
    }
}
