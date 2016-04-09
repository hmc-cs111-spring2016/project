package AbstractSyntax

package object QueryAST {
    

    abstract class Operation
    case class Relation(val name : String, val attributes : List[Attribute[_]]) extends Operation

    class Attribute[T <% SQLType](val name : String) {
        def == (that : Either[Attribute[T], T]) = ETComparison(Left(this), that)
        def != (that : Either[Attribute[T], T]) = NotCondition (this == that)
        def > (that : Either[Attribute[T], T]) = GTComparison(Left(this), that)
        def < (that : Either[Attribute[T], T]) = LTComparison(Left(this), that)
        def >= (that : Either[Attribute[T], T]) = OrCondition(this > that, this == that)
        def <= (that : Either[Attribute[T], T]) = OrCondition(this < that, this == that)
    }

    class SQLType(val value : Any)
    
    object SQLType {
        implicit def fromInt(i : Int) = new SQLType(i)
        implicit def fromString(s : String) = new SQLType(s)
    }


    abstract class Condition
    case class GTComparison[T](left :Either[Attribute[T], T], right : Either[Attribute[T], T]) extends Condition
    case class LTComparison[T](left :Either[Attribute[T], T], right : Either[Attribute[T], T]) extends Condition
    case class ETComparison[T](left :Either[Attribute[T], T], right : Either[Attribute[T], T]) extends Condition
    case class NotCondition(cond : Condition) extends Condition
    case class AndCondition(left : Condition, right : Condition) extends Condition
    case class OrCondition(left : Condition, right : Condition) extends Condition

    
    
    abstract class UnaryOperation extends Operation
    case class Projection(val attributes : List[Attribute[_]], val op : Operation) extends UnaryOperation
    case class Selection(val cond : Condition, val op : Operation) extends UnaryOperation
//    case class Rename(val start : Attribute[Any], val end : Attribute[Any], val op : Operation) extends UnaryOperation
    

//    abstract class BinaryOperation extends Operation;
//    case class Product() extends BinaryOperation;
//    case class Union() extends BinaryOperation;
//    case class Difference() extends BinaryOperation;
    
    
    
    
    case class Query(val attributes : List[Attribute[_]], table_name : String, condition : Option[Condition] = None) {
        override def toString = {
            val attribute_names = attributes.map { x => x.name }
            val select = "SELECT " + attribute_names
            val from = "FROM " + table_name
            val where = "WHERE " + condition.toString()
            select + "\n" + from + "\n" + where
        }
    }

    def toQuery(op: Operation) = op match {
        case r : Relation => relationToQuery(r)
        case p : Projection => projectionToQuery(p)
        case s : Selection => selectionToQuery(s)
//        case r : Rename => renameToQuery(r)
        case _ => throw new Exception("Operation not recognized")
    }
    
    def relationToQuery(r : Relation) : Query = Query(r.attributes, r.name, None)
    
    def projectionToQuery(p : Projection) : Query = {
        val original : Query = toQuery(p.op)
        val new_attributes = p.attributes.intersect(original.attributes)
        Query(new_attributes, original.table_name, original.condition)
    }
    
    def selectionToQuery(s : Selection) : Query = {
        val original : Query = toQuery(s.op)
        val new_condition = original.condition match {
            case None => Some(s.cond)
            case Some(c) => Some(AndCondition(c, s.cond))
        }
        Query(original.attributes, original.table_name, new_condition)
    }
    
           
        
        
}
