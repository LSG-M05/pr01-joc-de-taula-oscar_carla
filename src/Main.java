import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Enum para representar los roles de los jugadores
enum Rol {
    CAZADOR, VIDENTE, PROTECTOR, ANCIANO, ALDEANO, HOMBRE_LOBO, PERRO_LOBO
}

// Clase principal del juego
public class Main {
    private static class Jugador {
        private final String nombre;
        private Rol rol;
        private boolean vivo;

        public Jugador(String nombre, Rol rol) {
            this.nombre = nombre;
            this.rol = rol;
            this.vivo = true;
        }

        public String getNombre() {
            return nombre;
        }

        public Rol getRol() {
            return rol;
        }

        public void setRol(Rol rol) {
            this.rol = rol;
        }

        public boolean estaVivo() {
            return vivo;
        }

        public void matar() {
            vivo = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Jugador1", Rol.CAZADOR));
        jugadores.add(new Jugador("Jugador2", Rol.VIDENTE));
        jugadores.add(new Jugador("Jugador3", Rol.PROTECTOR));
        jugadores.add(new Jugador("Jugador4", Rol.ANCIANO));
        jugadores.add(new Jugador("Jugador5", Rol.ALDEANO));
        jugadores.add(new Jugador("Jugador6", Rol.HOMBRE_LOBO));
        jugadores.add(new Jugador("Jugador7", Rol.HOMBRE_LOBO));
        jugadores.add(new Jugador("Jugador8", Rol.PERRO_LOBO));

        System.out.println("Comienza el juego de Hombres lobo de Castronegro");
        System.out.println("Asignando roles a los jugadores...");

        // Resolver los roles
        ArrayList<Rol> roles = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            roles.add(jugador.getRol());
        }
        Collections.shuffle(roles);

        // Asignar roles a los jugadores
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).setRol(roles.get(i));
            System.out.println(jugadores.get(i).getNombre() + " es " + roles.get(i));
        }

        // Comenzar la partida
        boolean juegoActivo = true;
        boolean primeraNoche = true;

        while (juegoActivo) {
            boolean protectorActuado = false;
            boolean videnteActuado = false;
            boolean hombresLoboActuado = false;

            // Noche
            System.out.println("\nComienza la noche...");
            for (Jugador jugadorActual : jugadores) {
                if (jugadorActual.estaVivo()) {
                    if (primeraNoche) {
                        if (jugadorActual.getRol() == Rol.PERRO_LOBO) {
                            System.out.println("\nTurno de " + jugadorActual.getNombre() + " (" + jugadorActual.getRol() + ")");
                            System.out.println("¿Deseas ser aldeano o lobo? (A/L): ");
                            String decision = scanner.nextLine();
                            if (decision.equalsIgnoreCase("A")) {
                                System.out.println(jugadorActual.getNombre() + " ha decidido ser aldeano.");
                                jugadorActual.setRol(Rol.ALDEANO);
                            } else {
                                System.out.println(jugadorActual.getNombre() + " ha decidido ser lobo.");
                                jugadorActual.setRol(Rol.HOMBRE_LOBO);
                            }
                        }
                    } else {
                        if (jugadorActual.getRol() == Rol.PROTECTOR && !protectorActuado) {
                            System.out.println("\nTurno de " + jugadorActual.getNombre() + " (" + jugadorActual.getRol() + ")");
                            System.out.println("¿A quién quieres proteger esta noche?");
                            String protegido = scanner.nextLine();
                            for (Jugador protegidoJugador : jugadores) {
                                if (protegidoJugador.getNombre().equalsIgnoreCase(protegido)) {
                                    System.out.println(protegidoJugador.getNombre() + " está protegido esta noche.");
                                    protectorActuado = true;
                                    break;
                                }
                            }
                        } else if (jugadorActual.getRol() == Rol.VIDENTE && !videnteActuado) {
                            System.out.println("\nTurno de " + jugadorActual.getNombre() + " (" + jugadorActual.getRol() + ")");
                            System.out.println("Escribe el nombre del jugador cuyo rol quieres ver: ");
                            String nombreJugador = scanner.nextLine();
                            for (Jugador objetivoJugador : jugadores) {
                                if (objetivoJugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                                    System.out.println(objetivoJugador.getNombre() + " es " + objetivoJugador.getRol());
                                    videnteActuado = true;
                                    break;
                                }
                            }
                        } else if ((jugadorActual.getRol() == Rol.HOMBRE_LOBO || jugadorActual.getRol() == Rol.PERRO_LOBO) && !hombresLoboActuado) {
                            System.out.println("\nTurno de los Hombres Lobo");
                            System.out.println("¿A quién quieres matar?");
                            String objetivo = scanner.nextLine();
                            Jugador objetivoJugador = null;
                            for (Jugador jugador : jugadores) {
                                if (jugador.getNombre().equalsIgnoreCase(objetivo)) {
                                    objetivoJugador = jugador;
                                    break;
                                }
                            }
                            if (objetivoJugador == null) {
                                System.out.println("No se puede atacar a esa persona.");
                            } else if (!objetivoJugador.estaVivo()) {
                                System.out.println("No se puede atacar a un jugador muerto.");
                            } else {
                                objetivoJugador.matar();
                                System.out.println(objetivoJugador.getNombre() + " ha sido eliminado.");
                                hombresLoboActuado = true;
                            }
                        }
                    }
                } else {
                    System.out.println(jugadorActual.getNombre() + " está muerto y no puede actuar.");
                }
            }

            // Verificar condiciones de fin de juego
            boolean hombresLoboVivos = false;
            boolean otrosVivos = false;
            for (Jugador jugador : jugadores) {
                if (jugador.estaVivo()) {
                    if (jugador.getRol() == Rol.HOMBRE_LOBO || jugador.getRol() == Rol.PERRO_LOBO) {
                        hombresLoboVivos = true;
                    } else {
                        otrosVivos = true;
                    }
                }
            }

            if (!hombresLoboVivos) {
                System.out.println("¡Los aldeanos han ganado! Todos los hombres lobo han sido eliminados.");
                juegoActivo = false;
            } else if (!otrosVivos) {
                System.out.println("¡Los hombres lobo han ganado! No quedan aldeanos vivos.");
                juegoActivo = false;
            } else {
                // Fin de la noche
                System.out.println("\nFin de la noche\n");
                System.out.println("Resumen de la noche:");
                for (Jugador jugador : jugadores) {
                    if (!jugador.estaVivo()) {
                        System.out.println(jugador.getNombre() + " ha muerto. Su rol era " + jugador.getRol());
                    }
                }
                System.out.println("\nComienza el día...");
            }

            // Cambiar a noche siguiente
            primeraNoche = false;
        }

        scanner.close();
    }
}
