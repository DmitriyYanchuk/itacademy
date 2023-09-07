package yanchuk.autodiagnosticcenter.console;

public class ConsoleSortingParameters implements ConsoleParameters {

    private final ConsoleSorting consoleSorting;

    public ConsoleSortingParameters(final ConsoleSorting consoleSorting) {
        this.consoleSorting = consoleSorting;
    }

    @Override
    public final int sortingType() {
        int sortingType;

        for (; ; ) {
            System.out.println("Select a sort type \n 1 - Car type \n 2 - Car model \n 3 - Maintenance cost");
            sortingType = consoleSorting.nextInt();

            if (sortingType == 1 || sortingType == 2 || sortingType == 3) {
                break;
            }

            System.out.println("Please enter correct values");
        }
        return sortingType;
    }

    @Override
    public final int sortingOrder() {
        int sortingOrder;

        for (; ; ) {
            System.out.println("Select a sort order \n 1 - Direct \n 2 - Reverse");
            sortingOrder = consoleSorting.nextInt();

            if (sortingOrder == 1 || sortingOrder == 2) {
                break;
            }

            System.out.println("Please enter correct values");
        }
        return sortingOrder;
    }
}