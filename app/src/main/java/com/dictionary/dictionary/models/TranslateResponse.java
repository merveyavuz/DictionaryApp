package com.dictionary.dictionary.models;

import java.util.List;

public class TranslateResponse {
    private String result;
    private List<Meaning> tuc;

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public List<Meaning> getTuc() { return tuc; }
    public void setTuc(List<Meaning> tuc) { this.tuc = tuc; }


}
