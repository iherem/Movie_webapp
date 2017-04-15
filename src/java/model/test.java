/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import static model.Movie.amountPage;

/**
 *
 * @author mosza16
 */
public class test {
    public static void main(String[] args) {
           int amount = 0;
        int amountMovies = 1001;
        int AmountEachPage=10;
        if (AmountEachPage <= amountMovies) {
            if (amountMovies % AmountEachPage == 0) {
                amount = amountMovies / AmountEachPage;
            } else {
                amount = (amountMovies / AmountEachPage)+1;
            }
        }
        System.out.println(amount);
    }
}
