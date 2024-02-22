How to run it successfully:
1. Install Java 8.x, NetBeans (8.x+) and GlassFish 5.0 and run GlassFish (GlassFish 4.1.1 is unusable due to bug)
2. Go to http://localhost:4848/ -> Resources -> JMS Resources -> Connection Factories
3. Create New:
JNDI Name: TopicConnectionFactory
Resource Type: javax.jms.TopicConnectionFactory
4. Go to Resources -> JMS Resources -> Destination Resources
5. Create New:
JNDI Name: myTopic
Physical Destination Name: myTopic
Resource Type: javax.jms.Topic
5. Open this project
6. Right click on project -> Clean and Build
7. Right click on project -> Properties -> Run
- Set Arguments: myTopic name pass
8. Right click on project -> Run (then it will ask for selection of main class - you have only one choice)
9. Right click on project -> Properties -> Run
- Set Arguments: myTopic name2 pass
10. Right click on project -> Run
11. You can run more instances (repeat 10 and 11), but it is not necessary.
12. Write something into one instance console and observe other instances.
13. Write "exit" into console of given instance to quit.

Note: GlassFish 5.0 doesn't support Java 11+ and application will work properly only if it will run on same JRE as GlassFish. So without NetBeans running with old JVM you can run it e.g.:
JAVA_HOME=/home/user/jdk1.8.0_221/mvn compile
JAVA_HOME=/home/user/jdk1.8.0_221/mvn exec:java -Dexec.args="myTopic name pass"
and
JAVA_HOME=/home/user/jdk1.8.0_221/mvn exec:java -Dexec.args="myTopic name2 pass"
