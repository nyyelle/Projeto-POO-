public class Cliente extends Usuario{
    public Cliente(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Cliente: " + getNome() + "\nEmail: " + getEmail());
    }
}
