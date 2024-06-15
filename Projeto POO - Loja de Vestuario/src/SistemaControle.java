import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SistemaControle {
    private List<Usuario> usuarios;
    private List<Produto> produtos;
    private List<Venda> vendas;

    public SistemaControle() {
        this.usuarios = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProdutoPorNome(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome.trim())) {
                return produto;
            }
        }
        return null;
    }

    public void registrarVenda(Venda venda) {
        vendas.add(venda);
    }

    public void gerarRelatorioVendas() {
        System.out.println("\nRelatório de Vendas:");
        for (Venda venda : vendas) {
            System.out.println("Data: " + venda.getData());
            for (Produto produto : venda.getProdutos()) {
                produto.exibirInfo();
            }
            System.out.println("Vendedor: " + venda.getVendedor().getNome());
            System.out.println("Cliente: " + venda.getCliente().getNome());

            // Formatar o valor total para duas casas decimais
            BigDecimal valorTotalFormatado = new BigDecimal(venda.getValorTotal()).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Valor Total: R$" + valorTotalFormatado);
            System.out.println("--------------------------------");

            // Adicionar uma linha em branco após cada venda
            System.out.println();
        }
    }

    public void gerarRelatorioEstoque() {
        System.out.println("\nRelatório de Estoque:");
        for (Produto produto : produtos) {
            produto.exibirInfo();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
