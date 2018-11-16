package Exceções;
public class SIException extends Exception{
    private String usuario;
    
    public SIException(String usuario){
        super("Seguidor Inválido.");
        this.usuario = usuario;
    }
    public String getUsuario(){
        return this.usuario;
    }
    
}
