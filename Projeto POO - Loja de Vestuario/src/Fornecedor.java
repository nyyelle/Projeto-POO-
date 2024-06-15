public class Fornecedor extends Usuario{
    public Fornecedor(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Fornecedor: " + getNome() + "\nEmail: " + getEmail());
    }
}
