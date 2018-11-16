package myTwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Arquivo {
    public void gerarArquivo(Perfil perfil, String algo){
        File arquivo = new File("relatorio.txt");
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        try {           
            //arquivo.createNewFile();      
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter escrever = new BufferedWriter(fw);
            
            escrever.write(formatador.format( data )+ " ----> "+perfil.getUsuario()+ " "+ algo);
            escrever.newLine();
            
            escrever.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println("erro ao gerar arquivo.");
        }
    }
    
    public void excluirArquivo(){
        File arquivo = new File("relatorio.txt");
        arquivo.delete();
    }
}
    
    
