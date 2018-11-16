package Exceções;
public class PEException extends Exception{
    private String usuario;
     
    public PEException (String usuario){
        super("Perfil já existe.");
        this.usuario = usuario;
    }
 
    public String getUsuario() {
        return usuario;
    }
}
