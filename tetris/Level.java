package tetris;

public class Level {
    private Level(){}
    final static double[] delays ={1,0.9,0.8,0.7,0.6,0.5,0.4,0.3,0.2,0.1,0.09,0.08,0.07,0.06,0.05};
    final static int[] levels ={10,20,30,40,50,60,70,80,90,100,100,100,100,100,100,110,120,130,140,150,160,170,180,190,200,200,200,200,0};
    public static int checkLevel(int štVrstic){
        int index=0;
        while(štVrstic>0&&index<30){
            štVrstic-=levels[index];
            index++;
        }
        return index-1;
    }

    public static int getDelay(int level,int baseDelay){
        return switch (level) {
            case 0 -> (int) (delays[0] * baseDelay);
            case 1 -> (int) (delays[1] * baseDelay);
            case 2 -> (int) (delays[2] * baseDelay);
            case 3 -> (int) (delays[3] * baseDelay);
            case 4 -> (int) (delays[4] * baseDelay);
            case 5 -> (int) (delays[5] * baseDelay);
            case 6 -> (int) (delays[6] * baseDelay);
            case 7 -> (int) (delays[7] * baseDelay);
            case 8 -> (int) (delays[8] * baseDelay);
            case 9 -> (int) (delays[9] * baseDelay);
            case 10 -> (int) (delays[10] * baseDelay);
            case 11 -> (int) (delays[10] * baseDelay);
            case 12 -> (int) (delays[10] * baseDelay);
            case 13 -> (int) (delays[11] * baseDelay);
            case 14 -> (int) (delays[11] * baseDelay);
            case 15 -> (int) (delays[11] * baseDelay);
            case 16 -> (int) (delays[12] * baseDelay);
            case 17 -> (int) (delays[12] * baseDelay);
            case 18 -> (int) (delays[12] * baseDelay);
            case 19 -> (int) (delays[13] * baseDelay);
            case 20 -> (int) (delays[13] * baseDelay);
            case 21 -> (int) (delays[13] * baseDelay);
            case 22 -> (int) (delays[13] * baseDelay);
            case 23 -> (int) (delays[13] * baseDelay);
            case 24 -> (int) (delays[13] * baseDelay);
            case 25 -> (int) (delays[13] * baseDelay);
            case 26 -> (int) (delays[13] * baseDelay);
            case 27 -> (int) (delays[13] * baseDelay);
            case 28 -> (int) (delays[13] * baseDelay);
            case 29 -> (int) (delays[14] * baseDelay);
            default -> (int) (delays[0] * baseDelay);
        };
    }
    public static int getLinesToLevel(int level){
        int lines=0;
        if(level<29) {
            for(int i=0;i<=level;i++){
                lines+=levels[i];
            }
        }
        return lines;
    }
}
