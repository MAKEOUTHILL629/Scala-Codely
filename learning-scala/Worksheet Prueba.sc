
if("CodelyModal".isInstanceOf[String]){
  println("Es un String")
}

var lele = if ("CodelyModal".isInstanceOf[String]) 3 else 7

val felipe = "Felipe" *2;

var total =0;

for (i <- 0 to 10){
  total += i
}
def codely(name: String) : String ={
   name * 4
}

(1 to 10).foreach(println(_))

def minCodely(name: String) = name * 4

print(codely("Felipe"))

def numberRandom(): Int = {
  val r = scala.util.Random
  r.nextInt(10)
}

def functionInFunction(cadena: String): String = {
  def functionInFunction2(cadena2: String): String = {
    cadena + cadena2
  }
  functionInFunction2("Cadena2")
}

def printHola() = println("Hola")
printHola()

/*
Declara un val que contenga un bloque tal que `Thread.sleep(5000); "Hi"` y
cámbialo a lazy val. juega un poco para ver cómo cambia
todo. Cámbialo de nuevo a def y mira cómo se comporta ahora

val a = {
  Thread.sleep(5000)
  "Hi"
 */

def a = {
  Thread.sleep(5000)
  "Hi"       }

a

//Declara un val que contenga un immutable.Set como el de la lección anterior e introduce nuevos elementos en el Set

trait JsonWriter[A] {
  def write(value: A): Json
}

implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
  new JsonWriter[Option[A]] {
    def write(option: Option[A]): Json =
      option match {
        case Some(aValue) => writer.write(aValue)
        case None         => JsNull
      }
  }
