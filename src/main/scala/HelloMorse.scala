import chisel3._

class HelloMorse(freq: Int) extends Module {
  val io = IO(new Bundle() {
    val led = Output(UInt(1.W))
    val audio = Output(UInt(1.W))
    val gain = Output(UInt(1.W))
    val nshutdown = Output(UInt(1.W))
  })

  val DitMs = 110
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
    case ',' => List(0, 0, 0) // one more then spec, for easier hearing on faster pace
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

  // generating some sound
  // Need to make it asymetric, as it is too loud with the Pmod AMP2
  val Div = freq / 1000
  val Fac = 500
  val DivA = (Div / Fac).U
  val DivB = (Div * (Fac -1) / Fac).U
  val audioCntReg = RegInit(DivA)
  val toggleTick = audioCntReg === 0.U
  val toggleReg = RegInit(0.U)
  audioCntReg := audioCntReg - 1.U
  when (toggleTick) {
    when (toggleReg === 0.U) {
      audioCntReg := DivA
    } .otherwise {
      audioCntReg := DivB
    }
    toggleReg := ~toggleReg
  }

  io.gain := 0.U
  io.nshutdown := 1.U
  io.audio := toggleReg & regs(0)
  io.led := regs(0)
}

object HelloMorse extends App {
  // It is 10 MHz for the chip, but 100 MHz for the Basys 3 board
  val Basys3 = true
  emitVerilog(new HelloMorse(if (Basys3) 100000000 else 10000000))
}
