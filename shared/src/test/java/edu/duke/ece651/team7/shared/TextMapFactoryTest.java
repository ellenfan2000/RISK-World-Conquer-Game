package edu.duke.ece651.team7.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TextMapFactoryTest {
    

    @Test
    public void test_createMap(){
        MapFactory mf = new TextMapFactory();
        GameMap threePlayersMap = mf.createPlayerMap(3);
        Player p1 = threePlayersMap.new InitGroupOwner("GroupA");
        Player p2 = threePlayersMap.new InitGroupOwner("GroupB");
        Player p3 = threePlayersMap.new InitGroupOwner("GroupC");
        Territory territory1 = new Territory("Narnia", p1, 10);
        Territory territory2 = new Territory("Elantris", p2, 6);
        Territory territory3 = new Territory("Midkemia", p1,12);
        Territory territory4 = new Territory("Oz",p1, 8);
        Territory territory5 = new Territory("Scadrial", p2,5);
        Territory territory6 = new Territory("Roshar",p2,3);
        Territory territory7 = new Territory("Gondor",p3,13);
        Territory territory8 = new Territory("Mordor",p3,14);
        Territory territory9 = new Territory("Hogwarts",p3,3);
        Collection<Territory> terr = threePlayersMap.getTerritories();
        assertEquals(true, terr.contains(territory1));
        assertEquals(true, terr.contains(territory2));
        assertEquals(true, terr.contains(territory3));
        assertEquals(true, terr.contains(territory4));
        assertEquals(true, terr.contains(territory5));
        assertEquals(true, terr.contains(territory6));
        assertEquals(true, terr.contains(territory7));
        assertEquals(true, terr.contains(territory8));
        assertEquals(true, terr.contains(territory9));

        Territory terr2 = threePlayersMap.getTerritoryByName("Narnia");
        Player p11 = terr2.getOwner();
        Player p12 = new Player("GroupA");
        Player p13 = threePlayersMap.new InitGroupOwner("GroupA");
        assertNotEquals(p11, p12);
        assertEquals(p11, p13);

        assertThrows(IllegalArgumentException.class, ()->mf.createPlayerMap(5));
        GameMap twoPlayersMap = mf.createPlayerMap(2);
        GameMap fourPlayersMap = mf.createPlayerMap(4);

  
    }


    @Test
    public void test_createTwoPlayersMap_mockito(){
        MapFactory mf = new TextMapFactory();
        GameMap twoPlayersMap = mf.createPlayerMap(2);

        GameMap tmap = mock(GameMap.class);

        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        Territory t1 = mock(Territory.class);
        Territory t2 = mock(Territory.class);
        Territory t3 = mock(Territory.class);
        Territory t4 = mock(Territory.class);
        Territory t5 = mock(Territory.class);
        Territory t6 = mock(Territory.class);
        Territory t7 = mock(Territory.class);
        Territory t8 = mock(Territory.class);
        Territory t9 = mock(Territory.class);
        Territory t10 = mock(Territory.class);
        Territory t11 = mock(Territory.class);
        Territory t12 = mock(Territory.class);
        Territory t13 = mock(Territory.class);
        Territory t14 = mock(Territory.class);
        Territory t15 = mock(Territory.class);
        Territory t16 = mock(Territory.class);
        Territory t17 = mock(Territory.class);
        Territory t18 = mock(Territory.class);
        Territory t19 = mock(Territory.class);
        Territory t20 = mock(Territory.class);
        Territory t21 = mock(Territory.class);
        Territory t22 = mock(Territory.class);
        Territory t23 = mock(Territory.class);
        Territory t24 = mock(Territory.class);

        when(t1.getUnits()).thenReturn(0);
        when(t2.getUnits()).thenReturn(0);
        when(t3.getUnits()).thenReturn(0);
        when(t4.getUnits()).thenReturn(0);
        when(t5.getUnits()).thenReturn(0);
        when(t6.getUnits()).thenReturn(0);
        when(t7.getUnits()).thenReturn(0);
        when(t8.getUnits()).thenReturn(0);
        when(t9.getUnits()).thenReturn(0);
        when(t10.getUnits()).thenReturn(0);
        when(t11.getUnits()).thenReturn(0);
        when(t12.getUnits()).thenReturn(0);
        when(t13.getUnits()).thenReturn(0);
        when(t14.getUnits()).thenReturn(0);
        when(t15.getUnits()).thenReturn(0);
        when(t16.getUnits()).thenReturn(0);
        when(t17.getUnits()).thenReturn(0);
        when(t18.getUnits()).thenReturn(0);
        when(t19.getUnits()).thenReturn(0);
        when(t20.getUnits()).thenReturn(0);
        when(t21.getUnits()).thenReturn(0);
        when(t22.getUnits()).thenReturn(0);
        when(t23.getUnits()).thenReturn(0);
        when(t24.getUnits()).thenReturn(0);

        when(p1.getName()).thenReturn("GroupA");
        when(p2.getName()).thenReturn("GroupB");

        when(t1.getOwner()).thenReturn(p1);
        when(t2.getOwner()).thenReturn(p1);
        when(t3.getOwner()).thenReturn(p1);
        when(t4.getOwner()).thenReturn(p1);
        when(t5.getOwner()).thenReturn(p2);
        when(t6.getOwner()).thenReturn(p2);
        when(t7.getOwner()).thenReturn(p2);
        when(t8.getOwner()).thenReturn(p2);
        when(t9.getOwner()).thenReturn(p2);
        when(t10.getOwner()).thenReturn(p1);
        when(t11.getOwner()).thenReturn(p1);
        when(t12.getOwner()).thenReturn(p1);
        when(t13.getOwner()).thenReturn(p1);
        when(t14.getOwner()).thenReturn(p2);
        when(t15.getOwner()).thenReturn(p2);
        when(t16.getOwner()).thenReturn(p2);
        when(t17.getOwner()).thenReturn(p2);
        when(t18.getOwner()).thenReturn(p1);
        when(t19.getOwner()).thenReturn(p1);
        when(t20.getOwner()).thenReturn(p1);
        when(t21.getOwner()).thenReturn(p1);
        when(t22.getOwner()).thenReturn(p2);
        when(t23.getOwner()).thenReturn(p2);
        when(t24.getOwner()).thenReturn(p2);

        when(t1.getName()).thenReturn("Narnia");
        when(t2.getName()).thenReturn("Midkemia");
        when(t3.getName()).thenReturn("Oz");
        when(t4.getName()).thenReturn("Gondor");
        when(t5.getName()).thenReturn("Elantris");
        when(t6.getName()).thenReturn("Scadrial");
        when(t7.getName()).thenReturn("Roshar");
        when(t8.getName()).thenReturn("Mordor");
        when(t9.getName()).thenReturn("Hogwarts");
        when(t10.getName()).thenReturn("Westeros");
        when(t11.getName()).thenReturn("Essos");
        when(t12.getName()).thenReturn("Dorne");
        when(t13.getName()).thenReturn("Aranthia");
        when(t14.getName()).thenReturn("Drakoria");
        when(t15.getName()).thenReturn("Galadria");
        when(t16.getName()).thenReturn("Highgarden");
        when(t17.getName()).thenReturn("Winterfell");
        when(t18.getName()).thenReturn("Helvoria");
        when(t19.getName()).thenReturn("Dragonstone");
        when(t20.getName()).thenReturn("Pyke");
        when(t21.getName()).thenReturn("Oldtown");
        when(t22.getName()).thenReturn("Braavos");
        when(t23.getName()).thenReturn("Pentos");
        when(t24.getName()).thenReturn("Volantis");


        LinkedList<Territory> allTerritories = new LinkedList<Territory>() {
            {
                add(t1);
                add(t2);
                add(t3);
                add(t4);
                add(t5);
                add(t6);
                add(t7);
                add(t8);
                add(t9);
                add(t10);
                add(t11);
                add(t12);
                add(t13);
                add(t14);
                add(t15);
                add(t16);
                add(t17);
                add(t18);
                add(t19);
                add(t20);
                add(t21);
                add(t22);
                add(t23);
                add(t24);
            }
        };
        when(tmap.getTerritories()).thenReturn(allTerritories);

        LinkedList<Territory> t1n = new LinkedList<Territory>(){
            {
                add(t2);
                add(t4);
                add(t10);
            }
        };
        
        LinkedList<Territory> t2n = new LinkedList<Territory>(){
            {
                add(t1);
                add(t3);
                add(t4);
                add(t5);
                add(t11);
            }
        };

        LinkedList<Territory> t3n = new LinkedList<Territory>(){
            {
                add(t2);
                add(t5);
                add(t11);
            }
        };

        LinkedList<Territory> t4n = new LinkedList<Territory>(){
            {
                add(t1);
                add(t2);
                add(t5);
                add(t6);
                add(t10);
                add(t11);
            }
        };

        LinkedList<Territory> t5n = new LinkedList<Territory>(){
            {
                add(t2);
                add(t3);
                add(t4);
                add(t6);
                add(t7);
                add(t11);
                add(t12);
            }
        };

        LinkedList<Territory> t6n = new LinkedList<Territory>(){
            {
                add(t4);
                add(t5);
                add(t7);
                add(t12);
            }
        };

        LinkedList<Territory> t7n = new LinkedList<Territory>(){
            {
                add(t5);
                add(t6);
                add(t12);
                add(t13);
            }
        };

        LinkedList<Territory> t8n = new LinkedList<Territory>(){
            {
                add(t11);
                add(t15);
                add(t16);
            }
        };

        LinkedList<Territory> t9n = new LinkedList<Territory>(){
            {
                add(t10);
                add(t18);
            }
        };

        LinkedList<Territory> t10n = new LinkedList<Territory>(){
            {
                add(t1);
                add(t4);
                add(t9);
                add(t20);
                add(t23);
            }
        };

        LinkedList<Territory> t11n = new LinkedList<Territory>(){
            {
                add(t2);
                add(t3);
                add(t4);
                add(t5);
                add(t8);
                add(t14);
                add(t19);
                add(t17);
            }
        };

        LinkedList<Territory> t12n = new LinkedList<Territory>(){
            {
                add(t6);
                add(t7);
                add(t21);
                add(t22);
                add(t24);
                add(t5);
            }
        };

        LinkedList<Territory> t13n = new LinkedList<Territory>(){
            {
                add(t7);
                add(t22);
            }
        };

        LinkedList<Territory> t14n = new LinkedList<Territory>(){
            {
                add(t11);
                add(t21);
                add(t19);
            }
        };

        LinkedList<Territory> t15n = new LinkedList<Territory>(){
            {
                add(t8);
                add(t18);
                add(t23);
            }
        };

        LinkedList<Territory> t16n = new LinkedList<Territory>(){
            {
                add(t8);
                add(t17);
            }
        };

        LinkedList<Territory> t17n = new LinkedList<Territory>(){
            {
                add(t11);
                add(t16);
            }
        };

        LinkedList<Territory> t18n = new LinkedList<Territory>(){
            {
                add(t9);
                add(t15);
            }
        };

        LinkedList<Territory> t19n = new LinkedList<Territory>(){
            {
                add(t11);
                add(t14);
            }
        };

        LinkedList<Territory> t20n = new LinkedList<Territory>(){
            {
                add(t10);
            }
        };

        LinkedList<Territory> t21n = new LinkedList<Territory>(){
            {
                add(t12);
                add(t14);
            }
        };

        LinkedList<Territory> t22n = new LinkedList<Territory>(){
            {
                add(t12);
                add(t13);
            }
        };

        LinkedList<Territory> t23n = new LinkedList<Territory>(){
            {
                add(t10);
                add(t15);
            }
        };

        LinkedList<Territory> t24n = new LinkedList<Territory>(){
            {
                add(t12);
            }
        };
        when(tmap.getNeighbors("Narnia")).thenReturn(t1n);
        when(tmap.getNeighbors("Midkemia")).thenReturn(t2n);
        when(tmap.getNeighbors("Oz")).thenReturn(t3n);
        when(tmap.getNeighbors("Gondor")).thenReturn(t4n);
        when(tmap.getNeighbors("Elantris")).thenReturn(t5n);
        when(tmap.getNeighbors("Scadrial")).thenReturn(t6n);
        when(tmap.getNeighbors("Roshar")).thenReturn(t7n);
        when(tmap.getNeighbors("Mordor")).thenReturn(t8n);
        when(tmap.getNeighbors("Hogwarts")).thenReturn(t9n);
        when(tmap.getNeighbors("Westeros")).thenReturn(t10n);
        when(tmap.getNeighbors("Essos")).thenReturn(t11n);
        when(tmap.getNeighbors("Dorne")).thenReturn(t12n);
        when(tmap.getNeighbors("Aranthia")).thenReturn(t13n);
        when(tmap.getNeighbors("Drakoria")).thenReturn(t14n);
        when(tmap.getNeighbors("Galadria")).thenReturn(t15n);
        when(tmap.getNeighbors("Highgarden")).thenReturn(t16n);
        when(tmap.getNeighbors("Winterfell")).thenReturn(t17n);
        when(tmap.getNeighbors("Helvoria")).thenReturn(t18n);
        when(tmap.getNeighbors("Dragonstone")).thenReturn(t19n);
        when(tmap.getNeighbors("Pyke")).thenReturn(t20n);
        when(tmap.getNeighbors("Oldtown")).thenReturn(t21n);
        when(tmap.getNeighbors("Braavos")).thenReturn(t22n);
        when(tmap.getNeighbors("Pentos")).thenReturn(t23n);
        when(tmap.getNeighbors("Volantis")).thenReturn(t24n);

 

        LinkedList<Territory> p1t = new LinkedList<Territory>(){
            {
                add(t1);
                add(t2);
                add(t3);
                add(t4);
                add(t10);
                add(t11);
                add(t12);
                add(t13);
                add(t18);
                add(t19);
                add(t20);
                add(t21);
            }
        };

        LinkedList<Territory> p2t = new LinkedList<Territory>(){
            {
                add(t5);
                add(t6);
                add(t7);
                add(t8);
                add(t9);
                add(t14);
                add(t15);
                add(t16);
                add(t17);
                add(t22);
                add(t23);
                add(t24);
            }
        };


        when(p1.getTerritories()).thenReturn(p1t);
        when(p2.getTerritories()).thenReturn(p2t);


        assertEquals(twoPlayersMap.getTerritories().size(), tmap.getTerritories().size());
        int c=0;
        LinkedList<Territory> tt = (LinkedList<Territory>) tmap.getTerritories();
        for(Territory terr: twoPlayersMap.getTerritories()){          
            assertEquals(terr.getName(), tt.get(c).getName());
            assertEquals(terr.getOwner().getName(), tt.get(c).getOwner().getName());
            assertEquals(terr.getUnits(), tt.get(c).getUnits());
            // System.out.println("twoMap: "+terr.getName()+" tmap " + tt.get(c).getName());
            c++;
        }

        verify(t1).getUnits();
        verify(t2).getUnits();
        verify(t3).getUnits();
        verify(t4).getUnits();
        verify(t5).getUnits();
        verify(t6).getUnits();
        verify(t7).getUnits();
        verify(t8).getUnits();
        //....

        verify(t1).getName();
        verify(t2).getName();
        verify(t3).getName();
        verify(t4).getName();
        verify(t5).getName();
        //...

        verify(t1).getOwner();
        verify(t2).getOwner();
        verify(t3).getOwner();
        verify(t4).getOwner();
        verify(t5).getOwner();
        verify(t6).getOwner();
        verify(t7).getOwner();
        verify(t8).getOwner();
        //..

        for(Territory terr: twoPlayersMap.getTerritories()){  
            int c2=0;
            LinkedList<Territory> tt2 = (LinkedList<Territory>) tmap.getNeighbors(terr.getName());
            for(Territory terr2: twoPlayersMap.getNeighbors(terr.getName())){
                // System.out.println("twoMap: "+terr2.getName()+" tmap " + tt2.get(c2).getName());
                assertEquals(terr2.getName(), tt2.get(c2).getName());
                c2++;
            }

        }

        verify(tmap).getNeighbors("Narnia");
        verify(tmap).getNeighbors("Midkemia");
        verify(tmap).getNeighbors("Oz");
        verify(tmap).getNeighbors("Gondor");
        //...
    
    }

}
