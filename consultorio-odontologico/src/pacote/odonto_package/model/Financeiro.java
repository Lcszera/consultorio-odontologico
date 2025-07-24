package pacote.odonto_package.model;

public class Financeiro {
    private int id;
    private String descricao;
    private double valor;
    private String tipo;
    private String data;

    public Financeiro(int id, String descricao, double valor, String tipo, String data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}