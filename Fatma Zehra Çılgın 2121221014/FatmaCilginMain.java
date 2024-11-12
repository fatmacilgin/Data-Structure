package tombala;

import java.util.Random;

public class FatmaCilginMain {

    public static void main(String[] args) {

        boolean birinciCinko = false;
        boolean ikinciCinko = false;
        boolean ucuncuCinko = false;
        boolean birinciCinko2 = false;
        boolean ikinciCinko2 = false;
        boolean ucuncuCinko2 = false;
        FatmaCilginGame bingoGame = new FatmaCilginGame();
        FatmaCilginMultiLinkedList bingoCard1 = new FatmaCilginMultiLinkedList();
        Integer[][] card = {{5, -1, 22, -1, 45, -1, 60, 73, -1},
        {-1, 10, -1, 31, 47, 58, 68, -1, -1},
        {9, 17, 26, 38, -1, -1, -1, 79, 86}};

        bingoCard1.setCard(card);
        FatmaCilginMultiLinkedList bingoCard2 = new FatmaCilginMultiLinkedList();
        System.out.println("FATMA:");
        bingoCard1.printCard();
        System.out.println("ZEHRA:");
        bingoCard2.printCard();
        System.out.println("-----------------------------------------------------------------");
        int sayac1 = 0;
        int sayac2 = 0;

        while (!FatmaCilginGame.Tombala(bingoCard1)
                && !FatmaCilginGame.Tombala(bingoCard2)) {
            int[] randomPermutation = generatePermutation(90);
            for (int i = 0; i < randomPermutation.length; i++) {
                System.out.println("CEKILEN SAYI: " + randomPermutation[i] + "\n");
                // Her iki oyuncunun kartlarında çekilen sayıyı kontrol etme
                System.out.println("FATMANIN KARTI:");
                boolean marked1 = bingoCard1.markNumber(randomPermutation[i]);
                bingoCard1.printCard();

                System.out.println("ZEHRANIN KARTI:");
                boolean marked2 = bingoCard2.markNumber(randomPermutation[i]);
                bingoCard2.printCard();
                System.out.println("-----------------------------------------------------------------");

                if (FatmaCilginGame.CinkoKontrol(bingoCard1, 0) && !birinciCinko) {
                    birinciCinko = true;
                    if (birinciCinko) {
                        sayac1++;
                    }
                    System.out.println("Fatma 1. satirinda " + sayac1 + ". cinkosunu YAPTI!");
                }
                if (FatmaCilginGame.CinkoKontrol(bingoCard2, 0) && !birinciCinko2) {
                    birinciCinko2 = true;
                    if (birinciCinko2) {
                        sayac2++;
                    }
                    System.out.println("Zehra 1. satirinda " + sayac2 + ". cinkosunu YAPTI!");
                }
                if (FatmaCilginGame.CinkoKontrol(bingoCard1, 1) && !ikinciCinko) {
                    ikinciCinko = true;
                    if (ikinciCinko) {
                        sayac1++;
                    }
                    System.out.println("Fatma 2. satirinda " + sayac1 + ".cinkosunu YAPTI!");
                }
                if (FatmaCilginGame.CinkoKontrol(bingoCard2, 1) && !ikinciCinko2) {
                    ikinciCinko2 = true;
                    if (ikinciCinko2) {
                        sayac2++;
                    }
                    System.out.println("Zehra 2. satirinda " + sayac2 + ". cinkosunu YAPTI!");
                }
                if (FatmaCilginGame.CinkoKontrol(bingoCard1, 2) && !ucuncuCinko) {
                    ucuncuCinko = true;
                    if (ucuncuCinko) {
                        sayac1++;
                    }
                    System.out.println("Fatma 3. satirinda " + sayac1 + ". cinkosunu YAPTI!");
                }
                if (FatmaCilginGame.CinkoKontrol(bingoCard2, 2) && !ucuncuCinko2) {
                    ucuncuCinko2 = true;
                    if (ucuncuCinko2) {
                        sayac2++;
                    }
                    System.out.println("Zehra 3. satirinda " + sayac2 + ". cinkosunu YAPTI!");
                }

                if (FatmaCilginGame.Tombala(bingoCard1)) {
                    System.out.println("Fatma KAZANDI.");
                    break;
                }
                if (FatmaCilginGame.Tombala(bingoCard2)) {
                    System.out.println("Zehra KAZANDI.");
                    break;
                }
            }
        }
    }

    public static int[] generatePermutation(int number) {
        // Permütasyon dizisi oluştur
        int[] permutation = new int[number];
        for (int i = 0; i < number; i++) {
            permutation[i] = i + 1;
        }
        // Diziyi karıştır
        Random rand = new Random();
        for (int i = 0; i < number - 1; i++) {
            int j = rand.nextInt(number - i) + i;
            // Değerleri swap et
            int temp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = temp;
        }
        return permutation;
    }

}
