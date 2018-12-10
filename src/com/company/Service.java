package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Service {

    private double MO_exponential;
    private double MO_uniform;
    private double SKO_uniform;

    private double a;
    private double b;
    private double ksi;

    private double time_obs;
    private double time_pr;
    private double time_receipt;
    private double time_oz;
    private double time_start;
    private double current_time;

    private int kol_request;
    private int kol_channel;
    private int kol_el;
    private int lenght_ochered;
    private int schetchic;

    private ArrayList<Double> time_osv;
    private ArrayList<Double> ochered_post;
    private ArrayList<Integer> number_zayavki;

    public double getMO_exponential() {
        return MO_exponential;
    }

    public void setMO_exponential(double MO_exponential) {
        this.MO_exponential = MO_exponential;
    }

    public double getMO_uniform() {
        return MO_uniform;
    }

    public void setMO_uniform(double MO_uniform) {
        this.MO_uniform = MO_uniform;
    }

    public double getSKO_uniform() {
        return SKO_uniform;
    }

    public void setSKO_uniform(double SKO_uniform) {
        this.SKO_uniform = SKO_uniform;
    }

    public double getKsi() {
        return ksi;
    }

    public void setKsi(double ksi) {
        this.ksi = ksi;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getTime_obs() {
        return time_obs;
    }

    public void setTime_obs(double time_obs) {
        this.time_obs = time_obs;
    }

    public double getTime_pr() {
        return time_pr;
    }

    public void setTime_pr(double time_pr) {
        this.time_pr = time_pr;
    }

    public double getTime_oz() {
        return time_oz;
    }

    public void setTime_oz(double time_oz) {
        this.time_oz = time_oz;
    }

    public double getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(double current_time) {
        this.current_time = current_time;
    }

    public double getTime_receipt() {
        return time_receipt;
    }

    public void setTime_receipt(double time_receipt) {
        this.time_receipt = time_receipt;
    }

    public double getTime_start() {
        return time_start;
    }

    public void setTime_start(double time_start) {
        this.time_start = time_start;
    }

    public ArrayList<Double> getTime_osv() {
        return time_osv;
    }

    public void setTime_osv(ArrayList<Double> time_osv) {
        this.time_osv = time_osv;
    }

    public int getKol_request() {
        return kol_request;
    }

    public void setKol_request(int kol_request) {
        this.kol_request = kol_request;
    }

    public int getKol_channel() {
        return kol_channel;
    }

    public void setKol_channel(int kol_channel) {
        this.kol_channel = kol_channel;
    }

    public int getKol_el() {
        return kol_el;
    }

    public void setKol_el(int kol_el) {
        this.kol_el = kol_el;
    }

    public int getLenght_ochered() {
        return lenght_ochered;
    }

    public void setLenght_ochered(int lenght_ochered) {
        this.lenght_ochered = lenght_ochered;
    }

    public int getSchetchic() {
        return schetchic;
    }

    public void setSchetchic(int schetchic) {
        this.schetchic = schetchic;
    }

    public ArrayList<Double> getOchered_post() {
        return ochered_post;
    }

    public void setOchered_post(ArrayList<Double> ochered_post) {
        this.ochered_post = ochered_post;
    }

    public ArrayList<Integer> getNumber_zayavki() {
        return number_zayavki;
    }

    public void setNumber_zayavki(ArrayList<Integer> number_zayavki) {
        this.number_zayavki = number_zayavki;
    }

    public Service(int kol_request, int kol_channel) {
        this.MO_exponential = 5.0;
        this.MO_uniform = 5.0;
        this.SKO_uniform = 0.5;

        this.a = MO_uniform - SKO_uniform;
        this.b = MO_uniform + SKO_uniform;
        this.ksi = 0.0;

        this.time_pr = 0.0;
        this.time_oz = 0.0;
        this.time_start = 0.0;
        this.current_time = 0.0;

        this.kol_request = kol_request;
        this.kol_channel = kol_channel;
        this.kol_el = 0;
        this.lenght_ochered = 0;
        this.schetchic = 0;

        this.time_osv = new ArrayList<>();
        this.number_zayavki = new ArrayList<>();
        this.ochered_post = new ArrayList<>();

        time_osv.add(0.0);
    }

    public double GetKsi() {
        Random r = new Random();

        ksi = r.nextDouble();

        return ksi;
    }

    public double GetUniformY(){
        return a + ((b - a) * GetKsi());
    }

    public double GetExponentialY(){
        return -MO_exponential * Math.log10(GetKsi());
    }

    public void ServiceRequests(){

        int l = 0;

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        for(int i = 0; i < kol_request; i++) {

            l++;

            time_receipt = GetExponentialY();
            current_time += time_receipt;

            if(ochered_post.size() == 0) {
                if (time_osv.get(0) <= current_time) {

                    time_obs = GetUniformY();
                    time_start = current_time;
                    time_osv.set(0, (current_time + time_obs));

                    System.out.println("ЗАЯВКА № " + l + " ОБСЛУЖИВАЕТСЯ! Время поступления заявки = " + df.format(current_time) +
                            "; время начала обслуживания = " + df.format(time_start) +
                            "; время обслуживания = " + df.format(time_obs) +
                            "; время освобождения канала = " + df.format(time_osv.get(0)));

                } else {
                    ochered_post.add(current_time);
                    number_zayavki.add(l);

                    System.out.println("Заявка № " + l + " была добавлена в очередь. Она находится " + ochered_post.size() + " в очереди. " +
                            "Время поступления заявки = " + df.format(current_time) +
                            "; время освобождения канала = " + df.format(time_osv.get(0)));
                    kol_el = ochered_post.size();
                    lenght_ochered += kol_el;
                    schetchic++;
                }
            }

            else if(ochered_post.size() > 0) {

                    ochered_post.add(current_time);
                    number_zayavki.add(l);

                    System.out.println("Заявка № " + l + " была добавлена в очередь. Она находится " + ochered_post.size() + " в очереди. " +
                            "Время поступления заявки = " + df.format(current_time) +
                            "; время освобождения канала = " + df.format(time_osv.get(0)));

                    kol_el = ochered_post.size();
                    lenght_ochered += kol_el;
                    schetchic++;

                    if (time_osv.get(0) <= current_time) {
                        time_obs = GetUniformY();

                        if(ochered_post.get(0) > time_osv.get(0)){
                            time_start = ochered_post.get(0);
                        }

                        else {
                            time_start = time_osv.get(0);
                        }

                        time_oz += (time_start - ochered_post.get(0));
                        time_osv.set(0, (time_start + time_obs));

                        System.out.println("Заявка № " + df.format(number_zayavki.get(0)) + " ОБСЛУЖИВАЕТСЯ" +
                                "! Время поступления заявки = " + df.format(ochered_post.get(0)) +
                                "; время начала обслуживания = " + df.format(time_start) +
                                "; время обслуживания = " + df.format(time_obs) +
                                "; время освобождения канала = " + df.format(time_osv.get(0)));

                        ochered_post.remove(0);
                        number_zayavki.remove(0);

                        kol_el = ochered_post.size();
                        lenght_ochered += kol_el;
                        schetchic++;
                    }

                    else continue;
            }
        }
    }

    public void ServiceReguestsFromQueue(){

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        while(ochered_post.size() != 0) {
            time_obs = GetUniformY();

            if(ochered_post.get(0) > time_osv.get(0)){
                time_start = ochered_post.get(0);
            }

            else {
                time_start = time_osv.get(0);
            }

            time_oz += (time_start - ochered_post.get(0));
            time_osv.set(0, (time_start + time_obs));

            System.out.println("Заявка № " + df.format(number_zayavki.get(0)) + " ОБСЛУЖИВАЕТСЯ" +
                    "! Время поступления заявки = " + df.format(ochered_post.get(0)) +
                    "; время начала обслуживания = " + df.format(time_start) +
                    "; время обслуживания = " + df.format(time_obs) +
                    "; время освобождения канала = " + df.format(time_osv.get(0)));

            ochered_post.remove(0);
            number_zayavki.remove(0);

            kol_el = ochered_post.size();
            lenght_ochered += kol_el;
            schetchic++;
        }
    }

    public double SrTimeOz(){
        return time_oz / kol_request;
    }

    public double SrLenOch(){
        return lenght_ochered / schetchic;
    }
}