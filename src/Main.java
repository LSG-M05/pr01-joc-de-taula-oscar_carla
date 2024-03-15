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

        Scanner scanner = new Scanner(System.in);

        while (lobosVivos && aldeanosVivos) {
            System.out.println("\n--- Ronda de día ---");

            System.out.println("\n--- Ronda de noche ---");

            System.out.println("¿Continuar? (s/n)");
            String continuar = scanner.next();
            if (continuar.equalsIgnoreCase("n")) {
                break;
            }
        }

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
            String temp = roles[i];
            roles[i] = roles[indice];
            roles[indice] = temp;
        }
    }
}
