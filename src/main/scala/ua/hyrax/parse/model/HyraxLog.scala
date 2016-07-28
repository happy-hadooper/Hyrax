package ua.hyrax.parse.model
import java.util.concurrent.atomic.AtomicInteger
/**
  * Created by devian on 27.07.16.
  */
class HyraxLog(val killedContainers: AtomicInteger,val completedContainers: AtomicInteger,val releasedContainers: AtomicInteger) {



}
object HyraxLog{
  def apply(
  killedContainers: AtomicInteger,
  completedContainers: AtomicInteger,
  releasedContainers: AtomicInteger
  ): HyraxLog = new HyraxLog(killedContainers,completedContainers,releasedContainers)


  def apply(): HyraxLog = new HyraxLog(new AtomicInteger(0),new AtomicInteger(0),new AtomicInteger(0))
}