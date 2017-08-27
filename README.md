# For Boron-SR3

# Setup development environment

```
wget https://raw.githubusercontent.com/opendaylight/odlparent/stable/boron/settings.xml -O ~/.m2/settings.xml
```

# Create a new application

```
mvn archetype:generate -DarchetypeGroupId=org.opendaylight.controller -DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote -DarchetypeVersion=1.2.3-Boron-SR3 \
-DarchetypeGroupId=org.opendaylight.example \
-DarchetypeArtifactId=example \
-DarchetypePackage=org.opendaylight.example \
-DarchetypeClassPrefix=Example \
-DarchetypeCopyright=CC
```
