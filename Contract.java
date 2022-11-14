public interface Contract {

    void grab(String item); //adds an item to the Sim's box
    String drop(String item); //leaves the item where the Sim is located
    void examine(String item); //Tells the user what an item does 
    void use(String item); //show the guy with the object in hand
    boolean walk(String direction); 
    boolean fly(int x, int y);
    Number shrink();
    Number grow();
    void rest();
    void undo();

}

