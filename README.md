# For Boron-SR3

# Setup development environment

```
wget https://raw.githubusercontent.com/opendaylight/odlparent/stable/boron/settings.xml -O ~/.m2/settings.xml
```

# Create a new application

```
mvn archetype:generate -DarchetypeGroupId=org.opendaylight.controller -DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote -DarchetypeVersion=1.2.3-Boron-SR3
```

# Use an existing application

```
cd example/
mvn clean install
./karaf/target/assembly/bin/karaf

cd hello/
mvn clean install
./karaf/target/assembly/bin/karaf
curl -X POST -H "Content-Type: application/json" http://admin:admin@127.0.0.1:8080/restconf/operations/hello:hello-world -d '{"hello:input": { "hello:name":"Your Name"}}'
```

[ Swagger UI ] http://localhost:8080/apidoc/explorer/index.html

[ DLUX UI ] http://localhost:8181/index.html

# References for example and hello applications

* [ Ca ] [ Bo ] https://wiki.opendaylight.org/view/OpenDaylight_Controller:MD-SAL:Startup_Project_Archetype *****
* [ Bo ] http://docs.opendaylight.org/en/stable-boron/developer-guide/developing-apps-on-the-opendaylight-controller.html 
* [ Be ] http://opensourceforu.com/2016/07/creating-your-own-application-in-opendaylight/

# References for sampleapp application (Carbon)

* http://zoo.cs.yale.edu/classes/cs434/cs434-2017-spring/assignments/ps1/ODL_Summit_2016_Sample_App_Tutorial.pdf
