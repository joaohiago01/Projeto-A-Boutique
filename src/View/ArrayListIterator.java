package View;

import java.util.ArrayList;

public class ArrayListIterator<T> implements StrategyIterator {

	protected ArrayList<T> arrayList;
	protected int cont;
	
	public ArrayListIterator (ArrayList<T> arrayList) {
		this.arrayList = arrayList;
	}

	@Override
	public Iterator criarIterator() {
		return new ArrayListIteratorConcrete<T>(this.arrayList);
	}

}
