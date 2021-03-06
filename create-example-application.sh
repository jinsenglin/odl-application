#!/bin/bash

echo "Y" | mvn archetype:generate \
-DarchetypeGroupId=org.opendaylight.controller \
-DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote \
-DarchetypeVersion=1.2.3-Boron-SR3 \
-DgroupId=org.opendaylight.example \
-DartifactId=example \
-Dpackage=org.opendaylight.example \
-DclassPrefix=Example \
-Dcopyright="CC, Inc."
