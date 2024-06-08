package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.GuitarService;

import com.example.GuitarSite.repository.repositoryContract.GuitarServiceRepContract;
import com.example.GuitarSite.service.implementation.GuitarServiceServImp;
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
 * this is the GuitarServiceRepContractTest class
 */
public class GuitarServiceServTest {
    @Mock
    private GuitarServiceRepContract guitarServiceRepMock;

    private GuitarServiceServ guitarServiceServ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        guitarServiceServ = new GuitarServiceServImp(guitarServiceRepMock);
    }

    /**
     * Tests saving a guitar service. Mocks the repository's guitarServiceSave method to return the saved guitar service.
     * Verifies the service calls the repository's guitarServiceSave method and returns the same guitar service object.
     */
    @Test
    public void testSaveGuitarService() {
        //given
        GuitarService guitarService = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");

        //when
        when(guitarServiceRepMock.guitarServiceSave(guitarService)).thenReturn(guitarService);

        //verify
        GuitarService savedGuitarService = guitarServiceServ.saveGuitarService(guitarService);
        Mockito.verify(guitarServiceRepMock).guitarServiceSave(guitarService);
        assertEquals(guitarService, savedGuitarService);
    }

    /**
     * Tests saving a list of guitar services. Mocks repository's saveAllGuitarServices method to return the list.
     * Verifies the service calls saveAllGuitarServices and returns the same list.
     */

    @Test
    public void testSaveGuitarServices() {
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
        when(guitarServiceRepMock.saveAllGuitarServices(guitarServices)).thenReturn(guitarServices);

        //verify
        List<GuitarService> savedGuitarServices = guitarServiceServ.saveGuitarServices(guitarServices);
        Mockito.verify(guitarServiceRepMock).saveAllGuitarServices(guitarServices);
        assertEquals(guitarServices, savedGuitarServices);
    }

    /**
     * Tests retrieving all guitar services. Mocks repository's getAllGuitarServices method to return a list.
     * Verifies the service calls getAllGuitarServices and returns the same list.
     */
    @Test
    public void testGetGuitarServices() {
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
        when(guitarServiceRepMock.getAllGuitarServices()).thenReturn(guitarServiceList);

        //verify
        List<GuitarService> retrievedGuitarServices = guitarServiceServ.getGuitarServices();
        Mockito.verify(guitarServiceRepMock).getAllGuitarServices();
        assertEquals(guitarServiceList, retrievedGuitarServices);
    }

    /**
     * Tests fetching a GuitarService by ID from the repository.
     * Mocks guitarServiceById to return a predefined GuitarService object.
     * Verifies the retrieved GuitarService matches the expected one and that guitarServiceById was called.
     */
    @Test
    public void testGetGuitarServiceById() {
        int guitarServiceId = 150;
        GuitarService expectedGuitarService = new GuitarService(
                150,
                9,
                "contactInfo", "I have a broken front, and I want to change it",
                "YamahaGTX85",
                "ELECTRIC");

        // When
        when(guitarServiceRepMock.guitarServiceById(guitarServiceId)).thenReturn(expectedGuitarService);

        // Then
        GuitarService retrievedGuitarService = guitarServiceServ.getGuitarServiceById(guitarServiceId);
        assertEquals(expectedGuitarService, retrievedGuitarService);
        Mockito.verify(guitarServiceRepMock).guitarServiceById(guitarServiceId);
    }

    /**
     * Tests fetching a GuitarService by type from the repository.
     * Mocks guitarServiceByType to return a predefined GuitarService object.
     * Verifies the retrieved GuitarService matches the expected one and that guitarServiceByType was called.
     */
    @Test
    public void testGetGuitarServiceByType() {

        GuitarService guitarService = new GuitarService(
                23,
                5,
                "contactInfo", "I have a broken back, and I want to change it",
                "FenderPF300",
                "CLASSICAL");


        //when
        when(guitarServiceRepMock.guitarServiceByType("ELECTRIC")).thenReturn(guitarService);

        //verify
        GuitarService retrievedGuitarServices = guitarServiceServ.getGuitarServiceByType("ELECTRIC");
        Mockito.verify(guitarServiceRepMock).guitarServiceByType("ELECTRIC");
        assertEquals(guitarService, retrievedGuitarServices);
    }

    /**
     * Tests updating a GuitarService in the repository.
     * Mocks guitarServiceById and save methods of the repository.
     * Verifies successful updateGuitarService by comparing retrieved GuitarService with the updated version.
     * Also verifies that guitarServiceById and guitarServiceSave were called with appropriate arguments.
     */
    @Test
    public void testUpdateGuitarService() {
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
        when(guitarServiceRepMock.guitarServiceById(guitarService.getIdService())).thenReturn(guitarService);
        when(guitarServiceRepMock.guitarServiceSave(guitarService)).thenReturn(updatedGuitarService);

        // Test successful update
        GuitarService retrievedGuitarService = guitarServiceServ.updateGuitarService(guitarService);
        assertEquals(updatedGuitarService, retrievedGuitarService);
        Mockito.verify(guitarServiceRepMock).guitarServiceById(guitarService.getIdService());
        Mockito.verify(guitarServiceRepMock).guitarServiceSave(guitarService);
    }

    /**
     * Tests deleting a GuitarService by ID from the repository.
     * Mocks guitarServiceDelete to perform no action (void method).
     * Verifies that the deletion message returned by the service matches the expected success message.
     * Also verifies that guitarServiceDelete was called with the provided ID.
     */
    @Test
    public void testDeleteGuitarService() {
        int guitarServiceIdToDelete = 150;
        when(guitarServiceRepMock.guitarServiceDelete(guitarServiceIdToDelete)).thenReturn("GuitarService deleted successfully");
        String deletionMessage = guitarServiceServ.deleteGuitarService(guitarServiceIdToDelete);
        assertEquals("GuitarService deleted successfully", deletionMessage);
        Mockito.verify(guitarServiceRepMock).guitarServiceDelete(guitarServiceIdToDelete);
    }
}
