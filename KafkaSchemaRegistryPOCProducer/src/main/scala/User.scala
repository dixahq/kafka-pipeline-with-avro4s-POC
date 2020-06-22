import WeekDay.WeekDay
import com.sksamuel.avro4s.RecordFormat
import org.apache.avro.generic.GenericRecord

case class User(userId: String, username: String, address: String, name: String, weekday: WeekDay) {
  val genericRecord: GenericRecord = RecordFormat[User].to(this)
}