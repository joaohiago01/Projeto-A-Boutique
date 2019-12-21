package View;

public class VetorIteratorConcrete extends VetorIterator implements Iterator {

	public VetorIteratorConcrete(Object[] vetor) {
		super(vetor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object next() {
		String obj = vetor[cont].toString();
		cont++;
		return obj;
	}

	@Override
	public boolean hasNext() {
		if (cont >= vetor.length || vetor[cont] == null)
			return false;
		else 
			return true;
	}

}
