import java.util.Date;
import java.util.List;

public class Venda {
    private Date data;
    private List<Produto> produtos;
    private Vendedor vendedor;
    private Cliente cliente;
    private double valorTotal;

    public Venda(Date data, List<Produto> produtos, Vendedor vendedor, Cliente cliente, double valorTotal) throws ValorVendaInvalidoException {
        if (valorTotal <= 0) {
            throw new ValorVendaInvalidoException("O valor total da venda deve ser maior que zero.");
        }
        this.data = data;
        this.produtos = produtos;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Date getData() {
        return data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void exibirInfo() {
        System.out.println("Data: " + data);
        System.out.println("Produtos: ");
        for (Produto produto : produtos) {
            produto.exibirInfo();
        }
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("--------------------------------");
    }
}
