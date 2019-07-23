package com.labuda.gdlunch.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for the meal voucher calculator page
 */
@Controller
public class MealVoucherController {

    /**
     * Static list of combinations
     */
    private static List<CombinationWithSum> combinationsWithTenPiecesOfEach = createCombinationsUpToTen();

    static {
        // Sorts the combinations by the sum in ascending order
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

        for (int i = 0; i < combinationsWithTenPiecesOfEach.size(); i++) {
            if (combinationsWithTenPiecesOfEach.get(i).getSum() > parsedSum) {
                if (i == 0) {
                    break;
                }
                top = combinationsWithTenPiecesOfEach.get(i);
                bottom = combinationsWithTenPiecesOfEach.get(i - 1);
                break;
            }
        }

        model.addAttribute("topSum", top);
        model.addAttribute("botSum", bottom);
        model.addAttribute("botUnder", parsedSum - bottom.getSum());
        model.addAttribute("topAbove", top.getSum() - parsedSum);
        return mealVoucherPage(model);
    }

    /**
     * Creates the combinations of up to ten meal vouchers
     *
     * @return combinations of meal vouchers
     */
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

    /**
     * Simple meal voucher combinations representation
     */
    public static class CombinationWithSum {

        /**
         * Count of vouchers valued at 80
         */
        private final int countOf80;

        /**
         * Count of vouchers valued at 35
         */
        private final int countOf35;

        /**
         * Sum of the combination
         */
        private final int sum;

        /**
         * Constructor
         *
         * @param combination list of values combined
         */
        public CombinationWithSum(final List<Integer> combination) {
            this.countOf80 = combination.stream().filter(val -> val.equals(80)).toArray().length;
            this.countOf35 = combination.stream().filter(val -> val.equals(35)).toArray().length;
            this.sum = this.countOf35 * 35 + this.countOf80 * 80;
        }

        /**
         * Getter for sum
         *
         * @return sum of the combination
         */
        public int getSum() {
            return sum;
        }

        /**
         * Getter for the count of vouchers valued at 80
         *
         * @return count of vouchers valued at 80
         */
        public int getCountOf80() {
            return countOf80;
        }

        /**
         * Getter for the count of vouchers valued at 35
         *
         * @return count of vouchers valued at 35
         */
        public int getCountOf35() {
            return countOf35;
        }
    }

}
