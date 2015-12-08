# pdsEasyTravel
This project is an application to manage the travel info as Uber style. It's intended as a concept proof. It's not finished yet.
The application is a J2EE pure application: Java Server Faces v2, boostrap v3, EJB v3, Hibernate v5 and JBoss WildFly 9. 
Spring, Struts and other technologies are not included because aren't needed at this moment.

To deploy the application, it's necessary to create an database model in a posgre v9 DBMS and setting a datasource in JBoss WildFly 9. 

To resume, you must execute the inicial_data_v1.sql script file.
Then you have to create a dataSource in Jboss called "jboss/postgresDS" with "USER" and "PASSWORD" tokens: user=USER and password="PASSWORD".
After that, you have to run "ant" in the pdsEasyTravel source folder. 
The application will be deployed in jboss and it will be ready to work.

Enjoy



