package oving4.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import org.junit.jupiter.api.Test;

public class CoffeeCupTest {
    
    CoffeeCup tom;
    CoffeeCup nesten;

    @BeforeEach
    public void setup() {
        tom = new CoffeeCup();
        nesten = new CoffeeCup(40, 35);
    }

    @Test
    @DisplayName("Test that both constructors work")
    void testConstructor() {
        assertEquals(40, nesten.getCapacity());
        assertEquals(35, nesten.getCurrentVolume());
        assertEquals(0, tom.getCapacity());
        assertEquals(0, tom.getCurrentVolume());
        assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(20.0, -2.0);
        });
    }

    @Test
    @DisplayName("Test fillCoffee")
    void testFillCoffee() {
        assertThrows(IllegalArgumentException.class, () -> {nesten.fillCoffee(6);}, "Du kan ikke fylle på mer en kapasiteten");
        tom.increaseCupSize(40);
        tom.fillCoffee(39);
        assertEquals(39, tom.getCurrentVolume());
    }

    @Test
    @DisplayName("Test increaseCupSize")
    void testIncreaseCupSize() {
        tom.increaseCupSize(40);
        assertEquals(40, tom.getCapacity());
    }

    @Test
    @DisplayName("Test drink coffe")
    void testDrinkCoffee() {
        nesten.drinkCoffee(5);
        assertEquals(30, nesten.getCurrentVolume());
        assertThrows(IllegalArgumentException.class, () -> {tom.drinkCoffee(5);}, "Du kan ikke drikke kaffe nå");
    }
    
    


    


}
