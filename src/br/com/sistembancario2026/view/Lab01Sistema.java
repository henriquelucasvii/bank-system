package br.com.sistembancario2026.view;

import br.com.sistembancario2026.model.Conta;

import java.util.Locale;
import java.util.Scanner;

public class Lab01Sistema {
    Conta conta = new Conta();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

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
            }
        }
        sc.close();
    }

    private void executarCadastramento(Scanner sc) {
        System.out.println("Cadastro de Conta");

        System.out.println("Digite o número da sua agência:");
        int numeroAgencia = sc.nextInt();

        System.out.println("Digite o número da sua conta");
        int numeroConta = sc.nextInt();

        sc.nextLine();

        System.out.println("Digite o seu nome:");
        String nomeCliente = sc.nextLine();

        System.out.println("Digite o seu saldo:");
        double saldo = sc.nextDouble();

        if (confirmarOperacao(sc, "Cadastramento")) {
            conta.numeroAgencia = numeroAgencia;
            conta.numeroConta = numeroConta;
            conta.nomeCliente = nomeCliente;
            conta.saldo = saldo;

            System.out.println("Cadastro Realizado com Sucesso.");
        }
    }

    private boolean confirmarOperacao(Scanner sc, String operacao) {
        System.out.println("Deseja continuar com a operação de " + operacao + "? (S/N)");
        String resposta = sc.nextLine().toUpperCase();

        if (resposta.equals("S")) {
            return true;
        }

        System.out.println("Operação de " + operacao + " Cancelada pelo Usuário.");
        return false;
    }

    private void executarSaque(Scanner sc) {}

    private void executarDeposito(Scanner sc) {}
}
