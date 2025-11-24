public class DVD extends MaterialBiblioteca {

    private int duracionMinutos;
    private String genero;
    private boolean tieneSubtitulos;

    public DVD(String codigo, String titulo, String autor, int anioPublicacion,
               int duracionMinutos, String genero, boolean tieneSubtitulos) {
        super(codigo, titulo, autor, anioPublicacion);

        if (duracionMinutos <= 0)
            throw new IllegalArgumentException("Duración inválida");

        if (genero == null || genero.isEmpty())
            throw new IllegalArgumentException("El género no puede estar vacío");

        this.duracionMinutos = duracionMinutos;
        this.genero = genero;
        this.tieneSubtitulos = tieneSubtitulos;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if (diasRetraso < 0)
            throw new IllegalArgumentException("Días inválidos");
        return diasRetraso * 1.50;
    }

    // SOBRECARGA
    public double calcularMultaPorRetraso(int diasRetraso, boolean esEstreno) {
        double base = calcularMultaPorRetraso(diasRetraso);
        return esEstreno ? base * 2 : base;
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        return 3;
    }
}
