import exceptions.MonetaryValueInvalidException;
import exceptions.NotFundException;
import lombok.AccessLevel;
import lombok.Getter;
public abstract class Conta implements IConta {

	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;

	@Getter(AccessLevel.NONE)
	protected Cliente cliente;

	public Conta(Cliente cliente, int agencia) {
		this.agencia = agencia;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.saldo =0;
	}

	@Override
	public void sacar(double valor) {
		if (valor<0){
			throw new MonetaryValueInvalidException("Valor negativo");
		}
		if (saldo<valor){
			throw new NotFundException( "Agencia: " + String.format("%d", this.agencia) + " Numero: " + String.format("%d", this.numero));
		}
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		if (valor<0){
			throw new MonetaryValueInvalidException("Valor negativo");
		}
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		double saldoSai = this.saldo;
		try{
			this.sacar(valor);
			contaDestino.depositar(valor);
		} catch (NotFundException e){
			System.out.println(e.getMessage());
		} catch (RuntimeException e){
			if (this.saldo != saldoSai){
				contaDestino.transferir( saldoSai-this.saldo, this );
			}
		}
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
