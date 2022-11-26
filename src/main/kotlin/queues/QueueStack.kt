package queues

internal const val INSERTION_INDEX = 0

class QueueStack<E> : QueueContract<E> {

    var length: Int = 0
        private set

    val head: List<E>
        get() {
            secondStack = mutableListOf()

            var counter = 0
            val lastIndex = firstStack.lastIndex

            while (counter <= lastIndex) {
                val item = firstStack.removeAt(0)
                secondStack.add(0, item)
                counter++
            }
            return secondStack
        }

    private var firstStack = mutableListOf<E>()
    private var secondStack = mutableListOf<E>()

    override fun peek(): E? = firstStack.lastOrNull()

    override fun dequeue() {
        // update the second stack with items in the first stack
        // skipping the first element in the first stack
        secondStack = mutableListOf()

        var counter = 0
        val lastIndex = firstStack.lastIndex
        while(counter < lastIndex) {
            val item = firstStack.removeAt(0)
            secondStack.add(0, item)
            counter++
        }

        firstStack = mutableListOf()
        length = 0
        // re-update the first stack
        secondStack.forEach(::enqueue)
    }

    override fun enqueue(element: E) {
        firstStack.add(index = INSERTION_INDEX, element = element)
        length++
    }

    fun isEmpty(): Boolean = length == 0
}
