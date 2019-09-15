package heran�a;

//import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class TestaBanco {

	public static void main(String[] args) {
		
		Random r = new Random();
		//DecimalFormat df = new DecimalFormat();
		
		Scanner sc = new Scanner(System.in);
		
		int op, op1, op2, opconta;

		Banco b = new Banco();
		
		System.out.println("Digite a op��o correspondete ao tipo de conta: (1) Corrente ou (2) Poupan�a");
		opconta = sc.nextInt();
		System.out.println("Digite o nome do titular: ");
		b.nome = sc.next();
		System.out.println("Digite o CPF do titular: ");
		b.cpf = sc.next();
		System.out.println("Digite o n�mero da conta: ");
		b.numConta = sc.next();
		
		if (opconta == 1) {
			
			ContaCorrente cc = new ContaCorrente();
			
			b.tipoConta = "Corrente";
			
			String.format("%.2f", cc.saldo =  r.nextDouble()*100000); 
			
			System.out.println("Por favor confira se seus dados est�o corretos: ");
			System.out.println();
			b.imprimeDados();
			System.out.println();
			System.out.println("Pressione (1) para Prosseguir ou (2) para Corrigir");
			op2 = sc.nextInt();
			
			if (op2 == 2) {
				
				System.out.println();
				System.out.println();
				System.out.println("Ent�o corrija seus dados: ");
				System.out.println();
				System.out.println("Digite o nome do titular: ");
				b.nome = sc.next();
				System.out.println("Digite o CPF do titular: ");
				b.cpf = sc.next();
				System.out.println("Digite o n�mero da conta: ");
				b.numConta = sc.next();
				System.out.println();
				System.out.println();
				System.out.println("Dados atualizados com sucesso!");
				System.out.println();
				b.imprimeDados();
				System.out.println();
				
				} 
				
					op1 = 1;
					while (op1 == 1) {
						
						System.out.println();
						// depois fazer transfer�ncia  e abertura de contas
						System.out.println("O que voc� deseja fazer? \n (1) Consulta Saldo \n (2) Saque \n (3) Dep�sito \n (4) Encerrar"); 
						op = sc.nextInt();
						
						if (op == 1) {
							cc.imprimeSaldo(); 
						}
						
						if (op == 2) {
							System.out.println("Digite o valor do saque desejado: ");
							cc.saque = sc.nextDouble();
							cc.saque();
						}
						
						if (op == 3) {
							System.out.println("Digite o valor do dep�sito desejado: ");
							cc.deposito = sc.nextDouble();
							cc.deposito();
						}
						
						System.out.println();
						System.out.println("Deseja fazer outra opera��o? \n (1) Sim \n (2) N�o");
						op1 = sc.nextInt();
						
						if (op == 4) {
							op1 = 2;
						}
						
					}
				
			}
		
		if (opconta == 2) {
			
			ContaPoupanca cp = new ContaPoupanca();
			
			b.tipoConta = "Poupan�a";
			
			cp.saldo = r.nextDouble()*100000;
			
			System.out.println("Por favor confira se seus dados est�o corretos: ");
			System.out.println();
			b.imprimeDados();
			System.out.println();
			System.out.println("Pressione (1) para Prosseguir ou (2) para Corrigir");
			op2 = sc.nextInt();
			
			if (op2 == 2) {
				
				System.out.println();
				System.out.println();
				System.out.println("Ent�o corrija seus dados: ");
				System.out.println();
				System.out.println("Digite o nome do titular: ");
				b.nome = sc.next();
				System.out.println("Digite o CPF do titular: ");
				b.cpf = sc.next();
				System.out.println("Digite o n�mero da conta: ");
				b.numConta = sc.next();
				System.out.println();
				System.out.println();
				System.out.println("Dados atualizados com sucesso!");
				System.out.println();
				b.imprimeDados();
				System.out.println();
				
				} 
			
			op1 = 1;
			while (op1 == 1) {
				
				System.out.println();
				// depois fazer transfer�ncia  e abertura de contas
				System.out.println("O que voc� deseja fazer? \n (1) Consulta Saldo \n (2) Saque \n (3) Dep�sito \n (4) Encerrar"); 
				op = sc.nextInt();
				
				if (op == 1) {
					cp.imprimeSaldo();
				}
				
				if (op == 2) {
					System.out.println("Digite o valor do saque desejado: ");
					cp.saque = sc.nextDouble();
					cp.saque();
				}
				
				if (op == 3) {
					System.out.println("Digite o valor do dep�sito desejado: ");
					cp.deposito = sc.nextDouble();
					cp.deposito();
				}
				
				System.out.println();
				System.out.println("Deseja fazer outra opera��o? \n (1) Sim \n (2) N�o");
				op1 = sc.nextInt();
				
				if (op == 4) {
					op1 = 2;
				}
				
			}
			
		}
				
		sc.close();
		
	}

}
