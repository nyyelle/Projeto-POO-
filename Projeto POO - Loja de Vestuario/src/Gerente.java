public class Gerente extends Usuario {
    public Gerente(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Gerente: " + getNome() + "\nEmail: " + getEmail());
    }

}
