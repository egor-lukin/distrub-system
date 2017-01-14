import org.apache.ignite._
import org.apache.ignite.configuration._
import ignite.scala._

//main object
object TryIgnite {
  def multiply(arr: Array[Int]): Int = {
    return arr.reduce((s1, s2) => s1 * s2)
  }

  def toArray(str: String): Array[Array[Int]] = {
    return str.split("\n").map((s) => s.split(" ").map((item) => item.toInt))
  }

  def transp(arr: Array[Array[Int]]): Array[Array[Int]] = {
    return (0 to arr.length - 1).map((i) => arr.map((item) => item(i)).toArray).toArray
  }

  def main(args: Array[String]): Unit = {
    val cfg = new IgniteConfiguration
    val ignite = Ignition.start(cfg)
    val compute = ignite.compute(ignite.cluster)
    implicit val cr = ComputeRunner(compute)

    val lines = scala.io.Source.fromFile("matrix").mkString
    val arr = transp(toArray(lines))

    val chain = IgnitePipe.from(arr)
      .map((arr) => multiply(arr))
      .reduce

    println("----------------------------\n")

    println("Source matrix:\n" + lines)

    println("\nSum: " + chain.execute)
  }
}


