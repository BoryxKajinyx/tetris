package tetris;

public class Level {
    private Level(){}
    final static int[] delays ={300,270,240,210,180,150,120,90,60,30,28,26,24,22,20};
    final static int[] levels ={10,20,30,40,50,60,70,80,90,100,100,100,100,100,100,110,120,130,140,150,160,170,180,190,200,200,200,200};
    public static int checkLevel(int štVrstic){
        int index=0;
        štVrstic-=levels[0];
        while(štVrstic>0){
            if(štVrstic-levels[index+1]>0){
                index++;
                štVrstic-=levels[index];
            }
            else
            break;
        }
        return index;
    }
    public static int getDelay(int level){
        switch(level){
            case 0:return delays[0];
            case 1:return delays[1];
            case 2:return delays[2];
            case 3:return delays[3];
            case 4:return delays[4];
            case 5:return delays[5];
            case 6:return delays[6];
            case 7:return delays[7];
            case 8:return delays[8];
            case 9:return delays[9];

            case 10:return delays[10];
            case 11:return delays[10];
            case 12:return delays[10];

            case 13:return delays[11];
            case 14:return delays[11];
            case 15:return delays[11];

            case 16:return delays[12];
            case 17:return delays[12];
            case 18:return delays[12];

            case 19:return delays[13];
            case 20:return delays[13];
            case 21:return delays[13];
            case 22:return delays[13];
            case 23:return delays[13];
            case 24:return delays[13];
            case 25:return delays[13];
            case 26:return delays[13];
            case 27:return delays[13];
            case 28:return delays[13];

            case 29:return delays[14];
            default: return 300;
        }
    }
}
