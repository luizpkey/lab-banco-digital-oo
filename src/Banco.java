import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter

public class Banco {


	public Banco(String nome) {
		this.nome = nome;
		this.contas = new ArrayList<Conta>();
	}
	private static final int AGENCIA_PADRAO = 1;
	private String nome;
	private List<Conta> contas;

	public Conta addConta(Cliente cliente, TipoConta tipoConta) {
		Conta conta;
		if (tipoConta == TipoConta.CORRENTE){
			conta = new ContaCorrente(cliente,AGENCIA_PADRAO);
		}else if ( tipoConta == TipoConta.POUPANCA){
			conta = new ContaPoupanca(cliente,AGENCIA_PADRAO);
		}else{
			throw new IllegalArgumentException("Tipo de conta inválido");
		}
		contas.add( conta);
		return conta;
	}

}
