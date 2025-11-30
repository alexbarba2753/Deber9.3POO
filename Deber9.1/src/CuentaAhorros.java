public class CuentaAhorros implements OperacionesBancarias{
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaAhorros(String numeroCuenta, String titular, double saldo) {
        if (numeroCuenta == null || numeroCuenta.isEmpty())
            throw new IllegalArgumentException("Número de cuenta vacio");
        if (titular == null || titular.isEmpty())
            throw new IllegalArgumentException("Titular vacio");
        if (saldo < 0)
            throw new IllegalArgumentException("El saldo no puede ser cero");
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }


    @Override
    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Depósito: $"+monto+" - Saldo: $"+saldo);
    }

    @Override
    public boolean retirar(double monto) {
        if (saldo >= monto){
            saldo -= monto;
            System.out.println("Retiro: $"+monto+" - Saldo: $"+saldo);

            if (saldo < 100){
                saldo -= 5;
                System.out.println("Comisión aplicada: $5.0 - Saldo: $"+saldo);

            }
            return true;
        }
        System.out.println("Fondos Insuficientes.");
        return false;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }
}
