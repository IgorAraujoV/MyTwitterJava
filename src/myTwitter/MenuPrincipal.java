package myTwitter;
import Exceções.SIException;
import Exceções.PNAException;
import Exceções.MFPException;
import Exceções.UNCException;
import Exceções.PEException;
import Exceções.PIException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class MenuPrincipal {
    private MyTwitter myTwitter;
    Arquivo arquivo = new Arquivo();
    public MenuPrincipal(MyTwitter myTwitter){
        this.myTwitter = myTwitter;
    }
    
    public void menuPrincipal(){
        
        Scanner input = new Scanner(System.in);
      
        System.out.println("\n------------------------------");
        System.out.println("          MyTwitter");
        System.out.println("------------------------------");
        System.out.println("<1> Cadastrar \n<2> Buscar \n<3> Atualizar \n<4> Perfis Ativos \n<5> Ir para o Twitter\n<6> Sair");
        
        System.out.print("\nDigite a opção: ");
        try{
            int escolha = input.nextInt();
            System.out.println("");
            if(escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4 || escolha == 5 || escolha == 6){
                switch(escolha){
                    case 1:
                        System.out.println("<1> Cadastro \n");
                        this.cadastrar();
                        break;
                    case 2:
                        System.out.println("<2> Busca\n");
                        try {
                            this.buscar();
                        } catch (PIException ex) {
                            System.out.println("Perfil Inexistente.");
                            this.menuPrincipal();
                        }              
                        break;                                
                    case 3:
                        System.out.println("<3> Atualizar\n");
                        this.atualizar();
                        break;
                    case 4:
                        System.out.println("<4> Ver Perfis Ativos\n");
                        this.verPerfis();
                        this.menuPrincipal();
                        break;
                    case 5:
                        this.Twitter();
                        break;
                    case 6:
                        arquivo.excluirArquivo();
                        break;
                }
            }else{
                System.out.println("Digito inválido");
                this.menuPrincipal();
            }
        }catch(InputMismatchException ex){
            System.out.println("\nDigito inválido.");
            this.menuPrincipal();
        }
        
            
        
    }
    public void cadastrar(){
        PessoaFisica pessoaFisica = new PessoaFisica("nome");
        PessoaJuridica pessoaJuridica = new PessoaJuridica("nome");
        Scanner input = new Scanner(System.in);
        String u = "@";
        String tipoPerfil, nomeUsuario, estado, CPF, CNPJ;
        
        System.out.print("Digite o nome de usuário: @");
        nomeUsuario = input.nextLine();
        nomeUsuario = u + nomeUsuario;
        
        System.out.print("Digite tipo de perfil, Pessoa Fisica ou Juridica:  ");
        tipoPerfil = input.nextLine();
        if(tipoPerfil.equalsIgnoreCase("Pessoa Fisica") || tipoPerfil.equalsIgnoreCase("Fisica")){
            int i=0;
            System.out.print("Digite o CPF:  ");
            while(i == 0){
                CPF = input.nextLine();
                if(pessoaFisica.verificarCPF(CPF)){
                    pessoaFisica.setCpf(CPF);
                    i++; 
                }else
                    System.out.print("Inválido, digite novamente: ");
            }
            pessoaFisica.setUsuario(nomeUsuario);
            try {
                myTwitter.criarPerfil(pessoaFisica);
                System.out.println("\nCadastrado com sucesso!");
                arquivo.gerarArquivo(pessoaFisica, "foi cadastrado com sucesso!");
                this.menuPrincipal();
            } catch (PEException ex) {
                System.out.println("Perfil: "+ex.getUsuario()+", já existe.");
                this.menuPrincipal();
            }
        }    
        else if(tipoPerfil.equalsIgnoreCase("Pessoa Juridica") || tipoPerfil.equalsIgnoreCase("Juridica")){
            
            int i=0;
            System.out.print("Digite o CNPJ:  ");
            while(i == 0){
                CNPJ = input.nextLine();
                if(pessoaJuridica.verificarCNPJ(CNPJ)){
                    i++;
                    pessoaJuridica.setCNPJ(CNPJ);
                }else
                    System.out.print("Inválido, digite novamente: ");
            }
            pessoaJuridica.setUsuario(nomeUsuario);
            try {
                myTwitter.criarPerfil(pessoaJuridica);
                System.out.println("\nCadastrado com sucesso!");
                arquivo.gerarArquivo(pessoaJuridica, "foi cadastrado com sucesso!");
                this.menuPrincipal();
            } catch (PEException ex) {
                System.out.println("Perfil: "+ex.getUsuario()+", já existe.");
                this.menuPrincipal();
            }
       }else{
            System.out.println("Tipo de perfil inválido.");
            this.cadastrar();
        }
    }
    public void buscar() throws PIException{
        Scanner in = new Scanner(System.in);
        PessoaFisica pessoaFisica;
        PessoaJuridica pessoaJuridica;
                 
        System.out.print("Digite o nome do usuario a ser buscado: @");
        String buscarUsuario ="@" + in.nextLine();
         
        if(this.myTwitter.getRepositorio().buscar(buscarUsuario) instanceof PessoaFisica){                                                         
            pessoaFisica = (PessoaFisica) myTwitter.getRepositorio().buscar(buscarUsuario);
            System.out.println("Usuario: " + pessoaFisica.getUsuario());
            System.out.println("Tipo de Perfil: Pessoa Fisica");
            if(pessoaFisica.isAtivo())
                System.out.println("Estado: Ativo");
            else
                System.out.println("Estado: Inativo");
            System.out.print("CPF: ");
            pessoaFisica.printaCPF(pessoaFisica.getCpf());
            this.menuPrincipal();
        }else if(this.myTwitter.getRepositorio().buscar(buscarUsuario) instanceof PessoaJuridica){
            pessoaJuridica = (PessoaJuridica) myTwitter.getRepositorio().buscar(buscarUsuario);
            System.out.println("Usuario: " + pessoaJuridica.getUsuario());
            System.out.println("Tipo de Perfil: Pessoa Juridica");
            if(pessoaJuridica.isAtivo())
                System.out.println("Estado: Ativo");
            else
                System.out.println("Estado: Inativo");
            System.out.print("CNPJ: ");
            pessoaJuridica.printaCNPJ(pessoaJuridica.getCNPJ());
            this.menuPrincipal();
        }else
            throw new PIException(buscarUsuario);            }
    public void atualizar(){
        Scanner input = new Scanner(System.in);
        Perfil perfilAtualizar;
        String u = "@", nomeUsuario;
        
        System.out.print("Digite o usuario a ser atualizado: @");
        nomeUsuario = input.nextLine();
        nomeUsuario = u + nomeUsuario;
        
        perfilAtualizar = myTwitter.getRepositorio().buscar(nomeUsuario);
        if(perfilAtualizar != null){
            try {
                myTwitter.getRepositorio().Atualizar(perfilAtualizar);
                System.out.println("Perfil: "+nomeUsuario+", atualizado com sucesso. ");
                arquivo.gerarArquivo(perfilAtualizar, "atualizado de "+nomeUsuario+" para "+perfilAtualizar.getUsuario());
                this.menuPrincipal();
            } catch (UNCException ex) {
                System.out.println("Perfil: "+ex.getUsuario()+", não cadastrado.");
                this.menuPrincipal();
            }
        }else{
            System.out.println("Perfil: "+nomeUsuario+", inexistente.");
            this.menuPrincipal();
        }
    }
    public void verPerfis(){
        Vector<Perfil> perfis = myTwitter.getRepositorio().getPerfis();
        Vector<Perfil> perfisAtivos = new Vector();
        PessoaFisica pessoaFisica;
        PessoaJuridica pessoaJuridica;
        for(int i=0; i<perfis.size();i++)
            if(perfis.get(i).isAtivo())
                perfisAtivos.add(perfis.get(i));
        if(perfisAtivos.isEmpty()){
            System.out.println("Nenhum perfil ativo, cadastre ou atualize um perfil.");
            this.menuPrincipal();
        }        
        for(int i=0;i<perfisAtivos.size();i++){
            if(perfisAtivos.get(i) instanceof PessoaFisica){
                pessoaFisica = (PessoaFisica)perfisAtivos.get(i);
                System.out.print("Usuario | Tipo de Perfil | CPF:  "+ perfisAtivos.get(i).getUsuario()+"| Pessoa Física |  ");
                pessoaFisica.printaCPF(pessoaFisica.getCpf());
            }else if(perfisAtivos.get(i) instanceof PessoaJuridica){
                pessoaJuridica = (PessoaJuridica)perfisAtivos.get(i);
                System.out.print("Usuario | Tipo de Perfil | CNPJ: "+perfisAtivos.get(i).getUsuario()+" | Pessoa Jurídica |  ");
                pessoaJuridica.printaCNPJ(pessoaJuridica.getCNPJ());
            }             
        }System.out.println("");
    }
    public void Twitter(){
        Scanner input = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Scanner inn = new Scanner(System.in);
        Perfil perfil;
        Vector<Tweet> tweetsTimeline = new Vector();
        Vector<Tweet> tweets = new Vector();
        Vector<Perfil> seguidores = new Vector();
        int escolha;
        String u="@", usuarioUsado, mensagem, seguidor, seguido;
        
        System.out.println("\nPerfis Ativos: \n");
        this.verPerfis();
        
        System.out.print("\nDigite o perfil que desaja usar: @");
        usuarioUsado = input.nextLine();
        usuarioUsado = u + usuarioUsado;
        
        perfil = myTwitter.getRepositorio().buscar(usuarioUsado);
        if(perfil != null){
            try{
                System.out.println("\nUsuário: " + usuarioUsado);
                System.out.println("\n<0> Cancelar Perfil\n<1> Tweetar\n<2> Tweets\n<3> Timeline\n"
                    + "<4> Seguir\n<5> Seguidores\n<6> Quem eu sigo\n<7> Ver numero de seguidores\n<8> Escolher outro perfil para usar\n<9> Voltar ao Menu ");
            
                System.out.print("Digite uma opção: ");
                escolha = input.nextInt();
                System.out.println("");
                if(escolha == 0 || escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4 || escolha == 5 || escolha == 6 || escolha == 7 || escolha == 8 || escolha == 9){
                    switch(escolha){
                        case 0:
                            try {
                                myTwitter.cancelarPerfil(usuarioUsado);
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                                this.menuPrincipal();
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.menuPrincipal();
                            }

                            System.out.println("Perfil: "+usuarioUsado+" cancelado com sucesso!");
                            arquivo.gerarArquivo(perfil, "foi cancelado.");
                            this.menuPrincipal();
                            break;
                        case 1:
                            System.out.print("Digite o que deseja tweetar: ");
                            mensagem = in.nextLine();

                            try {
                                myTwitter.tweetar(usuarioUsado, mensagem);
                            } catch (MFPException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", tentou postar um tweet fora do padrão.");
                                this.Twitter();
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                                this.Twitter();
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.Twitter();
                            }

                            System.out.println("Tweetado com sucesso!");
                            arquivo.gerarArquivo(perfil, "tweetou: "+mensagem);
                            this.Twitter();
                            break;
                        case 2:          
                            try {
                                tweets = myTwitter.tweets(usuarioUsado);
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.Twitter();
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                                this.Twitter();
                            }

                            if(!tweets.isEmpty()){
                                System.out.println("Tweets de "+usuarioUsado+"\n");
                                for(int i=0;i<tweets.size();i++){
                                    System.out.println(tweets.get(i).getUsuario() + " --> "+tweets.get(i).getMensagem());
                                }                           
                                this.Twitter();
                            }else
                                System.out.println("Nenhum tweet de "+usuarioUsado);
                            this.Twitter();
                            break;
                        case 3:

                            try {
                                tweetsTimeline = myTwitter.timeline(usuarioUsado);
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                            }

                            if(!tweetsTimeline.isEmpty()){
                                System.out.println("Timeline de " + usuarioUsado);
                                for(int i=0;i<tweetsTimeline.size();i++){
                                    System.out.println(tweetsTimeline.get(i).getUsuario() + " --> "+tweetsTimeline.get(i).getMensagem());
                                }
                                this.Twitter();
                            }else
                                System.out.println("Timeline de "+ usuarioUsado+" vazia.");
                            this.Twitter();
                            break;
                        case 4:
                            System.out.print("Digite o usuario a ser seguido: @");
                            seguido = inn.nextLine();
                            seguido = u + seguido;
                            seguidor = usuarioUsado;

                            try {
                                myTwitter.seguir(seguidor, seguido);
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                                this.Twitter();
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.Twitter();
                            } catch (SIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", já segue "+seguido+".");
                                this.Twitter();
                            }

                            System.out.println(seguidor+" seguiu "+seguido);
                            arquivo.gerarArquivo(perfil, " seguiu "+seguido);
                            this.Twitter();
                            break;
                        case 5:

                            try {
                                seguidores = myTwitter.seguidores(usuarioUsado);
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                                this.Twitter();
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.Twitter();
                            }

                            if(!seguidores.isEmpty()){
                                System.out.println("Seguidores de "+usuarioUsado+"\n");
                                for(int i=0;i<seguidores.size();i++){
                                    System.out.println("--> "+seguidores.get(i).getUsuario());
                                }
                                this.Twitter();
                            }else
                                System.out.println(usuarioUsado+" não tem seguidores.");
                            this.Twitter();
                            break;
                        case 6:

                            try {
                                seguidores = myTwitter.quemEuSigo(usuarioUsado);
                            } catch (PNAException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                                this.Twitter();
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.Twitter();
                            }

                            if(!seguidores.isEmpty()){
                                System.out.println("Seguidores de "+usuarioUsado+"\n");
                                for(int i=0;i<seguidores.size();i++){
                                    System.out.println("--> "+seguidores.get(i).getUsuario());
                                }
                                this.Twitter();
                            }else
                                System.out.println(usuarioUsado+" não tem seguidores.");
                            this.Twitter();
                            break;

                        case 7:
                            int numSeg = 0;
                            try {
                                numSeg = myTwitter.numeroSeguidores(usuarioUsado);
                                System.out.println("Numero de seguidores: "+numSeg);
                                this.Twitter();
                            } catch (PNAException ex) {
                               System.out.println("Perfil: "+ex.getUsuario()+", não ativo.");
                               this.Twitter();
                            } catch (PIException ex) {
                                System.out.println("Perfil: "+ex.getUsuario()+", inexistente.");
                                this.Twitter();
                            }                
                            break;
                        case 8:
                            this.Twitter();
                            break;
                        case 9:
                            this.menuPrincipal();
                            break;
                    }            
                }else{
                    System.out.println("\nDigito inválido");
                    this.Twitter();
                }
            }catch(InputMismatchException ex){
                System.out.println("\nDigito inválido");
                this.Twitter();
            }
                
        }else{
            System.out.println("Perfil inexistente.");
            this.menuPrincipal();
        }
    }
}