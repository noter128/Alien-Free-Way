package com.tg04.alienfreewaytesting.model.game;

import com.tg04.alienfreeway.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testPositionInitialization() {
        Position position = new Position(5, 10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    public void testGetLeft() {
        Position position = new Position(5, 10);
        Position left = position.getLeft();
        assertEquals(4, left.getX());
        assertEquals(10, left.getY());
    }

    @Test
    public void testGetRight() {
        Position position = new Position(5, 10);
        Position right = position.getRight();
        assertEquals(6, right.getX());
        assertEquals(10, right.getY());
    }

    @Test
    public void testGetUp() {
        Position position = new Position(5, 10);
        Position up = position.getUp();
        assertEquals(5, up.getX());
        assertEquals(9, up.getY());
    }

    @Test
    public void testGetDown() {
        Position position = new Position(5, 10);
        Position down = position.getDown();
        assertEquals(5, down.getX());
        assertEquals(11, down.getY());
    }


    @Test
    public void testEqualsAndHashCode() {
        Position position1 = new Position(5, 10);
        Position position2 = new Position(5, 10);
        Position position3 = new Position(6, 10);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
        assertEquals(position1.hashCode(), position2.hashCode());
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }

    @Test
    public void testSetXAndSetY() {
        Position position = new Position(5, 10);
        position.setX(8);
        position.setY(12);

        assertEquals(8, position.getX());
        assertEquals(12, position.getY());
    }
}
