package com.example.Task.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
public class MeasurementController {
    @GetMapping("/convert-measurements")
    public List<Integer> convertMeasurements(@RequestParam("measurements") String word) {
        ArrayList<String> wholeWord = new ArrayList<String>();
        ArrayList<Integer> sum1 = new ArrayList<Integer>();
        sum1.clear();
        String partOfWord;
        int sum = 0;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int value = (int) letter - 96; // convert character to numerical value
            value = i + value;
            if (value < 0 || value > 26) {
                value = 0; // handle invalid characters
            }

            if(value >= word.length()){
                value = word.length();
                partOfWord = word.substring(i, value);
            }
            else{
                partOfWord = word.substring(i+1, value +1);
            }

            for (int j = 0; j < partOfWord.length(); j++) {
                if (partOfWord.charAt(j) == 'z') {
                    partOfWord = word.substring(i + 1, value + 2);
                    value = value + j + 1;
                }
            }
            System.out.println(partOfWord);

            wholeWord.add(partOfWord);

            i = value;
        }

        System.out.print("[");
        for (int i = 0; i < wholeWord.size(); i++) {
            String firstPart = wholeWord.get(i);
            sum = 0;
            for (int j = 0; j < firstPart.length(); j++) {
                char letter = firstPart.charAt(j);
                int value = (int) letter - 96;
                if (value < 0 || value > 26) {
                    value = 0; // handle invalid characters
                }
                sum = sum + value;
            }
            if (i + 1 == wholeWord.size()) {
                System.out.print(sum);
                sum1.add(sum);
            } else {
                System.out.print(sum + ", ");
                sum1.add(sum);
            }
        }
        System.out.print("]");
        return sum1;
    }
}
