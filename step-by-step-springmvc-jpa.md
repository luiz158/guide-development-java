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
	<org.hibernate-version>4.3.6.Final</org.hibernate-version>
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

right click at project and **Maven > Update Project...**  

Dependencies JPA and Hibernate:  
```xml
<!-- Hibernate -->
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-entitymanager</artifactId>
	<version>${org.hibernate-version}</version>
</dependency>
<!-- Spring ORM
To enable Hibernate support we need to add the dependency spring-orm 
that provide support to the main market ORMs supported by Spring: 
Hibernate (3 and 4), iBatis, JDO and JPA
-->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-orm</artifactId>
	<version>${org.springframework-version}</version>
</dependency>
```
 
Create model class **Person** with JPA annottations:  
```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String cpf;

	getters and setters...
}
```

Create interface **PersonDAO**:  
```java
public interface PersonDAO {

	List<Person> listAll();
	void save(Person entity);
	
}
```

Create class **PersonDAOImpl**:  
```java
/**
Para lidarmos com as transformações de exceções específicas de plataforma para as da hierarquia uniforme do
Spring. A solução é bastante simples: basta anotarmos nosso DAO com @Repository ao invés de @Component 
Esta é uma solução pode ser aplicada a qualquer DAO, inclusive JDBC. 
Esta anotação é um estereótipo que permite ao container do Spring aplicar funcionalidades mais interessantes a DAOs. 
 */
@Repository
public class PersonDAOImpl implements PersonDAO {
	private EntityManager entityManager;
	
	@Autowired
	public PersonDAOImpl(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Person> listAll() {
		return this.entityManager.createQuery("from Person").getResultList();
	}

	@Override
	public void save(Person entity) {
		this.entityManager.persist(entity);
	}
}
```
