public class Main {
    public static void main(String[] args) {

        // Titutlo vacío
        try {
            new Libro("LIB-111", "", "Autor", 2020, 100, "Planeta", false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Año inválido
        try {
            new Revista("REV-222", "Historia", "Autor", 900, 2, "Enero", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Biblioteca b = new Biblioteca("Central");

        // Código inválido
        try {
            b.agregarMaterial(new DVD("XX-999", "Pelicula", "Director", 2015, 120, "Acción", true));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Agregar 8 materiales válidos
        try {
            b.agregarMaterial(new Libro(Biblioteca.generarCodigoAleatorio("LIB"), "Java Básico", "Juan", 2018, 300, "Alfa", false));
            b.agregarMaterial(new Libro(Biblioteca.generarCodigoAleatorio("LIB"), "POO", "Ana", 2020, 250, "Beta", true));

            b.agregarMaterial(new Revista(Biblioteca.generarCodigoAleatorio("REV"), "Ciencia Hoy", "Luis", 2021, 12, "Marzo", false));
            b.agregarMaterial(new Revista(Biblioteca.generarCodigoAleatorio("REV"), "NeuroTech", "Laura", 2023, 5, "Agosto", true));

            b.agregarMaterial(new DVD(Biblioteca.generarCodigoAleatorio("DVD"), "Matrix", "Wachowski", 1999, 130, "Sci-Fi", true));
            b.agregarMaterial(new DVD(Biblioteca.generarCodigoAleatorio("DVD"), "Inception", "Nolan", 2010, 148, "Sci-Fi", true));

            b.agregarMaterial(new Libro(Biblioteca.generarCodigoAleatorio("LIB"), "Redes", "Carlos", 2016, 280, "Alpha", false));
            b.agregarMaterial(new Revista(Biblioteca.generarCodigoAleatorio("REV"), "Tecnología MX", "Roberto", 2022, 8, "Mayo", false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 4 préstamos exitosos
        MaterialBiblioteca m1 = b.buscarMaterial(bibliotecaCodigo(b, 0));
        MaterialBiblioteca m2 = b.buscarMaterial(bibliotecaCodigo(b, 1));
        MaterialBiblioteca m3 = b.buscarMaterial(bibliotecaCodigo(b, 2));
        MaterialBiblioteca m4 = b.buscarMaterial(bibliotecaCodigo(b, 3));

        b.prestarMaterial(m1.getCodigo());
        b.prestarMaterial(m2.getCodigo());
        b.prestarMaterial(m3.getCodigo());
        b.prestarMaterial(m4.getCodigo());

        // Préstamo de material ya prestado
        try {
            b.prestarMaterial(m1.getCodigo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Buscar material inexistente
        try {
            b.buscarMaterial("LIB-999");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Devolver 2 materiales con retraso
        System.out.println("Multa 1: $" + b.devolverMaterial(m1.getCodigo(), 3));
        System.out.println("Multa 2: $" + b.devolverMaterial(m2.getCodigo(), 10));

        // Disponibles
        System.out.println("\nMateriales disponibles:");
        b.listarMaterialesDisponibles();

        // Ordenar por año
        System.out.println("\nOrdenados por año:");
        b.ordenarPorAnio();
        b.listarMaterialesDisponibles();
    }

    private static String bibliotecaCodigo(Biblioteca b, int index) {
        return b.catalogo.get(index).getCodigo();
    }
}
