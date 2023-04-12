/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.PedidoDAO;
import java.util.ArrayList;
import model.VendaLivro;

/**
 *
 * @author 182120042
 */
public class PedidoServicos {

    public void cadLivro(VendaLivro pVO) {
        PedidoDAO pDAO = DAOFactory.getPedidoDAO();
        pDAO.cadastrarPedidoDAO(pVO);
    }

    public void atualizarLivro(VendaLivro pVO) {
        PedidoDAO pDAO = DAOFactory.getPedidoDAO();
        pDAO.atualizaPedidoByDoc(pVO);
    }

    public void deletarLivro(int idpedido) {
        PedidoDAO pDAO = DAOFactory.getPedidoDAO();
        pDAO.deletarPedidoDAO(idpedido);
    }

    public VendaLivro buscarLivroByCOF(int idpedido) {
        PedidoDAO pDAO = DAOFactory.getPedidoDAO();
        return pDAO.getPedidoByDoc(idpedido);
    }

    public ArrayList<VendaLivro> getLivros() {
        PedidoDAO pDAO = DAOFactory.getPedidoDAO();
        return pDAO.getPedidoDAO();
    }
}
