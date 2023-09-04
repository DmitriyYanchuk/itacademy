package yanchuk.autodiagnosticcenter.sorter;

import java.util.List;

public interface SortingList {

    List<String> sorting(List<String> list) throws SortingListException;
}
