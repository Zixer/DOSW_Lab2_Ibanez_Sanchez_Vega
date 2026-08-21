import edu.eci.dosw.reto4.reto4CurrencyExchangeScam;

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

public class Challenge4Test {

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
        Constructor<reto4CurrencyExchangeScam> constructor = reto4CurrencyExchangeScam.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSingleConversionStrict() {
        provideInput("100\n1\n1\n4\n2\n");
        
        reto4CurrencyExchangeScam.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta el registro de la transaccion individual", output.contains(String.format("%.2f %s -> %.2f %s", 100.00, "USD", 400000.00, "COP")));
        assertTrue("Falta el encabezado del resumen de intercambio", output.contains("EXCHANGE SUMMARY"));
        assertTrue("El total agrupado de la moneda de destino es incorrecto", output.contains(String.format("COP: %.2f", 400000.00)));
    }

    @Test
    public void testMultipleDestinationsFromOneSourceStrict() {
        provideInput("50\n2\n2\n1\n3\n2\n");
        
        reto4CurrencyExchangeScam.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta la conversion de EUR a USD", output.contains(String.format("%.2f %s -> %.2f %s", 50.00, "EUR", 55.00, "USD")));
        assertTrue("Falta la conversion de EUR a JPY", output.contains(String.format("%.2f %s -> %.2f %s", 50.00, "EUR", 8100.00, "JPY")));
        assertTrue("El total agrupado de USD es incorrecto", output.contains(String.format("USD: %.2f", 55.00)));
        assertTrue("El total agrupado de JPY es incorrecto", output.contains(String.format("JPY: %.2f", 8100.00)));
    }

    @Test
    public void testMultipleTransactionsGroupingStrict() {
        provideInput("10\n1\n1\n4\n1\n20\n2\n1\n4\n2\n");
        
        reto4CurrencyExchangeScam.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta la conversion de USD a COP", output.contains(String.format("%.2f %s -> %.2f %s", 10.00, "USD", 40000.00, "COP")));
        assertTrue("Falta la conversion de EUR a COP", output.contains(String.format("%.2f %s -> %.2f %s", 20.00, "EUR", 88000.00, "COP")));
        assertTrue("El total acumulado (Stream groupingBy) de COP es incorrecto", output.contains(String.format("COP: %.2f", 128000.00)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCurrencyThrowsException() {
        provideInput("100\n9\n");
        
        reto4CurrencyExchangeScam.run();
    }
}