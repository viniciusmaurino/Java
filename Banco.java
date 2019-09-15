package herança;

public class Banco {
	
	public String nome;
	public String cpf;
	public String numConta;
	public String tipoConta;
	public double saldo;
	public double saque;
	public double deposito;
	
	public void imprimeDados() {
		System.out.println("Nome do Titular: "+nome);
		System.out.println("CPF do Titular: "+cpf);
		System.out.println("Tipo de Conta: "+tipoConta);
		System.out.println("Número da Conta: "+numConta);
	}
	
	public void imprimeSaldo() {
		System.out.println("O saldo atual da conta é: R$"+(String.format("%.2f", saldo)));
	}
	
	public void deposito() {
		saldo = saldo+deposito;
		System.out.println("O saldo atual da conta é: "+(String.format("%.2f", saldo)));
	}
	
	public void saque() {
		saldo = saldo-saque;
		System.out.println("O saldo atual da conta é: "+(String.format("%.2f", saldo)));
	}

}
