/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Livro;
import model.VendaLivro;
import services.ClienteServicos;
import services.ServicosFactory;

/**
 *
 * @author 182120042
 */
public class PedidoDAO {

    public void cadastrarPedidoDAO(VendaLivro pVO) {
        try {
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "insert into pedidos values (null,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setInt(2, pVO.getIdCliente().getIdCliente());
            java.sql.Date dataVenda = java.sql.Date.valueOf(pVO.getDataVenda());
            pst.setDate(1, dataVenda);
            pst.setFloat(3, pVO.getSubTotal());
            pst.executeUpdate();

            String sqlIdPedido = "select max(idpedido) as idpedido from pedidos";
            PreparedStatement pst2 = con.prepareStatement(sqlIdPedido);
            ResultSet rsIdPed = pst2.executeQuery();
            int idPedido = 0;
            while (rsIdPed.next()) {
                idPedido = rsIdPed.getInt("idpedido");
            }

            String sqlPedLivros = "insert into pedidoslivros values (?,?)";
            PreparedStatement pst3 = con.prepareStatement(sqlPedLivros);
            for (Livro livro : pVO.getLivros()) {
                pst3.setInt(1, idPedido);
                pst3.setInt(2, livro.getIdLivro());
                pst3.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao realizar venda!\n"
                    + ex.getMessage());
        }
    }

}
