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
 * The persistent class for the process2 database table.
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@Entity
@Table(name = "process2")
@NamedQueries(value = { @NamedQuery(name = "findP2All", query = "SELECT P2 FROM Process2 P2"),
        @NamedQuery(name = "findProcess2RecordsByStatus", query = "SELECT P2 FROM Process2 P2 WHERE P2.msgStatus = :msgStatus"),
        @NamedQuery(name = "updateProcess2RecordByStatus", query = "UPDATE Process2 SET msgStatus = :msgStatus WHERE msgId = :msgId") })
public class Process2 implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String PROCESS_EVENT = "Process 2 Pass";

    @Id
    @Column(name = "msg_id", unique = true, nullable = false)
    private Integer msgId;

    @Column(name = "msg_status")
    private Integer msgStatus;

    @Column(name = "output_batch_id", length = 20)
    private String outputBatchId;

    @Column(name = "seq_no")
    private Integer seqNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "msg_datetime")
    private Date msgDatetime;

    private Integer thickness;

    private Integer width;

    public Process2() {
    }

    public Integer getMsgId() {
        return this.msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgStatus() {
        return this.msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getOutputBatchId() {
        return this.outputBatchId;
    }

    public void setOutputBatchId(String outputBatchId) {
        this.outputBatchId = outputBatchId;
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

    public Date getMsgDatetime() {
        return this.msgDatetime;
    }

    public void setMsgDatetime(Date msgDatetime) {
        this.msgDatetime = msgDatetime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Process2 [msgId=");
        builder.append(msgId);
        builder.append(", msgStatus=");
        builder.append(msgStatus);
        builder.append(", outputBatchId=");
        builder.append(outputBatchId);
        builder.append(", seqNo=");
        builder.append(seqNo);
        builder.append(", msgDatetime=");
        builder.append(msgDatetime);
        builder.append(", thickness=");
        builder.append(thickness);
        builder.append(", width=");
        builder.append(width);
        builder.append("]");
        return builder.toString();
    }

}