package myTwitter;

import Exceções.UNCException;
import Exceções.UJCException;
import java.util.Vector;

public interface IRepositorioUsuario {
    public Vector<String> usuarios = new Vector();
    public Vector <Perfil> getPerfis();
    public void cadastrar(Perfil usuario) throws UJCException;
    public Perfil buscar(String usuario);
    public void Atualizar(Perfil usuario) throws UNCException;
}
