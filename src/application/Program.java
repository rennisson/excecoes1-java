package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reserva;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Número do quarto: ");
		int numeroQuarto = sc.nextInt();
		System.out.print("Data de check-in (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.nextLine());
		System.out.println("Data de check-out (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.nextLine());
		
		if (!checkOut.after(checkIn)) {
			System.out.println("Erro na reserva: Data de check-out deve ser posterior à data de check-in");
		}
		else {
			Reserva reserva = new Reserva(numeroQuarto, checkIn, checkOut);
			System.out.println("Reserva: " + reserva);

			System.out.println();
			System.out.println("Entre com as datas para atualizar a reserva:");
			System.out.print("Data de check-in (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Data de check-out (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			String erro = reserva.atualizarDatas(checkIn, checkOut);
			if (erro != null) {
				System.out.println("Reserva: " + erro);
			}
			else {
				reserva.atualizarDatas(checkIn, checkOut);
				System.out.println("Reserva: " + reserva);
			}
		}

		sc.close();
	}
}
