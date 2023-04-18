package tetris;
import java.io.*;
public class io {

    public static RezultatIgre[] beriRezultate(){
        RezultatIgre[] rezultati=null;
        try {
            FileReader fr = new FileReader("tetris/Zapis.txt");
            rezultati = new RezultatIgre[prestejZapise(fr)];
            fr = new FileReader("tetris/Zapis.txt");
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < rezultati.length; i++) {
                rezultati[i] = new RezultatIgre();
                rezultati[i].name = br.readLine();
                rezultati[i].point = Integer.parseInt(br.readLine());
            }
            br.close();
        }catch(IOException ignored){}
        if(rezultati==null){
            rezultati=new RezultatIgre[0];
        }
        sortiraj(rezultati);
        return rezultati;
    }
    public static int prestejZapise(FileReader fr)throws IOException{
        int stZapisov=0;
        BufferedReader br=new BufferedReader(fr);
        String vrstica=br.readLine();
        while(vrstica!=null){
            for (int i = 0; i < vrstica.length(); i++) {
                if(Character.isLetter(vrstica.charAt(0))){
                    stZapisov++;
                    break;
                }
            }
            vrstica=br.readLine();
        }
        return stZapisov;
    }
    public static RezultatIgre[] dodajZapis(RezultatIgre[] r, RezultatIgre s){
        RezultatIgre[] rezultati=new RezultatIgre[r.length+1];
        for (int i = 0; i < r.length; i++) {
            rezultati[i]=new RezultatIgre();
            rezultati[i]=r[i];
        }
        rezultati[r.length]=s;
        sortiraj(rezultati);
        return rezultati;
    }
    public static void sortiraj(RezultatIgre[] rezultati){
        for (int i = 0; i < rezultati.length - 1; i++)
            for (int j = 0; j < rezultati.length - i - 1; j++)
                if (rezultati[j].point < rezultati[j + 1].point) {
                    RezultatIgre začasenRezultat = rezultati[j];
                    rezultati[j] = rezultati[j + 1];
                    rezultati[j + 1] = začasenRezultat;
                }
    }
    public static void piši(RezultatIgre[] rezultati)throws IOException{
        PrintWriter pw=new PrintWriter("tetris/Zapis.txt");
        sortiraj(rezultati);
        for (RezultatIgre score : rezultati) {
            pw.println(score.name);
            pw.println(score.point);
        }
        pw.close();
    }
    
}
