import Interfaces.MetodoPago;
import Interfaces.Reembolsable;
import Interfaces.Verificable;

public class PagoTransferencia implements MetodoPago, Verificable, Reembolsable {
    private String numeroCuenta;
    private String banco;
    private String titular;
    private String referenciaActual;

    public PagoTransferencia(String numeroCuenta, String banco, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.titular = titular;
    }

    @Override
    public boolean validar() {
        if (numeroCuenta.length() == 10){
            System.out.println("✅ Validación exitosa");
            return true;
        } else{
            System.out.println("❌ Validación fallida");
            return false;
        }
    }

    @Override
    public boolean procesarPago(double monto, String referencia) {
        System.out.println("🏦 Pago procesado: "+monto+"-Ref: "+referencia);
        this.referenciaActual = referencia;
        return true;
    }

    @Override
    public String generarComprobante() {
        return "Comprobante: TRANSFERENCIA-"+numeroCuenta+"-"+referenciaActual;
    }

    @Override
    public boolean verificarIdentidad(String documento) {
        if (documento.length() == 10){
            System.out.println("✅ Identidad Verificada: CI "+documento);
            return true;
        } else {
            System.out.println("❌ Identidad Inválida: CI "+documento+" (Tiene que ser 10 dígitos)");
            return false;
        }
    }

    @Override
    public boolean esSeguro() {
        System.out.println("✅ Método de pago seguro");
        return true;
    }

    @Override
    public boolean procesarDevolucion(double monto, String motivo) {
        System.out.println("❌ Este método de pago no permite devoluciones");
        return false;
    }

    @Override
    public int diasParaDevolucion() {
        return 0;
    }
}
