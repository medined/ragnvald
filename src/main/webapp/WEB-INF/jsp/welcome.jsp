<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <style>
            #footer p { font-size: 8pt; margin: 0px; padding: 0px;}
            #footer ul { margin: 0px 0px 0px 0px; padding: 0px;}
            #footer li { font-size: 8pt; margin-left: 20px; }
        </style>
    </head>
<body>
    <ul>
        <li><a href="/console" target="">Database Console</a></li>
        <li><a href="/pokemonsets" target="">Pokemon Set Management</a></li>
    </ul>

    <div id="footer">
        <p>Powered by</p>
        <ul>
            <li><a target="_blank" href="http://www.java.com">Java</li>
            <li><a target="_blank" href="http://maven.apache.org">Maven</li>
            <li><a target="_blank" href="https://netbeans.org">Netbeans</li>
            <li><a target="_blank" href="http://projects.spring.io/spring-boot">Spring Boot</li>
            <li><a target="_blank" href="http://jquery.com/">JQuery</li>
            <li><a target="_blank" href="http://www.datatables.net">DataTable plugin for JQuery</li>
            <li><a target="_blank" href="http://www.h2database.com">H2, the Java SQL database</li>
        </ul>
    </div>
</body>

</html>
