package br.com.sistemabancario2026.model;

public class Conta06Remunerada extends Conta06 implements ContaCorrenteInterface {

    public Conta06Remunerada(int numeroAgencia, int numeroConta, String nomeCliente, double saldo) {
        super(numeroAgencia, numeroConta, nomeCliente, saldo);
    }

    @Override
    public void calcularJuros() {
        double valor = getSaldo() * TAXA_JUROS;
        depositar(valor);

        System.out.printf("Novo saldo %.2f \n", this.getSaldo());
    }
}
