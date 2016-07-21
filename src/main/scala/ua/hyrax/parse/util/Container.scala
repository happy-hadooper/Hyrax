package ua.hyrax.parse.util

import ua.hyrax.parse.Const
import ua.hyrax.parse.model.{Log, State}

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