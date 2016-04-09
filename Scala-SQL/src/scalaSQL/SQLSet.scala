package scalaSQL

class SQLSet[A <% SQLTuple](val op : Operation) {

    def filter(cond : Condition) = new SQLSet[A](Selection(cond, op))
    
    def getQuery = toQuery(op)
}

object SQLSet {
    def apply[A <% SQLTuple](table_name : String, attributes : List[Attribute[_]]) = {
        new SQLSet[A](Relation(table_name, attributes))
    }
}

abstract class SQLTuple() {
    val attributes : List[Attribute[_]]
}
