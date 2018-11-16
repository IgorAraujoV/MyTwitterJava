package Exceções;
public class MFPException extends Exception{
    private String usuario;
    
    public MFPException(String usuario){
        super("Tweet fora do padrão, mensagem não está entre 1 e 140 caracteres.");
        this.usuario = usuario;
    }
    public String getUsuario(){
        return this.usuario;
    }
}
