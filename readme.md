# Introduccion a Scala

## Algunos pros que le ven a Scala

- Lenguaje conciso
- Tipado pero no verboso
- Buenas y consistentes APIs
- Compila sobre la JVM
- Permite la programación funcional (y no sólo con lambdas, sino también con typeclases...)
- Promueve la inmutabilidad

## Algunos contras

- Falta de documentación (ecosistema en proceso de maduración)
- Compilador lento (aunque mejorando bastante)
- Barrera de entrada (como todo lenguaje nuevo)

### Repaso de pros y contras

- Scala tiene:
  - Inferencia de tipos, Un compilador para la JVM, Un compilador para la JVM.
- Una de las desventajas de Scala es
  - Poca documentación de algunas librerías
- Gestionar la asincronía con Scala es
  - Sencilla de gestionar
- ¿Scala promueve la inmutabilidad?
  - Sí

### Pasos para crear un proyecto en Scala a partir de una plantilla

- Necesario tener instalado el JDK de java y SBT
- El proyecto se crea a partir de una plantilla en github con el comando sbt new codelytv/scala-bootstrap-template.g8 lo toma de https://github.com/CodelyTV/scala-basic-skeleton.g8 y lo crea en la carpeta que se le indique.
- Para ejecutar el proyecto se usa el comando sbt run
- Para ejecutar los test se usa el comando sbt test
- Para entrar a la consola de sbt se usa el comando sbt console, que nos permite compilar en codigo de scala o java
- En intellij se puede acceder a la consola creando un worksheet, es importante tener el plugin de scala instalado en intellij

### ¿Qué es SBT?

Es un build tool para Scala, similar a Maven o Gradle. Se puede usar para compilar, ejecutar tests, generar documentación, etc.

### Repaso de la creacion de proyecto

- Para empezar a utilizar Scala necesitamos...
  - El JDK y el SBT
- El comando `console` en el SBT
  - Nos abre una repl de Scala

## Valores, variables y funciones

### Variables

Son elementos que se declaran en el sistema y pueden ser reasignados en cualquier momento. es decir que pueden mutar a lo largo del ciclo de vida de una instancia.

```scala
var variable = 1;
variable = 2;
```

### Valores

El concepto de valor difiere del de variable en que una vez asignado no puede ser reasignado. Por lo que la referencia en memoria no puede cambiar, siempre sera la misma.

Nota en cuanto a la referencia, ya que si la estructura de datos que almacena el valor es mutable, el valor si puede cambiar. Es decir si la inicializamos in valor con una coleccionde tipo ```mutable.Set``` al ser mutable podemos agregar o quitar elementos de la coleccion.

Los valores se evaluan cuando se definen. Es decir que una vez definidos no se pueden cambiar y tendra el mismo resultado siempre. Existe el concepto de ```lazy val``` para evitar inicializar un valor hasta que se haga el primer uso de este.

```scala
val felipe = "Felipe" *2; // Se evalua y no vuelve a cambiar el valor el cual es el siguiente "FelipeFelipe"
```

### Funciones

Las funciones son las misma que en los demas lenguajes de programacion, pero se tienen encuenta los siguientes aspectos:

- Como todo en Scala es una expresion, por lo tanto se declara con un igual (=)

```scala
def suma(a: Int, b: Int): Int = a + b;
```

- Se evalua en cada llamada, es decir que si se generan valores aleatorios en la funcion, cada vez que se llame a la funcion se generaran nuevos valores aleatorios a diferencia de val y lazy val.
- Por convenciones las funciones que sean publicas se especifica el tipo de retorno, las privadas no es necesario especificar el tipo de retorno dado a la existencia de inferencia de tipos.

```scala
def suma(a: Int, b: Int): Int = a + b; // Publica
def suma(a: Int, b: Int) = a + b; // Privada
```

- Por convencion las funciones que no reciban parametros se declaran sin parentesis

```scala
"Hola".toString
```

- Las funciones que produzcan side-effects deberan ser declaras e invocadas con parentesis a pesar que no reciban parametros

```scala
def printHola() = println("Hola")
printHola()
```

### Repaso de valores, variables y funciones

- Es necesario declarar el tipo de las variables y valores en Scala
  - Falso
- Cuándo es recomendable especificar el tipo de retorno de las funciones?
  - Sólo cuando son públicas para que la API sea más clara
- Por qué no se pone la cláusula return en Scala?
  - Porque no es necesario ya que en Scala siempre se retorna la última expresión de un bloque
- Qué diferencia hay entre `val codelyVal = Random.nextInt()` y `def codelyDef = Random.nextInt()`?
  - Que `codelyVal` se evalúa en el momento de la declaración y `codelyDef` en cada llamada
- Si importamos scala.util.Random para poder llamar a `Random.nextInt`, por qué no importamos nada para instanciar `String`?
  - Porque String está en el package scala y éste se importa por defecto

## Control de flujo (Ifs y Fors)

### Todo es una expresion

Scala siempre devuelve la ultima expresion de un bloque, por lo tanto se puede hacer lo siguiente:

```scala
val a = if (true) 1 else 2
```

El compilador infiere que el valor de a es un Int, ya que tanto el bloque del if como el else devuelven un Int, de lo contrario si el bloque del if devolviera un String y el else un Int, el compilador infiere que el valor de a es Any.

### Iteraciones for comprehensions

Las iteraciones en Scala se hacen con for comprehensions, las cuales son una forma de iterar sobre una coleccion de datos, pero a diferencia de otros lenguajes de programacion, en Scala se pueden iterar sobre cualquier tipo de dato que sea iterable, es decir que implemente el trait Iterable.

```scala
val a = for (i <- 1 to 10) yield i * 2 // El yield es opcional, pero si se omite el for comprehension devuelve un Unit, devuelve una collection de datos gracias al yield

val b = for {
  i <- 1 to 10
  j <- 1 to 10
} yield i * j


(1 to 10).foreach(println(_)) // La _ indica que como solo se recibe un parametro se puede omitir el nombre del parametro en la funcion y se puede usar el placeholder _ para indicar que se recibe un parametro

(1 to 10).foreach(println) // Si se recibe un solo parametro se puede omitir el parentesis

(1 to 10) foreach println // Si se recibe un solo parametro se puede omitir el parentesis y el punto

```

### Fors como syntactic sugar de flatMap, map

Future[Option[Sandwich]]

**Sandwich**
Se pretende elaborar un sandwich en base a una serie de ingredientes.

**Option[Sandwich]**
Representar la opcionalidad de hacer un sandwich  o no encapsulandolo en un Option. De esta manera se hace explicito que puede o no haber un sandwich, dependiendo de los ingredientes.

**Future[Option[Sandwich]]**
Como para elaborar el sandwich es necesario ir al repositorio de ingredientes (nevera) para obtenerlos, se encapsula la llamada a la nevera en un Future, ya que la llamada a la nevera puede tardar en responder. de esta manera no se bloquea el hilo principal de ejecucion.

### FlatMap

#### Map

Por cada ingrediente es necesario ir a la nevera a buscarlo, es necesario entrar dentro del futuro para obtener el ingrediente, para cuando este termine de ejecutarse, de esa manera podemos ir a buscar el siguiente ingrediente. El map permite pasar la funcion anonima  que se ejecutara cuando el futuro termine de ejecutarse.

#### Flatten

El map devuelve un FutureFuture[Future[Future[Future[Future[Sandwich]]]]], pero lo que se necesita es un Future[Option[Sandwich]], por lo tanto se usa el flatten para aplanar la estructura de datos.

#### FlatMap (map + flatten)

El flatMap es una combinacion del map y el flatten, ya que el map devuelve un Future[Option[Sandwich]] y el flatten devuelve un Future[Sandwich], por lo tanto el flatMap devuelve un Future[Sandwich].

```scala
def make(): Future[Sandwich] = {
    val breadOptionFuture = fridge.takeBread()
    val cheeseOptionFuture = fridge.takeCheese()
    val hamOptionFuture = fridge.takeHam()
    val eggOptionFuture = fridge.takeEgg()
    val baconOptionFuture = fridge.takeBacon()

    breadOptionFuture.flatMap { breadOption =>
      cheeseOptionFuture.flatMap { cheeseOption =>
        hamOptionFuture.flatMap { hamOption =>
          eggOptionFuture.flatMap { eggOption =>
            baconOptionFuture.map { baconOption =>
              Sandwich(Seq(breadOption, cheeseOption, hamOption, eggOption, baconOption))
            }
          }
        }
      }
    }
  }
```

### For como syntactic sugar de flatMap

El for comprehension es una forma de escribir el flatMap de una manera mas legible.

```scala
 def make(): Future[Sandwich] =
    for {
      breadOption  <- fridge.takeBread()
      cheeseOption <- fridge.takeCheese()
      hamOption    <- fridge.takeHam()
      eggOption    <- fridge.takeEgg()
      baconOption  <- fridge.takeBacon()
    } yield Sandwich(Seq(breadOption, cheeseOption, hamOption, eggOption, baconOption))

```

### Repaso de control de flujo

- ¿Qué nos devuelve la expresión `1 to 10`?
  - Una colección de números del 1 al 10
- ¿Cuándo usaremos el método flatten?
  - Cuando queramos combinar cualquier tipo de conjunto de elementos que sean combinables entre sí. Cabe destacar la puntualización de que los elementos deben ser combinables entre sí. Por ejemplo, un Option no se puede combinar con un Future, pero dos Future sí.
- ¿A qué equivale una for comprehension con 1 yield?
  - ¿A qué equivale una for comprehension con 1 yield?
  
## Funciones de orden superior, funciones anonimas y currying

### Funciones de orden superior y funciones anonimas

Las funciones de orden superior son funciones que reciben como parametro otra funcion, es decir que la funcion de orden superior recibe como parametro otra funcion y la ejecuta dentro de su cuerpo, es decir que las funciones son ciudadanos de primera clase.

```scala
def print(
  value: String,
  printer: String => Unit // Funcion de orden superior
): Unit = printer(value) // Se ejecuta la funcion de orden superior

def printlnPrinter(value: String): Unit = println(value) // Funcion que se ejecuta dentro de la funcion de orden superior
val printlnPrinterVal = (value: String) => println(value) // Funcion que se ejecuta dentro de la funcion de orden superior

print("Playing with higher order functions", printlnPrinter) // Se pasa la funcion como parametro
print("Same with functions stored in values", printlnPrinterVal) // Se pasa la funcion como parametro
print("Even with anonymous functions!", (value: String) => println(value)) // Se pasa la funcion anonima como parametro
```

### Currying

El currying es una tecnica que permite transformar una funcion que recibe varios parametros en una funcion que recibe un solo parametro, pero que devuelve otra funcion que recibe el siguiente parametro y asi sucesivamente hasta que se reciban todos los parametros.

En Scala se pueden definir funciones que reciban varias listas de parametros, es util para inferir los tipos de la segunda lista de argumentos en base a los tipos de la primera lista de argumentos y para tener funciones parcialmente aplicadas (currying).

```scala
def sum(a: Int)(b: Int): Int = a+ b // Funcion currificada

def add1(b:Int) = sum(1)(b) // Funcion parcialmente aplicada
def add2(b:Int) = sum(2)(b) // Funcion parcialmente aplicada
def add3(b:Int) = sum(3)(b) // Funcion parcialmente aplicada
val partialSum = sum(3) _ // función parcial

val add4 = sum(4)(_)

add2(5)
add4(6)

def printCurrified(printer: String => Unit)(value: String): Unit = printer(value)
def printlnPrinter(value: String): Unit = println(value)
def printStd(value: String) = printCurrified(printlnPrinter)

printStd("See you in the https://pro.codely.tv/ course!")


// Multiplicacion y funciones de orden superior

def multiply(a: Int)(b: Int)(c: Int): Int = a * b * c // Funcion currificada

def double(f: Int => Int)(x: Int): Int = f(2 * x) // Funcion de orden superior

val result = double(multiply(2)(3))(4) // result será 48 (2 * 2 * 3 * 4)

```

### Repaso de funciones de orden superior, funciones anonimas y currying

- ¿Qué es una función de orden superior?
  - Una función que recibe otra función como parámetro
- ¿Qué es una función anónima?
  - Una función que no tiene nombre
- ¿Qué es el currying?
  - Una técnica que permite transformar una función que recibe varios parámetros en una función que recibe un solo parámetro, pero que devuelve otra función que recibe el siguiente parámetro y así sucesivamente hasta que se reciban todos los parámetros
- ¿Qué es una función parcialmente aplicada?
  - Una función que no recibe todos los parámetros que necesita para ejecutarse
- ¿Qué es una función currificada?
  - Una función que recibe varios parámetros y devuelve otra función que recibe el siguiente parámetro y así sucesivamente hasta que se reciban todos los parámetros
- ¿Qué patrón de diseño ayudan a enfocar las funciones de orden superior?
  - Patrón Adapter
- ¿Para qué puede ser útil el currying?
  - Aplicar parcialmente una función, e inferencia de tipos

## Genericos

### Usando Clases genericas

Ejemplo de uso de genericos sin darse cuenta

```scala
val integerSequence = Seq(1, 2, 3)
// Ambas lineas son equivalentes
val integerSequence: Seq[Int] = Seq[Int](1, 2, 3)

// Caso de String
val stringSequence = Seq("1", "2", "3")
// Ambas lineas son equivalentes
val stringSequence: Seq[String] = Seq[String]("1", "2", "3")
```

Con una sola implementacion de Seq se puede cubrir todos los casos, ya que el tipo de dato se infiere en tiempo de compilacion o especificar.

### Desarrolo de metodos y clases genericas

Analizando la siguiente linea de codigo:

```scala
def apply[A](elems: A*) // A* indica que se recibira un numero variable de parametros de tipo A
```

Se puede inferir que el metodo apply recibe un numero variable de parametros de tipo A, pero que tipo es A? A es un tipo generico, es decir que puede ser cualquier tipo de dato, pero como se puede saber que tipo de dato es A? A se puede inferir en tiempo de compilacion o especificar.

- def apply[A]: Declaración de función con tipo genérico de "alias" A
- (elems: A*): Parámetro elems donde encontraremos una serie de elementos de tipo A. Nótese que después del tipo "A", nos encontramos el carácter "*" que indica que es un variadic argument. Es por este carácter por el cuál nos encontraremos una lista de elementos y no un único elemento.
  
Ejemplo de uso de genericos en clases

```scala
case class UserId(value: String)
case class User(id: UserId)

trait Repository[AggregateIdType, AggregateType] {
  def save(aggregate: AggregateType): Unit

  def search(id: AggregateIdType): AggregateType
}

class FakeUserRepository extends Repository[UserId, User] {
  def save(aggregate: User): Unit = ()

  def search(id: UserId): User = User(id)
}
```

### Repaso de genericos

- ¿Cuándo será útil hacer uso de los genéricos?
  - Cuando queramos compartir ciertos contratos o lógicas independientemente de la clase a la que aplique

## Colecciones

### Colleciones mutables vs inmutables

![Colecciones inmutables](/Assets/collections-inmutable-diagram.svg)

---

![Colecciones implementacion inmutable](/Assets/collections-immutable-diagram-implementaciones.svg)

---

![Colecciones mutables](/Assets/collections-mutable-diagram.svg)

- Color turquesta: Traits / interfaces genéricas
- Color azúl: Implementaciones concretas
- Línea fina: Herencia
- Línea gruesa: Herencia + implementación por defecto que usará Scala al crear una nueva instancia del padre.

### Como escoger una coleccion

Se recomienda elegir la clase mas generica posible que cumpla con los requisitos funcionales que se tienen.

- **Set**: Conjunto de colecciones donde no se permite guardar 2 veces el mismo elemento y no se respeta el orden.
- **Seq**:  Colecciones secuenciales que, por tanto, respetan el order de los elementos y permiten almacenar múltiples representaciones de un mismo valor.
- **Map**: Colecciones de tipo mapa que asocian una determinada clave a un determinado tipo. Cabe destacar que en la API de colecciones de Scala ambos tipos, el de las claves y el de los valores, deben ser consistentes a lo largo de toda la colección.

[rendimiento de las collecciones en Scala](https://docs.scala-lang.org/overviews/collections/performance-characteristics.html)

### Añadir elementos

```scala
val codelis = Seq("Javi", "pepe") :+ "Rafa"
```

La operacion se compone de ":" y "+" donde ":" indica que se añadira el elemento a la derecha de la coleccion y "+" indica que se añadira el elemento a la izquierda de la coleccion. (Lado izquierdo o derecho de la operacion)

### Inmutable por defecto

Las colecciones en Scala son inmutables por defecto, es decir que no se pueden modificar, pero se pueden crear nuevas colecciones a partir de las existentes. Sin embargo para crear colecciones mutables se debe importar el paquete scala.collection.mutable

```scala
import scala.collection.mutable._

val mutableSet = mutable.Set(1, 2, 3)
```

### Conclusiones Colleciones

- 3 principales grupos de colecciones a usar según necesidades: Set, Seq, y Map
- Colecciones inmutables por defecto
- Al añadir elementos, los ":" determinan el lado donde se añade

### Repaso de colecciones

- ¿Qué colección usaré si quiero guardar palabras para realizar operaciones con ellas sin importar el orden? (Ejemplos de palabras: ("Rafa", "pilota", "Rafa", "maneja")Ejemplos de operaciones a realizar a posteriori: Contar total de palabras, contar ocurrencias de una misma palabra, contar las que empiecen por vocal, contar las de menos de 5 letras, etc.)
  - Seq, A pesar de no importar el orden, como uno de los objetivos es contar las ocurrencias de las palabras, necesito permitir que el elemento "Rafa" (por ejemplo) permanezca 2 veces en la colección. Esto el Set lo impediría ya que sólo almacenaría 1 "Rafa". Con lo cuál, la colección que menos complejidad tiene para representar el conjunto de palabras permitiendo esa duplicidad sería Seq.
- ¿Qué colección usaré para modelar el resultado de la operación `UserRepository#search(name: String): ???[User]`?
  - Set (Premisas derivadas del enunciado a tener en cuenta: Desde el momento en el que hacemos una búsqueda por nombre, vamos a tener que retornar una colección de usuarios ya que pueden existir múltiples usuarios con el mismo nombre. `User` contiene el identificador único del usuario. Con lo cuál, a pesar de que 2 usuarios compartan el nombre, van a ser instancias de `User` con valores diferentes ya que su `id` será diferente. Dadas estas premisas, Set ya nos permitiría retornar el conjunto de usuarios que buscamos ya que nos devuelve todas las ocurrencias.)

## Classes y Case Classes, companion objects y ScalaTest

### Clases Estandar

- Se pueden definir los atributos de clase necesarios para crear instancias en la propia "cabecera" de la clase: `class StandardClass(val atributo: String)`
- Al definir los atributos como `val`, Scala creará un método con el mismo nombre que el atributo para poder obtener su valor de forma pública
- Si no especificamos la keyword `val` al definir los atributos del constructor, serán atributos de tipo `val` privados
- Se pueden establecer valores por defecto. Especialmente útil si lo combinamos con la invocación del método usando named arguments para ObjectMother o Stubs (especificando el nombre de los atributos) `class StandardClass(val atributo: String = "valor por defecto")`
- Se pueden definir atributos en el cuerpo de la clase sin necesidad de que sean indicados en el constructor `class StandardClass(val atributo: String) { val atributo2 = "valor por defecto" }`

Ejemplo de clase estandar

```scala
final class StandardClass(
    val attributeInConstruct: String,
    private val privateAttributeInConstruct: String = "Some default value"
) {
  val attributeInBody                = "public body attribute value"
  private val privateAttributeInBody = "private body attribute value"
}
```

### ScalaTest

Es un framework de pruebas unitarias para Scala, es similar a JUnit, pero con una sintaxis mas legible y con mas funcionalidades.

```scala
package tv.codely.scala_intro_examples.lesson_09_oop

import org.scalatest.{Matchers, WordSpec} // Se importa la sintaxis de ScalaTest

final class StandardClassVisibilitiesSpec extends WordSpec with Matchers { // Se extiende de WordSpec y Matchers para poder usar la sintaxis de ScalaTest
  private val randomText    = "some text"
  private val standardClass = new StandardClass(attributeInConstruct = randomText)

  "Standard Class" should {
    "set as public the attributes defined in the constructor by default" in {
      standardClass.attributeInConstruct shouldBe randomText
    }
    "not compile if we try to access private attributes defined in the constructor" in {
      "standardClass.privateAttributeInConstruct" shouldNot compile
    }
    "set as public the attributes defined in the class body by default" in {
      val bodyAttributeValue = "public body attribute value"

      standardClass.attributeInBody shouldBe bodyAttributeValue
    }
    "not compile if we try to access private attributes defined in the body" in {
      "standardClass.privateAttributeInBody" shouldNot compile
    }
  }
}
```

### Case Classes

- Los atributos de constructor son valores inmutables y publicos por defecto sin necesidad de especifical la keyword `val`
- El método `copy` para generar nuevas instancias en base a una ya existente. Este método permite especificar los nuevos valores para aquellos atributos que queramos cambiar
- El método `apply` nos provee de un constructor por defecto. No deberemos especificar la keyword `new` para crear nuevas instancias.
- El método `unapply` nos será especialmente útil para deconstruir instancias de nuestra case class. Esta técnica es la que se usa por debajo para poder aplicar pattern matching como vemos en el vídeo.

Ejemplo de case class

```scala
package tv.codely.scala_intro_examples.lesson_09_oop

final case class CaseClass( // Se declara como case class
    attributeInConstruct: String, // 
    private val privateAttributeInConstruct: String = "Some default value"
) {
  val attributeInBody                = "public body attribute value"
  private val privateAttributeInBody = "private body attribute value"
}
```

Ejemplo de test visibilidades:

```scala
package tv.codely.scala_intro_examples.lesson_09_oop

import org.scalatest.{Matchers, WordSpec}

final class CaseClassVisibilitiesSpec extends WordSpec with Matchers {
  private val randomText = "some text"
  private val caseClass  = CaseClass(attributeInConstruct = randomText)

  "Case Class" should {
    "generate a public getter for the attributes defined in the constructor" in {
      caseClass.attributeInConstruct shouldBe randomText
    }
    "not compile if we try to access private attributes defined in the constructor" in {
      "caseClass.privateAttributeInConstruct" shouldNot compile
    }
    "set as public the attributes defined in the class body by default" in {
      val bodyAttributeValue = "public body attribute value"

      caseClass.attributeInBody shouldBe bodyAttributeValue
    }
    "not compile if we try to access private attributes defined in the body" in {
      "caseClass.privateAttributeInBody" shouldNot compile
    }
  }
}
```

Ejemplo test funcionalidades:

```scala
package tv.codely.scala_intro_examples.lesson_09_oop

import org.scalatest.{Matchers, WordSpec}

/**
  * In order to check all the capabilities that a case class have, just:
  * * Compile it with: `scalac`
  * * Inspect it with: `javap -private`
  *
  * You also have the option of running `scalac CaseClass.scala -print` in order to see the compiled version.
  */
final class CaseClassCapabilitiesSpec extends WordSpec with Matchers {
  private val randomText = "some text"
  private val caseClass  = CaseClass(attributeInConstruct = randomText) // Se crea una instancia de la case class a partir del companion object

  "Case Class" should {
    "provide an apply method in the companion object in order to construct new instances" in {
      "CaseClass(attributeInConstruct = randomText)" should compile
    }
    "provide a copy method making it easier for us to deal with immutability" in {
      val differentInstance = caseClass.copy(attributeInConstruct = "some different text") // Se crea una nueva instancia a partir de la instancia existente, cambiando el valor de un atributo

      differentInstance.attributeInBody shouldBe differentInstance.attributeInBody
    }
    "provide an unapply method making it easier deconstructing in pattern matching" in {
      val differentInstance = CaseClass.unapply(caseClass) // Se deconstruye la instancia en una tupla

      differentInstance shouldBe a[Option[(_, _)]]
    }
    "provide an implemented toString method displaying all the attribtue values" in {
      caseClass.toString shouldBe "CaseClass(some text,Some default value)"
    }
  }
}
```

### Companion Objects

- Se definen con la keyword `object` y bajo el mismo nombre de la clase o case class a la que acompañan
- Deben estar en el mismo fichero que la clase acompañada
- Todo lo que contengan está definido a nivel de clase y no de instancia. Todo aquello que especificaríamos en nuestras clases como `static` es un potencial candidato a estar en el companion object
- Especialmente útil para named constructors. 

Ejemplo de companion object:

```scala
package tv.codely.scala_intro_examples.lesson_09_oop

import scala.util.Random

object NumberWithCompanionObject {
  def apply(value: String): NumberWithCompanionObject = NumberWithCompanionObject(value = value.toInt)

  def random: NumberWithCompanionObject = NumberWithCompanionObject(value = Random.nextInt())
}

final case class NumberWithCompanionObject(value: Int) {
  val plusOne: NumberWithCompanionObject = copy(value = value + 1)
}
```

### Repaso de clases y case classes

- ¿Qué tipo de elemento usaremos para modelar entidades de nuestro dominio?
  - Case Classes, Las case classes nos proveen de métodos como los vistos en el vídeo. Especialmente útil el unapply para permitir pattern matching, etc.
- ¿Dónde ubicaremos un constructor semántico (named constructor)?
  - Companion Object

## Enums vs Sealed Traits

### Enums

Los enums son un tipo de dato que permite definir un conjunto de valores posibles, es decir que solo se puede tener uno de los valores definidos en el enum.

Ejemplo de implementacion simple(sin propiedades)

```scala
object Color extends Enumeration {
  val Red, Green, Blue = Value
}

object VideoType extends Enumeration {
  val Screencast, Interview = Value
  def canBeLong(videoType: VideoType.Value): Boolean = videoType != Screencast
}

```

Ejemplo de implementacion con propiedades y type alias

```scala
object WeekDay extends Enumeration {
  type WeekDay = Value // Se define un type alias para poder usar WeekDay en vez de WeekDay.Value

  protected case class Val(order: Int, isWorkingDay: Boolean) extends super.Val

  val Monday = Val(order = 1, isWorkingDay = true)
  val Tuesday = Val(order = 2, isWorkingDay = true)
  val Wednesday = Val(order = 3, isWorkingDay = true)
  val Thursday = Val(order = 4, isWorkingDay = true)
  val Friday = Val(order = 5, isWorkingDay = true)
  val Saturday = Val(order = 6, isWorkingDay = false)
  val Sunday = Val(order = 7, isWorkingDay = false)
}
```

#### Beneficios de enums

- Poder obtener todos sus posibles valores con WeekDay.values

#### Desventajas de enums

- Perdemos el tipo concreto y todos los valores de todos los Enums tendrán el mismo tipo tras el paso de type erasure del compilador. Es decir, que si tenemos un método que recibe un parámetro de tipo WeekDay, no podremos saber si el valor que nos llega es Monday, Tuesday, etc. ya que todos serán de tipo WeekDay.
- No podemos determinar si un pattern matching es exhaustivo en tiempo de compilación. Si usamos librerías como enumeratum o itemized sí podremos ya que por debajo acaban haciendo que sean estructuras selladas en resumen, no obstante, queda a vuestra elección si queremos depender de este tipo de librerías para modelar nuestro dominio

### Estructuras selladas (Sealed Traits/abstract classes)

Las estructuras selladas son un tipo de dato que permite definir un conjunto de valores posibles, es decir que solo se puede tener uno de los valores definidos en la estructura sellada. A diferencia de los enums, las estructuras selladas permiten definir propiedades y metodos.

Ejemplo de implementacion simple(sin propiedades)

```scala
package tv.codely.scala_intro_examples.lesson_10_enums_vs_sealed_structures

sealed trait RafaState

case object RafaWithTooMuchHair extends RafaState
case object RafaWithNotTooMuchHair extends RafaState
case object RafaSad extends RafaState
case object RafaHappy extends RafaState
```

Ejemplo de implementacion con propiedades y metodos

```scala
package tv.codely.scala_intro_examples.lesson_10_enums_vs_sealed_structures

import java.util.UUID

object DomainError {
  def unapply(arg: DomainError): Option[(Int, String, Map[String, Any])] =
    Some((arg.priority, arg.mnemonic, arg.context))
}

sealed abstract class DomainError(
  val priority: Int,
  val mnemonic: String,
  val context: Map[String, Any] = Map.empty
) extends Ordered[DomainError] {
  override def compare(that: DomainError): Int = this.priority - that.priority
}

case object TooMuchCpuLoad extends DomainError(0, "too_much_cpu_load")

case object NotEnoughDiskSpace extends DomainError(1, "not_enough_disk_space")

final case class UserWithoutPermissions(userId: UUID, action: String) extends DomainError(
  100,
  "user_without_permissions",
  Map("user_id" -> userId.toString, "action" -> action)
)
```

#### Beneficios de las estructuras selladas

- El compilador puede determinar en un pattern matching si éste es exhaustivo (contempla todos los posibles casos de la estructura) o n.

#### Desventajas de las estructuras selladas

- No tenemos una forma fácil de obtener todos los posibles valores.

### Conceptos de enums y sealed 

- El REPL de Scala podemos pegar un bloque de codigo con el comando :paste
- Exhaustividad en pattern matching implica lanzar Warning en tiempo de compilació, la exhaustividad en pattern matching, se refiere a la capacidad del compilador de Scala para verificar si todos los posibles casos han sido cubiertos en una declaración de pattern matching. Si no se cubren todos los casos, el compilador emitirá una advertencia de que el pattern matching no es exhaustivo
- Utilizaremos case objects (similar a un case class, pero sin parámetros. Es una forma conveniente de crear un objeto único y sin estado que se puede utilizar para representar constantes o valores enumerados.) cuando queramos modelar un concepto de dominio que sólo tiene una posible representación
- Método sorted para ordenar en base a un implícito de tipo Ordering que proveeremos automáticamente desde el momento en el que nuestra estructura DomainError extiende de Ordered proveyendo así del método compare para el criterio de ordenación.

```scala
sealed abstract class DomainError(
  val priority: Int,
  val mnemonic: String,
  val context: Map[String, Any] = Map.empty
) extends Ordered[DomainError] { // Se extiende de Ordered para poder usar el metodo compare
  override def compare(that: DomainError): Int = this.priority - that.priority // Se implementa el metodo compare para poder usar el metodo sorted
}
```

- Syntax sugar para hacer patter matching más concisos como función de primer orden a pasar a un map.
- Deconstrucción para el pattern matching vía método unapply implementado en DomainError

```scala
object DomainError {
  def unapply(arg: DomainError): Option[(Int, String, Map[String, Any])] =
    Some((arg.priority, arg.mnemonic, arg.context)) // Se implementa el metodo unapply para poder hacer pattern matching
}
```

- Pattern matching incluyendo condicionales en base a las propiedades deconstruidas.

```scala
someErrors.map {
  case DomainError(priority, _, _) if priority < 100 =>
    "Non critical"
  case other: DomainError                            =>
    s"/!\\ Critical /!\\: ${other.priority}"
}

```

### Repaso de enums y sealed

- Si quiero que mi modelado contenga atributos como por ejemplo EmployeeType.salaryRange, ¿qué debo usar?
  - Ambas lo soportan
- Si quiero obtener todos los posibles valores sin necesidad de aplicar métodos de reflexión, ¿Qué debo usar?
  - Enums
- Si quiero tener la garantía en tiempo de compilación de que he cubierto todos los casos, ¿qué usaré?
  - Sealed structures

## Asincronia en Scala con Futuros

### Futuros

Los futuros son un tipo de dato que permite ejecutar una tarea de forma asincrona, es decir que se ejecuta en un hilo diferente al hilo principal de ejecucion, de esta manera se evita bloquear el hilo principal de ejecucion.

```scala
object Benchmarker {
  def print(something: String): Unit = {
    val threadName = Thread.currentThread().getName
    val now = DateTime.now
    val minute = s"${now.minuteOfHour().get()}:${now.secondOfMinute().get()}"
    println(s"[$threadName] [$minute] $something")
  }

  def benchmark[T](taskName: String, task: => T): T = { // Se recibe una funcion como parametro, indicada por un generico
    print(s"🏁 Starting to $taskName")
    val result = task // aqui se ejecuta la funcion que se le pasa como parametro
    print(s"🔚 Finishing to $taskName")
    result
  }

  def simulate(taskName: String, during: Duration): Unit = benchmark(taskName, Thread.sleep(during.toMillis)) // Se le enviara la funcion de Sleep como parametro a la funcion bechanmark, pero la ejecucion no se hasta que en la funcion superior la ejecute
}

Usando la funcion simulate con futuros

```scala
examples/blob/master/src/main/tv/codely/scala_intro_examples/lesson_11_futures/1_BasicFuturesExamples.sc
def cutRafaHair(): Unit = simulate("💂‍️ Cut Rafa's hair", 30 seconds) 
def cutJaviHair(): Unit = simulate("🧔 Cut Javi's hair", 5 seconds)

def cutRafaHairAsync(): Future[Unit] = Future(cutRafaHair())
def cutJaviHairAsync(): Future[Unit] = Future(cutJaviHair())

// Blocks the current thread
cutRafaHair() // Se ejecuta la funcion en el hilo principal, termina en 30 segundos
cutJaviHair() // Se ejecuta la funcion en el hilo principal, termina en 5 segundos

print("👋 After sync calls 🕋") // Se imprime cuando justo termina la ejecucion de las funciones anteriores

// Executes each asynchronous operation in a different thread
val rafaFuture = cutRafaHairAsync() // Se ejecuta la funcion en un hilo diferente al principal
val javiFuture = cutJaviHairAsync() // Se ejecuta la funcion en un hilo diferente al principal

print("👋 After async calls 🔮") // Se imprime al instante en el hilo principal

// Waits until the future completes
Await.result(rafaFuture, 5 minutes) // Manera de esperar una tarea asincrona, se le pasa el futuro y el tiempo maximo de espera
Await.result(javiFuture, 5 minutes)

print("👋 After async calls ✋")
```

Se puede usar el programa de Java VisualVM para ver los hilos de ejecucion del programa.

### Named Constructors

Son metodos utiles a la hora de instanciar determinados tipos de futuros, ya que permiten crear futuros con un comportamiento especifico.

```scala
final case class User(id: UUID, name: String)

val randomUser = User(UUID.randomUUID(), "lerele")

// Sólo estamos instanciando un Future[User] para que coincida con una firma.
// No estamos ejecutando nada de forma asíncrona. En realidad, estamos evitando el cambio de contexto :)
// Útil si estamos acoplados al tipo Future en las interfaces de nuestros repositorios, pero tenemos una implementación en memoria.
Future.successful(randomUser) // Future[User]
Future.failed[User](new RuntimeException("Not found in cache")) // Future[User]
//Future(throw new RuntimeException("222Not found in cache")) // Future[User]

Future.unit // Future[Unit]

```

### Componiendo futuros

- **map**: Pasarle una función de primer orden al futuro para que la ejecute una vez termine satisfactoriamente. Como input de esta función redibiremos el resultado del futuro.
- **flatMap**: Igual que el caso anterior pero, además, ejecutará una llamada a flatten una vez acabae el map (util para reducir un nivel de indentación si nuestra función que le pasamos en el map también devuelve un Futuro).
- **sequence**: Pasar de una lista de futuros a un único futuro que contendrá la lista de elementos
- **traverse**: Recorrer la lista de futuros aplicando una determinada operación por cada uno de ellos.
- **reduceLeft**: Reducir una lista de futuros a un único futuro que contendrá el resultado de aplicar una determinada operación a cada uno de ellos, Agregar los resultados de una lista de futuros

```scala
// Ejecución secuencial de futuros
val fridge = new InMemoryFridge

// map & flatMap
val sandwichFutureMap = fridge.takeBread().flatMap { breadOption =>
  fridge.takeCheese().map { cheeseOption =>
    Sandwich(Seq(breadOption, cheeseOption))
  }
}

// for syntactic sugar to the nesting hell rescue!
val sandwichFutureFor = for {
  breadOption <- fridge.takeBread()
  cheeseOption <- fridge.takeCheese()
} yield Sandwich(Seq(breadOption, cheeseOption))

// Después de ejecutarla, ¡intenta sustituir la nevera utilizada por otra que retrase la acción takeBread!
//val fridge = new InDelayedMemoryFridge
val intFutureList = List.fill(100)(Future.successful(1))

// Sequence
val intListFuture = Future.sequence(intFutureList)

// Traverse
val doubledIntListFuture = Future.traverse(intFutureList)(intFuture => intFuture.map(_ + 1))

// FoldLeft
val sumFuture = Future.reduceLeft(intFutureList)(_ + _)
```

### Gestion de errores

- **fallbackTo**: En caso de que el futuro falle, ejecutar otro futuro, en este caso le podemos dar una instancia de Futuro alternativa a ejecutar en caso de que el primero de ellos falle.

```scala
def readFromCache = Future.failed(new RuntimeException("Not found in cache"))
def readFromSourceOfTruth = Future.successful("🐒 value from source of truth")

val videoTitle = readFromCache fallbackTo readFromSourceOfTruth

```

- **recover**: En caso de que el futuro falle, ejecutar una función de primer orden que nos permita recuperarnos del error y devolver un valor alternativo, nos permite pasarle una función parcial con los casos de los que queremos que se recupere indicándole el valor a retornar en ese caso.En el ejemplo del vídeo, si se produce una excepción dentro del futuro de ir a buscar el queso, estaríamos devolviendo None. Cabe destacar la alternativa de recoverWith para hacer exactamente lo mismo pero con una operación que devuelva un futuro. A diferencia del fallbackTo, lo que ganamos en este caso es que el futuro no se ejecuta si no se produce el error, y además sólo se ejecutará en el caso concreto del error que capturamos (case del pattern matching).

```scala
val sandwichFuture = for {
  cheeseOption <- fridge.takeCheese() recover { case NonFatal(e) => print(e.getMessage); None }
  breadOption <- fridge.takeBread() recover { case NonFatal(e) => print(e.getMessage); None }
} yield Sandwich(Seq(breadOption, cheeseOption))
```

- **failed**: Nos permite acceder al futuro fallido sólo si esto sucede. Con ello, podemos aplicar un foreach para obtener la excepción y producir side-effects (por ejemplo) vía el método foreach.

```scala
sandwichFuture.failed.foreach {
  case NonFatal(e) => print(s"🚨 Exception <${e.getMessage}> while making a Sandwich.")
}
```

#### Clase try

La clase Try es un tipo de dato que permite ejecutar una tarea de forma sincrona, es decir que se ejecuta en el hilo principal de ejecucion, de esta manera se bloquea el hilo principal de ejecucion.

```scala
def readFromCacheTry: Try[String] = Try(throw new RuntimeException("Not found in cache"))
def readFromSourceOfTruthTry: Try[String] = Try("lerele")

val videoTitleTry = readFromCacheTry.failed.flatMap(_ => readFromSourceOfTruthTry)

```

En caso de que la función a ejecutar pueda resolverse de forma satisfactoria (sin lanzar ninguna excepción), nos retornará finalmente una subinstancia de tipo Success. Es el caso de la segunda llamada con `Try("lerele")`

En caso de que la función que le pasemos lance alguna excepción, ésta será capturada internamente para no romper el flujo de ejecución exterior de forma abrupta, y lo que nos retornará será una subinstancia de tipo Failure. Este sería el caso de la primera invocación desde la función `readFromCacheTry`.

La construcción Try por tanto es útil para reflejar en nuestros contratos de forma explícita el hecho de que una operación puede fallar y lo debemos tener en cuenta. Destacar que las funciones del ejemplo devuelven algo de tipo `Try[String]`, a diferencia de lo que devolverían si aplicásemos el control de errores con excepciones puras (`String`).

El paralelismo entre el Try y el Future por tanto es total. La principal diferencia es que las computaciones a ejecutar dentro de Future se harán en otro thread de forma asíncrona, mientras que el Try sería una versión síncrona de Future.

#### Conclusiones sobre gestion de errores

Informacion sobre el manejo de errores en Scala [aqui](https://github.com/47degrees/functional-error-handling/blob/master/deck/README.md)

- **Option**: Nos será útil para representar la posibilidad de no retornar un determinado valor. De esta forma evitamos NullPointerExceptions al devolver null, y también hacemos explícita la posibilidad de devolver None.
- **Try**: Hacer explícito el hecho de que una determinada función puede lanzar una excepción en tiempo de ejecución
- **Either**: Cuando podemos determinar la jerarquía de tipos de error a retornar, usaremos Either y así nos podremos ahorrar el coste de instanciación y gestión de las excepciones. En el lado izquierdo tendremos la instancia del error en caso de que vaya mal, y en el derecho la instancia del resultado esperado en caso satisfactorio. Ejemplo: `Either[DomainError, User]`

### Bloking

El metodo Bloking lo que nos permite es definir una serie de sentencias que potencialmente bloquearán el thread donde se ejecute. El principal caso de uso, tiene que ver con latencias de red o de base de datos.

```scala
blocking {
  simulate("Some blocking operation", 1 second)
}
```

### Repaso de futuros

- ¿Para qué son útiles los futuros?
  - No bloquear el thread principal mientras se ejecuta la operación correspondiente, permitiendo así aprovechar el hardware disponible
- Con lo que hemos visto en el curso, ¿cuál sería la firma óptima del método para obtener usuarios de la base de datos?
  - UserRepository#find(id: UserId): Future[Option[User]]
    - Con Future[Option[User]] estamos:
      - Permitiendo que la operación se ejecute de forma asíncrona (todo englobado en un Future)
      - Permitiendo de que a pesar de que no se produzcan excepciones al ir a buscar el usuario en base de datos, si éste no está se devuelva un Futuro satisfactoriamente resuelto con None por resultado
      - No añadiendo niveles innecesarios como el que aportaría el Try ya que ya representamos la posibilidad de fallar conteniendo una excepción con el propio Future
- ¿Qué tipo de código englobaremos entre blocking()?
  - Cualquier porción de código que bloquee el thread donde se ejecuta. La diferencia entre englobar entre Future y englobar entre blocking es que el primero aporta asincronía mientras que el segundo simplemente le dice al thread pool que si se bloquea por esto, tiene permiso para actuar en consecuencia (abriendo más threads por ejemplo). Por esto ambas construcciones (Future y blocking) tienen objetivos y usos distintos a pesar de que se puedan confundir entre sí.
