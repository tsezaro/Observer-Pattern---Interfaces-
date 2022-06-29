data class Post(val emisor: Usuario, val asunto: String, val mensaje: String) {
    fun mailEmisor() = emisor.mailPrincipal

    fun contiene(Palabra : String) = mensaje.contains(Palabra) || asunto.contains(Palabra)
}