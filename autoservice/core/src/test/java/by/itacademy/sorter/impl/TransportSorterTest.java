package by.itacademy.sorter.impl;

import by.itacademy.sorter.SortingListException;
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
    void testSortingTransportListIsNullThrowsException() {
        final TransportSorter sorter = new TransportSorter(1,1);

        final Throwable exception = assertThrows(SortingListException.class, () -> sorter.sorting(null));

        assertNotNull(exception, "ProcessorException is null");
        assertEquals("Sorting error", exception.getMessage());
    }
}
