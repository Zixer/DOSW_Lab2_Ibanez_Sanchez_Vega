import edu.eci.dosw.reto6.reto6TalktoTechnicalSupport;

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

public class Challenge6Test {

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
        Constructor<reto6TalktoTechnicalSupport> constructor = reto6TalktoTechnicalSupport.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testBasicLowTicketResolvedStrict() {
        provideInput("Password reset\n1\n1\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("Falta la descripcion del ticket", output.contains("Password reset"));
        assertTrue("Falta el nivel BASIC", output.contains("BASIC"));
        assertTrue("Falta la prioridad LOW", output.contains("LOW"));
        assertTrue("El ticket basico debe ser resuelto", output.contains("RESOLVED"));
        assertTrue("El tecnico basico debe resolver el ticket", output.contains("Basic Technician"));
    }

    @Test
    public void testIntermediateTicketStrict() {
        provideInput("Database connection error\n2\n2\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("Falta la descripcion del ticket", output.contains("Database connection error"));
        assertTrue("Falta el nivel INTERMEDIATE", output.contains("INTERMEDIATE"));
        assertTrue("Falta la prioridad MEDIUM", output.contains("MEDIUM"));
        assertTrue("El ticket debe ser resuelto", output.contains("RESOLVED"));
        assertTrue("El tecnico intermedio debe resolver el ticket", output.contains("Intermediate Technician"));
    }

    @Test
    public void testAdvancedTicketStrict() {
        provideInput("Server configuration failure\n3\n2\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("Falta la descripcion del ticket", output.contains("Server configuration failure"));
        assertTrue("Falta el nivel ADVANCED", output.contains("ADVANCED"));
        assertTrue("Falta la prioridad MEDIUM", output.contains("MEDIUM"));
        assertTrue("El ticket debe ser resuelto", output.contains("RESOLVED"));
        assertTrue("El tecnico avanzado debe resolver el ticket", output.contains("Advanced Technician"));
    }

    @Test
    public void testTicketMovesThroughChainStrict() {
        provideInput("Advanced network problem\n3\n2\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("El ticket debe pasar por el tecnico basico", output.contains("Basic Technician"));
        assertTrue("El ticket debe pasar por el tecnico intermedio", output.contains("Intermediate Technician"));
        assertTrue("El ticket debe llegar al tecnico avanzado", output.contains("Advanced Technician"));
        assertTrue("El ticket debe quedar resuelto", output.contains("RESOLVED"));
    }

    @Test
    public void testPendingEscalationStrict() {
        provideInput("Critical system failure\n3\n3\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("Falta la descripcion del ticket", output.contains("Critical system failure"));
        assertTrue("Falta el nivel ADVANCED", output.contains("ADVANCED"));
        assertTrue("Falta la prioridad HIGH", output.contains("HIGH"));
        assertTrue("El ticket debe quedar pendiente de escalamiento", output.contains("PENDING ESCALATION"));
    }

    @Test
    public void testMultipleTicketsStrict() {
        provideInput("Password reset\n1\n1\n1\nDatabase error\n2\n2\n1\nServer failure\n3\n2\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("Falta el Ticket #1", output.contains("Ticket #1"));
        assertTrue("Falta el Ticket #2", output.contains("Ticket #2"));
        assertTrue("Falta el Ticket #3", output.contains("Ticket #3"));
        assertTrue("Falta la descripcion del primer ticket", output.contains("Password reset"));
        assertTrue("Falta la descripcion del segundo ticket", output.contains("Database error"));
        assertTrue("Falta la descripcion del tercer ticket", output.contains("Server failure"));
        assertTrue("Falta el tecnico basico", output.contains("Basic Technician"));
        assertTrue("Falta el tecnico intermedio", output.contains("Intermediate Technician"));
        assertTrue("Falta el tecnico avanzado", output.contains("Advanced Technician"));
    }

    @Test
    public void testTicketsByLevelStatisticsStrict() {
        provideInput("Basic issue\n1\n1\n1\nIntermediate issue\n2\n2\n1\nAdvanced issue\n3\n2\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("La cantidad de tickets BASIC es incorrecta", output.contains("BASIC: 1"));
        assertTrue("La cantidad de tickets INTERMEDIATE es incorrecta", output.contains("INTERMEDIATE: 1"));
        assertTrue("La cantidad de tickets ADVANCED es incorrecta", output.contains("ADVANCED: 1"));
    }

    @Test
    public void testResolvedAndPendingStatisticsStrict() {
        provideInput("Basic issue\n1\n1\n1\nCritical issue\n3\n3\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("La cantidad de tickets resueltos es incorrecta", output.contains("Resolved tickets: 1"));
        assertTrue("La cantidad de tickets pendientes es incorrecta", output.contains("Pending tickets: 1"));
    }

    @Test
    public void testAveragePriorityStrict() {
        provideInput("Low priority issue\n1\n1\n1\nMedium priority issue\n2\n2\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("El promedio de prioridad de tickets resueltos es incorrecto", output.contains(String.format("Average priority of resolved tickets: %.2f", 1.50)));
    }

    @Test
    public void testSummaryStrict() {
        provideInput("Password reset\n1\n1\n2\n");

        reto6TalktoTechnicalSupport.run();

        String output = outContent.toString();

        assertTrue("Falta el resumen final", output.contains("SUPPORT SUMMARY"));
        assertTrue("Falta la descripcion del ticket en el resumen", output.contains("Password reset"));
        assertTrue("Falta el estado del ticket", output.contains("RESOLVED"));
        assertTrue("Falta el tecnico que resolvio el ticket", output.contains("Basic Technician"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDifficultyThrowsException() {
        provideInput("Invalid ticket\n9\n");

        reto6TalktoTechnicalSupport.run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPriorityThrowsException() {
        provideInput("Invalid ticket\n1\n9\n");

        reto6TalktoTechnicalSupport.run();
    }
}