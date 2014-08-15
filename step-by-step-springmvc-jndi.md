Step-by-Step - SpringMVC and JNDI
=================================

Step-by-Step for create and configuration a project Spring MVC with JNDI Pool Connection

- Create Spring Project / Spring MVC Project, Name: **SpringJNDI**
- Modify the property **java-version** at pom.xml file for **1.7** and use the property in build block:
- Right click at project and Maven > **Update Project...**
 Create file [context.xml](context.xml) at directory **webapp/META-INF/**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/SpringJNDI" docBase="SpringJNDI">
 
    <Resource name="jdbc/SpringJNDI_Pool"
              global="jdbc/SpringJNDI_Pool"
              auth="Container"
              type="javax.sql.DataSource"
              driverClassName="com.mysql.jdbc.Driver"
              url="jdbc:mysql://192.9.200.20:3306/test"
              username="root"
              password="root"
               
              maxActive="100"
              maxIdle="20"
              minIdle="5"
              maxWait="10000"/>                  
</Context>
```

- Modify **web.xml** file and add code:
```xml
<resource-ref>
    <res-ref-name>jdbc/SpringJNDI_Pool</res-ref-name>
    <res-type>javax.sql.DataSource</res-type>
    <res-auth>Container</res-auth>
</resource-ref> 
```  

- Add bean DataSource at file **servlet-context.xml**  
```xml
<!-- Create DataSource Bean -->
<beans:bean id="dbDataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
        <beans:property name="jndiName" value="java:comp/env/jdbc/SpringJNDI_Pool" />
</beans:bean>	
```  
 
- Inject DataSource at class DAO:
```java
@Repository("exemploDAO")
public class ExemploDAO {

	@Autowired
	private DataSource dsExemplo;

	public void setDsExemplo(DataSource dsExemplo) {
		this.dsExemplo = dsExemplo;
	}
	
}
```  
