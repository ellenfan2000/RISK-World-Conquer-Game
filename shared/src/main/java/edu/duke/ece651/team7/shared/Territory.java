package edu.duke.ece651.team7.shared;

import java.util.HashSet;

public class Territory {
  private final String name;
  //private Player owner;
  private int units;
  private HashSet<String> neighbors;

  /**
   * Constructs a territory with default values
   */
  public Territory() {
    this.name = "Default";
  }

  /**
     Constucts a territory with just inputted name
  */
  public Territory(String name) {
    this.name = name;
    //this.owner = new Player();
    this.units = 0;
    this.neighbors = new HashSet<String>();

  }
  
  /**
     Constructs a territory with inputted values
     @param name name of territory
     @param owner player that owns the territory
     @param units number of player's units present in territory
     @param neighbors the nearby (adjacent) territories
  */
  public Territory(String name, //Player owner,
                   int units, HashSet<String> neighbors) {
    this.name = name;
    //this.owner = owner;
    this.units = units;
    this.neighbors = neighbors;
  }
  

  public String getName() {
    return name;
  }

  public int getUnits() {
    return units;
  }

  // public Player getOwner() {
  //   return owner;
  // }

  // public void setOwner(Player p) {
  //   owner = p;
  // }

  public void increaseUnits() {
    units++;
  }

  public void decreaseUnits() {
    if (units > 0) {
      units--;
    }
  }

  public Boolean isAdjacent(String name) {
    return neighbors.contains(name);
  }

}
