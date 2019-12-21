package View;

import java.util.ArrayList;

public class ContextStrategy<T> {

	private StrategyIterator strategyIterator;

	public Iterator kindIterator(Object collection) {
		if (collection instanceof String[]) {
			this.strategyIterator = new VetorIterator((Object[]) collection);
		} else if (collection instanceof ArrayList) {
			this.strategyIterator = new ArrayListIterator<T>((ArrayList<T>) collection);
		}
		return this.strategyIterator.criarIterator();
	}
}
