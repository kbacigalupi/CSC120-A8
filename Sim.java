import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;

/*The Sim Class implements the contract interface */
public class Sim implements Contract{
    ArrayList<String> haveItems; // the items that the sim has at any one time
    public int x; //position of x
    public int y; // position of y
    public String name; //name of Sim
    public Hashtable<String, String> items = new Hashtable<>(); //all the items in teh game
    public Map map; //the map the Sim will move around 
    public int size;
    public String lastCommand;
    
    /*Sim constructor
     * @param int size of the map of the game
     * @param name is the name of the Sim
     */
    public Sim(int size, String name) {
        this.x = 0;
        this.y = 0;
        this.name = name;
        /*The instance of map class that is associated with the person emoji*/
        this.map = new Map(10);
        this.size = 10;
        this.haveItems = new ArrayList<String>();
    }
    /*
     * Loads all of the items into a hashtable so they can be later "examined" - the value is what is printed out later
     */
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
    /*Takes the item at the position of the guy, adds it to the bag, and removes it from the map
     * @param item is the emoji to be added to the bag/removed from the map
     */
    public void grab(String item) {
        // error checking lin System.out.println("grab ==>" + item + "<==");
        if (haveItems.contains(item)) {
            System.out.println("Item is already in bag");
            return;
        }
        String check = map.getItem(this.x, this.y);
        //error checking line System.out.println("Check ==>" + check + "<==");
        if (item.equals(check)) {
            haveItems.add(item);
            this.map.remove(this.x, this.y);
            this.printMap();
            System.out.println(item + " is in bag");
            return;
        }
        System.out.println("There is no object to pick up-" + item);
    }

    /*Places an item on the map at the location of the sim (if it is in the bag/hasItems() dictionary) 
     * @param item the emoji of the item that is being dropped
     * @return's a string telling whether the map has been changed or not 
     */
    public String drop(String item) {
        if (haveItems.contains(item)) {
            this.map.add(this.x, this.y, item);
        }
        else {
            return item + " is not in your bag";
        }
        haveItems.remove(item);
        this.printMap();
        return(item + " has been dropped");
    }

    /*Examines an item that the character must have in their arraylist TBD
     * @param the item that character wants to examine
    */
    public void examine(String item) {
        if (haveItems.contains(item)) {
            System.out.println(item + " " + this.items.get(item));
        }
        else {
            System.out.println("Item is not in bag");
        }
    }

    /* TBD - check to see if the guy has is (ASCII with guy holding it??)
     * @param a string emoji that is to be used
    */
    public void use(String item) {
        if (haveItems.contains(item)) {
            System.out.println("Using " + item + "....");
            System.out.println("Check back later for what your sim can do with the item");
        }
        else {
            System.out.println("Cannot use item-you do not have it in your bag");
        }
    }

    /*Moves the sim one step up, down, right, or left
     * @param direction is the direction that the sim moves
     * @return true or false whether the sim can successfully move in that direction 
     */
    public boolean walk(String direction) {
        direction = direction.toUpperCase();
        //System.out.println(direction); error checking line 
        if (direction.equals("EAST")) {
            this.x += 1;
        }
        else if (direction.equals("WEST")) {
            this.x += -1;
        }
        else if (direction.equals("NORTH" )) {
            this.y += -1;
        }
        else if (direction.equals("SOUTH")) {
            this.y += 1;
        }
        else {
            System.out.println("Please enter a valid direction");
            return false;
        }
        this.printMap();
        return true;
    }

    /* Changes the posistion of the sim and reprints the map
     * @param int x is the x position (0 to 9) on the grid you want to go to
     * @param int y is the y position (0 to 9) on the grid you want to go to 
     * @return true or false whether the map has been redrawn
    */
    public boolean fly(int x, int y) {
        if (x < 0 ||x > size || y < 0 || y > size) {
            System.out.println("You may not fly to those coordinates");
            return false;
        }
        this.x = y;
        this.y = x;
        this.printMap();
        return true;
    }
    
    /*Tells the user that the character is resting & where they are located */
    public void rest() {
        System.out.println("You are resting at " + this.x + "," + this.y);
        System.out.print("You have ");
        for (int i = 0; i<this.haveItems.size(); i++) {
            System.out.print(this.haveItems.get(i) + ", ");
        }
        System.out.println(" ");
    }

    /*Decreases the size of the map that the guy stands on by one row and one column
    * Shifts items into the smaller graph as needed
    * @return the new size of the map
    */
    public Number shrink() {
        if (this.size >4) {
            this.size -= 1;
            map.shrink();
            this.printMap();
            return size;
        }
        else {
            System.out.println("Map is too small to shrink");
            return size;
        }

    }

    /*Increases the size of the map that the sim stands on by one row and column
    * @return new map size
    */
    public Number grow() {
        this.size += 1;
        map.grow();
        this.printMap();
        return this.size;
    }

    /*Shows all possible commands for the user-at the beginning and everytime the "so" command is used*/
    public void showOptions() {
        System.out.println("so (Shows the options of commands)");
        System.out.println("grab emoji (Must be on the space of the emoji)");
        System.out.println("drop emoji");
        System.out.println("e (move east)");
        System.out.println("w (move west)");
        System.out.println("n (move north)");
        System.out.println("s (move south)");
        System.out.println("fly x y");
        System.out.println("grow");
        System.out.println("shrink");
        System.out.println("ex emoji (examines an object-MUST be grabbed first)");
        System.out.println("info (shows items and location of sim)");
        System.out.println("use emoji");
        System.out.println("undo (doesn't work)");
        System.out.println("exit (ends the game");
    }

    /*TBD This method is realllll hard  */
    public void undo() {
        try {
            if (this.lastCommand.equals("e")) {
                this.walk("west");
            }
            else if (this.lastCommand.equals("w")) {
                this.walk("east");
            }
            else if (this.lastCommand.equals("s")) {
                this.walk("north");
            }
            else if (this.lastCommand.equals("n")) {
                this.walk("south");
            }
            else if(this.lastCommand.startsWith("drop")) {
                try {this.grab(lastCommand.substring(5));}
                catch (Exception e) {
                    System.out.println("Please type something in to grab");
                }
            }
            else if(this.lastCommand.startsWith("grab")) {
                try {
                this.drop(lastCommand.substring(5));}
                catch (Exception e) {
                    System.out.println("Please type in something to drop");
                }
            }
            else if(this.lastCommand.equals("shrink")) {
                this.grow();
            }
            else if(this.lastCommand.equals("grow")){
                this.shrink();
            }
            else {
                System.out.println("Cannot undo that action at this time. Check back for improvements!");
            }
        } catch (Exception e) {
        System.out.println("Cannot undo that action at this time. Check back for improvements!");
    }
        

    }

    /*Calls the printMap() function that is in the map class-
    * with reversed x and y to make the map print out the way we want it to */
    public void printMap() {
        this.map.printMap(y, x);
    }

    /*Runs the "interactive" part of the program with a scanner and a loop
     * @param String[] command line arguments
     */
    public static void main(String[] args) {
        /*The sim used throughout the game */
        Sim simOne = new Sim(10, "Your Sim");
        simOne.addItems();
        System.out.println("************************************");
        System.out.println("              NEW GAME             ");
        System.out.println(simOne.name + " can do many things. \nParenthesis are further explanations of a command \nAnytime you are working with an object, you must copy and paste the emoji");
        System.out.println("Welcome to Map Land! This is your map:");

        simOne.printMap();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String command = "";
        simOne.showOptions();

        while (!command.equals("exit")) {
            System.out.println("Enter next command");
            command = myObj.nextLine();  // Read user input
            command = command.toLowerCase();
            if (command.equals("e")) { //series of if statements to test for each command
                simOne.walk("east");
            }
            else if (command.equals("w")) {
                simOne.walk("west");
            }
            else if (command.equals("n")) {
                simOne.walk("north");
            }
            else if (command.equals("s")) {
                simOne.walk("south");
            }
            else if(command.startsWith("grab")) { //NEED ERROR CHECKING 
                //try {simOne.grab(command.substring(5));}
                simOne.grab(command.substring(5));
            }
            else if (command.startsWith("drop")) {
                System.out.println(simOne.drop(command.substring(5)));
            }
            else if (command.startsWith("ex")) {
                try {
                    simOne.examine(command.substring(3));
                  } catch (Exception e) {
                    System.out.println("No object specified");
                  }
            }
            else if (command.startsWith("use")) {
                try {
                    simOne.use(command.substring(4));
                } catch (Exception e) {
                    System.out.println("No object specified");
                }
            }
            else if(command.startsWith("fly")){
                String[] result = command.split(" ");
                int x = Integer.valueOf(result[1]);
                int y = Integer.valueOf(result[2]);
                simOne.fly(x, y);
            }
            else if (command.equals("shrink")) {
                simOne.shrink();
            }
            else if (command.equals("grow")) {
                simOne.grow();
            }
            else if (command.equals("info")) {
                simOne.rest();
            }
            else if (command.equals("so")) {
                simOne.showOptions();
            }
            else if (command.equals("undo")) {
                simOne.undo();
            }
            else {
                System.out.println("This is not a valid command");
            }
            simOne.lastCommand = command;
        }
        System.out.println("Thank you for playing!");
        myObj.close();
    }
    
}