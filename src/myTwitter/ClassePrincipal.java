package myTwitter;
public class ClassePrincipal {
    public static void main(String[] args) {
        IRepositorioUsuario repositorio = Repositorio.getInstance();
        MyTwitter myTwitter = new MyTwitter(repositorio);
        MenuPrincipal menu = new MenuPrincipal(myTwitter);
        menu.menuPrincipal();
    }
    
}
