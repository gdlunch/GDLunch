package com.labuda.gdlunch.mvc.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MealVoucherController {

    private static List<CombinationWithSum> combinationsWithTenPiecesOfEach = createCombinationsUpToTen();

    static {
        combinationsWithTenPiecesOfEach.sort(Comparator.comparingInt(o -> o.getSum()));
    }

    @RequestMapping(value = "/vouchers", method = RequestMethod.GET)
    public String mealVoucherPage(Model model) {
        return "mealvouchers";
    }

    @RequestMapping(value = "/vouchers", method = RequestMethod.POST)
    public String withSums(@RequestParam String sum, Model model) {
        int parsedSum;
        try {
            parsedSum = Integer.parseInt(sum);
        } catch (NumberFormatException e) {
            // Show alert
            model.addAttribute("message", "Your input [" + sum + "] was not a valid number");
            model.addAttribute("alertClass", "alert-danger");
            return mealVoucherPage(model);
        }

        CombinationWithSum top = combinationsWithTenPiecesOfEach.get(0);
        CombinationWithSum bottom = new CombinationWithSum(new ArrayList<>());

        for(int i = 0; i < combinationsWithTenPiecesOfEach.size(); i++) {
            if (combinationsWithTenPiecesOfEach.get(i).getSum() > parsedSum) {
                if (i == 0) {
                    break;
                }
                top = combinationsWithTenPiecesOfEach.get(i);
                bottom = combinationsWithTenPiecesOfEach.get(i-1);
                break;
            }
        }

        model.addAttribute("topSum", top);
        model.addAttribute("botSum", bottom);
        model.addAttribute("botUnder", parsedSum - bottom.getSum());
        model.addAttribute("topAbove", top.getSum() - parsedSum);
        return "mealvouchers";
    }

    private static List<CombinationWithSum> createCombinationsUpToTen() {
        List<CombinationWithSum> allCombinationsUpToTen = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Generator.combination(80, 35)
                    .multi(i)
                    .stream()
                    .forEach(combination -> allCombinationsUpToTen.add(new CombinationWithSum(combination)));
        }
        return allCombinationsUpToTen;
    }

    public static class CombinationWithSum {

        private final int countOf80;
        private final int countOf35;
        private final int sum;

        public CombinationWithSum(final List<Integer> combination) {
            this.countOf80 = combination.stream().filter(val -> val.equals(80)).toArray().length;
            this.countOf35 = combination.stream().filter(val -> val.equals(35)).toArray().length;
            this.sum = this.countOf35 * 35 + this.countOf80 * 80;
        }

        public int getSum() {
            return sum;
        }

        public int getCountOf80() {
            return countOf80;
        }

        public int getCountOf35() {
            return countOf35;
        }
    }

}
