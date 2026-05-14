package br.com.sistemabancario2026.view;

import br.com.sistemabancario2026.model.Conta;

import java.util.Locale;
import java.util.Scanner;

public class Lab01Sistema {
    Conta conta = new Conta();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Lab01Sistema app = new Lab01Sistema();
        app.exibirMenu();

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
                case 4:
                    executarConsulta();
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

        int numeroAgencia = lerNumeroAgEConta(sc, "NÚMERO DA AGÊNCIA");
        int numeroConta = lerNumeroAgEConta(sc, "NÚMERO DA CONTA");

        sc.nextLine();

        String nomeCliente = lerNomeCliente(sc);
        double saldo = lerValorSaqueDeposito(sc, "SALDO INICIAL");

        if (confirmarOperacao(sc, "CADASTRO")) {
            conta.setNumeroConta(numeroAgencia);
            conta.setNumeroConta(numeroConta);
            conta.setNomeCliente(nomeCliente);
            conta.setSaldo(saldo);

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

        double valorSaque = lerValorSaqueDeposito(sc, "SAQUE");

        if (valorSaque < conta.getSaldo()) {
            if (confirmarOperacao(sc, "SAQUE")) {
                int resultado = conta.sacar(valorSaque);

                if (resultado == 1) {
                    System.out.printf("Saque realizado com sucesso! Novo saldo: R$ %.2f \n", conta.getSaldo());
                } else {
                    System.out.println("ATENÇÃO!: Saldo insuficiente");
                }

            }
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    private void executarDeposito(Scanner sc) {
        System.out.println("===== Depósito =====");

        double valorDeposito = lerValorSaqueDeposito(sc, "DEPÓSITO");

        if (confirmarOperacao(sc, "DEPÓSITO")) {
            conta.depositar(valorDeposito);
            System.out.printf("Depósito realizado! Novo saldo: R$ %.2f \n", conta.getSaldo());
        }
    }

    public void executarConsulta() {
            conta.imprimir();
        }


    public int lerNumeroAgEConta(Scanner sc, String msg) {
        int valor = 0;

        while (valor < 1) {
            System.out.println(msg + " - Digite um valor");
            valor = sc.nextInt();

            if (valor < 1) {
                System.out.println("Digite um valor maior do que 1");
            }
        }

        return valor;
    }

    public String lerNomeCliente(Scanner sc) {
        String nomeCliente;

        do {
            System.out.println("Digite o seu nome:");
            nomeCliente = sc.nextLine();

            if (nomeCliente == null) {
                System.out.println("Digite um nome válido");
            }

        } while (nomeCliente == null);

        return nomeCliente;
    }

    public double lerValorSaqueDeposito(Scanner sc, String msg) {
        double valor;

        do {
            System.out.println(msg + " - Digite um valor:");
            valor = sc.nextDouble();

            if (valor < 1) {
                System.out.println("Digite um valor maior do que 1.");
            }
        } while (valor < 1);

        return valor;
    }
}
