import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;

/*The Sim Class implements the contract interface */
public class Sim implements Contract{
    ArrayList<String> haveitems; // the items that the sim has at any one time
    public int x;
    public int y;
    public String name;
    public Hashtable<String, String> items = new Hashtable<>(); //all the items in teh game
    public Map map;
    

    public Sim(int size, int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.map = new Map(10);

    }

    public void addItems() {
        items.put("🗝", "opens a door");
        items.put("👻", "says boo");
        items.put("👠", "makes you three inches taller");
        items.put("🕶", "makes you look sick");
        items.put("🧤", "makes you warm");
        items.put("🪴", "is something to water");
        items.put("🍁", "makes everyone smile");
        items.put("🍎", "something yummy to eat");
        items.put("🥎", "something to play with");
        items.put("🪓", "something to chop stuff with");
    }
    /* */
    public void grab(String item) {
        haveitems.add(item);
    }

    public String drop(String item) {
        haveitems.remove(item);
        return(item + "has been dropped");
    }

    public void examine(String item) {
        System.out.println(item + this.items.get(item));
    }

    public void use(String item) {
        System.out.println("Using" + item + "....");

        
    }

    public boolean walk(String direction) {
        direction = direction.toUpperCase();
        if (direction.equals("EAST")) {
            this.x += 1;
            return true;
        }
        else if (direction.equals("WEST")) {
            this.x -= 1;
            return true;
        }
        else if (direction.equals("NORTH" )) {
            this.y += -1;
            //System.out.println(direction);
            return true;
        }
        else if (direction.equals("SOUTH")) {
            this.y += 1;
            //System.out.println(direction);
            return true;
        }
        else {
            System.out.println("Please enter a valid direction");
            return false;
        }
        
    }

    public boolean fly(int x, int y) {
        this.x = x;
        this.y = y;
        
        return true;
    }
    
    
    public void rest() {
        System.out.println("Resting....");
        System.out.print("You are at" + this.x + "," + this.y);
    }

    /*Shrink needs to be finished TBD*/
    public Number shrink() {
       return 0;

    }

    /*Grow TBD */
    public Number grow() {
       return 1;
    }

    public void showOptions() {
        System.out.println(this.name + "can do any of the following:");
        System.out.println("grab(String item");
        System.out.println("drop(String item");
        System.out.println("");

    }

    public void undo() {

    }

    public void printMap() {
        this.map.printMap(y, x);
    }

    public static void main(String[] args) {
        Sim simOne = new Sim(1, 0, 0, "Billy");
        simOne.printMap();
        System.out.println();
        simOne.walk("West");
        simOne.printMap();
        System.out.println();

    }
    
}
