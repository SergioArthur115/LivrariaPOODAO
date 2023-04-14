/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrariapoo;

import controller.CCliente;
import controller.CEditora;
import controller.CLivro;
import controller.CVendaLivro;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Cliente;
import model.Editora;
import model.Livro;
import model.VendaLivro;
import services.ClienteServicos;
import services.EditoraServicos;
import services.LivroServicos;
import services.PedidoServicos;
import services.ServicosFactory;
import util.Validadores;

/**
 *
 * @author 182120042
 */
public class LivrariaPOO {

    public static CCliente cadCliente = new CCliente();
    public static CEditora cadEditora = new CEditora();
    public static CLivro cadLivro = new CLivro();
    public static CVendaLivro cadVendaLivro = new CVendaLivro();
    public static Scanner ler = new Scanner(System.in);
    public static String tipoCad = null;

    public static int leiaNumGPT() {
        int num = 99;
        boolean leu = false;
        Scanner lerNum = new Scanner(System.in);
        while (!leu) {
            try {
                num = lerNum.nextInt();
                leu = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, tente novamente!");
                ler.nextLine();
            }
        }
        return num;
    }

    public static float lerNumFLOAT() {
        Scanner ler = new Scanner(System.in);
        try {
            return ler.nextFloat();
        } catch (Exception e) {
            System.out.println("Tente novamente!");
            lerNumFLOAT();
        }
        return 0;
    }

    public static void menuP() {
        System.out.println("|Livraria|");
        System.out.println("|1 - Gerenciar Clientes|");
        System.out.println("|2 - Gerenciar Editoras|");
        System.out.println("|3 - Gerenciar Livros  |");
        System.out.println("|4 - Venda Livro       |");
        System.out.println("|0 - Sair              |");
        System.out.print("|Escolha uma opção: ");
    }

    public static void subMenu(int op) {
        tipoCad = null;
        switch (op) {
            case 1:
                tipoCad = "Cliente";
                break;
            case 2:
                tipoCad = "Editora";
                break;
            case 3:
                tipoCad = "Livro";
                break;
        }
        System.out.println("| Gerenciar " + tipoCad + "    |");
        System.out.println("|1 - Cadastrar " + tipoCad + " |");
        System.out.println("|2 - Editar " + tipoCad + "    |");
        System.out.println("|3 - Listar " + tipoCad + "s   |");
        System.out.println("|4 - Deletar " + tipoCad + "   |");
        System.out.println("|0 - Voltar");
        System.out.print("  Escolha uma opção: ");
    }

    public static void cadastrarCliente() {
        int idCliente;
        String nomeCliente;
        String cpf;
        String cnpj = null;
        String endereco;
        String telefone;
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();

        System.out.println("|Cadastro de Cliente|");
        System.out.print("Informe o CPF:");
        boolean cpfis, tel;
        int opCPF, opTel;
        do {
            cpf = ler.nextLine();
            cpfis = Validadores.isCPF(cpf);
            if (!cpfis) {
                System.out.println("CPF Inválido" + "\nDeseja tentar novamente? 1- Sim | 2 - Não");
                opCPF = leiaNumGPT();

                if (opCPF == 1) {
                    System.out.print("Informe o CPF:");
                } else if (opCPF == 2) {
                    System.out.println("Cadastro cancelado pelo usuário");
                    return;
                }
            }
        } while (!Validadores.isCPF(cpf));
        if (clienteS.buscarClienteByCOF(cpf).getCpf() != null) {
            System.out.println("Cliente já cadastrado!");
        } else {
            System.out.println("Informe o nome:");
            nomeCliente = ler.nextLine();
            System.out.println("Informe o telefone:");
            do {
                telefone = ler.nextLine();
                tel = Validadores.validarTelefone(telefone);
                if (!tel) {
                    System.out.println("Telefone Inválido" + "\nDeseja tentar novamente? 1- Sim | 2 - Não");
                    opTel = leiaNumGPT();

                    if (opTel == 1) {
                        System.out.print("Informe o Telefone:");
                    } else if (opTel == 2) {
                        System.out.println("Cadastro cancelado pelo usuário");
                        return;
                    }
                }
            } while (!Validadores.validarTelefone(telefone));

            System.out.println("Informe o endereço:");
            endereco = ler.nextLine();
            idCliente = cadCliente.geraID();
            Cliente cli = new Cliente(idCliente, nomeCliente, cpf, cnpj, endereco, telefone);
            //cadCliente.addCliente(cli);

            clienteS.cadCliente(cli);
            System.out.println("Cliente cadastrado com sucesso!");
        }
    }

    public static void editarCliente() {
        System.out.println("|Editar Cliente|");
        System.out.print("Informe o CPF: ");
        String cpf = ler.nextLine();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = clienteS.buscarClienteByCOF(cpf);
            if (cli != null) {
                System.out.println("1 - Nome: " + cli.getNomeCliente());
                System.out.println("2 - Endereço: " + cli.getEndereco());
                System.out.println("3 - Telefone: " + cli.getTelefone());
                System.out.println("4 - Todos as campos acima");
                System.out.print("Informe o campo a qual deseja alterar:");
                int opEditar = leiaNumGPT();

                if (opEditar == 1 || opEditar == 4) {
                    System.out.print("Informe o novo nome: ");
                    cli.setNomeCliente(ler.nextLine());
                }
                if (opEditar == 2 || opEditar == 4) {
                    System.out.print("Informe o novo endereço: ");
                    cli.setEndereco(ler.nextLine());
                }
                if (opEditar == 3 || opEditar == 4) {
                    System.out.print("Informe o novo telefone: ");
                    cli.setTelefone(ler.nextLine());
                }
                if (opEditar < 1 || opEditar > 4) {
                    System.out.println("Opção inválida");
                }
                System.out.println("Cliente: \n" + cli.toString());
                clienteS.atualizarCliente(cli);
            } else {
                System.out.println("Cliente não cadastrado!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }

    public static void listarCliente() {
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        for (Cliente cli : clienteS.getClientes()) {
            System.out.println("\nCPF: " + cli.getCpf());
            System.out.println("Nome: " + cli.getNomeCliente());
            System.out.println("Telefone: " + cli.getTelefone());
        }
    }

    public static void deletarCliente() {
        System.out.println("|Deletar Cliente|");
        System.out.print("Informe o CPF: ");
        String cpf = ler.nextLine();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = clienteS.buscarClienteByCOF(cpf);
            if (cli != null) {
                //cadCliente.removeCliente(cli);

                clienteS.deletarCliente(cpf);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Cliente não consta na base de dados!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }

    public static void cadastrarEditora() {
        int idEditora;
        String nomeEditora;
        String cnpj;
        String endereco;
        String telefone;
        String gerente;
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();

        System.out.println("|Cadastro de Editora|");
        System.out.print("Informe o CNPJ:");
        boolean cnpjis, tel;
        int opCNPJ, opTel;
        do {
            cnpj = ler.nextLine();
            cnpjis = Validadores.isCNPJ(cnpj);
            if (!cnpjis) {
                System.out.println("CNPJ Inválido" + "\nDeseja tentar novamente? 1- Sim | 2 - Não");
                opCNPJ = leiaNumGPT();

                if (opCNPJ == 1) {
                    System.out.print("Informe o CNPJ:");
                } else if (opCNPJ == 2) {
                    System.out.println("Cadastro cancelado pelo usuário");
                    return;
                }
            }
        } while (!Validadores.isCNPJ(cnpj));
        if (editoraS.buscarEditoraByCOF(cnpj).getCnpj() != null) {
            System.out.println("Editora já cadastrada!");
        } else {
            System.out.println("Informe o nome:");
            nomeEditora = ler.nextLine();
            System.out.println("Informe o telefone:");
            do {
                telefone = ler.nextLine();
                tel = Validadores.validarTelefone(telefone);
                if (!tel) {
                    System.out.println("Telefone Inválido" + "\nDeseja tentar novamente? 1- Sim | 2 - Não");
                    opTel = leiaNumGPT();

                    if (opTel == 1) {
                        System.out.print("Informe o Telefone:");
                    } else if (opTel == 2) {
                        System.out.println("Cadastro cancelado pelo usuário");
                        return;
                    }
                }
            } while (!Validadores.validarTelefone(telefone));
            System.out.println("Informe o endereço:");
            endereco = ler.nextLine();
            System.out.println("Informe o nome do gerente");
            gerente = ler.nextLine();
            idEditora = cadEditora.geraID();
            Editora edi = new Editora(idEditora, nomeEditora, cnpj, endereco, telefone, gerente);
            //cadEditora.addEditora(edi);
            editoraS.cadEditora(edi);
            System.out.println("Editora cadastrada com sucesso!");
        }
    }

    public static void editarEditora() {
        System.out.println("|Editar Editora|");
        System.out.print("Informe o CNPJ: ");
        String cnpj = ler.nextLine();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = editoraS.buscarEditoraByCOF(cnpj);
            if (edi != null) {
                System.out.println("1 - Nome: " + edi.getNomeEditora());
                System.out.println("2 - Endereço: " + edi.getEndereco());
                System.out.println("3 - Telefone: " + edi.getTelefone());
                System.out.println("4 - Gerente: " + edi.getGerente());
                System.out.println("5 - Todos as campos acima");
                System.out.print("Informe o campo a qual deseja alterar:");
                int opEditar = leiaNumGPT();

                if (opEditar == 1 || opEditar == 5) {
                    System.out.print("Informe o novo nome: ");
                    edi.setNomeEditora(ler.nextLine());
                }
                if (opEditar == 2 || opEditar == 5) {
                    System.out.print("Informe o novo endereço: ");
                    edi.setEndereco(ler.nextLine());
                }
                if (opEditar == 3 || opEditar == 5) {
                    System.out.print("Informe o novo telefone: ");
                    edi.setTelefone(ler.nextLine());
                }
                if (opEditar == 4 || opEditar == 5) {
                    System.out.print("Informe o novo gerente: ");
                    edi.setGerente(ler.nextLine());
                }
                if (opEditar < 1 || opEditar > 5) {
                    System.out.println("Opção inválida");
                }
                System.out.println("Editora: \n" + edi.toString());
                editoraS.atualizarEditora(edi);
            } else {
                System.out.println("Editora não cadastrada!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }

    public static void listarEditora() {
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        for (Editora edi : editoraS.getEditoras()) {
            System.out.println("\nCNPJ: " + edi.getCnpj());
            System.out.println("Nome: " + edi.getNomeEditora());
            System.out.println("Telefone: " + edi.getTelefone());
            System.out.println("Gerente: " + edi.getGerente());
        }
    }

    public static void deletarEditora() {
        System.out.println("|Deletar editora|");
        System.out.print("Informe o CNPJ: ");
        String cnpj = ler.nextLine();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = editoraS.buscarEditoraByCOF(cnpj);
            if (edi != null) {
                //cadEditora.removeEditora(edi);
                editoraS.deletarEditora(cnpj);
                System.out.println("Editora deletada com sucesso!");
            } else {
                System.out.println("Editora não consta na base de dados!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }

    public static void cadastrarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();

        System.out.println("|Cadastrar livro|");
        System.out.println("Informe o ISBN: ");
        String isbn = ler.nextLine();
        if (livroS.buscarLivroByCOF(isbn) == null) {
            System.out.println("Livro já cadastrado!.");
        } else {
            int idLivro = cadLivro.geraID();
            System.out.println("Informe o titulo do livro: ");
            String titulo = ler.nextLine();
            System.out.println("Informe o autor do livro: ");
            String autor = ler.nextLine();
            System.out.println("Informe o gênero: ");
            String assunto = ler.nextLine();
            System.out.println("Informe o estoque: ");
            int estoque = leiaNumGPT();
            System.out.println("Informe o preço: ");
            float preco = lerNumFLOAT();
            boolean isCNPJ = false;
            Editora idEditora = null;
            do {
                System.out.println("Informe o CNPJ da editora: ");
                String cnpj = ler.nextLine();
                isCNPJ = Validadores.isCNPJ(cnpj);
                if (isCNPJ) {
                    idEditora = editoraS.buscarEditoraByCOF(cnpj);
                    if (idEditora == null) {
                        System.out.println("Editora não cadastrada!.");
                        isCNPJ = false;
                    } else {
                        Livro li = new Livro(idLivro, idEditora, estoque, preco, titulo, autor, assunto, isbn);
                        livroS.cadLivro(li);
                        //cadLivro.addLivro(li);
                        System.out.println("Livro cadastrado com sucesso!.");
                        isCNPJ = false;
                    }
                } else {
                    System.out.println("CNPJ inválido!.");
                }
            } while (isCNPJ);

        }
    }

    public static void editarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        System.out.println("|Editar Livro|");
        System.out.print("Informe o ISBN: ");
        String isbn = ler.nextLine();
        Livro eli = livroS.buscarLivroByCOF(isbn);
        if (eli != null) {
            System.out.println("1 - Titulo: " + eli.getTitulo());
            System.out.println("2 - Estoque: " + eli.getEstoque());
            System.out.println("3 - Preço: " + eli.getPreco());
            System.out.println("4 - Todos as campos acima");
            System.out.print("Informe o campo a qual deseja alterar:");
            int opEditar = leiaNumGPT();

            if (opEditar == 1 || opEditar == 4) {
                System.out.print("Informe o novo titulo: ");
                eli.setTitulo(ler.nextLine());
            }
            if (opEditar == 2 || opEditar == 4) {
                System.out.print("Informe o novo estoque: ");
                eli.setEstoque(leiaNumGPT());
            }
            if (opEditar == 3 || opEditar == 4) {
                System.out.print("Informe o novo preço: ");
                eli.setPreco(lerNumFLOAT());
            }
            if (opEditar < 1 || opEditar > 4) {
                System.out.println("Opção inválida");
            }
            System.out.println("Livro: \n" + eli.toString());
            livroS.atualizarLivro(eli);
        } else {
            System.out.println("Livro não cadastrado!");
        }
    }

    public static void listarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        System.out.println("|Listar Livros|");
        for (Livro livro : livroS.getLivros()) {
            System.out.println("ISBN:  " + livro.getIsbn());
            System.out.println("Titulo:  " + livro.getTitulo());
            System.out.println("Assunto:  " + livro.getAssunto());
            System.out.println("Autor:  " + livro.getAutor());
            System.out.println("Estoque:  " + livro.getEstoque());
            System.out.println("Editora:  " + livro.getIdEditora().getNomeEditora() + "\n");

        }
    }

    public static void deletarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        System.out.println("| Deletar Livro|");
        System.out.print("Informe o ISBN: ");
        String isbn = ler.nextLine();
        Livro li = livroS.buscarLivroByCOF(isbn);
        if (li != null) {
            System.out.println("Livro " + li.getTitulo() + " Será deletado!.");
            livroS.deletarLivro(isbn);
            //cadLivro.removeLivro(li);
        } else {
            System.out.println("ISBN não encontrado!.");
        }
    }

    public static void vendaLivro() {
        int idVendaLivro;
        Cliente idCliente = null;
        ArrayList<Livro> livros = new ArrayList<>();
        float subTotal = 0;
        LocalDate dataVenda = LocalDate.now();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        LivroServicos livroS = ServicosFactory.getLivroServicos();

        do {
            System.out.println("Informe o CPF do cliente: ");
            String CPF = ler.nextLine();
            if (Validadores.isCPF(CPF)) {
                idCliente = cadCliente.getClienteCPF(CPF);
                if (idCliente == null) {
                    System.out.println("Cliente não cadastrado, tente novamente!");
                }
            } else {
                System.out.println("CPF inválido, tente novamente!");
            }
        } while (idCliente == null);

        boolean venda = true;
        do {
            Livro li = null;
            String isbn;
            do {
                System.out.println("Informe o ISBN: ");
                isbn = ler.nextLine();
                li = cadLivro.getLivroISBN(isbn);
                if (li == null) {
                    System.out.println("Livro não encontrado, tente novamente!");
                }
            } while (li == null);
            if (li.getEstoque() > 0) {
                livros.add(li);
                cadLivro.attEstoqueLivro(li.getIsbn());
                subTotal += li.getPreco();
            } else {
                System.out.println(li.getTitulo() + "está fora de estoque");
            }

            System.out.println("Deseja comprar mais livros nesta venda?"
                    + "\n1 - Sim | 2 - não"
                    + "\nDigite: ");
            if (leiaNumGPT() == 2) {
                venda = false;
            }
        } while (venda);
        idVendaLivro = cadVendaLivro.geraID();
        VendaLivro vl = new VendaLivro(idVendaLivro, idCliente, livros, subTotal, dataVenda);
        cadVendaLivro.addVendaLivro(vl);
        System.out.println("|Venda|\n" + vl.toString());
    }

    public static void vendaLivro2() {
        int idVendaLivro;
        Cliente idCliente = null;
        ArrayList<Livro> livros = new ArrayList<>();
        float subTotal = 0;
        LocalDate dataVenda = LocalDate.now();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        PedidoServicos pedidoS = ServicosFactory.getPedidoServicos();

        do {
            System.out.println("Informe o CPF do cliente: ");
            String CPF = ler.nextLine();
            if (Validadores.isCPF(CPF)) {
                idCliente = clienteS.buscarClienteByCOF(CPF);
                if (idCliente.getCpf() == null) {
                    System.out.println("Cliente não cadastrado, tente novamente!");
                }
            } else {
                System.out.println("CPF inválido, tente novamente!");
            }
        } while (idCliente.getCpf() == null);

        boolean venda = true;
        do {
            Livro li = null;
            String isbn;
            do {
                System.out.println("Informe o ISBN: ");
                isbn = ler.nextLine();
                li = livroS.buscarLivroByCOF(isbn);
                if (li.getIsbn() == null) {
                    System.out.println("Livro não encontrado, tente novamente!");
                }
            } while (li.getIsbn() == null);
            if (li.getEstoque() > 0) {
                livros.add(li);
                li.setEstoque(li.getEstoque() - 1);
                livroS.atualizarEstoque(li);
                subTotal += li.getPreco();
            } else {
                System.out.println(li.getTitulo() + "está fora de estoque");
            }

            System.out.println("Deseja comprar mais livros nesta venda?"
                    + "\n1 - Sim | 2 - não"
                    + "\nDigite: ");
            if (leiaNumGPT() == 2) {
                venda = false;
            }
        } while (venda);

        idVendaLivro = cadVendaLivro.geraID();
        VendaLivro vl = new VendaLivro(idVendaLivro, idCliente, livros, subTotal, dataVenda);
        pedidoS.cadPedidoLivro(vl);
        System.out.println("|Venda|\n" + vl.toString());
    }

    public static void main(String[] args) {
        //cadCliente.mockClientes();
        //cadEditora.mockEditoras();
        //cadLivro.mockLivros();
        //cadVendaLivro.mockVendaLivros();

        int opM;
        do {
            menuP();
            opM = leiaNumGPT();
            switch (opM) {
                case 1:
                case 2:
                case 3:
                    int opSM;
                    do {
                        subMenu(opM);
                        opSM = leiaNumGPT();
                        switch (opSM) {
                            case 1:
                                System.out.println("|Cadastrar|");
                                if (tipoCad.equals("Cliente")) {
                                    cadastrarCliente();
                                } else if (tipoCad.equals("Editora")) {
                                    cadastrarEditora();
                                } else if (tipoCad.equals("Livro")) {
                                    cadastrarLivro();
                                }

                                break;
                            case 2:
                                System.out.println("|Editar|");
                                if (tipoCad.equals("Cliente")) {
                                    editarCliente();
                                } else if (tipoCad.equals("Editora")) {
                                    editarEditora();
                                } else if (tipoCad.equals("Livro")) {
                                    editarLivro();
                                }

                                break;
                            case 3:
                                System.out.println("|Listar|");
                                if (tipoCad.equals("Cliente")) {
                                    listarCliente();
                                } else if (tipoCad.equals("Editora")) {
                                    listarEditora();
                                } else if (tipoCad.equals("Livro")) {
                                    listarLivro();
                                }

                                break;
                            case 4:
                                System.out.println("|Deletar|");
                                if (tipoCad.equals("Cliente")) {
                                    deletarCliente();
                                } else if (tipoCad.equals("Editora")) {
                                    deletarEditora();
                                } else if (tipoCad.equals("Livro")) {
                                    deletarLivro();
                                }
                                break;
                            case 0:
                                System.out.println("|Menu Principal|");
                                opM = 99;
                                break;
                            default:
                                System.out.println("ERROR 404!!!");
                                break;
                        }

                    } while (opSM != 0);
                    break;
                case 4:
                    System.out.println("|Venda Livro|");
                    vendaLivro2();
                    break;
                case 0:
                    System.out.println("Aplicação encerrada pelo usuário!");
                    break;
                default:
                    System.out.println("Entrada inválida, tente novamente!");
                    break;
            }
        } while (opM != 0);

    }
}
