package co.edu.poli.Model;

public class Producto {
    private int id;
    private String nombre;
    private double Precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.Precio= precio; 
    }

    public String crear() {
        return "Producto creado: ID = " + id + ", Nombre = " + nombre;
    }

    public String actualizar(String nuevoNombre) {
        String antiguo = this.nombre;
        this.nombre = nuevoNombre;
        return "Producto actualizado: ID = " + id + ", de '" + antiguo + "' a '" + nuevoNombre + "'";

    }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return Precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		Precio = precio;
	}
    
}

