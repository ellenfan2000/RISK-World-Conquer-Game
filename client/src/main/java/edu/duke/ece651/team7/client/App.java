/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team7.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class App {
  public static void main(String[] args) {
    if (args.length == 2) {
      try {
        Client client = new Client(args[0], Integer.parseInt(args[1]),
            new BufferedReader(new InputStreamReader(System.in)), System.out);
        client.start();
      } catch (RemoteException e) {
        System.err.println("Exception: " + e);
        System.err.println("Exception: " + e.getLocalizedMessage());
        System.err.println("Exception: " + e.getCause());
      } catch (Exception e) {
        System.err.println("Exception: " + e);
      }
    } else {
      System.err.println("Usage: client <host> <port>");
    }
  }
}
