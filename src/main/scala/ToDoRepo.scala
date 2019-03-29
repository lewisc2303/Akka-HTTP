import scala.concurrent.{ExecutionContext, Future}

trait ToDoRepo {

  def all(): Future[Seq[ToDo]]
  def done(): Future[Seq[ToDo]]
  def pending(): Future[Seq[ToDo]]
}

class InMemoryToDoRepository(initialToDos: Seq[ToDo] = Seq.empty)(implicit ec : ExecutionContext) extends ToDoRepo {

  private var todos: Vector[ToDo] = initialToDos.toVector

  override def all(): Future[Seq[ToDo]] = Future.successful(todos)

  override def done(): Future[Seq[ToDo]] = Future.successful((todos).filter(_.done))

  override def pending(): Future[Seq[ToDo]] = Future.successful((todos).filterNot(_.done))
}