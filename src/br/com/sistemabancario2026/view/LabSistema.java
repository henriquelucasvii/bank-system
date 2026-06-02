package br.com.sistemabancario2026.view;

import br.com.sistemabancario2026.model.*;
import br.com.sistemabancario2026.repository.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LabSistema {
    private ArrayList<Conta> listaContas = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        LabSistema app = new LabSistema();
        app.iniciarSistema();
        app.exibirMenu();
    }

    public void iniciarSistema() {
        this.listaContas = (ArrayList<Conta>) ContaRepository.carregarDadosCSV();
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {

            System.out.println("===== Sistema Bancário =====");
            System.out.println("Escolha uma das opções: \n1 - Cadastramento \n2 - Saque \n3 - Depósito \n4 - Consulta \n5 - Atualizar Rendimentos \n0 - Sair");
            option = lerOpcaoMenu(sc,5, "OPÇÕES DO SISTEMA BANCÁRIO");

            switch (option) {
                case 1 -> executarCadastramento(sc);
                case 2 -> executarSaque(sc);
                case 3 -> executarDeposito(sc);
                case 4 -> executarConsulta(sc);
                case 5 -> executarAtualizacaoJuros();
                case 0 -> executarSaida();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }

    public void executarSaida() {
        ContaRepository.salvarDados(this.listaContas);
        System.out.println("Sistema encerrado e dados guardados com sucesso no arquivo contas.csv");
    }

    private void executarCadastramento(Scanner sc) {
        System.out.println("===== Cadastro de Conta =====");

        int numeroConta = lerInteiroSeguro(sc, "NÚMERO DA CONTA");

        if (buscarContaPorNumero(numeroConta) != null) {
            System.out.println("Essa conta já exite");
            return;
        }

        String nomeCliente = lerNomeCliente(sc);

        int numeroAgencia = lerInteiroSeguro(sc, "NÚMERO DA AGÊNCIA");
        double saldo = lerDoubleSeguro(sc, "SALDO INICIAL");

        if (confirmarOperacao(sc, "CADASTRO")) {

            Conta conta = null;
            if (numeroAgencia > 5000) {
                float limite = (float) lerDoubleSeguro(sc, "LIMITE");
                conta = new ContaCorrenteEspecial(numeroAgencia, numeroConta, nomeCliente, saldo, limite);
            }

            if (numeroAgencia < 1000){
                conta = new ContaRemunerada(numeroAgencia, numeroConta, nomeCliente, saldo);
            }

            if (numeroAgencia >= 1000 && numeroAgencia <= 5000){
                conta = new ContaCorrente(numeroAgencia, numeroConta, nomeCliente, saldo);
            }

            listaContas.add(conta);
            System.out.println("Cadastro Realizado com Sucesso.");
        }
    }

    private boolean confirmarOperacao(Scanner sc, String operacao) {
        System.out.println("Deseja continuar com a operação de " + operacao + "? (S/N)");

        String resposta = sc.nextLine().toLowerCase().trim();

        if (resposta.equals("s") || resposta.equals("sim")) {
            return true;
        }

        System.out.println("Operação de " + operacao + " Cancelada pelo Usuário.");
        return false;
    }

    private void executarSaque(Scanner sc) {
        System.out.println("===== Saque =====");

        Conta conta = obterContaValidada(sc);
        if (conta == null) {
            return;
        }

        double valorSaque = lerDoubleSeguro(sc, "SAQUE");

        if (valorSaque >= conta.getSaldo()) {
            System.out.println("Saldo insuficiente.");
            return;
        }

        if (!confirmarOperacao(sc, "SAQUE")) {
            return;
        }

        int resultado = conta.sacar(valorSaque);

        if (resultado == 0) {
            System.out.printf("Saque realizado com sucesso! Novo saldo: R$ %.2f \n", conta.getSaldo());
        } else {
            System.out.println("ATENÇÃO!: Saldo insuficiente");
        }
    }

    private void executarDeposito(Scanner sc) {
        System.out.println("===== Depósito =====");

        Conta conta = obterContaValidada(sc);
        if (conta == null) {
            return;
        }

        double valorDeposito = lerDoubleSeguro(sc, "DEPÓSITO");

        if (confirmarOperacao(sc, "DEPÓSITO")) {
            conta.depositar(valorDeposito);
            System.out.printf("Depósito realizado! Novo saldo: R$ %.2f \n", conta.getSaldo());
        }
    }

    public void executarConsulta(Scanner sc) {
        Conta conta = obterContaValidada(sc);
        if (conta == null) {
            return;
        }
        conta.imprimir();
    }

    public void executarAtualizacaoJuros() {
        boolean atualizou = true;

        for (Conta conta: listaContas) {
            if (conta instanceof ContaCorrenteInterface contaRendeJuros) {
                contaRendeJuros.calcularJuros();
                atualizou = false;
            }
        }

        if (!atualizou) {
            System.out.println("Nenhuma conta remunerada encontrada na lista.");
        }
    }

    public int lerInteiroSeguro(Scanner sc, String msg) {
        while (true) {
            System.out.println(msg + " - Digite um valor: ");
            String valorEntrada = sc.nextLine().strip();

            try {
                int valor = Integer.parseInt(valorEntrada);

                if (valor > 0) {
                    return valor;
                }

                System.out.println("Digite um valor maior que zero");
            } catch (NumberFormatException error) {
                System.out.println("ERRO: Digite somente valores numéricos. \nCódigo do erro: " + error);
            }
        }
    }

    public double lerDoubleSeguro(Scanner sc, String msg) {
        while (true) {

            System.out.println(msg + " - Digite um valor: ");
            String valorEntrada = sc.nextLine().strip().replace(",", ".");

            try {
                double valor = Double.parseDouble(valorEntrada);

                if (valor > 0) {
                    return valor;
                }

                System.out.println("Digite um valor maior que zero");
            } catch (NumberFormatException error) {
                System.out.println("ERRO: Digite somente valores numéricos. \nCódigo do erro: " + error);
            }
        }
    }

    public String lerNomeCliente(Scanner sc) {
        String nomeCliente;

        do {
            System.out.println("Digite o seu nome:");
            nomeCliente = sc.nextLine().trim();

            if (nomeCliente.isEmpty()) {
                System.out.println("Digite um nome válido");
            }

        } while (nomeCliente.isEmpty());

        return nomeCliente;
    }

    public int lerOpcaoMenu(Scanner sc, int totalOpcoes, String msg) {
        while (true) {
            System.out.println("Operações de: " + msg);
            String valorEntrada = sc.nextLine().strip();

            try {
                int valor = Integer.parseInt(valorEntrada);

                if (valor >= 0) {
                    return valor;
                }

                System.out.println("Digite um valor maior que zero");
            } catch (NumberFormatException error) {;
                System.out.println("ERRO: Digite somente valores numéricos. " + error);
            }
        }
    }

    public Conta buscarContaPorNumero(int numeroConta) {
        for(Conta conta: listaContas) {
            if (numeroConta == conta.getNumeroConta()) {
                return conta;
            }
        }
        return null;
    }

    private Conta obterContaValidada(Scanner sc) {
        int numeroConta = lerInteiroSeguro(sc, "NÚMERO DA CONTA");
        Conta conta = buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada");
        }

        return conta;
    }
}