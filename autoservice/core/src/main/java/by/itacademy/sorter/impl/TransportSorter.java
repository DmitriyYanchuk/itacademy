package by.itacademy.sorter.impl;

import by.itacademy.sorter.SortingList;
import by.itacademy.sorter.SortingListException;

import java.util.Comparator;
import java.util.List;

public class TransportSorter implements SortingList {

    private static int SORTING_TYPE;
    private static int SORTING_ORDER;

    public TransportSorter(final int sortingType, final int sortingOrder) {
        SORTING_TYPE = sortingType;
        SORTING_ORDER = sortingOrder;
    }

    @Override
    public final List<String> sorting(final List<String> list) throws SortingListException {
        try {
            Comparator<String> comparator = Comparator.comparing(TransportSorter::getType);

            if (SORTING_ORDER == 2) {
                comparator = comparator.reversed();
            }

            return list.stream()
                    .sorted(comparator)
                    .toList();
        } catch (final RuntimeException exc) {
            throw new SortingListException("Sorting error", exc);
        }
    }

    private static String getType(final String transport) {
        final String[] parts = transport.split("\\,\\s");
        return parts[SORTING_TYPE - 1];
    }
}