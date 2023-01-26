package com.example.tezspringbe.services;

import com.example.tezspringbe.models.*;
import com.example.tezspringbe.repos.*;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.crypto.Data;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author meteh
 * @create 9.06.2022 18:46
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class NoticeServiceTest {

    @MockBean
    private NoticeRepo noticeRepo;

    @MockBean
    private ContactRepo contactRepo;

    @MockBean
    private AnalysisRequestRepo analysisRequestRepo;

    @MockBean
    private DatasetRepo dataSetRepo;

    @MockBean
    private AdminsRepo adminsRepo;

    @MockBean
    private AnalysisRequestAdminRepo analysisRequestAdminRepo;

    @InjectMocks
    private NoticeService noticeService;

    @Test
    void getAllNotice() {
        when(noticeRepo.findAll()).thenReturn(prepareNotice());
        List<Notice> noticeList = noticeService.getAllNotice();
        assertEquals(noticeList.size(),2);
        assertTrue(!noticeList.isEmpty());
    }

    @Test
    void createNewContact() {
        Contact contact = new Contact("mete","kodal@gmail.com","content");
        when(contactRepo.insert(any(Contact.class))).thenReturn(contact);
        boolean result = noticeService.createNewContact(contact);
        assertTrue(result);
    }

    @Test
    void createNewAnalysisRequest() {
        AnalysisRequest analysisRequest = new AnalysisRequest("123","exampleSurname","exampleMail","exampleRequest");
        when(analysisRequestRepo.insert(any(AnalysisRequest.class))).thenReturn(analysisRequest);
        boolean result = noticeService.createNewAnalysisRequest(analysisRequest);
        assertTrue(result);

    }

    @Test
    void createNewDataRequest() {
        Dataset dataset = new Dataset("mete","kodal@gmail.com","desc","path","onaylandi","type");
        when(dataSetRepo.insert(dataset)).thenReturn(dataset);
        boolean result = noticeService.createNewDataRequest(new MockMultipartFile("file","hello.txt", MediaType.TEXT_PLAIN_VALUE,"Example".getBytes()),"info");
        assertTrue(result);
    }

    @Test
    void checkLoginTrue() {
        String credentials = "abc abc";
        when(adminsRepo.findAll()).thenReturn(List.of(new Admins("abc","abc")));

        boolean result = noticeService.checkLogin(credentials);
        assertTrue(result);

    }

    @Test
    void checkLoginFalse() {
        String credentials = "abc abc";
        when(adminsRepo.findAll()).thenReturn(List.of(new Admins("abc","abcd")));

        boolean result = noticeService.checkLogin(credentials);
        assertFalse(result);

    }

    @Test
    void addNotice() {
        Notice notice = new Notice(null,"header","body");
        when(noticeRepo.insert(notice)).thenReturn(notice);
        assertTrue(noticeService.addNotice(notice));
    }

    @Test
    void adminAddDataset() {
        Dataset dataset = new Dataset("mete","kodal@gmail.com","desc","path","onaylandi","type");
        when(dataSetRepo.insert(dataset)).thenReturn(null);

        boolean result = noticeService.adminAddDataset(new MockMultipartFile("file","hello.txt", MediaType.TEXT_PLAIN_VALUE,"Example".getBytes()),"xyz");

        assertTrue(result);

    }

    @Test
    void updateNewDataRequestStatus() {
        when(dataSetRepo.findById(any(String.class))).thenReturn(Optional.empty());
        boolean result = noticeService.updateNewDataRequestStatus("paramId");
        assertFalse(result);
    }


    @Test
    void getDataRequest() {

        when(dataSetRepo.getAllOnaylanmadi(any(String.class))).thenReturn(List.of());
        List<Dataset> result = noticeService.getDataRequest();
        assertNull(result);
    }

    @Test
    void getDatasets() {
        Dataset dataset = new Dataset("mete","kodal@gmail.com","desc","path","onaylandi","type");
        when(dataSetRepo.getAllOnaylandi(any(String.class))).thenReturn(List.of(dataset));
        List<Dataset> result = noticeService.getDatasets();
        Dataset resultFirst = result.get(0);
        assertNotNull(result);
        assertEquals(resultFirst.getSenderName(),"mete");
    }

    @Test
    void getAdminNumbers() {
        Dataset dataset = new Dataset("mete","kodal@gmail.com","desc","path","onaylandi","type");
        when(dataSetRepo.getAllOnaylandi(any(String.class))).thenReturn(List.of(dataset));

        when(dataSetRepo.getAllOnaylanmadi(any(String.class))).thenReturn(List.of());
        when(analysisRequestRepo.findByShowInFe(any(Boolean.class))).thenReturn(List.of());
        List<Integer> result = noticeService.getAdminNumbers();
        assertEquals(result.size(),4);
        assertEquals(result.get(0),1);
        assertEquals(result.get(1),0);
        assertEquals(result.get(2),0);
        assertEquals(result.get(3),0);

    }

    @Test
    void getAllApprovedDataSetsMapped() {
        when(dataSetRepo.getAllOnaylandi(any(String.class))).thenReturn(List.of());
        List<ApprovedDataSet> result = noticeService.getAllApprovedDataSetsMapped();
        assertNull(result);
    }



    @Test
    void getDataAnalysisRequests() {
        AnalysisRequest analysisRequest = new AnalysisRequest("DATASETID","mete","mete@gmail.com","requestExample");
        analysisRequest.setShowInFE(false);
        when(analysisRequestRepo.findAll()).thenReturn(List.of(analysisRequest));

        List<ApproveAnalysisRequestResponse> result = noticeService.getDataAnalysisRequests();

        assertTrue(result.isEmpty());

    }


    @Test
    void searchDataSetsWithKeyword() {
        List<Dataset> datasetsKey = List.of(new Dataset("mete","mete@gmail.com","desc","path","onaylandi","type"));
        List<Dataset> datasetsDesc = List.of(new Dataset("ozan","ozan@gmail.com","desc","path","onaylandi","type"));
        when(dataSetRepo.findByTitlesContain(any(String.class))).thenReturn(datasetsKey);
        when(dataSetRepo.findByDescriptionsContain(any(String.class))).thenReturn(datasetsDesc);

        List<Dataset> result = noticeService.searchDataSetsWithKeyword("abc");

        assertEquals(result.size(),2);



    }

    @Test
    void getDatasetById() {
        when(dataSetRepo.findById(any(String.class))).thenReturn(Optional.empty());

        Dataset result = noticeService.getDatasetById("id");

        assertNull(result);
    }

    @Test
    void getAnalysisReqList() {
        when(analysisRequestAdminRepo.findByRelatedId(any(String.class))).thenReturn(List.of(new DatasetAnalysis(),new DatasetAnalysis()));

        List<DatasetAnalysis> result = noticeService.getAnalysisReqList("id");

        assertEquals(result.size(),2);
    }

    private List<Notice> prepareNotice() {
       ArrayList<Notice> notices = new ArrayList<Notice>();
       Notice noticeFirst = new Notice(LocalDate.now(),"ExampleHeader","ExampleBody");
       Notice noticeSecond = new Notice(LocalDate.MIN,"ExampleHeade2","ExampleBody2");
       notices.add(noticeFirst);
       notices.add(noticeSecond);
       return notices;
    }
}