import br.edu.ifrs.canoas.bd.produtos.Produto;
import br.edu.ifrs.canoas.bd.localizacao.Localizacao;
import java.util.ArrayList;

import javax.swing.*;

public class Main {

    public static void main(String args[]) {
        Usuario usuario = new Usuario();
        while (true) {
            switch (montaMenu()) {
                case 1:
                    switch (montaMenuUsuario()) {
                        case 1:
                            usuario.setNome(JOptionPane.showInputDialog(null, "Cadastro Usuario\nInforme o nome:"));
                            usuario.setSenha(JOptionPane.showInputDialog(null, "Informe uma senha:"));
                            usuario.setTipoUsuario(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o tipo do usuário:\n")));
                            usuario.insert();
                            break;
                      //GABRIEL FAÇA ISSO PLMDS
                        case 2:
                            switch (alteracaoUsuario()) {
                                case 1:
                                   int id = Integer.parseInt(JOptionPane.showInputDialog("Informe a ID do usuário que deseja alterar"));
                                   usuario.setNome(nome);
                                   usuario.setSenha(senha);
                                   usuario.setTipoUsuario(id);
                                    usuario.update(id);
                                   
                                    break;
                                case 2:
                                    usuario.setSenha(senha);
                                    usuario.setTipoUsuario(id);
                                    usuario.update(id);
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
                                    if (JOptionPane.YES_OPTION == opcao)
                                        System.exit(0);
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            JOptionPane.showInputDialog(null, "Exclusão");
                            break;
                        case 4:
                            JOptionPane.showInputDialog(null, "Listar todos");
                            break;
                        case 5:
                            JOptionPane.showInputDialog(null, "Listar especifico");
                            break;
                        default:
                            JOptionPane.showInputDialog(null, "SE FAZ DE BURRO NÉ, VAI VOLTAR PRO FUNDAMENTAL PRA APRENDER A LER");
                            break;
                    }
                case 2:
                    switch (montaMenuProduto()) {
                        case 1:
                            String nome = JOptionPane.showInputDialog(null, "Cadastro:\n" +
                                    "Informe o nome do produto:");
                            String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do produto:");
                            int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a localização x do produto:"));
                            int y = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a localização y do produto:"));
                            int z = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a localização z do produto:"));
                            
                            Categoria categoria = JOptionPane.showInputDialog(null, "informe a categoria do prodto");
                            Produto produto = new Produto(nome, descricao, localizacao, categoria);
                            break;
                        case 2:
                            switch (alteracaoUsuario()) {
                                case 1:
                                   
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
                                    if (JOptionPane.YES_OPTION == opcao)
                                        System.exit(0);
                                    break;
                                default:
                                    break;
                                break;
                            }
                        case 3:
                            JOptionPane.showInputDialog(null, "Exclusão");
                            break;
                        case 4:
                            JOptionPane.showInputDialog(null, "Listar todos");
                            break;
                        case 5:
                            JOptionPane.showInputDialog(null, "Listar especifico");
                            break;
                        default:
                            JOptionPane.showInputDialog(null, "TU É BURO OU TE FAZ? NÃO SABE LER AS OPÇÕES, DOENTE");
                            break;
                    }
                case 3:
                    JOptionPane.showMessageDialog(null, "! Obrigado por utilizar, aprenda a ler !");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "OPÇÃO ERADA CARALHO, DIGITA DIREITO A PORRA DA OPÇÃO, DEMENTE");
                    break;
            }
        }
    }


    public static int montaMenu() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "        Menu\n" +
                "1-Usuário\n" +
                "2-Produto\n" +
                "3-Sair\n" +
                "Informe a opção:"));
        return opcao;
    }

    public static int montaMenuUsuario() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "-----Menu Usuário-----\n" +
                "  1 - Cadastro\n" +
                "  2 - Alteração de dados\n" +
                "  3 - Exclusão de usuário\n" +
                "  4 - Listar todos\n" +
                "  5 - Listar especifico\n" +
                "-----Informe a opção-----"));
        return opcao;
    }

    public static int alteracaoUsuario() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "      Alteração de dados\n" +
                "       Escolha o que deseja alterar:\n" +
                "1-Nome\n" +
                "2-Senha\n" +
                "3-Tipo\n" +
                "4-Sair"));
        return opcao;
    }

    public static int montaMenuProduto() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "        Menu Produto\n" +
                "1-Cadastro\n" +
                "2-Alteração de dados\n" +
                "3-Exclusão de produto\n" +
                "4-Listar todos\n" +
                "5-Listar especifico\n" +
                "Informe a opção:"));
        return opcao;
    }

    public static int alteracaoProdutos() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "      Alteração de dados\n" +
                "       Escolha o que deseja alterar:\n" +
                "1-Nome\n" +
                "2-Descrição\n" +
                "3-Localização\n" +
                "4-Categoria\n" +
                "5-Sair"));
        return opcao;
    }
}