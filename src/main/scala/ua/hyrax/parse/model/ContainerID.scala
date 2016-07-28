package ua.hyrax.parse.model

import java.text.NumberFormat

/**
  * Created by devian on 22.07.16.
  */
//container_1467850995136_40534_01_000534
// 1467850995136 (время старта ресурс менеджера)
// 40534 порядковый номер апликухи
//01 порядковый номер аттемта
// номер контейнера 00534
class ContainerID(val time: Long,val appId: Int,val attemptId: Int,val containerId: Int) extends Serializable{
  override def toString: String = {
    val sb: StringBuilder = new StringBuilder
    sb.append("container_")
    sb.append(time)
    sb.append(ContainerID.appIdFormat.get.format(appId)).append("_")
    sb.append(ContainerID.appAttemptIdFormat.get.format(attemptId)).append("_")
    sb.append(ContainerID.containerIdFormat.get.format(containerId))
    sb.toString
  }


}

object ContainerID{
  private val appIdFormat: ThreadLocal[NumberFormat] = new ThreadLocal[NumberFormat]() {
    override def initialValue: NumberFormat = {
      val fmt: NumberFormat = NumberFormat.getInstance
      fmt.setGroupingUsed(false)
      fmt.setMinimumIntegerDigits(4)
      fmt
    }
  }
  private val appAttemptIdFormat: ThreadLocal[NumberFormat] = new ThreadLocal[NumberFormat]() {
    override def initialValue: NumberFormat = {
      val fmt: NumberFormat = NumberFormat.getInstance
      fmt.setGroupingUsed(false)
      fmt.setMinimumIntegerDigits(2)
      fmt
    }
  }
  private val containerIdFormat: ThreadLocal[NumberFormat] = new ThreadLocal[NumberFormat]() {
    override def initialValue: NumberFormat = {
      val fmt: NumberFormat = NumberFormat.getInstance
      fmt.setGroupingUsed(false)
      fmt.setMinimumIntegerDigits(6)
      fmt
    }
  }
}
