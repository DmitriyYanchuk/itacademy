package yanchuk.autodiagnosticcenter.sorter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportSorterTest {

    @Test
    void testListSortingSuccessfully() throws SortingListException {
        final List<String> transportList = new ArrayList<>();
        transportList.add("minibus, Sprinter264, 30");
        transportList.add("automobile, Audi Q7, 20");
        transportList.add("motorbike, Ninja ZX-14, 10");
        transportList.add("automobile, BMW M5, 20");

        final List<String> comparableList = new ArrayList<>();
        comparableList.add("automobile, Audi Q7, 20");
        comparableList.add("automobile, BMW M5, 20");
        comparableList.add("minibus, Sprinter264, 30");
        comparableList.add("motorbike, Ninja ZX-14, 10");

        final TransportSorter sorter = new TransportSorter(1,1);
        final List<String> sortingList = sorter.sorting(transportList);

        assertNotNull(sortingList, "Array is null");
        assertEquals(sortingList, comparableList);
    }

    @Test
    void testTransportSorterException() {
        final List<String> transportList = null;
        final TransportSorter sorter = new TransportSorter(1,1);

        final var exception = assertThrows(SortingListException.class, () -> sorter.sorting(transportList));

        assertNotNull(exception, "ListTransportException is null");
        assertEquals("Sorting error", exception.getMessage());
    }
}
