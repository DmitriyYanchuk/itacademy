package yanchuk.autodiagnosticcenter.editor;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListTransportEditorTest {

    @Test
    void testValidListSuccessfulCompilation() throws ListTransportException {
        final List<String> transportList = new ArrayList<>();
        transportList.add("motorbike, Ninja ZX-14");
        transportList.add("automobile, Audi Q7");
        transportList.add("automobile, Audi Q9!№");

        final List<String> comparableList = new ArrayList<>();
        comparableList.add("motorbike, Ninja ZX-14, 10");
        comparableList.add("automobile, Audi Q7, 20");

        final ListTransportEditor editor = new ListTransportEditor();
        final List<String> validList = editor.validList(transportList);

        assertNotNull(validList, "Array is null");
        assertEquals(validList, comparableList);
    }

    @Test
    void testInvalidListSuccessfulCompilation() throws ListTransportException {
        final List<String> transportList = new ArrayList<>();
        transportList.add("automobile, Audi Q7");
        transportList.add("automobile, Audi Q9!№");
        transportList.add("automobile, Mazda CX7");
        transportList.add("motorbike, Ninja **");

        final List<String> comparableList = new ArrayList<>();
        comparableList.add("automobile, Audi Q9!№,  ");
        comparableList.add("motorbike, Ninja **,  ");

        final ListTransportEditor editor = new ListTransportEditor();
        final List<String> invalidList = editor.invalidList(transportList);

        assertNotNull(invalidList, "Array is null");
        assertEquals(invalidList, comparableList);
    }

    @Test
    void testValidListTransportException() {
        final List<String> transportList = null;
        final ListTransportEditor editor = new ListTransportEditor();

        final var exception = assertThrows(ListTransportException.class, () -> editor.validList(transportList));

        assertNotNull(exception, "ListTransportException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }

    @Test
    void testInvalidListTransportException() {
        final List<String> transportList = null;
        final ListTransportEditor editor = new ListTransportEditor();

        final var exception = assertThrows(ListTransportException.class, () -> editor.invalidList(transportList));

        assertNotNull(exception, "ListTransportException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }
}