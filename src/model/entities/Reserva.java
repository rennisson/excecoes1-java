package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reserva {
	
	private Integer numeroQuarto;
	private Date checkIn;
	private Date checkOut;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reserva(Integer numeroQuarto, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Data de check-out deve ser posterior à data de check-in");
		}
		this.numeroQuarto = numeroQuarto;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(Integer numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duracao() {
		long dif = this.checkOut.getTime() - this.checkIn.getTime();
		return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS);
	}
	
	public String atualizarDatas(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("As datas da reserva devem ser datas futuras");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Data de check-out deve ser posterior à data de check-in");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}

	@Override
	public String toString() {
		return "Quarto: "
				+ numeroQuarto
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: " + sdf.format(checkOut)
				+ duracao()
				+ " noites";
	}
	
}
