import java.util.ArrayList;
import java.util.Scanner;

interface BisaBertarung {
    void serang(Monster target);
    void serang(Monster target, String jurus);
}

interface BisaMenyembuhkan {
    void sembuhkan();
}

abstract class Monster {
    protected String nama;
    protected int kesehatan;
    protected int kekuatan;

    public Monster(String nama, int kesehatan, int kekuatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
    }

    public abstract void tampilkanInfo();

    public void terimaSerangan(int damage) {
        kesehatan -= damage;
        if (kesehatan < 0) kesehatan = 0;
        System.out.println(nama + " menerima serangan sebesar " + damage + " poin.");
    }

    public boolean masihHidup() {
        return kesehatan > 0;
    }
}

class MonsterApi extends Monster implements BisaBertarung {
    public MonsterApi(String nama, int kesehatan, int kekuatan) {
        super(nama, kesehatan, kekuatan);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(" Monster Api: " + nama + " | HP: " + kesehatan + " | ATK: " + kekuatan);
    }

    @Override
    public void serang(Monster target) {
        System.out.println(nama + " menyerang " + target.nama + "!");
        target.terimaSerangan(kekuatan);
    }

    @Override
    public void serang(Monster target, String jurus) {
        int damage = kekuatan + 20;
        System.out.println(nama + " menggunakan jurus " + jurus + " ke " + target.nama + "!");
        target.terimaSerangan(damage);
    }
}

class MonsterAir extends Monster implements BisaBertarung, BisaMenyembuhkan {
    public MonsterAir(String nama, int kesehatan, int kekuatan) {
        super(nama, kesehatan, kekuatan);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(" Monster Air: " + nama + " | HP: " + kesehatan + " | ATK: " + kekuatan);
    }

    @Override
    public void serang(Monster target) {
        System.out.println(nama + " menyerang " + target.nama + "!");
        target.terimaSerangan(kekuatan);
    }

    @Override
    public void serang(Monster target, String jurus) {
        int damage = kekuatan + 15;
        System.out.println(nama + " menggunakan jurus " + jurus + " ke " + target.nama + "!");
        target.terimaSerangan(damage);
    }

    @Override
    public void sembuhkan() {
        int heal = 30;
        kesehatan += heal;
        System.out.println(nama + " menyembuhkan diri sebanyak " + heal + " poin!");
    }
}

class MonsterListrik extends Monster implements BisaBertarung {
    public MonsterListrik(String nama, int kesehatan, int kekuatan) {
        super(nama, kesehatan, kekuatan);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(" Monster Listrik: " + nama + " | HP: " + kesehatan + " | ATK: " + kekuatan);
    }

    @Override
    public void serang(Monster target) {
        System.out.println(nama + " menyerang " + target.nama + "!");
        target.terimaSerangan(kekuatan);
    }

    @Override
    public void serang(Monster target, String jurus) {
        int damage = kekuatan + 25;
        System.out.println(nama + " menggunakan jurus " + jurus + " ke " + target.nama + "!");
        target.terimaSerangan(damage);
    }
}

public class ArenaPertarungan {
    public static void main(String[] args) {
        ArrayList<Monster> arena = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("=== Input Data Monster ===");

        System.out.print("== Nama Monster Api: ");
        String namaApi = input.nextLine();
        System.out.print("HP Monster Api: ");
        int hpApi = input.nextInt();
        System.out.print("ATK Monster Api: ");
        int atkApi = input.nextInt();
        input.nextLine(); 

        System.out.print("== Nama Monster Air: ");
        String namaAir = input.nextLine();
        System.out.print("HP Monster Air: ");
        int hpAir = input.nextInt();
        System.out.print("ATK Monster Air: ");
        int atkAir = input.nextInt();
        input.nextLine(); 

        System.out.print("== Nama Monster Listrik: ");
        String namaListrik = input.nextLine();
        System.out.print("HP Monster Listrik: ");
        int hpListrik = input.nextInt();
        System.out.print("ATK Monster Listrik: ");
        int atkListrik = input.nextInt();
        input.nextLine(); 

        MonsterApi api = new MonsterApi(namaApi, hpApi, atkApi);
        MonsterAir air = new MonsterAir(namaAir, hpAir, atkAir);
        MonsterListrik listrik = new MonsterListrik(namaListrik, hpListrik, atkListrik);

        arena.add(api);
        arena.add(air);
        arena.add(listrik);

        System.out.println("\n=== Daftar Monster di Arena ===");
        for (Monster m : arena) {
            m.tampilkanInfo();
        }

        System.out.println("\n=== Pertarungan Dimulai! ===");
        api.serang(air);
        air.serang(api, "Gelombang Air");
        listrik.serang(api);
        air.sembuhkan();
        api.serang(listrik, "Api Neraka");
        listrik.serang(air, "Petir Halilintar");

        System.out.println("\n=== Status Akhir Monster ===");
        for (Monster m : arena) {
            m.tampilkanInfo();
            System.out.println("Status: " + (m.masihHidup() ? "Hidup" : "Kalah"));
            System.out.println();
        }

        input.close();
    }
}
