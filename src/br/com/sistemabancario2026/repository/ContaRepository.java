package br.com.sistemabancario2026.repository;

import br.com.sistemabancario2026.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContaRepository {

    public static void salvarDados(ArrayList<Conta> listaContas) {
        try (FileWriter fileWriter = new FileWriter("src/br/com/sistemabancario2026/repository/contas.csv")){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Conta conta: listaContas) {

                double limite = 0.0;

                if (conta instanceof ContaCorrenteEspecial contaRendeJuros) limite = contaRendeJuros.getLimite();
                String linhaTexto = conta.getNumeroAgencia() + ";" + conta.getNumeroConta() + ";" +  conta.getNomeCliente() + ";" +
                                    conta.getSaldo() + ";" +
                                    limite + "\n";

                bufferedWriter.write(linhaTexto);

            }
            bufferedWriter.close();
        } catch (IOException e) {

            System.out.println("Não foi possível concluir a operação!");
            e.printStackTrace();
        }
    }

    public static List<Conta> carregarDadosCSV() {
        ArrayList<Conta> listaConta = new ArrayList<>();

        try (FileReader fileReader = new FileReader("src/br/com/sistemabancario2026/repository/contas.csv")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row = bufferedReader.readLine();
            while (true) {
                if (row == null) break;

                String[] data = row.split(";");

                Conta conta = getConta(data);

                listaConta.add(conta);
                return listaConta;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaConta;
    }

    private static Conta getConta(String[] data) {
        int numeroAgencia = Integer.parseInt(data[0].trim());
        int numeroConta = Integer.parseInt(data[1].trim());
        String nomeCliente = data[2].trim();
        double saldo = Double.parseDouble(data[3].trim());
        float limite = Float.parseFloat(data[4].trim());

        Conta conta = null;

        if (numeroAgencia > 5000) {
            conta = new ContaCorrenteEspecial(numeroAgencia, numeroConta, nomeCliente, saldo, limite);
        }

        if (numeroAgencia < 1000){
            conta = new ContaRemunerada(numeroAgencia, numeroConta, nomeCliente, saldo);
        }

        if (numeroAgencia >= 1000 && numeroAgencia <= 5000){
            conta = new ContaCorrente(numeroAgencia, numeroConta, nomeCliente, saldo);
        }

        return conta;
    }
}