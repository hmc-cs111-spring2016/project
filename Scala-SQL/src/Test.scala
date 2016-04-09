
import AbstractSyntax.QueryAST._
import AbstractSyntax.QueryAST.SQLType

object Test extends App {

    val student = new Student()
    
    val students = SQLSet[Student]("students", student.attributes)
    
    val first_ten = students.filter(student.id > Right(10))
    
    println (first_ten.getQuery)
}

class Student() extends SQLTuple {
    val id = new Attribute[Int]("id")
    val name = new Attribute[String]("name")
    val age = new Attribute[Int]("age")

    val attributes = List(id, name, age)
}



