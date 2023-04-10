package edu.duke.ece651.team7.shared;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UnitTest {
    @Test
    public void test_upgrade(){
        Unit u1 = new Unit();
        Unit u2 = new Unit();
        Unit u3 = new Unit();

        assertEquals(u1.getLevel(), Level.CIVILIAN);
        assertEquals(u2.getLevel(), Level.CIVILIAN);
        assertEquals(u3.getLevel(), Level.CIVILIAN);

        u1.upgrade(4);
        assertEquals(u1.getLevel(), Level.ARTILLERY);
        assertThrows(IllegalArgumentException.class, ()->u1.upgrade(0));
        assertThrows(IllegalArgumentException.class, ()->u1.upgrade(-1));
        assertThrows(IllegalArgumentException.class, ()->u1.upgrade(6));

        u1.upgrade(2);
        assertEquals(u1.getLevel(), Level.ULTRON);
    }

    @Test
    public void test_compare(){
        Unit u1 = new Unit();
        Unit u2 = new Unit();
        Unit u3 = new Unit();
        assertFalse(u1 == u2);
        assertFalse(u1.equals(u2));
    }
}