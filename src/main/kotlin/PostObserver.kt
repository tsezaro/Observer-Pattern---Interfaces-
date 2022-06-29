interface PostObserver {
    fun postRecibido(post: Post, listaCorreo: ListaCorreo)
}

class mailObserver : PostObserver{
    lateinit var mailSender: MailSender
    var prefijo = ""

    override fun postRecibido(post: Post, listaCorreo: ListaCorreo) {
        mailSender.sendMail(
            Mail(from = post.mailEmisor(),
                to = mailDestino(listaCorreo, post),
                subject = "[${prefijo}] ${post.asunto}",
                content = post.mensaje)
        )
    }

    private fun mailDestino(listaCorreo: ListaCorreo, post: Post) =
        listaCorreo.getUsuariosDestino(post)
        .map { it.mailPrincipal }
        .joinToString(", ")
}

class MalasPalabrasObserver: PostObserver{
    val malasPalabras = mutableListOf<String>("guerra", "hambre")
    val postARevisar = mutableListOf<Post>()
    override fun postRecibido(post: Post, listaCorreo: ListaCorreo) {
        if(this.tieneMalasPalabras(post)){
            postARevisar.add(post)
        }
    }

    private fun tieneMalasPalabras(post: Post) =
        malasPalabras.any{ post.contiene(it) }

}