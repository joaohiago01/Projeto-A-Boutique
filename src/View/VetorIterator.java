package View;

public class VetorIterator implements StrategyIterator {

	protected Object[] vetor;
	protected int cont;

	public VetorIterator (Object[] vetor) {
		this.vetor = vetor;
	}

	@Override
	public Iterator criarIterator() {
		return new VetorIteratorConcrete(this.vetor);
	}

}
