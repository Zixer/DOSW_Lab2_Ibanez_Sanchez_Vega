import edu.eci.dosw.reto3.reto3KingdomofVehicles;

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

public class Challenge3Test {

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
        Constructor<reto3KingdomofVehicles> constructor = reto3KingdomofVehicles.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSingleVehiclePurchaseStrict() {
        provideInput("1\n1\n1\nCorolla\n2\n");
        
        reto3KingdomofVehicles.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta confirmación de creación", output.contains("Vehicle successfully created:"));
        assertTrue("Falta el modelo del vehículo", output.contains("Model: Corolla"));
        
        assertTrue("Subtotal incorrecto", output.contains(String.format("Subtotal: $%,.2f", 30000.0)));
        assertTrue("Descuento incorrecto", output.contains(String.format("Discount: $%,.2f", 0.0)));
        assertTrue("Total final incorrecto", output.contains(String.format("Final total: $%,.2f", 30000.0)));
    }

    @Test
    public void testTwoVehiclesPurchaseStrict() {
        provideInput("1\n1\n1\nCorolla\n1\n2\n1\n1\nYate\n2\n");
        
        reto3KingdomofVehicles.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta Corolla", output.contains("Model: Corolla"));
        assertTrue("Falta Yate", output.contains("Model: Yate"));
        
        assertTrue("Subtotal incorrecto", output.contains(String.format("Subtotal: $%,.2f", 80000.0)));
        assertTrue("Descuento incorrecto", output.contains(String.format("Discount: $%,.2f", 4000.0)));
        assertTrue("Total final incorrecto", output.contains(String.format("Final total: $%,.2f", 76000.0)));
    }

    @Test
    public void testThreeVehiclesPurchaseStrict() {
        provideInput("1\n1\n1\nA\n1\n1\n1\n1\nB\n1\n1\n1\n1\nC\n2\n");
        
        reto3KingdomofVehicles.run();
        
        String output = outContent.toString();
        
        assertTrue("Cantidad de vehículos incorrecta", output.contains("Number of vehicles: 3"));
        assertTrue("Subtotal incorrecto", output.contains(String.format("Subtotal: $%,.2f", 90000.0)));
        assertTrue("Descuento incorrecto", output.contains(String.format("Discount: $%,.2f", 9000.0)));
        assertTrue("Total final incorrecto", output.contains(String.format("Final total: $%,.2f", 81000.0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCategoryThrowsException() {
        provideInput("1\n1\n9\n");
        
        reto3KingdomofVehicles.run();
    }
}