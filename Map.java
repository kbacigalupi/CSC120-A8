import java.util.Random;
import java.util.List;
import java.util.Arrays;

/*Map class is associated with the Sim class in a one-to-one relationship right now */
public class Map {
    String[][]map;
    int size;
   List<String> mapItems = Arrays.asList("🗝","👻", "👠","🕶", "🧤", "🪴", "🍁", "🍎", "🥎", "🪓");

   /*Constructor for map - builds an array of arrays that can be printed out as a map
    * @size the number of both rows and columns created in the map
    */
    public Map(int size) {
        this.map = new String[size][size];
        this.size = size;
        Random rand = new Random();
        for (int i = 0; i <mapItems.size(); i++) {
                this.map[rand.nextInt(size)][rand.nextInt(size)] = mapItems.get(i);
        }

    }

    /*Makes a new map that is one row bigger and one column bigger; Copies the old map into the new one*/
    public void grow() {
        int newSize = size + 1;
        String[][] newMap = new String[newSize][newSize];
        for (int i = 0; i<size; i++) {
            for (int j = 0; j<size; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        size = newSize;
        map = newMap;
    }

    /*Makes a new map that is one row smaller and one column smaller but COPIES the old map into the new one
     * For every item that was in the last row/column, the item is randomly reassigned a spot
     */
    public void shrink() {
        int newSize = size -1;
        String[][] newMap = new String[newSize][newSize];
        for (int i = 0; i<newSize; i++) {
            for (int j = 0; j<newSize; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        Random rand = new Random();
        for (int i = 0; i<size - 1; i++) {
            if (map[i][size-1] != null) {
                newMap[rand.nextInt(newSize)][rand.nextInt(newSize)] = this.map[i][size-1];
            }
        }
        for (int i = 0; i<size; i++) {
            if (map[size-1][i] != null) {
                newMap[rand.nextInt(newSize)][rand.nextInt(newSize)] = this.map[size-1][i];
            }
        }
        size = newSize;
        map = newMap;
    }

    /*Replaces an emoji with a null value in the array of arrays, essentially taking the object off the board*/
    public void remove(int itemx, int itemy) {
        map[itemy][itemx] = null;
    }

    /* Replaces an empty square with a new item
     * @param itemx
     * @param itemy
     * @param item - the emoji/"item" being dropped
    */
    public void add(int itemx, int itemy, String item) {
        if (map[itemx][itemy] == null) {
            map[itemy][itemx] = item;
        }
        else {
            System.out.println("Item already exists here please choose new location");
        }
    }
    
    /* Tells the sim what item is in the square they reside in
     * @return the value of the array of arrays at the sim's location (an emoji or null)
     */
    public String getItem(int guyx, int guyy) {
        if (map[guyx][guyy] == "🧍 ") {
            return null;
        }
        return map[guyy][guyx];
    }

    /*Displays the map out row by row, checking to see if an object or person is at each location in the array of arrays
     * @param guyx is the row location of the Sim on the map
     * @param guyy is the column location of the Sim on the map
    */
    public void printMap(int guyx, int guyy) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j< size; j++) {
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

}