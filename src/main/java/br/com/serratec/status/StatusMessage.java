package br.com.serratec.status;

public class StatusMessage {
    
    private Integer status;
    private String titulo;
    private String mensagem;
    
    public StatusMessage(Integer status, String titulo, String mensagem) {
        this.status = status;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
