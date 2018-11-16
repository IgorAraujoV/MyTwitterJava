package myTwitter;
import Exceções.SIException;
import Exceções.PNAException;
import Exceções.MFPException;
import Exceções.PEException;
import Exceções.PIException;
import java.util.Vector;

public interface ITwitter {
    public void criarPerfil(Perfil usuario) throws PEException;
    public void cancelarPerfil(String usuario) throws PNAException, PIException;
    public void tweetar(String usuario, String mensagem) throws MFPException, PNAException, PIException;
    public Vector<Tweet> timeline(String usuario) throws PIException, PNAException;
    public Vector<Tweet> tweets(String usuario) throws PIException, PNAException;
    public void seguir(String seguidor, String seguido) throws PNAException, PIException, SIException;
    public int numeroSeguidores(String usuario) throws PNAException, PIException;
    public Vector<Perfil> seguidores(String usuario) throws PNAException, PIException;
}
