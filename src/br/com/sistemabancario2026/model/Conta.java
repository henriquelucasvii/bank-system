package br.com.sistemabancario2026.model;

public class Conta {
    private int numeroAgencia;
    private int numeroConta;
    private String nomeCliente;
    private double saldo;

    public Conta(int numeroAgencia, int numeroConta, String nomeCliente, double saldo) {
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int sacar(double valor) {
        if (this.saldo > valor) {
            this.saldo -= valor;
            return 0;
        }
        return 1;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void imprimir() {
        System.out.println("Número da agência: " + this.numeroAgencia +
                "\nNúmero da Conta: " + this.numeroConta +
                "\nNome do Cliente: " + this.nomeCliente +
                "\nSaldo do Cliente: " + this.saldo);
    }
}
