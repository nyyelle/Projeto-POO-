import java.text.DecimalFormat;

public class Produto implements Descontavel {
    private String nome;
    private String categoria;
    private Fornecedor fornecedor;
    private double preco;
    private int quantidadeEmEstoque;
    private String descricao;

    public Produto(String nome, String categoria, Fornecedor fornecedor, double preco, int quantidadeEmEstoque, String descricao) {
        this.nome = nome;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.descricao = descricao;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public double aplicarDesconto(double percentual) {
        return preco - (preco * percentual / 100);
    }

    public void exibirInfo() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Produto: " + nome + "\nCategoria: " + categoria + "\nPreço: R$" + df.format(preco) + "\nQuantidade em estoque: " + quantidadeEmEstoque + "\nDescrição: " + descricao);
        System.out.println("--------------------------------");
    }
}
