package org.example.schermate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeTest {

    @Test
    @DisplayName("Home Constructor")
    void initialize() {
        Integer xCord = 100;
        Integer yCord = 200;
        Home homeInstance = new Home(xCord, yCord);
        assertNotNull(homeInstance);
    }
}