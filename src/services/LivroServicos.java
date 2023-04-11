/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import dao.DAOFactory;
import java.util.ArrayList;
import model.Livro;
import dao.LivroDAO;
/**
 *
 * @author 182120042
 */
public class LivroServicos {

    public void cadLivro(Livro cVO) {
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.cadastrarLivroDAO(cVO);
    }

    public void atualizarLivro(Livro cVO) {
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.atualizaLivroByDoc(cVO);
    }

    public void deletarLivro(String cnpj) {
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        lDAO.deletarLivroDAO(cnpj);
    }

    public Livro buscarLivroByCOF(String isbn) {
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        return lDAO.getLivroByDoc(isbn);
    }

    public ArrayList<Livro> getLivros() {
        LivroDAO lDAO = DAOFactory.getLivroDAO();
        return lDAO.getLivrosDAO();
    }
}
