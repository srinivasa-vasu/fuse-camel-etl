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
package com.fuse.ra;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;

/**
 * Camel based route builder for the ETL business process flow
 * 
 * @author Srinivasa Vasu
 * @date 08-Aug-2016
 */
@ApplicationScoped
@Startup
@ContextName("ref-ds-integrate-etl")
public class ReferenceRouteBuilder extends RouteBuilder {

    private static String BROKER_URL = "vm://localhost?broker.persistent=false&broker.useJmx=true&broker.useShutdownHook=false";

    static {
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
    }

    
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setBrokerURL(BROKER_URL);
        getContext().addComponent("activemq", activeMQComponent);
        getContext().addRoutePolicyFactory(new MetricsRoutePolicyFactory());

        from("timer://getProcess1Records?period=60000&delay=35000").routeId("Process1_Timer")
                .beanRef("entityHandler", "findProcess1RecordsByStatus").to("activemq:queue:Process1MessageQueue");

        from("activemq:queue:Process1MessageQueue").routeId("Process1_ETL").transacted().split(body()).transform(body())
                .beanRef("entityHandler", "insertProcessEventLogP1").beanRef("entityHandler", "updateProcess1")
                .to("log: ${body}").end();

        from("timer://getProcess12Records?period=60000&delay=40000").routeId("Process12_Timer")
                .beanRef("entityHandler", "findProcess12RecordsByStatus").to("activemq:queue:Process12MessageQueue");

        from("activemq:queue:Process12MessageQueue").routeId("Process12_ETL").transacted().split(body()).doTry()
                .beanRef("entityHandler", "insertProcessEventLogP12").beanRef("entityHandler", "updateProcess12")
                .doCatch(Exception.class).beanRef("entityHandler", "updateProcess12F")
                .to("activemq:Process12ErrorQueue").to("direct:mkmail").end().to("log: ${body}").end();

        from("timer://getProcess2Records?period=60000&delay=50000").routeId("Process2_Timer").transacted()
                .beanRef("entityHandler", "findProcess2RecordsByStatus").to("activemq:queue:Process2MessageQueue");

        from("activemq:queue:Process2MessageQueue").routeId("Process2_ETL").transacted().split(body()).transform(body())
                .beanRef("entityHandler", "insertProcessEventLogP2").beanRef("entityHandler", "updateProcess2")
                .to("log: ${body}").end();

        from("direct:mkmail").routeId("Failed_Messages")
                .transform(body().prepend("Subject: Message Processing Failed \n Body: ")).log("${body}")
                .to("mock:end");

        from("timer://createProcessRecords?period=15000&delay=30000").routeId("Create_Records").transacted()
                .beanRef("entityHandler", "createRecords").end();

        /*
         * from("direct:a").setHeader("subject", constant(
         * "Message Processing Failed")).transform(constant(
         * "Processing failed: \n").append(body())) .to(
         * "smtps://smtp.gmail.com:465?username=&password=&to="
         * );
         */

    }

}
