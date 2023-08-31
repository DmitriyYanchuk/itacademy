package yanchuk.autodiagnosticcenter.sorter;

import java.util.*;

public class TransportSorter implements SortingList {

    private static int SORTING_TYPE;
    private static int SORTING_ORDER;

    public TransportSorter() {
        final Scanner scan = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select a sort type \n 1 - Car type \n 2 - Car model \n 3 - Maintenance cost");
            final int sortingType = scan.nextInt();
            switch (sortingType) {
                case 1 -> exit = true;
                case 2 -> exit = true;
                case 3 -> exit = true;
                default -> System.out.println("Please enter correct values");
            }

            if (exit) {
                System.out.println("Select a sort order \n 1 - Direct \n 2 - Reverse");
                final int sortingOrder = scan.nextInt();
                switch (sortingOrder) {
                    case 1 -> exit = true;
                    case 2 -> exit = true;
                    default -> {
                        exit = false;
                        System.out.println("Please enter correct values");
                    }
                }

                this.SORTING_ORDER = sortingOrder;
                this.SORTING_TYPE = sortingType;
            }
        }
    }

    @Override
    public final List<String> sorting(List<String> list) throws SortingListException {
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
        final String type = parts[SORTING_TYPE - 1];
        return type;
    }
}