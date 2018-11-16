package Exceções;
public class PNAException extends Exception{
    private String usuario;
    
    public PNAException(String usuario){
        super("Perfil não ativo.");
        this.usuario = usuario;
    }
    public String getUsuario(){
        return this.usuario;
    }
}
