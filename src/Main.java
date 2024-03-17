import java.util.Random;
import java.util.Scanner;

public class JuegoRoles {
    public static void main(String[] args) {
        String[] jugadores = {"Jugador 1", "Jugador 2", "Jugador 3", "Jugador 4",
                             "Jugador 5", "Jugador 6", "Jugador 7", "Jugador 8"};

        String[] roles = {"Lobo", "Lobo", "Aldeano", "Vidente", "Protector", "Abuelo", "Perro-lobo", "Cazador"};

        asignarRoles(jugadores, roles);

        System.out.println("Roles asignados:");
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println(jugadores[i] + ": " + roles[i]);
        }

        boolean lobosVivos = true;
        boolean aldeanosVivos = true;
        boolean perroLoboSeleccionado = false;

        Scanner scanner = new Scanner(System.in);

        while (lobosVivos && aldeanosVivos) {
            System.out.println("\n--- Ronda de día ---");
            // Lógica para el día

            System.out.println("\n--- Ronda de noche ---");
            // Lógica para la noche
            if (!perroLoboSeleccionado) {
                // Seleccionar el rol del Perro-lobo solo en la primera noche
                System.out.println("Perro-lobo, ¿deseas ser lobo o aldeano? (lobo/aldeano)");
                String decision = scanner.next();
                if (decision.equalsIgnoreCase("lobo")) {
                    roles[6] = "Lobo";
                } else {
                    roles[6] = "Aldeano";
                }
                perroLoboSeleccionado = true;
            } else {
                // Lógica para los roles restantes en todas las noches excepto la primera
                // Protector, Vidente y Lobos
                System.out.println("Protector, selecciona a qué jugador deseas proteger:");
                String jugadorProtegido = scanner.next();
                System.out.println("El protector ha protegido a " + jugadorProtegido);

                System.out.println("Lobos, discutan a qué jugador desean eliminar:");
                String jugadorEliminado = scanner.next();
                System.out.println("Los lobos han decidido eliminar a " + jugadorEliminado);

                System.out.println("Vidente, ¿a qué jugador deseas investigar su rol?");
                String jugadorInvestigado = scanner.next();
                String rolInvestigado = roles[obtenerIndiceJugador(jugadores, jugadorInvestigado)];
                System.out.println("El rol de " + jugadorInvestigado + " es: " + rolInvestigado);
            }

            // Simulando el fin de la ronda
            System.out.println("¿Continuar? (s/n)");
            String continuar = scanner.next();
            if (continuar.equalsIgnoreCase("n")) {
                break;
            }
        }

        // Determinar el ganador
        if (lobosVivos) {
            System.out.println("¡Los lobos ganaron!");
        } else if (aldeanosVivos) {
            System.out.println("¡Los aldeanos ganaron!");
        } else {
            System.out.println("¡Es un empate!");
        }
    }

    public static void asignarRoles(String[] jugadores, String[] roles) {
        Random random = new Random();
        for (int i = 0; i < jugadores.length; i++) {
            int indice = random.nextInt(jugadores.length);
            // Intercambiamos los roles en los índices i e indice
            String temp = roles[i];
            roles[i] = roles[indice];
            roles[indice] = temp;
        }
    }
   
    public static int obtenerIndiceJugador(String[] jugadores, String jugador) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].equals(jugador)) {
                return i;
            }
        }
        return -1;
    }
}
