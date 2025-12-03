import Interfaces.MetodoPago;
import Interfaces.Reembolsable;
import Interfaces.Verificable;

import java.time.LocalDate;

public class PagoTarjeta implements MetodoPago, Reembolsable, Verificable {
    private String numeroTarjeta;
    private String cvv;
    private LocalDate fechaExpiracion;
    private String titular;
    private String referenciaActual;

    public PagoTarjeta(String numeroTarjeta, String cvv, LocalDate fechaExpiracion, String titular) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;
        this.titular = titular;
    }

    @Override
    public boolean validar() {
        if (cvv.length() != 3) {
            System.out.println("❌ Validación Fallida: cvv menor a 3 dígitos");
            return false;
        }
        if (fechaExpiracion.isBefore(LocalDate.now())) {
            System.out.println("❌ Validación Fallida: fecha de expiración incorrecta");
            return false;
        }
        System.out.println("✅ Validación Exitosa");
        return true;

    }

    @Override
    public boolean procesarPago(double monto, String referencia) {
        System.out.println("💳 Pago Procesado: "+monto+ "-Ref: "+referencia);
        this.referenciaActual = referencia;
        return true;
    }

    @Override
    public String generarComprobante() {
        String ult4 = numeroTarjeta.substring(numeroTarjeta.length()-4);
        return "Comprobante: TARJETA-xxxx-"+ult4+"-"+referenciaActual;
    }

    @Override
    public boolean procesarDevolucion(double monto, String motivo) {
        System.out.println("✅ Devolución Procesada: $"+monto);
        System.out.println("Motivo: "+motivo);
        return true;
    }

    @Override
    public int diasParaDevolucion() {
        return 30;
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


}
