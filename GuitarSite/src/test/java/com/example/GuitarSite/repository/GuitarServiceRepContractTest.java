package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.GuitarService;

import com.example.GuitarSite.repository.repositoryContract.GuitarServiceRepContract;
import com.example.GuitarSite.repository.repositoryImpl.GuitarServiceRepContractImpl;
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
 * This is the GuitarServiceRepContractTest class, which defines unit tests
 * for the methods of the GuitarServiceRepContract interface.
 */
public class GuitarServiceRepContractTest {
    @Mock
    private GuitarServiceRep guitarServiceRepMock;

    private GuitarServiceRepContract guitarServiceRepContract;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        guitarServiceRepContract = new GuitarServiceRepContractImpl(guitarServiceRepMock);
    }
    /**
     * Tests the {@link GuitarServiceRepContract #guitarServiceSave(GuitarService)} method.
     * Verifies that the {@link GuitarServiceRep #save(GuitarService)} method of the GuitarServiceRep implementation is called with the provided guitar service.
     * Also verifies that the returned guitar service matches the input guitar service.
     */
    @Test
    public void testGuitarServiceSave() {
        //given
        GuitarService guitarService = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");

        //when
        when(guitarServiceRepMock.save(guitarService)).thenReturn(guitarService);

        //verify
        GuitarService savedGuitarService = guitarServiceRepContract.guitarServiceSave(guitarService);
        Mockito.verify(guitarServiceRepMock).save(guitarService);
        assertEquals(guitarService, savedGuitarService);
    }
    /**
     * Tests the {@link GuitarServiceRepContract #saveAllGuitarServices(List)} method.
     * Verifies that the {@link GuitarServiceRep #saveAll(List)} method of the GuitarServiceRep implementation is called with the provided list of guitar services.
     * Also verifies that the returned list of guitar services matches the input list.
     */

    @Test
    public void testSaveAllGuitarServices() {
        //given
        GuitarService guitarService1 = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");

        GuitarService guitarService2 = new GuitarService(
                150,
                9,
                "contactInfo", "I have a broken front, and I want to change it",
                "YamahaGTX85",
                "ELECTRIC");

        List<GuitarService> guitarServices = new ArrayList<>();
        guitarServices.add(guitarService1);
        guitarServices.add(guitarService2);

        //when
        when(guitarServiceRepMock.saveAll(guitarServices)).thenReturn(guitarServices);

        //verify
        List<GuitarService> savedGuitarServices = guitarServiceRepContract.saveAllGuitarServices(guitarServices);
        Mockito.verify(guitarServiceRepMock).saveAll(guitarServices);
        assertEquals(guitarServices, savedGuitarServices);
    }

    /**
     * Tests the {@link GuitarServiceRepContract #getAllGuitarServices()} method.
     * Verifies that the {@link GuitarServiceRep #findAll()} method of the GuitarServiceRep implementation is called.
     * Also verifies that the returned list of guitar services matches the expected list.
     */
    @Test
    public void testGetAllGuitarServices() {
        GuitarService guitarService1 = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");

        GuitarService guitarService2 = new GuitarService(
                150,
                9,
                "contactInfo", "I have a broken front, and I want to change it",
                "YamahaGTX85",
                "ELECTRIC");

        List<GuitarService> guitarServiceList = new ArrayList<>();
        guitarServiceList.add(guitarService1);
        guitarServiceList.add(guitarService2);

        //when
        when(guitarServiceRepMock.findAll()).thenReturn(guitarServiceList);

        //verify
        List<GuitarService> retrievedGuitarServices = guitarServiceRepContract.getAllGuitarServices();
        Mockito.verify(guitarServiceRepMock).findAll();
        assertEquals(guitarServiceList, retrievedGuitarServices);
    }

    /**
     * Tests the {@link GuitarServiceRepContract #guitarServiceById()} method.
     * Mocks {@link GuitarServiceRep #findById(int)} to return a predefined GuitarService object.
     * Verifies the retrieved GuitarService matches the expected one and that {@link GuitarServiceRep #findById(int)} was called.
     */
    @Test
    public void testGuitarServiceById() {
        int guitarServiceId = 150;
        GuitarService expectedGuitarService = new GuitarService(
                150,
                9,
                "contactInfo", "I have a broken front, and I want to change it",
                "YamahaGTX85",
                "ELECTRIC");

        // When
        when(guitarServiceRepMock.findById(guitarServiceId)).thenReturn(Optional.of(expectedGuitarService));

        // Then
        GuitarService retrievedGuitarService = guitarServiceRepContract.guitarServiceById(guitarServiceId);
        assertEquals(expectedGuitarService, retrievedGuitarService);
        Mockito.verify(guitarServiceRepMock).findById(guitarServiceId);
    }

    /**
     * Tests the {@link GuitarServiceRepContract #guitarServiceByType(String)} method.
     * Mocks {@link GuitarServiceRep #findByType(String)} to return a predefined GuitarService object.
     * Verifies the retrieved GuitarService matches the expected one and that {@link GuitarServiceRep #findByType(String)} was called.
     */
    @Test
    public void testGuitarServiceByType() {

        GuitarService guitarService = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");


        //when
        when(guitarServiceRepMock.findByType("ELECTRIC")).thenReturn(guitarService);

        //verify
        GuitarService retrievedGuitarServices = guitarServiceRepContract.guitarServiceByType("ELECTRIC");
        Mockito.verify(guitarServiceRepMock).findByType("ELECTRIC");
         assertEquals(guitarService, retrievedGuitarServices);
    }

    /**
     * Tests the {@link GuitarServiceRepContract #guitarServiceUpdate(GuitarService)} method.
     * Mocks both {@link GuitarServiceRep #findById(int)} and {@link GuitarServiceRep #save(GuitarService)} methods of the repository.
     * Verifies successful update by comparing retrieved GuitarService with the updated version.
     * Also verifies that {@link GuitarServiceRep #findById(int)} and {@link GuitarServiceRep #save(GuitarService)} were called with appropriate arguments.
     */
    @Test
    public void testGuitarServiceUpdate() {
        GuitarService guitarService = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");

        GuitarService updatedGuitarService = new GuitarService(
                23,
                5,
                "contactInfo", "I have to change the string",
                "FenderPF300",
                "CLASSICAL");

        // Mock guitar service repository behavior
        when(guitarServiceRepMock.findById(guitarService.getIdService())).thenReturn(Optional.of(guitarService));
        when(guitarServiceRepMock.save(guitarService)).thenReturn(updatedGuitarService);

        // Test successful update
        GuitarService retrievedGuitarService = guitarServiceRepContract.guitarServiceUpdate(guitarService);
        assertEquals(updatedGuitarService, retrievedGuitarService);
        Mockito.verify(guitarServiceRepMock).findById(guitarService.getIdService());
        Mockito.verify(guitarServiceRepMock).save(guitarService);
    }

    /**
     * Tests the {@link GuitarServiceRepContract #guitarServiceDelete(int)} method.
     * Mocks {@link GuitarServiceRep #deleteById(int)} to perform no action (void method).
     * Verifies that the deletion message returned by the service matches the expected success message.
     * Also verifies that {@link GuitarServiceRep #deleteById(int)} was called with the provided ID.
     */
    @Test
    public void testGuitarServiceDelete() {
        int guitarServiceIdToDelete = 150;
        Mockito.doNothing().when(guitarServiceRepMock).deleteById(guitarServiceIdToDelete);
        String deletionMessage = guitarServiceRepContract.guitarServiceDelete(guitarServiceIdToDelete);
        assertEquals("GuitarService deleted successfully", deletionMessage);
        Mockito.verify(guitarServiceRepMock).deleteById(guitarServiceIdToDelete);
    }
}
