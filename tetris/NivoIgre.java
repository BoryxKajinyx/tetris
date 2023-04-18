package tetris;

public class NivoIgre {
    private NivoIgre(){}
    final static double[] zamiki ={1,0.9,0.8,0.7,0.6,0.5,0.4,0.3,0.2,0.1,0.09,0.08,0.07,0.06,0.05};
    final static int[] nivoji ={10,20,30,40,50,60,70,80,90,100,100,100,100,100,100,110,120,130,140,150,160,170,180,190,200,200,200,200,200};
    public static int preveriNivo(int štVrstic){
        int index=0;
        while(štVrstic>0&&index<30){
            štVrstic-= nivoji[index];
            index++;
        }
        return index-1;
    }

    public static int pridobiZamik(int nivo, int osnovniZamik){
        switch (nivo) {
            case 1:
                return (int) (zamiki[1] * osnovniZamik);
            case 2:
                return (int) (zamiki[2] * osnovniZamik);
            case 3:
                return (int) (zamiki[3] * osnovniZamik);
            case 4:
                return (int) (zamiki[4] * osnovniZamik);
            case 5:
                return (int) (zamiki[5] * osnovniZamik);
            case 6:
                return (int) (zamiki[6] * osnovniZamik);
            case 7:
                return (int) (zamiki[7] * osnovniZamik);
            case 8:
                return (int) (zamiki[8] * osnovniZamik);
            case 9:
                return (int) (zamiki[9] * osnovniZamik);
            case 10:
                return (int) (zamiki[10] * osnovniZamik);
            case 11:
                return (int) (zamiki[10] * osnovniZamik);
            case 12:
                return (int) (zamiki[10] * osnovniZamik);
            case 13:
                return (int) (zamiki[11] * osnovniZamik);
            case 14:
                return (int) (zamiki[11] * osnovniZamik);
            case 15:
                return (int) (zamiki[11] * osnovniZamik);
            case 16:
                return (int) (zamiki[12] * osnovniZamik);
            case 17:
                return (int) (zamiki[12] * osnovniZamik);
            case 18:
                return (int) (zamiki[12] * osnovniZamik);
            case 19:
                return (int) (zamiki[13] * osnovniZamik);
            case 20:
                return (int) (zamiki[13] * osnovniZamik);
            case 21:
                return (int) (zamiki[13] * osnovniZamik);
            case 22:
                return (int) (zamiki[13] * osnovniZamik);
            case 23:
                return (int) (zamiki[13] * osnovniZamik);
            case 24:
                return (int) (zamiki[13] * osnovniZamik);
            case 25:
                return (int) (zamiki[13] * osnovniZamik);
            case 26:
                return (int) (zamiki[13] * osnovniZamik);
            case 27:
                return (int) (zamiki[13] * osnovniZamik);
            case 28:
                return (int) (zamiki[13] * osnovniZamik);
            case 29:
                return (int) (zamiki[14] * osnovniZamik);
            default:
                return (int) (zamiki[0] * osnovniZamik);
        }
    }
    public static int pretvoriNivoVVrstice(int nivo){
        int lines=0;
        if(nivo<29) {
            for(int i=0;i<=nivo;i++){
                lines+= nivoji[i];
            }
        }
        return lines;
    }
}
