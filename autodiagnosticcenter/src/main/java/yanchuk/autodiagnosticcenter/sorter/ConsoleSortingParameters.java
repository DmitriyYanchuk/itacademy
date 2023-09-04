package yanchuk.autodiagnosticcenter.sorter;

import java.util.Scanner;

public class ConsoleSortingParameters implements ConsoleSorting {

    private final Scanner scan;

    public ConsoleSortingParameters() {
        this.scan = new Scanner(System.in);
    }

    @Override
    public int sortingType() {
        int sortingType;

        for (; ; ) {
            System.out.println("Select a sort type \n 1 - Car type \n 2 - Car model \n 3 - Maintenance cost");
            sortingType = scan.nextInt();

            if (sortingType == 1 || sortingType == 2 || sortingType == 3) {
                break;
            }

            System.out.println("Please enter correct values");
        }
        return sortingType;
    }

    @Override
    public int sortingOrder() {
        int sortingOrder;

        for (; ; ) {
            System.out.println("Select a sort order \n 1 - Direct \n 2 - Reverse");
            sortingOrder = scan.nextInt();

            if (sortingOrder == 1 || sortingOrder == 2) {
                break;
            }

            System.out.println("Please enter correct values");
        }
        return sortingOrder;
    }
}