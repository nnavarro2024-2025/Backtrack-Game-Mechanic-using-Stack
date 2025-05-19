/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Students Account
 */
public class Mavenproject3 {

    public static void main(String[] args) {
 Scanner scan = new Scanner(System.in);
        Random random = new Random();
        
        int playerHP = 100;
        int playerMaxDmg = 50;
        int playerMinDmg = 5;
        int playerHealAmount = 20;
        int playerHealCount = 5;  
        
        int botHP = 100;
        int botMaxDmg = 10;
        int botMinDmg = 3;
        int botStunnedTurns = 0;

        int turnCount = 0;
        
        System.out.println("Welcome to the Battle Game!");
        System.out.println("You are facing a fierce monster.");
        
        
        while (playerHP > 0 && botHP > 0) {
            System.out.println("\n---- Turn " + (turnCount + 1) + " ----");
            if (isPlayerTurn(turnCount)) {
                System.out.println("Your HP: " + playerHP);
                System.out.println("Monster HP: " + botHP);
                System.out.println("What would you like to do?");
                System.out.println("1. Attack");
                System.out.println("2. Stun ");
                System.out.println("3. Heal (" + playerHealCount + " left)");
                System.out.print("Enter your  Best Choice: ");
                
                String choice = scan.nextLine();
                
                switch(choice) {
                    case "1":
                        int playerDmg = playerMinDmg + random.nextInt(playerMaxDmg - playerMinDmg + 1);
                        botHP -= playerDmg;
                        System.out.println("You attack the monster and deal " + playerDmg + " damage!");
                        if (botHP <= 0) {
                            botHP = 0;
                            System.out.println("The monster has been defeated! You win!");
                            break;
                        }
                        break;
                    case "2":
                        if (botStunnedTurns == 0) {
                            botStunnedTurns = 1;
                            System.out.println("You stunned the monster! It will miss its next turn.");
                        } else {
                            System.out.println("The monster is already stunned. You wasted your turn.");
                        }
                        break;
                    case "3":
                        if (playerHealCount > 0) {
                            playerHP += playerHealAmount;
                            if (playerHP > 100) playerHP = 100;
                            playerHealCount--;
                            System.out.println("You healed yourself for " + playerHealAmount + " HP! Current HP: " + playerHP);
                        } else {
                            System.out.println("You have no heals left!");
                            continue;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. You lose your turn.");
                        break;
                }
                
                turnCount++;
                
            } else {
                if (botStunnedTurns > 0) {
                    System.out.println("The monster is stunned and skips its turn.");
                    botStunnedTurns--;
                } else {
                    int botDmg = botMinDmg + random.nextInt(botMaxDmg - botMinDmg + 1);
                    playerHP -= botDmg;
                    if (playerHP < 0) playerHP = 0;
                    System.out.println("The monster attacks and deals " + botDmg + " damage to you!");
                    System.out.println("Your HP: " + playerHP);
                    if (playerHP <= 0) {
                        System.out.println("You have been defeated by the monster. Game Over.");
                        break;
                    }
                }
                turnCount++;
            }
        }
        
        System.out.println("\nThanks for playing!");
        scan.close();
    }
    
    static boolean isPlayerTurn(int turn) {
        return (turn % 2 == 0);
    }
}