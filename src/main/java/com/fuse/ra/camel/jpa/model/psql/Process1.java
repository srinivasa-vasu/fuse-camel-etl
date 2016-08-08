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
 * The persistent class for the process1 database table.
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@Entity
@Table(name = "process1")
@NamedQueries(value = { @NamedQuery(name = "findP1All", query = "SELECT P1 FROM Process1 P1"),
        @NamedQuery(name = "findProcess1RecordsByStatus", query = "SELECT P1 FROM Process1 P1 WHERE P1.msgStatus = :msgStatus"),
        @NamedQuery(name = "updateProcess1RecordByStatus", query = "UPDATE Process1 SET msgStatus = :msgStatus WHERE msgId = :msgId") })
public class Process1 implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String PROCESS_EVENT = "Process 1 Pass";

    @Id
    @Column(name = "msg_id", unique = true, nullable = false)
    private Integer msgId;

    @Temporal(TemporalType.DATE)
    @Column(name = "msg_datetime")
    private Date msgDatetime;

    @Column(name = "msg_status")
    private Integer msgStatus;

    @Column(name = "seq_no")
    private Integer seqNo;

    public Process1() {
    }

    public Integer getMsgId() {
        return this.msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
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

    public Integer getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Process1 [msgId=");
        builder.append(msgId);
        builder.append(", msgDatetime=");
        builder.append(msgDatetime);
        builder.append(", msgStatus=");
        builder.append(msgStatus);
        builder.append(", seqNo=");
        builder.append(seqNo);
        builder.append("]");
        return builder.toString();
    }

}