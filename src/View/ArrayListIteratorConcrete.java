package View;

import java.util.ArrayList;

public class ArrayListIteratorConcrete<T> extends ArrayListIterator<T> implements Iterator {

	public ArrayListIteratorConcrete(ArrayList<T> arrayList) {
		super(arrayList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object next() {
		Object next = arrayList.get(cont);
		cont++;
		return next;
	}

	@Override
	public boolean hasNext() {
		return cont < arrayList.size();
	}
}
