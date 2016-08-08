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
package com.fuse.ra.camel.jpa.handler;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.fuse.ra.camel.jpa.model.mysql.InputBatch;
import com.fuse.ra.camel.jpa.model.mysql.OutputBatch;
import com.fuse.ra.camel.jpa.model.mysql.ProcessEventLog;
import com.fuse.ra.camel.jpa.model.psql.Process1;
import com.fuse.ra.camel.jpa.model.psql.Process12;
import com.fuse.ra.camel.jpa.model.psql.Process2;
import com.fuse.ra.camel.jpa.source.MySQLDS;
import com.fuse.ra.camel.jpa.source.PSQLDS;

/**
 * <p>
 * Entity handler that manages <blockquote>Postgres</blockquote> and <blockquote>MySQL</blockquote> entities. ETL
 * operations are handled.
 * 
 * <p>
 * Two entity managers are defined,
 * <ol>
 * <li>For Postgres</li>
 * <li>For MySSQL</li>
 * </ol>
 * 
 * and the entity manager types are identified through {@code javax.inject.Qualifier} annotations.
 * 
 * <p>
 * Created sample entities to show how data can be extracted from one source and then transformed and loaded into
 * another source.
 * 
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@Alternative
@Named("entityHandler")
public class EntityHandler {

    private static final int MSG_INIT = 0;
    private static final int MSG_SUCCESS = 1;
    private static final int MSG_FAILED = 2;

    @Inject
    @PSQLDS
    private EntityManager sourceManager;

    @Inject
    @MySQLDS
    private EntityManager targetManager;

    public EntityHandler() {
    }

    int counter = 1;

    /**
     * Create records in source data source through a scheduler
     * 
     * @return boolean
     */
    @TransactionAttribute(REQUIRED)
    public void createRecords() {

        Date dt = new Date();
        Process1 p1 = new Process1();
        p1.setMsgId(counter);
        p1.setSeqNo(1);
        p1.setMsgDatetime(dt);
        p1.setMsgStatus(0);
        sourceManager.persist(p1);

        Process12 p12 = new Process12();
        p12.setMsgId(counter);
        p12.setSeqNo(1);
        p12.setMsgDatetime(dt);
        p12.setRejectionId("R1");
        p12.setThickness(1000);
        p12.setWidth(600);
        p12.setMsgStatus(0);
        if (counter % 3 == 0) {
            p12.setInputBatchId("R" + counter);
        } else {
            p12.setInputBatchId(String.valueOf(counter));
        }
        sourceManager.persist(p12);

        Process2 p2 = new Process2();
        p2.setMsgId(counter);
        p2.setSeqNo(1);
        p2.setMsgDatetime(dt);
        p2.setThickness(1000);
        p2.setWidth(600);
        p2.setMsgStatus(0);
        p2.setOutputBatchId(String.valueOf(counter));
        sourceManager.persist(p2);

        counter++;
    }

    /**
     * Get the unprocessed records from the source data source
     * 
     * @return list List<Process1>
     */
    public List<Process1> findProcess1RecordsByStatus() {
        return sourceManager.createNamedQuery("findProcess1RecordsByStatus", Process1.class)
                .setParameter("msgStatus", MSG_INIT).getResultList();
    }

    /**
     * Get the unprocessed records from the source data source
     * 
     * @return list List<Process12>
     */
    public List<Process12> findProcess12RecordsByStatus() {
        return sourceManager.createNamedQuery("findProcess12RecordsByStatus", Process12.class)
                .setParameter("msgStatus", MSG_INIT).getResultList();
    }

    /**
     * Get the unprocessed records from the source data source
     * 
     * @return list List<Process2>
     */
    public List<Process2> findProcess2RecordsByStatus() {
        return sourceManager.createNamedQuery("findProcess2RecordsByStatus", Process2.class)
                .setParameter("msgStatus", MSG_INIT).getResultList();
    }

    /**
     * Insert transformed record to the target source
     * 
     * @param srcEntity
     *            Process1
     * @return process Process1
     */
    @TransactionAttribute(REQUIRED)
    public Process1 insertProcessEventLogP1(Process1 srcEntity) {
        ProcessEventLog tarEntity = new ProcessEventLog();
        tarEntity.setMsgId(srcEntity.getMsgId());
        tarEntity.setMsgDatetime(srcEntity.getMsgDatetime());
        tarEntity.setSeqNo(srcEntity.getSeqNo());
        tarEntity.setEventName(Process1.PROCESS_EVENT);
        tarEntity.setEventDatetime(new Date());
        targetManager.persist(tarEntity);
        return srcEntity;
    }

    /**
     * Update source status
     * 
     * @param srcEntity
     *            Process1
     * @return process Process1
     */
    @TransactionAttribute(REQUIRED)
    public Process1 updateProcess1(Process1 srcEntity) {
        Process1 updEntity = sourceManager.find(Process1.class, srcEntity.getMsgId());
        updEntity.setMsgStatus(MSG_SUCCESS);
        sourceManager.flush();
        /*
         * sourceManager.createNamedQuery("updateProcess1RecordByStatus"). setParameter("msgStatus", MSG_SUCCESS)
         * .setParameter("msgId", srcEntity.getMsgId()).executeUpdate();
         */

        return srcEntity;
    }

    /**
     * Insert transformed record to the target source
     * 
     * @param srcEntity
     *            Process12
     * @return process Process12
     */
    @TransactionAttribute(REQUIRED)
    public Process12 insertProcessEventLogP12(Process12 srcEntity) {
        ProcessEventLog tarEntity = new ProcessEventLog();
        tarEntity.setMsgId(srcEntity.getMsgId());
        tarEntity.setMsgDatetime(srcEntity.getMsgDatetime());
        tarEntity.setSeqNo(srcEntity.getSeqNo());
        tarEntity.setEventName(Process12.PROCESS_EVENT);
        tarEntity.setBatchId(Integer.valueOf(srcEntity.getInputBatchId().trim()));
        tarEntity.setThickness(srcEntity.getThickness());
        tarEntity.setWidth(srcEntity.getWidth());
        tarEntity.setEventDatetime(new Date());
        InputBatch inputBatch = new InputBatch();
        inputBatch.setBatchId(tarEntity.getBatchId());
        inputBatch.setRejectionId(srcEntity.getRejectionId());
        inputBatch.setThickness(srcEntity.getThickness());
        inputBatch.setWidth(srcEntity.getWidth());
        tarEntity.setInputBatches(new ArrayList<InputBatch>());
        tarEntity.addInputBatch(inputBatch);
        targetManager.persist(tarEntity);
        return srcEntity;
    }

    /**
     * Update source status
     * 
     * @param srcEntity
     *            Process12
     * @return process Process12
     */
    @TransactionAttribute(REQUIRED)
    public Process12 updateProcess12(Process12 srcEntity) {
        Process12 updEntity = sourceManager.find(Process12.class, srcEntity.getMsgId());
        updEntity.setMsgStatus(MSG_SUCCESS);
        sourceManager.flush();
        /*
         * sourceManager.createNamedQuery("updateProcess12RecordByStatus"). setParameter("msgStatus", MSG_SUCCESS)
         * .setParameter("msgId", srcEntity.getMsgId()).executeUpdate();
         */

        return srcEntity;
    }
    
    /**
     * Update source status
     * 
     * @param srcEntity
     *            Process12
     * @return process Process12
     */
    @TransactionAttribute(REQUIRED)
    public Process12 updateProcess12F(Process12 srcEntity) {
        srcEntity.setMsgStatus(MSG_FAILED);
        sourceManager.createNamedQuery("updateProcess12RecordByStatus").setParameter("msgStatus", MSG_FAILED)
                .setParameter("msgId", srcEntity.getMsgId()).executeUpdate();
        // sourceManager.refresh(srcEntity);
        return srcEntity;
    }

    /**
     * Insert transformed record to the target source
     * 
     * @param srcEntity
     *            Process2
     * @return process Process2
     */
    @TransactionAttribute(REQUIRED)
    public Process2 insertProcessEventLogP2(Process2 srcEntity) {
        ProcessEventLog tarEntity = new ProcessEventLog();
        tarEntity.setMsgId(srcEntity.getMsgId());
        tarEntity.setMsgDatetime(srcEntity.getMsgDatetime());
        tarEntity.setSeqNo(srcEntity.getSeqNo());
        tarEntity.setEventName(Process2.PROCESS_EVENT);
        tarEntity.setBatchId(Integer.valueOf(srcEntity.getOutputBatchId().trim()));
        tarEntity.setThickness(srcEntity.getThickness());
        tarEntity.setWidth(srcEntity.getWidth());
        tarEntity.setEventDatetime(new Date());
        OutputBatch outputBatch = new OutputBatch();
        outputBatch.setBatchId(tarEntity.getBatchId());
        outputBatch.setThickness(srcEntity.getThickness());
        outputBatch.setWidth(srcEntity.getWidth());
        tarEntity.setOutputBatches(new ArrayList<OutputBatch>());
        tarEntity.addOutputBatch(outputBatch);
        targetManager.persist(tarEntity);
        return srcEntity;
    }

    /**
     * Update source status
     * 
     * @param srcEntity
     *            Process2
     * @return process Process2
     */
    @TransactionAttribute(REQUIRED)
    public Process2 updateProcess2(Process2 srcEntity) {
        Process2 updEntity = sourceManager.find(Process2.class, srcEntity.getMsgId());
        updEntity.setMsgStatus(MSG_SUCCESS);
        /*
         * sourceManager.createNamedQuery("updateProcess2RecordByStatus").setParameter("msgStatus", MSG_SUCCESS)
         * .setParameter("msgId", srcEntity.getMsgId()).executeUpdate();
         */
        sourceManager.flush();
        return srcEntity;
    }

}
