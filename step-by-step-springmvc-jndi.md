Step-by-Step - SpringMVC and JNDI
=================================

Step-by-Step for create and configuration a project Spring MVC with JNDI Pool Connection

- Create Spring Project / Spring MVC Project, Name: **SpringJNDI**
- Modify the property **java-version** at pom.xml file for **1.7** and use the property in build block:
- Right click at project and Maven > **Update Project...**
 Create file [context.xml](context.xml) at directory **webapp/META-INF/**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/MonitorSosFirst" docBase="MonitorSosFirst">
 
        <Resource name="jdbc/monitorSosfirst_Pool"
                  global="jdbc/monitorSosfirst_Pool"
                  auth="Container"
                  type="javax.sql.DataSource"
                  driverClassName="oracle.jdbc.driver.OracleDriver"
                  url="jdbc:oracle:thin:@192.168.9.101:1526/tdp"
                  username="tdvadm"
                  password="aged12"
                   
                  maxActive="100"
                  maxIdle="20"
                  minIdle="5"
                  maxWait="10000"/>
                  
</Context>
```

-- Modify **web.xml** file and add code:
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/MonitorSosFirst" docBase="MonitorSosFirst">
 
        <Resource name="jdbc/monitorSosfirst_Pool"
                  global="jdbc/monitorSosfirst_Pool"
                  auth="Container"
                  type="javax.sql.DataSource"
                  driverClassName="oracle.jdbc.driver.OracleDriver"
                  url="jdbc:oracle:thin:@192.168.9.101:1526/tdp"
                  username="tdvadm"
                  password="aged12"
                   
                  maxActive="100"
                  maxIdle="20"
                  minIdle="5"
                  maxWait="10000"/>
                  
</Context>
 ```  
 
6. At Class DAO Inject DataSource: 
