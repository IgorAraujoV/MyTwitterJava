package Exceções;
public class PIException extends Exception {
    String usuario;
    
    public PIException(String usuario){
        super("Perfil inexistente.");
        this.usuario = usuario;
    }
    public String getUsuario(){
        return this.usuario;
    }
}
