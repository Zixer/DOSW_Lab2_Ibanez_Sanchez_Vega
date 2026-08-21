import edu.eci.dosw.reto7.reto7MagicRemoteControl;

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

public class Challenge7Test {

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
        Constructor<reto7MagicRemoteControl> constructor = reto7MagicRemoteControl.class.getDeclaredConstructor();
        assertTrue("El constructor debe ser privado", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testTurnLightOnStrict() {
        provideInput("1\nDaniel\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("No se ejecuto la accion de encender la luz", output.contains("Action executed: Turn light ON"));
        assertTrue("No se registro correctamente el usuario", output.contains("User: Daniel"));
        assertTrue("No se registro el dispositivo", output.contains("Device: Living Room Light"));
        assertTrue("La luz debe terminar encendida", output.contains("Living Room Light: ON"));
    }

    @Test
    public void testOpenDoorStrict() {
        provideInput("2\nLaura\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("No se ejecuto la accion de abrir la puerta", output.contains("Action executed: Open door"));
        assertTrue("No se registro correctamente el usuario", output.contains("User: Laura"));
        assertTrue("No se registro Main Door", output.contains("Device: Main Door"));
        assertTrue("La puerta debe terminar abierta", output.contains("Main Door: OPEN"));
    }

    @Test
    public void testSetVolumeWithParameterStrict() {
        provideInput("3\nSergio\n80\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("No se ejecuto el cambio de volumen", output.contains("Action executed: Set volume to 80"));
        assertTrue("No se registro correctamente el usuario", output.contains("User: Sergio"));
        assertTrue("No se registro el Music System", output.contains("Device: Music System"));
        assertTrue("El volumen final debe ser 80", output.contains("Music Volume: 80"));
    }

    @Test
    public void testSetBlindPositionStrict() {
        provideInput("4\nCarlos\n70\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("No se ejecuto el cambio de posicion", output.contains("Action executed: Set blind position to 70%"));
        assertTrue("No se registro correctamente el usuario", output.contains("User: Carlos"));
        assertTrue("No se registro la persiana", output.contains("Device: Living Room Blind"));
        assertTrue("La persiana debe terminar en 70%", output.contains("Living Room Blind: 70%"));
    }

    @Test
    public void testUndoVolumeStrict() {
        provideInput("3\nDaniel\n80\n5\n1\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("La accion de volumen no fue ejecutada", output.contains("Action executed: Set volume to 80"));
        assertTrue("La accion de volumen no fue deshecha", output.contains("Action undone: Set volume to 80"));
        assertTrue("La accion debe quedar como UNDONE", output.contains("Status: UNDONE"));
        assertTrue("El usuario debe mantenerse en el historial", output.contains("User: Daniel"));
        assertTrue("El volumen debe regresar a 20", output.contains("Music Volume: 20"));
    }

    @Test
    public void testUndoBlindPositionStrict() {
        provideInput("4\nLaura\n60\n5\n1\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("La accion de la persiana no fue ejecutada", output.contains("Action executed: Set blind position to 60%"));
        assertTrue("La accion de la persiana no fue deshecha", output.contains("Action undone: Set blind position to 60%"));
        assertTrue("La accion debe quedar como UNDONE", output.contains("Status: UNDONE"));
        assertTrue("La persiana debe regresar a 0%", output.contains("Living Room Blind: 0%"));
    }

    @Test
    public void testMultipleActionsAndUsersStrict() {
        provideInput("1\nDaniel\n2\nLaura\n3\nSergio\n65\n4\nCarlos\n40\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("Falta la accion de la luz", output.contains("Turn light ON"));
        assertTrue("Falta la accion de la puerta", output.contains("Open door"));
        assertTrue("Falta la accion de volumen", output.contains("Set volume to 65"));
        assertTrue("Falta la accion de la persiana", output.contains("Set blind position to 40%"));
        assertTrue("Falta Daniel", output.contains("User: Daniel"));
        assertTrue("Falta Laura", output.contains("User: Laura"));
        assertTrue("Falta Sergio", output.contains("User: Sergio"));
        assertTrue("Falta Carlos", output.contains("User: Carlos"));
        assertTrue("La luz debe terminar ON", output.contains("Living Room Light: ON"));
        assertTrue("La puerta debe terminar OPEN", output.contains("Main Door: OPEN"));
        assertTrue("El volumen debe terminar en 65", output.contains("Music Volume: 65"));
        assertTrue("La persiana debe terminar en 40%", output.contains("Living Room Blind: 40%"));
    }

    @Test
    public void testUndoSpecificActionStrict() {
        provideInput("1\nDaniel\n3\nLaura\n90\n4\nCarlos\n75\n5\n2\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("Se debe deshacer la accion numero 2", output.contains("Action undone: Set volume to 90"));
        assertTrue("El volumen debe regresar a 20", output.contains("Music Volume: 20"));
        assertTrue("La luz debe permanecer encendida", output.contains("Living Room Light: ON"));
        assertTrue("La persiana debe permanecer en 75%", output.contains("Living Room Blind: 75%"));
        assertTrue("La accion debe seguir en el historial", output.contains("Set volume to 90"));
        assertTrue("Debe aparecer como UNDONE", output.contains("Status: UNDONE"));
    }

    @Test
    public void testCompleteHistoryStrict() {
        provideInput("1\nDaniel\n3\nLaura\n50\n6\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("Falta el encabezado del historial", output.contains("ACTION HISTORY"));
        assertTrue("Falta la primera accion", output.contains("1. Turn light ON"));
        assertTrue("Falta la segunda accion", output.contains("2. Set volume to 50"));
        assertTrue("Falta Living Room Light", output.contains("Device: Living Room Light"));
        assertTrue("Falta Music System", output.contains("Device: Music System"));
        assertTrue("Las acciones deben aparecer como ACTIVE", output.contains("Status: ACTIVE"));
    }

    @Test
    public void testCannotUndoSameActionTwiceStrict() {
        provideInput("3\nDaniel\n80\n5\n1\n5\n1\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("El primer undo debe ejecutarse", output.contains("Action undone: Set volume to 80"));
        assertTrue("No debe permitir un segundo undo", output.contains("This action was already undone."));
        assertTrue("El volumen debe permanecer en 20", output.contains("Music Volume: 20"));
    }

    @Test
    public void testInvalidUndoPositionStrict() {
        provideInput("1\nDaniel\n5\n9\n0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("Debe detectar una accion invalida", output.contains("Invalid action number."));
        assertTrue("La luz debe permanecer encendida", output.contains("Living Room Light: ON"));
    }

    @Test
    public void testInitialDeviceStatesStrict() {
        provideInput("0\n");
        reto7MagicRemoteControl.run();
        String output = outContent.toString();

        assertTrue("La luz inicialmente debe estar OFF", output.contains("Living Room Light: OFF"));
        assertTrue("La puerta inicialmente debe estar CLOSED", output.contains("Main Door: CLOSED"));
        assertTrue("El volumen inicial debe ser 20", output.contains("Music Volume: 20"));
        assertTrue("La persiana inicialmente debe estar en 0%", output.contains("Living Room Blind: 0%"));
    }
}