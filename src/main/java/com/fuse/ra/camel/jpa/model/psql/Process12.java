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
package com.fuse.ra.camel.jpa.model.psql;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the process12 database table.
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@Entity
@Table(name = "process12")
@NamedQueries(value = { @NamedQuery(name = "findP12All", query = "SELECT P12 FROM Process1 P12"),
        @NamedQuery(name = "findProcess12RecordsByStatus", query = "SELECT P12 FROM Process12 P12 WHERE P12.msgStatus = :msgStatus"),
        @NamedQuery(name = "updateProcess12RecordByStatus", query = "UPDATE Process12 SET msgStatus = :msgStatus WHERE msgId = :msgId") })
public class Process12 implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String PROCESS_EVENT = "Process 12 Pass";

    @Id
    @Column(name = "msg_id", unique = true, nullable = false)
    private Integer msgId;

    @Column(name = "input_batch_id", length = 20)
    private String inputBatchId;

    @Temporal(TemporalType.DATE)
    @Column(name = "msg_datetime")
    private Date msgDatetime;

    @Column(name = "msg_status")
    private Integer msgStatus;

    @Column(name = "rejection_id", length = 20)
    private String rejectionId;

    @Column(name = "rejection_type")
    private Integer rejectionType;

    @Column(name = "seq_no")
    private Integer seqNo;

    private Integer thickness;

    private Integer width;

    public Process12() {
    }

    public Integer getMsgId() {
        return this.msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getInputBatchId() {
        return this.inputBatchId;
    }

    public void setInputBatchId(String inputBatchId) {
        this.inputBatchId = inputBatchId;
    }

    public Date getMsgDatetime() {
        return this.msgDatetime;
    }

    public void setMsgDatetime(Date msgDatetime) {
        this.msgDatetime = msgDatetime;
    }

    public Integer getMsgStatus() {
        return this.msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getRejectionId() {
        return this.rejectionId;
    }

    public void setRejectionId(String rejectionId) {
        this.rejectionId = rejectionId;
    }

    public Integer getRejectionType() {
        return this.rejectionType;
    }

    public void setRejectionType(Integer rejectionType) {
        this.rejectionType = rejectionType;
    }

    public Integer getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getThickness() {
        return this.thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Process12 [msgId=");
        builder.append(msgId);
        builder.append(", inputBatchId=");
        builder.append(inputBatchId);
        builder.append(", msgDatetime=");
        builder.append(msgDatetime);
        builder.append(", msgStatus=");
        builder.append(msgStatus);
        builder.append(", rejectionId=");
        builder.append(rejectionId);
        builder.append(", rejectionType=");
        builder.append(rejectionType);
        builder.append(", seqNo=");
        builder.append(seqNo);
        builder.append(", thickness=");
        builder.append(thickness);
        builder.append(", width=");
        builder.append(width);
        builder.append("]");
        return builder.toString();
    }

}