package edu.duke.ece651.team7.shared;

import java.util.List;
import java.util.Map;

public interface MapFactory {

  /**
   * This method creates a new GameMap object with 9 territories and their
   * adjacency lists.
   * It creates 3 players and assigns territories to them.
   * the map shown in initial prj1
   * 
   * @return a GameMap object with 9 territories and their adjacency lists
   */
  public GameMap createMap();

  /**
   * This method creates a new GameMap object with 9 territories and their
   * adjacency lists.
   * It creates 3 initial groups and assigns territories to them.
   * the map shown in initial prj1
   * 
   * @return a GameMap object with 9 territories and their adjacency lists
   */
  public GameMap createMapNew(int initGroupNum);

}