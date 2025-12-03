import javax.crypto.spec.PSource;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        double totalProcesado = 0;
        double totalDevuelto = 0;

        Scanner entrada = new Scanner(System.in);

        System.out.println("=== PROCESAMIENTO DE PAGOS ===");
        // Pago con Tarjeta de Crédito

        System.out.println("\n--- Pago con Tarjeta de Crédito ---");

        PagoTarjeta tarjeta = new PagoTarjeta("1234567890","123", LocalDate.of(2030,5,1),"Mateo Barba");

        tarjeta.validar();
        tarjeta.verificarIdentidad("1234567890");
        tarjeta.esSeguro();
        tarjeta.procesarPago(250,"TXN-001");
        System.out.println(tarjeta.generarComprobante());
        totalProcesado+=250;

        System.out.println("\n --- Pago con Paypal ---");

        PagoPaypal paypal = new PagoPaypal("usuario@email.com","TOKEN123");

        paypal.validar();
        paypal.procesarPago(180,"TXN-002");
        System.out.println(paypal.generarComprobante());
        totalProcesado+=180;

        System.out.println("\n --- Pago con Transferencia ---");

        PagoTransferencia transferencia = new PagoTransferencia("1234567890","Banco Pichincha","Mato Barba");

        transferencia.validar();
        transferencia.verificarIdentidad("1234567890");
        transferencia.esSeguro();
        transferencia.procesarPago(500,"TXN-003");
        System.out.println(transferencia.generarComprobante());
        totalProcesado+=500;

        System.out.println("\n==== PROCESAMIENTO DE DEVOLUCIONES ====");
        System.out.println("\n--- Devolución Tarjeta ---");
        tarjeta.procesarDevolucion(50,"Producto defectuoso");
        System.out.println("Días permitidos: "+tarjeta.diasParaDevolucion()+" días");
        totalDevuelto+=50;

        System.out.println("\n--- Devolución Transferencia ---");
        transferencia.procesarDevolucion(100,"Producto defectuoso");

        System.out.println("\nTOTAL PROCESADO: $"+totalProcesado);
        System.out.println("TOTAL DEVUELTO: $"+totalDevuelto);
        System.out.println("NETO: $"+(totalProcesado - totalDevuelto));
    }
}