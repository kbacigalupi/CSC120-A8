public class Map {
    String[][]map;
    int size;

    public Map(int size) {
        this.map = new String[size][size];
        this.map[0][2] = "🗝";
        this.map[1][4] = "👻";
        this.map[3][8] = "👠";
        this.map[4][3] = "🕶";
        this.map[5][2] = "🧤";
        this.map[6][9] = "🪴";
        this.map[7][0] = "🍁";
        this.map[8][1] = "🍎";
        this.map[9][5] = "🥎";
        this.map[2][9] = "🪓";
        this.size = size;

    }

    public void printMap(int guyx, int guyy) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j< 10; j++) {
                System.out.print("[");
                if (i == guyx && j == guyy) {
                    System.out.print("🧍");
                }
                if (this.map[i][j] == null) {
                    System.out.print(" ");
                }
                else {
                    System.out.print(this.map[i][j]);
                }
                System.out.print("]");
            }
            System.out.println("");
            System.out.println(" ");
                } 
    }

    public String toString() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j< 10; j++) {
                System.out.print("[");
                if (this.map[i][j] == null) {
                    System.out.print(" ");
                }
                else {
                    System.out.print(this.map[i][j]);
                }
                System.out.print("]");
            }
            System.out.println("");
            System.out.println(" ");
                } 
            return "";
    }
    

    public static void main(String[] args) {
        //String[][] map1 = new String[10][10];
        //Map myMap = new Map(map1);
        //System.out.println(myMap);
       
    }

}