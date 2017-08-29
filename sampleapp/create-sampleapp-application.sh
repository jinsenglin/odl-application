#!/bin/bash

echo "Y" | mvn archetype:generate \
-DarchetypeGroupId=org.opendaylight.controller \
-DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote \
-DarchetypeVersion=1.3.0-Carbon \
-DgroupId=org.opendaylight.sampleapp \
-DartifactId=sampleapp \
-Dpackage=org.opendaylight.sampleapp \
-DclassPrefix=Sampleapp \
-Dcopyright="CC, Inc."

# Then modify following files according to the instractions in the file "instructions.txt"
# * api/src/main/yang/sampleapp.yang
# * api/pom.xml
# * ===========
# * impl/src/main/java/org/opendaylight/sampleapp/impl/SampleAppListener.java
# * impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
# * ===========
# * impl/**.java
# * impl/pom.xml
# * impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
# * features/src/main/features/features.xml
