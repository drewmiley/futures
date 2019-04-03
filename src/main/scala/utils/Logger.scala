package utils

object Logger {

  def log(msg: Any): Unit = println(msg)
  def log(msg: Any, e: Throwable): Unit = println(msg + "\n" + e.getStackTrace)
  def error(msg: Any): Unit = log(msg)
  def error(msg: Any, e: Throwable): Unit = log(msg)
}
