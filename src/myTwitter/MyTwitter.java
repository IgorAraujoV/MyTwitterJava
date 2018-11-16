package myTwitter;
import Exceções.SIException;
import Exceções.PNAException;
import Exceções.MFPException;
import Exceções.UJCException;
import Exceções.PEException;
import Exceções.PIException;
import java.util.Scanner;
import java.util.Vector;

public class MyTwitter implements ITwitter {
    private IRepositorioUsuario repositorio;
    Arquivo arquivo = new Arquivo();
    public MyTwitter(IRepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }

    public IRepositorioUsuario getRepositorio() {
        return repositorio;
    }
    
    @Override
    public void criarPerfil(Perfil usuario) throws PEException { 
        String nomeUsuario = usuario.getUsuario();
        try {
            this.repositorio.cadastrar(usuario);
        }catch (UJCException ex) {
            System.out.println("Usuario: "+ex.getUsuario()+", já cadastrado.");
            throw new PEException(nomeUsuario);
        }
        
            
    }

    @Override
    public void cancelarPerfil(String usuario) throws PNAException, PIException{
        Perfil cancelarPerfil = this.repositorio.buscar(usuario);
        if(cancelarPerfil != null){
            if(cancelarPerfil.isAtivo()){
                cancelarPerfil.setAtivo(false);
            }else{
                throw new PNAException(usuario);
            }
        }else{
            throw new PIException(usuario);
        }
    }

    @Override
    public void tweetar(String usuario, String mensagem) throws MFPException, PNAException, PIException{
        Perfil perfil = this.repositorio.buscar(usuario);
        Tweet tweet = new Tweet(usuario, mensagem);
        if(perfil != null){
            if(perfil.isAtivo()){
                if(mensagem.length() > 0 && mensagem.length() < 140){
                    perfil.addTweet(tweet);
                    perfil.setTweets(tweet);
                    for(int i=0;i<perfil.getSeguidores().size();i++){
                        this.repositorio.buscar(perfil.getSeguidores().get(i)).addTweet(tweet);
                    }
                }else
                    throw new MFPException(usuario);
            }else
                throw new PNAException(usuario);
        }else{
            throw new PIException(usuario);
        }
    }

    public Vector<Tweet> timeline(String usuario) throws PIException, PNAException {
        Perfil perfil = repositorio.buscar(usuario);
        if(perfil != null){
            if(perfil.isAtivo()){
                return perfil.getTimeline();
            }else
                throw new PNAException(usuario);
        }else
            throw new PIException(usuario);       
    }

    @Override
    public Vector<Tweet> tweets(String usuario) throws PIException, PNAException{
         Perfil perfil = this.repositorio.buscar(usuario);
         if(perfil != null){
             if(perfil.isAtivo()){
                 return perfil.getTweets();
             }else
                 throw new PNAException(usuario);
         }else
             throw new PIException(usuario);
         
    }

    @Override
    public void seguir(String seg, String seguid) throws PNAException, PIException, SIException{
        Perfil seguidor = this.repositorio.buscar(seg);
        Perfil seguido = this.repositorio.buscar(seguid);
        if(seguidor == null)
            throw new PIException(seg);
        if(seguido == null)
            throw new PIException(seguid);
            
        if(seguidor.isAtivo() && seguido.isAtivo()){
            if(seguidor.getUsuario().equals(seguid))
                throw new SIException(seg);
            else if(!seguido.getSeguidores().contains(seg)){
                seguido.addSeguidor(seg);
                seguidor.addQuemEuSigo(seguid);
            }else
                throw new SIException(seg);
        }else if(!seguidor.isAtivo())
                throw new PNAException(seguid);
              else
                throw new PNAException(seg);           
    }
    
    @Override
    public int numeroSeguidores(String usuario) throws PNAException, PIException{
        Perfil perfil = this.repositorio.buscar(usuario);
        int numSeguidores = 0;
        if(perfil != null){
            if(perfil.isAtivo()){
                for (int i=0;i<perfil.getSeguidores().size();i++) {
                    Perfil testePerfil = this.repositorio.buscar(perfil.getSeguidores().get(i));
                    if(testePerfil != null && testePerfil.isAtivo())
                        numSeguidores++;
                }
                return numSeguidores;
            }else
                throw new PNAException(usuario);
        }else 
            throw new PIException(usuario);
        
    }

    
    public Vector<Perfil> quemEuSigo(String usuario) throws PNAException, PIException{
        Perfil perfil = this.repositorio.buscar(usuario);
        Vector <Perfil> seguidores = new Vector();
        if(perfil != null){
            if(perfil.isAtivo()){
                for (int i=0;i<perfil.getQuemEuSigo().size();i++) {
                    Perfil testePerfil = this.repositorio.buscar(perfil.getQuemEuSigo().get(i));
                    if(testePerfil != null && testePerfil.isAtivo())
                        seguidores.add(testePerfil);
                }
                
                return seguidores;
            }else 
                throw new PNAException(usuario);
        }else
            throw new PIException(usuario);     
    }
    
    public Vector<Perfil> seguidores(String usuario) throws PNAException, PIException{
        Perfil perfil = this.repositorio.buscar(usuario);
        Vector <Perfil> seguidores = new Vector();
        if(perfil != null){
            if(perfil.isAtivo()){
                for (int i=0;i<perfil.getSeguidores().size();i++) {
                    Perfil testePerfil = this.repositorio.buscar(perfil.getSeguidores().get(i));
                    if(testePerfil != null && testePerfil.isAtivo())
                        seguidores.add(testePerfil);
                }
                
                return seguidores;
            }else 
                throw new PNAException(usuario);
        }else
            throw new PIException(usuario);     
    }
    
}