import chisel3._

class HelloMorse(freq: Int) extends Module {
  val io = IO(new Bundle() {
    val led = Output(UInt(1.W))
  })

  val DitMs = 150
  val Cnt = (freq / (1000 / DitMs) - 1).U

  val code = Map(
    'a' -> ".-",
    'b' -> "-...",
    'c' -> "-.-.",
    'd' -> "-..",
    'e' -> ".",
    'f' -> "..-.",
    'g' -> "--.",
    'h' -> "....",
    'i' -> "..",
    'j' -> ".---",
    'k' -> "-.-",
    'l' -> ".-..",
    'm' -> "--",
    'n' -> "-.",
    'o' -> "---",
    'p' -> ".--.",
    'q' -> "--.-",
    'r' -> ".-.",
    's' -> "...",
    't' -> "-",
    'u' -> "..-",
    'v' -> "...-",
    'w' -> ".--",
    'x' -> "-..-",
    'y' -> "-.--",
    'z' -> "--..",
    '0' -> "-----",
    '1' -> ".----",
    '2' -> "..---",
    '3' -> "...--",
    '4' -> "....-",
    '5' -> ".....",
    '6' -> "-....",
    '7' -> "--...",
    '8' -> "---..",
    '9' -> "----.",
    ' ' -> " "
  )

  val message = "hello world  "

  def toBits(c: Char) = c match {
    case '.' => List(1, 0)
    case '-' => List(1, 1, 1, 0)
    case ',' => List(0, 0)
    case ' ' => List(0, 0, 0, 0)
    case _ => println(c); List(123)
  }

  val m = message.map(c => code(c))
  println(m)
  val s = m.reduce(_ + "," + _)
  println(s)
  val l = s.map(toBits(_))
  println(l)
  val sl = l.reduce(_ ++ _).map(_.U(1.W))
  println(sl)
  val regs = RegInit(VecInit(sl))

  val cntReg = RegInit(Cnt)
  val tick = cntReg === 0.U
  cntReg := Mux(tick, Cnt, cntReg - 1.U)

  for (i <- 0 until sl.length - 1) {
    when (tick) {
      regs(i) := regs(i + 1)
    }
  }
  when (tick) {
    regs(sl.length-1) := regs(0)
  }


  io.led := regs(0)
}

object HelloMorse extends App {
  // It is 10 MHz for the chip (100 MHz the Basys3)
  emitVerilog(new HelloMorse(10000000))
}
