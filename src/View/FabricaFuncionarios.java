package View;

public class FabricaFuncionarios {

	private FuncionarioCargo funcionarioCargo;
	
	public FuncionarioCargo cargo () {
		return funcionarioCargo;
	}
	
	public void createMethod (String CARGO) {
		switch (CARGO) {
		case "GERENTE":
			funcionarioCargo = new CargoGerente();
			break;

		case "VENDEDOR":
			funcionarioCargo = new CargoVendedor();
			break;
		}
	}
}
