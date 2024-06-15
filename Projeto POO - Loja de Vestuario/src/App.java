import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        SistemaControle sistema = new SistemaControle();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean running = true;

        while (running) {
            System.out.println("\n----- Sistema de Controle de Estoque e Vendas -----");
            System.out.println("| 1. Adicionar novo usuário     |");
            System.out.println("| 2. Adicionar novo produto     |");
            System.out.println("| 3. Registrar venda            |");
            System.out.println("| 4. Ofertar desconto           |");
            System.out.println("| 5. Pesquisar produto          |");
            System.out.println("| 6. Gerar relatório de vendas  |");
            System.out.println("| 7. Gerar relatório de estoque |");
            System.out.println("| 8. Sair                       |");
            System.out.println("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    adicionarUsuario(sistema, scanner);
                    break;
                case 2:
                    adicionarProduto(sistema, scanner);
                    break;
                case 3:
                    registrarVenda(sistema, scanner);
                    break;
                case 4:
                    ofertarDesconto(sistema, scanner);
                    break;
                case 5:
                    pesquisarProduto(sistema, scanner);
                    break;
                case 6:
                    sistema.gerarRelatorioVendas();
                    break;
                case 7:
                    sistema.gerarRelatorioEstoque();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void adicionarUsuario(SistemaControle sistema, Scanner scanner) {
        System.out.println("\n Adicionar novo usuário: ");
        System.out.println("| 1. Gerente    |");
        System.out.println("| 2. Vendedor   |");
        System.out.println("| 3. Caixa      |");
        System.out.println("| 4. Cliente    |");
        System.out.println("| 5. Fornecedor |");
        System.out.println("Escolha o tipo de usuário: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = null;
        switch (tipo) {
            case 1:
                usuario = new Gerente(nome, email);
                break;
            case 2:
                usuario = new Vendedor(nome, email);
                break;
            case 3:
                usuario = new Caixa(nome, email);
                break;
            case 4:
                usuario = new Cliente(nome, email);
                break;
            case 5:
                usuario = new Fornecedor(nome, email);
                break;
            default:
                System.out.println("Tipo de usuário inválido!");
                return;
        }

        sistema.adicionarUsuario(usuario);
        System.out.println("Usuário adicionado com sucesso!");
    }

    private static void adicionarProduto(SistemaControle sistema, Scanner scanner) {
        System.out.println("\nAdicionar novo produto:");
        System.out.print("| Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("| Categoria (Ex.: camisetas, calças, vestidos, saias, etc.): ");
        String categoria = scanner.nextLine();
        System.out.print("| Fornecedor: ");
        String nomeFornecedor = scanner.nextLine();
        System.out.print("| Preço: R$");
        String precoInput = scanner.nextLine();

        double preco;
        try {
            preco = Double.parseDouble(precoInput.replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Formato de preço inválido!");
            return;
        }

        System.out.print("| Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("| Descrição do produto: ");
        String descricao = scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor(nomeFornecedor, nomeFornecedor + "@example.com");
        Produto produto = new Produto(nome, categoria, fornecedor, preco, quantidade, descricao);

        sistema.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    private static void registrarVenda(SistemaControle sistema, Scanner scanner) {
        System.out.println("\nRegistrar venda:");
        List<Produto> produtosVendidos = new ArrayList<>();
        double valorTotal = 0;

        while (true) {
            System.out.print("| Nome do produto: ");
            String nomeProduto = scanner.nextLine();
            Produto produto = sistema.buscarProdutoPorNome(nomeProduto);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("| Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (quantidade > produto.getQuantidadeEmEstoque()) {
                System.out.println("Quantidade em estoque insuficiente!");
                continue;
            }

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
            produtosVendidos.add(produto);
            valorTotal += produto.getPreco() * quantidade;

            System.out.print("Deseja adicionar mais produtos? (sim/nao): ");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("sim")) {
                break;
            }
        }

        System.out.print("| Nome do vendedor: ");
        String nomeVendedor = scanner.nextLine();
        Vendedor vendedor = null;
        for (Usuario u : sistema.getUsuarios()) {
            if (u instanceof Vendedor && u.getNome().equalsIgnoreCase(nomeVendedor)) {
                vendedor = (Vendedor) u;
                break;
            }
        }

        if (vendedor == null) {
            System.out.println("Vendedor não encontrado!");
            return;
        }

        System.out.print("| Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = null;
        for (Usuario u : sistema.getUsuarios()) {
            if (u instanceof Cliente && u.getNome().equalsIgnoreCase(nomeCliente)) {
                cliente = (Cliente) u;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        try {
            Venda venda = new Venda(new Date(), produtosVendidos, vendedor, cliente, valorTotal);
            sistema.registrarVenda(venda);
            System.out.println("Venda registrada com sucesso!");
        } catch (ValorVendaInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void ofertarDesconto(SistemaControle sistema, Scanner scanner) {
        System.out.println("\nOfertar desconto:");
        System.out.print("| Nome do produto: ");
        String nomeProduto = scanner.nextLine();
        Produto produto = sistema.buscarProdutoPorNome(nomeProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("| Percentual de desconto (0-100): ");
        double percentualDesconto = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        if (percentualDesconto < 0 || percentualDesconto > 100) {
            System.out.println("Percentual de desconto inválido!");
            return;
        }

        double precoComDesconto = produto.getPreco() * (1 - percentualDesconto / 100);
        BigDecimal bd = new BigDecimal(precoComDesconto).setScale(2, RoundingMode.HALF_UP);
        precoComDesconto = bd.doubleValue();

        produto.setPreco(precoComDesconto);
        System.out.println("Desconto aplicado com sucesso! Novo preço: R$" + precoComDesconto);
    }

    private static void pesquisarProduto(SistemaControle sistema, Scanner scanner) {
        System.out.println("\nPesquisar produto:");
        System.out.print("| Nome do produto: ");
        String nomeProduto = scanner.nextLine().trim();
        Produto produto = sistema.buscarProdutoPorNome(nomeProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
        } else {
            produto.exibirInfo();
        }
    }
}
