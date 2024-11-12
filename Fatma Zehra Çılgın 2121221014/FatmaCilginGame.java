package tombala;

public class FatmaCilginGame<T> {

    public static <T> boolean CinkoKontrol(FatmaCilginMultiLinkedList<T> card, int row) {
        //parametre olarak girilen satırda dolaşır.
        int count = 0;
        for (int j = 0; j < 9; j++) {
            T data = (T) card.card[row][j].data;
            if (data != null && data.toString().startsWith("|") && data.toString().endsWith("|")) {
                count++;
            }
        }
        return count == 5; // Çinko için 5 işaretli hücre gerekir
    }

    public static <T> boolean Tombala(FatmaCilginMultiLinkedList<T> card) {
        // Tüm kartın üzerinde dolaşarak işaretlenmiş hücre sayısını hesapla
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                T data = (T) card.card[i][j].data;
                if (data != null && data.toString().startsWith("|") && data.toString().endsWith("|")) {
                    count++;
                }
            }
        }
        return count == 15; //tombala için 15 işaretli hücre gerekir
    }

}
