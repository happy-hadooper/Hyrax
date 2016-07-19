package ua.devian.parse.util

import ua.devian.parse.Const
import ua.devian.parse.model.{Log, State}

/**
  * Created by devian on 15.07.16.
  */

class Container(val name:String,var states: Iterable[State]) extends Serializable{

}
object Container extends Serializable{

  def killed(string: String) = {
    string.contains("KILLED".intern())
  }
  def completed(string: String) = {
    string.contains("COMPLETED".intern())
  }
  def released(string: String) = {
    string.contains("RELEASED".intern())
  }

  def inTransition(log:(_,_,String,_)): Boolean ={
    log._3.contains(Const.CLASS_CONTAINER_IMPL)
  }
  def inTransition(log: Log): Boolean ={
    log.logClass.contains(Const.CLASS_CONTAINER_IMPL)
  }
}