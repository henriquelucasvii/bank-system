package br.com.sistemabancario2026.model;

public class Conta04Remunerada extends Conta04 implements ContaCorrenteInterface04 {

    public Conta04Remunerada(int numeroAgencia, int numeroConta, String nomeCliente, double saldo) {
        super(numeroAgencia, numeroConta, nomeCliente, saldo);
    }

    @Override
    public void calcularJuros() {
        double valor = getSaldo() * TAXA_JUROS;
        depositar(valor);

        System.out.printf("Novo saldo %.2f \n", this.getSaldo());
    }
}
