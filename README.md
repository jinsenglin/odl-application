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
```

[ REST Web UI ] http://localhost:8181/apidoc/explorer/index.html

[ DLUX Web UI ] http://localhost:8181/index.html

# References

* [ Bo ] http://docs.opendaylight.org/en/stable-boron/developer-guide/developing-apps-on-the-opendaylight-controller.html 
* [ Ca ] [ Bo ] https://wiki.opendaylight.org/view/OpenDaylight_Controller:MD-SAL:Startup_Project_Archetype
* [ Be ] http://opensourceforu.com/2016/07/creating-your-own-application-in-opendaylight/
