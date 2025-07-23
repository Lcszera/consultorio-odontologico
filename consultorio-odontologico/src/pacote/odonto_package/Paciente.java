package pacote.odonto_package;

public class Paciente {
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;

    public Paciente(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }


    // Método para exibição em listas (continua o mesmo)
    @Override
    public String toString() {
        return this.nome;
    }
}