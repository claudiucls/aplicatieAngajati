package com.cls.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cls.entities.Angajat;

public class Sarbatori {
//	@Autowired
//	private ConcediuRepository cr;
	
	public static List<LocalDate> sar2016 = Arrays.asList(LocalDate.of(2016, Month.JANUARY, 1),
			LocalDate.of(2016, Month.JANUARY, 2), LocalDate.of(2016, Month.JANUARY, 24),
			LocalDate.of(2016, Month.MAY, 1), LocalDate.of(2016, Month.MAY, 2), LocalDate.of(2016, Month.JUNE, 19),
			LocalDate.of(2016, Month.JUNE, 20), LocalDate.of(2016, Month.AUGUST, 15),
			LocalDate.of(2016, Month.NOVEMBER, 30), LocalDate.of(2016, Month.DECEMBER, 1),
			LocalDate.of(2016, Month.DECEMBER, 25), LocalDate.of(2016, Month.DECEMBER, 26));

	public static boolean esteSarbatoare(LocalDate d) {
		if (sar2016.contains(d)) {
			return true;
		} else
			return false;
	}

	public static int zileLucratoareInLuna(LocalDate date) {
		int days = date.lengthOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		int no = 0;
		for (int d = 1; d <= days; d++) {
			LocalDate dTest = LocalDate.of(year, month, d);
			if (dTest.getDayOfWeek() == DayOfWeek.SUNDAY || dTest.getDayOfWeek() == DayOfWeek.SATURDAY
					|| Sarbatori.sar2016.contains(dTest)) {
				no += 1;
			}
		}
		return days - no;
	}

	public int zileLucrateDeAngajatPeLuna(Angajat a, LocalDate date) {
		//List<Concediu> evs = cr.getConcediiByAngajat();
		int zile = 0;
		/*if (!evs.isEmpty()) {
			for (Event e : evs) {
				LocalDate d = e.getDataDeLa();
				LocalDate p = e.getDataPanaLa();
				if (d.getMonth() != date.getMonth() && p.getMonth() != date.getMonth()) {
				} else if (d.getMonth() == date.getMonth() && p.getMonth() != date.getMonth()) {
					int i = d.getDayOfMonth();
					while (i <= date.lengthOfMonth()) {
						if (!(d.getDayOfWeek() == DayOfWeek.SUNDAY || d.getDayOfWeek() == DayOfWeek.SATURDAY)) {
							zile++;
						}
						i++;
						d = d.plusDays(1);
					}
				} else if (d.getMonth() != date.getMonth() && p.getMonth() == date.getMonth()) {
					int i = 1;
					LocalDate x = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
					while (i <= p.getDayOfMonth()) {
						if (!(x.getDayOfWeek() == DayOfWeek.SUNDAY || x.getDayOfWeek() == DayOfWeek.SATURDAY)) {
							zile++;
						}
						i++;
						x = x.plusDays(1);

					}
				} else if (d.getMonth() == date.getMonth() && p.getMonth() == date.getMonth()) {
					int i = p.getDayOfMonth();
					int j = d.getDayOfMonth();
					while (j <= i) {
						if (!(d.getDayOfWeek() == DayOfWeek.SUNDAY || d.getDayOfWeek() == DayOfWeek.SATURDAY)) {
							zile++;
						}
						j++;
						d = d.plusDays(1);
					}
				}
			}
		}*/
		return zile;
	}
}
