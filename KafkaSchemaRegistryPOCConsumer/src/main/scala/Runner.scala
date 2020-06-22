import java.time.Duration
import java.util.Properties

import com.sksamuel.avro4s.RecordFormat
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.KafkaConsumer

import collection.JavaConverters._

object Runner  {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("schema.registry.url", "http://localhost:8081")
    props.put("group.id", "test")

    val consumer = new KafkaConsumer[String, GenericRecord](props)
    val topics = Seq("my_topic").asJava
    consumer.subscribe(topics)

    try {
      while(true) {
        val records = consumer.poll(Duration.ofSeconds(10))
        for(r <- records.asScala) {
          val user = RecordFormat[User].from(r.value())
          println("Consuming: " + user.username)
        }
      }
    } catch {
      case e => println("Error: " + e.getLocalizedMessage)
    } finally {
      consumer.close()
    }
  }
}