#!/bin/bash

echo "Y" | mvn archetype:generate \
-DarchetypeGroupId=org.opendaylight.controller \
-DarchetypeArtifactId=opendaylight-startup-archetype \
-DarchetypeRepository=http://nexus.opendaylight.org/content/repositories/opendaylight.release/ \
-DarchetypeCatalog=remote \
-DarchetypeVersion=1.2.3-Boron-SR3 \
-DgroupId=org.opendaylight.hello \
-DartifactId=hello \
-Dpackage=org.opendaylight.hello \
-DclassPrefix=Hello \
-Dcopyright="CC, Inc."

# Then modify following files
# * api/src/main/yang/hello.yang
# * impl/src/main/java/org/opendaylight/hello/impl/HelloWorldImpl.java
# * impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
# * impl/src/main/java/org/opendaylight/hello/impl/HelloProvider.java

<<CMD
cd hello
mvn clean install

cp to-be/hello.yang api/src/main/yang/hello.yang
cd api
mvn clean install
cd ..

cp HelloWorldImpl.java impl/src/main/java/org/opendaylight/hello/impl/HelloWorldImpl.java
cp impl-blueprint.xml impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
cp HelloProvider.java impl/src/main/java/org/opendaylight/hello/impl/HelloProvider.java
cd impl
mvn clean install
cd ..

mvn clean install
CMD
