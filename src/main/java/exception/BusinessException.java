package exception;

public class BusinessException extends Exception {

	// Serializa el mensaje y el mensaje sae en orden
	// la tira de bit se ordena y pasa del primer a ultimo byte
	private static final long serialVersionUID = -88237426027806218L;

	public BusinessException(String mensaje) {
		super(mensaje);
	}

}
