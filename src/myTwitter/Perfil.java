package myTwitter;
import java.util.Vector;

public abstract class Perfil {
    private String usuario;
    private Vector<String> seguidores;
    private Vector<String> quemEuSigo;
    private Vector<Tweet> timeline;
    private Vector<Tweet> tweets;
    private boolean ativo;
    
    public Perfil(String usuario){
        this.usuario = usuario;
        this.ativo = true;
        this.seguidores = new Vector();
        this.quemEuSigo = new Vector();
        this.timeline = new Vector();
        this.tweets = new Vector();
    }
    public void addSeguidor(String usuario){
        this.seguidores.add(usuario);
    }
    public void addQuemEuSigo(String usuario){
        this.quemEuSigo.add(usuario);
    }

    public Vector<String> getQuemEuSigo() {
        return quemEuSigo;
    }

    public void setQuemEuSigo(Vector<String> quemEuSigo) {
        this.quemEuSigo = quemEuSigo;
    }
    
    public void addTweet(Tweet tweet){
        this.timeline.add(tweet);
    }
    
    public void setTweets(Tweet tweet){
        this.tweets.add(tweet);
    }
    
    public Vector<Tweet> getTweets(){
        return this.tweets;
    }
    
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Vector<String> getSeguidores() {
        return this.seguidores;
    }

    public Vector<Tweet> getTimeline() {
        return this.timeline;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}