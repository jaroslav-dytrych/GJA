Run the example application using Java 1.8 this way:
JAVA_HOME=/home/userName/jdk1.8.0_221/ && mvn test

Doesn't work with Java 9+ (different ClassLoader => No classpath URLs matched.).
