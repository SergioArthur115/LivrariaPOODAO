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
import dao.EditoraDAO;
import services.EditoraServicos;
import services.ServicosFactory;

/**
 *
 * @author 182120042
 */
public class LivroDAO {

    public void cadastrarLivroDAO(Livro cVO) {
        try {
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "insert into livros values (null,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setString(1, cVO.getTitulo());
            pst.setString(2, cVO.getAutor());
            pst.setString(3, cVO.getAssunto());
            pst.setString(4, cVO.getIsbn());
            pst.setInt(5, cVO.getEstoque());
            pst.setFloat(6, cVO.getPreco());
            pst.setInt(7, cVO.getIdEditora().getIdEditora());
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
            System.out.println("Erro ao consultar CPF!\n"
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

    public void atualizaLivroByDoc(Livro cVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set titulo = ?,autor = ?,assunto = ?,estoque = ?,preco = ? "
                    + "where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getTitulo());
            pst.setString(2, cVO.getAutor());
            pst.setString(3, cVO.getAssunto());
            pst.setInt(4, cVO.getEstoque());
            pst.setFloat(5, cVO.getPreco());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar CPF!\n"
                    + ex.getMessage());
        }
    }
}
