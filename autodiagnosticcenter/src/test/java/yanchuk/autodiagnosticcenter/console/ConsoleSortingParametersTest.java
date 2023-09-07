package yanchuk.autodiagnosticcenter.console;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ConsoleSortingParametersTest {

    @Test
    void testSortingTypeSetParametersHappyPath() {
        final int expectedSortingType = 2;

        final ConsoleSorting consoleSorting = Mockito.mock(ConsoleSorting.class);
        Mockito.when(consoleSorting.nextInt()).thenReturn(2);

        final ConsoleSortingParameters sortingParameters = new ConsoleSortingParameters(consoleSorting);
        final int actualSortingType = sortingParameters.sortingType();

        Assertions.assertEquals(expectedSortingType, actualSortingType);

        Mockito.verify(consoleSorting).nextInt();
        Mockito.verifyNoMoreInteractions(consoleSorting);
    }

    @Test
    void testSortingOrderSetParametersHappyPath() {
        final int expectedSortingOrder = 1;

        final ConsoleSorting consoleSorting = Mockito.mock(ConsoleSorting.class);
        Mockito.when(consoleSorting.nextInt()).thenReturn(1);

        final ConsoleSortingParameters sortingParameters = new ConsoleSortingParameters(consoleSorting);
        final int actualSortingOrder = sortingParameters.sortingOrder();

        Assertions.assertEquals(expectedSortingOrder, actualSortingOrder);

        Mockito.verify(consoleSorting).nextInt();
        Mockito.verifyNoMoreInteractions(consoleSorting);
    }
}
