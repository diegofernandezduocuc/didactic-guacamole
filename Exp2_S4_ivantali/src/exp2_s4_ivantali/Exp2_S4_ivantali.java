package exp2_s4_ivantali;

import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Exp2_S4_ivantali {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        String di = "[" + '\u2591' + "]"; // disponible ░
        String oc = "[" + '\u2588' + "]"; // ocupado █
        int asientos = 20;
        int num = asientos * 4;
        // Asientos independientes por zona
        String[] palcoA = new String[asientos];
        String[] palcoB = new String[asientos];
        String[] plateaAlta = new String[asientos];
        String[] plateaBaja = new String[asientos];
        
        for (int i = 0; i < asientos; i++) {
            palcoA[i] = di;
            palcoB[i] = di;
            plateaAlta[i] = di;
            plateaBaja[i] = di;
        }
        // Voucher acumulativo
        String[] zonasCompradas = new String[num];
        int[] asientosComprados = new int[num];
        double[] preciosFinales = new double[num];
        int[] descuentosAplicados = new int[num];
        int totalCompras = 0;
        for (;;) { 
            System.out.println("-== MENÚ PRINCIPAL ==-");
            System.out.println("1). Comprar entrada");
            System.out.println("2). Salir");
            int opcion = 0;
            while (opcion < 1 || opcion > 2) {
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
            }
            if (opcion == 1) {
                int zona = 0;
                while (zona < 1 || zona > 4) {
                    System.out.println("\n-== PLANO DEL TEATRO ==-");
                    System.out.print("Palco A\t\t");
                    for (String s : palcoA) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    System.out.print("Palco B\t\t");
                    for (String s : palcoB) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    System.out.print("Platea Alta\t");
                    for (String s : plateaAlta) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    System.out.print("Platea Baja\t");
                    for (String s : plateaBaja) {
                        System.out.print(s + " ");
                    }                    
                    System.out.println();
                    System.out.println("\nZonas disponibles:");
                    System.out.println("1. Palco A \t($18.000)");
                    System.out.println("2. Palco B \t($15.000)");
                    System.out.println("3. Platea Alta \t($10.000)");
                    System.out.println("4. Platea Baja \t($12.000)");
                    System.out.print("Seleccione la zona (1 a 4): ");
                    zona = scanner.nextInt();
                    scanner.nextLine();
                }
                String[] zonaSeleccionada;
                int precioBase = 0;
                String nombreZona = "";
                switch (zona) {
                    case 1 -> {
                        zonaSeleccionada = palcoA;
                        precioBase = 18000;
                        nombreZona = "Palco A";
                    }
                    case 2 -> {
                        zonaSeleccionada = palcoB;
                        precioBase = 15000;
                        nombreZona = "Palco B";
                    }
                    case 3 -> {
                        zonaSeleccionada = plateaAlta;
                        precioBase = 10000;
                        nombreZona = "Platea Alta";
                    }
                    case 4 -> {
                        zonaSeleccionada = plateaBaja;
                        precioBase = 12000;
                        nombreZona = "Platea Baja";
                    }                    
                    default -> {
                        System.out.println("Zona inválida.");
                        continue;
                    }
                }
                System.out.print("Seleccione el asiento (1 a " + asientos + "): ");
                int asiento = scanner.nextInt();
                scanner.nextLine();
                if (asiento < 1 || asiento > asientos ) {
                    System.out.println("Asiento inválido.");
                    continue;
                }
                if (zonaSeleccionada[asiento - 1].equals(oc)) {
                    System.out.println("Ese asiento ya esta ocupado.");
                    continue;
                }
                // Marcar como ocupado
                zonaSeleccionada[asiento - 1] = oc;
                int esEstudiante = 0;
                while (esEstudiante < 1 || esEstudiante > 2) {
                    System.out.print("¿Es estudiante? (1 = Sí / 2 = No): ");
                    esEstudiante = scanner.nextInt();
                    scanner.nextLine();
                }
                double descuento = 0;
                int edad = 0;
                while (edad < 1 || edad > 120) {
                    System.out.print("Ingrese su edad: ");
                    edad = scanner.nextInt();
                    scanner.nextLine();
                    if (edad < 1 || edad > 120) {
                        System.out.println("Edad inválida.");
                        continue;
                    } else if (edad >= 60) {
                        descuento = 0.15;
                        System.out.println("Descuento tercera edad aplicado (15%)");
                    } else if (esEstudiante == 1) {
                        descuento = 0.10;
                        System.out.println("Descuento estudiante aplicado (10%)");
                    } else {
                        System.out.println("Sin descuento aplicado.");
                    }
                    double precioFinal = precioBase - (precioBase * descuento);
                    // Guardar datos en voucher
                    zonasCompradas[totalCompras] = nombreZona;
                    asientosComprados[totalCompras] = asiento;
                    preciosFinales[totalCompras] = precioFinal;
                    descuentosAplicados[totalCompras] = (int) (descuento * 100);
                    totalCompras++;

                    // Mostrar resumen
                    System.out.println("\n=== RESUMEN DE COMPRA ===");
                    System.out.println("Zona: " + nombreZona);
                    System.out.println("Asiento: " + asiento);
                    System.out.println("Precio base: $" + precioBase);
                    System.out.println("Descuento: " + (int) (descuento * 100) + "%");
                    System.out.printf("Total a pagar: $%.0f\n", precioFinal);
                }
                System.out.print("\n¿Desea comprar otra entrada? (1 = Sí / 2 = No): ");
                int seguir = scanner.nextInt();
                scanner.nextLine();
                if (seguir != 1) {
                    break;
                }
            } else {
                break;
            }
        }
        // Mostrar voucher acumulado
        System.out.println("\n=== VOUCHER FINAL ===");
        double totalGeneral = 0;
        for (int i = 0; i < totalCompras; i++) {
            System.out.println("Entrada " + (i + 1));
            System.out.println("Zona: " + zonasCompradas[i]);
            System.out.println("Asiento: " + asientosComprados[i]);
            System.out.println("Descuento aplicado: " + descuentosAplicados[i] + "%");
            System.out.printf("Total pagado: $%.0f\n", preciosFinales[i]);
            System.out.println();
            totalGeneral += preciosFinales[i];
        }
        System.out.printf("Total pagado: $%.0f\n", + totalGeneral);
        System.out.println("Gracias por usar el sistema.");
    }
}
