package com.example.tezspringbe.models;

/**
 * @Author meteh
 * @create 28.05.2022 22:37
 *//*
 * @created 05
 * @author meteh
 */
public class ApprovedDataSet {
    private String dataSetId;
    private String nameOfDataSet;

    public String getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(String dataSetId) {
        this.dataSetId = dataSetId;
    }

    public ApprovedDataSet(String dataSetId, String nameOfDataSet) {
        this.dataSetId = dataSetId;
        this.nameOfDataSet = nameOfDataSet;
    }

    public String getNameOfDataSet() {
        return nameOfDataSet;
    }

    public void setNameOfDataSet(String nameOfDataSet) {
        this.nameOfDataSet = nameOfDataSet;
    }

}

