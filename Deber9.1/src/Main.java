public class Main {
    public static void main(String[] args) {
        CuentaAhorros cuentaAh = new CuentaAhorros("1234","Mateo",200);
        CuentaCorriente cuentaCo = new CuentaCorriente("4321","Alex",1000);

        System.out.println("==== CUENTA DE AHORROS ====");
        cuentaAh.depositar(300);
        cuentaAh.retirar(450);

        System.out.println("\n==== CUENTA CORRIENTE ====");
        cuentaCo.retirar(1200);
        cuentaCo.transferir(300, "9876");

        System.out.println("\n==== SALDOS FINALES ====");
        System.out.println("Cuenta Ahorros: $"+cuentaAh.consultarSaldo());
        System.out.println("Cuenta Corriente: $"+cuentaCo.consultarSaldo());
    }
}