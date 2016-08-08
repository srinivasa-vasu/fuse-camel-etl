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
package com.fuse.ra.camel.resource;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Configures a SpringTransactionPolicy using the PROPAGATION_REQUIRED policy.
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */

@Named("PROPAGATION_REQUIRED")
public class PropagationRequiredPolicy extends SpringTransactionPolicy {
    @Inject
    public PropagationRequiredPolicy(AppTransactionManager cdiTransactionManager) {
        super(new TransactionTemplate(cdiTransactionManager,
                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED)));
    }
}
