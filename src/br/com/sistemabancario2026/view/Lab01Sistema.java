package br.com.sistemabancario2026.view;

import br.com.sistemabancario2026.model.Conta;

import java.util.Locale;
import java.util.Scanner;

public class Lab01Sistema {
    Conta conta = new Conta();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Lab01Sistema sistema = new Lab01Sistema();
        sistema.exibirMenu();

    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {

            System.out.println("===== Sistema Bancário =====");
            System.out.println("Escolha uma das opções: \n1 - Cadastramento \n2 - Saque \n3 - Depósito \n0 - Sair");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    executarCadastramento(sc);
                case 2:
                    executarSaque(sc);
                case 3:
                    executarDeposito(sc);
                case 0:
                    System.out.println("Encerrando o programa");
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }

    private void executarCadastramento(Scanner sc) {
        System.out.println("===== Cadastro de Conta =====");

        System.out.println("Digite o número da sua agência:");
        int numeroAgencia = sc.nextInt();

        System.out.println("Digite o número da sua conta");
        int numeroConta = sc.nextInt();

        sc.nextLine();

        System.out.println("Digite o seu nome:");
        String nomeCliente = sc.nextLine();

        System.out.println("Digite o seu saldo:");
        double saldo = sc.nextDouble();

        if (confirmarOperacao(sc, "CADASTRO")) {
            conta.numeroAgencia = numeroAgencia;
            conta.numeroConta = numeroConta;
            conta.nomeCliente = nomeCliente;
            conta.saldo = saldo;

            System.out.println("Cadastro Realizado com Sucesso.");
        }
    }

    private boolean confirmarOperacao(Scanner sc, String operacao) {
        System.out.println("Deseja continuar com a operação de " + operacao + "? (S/N)");
        sc.nextLine();
        String resposta = sc.nextLine().toUpperCase();

        if (resposta.equals("S")) {
            return true;
        }

        System.out.println("Operação de " + operacao + " Cancelada pelo Usuário.");
        return false;
    }

    private void executarSaque(Scanner sc) {
        System.out.println("===== Saque =====");

        System.out.println("Digite o valor do saque:");
        double saque = sc.nextDouble();

        if (saque > conta.saldo) {
            System.out.println("ATENÇÃO - Saldo insuficiente.");
            return;
        }

        if (confirmarOperacao(sc, "SAQUE")) {
            conta.saldo -= saque;
            System.out.printf("Saque realizado! Novo saldo: R$ %.2f%n", conta.saldo);
        }
    }

    private void executarDeposito(Scanner sc) {
        System.out.println("===== Depósito =====");

        System.out.println("Digite o valor do deposito:");
        double valorDeposito = sc.nextDouble();

        if (confirmarOperacao(sc, "DEPÓSITO")) {
            conta.saldo += valorDeposito;
            System.out.printf("Depósito realizado! Novo saldo: R$ %.2f%n", conta.saldo);

        }
    }
}
