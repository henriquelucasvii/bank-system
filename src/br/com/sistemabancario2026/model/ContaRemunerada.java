package br.com.sistemabancario2026.model;

public class Conta05Remunerada extends Conta05 implements ContaCorrenteInterface {

    public Conta05Remunerada(int numeroAgencia, int numeroConta, String nomeCliente, double saldo) {
        super(numeroAgencia, numeroConta, nomeCliente, saldo);
    }

    @Override
    public void calcularJuros() {
        double valor = getSaldo() * TAXA_JUROS;
        depositar(valor);

        System.out.printf("Novo saldo %.2f \n", this.getSaldo());
    }
}
