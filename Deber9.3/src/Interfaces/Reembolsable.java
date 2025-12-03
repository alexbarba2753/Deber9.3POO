package Interfaces;

public interface Reembolsable {
    boolean procesarDevolucion(double monto, String motivo);
    int diasParaDevolucion(); //dias permitidos para la devolución
}
