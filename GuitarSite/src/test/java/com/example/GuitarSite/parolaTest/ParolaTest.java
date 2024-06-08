package com.example.GuitarSite.parolaTest;

import com.example.GuitarSite.program.Parola;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParolaTest {
    @Test
    public void testParola0() {

        String input = "ABC";
        String expected = "BCD";

        // Act
        String result = Parola.encrypt(input, 1);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testParola1() {

        String input = "ANA";
        String expected = "BOB";

        // Act
        String result = Parola.encrypt(input, 1);

        // Assert
        assertEquals(expected, result);
    }


    @Test
    public void testParola2() {

        String input = "ABC";
        String expected = "EFG";

        // Act
        String result = Parola.encrypt(input, 4);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testParola3() {

        String input = "JAVA";
        String expected = "NEZE";

        // Act
        String result = Parola.encrypt(input, 4);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testParola4() {

        String input = "XYZ";
        String expected = "BCD";

        // Act
        String result = Parola.encrypt(input, 4);

        // Assert
        assertEquals(expected, result);
    }


}


