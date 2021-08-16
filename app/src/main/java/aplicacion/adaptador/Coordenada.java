package aplicacion.adaptador;


public class Coordenada extends utilidades.localizacion_gps.Coordenada {
    public Coordenada () {
        super();
    }

    protected int identificador;
    protected int recibido;
    protected String texto = null;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    protected String titulo = null;

    public long getSesion() {
        return sesion;
    }

    public void setSesion(long sesion) {
        this.sesion = sesion;
    }

    protected long sesion;

    @Override
    public int getIdentificador() {
        return identificador;
    }

    @Override
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getRecibido() {
        return recibido;
    }

    public void setRecibido(int recibido) {
        this.recibido = recibido;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    protected int eliminado;






}
