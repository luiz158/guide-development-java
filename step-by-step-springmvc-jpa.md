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
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-jdbc</artifactId>
	<version>${org.springframework-version}</version>
</dependency>		
<dependency>
	<groupId>commons-dbcp</groupId>
	<artifactId>commons-dbcp</artifactId>
	<version>1.2.2</version>
</dependency>

<!-- AOP dependency -->
<dependency>
	<groupId>cglib</groupId>
	<artifactId>cglib</artifactId>
	<version>2.2</version>
</dependency>

<!-- MySQL -->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.31</version>
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

	public Person() {
	}

	public Person(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	// getters and setters...

	@Override
	public String toString() {
		return "Person [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
}```

Create Generic class **PersonDAO**:  
```java
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
Para lidarmos com as transformações de exceções específicas de plataforma para as da hierarquia 
uniforme do Spring. A solução é bastante simples: basta anotarmos nosso DAO com @Repository ao 
invés de @Component Esta é uma solução pode ser aplicada a qualquer DAO, inclusive JDBC. 
Esta anotação é um estereótipo que permite ao container do Spring aplicar funcionalidades mais 
interessantes a DAOs. 
-----------------------
Apenas a presença da anotação @Repositoty nas classes DAO não é suficiente, 
faz-se necessário também incluir a definição de um novo bean do tipo:
PersistenceExceptionTranslationPostProcessor. 
O que este bean faz é criar um aspecto do tipo after throw para todos os beans 
anotados com @Repository.
A exceção será interceptada e automaticamente convertida para outra presente na 
hierarquia de exceções uniformizada do Spring. adicione o bean no arquivo: servlet-context.xml
<bean class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>	
*/
@Repository
public class GenericDAO<T> {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void persist(T entity) {
		System.out.println(entity.getClass().getName());
		manager.persist(entity);
	}
}
```

Create class **PersonDAOImpl**:  
```java
import org.springframework.stereotype.Repository;
import com.eprogramar.springjpa.model.Person;

@Repository("personDAOImpl")
public class PersonDAOImpl extends GenericDAO<Person> {
}
```

class HomeController
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eprogramar.springjpa.dao.PersonDAOImpl;
import com.eprogramar.springjpa.model.Person;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private PersonDAOImpl personDAOImpl;

	@Autowired
	@Qualifier("personDAOImpl")
	public void setPersonDAOImpl(PersonDAOImpl personDAOImpl) {
		this.personDAOImpl = personDAOImpl;
	}
	
	public PersonDAOImpl getPersonDAOImpl() {
		return personDAOImpl;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("home()...");
		ModelAndView mv = new ModelAndView("home");
		
		getPersonDAOImpl().persist( new Person("Fabiano", "123") );
		
		return mv;
	}
	
}
```
