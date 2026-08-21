import edu.eci.dosw.reto1.reto1DonPepeStore;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class Challenge1Test {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<reto1DonPepeStore> constructor = reto1DonPepeStore.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testRunMethodOutputTotals() {
        reto1DonPepeStore.run();
        String output = outContent.toString();
        assertTrue("Subtotal incorrecto", output.contains("Subtotal: COP 56500.0"));
        assertTrue("Monto de descuento incorrecto", output.contains("Discount amount: COP 5650.0"));
        assertTrue("Total incorrecto", output.contains("Total: COP 50850.0"));
    }

    @Test
    public void testRunMethodOutputProducts() {
        reto1DonPepeStore.run();
        String output = outContent.toString();
        assertTrue("Falta T-shirt", output.contains("2 x T-shirt = COP 40000.0"));
        assertTrue("Falta Cookies", output.contains("3 x Cookies = COP 1500.0"));
        assertTrue("Falta Natural Juice", output.contains("5 x Natural Juice = COP 15000.0"));
    }

    @Test
    public void testRunMethodDiscountPolicy() {
        reto1DonPepeStore.run();
        String output = outContent.toString();
        assertTrue("Política de descuento incorrecta", output.contains("Discount: Frequent customer - 10%"));
    }

    @Test
    public void testRunMethodExcludedProduct() {
        reto1DonPepeStore.run();
        String output = outContent.toString();
        assertFalse("El producto Pants no debe estar en el recibo", output.contains("Pants"));
    }
}