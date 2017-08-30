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

cd sampleapp/sampleapp/
mvn clean install
./karaf/target/assembly/bin/karaf clean
curl -X POST -H "Content-Type: application/json" http://admin:admin@127.0.0.1:8080/restconf/config/sampleapp:access-list -d @add-permit-rule.json
curl -X POST -H "Content-Type: application/json" http://admin:admin@127.0.0.1:8080/restconf/config/sampleapp:access-list -d @add-deny-rule.json
curl -X GET http://admin:admin@127.0.0.1:8080/restconf/config/sampleapp:access-list
```

[ Swagger UI ] http://localhost:8080/apidoc/explorer/index.html

[ DLUX UI ] http://localhost:8181/index.html

# References for example and hello applications (Boron-SR3)

* [ Ca ] [ Bo ] https://wiki.opendaylight.org/view/OpenDaylight_Controller:MD-SAL:Startup_Project_Archetype *****
* [ Bo ] http://docs.opendaylight.org/en/stable-boron/developer-guide/developing-apps-on-the-opendaylight-controller.html 
* [ Be ] http://opensourceforu.com/2016/07/creating-your-own-application-in-opendaylight/

# References for sampleapp application (Carbon)

* http://zoo.cs.yale.edu/classes/cs434/cs434-2017-spring/assignments/ps1/ODL_Summit_2016_Sample_App_Tutorial.pdf

# Troubleshooting

```
# Error Message

[ERROR] Failed to execute goal on project sampleapp-impl: Could not resolve dependencies for project org.opendaylight.sampleapp:sampleapp-impl:bundle:0.1.0-SNAPSHOT: The following artifacts could not be resolved: org.opendaylight.openflowplugin.model:model-flow-service:jar:0.4.0-SNAPSHOT, org.opendaylight.openflowplugin.model:model-flow-base:jar:0.4.0-SNAPSHOT, org.opendaylight.genius:mdsalutil-api:jar:0.2.0-SNAPSHOT: Could not find artifact org.opendaylight.openflowplugin.model:model-flow-service:jar:0.4.0-SNAPSHOT in opendaylight-snapshot (https://nexus.opendaylight.org/content/repositories/opendaylight.snapshot/) -> [Help 1]

# Google model-flow-service
# -> https://github.com/opendaylight/openflowplugin/blob/master/model/model-flow-service/pom.xml
# -> Switch to branch stable/Boron # Boron Boron Boron instead of Carbon
# -> Get <version>0.3.5-SNAPSHOT</version>
#
# Check https://nexus.opendaylight.org/content/repositories/public/
```

# Bug

```
2017-08-30 10:07:29,118 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT | Sample App listener onDataTreeChanged [LazyDataTreeModification{path = DataTreeIdentifier{datastoreType = CONFIGURATION, rootIdentifier = InstanceIdentifier{targetType=interface org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.AccessList, path=[org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.AccessList]}}, rootNode = LazyDataObjectModification{identifier = org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.AccessList, domData = ChildNode{mod = NodeModification [identifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list, modificationType=MERGE, childModification={(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry=NodeModification [identifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry, modificationType=MERGE, childModification={(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry[{(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name=permit}]=NodeModification [identifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry[{(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name=permit}], modificationType=WRITE, childModification={}]}]}], oldMeta = null, newMeta = SimpleContainerNode{version=org.opendaylight.yangtools.yang.data.api.schema.tree.spi.Version@63ddb500, data=ImmutableContainerNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list, value=[ImmutableMapNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry, value=[ImmutableMapEntryNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry[{(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name=permit}], value=[ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)node-id, value=openflow:1, attributes={}}, ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name, value=permit, attributes={}}, ImmutableContainerNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)matches, value=[ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)destination-ipv4-network, value=10.0.0.2/32, attributes={}}, ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)source-ipv4-network, value=10.0.0.1/32, attributes={}}], attributes={}}, ImmutableContainerNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)actions, value=[ImmutableChoiceNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)packet-handling, value=[ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)permit, value=null, attributes={}}]}], attributes={}}], attributes={}}]}], attributes={}}}}}}] 
2017-08-30 10:07:29,131 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  Write after data AccessList{getAccessListEntry=[AccessListEntry{getActions=Actions{getPacketHandling=Permit{isPermit=true, augmentations={}}, augmentations={}}, getMatches=Matches{getDestinationIpv4Network=Ipv4Prefix [_value=10.0.0.2/32], getSourceIpv4Network=Ipv4Prefix [_value=10.0.0.1/32], augmentations={}}, getNodeId=openflow:1, getRuleName=permit, augmentations={}}], augmentations={}} 
2017-08-30 10:07:29,131 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  Printing the AccessList Entries : 
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  Key : AccessListEntryKey [_ruleName=permit]
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  Rule Name : permit
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  NodeList :  openflow:1
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  Actions : Actions{getPacketHandling=Permit{isPermit=true, augmentations={}}, augmentations={}}
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  Matches Matches{getDestinationIpv4Network=Ipv4Prefix [_value=10.0.0.2/32], getSourceIpv4Network=Ipv4Prefix [_value=10.0.0.1/32], augmentations={}}
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppListener                | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  AccessListEntry : AccessListEntry{getActions=Actions{getPacketHandling=Permit{isPermit=true, augmentations={}}, augmentations={}}, getMatches=Matches{getDestinationIpv4Network=Ipv4Prefix [_value=10.0.0.2/32], getSourceIpv4Network=Ipv4Prefix [_value=10.0.0.1/32], augmentations={}}, getNodeId=openflow:1, getRuleName=permit, augmentations={}}
2017-08-30 10:07:29,132 | INFO  | on-dispatcher-41 | SampleAppDataStoreHandler        | 276 - org.opendaylight.sampleapp.impl - 0.1.0.SNAPSHOT |  installflow with data LazyDataObjectModification{identifier = org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.AccessList, domData = ChildNode{mod = NodeModification [identifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list, modificationType=MERGE, childModification={(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry=NodeModification [identifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry, modificationType=MERGE, childModification={(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry[{(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name=permit}]=NodeModification [identifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry[{(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name=permit}], modificationType=WRITE, childModification={}]}]}], oldMeta = null, newMeta = SimpleContainerNode{version=org.opendaylight.yangtools.yang.data.api.schema.tree.spi.Version@63ddb500, data=ImmutableContainerNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list, value=[ImmutableMapNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry, value=[ImmutableMapEntryNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)access-list-entry[{(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name=permit}], value=[ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)node-id, value=openflow:1, attributes={}}, ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)rule-name, value=permit, attributes={}}, ImmutableContainerNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)matches, value=[ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)destination-ipv4-network, value=10.0.0.2/32, attributes={}}, ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)source-ipv4-network, value=10.0.0.1/32, attributes={}}], attributes={}}, ImmutableContainerNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)actions, value=[ImmutableChoiceNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)packet-handling, value=[ImmutableLeafNode{nodeIdentifier=(urn:opendaylight:params:xml:ns:yang:sampleapp?revision=2015-01-05)permit, value=null, attributes={}}]}], attributes={}}], attributes={}}]}], attributes={}}}}} 
Uncaught error from thread [opendaylight-cluster-data-notification-dispatcher-41] shutting down JVM since 'akka.jvm-exit-on-fatal-error' is enabled for ActorSystem[opendaylight-cluster-data]
java.lang.InstantiationError: org.opendaylight.genius.mdsalutil.FlowEntity
    at org.opendaylight.sampleapp.impl.SampleAppDataStoreHandler.getFlowEntity(SampleAppDataStoreHandler.java:72)
    at org.opendaylight.sampleapp.impl.SampleAppDataStoreHandler.populateMDsalUtilWay(SampleAppDataStoreHandler.java:51)
    at org.opendaylight.sampleapp.impl.SampleAppDataStoreHandler.installFlow(SampleAppDataStoreHandler.java:46)
    at org.opendaylight.sampleapp.impl.SampleAppListener.onDataTreeChanged(SampleAppListener.java:71)
    at org.opendaylight.controller.md.sal.binding.impl.BindingDOMDataTreeChangeListenerAdapter.onDataTreeChanged(BindingDOMDataTreeChangeListenerAdapter.java:41)
    at org.opendaylight.controller.cluster.datastore.DataTreeChangeListenerActor.dataChanged(DataTreeChangeListenerActor.java:59)
    at org.opendaylight.controller.cluster.datastore.DataTreeChangeListenerActor.handleReceive(DataTreeChangeListenerActor.java:38)
    at org.opendaylight.controller.cluster.common.actor.AbstractUntypedActor.onReceive(AbstractUntypedActor.java:28)
    at akka.actor.UntypedActor$$anonfun$receive$1.applyOrElse(UntypedActor.scala:165)
    at akka.actor.Actor$class.aroundReceive(Actor.scala:497)
    at akka.actor.UntypedActor.aroundReceive(UntypedActor.scala:95)
    at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
    at akka.actor.ActorCell.invoke(ActorCell.scala:495)
    at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
    at akka.dispatch.Mailbox.run(Mailbox.scala:224)
    at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
    at scala.concurrent.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
    at scala.concurrent.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
    at scala.concurrent.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
    at scala.concurrent.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)
2017-08-30 10:07:29,139 | ERROR | lt-dispatcher-20 | ActorSystemImpl                  | 179 - com.typesafe.akka.slf4j - 2.4.17 | Uncaught error from thread [opendaylight-cluster-data-notification-dispatcher-41] shutting down JVM since 'akka.jvm-exit-on-fatal-error' is enabled
```
