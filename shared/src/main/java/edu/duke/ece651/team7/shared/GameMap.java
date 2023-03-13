package edu.duke.ece651.team7.shared;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameMap implements Serializable{

    private Map<Territory, List<Territory>>  territoriesAdjacentList;


    /**
    * Constructs a GameMap object with a set of territories.
    * @param territoriesAdjacentList a mapping between a Territory object and a list of adjacent territories
    */
    public GameMap(Map<Territory, List<Territory>>  territoriesAdjacentList){
     this.territoriesAdjacentList = territoriesAdjacentList;
    }       

    /**
    *
    *Returns a collection of all territories in the game map.
    @return a collection of all territories in the game map
    */
    public Collection<Territory> getTerritories(){
         return territoriesAdjacentList.keySet();      
    }

    /**
    *
    *Returns a collection of all territories adjacent to the specified territory.
    *@param name the name of the territory for which adjacent territories are being retrieved
    *@return a collection of all territories adjacent to the specified territory
    *@throws IllegalArgumentException if no territory with the specified name is found
    */
    public Collection<Territory> getNeighbors(String name){
        Territory terr = getTerritoryByName(name);
        return territoriesAdjacentList.get(terr);
    }

    /**
    * Returns the territory with the specified name.
    * @param name the name of the territory to retrieve
    * @return the territory with the specified name
    * @throws IllegalArgumentException if no territory with the specified name is found
    */
    public Territory getTerritoryByName(String name){
        for(Territory terr: territoriesAdjacentList.keySet()){
            if(terr.getName().equals(name)){
               return terr;         
            }      
        }
        //temporary throw       
         throw new IllegalArgumentException("No Territory found with name: " + name); 
        }


    /**
    * Checks whether two territories are adjacent to each other.
    *
    * @param from the name of the territory to check adjacency from
    * @param to the name of the territory to check adjacency to
    * @return true if the territories are adjacent, false otherwise
    * @throws IllegalArgumentException if either the fromTerritory or toTerritory cannot be found
    */
    public boolean isAdjacent(String from, String to){    
        Territory fromTerritory = getTerritoryByName(from);
        Territory toTerritory = getTerritoryByName(to);
        List<Territory> adjacentTerritories = territoriesAdjacentList.get(fromTerritory);
        return adjacentTerritories.contains(toTerritory);
    }


    /**

    Determines whether a path exists between two territories which belong to the same owner.
    @param from the name of the source territory
    @param to the name of the destination territory
    @return true if a path exists between the source and destination territories, false otherwise
    @throws IllegalArgumentException if either the source or destination territory cannot be found
    */
    
    public boolean hasPath(String from, String to){
        Territory source = getTerritoryByName(from);
        Territory destination = getTerritoryByName(to);
        Set<Territory> territoryVisited = new HashSet<>();
        LinkedList<Territory> queue = new LinkedList<>();
        queue.add(source);
        territoryVisited.add(source);
        while (!queue.isEmpty()) {
            Territory curTerritory = queue.removeFirst();
            for (Territory neighbourTerritory : territoriesAdjacentList.get(curTerritory)) {
                if (neighbourTerritory.equals(destination)) {
                    return true;
                }
                if (!territoryVisited.contains(neighbourTerritory) 
                    // && neighbourTerritory.getOwner().equals(source.getOwner())
                    ) {
                        territoryVisited.add(neighbourTerritory);
                        queue.add(neighbourTerritory);
                 }
            }
        }
        return false;
    }




}
