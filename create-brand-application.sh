#!/bin/bash

echo "Y" | mvn archetype:generate \
-DarchetypeGroupId=org.opendaylight.controller \
-DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote \
-DarchetypeVersion=1.2.3-Boron-SR3 \
-DgroupId=tw.com.cc.brand \
-DartifactId=brand \
-Dpackage=tw.com.cc.brand \
-DclassPrefix=Brand \
-Dcopyright="CC, Inc."

# Then modify following files
# * karaf/target/assembly/etc/branding.properties

<<CMD
cd brand
mvn clean install

cp to-be/branding.properties karaf/target/assembly/etc/branding.properties
CMD
