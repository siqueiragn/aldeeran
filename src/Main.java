
import br.edu.ifrs.canoas.bd.categorias.CategoriaProduto;
import static br.edu.ifrs.canoas.bd.categorias.CategoriaProduto.getLista;
import br.edu.ifrs.canoas.bd.categorias.CategoriaUsuario;
import static br.edu.ifrs.canoas.bd.categorias.CategoriaUsuario.getAll;
import br.edu.ifrs.canoas.bd.produtos.Produto;
import br.edu.ifrs.canoas.bd.localizacao.Localizacao;
import static br.edu.ifrs.canoas.bd.produtos.Produto.getAllProducts;
import static br.edu.ifrs.canoas.bd.produtos.Produto.loadID;
import br.edu.ifrs.canoas.bd.usuarios.Usuario;
import static br.edu.ifrs.canoas.bd.usuarios.Usuario.getAllUsers;
import static br.edu.ifrs.canoas.bd.usuarios.Usuario.loadUser;
import java.util.ArrayList;

import javax.swing.*;

public class Main {

    public static void main(String args[]) {
        boolean authMe = false;
        while (true) {
            ArrayList<CategoriaUsuario> ctUserList = getAll();
            String auxListUser = "\n";
            for (CategoriaUsuario ctUserList1 : ctUserList) {
                auxListUser += ctUserList1.getIdCategoria() + " - " + ctUserList1.getNome() + "\n";
            }

            ArrayList<CategoriaProduto> ctProdLista = new ArrayList<>();
            CategoriaProduto ctProd = null;
            ctProdLista = getLista();
            String auxLista = "\n";
            for (CategoriaProduto ctProdLista1 : ctProdLista) {
                auxLista += ctProdLista1.getIdCategoria() + " - " + ctProdLista1.getNome() + "\n";
            }

            Usuario userLogged = new Usuario();
            int idLogin;
            String senha = "";
            if (authMe == false) {
                idLogin = Integer.parseInt(JOptionPane.showInputDialog("Informe a ID do usuário: "));
                senha = JOptionPane.showInputDialog("Informe a senha do usuário: ");
                userLogged = loadUser(idLogin);
                if (userLogged.getIdUsuario() != 0) {
                    if (userLogged.getSenha().equals(senha)) {
                        JOptionPane.showMessageDialog(null, "Autenticado com sucesso!");
                        authMe = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID inexistente!");
                }
            }

            if (authMe == true) {
                switch (montaMenu()) {
                    case 1:
                        switch (montaMenuUsuario()) {
                            case 1:
                                if(userLogged.getTipoUsuario() > 2){
                                Usuario user = new Usuario();
                                user.setNome(JOptionPane.showInputDialog(null, "Cadastro\n"
                                        + "Informe o nome:"));
                                user.setSenha(JOptionPane.showInputDialog(null, "Informe uma senha:"));

                                user.setTipoUsuario(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o tipo do usuário: " + auxListUser)));
                                user.insert();
                                } else 
                                    JOptionPane.showMessageDialog(null, "Sem permissão!");
                                break;
                            case 2:
                                int id;
                                if (userLogged.getTipoUsuario() > 2) {
                                    id = Integer.parseInt(JOptionPane.showInputDialog("Informe a ID do usuário que deseja alterar"));
                                } else {
                                    id = userLogged.getIdUsuario();
                                }
                                
                                Usuario user = new Usuario();
                                switch (alteracaoUsuario()) {
                                    // NOME
                                    case 1:
                                        user.setNome(JOptionPane.showInputDialog("Informe o novo nome: "));
                                        user.updateUser(id, 1);
                                        break;
                                    // SENHA
                                    case 2:
                                        user.setSenha(JOptionPane.showInputDialog("Informe a nova senha: "));
                                        user.updateUser(id, 2);
                                        break;
                                    // TIPO
                                    case 3:
                                        user.setTipoUsuario(Integer.parseInt(JOptionPane.showInputDialog("Informe o tipo do usuário: " + auxListUser)));
                                        user.updateUser(id, 3);
                                        break;
                                    case 0:
                                        break;

                                }
                                break;
                            case 3:
                                if (userLogged.getTipoUsuario() > 2) {
                                    user = new Usuario();
                                    user.delete(Integer.parseInt(JOptionPane.showInputDialog(null, "Exclusão: \nInforme a ID do usuário que deseja excluir: ")));
                                } else {
                                    JOptionPane.showMessageDialog(null, "Sem permissão!");
                                }
                                break;
                            case 4:
                                String auxUser = "";
                                ArrayList<Usuario> UserList = UserList = getAllUsers();
                                for (Usuario UserList1 : UserList) {
                                    auxUser += UserList1.toString();
                                }
                                JOptionPane.showMessageDialog(null, auxUser);
                                break;
                            case 5:
                                user = new Usuario();
                                user = loadUser(Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do usuário que deseja localizar: ")));
                                JOptionPane.showMessageDialog(null, user.toString());

                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showInputDialog(null, "Opção inválida!");
                                break;

                        }
                        break;
                    case 2:
                        switch (montaMenuProduto()) {
                            case 1:
                                if (userLogged.getTipoUsuario() > 1) {
                                    Produto prod = new Produto();
                                    prod.setNome(JOptionPane.showInputDialog(null, "Cadastro:\n"
                                            + "Informe o nome do produto:"));
                                    prod.setDescricao(JOptionPane.showInputDialog(null, "Informe a descrição do produto:"));
                                    prod.setX(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a localização x do produto:")));
                                    prod.setY(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a localização y do produto:")));
                                    prod.setZ(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a localização z do produto:")));

                                    prod.setCategoria(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a categoria do prodto: " + auxLista)));
                                    prod.insert();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Sem permissão!");
                                }
                                break;
                            case 2:
                                Produto prod;
                                if (userLogged.getTipoUsuario() > 1) {
                                    int id = Integer.parseInt(JOptionPane.showInputDialog("Informe a ID do produto que deseja alterar: "));
                                    switch (alteracaoProdutos()) {
                                        case 1:
                                            prod = new Produto();
                                            prod.setNome(JOptionPane.showInputDialog("Informe o novo nome do produto: "));
                                            prod.update(id, 1);
                                            break;
                                        case 2:
                                            prod = new Produto();
                                            prod.setNome(JOptionPane.showInputDialog("Informe a nova descrição do produto: "));
                                            prod.update(id, 2);
                                            break;
                                        case 3:
                                            prod = new Produto();
                                            prod.setCategoria(Integer.parseInt(JOptionPane.showInputDialog("Informe a nova categoria do produto: \n" + auxLista)));
                                            prod.update(id, 3);
                                            break;
                                        case 4:
                                            prod = new Produto();
                                            prod.setX(Double.parseDouble(JOptionPane.showInputDialog("Informe a nova coordenada X do produto: ")));
                                            prod.update(id, 4);
                                            break;
                                        case 5:
                                            prod = new Produto();
                                            prod.setY(Double.parseDouble(JOptionPane.showInputDialog("Informe a nova coordenada Y do produto: ")));
                                            prod.update(id, 5);
                                            break;
                                        case 6:
                                            prod = new Produto();
                                            prod.setZ(Double.parseDouble(JOptionPane.showInputDialog("Informe a nova coordenada Z do produto: ")));
                                            prod.update(id, 6);
                                        case 0:
                                            break;

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Sem permissão!");
                                }
                                break;

                            case 3:
                                prod = new Produto();
                                prod.delete(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a ID do produto que deseja excluir: ")));
                                break;
                            case 4:

                                String auxProd = "";
                                ArrayList<Produto> listProd = getAllProducts();
                                for (Produto obj : listProd) {
                                    auxProd += obj.toString();
                                }
                                JOptionPane.showMessageDialog(null, auxProd);
                                break;
                            case 5:
                                prod = new Produto();
                                prod = loadID(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a ID do produto que deseja localizar: ")));
                                JOptionPane.showMessageDialog(null, prod.toString());
                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção inválida!");
                                break;
                        }
                        break;
                    case 3:

                        break;

                    case 4:/* IMPLEMENTAR FUTURAMENTE
                         switch(Integer.parseInt(JOptionPane.showInputDialog("1 - Categorias de Usuário\n2 - Categorias de Produto\n0 - Sair"))){
                         case 1:
                         CategoriaUsuario catUser = new CategoriaUsuario();
                         catUser.setNome(JOptionPane.showInputDialog("Informe o nome da categoria: "));
                         catUser.setDescricao(JOptionPane.showInputDialog("Informe a descrição da categoria: "));
                         catUser.insert();
                         break;
                         case 2:
                         CategoriaProduto catProd = new CategoriaProduto();
                         catProd.setNome(JOptionPane.showInputDialog("Informe o nome da categoria: "));
                         catProd.setDescricao(JOptionPane.showInputDialog("Informe a descrição da categoria: "));
                         catProd.insert();
                         break;
                         case 0:
                         break;
                            
                         }*/

                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                }
            }
        }
    }

    public static int montaMenu() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "        Menu\n"
                + "1 - Usuário\n"
                + "2 - Produto\n"
                + "0 - Sair\n"));
        return opcao;
    }

    public static int montaMenuUsuario() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "         Menu Usuário\n"
                + "1 - Cadastro\n"
                + "2 - Alteração de dados\n"
                + "3 - Exclusão de usuário\n"
                + "4 - Listar todos\n"
                + "5 - Listar especifico\n"
                + "0 - Sair"));
        return opcao;
    }

    public static int alteracaoUsuario() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "      Alteração de dados\n"
                + "       Escolha o que deseja alterar:\n"
                + "1 - Nome\n"
                + "2 - Senha\n"
                + "3 - Tipo\n"
                + "0 - Sair"));
        return opcao;
    }

    public static int montaMenuProduto() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "        Menu Produto\n"
                + "1 - Cadastro\n"
                + "2 - Alteração de dados\n"
                + "3 - Exclusão de produto\n"
                + "4 - Listar todos\n"
                + "5 - Listar especifico\n"
                + "0 - Sair"));
        return opcao;
    }

    public static int alteracaoProdutos() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "      Alteração de dados\n"
                + "       Escolha o que deseja alterar:\n"
                + "1-Nome\n"
                + "2-Descrição\n"
                + "3-Categoria\n"
                + "4-Coordenada X\n"
                + "5-Coordenada Y\n"
                + "6-Coordenada Z\n"
                + "0-Sair"));
        return opcao;
    }

    public static int menuAutenticado() {
        return 1;
    }

}
