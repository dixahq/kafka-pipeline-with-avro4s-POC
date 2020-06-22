# Proof of Concept: A Kafka Pipeline with Avro4s and Confluent Schema Registry

This is a POC that contains a Kafka Consumer and a Kafka Producer. 
The messages are being automatically de/serialized using a library [Avro4s](https://github.com/sksamuel/avro4s)
The communication between a producer and a consumer is using a Confluent schema registry to validate the protocols

## Prerequisites
* [Apache Kafka 2.12_2.3.1](https://kafka.apache.org/downloads)
* [Apache Zookeeper 3.5.6](https://zookeeper.apache.org/releases.html) or later
* [Confluent Platform] (https://www.confluent.io/download/)

## Deployment
* Start zookeeper sever. Run from the Zookeeper root folder: 
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

* Start Kafka server. Run from the Kafka root folder:
```
bin/kafka-server-start.sh config/server.properties
```

* Start Schema Registry. Run from the Confluent Platform root:
```
bin/schema-registry-start etc/schema-registry/schema-registry.properties
```

* Create a new topic "my_topic". Run from the Kafka root:

```
/bin/kafka-topics.sh --create \
    --zookeeper <hostname>:<port> \
    --topic my_topic \
    --partitions <number-of-partitions> \
    --replication-factor <number-of-replicating-servers>
```

## Verifying
You can use Kafka console to verify that consumer and producer work as expected:

 ```
 bin/kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic
 bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic --from-beginning
 ```
 ## Gerating Avro files with Avro4s:
 Run from the producer root:

 ```
 sbt generateAvro
```

 ## Deploy Schema Registry
 After the avro file is generated, you need to deploy it using the [Confluent Schema Registry API](https://docs.confluent.io/current/schema-registry/develop/using.html)

 

