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
import services.EditoraServicos;
import services.ServicosFactory;

/**
 *
 * @author 182120042
 */
public class LivroDAO {

    public void cadastrarLivroDAO(Livro lVO) {
        try {
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "insert into livros values (null,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setString(1, lVO.getTitulo());
            pst.setString(2, lVO.getAutor());
            pst.setString(3, lVO.getAssunto());
            pst.setString(4, lVO.getIsbn());
            pst.setInt(5, lVO.getEstoque());
            pst.setFloat(6, lVO.getPreco());
            pst.setInt(7, lVO.getIdEditora().getIdEditora());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar!\n"
                    + ex.getMessage());
        }
    }

    public ArrayList<Livro> getLivrosDAO() {
        ArrayList<Livro> livros = new ArrayList<>();
        EditoraServicos ediS = ServicosFactory.getEditoraServicos();
        //EditoraDAO edDAO = new EditoraDAO();
        try {
            Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql = "Select * from livros";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Livro l = new Livro();
                l.setIdLivro(rs.getInt("idlivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAssunto(rs.getString("assunto"));
                l.setIsbn(rs.getString("isbn"));
                l.setEstoque(rs.getInt("estoque"));
                l.setPreco(rs.getFloat("preco"));
                l.setIdEditora(ediS.buscarEditoraById(rs.getInt("ideditora")));
                //l.setIdEditora(edDAO.getEditoraID(rs.getInt("ideditora")));
                livros.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar!\n"
                    + ex.getMessage());
        }
        return livros;
    }

    public Livro getLivroByDoc(String isbn) {
        Livro l = new Livro();
        EditoraServicos ediS = ServicosFactory.getEditoraServicos();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from livros where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l.setIdLivro(rs.getInt("idlivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAssunto(rs.getString("assunto"));
                l.setIsbn(rs.getString("isbn"));
                l.setEstoque(rs.getInt("estoque"));
                l.setPreco(rs.getFloat("preco"));
                l.setIdEditora(ediS.buscarEditoraById(rs.getInt("ideditora")));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar ISBN!\n"
                    + ex.getMessage());
        }
        return l;
    }

    public void deletarLivroDAO(String isbn) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from livros where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar!\n"
                    + ex.getMessage());
        }
    }

    public void atualizaLivroByDoc(Livro lVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set titulo = ?,autor = ?,assunto = ?,estoque = ?,preco = ? "
                    + "where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lVO.getTitulo());
            pst.setString(2, lVO.getAutor());
            pst.setString(3, lVO.getAssunto());
            pst.setInt(4, lVO.getEstoque());
            pst.setFloat(5, lVO.getPreco());
            pst.setString(6, lVO.getIsbn());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar ISBN!\n"
                    + ex.getMessage());
        }
    }
        public void atualizaEstoqueByDoc(Livro lVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set estoque = ? "
                    + "where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, lVO.getEstoque());
            pst.setString(2, lVO.getIsbn());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar estoque!\n"
                    + ex.getMessage());
        }
    }
}
