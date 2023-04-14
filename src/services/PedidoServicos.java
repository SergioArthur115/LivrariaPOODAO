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

    public void cadPedidoLivro(VendaLivro pVO) {
        PedidoDAO pDAO = DAOFactory.getPedidoDAO();
        pDAO.cadastrarPedidoDAO(pVO);
    }

}
