package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.enums.GuitarType;
import com.example.GuitarSite.repository.repositoryContract.GuitarRepContract;
import com.example.GuitarSite.repository.repositoryImpl.GuitarRepContractImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
/**
 * Unit tests for the {@link GuitarRepContract} class.
 */
public class GuitarRepContractTest {

    @Mock
    private GuitarRep guitarRepMock;

    private GuitarRepContract guitarRepContract;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        guitarRepContract = new GuitarRepContractImpl(guitarRepMock);
    }
    /**
     * Tests the {@link GuitarRepContract #guitarSave(Guitar)} method.
     * Verifies that the {@link GuitarRep #save(Guitar)} method of the GuitarRep implementation is called with the provided guitar.
     * Also verifies that the returned guitar matches the input guitar.
     */
    @Test
    public void testGuitarSave() {
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
        when(guitarRepMock.save(guitar)).thenReturn(guitar);

        // Verify
        Guitar savedGuitar = guitarRepContract.guitarSave(guitar);
        Mockito.verify(guitarRepMock).save(guitar);
        assertEquals(guitar, savedGuitar);
    }
    /**
     * Tests the {@link GuitarRepContract #saveAllGuitars(List)} method.
     * Verifies that the {@link GuitarRep #saveAll(List)} method of the GuitarRep implementation is called with the provided list of guitars.
     * Also verifies that the returned list of guitars matches the input list.
     */
    @Test
    public void testSaveAllGuitars() {
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
        when(guitarRepMock.saveAll(guitars)).thenReturn(guitars);

        // Verify
        List<Guitar> savedGuitars = guitarRepContract.saveAllGuitars(guitars);
        Mockito.verify(guitarRepMock).saveAll(guitars);
        assertEquals(guitars, savedGuitars);
    }
    /**
     * Tests the {@link GuitarRepContract #getAllGuitars()} method.
     * Verifies that the {@link GuitarRep #findAll()} method of the GuitarRep implementation is called.
     * Also verifies that the returned list of guitars matches the expected list.
     */
    @Test
    public void testGetAllGuitars() {
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
        when(guitarRepMock.findAll()).thenReturn(guitarList);

        // Verify
        List<Guitar> retrievedGuitars = guitarRepContract.getAllGuitars();
        Mockito.verify(guitarRepMock).findAll();
        assertEquals(guitarList, retrievedGuitars);
    }
    /**
     * Tests the {@link GuitarRepContract #getGuitarById(int)} method.
     * Verifies that the {@link GuitarRep #findById(int)} method of the GuitarRep implementation is called with the provided guitar ID.
     */
    @Test
    public void testGuitarById() {

        int guitarId = 84;

        // Then
        Guitar retrievedGuitar = guitarRepContract.guitarById(guitarId);
        Mockito.verify(guitarRepMock).findById(guitarId);
    }
    /**
     * Tests the {@link GuitarRepContract #guitarByType(String)} method.
     * Verifies that the {@link GuitarRep #findByType(String)} method of the GuitarRep implementation is called with the provided guitar type.
     */
    @Test
    public void testGuitarByType() {

        // Verify
        Guitar retrievedGuitar = guitarRepContract.guitarByType(GuitarType.CLASSIC);
        Mockito.verify(guitarRepMock).findByType(GuitarType.CLASSIC);
    }
    /**
     * Tests the {@link GuitarRepContract #guitarUpdate(Guitar)} method.
     * Verifies that the {@link GuitarRep #findById(int)} method of the GuitarRep implementation is called with the guitar's ID.
     * Verifies that the {@link GuitarRep #save(Guitar)} method of the GuitarRep implementation is called with the updated guitar.
     * Also verifies that the returned guitar matches the updated guitar.
     */
    @Test
    public void testGuitarUpdate() {
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
        when(guitarRepMock.findById(guitar.getIdGuitar())).thenReturn(Optional.of(guitar));
        when(guitarRepMock.save(guitar)).thenReturn(updatedGuitar);

        // Test successful update
        Guitar retrievedGuitar = guitarRepContract.guitarUpdate(guitar);
        assertEquals(updatedGuitar, retrievedGuitar);
        Mockito.verify(guitarRepMock).findById(guitar.getIdGuitar());
        Mockito.verify(guitarRepMock).save(guitar);
    }
    /**
     * Tests the {@link GuitarRepContract #guitarDelete(int)} method.
     * Verifies that the {@link GuitarRep #deleteById(int)} method of the GuitarRep implementation is called with the provided guitar ID.
     * Also verifies that the deletion message returned by the service matches the expected success message.
     */
    @Test
    public void testGuitarDelete() {
        int guitarIdToDelete = 84;
        Mockito.doNothing().when(guitarRepMock).deleteById(guitarIdToDelete);
        String deletionMessage = guitarRepContract.guitarDelete(guitarIdToDelete);
        assertEquals("deleted successfully", deletionMessage);
        Mockito.verify(guitarRepMock).deleteById(guitarIdToDelete);
    }
}
