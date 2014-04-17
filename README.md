Phyraxi
=====

Modules
-------

* phyraxi-core: shared classes (domain objects and such)
* phyraxi-engine: game engine
* phyraxi-rest-server: a REST server
 * generates a standalone runnable JAR
* phyraxi-web-client: static (HTML, JS) web client
 * generates a WAR including the REST server, and a tar.gz package of static content for separate deployment

Building
--------

A complete build can be done by building in the top-level directory. Run "mvn package" to generate all the artifacts, "mvn install" to also install them in the local repository. Similarly, each module can be built individually by running the command in the respective module directory - this will use the locally installed artifacts for sibling modules.

Running
-------

* the REST server can be run in standalone-mode; "mvn package && java -jar target/phyraxi-rest-server-&lt;version&gt;-jar-with-dependencies.jar"
* web client project can be run as a webapp containing the server: "mvn jetty:run"
 * if the above doesn't work, try with the groupId: "mvn org.eclipse.jetty:jetty:run" (or configure plugin groups)
