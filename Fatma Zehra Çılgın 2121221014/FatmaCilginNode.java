package tombala;

public class FatmaCilginNode<T> {

    T data;
    FatmaCilginNode<T> nextRow; // Bir sonraki satırdaki aynı sütun
    FatmaCilginNode<T> nextColumn;//Bir sonraki sütundaki aynı satır 

    public FatmaCilginNode(T data) {
        this.data = data;
        this.nextRow = null;
        this.nextColumn = null;
    }

}
