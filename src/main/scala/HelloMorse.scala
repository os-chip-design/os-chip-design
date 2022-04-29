import chisel3._

class HelloMorse extends Module {
  val io = IO(new Bundle() {
    val led = Output(UInt(1.W))
  })

  val code = Map('a' -> ".-",
    'b' -> "-...",
    'c' -> "-.-."
  )

  val message = "cab"

  def toBits(c: Char) = c match {
    case '.' => List(1, 0)
    case '-' => List(111, 0)
    case _ => List()
  }
  val l = message.map(c => toBits(c))
  for (x <- l) {
    for (y <- x) {
      println(y)
    }
  }

  io.led := 1.U
}

object HelloMorse extends App {
  emitVerilog(new HelloMorse)
}
