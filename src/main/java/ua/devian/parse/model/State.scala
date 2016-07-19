package ua.devian.parse.model

/**
  * Created by devian on 15.07.16.
  */
@SerialVersionUID(1231249239L)
class State(val containerId: String,val from: String,val to:String ,val time: Long) extends Serializable{


  def canEqual(other: Any): Boolean = other.isInstanceOf[State]

  override def equals(other: Any): Boolean = other match {
    case that: State =>
      (that canEqual this) &&
        containerId == that.containerId &&
        from == that.from &&
        to == that.to &&
        time == that.time
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(containerId, from, to, time)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}