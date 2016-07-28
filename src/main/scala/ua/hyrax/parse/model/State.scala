package ua.hyrax.parse.model

/**
  * Created by devian on 15.07.16.
  */
@SerialVersionUID(1231249239L)
case class State(val containerId: String, val from: String, val to: String, val time: Long) extends Serializable {


  override def equals(other: Any): Boolean = other match {
    case that: State =>
      (that canEqual this) &&
        containerId == that.containerId &&
        from == that.from &&
        to == that.to &&
        time == that.time
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[State]

  override def hashCode(): Int = {
    (time/1000).toInt
  }
}

