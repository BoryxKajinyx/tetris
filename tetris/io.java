package tetris;
import java.io.*;
public class io {
    public static Score[] beriStart() throws IOException{
        FileReader fr=new FileReader("tetris/Zapis.txt");
        Score[] scores = new Score[prestejZapise(fr)];
        fr=new FileReader("Zapis.txt");
        BufferedReader br=new BufferedReader(fr);
        for (int i = 0; i < scores.length; i++) {
            scores[i]=new Score();
            scores[i].name=br.readLine();
            scores[i].point=Integer.parseInt(br.readLine());
        }
        br.close();
        sort(scores);
        return scores;
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
    public static Score[] dodajZapis(Score[] sb,Score s){
        Score[] sb1=new Score[sb.length+1];
        for (int i = 0; i < sb.length; i++) {
            sb1[i]=new Score();
            sb1[i]=sb[i];
        }
        sb1[sb.length]=s;
        sort(sb1);
        return sb1;
    }
    public static void sort(Score[] array){
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - i - 1; j++)
                if (array[j].point > array[j + 1].point) {
                    // swap arr[j+1] and arr[j]
                    Score temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }
    public static void piši(Score[] sb)throws IOException{
        PrintWriter pw=new PrintWriter(new File("Zapis.txt"));
        for (int i = 0; i < sb.length; i++) {
            pw.println(sb[i].name);
            pw.println(sb[i].point);
        }
        pw.close();
    }
    
}
