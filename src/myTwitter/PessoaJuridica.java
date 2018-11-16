package myTwitter;
public class PessoaJuridica extends Perfil{
    private String cnpj;
    public PessoaJuridica(String usuario) {
        super(usuario);
    }

    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }
    public boolean verificarCNPJ(String CNPJ){
        
        if(CNPJ.length() != 14)
            return false;
        
        /*int vetor[] = new int[14];
        int j=5, soma=0, c=0;
        int P, S;
        
        for(int i=0;i<14;i++){
            vetor[i] = Integer.parseInt(String.valueOf(CNPJ.charAt(i)));
        }
        
        for(int i=0;i<12;i++){
            if(c==4)
                j=9;
            soma+=(vetor[i]*j);
            j--;
            c++;
        }
        
        if(soma%11 <2)
            P = 0;
        else
            P = 11 - (soma%11);
        
        j=6;
        soma=0;
        c=0;
        for(int i=0;i<13;i++){
            if(c==5)
                j=9;
            soma+=(vetor[i]*j);
            j--;
            c++;
        }
        
        if(soma%11 <2)
            S = 0;
        else
            S = 11 - (soma%11);
        
        if(P==vetor[12] && S==vetor[13])
            return true;
        else
            return false;*/
        return true;
    }
    public void printaCNPJ(String CNPJ){
        System.out.println((CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." + CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12) + "-"
                + CNPJ.substring(12, 14)));
    }
}
