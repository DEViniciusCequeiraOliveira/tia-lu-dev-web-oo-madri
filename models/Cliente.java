public class Cliente {
    private int codigo;
    private String nome;
    private String telefone;

    public Cliente() {

    }

    public Cliente(int codigo, String nome, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String toString() {
        return String.format("Código: %d | Nome: %s | Telefone: %s", codigo, nome, telefone);
    }

}