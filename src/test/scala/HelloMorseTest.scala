import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HelloMorseTest extends AnyFlatSpec with ChiselScalatestTester {
  "Morse" should "pass" in {
    test(new HelloMorse(1000)).withAnnotations(Seq(WriteVcdAnnotation)) { c =>
      println("We are generating a VCD file")
      c.clock.setTimeout(0)
      c.clock.step((10000))
    }
  }
}
