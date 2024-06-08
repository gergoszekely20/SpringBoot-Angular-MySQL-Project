package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.enums.GuitarType;
import com.example.GuitarSite.repository.repositoryContract.GuitarRepContract;
import com.example.GuitarSite.service.implementation.GuitarServImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
/**
 * Unit tests for the GuitarServ class.
 */
public class GuitarServTest {

    @Mock
    private GuitarRepContract guitarRepMock;

    private GuitarServ guitarServ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        guitarServ = new GuitarServImp(guitarRepMock);
    }
    /**
     * Tests the saveGuitar method of GuitarServ.
     * Verifies that the guitarSave method of GuitarRepContract is called with the provided guitar.
     * Also verifies that the returned guitar matches the input guitar.
     */
    @Test
    public void testSaveGuitar() {
        // Given
        Guitar guitar = new Guitar(
                84,
                "YamahaD360",
                GuitarType.CLASSIC,
                1706,
                "it's a very nice guitar",
                45,
                "yamahad360.jpg");

        // When
        when(guitarRepMock.guitarSave(guitar)).thenReturn(guitar);

        // Verify
        Guitar savedGuitar = guitarServ.saveGuitar(guitar);
        Mockito.verify(guitarRepMock).guitarSave(guitar);
        assertEquals(guitar, savedGuitar);
    }
    /**
     * Tests the saveGuitars method of GuitarServ.
     * Verifies that the saveAllGuitars method of GuitarRepContract is called with the provided list of guitars.
     * Also verifies that the returned list of guitars matches the input list.
     */
    @Test
    public void testSaveGuitars() {
        // Given
        Guitar guitar1 = new Guitar(
                84,
                "YamahaD360",
                GuitarType.CLASSIC,
                1706,
                "it's a very nice guitar",
                45,
                "yamahad360.jpg");

        Guitar guitar2 = new Guitar(
                994,
                "YamahaYD360",
                GuitarType.BASS,
                2606,
                "it's a very nice guitar",
                20,
                "yamahayd360.jpg");

        List<Guitar> guitars = new ArrayList<>();
        guitars.add(guitar1);
        guitars.add(guitar2);

        // When
        when(guitarRepMock.saveAllGuitars(guitars)).thenReturn(guitars);

        // Verify
        List<Guitar> savedGuitars = guitarServ.saveGuitars(guitars);
        Mockito.verify(guitarRepMock).saveAllGuitars(guitars);
        assertEquals(guitars, savedGuitars);
    }
    /**
     * Tests the getGuitars method of GuitarServ.
     * Verifies that the getAllGuitars method of GuitarRepContract is called.
     * Also verifies that the returned list of guitars matches the expected list.
     */
    @Test
    public void testGetGuitars() {
        Guitar guitar1 = new Guitar(
                84,
                "YamahaD360",
                GuitarType.CLASSIC,
                1706,
                "it's a very nice guitar",
                45,
                "yamahad360.jpg");

        Guitar guitar2 = new Guitar(
                994,
                "YamahaYD360",
                GuitarType.BASS,
                2606,
                "it's a very nice guitar",
                20,
                "yamahayd360.jpg");

        List<Guitar> guitarList = new ArrayList<>();
        guitarList.add(guitar1);
        guitarList.add(guitar2);

        // When
        when(guitarRepMock.getAllGuitars()).thenReturn(guitarList);

        // Verify
        List<Guitar> retrievedGuitars = guitarServ.getGuitars();
        Mockito.verify(guitarRepMock).getAllGuitars();
        assertEquals(guitarList, retrievedGuitars);
    }
    /**
     * Tests the getGuitarById method of GuitarServ.
     * Verifies that the guitarById method of GuitarRepContract is called with the provided guitar ID.
     */
    @Test
    public void testGetGuitarById() {

        int guitarId = 84;

        // Then
        Guitar retrievedGuitar = guitarServ.getGuitarById(guitarId);
        Mockito.verify(guitarRepMock).guitarById(guitarId);
    }
    /**
     * Tests the getGuitarByType method of GuitarServ.
     * Verifies that the guitarByType method of GuitarRepContract is called with the provided guitar type.
     */
    @Test
    public void testGetGuitarByType() {

        // Verify
        Guitar retrievedGuitar = guitarServ.getGuitarByType(GuitarType.CLASSIC);
        Mockito.verify(guitarRepMock).guitarByType(GuitarType.CLASSIC);
    }
    /**
     * Tests the updateGuitar method of GuitarServ.
     * Verifies that the guitarById method of GuitarRepContract is called with the guitar's ID.
     * Verifies that the guitarSave method of GuitarRepContract is called with the updated guitar.
     * Also verifies that the returned guitar matches the updated guitar.
     */
    @Test
    public void testUpdateGuitar() {
        Guitar guitar = new Guitar(
                84,
                "YamahaD360",
                GuitarType.CLASSIC,
                1706,
                "it's a very nice guitar",
                45,
                "yamahad360.jpg");

        Guitar updatedGuitar = new Guitar(
                84,
                "Updated Model",
                GuitarType.CLASSIC,
                2000,
                "Updated description",
                50,
                "updated-image.jpg");

        // Mock guitar repository behavior
        when(guitarRepMock.guitarById(guitar.getIdGuitar())).thenReturn(guitar);
        when(guitarRepMock.guitarSave(guitar)).thenReturn(updatedGuitar);

        // Test successful update
        Guitar retrievedGuitar = guitarServ.updateGuitar(guitar);
        assertEquals(updatedGuitar, retrievedGuitar);
        Mockito.verify(guitarRepMock).guitarById(guitar.getIdGuitar());
        Mockito.verify(guitarRepMock).guitarSave(guitar);
    }
    /**
     * Tests the deleteGuitar method of GuitarServ.
     * Verifies that the guitarDelete method of GuitarRepContract is called with the provided guitar ID.
     * Also verifies that the deletion message is as expected.
     */
    @Test
    public void testDeleteGuitar() {
        int guitarIdToDelete = 84;
        when(guitarRepMock.guitarDelete(guitarIdToDelete)).thenReturn("deleted successfully");
        String deletionMessage = guitarServ.deleteGuitar(guitarIdToDelete);
        assertEquals("deleted successfully", deletionMessage);
        Mockito.verify(guitarRepMock).guitarDelete(guitarIdToDelete);
    }
}
