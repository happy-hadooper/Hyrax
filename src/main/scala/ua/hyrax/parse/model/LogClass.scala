package ua.hyrax.parse.model

/**
  * Created by devian on 22.07.16.
  */
object LogClass extends Enumeration{
  type LogClass = Value
  val RMContainerImpl= Value("RMContainerImpl")
  val RMAppAttemptImpl= Value("RMAppAttemptImpl")
  val FairScheduler = Value("FairScheduler")
  val Null = Value("Null")


}

