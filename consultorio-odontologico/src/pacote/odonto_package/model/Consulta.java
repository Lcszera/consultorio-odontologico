package pacote.odonto_package.model;

public class Consulta {

    private int id;
    private String nomePaciente;
    private String data;
    private String hora;
    private String procedimento;
    private String status;

    public Consulta(int id, String nomePaciente, String data, String hora, String procedimento, String status) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.data = data;
        this.hora = hora;
        this.procedimento = procedimento;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getProcedimento() { return procedimento; }
    public void setProcedimento(String procedimento) { this.procedimento = procedimento; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}