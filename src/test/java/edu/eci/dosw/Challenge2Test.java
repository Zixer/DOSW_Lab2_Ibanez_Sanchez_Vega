import edu.eci.dosw.reto2.reto2FiveStarChef;

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

public class Challenge2Test {

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
        Constructor<reto2FiveStarChef> constructor = reto2FiveStarChef.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testRunValidInputStrict() {
        provideInput("1\n6\n7\n8\n");
        
        reto2FiveStarChef.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta confirmación del Pan", output.contains("Pan agregado."));
        assertTrue("Falta confirmación del Tocino", output.contains("Tocino agregado."));
        assertTrue("Falta confirmación de Salsa BBQ", output.contains("Salsa BBQ agregada."));

        assertTrue("El formato del Pan en el resumen es incorrecto", output.contains("- Pan - $2.00"));
        assertTrue("El formato del Tocino en el resumen es incorrecto", output.contains("- Tocino - $2.00"));
        assertTrue("El formato de la Salsa BBQ en el resumen es incorrecto", output.contains("- Salsa BBQ - $1.00"));

        assertTrue("El cálculo del precio final falló o el formato no es %.2f", output.contains("Precio final: $5.00"));
    }

    @Test
    public void testRunInvalidInputStrict() {
        provideInput("9\n8\n");
        
        reto2FiveStarChef.run();
        
        String output = outContent.toString();
        
        assertTrue("El programa no notificó la opción inválida", output.contains("Opción inválida."));
        
        assertTrue("El precio de una hamburguesa sin ingredientes debe ser $0.00", output.contains("Precio final: $0.00"));
        assertFalse("No debería haber pan en el resumen", output.contains("- Pan"));
    }

    @Test
    public void testRunDuplicateIngredientsStrict() {
        provideInput("2\n2\n3\n3\n8\n");
        
        reto2FiveStarChef.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta confirmación de la Carne", output.contains("Carne agregada."));
        assertTrue("Falta confirmación del Queso", output.contains("Queso agregado."));
        
        assertTrue("El resumen debe contener el formato de Carne", output.contains("- Carne - $5.00"));
        assertTrue("El resumen debe contener el formato de Queso", output.contains("- Queso - $1.50"));
        
        assertTrue("El cálculo del precio con ingredientes duplicados falló", output.contains("Precio final: $13.00"));
    }

    @Test
    public void testRunEmptyOrderStrict() {
        provideInput("8\n");
        
        reto2FiveStarChef.run();
        
        String output = outContent.toString();
        
        assertTrue("Falta el encabezado del programa", output.contains("RETO 2 - CHEF HAMBURGUESA"));
        
        assertFalse("No debería confirmar Carne si no se pidió", output.contains("Carne agregada."));
        assertFalse("No debería incluir Pan en el resumen de una orden vacía", output.contains("- Pan"));
        
        assertTrue("Una orden vacía debe costar exactamente $0.00", output.contains("Precio final: $0.00"));
    }
}