package co.edu.uniremington.model;

/** Open/Close
 *Si necesitamos un nuevo estado lo podedmos agregar, sin necesidad de modificar la lógica ´rincipales del  Enum.
 */

public enum StateBook {
    Available, //Disponible
    Borrowed, //Prestado
    Reserved //Reservado
}