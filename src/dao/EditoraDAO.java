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
import model.Editora;

/**
 *
 * @author 182120042
 */
public class EditoraDAO {

    public void cadastrarEditoraDAO(Editora cVO) {
        try {
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "insert into editoras values (null,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setString(1, cVO.getNomeEditora());
            pst.setString(2, cVO.getEndereco());
            pst.setString(3, cVO.getTelefone());
            pst.setString(4, cVO.getGerente());
            pst.setString(5, cVO.getCnpj());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar!\n"
                    + ex.getMessage());
        }
    }

    public ArrayList<Editora> getEditorasDAO() {
        ArrayList<Editora> editoras = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql = "Select * from editoras";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Editora e = new Editora();
                e.setIdEditora(rs.getInt("ideditora"));
                e.setNomeEditora(rs.getString("nomeEditora"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
                e.setCnpj(rs.getString("cnpj"));
                editoras.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar!\n"
                    + ex.getMessage());
        }
        return editoras;
    }

    public Editora getEditoraByDoc(String cnpj) {
        Editora e = new Editora();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from editoras where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                e.setIdEditora(rs.getInt("ideditora"));
                e.setNomeEditora(rs.getString("nomeEditora"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
                e.setCnpj(rs.getString("cnpj"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar CNPJ!\n"
                    + ex.getMessage());
        }
        return e;
    }

    public void deletarEditoraDAO(String cnpj) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from editoras where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar!\n"
                    + ex.getMessage());
        }
    }

    public void atualizaEditoraByDoc(Editora cVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update editoras set nomeEditora = ?,endereco = ?,telefone = ?,gerente = ? "
                    + "where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNomeEditora());
            pst.setString(2, cVO.getEndereco());
            pst.setString(3, cVO.getTelefone());
            pst.setString(4, cVO.getGerente());
            pst.setString(5, cVO.getCnpj());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar CNPJ!\n"
                    + ex.getMessage());
        }
    }

    public Editora getEditoraID(int ideditora) {
        Editora e = new Editora();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from editoras where ideditora = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, ideditora);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                e.setIdEditora(rs.getInt("ideditora"));
                e.setNomeEditora(rs.getString("nomeEditora"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
                e.setCnpj(rs.getString("cnpj"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar ID!\n"
                    + ex.getMessage());
        }
        return e;
    }
}
