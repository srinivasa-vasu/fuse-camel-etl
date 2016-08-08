/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fuse.ra.camel.jpa.model.mysql;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the process_event_log database table.
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@Entity
@Table(name = "process_event_log")
@NamedQuery(name = "ProcessEventLog.findAll", query = "SELECT p FROM ProcessEventLog p")
public class ProcessEventLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "batch_id")
    private int batchId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_datetime")
    private Date eventDatetime;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_status")
    private int eventStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "msg_datetime")
    private Date msgDatetime;

    @Column(name = "msg_id")
    private int msgId;

    @Column(name = "seq_no")
    private int seqNo;

    private int thickness;

    private int width;

    // bi-directional many-to-one association to InputBatch
    @OneToMany(mappedBy = "processEventLog", cascade = CascadeType.ALL)
    private List<InputBatch> inputBatches;

    // bi-directional many-to-one association to OutputBatch
    @OneToMany(mappedBy = "processEventLog", cascade = CascadeType.ALL)
    private List<OutputBatch> outputBatches;

    public ProcessEventLog() {
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public Date getEventDatetime() {
        return this.eventDatetime;
    }

    public void setEventDatetime(Date eventDatetime) {
        this.eventDatetime = eventDatetime;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventStatus() {
        return this.eventStatus;
    }

    public void setEventStatus(int eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Date getMsgDatetime() {
        return this.msgDatetime;
    }

    public void setMsgDatetime(Date msgDatetime) {
        this.msgDatetime = msgDatetime;
    }

    public int getMsgId() {
        return this.msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public int getThickness() {
        return this.thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<InputBatch> getInputBatches() {
        return this.inputBatches;
    }

    public void setInputBatches(List<InputBatch> inputBatches) {
        this.inputBatches = inputBatches;
    }

    public InputBatch addInputBatch(InputBatch inputBatch) {
        getInputBatches().add(inputBatch);
        inputBatch.setProcessEventLog(this);

        return inputBatch;
    }

    public InputBatch removeInputBatch(InputBatch inputBatch) {
        getInputBatches().remove(inputBatch);
        inputBatch.setProcessEventLog(null);

        return inputBatch;
    }

    public List<OutputBatch> getOutputBatches() {
        return this.outputBatches;
    }

    public void setOutputBatches(List<OutputBatch> outputBatches) {
        this.outputBatches = outputBatches;
    }

    public OutputBatch addOutputBatch(OutputBatch outputBatch) {
        getOutputBatches().add(outputBatch);
        outputBatch.setProcessEventLog(this);

        return outputBatch;
    }

    public OutputBatch removeOutputBatch(OutputBatch outputBatch) {
        getOutputBatches().remove(outputBatch);
        outputBatch.setProcessEventLog(null);

        return outputBatch;
    }

}