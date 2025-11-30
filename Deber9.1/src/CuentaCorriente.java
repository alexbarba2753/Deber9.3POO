public class CuentaCorriente implements OperacionesBancarias, Transferible{
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private double sobregiro = 500;

    public CuentaCorriente(String numeroCuenta, String titular, double saldo) {
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
    }

    @Override
    public boolean retirar(double monto) {
        if (saldo + sobregiro >= monto){
            saldo -= monto;
            if (saldo < 0){
                System.out.println("Retiro: $"+monto+" - Saldo: $"+saldo+" (Sobregiro Utilizado)");
            } else {
                System.out.println("Retiro: $"+monto+" - Saldo: $"+saldo);
            }
            return true;
        }
        System.out.println("Límite de Sobregiro excedido");
        return false;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public boolean transferir(double monto, String cuentaDestino) {
        if (saldo + sobregiro >= monto){
            saldo -= monto;
            System.out.println("Transferencia: $"+monto+" a cuenta: "+cuentaDestino+" - Saldo: $"+saldo);
            return true;
        }
        System.out.println("No se pudo Transferir, sobregiro Insuficiente.");
        return false;
    }
}
