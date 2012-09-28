import scala.tools.nsc.interactive.tests._

object Test extends InteractiveTest {

  override def main(args: Array[String]): Unit = {
    val res0 = compiler.askForResponse( () => {
      println("[ outer] askForResponse")
      val res = compiler.askForResponse( () => { println("[nested] askForResponse") })
      println (res.get(5000) match {
        case Some(_) => "passed"
        case None    => "timeout"
      })
    })

    res0.get(5000)

    println("done")
    compiler.askShutdown()
  }
}