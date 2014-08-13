Step-by-Step - Spring MVC and JPA
=================================

*Step-by-Step for create and configuration a project Spring MVC with JPA and Hibernate*

**IDE:** STS - Spring Tool Suite  
**Java Version:** 1.7  
**Spring Version:** 3.1.1.RELEASE  

---------------------------------

**Create Spring Project / Spring MVC Project**  
**Project Name:** SpringJPA

modify the property **java-version** at pom.xml file for **1.7**: 
```xml
<properties>
	<java-version>1.7</java-version>
	<org.springframework-version>3.1.1.RELEASE</org.springframework-version>
	<org.aspectj-version>1.6.10</org.aspectj-version>
	<org.slf4j-version>1.6.6</org.slf4j-version>
</properties>
```

at pom.xml file modify **maven-compiler-plugin** and add property **java-version**:  
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>2.5.1</version>
	<configuration>
	    <source>${java-version}</source>
	    <target>${java-version}</target>
	    <compilerArgument>-Xlint:all</compilerArgument>
	    <showWarnings>true</showWarnings>
	    <showDeprecation>true</showDeprecation>
	</configuration>
</plugin>
```
