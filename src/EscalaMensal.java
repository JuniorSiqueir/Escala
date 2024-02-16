import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class EscalaMensal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar ao usuário o número do mês e o ano desejados
        System.out.print("Digite o número do mês (1 para Janeiro, 2 para Fevereiro, etc.): ");
        int mes = scanner.nextInt() - 1; // Subtraindo 1 para corresponder aos índices de meses em Java (janeiro é 0)
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();

        // Criar uma instância de Calendar
        Calendar calendario = Calendar.getInstance();

        // Definir o calendário para o mês e ano fornecidos pelo usuário
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.YEAR, ano);

        // Obter o número de dias no mês
        int numeroDias = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Lista para armazenar os dias disponíveis de cada pessoa
        List<List<Integer>> escalaMensal = new ArrayList<>();
        List<String> nomesPessoas = new ArrayList<>();

        // Solicitar os nomes e os dias disponíveis de cada pessoa
        while (true) {
            System.out.print("Digite o nome da pessoa (ou 'fim' para encerrar): ");
            String nome = scanner.next();
            if (nome.equals("fim")) break;
            nomesPessoas.add(nome);

            List<Integer> diasPessoa = new ArrayList<>();
            while (true) {
                System.out.print("Digite os dias disponíveis para " + nome + " (ou -1 para encerrar): ");
                int diaDisponivel = scanner.nextInt();
                if (diaDisponivel == -1) break;

                // Verificar se o dia é válido
                if (diaDisponivel < 1 || diaDisponivel > numeroDias) {
                    System.out.println("Dia inválido. Por favor, digite um dia válido.");
                    continue;
                }
                diasPessoa.add(diaDisponivel);
            }
            escalaMensal.add(diasPessoa);
        }

        // Criar uma lista para armazenar as pessoas disponíveis em cada dia
        List<List<String>> escalaDia = new ArrayList<>();
        for (int dia = 1; dia <= numeroDias; dia++) {
            List<String> pessoasDisponiveis = new ArrayList<>();
            for (int i = 0; i < escalaMensal.size(); i++) {
                List<Integer> diasPessoa = escalaMensal.get(i);
                if (diasPessoa.contains(dia)) {
                    pessoasDisponiveis.add(nomesPessoas.get(i));
                }
            }
            escalaDia.add(pessoasDisponiveis);
        }

        // Imprimir a escala mensal
        System.out.println("\nEscala Diária:");
        for (int dia = 1; dia <= numeroDias; dia++) {
            List<String> pessoasDisponiveis = escalaDia.get(dia - 1);
            System.out.println("Dia " + dia + ": " + pessoasDisponiveis);
        }

        scanner.close();
    }
}