import ammonite.ops._
import com.sksamuel.avro4s.AvroSchema

object AvroConverter {
  private val avroSchemas = Set(
  AvroSchema[User]
  )
  def main(args: Array[String]): Unit = {
    rm(pwd / "avro")
    mkdir(pwd / "avro")
    avroSchemas.foreach { schema =>
      write(pwd / "avro" / s"${schema.getFullName}.avsc", schema.toString(true))
    }
  }
}
