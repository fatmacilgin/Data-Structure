package tombala;

import java.util.Random;

public class FatmaCilginMultiLinkedList<T> {

    FatmaCilginNode[][] card;
    FatmaCilginNode usedNumbers; // Daha önce kullanılmış sayıların linked listi

    public FatmaCilginMultiLinkedList() {
        card = new FatmaCilginNode[3][9];
        usedNumbers = null;
        generateCard();
    }
//kartı oluşturan method.
  private void generateCard() {
        Random rand = new Random();
        FatmaCilginNode[] prevColumn = new FatmaCilginNode[9]; // Önceki sütundaki düğümler
        for (int i = 0; i < 3; i++) { // Satırlar için döngü
            int[] selectedColumns = new int[5]; // Her satırda 5 sütun seçilecek
            int count = 0;
            // Farklı 5 sütun seç
            while (count < 5) {
                int column = rand.nextInt(9); // Rastgele bir sütun seç
                if (!contains(selectedColumns, column)) {
                    selectedColumns[count] = column;
                    count++;
                }
            }
            // Sütunlarda rastgele sayılar eklenmesi
            for (int j = 0; j < 9; j++) {
                FatmaCilginNode newNode;
                if (contains(selectedColumns, j)) {
                    int start = j * 10; // Sütunun başlangıç değeri
                    int end = (j + 1) * 10 - 1; // Sütunun bitiş değeri
                    int randomNumber;
                    do {
                        randomNumber = rand.nextInt(end - start + 1) + start;
                    } while (existsInMultiLinkedList(usedNumbers, randomNumber)); // Daha önce kullanılmamış bir sayıyı seçene kadar devam et

                    newNode = new FatmaCilginNode(randomNumber);
                    // Kullanılan sayılar listesine yeni sayıyı ekle
                    usedNumbers = addLast(usedNumbers, randomNumber);
                } else {
                    newNode = new FatmaCilginNode(-1); //sayı seçilmeyen sütunlar için başlangıç düğümü
                }
                // Multi linked list yapısına göre bağlantıları kur
                if (i > 0) { 
                    prevColumn[j].nextRow = newNode;
                }
                if (j > 0) { 
                    card[i][j - 1].nextColumn = newNode;
                }
                // Düğümü kart matrisine ekle
                card[i][j] = newNode;
                prevColumn[j] = newNode;
            }
        }
    }

    private boolean contains(int[] array, int key) {
        for (int value : array) {
            if (value == key) {
                return true;
            }
        }
        return false;
    }

    private boolean existsInMultiLinkedList(FatmaCilginNode Head, int key) {
        FatmaCilginNode current = Head;
        while (current != null) {
            if (current.data.equals(key)) {
                return true;
            }
            current = current.nextRow;
        }
        return false;
    }

    private FatmaCilginNode addLast(FatmaCilginNode head, int data) {
        FatmaCilginNode newNode = new FatmaCilginNode(data);
        if (head == null) {
            return newNode;
        }
        FatmaCilginNode current = head;
        while (current.nextRow != null) {
            current = current.nextRow;
        }
        current.nextRow = newNode;
        return head;
    }

    public boolean markNumber(int drawnNumber) {
        boolean marked = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (card[i][j].data.equals(drawnNumber)) {
                    card[i][j].data = "|" + card[i][j].data + "|";
                    marked = true;
                }
            }
        }
        return marked;
    }

    private boolean checkColumnRange(T[][] matrix, int columnIndex, int start, int end) {
        for (int i = 0; i < matrix.length; i++) {
            T currentNumber = matrix[i][columnIndex];
            if (!currentNumber.equals(-1)) {
                int number = (int) currentNumber;
                if (number < start || number > end) {
                    System.out.println("Hatali giris: " + (columnIndex + 1) + ". sutun " + (start) + "-" + (end - 1) + 
                            " arasi sayilar olmalidir!\nrastgele kart olusturuldu.");
                    return false;
                }
            }
        }
        return true;
    }

    public void setCard(T[][] manuallyEnteredCard) {
        if (manuallyEnteredCard.length != 3 || manuallyEnteredCard[0].length != 9) {
            System.out.println("Hatali giris: Girmis oldugunuz matris boyutları dogru degil!\nRastgele kart olusturuldu.");
            return;
        } else {
            for (int i = 0; i < 9; i++) {
                if (!checkColumnRange(manuallyEnteredCard, i, (i * 10), (i + 1) * 10)) {
                    return;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                // Matristeki değerleri kart matrisine kopyala
                card[i][j] = new FatmaCilginNode(manuallyEnteredCard[i][j]);
            }
        }
    }

    public void printCard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                T data = (T) card[i][j].data;
                if (data != null && data.equals(-1)) {
                    System.out.print("x\t"); // -1 olan hücreler için 'x' yazdır
                } else {
                    System.out.print(data + "\t"); // Diğer hücreler için veriyi yazdır
                }
            }
            System.out.println();
        }
    }
}
