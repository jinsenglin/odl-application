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
