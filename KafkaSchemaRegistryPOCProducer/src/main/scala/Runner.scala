import java.util.Properties

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Runner  {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", "http://localhost:8081")

    val producer = new KafkaProducer[String, GenericRecord](props)

    print("Foooooooo")

    for(a <- 1 to 10) {
      println("Foo")
      val user = User( "12345_with_enum", "valera_dragon_with_enum", "Elasr Alfelts Vej 102", "Valera", WeekDay.other)
      val producerRecord = new ProducerRecord[String, GenericRecord]("my_topic", "User", user.genericRecord)
      val foo = producer.send(producerRecord)
      print("Foo" + foo.get())
    }


        producer.close()
  }
}
