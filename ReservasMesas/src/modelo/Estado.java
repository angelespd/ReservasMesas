package modelo;

public interface Estado {

	public void liberar(Mesa m);
	public void reservar(Mesa m) throws Exception;
	public void ocupar(Mesa m);
	
}
