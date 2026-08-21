import edu.eci.dosw.reto5.reto5CustomizedCoffed;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class Challenge5Test {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<reto5CustomizedCoffed> constructor = reto5CustomizedCoffed.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testBasicCoffeeNoToppingsStrict() {
        provideInput("0\n2\n");
        
        reto5CustomizedCoffed.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta la descripcion del cafe basico", output.contains("Description: Basic Coffee"));
        assertTrue("El precio base es incorrecto", output.contains(String.format("Price: COP %,.0f", 5000.0)));
        assertTrue("El total del resumen es incorrecto", output.contains(String.format("TOTAL ORDER: COP %,.0f", 5000.0)));
    }

    @Test
    public void testMultiplePredefinedToppingsStrict() {
        provideInput("1\n2\n0\n2\n");
        
        reto5CustomizedCoffed.run();
        
        String output = outContent.toString();
        
        assertTrue("La descripcion concatenada de toppings es incorrecta", output.contains("Description: Basic Coffee, Milk, Chocolate"));
        assertTrue("El precio total con toppings fijos es incorrecto", output.contains(String.format("Price: COP %,.0f", 7500.0)));
    }

    @Test
    public void testCustomToppingStrict() {
        provideInput("6\nVanilla Syrup\n850\n0\n2\n");
        
        reto5CustomizedCoffed.run();
        
        String output = outContent.toString();
        
        assertTrue("La descripcion del topping personalizado es incorrecta", output.contains("Description: Basic Coffee, Vanilla Syrup"));
        assertTrue("El precio con topping personalizado es incorrecto", output.contains(String.format("Price: COP %,.0f", 5850.0)));
    }

    @Test
    public void testMultipleCoffeesOrderTotalStrict() {
        provideInput("1\n0\n1\n3\n0\n2\n");
        
        reto5CustomizedCoffed.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta el Cafe #1 en el resumen", output.contains("Coffee #1"));
        assertTrue("Falta la descripcion del Cafe #1", output.contains("Description: Basic Coffee, Milk"));
        assertTrue("El precio individual del Cafe #1 es incorrecto", output.contains(String.format("Price: COP %,.0f", 6000.0)));
        
        assertTrue("Falta el Cafe #2 en el resumen", output.contains("Coffee #2"));
        assertTrue("Falta la descripcion del Cafe #2", output.contains("Description: Basic Coffee, Caramel"));
        assertTrue("El precio individual del Cafe #2 es incorrecto", output.contains(String.format("Price: COP %,.0f", 6200.0)));
        
        assertTrue("La sumatoria del total de la orden (Stream) es incorrecta", output.contains(String.format("TOTAL ORDER: COP %,.0f", 12200.0)));
    }
}