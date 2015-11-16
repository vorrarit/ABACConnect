package com.zicure.abacconnect.transcript.model;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class Transcript {

    ArrayList<TranscriptInfo> transcriptData = new ArrayList<TranscriptInfo>();

    public Transcript() {
    }

    public Transcript(ArrayList<TranscriptInfo> transcriptData) {
        this.transcriptData = transcriptData;
    }

    public ArrayList<TranscriptInfo> getTranscriptData() {
        return transcriptData;
    }

    public void setTranscriptData(ArrayList<TranscriptInfo> transcriptData) {
        this.transcriptData = transcriptData;
    }

    public void addTranscript(TranscriptInfo transcriptInfo) {
        this.transcriptData.add(transcriptInfo);
    }
}