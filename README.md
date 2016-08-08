# fuse-camel-etl
Red Hat Fuse based camel integration (ETL between two relational sources)

Fuse Camel ETL
-----------------

This example demonstrates using the camel-jpa and few integration components with A-MQ running on Red Hat JBoss Fuse to produce and consume JMS messages
to extract, transform and load entities from two relational data sources.

In this example, a Camel route controlled by a timer component that consumes records from source relational system that get transformed and loaded into a target relational system.

Prerequisites
-------------

* Maven
* JBoss Fuse Server

Create two relational sources, may be
* Source - Postgres
* Target - MySQL

You should find ddls' under src/main/resources/db. Create schemas, run the ddls and configure JNDI data sources as,
* java:/mysqljws
* java:/psqljws 

or you can have a different name which needs to be updated in persistence.xml

Running the example
-------------------

To run the example.

1. Start the application server in standalone mode `${JBOSS_HOME}/bin/standalone.sh -c standalone-full.xml`
2. Build and deploy the project `mvn install -Pdeploy`
3. Browse to http://localhost:8080/hawtio/

You should see the Camel routes and A-MQ queues.


Undeploy
--------

To undeploy the example run `mvn clean -Pdeploy`.
