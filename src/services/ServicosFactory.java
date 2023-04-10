/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author 182120042
 */
public class ServicosFactory {

    private static ClienteServicos cServicos = new ClienteServicos();
    private static EditoraServicos eServicos = new EditoraServicos();

    public static ClienteServicos getClienteServicos() {
        return cServicos;
    }

    public static EditoraServicos getEditoraServicos() {
        return eServicos;
    }
}
