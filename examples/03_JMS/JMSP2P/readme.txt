How to run it successfully:
1. Install Java 8.x, NetBeans (8.x+) with GlassFish 5.0 and run GlassFish (GlassFish 4.1.1 is unusable due to bug)
2. Go to http://localhost:4848/ -> Resources -> JMS Resources -> Connection Factories
3. Create New:
JNDI Name: QueueConnectionFactory
Resource Type: javax.jms.QueueConnectionFactory
4. Go to Resources -> JMS Resources -> Destination Resources
5. Create New:
JNDI Name: myQueue
Physical Destination Name: myQueue
Resource Type: javax.jms.Queue
6. Open this project
7. Right click on project -> Clean and Build
8. Right click on Receiver.java -> Run File
9. Right click on Sender.java -> Run File
10. Observe outputs

Note: GlassFish 5.0 doesn't support Java 11+ and application will work properly only if it will run on same JRE as GlassFish. So without NetBeans running with old JVM you can run it e.g.:
JAVA_HOME=/home/dytrych/NetBeans/jdk1.8.0_221/ && mvn compile
JAVA_HOME=/home/dytrych/NetBeans/jdk1.8.0_221/ && mvn exec:java -Dexec.mainClass="cz.vutbr.fit.knot.gja.Sender"
JAVA_HOME=/home/dytrych/NetBeans/jdk1.8.0_221/ && mvn exec:java -Dexec.mainClass="cz.vutbr.fit.knot.gja.Receiver"
