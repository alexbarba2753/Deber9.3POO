import Interfaces.MetodoPago;
import Interfaces.Reembolsable;

public class PagoPaypal implements MetodoPago, Reembolsable {
    private String email;
    private String token;
    private String referenciaActual;

    public PagoPaypal(String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override
    public boolean validar() {
        if (email.contains("@")){
            System.out.println("✅ Validación exitosa");
            return true;
        } else{
            System.out.println("❌ Validación fallida");
            return false;
        }
    }

    @Override
    public boolean procesarPago(double monto, String referencia) {
        System.out.println("💰Pago procesado: "+monto+"-Ref: "+referencia);
        this.referenciaActual = referencia;
        return true;
    }

    @Override
    public String generarComprobante() {
        return "Comprobante: PAYPAL-"+email+"-" + referenciaActual;
    }

    @Override
    public boolean procesarDevolucion(double monto, String motivo) {
        System.out.println("✅ Devolución Procesada: $"+monto);
        System.out.println("Motivo: "+motivo);
        return true;
    }

    @Override
    public int diasParaDevolucion() {
        return 180;
    }
}
