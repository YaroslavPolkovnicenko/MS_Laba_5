package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        Service service = new Service(20, 1);

        service.ServiceRequests();
        service.ServiceReguestsFromQueue();

        System.out.println("Общее время ожидания = " + df.format(service.getTime_oz()) + "; среднее время ожидания = " + df.format(service.SrTimeOz()));
        System.out.println("Общеая длина очереди = " + df.format(service.getLenght_ochered()) + "; средняя длина очереди = " + df.format(service.SrLenOch()));
    }
}
