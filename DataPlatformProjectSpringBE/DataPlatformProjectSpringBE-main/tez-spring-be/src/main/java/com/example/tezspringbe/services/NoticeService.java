package com.example.tezspringbe.services;


import com.example.tezspringbe.models.*;
import com.example.tezspringbe.repos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;


@Service
@AllArgsConstructor
public class NoticeService {

    private  NoticeRepo noticeRepo;
    private ContactRepo contactRepo;
    private AnalysisRequestRepo analysisRequestRepo;
    private DatasetRepo datasetRepo;
    private AdminsRepo adminsRepo;
    private AnalysisRequestAdminRepo analysisRequestAdminRepo;
    private static String UPLOADED_FOLDER = "D:\\uploaded_data_sets"; //burası farklı olabilir sende.
    private static String UPLOADED_DATA_ANALYSIS = "D:\\uploaded_data_analysis";

    public List<Notice> getAllNotice() {
        List<Notice> notices = noticeRepo.findAll();
        Collections.sort(notices,((o1, o2) -> o2.getDateOfPublish().compareTo(o1.getDateOfPublish())));
        return  notices;
    }

    public boolean createNewContact(Contact contact){
        Contact contactResponse = contactRepo.insert(contact);
        if(contactResponse != null) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean createNewAnalysisRequest(AnalysisRequest analysisRequest){
        analysisRequest.setShowInFE(true);
        AnalysisRequest analysisRequestResponse = analysisRequestRepo.insert(analysisRequest);
        if(analysisRequestResponse != null) {
            return true;
        }
        else {
            return false;
        }

    }
    public boolean createNewDataRequest(MultipartFile newFile, String newInfo)  {

        if(newFile.isEmpty()) {
            return false;
        }
        try {
            byte[] bytes = newFile.getBytes();
            String pathOfFile = UPLOADED_FOLDER.concat("\\").concat(newFile.getOriginalFilename().toLowerCase());
            Path path = Paths.get(pathOfFile);

            String[] infos = newInfo.split(",");
            String senderName = null;
            String senderEmail = null;
            String description = null;

            for(int i =0;i<infos.length;i++) {
                String[] temp = infos[i].split(":");
                if(i==3) {
                    break;
                }
                if(temp[0].contains("Email")) {
                    senderEmail = temp[1];
                    senderEmail = senderEmail.replace("{","");
                    senderEmail = senderEmail.replace("}","");
                    senderEmail = senderEmail.replace("\"","");
                }
                else if (temp[0].contains("Name")) {
                    senderName = temp[1];
                    senderName = senderName.replaceAll("\"","");
                }
                else if (temp[0].contains("description"))
                {
                    description = temp[1];
                    description = description.replace("{","");
                    description = description.replace("}","");
                    description = description.replace("\"","");
                }

            }
            System.out.println(senderName);
            System.out.println(senderEmail);
            Dataset newDataRequestToDb = new Dataset(senderName,senderEmail,description,pathOfFile,"onaylanmadi",newFile.getContentType());
            Files.write(path,bytes);
            datasetRepo.insert(newDataRequestToDb);



        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hata alırsan 29'a bak.");
        }
        return true;
    }
    public boolean checkLogin(String credentials) {
        String[] arrOfStr = credentials.split(" ",2);
        String usernameInput = arrOfStr[0];
        String passwordInput = arrOfStr[1];
        boolean loggedIn = false;
        List<Admins> adminsList = adminsRepo.findAll();
        for(Admins admin:adminsList)
        {
            if (admin.getUsername().equals(usernameInput))
            {
                if(admin.getPassword().equals(passwordInput)){
                    loggedIn = true;
                    break;
                }
            }
        }
        return loggedIn;
    }

    public boolean addNotice(Notice notice){
        notice.setDateOfPublish(LocalDate.now());
        Notice noticeResponse = noticeRepo.insert(notice);
        if(noticeResponse != null) {
            return true;
        }
        else {
            return false;
        }

    }
    public boolean adminAddDataset(MultipartFile newFile, String newInfo)  {

        if(newFile.isEmpty()) {
            return false;
        }
        try {
            byte[] bytes = newFile.getBytes();
            String pathOfFile = UPLOADED_FOLDER.concat("\\").concat(newFile.getOriginalFilename().toLowerCase());
            Path path = Paths.get(pathOfFile);

            String[] infos = newInfo.split(",");
            String senderName = null;
            String senderEmail = null;
            String description = null;

            for(int i =0;i<infos.length;i++) {
                String[] temp = infos[i].split(":");
                if(i==3) {
                    break;
                }
                if(temp[0].contains("Email")) {
                    senderEmail = temp[1];
                    senderEmail = senderEmail.replace("{","");
                    senderEmail = senderEmail.replace("}","");
                    senderEmail = senderEmail.replace("\"","");
                }
                else if (temp[0].contains("Name")) {
                    senderName = temp[1];
                    senderName = senderName.replaceAll("\"","");
                }
                else if (temp[0].contains("description"))
                {
                    description = temp[1];
                    description = description.replace("{","");
                    description = description.replace("}","");
                    description = description.replace("\"","");
                }
            }
            System.out.println(senderName);
            System.out.println(senderEmail);
            Dataset adminDataset = new Dataset(senderName,senderEmail,description,pathOfFile,"onaylandi",newFile.getContentType());
            Files.write(path,bytes);
            datasetRepo.insert(adminDataset);



        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hata alırsan 29'a bak.");
        }
        return true;
    }
    public boolean updateNewDataRequestStatus(String paramId) {
        Optional<Dataset> oldVersion = datasetRepo.findById(paramId);

        if(oldVersion.isEmpty()) {
            return false;
        }

        else {
            Dataset newVersion = oldVersion.get();
            newVersion.setOnaylandiMi("onaylandi");
            datasetRepo.save(newVersion);
            return true;
        }


    }

    public boolean deleteDataRequestFromDb(String paramId) {

        Optional<Dataset> response = datasetRepo.findById(paramId);

        if(response.isEmpty()) {
            return false;
        }
        else {
            Dataset deleteThis = response.get();
            datasetRepo.delete(deleteThis);
            return true;
        }

    }

    public List<Dataset> getDataRequest() {

        List<Dataset> result = datasetRepo.getAllOnaylanmadi("onaylanmadi");

        if(result.isEmpty()) {
            System.out.println("dataRequestler alinamadi");
            return null;
        }
        else {
            return result;
        }

    }
    public List<Dataset> getDatasets(){
        List<Dataset> datasetList = datasetRepo.getAllOnaylandi("onaylandi");

        if(datasetList.isEmpty()) {
            System.out.println("datasetler alinamadi");
            return null;
        }
        else {
            return datasetList;
        }
    }
    public List<Integer> getAdminNumbers(){
        List<Integer> my_list = new ArrayList<>();
        List<Dataset> datasetList = datasetRepo.getAllOnaylandi("onaylandi");
        List<Dataset> result = datasetRepo.getAllOnaylanmadi("onaylanmadi");
        my_list.add(datasetList.size());
        Long x = noticeRepo.count();
        my_list.add(x.intValue());
        my_list.add(result.size());
        List<AnalysisRequest> analysisRequestList = analysisRequestRepo.findByShowInFe(true);
        my_list.add(analysisRequestList.size());

        return my_list;
    }

    public List<ApprovedDataSet> getAllApprovedDataSetsMapped() {
        List<Dataset> result = datasetRepo.getAllOnaylandi("onaylandi");

        if(result.isEmpty()) {
            System.out.println("dataRequestler alinamadi");
            return null;
        }

        else {
            List<ApprovedDataSet> approvedDataSetList = new ArrayList<ApprovedDataSet>();

            for(int x=0;x<result.size();x++) {
                Dataset mapThisDataSet = result.get(x);
                ApprovedDataSet approvedDataSet = new ApprovedDataSet(mapThisDataSet.getId(),getDataSetName(mapThisDataSet.getDataPathOfDataSet()));
                approvedDataSetList.add(approvedDataSet);
            }

            return  approvedDataSetList;
        }
    }

    private String getDataSetName(String fullPath) {
        String[] strArray = fullPath.split("\\\\");
        return strArray[strArray.length-1];
    }


    public boolean saveDataAnalysisToDb(MultipartFile file, String info) {
        if(file.isEmpty()) {
            return false;
        }
        else {
            try {
                byte[] bytes = file.getBytes();
                String pathOfFile = UPLOADED_DATA_ANALYSIS.concat("\\").concat(file.getOriginalFilename().toLowerCase());
                Path path = Paths.get(pathOfFile);

                String[] infos = info.split(",");
                String senderName = null;
                String dataset_id = null;
                String description = null;
                for(int i = 0;i<infos.length;i++) {
                    String[] temp = infos[i].split(":");
                    if(temp[0].contains("dataset")) {
                        dataset_id = temp[1];
                        dataset_id = dataset_id.replace("\"","");
                    }
                    else if (temp[0].contains("sender")) {
                        senderName = temp[1];
                        senderName = senderName.replaceAll("}"," ");
                        senderName = senderName.replace("\"","");

                    }
                    else {
                        description = temp[1];
                        description = description.replace("\"","");
                    }
                }


                DatasetAnalysis datasetAnalysis = new DatasetAnalysis(dataset_id,senderName,pathOfFile,description);
                Files.write(path,bytes);
                analysisRequestAdminRepo.insert(datasetAnalysis);



            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Hata alırsan 30'a bak.");
            }
            return true;
        }
    }



    public List<ApproveAnalysisRequestResponse> getDataAnalysisRequests() {
        List<AnalysisRequest> analysisRequestList = analysisRequestRepo.findAll();
        List<ApproveAnalysisRequestResponse> responseArrayList = new ArrayList<ApproveAnalysisRequestResponse>();

        for(int x = 0 ; x<analysisRequestList.size();x++) {
            AnalysisRequest analysisRequest = analysisRequestList.get(x);

            if(analysisRequest.isShowInFE()) {
                ApproveAnalysisRequestResponse approveAnalysisRequestResponse = new ApproveAnalysisRequestResponse(analysisRequest.getId(),
                        "dataSetName",analysisRequest.getNameSurname(),analysisRequest.getEmail(),analysisRequest.getRequest());

                System.out.println(analysisRequest.getId());
                Optional<Dataset> datasetOptional = datasetRepo.findById(analysisRequest.getId());

                Dataset dataset = datasetOptional.get();

                String[] nameSplit = dataset.getDataPathOfDataSet().split("\\\\");
                String nameOfDataSet = nameSplit[nameSplit.length-1];

                approveAnalysisRequestResponse.setData_set_name(nameOfDataSet);

                responseArrayList.add(approveAnalysisRequestResponse);
            }

            else {
                continue;
            }
        }
        return responseArrayList;
    }

    public boolean saveDataAnalysisComeFromUser(MultipartFile file, String info) throws IOException {
        DatasetAnalysis datasetAnalysis = new ObjectMapper().readValue(info,DatasetAnalysis.class);

        byte[] bytes = file.getBytes();
        String pathOfFile = UPLOADED_DATA_ANALYSIS.concat("\\").concat(file.getOriginalFilename().toLowerCase());
        Path path = Paths.get(pathOfFile);

        Files.write(path,bytes);

        datasetAnalysis.setPathOfAnalysis(pathOfFile);

        analysisRequestAdminRepo.insert(datasetAnalysis);


        List<AnalysisRequest> oldVersion = analysisRequestRepo.findByRelatedId(datasetAnalysis.getRelated_data_set_id());

        if(oldVersion.isEmpty()) {
            return false;
        }

        else {
            AnalysisRequest newVersion = oldVersion.get(0);
            newVersion.setShowInFE(false);

            analysisRequestRepo.save(newVersion);
            return true;
        }
    }


    public List<Dataset> searchDataSetsWithKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        List<Dataset> searchResultTitles = datasetRepo.findByTitlesContain(keyword);

        List<Dataset> searchResultDescriptions = datasetRepo.findByDescriptionsContain(keyword);

        Set<Dataset> set = new LinkedHashSet<>(searchResultTitles);

        set.addAll(searchResultDescriptions);

        List<Dataset> result = new ArrayList<Dataset>(set);

        return  result;
    }

    public Dataset getDatasetById(String id){
        Optional<Dataset> selectedDataset = datasetRepo.findById(id);
        if(selectedDataset.isEmpty()) {
            return null;
        }
        else {
            return selectedDataset.get();
        }
    }

    public List<DatasetAnalysis> getAnalysisReqList(String id){
        List<DatasetAnalysis> analysisList = analysisRequestAdminRepo.findByRelatedId(id);
        return analysisList;
    }




}

