package n1x0nj4.maidenmvvm.mapper

interface ViewMapper<in D, out P> {

    fun mapToView(domain: D): P
}