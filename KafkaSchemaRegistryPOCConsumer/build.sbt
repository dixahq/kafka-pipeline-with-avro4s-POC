name := "KafkaSchemaRegistryPOCConsumer"

version := "0.1"

scalaVersion := "2.12.9"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.3.1",
  "com.sksamuel.avro4s" %% "avro4s-core" % "2.0.2",
  "io.confluent" % "kafka-avro-serializer" % "5.5.0"
)

resolvers += "confluent" at "https://packages.confluent.io/maven/"
