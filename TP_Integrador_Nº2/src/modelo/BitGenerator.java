package modelo;

import java.io.FileOutputStream;

public class BitGenerator {
    private boolean[] bits = new boolean[8];
    private byte byteToWrite;
    private int index = 0;
    FileOutputStream fos;

    public BitGenerator(FileOutputStream fos) {
        this.fos = fos;
    }

    public void writeBit(int bit) {
        if (bit == 0) {
            bits[index] = false;
        } else {
            bits[index] = true;
        }
        index++;
        if (index == 8) {
            for (int i = 0; i < 8; i++) {
                byteToWrite = (byte) (byteToWrite << 1);
                if (bits[i]) {
                    byteToWrite = (byte) (byteToWrite | 1);
                }
            }
            try {
                fos.write(byteToWrite);
                index = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void flush() {
        if (index > 0) {
            for (int i = index; i < 8; i++) {
                bits[i] = false;
            }
            for (int i = 0; i < 8; i++) {
                byteToWrite = (byte) (byteToWrite << 1);
                if (bits[i]) {
                    byteToWrite = (byte) (byteToWrite | 1);
                }
            }
            try {
                fos.write(byteToWrite);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
