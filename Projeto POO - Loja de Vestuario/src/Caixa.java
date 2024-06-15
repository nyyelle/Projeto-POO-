public class Caixa extends Usuario {
    public Caixa(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Caixa: " + getNome() + "\nEmail: " + getEmail());
    }
}
