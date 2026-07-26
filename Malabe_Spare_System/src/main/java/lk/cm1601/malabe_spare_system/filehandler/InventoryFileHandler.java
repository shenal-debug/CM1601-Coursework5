package lk.cm1601.malabe_spare_system.filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InventoryFileHandler {

    public void readInventoryFile() {

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader("src/main/resources/data/inventory_legacy.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);

            }

            reader.close();

        } catch (IOException e) {

            System.out.println("File cannot be read.");

        }

    }

    public void savePart(lk.cm1601.malabe_spare_system.model.Part part) {

        try {

            java.io.FileWriter writer = new java.io.FileWriter(
                    "src/main/resources/data/inventory_legacy.txt", true);

            writer.write(
                    part.getPartCode() + "," +
                            part.getPartName() + "," +
                            part.getBrand() + "," +
                            part.getPrice() + "," +
                            part.getQuantity() + "," +
                            part.getCategory() + "," +
                            part.getDate() + "," +
                            part.getImage() +
                            System.lineSeparator()
            );

            writer.close();

        } catch (IOException e) {

            System.out.println("Unable to save part.");

        }

    }

}