/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team7.server;

import edu.duke.ece651.team7.shared.MapFactory;
import edu.duke.ece651.team7.shared.TextMapFactory;

public class App {
  public static void main(String[] args) {
    if (args.length == 2) {
      try {
        // MapFactory factory = new TextMapFactory();
        // Server server = new Server(Integer.parseInt(args[1]), System.out, 10,
        // factory.createMapTest());
        // server.start(Integer.parseInt(args[0]));
      } catch (Exception e) {
        System.err.println("Exception: " + e);
      }
    } else {
      System.err.println("Usage: server <port> <clientCapacity>");
    }
    // Map<Integer, ArrayList<String> > attackOrderPool = new HashMap<Integer,
    // ArrayList<String> >();
    // attackOrderPool.put(1,new ArrayList<String>(Arrays.asList("a", "b",
    // "c","d","e","f","g")));
    // ArrayList<String> combats = attackOrderPool.get(1);
    // int i = 0;
    // int count = 0;
    // while (i < combats.size()){
    // int defenser = i;
    // if(i == combats.size()-1){
    // i = -1;
    // }
    // int attacker = i+1;
    // System.out.println(combats.get(defenser) + ", " + combats.get(attacker));
    // if(combats.get(defenser) == "a" || combats.get(defenser) == "e" ||
    // combats.get(defenser) == "g"){
    // combats.remove(defenser);
    // if(i!=-1){
    // i--;
    // }
    // }
    // if(count == 10){
    // break;
    // }
    // i++;
    // count++;
    // }
    // for(int j = 0; j < attackOrderPool.get(1).size(); j++){
    // System.out.println(attackOrderPool.get(1).get(j));
    // }

  }
}
