Maven Dependence for Project Spring MVC with Spring IoC/Spring Transaction/JPA/Hibernate
========================================================================================

**this example is using MySQL Database**

```xml
<dependencies>
	<!-- Spring -->
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>${org.springframework-version}</version>
		<exclusions>
			<!-- Exclude Commons Logging in favor of SLF4j -->
			<exclusion>
				<groupId>commons-logging</groupId>
				<artifactId>commons-logging</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-webmvc</artifactId>
		<version>${org.springframework-version}</version>
	</dependency>

	<!-- AspectJ -->
	<dependency>
		<groupId>org.aspectj</groupId>
		<artifactId>aspectjrt</artifactId>
		<version>${org.aspectj-version}</version>
	</dependency>

	<!-- Logging -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-api</artifactId>
		<version>${org.slf4j-version}</version>
	</dependency>
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>jcl-over-slf4j</artifactId>
		<version>${org.slf4j-version}</version>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-log4j12</artifactId>
		<version>${org.slf4j-version}</version>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>log4j</groupId>
		<artifactId>log4j</artifactId>
		<version>1.2.15</version>
		<exclusions>
			<exclusion>
				<groupId>javax.mail</groupId>
				<artifactId>mail</artifactId>
			</exclusion>
			<exclusion>
				<groupId>javax.jms</groupId>
				<artifactId>jms</artifactId>
			</exclusion>
			<exclusion>
				<groupId>com.sun.jdmk</groupId>
				<artifactId>jmxtools</artifactId>
			</exclusion>
			<exclusion>
				<groupId>com.sun.jmx</groupId>
				<artifactId>jmxri</artifactId>
			</exclusion>
		</exclusions>
		<scope>runtime</scope>
	</dependency>

	<!-- @Inject -->
	<dependency>
		<groupId>javax.inject</groupId>
		<artifactId>javax.inject</artifactId>
		<version>1</version>
	</dependency>

	<!-- Servlet -->
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>servlet-api</artifactId>
		<version>2.5</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet.jsp</groupId>
		<artifactId>jsp-api</artifactId>
		<version>2.1</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>jstl</artifactId>
		<version>1.2</version>
	</dependency>

	<!-- JPA / Hibernate -->
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-entitymanager</artifactId>
		<version>${org.hibernate-version}</version>
	</dependency>
	<dependency>
		<groupId>org.hibernate.javax.persistence</groupId>
		<artifactId>hibernate-jpa-2.1-api</artifactId>
		<version>1.0.0.Final</version>
	</dependency>

	<!-- MySQL -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>5.1.31</version>
	</dependency>

	<!-- Test -->
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.7</version>
		<scope>test</scope>
	</dependency>
</dependencies>

```

*for use with HSQLDB use an depencence:*

```xml
<dependency>
	<groupId>hsqldb</groupId>
	<artifactId>hsqldb</artifactId>
	<version>1.8.0.10</version>
</dependency>
```
