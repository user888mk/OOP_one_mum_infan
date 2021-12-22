package service;

import model.Infant;
import model.Mum;
import repository.MumRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MumService {

    public void loadData(MumRepository mumRepository) {
        File file = new File("mamy.txt");

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(" ");
                List<Infant> infantList = new ArrayList<>();

                Mum mum = new Mum(Long.parseLong(splittedArray[0]), splittedArray[1],
                        Integer.parseInt(splittedArray[2]), infantList);
                mumRepository.getMums().add(mum);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Mum> findMums(List<Infant> infants) {
        List<Mum> filteredMums = new ArrayList<>();

        for (Infant infant : infants) {
            if (infant.getMum().getAge() < 25 && infant.getWeight() > 4000)
                filteredMums.add(infant.getMum());
        }
        return filteredMums;
    }

    public List<Mum> twins(List<Mum> mums) {
        List<Mum> mumWithSiblings = new ArrayList<>();
        for (Mum mum : mums) {
            if (mum.getInfantList() != null && !mum.getInfantList().isEmpty()) {
                List<Infant> infantList = mum.getInfantList();
                for (int i = 1; i < infantList.size(); i++) {
                    Infant searchInfant = infantList.get(i - 1);
                    if (infantList.get(i).getBornDay().equals(searchInfant.getBornDay())) {
                        mumWithSiblings.add(mum);
                        break;
                    }
                }
            }
        }
        return mumWithSiblings;
    }
}