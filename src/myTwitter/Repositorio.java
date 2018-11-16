package myTwitter;
import Exceções.UNCException;
import Exceções.UJCException;
import java.util.Vector;
import java.util.Scanner;


public class Repositorio implements IRepositorioUsuario{

    Vector<Perfil> perfis = new Vector();
    private static Repositorio getInstance = null;
    
    public Repositorio(){}
    
    public static Repositorio getInstance(){
       if(getInstance == null)
           getInstance = new Repositorio();
        
       return getInstance;
   }
    
    @Override
    public void cadastrar(Perfil usuario) throws UJCException{
        String usuarioPerfil = usuario.getUsuario();
        perfis.add(usuario);
        for(int i=1; i<perfis.size();i++)
            if(perfis.get(i-1).getUsuario().equals(usuarioPerfil)){
                perfis.remove(i-1);
                throw new UJCException(usuarioPerfil);
            }
    }
    
    @Override
    public Vector<Perfil> getPerfis() {
        return perfis;
    }
    
    @Override
    public Perfil buscar(String usuario) {
        for(int i=0; i<perfis.size();i++){
            if(perfis.get(i).getUsuario().equals(usuario)){
                    return perfis.get(i); 
            }
        }
        return null;      
    }

    @Override
    public void Atualizar(Perfil usuario) throws UNCException {
        Scanner input = new Scanner(System.in);
        Perfil perfilAtualizar = usuario;
        PessoaFisica pessoaFisica;
        PessoaJuridica pessoaJuridica;
        String nCpf, nCnpj, nEstado, nUsuario, nPerfil;
        String u = "@";
        
        if(this.buscar(perfilAtualizar.getUsuario()) != null){
            if(perfilAtualizar instanceof PessoaFisica){
                pessoaFisica = (PessoaFisica) perfilAtualizar;
                System.out.print("Digite o novo usuario: @");
                nUsuario = u + input.nextLine();
                Perfil nperfil, nperfil1;
                for(int i=0; i<pessoaFisica.getQuemEuSigo().size();i++){
                    nperfil = buscar(pessoaFisica.getQuemEuSigo().get(i));
                    nperfil.getSeguidores().add(perfis.indexOf(perfis.get(perfis.indexOf(usuario))), nUsuario);
                }
                for(int i=0; i<pessoaFisica.getSeguidores().size();i++){
                    nperfil1 = buscar(pessoaFisica.getSeguidores().get(i));
                    nperfil1.getQuemEuSigo().add(perfis.indexOf(perfis.get(perfis.indexOf(usuario))), nUsuario);
                }if( nUsuario != null || !nUsuario.equals(u))
                    pessoaFisica.setUsuario(nUsuario);
                System.out.print("Digite o estado, pode ser Ativo ou Inativo: ");
                nEstado = input.nextLine();
                if(nEstado.equalsIgnoreCase("Ativo")){
                    pessoaFisica.setAtivo(true);
                }else
                    pessoaFisica.setAtivo(false);

                int i=0;
                System.out.print("Digite o novo CPF: ");
                while(i == 0){
                    nCpf = input.nextLine();
                    if(pessoaFisica.verificarCPF(nCpf))
                        i++;
                    pessoaFisica.setCpf(nCpf);
                }        
            }else if(perfilAtualizar instanceof PessoaJuridica){
                pessoaJuridica = (PessoaJuridica) perfilAtualizar;
                System.out.print("Digite o novo usuario: @");
                nUsuario = u + input.nextLine();
                Perfil nperfil, nperfil1;
                for(int i=0; i<pessoaJuridica.getQuemEuSigo().size();i++){
                    nperfil = buscar(pessoaJuridica.getQuemEuSigo().get(i));
                    nperfil.getSeguidores().add(perfis.indexOf(perfis.get(perfis.indexOf(usuario))), nUsuario);
                }
                for(int i=0; i<pessoaJuridica.getSeguidores().size();i++){
                    nperfil1 = buscar(pessoaJuridica.getSeguidores().get(i));
                    nperfil1.getQuemEuSigo().add(perfis.indexOf(perfis.get(perfis.indexOf(usuario))), nUsuario);
                }
                pessoaJuridica.setUsuario(nUsuario);   
                 
                System.out.print("Digite o estado, pode ser Ativo ou Inativo: ");
                nEstado = input.nextLine();
                if(nEstado.equalsIgnoreCase("Ativo")){
                    pessoaJuridica.setAtivo(true);
                }else
                    pessoaJuridica.setAtivo(false);                 

                int i=0;
                System.out.print("Digite o novo CNPJ: ");
                while(i == 0){
                    nCnpj = input.nextLine();
                    if(pessoaJuridica.verificarCNPJ(nCnpj))
                        i++;
                    pessoaJuridica.setCNPJ(nCnpj);
                } 
            }else{
                System.out.println("Tipo de perfil inválido.");
            }
        }else{
            throw new UNCException(perfilAtualizar.getUsuario());
        }            
    }
}