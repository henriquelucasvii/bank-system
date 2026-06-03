package br.com.sistemabancario2026.model;

public class Conta06CorrenteEspecial extends Conta06 {
    private float limite;

    public Conta06CorrenteEspecial(int numeroAgencia, int numeroConta, String nomeCliente, double saldo, float limite) {
        super(numeroAgencia, numeroConta, nomeCliente, saldo);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public int sacar(double valor) {
        if (valor > (getSaldo() + this.limite)) {
            return 0;
        }

        setSaldo(getSaldo() - valor);
        return 1;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Limite do Cliente: " + this.limite);
        System.out.println("Total disponivel: " + (this.limite + this.getSaldo()));
    }
}
