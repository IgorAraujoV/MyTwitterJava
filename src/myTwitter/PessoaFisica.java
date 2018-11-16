package myTwitter;

public class PessoaFisica extends Perfil {
    
    private String cpf;
    
    public PessoaFisica(String usuario) {
        super(usuario);
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String CPF) {
        this.cpf = CPF;
    }
    
    public boolean verificarCPF(String CPF){
        
        if(CPF.length() != 11)
            return false;
        
        int vetor[] = new int[11];
        int j=10, soma=0;
        int P, S;
        
        for(int i=0;i<11;i++){
            vetor[i] = Integer.parseInt(String.valueOf(CPF.charAt(i)));
        }
        
        for(int i=0;i<9;i++){
            soma+=(vetor[i]*j);
            j--;
        }
        
        if(soma%11 <2)
            P = 0;
        else
            P = 11 - (soma%11);
        
        j=11;
        soma=0;
        for(int i=0;i<10;i++){
            soma+=(vetor[i]*j);
            j--;
        }
        
        if(soma%11 <2)
            S = 0;
        else
            S = 11 - (soma%11);
        
        if(P==vetor[9] && S==vetor[10])
            return true;
        else
            return false;
    }
    public void printaCPF(String CPF){
        System.out.println((CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11)));
    }
}
