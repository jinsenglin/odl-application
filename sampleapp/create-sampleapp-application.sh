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
# * impl/src/main/java/org/opendaylight/sampleapp/impl/FlowData.java
# * impl/src/main/java/org/opendaylight/sampleapp/impl/SampleAppConstants.java
# * impl/src/main/java/org/opendaylight/sampleapp/impl/SampleAppDataStoreHandler.java
# * impl/src/main/java/org/opendaylight/sampleapp/impl/SampleAppListener.java
# * impl/src/main/java/org/opendaylight/sampleapp/impl/SampleAppUtils.java
# * impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
# * impl/pom.xml
# * features/src/main/features/features.xml
# * features/pom.xml

<<CMD
cd sampleapp
mvn clean install

cp to-be/sampleapp-1/sampleapp.yang api/src/main/yang/sampleapp.yang
cp to-be/sampleapp-1/api/pom.xml api/pom.xml
cd api
mvn clean install
cd ..

cp to-be/sampleapp-2/SampleAppListener.java impl/src/main/java/org/opendaylight/sampleapp/impl/SampleAppListener.java
cp to-be/sampleapp-2/impl-blueprint.xml impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
cd impl
mvn clean install
cd ..

cp to-be/sampleapp-3/*.java impl/src/main/java/org/opendaylight/sampleapp/impl/
cp to-be/sampleapp-3/impl/pom.xml impl/pom.xml
cp to-be/sampleapp-3/impl-blueprint.xml impl/src/main/resources/org/opendaylight/blueprint/impl-blueprint.xml
cd impl
mvn clean install
cd ..
cp to-be/sampleapp-3/features.xml features/src/main/features/features.xml
cp to-be/sampleapp-3/features/pom.xml features/pom.xml
cd features
mvn clean install
cd ..

mvn clean install
CMD
