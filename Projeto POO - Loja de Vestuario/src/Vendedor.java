public class Vendedor extends Usuario {
    public Vendedor(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Vendedor: " + getNome() + "\nEmail: " + getEmail());
    }
    
}
