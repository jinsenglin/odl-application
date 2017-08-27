#!/bin/bash

echo "Y" | mvn archetype:generate -DarchetypeGroupId=org.opendaylight.controller -DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote -DarchetypeVersion=1.2.3-Boron-SR3 \
-DgroupId=org.opendaylight.hello \
-DartifactId=hello \
-Dpackage=org.opendaylight.hello \
-DclassPrefix=Hello \
-Dcopyright="CC, Inc."
