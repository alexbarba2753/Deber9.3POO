import Excepciones.CodigoInvalidoException;
import Excepciones.MaterialNoDisponibleException;
import Excepciones.MaterialNoEncontradoException;

import java.util.ArrayList;

public class Biblioteca {

    ArrayList<MaterialBiblioteca> catalogo = new ArrayList<>();
    private String nombre;

    public Biblioteca(String nombre) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("Nombre inválido");
        this.nombre = nombre;
    }

    // Validar formato de código
    public static void validarCodigo(String codigo) throws CodigoInvalidoException {
        if (!(codigo.startsWith("LIB-") || codigo.startsWith("REV-") || codigo.startsWith("DVD-")))
            throw new CodigoInvalidoException("Código inválido: " + codigo);
    }

    // Generar códigos aleatorios
    public static String generarCodigoAleatorio(String tipo) {
        int num = (int)(Math.random() * 900) + 100;
        return tipo + "-" + num;
    }

    // Agregar material
    public void agregarMaterial(MaterialBiblioteca m) throws CodigoInvalidoException {
        if (m == null)
            throw new NullPointerException("Material no puede ser null");

        validarCodigo(m.getCodigo());
        catalogo.add(m);
    }

    // Buscar material
    public MaterialBiblioteca buscarMaterial(String codigo) {
        for (MaterialBiblioteca m : catalogo) {
            if (m.getCodigo().equals(codigo))
                return m;
        }
        throw new MaterialNoEncontradoException("No existe material: " + codigo);
    }

    // Prestar material
    public void prestarMaterial(String codigo) {
        MaterialBiblioteca m = buscarMaterial(codigo);
        if (m.isPrestado())
            throw new MaterialNoDisponibleException("Material ya prestado");
        m.prestar();
    }

    // Devolver material y calcular multa
    public double devolverMaterial(String codigo, int diasRetraso) {
        if (diasRetraso < 0)
            throw new IllegalArgumentException("Días de retraso inválidos");

        MaterialBiblioteca m = buscarMaterial(codigo);
        double multa = m.calcularMultaPorRetraso(diasRetraso);
        m.devolver();
        return multa;
    }

    // Listar disponibles
    public void listarMaterialesDisponibles() {
        for (MaterialBiblioteca m : catalogo) {
            if (!m.isPrestado())
                System.out.println(m);
        }
    }

    // Bubble sort por año
    public void ordenarPorAnio() {
        for (int i = 0; i < catalogo.size() - 1; i++) {
            for (int j = 0; j < catalogo.size() - i - 1; j++) {
                if (catalogo.get(j).getAnioPublicacion() > catalogo.get(j + 1).getAnioPublicacion()) {
                    MaterialBiblioteca aux = catalogo.get(j);
                    catalogo.set(j, catalogo.get(j + 1));
                    catalogo.set(j + 1, aux);
                }
            }
        }
    }
}
