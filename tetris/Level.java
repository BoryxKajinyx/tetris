package tetris;

public class Level {
    private Level(){}
    final static double[] delays ={1,0.9,0.8,0.7,0.6,0.5,0.4,0.3,0.2,0.1,0.09,0.08,0.07,0.06,0.05};
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
        int baseDelay=300;
        switch(level){
            case 0:return (int) (delays[0]*baseDelay);
            case 1:return (int) (delays[1]*baseDelay);
            case 2:return (int) (delays[2]*baseDelay);
            case 3:return (int) (delays[3]*baseDelay);
            case 4:return (int) (delays[4]*baseDelay);
            case 5:return (int) (delays[5]*baseDelay);
            case 6:return (int) (delays[6]*baseDelay);
            case 7:return (int) (delays[7]*baseDelay);
            case 8:return (int) (delays[8]*baseDelay);
            case 9:return (int) (delays[9]*baseDelay);

            case 10:return (int)(delays[10]*baseDelay);
            case 11:return (int)(delays[10]*baseDelay);
            case 12:return (int)(delays[10]*baseDelay);

            case 13:return (int)(delays[11]*baseDelay);
            case 14:return (int)(delays[11]*baseDelay);
            case 15:return (int)(delays[11]*baseDelay);

            case 16:return (int)(delays[12]*baseDelay);
            case 17:return (int)(delays[12]*baseDelay);
            case 18:return (int)(delays[12]*baseDelay);

            case 19:return (int)(delays[13]*baseDelay);
            case 20:return (int)(delays[13]*baseDelay);
            case 21:return (int)(delays[13]*baseDelay);
            case 22:return (int)(delays[13]*baseDelay);
            case 23:return (int)(delays[13]*baseDelay);
            case 24:return (int)(delays[13]*baseDelay);
            case 25:return (int)(delays[13]*baseDelay);
            case 26:return (int)(delays[13]*baseDelay);
            case 27:return (int)(delays[13]*baseDelay);
            case 28:return (int)(delays[13]*baseDelay);

            case 29:return (int)(delays[14]*baseDelay);
            default:return (int) (delays[0]*baseDelay);
        }
    }
    public static int getDelay(int level,int baseDelay){
        switch(level){
            case 0:return (int) (delays[0]*baseDelay);
            case 1:return (int) (delays[1]*baseDelay);
            case 2:return (int) (delays[2]*baseDelay);
            case 3:return (int) (delays[3]*baseDelay);
            case 4:return (int) (delays[4]*baseDelay);
            case 5:return (int) (delays[5]*baseDelay);
            case 6:return (int) (delays[6]*baseDelay);
            case 7:return (int) (delays[7]*baseDelay);
            case 8:return (int) (delays[8]*baseDelay);
            case 9:return (int) (delays[9]*baseDelay);

            case 10:return (int)(delays[10]*baseDelay);
            case 11:return (int)(delays[10]*baseDelay);
            case 12:return (int)(delays[10]*baseDelay);

            case 13:return (int)(delays[11]*baseDelay);
            case 14:return (int)(delays[11]*baseDelay);
            case 15:return (int)(delays[11]*baseDelay);

            case 16:return (int)(delays[12]*baseDelay);
            case 17:return (int)(delays[12]*baseDelay);
            case 18:return (int)(delays[12]*baseDelay);

            case 19:return (int)(delays[13]*baseDelay);
            case 20:return (int)(delays[13]*baseDelay);
            case 21:return (int)(delays[13]*baseDelay);
            case 22:return (int)(delays[13]*baseDelay);
            case 23:return (int)(delays[13]*baseDelay);
            case 24:return (int)(delays[13]*baseDelay);
            case 25:return (int)(delays[13]*baseDelay);
            case 26:return (int)(delays[13]*baseDelay);
            case 27:return (int)(delays[13]*baseDelay);
            case 28:return (int)(delays[13]*baseDelay);

            case 29:return (int)(delays[14]*baseDelay);
            default:return (int) (delays[0]*baseDelay);
        }
    }
}
