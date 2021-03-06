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


/**
 * The persistent class for the input_batch database table.
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@Entity
@Table(name="input_batch")
@NamedQuery(name="InputBatch.findAll", query="SELECT i FROM InputBatch i")
public class InputBatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="batch_id")
	private int batchId;

	@Column(name="rejection_id")
	private String rejectionId;

	private int thickness;

	private int width;

	//bi-directional many-to-one association to ProcessEventLog
	@ManyToOne
	@JoinColumn(name="event_id")
	private ProcessEventLog processEventLog;

	public InputBatch() {
	}

	public int getBatchId() {
		return this.batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getRejectionId() {
		return this.rejectionId;
	}

	public void setRejectionId(String rejectionId) {
		this.rejectionId = rejectionId;
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

	public ProcessEventLog getProcessEventLog() {
		return this.processEventLog;
	}

	public void setProcessEventLog(ProcessEventLog processEventLog) {
		this.processEventLog = processEventLog;
	}

}