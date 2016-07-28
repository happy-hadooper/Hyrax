package ua.hyrax.parse.model

import ua.hyrax.parse.model.Level.Level


/**
  * Created by devian on 22.07.16.
  */
object Level extends Enumeration{
  type Level = Value
  val INFO= Value("INFO")
  val DEBUG= Value("DEBUG")
  val ERROR= Value("ERROR")
  val FATAL= Value("FATAL")
  val OFF = Value("OFF")
  val TRACE = Value("TRACE")
  val WARN = Value("WARN")
  val Null = Value("Null")

}

