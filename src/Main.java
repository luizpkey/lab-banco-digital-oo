import exceptions.MonetaryValueInvalidException;

public class Main {

	public static void main(String[] args) {

		Banco banco = new Banco("Dio Bank");
		Cliente baltazar = new Cliente();
		baltazar.setNome("Baltazar");
		Conta contaCorrente;

		contaCorrente = banco.addConta(baltazar, TipoConta.CORRENTE);
		Conta contaPoupanca;
		contaPoupanca = banco.addConta(baltazar, TipoConta.POUPANCA);

		try {

			contaCorrente.depositar(100);
			contaCorrente.transferir(100, contaPoupanca);

			contaCorrente.imprimirExtrato();
			contaPoupanca.imprimirExtrato();

			contaCorrente.transferir(100, contaPoupanca);
			contaCorrente.depositar(100);

			contaCorrente.imprimirExtrato();
			contaPoupanca.imprimirExtrato();

			contaCorrente.depositar(-100);
			contaCorrente.transferir(100, contaPoupanca);

			contaCorrente.imprimirExtrato();
			contaPoupanca.imprimirExtrato();
    	} catch (MonetaryValueInvalidException e) {
			e.toString();
			System.out.println(e.getMessage());
		}
	}

}
