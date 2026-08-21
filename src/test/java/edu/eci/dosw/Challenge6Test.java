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
        Constructor<reto6TechnicalSupport> constructor = reto6TechnicalSupport.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testBasicLowTicketResolvedByBasicTechnicianStrict() {
        provideInput("Password reset\n1\n1\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Falta el ticket creado", output.contains("Ticket #1"));
        assertTrue("Falta la descripcion", output.contains("Password reset"));
        assertTrue("Falta la dificultad BASIC", output.contains("Difficulty: BASIC"));
        assertTrue("Falta la prioridad LOW", output.contains("Priority: LOW"));
        assertTrue("El tecnico basico debe resolver el ticket", output.contains("Resolved by: Daniel - Basic Technician"));
    }

    @Test
    public void testBasicHighTicketPassesToIntermediateStrict() {
        provideInput("Printer failure\n1\n3\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("El tecnico basico debe revisar el ticket", output.contains("Daniel - Basic Technician is reviewing Ticket #1"));
        assertTrue("El tecnico basico no debe resolver prioridad HIGH", output.contains("Daniel - Basic Technician cannot resolve Ticket #1"));
        assertTrue("Debe pasar al tecnico intermedio", output.contains("Passing ticket to Laura - Intermediate Technician"));
        assertTrue("El tecnico intermedio debe resolver el ticket", output.contains("Resolved by: Laura - Intermediate Technician"));
    }

    @Test
    public void testIntermediateTicketResolvedByIntermediateStrict() {
        provideInput("Database connection error\n2\n2\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("El ticket debe pasar por el tecnico basico", output.contains("Daniel - Basic Technician"));
        assertTrue("El ticket debe llegar al tecnico intermedio", output.contains("Laura - Intermediate Technician"));
        assertTrue("El tecnico intermedio debe resolver el ticket", output.contains("Resolved by: Laura - Intermediate Technician"));
        assertTrue("Debe mostrarse el recorrido completo", output.contains("Daniel - Basic Technician -> Laura - Intermediate Technician"));
    }

    @Test
    public void testAdvancedMediumTicketResolvedByAdvancedStrict() {
        provideInput("Server configuration failure\n3\n2\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Debe revisar el tecnico basico", output.contains("Daniel - Basic Technician is reviewing Ticket #1"));
        assertTrue("Debe revisar el tecnico intermedio", output.contains("Laura - Intermediate Technician is reviewing Ticket #1"));
        assertTrue("Debe revisar el tecnico avanzado", output.contains("Carlos - Advanced Technician is reviewing Ticket #1"));
        assertTrue("El tecnico avanzado debe resolver el ticket", output.contains("Resolved by: Carlos - Advanced Technician"));
    }

    @Test
    public void testAdvancedHighTicketPendingEscalationStrict() {
        provideInput("Critical server failure\n3\n3\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("El ticket debe pasar por el tecnico basico", output.contains("Daniel - Basic Technician"));
        assertTrue("El ticket debe pasar por el tecnico intermedio", output.contains("Laura - Intermediate Technician"));
        assertTrue("El ticket debe pasar por el tecnico avanzado", output.contains("Carlos - Advanced Technician"));
        assertTrue("El ticket debe quedar pendiente", output.contains("PENDING ESCALATION"));
    }

    @Test
    public void testTechnicianPathStrict() {
        provideInput("Network architecture problem\n3\n1\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Debe mostrarse el recorrido completo", output.contains("Daniel - Basic Technician -> Laura - Intermediate Technician -> Carlos - Advanced Technician"));
        assertTrue("El tecnico avanzado debe resolver el ticket", output.contains("Resolved by: Carlos - Advanced Technician"));
    }

    @Test
    public void testMultipleTicketsStrict() {
        provideInput("Password reset\n1\n1\n1\nDatabase failure\n2\n3\n1\nCritical server failure\n3\n3\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Falta Ticket #1", output.contains("Ticket #1"));
        assertTrue("Falta Ticket #2", output.contains("Ticket #2"));
        assertTrue("Falta Ticket #3", output.contains("Ticket #3"));
        assertTrue("El primer ticket debe ser resuelto por el tecnico basico", output.contains("Resolved by: Daniel - Basic Technician"));
        assertTrue("El segundo ticket debe ser resuelto por el tecnico intermedio", output.contains("Resolved by: Laura - Intermediate Technician"));
        assertTrue("El tercer ticket debe quedar pendiente", output.contains("Status: PENDING ESCALATION"));
    }

    @Test
    public void testTicketsByDifficultyStatisticsStrict() {
        provideInput("Issue one\n1\n1\n1\nIssue two\n2\n1\n1\nIssue three\n3\n2\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Debe aparecer BASIC: 1", output.contains("BASIC: 1"));
        assertTrue("Debe aparecer INTERMEDIATE: 1", output.contains("INTERMEDIATE: 1"));
        assertTrue("Debe aparecer ADVANCED: 1", output.contains("ADVANCED: 1"));
    }

    @Test
    public void testResolvedAndPendingStatisticsStrict() {
        provideInput("Basic issue\n1\n1\n1\nCritical issue\n3\n3\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Debe haber un ticket resuelto", output.contains("Resolved tickets: 1"));
        assertTrue("Debe haber un ticket pendiente", output.contains("Pending tickets: 1"));
    }

    @Test
    public void testEscalatedTicketsStatisticsStrict() {
        provideInput("Basic problem\n1\n1\n1\nIntermediate problem\n2\n2\n1\nAdvanced problem\n3\n2\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Debe contar dos tickets que pasaron por varios tecnicos", output.contains("Tickets that passed through multiple technicians: 2"));
    }

    @Test
    public void testAveragePriorityResolvedTicketsStrict() {
        provideInput("Issue one\n1\n1\n1\nIssue two\n1\n3\n1\nIssue three\n3\n3\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("El promedio de prioridad debe ser 2.00", output.contains("Average priority of resolved tickets: 2.00"));
    }

    @Test
    public void testSummaryHeaderStrict() {
        provideInput("Simple problem\n1\n1\n2\n");
        reto6TechnicalSupport.run();
        String output = outContent.toString();

        assertTrue("Falta el encabezado SUPPORT SUMMARY", output.contains("SUPPORT SUMMARY"));
        assertTrue("Falta el encabezado STATISTICS", output.contains("STATISTICS"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDifficultyThrowsException() {
        provideInput("Invalid difficulty\n9\n");
        reto6TechnicalSupport.run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPriorityThrowsException() {
        provideInput("Invalid priority\n1\n9\n");
        reto6TechnicalSupport.run();
    }
}